package com.repo.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author weibing@primeton.com
 * @date 2019/11/5 11:43
 */
@SpringBootApplication
@ComponentScan({"com.primeton.bpm.portal.process.common.util","com.primeton.ft.bpm.repo.core.dao.mapper","com.primeton.bpm.portal.process.service.impl","com.primeton.ft.bpm.repo.core.service.impl"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
