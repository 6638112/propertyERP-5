package com.cnfantasia.server.api.plotproperty.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;

/**
 * Created by yangh on 2016/10/24.
 */
public class AlterFeeItem {
    private BigInteger itemId;
    private String name;
    private BigDecimal totalPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getItemId() {
        return itemId;
    }

    public void setItemId(BigInteger itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getTotalPrice() {
        if(totalPrice!=null) {
            return BigDecimalUtil.div100(totalPrice);
        } else {
            return BigDecimal.valueOf(0);
        }
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
