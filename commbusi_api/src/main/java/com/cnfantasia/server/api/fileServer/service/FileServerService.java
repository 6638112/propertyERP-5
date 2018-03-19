/**   
* Filename:    FileServerService.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午2:13:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.fileServer.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.fileServer.dao.IFileServerDao;
import com.cnfantasia.server.api.fileServer.entity.FileServerConfigEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.BaseEntity;
import com.google.zxing.common.BitMatrix;

/**
 * Filename:    FileServerService.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午2:13:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
@Service
public class FileServerService implements IFileServerService{
	private IFileServerDao fileServerDao;
	public void setFileServerDao(IFileServerDao fileServerDao) {
		this.fileServerDao = fileServerDao;
	}
	
	private ISysParamParser fileServerParamParser;
	public void setFileServerParamParser(ISysParamParser fileServerParamParser) {
		this.fileServerParamParser = fileServerParamParser;
	}
	
	@Override
	public String getAccessUrl(String relativePath,BaseEntity baseEntity){
		String updTime = null;
		if(baseEntity!=null&&baseEntity instanceof BaseEntity){
			if(!StringUtils.isEmpty(baseEntity.getSys0UpdTime())){
				updTime = baseEntity.getSys0UpdTime();
			}
		}
		return getAccessUrl(relativePath, updTime);
	}
	
	@Override
	public String getAccessUrl(String relativePath, Date updTime){
		String accessUrl = getAccessUrl(relativePath);
		if(updTime != null){
			return accessUrl + "?" + DateUtil.formatFileName.get().format(updTime);
		} else {
			return accessUrl;
		}
	}
	
	@Override
	public String getAccessUrl(String relativePath,String updTime){
		String accessUrl = getAccessUrl(relativePath);
		StringBuffer sbfAppend = new StringBuffer();
		if(!StringUtils.isEmpty(updTime)){
				Date date = null;
				try {
					date = DateUtil.formatSecond.get().parse(updTime);
					sbfAppend.append("?");
					sbfAppend.append(DateUtil.formatFileName.get().format(date));
				} catch (ParseException e) {
					throw new RuntimeException(DateUtil.class + "Time parse error:" + e.getMessage() + ",time is:" + updTime);
				}
		}
		return accessUrl+sbfAppend.toString();
	}
	
	private String getAccessUrl(String relativePath) {
		String res = getAccessBaseUrl();
		if(!StringUtils.isEmpty(relativePath)){
			res = res+relativePath;
		}
		return res;
	}
	
	private String getAccessBaseUrl() {
		FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
		String accessBaseUrl = fileServerConfigEntity.getAccessBaseUrl();
		return accessBaseUrl;
		
//		String res = getFileServierBasePath();
//		return res;
	}
	
	@Override
	public void uploadFile(String relativeFilePath, byte[] fileContent) throws IOException {
		String allPath = getFileServierUploadAllPath(relativeFilePath);
		fileServerDao.uploadFileAllPath(allPath, fileContent);
	}
	
	@Override
	public void uploadFile(String appBasePath, String relativeFilePath, byte[] fileContent) throws IOException {
//		fileServerDao.uploadFile(appBasePath,relativeFilePath, fileContent);
		String allPath = appBasePath+relativeFilePath;
		fileServerDao.uploadFileAllPath(allPath, fileContent);
	}
	
	@Override
	public byte[] downLoad(String relativeFilePath) throws IOException {
		String allPath = getFileServierUploadAllPath(relativeFilePath);
		byte[] downLoadRes = fileServerDao.downLoadAllPath(allPath);
		return downLoadRes;
	}

	@Override
	public void uploadQCodeFile(BitMatrix matrix, String filePostfix, String relativeFilePath) throws IOException {
		String allPath = getFileServierUploadAllPath(relativeFilePath);
		fileServerDao.uploadQCodeFileAllPath(matrix, filePostfix, allPath);
	}

	@Override
	public String getFileServierUploadBasePath() {
//		return fileServerDao.getAppBasePath();
	 //	例如 : D:\ProgramGreen\apache-tomcat-6.0.39\webapps\API\relativePath
		
//	 HttpServletRequest request = SessionManager.getRequest();
//	 String basePath=new StringBuffer().append(request.getSession().getServletContext().getRealPath("/")).toString();
//	 return basePath;
	 
		FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
		String localFileDir = fileServerConfigEntity.getLocalFileDir();
		return localFileDir;
	 
	}
	
	private String getFileServierUploadAllPath(String relativePath) {
		String res = getFileServierUploadBasePath();
		if (!StringUtils.isEmpty(relativePath)) {
			res = res + relativePath;
		}
		return res;
	}
	
	
}
