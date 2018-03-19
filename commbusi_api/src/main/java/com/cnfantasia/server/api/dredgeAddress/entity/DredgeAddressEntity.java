package com.cnfantasia.server.api.dredgeAddress.entity;

import java.math.BigInteger;

/**
 * @ClassName: DredgeAddressEntity.
 * @Date: 2017-06-27 10:54
 * @Auther: kangduo
 * @Description: ()
 */
public class DredgeAddressEntity {

    private BigInteger addressId;
    private String city;
    private BigInteger cityId;
    private String block;
    private BigInteger blockId;
    private String address;
    private BigInteger gbId;
    private Boolean valid;

    public BigInteger getAddressId() {
        return addressId;
    }

    public void setAddressId(BigInteger addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigInteger getCityId() {
        return cityId;
    }

    public void setCityId(BigInteger cityId) {
        this.cityId = cityId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public BigInteger getBlockId() {
        return blockId;
    }

    public void setBlockId(BigInteger blockId) {
        this.blockId = blockId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
