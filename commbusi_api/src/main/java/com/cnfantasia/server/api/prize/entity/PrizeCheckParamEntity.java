/**   
* Filename:    PrizeCheckParamEntity.java   
* @version:    1.0  
* Create at:   2015年6月5日 上午8:38:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年6月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prize.entity;

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Filename:    PrizeCheckParamEntity.java
 * @version:    1.0.0
 * Create at:   2015年6月5日 上午8:38:39
 * Description:抽奖核对参数信息实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年6月5日       shiyl             1.0             1.0 Version
 */
public class PrizeCheckParamEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(getClass());
	private static final Integer confirmPrizeStatus_success = 1;
//private static final Integer confirmPrizeStatus_failed = 2;
	
	/**确认上次对应抽奖的编号,可为空*/
	private Integer confirmPrizeNum;
	/**对应抽奖编号的门牌Id,可为空*/
	private BigInteger confirmRoomId;
	/**对应抽奖编号的抽奖时间,可为空*/
	private String confirmPrizeTime;
	/**对应抽奖编号的处理结果,可为空*/
	private Integer confirmPrizeStatus;
	/**对应抽奖编号的抽奖记录Id,可为空*/
	private BigInteger confirmPrizeRecordId;
	/**当前请求抽奖对应第几次*/
	private Integer currPrizeNum;
	
	
	
	public PrizeCheckParamEntity(){}
	public PrizeCheckParamEntity(Integer confirmPrizeNum,BigInteger confirmRoomId,String confirmPrizeTime,Integer confirmPrizeStatus,BigInteger confirmPrizeRecordId,Integer currPrizeNum){
		this.confirmPrizeNum = confirmPrizeNum;
		this.confirmRoomId = confirmRoomId;
		this.confirmPrizeTime = confirmPrizeTime;
		this.confirmPrizeStatus = confirmPrizeStatus;
		this.confirmPrizeRecordId = confirmPrizeRecordId;
		this.currPrizeNum = currPrizeNum;
	}
	
	public Integer getConfirmPrizeNum() {
		return confirmPrizeNum;
	}
	public void setConfirmPrizeNum(Integer confirmPrizeNum) {
		this.confirmPrizeNum = confirmPrizeNum;
	}
	
	public BigInteger getConfirmRoomId() {
		return confirmRoomId;
	}
	public void setConfirmRoomId(BigInteger confirmRoomId) {
		this.confirmRoomId = confirmRoomId;
	}
	public String getConfirmPrizeTime() {
		return confirmPrizeTime;
	}
	public void setConfirmPrizeTime(String confirmPrizeTime) {
		this.confirmPrizeTime = confirmPrizeTime;
	}
	public Integer getConfirmPrizeStatus() {
		return confirmPrizeStatus;
	}
	public void setConfirmPrizeStatus(Integer confirmPrizeStatus) {
		this.confirmPrizeStatus = confirmPrizeStatus;
	}
	
//	public BigInteger getConfirmPrizeRecordId() {
//		return confirmPrizeRecordId;
//	}
	public void setConfirmPrizeRecordId(BigInteger confirmPrizeRecordId) {
		this.confirmPrizeRecordId = confirmPrizeRecordId;
	}
	
	public Integer getCurrPrizeNum() {
		return currPrizeNum;
	}
	public void setCurrPrizeNum(Integer currPrizeNum) {
		this.currPrizeNum = currPrizeNum;
	}
	
	/**
	 * 获取临时抽奖记录的Id
	 * @return
	 */
	public BigInteger getConfirmPrizeTmpDataId() {
		return confirmPrizeRecordId;
	}
	
	/**
	 * 是否存在抽奖确认信息
	 * @return true 存在,false不存在
	 */
	public boolean ifExistConfirmData(){
		if(confirmPrizeNum==null||confirmPrizeStatus==null/*||confirmPrizeRecordId==null*/){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 抽奖确认信息是否标识为成功
	 * @return true成功 false失败
	 */
	public boolean ifConfirmSuccess(){
		if(confirmPrizeStatus==null||confirmPrizeStatus.compareTo(confirmPrizeStatus_success)==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 将当前待请求的抽奖次数减一
	 * @param count
	 */
	public boolean subCurrPrizeNumOne(){
		boolean res = false;
		if(confirmPrizeNum!=null&&confirmPrizeNum>1){
			confirmPrizeNum = confirmPrizeNum-1;
			res = true;
		}else{
			logger.info("com.cnfantasia.server.api.prize.entity.PrizeCheckParamEntity.subCurrPrizeNumOne() failed,curr confirmPrizeNum is:"+confirmPrizeNum);
		}
		return res;
	}
}
