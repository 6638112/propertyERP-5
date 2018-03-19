/**   
* Filename:    SmallPicUploadService.java   
* @version:    1.0  
* Create at:   2014年9月4日 上午2:47:23   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月4日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;

/**
 * Filename:    SmallPicUploadService.java
 * @version:    1.0.0
 * Create at:   2014年9月4日 上午2:47:23
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月4日       shiyl             1.0             1.0 Version
 */
public class SmallPicUploadService implements ISmallPicUploadService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	public static void main(String[] args) {
		String fileName ="aaa.jpg";
		int lastPointIndex = fileName.lastIndexOf(".");// 最后点的下标
		System.out.println(fileName.substring(0,lastPointIndex));
	}
	@Override
	public boolean uploadAllSmallPic(BusinessModelType businessModelType,String appBasePath,String picBasePath, String fileName, byte[] fileContent) {
		boolean uploadRes = true;
		//根据文件名及规格得到要生成的目标文件路径和列表
		int lastPointIndex = fileName.lastIndexOf(".");// 最后点的下标
		String fileType = null;// 文件类型
		boolean shouldMakeMini = false;// 是否需要生成小图
		if (lastPointIndex != -1) {
			fileType = fileName.substring(lastPointIndex+".".length());
			shouldMakeMini = true;
		}
		if(shouldMakeMini){
			//逐个生成目标文件byte[]
			Map<String,byte[]> goalFileResMap = new HashMap<String, byte[]>();
			Map<String,WidthHeight> guigeList = null;
			if(businessModelType==null){//为空则默认使用全部
				businessModelType = BusinessModelType.All;
			}
			guigeList = businessModelType.getGuigeList();
			
			for(String goalFileName:guigeList.keySet()){
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				if(shouldMakeMini){
					try {
						String smallIconPath = picBasePath+fileName.substring(0,lastPointIndex)+File.separator+goalFileName+"."+fileType;
						logger.debug("smallIconPath="+smallIconPath);
						//指定高宽压缩
//						byte[] convertByte = ImageZipUtil.zipImageFile(fileContent, tmpWidthHeight.getWidth(), tmpWidthHeight.getHeight(), SmallPicUploadConfig.Default_Quality);
						//按宽等比例压缩
//						byte[] convertByte = ImageZipUtil.zipImageFile(fileContent, tmpWidthHeight.getWidth(), null, SmallPicUploadConfig.Default_Quality);
						//按高等比例压缩
//						byte[] convertByte = ImageZipUtil.zipImageFile(fileContent,null,tmpWidthHeight.getHeight(),SmallPicUploadConfig.Default_Quality);//
						//按短边比例压缩
						byte[] convertByte = ImageZipUtil.zipImageFileAutoShort(fileContent,tmpWidthHeight.getWidth(),tmpWidthHeight.getHeight(),SmallPicUploadConfig.Default_Quality);//
						goalFileResMap.put(smallIconPath, convertByte);
					} catch (IOException e) {
						uploadRes = false;
						logger.info("zipImageFile  pic failed,fileName="+fileName+",picBasePath="+picBasePath+",fileType="+fileType+","+tmpWidthHeight.getFileName(),e);
					}
				}
			}
			//逐个上传
			for(String goalPath:goalFileResMap.keySet()){
				try {
					fileServerService.uploadFile(appBasePath,goalPath, goalFileResMap.get(goalPath));
				} catch (IOException e) {
					uploadRes = false;
					logger.info("upload small pic failed,"+goalPath,e);
				}
			}
		}
		return uploadRes;
	}
	
}
