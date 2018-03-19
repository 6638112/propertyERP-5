/**   
* Filename:    PropertyGoodNewsShellEntity.java   
* @version:    1.0  
* Create at:   2015年8月25日 下午9:34:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.entity;

import java.util.List;

import com.cnfantasia.server.business.pub.utils.DateUtil;

/**
 * Filename:    PropertyGoodNewsShellEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月25日 下午9:34:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月25日       shiyl             1.0             1.0 Version
 */
public class PropertyGoodNewsShellEntity{
	/**小区信息*/
	private GBWithPropMonthYear gbInfo;
	/**列表数据*/
	private List<PropertyGoodNewsRowDataEntity> dataList;
	/**总的节省的金额*/
	private Long totalSavedMoney;
	/**累计缴费用户数*/
	private Long totalPayUserCount;
	public PropertyGoodNewsShellEntity(){}//转Json需要
	/**
	 * 构造函数
	 * @param gbInfo 小区相关信息
	 * @param dataList 单行数据列表
	 * @param totalPayUserCount 累计缴费用户数
	 */
	public PropertyGoodNewsShellEntity(GBWithPropMonthYear gbInfo,List<PropertyGoodNewsRowDataEntity> dataList,Long totalPayUserCount){
		this.gbInfo = gbInfo;  
		this.dataList = dataList;
		this.totalPayUserCount = totalPayUserCount;
		this.totalSavedMoney = 0L;
		for(PropertyGoodNewsRowDataEntity tmpData:dataList){
			totalSavedMoney+=tmpData.getSavedMoney()==null?0L:tmpData.getSavedMoney();
		}
	}
	
//	public String fetchPropPayEndMonthDay(){
//		return DateUtil.strFormate(gbInfo.getPropPayDateEnd(), DateUtil.formatDay.get(), DateUtil.chineseMonthDay.get());
//	}
	public String fetchPropPayEndDay(){
		return DateUtil.strFormate(gbInfo.getPropPayDateEnd(), DateUtil.formatDay.get(), DateUtil.formatOnlyDay.get());
	}
	public String fetchPropMonth(){
		return gbInfo.getPropMonth();
	}
	public String fetchGbName() {
		return gbInfo.getGbName();
	}
	
	//get 方法
	public GBWithPropMonthYear getGbInfo() {
		return gbInfo;
	}

	public List<PropertyGoodNewsRowDataEntity> getDataList() {
		return dataList;
	}

	public Long getTotalSavedMoney() {
		return totalSavedMoney;
	}

	public Long getTotalPayUserCount() {
		return totalPayUserCount;
	}
	
	//set方法forJson转换
	public void setGbInfo(GBWithPropMonthYear gbInfo) {
		this.gbInfo = gbInfo;
	}
	public void setDataList(List<PropertyGoodNewsRowDataEntity> dataList) {
		this.dataList = dataList;
	}
	public void setTotalSavedMoney(Long totalSavedMoney) {
		this.totalSavedMoney = totalSavedMoney;
	}
	public void setTotalPayUserCount(Long totalPayUserCount) {
		this.totalPayUserCount = totalPayUserCount;
	}
}
