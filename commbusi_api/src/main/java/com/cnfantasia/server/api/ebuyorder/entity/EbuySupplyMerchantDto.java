package com.cnfantasia.server.api.ebuyorder.entity;

import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

/**
 * 供应商DTO实体类
 */
public class EbuySupplyMerchantDto implements Serializable {
    private static final long serialVersionUID = 9033239970445134911L;

    private EbuySupplyMerchant ebuySupplyMerchant;

    private EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount;

    /**
     * 服务城市
     */
    private Set<BigInteger> serveCityIds;

    /**
     * 快递类型  1 包邮  2 满足条件包邮
     */
    private Integer deliveryType;

    /**
     * 邮费
     */
    private Double deliveryFee;

    /**
     * 免邮起始金额
     */
    private Double deliveryFeeFreeStart;

    /**
     * 结算邮费
     */
    private Double settleDeliveryFee;

    /**
     * 结算免邮起始金额
     */
    private Double settleDeliveryFeeFreeStart;

    private String userAccount;

    private String password;

    public EbuySupplyMerchant getEbuySupplyMerchant() {
        return ebuySupplyMerchant;
    }

    public void setEbuySupplyMerchant(EbuySupplyMerchant ebuySupplyMerchant) {
        this.ebuySupplyMerchant = ebuySupplyMerchant;
    }

    public Set<BigInteger> getServeCityIds() {
        return serveCityIds;
    }

    public void setServeCityIds(Set<BigInteger> serveCityIds) {
        this.serveCityIds = serveCityIds;
    }

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getDeliveryFeeFreeStart() {
        return deliveryFeeFreeStart;
    }

    public void setDeliveryFeeFreeStart(Double deliveryFeeFreeStart) {
        this.deliveryFeeFreeStart = deliveryFeeFreeStart;
    }

    public Double getSettleDeliveryFee() {
        return settleDeliveryFee;
    }

    public void setSettleDeliveryFee(Double settleDeliveryFee) {
        this.settleDeliveryFee = settleDeliveryFee;
    }

    public Double getSettleDeliveryFeeFreeStart() {
        return settleDeliveryFeeFreeStart;
    }

    public void setSettleDeliveryFeeFreeStart(Double settleDeliveryFeeFreeStart) {
        this.settleDeliveryFeeFreeStart = settleDeliveryFeeFreeStart;
    }

    public EbuySupplyMerchantBankAccount getEbuySupplyMerchantBankAccount() {
        return ebuySupplyMerchantBankAccount;
    }

    public void setEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount) {
        this.ebuySupplyMerchantBankAccount = ebuySupplyMerchantBankAccount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
