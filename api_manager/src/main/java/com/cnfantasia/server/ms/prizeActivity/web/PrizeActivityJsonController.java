/**   
* Filename:    PrizeActivityJsonController.java   
* @version:    1.0  
* Create at:   2015年9月15日 上午9:52:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.prizeActivity.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.commbusi.prizeActivity.constant.PrizeActivityDict;
import com.cnfantasia.server.commbusi.prizeActivity.entity.ActHasOptParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.ActOptionParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftRepeatMap;
import com.cnfantasia.server.commbusi.prizeActivity.entity.GiftUqMarkCode;
import com.cnfantasia.server.commbusi.prizeActivity.entity.PriGiftUploadParam;
import com.cnfantasia.server.commbusi.prizeActivity.entity.UnitGiftParam;
import com.cnfantasia.server.commbusi.prizeActivity.service.IPrizeActivityService;
import com.cnfantasia.server.commbusi.prizeActivity.service.IPrizeOptionService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;


/**
 * Filename:    PrizeActivityJsonController.java
 * @version:    1.0.0
 * Create at:   2015年9月15日 上午9:52:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月15日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/prizeActivityJson")
public class PrizeActivityJsonController extends BaseController{
	@Resource(type=IPrizeActivityService.class)
	private IPrizeActivityService prizeActService;
	@Resource(type=IPrizeOptionService.class)
	private IPrizeOptionService prizeOptService;
	
	private void validateActivityIcon(RequestFileEntity shareIconImage){
		if(shareIconImage!=null&&shareIconImage.getFileSize()!=null&&shareIconImage.getFileSize()>0){
			long nowK = shareIconImage.getFileSize()/1024;
			long limitK = 25;
			if(nowK>limitK){
				throw new BusinessRuntimeException("PrizeActivityController.validateActivityIcon.tooBig",new Object[]{nowK,limitK});
			}
		}
	}
	private void validateOptionIcon(RequestFileEntity shareIconImage){
		if(shareIconImage!=null&&shareIconImage.getFileSize()!=null&&shareIconImage.getFileSize()>0){
			long nowK = shareIconImage.getFileSize()/1024;
			long limitK = 20;
			if(nowK>limitK){
				throw new BusinessRuntimeException("PrizeActivityController.validateOptionIcon.tooBig",new Object[]{nowK,limitK});
			}
		}
	}
	
	/**
	 * 新增活动
	 * @param request
	 * @return
	 */
	@RequestMapping("/doActivityAdd.json")
	@ResponseBody
	public JsonResponse doActivityAdd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		String name = ParamUtils.getString(request, "name", null);
		String startTime = ParamUtils.getString(request, "startTime", null);
		String endTime = ParamUtils.getString(request, "endTime", null);
		String shareText = ParamUtils.getString(request, "shareText", null);
		Integer activityStatus = ParamUtils.getInteger(request, "activityStatus", PrizeActivityDict.MsPrizeActivity_ActivityStatus.getDefault());
		RequestFileEntity shareIconImage = CommRequestFileParser.parseRequsetFileInfo(request, "shareIcon");
		validateActivityIcon(shareIconImage);
		List<ActOptionParam> actOptionParamList = null;
		{
			String[] optionList = request.getParameterValues("optionList");
			if(optionList!=null&&optionList.length>0){
				actOptionParamList = new ArrayList<ActOptionParam>();
				for(String optIdStr:optionList){
					BigInteger prizeOptionId = new BigInteger(optIdStr);
					Long totalMaxCount = Long.valueOf(ParamUtils.getString(request, optIdStr+"_total", "0"));
					Long dayMaxCount = Long.valueOf(ParamUtils.getString(request, optIdStr+"_day", "0"));
					ActOptionParam opt = new ActOptionParam(prizeOptionId, totalMaxCount, dayMaxCount);
					actOptionParamList.add(opt);
				}
			}
		}
		if(actOptionParamList==null||actOptionParamList.size()<=0){
			throw new BusinessRuntimeException("PrizeActivityController.doActivityAdd.optionList0");
		}
		//2.交互
		prizeActService.addMsPrizeActivity(userId, name, startTime, endTime, shareText, shareIconImage, actOptionParamList, activityStatus);
		//3.结果处理
		JsonResponse res = new JsonResponse();
		return res;
	}
	
	/**
	 * 编辑活动
	 * @param request
	 * @return
	 */
	@RequestMapping("/doActivityUpd.json")
	@ResponseBody
	public JsonResponse doActivityUpd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		BigInteger prizeActId = ParamUtils.getBigInteger(request, "actId", null);
		String name = ParamUtils.getString(request, "name", null);
		String startTime = ParamUtils.getString(request, "startTime", null);
		String endTime = ParamUtils.getString(request, "endTime", null);
		String shareText = ParamUtils.getString(request, "shareText", null);
		Integer activityStatus = ParamUtils.getInteger(request, "activityStatus", PrizeActivityDict.MsPrizeActivity_ActivityStatus.getDefault());
		RequestFileEntity shareIconImage = CommRequestFileParser.parseRequsetFileInfo(request, "shareIcon");
		validateActivityIcon(shareIconImage);
		List<ActHasOptParam> actHasOptParamList = null;
		{
			String[] optionRelaList = request.getParameterValues("optionRelaList");
			if(optionRelaList!=null&&optionRelaList.length>0){
				actHasOptParamList = new ArrayList<ActHasOptParam>();
				for(String relaIdStr:optionRelaList){
					BigInteger prizeOptionId = new BigInteger(relaIdStr);
					Long totalMaxCount = Long.valueOf(ParamUtils.getString(request, relaIdStr+"_totalRela", "0"));
					Long dayMaxCount = Long.valueOf(ParamUtils.getString(request, relaIdStr+"_dayRela", "0"));
					ActHasOptParam rela = new ActHasOptParam(prizeOptionId, totalMaxCount, dayMaxCount);
					actHasOptParamList.add(rela);
				}
			}
		}
		List<ActOptionParam> actOptionParamList = null;
		{
			String[] optionList = request.getParameterValues("optionList");
			if(optionList!=null&&optionList.length>0){
				actOptionParamList = new ArrayList<ActOptionParam>();
				for(String optIdStr:optionList){
					BigInteger prizeOptionId = new BigInteger(optIdStr);
					Long totalMaxCount = Long.valueOf(ParamUtils.getString(request, optIdStr+"_total", "0"));
					Long dayMaxCount = Long.valueOf(ParamUtils.getString(request, optIdStr+"_day", "0"));
					ActOptionParam opt = new ActOptionParam(prizeOptionId, totalMaxCount, dayMaxCount);
					actOptionParamList.add(opt);
				}
			}
		}
		//2.交互
		prizeActService.updateMsPrizeActivity(prizeActId, userId, name, startTime, endTime, shareText, shareIconImage, actOptionParamList, activityStatus, actHasOptParamList);
		//3.结果处理
		JsonResponse res = new JsonResponse();
		res.putData("actId", prizeActId);
		return res;
	}
	
	/**
	 * 删除活动
	 * @param request
	 * @return
	 */
	@RequestMapping("/doActivityDel.json")
	@ResponseBody
	public JsonResponse doActivityDel(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger actId = ParamUtils.getBigInteger(request, "actId", null);
		//2.交互
		prizeActService.deleteMsPrizeActivity(actId);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return jsonResponse;
	}
	
	/**
	 * 提交奖项更新
	 * @param request
	 * @return
	 */
	@RequestMapping("/doOptionUpd.json")
	@ResponseBody
	public JsonResponse doOptionUpd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger optId = ParamUtils.getBigInteger(request, "optId", null);
		BigInteger userId = UserContext.getOperIdBigInteger();
		String name = ParamUtils.getString(request, "name",null);
		String valiStartTime = ParamUtils.getString(request, "valiStartTime",null);
		String valiEndTime = ParamUtils.getString(request, "valiEndTime",null);
		RequestFileEntity iconImage = CommRequestFileParser.parseRequsetFileInfo(request, "icon");
		validateOptionIcon(iconImage);
		String luckDesc = ParamUtils.getString(request, "luckDesc",null);
		String useDesc = request.getParameter("useDesc");
