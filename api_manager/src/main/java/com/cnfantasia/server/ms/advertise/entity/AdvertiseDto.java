package com.cnfantasia.server.ms.advertise.entity;

import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * 广告DTO
 */
public class AdvertiseDto implements Serializable {
	private static final long serialVersionUID = -6695144046822965164L;
	private EbuyAdvertise ebuyAdvertise;
	private Integer advType;
	private Integer areaType;
	private Integer alertAppPageType;
	private Set<BigInteger> shelfIds;
	private Set<BigInteger> cityIds;
	private Set<BigInteger> gbIds;
	private List<BigInteger> blockId;

	private String firstLine;
	private String secondLine;
	private String thirdLine;
	private BigInteger dredgeProductId;
	private BigInteger storeShelfId;

	public Integer getAlertAppPageType() {
		return alertAppPageType;
	}

	public void setAlertAppPageType(Integer alertAppPageType) {
		this.alertAppPageType = alertAppPageType;
	}

	public EbuyAdvertise getEbuyAdvertise() {
		return ebuyAdvertise;
	}

	public void setEbuyAdvertise(EbuyAdvertise ebuyAdvertise) {
		this.ebuyAdvertise = ebuyAdvertise;
	}

	public Integer getAdvType() {
		return advType;
	}

	public void setAdvType(Integer advType) {
		this.advType = advType;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	public Set<BigInteger> getShelfIds() {
		return shelfIds;
	}

	public void setShelfIds(Set<BigInteger> shelfIds) {
		this.shelfIds = shelfIds;
	}

	public Set<BigInteger> getCityIds() {
		return cityIds;
	}

	public void setCityIds(Set<BigInteger> cityIds) {
		this.cityIds = cityIds;
	}

	public Set<BigInteger> getGbIds() {
		return gbIds;
	}

	public void setGbIds(Set<BigInteger> gbIds) {
		this.gbIds = gbIds;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}

	public String getSecondLine() {
		return secondLine;
	}

	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	public String getThirdLine() {
		return thirdLine;
	}

	public void setThirdLine(String thirdLine) {
		this.thirdLine = thirdLine;
	}

	public BigInteger getDredgeProductId() {
		return dredgeProductId;
	}

	public void setDredgeProductId(BigInteger dredgeProductId) {
		this.dredgeProductId = dredgeProductId;
	}

	public List<BigInteger> getBlockId() {
		return blockId;
	}

	public void setBlockId(List<BigInteger> blockId) {
		this.blockId = blockId;
	}

	public BigInteger getStoreShelfId() {
		return storeShelfId;
	}

	public void setStoreShelfId(BigInteger storeShelfId) {
		this.storeShelfId = storeShelfId;
	}
}
