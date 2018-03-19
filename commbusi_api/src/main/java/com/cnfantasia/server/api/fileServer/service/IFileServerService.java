/**   
* Filename:    IFileServerService.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午2:12:57   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.fileServer.service;

import java.io.IOException;
import java.util.Date;

import com.cnfantasia.server.domain.pub.BaseEntity;
import com.google.zxing.common.BitMatrix;

/**
 * Filename:    IFileServerService.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午2:12:57
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
public interface IFileServerService {
//	/**
//	 * 获取Url访问路径
//	 * @param relativePath
//	 * @return
//	 */
//	public String getAccessUrl(String relativePath);
	/**
	 * 获取Url访问路径
	 * @param relativePath
	 * @return
	 */
	public String getAccessUrl(String relativePath,BaseEntity baseEntity);
	public String getAccessUrl(String relativePath,String updTime);
//	public String getAccessBaseUrl();
	/**
	 * 上传文件
	 * @param relativeFilePath
	 * @param fileContent
	 * @return
	 */
	public void uploadFile(String relativeFilePath,byte[] fileContent) throws IOException;
	/**
	 * 上传文件
	 * @param relativeFilePath
	 * @param fileContent
	 * @return
	 */
	public void uploadFile(String appBasePath,String relativeFilePath,byte[] fileContent) throws IOException;
	/**
	 * 上传二维码图片
	 * @param matrix
	 * @param filePostfix 生成二维码图片的格式：png,jpeg,gif等格式
	 * @param relativeFilePath 生成二维码图片位置
	 * @throws IOException
	 */
	public void uploadQCodeFile(BitMatrix matrix, String filePostfix, String relativeFilePath) throws IOException;
	/**
	 * 下载文件
	 * @param relativeFilePath
	 * @return
	 */
	public byte[] downLoad(String relativeFilePath) throws IOException;
	
	/**
	 * 获取文件服务器上传目录的根路径
	 * @return
	 */
	public String getFileServierUploadBasePath();
	
	public String getAccessUrl(String relativePath, Date updTime);
}
