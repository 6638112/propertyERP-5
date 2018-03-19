package com.cnfantasia.server.api.address.dto;

import java.util.List;

/**
 * @className: AddressProvinceDto.
 * @date: 2017-11-08 17:10
 * @author: kangduo
 * @description: ()
 */
public class AddressProvinceDto {

    private Long provinceId;
    private String provinceName;
    private List<AddressCityDto> cityList;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<AddressCityDto> getCityList() {
        return cityList;
    }

    public void setCityList(List<AddressCityDto> cityList) {
        this.cityList = cityList;
    }
}
