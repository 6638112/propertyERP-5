package com.cnfantasia.server.api.groupBuildingCycleCfg.entity;

/**
 * @ClassName: UpdatePayBillTypeDict
 * @Date: 2017-09-19 15:18
 * @Auther: yanghua
 * @Description:(账单明细更新类型)
 */
public final class UpdatePayBillTypeDict {
    //1 根据已有的房间收费项金额
    public static final int UPDATEALL = 1;
    // 2 新增收费项（账单配置中已经包含的收费项）
    public static final int ADDITEM = 2;
    // 3新增房间
    public static final int ADDROOM = 3;
    // 4收费项删除
    public static final int DELETEITEM = 4;
}
