package com.primeton.web.test.demo1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weibing@primeton.com
 * @date 2019/10/12 17:52
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    @RequestMapping("/h1")
    public String hello(@RequestParam("p1") String p1, @RequestParam("p2") String p2) {
        if (true) {
            throw new RuntimeException("false");
        }
        return "hello " + p1 + ", msg:" + p2;
    }
}
