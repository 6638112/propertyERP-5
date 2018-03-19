package com.cnfantasia.server.ms.propertyRepair.web;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommMobileService;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForDetail;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.httpcllient.IHttpClient;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.comments.entity.Comments;
import com.cnfantasia.server.domainbase.comments.service.CommentsBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.service.IDredgeBillHasProcessRecordingBaseService;
import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;
import com.cnfantasia.server.domainbase.dredgeType.service.IDredgeTypeBaseService;
import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.entity.DredgeTypePriceConfig;
import com.cnfantasia.server.domainbase.dredgeTypePriceConfig.service.IDredgeTypePriceConfigBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyPayRecord.entity.EbuyPayRecord;
import com.cnfantasia.server.domainbase.ebuyPayRecord.service.IEbuyPayRecordBaseService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;
import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.service.IGroupBuildingHasTPropertyServiceBaseService;
import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;
import com.cnfantasia.server.domainbase.propertyRepairType.service.IPropertyRepairTypeBaseService;
import com.cnfantasia.server.domainbase.propertyRepairTypeBase.service.PropertyRepairTypeBaseBaseService;
import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;
import com.cnfantasia.server.domainbase.propertyRepairer.service.PropertyRepairerBaseService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.propertyRepair.constant.PropertyRepairConstant;
import com.cnfantasia.server.ms.propertyRepair.entity.DredgeTypeEntity;
import com.cnfantasia.server.ms.propertyRepair.entity.PropertyRepairConfig;
import com.cnfantasia.server.ms.propertyRepair.entity.PropertyRepairEntity;
import com.cnfantasia.server.ms.propertyRepair.entity.PropertyRepairTypeEntity;
import com.cnfantasia.server.ms.propertyRepair.entity.PropertyRepairerEntity;
import com.cnfantasia.server.ms.propertyRepair.service.IPropertyRepairService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.DateUtil;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.service.IRevenueService;

