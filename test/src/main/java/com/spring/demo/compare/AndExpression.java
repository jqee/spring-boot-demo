/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2019-10-30 20:37:24 
 *******************************************************************************/

package com.spring.demo.compare;

import java.util.ArrayList;
import java.util.List;

/**
 * AndExpression.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public class AndExpression implements ConditionExpression {
    
    protected List<SimpleCondition> conditions = new ArrayList<>();

    
    
    public List<SimpleCondition> getConditions() {
        return conditions;
    }



    public void setConditions(List<SimpleCondition> conditions) {
        this.conditions = conditions;
    }



    @Override
    public boolean value(ExpContext ctx) {
        if (conditions == null) {
            return true;
        }
        
        for (SimpleCondition simpleCondition : conditions) {
            if (!simpleCondition.value(ctx)) {
                return false;
            }
        }
        return true;
    }
    
    
}
