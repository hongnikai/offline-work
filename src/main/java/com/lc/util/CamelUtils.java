package com.lc.util;

import org.apache.commons.lang3.StringUtils;

/**
 * string转换驼峰
 *
 * @author lc 2022/9/13 2:28 PM
 */
public class CamelUtils {

    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        if (StringUtils.isEmpty(name)) {
            return "";
        } else if (!name.contains("_")) {
            return name.toLowerCase();
        }
        String[] camels = name.split("_");
        for (String camel : camels) {
            if (camel.isEmpty()) {
                continue;
            }
            if (result.length() == 0) {
                result.append(camel.toLowerCase());
            } else {
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


}
