package com.cnfantasia.server.ms.groupBuilding.web;

import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.payment.dao.AliDiffPaymentPayDao;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.service.IGbSoftwareConfigBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.propertyCompany.entity.PropertyCompanyWorkbenchEntity;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;
import com.cnfantasia.server.ms.propertyManagement.service.IPropertyManagementService;
import com.cnfantasia.server.ms.provinceCityBlock.entity.ProvinceWithCityBlock;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.propertySoftwareDock.base.exception.PropertySoftwareDockException;
import com.propertySoftwareDock.base.service.IPropertySoftwareDockService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/groupBuilding")
public class GroupBuildingController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());

	@Resource
	private IGroupBuildingService groupBuildingService;

	@Resource
	IPropertyManagementService propertyManagementService;
	
	@Resource
	private IPlotpropertyCfgService plotpropertyCfgService;

	@Resource
	private IGbSoftwareConfigBaseService gbSoftwareConfigBaseService;

	@Resource
	private AliDiffPaymentPayDao aliDiffPaymentPayDao;
	/**
	 * 列表(物业公司或管理处使用)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list4PC(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/groupBuildingList");
		return modelAndView;
	}
	
	/**
	 * 列表-解放区后运营人员使用
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list4admin.html")
	public ModelAndView list4admin(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isGBConfig", 1);
		
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/gbList4Admin");
		return modelAndView;
	}
	
	/**
	 * 查找-物业公司或管理处
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search4PC(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("pcName"));//物业公司
		paramMap.put("pmName", request.getParameter("pmName"));//管理处
		paramMap.put("gbName", request.getParameter("gbName"));//小区
		paramMap.put("gbId", request.getParameter("gbId"));//小区
		if (!"-1".equals(request.getParameter("auditStatus")))
			paramMap.put("auditStatus", request.getParameter("auditStatus"));//单据状态
		handleListOrSearch(request, paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/groupBuildingList");
		return modelAndView;
	}

	/**
	 * 正式小区参数配置-查找-解放区后台运营人员
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search4Admin.html")
	public ModelAndView search4Admin(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("pcName"));//物业公司
		paramMap.put("pmName", request.getParameter("pmName"));//管理处
		paramMap.put("gbName", request.getParameter("gbName"));//小区
		paramMap.put("auditStatus", request.getParameter("auditStatus"));//单据状态
		paramMap.put("isGBConfig", 1); //小区参数配置，只查状态1和9的
		
		int isCanPay = ParamUtils.getInt(request, "isCanPay", -1);
		if (isCanPay > -1) {
			paramMap.put("isCanPay", isCanPay);
		}

		int paytimeType = ParamUtils.getInt(request, "paytimeType", -1);
		if (paytimeType > -1) {
			paramMap.put("paytimeType", paytimeType);
		}
		
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/gbList4Admin");
		return modelAndView;
	}
	
	/**
	 * 物业新增小区审核-查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchAudit.html")
	public ModelAndView searchAudit(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("pcName"));//物业公司
		paramMap.put("pmName", request.getParameter("pmName"));//管理处
		paramMap.put("gbName", request.getParameter("gbName"));//小区
		paramMap.put("auditStatus", request.getParameter("auditStatus"));//单据状态
		paramMap.put("isPCAddNew", "true");
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/groupBuildingAuditList");
		return modelAndView;
	}
	
	/**
	 * 渠道新增小区-查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchCPAudit.html")
	public ModelAndView searchCP(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("pcName"));//物业公司
		paramMap.put("gbName", request.getParameter("gbName"));//小区
		paramMap.put("auditStatus", request.getParameter("auditStatus"));//单据状态
		handleListOrSearchCP(request, paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/groupBuildingAuditList4CP");
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * @param request
	 * @param paramMap
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		if(ParamUtils.getString(request, "signStatus", "-1").equals("-1") && paramMap.size() < 2) {
			paramMap.put("signStatus", 1);
			request.setAttribute("signStatus", 1);
		} else if (!"-1".equals(request.getParameter("signStatus"))) {
			paramMap.put("signStatus", request.getParameter("signStatus"));//签约状态
		}
		
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());

		//总数量
		int resultSize = groupBuildingService.selectGroupBuildingForCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<GroupBuildingSimpleEntity> searchRestList = groupBuildingService.selectGroupBuildingForList(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	/**
	 * 邀请奖励-初始化编辑或添加配置信息
	 * @author huangzj 2015-05-12
	 * @param request
	 * @return
	 */
	@RequestMapping("/initEdit.html")
	public ModelAndView initEdit(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		//获取小区对象
		String id = request.getParameter("id");
		GroupBuildingSimpleEntity entity = new GroupBuildingSimpleEntity();
		if(!StringUtils.isEmpty(id)){//编辑使用当前单据的物业公司信息
			entity = groupBuildingService.selectGroupBuildingById(CnfantasiaCommUtil.convert2BigInteger(id));
		}
		//加载管理处
		List<PropertyManagementEntity> managements = CnfantasiaCommUtil.getPropertyManagementByCurrentUser();
		//当前角色判断，如果当前角色是管理处则取当前管理处信息
		if(UserContext.getCurrUser().getIsPmUser()==1){//当前角色是管理处
			if(null!=managements && managements.size()>0){//不是物业公司的直接去管理层的信息
				entity.settPropertyCompanyFId(managements.get(0).gettPropertyCompanyFId());
				entity.setPropertyCompanyName(managements.get(0).getCompanyName());
				entity.settPropertyManagementFId(managements.get(0).getId());
				entity.setPropertyManagementName(managements.get(0).getName());
				request.setAttribute("isMgt", 1);
			}else{
				request.setAttribute(JSPConstants.OprtResult, "跳转失败，您当前没有管理处的信息，请联系物业管理员配置您的管理处!");
				request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
				modelAndView.setViewName(JSPConstants.OprtSuccessPage);
				return modelAndView;
			}
		}else if(UserContext.getCurrUser().getIsadmin()==0){//物业公司
			request.setAttribute("isPc", 1);
			request.setAttribute("managements", managements);
			PropertyCompanyWorkbenchEntity pc = CnfantasiaCommUtil.getPropertyCompanyByUserId();
			if(pc != null) {
				entity.settPropertyCompanyFId(pc.getId());
				entity.setPropertyCompanyName(pc.getName());
			} else {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				handleListOrSearch(request, paramMap);
				request.setAttribute(JSPConstants.OprtResult, "跳转失败，您没有物业或管理处信息!");
				request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
				//modelAndView.setViewName("/groupBuilding/groupBuildingList");
				modelAndView.setViewName(JSPConstants.OprtSuccessPage);
				return modelAndView;
			}
		}else{
			request.setAttribute(JSPConstants.OprtResult, "跳转失败，您没有物业或管理处权限操作!");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;
		}
		//加载省
		if(null==pcbList){
			pcbList = CnfantasiaCommUtil.getProvinceWithCityBlockList();
		}
		request.setAttribute("pcbList", pcbList);
		request.setAttribute("entity", entity);
		modelAndView.setViewName("/groupBuilding/groupBuildingEdit");
		return modelAndView;
	}
	
	/**省*/
	private List<ProvinceWithCityBlock> pcbList;
	/**
	 * 物业管理 新增商家
	 * @author huangzj 2015-04-23
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveEdit.html")
	public ModelAndView saveEdit(HttpServletRequest request) throws IOException{
		String id = request.getParameter("gbId");//ID
		String type = request.getParameter("type");//操作类型
		
		String gbName = request.getParameter("gbName");//小区名
		String blockId = request.getParameter("blockId");//地址
		String addressDesc = request.getParameter("addressDesc");//详情地址
		String companyId = request.getParameter("companyId");//物业公司
		String managementId = request.getParameter("managementId");//管理处
		String streetName = request.getParameter("streetName");//街道
		String streetTel = request.getParameter("streetTel");//街道电话
		String neighborName = request.getParameter("neighborName");//居委会
		String neighborTel = request.getParameter("neighborTel");//居委会电话
		
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		GroupBuilding groupBuilding = new GroupBuilding();
		if(!managementId.isEmpty() && gbId!=null) {
			groupBuilding = groupBuildingService.getGroupBuildingBySeqId(gbId);
		}
		
		if(groupBuildingService.saveOrUpdateGroupBuilding
				(id, gbName, blockId, addressDesc, managementId, companyId, streetName, streetTel, neighborName, neighborTel)>0){
			//更新历史数据（小区配置管理处后，将历史未结算物业缴费订单自动归集到相应管理处下）,小区管理处更新完成后才进行数据的更新
			if(!managementId.isEmpty() && gbId!=null) {
				groupBuildingService.updateHistoryManagementRevenueData(groupBuilding, managementId);
			}
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
		}
		if("audit".equals(type)){
			request.setAttribute(JSPConstants.ToURL, "../groupBuilding/auditList.html");
		}else{
			request.setAttribute(JSPConstants.ToURL, "../groupBuilding/list.html");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查看明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/view.html")
	public ModelAndView view(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			request.setAttribute("entity", groupBuildingService.selectGroupBuildingById(CnfantasiaCommUtil.convert2BigInteger(id)));
		}
		request.setAttribute("viewType", request.getParameter("type"));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/groupBuildingView");
		return modelAndView;
	}
	
	/**
	 * 查看明细--渠道合伙人查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/view4CP.html")
	public ModelAndView view4CP(HttpServletRequest request) {
		String id = request.getParameter("id");

		GroupBuildingSimpleEntity entity = groupBuildingService.selectGroupBuildingByGbrId(CnfantasiaCommUtil.convert2BigInteger(id));
		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("entity", entity);
		modelAndView.setViewName("/groupBuilding/groupBuildingView4CP");
		return modelAndView;
	}
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteGB.html")
	@ResponseBody
	public String deleteGB(String id) {
		if(groupBuildingService.deleteGroupBuildingById(id)>0){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}
	
	/**
	 * 物业新增小区审核列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/auditList.html")
	public ModelAndView auditList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isPCAddNew", "true");

		handleListOrSearch(request, paramMap);

		return new ModelAndView("/groupBuilding/groupBuildingAuditList");
	}
	
	/**
	 * 渠道新增小区列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/cpAuditList.html")
	public ModelAndView auditList4CP(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearchCP(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/groupBuilding/groupBuildingAuditList4CP");
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * @param request
	 * @param paramMap
	 */
	private void handleListOrSearchCP(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		//总数量
		int resultSize = groupBuildingService.selectGroupBuilding4CPForCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<GroupBuildingSimpleEntity> searchRestList = groupBuildingService.selectGroupBuildingForCPList(curPageIndex, pageSize, paramMap);
		request.setAttribute("resList", searchRestList);
	}
	
	/**
	 * 小区审核-初始化审核信息
	 * @author huangzj 2015-05-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAudit.html")
	public ModelAndView initAudit(HttpServletRequest request) {
		//加载省
		if(null==pcbList){
			pcbList = CnfantasiaCommUtil.getProvinceWithCityBlockList();
		}
		request.setAttribute("pcbList", pcbList);
		String id = request.getParameter("id");
		GroupBuildingSimpleEntity entity = groupBuildingService.selectGroupBuildingById(CnfantasiaCommUtil.convert2BigInteger(id));
		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("entity", entity);
		modelAndView.setViewName("/groupBuilding/groupBuildingAudit");
		return modelAndView;
	}
	
	/**
	 * 小区审核-初始化审核信息
	 * @author wenfq 2015-06-30
	 */
	@RequestMapping("/initAudit4CP.html")
	public ModelAndView initAudit4CP(HttpServletRequest request) {
		//加载省
		if(null==pcbList){
			pcbList = CnfantasiaCommUtil.getProvinceWithCityBlockList();
		}
		request.setAttribute("pcbList", pcbList);
		String id = request.getParameter("id");
		GroupBuildingSimpleEntity entity = groupBuildingService.selectGroupBuildingByGbrId(CnfantasiaCommUtil.convert2BigInteger(id));
		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("entity", entity);
		modelAndView.setViewName("/groupBuilding/groupBuildingAudit4CP");
		return modelAndView;
	}
	
	/**
	 * 小区审核-保存化审核信息
	 * @author huangzj 2015-04-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAudit.html")
	public ModelAndView saveAudit(HttpServletRequest request) throws IOException{
		String gbId = request.getParameter("gbId");//ID
		
		String auditStatus = request.getParameter("auditStatus");//通过与否：0通过，1不通过
		String auditDesc = request.getParameter("auditDesc");//审核结果描述
		
		if (groupBuildingService.saveAuditGroupBuilding(gbId, auditStatus, auditDesc) > 0) {
			request.setAttribute(JSPConstants.OprtResult, "审核操作成功");
		} else {
			request.setAttribute(JSPConstants.OprtResult, "审核操作成功");
		}
		
		request.setAttribute(JSPConstants.ToURL, "../groupBuilding/auditList.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 渠道新增小区审核-保存审核信息
	 * @author wenfq 2015-06-29
	 */
	@RequestMapping("/saveAuditCP.html")
	public ModelAndView saveAuditCP(HttpServletRequest request) throws IOException{
		String id = request.getParameter("gbrId");//ID
		
		String auditStatus = request.getParameter("auditStatus");//审核结果
		String auditDesc = request.getParameter("auditDesc");//审核描述
		
		if(groupBuildingService.saveAuditGroupBuilding4CP(id, auditStatus, auditDesc) > 0){
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "审批结果保存成功");
		}
		
		request.setAttribute(JSPConstants.ToURL, "../groupBuilding/cpAuditList.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 小区审核-初始化编辑信息
	 * @author huangzj 2015-05-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/initAuditEdit.html")
	public ModelAndView initAuditEdit(HttpServletRequest request) {
		//加载省
		if(null==pcbList){
			pcbList = CnfantasiaCommUtil.getProvinceWithCityBlockList();
		}
		request.setAttribute("pcbList", pcbList);
		String id = request.getParameter("id");
		GroupBuildingSimpleEntity entity = groupBuildingService.selectGroupBuildingById(CnfantasiaCommUtil.convert2BigInteger(id));
		ModelAndView modelAndView = new ModelAndView();

		GbSoftwareConfig config = new GbSoftwareConfig();
		config.setGbId(CnfantasiaCommUtil.convert2BigInteger(id));
		List<GbSoftwareConfig> configList = gbSoftwareConfigBaseService.getGbSoftwareConfigByCondition(MapConverter.toMap(config));
		if (!DataUtil.isEmpty(configList)) {
			modelAndView.addObject("config", configList.get(0));
		}

		request.setAttribute("entity", entity);

		{
			PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = aliDiffPaymentPayDao.getDefaultAliPayInfo(CnfantasiaCommbusiUtil.convert2BigInteger(id));
			request.setAttribute("propertyCompanyThirdPayCfg", propertyCompanyThirdPayCfg);
		}

		modelAndView.setViewName("/groupBuilding/groupBuildingAuditEdit");
		return modelAndView;
	}

	/**
	 * 小区审核-保存化审核信息
	 * @author huangzj 2015-04-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAuditEdit.html")
	public ModelAndView saveAuditEdit(HttpServletRequest request, GbSoftwareConfig config) throws IOException{
		String id = request.getParameter("gbId");//ID
		
		String gbName = request.getParameter("gbName");//小区名
		String blockId = request.getParameter("blockId");//地址
		String addressDesc = request.getParameter("addressDesc");//详情地址
		String streetName = request.getParameter("streetName");//街道
		String streetTel = request.getParameter("streetTel");//街道电话
		String neighborName = request.getParameter("neighborName");//居委会
		String neighborTel = request.getParameter("neighborTel");//居委会电话
		
		String signStatus = request.getParameter("signStatus");//签约状态
		Integer hlbSwitch = ParamUtils.getInteger(request, "hlbSwitch", 0);//胡萝卜广告显示状态
		Integer isSendWyMsg = "on".equals(request.getParameter("isSendWyMsg")) ? 1 : 0;
//		String propfeeCanpay = request.getParameter("propfeeCanpay");//是否开启缴费
//		String payPeriodStart = request.getParameter("payPeriodStart");//开始缴费周期
//		String payPeriodEnd = request.getParameter("payPeriodEnd");//结束缴费周期
//		String propertyMonthChange = request.getParameter("propertyMonthChange");//是否跨月
		GroupBuilding groupBuilding = new GroupBuilding();
		groupBuilding.setId(CnfantasiaCommUtil.convert2BigInteger(id));
		groupBuilding.setName(gbName);
		groupBuilding.settBlockFId(CnfantasiaCommUtil.convert2BigInteger(blockId));
		groupBuilding.setAddressDesc(addressDesc);
		groupBuilding.setStreetName(streetName);
		groupBuilding.setStreetTel(streetTel);
		groupBuilding.setNeighborName(neighborName);
		groupBuilding.setNeighborTel(neighborTel);
		groupBuilding.setSignStatus(Integer.parseInt(signStatus));
		groupBuilding.setHlbSwitch(hlbSwitch);
		groupBuilding.setIsSendWyMsg(isSendWyMsg);
		if(groupBuildingService.saveAuditEditGroupBuilding(groupBuilding, config) > 0) {
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
		}
		
		request.setAttribute(JSPConstants.ToURL, "../groupBuilding/list4admin.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 验证当前保存的小区是否已经在当前的小区存在
	 * */
	@RequestMapping("/validateGBIsExists.html")
	@ResponseBody
	public String validateGBIsExists(String gbName, String blockId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", gbName);//小区名
		paramMap.put("blockId", blockId);//所属地区ID
		Long i = this.groupBuildingService.queryGroupbuildingIsExists(paramMap);
		String retStatus = "";
		if(null==i){//没有存在的重复小区，可以添加
			retStatus = "OK";
		}else if(2L==i){//有重复存在并且被物业公司管理的小区不能添加
			retStatus = "NO";
		}else{//有存在没有物业公司管理的小区，可以关联到当前物业公司
			retStatus = i+"";
		}
		return retStatus;
	}
	
	/**
	 * 列表(门禁认证选项配置管理)
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/keyConfigIndex.html")
	public ModelAndView listKeyConfig(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/buildingKeyConfig/buildingList");
		return modelAndView;
	}
	
	/**
	 * 查找-门禁认证选项配置管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/searchKeyConfig.html")
	public ModelAndView searchKeyConfig(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcName", request.getParameter("pcName"));//物业公司
		paramMap.put("pmName", request.getParameter("pmName"));//管理处
		paramMap.put("gbName", request.getParameter("gbName"));//小区
		if (!"-1".equals(request.getParameter("auditStatus")))
			paramMap.put("auditStatus", request.getParameter("auditStatus"));//单据状态
		handleListOrSearch(request, paramMap);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/buildingKeyConfig/buildingList");
		return modelAndView;
	}
	
	/**
	 * 发送短信
	 * 
	 * @param gbId 小区id
	 * @param msgType 短信类型
	 * @param msgContent 短信内容
	 * @param request
	 * @return
	 */
	@RequestMapping("/sendMsg.html")
	@ResponseBody
	public JsonResponse sendMsg(String gbId, String msgType, String msgContent, HttpServletRequest request){
		List<String> mobileList = null;
		// 从上传文件中获取手机号码
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile mobileFile = multipartRequest.getFile("mobileFile");
			if(mobileFile != null && !StringUtils.isEmpty(mobileFile.getOriginalFilename())){
				String ext = mobileFile.getOriginalFilename().substring(mobileFile.getOriginalFilename().lastIndexOf(".")+1);// 文件后缀
				if("txt".equals(ext)){
					mobileList = getMobileFromText(mobileFile);
				} else if("xls".equals(ext) || "xlsx".equals(ext)){
					mobileList = getMobileFromExcel(mobileFile);
				}
			}
		}
		
		List<String> allMobiles = groupBuildingService.queryMobiles(msgType, gbId);
		// 获取需要发送短信的手机号码
		List<String> mobiles = getMobileforMsg(allMobiles, mobileList);
		logger.info("The mobiles of sendMsg are " + mobiles.toString());
		
		JsonResponse jsonResponse = new JsonResponse();
		if(mobiles!=null && mobiles.size()>0){
//			boolean isSuccessed = commMobileService.sendMsg(mobiles, msgContent);
			ShortMsgUtil.sendMessages(mobiles, msgContent);
//			if(isSuccessed){
				jsonResponse.setMessage("发送成功！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
//			} else {
//				jsonResponse.setMessage("发送失败！");
//				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
//			}
		} else {
			jsonResponse.setMessage("没有需要发送的手机号码，发送短信失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		}
		
		return jsonResponse;
	}

	@RequestMapping(value = "/synSoftwareData.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse synSoftwareData(BigInteger gbId) {
		JsonResponse json = new JsonResponse();
		GbSoftwareConfig config = new GbSoftwareConfig();
		config.setGbId(gbId);
		config.setIsValid(1);
		List<GbSoftwareConfig> configList = gbSoftwareConfigBaseService
				.getGbSoftwareConfigByCondition(MapConverter.toMap(config));
		if (!DataUtil.isEmpty(configList)) {
			config = configList.get(0);
			IPropertySoftwareDockService propertySoftwareDockService =
					(IPropertySoftwareDockService) CnfantasiaCommbusiUtil.getBeanManager(config.getServiceClassName());
			try {
				String result  = propertySoftwareDockService.initData(gbId);
				if (result != null) {
					json.setMessage(result);
					json.setStatus("0001");
				}
			} catch (Exception e) {
				json.setStatus("0001");
				if (PropertySoftwareDockException.Empty_Gb_House.equals(e.getMessage())) {
					json.setMessage("当前小区未获取到房间信息，请联系物业确认！");
				} else if (PropertySoftwareDockException.Get_Data_Error.equals(e.getMessage())) {
					json.setMessage("连接物业软件服务器有误！");
				} else {
					json.setMessage("同步数据过程中出现未知异常！");
					e.printStackTrace();
					logger.info("同步数据过程中出现未知异常:" + e.getMessage());
				}
			}
		}
		return json;
	}

	/**
	 * 查询小区列表的下拉菜单
	 * @param request
	 * @return
     */
	@RequestMapping("/getGroupBuildings.json")
	@ResponseBody
	public String getGroupBuildings(HttpServletRequest request) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("gbIdList", UserContext.getGbIdList());
        String groupBuildings = (String) request.getSession().getAttribute("jfq_my_groupbuilding_select");
        if(DataUtil.isEmpty(groupBuildings)) {
            List<Map<BigInteger, String>> list = groupBuildingService.getGroupBuildingList(paraMap);
            request.getSession().setAttribute("gbListForReport", list);
            JSONObject jsonObj = new JSONObject();
            for (int i = 0; i < list.size(); i++) {
                jsonObj.put(list.get(i).toString(), list.get(i));
            }
            groupBuildings = jsonObj.toJSONString();
            request.getSession().setAttribute("jfq_my_groupbuilding_select", groupBuildings);
        }
        return  groupBuildings;
	}
	
	/**
	 * 获取需要发送短信的手机号码
	 * 
	 * @param allMobiles 所有的手机号码
	 * @param removeMobiles 需要排除掉的手机号码
	 * @return
	 */
	private List<String> getMobileforMsg(List<String> allMobiles, List<String> removeMobiles){
		if(allMobiles==null){
			return null;
		} else if(removeMobiles==null){
			return allMobiles;
		} else {
			List<String> mobileList = new ArrayList<String>();
			for (String mobile : allMobiles) {
				boolean isContained = false;// 包含在排除的手机号中
				for (String removeMobile : removeMobiles) {
					if(mobile.equals(removeMobile)){
						isContained = true;
						break;
					}
				}
				if(!isContained){
					mobileList.add(mobile);
				}
			}
			return mobileList;
		}
	}
	
	/**
	 * 从上传的txt文件中获取手机号
	 * 
	 * @param request
	 * @return
	 */
	private List<String> getMobileFromText(MultipartFile file){
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		List<String> mobileList = new ArrayList<String>();
		String lineStr = "";
		try {
			while((lineStr=reader.readLine())!=null){
				lineStr = lineStr.trim();
				if(!"".equals(lineStr)){
					mobileList.add(lineStr);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mobileList;
	}
	
	/**
	 * 从上传的excel文件中获取手机号
	 * 
	 * @param request
	 * @return
	 */
	private List<String> getMobileFromExcel(MultipartFile file){
		Workbook wb = null;
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		if(ext.equals("xlsx")){
			// 2007及以上版本
			try {
				wb = new XSSFWorkbook(file.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 低版本
			try {
				wb = new HSSFWorkbook(file.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<String> mobileList = new ArrayList<String>();
		if(wb!=null){
			Sheet sheet = wb.getSheetAt(0);
			Row row = null;
			for (int i = 0, size = sheet.getLastRowNum(); i <= size; i++) {
				row = sheet.getRow(i);
				String mobile = getCellValue(row.getCell(0));
				if(null!=mobile && !"".equals(mobile.trim())){
					mobileList.add(mobile.trim());
				}
			}
		}
		
		return mobileList;
	}
	
	/**
	 * 获取excel表格单元的值
	 * 
	 * @param cell
	 * @return String
	 */
	private static String getCellValue(Cell cell) {
		String object = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			object = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			object = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
			object = String.valueOf(cell.getErrorCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			object = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			Double x = cell.getNumericCellValue();
			object = String.valueOf(x.longValue());
			break;
		case Cell.CELL_TYPE_STRING:
			object = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return object;
	}
}