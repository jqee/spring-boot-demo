/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2019-10-30 20:54:39 
 *******************************************************************************/

package com.spring.demo.compare;

/**
 * Operator. 操作符
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
public enum Operator {
    /**
     *
     */
    EQ("=","等于"),
    GT(">","大于"),
    GE(">=","不小于"),
    LT("<","小于"),
    LE("<=","不大于"),
    NE("!=","不等于"),
    IN("in","在集合"),
    NOT_IN("not in","不在集合"),
    CONTAINS("contains","包含"),
    NOT_CONTAINS("not contains","不包含"),
    START_WITH("start_with","开始于"),
    END_WITH("end_with","结束于"),
    IS_EMPTY("is empty","为空"),
    NOT_EMPTY("not empty","不为空"),
    
    ;
    
    private String symbol;
    private String description;
    
    private Operator(String symbol,String desc){
        this.symbol = symbol;
        this.description=desc;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
