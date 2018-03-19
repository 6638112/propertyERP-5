package com.cnfantasia.wl.wechat.model;

/* import java.io.Serializable;*/
import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
/**
 * 描述:(产品参数) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyProductParameters implements Serializable{
*/
public class EbuyProductParametersNew extends EbuyProductParameters{

	private static final long serialVersionUID = 1L;

	public EbuyProductParametersNew(){
	}
	/**	 * 	 * @param id 	 * @param tEbuyProductFId 	 * @param tPropName 属性名称	 * @param tPropValue 属性值	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyProductParametersNew(BigInteger id,BigInteger tEbuyProductFId,String tPropName,String tPropValue,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		super(id, tEbuyProductFId, tPropName, tPropValue, sys0AddTime, sys0UpdTime, sys0DelTime, sys0AddUser, sys0UpdUser, sys0DelUser, sys0DelState);
	}
	
	public BigInteger getTEbuyProductFId() {		return gettEbuyProductFId();	}	public void setTEbuyProductFId(BigInteger tEbuyProductFId) {
		settEbuyProductFId(tEbuyProductFId);	}	public String getTPropName() {		return gettPropName();	}	public void setTPropName(String tPropName) {
		settPropName(tPropName);	}	public String getTPropValue() {		return gettPropValue();	}	public void setTPropValue(String tPropValue) {
		settPropValue(tPropValue);
	}
}
