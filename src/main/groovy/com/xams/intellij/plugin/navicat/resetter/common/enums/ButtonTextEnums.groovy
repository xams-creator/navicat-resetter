package com.xams.intellij.plugin.navicat.resetter.common.enums


enum ButtonTextEnums {

    RESET("Reset", ""),
    CANCEL("Cancel", "")

    String value
    String description

    ButtonTextEnums(String value, String description) {
        this.value = value
        this.description = description
    }

}
