package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import com.xams.intellij.plugin.navicat.resetter.service.NavicatResetterService
import com.xams.intellij.plugin.navicat.resetter.util.RegSimpleCommandExecutor

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
        List<String> regs = []
        regs.addAll(searchNavicat())
        regs.addAll(searchCLSID())

        regs.each {
            RegSimpleCommandExecutor.delete([
                it, "/f"
            ])
        }
    }

    List<String> searchNavicat() {
        List<String> regs = []
        String command = "HKEY_CURRENT_USER\\SOFTWARE\\PremiumSoft\\Navicat\\"
        Process process = RegSimpleCommandExecutor.query([command])
        process.text.eachLine {
            if (it && it.startsWith(command)) {
                if (it.contains("Update") || it.contains("Registration")) {
                    regs.add(it)
                }
            }
            return null
        }
        return regs
    }

    List<String> searchCLSID() {
        List<String> regs = []
        String command = "HKEY_CURRENT_USER\\SOFTWARE\\Classes\\CLSID\\"
        Process process = RegSimpleCommandExecutor.query([command, "/s"])
        process.text.eachLine {
            if (it && it.startsWith(command)) {
                if (it.endsWith("Info")) {
                    regs.add(it.replaceFirst("Info", ""))
                }
            }
            return null
        }
        return regs
    }

}
