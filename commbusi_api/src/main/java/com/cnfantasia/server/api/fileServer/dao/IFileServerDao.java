/**   
* Filename:    IFileServerDao.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午1:06:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.fileServer.dao;

import java.io.IOException;

import com.google.zxing.common.BitMatrix;

/**
 * Filename:    IFileServerDao.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午1:06:34
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
public interface IFileServerDao{
	/**
	 * 上传文件
	 * @param relativeFilePath
	 * @param fileContent
	 * @return
	 */
	public void uploadFileAllPath(String allFilePath,byte[] fileContent) throws IOException;
//	public void uploadFileAllPath(String appBasePath,String relativeFilePath,byte[] fileContent) throws IOException;
	/**
	 * 上传二维码图片
	 * @param matrix
	 * @param filePostfix 生成二维码图片的格式：png,jpeg,gif等格式
	 * @param relativeFilePath 生成二维码图片位置
	 * @throws IOException
	 */
	public void uploadQCodeFileAllPath(BitMatrix matrix, String filePostfix, String allFilePath) throws IOException;
	/**
	 * 下载文件
	 * @param relativeFilePath
	 * @return
	 */
	public byte[] downLoadAllPath(String allFilePath) throws IOException;
	
//	public String getAppBasePath();
}
