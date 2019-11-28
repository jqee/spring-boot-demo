/*******************************************************************************
 *
 * Copyright (c) 2001-2019 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2019-10-30 20:48:29
 *******************************************************************************/

package com.spring.demo.compare;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * CondValue.
 *
 * @author haoyf (mailto: haoyf@primeton.com)
 */
@Data
@Builder
@AllArgsConstructor
public class ValueObject {

    private ValueType type = ValueType.Constant;
    private String propertyId;
    private String entityId;
    private String value;
    private Object realValue;

    public ValueObject() {
    }
}
