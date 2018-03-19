/**   
 * Filename:    FileServerDaoLocalTomcatImpl.java   
 * @version:    1.0  
 * Create at:   2014年5月29日 上午1:16:41   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月29日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.fileServer.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.cnfantasia.server.api.pub.qrcode.QRCodeUtil;
import com.cnfantasia.server.common.utils.FileUtils;
import com.google.zxing.common.BitMatrix;

/**
 * Filename: FileServerDaoLocalTomcatImpl.java
 * 
 * @version: 1.0.0 Create at: 2014年5月29日 上午1:16:41 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月29日 shiyl 1.0 1.0 Version
 */
@Repository
public class FileServerDaoLocalTomcatImpl implements IFileServerDao {
	private Log logger = LogFactory.getLog(getClass());

	@Override
	public void uploadFileAllPath(String allFilePath, byte[] fileContent) throws IOException {
//		String goalFilePath = getAbsolutePath(allFilePath);
		String goalFilePath = allFilePath;
		doUpload(goalFilePath, fileContent);
	}

	private void doUpload(String goalFilePath, byte[] fileContent) throws IOException {
		logger.debug("FileServerDaoLocalTomcatImpl.goalFilePath src01 is:" + goalFilePath);
		goalFilePath = goalFilePath.replaceAll("/", "\\" + File.separator);
		String dir = goalFilePath.substring(0, goalFilePath.lastIndexOf(File.separator));
		File fileDir = new File(dir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File goalFile = new File(goalFilePath);
		goalFile.createNewFile();
		FileUtils.byteToFile(fileContent, goalFilePath);
	}

//	@Override
//	public void uploadFile(String appBasePath, String relativeFilePath, byte[] fileContent) throws IOException {
//		String goalFilePath = appBasePath + relativeFilePath;
//		doUpload(goalFilePath, fileContent);
//	}

	@Override
	public byte[] downLoadAllPath(String allFilePath) throws IOException {
//		String goalFilePath = getAbsolutePath(allFilePath);
		String goalFilePath = allFilePath;
		byte[] resBytes = null;
		resBytes = FileUtils.fileToByte(goalFilePath);
		return resBytes;
	}

	@Override
	public void uploadQCodeFileAllPath(BitMatrix matrix, String filePostfix, String allFilePath) throws IOException {
//		String goalFilePath = getAbsolutePath(relativeFilePath);
		String goalFilePath = allFilePath;
		BufferedImage image = QRCodeUtil.toBufferedImage(matrix);
		File file = new File(goalFilePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		file.createNewFile();
		ImageIO.write(image, filePostfix, file);
	}

////	@Override
//	private String getAppBasePath() {
//	//	例如 : D:\ProgramGreen\apache-tomcat-6.0.39\webapps\API\relativePath
////		 HttpServletRequest request = SessionManager.getRequest();
////		 String basePath=new
////		 StringBuffer().append(request.getSession().getServletContext().getRealPath("/")).toString();
////		 return basePath;
//
//		FileServerConfigEntity fileServerConfigEntity = fileServerParamParser.parseParamValue();
//		String accessBaseUrl = fileServerConfigEntity.getAccessBaseUrl();
//		return accessBaseUrl;
//	}

	public static void main(String[] args) {
		String goalFilePath = "D:\\ProgramGreen\\apache-tomcat-6.0.39\\webapps\\API\\userProfilePic/400392014-09-04_13_25_190va0cb.jpg";
		// goalFilePath = goalFilePath.replaceAll("/", "\\\\");
		goalFilePath = goalFilePath.replaceAll("/", "\\" + File.separator);
		System.out.println(goalFilePath);
	}

}
