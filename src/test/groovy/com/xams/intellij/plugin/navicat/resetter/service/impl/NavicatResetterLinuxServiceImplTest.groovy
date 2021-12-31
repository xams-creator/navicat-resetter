package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.xams.intellij.plugin.navicat.resetter.util.RegSimpleCommandExecutor
import org.junit.Test


final class NavicatResetterLinuxServiceImplTest {


    @Test
    void apply() {
        File dir = new File(System.getProperty('user.home') + '/.config/navicat/MySQL')
        if (dir.exists()) {
            dir.deleteDir()
        }
    }


}
