package com.propertySoftwareDock.jeez.entity;

/**
 * @ClassName: JeezDict
 * @Date: 2016-11-17 19:51
 * @Auther: kangduo
 * @Description:(极致字典类)
 */
public class JeezDict {
    public static class ReqUrl {
        //获取用户信息链接
        public static final String Get_Users_Info = "GetCustomerPhone";
        //获取房间信息链接
        public static final String Get_Room_Info = "GetHousesByCustomer";
        //取账单链接
        public static final String Get_Pay_Bill = "GetLateFee";
        //销账
        public static final String To_Pay_Bill = "ToPayByBills";
        //获取安全时间戳
        public static final String Get_Encryption_Key = "GetEncryptionKey";
        //推送公共维修单
        public static final String Push_Property_Repair = "CreateRequsetBill";
        //获取极致维修单详情
        public static final String Property_Repair_List = "GetRequsetList";
    }
}
