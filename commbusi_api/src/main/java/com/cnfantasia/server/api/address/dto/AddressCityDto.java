package com.cnfantasia.server.api.address.dto;

import java.util.List;

/**
 * @className: AddressCityDto.
 * @date: 2017-11-08 17:10
 * @author: kangduo
 * @description: ()
 */
public class AddressCityDto {

    private Long cityId;
    private String cityName;
    private List<AddressBlockDto> blockList;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<AddressBlockDto> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<AddressBlockDto> blockList) {
        this.blockList = blockList;
    }
}
