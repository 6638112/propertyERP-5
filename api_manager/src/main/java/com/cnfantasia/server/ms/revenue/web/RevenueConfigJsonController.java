package com.cnfantasia.server.ms.revenue.web;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.entity.LeftRightTime;
import com.cnfantasia.server.ms.revenue.entity.MinMaxDate;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigDetail;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

/**
 * 收益配置Json
 * @author shiyl
 *
 */
@Controller
@RequestMapping("/revenueConfigJson")
public class RevenueConfigJsonController extends BaseController{
	@Resource
	private IRevenueService revenueService;
	@Resource
	private RevenueConfigCommonController revenueConfigCommonController;
	
	public void assertNotNull(Object projectType,String desc){
		if(projectType==null){
			throw new BusinessRuntimeException("revenueConfigJson.doConfigAdd.valueNull",new Object[]{desc});
		}
	}
	
	private Double initTotalValue(Integer ruleType,Double companyValue,Double agentValue,Double recommenderValue,Double platformValue){
		Double totalValueRes = companyValue+agentValue+recommenderValue+platformValue;
		if(ruleType.compareTo(RevenueDict.RuleType.ByPercent)==0){
			if(totalValueRes>100){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigOper.moreThan100");
			}else{
				totalValueRes = 100.0;
			}
		}
		/*{
			totalValueRes = totalValue;
		}*/
		/*if(totalValueRes!=(companyValue+agentValue+platformValue+repairValue)){
			if(ruleType.compareTo(RevenueDict.RuleType.ByPercent)==0){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigOper.valueNot100");
			}else{
				throw new BusinessRuntimeException("revenueConfigJson.doConfigOper.valueNotEqual");
			}
		}*/
		return totalValueRes;
	}
	
	/**
	 * 新增配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/doConfigAdd.json")
	@ResponseBody
	public String doConfigAdd(HttpServletRequest request,HttpServletResponse response) {
//		response.setContentType("text/html");
//		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		//1.搜集参数
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, "companyId");
		if(companyId==null){
			throw new BusinessRuntimeException("revenueConfigJson.doConfigAdd.companyIdNull");
		}
		Integer activeStatus = ParamUtils.getInteger(request, "activeStatus", null);assertNotNull(activeStatus, "有效状态");
		
		Integer projectType = ParamUtils.getInteger(request, "projectType", null);assertNotNull(projectType, "收益类别");
		Integer ruleType = ParamUtils.getInteger(request, "ruleType", null);assertNotNull(ruleType, "计算方式");
		Double  totalValue = null;
		Double  companyValue = ParamUtils.getDouble(request, "companyValue", null);assertNotNull(companyValue, "物业收益");
		Double  agentValue = ParamUtils.getDouble(request, "agentValue", null);assertNotNull(agentValue, "代理收益");
		Double  recommenderValue = 0.0;
		if(projectType == 2 || projectType ==3) {
			recommenderValue = ParamUtils.getDouble(request, "recommenderValue", null);assertNotNull(recommenderValue, "推荐人收益");
		}
		
		Double  platformValue = 0.0;
		if(projectType == 4) {
//			platformValue = ParamUtils.getDouble(request, "platformValue", null);assertNotNull(platformValue, "平台收益");
			platformValue = 100 - companyValue - agentValue;
		} else {
			platformValue = ParamUtils.getDouble(request, "platformValue", null);assertNotNull(platformValue, "平台收益");
		}
		
		
		totalValue = initTotalValue(ruleType, companyValue, agentValue, recommenderValue, platformValue);//totalValue改为系统自动计算
		Double  repairValue = totalValue-companyValue-agentValue-recommenderValue-platformValue;
		
		String startDate = ParamUtils.getStringTrim(request, "startDate");
		String endDate = ParamUtils.getStringTrim(request, "endDate");
		//2.交互
		revenueService.addRevenueConfig(companyId,projectType,ruleType,totalValue,companyValue,agentValue,recommenderValue,platformValue,repairValue,startDate,endDate,activeStatus);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 * 修改配置
	 * @param request
	 * @return
	 */
	@RequestMapping("/doConfigUpd.json")
	@ResponseBody
	public String doConfigUpd(HttpServletRequest request) {
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, null);
		//1.搜集参数
		BigInteger dataId = ParamUtils.getBigInteger(request, "dataId", null);
		if(dataId==null){
			throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.dataIdNull");
		}
		Integer activeStatus = ParamUtils.getInteger(request, "activeStatus", null);assertNotNull(activeStatus, "有效状态");
		Integer projectType = ParamUtils.getInteger(request, "projectType", null);assertNotNull(projectType, "收益类别");
		Integer ruleType = ParamUtils.getInteger(request, "ruleType", null);assertNotNull(ruleType, "计算方式");
		Double  totalValue = null;
		Double  companyValue = ParamUtils.getDouble(request, "companyValue", null);assertNotNull(companyValue, "物业收益");
		Double  agentValue = ParamUtils.getDouble(request, "agentValue", null);assertNotNull(agentValue, "代理收益");
//		Double  recommenderValue = ParamUtils.getDouble(request, "recommenderValue", null);assertNotNull(recommenderValue, "推荐人收益");
//		Double  platformValue = ParamUtils.getDouble(request, "platformValue", null);assertNotNull(platformValue, "平台收益");
//		Double  repairValue = ParamUtils.getDouble(request, "repairValue", null);assertNotNull(repairValue, "师傅收益");
//		totalValue = initTotalValue(ruleType, totalValue, companyValue, agentValue, platformValue, repairValue);//totalValue改为系统自动计算
		
