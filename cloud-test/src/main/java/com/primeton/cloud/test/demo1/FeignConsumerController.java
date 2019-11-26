package com.primeton.cloud.test.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weibing@primeton.com
 * @date 2019/10/12 17:00
 */
@RestController
public class FeignConsumerController {
    private final HelloService helloService;

    @Autowired
    public FeignConsumerController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping
    public String hello(@RequestParam String p1, @RequestParam String p2) {
        System.out.println("fegin consumer get hello!");
        return helloService.hello(p1, p2);
    }

}
