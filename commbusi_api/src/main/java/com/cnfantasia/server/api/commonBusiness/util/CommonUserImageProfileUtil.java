/**   
 * Filename:    CommonUserImageProfileUtil.java   
 * @version:    1.0  
 * Create at:   2014年5月29日 上午3:54:17   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年5月29日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.commonBusiness.util;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.HttpClientUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: CommonUserImageProfileUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年5月29日 上午3:54:17 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年5月29日 shiyl 1.0 1.0 Version
 */
public class CommonUserImageProfileUtil {
	private static Log logger = LogFactory.getLog(CommonUserImageProfileUtil.class);
	public static RequestFileEntity parseRequsetImageInfo(UserImageParamEntity userImageParamEntity,HttpServletRequest request) {
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		BigInteger userId = UserContext.getOperIdBigInteger();
		String userImgprofileName = null;
		byte[] userImage = null;
		if (request instanceof MultipartHttpServletRequest) {// 获取图片
			// 转型为MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile imgProfile = multipartRequest.getFile("imgProfile");
			// String fileName=imgProfile.getOriginalFilename();//取得文件名
//			String fileType = imgProfile.getContentType();// 取得上传文件的类型和格式
//			String format = fileType.substring(fileType.indexOf("/") + 1, fileType.length()).toLowerCase();// 取得上传文件格式
			String fileName = imgProfile.getOriginalFilename();
			String format = fileName.substring(fileName.lastIndexOf(".")+1);
			// 检查上传文件格式是否支持
			if (!userImageParamEntity.getSupportFormates().contains(format)) {
				throw new BusinessRuntimeException("CommonUserImageProfileUtil.parseRequsetImageInfo.typeNotSupport.error",
						new Object[] { format });
			} else if (imgProfile.getSize() > userImageParamEntity.getMaxSize()) {// 检查上传文件的大小，不超过200k
				throw new BusinessRuntimeException("CommonUserImageProfileUtil.parseRequsetImageInfo.sizeTooBig.error",
						new Object[] { userImageParamEntity.getMaxSize() + "byte" });
			}
			try {
				userImage = imgProfile.getBytes();
				// 用户图像名称：用户Id+_当前时间字符串+上传文件类型
				userImgprofileName = getUserImgprofileName(userId, format);
			} catch (IOException e) {
				logger.debug("imgProfile.getBytes cause IOException:" + e.getMessage());
				throw new BusinessRuntimeException("CommonUserImageProfileUtil.parseRequsetImageInfo.getBytes.error");
			}
		} else {
			String imgProfile = request.getParameter("imgProfile");
			if (!StringUtils.isEmpty(imgProfile)) {
				try {
					userImage = HttpClientUtil.doGet(imgProfile);
				} catch (Exception e) {
					logger.debug("imgProfile.getBytesByUrl cause IOException:" + e.getMessage());
					throw new BusinessRuntimeException("CommonUserImageProfileUtil.parseRequsetImageInfo.getBytesByUrl.error",e);
				}
				// 用户图像名称：用户Id+当前时间字符串+上传文件类型
				String defaultFileType = "jpg";// 默认jpg格式图片
//				if (imgProfile.lastIndexOf(".") != -1) {
//					defaultFileType = imgProfile.substring(imgProfile.lastIndexOf(".") + 1);
//				}
				userImgprofileName = getUserImgprofileName(userId, defaultFileType);
			}
		}
		RequestFileEntity userImageEntity = new RequestFileEntity(userImgprofileName, userImage);
		return userImageEntity;
	}
	
	private static String getUserImgprofileName(BigInteger userId,String format){
		String userImgprofileName = null;
		userImgprofileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr()).append(RandomUtils.createRandom(false, 6)).append(".")
				.append(format).toString();
		return userImgprofileName;
	}
}
