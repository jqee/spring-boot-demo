/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 20:43:36 
 *******************************************************************************/

package com.spring.demo.compare;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * SingleExpression.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
@Builder
@Data
@AllArgsConstructor
public class SimpleCondition implements ConditionExpression {


    public SimpleCondition() {
    }

    /**
     * 逻辑判断执行者
     */
    private ValueObject leftValue = new ValueObject();
    private ValueObject rightValue = new ValueObject();
    private Operator operator = Operator.IS_EMPTY;

    @Override
    public boolean value(ExpContext ctx) {
        return SimpleConditionHelper.value(this, ctx);
    }


    public ValueObject getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(ValueObject leftValue) {
        this.leftValue = leftValue;
    }

    public ValueObject getRightValue() {
        return rightValue;
    }

    public void setRightValue(ValueObject rightValue) {
        this.rightValue = rightValue;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

}
