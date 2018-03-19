package com.cnfantasia.server.ms.groupBuilding.web;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.commbusi.plotproperty.entity.GroupBuildingCfgEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.ebuyorder.entity.GroupBuilding;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.commbusi.plotproperty.entity.EditPropPayBillTypeEntity;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.EbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;

/**
 * 小区信息维护Controller
* Filename:    GroupBuildingJsonController.java
* @version:    1.0.0
* Create at:   2015年12月17日 下午1:32:38
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月17日       shiyl             1.0             1.0 Version
 */
@Controller
@RequestMapping("/groupBuildingJson")
public class GroupBuildingJsonController extends BaseController{
	@Resource
	private IPlotpropertyCfgService plotpropertyCfgService;

	@Resource
	private IGroupBuildingBaseService groupBuildingBaseService;

	@Resource
	private IGroupBuildingService msGroupBuildingService;
	
	@Resource
	private EbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
	
	private void assertNotNull(Object target,String desc){
		if(target==null){
			throw new BusinessRuntimeException("GroupBuildingJsonController.assertNotNull.isnull",new Object[]{desc});
		}
	}
	
	private void validateIcon(RequestFileEntity iconImage){
		if(iconImage!=null&&iconImage.getFileSize()!=null&&iconImage.getFileSize()>0){
			long nowK = iconImage.getFileSize()/1024;
			long limitK = 25;
			if(nowK>limitK){
				throw new BusinessRuntimeException("GroupBuildingJsonController.validateIcon.tooBig",new Object[]{nowK,limitK});
			}
		}
	}
	
	@RequestMapping("/operPayBillType.json")
	@ResponseBody
	public String operPayBillType(HttpServletRequest request){
		//1.搜集参数
		BigInteger billTypeId = ParamUtils.getBigInteger(request, "billTypeId", null);
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);assertNotNull(gbId, "小区Id");
		String typeName = ParamUtils.getStringTrim(request, "typeName");assertNotNull(typeName, "类别名称");
		RequestFileEntity iconImage = CommRequestFileParser.parseRequsetFileInfo(request, "icon");
		validateIcon(iconImage);
//		Boolean isProp = ParamUtils.getBoolean(request, "isProp",null);assertNotNull(isProp, "是否为物业费");
		Integer activeStatus = ParamUtils.getInteger(request, "activeStatus", null);assertNotNull(activeStatus, "开启状态");
		Integer preferStatus = ParamUtils.getInteger(request, "isPrefer", null);assertNotNull(preferStatus, "开启状态");

		//小区开启随机立减后交费类型才能开启缴费类型
		if (preferStatus != null && preferStatus == 1) {//需要进行开启的时候才需要进行判断
			GroupBuildingSimpleEntity groupBuilding =  msGroupBuildingService.selectGroupBuildingById(gbId);
			Boolean isCanOpenPreferToBillType = groupBuilding.getIsPrefer() != null && (groupBuilding.getIsPrefer() == 1);
			if(!isCanOpenPreferToBillType) {
				return "{'message':'保存失败！请开启小区随机立减'}";
			}
		}

