/**   
* Filename:    ISmallPicUploadService.java   
* @version:    1.0  
* Create at:   2014年9月4日 上午2:47:00   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;

/**
 * Filename:    ISmallPicUploadService.java
 * @version:    1.0.0
 * Create at:   2014年9月4日 上午2:47:00
 * Description: 小图上传服务类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月4日       shiyl             1.0             1.0 Version
 */
public interface ISmallPicUploadService {
	/**
	 * 上传小图
	 * @param businessModelType 页面模块类型
	 * @param appBasePath 应用根路径
	 * @param picBasePath 目标目录
	 * @param fileName 当前文件名
	 * @param fileContent 当前文件内容
	 * @return
	 */
	public boolean uploadAllSmallPic(BusinessModelType businessModelType,String appBasePath,String picBasePath,String fileName,byte[] fileContent);
	
}
