package com.cnfantasia.server.api.ebuyFightSupplyMerchant.entity;

import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

/**
 * 自提点DTO
 */
public class EbuyFightSupplyMerchantDto implements Serializable {
    private static final long serialVersionUID = 2683349763569033054L;

    private BigInteger fightMerchantId;
    private EbuyFightSupplyMerchant ebuyFightSupplyMerchant;
    private Integer groupBuildingCount;
    private String cityName;
    private BigInteger cityId;
    private String groupBuildingName;
    private String fightMerchantName;
    private Set<BigInteger> buildingIds;
    /** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

    public EbuyFightSupplyMerchant getEbuyFightSupplyMerchant() {
        return ebuyFightSupplyMerchant;
    }

    public void setEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant) {
        this.ebuyFightSupplyMerchant = ebuyFightSupplyMerchant;
    }

    public BigInteger getFightMerchantId() {
        return fightMerchantId;
    }

    public void setFightMerchantId(BigInteger fightMerchantId) {
        this.fightMerchantId = fightMerchantId;
    }

    public Integer getGroupBuildingCount() {
        return groupBuildingCount;
    }

    public void setGroupBuildingCount(Integer groupBuildingCount) {
        this.groupBuildingCount = groupBuildingCount;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getGroupBuildingName() {
        return groupBuildingName;
    }

    public void setGroupBuildingName(String groupBuildingName) {
        this.groupBuildingName = groupBuildingName;
    }

    public String getFightMerchantName() {
        return fightMerchantName;
    }

    public void setFightMerchantName(String fightMerchantName) {
        this.fightMerchantName = fightMerchantName;
    }

    public Set<BigInteger> getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(Set<BigInteger> buildingIds) {
        this.buildingIds = buildingIds;
    }

    public BigInteger getCityId() {
        return cityId;
    }

    public void setCityId(BigInteger cityId) {
        this.cityId = cityId;
    }

	public String getAddMan() {
		return addMan;
	}

	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
    
}
