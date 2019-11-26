package com.spring.demo.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.emoji.EmojiUtil;
import org.junit.Test;

/**
 * @author weibing@primeton.com
 * @date 2019/10/29 11:33
 */
public class UserTest {

    @Test
    public void test3() {
        String alias = EmojiUtil.toAlias("😄");//:smile:
        System.out.println(alias);
    }

    @Test
    public void test2() {
        //StringBuilder
        TimeInterval timer = DateUtil.timer();
        StringBuilder b2 = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            b2.append("test");
            b2 = new StringBuilder();
        }
        Console.log(timer.interval());
        //StrBuilder
        timer.restart();
        StrBuilder builder = StrBuilder.create();
        for (int i = 0; i < 1000000; i++) {
            builder.append("test");
            builder.reset();
        }
        Console.log(timer.interval());
    }

    @Test
    public void test1() {
        String s = SecureUtil.md5("China");
        System.out.println(s);
    }

    @Test
    public void name() {
        System.out.println(123);
    }
}