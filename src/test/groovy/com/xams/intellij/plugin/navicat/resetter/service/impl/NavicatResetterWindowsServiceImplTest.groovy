package com.xams.intellij.plugin.navicat.resetter.service.impl

import com.xams.intellij.plugin.navicat.resetter.util.RegSimpleCommandExecutor
import org.junit.Test


final class NavicatResetterWindowsServiceImplTest {


    @Test
    void searchNavicat() {
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

        println(regs)

//        String root = "HKEY_CURRENT_USER\\SOFTWARE\\Classes\\CLSID"
//
//        process.text.eachLine {
//            if (!it.isEmpty()) {
//                String id = "${it}\\info"
//                Process process1
//                try {
//                    process1 = "reg query ${id}".execute()
//                    int length = process1.text.length();
//                    if (length > 0) {
//                        removed.add(it)
//                    }
//                } catch (Exception ignore) {
//
//                }
//            }
//            return
//        }
//
//        removed.each {
//            Process process1 = "reg delete ${it} /f".execute()
////            println("reg delete ${it} /va")
//        }
    }


    @Test
    void searchCLSID() {
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

        println(regs)

//        regs.each {
//            RegDeleteSimpleCommandExecutor.execute()
//        }


    }


}
