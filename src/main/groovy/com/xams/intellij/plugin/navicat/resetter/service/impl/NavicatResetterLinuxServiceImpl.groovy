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
        println('linux')
    }


}
