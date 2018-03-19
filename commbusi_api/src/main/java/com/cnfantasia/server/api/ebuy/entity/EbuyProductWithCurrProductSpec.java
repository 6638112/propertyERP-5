/**   
* Filename:    EbuyProductWithCurrProductSpec.java   
* @version:    1.0  
* Create at:   2015年1月9日 上午8:26:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.ebuy.util.EbuyDeliveryMethodUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.ebuyDeliveryMethod.entity.EbuyDeliveryMethod;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductSpec.entity.EbuyProductSpec;

/**
 * Filename:    EbuyProductWithCurrProductSpec.java
 * @version:    1.0.0
 * Create at:   2015年1月9日 上午8:26:44
 * Description: 产品信息以及当前购买的规格信息规格信息可以为空
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月9日       shiyl             1.0             1.0 Version
 */
public class EbuyProductWithCurrProductSpec extends EbuyProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private EbuyProductEntity ebuyProductEntity;
	
	/**
	 * 构造方法
	 * @param ebuyProduct 产品信息
	 * @param ebuyProductSpec 产品规格信息
	 */
	public EbuyProductWithCurrProductSpec(EbuyProduct ebuyProduct,EbuyProductSpec ebuyProductSpec){
		super(ebuyProduct);
		this.ebuyProductSpec = ebuyProductSpec;
	}
	
	public EbuyProductWithCurrProductSpec(EbuyProductEntity ebuyProduct,EbuyProductSpec ebuyProductSpec){
		super(ebuyProduct);
		this.ebuyProductSpec = ebuyProductSpec;
		this.ebuyProductEntity = ebuyProduct;
	}
	
	public EbuyProductEntity doEntity(){
		return ebuyProductEntity;
	}
	
	
	/**当前购买的产品规格信息*/
	private EbuyProductSpec ebuyProductSpec;
	public void setEbuyProductSpec(EbuyProductSpec ebuyProductSpec) {
		this.ebuyProductSpec = ebuyProductSpec;
	}
	
	/**获取商品Id*/
	public BigInteger getProductId(){
		return super.getId();
	}
	/**获取商品规格Id*/
	public BigInteger getProductSpecId(){
		return ebuyProductSpec==null?null:ebuyProductSpec.getId();
	}
	
	public boolean isEqualExt(EbuyProductWithCurrProductSpec goal){
		BigInteger goalProductId = goal.getProductId();
		BigInteger goalProductSpecId = goal.getProductSpecId();
		boolean res = isEqualExt(goalProductId, goalProductSpecId);
		return res;
	}
	public boolean isEqualExt(BigInteger goalProductId,BigInteger goalProductSpecId){
		boolean res = false;
		if(this.doEntity().getProductShelf().getId()!=null&&goalProductId!=null&&this.doEntity().getProductShelf().getId().compareTo(goalProductId.longValue())==0){
			if(this.getProductSpecId()==null&&goalProductSpecId==null){//都为空
				res = true;
			}else if(this.getProductSpecId()!=null&&goalProductSpecId!=null
					&&this.getProductSpecId().compareTo(goalProductSpecId)==0){
				res = true;//都不为空且相等
			}
		}
		return res;
	}
	
