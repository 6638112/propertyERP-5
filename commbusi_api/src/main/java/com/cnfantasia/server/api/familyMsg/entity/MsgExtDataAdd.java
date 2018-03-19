/**   
* Filename:    MsgExtDataAdd.java   
* @version:    1.0  
* Create at:   2015年3月12日 上午3:34:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月12日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.familyMsg.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    MsgExtDataAdd.java
 * @version:    1.0.0
 * Create at:   2015年3月12日 上午3:34:39
 * Description:家庭留言内容的拓展信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月12日       shiyl             1.0             1.0 Version
 */
public class MsgExtDataAdd implements Serializable{
	private static final long serialVersionUID = 1L;
	/**数据Id*/
	private BigInteger dataId;
	/**数据类型*/
	private Integer dateType;
	
	/**
	 * 构造方法
	 * @param dataId 数据Id
	 * @param dateType 数据类型
	 */
	public MsgExtDataAdd(BigInteger dataId,Integer dateType){
		this.dataId = dataId;
		this.dateType = dateType;
	}
	
	public BigInteger getDataId() {
		return dataId;
	}
	public void setDataId(BigInteger dataId) {
		this.dataId = dataId;
	}
	public Integer getDateType() {
		return dateType;
	}
	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}
	
}
