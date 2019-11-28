package com.spring.demo.compare;

import com.alibaba.fastjson.JSONPath;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weibing@primeton.com
 * @date 2019/11/26 17:31
 */
@Data
public class ExpContextImpl implements ExpContext {

    private Map<String, Object> context = new ConcurrentHashMap<>();


    @Override
    public Object get(String path) {
        return JSONPath.eval(context, path);
    }

    @Override
    public boolean contains(String path) {
        return JSONPath.contains(context, path);
    }

    @Override
    public boolean containsValue(String key, Object value) {
        return JSONPath.containsValue(context, key, value);
    }

    @Override
    public int size(String path) {
        return JSONPath.size(context, path);
    }

    @Override
    public void put(String key, Object value) {
        context.put(key, value);
    }

    @Override
    public void putAll(Map<String, Object> map) {
        context.putAll(map);
    }
}
