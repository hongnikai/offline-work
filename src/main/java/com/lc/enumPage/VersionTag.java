package com.lc.enumPage;

public enum VersionTag{

    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY");//记住要用分号结束


    VersionTag(String name) {
    }

    public VersionTag fromString(String str){

        return VersionTag.valueOf(str);

    }


}