//		String useDesc = ParamUtils.getString(request, "useDesc",null);
		String linkUrl = ParamUtils.getString(request, "linkUrl",null);
		String comment = ParamUtils.getString(request, "comment",null);
		Integer status = ParamUtils.getInteger(request, "status", PrizeActivityDict.MsPrizeOption_Status.getDefault());
		Integer isGlobal = ParamUtils.getInteger(request, "isGlobal",PrizeActivityDict.MsPrizeOption_AdressType.GLOBAL);
		String[] cityNameList = null;
		if(isGlobal.compareTo(PrizeActivityDict.MsPrizeOption_AdressType.GLOBAL)!=0){//不是全国的
			cityNameList = request.getParameterValues("cityNameList");
			if(cityNameList==null||cityNameList.length<=0){
				throw new BusinessRuntimeException("PrizeActivityController.doOptionUpd.cityNameList0");
			}
		}
		//2.交互
		prizeOptService.updMsPrizeOption(userId, optId, name, valiStartTime, valiEndTime, iconImage, luckDesc, useDesc, linkUrl, comment, status,cityNameList);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("optId", optId);
		return jsonResponse;
	}
	/**
	 * 提交奖项新增
	 * @param request
	 * @return
	 */
	@RequestMapping("/doOptionAdd.json")
	@ResponseBody
	public JsonResponse doOptionAdd(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdBigInteger();
		String name = ParamUtils.getString(request, "name",null);
		String valiStartTime = ParamUtils.getString(request, "valiStartTime",null);
		String valiEndTime = ParamUtils.getString(request, "valiEndTime",null);
		RequestFileEntity iconImage = CommRequestFileParser.parseRequsetFileInfo(request, "icon");
		validateOptionIcon(iconImage);
		String luckDesc = ParamUtils.getString(request, "luckDesc",null);
//		String useDesc = ParamUtils.getString(request, "useDesc",null);
		String useDesc = request.getParameter("useDesc");
		String linkUrl = ParamUtils.getString(request, "linkUrl",null);
		String comment = ParamUtils.getString(request, "comment",null);
		Integer status = ParamUtils.getInteger(request, "status", PrizeActivityDict.MsPrizeOption_Status.getDefault());
		Integer isGlobal = ParamUtils.getInteger(request, "isGlobal",PrizeActivityDict.MsPrizeOption_AdressType.GLOBAL);
		String[] cityNameList = null;
		if(isGlobal.compareTo(PrizeActivityDict.MsPrizeOption_AdressType.GLOBAL)!=0){//不是全国的
			cityNameList = request.getParameterValues("cityNameList");
			if(cityNameList==null||cityNameList.length<=0){
				throw new BusinessRuntimeException("PrizeActivityController.doOptionAdd.cityNameList0");
			}
		}
		//2.交互
		prizeOptService.addMsPrizeOption(userId,name, valiStartTime, valiEndTime, iconImage, luckDesc, useDesc, linkUrl, comment, status,cityNameList);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return jsonResponse;
	}
	
	/**
	 * 删除奖项
	 * @param request
	 * @return
	 */
	@RequestMapping("/doOptionDel.json")
	@ResponseBody
	public JsonResponse doOptionDel(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger optId = ParamUtils.getBigInteger(request, "optId", null);
		//2.交互
		prizeOptService.deleteMsPrizeOption(optId);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return jsonResponse;
	}
	
	/**
	 * 删除奖品
	 * @param request
	 * @return
	 */
	@RequestMapping("/doGiftDel.json")
	@ResponseBody
	public JsonResponse doGiftDel(HttpServletRequest request) {
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger giftId = ParamUtils.getBigInteger(request, "giftId", null);
		//2.交互
		prizeOptService.deleteMsPrizeGift(giftId);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return jsonResponse;
	}
	
	
	/**
	 * 查询奖项剩余可以奖品数量
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryLeftPriOptCount.json")
	@ResponseBody
	public JsonResponse qryLeftPriOptCount(HttpServletRequest request){
		UserContext.getOperIdBigIntegerMustExist();
		//1.搜集参数
		BigInteger optId = ParamUtils.getBigInteger(request, "optId", null);
		//2.交互
		Integer leftCount = prizeActService.getLeftPrizeOptionCount(optId);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("leftCount", leftCount);
		return jsonResponse;
	}
	
	/**
	 * 导入奖品信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importPrizeGift.json")
	@ResponseBody
	public JsonResponse importPrizeGift(HttpServletRequest request,HttpServletResponse response){
		UserContext.getOperIdBigIntegerMustExist();
		int succAddCount = 0;
		BigInteger priOptId = ParamUtils.getBigInteger(request, "optId", null);
		if(priOptId==null){throw new BusinessRuntimeException("PrizeActivityController.importPrizeGift.priOptIdEmpty");}
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			if(uploadExcelfile==null||uploadExcelfile.isEmpty()){throw new BusinessRuntimeException("PrizeActivityController.importPrizeGift.fileEmpty");}
			HSSFWorkbook wb;
			try {
				wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			} catch (IOException e) {
				throw new BusinessRuntimeException("PrizeActivityController.importPrizeGift.parseExcelFailed");
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			int startRow =1;
			int giftStart = 2;
			int giftStep = 3;
			int lastRowNum = sheet.getLastRowNum();
			List<PriGiftUploadParam> dataList = new ArrayList<PriGiftUploadParam>();
			GiftRepeatMap giftRepeatMap = new GiftRepeatMap();
			for (int i = startRow; i <= lastRowNum; i++) {
				HSSFRow currRow = sheet.getRow(i);
				int lastCellNum = currRow.getLastCellNum();
				
				Double number = cellDouble(currRow.getCell(0));
				String unit = cellString(currRow.getCell(1));
				if(number==null||StringUtils.isEmpty(unit)){throw new BusinessRuntimeException("PrizeActivityController.importPrizeGift.numberOrunitEmpty",new Object[]{i+1});}
				List<UnitGiftParam> unitGiftList = new ArrayList<UnitGiftParam>();
				for(int c=giftStart;c+2 <= lastCellNum; c+=giftStep){
					String uqMark = cellString(currRow.getCell(c));
					String giftName = cellString(currRow.getCell(c+1));
					String valueCode = cellString(currRow.getCell(c+2)); 
					UnitGiftParam unitGiftParam = new UnitGiftParam(uqMark, giftName, valueCode);
					if(!unitGiftParam.isNull()){
						unitGiftList.add(unitGiftParam);
						giftRepeatMap.store(i, c, uqMark, valueCode);//当前文件中的重复校验
					}
				}
				if(unitGiftList==null||unitGiftList.size()<=0){throw new BusinessRuntimeException("PrizeActivityController.importPrizeGift.unitGiftList0",new Object[]{i+1});}
				PriGiftUploadParam tmpGift = new PriGiftUploadParam(number, unit, unitGiftList);
				dataList.add(tmpGift);
			}
			if(giftRepeatMap.hasRepeatData()){
				Set<GiftUqMarkCode> repeatKeys = giftRepeatMap.getRepeatKeys();
				String show = giftRepeatMap.show(repeatKeys);
				throw new BusinessRuntimeException("PrizeActivityController.importPrizeGift.currFileRepeat",new Object[]{repeatKeys.size(),show});
			}
			succAddCount = prizeOptService.uploadPriGift(priOptId, dataList,giftRepeatMap);
		}
		//解析出各行数据 空行数据
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("addCount", succAddCount);//成功新增的记录数
//		response.setContentType("text/html;charset=UTF-8"); 
		return jsonResponse;
	}
	
	public static Double cellDouble(HSSFCell cell){
		if(cell==null){return null;}
		String cellString = cell.toString();
		if(StringUtils.isEmpty(cellString)){
			return null;
		}
//		return cell.getNumericCellValue();
		return Double.valueOf(cellString);
	}
	public static String cellString(HSSFCell cell){
		if(cell==null){return null;}
		return cell.toString();
	}
}
