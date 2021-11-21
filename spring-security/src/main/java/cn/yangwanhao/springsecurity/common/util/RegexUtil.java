package cn.yangwanhao.springsecurity.common.util;

import cn.yangwanhao.springsecurity.common.GlobalConstant;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtil {

    /**
     * 匹配手机号码（先支持13, 15, 17, 18开头的手机号码）.
     *
     * @param inputStr the input str
     *
     * @return the boolean
     */
    public static Boolean isMobileNumber(String inputStr) {
        return !isNull(inputStr) && inputStr.matches(GlobalConstant.Regex.MOBILE_PHONE);
    }

    /**
     * Is email boolean.
     *
     * @param str the str
     *
     * @return the boolean
     */
    public static boolean isEmail(String str) {
        boolean bl = true;
        if (isSEmptyOrNull(str) || !str.matches(GlobalConstant.Regex.EMAIL)) {
            bl = false;
        }
        return bl;
    }

    /**
     * 判断一个或多个对象是否为空
     *
     * @param values 可变参数, 要判断的一个或多个对象
     *
     * @return 只有要判断的一个对象都为空则返回true, 否则返回false boolean
     */
    public static boolean isNull(Object... values) {
        if (!isNotNullAndNotEmpty(values)) {
            return true;
        }
        for (Object value : values) {
            boolean flag;
            if (value instanceof Object[]) {
                flag = !isNotNullAndNotEmpty((Object[]) value);
            } else if (value instanceof Collection<?>) {
                flag = !isNotNullAndNotEmpty((Collection<?>) value);
            } else if (value instanceof String) {
                flag = isOEmptyOrNull(value);
            } else {
                flag = (null == value);
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * Is o empty or null boolean.
     *
     * @param o the o
     *
     * @return boolean boolean
     */
    private static boolean isOEmptyOrNull(Object o) {
        return o == null || isSEmptyOrNull(o.toString());
    }

    /**
     * Is s empty or null boolean.
     *
     * @param s the s
     *
     * @return boolean boolean
     */
    private static boolean isSEmptyOrNull(String s) {
        return trimAndNullAsEmpty(s).length() <= 0;
    }

    /**
     * Trim and null as empty string.
     *
     * @param s the s
     *
     * @return java.lang.String string
     */
    private static String trimAndNullAsEmpty(String s) {
        if (s != null && !s.trim().equals(GlobalConstant.Regex.STRING_NULL)) {
            return s.trim();
        } else {
            return "";
        }
        // return s == null ? "" : s.trim();
    }

    /**
     * 判断对象数组是否为空并且数量大于0
     *
     * @param value the value
     *
     * @return boolean
     */
    private static Boolean isNotNullAndNotEmpty(Object[] value) {
        boolean bl = false;
        if (null != value && 0 < value.length) {
            bl = true;
        }
        return bl;
    }

    /**
     * 判断对象集合（List,Set）是否为空并且数量大于0
     *
     * @param value the value
     *
     * @return boolean
     */
    private static Boolean isNotNullAndNotEmpty(Collection<?> value) {
        boolean bl = false;
        if (null != value && !value.isEmpty()) {
            bl = true;
        }
        return bl;
    }

    /**
     * Uuid string.
     *
     * @return the string
     */
    public synchronized static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
