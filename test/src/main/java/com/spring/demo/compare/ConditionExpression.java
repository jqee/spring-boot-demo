/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 20:33:59 
 *******************************************************************************/

package com.spring.demo.compare;

/**
 * ConditionExpression.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public interface ConditionExpression {

    /**
     * 获取表达式运算结果
     *
     * @param ctx 上下文
     * @return true or false
     */
    boolean value(ExpContext ctx);
}
