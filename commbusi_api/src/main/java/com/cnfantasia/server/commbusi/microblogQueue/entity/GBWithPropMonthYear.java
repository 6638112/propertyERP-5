/**   
* Filename:    GBWithPropMonthYear.java   
* @version:    1.0  
* Create at:   2015年8月26日 上午10:42:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.entity;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import com.cnfantasia.server.business.pub.utils.DateUtil;


/**
 * Filename:    GBWithPropMonthYear.java
 * @version:    1.0.0
 * Create at:   2015年8月26日 上午10:42:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月26日       shiyl             1.0             1.0 Version
 */
public class GBWithPropMonthYear{
	/**小区Id*/
	private BigInteger gbId;
	/**小区名称*/
	private String gbName;
	/**物业缴费年份*/
	private String propYear;
	/**物业缴费月份*/
	private String propMonth;
	/**物业缴费开始时间*/
	private String propPayDateBegin;
	/**物业缴费截止时间*/
	private String propPayDateEnd;
	
	public String fetchPropYearMonthStr(){
		Integer year = Integer.valueOf(propYear);
		Integer month = Integer.valueOf(propMonth);
		return DateUtil.formateYear(year)+DateUtil.formateMonth(month);
	}
	
	/**
	 * 刷新时间相关信息
	 * @param date
	 */
	public void freshPropInfo(Date date){
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		String propMonth = DateUtil.formatOnlyMonth.get().format(ca.getTime());
		String propYear = DateUtil.formatOnlyYear.get().format(ca.getTime());
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMinimum(Calendar.DAY_OF_MONTH));
		String propPayDateBegin = DateUtil.formatDay.get().format(ca.getTime());
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String propPayDateEnd = DateUtil.formatDay.get().format(ca.getTime());
		this.setPropMonth(propMonth);
		this.setPropPayDateBegin(propPayDateBegin);
		this.setPropPayDateEnd(propPayDateEnd);
		this.setPropYear(propYear);
	}
	
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public String getPropYear() {
		return propYear;
	}
	public void setPropYear(String propYear) {
		this.propYear = propYear;
	}
	public String getPropMonth() {
		return propMonth;
	}
	public void setPropMonth(String propMonth) {
		this.propMonth = propMonth;
	}
	public String getPropPayDateBegin() {
		return propPayDateBegin;
	}
	public void setPropPayDateBegin(String propPayDateBegin) {
		this.propPayDateBegin = propPayDateBegin;
	}
	public String getPropPayDateEnd() {
		return propPayDateEnd;
	}
	public void setPropPayDateEnd(String propPayDateEnd) {
		this.propPayDateEnd = propPayDateEnd;
	}

}
