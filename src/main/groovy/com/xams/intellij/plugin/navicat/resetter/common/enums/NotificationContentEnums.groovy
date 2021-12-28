package com.xams.intellij.plugin.navicat.resetter.common.enums


enum NotificationContentEnums {

    OK("successful!", ""),
    FAILED("failed!", "")

    String value
    String description

    NotificationContentEnums(String value, String description) {
        this.value = value
        this.description = description
    }

}
