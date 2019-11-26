package com.primeton.jackson.test.demo1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weibing@primeton.com
 * @date 2019/10/10 13:39
 */
public class JacksonExample {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = createDummyUser();
        mapper.writeValue(new File("user.json"), user);
        String jsonInString = mapper.writeValueAsString(user);
        System.out.println(jsonInString);
        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(jsonInString);
    }

    private static User createDummyUser() {
        User user = new User();
        user.setName("rich");
        user.setAge(33);
        List<String> msg = new ArrayList<>();
        msg.add("hello jackson 1");
        msg.add("hello jackson 2");
        msg.add("hello jackson 3");
        user.setMessages(msg);
        return user;
    }
}
