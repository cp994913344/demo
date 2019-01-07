package com.ljs.demo.common.Enums;

public enum StatusEnums {

    START("1", "启用"),
    FORBIDDEN("2", "禁用"),
    REMOVE("3", "删除");

    private String code;

    private String description;

    private StatusEnums(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
