/*******************************************************************************
 * $Header: /cvsroot/PTP50/workdir/eos/develop/src/server/com.primeton.eos.engine.core/src/main/java/com/primeton/ext/engine/core/SimpleConditionHelper.java,v 1.1 2013/10/11 18:53:49 haoyf Exp $
 * $Revision: 1.1 $
 * $Date: 2013/10/11 18:53:49 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2007-7-11
 *******************************************************************************/


package com.spring.demo.compare;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 条件判断帮助类<br>
 * 用于提供不同的条件判断场景<br>
 *
 * @author
 */
public final class SimpleConditionHelper {
    private static Map<String, Integer> operators = new HashMap<>();

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

    public static void main(String[] args) {
        String a = "3345";
        System.out.println(compare(a, a, Operator.CONTAINS));
    }

    private static boolean compare(Object left, Object right, Operator operator) {
        switch (operator) {
            case EQ:
                return equal(left, right);
            case GE:
                return greaterEqual(left, right);
            case GT:
                return greaterThan(left, right);
            case IN:
                return in(left, right);
            case LE:
                return lowerEqual(left, right);
            case LT:
                return lowerThan(left, right);
            case NE:
                return notEqual(left, right);
            case NOT_IN:
                return notIn(left, right);
            case CONTAINS:
                return contains(left, right);
            case END_WITH:
                return endWith(left, right);
            case IS_EMPTY:
                return isEmpty(left);
            case NOT_EMPTY:
                return notEmpty(left);
            case START_WITH:
                return startWith(left, right);
            case NOT_CONTAINS:
                return notContains(left, right);
            default:
                return false;
        }
    }

    private static boolean contains(Object left, Object right) {
        return contains(toString(left), toString(right));
    }

    private static boolean contains(String left, String right) {
        return isNoneBlank(left, right) && left.contains(right);
    }

    private static boolean notContains(Object left, Object right) {
        return !contains(left, right);
    }

    private static boolean startWith(Object left, Object right) {
        return startWith(toString(left), toString(right));
    }

