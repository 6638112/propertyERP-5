package com.cnfantasia.server.ms.payBill.entity;

import com.cnfantasia.server.common.utils.DataUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

/**
 * @ClassName: GroupBuildingReportEntity
 * @Date: 2017-06-08 16:14
 * @Auther: yanghua
 * @Description:(报表：小区)
 */
public class GroupBuildingReportEntity {
    private BigInteger gbId;
    private String gbName;
    /**本期应收合计*/
    private Long succAmt;
    /**往期欠费合计*/
    private Long lastUnpaidAmt;
    /**解放区缴费合计*/
    private Long jfqPayAmt;
    /**银行托收缴费合计*/
    private Long bankPayAmt;
    /**现金缴费合计*/
    private Long cashPayAmt;
    /**待收金额*/
    private Long needPayAmt;
    /**收缴率*/
    private Double collectionRate;
    /**欠费率*/
    private Double unpaidRate;
    /**是否收取欠费*/
    /** 是否收缴欠费（2收缴 1或者空不收缴） */
    private Integer isCollectArrears;
    private Long deduPirce;

    /**费用类型*/
    private List<PropertyFeeReportEntity> feeReportEntityList;

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public Long getSuccAmt() {
        return succAmt;
    }

    public void setSuccAmt(Long succAmt) {
        this.succAmt = succAmt;
    }

    public Long getJfqPayAmt() {
        return jfqPayAmt;
    }

    public void setJfqPayAmt(Long jfqPayAmt) {
        this.jfqPayAmt = jfqPayAmt;
    }

    public Long getBankPayAmt() {
        return bankPayAmt;
    }

    public void setBankPayAmt(Long bankPayAmt) {
        this.bankPayAmt = bankPayAmt;
    }

    public Long getCashPayAmt() {
        return cashPayAmt;
    }

    public void setCashPayAmt(Long cashPayAmt) {
        this.cashPayAmt = cashPayAmt;
    }

    public Long getNeedPayAmt() {
        return needPayAmt;
    }

    public void setNeedPayAmt(Long needPayAmt) {
        this.needPayAmt = needPayAmt;
    }

    public Double getCollectionRate() {
        //收缴率：（解放区缴费+银行托收+现金缴费）/（本月应收合计+往月欠费合计）
        //被除数
        Long dividend = (jfqPayAmt == null ? 0L : jfqPayAmt) + (bankPayAmt == null ? 0L : bankPayAmt) + (cashPayAmt == null ? 0L : cashPayAmt);
        //除数
        Long divisor = (succAmt == null ? 0L : succAmt) + (lastUnpaidAmt == null ? 0L : lastUnpaidAmt);
        BigDecimal dividendB = new BigDecimal(dividend);
        BigDecimal divisorB = new BigDecimal(divisor);
        BigDecimal bigDecimal = dividendB.divide(divisorB, 14, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        if(bigDecimal == null || bigDecimal.doubleValue() == 0) {
            return 0d;
        }
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void setCollectionRate(Double collectionRate) {
        this.collectionRate = collectionRate;
    }

    public Double getUnpaidRate() {
        //欠费率：待收金额/（本月应收合计+往月欠费合计）
        //被除数
        Long dividend = (needPayAmt == null ? 0L : needPayAmt) + (lastUnpaidAmt == null ? 0L : lastUnpaidAmt);
        //除数
        Long divisor = (succAmt == null ? 0L : succAmt) + (lastUnpaidAmt == null ? 0L : lastUnpaidAmt);
        BigDecimal dividendB = new BigDecimal(dividend);
        BigDecimal divisorB = new BigDecimal(divisor);
        BigDecimal bigDecimal = dividendB.divide(divisorB, 14, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        if(bigDecimal == null || bigDecimal.doubleValue() == 0) {
            return 0d;
        }
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void setUnpaidRate(Double unpaidRate) {
        this.unpaidRate = unpaidRate;
    }

    public List<PropertyFeeReportEntity> getFeeReportEntityList() {
        return feeReportEntityList;
    }

    public void setFeeReportEntityList(List<PropertyFeeReportEntity> feeReportEntityList) {
        this.feeReportEntityList = feeReportEntityList;
    }
    
    public Integer getPfCount() {
    	if(feeReportEntityList == null || feeReportEntityList.size() == 0) {
    		return 1;
    	} else {
    		return feeReportEntityList.size();
    	}
    }

    public Integer getIsCollectArrears() {
        return isCollectArrears;
    }

    public void setIsCollectArrears(Integer isCollectArrears) {
        this.isCollectArrears = isCollectArrears;
    }

    public Long getDeduPirce() {
        return deduPirce;
    }

    public void setDeduPirce(Long deduPirce) {
        this.deduPirce = deduPirce;
    }

    public Long getLastUnpaidAmt() {
        lastUnpaidAmt = 0L;
        if(!DataUtil.isEmpty(feeReportEntityList)) {
            for (PropertyFeeReportEntity propertyFeeReportEntity : feeReportEntityList) {
                lastUnpaidAmt += propertyFeeReportEntity.getUnpaidAmt() == null ? 0L : propertyFeeReportEntity.getUnpaidAmt();
            }
        }
        if(!DataUtil.isEmpty(deduPirce) && lastUnpaidAmt > 0) {
            lastUnpaidAmt = lastUnpaidAmt - deduPirce;
        }
        return lastUnpaidAmt;
    }

    public void setLastUnpaidAmt(Long lastUnpaidAmt) {
        this.lastUnpaidAmt = lastUnpaidAmt;
    }
}
