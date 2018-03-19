package com.cnfantasia.server.api.ebuy.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;
/**
 * 描述:(商品表) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class EbuyProductEntity extends EbuyProduct{
	private static final long serialVersionUID = 1L;
	/**
	 * 供应商信息
	 */
	private EbuySupplyMerchantEntity ebuySupplyMerchantEntity;
	
	private EbuyProductShelf productShelf;


	private String sosName; //货源名称
	private String sosIntroduceUrl; //货源介绍URL

	private Integer buyNum;


	public EbuySupplyMerchantEntity getEbuySupplyMerchantEntity() {
		return ebuySupplyMerchantEntity;
	}
	public void setEbuySupplyMerchantEntity(EbuySupplyMerchantEntity ebuySupplyMerchantEntity) {
		this.ebuySupplyMerchantEntity = ebuySupplyMerchantEntity;
	}
	@Override
	public BigInteger gettSupplyMerchantFId() {
		if(ebuySupplyMerchantEntity==null){return null;}
		return ebuySupplyMerchantEntity.getId();
	}
	@Override
	public void settSupplyMerchantFId(BigInteger tSupplyMerchantFId) {
		if(ebuySupplyMerchantEntity==null){
			ebuySupplyMerchantEntity = new EbuySupplyMerchantEntity();
		}
		ebuySupplyMerchantEntity.setId(tSupplyMerchantFId);
	}
	
	/**
	 * 认证信息
	 */
	private List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList;
	
	public List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> getEbuyProductHasTEbuyAuthEntityList() {
		return ebuyProductHasTEbuyAuthEntityList;
	}
	public void setEbuyProductHasTEbuyAuthEntityList(
			List<EbuyProductHasTEbuyAuthEntity_EbuyAuth> ebuyProductHasTEbuyAuthEntityList) {
		this.ebuyProductHasTEbuyAuthEntityList = ebuyProductHasTEbuyAuthEntityList;
	}
	
//	/**
//	 * 认证信息
//	 */
//	private List<EbuyAuth> ebuyAuthList;
//	public List<EbuyAuth> getEbuyAuthList() {
//		return ebuyAuthList;
//	}
//	public void setEbuyAuthList(List<EbuyAuth> ebuyAuthList) {
//		this.ebuyAuthList = ebuyAuthList;
//	}
	

	/**
	 * 默认配送信息
	 */
	private EbuyDeliveryMethod defaultEbuyDeliveryMethod;
	public EbuyDeliveryMethod getDefaultEbuyDeliveryMethod() {
		return defaultEbuyDeliveryMethod;
	}
	public void setDefaultEbuyDeliveryMethod(EbuyDeliveryMethod defaultEbuyDeliveryMethod) {
		this.defaultEbuyDeliveryMethod = defaultEbuyDeliveryMethod;
	}
	
//	/**
//	 * 获取商品默认的配送描述信息
//	 * @return
//	 */
//	public String getDefaultDeliveryName(){
//		if(defaultEbuyDeliveryMethod!=null){
//			return defaultEbuyDeliveryMethod.getName();
//		}else{
//			//从供应商处 获取配送信息
//			List<EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod> merchantHasDeliveryMethodList=ebuySupplyMerchantEntity.getMerchantHasDeliveryMethodList();
//			if(merchantHasDeliveryMethodList!=null&&merchantHasDeliveryMethodList.size()>0){
//				return merchantHasDeliveryMethodList.get(0).getEbuyDeliveryMethod().getDesc();
//			}
//		}
//		return null;
//	}
	
//	/**
//	 * 获取商品默认的运费信息
//	 * @return
//	 */
//	public Long getDefaultDeliveryFee(){
//		if(defaultEbuyDeliveryMethod!=null){
//			return defaultEbuyDeliveryMethod.getFee();
//		}else{
//			//从供应商处 获取配送信息
//			List<EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod> merchantHasDeliveryMethodList=ebuySupplyMerchantEntity.getMerchantHasDeliveryMethodList();
//			if(merchantHasDeliveryMethodList!=null&&merchantHasDeliveryMethodList.size()>0){
//				return merchantHasDeliveryMethodList.get(0).getEbuyDeliveryMethod().getFee();
//			}
//		}
//		return null;
//	}
	
	
	@Override
	public BigInteger getDefaultDeliveryId() {
		if(defaultEbuyDeliveryMethod==null){return null;}
		return defaultEbuyDeliveryMethod.getId();
	}
	@Override
	public void setDefaultDeliveryId(BigInteger defaultDeliveryId) {
		if(defaultEbuyDeliveryMethod==null){
			defaultEbuyDeliveryMethod = new EbuyDeliveryMethod();
		}
		defaultEbuyDeliveryMethod.setId(defaultDeliveryId);
	}
	
	/**
	 * 产品参数信息
	 */
	private List<EbuyProductParameters> ebuyProductParametersList;
	public List<EbuyProductParameters> getEbuyProductParametersList() {
		return ebuyProductParametersList;
	}
	public void setEbuyProductParametersList(List<EbuyProductParameters> ebuyProductParametersList) {
		this.ebuyProductParametersList = ebuyProductParametersList;
	}
	
	/**
	 * 评价总数
	 */
	private Integer commentTotalCount;
	public Integer getCommentTotalCount() {
		return commentTotalCount;
	}
	public void setCommentTotalCount(Integer commentTotalCount) {
		this.commentTotalCount = commentTotalCount;
	}
	/**
	 * 第一条评论的内容
	 */
	private String firstComentContent;
	public String getFirstComentContent() {
		return firstComentContent;
	}
	public void setFirstComentContent(String firstComentContent) {
		this.firstComentContent = firstComentContent;
	}
	/**
	 * 是否被收藏false否，true是
	 */
	private Boolean isCollected;
	public Boolean getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}
	/**
	 * 产品图片列表
	 */
	private List<EbuyProductPic> ebuyProductPicList;
	public List<EbuyProductPic> getEbuyProductPicList() {
		return ebuyProductPicList;
	}
	public void setEbuyProductPicList(List<EbuyProductPic> ebuyProductPicList) {
		this.ebuyProductPicList = ebuyProductPicList;
	}
	
	/**
	 * 产品价格相关的规格列表
	 */
	private List<EbuyProductSpec> ebuyProductSpecList;
	public List<EbuyProductSpec> getEbuyProductSpecList() {
		return ebuyProductSpecList;
	}
	public void setEbuyProductSpecList(List<EbuyProductSpec> ebuyProductSpecList) {
		this.ebuyProductSpecList = ebuyProductSpecList;
	}
	
	/**
	 * 产品介绍的图片列表
	 */
	private List<EbuyProductIntroducePic> ebuyProductIntroducePicList;
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicList() {
		return ebuyProductIntroducePicList;
	}
	public void setEbuyProductIntroducePicList(List<EbuyProductIntroducePic> ebuyProductIntroducePicList) {
		this.ebuyProductIntroducePicList = ebuyProductIntroducePicList;
	}
	
	public EbuyProductShelf getProductShelf() {
		return productShelf;
	}
	
	public void setProductShelf(EbuyProductShelf productShelf) {
		this.productShelf = productShelf;
	}

	public String getSosName() {
		return sosName;
	}

	public void setSosName(String sosName) {
		this.sosName = sosName;
	}

	public String getSosIntroduceUrl() {
		return sosIntroduceUrl;
	}

	public void setSosIntroduceUrl(String sosIntroduceUrl) {
		this.sosIntroduceUrl = sosIntroduceUrl;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
}
