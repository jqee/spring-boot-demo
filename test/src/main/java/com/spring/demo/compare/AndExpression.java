/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 20:37:24 
 *******************************************************************************/

package com.spring.demo.compare;

import cn.hutool.core.lang.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AndExpression.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public class AndExpression implements ConditionExpression {

    private List<SimpleCondition> conditions = new ArrayList<>();


    public List<SimpleCondition> getConditions() {
        return conditions;
    }


    public void setConditions(List<SimpleCondition> conditions) {
        this.conditions = conditions;
    }

    public void add(SimpleCondition and) {
        conditions.add(and);
    }

    public void add(SimpleCondition... and) {
        Arrays.asList(and).forEach(this::add);
    }


    @Override
    public boolean value(ExpContext ctx) {
        if (conditions == null || conditions.size() < 1) {
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
