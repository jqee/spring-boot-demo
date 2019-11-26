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
public abstract class ExpContext {
    private Map<String, Object> data;

    public Object get(String key) {
        return data.get(key);
    }
}
