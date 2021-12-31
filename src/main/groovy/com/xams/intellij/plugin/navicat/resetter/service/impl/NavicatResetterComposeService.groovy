package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.xams.intellij.plugin.navicat.resetter.common.Constants
import com.xams.intellij.plugin.navicat.resetter.common.enums.NotificationContentEnums
import com.xams.intellij.plugin.navicat.resetter.service.NavicatResetterService

@Service
final class NavicatResetterComposeService implements NavicatResetterService {

    final Project project

    final List<NavicatResetterService> services

    NavicatResetterComposeService(Project project) {
        this.project = project
        this.services = new LinkedList<>()
        this.services.add(project.getService(NavicatResetterLinuxServiceImpl))
        this.services.add(project.getService(NavicatResetterWindowsServiceImpl))
        this.services.add(project.getService(NavicatResetterMacServiceImpl))
    }

    @Override
    boolean supports() {
        return true
    }

    @Override
    void apply() {
        this.services.each { service ->
            if (service.supports()) {
                service.apply()
                publish(project, Constants.GROUP)
            }
        }
    }

    void publish(Project project, NotificationGroup group) {
        Notifications.Bus.notify(
            group.createNotification(
                NotificationContentEnums.OK.getValue(), NotificationType.INFORMATION
            ),
            project
        )
    }
}
