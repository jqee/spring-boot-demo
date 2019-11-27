package com.spring.demo.compare;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.spring.demo.compare.SimpleConditionHelper.compare;

/**
 * @author weibing@primeton.com
 * @date 2019/11/27 17:07
 */
public class SimpleConditionHelperTest {

    @Test
    public void hello() {
        List<String> aa = Collections.singletonList("cc");
        testCompare("cc", aa, Operator.IN, true);
        testCompare("1", "[\"1\",\"2\",\"3\"]", Operator.IN, true);
        testCompare(1, "[\"1\",\"2\",\"3\"]", Operator.IN, true);
        testCompare("4", "[\"1\",\"2\",\"3\"]", Operator.IN, false);
        testCompare("4", "{\"1\":\"2\"}", Operator.IN, false);
        testCompare("1", "{\"1\":\"2\"}", Operator.IN, true);
        testCompare("1", "1,2,3", Operator.IN, true);
        testCompare("1,2", "1,2,3", Operator.IN, true);
        testCompare("1.000000007", "1.000000007", Operator.CONTAINS, true);
        testCompare("abs", "b", Operator.NOT_CONTAINS, false);
        testCompare("abs", "b", Operator.CONTAINS, true);
        testCompare("hello", "world", Operator.GT, false);
        testCompare("", null, Operator.IS_EMPTY, true);
        testCompare("aaa", null, Operator.NOT_EMPTY, true);
        testCompare("99.000000000000000000000000000002", "99.000000000000000000000000000001", Operator.GE, true);
        testCompare("a", "ab", Operator.CONTAINS, false);
        testCompare("abc", "ab", Operator.CONTAINS, true);
        testCompare("ab", "abc", Operator.IN, false);
        Date now = DateUtil.date().toJdkDate();
        Date yesterday = DateUtil.yesterday().toJdkDate();
        testCompare(now, yesterday, Operator.LE, false);
        testCompare(now, yesterday, Operator.LT, false);
        testCompare(now, yesterday, Operator.GE, true);
        testCompare(now, yesterday, Operator.GT, true);
        testCompare(now, now, Operator.EQ, true);
        testCompare(now, now, Operator.LE, true);
    }

    private void testCompare(Object left, Object right, Operator operator, boolean expected) {
        Object result1;
        boolean result = compare(left, right, operator);
        if (Objects.equals(result, true)) {
            result1 = result + " ";
        } else {
            result1 = result + "";
        }
        Console.log("result: {} | {} {} {} ", result1, left, operator.getSymbol(), right);
        Assert.assertEquals(expected, result);

    }
}