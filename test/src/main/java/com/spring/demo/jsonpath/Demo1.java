package com.spring.demo.jsonpath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weibing@primeton.com
 * @date 2019/11/26 16:34
 */
public class Demo1 {
    public static void main(String[] args) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>(1);
        User user = User.builder().id("aa").name("nn").build();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(map);
        map.put("user", user);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(mapper.writeValueAsString(map));
        String userId = JsonPath.read(document, "user.id");
        System.out.println(userId);
    }

    @Data
    @Builder
    static class User {
        private String id;
        private String name;
    }
}