		//2.交互
		if(billTypeId==null){//为空则新增
			billTypeId = plotpropertyCfgService.addPayBillType(gbId, typeName, activeStatus,iconImage,preferStatus);
		}else{//不为空则更新
			boolean isProp = false;
			plotpropertyCfgService.updPayBillType(gbId, billTypeId, typeName, activeStatus,isProp,iconImage,preferStatus);
		}
		//3.结果处
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("billTypeId", billTypeId);//返回新增的Id
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 * 增加抄表缴费配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMRPayBillType.json")
	@ResponseBody
	public String addMRPayBillType(HttpServletRequest request){
		//1.搜集参数
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);assertNotNull(gbId, "小区Id");
		String typeName = ParamUtils.getStringTrim(request, "typeName");assertNotNull(typeName, "类别名称");
		Integer activeStatus = ParamUtils.getInteger(request, "activeStatus", null);assertNotNull(activeStatus, "开启状态");
		Integer preferStatus = ParamUtils.getInteger(request, "isPrefer", null);assertNotNull(preferStatus, "开启状态");
		//2.交互
		BigInteger billTypeId = plotpropertyCfgService.addMRPayBillType(gbId, typeName, activeStatus, preferStatus);
		//3.结果处
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("billTypeId", billTypeId);//返回新增的Id
		return JSON.toJSONString(jsonResponse);
	}
	
	
	@RequestMapping("/delPayBillType.json")
	@ResponseBody
	public String delPayBillType(HttpServletRequest request){
		//1.搜集参数
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);assertNotNull(gbId, "小区Id");
		BigInteger billTypeId = ParamUtils.getBigInteger(request, "billTypeId", null);assertNotNull(billTypeId, "类别Id");
		//2.交互
		plotpropertyCfgService.delPayBillType(gbId, billTypeId);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(jsonResponse);
	}
	
	@RequestMapping("/editPropPayBillType.json")
	@ResponseBody
	public String editPropPayBillType(HttpServletRequest request){
		//1.搜集参数
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);assertNotNull(gbId, "小区Id");
		Integer paytimeType = ParamUtils.getInteger(request, "paytimeType", null);assertNotNull(paytimeType, "缴费方式");
		Integer propertyPeriodType = ParamUtils.getInteger(request, "propertyPeriodType", null);
		String[] periodMonths = ParamUtils.getStrings(request, "periodMonths");
		Integer activeStatus = ParamUtils.getInteger(request, "activeStatus", null);
		assertNotNull(activeStatus, "是否开启缴费");
		Integer propertyMonthChange = ParamUtils.getInteger(request, "propertyMonthChange", null);
		Integer payPeriodStart = ParamUtils.getInteger(request, "payPeriodStart", null);
		Integer payPeriodEnd = ParamUtils.getInteger(request, "payPeriodEnd", null);
		Integer verificationStatus = ParamUtils.getInteger(request, "verificationStatus", null);
		Integer isPrefer = ParamUtils.getInteger(request, "isPrefer", null);

		EditPropPayBillTypeEntity editPropPayBillTypeEntity = new EditPropPayBillTypeEntity();
		editPropPayBillTypeEntity.setGbId(gbId);
		editPropPayBillTypeEntity.setPaytimeType(paytimeType);
		editPropPayBillTypeEntity.setPropertyPeriodType(propertyPeriodType);
		editPropPayBillTypeEntity.setPeriodMonthsArr(periodMonths);
		editPropPayBillTypeEntity.setActiveStatus(activeStatus);
		editPropPayBillTypeEntity.setPropertyMonthChange(propertyMonthChange);
		editPropPayBillTypeEntity.setPayPeriodStart(payPeriodStart);
		editPropPayBillTypeEntity.setPayPeriodEnd(payPeriodEnd);
		editPropPayBillTypeEntity.setVerificationStatus(verificationStatus);
		editPropPayBillTypeEntity.setIsPrefer(isPrefer);
		//2.交互
		plotpropertyCfgService.editPropPayBillType(editPropPayBillTypeEntity);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		//前端判断是否需要关闭所有的随机立减开关
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("preferStatus", isPrefer);
		jsonResponse.setDataValue(resMap);

		return JSON.toJSONString(jsonResponse);
	}

	@RequestMapping("/saveGbConfig.json")
	@ResponseBody
	public String saveGbConfig(GroupBuildingCfgEntity groupBuildingCfgEntity){
		//交互
		plotpropertyCfgService.saveGbConfig(groupBuildingCfgEntity);
		//结果处理
		JsonResponse jsonResponse = new JsonResponse();

		return JSON.toJSONString(jsonResponse);
	}
	
	@RequestMapping("/editPropPayBillOpenAttribute.json")
	@ResponseBody
	public String editPropPayBillOpenAttribute(HttpServletRequest request){
		//1.搜集参数
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);assertNotNull(gbId, "小区Id");
		Integer activeStatus = ParamUtils.getInteger(request, "activeStatus", null);assertNotNull(activeStatus, "是否开启缴费");
		Integer verificationStatus = ParamUtils.getInteger(request, "verificationStatus", null);
		Integer isPrefer = ParamUtils.getInteger(request, "isPrefer", null);
		//2.交互
		plotpropertyCfgService.editPropPayBillOpenAttribute(gbId, activeStatus, verificationStatus, isPrefer);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(jsonResponse);
	}

	@RequestMapping(value = "/getBuildingListByName.json", method = RequestMethod.GET)
	@ResponseBody
	public List getBuildingListByName(String name){
		//根据gbName查询
		GroupBuilding groupBuilding = new GroupBuilding();
		PageModel pageModel = new PageModel(0, 20);
		Map param = new HashMap();
		param.put("name", name);
		return groupBuildingBaseService.getGroupBuildingByConditionDim(param, pageModel);
	}

	@RequestMapping(value = "/getBuildingListByNameAndCityId.json",method = RequestMethod.GET)
	@ResponseBody
	public List getBuildingListByNameAndCityId(String name, BigInteger cityId, BigInteger merchantId) {
		if(merchantId!=null){
			EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(merchantId);
			if(ebuySupplyMerchant!=null && ebuySupplyMerchant.getType()!=null && ebuySupplyMerchant.getType()==2){
				// 楼下店，则按供应商服务范围查询小区
				return msGroupBuildingService.searchByNameAndMerchantServiceArea(name, merchantId);
			}
		}
		
		PageModel pageModel = new PageModel(0, 20);
		return msGroupBuildingService.getBuildingListByNameAndCityId(name, cityId, pageModel);
	}

	@RequestMapping(value = "/getBuildingListByNameAndBlockId.json",method = RequestMethod.GET)
	@ResponseBody
	public List getBuildingListByNameAndBlockId(String name, String blockId) {
		BigInteger blockIdIn = new BigInteger(blockId);
		List<BigInteger> gbIdList = UserContext.getGbIdList();
		return msGroupBuildingService.getBuildingListByNameAndBlockId(name, blockIdIn, gbIdList);
	}
	
	@RequestMapping("/isHasEmptyProprietor.json")
	@ResponseBody
	public JsonResponse isHasEmptyProprietor(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		int emptyType = msGroupBuildingService.isHasEmptyProprietorByGbId(gbId);//为空状态：0全部不为空，1部分为空，2全部为空，3没有楼栋信息
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("emptyType", emptyType);
		jsonResponse.setDataValue(resMap);
		
		return jsonResponse;
	}
	
}
