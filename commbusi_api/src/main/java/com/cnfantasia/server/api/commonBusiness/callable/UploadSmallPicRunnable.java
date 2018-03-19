/**   
* Filename:    UploadSmallPicRunnable.java   
* @version:    1.0  
* Create at:   2014年9月4日 上午3:35:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.callable;

import java.util.concurrent.Callable;

import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.service.ISmallPicUploadService;

/**
 * Filename:    UploadSmallPicRunnable.java
 * @version:    1.0.0
 * Create at:   2014年9月4日 上午3:35:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月4日       shiyl             1.0             1.0 Version
 */
public class UploadSmallPicRunnable implements Callable<Boolean>{
	private ISmallPicUploadService smallPicUploadService;
	private BusinessModelType businessModelType;
	private String appBasePath;
	private String picBasePath;
	private String fileName;
	private byte[] fileContent;
	
	public UploadSmallPicRunnable(ISmallPicUploadService smallPicUploadService,BusinessModelType businessModelType,String appBasePath,String picBasePath, String fileName, byte[] fileContent){
		this.smallPicUploadService = smallPicUploadService;
		this.businessModelType = businessModelType;
		this.appBasePath = appBasePath;
		this.picBasePath = picBasePath;
		this.fileName = fileName;
		this.fileContent = fileContent;
	}
	

	@Override
	public Boolean call() throws Exception {
		return smallPicUploadService.uploadAllSmallPic(businessModelType,appBasePath,picBasePath, fileName, fileContent);
	}
	
}
