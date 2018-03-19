/**   
* Filename:    RoomInfoPicParamEntity.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午9:17:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.room.entity;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    RoomInfoPicParamEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午9:17:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class RoomInfoPicParamEntity {
	/**图片跟路径*/
	private String picBasePath;
	/**图片格式*/
	private String goalFileType;
	/**高*/
	private Integer height;
	/**宽*/
	private Integer width;
	/**
	 * 构造方法
	 * @param picBasePath 图片跟路径
	 * @param goalFileType 图片格式
	 * @param height 高
	 * @param width 宽
	 */
	public RoomInfoPicParamEntity(String picBasePath,String goalFileType,Integer height,Integer width){
		this.picBasePath= picBasePath;
		this.goalFileType= goalFileType;
		this.height= height;
		this.width= width;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public String getPicBasePath() {
		return picBasePath;
	}
	public void setPicBasePath(String picBasePath) {
		this.picBasePath = picBasePath;
	}
	public String getGoalFileType() {
		return goalFileType;
	}
	public void setGoalFileType(String goalFileType) {
		this.goalFileType = goalFileType;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	
}
