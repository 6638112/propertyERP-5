/**   
* Filename:    TimeRange.java   
* @version:    1.0  
* Create at:   2015年9月14日 上午11:06:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月14日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;

/**
 * Filename:    TimeRange.java
 * @version:    1.0.0
 * Create at:   2015年9月14日 上午11:06:53
 * Description:时间范围实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月14日       shiyl             1.0             1.0 Version
 */
public class TimeRange {
	private static Log logger = LogFactory.getLog(TimeRange.class);
	private BigInteger id;
	/**开始时间*/
	private String startTime;
	/**截止时间*/
	private String endTime;
	/**默认数据格式*/
	private DateFormat defaultDateFormat = DateUtil.formatSecond.get();
	public TimeRange(){}
	public TimeRange(String startTime,String endTime,DateFormat defaultDateFormat){
		this.startTime = startTime;
		this.endTime = endTime;
		this.defaultDateFormat = defaultDateFormat;
	}
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=");
		sbf.append(id);
		sbf.append(";startTime=");
		sbf.append(startTime);
		sbf.append(";endTime=");
		sbf.append(endTime);
		return sbf.toString();
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public DateFormat getDefaultDateFormat() {
		return defaultDateFormat;
	}
	public void setDefaultDateFormat(DateFormat defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}
	/**
	 * 判断两个时间范围是否有交集
	 * @param A
	 * @param B
	 * @return true有交集,false无交集
	 */
	public static boolean hasIntersection(TimeRange A,TimeRange B){
		Date aStart = null;
		Date aEnd = null;
		Date bStart = null;
		Date bEnd = null;
		try {
			aStart = A.getDefaultDateFormat().parse(A.getStartTime());
			aEnd = A.getDefaultDateFormat().parse(A.getEndTime());
			bStart = B.getDefaultDateFormat().parse(B.getStartTime());
			bEnd = B.getDefaultDateFormat().parse(B.getEndTime());
		} catch (ParseException e) {
			logger.debug("TimeRange.hasIntersection cause exception,A:"+A+",B:"+B, e);
			throw new BusinessRuntimeException(e);
		}
		if( bEnd.before(aStart) || bStart.after(aEnd) )//b结束在a之前 或者 b开始在a结束之后
		   return false;
		else
		   return true;
	}
	
	
}
