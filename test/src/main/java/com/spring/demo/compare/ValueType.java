/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 20:44:32 
 *******************************************************************************/

package com.spring.demo.compare;

/**
 * ValueType.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public enum ValueType {
    /**
     * 值类型
     */
    Variable("变量"),
    Constant("常量");

    private String description;

    public String getDescription() {
        return description;
    }

    ValueType(String description) {
        this.description = description;
    }

}
