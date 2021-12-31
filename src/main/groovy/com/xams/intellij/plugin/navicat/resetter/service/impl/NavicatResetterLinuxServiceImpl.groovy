package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.intellij.openapi.components.Service
import com.intellij.openapi.util.SystemInfo
import com.xams.intellij.plugin.navicat.resetter.service.NavicatResetterService

@Service
final class NavicatResetterLinuxServiceImpl implements NavicatResetterService {


    @Override
    boolean supports() {
        return SystemInfo.isLinux
    }

    @Override
    void apply() {
        File file = new File(System.getProperty('user.home') + '/.config/navicat/MySQL')
        if (file.isDirectory() && file.exists()) {
            file.deleteDir()
        }
    }

}
