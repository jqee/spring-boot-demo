package com.spring.demo.compare;

import lombok.Builder;
import lombok.Data;

/**
 * @author weibing@primeton.com
 * @date 2019/11/28 15:27
 */
@Builder
@Data
public class User {
    private Long id;
    private String name;
}
