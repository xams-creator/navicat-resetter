package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
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
            }
        }
    }


}
