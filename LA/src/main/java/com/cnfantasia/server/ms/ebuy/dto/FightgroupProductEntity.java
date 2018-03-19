package com.cnfantasia.server.ms.ebuy.dto;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyFightOrder.entity.EbuyFightOrder;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;

public class FightgroupProductEntity extends EbuyProduct implements Serializable{
	private static final long serialVersionUID = -45458488487787878L;
	/**已有几人成团 */
	private List<EbuyFightOrder> buyNum;
	/** 货架价格 */
	private Long shelfPrice;
	/** 拼购价格 */
	private Long fightPrice;
	/** 到开始时间*/
	private String startDate;
	/** 到期日 */
	private String expireDate;
	/** 拼团人数*/
	private Long fightNum;
	/** 商品图片 */
	private List<String> picUrls;
	/** 自提点信息 */
	private EbuyFightSupplyMerchant zitiDian;
	/** 拼购商品表id */
	private BigInteger fightProductId;
	/** 拼购状态 */	
	private Integer fightStatus;
	/**供应商信息 */
	private EbuySupplyMerchant merchantName;
	/**商品规则id */
	private BigInteger productSpecId;
	
	private Long productShelfId;
	
	public BigInteger getProductSpecId() {
		return productSpecId;
	}
	public void setProductSpecId(BigInteger productSpecId) {
		this.productSpecId = productSpecId;
	}
	public Integer getFightStatus() {
		return fightStatus;
	}
	public void setFightStatus(Integer fightStatus) {
		this.fightStatus = fightStatus;
	}
	
	public EbuySupplyMerchant getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(EbuySupplyMerchant merchantName) {
		this.merchantName = merchantName;
	}
	private List<EbuyProductIntroducePic>  proIntroduceList;
	public List<EbuyProductIntroducePic> getProIntroduceList() {
		return proIntroduceList;
	}
	public void setProIntroduceList(List<EbuyProductIntroducePic> proIntroduceList) {
		this.proIntroduceList = proIntroduceList;
	}
	public BigInteger getFightProductId() {
		return fightProductId;
	}
	public void setFightProductId(BigInteger fightProductId) {
		this.fightProductId = fightProductId;
	}
	public EbuyFightSupplyMerchant getZitiDian() {
		return zitiDian;
	}
	public void setZitiDian(EbuyFightSupplyMerchant zitiDian) {
		this.zitiDian = zitiDian;
	}
	public List<EbuyFightOrder> getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(List<EbuyFightOrder> buyNum) {
		this.buyNum = buyNum;
	}
	public Long getShelfPrice() {
		return shelfPrice;
	}
	public void setShelfPrice(Long shelfPrice) {
		this.shelfPrice = shelfPrice;
	}
	public Long getFightPrice() {
		return fightPrice;
	}
	public void setFightPrice(Long fightPrice) {
		this.fightPrice = fightPrice;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public Long getFightNum() {
		return fightNum;
	}
	public String getFightNumStr() {
		return this.fightNum+"人成团";
	}
	public void setFightNum(Long fightNum) {
		this.fightNum = fightNum;
	}
	
	public String getPicName(){
		if(this.picUrls!=null && this.picUrls.size()>0){
			return this.picUrls.get(0);
		}else{
			return this.getPicBaseMini();
		}
	}
	public List<String> getPicUrls() {
			return picUrls;
	}
	public void setPicUrls(List<String> picUrls) {
		this.picUrls = picUrls;
	}
	public String getBuyNumMsg(){
		if(this.buyNum.size()<this.fightNum){
			return this.buyNum.size()+"人已购买";
		}else{
			return this.buyNum.size()+"人已成团";
		}
	}
	
	public List<String> getTitlePics(){
		if(this.picUrls!=null && this.picUrls.size()>0){
			return picUrls;
		}else{
			List<String> list = new ArrayList<String>();
			list.add(this.getPicBaseMini());
			return list;
		}
	}
	public Long getProductShelfId() {
		return productShelfId;
	}
	public void setProductShelfId(Long productShelfId) {
		this.productShelfId = productShelfId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
}
