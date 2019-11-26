package com.primeton.cloud.test.demo1;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author weibing@primeton.com
 * @date 2019/10/12 16:58
 */
@FeignClient(value = "hello-service",path = "/api/hello")
public interface HelloService {
    @GetMapping("/h1")
    String hello(@RequestParam("p1") String p1, @RequestParam("p2") String p2);
}
