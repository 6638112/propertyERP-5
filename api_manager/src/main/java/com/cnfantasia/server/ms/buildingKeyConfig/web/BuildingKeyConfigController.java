package com.cnfantasia.server.ms.buildingKeyConfig.web;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;
import com.cnfantasia.server.ms.buildingKeyConfig.service.IBuildingKeyConfigService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;

/**
 * 门禁认证选项配置管理Controller
 *
 * @author Liyl
 * @version 1.0 2016年3月22日 下午5:57:52
 */
@Controller
@RequestMapping("/buildingKeyConfig")
public class BuildingKeyConfigController extends BaseController{
	
	@Resource(name="buildingKeyConfigService")
	private IBuildingKeyConfigService buildingKeyConfigService;

	/**
	 * 跳到配置页面
	 * @param groupBuildingId
	 * @return
	 */
	@RequestMapping("/keyConfigIndex.html")
	public ModelAndView keyConfigIndex(String groupBuildingId, String gbName){
		/*String gbNameString = null;
		if(null!=gbName){
			try {
				gbNameString = new String(gbName.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}*/
		// 查询小区配置信息
		List<BuildingKeyConfig> configList = buildingKeyConfigService.selectBuildingKeyConfigByCondition(groupBuildingId);
		
		ModelAndView modelAndView = new ModelAndView();		
//		modelAndView.addObject("gbName", gbNameString);
		modelAndView.addObject("gbName", gbName);
		modelAndView.addObject("groupBuildingId", groupBuildingId);
		modelAndView.addObject("configs", configList);
		modelAndView.setViewName("/buildingKeyConfig/buildingKeyConfig");		
		return modelAndView;
	}
	
	/**
	 * 新增(修改)配置信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	@RequestMapping("/updateBuildingKeyConfig.html")
	@ResponseBody
	public synchronized JsonResponse updateBuildingKeyConfig(String groupBuildingId, String configs){
		List<BuildingKeyConfig> buildingKeyConfigList = JSON.parseArray(configs,BuildingKeyConfig.class);
		
		IUuidManager uuidManager = (IUuidManager) CnfantasiaCommUtil.getBeanManager("uuidManager");
		List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_building_key_config, buildingKeyConfigList.size());
		for(int i=0; i<buildingKeyConfigList.size(); i++){
			BuildingKeyConfig buildingKeyConfig = buildingKeyConfigList.get(i);
			buildingKeyConfig.setId(ids.get(i));// 设置t_building_key_config表的id
			buildingKeyConfig.setOrder(ids.get(i));// 设置t_building_key_config表的顺序号，默认为对应的id
			buildingKeyConfig.settGroupBuildingFId(new BigInteger(groupBuildingId));// 设置小区id
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		// 影响的行数
		int count = buildingKeyConfigService.insertBuildingKeyConfigBatch(groupBuildingId, buildingKeyConfigList);
		if(count>0){
			// 查询小区配置信息
			List<BuildingKeyConfig> configList = buildingKeyConfigService.selectBuildingKeyConfigByCondition(groupBuildingId);
			
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setDataValue(configList);
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	
	/**
	 * 根据id删除配置信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteBuildingKeyConfig.html")
	@ResponseBody
	public JsonResponse deleteBuildingKeyConfig(java.math.BigInteger id){
		int count = buildingKeyConfigService.deleteBuildingKeyConfig(id);
		JsonResponse jsonResponse = new JsonResponse();
		if(count>0){
			jsonResponse.setMessage("操作成功！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	
	/**
	 * 配置项上移
	 * @param groupBuildingId
	 * @param id
	 * @param order
	 * @return
	 */
	@RequestMapping("/upOrder.html")
	@ResponseBody
	public JsonResponse upOrder(String groupBuildingId, BigInteger id, BigInteger order){
		JsonResponse jsonResponse = buildingKeyConfigService.upOrder(id, order);
		if(jsonResponse.getStatus().equals(CommConstants.ResponseStatus.SUCCESS)){
			// 查询小区配置信息
			List<BuildingKeyConfig> configList = buildingKeyConfigService.selectBuildingKeyConfigByCondition(groupBuildingId);
			jsonResponse.setDataValue(configList);
		} 
		return jsonResponse;
	}

	/**
	 * 配置项下移
	 * @param groupBuildingId
	 * @param id
	 * @param order
	 * @return
	 */
	@RequestMapping("/downOrder.html")
	@ResponseBody
	public JsonResponse downOrder(String groupBuildingId, BigInteger id, BigInteger order) {
		JsonResponse jsonResponse = buildingKeyConfigService.downOrder(id, order);
		if(jsonResponse.getStatus().equals(CommConstants.ResponseStatus.SUCCESS)){
			// 查询小区配置信息
			List<BuildingKeyConfig> configList = buildingKeyConfigService.selectBuildingKeyConfigByCondition(groupBuildingId);
			jsonResponse.setDataValue(configList);
		}
		return jsonResponse;
	}
}
