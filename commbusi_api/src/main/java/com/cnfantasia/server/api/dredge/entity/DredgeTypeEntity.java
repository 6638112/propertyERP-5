/**   
* Filename:    DredgeTypeEntity.java   
* @version:    1.0  
* Create at:   2016年1月12日 下午3:22:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年1月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.dredge.entity;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;
import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;

/**
 * Filename:    DredgeTypeEntity.java
 * @version:    1.0.0
 * Create at:   2016年1月12日 下午3:22:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年1月12日       shiyl             1.0             1.0 Version
 */
public class DredgeTypeEntity extends DredgeType{
	private static final long serialVersionUID = 1L;
	
	/**父类名称*/
	private String parentName;
	
	private List<DredgeType2nd> dredgeType2ndList;

	private String picUrlLight;
	private String picUrlGrey;
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<DredgeType2nd> getDredgeType2ndList() {
		return dredgeType2ndList;
	}

	public void setDredgeType2ndList(List<DredgeType2nd> dredgeType2ndList) {
		this.dredgeType2ndList = dredgeType2ndList;
	}

	public String getPicUrlLight() {
		return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, getPicUrl(), null);
	}

	public void setPicUrlLight(String picUrlLight) {
		this.picUrlLight = picUrlLight;
	}

	public String getPicUrlGrey() {
		String picUrl = getPicUrl();
		picUrl = picUrl.substring(0, picUrl.lastIndexOf(".")) + "_grey" + picUrl.substring(picUrl.lastIndexOf("."));
		return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, picUrl, null);
	}

	public void setPicUrlGrey(String picUrlGrey) {
		this.picUrlGrey = picUrlGrey;
	}
}
