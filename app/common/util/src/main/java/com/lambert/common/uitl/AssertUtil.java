package com.lambert.common.uitl;

import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * @Auther: lambert
 * @Date: 2019/5/8 22:16
 * @Description:
 */
public class AssertUtil {
    /**
     * 断言表达式的值为true，否则抛出传入的异常。
     *
     * @param expValue 断言表达式
     * @param e        抛出的异常
     */
    public static void assertTrue(boolean expValue, RuntimeException e) {

        if (!expValue) {

            throw e;
        }
    }

    /**
     * 断言表达式的值为false，否则抛出传入的异常。
     *
     * @param expValue 断言表达式
     * @param e        抛出的异常
     */
    public static void assertFalse(boolean expValue, RuntimeException e) {

        if (expValue) {

            throw e;
        }
    }

    /**
     * 断言两个对象相等，否则抛出传入的异常。
     *
     * @param obj1
     * @param e
     */
    public static void assertEquals(Object obj1, Object obj2, RuntimeException e) {

        if (obj1 == null) {

            assertNull(obj2, e);
            return;
        }

        if (!obj1.equals(obj2)) {

            throw e;
        }
    }

    /**
     * 断言两个对象不等，否则抛出传入的异常。
     *
     * @param obj1
     * @param e
     */
    public static void assertNotEquals(Object obj1, Object obj2, RuntimeException e) {

        if (obj1 == null) {

            assertNotNull(obj2, e);
            return;
        }

        if (obj1.equals(obj2)) {

            throw e;
        }
    }

    /**
     * 断言对象至少等于容器中的一个
     *
     * @param obj1
     * @param objects
     * @param e
     */
    public static void assertEqualsAny(Object obj1, Object[] objects, RuntimeException e) {

        if (null == objects) {

            throw e;
        }

        for (Object obj2 : objects) {

            if (obj1 == null) {

                if (obj2 == null) {
                    return;
                }

                continue;
            }

            if (obj1.equals(obj2)) {

                return;
            }
        }

        throw e;
    }

    /**
     * 断言两个对象相同，否则抛出传入的异常。
     *
     * @param obj1
     * @param e
     */
    public static void assertIs(Object obj1, Object obj2, RuntimeException e) {

        if (obj1 != obj2) {

            throw e;
        }
    }

    /**
     * 断言两个对象不同，否则抛出传入的异常。
     *
     * @param obj1
     * @param e
     */
    public static void assertIsNot(Object obj1, Object obj2, RuntimeException e) {

        if (obj1 == obj2) {

            throw e;
        }
    }

    /**
     * 断言对象在容器中
     *
     * @param obj1
     * @param objs
     * @param e
     */
    public static void assertIn(Object obj1, Object[] objs, RuntimeException e) {

        if (null == objs) {

            throw e;
        }

        for (Object obj : objs) {

            if (obj == obj1) {

                return;
            }
        }

        throw e;
    }

    /**
     * 断言对象为空，否则抛出传入的异常。
     *
     * @param str 断言字符串
     * @param e   抛出的异常
     */
    public static void assertBlank(String str, RuntimeException e) {

        if (StringUtils.isNotBlank(str)) {

            throw e;
        }
    }

    /**
     * 断言对象为空，否则抛出传入的异常。
     *
     * @param str 断言字符串
     * @param e   抛出的异常
     */
    public static void assertNotBlank(String str, RuntimeException e) {

        if (StringUtils.isBlank(str)) {

            throw e;
        }
    }

    /**
     * 断言对象为null，否则抛出传入的异常。
     *
     * @param object 断言对象
     * @param e        抛出的异常
     */
    public static void assertNull(Object object, RuntimeException e) {

        if (object != null) {

            throw e;
        }
    }

    /**
     * 断言对象非null，否则抛出传入的异常。
     *
     * @param object 断言对象
     * @param e        抛出的异常
     */
    public static void assertNotNull(Object object, RuntimeException e) {

        if (null == object) {

            throw e;
        }
    }

    /**
     * 断言字符串不为空，否则抛IllegalArgumentException
     * @param targetValue 断言目标字符串
     * @param fieldName 字符串名称
     */
    public static void assertNotBlank(String targetValue, String fieldName) {

        if (StringUtils.isBlank(targetValue)) {

            throw new IllegalArgumentException(String.format("参数[%s]不能为空。", fieldName));
        }
    }

    /**
     * 断言对象非null，否则抛出IllegalArgumentException。
     *
     * @param object 断言对象
     * @param errorMessage   错误描述
     */
    public static void assertNotNull(Object object, String errorMessage) {

        if (null == object) {

            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * 断言集合不为空或null，否则抛出传入的异常。
     *
     * @param collection 断言集合
     * @param e          抛出的异常
     */
    @SuppressWarnings("rawtypes")
    public static void assertNotBlank(Collection collection, RuntimeException e) {

        if (null == collection || collection.size() == 0) {

            throw e;
        }
    }

}
