package com.cnfantasia.server.ms.ebuyProduct.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.cnfantasia.server.business.commonBusiness.constant.SmallPicUploadConfig.BusinessModelType;
import com.cnfantasia.server.business.commonBusiness.entity.WidthHeight;
import com.cnfantasia.server.business.pub.utils.ImageZipUtil;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 商品图片处理工具类
 * 
 * @author liyulin
 * @version 1.0 2016年6月2日 下午1:33:04
 */
public final class ProductImageUtil {

	/**
	 * 生成小图, 并对原图都有压缩
	 * 
	 * @param bigImageFile 
	 * @param imageDir
	 */
	public final static void generateMiniImage(File bigImageFile, String imageDir) {
		Map<String, WidthHeight> guigeList = BusinessModelType.Market.getGuigeList();
		String descDirecBasePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + imageDir;

		String fileName = bigImageFile.getName();
		int lastPointIndex = fileName.lastIndexOf(".");
		String fileType = fileName.substring(lastPointIndex); //文件后缀名，带.号

		String goalDirectoryFilePath = descDirecBasePath + fileName.substring(0, lastPointIndex);
		
		// 创建小图的目标目录
		File goalFileDir = new File(goalDirectoryFilePath);
		if(!goalFileDir.exists()){
			goalFileDir.mkdirs();
		}

		for (String goalFileName : guigeList.keySet()) {
			String smallIcon = goalDirectoryFilePath + "/" + goalFileName + fileType;
			try {
				WidthHeight tmpWidthHeight = guigeList.get(goalFileName);
				ImageZipUtil.zipImageFile(bigImageFile.getAbsolutePath(), null, tmpWidthHeight.getHeight(), 1f, smallIcon);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
