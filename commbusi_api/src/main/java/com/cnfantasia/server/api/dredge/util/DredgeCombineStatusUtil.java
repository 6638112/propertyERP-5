package com.cnfantasia.server.api.dredge.util;

import com.cnfantasia.server.api.dredge.constant.DredgeConstant;

/**
 * @ClassName: DredgeCombineStatusUtil.
 * @Date: 2017-10-10 15:49
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeCombineStatusUtil {

    public static Integer getCombineStatus(Integer billType, Integer status) {
        if (billType == null || status == null) {
            return null;
        }
        Integer combineStatus;
        //服务前付款
        if (billType == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
            int x = 0;
            switch (status) {
                case DredgeConstant.DredgeBill.DredgeBill_Not_Pay_First :
                    combineStatus = 1;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Submited :
                    combineStatus = 2;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Accepted :
                    combineStatus = 3;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment :
                    combineStatus = 5;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Canceled :
                    combineStatus = 6;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment :
                    combineStatus = 5;
                    break;
                case DredgeConstant.DredgeBill.Master_Finish :
                    combineStatus = 4;
                    break;
                case DredgeConstant.DredgeBill.Apply_Refund :
                    combineStatus = 7;
                    break;
                case DredgeConstant.DredgeBill.Refund_Success :
                    combineStatus = 8;
                    break;
                default:
                    combineStatus = null;
            }
        } else {//服务后付款
            switch (status) {
                case DredgeConstant.DredgeBill.DredgeBill_Status_Submited :
                    combineStatus = 2;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Accepted :
                    combineStatus = 3;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment :
                    combineStatus = 5;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Canceled :
                    combineStatus = 6;
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment :
                    combineStatus = 5;
                    break;
                case DredgeConstant.DredgeBill.Master_Finish :
                    combineStatus = 4;
                    break;
                case DredgeConstant.DredgeBill.Apply_Refund :
                    combineStatus = 7;
                    break;
                case DredgeConstant.DredgeBill.Refund_Success :
                    combineStatus = 8;
                    break;
                default:
                    combineStatus = null;
            }
        }
        return combineStatus;
    }

    public static String getCombineStatusDesc(Integer billType, Integer status) {
        if (billType == null || status == null) {
            return "";
        }
        String combineStatus = "";
        //服务前付款
        if (billType == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
            int x = 0;
            switch (status) {
                case DredgeConstant.DredgeBill.DredgeBill_Not_Pay_First :
                    combineStatus = "已下单/未支付/未分配";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Submited :
                    combineStatus = "已下单/已支付/未分配";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Accepted :
                    combineStatus = "已下单/已支付/已分配";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment :
                    combineStatus = "已下单/已支付/已服务";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Canceled :
                    combineStatus = "已取消/未支付/未分配";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment :
                    combineStatus = "已下单/已支付/已服务";
                    break;
                case DredgeConstant.DredgeBill.Master_Finish :
                    combineStatus = "已下单/已支付/已服务";
                    break;
                case DredgeConstant.DredgeBill.Apply_Refund :
                    combineStatus = "退款中";
                    break;
                case DredgeConstant.DredgeBill.Refund_Success :
                    combineStatus = "已退款";
                    break;
                default:
                    combineStatus = "";
            }
        } else {//服务后付款
            switch (status) {
                case DredgeConstant.DredgeBill.DredgeBill_Status_Submited :
                    combineStatus = "已下单/未支付/未分配";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Accepted :
                    combineStatus = "已下单/未支付/已分配";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment :
                    combineStatus = "已完成/已支付/已服务";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_Canceled :
                    combineStatus = "已取消/未支付";
                    break;
                case DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment :
                    combineStatus = "已完成/已支付/已服务";
                    break;
                case DredgeConstant.DredgeBill.Master_Finish :
                    combineStatus = "已下单/未支付/已服务";
                    break;
                case DredgeConstant.DredgeBill.Refund_Success :
                    combineStatus = "已退款";
                    break;
                default:
                    combineStatus = "";
            }
        }
        return combineStatus;
    }
}
