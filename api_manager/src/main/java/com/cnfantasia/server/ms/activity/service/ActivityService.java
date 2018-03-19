package com.cnfantasia.server.ms.activity.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.ebuyAdvertise.dao.IEbuyAdvertiseBaseDao;
import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.ms.activity.dao.IActivityDao;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant;
import com.cnfantasia.server.ms.advertise.dao.IAdvertiseForOmsDao;
import com.cnfantasia.server.ms.advertise.entity.AdvertiseDto;
import com.cnfantasia.server.ms.advertise.service.IAdvertiseForOmsService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 活动管理
 * 
 * @author liyulin
 * @version 1.0 2016年12月27日 下午2:54:25
 */
public class ActivityService implements IActivityService {

	private IActivityDao activityDao;
	private IEbuyAdvertiseBaseDao ebuyAdvertiseBaseDao;
	private IAdvertiseForOmsDao advertiseForOmsDao;
	private IAdvertiseForOmsService advertiseForOmsService;

	public void setActivityDao(IActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public void setEbuyAdvertiseBaseDao(IEbuyAdvertiseBaseDao ebuyAdvertiseBaseDao) {
		this.ebuyAdvertiseBaseDao = ebuyAdvertiseBaseDao;
	}

	public void setAdvertiseForOmsDao(IAdvertiseForOmsDao advertiseForOmsDao) {
		this.advertiseForOmsDao = advertiseForOmsDao;
	}

	public void setAdvertiseForOmsService(IAdvertiseForOmsService advertiseForOmsService) {
		this.advertiseForOmsService = advertiseForOmsService;
	}

	/**
	 * 查询活动列表（code=ACTIVITY_ENTRANCE）
	 * 
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> selectActivities() {
		return activityDao.selectActivities();
	}

	/**
	 * 更新活动
	 * 
	 * @param advertiseDto
	 * @return
	 */
	@Override
	@Transactional
	public boolean updateActivity(AdvertiseDto advertiseDto, HttpServletRequest request) {
		String[] pic = null;
		try {
			pic = uploadImage(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
		advertiseDto.getEbuyAdvertise().setPicName(pic[0] + ";" + pic[1]);
		int affectedCount = ebuyAdvertiseBaseDao.updateEbuyAdvertise(advertiseDto.getEbuyAdvertise());

		//处理用户范围
		advertiseForOmsDao.deleteSaEbSupplyByAdvId(advertiseDto.getEbuyAdvertise().getId(), AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
		int areaType = advertiseDto.getAreaType() == null ? 1 : advertiseDto.getAreaType();
		List<BigInteger> cityIds = advertiseDto.getCityIds() == null ? null : new ArrayList<BigInteger>(advertiseDto.getCityIds());
		List<BigInteger> gbIds = advertiseDto.getGbIds() == null ? null : new ArrayList<BigInteger>(advertiseDto.getGbIds());
		advertiseForOmsService.dealServiceArea(areaType, advertiseDto.getEbuyAdvertise().getId(), cityIds,null, gbIds, AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
		return affectedCount > 0;
	}

	private String[] uploadImage(HttpServletRequest request) throws IOException {
		String appPic = ParamUtils.getString(request, "appPic", "");
		String previewPic = ParamUtils.getString(request, "previewPic", "");
		// 浏览图片
		// 详情图片
		if (request instanceof MultipartHttpServletRequest) {
			String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + PathConstants.Advertise_Pic_base;

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// app显示图片
			MultipartFile appPicFile = multipartRequest.getFile("appPicFile");
			if (appPicFile != null && !StringUtils.isEmpty(appPicFile.getOriginalFilename())) {
				int indexOfDot = appPicFile.getOriginalFilename().indexOf(".");
				String fileNameC = UUIDGenerater.getFileName() + appPicFile.getOriginalFilename().substring(indexOfDot);
				File fileC = new File(picPath + fileNameC);
				if (!fileC.exists()) {
					fileC.mkdirs();
				}
				appPicFile.transferTo(fileC);
				appPic = fileNameC;
			}

			// 预览图片
			MultipartFile previewPicFile = multipartRequest.getFile("previewPicFile");
			if (previewPicFile != null && !StringUtils.isEmpty(previewPicFile.getOriginalFilename())) {
				int indexOfDot = previewPicFile.getOriginalFilename().indexOf(".");
				String fileNameC = UUIDGenerater.getFileName() + previewPicFile.getOriginalFilename().substring(indexOfDot);
				File fileC = new File(picPath + fileNameC);
				if (!fileC.exists()) {
					fileC.mkdirs();
				}
				previewPicFile.transferTo(fileC);
				previewPic = fileNameC;
			}
		}
		return new String[] { appPic, previewPic };
	}
	
}
