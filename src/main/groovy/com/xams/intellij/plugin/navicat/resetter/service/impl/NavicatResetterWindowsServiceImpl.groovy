package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.xams.intellij.plugin.navicat.resetter.common.Constants
import com.xams.intellij.plugin.navicat.resetter.common.enums.NotificationContentEnums
import com.xams.intellij.plugin.navicat.resetter.service.NavicatResetterService

@Service
final class NavicatResetterWindowsServiceImpl implements NavicatResetterService {

    final Project project

    NavicatResetterWindowsServiceImpl(Project project) {
        this.project = project
    }

    @Override
    boolean supports() {
        return SystemInfo.isWindows
    }

    @Override
    void apply() {
        List<String> removed = [
            "HKEY_CURRENT_USER\\SOFTWARE\\PremiumSoft\\Navicat\\Update",
            "HKEY_CURRENT_USER\\SOFTWARE\\PremiumSoft\\Navicat\\Registration15XCS",
            "HKEY_CURRENT_USER\\SOFTWARE\\PremiumSoft\\Navicat\\Registration16XCS"
        ]
        String root = "HKEY_CURRENT_USER\\SOFTWARE\\Classes\\CLSID"

        Process process = "reg query ${root}".execute()
        process.text.eachLine {
            if (!it.isEmpty()) {
                String id = "${it}\\info"
                Process process1
                try {
                    process1 = "reg query ${id}".execute()
                    int length = process1.text.length();
                    if (length > 0) {
                        removed.add(it)
                    }
                } catch (Exception ignore) {

                }
            }
            return
        }

        removed.each {
            Process process1 = "reg delete ${it} /f".execute()
//            println("reg delete ${it} /va")
        }
        Notifications.Bus.notify(
            Constants.GROUP.createNotification(
                NotificationContentEnums.OK.getValue(), NotificationType.INFORMATION
            ),
            project
        )
    }


}
