/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 20:35:25 
 *******************************************************************************/

package com.spring.demo.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OrExpression.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public class OrExpression implements ConditionExpression {

    private List<AndExpression> andExprs = new ArrayList<>();

    public List<AndExpression> getAndExprs() {
        return andExprs;
    }

    public void setAndExprs(List<AndExpression> andExprs) {
        this.andExprs = andExprs;
    }

    public void add(AndExpression and) {
        andExprs.add(and);
    }

    public void add(AndExpression... and) {
        Arrays.asList(and).forEach(this::add);
    }


    @Override
    public boolean value(ExpContext ctx) {
        if (andExprs == null || andExprs.size() < 1) {
            return true;
        }

        for (AndExpression andExpression : andExprs) {
            if (andExpression.value(ctx)) {
                return true;
            }
        }

        return false;
    }


}
