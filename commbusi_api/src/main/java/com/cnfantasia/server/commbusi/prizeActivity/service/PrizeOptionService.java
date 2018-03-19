/**   
* Filename:    PrizeOptionService.java   
* @version:    1.0  
* Create at:   2015年9月10日 上午11:41:34   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityConstant;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict.MsPrizeGift_ConvertStatus;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict.MsPrizeOption_Status;
import com.cnfantasia.server.commbusi.prizeActivity.dao.IPrizeActivityDao;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftRepeatMap;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftUqMarkCode;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeGiftForPrize;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionEntity;
import com.cnfantasia.server.commbusi.prizeActivity.entity.MsPrizeOptionForList;
import com.cnfantasia.server.commbusi.prizeActivity.entity.PriGiftUploadParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.UnitGiftParam;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.msPrizeGift.dao.IMsPrizeGiftBaseDao;
import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;
import com.cnfantasia.server.domainbase.msPrizeGiftCode.dao.IMsPrizeGiftCodeBaseDao;
import com.cnfantasia.server.domainbase.msPrizeGiftCode.entity.MsPrizeGiftCode;
import com.cnfantasia.server.domainbase.msPrizeOption.dao.IMsPrizeOptionBaseDao;
import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;
import com.cnfantasia.server.domainbase.msPrizeOptionCity.dao.IMsPrizeOptionCityBaseDao;
import com.cnfantasia.server.domainbase.msPrizeOptionCity.entity.MsPrizeOptionCity;

/**
 * Filename:    PrizeOptionService.java
 * @version:    1.0.0
 * Create at:   2015年9月10日 上午11:41:34
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月10日       shiyl             1.0             1.0 Version
 */
