package com.spring.demo.compare;

import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.var;
import org.junit.Test;

import java.util.Collections;

/**
 * @author weibing@primeton.com
 * @date 2019/11/28 14:40
 */
public class OrExpressionTest {
    @Test
    public void test1() {
        OrExpression expression = new OrExpression();
        AndExpression and1 = new AndExpression();
        AndExpression and2 = new AndExpression();
        expression.add(and1, and2);
        var l1 = ValueObject.builder().value("user.name").type(ValueType.Variable).build();
        var l2 = ValueObject.builder().value("user.id").type(ValueType.Variable).build();
        var r1 = ValueObject.builder().value("san").type(ValueType.Constant).build();
        var r2 = ValueObject.builder().value("1").type(ValueType.Constant).build();

        var r3 = ValueObject.builder().value("userList").type(ValueType.Variable).build();
        var r4 = ValueObject.builder().value("san").type(ValueType.Constant).build();
        var empty = ValueObject.builder().value("").type(ValueType.Constant).build();

        var s1 = SimpleCondition.builder().leftValue(l1).rightValue(r1).operator(Operator.CONTAINS).build();
        var s2 = SimpleCondition.builder().leftValue(l2).rightValue(r2).operator(Operator.EQ).build();
        var s3 = SimpleCondition.builder().leftValue(l1).rightValue(r3).operator(Operator.IN).build();
        var s5 = SimpleCondition.builder().leftValue(l1).rightValue(r4).operator(Operator.IN).build();


        var s4 = SimpleCondition.builder().leftValue(r2).operator(Operator.IS_EMPTY).build();
        var user = User.builder().id(1L).name("san").build();
        and1.add(s1, s2, s3, s5);
        and2.add(s4);

        ExpContext context = new ExpContextImpl();
        context.put("user", user);

        var a = Collections.singletonList("san");
        context.put("userList", a);

        boolean res = expression.value(context);
        Console.log(">>> final result: {}", res);
        Console.log(JSON.toJSONString(expression, SerializerFeature.DisableCircularReferenceDetect));

    }

    @Test
    public void name() {
        Console.log(123);
    }
}