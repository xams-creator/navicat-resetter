package com.xams.intellij.plugin.navicat.resetter.action

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.SystemInfoRt
import org.jetbrains.annotations.NotNull

class NavicatResetterAction extends AnAction {

    private static String COMMON_TITLE = "Navicat Resetter"

    private final NotificationGroup group

    NavicatResetterAction() {
        super()
        this.group = new NotificationGroup(
            COMMON_TITLE, NotificationDisplayType.BALLOON, true
        );
    }

    @Override
    void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        int value = Messages.showOkCancelDialog(
            project,
            "Please close navicat15 before executing!",
            COMMON_TITLE,
            "Reset",
            "Cancel",
            Messages.getQuestionIcon());
        Messages.OK == value && apply(project)
    }

    void apply(Project project) {
        if (!SystemInfoRt.isWindows) {
            Notifications.Bus.notify(
                this.group.createNotification("暂时不支持此系统", NotificationType.INFORMATION),
                project
            )
            return
        }
        resetWindowsNavicat(project)
    }


    void resetWindowsNavicat(Project project) {
        List<String> removed = [
            "HKEY_CURRENT_USER\\SOFTWARE\\PremiumSoft\\Navicat\\Update",
            "HKEY_CURRENT_USER\\SOFTWARE\\PremiumSoft\\Navicat\\Registration15XCS"
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
            this.group.createNotification("执行成功", NotificationType.INFORMATION),
            project
        )
    }

}
