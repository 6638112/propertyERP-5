package com.cnfantasia.server.ms.dredgeRefund.entity;

import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @ClassName: DredgeRefundEntity.
 * @Date: 2017-10-12 14:14
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeRefundEntity implements Serializable {
    private static final long serialVersionUID = 1844890094246893197L;

    private BigInteger id;
    private BigInteger dredgeBillFId;
    private Integer dredgeBillStatus;
    private Long refundAmount;
    private Long refundCouponAmount;
    /** 状态 1 申请中 2 退款成功 3 退款失败 */
    private Integer refundStatus;
    /** 退款方式 1 全额退款 2 部分退款 */
    private Integer refundType;
    private String auditReason = "";

    //新增退款时用
    private BigDecimal refundAmountDecimalAdd;
    private BigDecimal refundCouponAmountDecimalAdd;

    @Excel(title = "订单号")
    private String billNo;
    @Excel(title = "商品名称")
    private String dredgeProductName;
    @Excel(title = "支付时间")
    private String payTime;
    private Integer payMethod;
    @Excel(title = "支付方式")
    private String payMethodDesc;
    @Excel(title = "支付流水号")
    private String flowNo;
    @Excel(title = "联系人")
    private String linkName;
    @Excel(title = "联系电话")
    private String linkPhone;
    private Integer billTotalAmount;
    @Excel(title = "订单总额")
    private BigDecimal billTotalAmountDecimal;
    @Excel(title = "申请退款金额")
    private BigDecimal refundAmountDecimal;
    @Excel(title = "申请退款粮票金额")
    private BigDecimal refundCouponAmountDecimal;
    @Excel(title = "退款原因")
    private String refundReason;
    @Excel(title = "备注")
    private String note;

    private String applyUser;
    @Excel(title = "申请退款时间")
    private String applyTime;
    private String auditUser = "";
    @Excel(title = "退款完成时间")
    private String auditTime;
    @Excel(title = "退款方式")
    private String refundTypeDesc;
    @Excel(title = "退款状态")
    private String refundStatusDesc;

    private String applyTimeStart;
    private String applyTimeEnd;

    private Integer orderAmount;
    private Integer orderCouponAmount;
    private Integer couponDiscountMoney;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getDredgeBillFId() {
        return dredgeBillFId;
    }

    public void setDredgeBillFId(BigInteger dredgeBillFId) {
        this.dredgeBillFId = dredgeBillFId;
    }

    public Integer getDredgeBillStatus() {
        return dredgeBillStatus;
    }

    public void setDredgeBillStatus(Integer dredgeBillStatus) {
        this.dredgeBillStatus = dredgeBillStatus;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Long getRefundCouponAmount() {
        return refundCouponAmount;
    }

    public void setRefundCouponAmount(Long refundCouponAmount) {
        this.refundCouponAmount = refundCouponAmount;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public BigDecimal getRefundAmountDecimalAdd() {
        return refundAmountDecimalAdd;
    }

    public void setRefundAmountDecimalAdd(BigDecimal refundAmountDecimalAdd) {
        this.refundAmountDecimalAdd = refundAmountDecimalAdd;
    }

    public BigDecimal getRefundCouponAmountDecimalAdd() {
        return refundCouponAmountDecimalAdd;
    }

    public void setRefundCouponAmountDecimalAdd(BigDecimal refundCouponAmountDecimalAdd) {
        this.refundCouponAmountDecimalAdd = refundCouponAmountDecimalAdd;
    }

    public BigDecimal getRefundAmountDecimal() {
        return refundAmount == null ? null : PriceUtil.div100(refundAmount);
    }

    public void setRefundAmountDecimal(BigDecimal refundAmountDecimal) {
        this.refundAmountDecimal = refundAmountDecimal;
    }

    public BigDecimal getRefundCouponAmountDecimal() {
        return refundCouponAmount == null ? null : PriceUtil.div100(refundCouponAmount);
    }

    public void setRefundCouponAmountDecimal(BigDecimal refundCouponAmountDecimal) {
        this.refundCouponAmountDecimal = refundCouponAmountDecimal;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getDredgeProductName() {
        return dredgeProductName;
    }

    public void setDredgeProductName(String dredgeProductName) {
        this.dredgeProductName = dredgeProductName;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethodDesc() {
        if (payMethod == null) {
            return null;
        }
        if (payMethod == 1) {
            return "微信支付";
        } else if (payMethod == 2) {
            return "支付宝支付";
        } else if (payMethod == 4) {
            return "粮票支付";
        } else if (payMethod == 7) {
            return "纯优惠券支付";
        } else if (payMethod == 9) {
            return "银行卡支付";
        }
        return "未知";
    }

    public void setPayMethodDesc(String payMethodDesc) {
        this.payMethodDesc = payMethodDesc;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getRefundTypeDesc() {
        if (refundType == null) {
            return null;
        }
        if (refundType == 1) {
            return "全额退款";
        } else if (refundType == 2){
            return "部分退款";
        }
        return refundTypeDesc;
    }

    public void setRefundTypeDesc(String refundTypeDesc) {
        this.refundTypeDesc = refundTypeDesc;
    }

    public String getRefundStatusDesc() {
        if (refundStatus == null) {
            return null;
        }
        if (refundStatus == DredgeConstant.DredgeRefundStatus.APPLY) {
            return "待审核";
        } else if (refundStatus == DredgeConstant.DredgeRefundStatus.FAIL) {
            return "退款失败";
        } else if (refundStatus == DredgeConstant.DredgeRefundStatus.SUCCESS) {
            return "已退款";
        }
        return refundStatusDesc;
    }

    public void setRefundStatusDesc(String refundStatusDesc) {
        this.refundStatusDesc = refundStatusDesc;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(String auditUser) {
        this.auditUser = auditUser;
    }

    public BigDecimal getBillTotalAmountDecimal() {
        return billTotalAmount == null ? null : PriceUtil.div100(Long.valueOf(billTotalAmount));
    }

    public void setBillTotalAmountDecimal(BigDecimal billTotalAmountDecimal) {
        this.billTotalAmountDecimal = billTotalAmountDecimal;
    }

    public Integer getBillTotalAmount() {
        return billTotalAmount;
    }

    public void setBillTotalAmount(Integer billTotalAmount) {
        this.billTotalAmount = billTotalAmount;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getApplyTimeStart() {
        return applyTimeStart;
    }

    public void setApplyTimeStart(String applyTimeStart) {
        this.applyTimeStart = applyTimeStart;
    }

    public String getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(String applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getOrderCouponAmount() {
        return orderCouponAmount;
    }

    public void setOrderCouponAmount(Integer orderCouponAmount) {
        this.orderCouponAmount = orderCouponAmount;
    }

    public Integer getCouponDiscountMoney() {
        return couponDiscountMoney;
    }

    public void setCouponDiscountMoney(Integer couponDiscountMoney) {
        this.couponDiscountMoney = couponDiscountMoney;
    }
}
