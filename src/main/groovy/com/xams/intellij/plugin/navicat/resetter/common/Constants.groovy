package com.xams.intellij.plugin.navicat.resetter.common

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup

final class Constants {

    final static String PLUGIN_DIALOG_TITLE = "Navicat Resetter"

    final static String PLUGIN_DIALOG_MESSAGE = "Please close navicat15 before executing!"

    final static NotificationGroup GROUP = new NotificationGroup(
        PLUGIN_DIALOG_TITLE,
        NotificationDisplayType.BALLOON,
        true
    )

}