    private static boolean startWith(String left, String right) {
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

    private static boolean endWith(Object left, Object right) {
        return endWith(toString(left), toString(right));
    }

    private static boolean endWith(String left, String right) {
        return isNoneBlank(left, right) && left.endsWith(right);
    }

    private static boolean isNoneBlank(CharSequence... params) {
        return StringUtils.isNoneBlank(params);
    }

    private static String toString(Object object) {
        return object == null ? null : String.valueOf(object);
    }

    private static boolean in(Object left, Object right) {
        return false;
    }

    private static boolean notIn(Object left, Object right) {
        return !in(left, right);
    }

    private static boolean lowerThan(Object left, Object right) {
        return false;
    }

    private static boolean lowerEqual(Object left, Object right) {
        return equal(left, right) || lowerThan(left, right);
    }

    private static boolean greaterThan(Object left, Object right) {
        return false;
    }

    private static boolean greaterEqual(Object left, Object right) {
        return equal(left, right) || greaterThan(left, right);
    }

    private static boolean equal(Object left, Object right) {
        return left.equals(right);
    }

    private static boolean notEqual(Object left, Object right) {
        return !equal(left, right);
    }


    public static final int GT = 1;
    public static final int GE = 2;
    public static final int E = 3;
    public static final int LE = 4;
    public static final int LT = 5;
    public static final int NE = 6;
    public static final int NULL = 7;
    public static final int NOT_NULL = 8;
    public static final int NOT_NULL_OR_EMPTY = 9;
    public static final int NULL_OR_EMPTY = 10;

    static {
        operators.put(">", GT);
        operators.put(">=", GE);
        operators.put("==", E);
        operators.put("<=", LE);
        operators.put("<", LT);
        operators.put("!=", NE);
        operators.put("GT", GT);
        operators.put("GE", GE);
        operators.put("EQ", E);
        operators.put("LE", LE);
        operators.put("LT", LT);
        operators.put("NE", NE);
    }

    /**
     * 根据操作符，判断两个boolean类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(boolean l, boolean r, String ops) {
        if (!operators.containsKey(ops)) {
            throw new RuntimeException("unknown relational operator:" + ops);
        }
        int op = operators.get(ops);

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断两个String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(String l, String r, String ops) {
        if (!operators.containsKey(ops)) {
            throw new RuntimeException("unknown relational operator:" + ops);
        }
        int op = operators.get(ops);
        if (l == null) {
            if (op == NULL || op == NULL_OR_EMPTY || (op == E && r == null) || (op == NE && r != null)) {
                return true;
            } else {
                return false;
            }
        }

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            case NULL:
                return l == null;
            case NOT_NULL:
                return l != null;
            case NULL_OR_EMPTY:
                return l == null || l.length() < 1;
            case NOT_NULL_OR_EMPTY:
                return l != null && l.length() > 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断boolean和String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(boolean l, String r, String ops) {
        boolean rValue = Boolean.parseBoolean(r);
        return compare(l, rValue, ops);
    }

    /**
     * 根据操作符，判断两个char类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(char l, char r, String ops) {
        if (!operators.containsKey(ops)) {
            //throw new RuntimeException("unknown relational operator:"+ops);
            //throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
        }
        int op = operators.get(ops);

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断char,String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(char l, String r, String ops) {
        if (r == null || r.length() != 1) {
            return false;
        }

        return compare(l, r.charAt(0), ops);
    }

    /**
     * 根据操作符，判断两个int类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(int l, int r, String ops) {
        if (!operators.containsKey(ops)) {
            //throw new RuntimeException("unknown relational operator:"+ops);
            throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037, new Object[]{ops});
        }
        int op = operators.get(ops);

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断int,String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(int l, String r, String ops) {
        int rValue = Double.valueOf(r).intValue();
        return compare(l, rValue, ops);
    }

    /**
     * 根据操作符，判断两个long类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(long l, long r, String ops) {
        if (!operators.containsKey(ops)) {
            //throw new RuntimeException("unknown relational operator:"+ops);
            throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037, new Object[]{ops});
        }

        int op = operators.get(ops);

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断long,String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(long l, String r, String ops) {
        long rValue = Double.valueOf(r).longValue();
        return compare(l, rValue, ops);
    }

    /**
     * 根据操作符，判断两个double类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(double l, double r, String ops) {
        if (!operators.containsKey(ops)) {
            //throw new RuntimeException("unknown relational operator:"+ops);
            throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037, new Object[]{ops});
        }

        int op = operators.get(ops);

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断double,String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(double l, String r, String ops) {
        double rValue = Double.valueOf(r).doubleValue();
        return compare(l, rValue, ops);
    }

    /**
     * 根据操作符，判断两个Date类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(Date l, Date r, String ops) {
        if (!operators.containsKey(ops)) {
            //throw new RuntimeException("unknown relational operator:"+ops);
            throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037, new Object[]{ops});
        }

        int op = operators.get(ops);
        if (l == null) {
            if (op == NULL || op == NULL_OR_EMPTY || (op == E && r == null) || (op == NE && r != null)) {
                return true;
            } else {
                return false;
            }
        }

        switch (op) {
            case GT:
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                return SimpleConditionUtil.compare(l, r) == 0;
            case LE:
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                return SimpleConditionUtil.compare(l, r) != 0;
            default:
                return false;
        }
    }

    /**
     * 根据操作符，判断Date,String类型的条件表达式
     *
     * @param l
     * @param r
     * @param ops
     * @return
     */
    public static boolean compare(Date l, String r, String ops) {
        Date rValue = DataUtil.toDate(r);
        return compare(l, rValue, ops);
    }

    /**
     * 根据操作符，判断两个Object类型的条件表达式
     *
     * @param l
     * @param r
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean compare(Object l, Object r) {
        String ops = "a";
        if (!operators.containsKey("a")) {
            //throw new RuntimeException("unknown relational operator:"+ops);
            throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037, new Object[]{ops});
        }
        int op = operators.get("a");

        //if both are Numbers; then comare as double values;
        boolean flag = false;
        if ((l instanceof Number && r instanceof Number) ||
                (l instanceof Number && r instanceof String) ||
                (l instanceof Boolean && r instanceof String) ||
                (l instanceof Date && r instanceof String) ||
                (l instanceof Date && r instanceof Date) ||
                (l instanceof String && r instanceof String) ||
                (l instanceof Character && r instanceof String) ||
                (l instanceof String && r instanceof Character)) {
            flag = true;
        }

        switch (op) {
            case GT:
                if (l == null || r == null) {
                    return false;
                }
                return SimpleConditionUtil.compare(l, r) > 0;
            case GE:
                if (l == null || r == null) {
                    return false;
                }
                return SimpleConditionUtil.compare(l, r) >= 0;
            case E:
                // TODO:如果flag==true了,l和r就不会是null.
                // if (l == null || r == null) {
                // return false;
                // }
                if (flag) {
                    return SimpleConditionUtil.compare(l, r) == 0;
                } else {
                    if (l == null) {
                        if (r == null) {
                            return true;
                        }
                        return false;
                    }
                    return l.equals(r);
                }
            case LE:
                if (l == null || r == null) {
                    return false;
                }
                return SimpleConditionUtil.compare(l, r) <= 0;
            case LT:
                if (l == null || r == null) {
                    return false;
                }
                return SimpleConditionUtil.compare(l, r) < 0;
            case NE:
                if (flag) {
                    return SimpleConditionUtil.compare(l, r) != 0;
                } else {
                    if (l == null) {
                        if (r == null)
                            return false;
                        return true;
                    }
                    return !l.equals(r);
                }
            case NULL:
                return l == null;
            case NOT_NULL:
                return l != null;
            case NULL_OR_EMPTY:
                if (l instanceof Number) {
                    return ((Number) l).doubleValue() == 0;  //BUG: 39194 数值类型为float、double时，当数值介于-1和1之间时，用NullOrEmpty判定时返回True!
                } else {
                    if (l == null) {
                        return true;
                    } else {
                        Class cls = l.getClass();
                        if (cls.isArray()) {
                            return Array.getLength(l) == 0;
                        } else if (cls == String.class) {
                            return ((String) l).length() < 1;
                        } else if (l instanceof Collection) {
                            return ((Collection) l).isEmpty();
                        }
                    }

                }
            case NOT_NULL_OR_EMPTY:
                if (l instanceof Number) {
                    return ((Number) l).intValue() != 0;
                } else {
                    if (l == null) {
                        return false;
                    } else {
                        Class cls = l.getClass();
                        if (cls.isArray()) {
                            return Array.getLength(l) > 0;
                        } else if (cls == String.class) {
                            return ((String) l).length() > 0;
                        } else if (l instanceof Collection) {
                            return !((Collection) l).isEmpty();
                        }
                    }
                }
            default:
                return false;
        }
    }

    /**
     * @param o              需要转的数组对象
     * @param componentClass 目标class
     * @return 转后的数组对象
     */
    public static Object array2Array(Object o, Class componentClass) {
        if (null == o)
            return o;
        if (o.getClass().isArray()) {
            if (null == componentClass) {
                return o;
            }
            if (o.getClass().getComponentType() != componentClass) {
                int length = Array.getLength(o);
                Object newArray = Array.newInstance(componentClass, length);
                Object tmp = null;
                for (int i = 0; i < length; i++) {
                    tmp = DataUtil.toType(componentClass, Array.get(o, i));
                    Array.set(newArray, i, tmp);
                }
                return newArray;
            } else {
                return o;
            }
        }
        throw new IllegalArgumentException(componentClass.getCanonicalName());
    }


//	/**
//	 * 根据操作符，判断两个boolean类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(boolean l,boolean r, String ops){
//		if(!operators.containsKey(ops)){
//			throw new RuntimeException("unknown relational operator:"+ops);
//		}
//		int op = operators.get(ops);
//
//		switch(op){
//		case GT:
//			return false;
//		case GE:
//			return false;
//		case E:
//			return l==r;
//		case LE:
//			return false;
//		case LT:
//			return false;
//		case NE:
//			return l!=r;
//		default:
//			return false;
//		}
//	}
//	/**
//	 * 根据操作符，判断两个String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(String l,String r, String ops){
//		if(!operators.containsKey(ops)){
//			throw new RuntimeException("unknown relational operator:"+ops);
//		}
//		int op = operators.get(ops);
//		if(l == null){
//            if(op == NULL || op == NULL_OR_EMPTY || op==E && r ==null){
//                return true;
//            }else{
//                return false;
//            }
//        }
//
//		switch(op){
//		case GT:
//			return l.compareTo(r)>0;
//		case GE:
//			return l.compareTo(r)>=0;
//		case E:
//			return l.compareTo(r)==0;
//		case LE:
//			return l.compareTo(r)<=0;
//		case LT:
//			return l.compareTo(r)<0;
//		case NE:
//			return l.compareTo(r)!=0;
//		case NULL:
//			return l==null;
//		case NOT_NULL:
//			return l!=null;
//		case NULL_OR_EMPTY:
//			return l == null || l.length()<1;
//		case NOT_NULL_OR_EMPTY:
//			return l != null && l.length()>0;
//		default:
//			return false;
//		}
//	}
//	/**
//	 * 根据操作符，判断boolean和String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(boolean l,String r, String ops){
//		boolean rValue = Boolean.parseBoolean(r);
//		return compare(l,rValue,ops);
//	}
//
//	/**
//	 * 根据操作符，判断两个char类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(char l,char r, String ops){
//		if(!operators.containsKey(ops)){
//			//throw new RuntimeException("unknown relational operator:"+ops);
//			throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
//		}
//		int op = operators.get(ops);
//
//		switch(op){
//		case GT:
//			return l>r;
//		case GE:
//			return l>=r;
//		case E:
//			return l==r;
//		case LE:
//			return l<=r;
//		case LT:
//			return l<r;
//		case NE:
//			return l!=r;
//		default:
//			return false;
//		}
//	}
//
//	/**
//	 * 根据操作符，判断char,String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(char l,String r, String ops){
//		if(r == null || r.length()!=1){
//			return false;
//		}
//
//		return compare(l,r.charAt(0),ops);
//	}
//
//	/**
//	 * 根据操作符，判断两个int类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(int l,int r, String ops){
//		if(!operators.containsKey(ops)){
//			//throw new RuntimeException("unknown relational operator:"+ops);
//			throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
//		}
//		int op = operators.get(ops);
//
//		switch(op){
//		case GT:
//			return l>r;
//		case GE:
//			return l>=r;
//		case E:
//			return l==r;
//		case LE:
//			return l<=r;
//		case LT:
//			return l<r;
//		case NE:
//			return l!=r;
//		default:
//			return false;
//		}
//	}
//
//	/**
//	 * 根据操作符，判断int,String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(int l,String r, String ops){
//		int rValue = Double.valueOf(r).intValue();
//		return compare(l,rValue,ops);
//	}
//
//	/**
//	 * 根据操作符，判断两个long类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(long l,long r,String ops){
//		if(!operators.containsKey(ops)){
//			//throw new RuntimeException("unknown relational operator:"+ops);
//			throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
//		}
//
//		int op = operators.get(ops);
//
//		switch(op){
//		case GT:
//			return l>r;
//		case GE:
//			return l>=r;
//		case E:
//			return l==r;
//		case LE:
//			return l<=r;
//		case LT:
//			return l<r;
//		case NE:
//			return l!=r;
//		default:
//			return false;
//		}
//	}
//
//	/**
//	 * 根据操作符，判断long,String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(long l,String r, String ops){
//		long rValue = Double.valueOf(r).longValue();
//		return compare(l,rValue,ops);
//	}
//	/**
//	 * 根据操作符，判断两个double类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(double l,double r,String ops){
//		if(!operators.containsKey(ops)){
//			//throw new RuntimeException("unknown relational operator:"+ops);
//			throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
//		}
//
//		int op = operators.get(ops);
//
//		switch(op){
//		case GT:
//			return l>r;
//		case GE:
//			return l>=r;
//		case E:
//			return l==r;
//		case LE:
//			return l<=r;
//		case LT:
//			return l<r;
//		case NE:
//			return l!=r;
//		default:
//			return false;
//		}
//	}
//	/**
//	 * 根据操作符，判断double,String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(double l,String r, String ops){
//		double rValue = Double.valueOf(r).doubleValue();
//		return compare(l,rValue,ops);
//	}
//	/**
//	 * 根据操作符，判断两个Date类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(Date l,Date r,String ops){
//		if(!operators.containsKey(ops)){
//			//throw new RuntimeException("unknown relational operator:"+ops);
//			throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
//		}
//
//		int op = operators.get(ops);
//		if(l == null){
//            if(op == NULL || op == NULL_OR_EMPTY || op==E && r ==null){
//                return true;
//            }else{
//                return false;
//            }
//        }
//
//		switch(op){
//		case GT:
//			return l.compareTo(r)>0;
//		case GE:
//			return l.compareTo(r)>=0;
//		case E:
//			return l.compareTo(r)==0;
//		case LE:
//			return l.compareTo(r)<=0;
//		case LT:
//			return l.compareTo(r)<0;
//		case NE:
//			return l.compareTo(r)!=0;
//		default:
//			return false;
//		}
//	}
//	/**
//	 * 根据操作符，判断Date,String类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	public static boolean compare(Date l,String r, String ops){
//		Date rValue = DataUtil.toDate(r);
//		return compare(l,rValue,ops);
//	}
//	/**
//	 * 根据操作符，判断两个Object类型的条件表达式
//	 * @param l
//	 * @param r
//	 * @param ops
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static boolean compare(Object l,Object r,String ops){
//		//if both are Numbers; then comare as double values;
//		if(l instanceof Number && r instanceof Number){
//			return compare(((Number)l).doubleValue(),((Number)r).doubleValue(),ops);
//		}else if (l instanceof Number && r instanceof String){
//			return compare(((Number)l).doubleValue(),(String)r,ops);
//		}else if (l instanceof Boolean && r instanceof String){
//			return compare(((Boolean)l).booleanValue(),(String)r,ops);
//		}else if(l instanceof Date && r instanceof String){
//			return compare((Date)l,(String)r,ops);
//		}else if(l instanceof Date && r instanceof Date){
//			return compare((Date)l,(Date)r,ops);
//		}else if(l instanceof String && r instanceof String){
//			return compare((String)l,(String)r,ops);
//		}else if(l instanceof Character && r instanceof String){
//			return compare(((Character)l).charValue(),(String)r,ops);
//		}else if(l instanceof String && r instanceof Character){
//			return compare((String)l,((Character)r).toString(),ops);
//		}
//
//		if(!operators.containsKey(ops)){
//			//throw new RuntimeException("unknown relational operator:"+ops);
//			throw new ClassBuildException(ExceptionConstant.CLASSBUILD_16100037,new Object[]{ops});
//		}
//		int op = operators.get(ops);
//
//		switch(op){
//		case GT:
//			if(l == null || r == null){
//				return false;
//			}
//			//if implements Comparable interface;
//			if(!(l instanceof Comparable)){
//				return false;
//			}
//			return ((Comparable)l).compareTo(r)>0;
//		case GE:
//			if(l == null || r == null){
//				return false;
//			}
//			//if implements Comparable interface;
//			if(!(l instanceof Comparable)){
//				return false;
//			}
//			return ((Comparable)l).compareTo(r)>=0;
//		case E:
//			if(l == null || r == null){
//				return false;
//			}
//
//			return l.equals(r);
//		case LE:
//			if(l == null || r == null){
//				return false;
//			}
//			//if implements Comparable interface;
//			if(!(l instanceof Comparable)){
//				return false;
//			}
//			return ((Comparable)l).compareTo(r)<=0;
//		case LT:
//			if(l == null || r == null){
//				return false;
//			}
//			//if implements Comparable interface;
//			if(!(l instanceof Comparable)){
//				return false;
//			}
//			return ((Comparable)l).compareTo(r)<0;
//		case NE:
//			if(l == null || r == null){
//				return false;
//			}
//			return !l.equals(r);
//		case NULL:
//			return l==null;
//		case NOT_NULL:
//			return l!=null;
//		case NULL_OR_EMPTY:
//			if(l instanceof Number){
//				return ((Number)l).intValue() == 0;
//			}else{
//				if(l== null){
//					return true;
//				}else{
//					Class cls = l.getClass();
//					if(cls.isArray()){
//						return Array.getLength(l)==0;
//					}else if(cls == String.class){
//						return ((String)l).length()<1;
//					}
//				}
//
//			}
//		case NOT_NULL_OR_EMPTY:
//			if(l instanceof Number){
//				return ((Number)l).intValue() != 0;
//			}else{
//				if(l== null){
//					return true;
//				}else{
//					Class cls = l.getClass();
//					if(cls.isArray()){
//						return Array.getLength(l)>0;
//					}else if(cls == String.class){
//						return ((String)l).length()>0;
//					}
//				}
//			}
//		default:
//			return false;
//		}
//	}


}
