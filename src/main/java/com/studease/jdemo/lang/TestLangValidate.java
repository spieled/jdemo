package com.studease.jdemo.lang;

import org.apache.commons.lang3.StringUtils;

/**
 * Author: liushaoping
 * Date: 2016/3/28.
 */
public class TestLangValidate {

    public static void main(String[] args) {
        // Validate.isTrue(1>2, "%s must greater than %s", 1, 2);
        System.out.println(StringUtils.stripEnd(" 12,4101,,", ","));
        System.out.println(StringUtils.stripEnd("12,4101,", ","));
        System.out.println(StringUtils.stripEnd("12,4101", ","));

        Integer.parseInt("-12");
        Integer.parseInt("+12");

        System.out.println(StringUtils.appendIfMissing("2", "轴"));
        System.out.println(StringUtils.appendIfMissing("2轴", "轴"));


    }

}
