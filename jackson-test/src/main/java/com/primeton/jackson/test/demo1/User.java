package com.primeton.jackson.test.demo1;

import lombok.Data;

import java.util.List;

/**
 * @author weibing@primeton.com
 * @date 2019/10/10 11:36
 */
@Data
public class User {
    private String name;
    private int age;
    private List<String> messages;
}
