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
    
     boolean value(ExpContext ctx) ;
}
