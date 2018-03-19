/**   
* Filename:    CommonMultiFileUtil.java   
* @version:    1.0  
* Create at:   2014年6月5日 上午11:15:49   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.business.pub.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnfantasia.server.business.pub.entity.MultiFileEntity;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;

/**
 * Filename:    CommonMultiFileUtil.java
 * @version:    1.0.0
 * Create at:   2014年6月5日 上午11:15:49
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月5日       shiyl             1.0             1.0 Version
 */
public class CommonMultiFileUtil {
	private static Log logger = LogFactory.getLog(CommonMultiFileUtil.class);
	public static MultiFileEntity parseRequsetFileInfo(HttpServletRequest request,String parameter) {
		String resultFileName = null;
		byte[] datas = null;
		InputStream inputStream;
		if(!(request instanceof MultipartHttpServletRequest)){
			throw new ValidateRuntimeException("CommonMultiFileUtil.parseRequsetFileInfo.unsupportedRequest.error");
		}
		{// 获取文件
			// 转型为MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadFile = multipartRequest.getFile(parameter);
			if(uploadFile==null){return  new MultiFileEntity(resultFileName, datas,null);}//syl-add-2014-10-10 11:48:06
			String fileName = uploadFile.getOriginalFilename();
//			String format = fileName.substring(fileName.lastIndexOf(".")+1);
			try {
				datas = uploadFile.getBytes();
				resultFileName = fileName;
				inputStream = uploadFile.getInputStream();
			} catch (IOException e) {
				logger.debug("parseRequsetFileInfo.getBytes cause IOException:" + e.getMessage());
				throw new BusinessRuntimeException("CommonMultiFileUtil.parseRequsetFileInfo.getBytes.error");
			}
		}
		MultiFileEntity multiFileEntity = new MultiFileEntity(resultFileName, datas,inputStream);
		return multiFileEntity;
	}
	
	/**
	 * 上传由Request传入的文件到Web服务器中
	 * 
	 * @param uploadImageFile
	 *            Spring的Reqeust请求带上的文件
	 * @param imageServerBasePath
	 *            文件服务器的根路径
	 * @param imagePath
	 *            待上传的文件所要存的子路径
	 * @param moduleName
	 *            模块名称
	 * @param id
	 *            文件所依据记录的id
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadFile(MultipartFile uploadImageFile, String imageServerBasePath, String imagePath, String moduleName, BigInteger id)
			throws IOException {
		String filePath = imageServerBasePath + "/" + imagePath;

		File fileDir = new File(filePath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		String thisFileName = "";
		if (uploadImageFile != null && !StringUtils.isEmpty(uploadImageFile.getOriginalFilename())) {//有上传图片 
			//文件名要做到唯一：模块名 + 当前时间 + id
			thisFileName = moduleName + System.currentTimeMillis() + id
					+ uploadImageFile.getOriginalFilename().substring((uploadImageFile.getOriginalFilename().lastIndexOf(".")));
			File file = new File(filePath + "/" + thisFileName);
			uploadImageFile.transferTo(file);
		}

		return thisFileName;
	}
	
	/**
	 * 上传文件到服务器中
	 * @author wenfq 
	 * @param suffix 文件后缀名，包含"."
	 * @param imagePath 图片路径，包含前后/
	 * @return 文件路径，如/bankCollection/exportDetailFile1492482424029.txt
	 */
	public static String uploadFile(String suffix, String fileContent, String imageServerBasePath, String imagePath, String moduleName)
			throws IOException {
		String filePath = imageServerBasePath + imagePath;

		File fileDir = new File(filePath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		String thisFileName = "";
		if (!StringUtils.isEmpty(suffix)) {// 有上传图片
			// 文件名要做到唯一：模块名 + 当前时间 + id
			thisFileName = moduleName + System.currentTimeMillis() + suffix;
			File file = new File(filePath + thisFileName);
			//PrintWriter pw = new PrintWriter(new FileOutputStream(file), "UTF-8");  
			PrintWriter pw = new PrintWriter(file, "UTF-8");  
			pw.print(fileContent);
			pw.close();
		}

		return imagePath + thisFileName;
	}
	
	
	/**
	 * 上传由前端JS压缩后的图片 
	 * @param jsImageFileContent JS压缩后的图片内容，Base64加密后的。取第一个逗号后的内容
	 */
	public static String uploadJSFile(String jsImageFileContent, String imageServerBasePath, String imagePath, String moduleName, BigInteger id)
			throws IOException {
		String filePath = imageServerBasePath + imagePath;

		File fileDir = new File(filePath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		String thisFileName = "";
		if (!StringUtils.isEmpty(jsImageFileContent)) {//有上传图片 
			//文件名要做到唯一：模块名 + 当前时间 + id
			thisFileName = moduleName + RandomUtils.nextInt(4) + System.currentTimeMillis() + id + ".jpg";// 不管输出什么格式图片，转为jpg，此处不需改动
			File file = new File(filePath + "/" + thisFileName);
			
            //通过Base64解密，将图片数据解密成字节数组
            String picmsg = jsImageFileContent.substring(jsImageFileContent.indexOf(",")  + 1);
            byte[] bytes = com.alibaba.fastjson.util.Base64.decodeFast(picmsg);
            //构造字节数组输入流
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //读取输入流的数据
            BufferedImage bi = ImageIO.read(bais);
            //将数据信息写进图片文件中
            ImageIO.write(bi, "jpg", file);// 不管输出什么格式图片，转为jpg，此处不需改动
            bais.close();
		}

		return thisFileName;
	}
}
