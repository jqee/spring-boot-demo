package com.spring.demo.jsonpath.fastjson;

import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import lombok.var;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author weibing@primeton.com
 * @date 2019/11/28 9:32
 */
public class EntityTest {

    @Test
    @SuppressWarnings("unchecked")
    public void test8() {
        var entity = Entity.builder().id(null).name("hello").build();
        var map = Collections.singletonMap("e", entity);
        var result = (Collection<String>) JSONPath.eval(map, "e.keySet()");
        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.contains("name"));

        entity.setId(1L);
        result = (Collection<String>) JSONPath.eval(map, "e.keySet()");
        Assert.assertEquals(2, result.size());
        Assert.assertTrue(result.contains("id"));
        Assert.assertTrue(result.contains("name"));

        Assert.assertEquals(result, JSONPath.keySet(map, "e"));
        Assert.assertEquals(result, new JSONPath("e").keySet(map));

    }

    @Test
    @SuppressWarnings("unchecked")
    public void test7() {
        var departs1 = new ArrayList<>();
        departs1.add(Collections.singletonMap("id", 1001L));
        departs1.add(Collections.singletonMap("id", 1002L));
        departs1.add(Collections.singletonMap("id", 1003L));
        var departs2 = new ArrayList<>();
        departs2.add(Collections.singletonMap("id", 2001L));
        departs2.add(Collections.singletonMap("id", 2002L));
        departs2.add(Collections.singletonMap("id", 2003L));
        var departs3 = new ArrayList<>();
        departs3.add(Collections.singletonMap("id", 12001L));
        departs3.add(Collections.singletonMap("id", 12002L));
        departs3.add(Collections.singletonMap("id", 12003L));
        departs1.add(departs3);
        var company = new HashMap<>();
        company.put("departs1", departs1);
        company.put("departs2", departs2);
        var root = Collections.singletonMap("company", company);
        Console.log(JSON.toJSONString(root));
        var idList = (List<Long>) JSONPath.eval(root, "$..id");
        Assert.assertEquals(9, idList.size());
        Console.log(JSON.toJSONString(idList));


    }


    @Test
    public void test6() {
        var entity = Entity.builder().id(1001L).name("ljw2083").build();
        Assert.assertTrue(JSONPath.containsValue(entity, "id", 1001));
        Assert.assertSame(entity, JSONPath.eval(entity, "[id = 1001]"));
        Assert.assertNull(JSONPath.eval(entity, "[id = 1002]"));
        JSONPath.set(entity, "id", 1003L);
        Assert.assertEquals(1003, entity.getId().intValue());
        JSONPath.set(entity, "value", new int[0]);
        JSONPath.arrayAdd(entity, "value", 1, 2, 3);
        System.out.println(JSON.toJSONString(entity));

    }


    @Test
    @SuppressWarnings("unchecked")
    public void test5() {
        var list = new ArrayList<Entity>();
        list.add(Entity.builder().id(1001L).name("wenshao").build());
        list.add(Entity.builder().id(1002L).name("ljw2083").build());
        list.add(Entity.builder().id(1003L).name("Yako").build());
        list.add(Entity.builder().id(1004L).name(null).build());
        var names = (List<Entity>) JSONPath.eval(list, "[id in (1001)]");
        Assert.assertEquals(1, names.size());
        Assert.assertSame(list.get(0), names.get(0));
    }


    @Test
    @SuppressWarnings("unchecked")
    public void test4() {
        var list = new ArrayList<Entity>();
        list.add(Entity.builder().name("wenshao").build());
        list.add(Entity.builder().name("ljw2083").build());
        list.add(Entity.builder().name("Yako").build());
        var names = (List<Entity>) JSONPath.eval(list, "[1:2]");
        Assert.assertEquals(2, names.size());
        Assert.assertSame(list.get(1), names.get(0));
        Assert.assertSame(list.get(2), names.get(1));
    }


    @Test
    @SuppressWarnings("unchecked")
    public void test3() {
        var list = new ArrayList<Entity>();
        list.add(Entity.builder().name("wenshao").build());
        list.add(Entity.builder().name("ljw2083").build());
        list.add(Entity.builder().name("Yako").build());
        var names = (List<Entity>) JSONPath.eval(list, "[1,2]");
        Assert.assertEquals(2, names.size());
        Assert.assertSame(list.get(1), names.get(0));
        Assert.assertSame(list.get(2), names.get(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test2() {
        var list = new ArrayList<Entity>();
        list.add(Entity.builder().name("wenshao").build());
        list.add(Entity.builder().name("ljw2083").build());
        var names = (List<String>) JSONPath.eval(list, "$.name");
        Assert.assertSame(list.get(0).getName(), names.get(0));
        Assert.assertSame(list.get(1).getName(), names.get(1));
    }

    @Test
    public void testCreate() {
        Entity entity = Entity.builder().id(123L).value(new Object()).build();
        Assert.assertSame(entity.getValue(), JSONPath.eval(entity, "$.value"));
        Assert.assertTrue(JSONPath.contains(entity, "$.value"));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.id", 123));
        Assert.assertTrue(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        Assert.assertEquals(2, JSONPath.size(entity, "$"));
        Assert.assertEquals(0, JSONPath.size(new Object(), "$"));
    }
}