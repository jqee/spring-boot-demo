package com.spring.demo.compare;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;


/**
 * 条件判断帮助类<br>
 * 用于提供不同的条件判断场景<br>
 *
 * @author
 */
public final class SimpleConditionHelper {

    private static final Set<String> signalSet = new HashSet<>();

    static {
        signalSet.add(",");
        signalSet.add(";");
    }

    public static boolean value(SimpleCondition condition, ExpContext context) {
        Object left = getValue(condition.getLeftValue(), context);
        Object right = getValue(condition.getRightValue(), context);
        if (left == null && right == null) {
            return false;
        }
        return compare(left, right, condition.getOperator());
    }

    private static Object getValue(ValueObject valueObject, ExpContext context) {
        if (valueObject == null) {
            return null;
        }
        ValueType type = valueObject.getType();
        if (isConstant(type)) {
            return valueObject.getValue();
        }
        if (isVariable(type)) {
            return getValue(valueObject.getValue(), context);
        }
        return null;
    }

    private static Object getValue(String value, ExpContext context) {
        return context.get(value);
    }

    private static boolean isConstant(ValueType type) {
        return ValueType.Constant == type;
    }

    private static boolean isVariable(ValueType type) {
        return ValueType.Variable == type;
    }

    public static boolean compare(Object left, Object right, Operator operator) {
        switch (operator) {
            case EQ:
                return equals(left, right);
            case GE:
            case GT:
            case LE:
            case LT:
                return compareNumber(left, right, operator);
            case IN:
                return isIn(left, right);
            case NE:
                return notEquals(left, right);
            case NOT_IN:
                return notIn(left, right);
            case CONTAINS:
                return contains(left, right);
            case END_WITH:
                return isEndWith(left, right);
            case IS_EMPTY:
                return isEmpty(left);
            case NOT_EMPTY:
                return notEmpty(left);
            case START_WITH:
                return isStartWith(left, right);
            case NOT_CONTAINS:
                return isNotContains(left, right);
            default:
                return false;
        }
    }


    private static boolean compareNumber(Object left, Object right, Operator operator) {
        if (isAllDate(left, right)) {
            Date leftDate = (Date) left;
            Date rightDate = (Date) right;
            return compareNumber(leftDate.getTime(), rightDate.getTime(), operator);
        }
        if (!isAllNumber(left, right)) {
            return false;
        }
        BigDecimal leftNum = NumberUtil.toBigDecimal(toString(left));
        BigDecimal rightNum = NumberUtil.toBigDecimal(toString(right));
        switch (operator) {
            case LT:
                return NumberUtil.isLess(leftNum, rightNum);
            case LE:
                return NumberUtil.isLessOrEqual(leftNum, rightNum);
            case GT:
                return !NumberUtil.isLessOrEqual(leftNum, rightNum);
            case GE:
                return !NumberUtil.isLess(leftNum, rightNum);
            default:
                return false;
        }
    }

    private static boolean isAllDate(Object... params) {
        if (params.length < 1) {
            return false;
        }
        for (Object param : params) {
            if (!(param instanceof Date)) {
                return false;
            }
        }
        return true;
    }

    private static boolean contains(Object left, Object right) {
        return contains(toString(left), toString(right));
    }

    private static boolean contains(String left, String right) {
        return isNoneBlank(left, right) && left.contains(right);
    }

    private static boolean isNotContains(Object left, Object right) {
        return !contains(left, right);
    }

    private static boolean isStartWith(Object left, Object right) {
        return isStartWith(toString(left), toString(right));
    }

    private static boolean isStartWith(String left, String right) {
        return isNoneBlank(left, right) && left.startsWith(right);
    }

    private static boolean isEmpty(Object left) {
        return isEmpty(toString(left));
    }

    private static boolean isEmpty(String left) {
        return StringUtils.isBlank(left);
    }

    private static boolean notEmpty(Object left) {
        return !isEmpty(left);
    }

    private static boolean isEndWith(Object left, Object right) {
        return isEndWith(toString(left), toString(right));
    }

    private static boolean isEndWith(String left, String right) {
        return isNoneBlank(left, right) && left.endsWith(right);
    }

    private static boolean isNoneBlank(CharSequence... params) {
        return StringUtils.isNoneBlank(params);
    }

    private static String toString(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof String) {
            return (String) object;
        }
        return JSON.toJSONString(object);
    }

    @SuppressWarnings("unchecked")
    private static boolean isIn(Object left, Object right) {
        if (isAnyNull(left, right)) {
            return false;
        }
        if (right instanceof Map) {
            Map<Object, Object> map = (Map<Object, Object>) right;
            return map.containsKey(left);
        }
        if (right instanceof Collection) {
            Collection<Object> collection = (Collection<Object>) right;
            if (collection.contains(left) || collection.contains(JSON.toJSONString(left))) {
                return true;
            }

        }
        Class<?> rightClazz = right.getClass();
        if (rightClazz.isArray()) {
            int len = Array.getLength(right);
            for (int i = 0; i < len; ++i) {
                Object item = Array.get(right, i);
                if (equals(left, item)) {
                    return true;
                }
            }
        }
        // 根据逗号和分号分割查询
        if (checkSpilt(left, right)) {
            return true;
        }
        // 检查字符数组
        return checkJson(left, right);

    }

    private static boolean checkJson(Object left, Object right) {
        if (!(right instanceof String)) {
            return false;
        }
        String rightJson = (String) right;
        try {
            Object object = JSONObject.parse(rightJson);
            return isIn(left, object);
        } catch (JSONException e) {
            return false;
        }
    }

    private static boolean checkSpilt(Object left, Object right) {
        Set<String> rightSpiltSet;
        Set<String> leftSpiltSet;
        for (String signal : signalSet) {
            rightSpiltSet = spiltStrToSet(right, signal);
            leftSpiltSet = spiltStrToSet(left, signal);
            if (rightSpiltSet.containsAll(leftSpiltSet)) {
                return true;
            }
        }
        return false;
    }

    private static Set<String> spiltStrToSet(Object right, String signal) {
        return Sets.newHashSet(toString(right).split(signal));
    }


    private static boolean notIn(Object left, Object right) {
        return !isIn(left, right);
    }

    private static boolean equals(String left, String right) {
        BigDecimal leftNum = NumberUtil.toBigDecimal(left);
        BigDecimal rightNum = NumberUtil.toBigDecimal(right);
        return NumberUtil.equals(leftNum, rightNum);
    }

    private static boolean isAllNumber(Object... params) {
        if (params.length < 1) {
            return false;
        }
        for (Object param : params) {
            if (!NumberUtil.isNumber(toString(param))) {
                return false;
            }
        }
        return true;
    }

    private static boolean equals(Object left, Object right) {
        if (isAllNumber(left, right)) {
            return equals(toString(left), toString(right));
        }
        if (isAllDate(left, right)) {
            Date leftDate = (Date) left;
            Date rightDate = (Date) right;
            return equals(leftDate.getTime(), rightDate.getTime());
        }

        return left.equals(right);
    }

    private static boolean notEquals(Object left, Object right) {
        return !equals(left, right);
    }

    private static boolean isAnyNull(Object... params) {
        for (Object param : params) {
            if (Objects.isNull(param)) {
                return true;
            }
        }
        return false;
    }

}
