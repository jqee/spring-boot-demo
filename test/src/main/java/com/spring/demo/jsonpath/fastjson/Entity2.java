package com.spring.demo.jsonpath.fastjson;

import lombok.Builder;
import lombok.Data;

/**
 * @author weibing@primeton.com
 * @date 2019/11/28 9:31
 */
@Data
@Builder
public class Entity2 {

    private Integer id;
    private String name;
    private Object value;


}