public class PrizeOptionService implements IPrizeOptionService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPrizeActivityDao prizeActivityDao;
	public void setPrizeActivityDao(IPrizeActivityDao prizeActivityDao) {
		this.prizeActivityDao = prizeActivityDao;
	}
	
	private IMsPrizeOptionBaseDao msPrizeOptionBaseDao;
	public void setMsPrizeOptionBaseDao(IMsPrizeOptionBaseDao msPrizeOptionBaseDao) {
		this.msPrizeOptionBaseDao = msPrizeOptionBaseDao;
	}
	
	private IMsPrizeGiftBaseDao msPrizeGiftBaseDao;
	public void setMsPrizeGiftBaseDao(IMsPrizeGiftBaseDao msPrizeGiftBaseDao) {
		this.msPrizeGiftBaseDao = msPrizeGiftBaseDao;
	}
	
	private IMsPrizeGiftCodeBaseDao msPrizeGiftCodeBaseDao;
	public void setMsPrizeGiftCodeBaseDao(
			IMsPrizeGiftCodeBaseDao msPrizeGiftCodeBaseDao) {
		this.msPrizeGiftCodeBaseDao = msPrizeGiftCodeBaseDao;
	}
	
	private IMsPrizeOptionCityBaseDao msPrizeOptionCityBaseDao;
	public void setMsPrizeOptionCityBaseDao(
			IMsPrizeOptionCityBaseDao msPrizeOptionCityBaseDao) {
		this.msPrizeOptionCityBaseDao = msPrizeOptionCityBaseDao;
	}

	@Override
	public List<MsPrizeOptionForList> getMsPrizeOptionList(String name,
			Integer qryStatus,Integer useStatus, PageModel pageModel) {
		return prizeActivityDao.selectMsPrizeOptionList(name,qryStatus,useStatus,pageModel);
	}

	@Override
	public MsPrizeOptionEntity getMsPrizeOptionDetail(BigInteger priOptId) {
		return prizeActivityDao.selectMsPrizeOptionDetail(priOptId);
	}
	
	private void validatePriOptCanDelOrUpd(BigInteger priOptId){
		List<String> actNameList = prizeActivityDao.selectMsPrizeOptionIsUsedPriActList(priOptId);
		if(actNameList!=null&&actNameList.size()>0){
			throw new BusinessRuntimeException("PrizeOptionService.validatePriOptCanDelOrUpd.actUsed", new Object[]{actNameList});
		}
	}
	
	@Override
	public void deleteMsPrizeOption(BigInteger priOptId) {
		validatePriOptCanDelOrUpd(priOptId);//校验
		Integer resCount =  msPrizeOptionBaseDao.deleteMsPrizeOptionLogic(priOptId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PrizeOptionService.deleteMsPrizeOption.count0");
		}
	}
	
	@Override
	public void addMsPrizeOption(BigInteger userId,String name, String valiStartTime,
			String valiEndTime, RequestFileEntity iconImage, String luckDesc,
			String useDesc, String linkUrl, String comment, Integer status,String[] cityNameList) {
		if(!MsPrizeOption_Status.isExist(status)){status = MsPrizeOption_Status.getDefault();}
		MsPrizeOption toAddPriOpt = new MsPrizeOption();
		toAddPriOpt.setComment(comment);
		BigInteger optId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_option);
		toAddPriOpt.setId(optId);
		if(iconImage!=null&&iconImage.getFileContent()!=null){//生成文件名 optId+userId
			String icon = new StringBuffer().append(optId)
					.append("_").append(userId).append(DateUtil.getCurrSysTimeStr())
					.append(".").append(iconImage.getFileType()).toString();
			String actBasePath = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.PrizeOption_Icon_BasePath);
			String serverPath = actBasePath+icon;
			try {
				ApplicationContextBothUtil.getFileServerService().uploadFile(serverPath, iconImage.getFileContent());
			} catch (IOException e) {
				logger.info("upload MsPrizeOption file cause exception:"+e.getMessage(), e);
				throw new BusinessRuntimeException("PrizeOptionService.addMsPrizeOption.uploadIcon.error",new Object[]{iconImage.getFileName()});
			}
			toAddPriOpt.setIcon(icon);
		}
		toAddPriOpt.setLastUpdTime(ApplicationContextBothUtil.getCurrentTime());
		toAddPriOpt.setLinkUrl(linkUrl);
		toAddPriOpt.setLuckDesc(luckDesc);
		toAddPriOpt.setName(name);
		toAddPriOpt.setStatus(status);
		toAddPriOpt.setUseDesc(useDesc);
		toAddPriOpt.setValiEndTime(valiEndTime);
		toAddPriOpt.setValiStartTime(valiStartTime);
		Integer resCount = msPrizeOptionBaseDao.insertMsPrizeOption(toAddPriOpt);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PrizeOptionService.addMsPrizeOption.count0");
		}
		initCityNameList(optId, cityNameList, false);
	}

	private void initCityNameList(BigInteger optId,String[] cityNameList,boolean isOptUpd){
		if(optId==null){return;}
		//设置城市
		if(cityNameList==null||cityNameList.length<=0){
			cityNameList = new String[]{PrizeActivityConstant.DEFAULT_PRIOPT_ADDRESS};
		}
		if(isOptUpd){//删除历史的
			prizeActivityDao.deletePirzeOptionCity(optId);
		}
		//批量新增
		List<MsPrizeOptionCity> optionCityList = new ArrayList<MsPrizeOptionCity>();
		List<BigInteger> addIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_option_city, cityNameList.length);
		for(int i=0;i<cityNameList.length;i++){
			String cityName = cityNameList[i].trim();
			MsPrizeOptionCity tmpAdd = new MsPrizeOptionCity();
			tmpAdd.setCityName(cityName);
			tmpAdd.setId(addIdList.get(i));
			tmpAdd.setPrizeOptionId(optId);
			optionCityList.add(tmpAdd);
		}
		int count = msPrizeOptionCityBaseDao.insertMsPrizeOptionCityBatch(optionCityList);
		if(count<=0){
			throw new BusinessRuntimeException("PrizeOptionService.initCityNameList.count0");
		}
	}
	
	@Override
	public void updMsPrizeOption(BigInteger userId, BigInteger optId,
			String name, String valiStartTime, String valiEndTime,
			RequestFileEntity iconImage, String luckDesc, String useDesc,
			String linkUrl, String comment, Integer status,String[] cityNameList) {
		if(optId==null){throw new BusinessRuntimeException("PrizeOptionService.updMsPrizeOption.optIdNull");}
		validatePriOptCanDelOrUpd(optId);//校验
		if(!MsPrizeOption_Status.isExist(status)){status = MsPrizeOption_Status.getDefault();}
		MsPrizeOption toUpdatePriOpt = new MsPrizeOption();
		toUpdatePriOpt.setComment(comment);
		if(iconImage!=null&&iconImage.getFileContent()!=null){//生成文件名 optId+userId
			String icon = new StringBuffer().append(optId)
					.append("_").append(userId).append(DateUtil.getCurrSysTimeStr())
					.append(".").append(iconImage.getFileType()).toString();
			String actBasePath = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.PrizeOption_Icon_BasePath);
			String serverPath = actBasePath+icon;
			try {
				ApplicationContextBothUtil.getFileServerService().uploadFile(serverPath, iconImage.getFileContent());
			} catch (IOException e) {
				logger.info("upload MsPrizeOption file cause exception:"+e.getMessage(), e);
				throw new BusinessRuntimeException("PrizeOptionService.addMsPrizeOption.uploadIcon.error",new Object[]{iconImage.getFileName()});
			}
			toUpdatePriOpt.setIcon(icon);
		}
		toUpdatePriOpt.setLastUpdTime(ApplicationContextBothUtil.getCurrentTime());
		toUpdatePriOpt.setLinkUrl(linkUrl);
		toUpdatePriOpt.setLuckDesc(luckDesc);
		toUpdatePriOpt.setName(name);
		toUpdatePriOpt.setStatus(status);
		toUpdatePriOpt.setUseDesc(useDesc);
		toUpdatePriOpt.setValiEndTime(valiEndTime);
		toUpdatePriOpt.setValiStartTime(valiStartTime);
		toUpdatePriOpt.setId(optId);
		Integer resCount = msPrizeOptionBaseDao.updateMsPrizeOption(toUpdatePriOpt);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PrizeOptionService.updMsPrizeOption.count0");
		}
		initCityNameList(optId, cityNameList, true);
	}
	
	@Override
	public Integer uploadPriGift(BigInteger priOptId,List<PriGiftUploadParam> dataList,GiftRepeatMap giftRepeatMap) {
		if(dataList==null||dataList.size()<=0){
			throw new BusinessRuntimeException("PrizeOptionService.uploadPriGift.dataList0");
		}
		//文件内重复数据已校验
		//跟数据库重复数据的校验
		Set<GiftUqMarkCode> dbRepeatCodeSet = prizeActivityDao.selectRepeatCodeSet(giftRepeatMap.getDataMapKey());
		if(dbRepeatCodeSet!=null&&dbRepeatCodeSet.size()>0){//当前不重复的key_code
			throw new BusinessRuntimeException("PrizeOptionService.uploadPriGift.dbRepeat",new Object[]{dbRepeatCodeSet.size(),giftRepeatMap.show(dbRepeatCodeSet)});
		}
		List<MsPrizeGift> toAddMsPrizeGiftList = new ArrayList<MsPrizeGift>();
		List<MsPrizeGiftCode> toAddCodeList = new ArrayList<MsPrizeGiftCode>();
		List<BigInteger> toAddGiftIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_gift, dataList.size());
		for(int i=0;i<dataList.size();i++){
			PriGiftUploadParam uploadGift = dataList.get(i);
			BigInteger priGiftId = toAddGiftIdList.get(i);
			MsPrizeGift toAddPrizeGift = new MsPrizeGift();
			toAddPrizeGift.setConvertRoomId(null);
			toAddPrizeGift.setConvertStatus(MsPrizeGift_ConvertStatus.NotUse);
			toAddPrizeGift.setConvertTime(null);
			toAddPrizeGift.setConvertUserId(null);
			toAddPrizeGift.setId(priGiftId);
			toAddPrizeGift.setNumber(uploadGift.getNumber());
			toAddPrizeGift.setPrizeActId(null);
			toAddPrizeGift.setPrizeOptionId(priOptId);
			toAddPrizeGift.setUnit(uploadGift.getUnit());
			toAddMsPrizeGiftList.add(toAddPrizeGift);
			
			List<UnitGiftParam> codeList = uploadGift.getUnitGiftParamList();
			for(UnitGiftParam codeUnit:codeList){
				MsPrizeGiftCode toAddCode = new MsPrizeGiftCode();
				toAddCode.setCodeName(codeUnit.getGiftName());
//				toAddCode.setId(id);
				toAddCode.setPriGiftId(priGiftId);
				toAddCode.setUqMark(codeUnit.getUqMark());
				toAddCode.setValueCode(codeUnit.getValueCode());
				toAddCodeList.add(toAddCode);
			}
		}
		List<BigInteger> toAddCodeIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_ms_prize_gift_code, toAddCodeList.size());
		for(int i=0;i<toAddCodeList.size();i++){
			toAddCodeList.get(i).setId(toAddCodeIdList.get(i));
		}
		Integer addCount = msPrizeGiftBaseDao.insertMsPrizeGiftBatch(toAddMsPrizeGiftList);
		 msPrizeGiftCodeBaseDao.insertMsPrizeGiftCodeBatch(toAddCodeList);
		return addCount;
	}
	
	
	@Override
	public List<MsPrizeGiftEntity> getMsPrizeGiftList(BigInteger priOptId,
			String codeName, String valueCode, String uqMark,
			Integer qryStatus, PageModel pageModel) {
		return prizeActivityDao.selectMsPrizeGiftList(priOptId,codeName,valueCode,uqMark,qryStatus,pageModel);
	}

	@Override
	public void deleteMsPrizeGift(BigInteger giftId) {
		Integer count = prizeActivityDao.deleteMsPrizeGiftAndCodeLogic(giftId);
		if(count==null||count<=0){
			throw new BusinessRuntimeException("PrizeOptionService.deleteMsPrizeGift.count0");
		}
	}

	@Override
	public MsPrizeGiftForPrize getMsPrizeGiftDetail(BigInteger giftId) {
		return prizeActivityDao.selectMsPrizeGiftDetail(giftId);
	}
	
}
