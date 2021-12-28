package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.intellij.openapi.components.Service
import com.intellij.openapi.util.SystemInfo
import com.xams.intellij.plugin.navicat.resetter.service.NavicatResetterService

@Service
final class NavicatResetterMacServiceImpl implements NavicatResetterService {


    @Override
    boolean supports() {
        return SystemInfo.isMac
    }

    @Override
    void apply() {
        println('mac os')
    }


}
