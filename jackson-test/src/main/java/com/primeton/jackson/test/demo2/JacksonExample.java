package com.primeton.jackson.test.demo2;

/**
 * @author weibing@primeton.com
 * @date 2019/10/10 14:02
 */
public class JacksonExample {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = Class.forName("java.lang.String");
        System.out.println(clazz.newInstance().getClass());

    }
}
