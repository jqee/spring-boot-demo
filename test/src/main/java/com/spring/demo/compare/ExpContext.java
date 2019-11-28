/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 21:35:49 
 *******************************************************************************/

package com.spring.demo.compare;

import java.util.Map;

/**
 * ExpContext.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public interface ExpContext {

    /**
     * 获取
     *
     * @param path 路径
     * @return 值
     */
    Object get(String path);

    /**
     * 查询
     *
     * @param path 路径
     * @return true or false
     */
    boolean contains(String path);

    /**
     * 查询
     *
     * @param key  路径
     * @param value 值
     * @return true or false
     */
    boolean containsValue(String key, Object value);

    /**
     * size
     *
     * @param path path
     * @return size
     */
    int size(String path);


    /**
     * put
     *
     * @param path  path
     * @param value value
     */
    void put(String path, Object value);


    /**
     * put all
     *
     * @param map map
     */
    void putAll(Map<String, Object> map);


}
