package com.ljs.demo.common.Enums.enumsTest;

public enum EnumSwitch {

    RED, GREEB, BLANK, YELLOW;

    public static void main(String[] args) {

        EnumSwitch enumSwitch = EnumSwitch.RED;

        switch (enumSwitch) {
            case RED:
                enumSwitch = EnumSwitch.GREEB;
                break;
            case YELLOW:
                enumSwitch = EnumSwitch.RED;
                break;
            case GREEB:
                enumSwitch = EnumSwitch.BLANK;
                break;

        }

        System.out.println(enumSwitch);

    }
}
