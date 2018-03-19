package com.cnfantasia.server.ms.omsPermiRole.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.omsUserHasPropertyDistrict.entity.OmsUserHasPropertyDistrict;
import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;
import com.cnfantasia.server.domainbase.propertyDistrict.service.IPropertyDistrictBaseService;
import com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.entity.PropertyDistrictHasGroupBuilding;
import com.cnfantasia.server.ms.omsPermiRole.entity.DistrictManager;
import com.cnfantasia.server.ms.omsPermiRole.entity.DistrictManagerDo;
import com.cnfantasia.server.ms.omsPermiRole.service.IOmsPermiRoleService;
import com.cnfantasia.server.ms.pub.BaseController;

/**
 * 
 * 类说明：权限角色
 * 
 * @author wen_fuqiang
 */
@Controller
@RequestMapping("/district")
public class OmsDistrictController extends BaseController {
	
	@Resource
	private IOmsPermiRoleService omsPermiRoleService;
	
	@Resource
	private IPropertyDistrictBaseService propertyDistrictBaseService;
	
//	@Resource
//	private IOmsUserHasPropertyDistrictBaseService omsUserHasPropertyDistrictBaseService;

	@RequestMapping("/districtList.html")
	public ModelAndView list(HttpServletRequest requset) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<DistrictManager> districtManagerList = omsPermiRoleService.getDistrictManagerList(paramMap);
		requset.setAttribute("districtManagerList", districtManagerList);
		return new ModelAndView("/omsPermiRole/districtList");
	}
	
	/**
	 * 片区编辑
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/districtEdit.html")
	public ModelAndView edit(HttpServletRequest requset, BigInteger id) {
		ModelAndView modelAndView = new ModelAndView();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("districtId", id);
		List<DistrictManager> districtManagerList = omsPermiRoleService.getDistrictManagerList(paramMap);
		
		requset.setAttribute("districtManager", districtManagerList.get(0));
		
		modelAndView.setViewName("/omsPermiRole/districtEdit");
		return modelAndView;
	}
	
	@RequestMapping(value = "getOmsUserList.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getOmsUserList(String qryStr, Integer appType) {
        PageModel pageModel = new PageModel(0, 20);
        return omsPermiRoleService.getOmsUserList(qryStr, pageModel);
    }
	
	/**
	 * 新增片区
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/addDistrictPage.html")
	public ModelAndView addDistrictPage(HttpServletRequest requset, BigInteger id) {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("/omsPermiRole/addDistrictPage");
		return modelAndView;
	}
	
	/**
	 * 角色保存
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/addDistrict.json")
	@ResponseBody
	public JsonResponse addDistrict(HttpServletRequest request, PropertyDistrict propertyDistrict) {
		JsonResponse json = new JsonResponse();

		propertyDistrict.setId(ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_property_district));
		propertyDistrictBaseService.insertPropertyDistrict(propertyDistrict);
		return json;
	}

	/**
	 * 保存
	 * 
	 * @param requset
	 * @return
	 */
	@RequestMapping("/districtSave.json")
	@ResponseBody
	public JsonResponse save(HttpServletRequest request, DistrictManagerDo districtManagerDo) {
		JsonResponse json = new JsonResponse();
		List<OmsUserHasPropertyDistrict> omsUserHasPropertyDistrictList = new ArrayList<OmsUserHasPropertyDistrict>();
		List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList = new ArrayList<PropertyDistrictHasGroupBuilding>();
		
		
		if(districtManagerDo.getUserIds() != null && districtManagerDo.getUserIds().size() > 0) {
			Set<BigInteger> userIdSet = districtManagerDo.getUserIds();
			
			List<BigInteger> uuidList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_oms_user_has_property_district, userIdSet.size());
			int i = 0;
			for(BigInteger userId : userIdSet) {
				OmsUserHasPropertyDistrict omsUserHasPropertyDistrict = new OmsUserHasPropertyDistrict();
				omsUserHasPropertyDistrict.setId(uuidList.get(i++));
				omsUserHasPropertyDistrict.settOmsUserFId(userId);
				omsUserHasPropertyDistrict.settPropertyDistrictFId(districtManagerDo.getId());
//				omsUserHasPropertyDistrict.setSys0AddUser(UserContext.getOperIdBigInteger());
				omsUserHasPropertyDistrictList.add(omsUserHasPropertyDistrict);
			}
		}
		
		if(districtManagerDo.getGbIds() != null && districtManagerDo.getGbIds().size() > 0) {
			Set<BigInteger> gbIdSet = districtManagerDo.getGbIds();
			
			List<BigInteger> uuidList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_property_district_has_group_building, gbIdSet.size());
			int i = 0;
			for(BigInteger gbId : gbIdSet) {
				if(gbId != null) {
					PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding = new PropertyDistrictHasGroupBuilding();
					propertyDistrictHasGroupBuilding.setId(uuidList.get(i++));
					propertyDistrictHasGroupBuilding.settGroupBuildingFId(gbId);
					propertyDistrictHasGroupBuilding.settPropertyDistrictFId(districtManagerDo.getId());
					propertyDistrictHasGroupBuildingList.add(propertyDistrictHasGroupBuilding);
				}
			}
		}
		try {
			omsPermiRoleService.saveDistrictManager(omsUserHasPropertyDistrictList, propertyDistrictHasGroupBuildingList);
		} catch (Exception e) {
			json.setMessage("所选小区已经被其它片区所有！保存失败！");
			json.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
		}
		
		
		return json;
	}

}