//	/**获取商品所属供应商Id*/
//	public BigInteger getSupplyMerchantFIdExt(){
//		return super.gettSupplyMerchantFId();
//	}
	
	
	/**
	 * 判断当前单个商品使用的配送方式
	 * syl-add 2015-4-23 14:46:04 move from EbuyProductEntity 
	 * @return
	 */
	private EbuyDeliveryMethod getCurrDefaultEbuyDeliveryMethod(){
		//syl-del 2015-4-23 14:55:00 去掉通过商品本身配置的配送方式的支持
//		EbuyDeliveryMethod defaultEbuyDeliveryMethod = doEntity().getDefaultEbuyDeliveryMethod();
//		if(defaultEbuyDeliveryMethod!=null){
//			return defaultEbuyDeliveryMethod;
//		}else
		{
			EbuySupplyMerchantEntity ebuySupplyMerchantEntity = doEntity().getEbuySupplyMerchantEntity();
			//从供应商处 获取配送信息
			List<EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod> merchantHasDeliveryMethodList=ebuySupplyMerchantEntity.getMerchantHasDeliveryMethodList();
			if(merchantHasDeliveryMethodList!=null&&merchantHasDeliveryMethodList.size()>0){
				for(EbuySupplyMerchantHasTEbuyDeliveryMethodEntity_DeliveryMethod tmpA:merchantHasDeliveryMethodList){
					Long priceAmount = getPriceDiscount();//折后价格
					EbuyDeliveryMethod tmpMethod = tmpA.getEbuyDeliveryMethod();
					if(EbuyDeliveryMethodUtil.fetchIsContain(priceAmount, tmpMethod.getPriceAmountStart(), tmpMethod.getPriceAmountEnd())){
						return tmpMethod;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取商品默认的配送描述信息
	 * syl-add 2015-4-23 14:46:04 move from EbuyProductEntity 
	 * @return
	 */
	public String getDefaultDeliveryName(){
		if(ebuyProductEntity.getProductShelf() != null && ebuyProductEntity.getProductShelf().getOpType() != null && ebuyProductEntity.getProductShelf().getOpType() == 1) {
			return "免邮费";
		}
		EbuyDeliveryMethod currMethod = getCurrDefaultEbuyDeliveryMethod();
		return currMethod==null?null:currMethod.getDesc();
	}
	/**
	 * 获取商品默认的运费信息
	 * syl-add 2015-4-23 14:46:04 move from EbuyProductEntity 
	 * @return
	 */
	public Long getDefaultDeliveryFee(){
		EbuyDeliveryMethod currMethod = getCurrDefaultEbuyDeliveryMethod();
		return currMethod==null?null:currMethod.getFee();
	}
	
	
	/**
	 * 获取商品及相关属性的名称描述
	 * @return
	 */
	@Override
	public String getName(){
		StringBuffer resSbf = new StringBuffer();
		resSbf.append(super.getName());
		if(ebuyProductSpec!=null){
			if(!StringUtils.isEmpty(ebuyProductSpec.getPhoneAgentType())){
				resSbf.append(",服务商 ");
				resSbf.append(ebuyProductSpec.getPhoneAgentType());
			}
			if(!StringUtils.isEmpty(ebuyProductSpec.getPhoneAmount())){
				resSbf.append(",金额 ");
				resSbf.append(ebuyProductSpec.getPhoneAmount());
			}
			if(!StringUtils.isEmpty(ebuyProductSpec.getSize())){
				resSbf.append(",尺寸 ");
				resSbf.append(ebuyProductSpec.getSize());
			}
			if(!StringUtils.isEmpty(ebuyProductSpec.getColour())){
				resSbf.append(",颜色 ");
				resSbf.append(ebuyProductSpec.getColour());
			}
		}
		return resSbf.toString();
	}
	
	public EbuyProductShelf getShelfProduct() {
		if(ebuyProductEntity != null) {
			return ebuyProductEntity.getProductShelf();
		}
		return null;
	}
	
	
//	/**
//	 * 查询待支付的现金金额
//	 * @return
//	 */
//	public Long getPriceDiscountExt(){
//		Long resAmount = null;
//		if(ebuyProductSpec!=null){
//			resAmount = ebuyProductSpec.getPriceDiscount();
//		}else{
//			resAmount = ebuyProduct.getPriceDiscount();
//		}
//		if(resAmount==null){resAmount = 0L;}
//		return resAmount;
//	}
	
//	/**
//	 * 查询待支付的积分金额
//	 * @return
//	 */
//	public Long getPricePointExt(){
//		Long resAmount = null;
//		if(ebuyProductSpec!=null){
//			resAmount = ebuyProductSpec.getPricePoint();
//		}else{
//			resAmount = ebuyProduct.getPricePoint();
//		}
//		if(resAmount==null){resAmount = 0L;}
//		return resAmount;
//	}
	
	@Override
	public Long getPrice() {
		Long res = ebuyProductSpec != null&&ebuyProductSpec.getPrice()!=null ? ebuyProductSpec.getPrice() : super.getPrice();
		return res == null ? 0L : res;
	}
	@Override
	public Long getPriceDiscount() {
		Long res = ebuyProductSpec != null&&ebuyProductSpec.getPriceDiscount()!=null ? ebuyProductSpec.getPriceDiscount() : super.getPriceDiscount();
		if(ebuyProductEntity != null && ebuyProductEntity.getProductShelf() != null && ebuyProductEntity.getProductShelf().getPriceDicount() != null) {
			res = ebuyProductEntity.getProductShelf().getPriceDicount().longValue();
		}
		return res == null ? 0L : res;
	}
	@Override
	public BigInteger getLeftCount() {
		BigInteger res = ebuyProductSpec != null&&ebuyProductSpec.getLeftCount()!=null ? ebuyProductSpec.getLeftCount() : super.getLeftCount();
		return res == null ? BigInteger.ZERO : res;
	}
	@Override
	public BigInteger getSelNum() {
		BigInteger res = ebuyProductSpec != null&&ebuyProductSpec.getSelNum()!=null ? ebuyProductSpec.getSelNum() : super.getSelNum();
		return res == null ? BigInteger.ZERO : res;
	}
	@Override
	public String getPicBase() {
		return ebuyProductSpec!=null&&ebuyProductSpec.getPicBase()!=null ? ebuyProductSpec.getPicBase():super.getPicBase();
	}
	@Override
	public Long getPricePoint() {
		Long res = ebuyProductSpec != null&&ebuyProductSpec.getPricePoint()!=null ? ebuyProductSpec.getPricePoint() : super.getPricePoint();
		return res == null ? 0L : res;
	}
	@Override
	public Long getPriceDiscountPoint() {
		Long res = ebuyProductSpec != null&&ebuyProductSpec.getPriceDiscountPoint()!=null ? ebuyProductSpec.getPriceDiscountPoint() : super.getPriceDiscountPoint();
		return res == null ? 0L : res;
	}
	@Override
	public String getSys0UpdTime(){
		return ebuyProductSpec!=null&&ebuyProductSpec.getSys0UpdTime()!=null ? ebuyProductSpec.getSys0UpdTime():super.getSys0UpdTime();
	}
	@Override
	public String getSys0AddTime() {
		return  ebuyProductSpec!=null&&ebuyProductSpec.getSys0AddTime()!=null?ebuyProductSpec.getSys0AddTime():super.getSys0AddTime();
	}
	@Override
	public String getSys0DelTime() {
		return ebuyProductSpec!=null&&ebuyProductSpec.getSys0DelTime()!=null?ebuyProductSpec.getSys0DelTime():super.getSys0DelTime();
	}
	@Override
	public BigInteger getSys0UpdUser() {
		return ebuyProductSpec!=null&&ebuyProductSpec.getSys0UpdUser()!=null?ebuyProductSpec.getSys0UpdUser():super.getSys0UpdUser();
	}
	@Override
	public BigInteger getSys0AddUser() {
		return ebuyProductSpec!=null&&ebuyProductSpec.getSys0AddUser()!=null?ebuyProductSpec.getSys0AddUser():super.getSys0AddUser();
	}
	@Override
	public BigInteger getSys0DelUser() {
		return ebuyProductSpec!=null&&ebuyProductSpec.getSys0DelUser()!=null?ebuyProductSpec.getSys0DelUser():super.getSys0DelUser();
	}
	@Override
	public Integer getSys0DelState() {
		return ebuyProductSpec!=null&&ebuyProductSpec.getSys0DelState()!=null?ebuyProductSpec.getSys0DelState():super.getSys0DelState();
	}
	
	@Override
	public void setPrice(Long price) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setPrice(price);}else super.setPrice(price);
	}
	@Override
	public void setPriceDiscount(Long priceDiscount) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setPriceDiscount(priceDiscount);}else super.setPriceDiscount(priceDiscount);
	}
	@Override
	public void setLeftCount(BigInteger leftCount) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setLeftCount(leftCount);}else super.setLeftCount(leftCount);
	}
	@Override
	public void setSelNum(BigInteger selNum) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSelNum(selNum);}else super.setSelNum(selNum);
	}
	@Override
	public void setPicBase(String picBase) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setPicBase(picBase);}else super.setPicBase(picBase);
	}
	@Override
	public void setPricePoint(Long pricePoint) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setPricePoint(pricePoint);}else super.setPricePoint(pricePoint);
	}
	@Override
	public void setPriceDiscountPoint(Long priceDiscountPoint) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setPriceDiscountPoint(priceDiscountPoint);}else super.setPriceDiscountPoint(priceDiscountPoint);
	}
	@Override
	public void setSys0AddTime(String sys0AddTime) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0AddTime(sys0AddTime);}else super.setSys0AddTime(sys0AddTime);
	}
	@Override
	public void setSys0UpdTime(String sys0UpdTime) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0UpdTime(sys0UpdTime);}else super.setSys0UpdTime(sys0UpdTime);
	}
	@Override
	public void setSys0DelTime(String sys0DelTime) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0DelTime(sys0DelTime);}else super.setSys0DelTime(sys0DelTime);
	}
	@Override
	public void setSys0AddUser(BigInteger sys0AddUser) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0AddUser(sys0AddUser);}else super.setSys0AddUser(sys0AddUser);
	}
	@Override
	public void setSys0UpdUser(BigInteger sys0UpdUser) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0UpdUser(sys0UpdUser);}else super.setSys0UpdUser(sys0UpdUser);
	}
	@Override
	public void setSys0DelState(Integer sys0DelState) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0DelState(sys0DelState);}else super.setSys0DelState(sys0DelState);
	}
	@Override
	public void setSys0DelUser(BigInteger sys0DelUser) {
		if(ebuyProductSpec!=null){ebuyProductSpec.setSys0DelUser(sys0DelUser);}else super.setSys0DelUser(sys0DelUser);
	}
	
}
