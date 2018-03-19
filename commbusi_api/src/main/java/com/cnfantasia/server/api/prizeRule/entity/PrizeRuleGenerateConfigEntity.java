/**   
* Filename:    PrizeRuleGenerateConfigEntity.java   
* @version:    1.0  
* Create at:   2014年8月30日 上午4:11:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.prizeRule.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.prizeRuleGenerateUsercount.entity.PrizeRuleGenerateUsercount;

/**
 * Filename:    PrizeRuleGenerateConfigEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月30日 上午4:11:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月30日       shiyl             1.0             1.0 Version
 */
public class PrizeRuleGenerateConfigEntity extends PrizeRuleGenerateUsercount{
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(getClass());
	
	public boolean fetchIsContain(Long userCount){
		Long startCount = this.getStartCount();
		Long endCount = this.getEndCount();
		if(startCount!=null&&endCount!=null&&userCount>=startCount&&userCount<endCount){
			return true;
		}else{
			return false;
		}
	}
	
//	public static void main(String[] args) throws ParseException {
//		String startTime = "2014-12-31 16:13:18";
//		String endTime = "2015-1-3 17:13:18";
//		String dateTime = "2015-1-1 16:17:18";
//		Date startTimeDate = DateUtil.formatSecond.get().parse(startTime);
//		Date endTimeDate = DateUtil.formatSecond.get().parse(endTime);
//		Date date = DateUtil.formatSecond.get().parse(dateTime);
//		if(date.compareTo(startTimeDate)>=0&&date.compareTo(endTimeDate)<0){
//			System.out.println("true");
//		}else{
//			System.out.println("false");
//		}
//	}
	
	public boolean fetchIsContain(String dateTime){
//		String startTime = this.getStartTime();
//		String endTime = this.getEndTime();
//		if(startTime!=null&&endTime!=null){
//			try {
//				Date startTimeDate = DateUtil.formatSecond.get().parse(startTime);
//				Date endTimeDate = DateUtil.formatSecond.get().parse(endTime);
//				Date date = DateUtil.formatSecond.get().parse(dateTime);
//				if(date.compareTo(startTimeDate)>=0&&date.compareTo(endTimeDate)<0){
//					return true;
//				}
//			} catch (Exception e) {
//				logger.debug("PrizeRuleGenerateConfigEntity.fetchIsContain.parse error,startTime is "+startTime+",endTime is "+endTime+",dateTime is "+dateTime,e);
//			}
//		}
//		return false;
		
		Date date = null;
		try {
			date = DateUtil.formatSecond.get().parse(dateTime);
		} catch (Exception e) {
			logger.debug("PrizeRuleGenerateConfigEntity.fetchIsContain.parse error,dateTime is "+dateTime,e);
		}
		return fetchIsContain(date);
	}
	
	public boolean fetchIsContain(Date date){
		String startTime = this.getStartTime();
		String endTime = this.getEndTime();
		if(startTime!=null&&endTime!=null){
			try {
				Date startTimeDate = DateUtil.formatSecond.get().parse(startTime);
				Date endTimeDate = DateUtil.formatSecond.get().parse(endTime);
				if(date.compareTo(startTimeDate)>=0&&date.compareTo(endTimeDate)<0){
					return true;
				}
			} catch (Exception e) {
				logger.debug("PrizeRuleGenerateConfigEntity.fetchIsContain.parse error,startTime is "+startTime+",endTime is "+endTime+",date is "+date,e);
			}
		}
		return false;
	}
	
	public BigDecimal fetchAreaAPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaAPercent(), 10000L);
	}
	public BigDecimal fetchAreaBPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaBPercent(), 10000L);
	}
	public BigDecimal fetchAreaCPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaCPercent(), 10000L);
	}
	public BigDecimal fetchAreaDPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaDPercent(), 10000L);
	}
	public BigDecimal fetchAreaEPercentDiv10000(){
		return PriceUtil.divByDivNum(getAreaEPercent(), 10000L);
	}
	
}
