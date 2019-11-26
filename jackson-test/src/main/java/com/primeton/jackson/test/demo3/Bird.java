package com.primeton.jackson.test.demo3;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author weibing@primeton.com
 * @date 2019/10/12 11:39
 */
public class Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Bird is flying...");
        try {
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
