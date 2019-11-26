package com.primeton.cloud.test.demo2;

import com.google.common.base.CaseFormat;

/**
 * @author weibing@primeton.com
 * @date 2019/10/17 15:27
 */
public class A {
    public static String toCamel(String str) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str);
    }

    public static String toUnderScore(String str) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    public static void main(String[] args) {
        System.out.println(toCamel("Aaa_cc"));
    }

}
