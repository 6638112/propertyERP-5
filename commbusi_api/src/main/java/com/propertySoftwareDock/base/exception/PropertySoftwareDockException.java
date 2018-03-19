package com.propertySoftwareDock.base.exception;

import com.cnfantasia.server.common.exception.FocException;

/**
 * @ClassName: PropertySoftwareDockException
 * @Date: 2016-12-06 9:54
 * @Auther: kangduo
 * @Description:(物业对接过程中产生的异常)
 */
public class PropertySoftwareDockException extends FocException{
    private static final long serialVersionUID = -1486944705643060319L;
    //默认异常code
    private static final String DEFAULT_ERROR_CODE = "property.software.dock.error";
    //连接极致获取数据过程中异常
    public static final String Get_Data_Error = "remote.server.getData.error";
    //根据小区获取不到用户
    public static final String Empty_Gb_House = "software.gb.house.empty";
    //一个房间多个用户
    public static final String No_Such_Gb = "sys.no.such.gb.config";
    /**
     * 构造方法
     */
    public PropertySoftwareDockException() {
        super(DEFAULT_ERROR_CODE);
    }

    /**
     * 构造方法
     */
    public PropertySoftwareDockException(String msg) {
        super(msg);
    }

    /**
     * 构造方法
     */
    public PropertySoftwareDockException(String msg, Object[] args) {
        super(msg, args);
    }
}