@RequestMapping("/propertyRepair")
@Controller
public class PropertyRepairController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	@Resource
	IPropertyRepairService propertyRepairService;
	
	@Resource
	IGroupBuildingHasTPropertyServiceBaseService groupBuildingHasTPropertyServiceBaseService;

	@Resource
	IPropertyRepairTypeBaseService propertyRepairTypeBaseService;

	@Resource
	ICommMobileService commMobileService;
	
	@Resource
	PropertyRepairerBaseService propertyRepairerBaseService;
	
	@Resource
	PropertyRepairTypeBaseBaseService propertyRepairTypeBasebaseService;
	
	@Resource
	private IHttpClient simpleHttpClient;
	
	@Resource
	private IRevenueService revenueService;
	
	@Resource
	private IDredgeBillHasProcessRecordingBaseService dredgeBillHasProcessRecordingBaseService;
	
	@Resource
	private IFileServerService fileServerService;

	@Resource
	private IDredgeBillBaseService dredgeBillBaseService;

	@Resource
	private IDredgeTypeBaseService dredgeTypeBaseService;

	@Resource
	private DredgeService dredgeService;

	@Resource
	private IEbuyOrderBaseService ebuyOrderBaseService;

	@Resource
	private IEbuyPayRecordBaseService ebuyPayRecordBaseService;

	@Resource
	private IDredgeTypePriceConfigBaseService dredgeTypePriceConfigBaseService;

	@RequestMapping("/listRepairConfig.html")
	public ModelAndView qryRepairDetail(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("pmName", ParamUtils.getString(request, "pmName"));
		paramMap.put("gbName", ParamUtils.getString(request, "gbName"));
		paramMap.put("openStatus", ParamUtils.getInteger(request, "openStatus", null));

		int resultSize = propertyRepairService.listRepairConfig_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		PageUtils.addPageInfoToParam(request, paramMap);
		List<PropertyRepairConfig> propertyRepairConfigList = propertyRepairService.listRepairConfig(paramMap);
		request.setAttribute("resList", propertyRepairConfigList);
		request.setAttribute("param", paramMap);

		return new ModelAndView("/propertyRepair/prConfigList");
	}

	@RequestMapping("/closeRepairService.html")
	public ModelAndView closeRepairService(HttpServletRequest request) {
		String gbId = request.getParameter("gbId");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGroupBuildingFId", gbId);
		paramMap.put("tPropertyServiceFId", PropertyRepairConstant.PROPERTY_SERVICE_REPAIR);
		paramMap.put("sys0DelState", 0);
		paramMap.put("status", 1);
		List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList = groupBuildingHasTPropertyServiceBaseService
				.getGroupBuildingHasTPropertyServiceByCondition(paramMap);
		if(groupBuildingHasTPropertyServiceList.size()!=1){
			logger.info("more than one property repair service records.");
			throw new BusinessRuntimeException("PropertyRepairController.closeRepairService.error--more than one property repair service records.");
		}

		groupBuildingHasTPropertyServiceList.get(0).setStatus(2);//关闭服务
		int updCount = groupBuildingHasTPropertyServiceBaseService.updateGroupBuildingHasTPropertyService(groupBuildingHasTPropertyServiceList.get(0));
		
		return new ModelAndView("redirect:/propertyRepair/listRepairConfig.html");
	}

	@RequestMapping("/openRepairService.html")
	public ModelAndView openRepairService(HttpServletRequest request) {
		String gbId = request.getParameter("gbId");
		request.setAttribute("gbId", gbId);
		return new ModelAndView("/propertyRepair/prOpenService");
	}

	@RequestMapping("/saveOpenRepairService.html")
	public ModelAndView saveOpenRepairService(HttpServletRequest request) {
		String gbId = request.getParameter("gbId");
		String serviceMobile = request.getParameter("serviceMobile");
		int udpCount = propertyRepairService.openRepairService(gbId, serviceMobile);
		return new ModelAndView("redirect:/propertyRepair/addNewRepairer.html");
	}

	/**
	 * 维修工-查看
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewRepairer.html")
	public ModelAndView viewRepairer(HttpServletRequest request) {
		String repairerId = request.getParameter("repairerId");
		PropertyRepairer propertyRepairer = propertyRepairerBaseService.getPropertyRepairerBySeqId(new BigInteger(repairerId));
		request.setAttribute("propertyRepairer", propertyRepairer);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<PropertyRepairTypeEntity> prTypeList = propertyRepairService.listRepairerTypeByOmsUser(paramMap);
		request.setAttribute("prTypeList", prTypeList);

		return new ModelAndView("/propertyRepair/prRepairerView");
	}

	/**
	 * 维修工-编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/editRepairer.html")
	public ModelAndView editRepairer(HttpServletRequest request) {
		String repairerId = request.getParameter("repairerId");
		PropertyRepairer propertyRepairer = propertyRepairerBaseService.getPropertyRepairerBySeqId(new BigInteger(repairerId));
		request.setAttribute("propertyRepairer", propertyRepairer);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<PropertyRepairTypeEntity> prTypeList = propertyRepairService.listRepairerTypeByOmsUser(paramMap);
		request.setAttribute("prTypeList", prTypeList);

		return new ModelAndView("/propertyRepair/prRepairerEdit");
	}

	/**
	 * 维修工-新增（列表界面新增）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNewRepairer.html")
	public ModelAndView addNewRepairer(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<PropertyRepairTypeEntity> prTypeList = propertyRepairService.listRepairerTypeByOmsUser(paramMap);
		request.setAttribute("prTypeList", prTypeList);
		return new ModelAndView("/propertyRepair/prRepairerAddNew");
	}

	/**
	 * 保存维修工
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveRepairer.html")
	public ModelAndView saveRepairer(HttpServletRequest request) {
		String gbId = request.getParameter("gbId");
		String repairerId = request.getParameter("propertyRepairerId");
		String repairerName = request.getParameter("repairerName");
		String repairerTel = request.getParameter("repairerTel");
		BigInteger repairTypeId = new BigInteger(request.getParameter("repairType"));
		String propertyManagementFId = request.getParameter("pmId");
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile headimgurl = multipartRequest.getFile("headimgurl");
		
		String idNumber = ParamUtils.getString(request, "idNumber");
		MultipartFile idimgurl1 = multipartRequest.getFile("idimgurl1");
		MultipartFile idimgurl2 = multipartRequest.getFile("idimgurl2");

		propertyRepairService.saveRepairer(gbId, propertyManagementFId, repairTypeId, repairerId, repairerName, repairerTel, headimgurl, idNumber, idimgurl1, idimgurl2);

		return new ModelAndView("redirect:/propertyRepair/listRepairer.html");
	}

	/**
	 * 报修单-列表
	 */
	@RequestMapping("/listRepair.html")
	public ModelAndView listRepair(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		handleRepairListOrSearch(request, paramMap);
		
		return new ModelAndView("/propertyRepair/propertyRepairList");
	}

	/**
	 * 报修单-查找
	 */
	@RequestMapping("/searchRepair.html")
	public ModelAndView searchRepair(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("repairNumber", request.getParameter("repairNumber"));//维修工单编码
		paramMap.put("huaId", request.getParameter("huaId"));//解放号
		paramMap.put("gbName", request.getParameter("gbName"));//小区
		paramMap.put("createTimeBegin", request.getParameter("createTimeBegin"));//创建时间-开始
		paramMap.put("createTimeEnd", request.getParameter("createTimeEnd"));//创建时间-结束
		paramMap.put("assignTimeBegin", request.getParameter("assignTimeBegin"));//创建时间-开始
		paramMap.put("assignTimeEnd", request.getParameter("assignTimeEnd"));//创建时间-结束
		paramMap.put("finishTimeBegin", request.getParameter("finishTimeBegin"));//创建时间-开始
		paramMap.put("finishTimeEnd", request.getParameter("finishTimeEnd"));//创建时间-结束
		paramMap.put("repairType", request.getParameter("repairType"));//维修类型
		if(StringUtils.isNotEmpty(request.getParameter("repairState")))
			paramMap.put("repairState", request.getParameter("repairState"));//维修状态
		paramMap.put("repairer", request.getParameter("repairer"));//维修人
		paramMap.put("repairId", request.getParameter("repairId"));//维修ID

		handleRepairListOrSearch(request, paramMap);

		return new ModelAndView("/propertyRepair/propertyRepairList");
	}

	/**
	 * 报修单-列表或查找
	 */
	private void handleRepairListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("gbIdList", UserContext.getGbIdList());

		int resultSize = propertyRepairService.listRepair_forCount(paramMap);

		request.setAttribute("resultSize", resultSize);

		PageUtils.addPageInfoToParam(request, paramMap);

		List<PropertyRepairEntity> propertyRepairList = propertyRepairService.listRepair(paramMap);
		request.setAttribute("resList", propertyRepairList);

		//列出所有不重复的维修类型
		paramMap.put("_begin", null);
		paramMap.put("_length", null);
		List<PropertyRepairTypeEntity> prTypeList = propertyRepairService.listRepairerTypeByOmsUser(paramMap);
		Set<String> prTypeSet = new HashSet<String>();
		for (int i = 0; i < prTypeList.size(); i++) {
			prTypeSet.add(prTypeList.get(i).getName());
		}
		request.setAttribute("prTypeSet", prTypeSet);

		//列出的维修工人
		List<PropertyRepairerEntity> propertyRepairerList = propertyRepairService.listRepairer(paramMap);
		request.setAttribute("propertyRepairerList", propertyRepairerList);
		
		// 内转外权限：客服、管理员
		Set<UserRole> userRoleSet = revenueService.getOmsUserRoleListByUserId(UserContext.getOperIdBigIntegerMustExist());
		if(userRoleSet.contains(UserRole.CustomerService) || userRoleSet.contains(UserRole.SysAdmin)){
			request.setAttribute("isKefu", true);
		} else {
			request.setAttribute("isKefu", false);
		}
	}

	/**
	 * 报修单-分配处理人
	 */
	@RequestMapping("/assignRepair.html")
	public ModelAndView assignRepair(HttpServletRequest request) {
		if (request.getParameter("repairId") == null && request.getParameter("dredgeId") == null)
			throw new IllegalArgumentException("repairId can not be null");

		PropertyRepairEntity propertyRepairEntity = propertyRepairService.select_PropertyRepair_byId(request.getParameter("repairId"));
		request.setAttribute("pr", propertyRepairEntity);

		BigInteger dredgeId = ParamUtils.getBigInteger(request, "dredgeId", null);
		DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeId);
		request.setAttribute("db", db);
		if (db != null) {
			request.setAttribute("dredgeTypeName", dredgeTypeBaseService.getDredgeTypeBySeqId(db.gettDredgeTypeFId()).getName());
		}
		//列出的维修工人
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<PropertyRepairerEntity> propertyRepairerList = propertyRepairService.listRepairer(paramMap);
		request.setAttribute("propertyRepairerList", propertyRepairerList);

		return new ModelAndView("/propertyRepair/propertyRepairAssign");
	}

	/**
	 * 报修单-分配处理人
	 */
	@RequestMapping("/assignRepairSave.html")
	public ModelAndView assignRepairSave(HttpServletRequest request) {
		String repairId = request.getParameter("repairId");
		String propertyRepairerId = request.getParameter("propertyRepairerId");
		String estimateDoorTimeBegin = request.getParameter("estimateDoorTimeBegin");
		String estimateDoorTimeEnd = request.getParameter("estimateDoorTimeEnd");
		String asignDesc = request.getParameter("asignDesc");
		if (repairId == null)
			throw new IllegalArgumentException("repairId can not be null");
		if (propertyRepairerId == null) {
			throw new IllegalArgumentException("propertyRepairerId can not be null");
		}

		PropertyRepair propertyRepair = new PropertyRepair();
		propertyRepair.setId(new BigInteger(repairId));
		propertyRepair.settPropertyRepairerFId(new BigInteger(propertyRepairerId));
		propertyRepair.setEstimateDoorTimeBegin(estimateDoorTimeBegin);
		propertyRepair.setEstimateDoorTimeEnd(estimateDoorTimeEnd);
		propertyRepair.setRepaireState(2);//已分配
		propertyRepair.setAsignDesc(asignDesc);
		propertyRepair.setAsignTime((DateUtil.formatSecond.format(new Date())));
		propertyRepairService.assignPropertyRepair(propertyRepair);


		return new ModelAndView("redirect:/propertyRepair/listRepair.html");
	}

	/**
	 * 报修单-关闭提单
	 */
	@RequestMapping("/closeRepair.html")
	public ModelAndView closeRepair(HttpServletRequest request) {
		if (request.getParameter("repairId") == null)
			throw new IllegalArgumentException("repairId can not be null");

		PropertyRepairEntity propertyRepairEntity = propertyRepairService.select_PropertyRepair_byId(request.getParameter("repairId"));
		request.setAttribute("pr", propertyRepairEntity);

		return new ModelAndView("/propertyRepair/propertyRepairClose");
	}

	/**
	 * 报修单-关闭提单保存
	 */
	@RequestMapping("/closeRepairSave.html")
	public ModelAndView closeRepairSave(HttpServletRequest request) {
		String repairId = request.getParameter("repairId");
		String finishDesc = request.getParameter("finishDesc");
		if (repairId == null)
			throw new IllegalArgumentException("repairId can not be null");

		PropertyRepair propertyRepair = new PropertyRepair();
		propertyRepair.setId(new BigInteger(repairId));
		propertyRepair.setRepaireState(3);//已结束
		propertyRepair.setFinishDesc(finishDesc);
		propertyRepair.setFinishedTime(DateUtil.formatSecond.format(new Date()));
		propertyRepairService.closePropertyRepair(propertyRepair);

		return new ModelAndView("redirect:/propertyRepair/listRepair.html");
	}

	@Resource
	private CommentsBaseService commentsBaseService;

	/**
	 * 报修单-详情查看
	 */
	@RequestMapping("/viewRepairDetail.html")
	public ModelAndView viewRepairDetail(HttpServletRequest request) {
		BigInteger repairId = ParamUtils.getBigInteger(request, "repairId", null);
		BigInteger dredgeId = ParamUtils.getBigInteger(request, "dredgeId", null);
		if (repairId == null && dredgeId == null)
			throw new IllegalArgumentException("repairId can not be null");

		ModelAndView mav = new ModelAndView("/propertyRepair/propertyRepairView");
		if (repairId != null) {
			PropertyRepairEntity propertyRepairEntity = propertyRepairService.select_PropertyRepair_byId(repairId.toString());
			mav.addObject("pr", propertyRepairEntity);
			if (!StringUtils.isEmpty(propertyRepairEntity.getPicUrl())) {//报修的图片
				String[] pics = propertyRepairEntity.getPicUrl().split(";");
				List<String> picList = new ArrayList<String>();
				String relativePath = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.Repair_Pic_BasePath);
				for (int j = 0; j < pics.length; j++) {
					picList.add(fileServerService.getAccessUrl(relativePath + pics[j], propertyRepairEntity));
				}
				if (!picList.isEmpty())
					mav.addObject("prPicList", picList);
			}

			if (propertyRepairEntity.getPropertyRepairer() != null) {//评论相关
				//取得用户的评论
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("targetType", 121);//物业报修评论
				paramMap.put("targetId", propertyRepairEntity.getId());
				paramMap.put("userId", propertyRepairEntity.gettUserFId());//评论的是报修单
				List<Comments> commentList = commentsBaseService.getCommentsByCondition(paramMap);
				if (!commentList.isEmpty()) {
					mav.addObject("commentContent", commentList.get(0).getContent());
					mav.addObject("starLevel", commentList.get(0).getLevel());
				}
			}
		} else {
			DredgeBillForDetail db = dredgeService.qryDredgeBillDetail(dredgeId.toString());
			dredgeService.appendMaterialFee(db);
			mav.addObject("dredgeBill", db);
			if (!DataUtil.isEmpty(db.getPicInfo())) {//图片
				mav.addObject("prPicList", db.getPicInfo());
			}
			if (db.getMasterId() != null) {//评论相关
				//取得用户的评论
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("targetType", 122);//维修评论
				paramMap.put("targetId", db.getId());
				paramMap.put("userId", db.getUserId());//评论的是报修单
				List<Comments> commentList = commentsBaseService.getCommentsByCondition(paramMap);
				if (!commentList.isEmpty()) {
					mav.addObject("commentContent", commentList.get(0).getContent());
					mav.addObject("starLevel", commentList.get(0).getLevel());
				}
			}
			if ((db.getStatus() == 3 || db.getStatus() == 5) && db.getOrderId() != null) {
				EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(BigInteger.valueOf(db.getOrderId()));
				mav.addObject("order", order);
				EbuyPayRecord payRecord = new EbuyPayRecord();
				payRecord.settEbuyOrderFId(order.getId());
				payRecord.setPayStatus(2);
				List<EbuyPayRecord> payRecordList = ebuyPayRecordBaseService.getEbuyPayRecordByCondition(MapConverter.toMap(payRecord), new PageModel(0, 1));
				if (!DataUtil.isEmpty(payRecordList)) {
					mav.addObject("payRecord", payRecordList.get(0));
				}

			}
			
		}

		{// 取出流程记录
			DredgeBillHasProcessRecording dredgeBillHasProcessRecordingQry = new DredgeBillHasProcessRecording();
			dredgeBillHasProcessRecordingQry.setBillType(repairId == null ?
					DredgeConstant.DredgeBillType.Dredge_Bill_Common : DredgeConstant.DredgeBillType.Property_Repair);
			if (repairId != null) {
				dredgeBillHasProcessRecordingQry.settPropertyRepairFId(repairId);
			} else {
				dredgeBillHasProcessRecordingQry.settDredgeBillFId(dredgeId);
			}
			List<DredgeBillHasProcessRecording> prList = dredgeBillHasProcessRecordingBaseService.getDredgeBillHasProcessRecordingByCondition(MapConverter.toMap(dredgeBillHasProcessRecordingQry));
			if (!prList.isEmpty())
				mav.addObject("processRecordList", propertyRepairService.getProcessRecordList(prList));
		}

		return mav;
	}

	/**
	 * 维修工-列表
	 */
	@RequestMapping("/listRepairer.html")
	public ModelAndView listRepairer(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		handleRepairerListOrSearch(request, paramMap);

		return new ModelAndView("/propertyRepair/prRepairerList");
	}

	/**
	 * 维修工-删除
	 * 
	 * @return true成功删除，false删除失败
	 */
	@RequestMapping("/deleteRepairer.html")
	@ResponseBody
	public String deleteRepairer(HttpServletRequest request) {
		String repairerId = request.getParameter("repairerId");
		if (repairerId == null)
			throw new IllegalArgumentException("repairerId is null");
		
		int updCount = propertyRepairerBaseService.deletePropertyRepairerLogic(new BigInteger(repairerId));
		return updCount == 1 ? "true" : "false";
	}

	/**
	 * 维修工-查询
	 */
	@RequestMapping("/searchRepairer.html")
	public ModelAndView searchRepairer(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("prName", request.getParameter("prName"));
		paramMap.put("prPhone", request.getParameter("prPhone"));
		handleRepairerListOrSearch(request, paramMap);

		return new ModelAndView("/propertyRepair/prRepairerList");
	}

	private void handleRepairerListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		//获取小区信息
		paramMap.put("gbIdList", UserContext.getGbIdList());

		int resultSize = propertyRepairService.listRepairer_forCount(paramMap);

		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);

		List<PropertyRepairerEntity> propertyRepairerList = propertyRepairService.listRepairer(paramMap);
		request.setAttribute("resList", propertyRepairerList);
	}

	/**
	 * 维修类型列表
	 */
	@RequestMapping("/listRepairType.html")
	public ModelAndView listRepairType(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleRepairTypeListOrSearch(request, paramMap);

		return new ModelAndView("/propertyRepair/prTypeList");
	}

	/**
	 * 维修类型查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/prtSearch.html")
	public ModelAndView prtSearch(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("prtName", request.getParameter("prtName"));
		handleRepairTypeListOrSearch(request, paramMap);

		return new ModelAndView("/propertyRepair/prTypeList");
	}

	private void handleRepairTypeListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());

		int resultSize = propertyRepairService.listRepairerTypeByOmsUser_forCount(paramMap);

		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);

		List<PropertyRepairTypeEntity> propertyRepairTypeList = propertyRepairService.listRepairerTypeByOmsUser(paramMap);
		request.setAttribute("resList", propertyRepairTypeList);
	}

	/**
	 * 新增维修类型
	 */
	@RequestMapping("/addNewRepairerType.html")
	public ModelAndView addNewRepairerType(HttpServletRequest request) {

		List<PropertyManagement> propertyManagementList = propertyRepairService.listPropertyManagementByOmsUser(UserContext.getCurrUser());
		request.setAttribute("pmList", propertyManagementList);

		return new ModelAndView("/propertyRepair/prTypeMaintain");
	}

	/**
	 * 新增维修类型-保存
	 */
	@RequestMapping("/addNewRepairerTypeSave.html")
	public ModelAndView addNewRepairerTypeSave(HttpServletRequest request) {
		String pmId = request.getParameter("pmId");
		String prtName = request.getParameter("prtName");
		PropertyRepairType propertyRepairType = new PropertyRepairType();
		CnfantasiaCommUtil.newStandard(propertyRepairType, SEQConstants.t_property_repair_type);
		propertyRepairType.settPropertyManagementFId(new BigInteger(pmId));
		propertyRepairType.setName(prtName);

		propertyRepairTypeBaseService.insertPropertyRepairType(propertyRepairType);

		return new ModelAndView("redirect:/propertyRepair/listRepairType.html");
	}

	/**
	 * 编辑维修类型
	 */
	@RequestMapping("/editRepairerType.html")
	public ModelAndView editRepairerType(HttpServletRequest request) {
		String repairerTypeId = request.getParameter("repairerTypeId");
		PropertyRepairType propertyRepairType = propertyRepairTypeBaseService.getPropertyRepairTypeBySeqId(new BigInteger(repairerTypeId));
		request.setAttribute("prt", propertyRepairType);

		List<PropertyManagement> propertyManagementList = propertyRepairService.listPropertyManagementByOmsUser(UserContext.getCurrUser());
		request.setAttribute("pmList", propertyManagementList);

		return new ModelAndView("/propertyRepair/prTypeMaintain");
	}

	/**
	 * 更新维修类型
	 */
	@RequestMapping("/updateRepairType.html")
	public ModelAndView updateRepairType(HttpServletRequest request) {
		String repairerTypeId = request.getParameter("repairerTypeId");
		String pmId = request.getParameter("pmId");
		String prtName = request.getParameter("prtName");
		PropertyRepairType propertyRepairType = new PropertyRepairType();
		propertyRepairType.setId(new BigInteger(repairerTypeId));
		propertyRepairType.settPropertyManagementFId(new BigInteger(pmId));
		propertyRepairType.setName(prtName);

		propertyRepairTypeBaseService.updatePropertyRepairType(propertyRepairType);

		return new ModelAndView("redirect:/propertyRepair/listRepairType.html");
	}
	
	/**
	 * 批量保存维修类型
	 */
	@RequestMapping("/updateRepairTypeBatch.html")
	public ModelAndView updateRepairTypeBatch(HttpServletRequest request) {
		String deletePRTids = request.getParameter("delStateId");
		String addNewIds = request.getParameter("newStateId");
		String addNewNames = request.getParameter("newStateName");
		String priceDescList = request.getParameter("priceDescList");
		String priceCfgIdList = request.getParameter("priceCfgIdList");//需要删除的价格配置id
		String pmId = request.getParameter("pmId");
		
		if (StringUtils.isNotEmpty(deletePRTids)) { // 删除类型
			List<java.math.BigInteger> idList = new ArrayList<BigInteger>();
			String[] prtIds = deletePRTids.split(",");
			for (int i = 0; i < prtIds.length; i++) {
				idList.add(new BigInteger( prtIds[i]));
			}

			if (idList.size() > 0) {
				propertyRepairTypeBaseService.deletePropertyRepairTypeLogicBatch(idList);
			}
		}
		
		if (StringUtils.isNotEmpty(addNewIds) && StringUtils.isNotEmpty(addNewNames)) { // 增加类型
			String[] baseTypeIds = addNewIds.split(",");
			String[] baseTypeNames = addNewNames.split(",");

			List<PropertyRepairType> propertyRepairTypeList = new ArrayList<PropertyRepairType>(baseTypeIds.length);

			if (baseTypeIds.length > 0) {
				for (int i = 0; i < baseTypeIds.length; i++) {
					PropertyRepairType propertyRepairType = new PropertyRepairType();
					propertyRepairType.settPropertyManagementFId(new BigInteger(pmId));
					propertyRepairType.setName(baseTypeNames[i]);
					propertyRepairType.settPropertyRepairTypeBaseFId(BigInteger.ZERO);
					propertyRepairType.settDredgeTypeFId(new BigInteger(baseTypeIds[i]));
					propertyRepairTypeList.add(propertyRepairType);
				}
				CnfantasiaCommUtil.newStandardList(propertyRepairTypeList, SEQConstants.t_property_repair_type);
				propertyRepairTypeBaseService.insertPropertyRepairTypeBatch(propertyRepairTypeList);
			}
		}

		if (StringUtils.isNotEmpty(priceCfgIdList)) { // 删除维护价格描述
			List<java.math.BigInteger> idList = new ArrayList<BigInteger>();
			String[] priceCfgIds = priceCfgIdList.split(",");
			for (int i = 0; i < priceCfgIds.length; i++) {
				idList.add(new BigInteger( priceCfgIds[i]));
			}

			if (idList.size() > 0) {
				dredgeTypePriceConfigBaseService.deleteDredgeTypePriceConfigLogicBatch(idList);
			}
		}

		//维护价格描述
		if(!DataUtil.isEmpty(priceDescList)) {
			List<DredgeTypePriceConfig> dredgeTypePriceConfigsForUpdate = new ArrayList<DredgeTypePriceConfig>();
			List<DredgeTypePriceConfig> dredgeTypePriceConfigsForAdd = new ArrayList<DredgeTypePriceConfig>();
			String[] descList = priceDescList.split(",");
			for (String s : descList) {
				//管理处ID + 维修类型id + 描述 + 参考价格
				String[] descs = s.split("@#@");
				DredgeTypePriceConfig dredgeTypePriceConfig = new DredgeTypePriceConfig();
				dredgeTypePriceConfig.settPropertyManagementFId(BigInteger.valueOf(Long.parseLong(descs[0])));
				dredgeTypePriceConfig.settDredgeTypeFId(BigInteger.valueOf(Long.parseLong(descs[1])));
				List<DredgeTypePriceConfig> dredgeTypePriceConfigByCondition = dredgeTypePriceConfigBaseService.getDredgeTypePriceConfigByCondition(MapConverter.toMap(dredgeTypePriceConfig));
				if(!DataUtil.isEmpty(dredgeTypePriceConfigByCondition)) {
					dredgeTypePriceConfig = dredgeTypePriceConfigByCondition.get(0);
				}
				//spit方法如果最后一个分隔符后的为空则不加入到数组中，中间的为空可以加入数组
				if(descs.length > 2) {
					dredgeTypePriceConfig.setPriceDesc(descs[2]);
				}
				if(descs.length == 4) {
					dredgeTypePriceConfig.setDesc(descs[3]);
				}
				if(!DataUtil.isEmpty(dredgeTypePriceConfig.getId())) {
					dredgeTypePriceConfigsForUpdate.add(dredgeTypePriceConfig);
				} else {
					dredgeTypePriceConfig.settPropertyManagementFId(new BigInteger(pmId));
					dredgeTypePriceConfigsForAdd.add(dredgeTypePriceConfig);
				}
			}
			if(!DataUtil.isEmpty(dredgeTypePriceConfigsForAdd)) {
				CnfantasiaCommUtil.newStandardList(dredgeTypePriceConfigsForAdd, SEQConstants.t_dredge_type_price_config);
				dredgeTypePriceConfigBaseService.insertDredgeTypePriceConfigBatch(dredgeTypePriceConfigsForAdd);
			}
			if(!DataUtil.isEmpty(dredgeTypePriceConfigsForUpdate)) {
				dredgeTypePriceConfigBaseService.updateDredgeTypePriceConfigBatch(dredgeTypePriceConfigsForUpdate);
			}
		}

		return new ModelAndView("redirect:/propertyRepair/listRepairType.html");
	}

	/**
	 * 删除维修类型
	 */
	@RequestMapping("/deleteRepairerType.html")
	public ModelAndView deleteRepairerType(HttpServletRequest request) {
		String repairerTypeId = request.getParameter("repairerTypeId");
		propertyRepairTypeBaseService.deletePropertyRepairTypeLogic(new BigInteger(repairerTypeId));
		return new ModelAndView("redirect:/propertyRepair/listRepairType.html");
	}
	
	/**
	 * 校验手机号唯一性
	 */
	@RequestMapping("/verifyMobile.html")
	@ResponseBody
	public JsonResponse verifyMobile(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		String mobile = request.getParameter("mobile");
		String dwId = request.getParameter("dwId");//师傅id
		
		PropertyRepairer propertyRepairer = new PropertyRepairer();
		propertyRepairer.setTel(mobile);
		propertyRepairer.setSys0DelState(0);
		
		jsonResponse.putData("isMobileRepeated", 0);
		List<PropertyRepairer> propertyRepairerList = propertyRepairerBaseService.getPropertyRepairerByCondition(MapConverter.toMap(propertyRepairer));
		for(int i = 0; i < propertyRepairerList.size(); i++){
			if(!propertyRepairerList.get(i).getId().toString().equals(dwId) && propertyRepairerList.get(i).getTel().equals(mobile)){
				jsonResponse.putData("isMobileRepeated", 1);
				break;
			}
		}
		
		return jsonResponse;
	}
	
	/**
	 * 列出管理处下所有维修类型
	 */
	@RequestMapping("/qryPRTypeListByPMId.html")
	@ResponseBody
	public JsonResponse qryPRTypeList_by_pmId(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger pmId = ParamUtils.getBigInteger(request, "pmId", null);
		List<PropertyRepairTypeEntity> propertyRepairTypeEntityList = propertyRepairService.getPropertyRepairTypeEntityListByPMId(pmId);
		jsonResponse.putData("prtList", propertyRepairTypeEntityList);

		DredgeType dredgeType = new DredgeType();
		dredgeType.settCommunitySupplyTypeFId(DredgeConstant.ParentCommunitySupplyType.WYBX);
		List<DredgeTypeEntity> dredgeTypeList = propertyRepairService.getDredgeTypeByCondition(MapConverter.toMap(dredgeType));
		for (DredgeTypeEntity type : dredgeTypeList) {
			for (PropertyRepairTypeEntity propertyRepairTypeEntity : propertyRepairTypeEntityList) {
				if (type.getId().compareTo(propertyRepairTypeEntity.getBaseTypeId()) == 0) {
					type.setSys0DelState(propertyRepairTypeEntity.getId().intValue());//借用该字段来标识物业公司已经引用该字段
					//填充描述、参考价格信息
					type.setDesc(propertyRepairTypeEntity.getDesc());
					type.setPriceDesc(propertyRepairTypeEntity.getPriceDesc());
					type.setPriceCfgId(propertyRepairTypeEntity.getPriceCfgId());
					break;
				}
				//不满足物业公司引用的 清除描述信息, 维护的时候不对dredgeType中的描述信息进行维护，所以不能显示
				type.setDesc(null);
				type.setPriceDesc(null);
			}
		}
		Collections.reverse(dredgeTypeList);
		jsonResponse.putData("dredgeTypeList", dredgeTypeList);
		
		return jsonResponse;
	}
	
	/**
	 * 维修内转外
	 */
	@RequestMapping("/inToOut.html")
	@ResponseBody
	public JsonResponse inToOut(String dredgeTypeId, String subTypeId, String expectDate, String repairId, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dredgeTypeId", dredgeTypeId);
		params.put("subTypeId", subTypeId);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			params.put("expectDate", df.parse(expectDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		params.put("repairId", repairId);
		params.put("userId", userId);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/addDredgeBill.json", params);
		return jsonResponse;
	}
	
	/**
	 * 查询服务类型
	 * 
	 * @return
	 */
	@RequestMapping("/queryServiceTypes.json")
	@ResponseBody
	public JsonResponse queryServiceTypes(BigInteger userId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		JsonResponse jsonResponse = simpleHttpClient.submitSimple("/dredge/qryCommunitySupplyTypeAndDredgeType.json", params);

		return jsonResponse;
	}
	
	/**
	 * 查询该房号是否开启报修服务
	 * @author wenfq 2017-02-28
	 * @return 
	 */
	@RequestMapping("/qryIsOpenRepaireServiceByRoomId.json")
	@ResponseBody
	public JsonResponse qryIsOpenRepaireServiceByRoomId(BigInteger roomId){
		if(roomId == null){
			throw new ValidateRuntimeException("roomId can't be null!");
		}
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.putData("isOpenService", propertyRepairService.qryIsOpenRepaireServiceByRoomId(roomId));
		return jsonResponse;
	}
	
	/**
	 * 查询该房号下开启的物业维修类型
	 * @author wenfq 2017-02-28
	 * @return 
	 */
	@RequestMapping("/qryPrTypeListByRoomId.json")
	@ResponseBody
	public JsonResponse qryPrTypeListByRoomId(BigInteger roomId){
		if(roomId == null){
			throw new ValidateRuntimeException("roomId can't be null!");
		}
		JsonResponse jsonResponse = new JsonResponse();
		List<PropertyRepairTypeEntity> propertyRepairTypeEntityList = propertyRepairService.select_prTypeList_by_roomId(roomId);
		jsonResponse.putData("prtList", propertyRepairTypeEntityList);
		return jsonResponse;
	}

}