		Double  recommenderValue = 0.0;
		if(projectType == 2 || projectType ==3) {
			recommenderValue = ParamUtils.getDouble(request, "recommenderValue", null);assertNotNull(recommenderValue, "推荐人收益");
		}
		
		Double  platformValue = 0.0;
		if(projectType == 4) {
//			platformValue = ParamUtils.getDouble(request, "platformValue", null);assertNotNull(platformValue, "平台收益");
			platformValue = 100 - companyValue - agentValue;
		} else {
			platformValue = ParamUtils.getDouble(request, "platformValue", null);assertNotNull(platformValue, "平台收益");
		}
		
		totalValue = initTotalValue(ruleType, companyValue, agentValue, recommenderValue, platformValue);//totalValue改为系统自动计算
		Double  repairValue = totalValue-companyValue-agentValue-recommenderValue-platformValue;
		
		String startDate = ParamUtils.getStringTrim(request, "startDate");
		String endDate = ParamUtils.getStringTrim(request, "endDate");
		{//校验
			//1.开始截止时间不能为空
			if(StringUtils.isEmpty(startDate)){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.startDate.empty");
			}
			if(StringUtils.isEmpty(endDate)){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.endDate.empty");
			}
			//2.开始应小于等于截止时间
			if(DateUtil.distance(startDate, DateUtil.formatDay.get(), endDate, DateUtil.formatDay.get())>0){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.datecompare.error");
			}
		}
		//2.交互
		RevenueConfigDetail detail = revenueService.getRevenueConfigDetail(dataId);//根据Id查询详情
		BigInteger detailCompanyId = detail==null?null:detail.getCompanyId();
		Integer detailProjectType = detail==null?null:detail.getProjectType();
		if(companyId!=null&&companyId.compareTo(detailCompanyId)!=0){
			throw new BusinessRuntimeException("revenueConfig.configUpd.detailNEqual");
		}
		LeftRightTime areaInfo = revenueService.getLeftRightTime(detailCompanyId, detailProjectType, detail);
		{//校验
			//3.开始时间大于等于leftTime,截止时间小于等于rightTime
			if(!areaInfo.getLeftTime().isInArea(startDate, DateUtil.formatDay.get())){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.startDate.valiFailed");
			}
			if(!areaInfo.getRightTime().isInArea(endDate, DateUtil.formatDay.get())){
				throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.endDate.valiFailed");
			}
		}
		if(RevenueDict.ActiveStatus.OPEN.compareTo(detail.getActiveStatus())==0){
			throw new BusinessRuntimeException("revenueConfigJson.doConfigUpd.isOpen");
		}
		revenueService.updRevenueConfig(dataId,ruleType,totalValue,companyValue,agentValue,recommenderValue,platformValue,repairValue,startDate,endDate,activeStatus,areaInfo,detail);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(jsonResponse);
	}
	
	
	/**
	 * 删除配置项
	 * @param request
	 * @return
	 */
	@RequestMapping("/doRevenueConfigDel.json")
	@ResponseBody
	public String doRevenueConfigDel(HttpServletRequest request) {
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, null);
		//1.搜集参数
		BigInteger dataId = ParamUtils.getBigInteger(request, "dataId", null);
		//2.交互
		RevenueConfigDetail detail = revenueService.getRevenueConfigDetail(dataId);//根据Id查询详情
		BigInteger detailCompanyId = detail==null?null:detail.getCompanyId();
		Integer detailProjectType = detail==null?null:detail.getProjectType();
		if(companyId!=null&&companyId.compareTo(detailCompanyId)!=0){
			throw new BusinessRuntimeException("revenueConfig.configUpd.detailNEqual");
		}
		LeftRightTime areaInfo = revenueService.getLeftRightTime(detailCompanyId, detailProjectType, detail);
		if(RevenueDict.ActiveStatus.OPEN.compareTo(detail.getActiveStatus())==0){
			throw new BusinessRuntimeException("revenueConfigJson.doConfigDel.isOpen");
		}
		revenueService.delRevenueConfig(dataId,areaInfo);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(jsonResponse);
	}
	
	/**
	 * 查询物业公司列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryPropCompanyList.json")
	@ResponseBody
	public String qryPropCompanyList(HttpServletRequest request) {
		//1.搜集参数
		String companyName = ParamUtils.getStringTrim(request, "companyName");
		//2.交互
		List<PropertyCompany> propertyCompanyList = revenueService.getPropCompanyList(companyName);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(ControllerUtils.addPageInfo(jsonResponse, propertyCompanyList));
	}
	
	/**
	 * 查询最早最晚时间
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryMinMaxDate.json")
	@ResponseBody
	public String qryMinMaxDate(HttpServletRequest request) {
		//1.搜集参数
		BigInteger companyId = revenueConfigCommonController.getParamCompanyId(request, "companyId");assertNotNull(companyId, "物业公司");
		Integer projectType = ParamUtils.getInteger(request, "projectType", null);assertNotNull(projectType, "收益类别");
		//2.交互
		MinMaxDate minMaxDate = revenueService.getMinMaxDate(companyId,projectType);
		//3.结果处理
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(minMaxDate);
		return JSON.toJSONString(jsonResponse);
	}
	
	
}
