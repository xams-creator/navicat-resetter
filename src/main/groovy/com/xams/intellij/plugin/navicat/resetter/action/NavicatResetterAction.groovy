package com.xams.intellij.plugin.navicat.resetter.action

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.xams.intellij.plugin.navicat.resetter.common.Constants
import com.xams.intellij.plugin.navicat.resetter.common.enums.ButtonTextEnums
import com.xams.intellij.plugin.navicat.resetter.service.NavicatResetterService
import com.xams.intellij.plugin.navicat.resetter.service.impl.NavicatResetterComposeService
import org.jetbrains.annotations.NotNull

class NavicatResetterAction extends AnAction {

    @Override
    void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT)
        NavicatResetterService service = project.getService(NavicatResetterComposeService)

        int value = Messages.showOkCancelDialog(
            project,
            Constants.PLUGIN_DIALOG_MESSAGE,
            Constants.PLUGIN_DIALOG_TITLE,
            ButtonTextEnums.RESET.getValue(),
            ButtonTextEnums.CANCEL.getValue(),
            Messages.getQuestionIcon()
        )
        Messages.OK == value && service.apply()
    }

}
