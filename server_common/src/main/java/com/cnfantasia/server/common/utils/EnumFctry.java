package com.cnfantasia.server.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhe
 * @date 2015年9月1日
 * @description 枚举工厂
 *
 */
public final class EnumFctry {

    /**
     * @author wangzhe
     * @date 2015年9月1日
     * @description Integer转为Enum，若Integer为null则返回null。若找不到对应的Enum则返回null
     *
     * @param cls 枚举类
     * @param ordinal 枚举序号
     * @return 枚举对象
     */
    public static <E extends Enum<E>> E ToEnum(final Class<E> cls, final Integer ordinal) {
        final E[] values = cls.getEnumConstants();
        if (null != values && null != ordinal) {
            if (ordinal < values.length && ordinal >= 0 && values[ordinal].ordinal() == ordinal) {
                return values[ordinal];
            }
            for (final E em : values) {
                if (em.ordinal() == ordinal) {
                    return em;
                }
            }
        } else {
            return null;
        }
        return null;
    }

    /**
     * @author wangzhe
     * @date 2015年9月11日
     * @description 获取某个类中所有的枚举
     *
     * @param cls 类
     * @return 枚举list
     */
    public static <E extends Enum<E>> List<E> getAllEnums(final Class<E> cls) {
        final List<E> ret = new ArrayList<E>();
        final E[] values = cls.getEnumConstants();
        if (null != values && 0 < values.length) {
            ret.addAll(Arrays.asList(values));
        } else {
        }
        return ret;
    }

    private EnumFctry() {
    }
}