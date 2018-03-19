/**   
* Filename:    PriGiftUploadParam.java   
* @version:    1.0  
* Create at:   2015年9月15日 下午8:20:11   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.util.List;


/**
 * Filename:    PriGiftUploadParam.java
 * @version:    1.0.0
 * Create at:   2015年9月15日 下午8:20:11
 * Description:奖品信息上传
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月15日       shiyl             1.0             1.0 Version
 */
public class PriGiftUploadParam {
	private Double number;
	private String unit;
	private List<UnitGiftParam> unitGiftParamList;
	
	public PriGiftUploadParam(Double number,String unit,List<UnitGiftParam> unitGiftParamList){
		this.number = number;
		this.unit = unit;
		this.unitGiftParamList = unitGiftParamList;
	}
	
	public Double getNumber() {
		return number;
	}
	public void setNumber(Double number) {
		this.number = number;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<UnitGiftParam> getUnitGiftParamList() {
		return unitGiftParamList;
	}
	public void setUnitGiftParamList(java.util.List<UnitGiftParam> unitGiftParamList) {
		this.unitGiftParamList = unitGiftParamList;
	}
	
}
