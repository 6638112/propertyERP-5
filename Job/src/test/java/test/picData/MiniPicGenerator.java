/**   
 * Filename:    MiniPicGenerator.java   
 * @version:    1.0  
 * Create at:   2014年9月4日 上午12:41:48   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年9月4日    shiyl      1.0         1.0 Version   
 */
package test.picData;

import java.io.File;
import java.util.Map;

import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig;
import com.cnfantasia.server.api.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.api.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;

/**
 * Filename: MiniPicGenerator.java
 * 
 * @version: 1.0.0 Create at: 2014年9月4日 上午12:41:48 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年9月4日 shiyl 1.0 1.0 Version
 */
public class MiniPicGenerator {
//	 public static final String srcDirPath = "D:/ProgramData/workspace/example/API/src/main/webapp";
	 public static final String srcDirPath = "F:/zljt_product_pic";
//	public static final String srcDirPath = "F:\\programs\\Fantasia\\我的任务\\_backup\\API资源包备份\\生产环境\\2014-9-5 113041\\API";
	public static final String goalDirPath = "F:\\API";
	public static final String[] picTags = new String[] { "jpg", "png","camera_0","camera_1","camera_2","jpeg" };
	public static float quality = SmallPicUploadConfig.Default_Quality;
	public static final boolean refreshAll = true;//已存在的文件是否刷新
	
//	public static final Map<String,WidthHeight> guigeList = BusinessModelType.User.getGuigeList();//用户
//	public static final Map<String,WidthHeight> guigeList = BusinessModelType.Market.getGuigeList();//电商-超市
//	public static final Map<String,WidthHeight> guigeList = BusinessModelType.Kitchen.getGuigeList();//厨房
	public static final Map<String,WidthHeight> guigeList = BusinessModelType.JieFang.getGuigeList();//街坊
//	public static final Map<String,WidthHeight> guigeList = BusinessModelType.Pinyipin.getGuigeList();//拼一拼
//	public static final Map<String,WidthHeight> guigeList = BusinessModelType.Exchange.getGuigeList();//换一换
	
	public static void main(String[] args) {
		File fileDir = new File(srcDirPath);
		File[] fileDirAll = fileDir.listFiles();
		for (File signalFileDir : fileDirAll) {
			if (signalFileDir.isDirectory()) {
				File[] infoListFile = signalFileDir.listFiles();
				for (File tmpFile : infoListFile) {
					// 解析文件名
					if (tmpFile.isFile()) {
						String fileName = tmpFile.getName();// 文件名称
						int lastPointIndex = fileName.lastIndexOf(".");// 最后点的下标
						String fileType = null;// 文件类型
						boolean shouldMakeMini = false;// 是否需要生成小图
						if (lastPointIndex != -1) {
							fileType = fileName.substring(lastPointIndex+".".length());
							if (fileType != null) {
								for (String tmpTag : picTags) {
									if (fileType.equalsIgnoreCase(tmpTag)) {
										shouldMakeMini = true;
										break;
									}
								}
							}
						}
						if (shouldMakeMini) {
							String currGoalDirecBasePath = signalFileDir.getAbsolutePath().substring(srcDirPath.length());
							String goalDirectoryFilePath =goalDirPath+currGoalDirecBasePath+"/"+fileName.substring(0, lastPointIndex);
							// 创建目标目录
//							System.out.println("准备创建目标目录:"+goalDirectoryFilePath);
							File goalFileDir = new File(goalDirectoryFilePath);
							if(!goalFileDir.exists()){
								goalFileDir.mkdirs();
							}
							// 根据定义的规格，生成多个目标文件
//							System.out.println("准备生成小图片：" + fileName);
							for(String goalFileName:guigeList.keySet()){
								WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
								String smallIcon = goalDirectoryFilePath+"/"+goalFileName+"."+fileType;
								if(!new File(smallIcon).exists()||refreshAll){
									System.out.println(tmpFile.getAbsolutePath()+"--准备生成目标文件："+smallIcon);
									try {
										String resSmallIcon = ImageZipUtil.zipImageFile(tmpFile.getAbsolutePath(),null,tmpWidthHeight.getHeight(), quality, smallIcon);
//										String resSmallIcon = ImageZipUtil.zipImageFile(tmpFile.getAbsolutePath(),tmpWidth.getWidth(),null, quality, smallIcon);
//										String resSmallIcon = ImageZipUtil.zipImageFile(tmpFile.getAbsolutePath(), tmpWidth.getWidth(),tmpWidth.getHeight(), quality, smallIcon);
										System.out.println(resSmallIcon+"创建成功");
									} catch (Exception e) {
										System.out.println(smallIcon+"创建失败");
									}
								}
							}
						} else {
							System.out.println("文件" + fileName + " 未作处理。");
						}
					}
				}
			}
		}

	}
}