package com.cnfantasia.server.ms.dredge.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commonBusiness.entity.OperateConfigRange;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.AddDredgeBillParamter;
import com.cnfantasia.server.api.dredge.entity.DredgeBillEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForDetail;
import com.cnfantasia.server.api.dredge.entity.DredgeDetails;
import com.cnfantasia.server.api.dredge.entity.DredgeDetails.ProcessRecording;
import com.cnfantasia.server.api.dredge.entity.DredgeMasterList4admin;
import com.cnfantasia.server.api.dredge.entity.DredgeProduct4Admin;
import com.cnfantasia.server.api.dredge.entity.DredgeProduct4Turn;
import com.cnfantasia.server.api.dredge.entity.DredgeProductView;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeEntity;
import com.cnfantasia.server.api.dredge.entity.MasterInfo4Audit;
import com.cnfantasia.server.api.dredge.entity.ProductAddNewParamter;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.dredge.util.DredgeCombineStatusUtil;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.communitySupply.ICommunitySupplyManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.IDredgeBillBaseService;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;
import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.service.DredgeBillHasProcessRecordingBaseService;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProduct.service.DredgeProductBaseService;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;
import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;
import com.cnfantasia.server.domainbase.dredgeWorker.service.DredgeWorkerBaseService;
import com.cnfantasia.server.domainbase.dredgeWorker.service.IDredgeWorkerBaseService;
import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.entity.DredgeWorkerRevenueConfig;
import com.cnfantasia.server.domainbase.dredgeWorkerRevenueConfig.service.IDredgeWorkerRevenueConfigBaseService;
import com.cnfantasia.server.domainbase.serviceSupplier.entity.ServiceSupplier;
import com.cnfantasia.server.domainbase.serviceSupplier.service.ServiceSupplierBaseService;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant.EbSupplyType;
import com.cnfantasia.server.ms.advertise.service.IAdvertiseForOmsService;
import com.cnfantasia.server.ms.ebuy.constant.EbuyDict;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.service.INoticeService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.propertyRepair.service.IPropertyRepairService;
import com.cnfantasia.server.ms.provinceCityBlock.service.IProvinceCityBlockService;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dredge")
public class DredgeController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
		
	@Resource
	DredgeService dredgeService;

	@Resource
	IProvinceCityBlockService provinceCityBlockService;
	@Resource
	IDredgeBillBaseService dredgeBillBaseService;
	@Resource
	IDredgeWorkerBaseService dredgeWorkerBaseService;
	@Resource
	IDredgeWorkerRevenueConfigBaseService dredgeWorkerRevenueConfigBaseService;
	@Resource
	DredgeBillHasProcessRecordingBaseService dredgeBillHasProcessRecordingBaseService;

	@Resource
	ServiceSupplierBaseService serviceSupplierBaseService;
	
	@Resource
	private IPropertyRepairService propertyRepairService;
	
	@Resource
	private ICommunitySupplyManager communitySupplyManager;
	
	@Resource 
	private DredgeProductBaseService dredgeProductBaseService;
	
	@Resource 
    IAdvertiseForOmsService advertiseForOmsService;

	/**
	 * 维修师傅列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/masterlist.html")
	public ModelAndView masterList(HttpServletRequest request) {
		
		request.setAttribute("pcbList", provinceCityBlockService.getProvinceWithCityBlockList());
		
		List<Map> dtList = dredgeService.selectDredgeTypeNameList();
		request.setAttribute("dtList", dtList);	
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("auditStatus", request.getParameter("auditStatus"));
		paramMap.put("dtId", request.getParameter("dtId"));
		paramMap.put("abId", request.getParameter("abId"));
		paramMap.put("mobile", request.getParameter("mobile"));
		paramMap.put("realName", request.getParameter("realName"));
		paramMap.put("huaId", request.getParameter("huaId"));
		paramMap.put("idNumber", request.getParameter("idNumber"));
		paramMap.put("registTimeBegin", request.getParameter("registTimeBegin"));
		paramMap.put("registTimeEnd", request.getParameter("registTimeEnd"));
		
		int resultSize = dredgeService.getMaster4AuditListSize(paramMap);
		request.setAttribute("resultSize", resultSize);
		
		List<DredgeMasterList4admin> masterList = dredgeService.selectMasterList4Audit(paramMap);
		request.setAttribute("resList", masterList);
		return new ModelAndView("/dredge/master4AuditList");
	}
	
	/**
	 * 维修师傅-审核
	 * @param request
	 * @return
	 */
	@RequestMapping("/masterAudit.html")
	public ModelAndView masterAudit(HttpServletRequest request) {
		
		BigInteger dwId = ParamUtils.getBigInteger(request, "dwId", null);
		if(dwId == null) {
			throw new ValidateRuntimeException("dwId can't be null");
		}
		MasterInfo4Audit dw = dredgeService.selectMasterDetail_forAudit(dwId);
		request.setAttribute("dw", dw);
		
		return new ModelAndView("/dredge/masterAudit");
	}
	
	/**
	 * 维修师傅-审核保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/masterAuditSave.html")
	public ModelAndView masterAuditSave(HttpServletRequest request) {
		BigInteger dwId = ParamUtils.getBigInteger(request, "dwId", null);
		String auditResultString = ParamUtils.getString(request, "auditResult", null);
		if(dwId == null || auditResultString == null) {
			throw new ValidateRuntimeException("dwId or auditResult can't be null");
		}
		
		int auditResult = auditResultString.equals("pass") ? 2 : 3;
		String auditDesc = ParamUtils.getString(request, "notPassReason", null);
		
		DredgeWorker dw = new DredgeWorker();
		dw.setId(dwId);
		dw.setAuditStatus(auditResult);
		dw.setAuditDesc(auditDesc);
		dw.setSys0UpdUser(UserContext.getOperIdBigInteger());
		dredgeWorkerBaseService.updateDredgeWorker(dw); 
		
		return new ModelAndView("redirect:/dredge/masterlist.html");
	}
	
	/**
	 * 维修师傅-查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/masterViewDetail.html")
	public ModelAndView masterViewDetail(HttpServletRequest request) {
		
		BigInteger dwId = ParamUtils.getBigInteger(request, "dwId", null);
		if(dwId == null) {
			throw new ValidateRuntimeException("dwId can't be null");
		}
		
		MasterInfo4Audit dw = dredgeService.selectMasterDetail_forAudit(dwId);
		request.setAttribute("dw", dw);
		
		return new ModelAndView("/dredge/masterViewDetail");
	}
	
	/**
	 * 配置新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/configAdd.html")
	public ModelAndView configAdd(HttpServletRequest request){
		//1.搜集参数
		BigInteger dwId = ParamUtils.getBigInteger(request, "dwId", null);
		if(dwId == null) {
			throw new ValidateRuntimeException("dwId can't be null");
		}
		
		//2.交互
		MasterInfo4Audit dw = dredgeService.selectMasterDetail_forAudit(dwId);
		request.setAttribute("dw", dw);
		DredgeWorkerRevenueConfig dwrcQry = new DredgeWorkerRevenueConfig();
		dwrcQry.settUserFId(new BigInteger(dw.getHuaId()+""));
		List<DredgeWorkerRevenueConfig> dwrcList = dredgeWorkerRevenueConfigBaseService.getDredgeWorkerRevenueConfigByCondition(MapConverter.convertBean(dwrcQry));
		if(dwrcList.size()==1){
			request.setAttribute("dwrc", dwrcList.get(0));
		}
		
		//3.结果处理
		return new ModelAndView("/dredge/masterRevenueConfigAdd");
	}
	
	/**
	 * 配置新增页面——保存
	 * @param request
	 * @return
	 */
	@RequestMapping("/configSave.json")
	@ResponseBody
	public JsonResponse configSave(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setMessage("操作成功");
		//1.搜集参数
		BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
		int ruleType = ParamUtils.getInt(request, "ruleType", 1); //默认百分比
		double platformValue = ParamUtils.getDouble(request, "platformValue", 20); //默认20%
		int activeStatus = ParamUtils.getInt(request, "activeStatus", 2);//默认不开启
		 
		if(userId == null) {
			throw new ValidateRuntimeException("userId can't be null");
		}
		
		//2.交互
		DredgeWorkerRevenueConfig dwrcQry = new DredgeWorkerRevenueConfig();
		dwrcQry.settUserFId(userId);
		List<DredgeWorkerRevenueConfig> dwrcList = dredgeWorkerRevenueConfigBaseService.getDredgeWorkerRevenueConfigByCondition(MapConverter.convertBean(dwrcQry));
		DredgeWorkerRevenueConfig dwrc = null;
		if(dwrcList.size()==0){
			dwrc = new DredgeWorkerRevenueConfig();
			dwrc.setActiveStatus(activeStatus);
			dwrc.setPlatformValue(platformValue);
			dwrc.setRuleType(ruleType);
			dwrc.settUserFId(userId);
			dwrc.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_dredge_worker_revenue_config));
			dredgeWorkerRevenueConfigBaseService.insertDredgeWorkerRevenueConfig(dwrc);
			jsonResponse.setMessage("新增配置成功");
		}else{
			dwrc = dwrcList.get(0);
			dwrc.setActiveStatus(activeStatus);
			dwrc.setPlatformValue(platformValue);
			dwrc.setRuleType(ruleType);
			dredgeWorkerRevenueConfigBaseService.updateDredgeWorkerRevenueConfig(dwrc);
			jsonResponse.setMessage("更新配置成功");
		}
		
		//3.结果处理
		return jsonResponse;
	}
	
	
	/**
	 * 派单，如果十分到家的师傅，还要推送到十分到家
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	@RequestMapping("/assignBillToWorker.json")
	@ResponseBody
	public JsonResponse assignBillToWorker(HttpServletRequest request,BigInteger dredgeBillId,  BigInteger workerId) throws IOException {
		double laborFee = ParamUtils.getDouble(request, "laborFee", 0);
		double otherFee = ParamUtils.getDouble(request, "otherFee", 0);
		String expectWorkTime = ParamUtils.getString(request, "expectWorkTime", null);
		JsonResponse jsonResponse = new JsonResponse();

		DredgeWorker dwQry = new DredgeWorker();
		dwQry.settUserFId(workerId);
		DredgeWorkerBaseService dredgeWorkerBaseService = (DredgeWorkerBaseService) CnfantasiaCommbusiUtil.getBeanManager("dredgeWorkerBaseService");
		DredgeWorker dw = dredgeWorkerBaseService.getDredgeWorkerByCondition(MapConverter.toMap(dwQry)).get(0);
		
		if (DredgeConstant.DredgeServiceSupplier.SFDJ.equals(dw.gettServiceSupplierFId())) {//如果是十分到家的师傅
			if(laborFee + otherFee <= 0){
				jsonResponse.setStatus("0001");
				jsonResponse.setMessage("十分到家的推送单必须要录入金额");
				return jsonResponse;
			}
			String pushResultInfo = dredgeService.pushOrderToSfdj(dredgeBillId, workerId, laborFee, otherFee);
			JSONObject parseObject = JSON.parseObject(pushResultInfo);
			// 十分到家 code=10000 才是成功
			jsonResponse.setStatus("10000".equals(parseObject.getString("code")) ? "0000" : "0001");
			jsonResponse.setMessage(parseObject.getString("msg"));
			if(jsonResponse.getStatus().equals("0000")){
				dredgeService.assignBillToWorker(dredgeBillId, workerId, laborFee, otherFee, expectWorkTime);
			}
		} else if (DredgeConstant.DredgeServiceSupplier.QSDJ.equals(dw.gettServiceSupplierFId())) {//如果是 轻松到家的师傅
			if (laborFee + otherFee <= 0) {
				jsonResponse.setStatus("0001");
				jsonResponse.setMessage("轻松到家的推送单必须要录入金额");
				return jsonResponse;
			}
			String pushResultInfo = dredgeService.pushOrderToQsdj(dredgeBillId, workerId, laborFee, otherFee);
			JSONObject parseObject = JSON.parseObject(pushResultInfo);
			// 轻送到家 code=0 才是成功
			jsonResponse.setStatus("0".equals(parseObject.getString("code")) ? "0000" : "0001");
			jsonResponse.setMessage(parseObject.getString("msg"));
			if (jsonResponse.getStatus().equals("0000")) {
				dredgeService.assignBillToWorker(dredgeBillId, workerId, laborFee, otherFee, expectWorkTime);
			}
		} else {
			dredgeService.assignBillToWorker(dredgeBillId, workerId, laborFee, otherFee, expectWorkTime);
		}
		return jsonResponse;
	}
	
	/**
	 * 查看 十分到家 订单状态
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws HttpException 
	 */
	@RequestMapping("/querySfdjOrderStatus.json")
	@ResponseBody
	public String querySfdjOrderStatus(HttpServletRequest request, BigInteger dredgeBillId) throws IOException {
		return dredgeService.querySfdjOrderStatus(dredgeBillId);
	}

	/**
	 * 维修单列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/dredgeOrderList.html")
	public ModelAndView dredgeOrderList(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sys0AddTime_START", ParamUtils.getString(request, "sys0AddTime_START"));
		paramMap.put("sys0AddTime_END", ParamUtils.getString(request, "sys0AddTime_END"));
		paramMap.put("dredgeType", ParamUtils.getString(request, "dredgeType"));
		paramMap.put("address", ParamUtils.getString(request, "address"));
		paramMap.put("dredgeBillId", ParamUtils.getString(request, "dredgeBillId"));
		paramMap.put("tUserFId", ParamUtils.getString(request, "tUserFId"));
		paramMap.put("userTel", ParamUtils.getString(request, "userTel"));
		paramMap.put("payTime_START", ParamUtils.getString(request, "payTime_START"));
		paramMap.put("payTime_END", ParamUtils.getString(request, "payTime_END"));
		paramMap.put("content", ParamUtils.getString(request, "content"));
		paramMap.put("expectdate", ParamUtils.getString(request, "expectdate"));
		paramMap.put("tel", ParamUtils.getString(request, "tel"));
		paramMap.put("cityName", ParamUtils.getString(request, "cityName"));
		paramMap.put("blockName", ParamUtils.getString(request, "blockName"));
		paramMap.put("tWorkerFId", ParamUtils.getString(request, "tWorkerFId"));
		paramMap.put("workerName", ParamUtils.getString(request, "workerName"));
		paramMap.put("workerMobile", ParamUtils.getString(request, "workerMobile"));
		paramMap.put("billNo", ParamUtils.getString(request, "billNo"));
		paramMap.put("buyerId", ParamUtils.getString(request, "buyerId"));
		paramMap.put("serviceSupplier", ParamUtils.getString(request, "serviceSupplier"));
		String combineStatus = ParamUtils.getString(request, "combineStatus");
		if(!DataUtil.isEmpty(combineStatus)) {//全部查询 不需要状态信息
			paramMap.put("combineStatus", combineStatus);//提交派单中
		}
		//总数量
		int resultSize = dredgeService.getDredgeBillListCount(paramMap);
		//导出列表使用
		request.getSession().setAttribute("dredgeOrderListQueryParamMap", ((HashMap<String, Object>) paramMap).clone());
		// 页数的参数名
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数
		int pageSize = Integer.valueOf(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);

		List<DredgeBillEntity> dredgeOrderList = dredgeService.getDredgeBillList(paramMap);

		request.setAttribute("resultSize", resultSize);
		request.setAttribute("resList", dredgeOrderList);
		request.setAttribute("param", paramMap);
		return new ModelAndView("/dredge/dredgeOrderList");
	}
	
	/**
	 * 维修单列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewDetail.html")
	public ModelAndView viewDetail(HttpServletRequest request, String dredgeId) {
		DredgeDetails detail = dredgeService.getDredgeBillDetail(dredgeId);
		if(detail.getPayMethod() != null) {
			detail.setPayMethodStr(EbuyDict.PAYMETHOD_MAP.get(detail.getPayMethod().toString()));
		}
		
		request.setAttribute("detail", detail);
		
		// 取出流程记录
		{
			DredgeBillHasProcessRecording dredgeBillHasProcessRecordingQry = new DredgeBillHasProcessRecording();
			dredgeBillHasProcessRecordingQry.setBillType(DredgeConstant.DredgeBillType.Dredge_Bill_Common);
			dredgeBillHasProcessRecordingQry.settDredgeBillFId(new BigInteger(dredgeId));
			List<DredgeBillHasProcessRecording> prList = dredgeBillHasProcessRecordingBaseService.getDredgeBillHasProcessRecordingByCondition(MapConverter.toMap(dredgeBillHasProcessRecordingQry));
			if(!prList.isEmpty())
				request.setAttribute("processRecordList", dredgeService.convertProcessRecordMap(prList));
		}

		return new ModelAndView("/dredge/viewDetail");
	}
	
	/**
	 * 查看现场照片
	 * @param request
	 * @return
	 */
	@RequestMapping("/getScenePhotos.json")
	public ModelAndView getScenePhotos(HttpServletRequest request) {
		DredgeBillForDetail dredge = dredgeService.qryDredgeBillDetail(ParamUtils.getString(request, "id"));
		List<DredgeBillForDetail> picEntityList = new ArrayList<DredgeBillForDetail>();
		if(dredge != null && dredge.getPicUrl() != null && !("").equals(dredge.getPicUrl())){
			String picStr = dredge.getPicUrl();
			String[] picArr = picStr.split(";");
			if(picArr.length>0){
				for(int i=0;i<picArr.length;i++){
					DredgeBillForDetail picEntity = new DredgeBillForDetail();
					picEntity.setPicUrl(picArr[i]);
					picEntityList.add(picEntity);
				}
			}
		}
		request.setAttribute("resList", picEntityList);
		return new ModelAndView("/dredge/dredgePhotosShow");
	}
	
	/**
	 * 查看师傅流程记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewProcessRecord.json")
	public ModelAndView viewProcessRecord(HttpServletRequest request) {
		// 取出流程记录
		{
			DredgeBillHasProcessRecording dredgeBillHasProcessRecordingQry = new DredgeBillHasProcessRecording();
			dredgeBillHasProcessRecordingQry.setBillType(DredgeConstant.DredgeBillType.Dredge_Bill_Common);
			dredgeBillHasProcessRecordingQry.settDredgeBillFId(new BigInteger((ParamUtils.getString(request, "id"))));
			List<DredgeBillHasProcessRecording> prList = dredgeBillHasProcessRecordingBaseService.getDredgeBillHasProcessRecordingByCondition(MapConverter.toMap(dredgeBillHasProcessRecordingQry));
			if(!prList.isEmpty())
				request.setAttribute("processRecordList", dredgeService.convertProcessRecordMap(prList));
		}
				
		return new ModelAndView("/dredge/processRecordShow");
	}
	
	/**
	 * 查询订单跟踪信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryOrderFollowRecord.json")
	@ResponseBody
	public JsonResponse qryOrderFollowRecord(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		List<DredgeBillFollowRecord> recordList = dredgeService.qryOrderFollowRecord(ParamUtils.getString(request, "id"));
		jsonResponse.setDataValue(recordList);
		return jsonResponse;
	}

	/**
	 * 师傅列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getDredgeWorkerList.json")
	@ResponseBody
	public JsonResponse getDredgeWorkerList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//师傅列表
		List<Map<String, Object>> dredgeWorkerList = dredgeService.getDredgeWorkerList();
		jsonResponse.setDataValue(dredgeWorkerList);

		return jsonResponse;
	}

	/**
	 * 维修单跟进
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateDredgeBillProgress.json")
	@ResponseBody
	public String updateDredgeBillProgress(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rateProgress", ParamUtils.getString(request, "rateProgress"));
		paramMap.put("userAccount", UserContext.getCurrUser().getUserAccount());//登录账号
		paramMap.put("dredgeBillId",ParamUtils.getString(request, "dredgeBillId"));
		//师傅列表
		int count = dredgeService.updateDredgeBillProgress(paramMap);

		JsonResponse jsonResponse = new JsonResponse();
		return JSON.toJSONString(jsonResponse);
	}

	/**
	 * 修改维修单状态
	 * @param request
	 * @return
	 */
	@RequestMapping("/editDredgeBillStatus.json")
	@ResponseBody
	public String editDredgeBillStatus(HttpServletRequest request) {
		BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", BigInteger.ZERO);
		JsonResponse jsonResponse = new JsonResponse();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("status", ParamUtils.getString(request, "status"));
		paramMap.put("dredgeBillId", dredgeBillId);
		dredgeService.editDredgeBillStatus(paramMap);

		return JSON.toJSONString(jsonResponse);
	}

	@RequestMapping(value = "assignMaster.html", method = RequestMethod.POST)
	public ModelAndView assignMaster(BigInteger dredgeId, BigInteger propertyRepairerId, String estimateDoorTimeBegin) {
		if (dredgeId == null)
			throw new IllegalArgumentException("repairId can not be null");
		if (propertyRepairerId == null) {
			throw new IllegalArgumentException("propertyRepairerId can not be null");
		}
		DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.setId(dredgeId);
		dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
		dredgeBill.settWorkerFId(propertyRepairerId);
		dredgeBill.setAcceptTime(DateUtils.getCurrentDate());
		dredgeBill.setExpectWorkTime(estimateDoorTimeBegin);
		dredgeService.assignMaster(dredgeBill);

		return new ModelAndView("redirect:/propertyRepair/listRepair.html");
	}

	@RequestMapping(value = "closeRepair.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse closeRepair(BigInteger dredgeId) {
		JsonResponse json = new JsonResponse();
		DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.setId(dredgeId);
		DredgeBill now = dredgeBillBaseService.getDredgeBillBySeqId(dredgeId);
		if (now.getStatus().compareTo(DredgeConstant.DredgeBill.DredgeBill_Status_Submited) == 0) {
			dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Property_Closed);
			dredgeBillBaseService.updateDredgeBill(dredgeBill);
			ShortMsgUtil.sendMessage(now.getTel(), "dredge_close", "");

			String title = "物业订单关闭";
			String content = "非常抱歉，您的物业订单因不在物业服务范围内，已被物业管理处关闭，点击查看详情。";
			String pushId = MsgpushDict.PushId.PropertyRepairBill_Closed;
			propertyRepairService.addPushMessage(title, content, pushId, now.gettUserFId(), NoticeDict.Message_Type.Property_Repair_Closed);
		} else {
			json.setStatus("0001");
			json.setMessage("当前状态不可关闭");
		}
		return json;
	}

	@RequestMapping(value = "turnBillType.html", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse turnBillType(BigInteger dredgeProductId, String expectDate, BigInteger dredgeId, HttpServletRequest request) throws ParseException {

		List<DredgeProductSpecEntity> specList = JSON.parseArray(request.getParameter("specList"), DredgeProductSpecEntity.class);

		DredgeBill nowBill = dredgeBillBaseService.getDredgeBillBySeqId(dredgeId);
		AddDredgeBillParamter paramter = new AddDredgeBillParamter();
		paramter.setOldBillId(dredgeId);
		paramter.setUserId(nowBill.gettUserFId());
		paramter.setPics(nowBill.getPicUrl());
		paramter.setDredgeProductId(dredgeProductId);
		paramter.setDredgeAddress(nowBill.getAddress());
		paramter.setDredgeContent(nowBill.getContent());
		paramter.setTel(nowBill.getTel());
		paramter.setExpectDate(DateUtil.formatSecond.get().parse(expectDate).getTime());
		paramter.setBlockId(nowBill.gettAddressBlockFId());
		paramter.setRoomId(nowBill.getRoomid());
		paramter.setSubmitChannel(nowBill.getSubmitChannel());
		paramter.setProductSpecList(specList);
		dredgeService.addDredgeBill(paramter);

		return new JsonResponse();
	}

	@RequestMapping("/exportDredgeOrders.json")
	public void exportDredgeOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("dredgeOrderListQueryParamMap");
		List<DredgeBillEntity> dredgeOrderList = dredgeService.getDredgeBillList(paramMap);
		FileInputStream fin = null;
		OutputStream fOut = null;
		if (dredgeOrderList.size() > 0) {
			try {
				String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_dredge_order_list_export.xls");
				fin = new FileInputStream(new File(filePath));
				HSSFWorkbook wb = new HSSFWorkbook(fin);
				HSSFSheet sheet = wb.getSheetAt(0);
				int dataFromRowIndex = 1; //从第2行开始是数据行
				for (int i = 0; i < dredgeOrderList.size(); i++) {
					DredgeBillEntity dredgeBillEntity = dredgeOrderList.get(i);
					HSSFRow row = sheet.getRow(i + dataFromRowIndex);
					HSSFCellStyle cellStyle = wb.createCellStyle();       
					cellStyle.setWrapText(true);       
					
					
					if (row == null) {
						row = sheet.createRow(i + dataFromRowIndex);
					}
					int j = 0;
					row.createCell(j++).setCellValue(dredgeBillEntity.getBillNo());//订单号
					String type = dredgeBillEntity.getSupplyTypeName() + "-" + dredgeBillEntity.getDredgeType();
					if(dredgeBillEntity.getDredgeType2nd() != null) {
						type += "-" + dredgeBillEntity.getDredgeType2nd();
					}
					row.createCell(j++).setCellValue(dredgeBillEntity.getSupplyTypeName() + "-" + dredgeBillEntity.getDredgeType() + "-" + dredgeBillEntity.getDredgeType2nd());// 维修类型
					row.createCell(j++).setCellValue(dredgeBillEntity.getServiceSupplier());// 服务供应商
					row.createCell(j++).setCellValue(EbuyDict.PAYMETHOD_MAP.get(dredgeBillEntity.getPayMethod().toString()));// 服务供应商
					row.createCell(j++).setCellValue(div100(dredgeBillEntity.getTotalAmount() == null ? 0L : dredgeBillEntity.getTotalAmount()).toString());// 订单总额
					Long payAmountpPerson = 0L;
					Long payAmountOrther = 0L;
					List<DredgeBillAmountDetail> dredgeBillAmountDetailList = dredgeBillEntity.getDredgeBillAmountDetailList();
					if(!DataUtil.isEmpty(dredgeBillAmountDetailList)) {
						if(!DataUtil.isEmpty(dredgeBillAmountDetailList.get(0))) {
							payAmountpPerson = dredgeBillAmountDetailList.get(0).getPayAmount() == null ? 0L: dredgeBillAmountDetailList.get(0).getPayAmount();
						}
						if(dredgeBillAmountDetailList.size() == 2 && !DataUtil.isEmpty(dredgeBillAmountDetailList.get(1))) {
							payAmountOrther = dredgeBillAmountDetailList.get(1).getPayAmount() == null ? 0L : dredgeBillAmountDetailList.get(1).getPayAmount();
						}
					}
					row.createCell(j++).setCellValue(div100(payAmountpPerson).toString());// 人工费
					if(dredgeBillEntity.getOrderAmount() - dredgeBillEntity.getTotalAmount() > 0) {
						row.createCell(j++).setCellValue(div100(dredgeBillEntity.getOrderAmount() - dredgeBillEntity.getTotalAmount()).toString());// 耗材费
					} else {
						row.createCell(j++).setCellValue("0.00");// 耗材费
					}
					
					row.createCell(j++).setCellValue(div100(payAmountOrther).toString());// 其他费
					row.createCell(j++).setCellValue(dredgeBillEntity.getSys0AddTime());// 下单时间
					row.createCell(j++).setCellValue(dredgeBillEntity.getPayTime());// 支付时间
					row.createCell(j++).setCellValue(dredgeBillEntity.getExpectdate());// 预约时间/期望时间
					row.createCell(j++).setCellValue(dredgeBillEntity.getAcceptTime());// 接单时间
					row.createCell(j++).setCellValue(dredgeBillEntity.getExpectWorkTime());// 预计上门时间
					row.createCell(j++).setCellValue(dredgeBillEntity.getCityName());// 城市
					row.createCell(j++).setCellValue(dredgeBillEntity.getBlockName());// 区县
					row.createCell(j++).setCellValue(dredgeBillEntity.getAddress());// 维修地址
					row.createCell(j++).setCellValue(dredgeBillEntity.getContent());// 内容描述
					row.createCell(j++).setCellValue(dredgeBillEntity.gettUserFId()+"");//用户解放号
					row.createCell(j++).setCellValue(dredgeBillEntity.getUserTel());//下单用户手机号
					row.createCell(j++).setCellValue(dredgeBillEntity.getBuyerId()==null?"":dredgeBillEntity.getBuyerId().toString());;//支付解放号
					row.createCell(j++).setCellValue(dredgeBillEntity.getLinkName());// 联系人
					row.createCell(j++).setCellValue(dredgeBillEntity.getTel());// 联系电话
					row.createCell(j++).setCellValue(dredgeBillEntity.gettWorkerFId() == null ? "" : dredgeBillEntity.gettWorkerFId() +"");// 师傅解放号
					row.createCell(j++).setCellValue(dredgeBillEntity.getWorkerName());// 师傅姓名
					row.createCell(j++).setCellValue(dredgeBillEntity.getWorkerMobile());// 师傅手机
					row.createCell(j++).setCellValue(getOrderCombineStatus(dredgeBillEntity.getCombineStatus()));// 订单状态
					row.createCell(j++).setCellValue(DredgeCombineStatusUtil.getCombineStatusDesc(dredgeBillEntity.getBillType(), dredgeBillEntity.getStatus()));// 订单状态描述

					StringBuilder pr = new StringBuilder();
					short height = 0;
					for(ProcessRecording prTemp : dredgeBillEntity.getProcessRecordingList()) {
						if(height == 0) {
							pr.append(DateUtils.formatTime(prTemp.getRecordingTm())).append(prTemp.getProcessDesc());
						} else {
							pr.append("\n").append(DateUtils.formatTime(prTemp.getRecordingTm())).append(prTemp.getProcessDesc());
						}
						height += 330;
					}
					
					HSSFCell cell = row.createCell(j++);
					cell.setCellValue(pr.toString());// 流程记录
					cell.setCellStyle(cellStyle);
					if(height > 0) {
						row.setHeight(height);
					}
					
					/*
					short height2 = 0;
					pr = "";
					for(Comment comment : dredgeBillEntity.getCommentList()) {
						if(height2 == 0) {
							pr += comment.getName() + ":" + comment.getValue();
						} else {
							pr += "\n" + comment.getName() + ":" + comment.getValue();
						}
						height2 += 330;
					}
					
					cell = row.createCell(j++);
					cell.setCellValue(pr);// 业主评价
					cell.setCellStyle(cellStyle);
					if(height2 > 0 && height2 > height) {
						row.setHeight(height2);
					}
					*/
					row.createCell(j++).setCellValue(dredgeBillEntity.getCommentLevel());// 星级
					
				}
				sheet.autoSizeColumn((short)25);
				sheet.autoSizeColumn((short)26);
				// 生成提示信息
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String fileName = "维修单列表" + format.format(new Date());
				response.setContentType("application/vnd.ms-excel;charset=UTF-8");
				response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
				fOut = response.getOutputStream();
				wb.write(fOut);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fOut != null) {
					try {
						fOut.flush();
						fOut.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fin != null) {
					try {
						fin.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private static BigDecimal div100(Long srcPrice) {
		if(srcPrice==null){return null;}
		BigDecimal tmpDeci = new BigDecimal(srcPrice);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
		return resBig;
	}

	private String getOrderCombineStatus(Integer status) {
		String orderStatus = "";
		switch (status) {
			case 1 :
				orderStatus = "待付款";
				break;
			case 2 :
				orderStatus = "待分配";
				break;
			case 3 :
				orderStatus = "待服务";
				break;
			case 4 :
				orderStatus = "已服务";
				break;
			case 5 :
				orderStatus = "已完成";
				break;
			case 6 :
				orderStatus = "已取消";
				break;
			case 7 :
				orderStatus = "退款中";
				break;
			case 8 :
				orderStatus = "已退款";
				break;
			default:
				orderStatus = "状态不对";
		}
		return orderStatus;
	}
	private String getOrderStatus(Integer status) {
		String orderStatus = "";
		switch (status) {
			case 0 :
				orderStatus = "待付款";
				break;
			case 1 :
				orderStatus = "可接单";
				break;
			case 2 :
				orderStatus = "已接单";
				break;
			case 3 :
				orderStatus = "用户已付款";
				break;
			case 4 :
				orderStatus = "用户已取消";
				break;
			case 5 :
				orderStatus = "已评价";
				break;
			case 7 :
				orderStatus = "已接单";
				break;
			case 8 :
				orderStatus = "已接单";
				break;
			default:
				orderStatus = "状态不对";
		}
		return orderStatus;
	}
	
	/**
	 * 上门服务商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/productList.html")
	public ModelAndView qryDredgeProductList(HttpServletRequest request) {
		// 1.搜集参数
		Map<String,Object> paramMap = this.getParameterMap(request);
		// 2.交互
		List<DredgeProduct4Admin> dpList = dredgeService.qryDredgeProductList(paramMap);
		// 3.结果处理
		request.setAttribute("cstTypeList", communitySupplyManager.getCommunitySupplyTypeList(null));//取上门服务类
		request.setAttribute("ssList", serviceSupplierBaseService.getServiceSupplierByCondition(null));//服务供应商
		request.setAttribute("resList", dpList);
		request.setAttribute("resultSize", dredgeService.qryDredgeProductCount(paramMap));
		
		return new ModelAndView("/dredge/dredgeProductList");
	}

	@RequestMapping(value = "/productList.json", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse dredgeProductList(HttpServletRequest request) {
		// 1.搜集参数
		Map<String,Object> paramMap = this.getParameterMap(request);
		paramMap.put("upShelfState", request.getParameter("upShelfState"));
		paramMap.put("_begin", 0);
		paramMap.put("_length", 20);
		// 2.交互
		List<DredgeProduct4Admin> dpList = dredgeService.qryDredgeProductList(paramMap);
		JsonResponse json = new JsonResponse();
		json.putData("list", dpList);
		return json;
	}

	@Resource
	private INoticeService noticeService;
	
	
	/**
	 * 获取商品及规则List
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryProductList.json")
	public JsonResponse qryProductList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		
		// 1.搜集参数
		BigInteger dt2Id = ParamUtils.getBigInteger(request, "dt2Id", null);
		
		// 2.交互
		List<DredgeProduct4Turn> dredgeTypeList = dredgeService.qryDredgeProductList4Trun(dt2Id);
		
		// 3.结果处理
		jsonResponse.putData("list", dredgeTypeList);
		
		return jsonResponse;
	}
	
	/**
	 * 获取疏通服务类型
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getDredgeTypeList.json")
	public JsonResponse getDredgeTypeList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		// 1.搜集参数
		BigInteger parentTypeId = ParamUtils.getBigInteger(request, "parentTypeId", null);

		// 2.交互
		List<DredgeTypeEntity> dredgeTypeList = dredgeService.getDredgeTypeByParentTypeId(parentTypeId, null, true);

		// 3.结果处理
		jsonResponse.putData("list", dredgeTypeList);
		
		return jsonResponse;
	}
	
	/**
	 * 查询服务供应商
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/productUdpStatus.json")
	public JsonResponse productUdpStatus(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		
		// 1.搜集参数
		int status = ParamUtils.getInt(request, "status", 2);
		BigInteger id = ParamUtils.getBigInteger(request, "dpId", null);
		
		// 2.交互
		if(id == null){
			jsonResponse.setStatus("0001");
			jsonResponse.setMessage("id不能为空");
			return jsonResponse;
		}
		
		DredgeProduct dp = new DredgeProduct();
		dp.setId(id);
		dp.setStatus(status == 1 ? 2: 1);
		dredgeProductBaseService.updateDredgeProduct(dp);
		
		// 3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 查询服务供应商
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getServiceSupplierList.json")
	public JsonResponse getServiceSupplierList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();

		// 1.搜集参数

		// 2.交互
		List<ServiceSupplier> ssList = serviceSupplierBaseService.getServiceSupplierByCondition(null);

		// 3.结果处理
		jsonResponse.putData("list", ssList);
		
		return jsonResponse;
	}
	
	/**
	 * 上门服务商品新增
	 * @param request
	 * @return
	 */
	@RequestMapping("/productAddNew.html")
	public ModelAndView productAddNew(HttpServletRequest request) {
		// 1.搜集参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("gbIdList", UserContext.getGbIdList());
		// 2.交互
		List<GroupBuildingSimpleEntity> gbList = noticeService.getGroupBuildingSimpleList(params);
		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
		
		// 3.结果处理
		request.setAttribute("gbList", gbList);
		request.setAttribute("blockList", blockList);
		request.setAttribute("cstTypeList", communitySupplyManager.getCommunitySupplyTypeList(null));//取上门服务类
		request.setAttribute("ssList", serviceSupplierBaseService.getServiceSupplierByCondition(null));//服务供应商
		
		return new ModelAndView("/dredge/dredgeProductAddNew");
	}
	
	/**
	 * 上门服务商品查看
	 * @param request
	 * @return
	 */
	@RequestMapping("/productView.html")
	public ModelAndView productView(HttpServletRequest request) {
		// 1.搜集参数
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		// 2.交互
		DredgeProductView dp = dredgeService.qryDredgeProductDetail(id);
		List<OperateConfigRange> dpSellRangeList = dredgeService.qryDredgeProductSellRangeByPrdtId(id);
		// 3.结果处理
		request.setAttribute("dp", dp);//取上门服务类
		request.setAttribute("dpSellRangeList", dpSellRangeList);//销售范围
		
		return new ModelAndView("/dredge/dredgeProductView");
	}
	
	/**
	 * 上门服务商品更新
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/productUpdate.html")
	@Transactional
	public ModelAndView productUpdate(HttpServletRequest request) throws IllegalStateException, IOException {
		// 1.搜集参数
		ProductAddNewParamter p = new ProductAddNewParamter();
		p.setId(ParamUtils.getBigInteger(request, "id", null));
		p.setPrdtName(ParamUtils.getString(request, "prdtName"));
		p.setDesc(ParamUtils.getString(request, "desc"));
		p.setDt2Id(ParamUtils.getBigInteger(request, "dt2Id", null));
		p.setSsId(ParamUtils.getBigInteger(request, "ssId", null));
		p.setPayType(ParamUtils.getInt(request, "payType", 2));//"1":"服务前付款","2":"服务后付款"}
		String prdtSpecJson = request.getParameter("prdtSpecJson");
		if (prdtSpecJson != null) {
			p.setPrdtSpecList(JSON.parseArray(prdtSpecJson, DredgeProductSpecification.class));
		}
		String[] prdtPicHistory = request.getParameterValues("prdtPicHistory");
		StringBuilder prdtPicHistoryStringBuilder = new StringBuilder();
		for (int i = 0; prdtPicHistory != null && i < prdtPicHistory.length; i++) {
			int j = prdtPicHistory[i].lastIndexOf("/");
			prdtPicHistoryStringBuilder.append(prdtPicHistory[i].substring(j + 1) + ";");
		}
		p.setPrdtPics(prdtPicHistoryStringBuilder.toString());

		String[] introducePicHistory = request.getParameterValues("introducePicHistory");
		StringBuilder introducePicHistroySB = new StringBuilder();
		for (int i = 0; introducePicHistory != null && i < introducePicHistory.length; i++) {
			int j = introducePicHistory[i].lastIndexOf("/");
			introducePicHistroySB.append(introducePicHistory[i].substring(j + 1) + ";");
		}
		p.setIntroducePics(introducePicHistroySB.toString());
		
		List<MultipartFile> prdtPicList = ((MultipartHttpServletRequest) request).getFiles("prdtPic");
		List<MultipartFile> introducePicList =  ((MultipartHttpServletRequest) request).getFiles("introducePic");
		p.setSellRange(ParamUtils.getInt(request, "sellRange", 1));//1 == all country
//		p.setCityIds(ParamUtils.getLongs(request, "cityId", -1));
		p.setGbIds(ParamUtils.getBigIntegerList(request, "gbId", -1));
		p.setBlockIds(ParamUtils.getBigIntegerList(request, "blockId", -1));
		
		//share...
		List<MultipartFile> sharePicList =  ((MultipartHttpServletRequest) request).getFiles("sharePic");
		p.setShareFridendTitle(ParamUtils.getString(request, "shareFridendTitle"));
		p.setShareTimelineTitle(ParamUtils.getString(request, "shareTimelineTitle"));
		List<MultipartFile> sharePushPicList =  ((MultipartHttpServletRequest) request).getFiles("sharePushPic");
		p.setShareContent(ParamUtils.getString(request, "shareContent"));
		
		// 2.交互
		if(p.getPrdtName() == null) {
			throw new ValidateRuntimeException("商品名称不能为空！");
		}
		if(p.getDesc() == null) {
			throw new ValidateRuntimeException("商品描述不能为空！");
		}
		/*
		if(p.getDt2Id() == null) {
			throw new ValidateRuntimeException("产品大类不能为空！");
		}
		 * if(p.getSellRange() == 2 &&
				p.getCityIds().size() == 0) {
			throw new ValidateRuntimeException("销售范围为城市时，传入城市不能为空！");
		}
		if(p.getSellRange() == 3 &&
				p.getGbIds().size() == 0) {
			throw new ValidateRuntimeException("销售范围为小区时，传入小区不能为空！");
		}*/
		
		// 3.结果处理
		int updCount =  dredgeService.updateDredgeProduct(p, prdtPicList, introducePicList, sharePicList, sharePushPicList);
		advertiseForOmsService.dealServiceArea(p.getSellRange(), p.getId(), null, p.getBlockIds(), p.getGbIds(), OperationDict.OperationSaHasEbSupply_type.t_dredge_product);
		return new ModelAndView("redirect:/dredge/productList.html");
	}
	
	/**
	 * 上门服务商品编辑
	 * @param request
	 * @return
	 */
	@RequestMapping("/productEdit.html")
	public ModelAndView productEdit(HttpServletRequest request) {
		// 1.搜集参数
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		// 2.交互
		DredgeProductView dp = dredgeService.qryDredgeProductDetail(id);
		// 3.结果处理
		request.setAttribute("dp", dp);//取上门服务类
		request.setAttribute("cstTypeList", communitySupplyManager.getCommunitySupplyTypeList(null));//取上门服务类
		request.setAttribute("ssList", serviceSupplierBaseService.getServiceSupplierByCondition(null));//服务供应商
		getDredgeProductArea(id, request, true);
		
		return new ModelAndView("/dredge/dredgeProductEdit");
	}

	/**
	 * 上门服务商品复制.
	 * @param request
	 * @return
	 */
	@RequestMapping("/productCopy.html")
	public ModelAndView productCopy(HttpServletRequest request) {
		// 1.搜集参数
		BigInteger id = ParamUtils.getBigInteger(request, "id", null);
		// 2.交互
		DredgeProductView dp = dredgeService.qryDredgeProductDetail(id);
		// 3.结果处理
		request.setAttribute("dp", dp);//取上门服务类
		request.setAttribute("cstTypeList", communitySupplyManager.getCommunitySupplyTypeList(null));//取上门服务类
		request.setAttribute("ssList", serviceSupplierBaseService.getServiceSupplierByCondition(null));//服务供应商
		getDredgeProductArea(id, request, true);

		return new ModelAndView("/dredge/dredgeProductCopy");
	}

	private void getDredgeProductArea(BigInteger dredgeProductId, HttpServletRequest request, boolean isUpdate) {
		// 商品范围
		List<Map<String, Object>> areas = advertiseForOmsService.getAdvAreaByAdvId(dredgeProductId, OperationDict.OperationSaHasEbSupply_type.t_dredge_product);
		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(new HashMap<String, Object>());
		request.setAttribute("blockList", blockList);
		List<GroupBuildingSimpleEntity> gbList = advertiseForOmsService.getGbs();
		request.setAttribute("gbList", gbList);
		if (!DataUtil.isEmpty(areas)) {
			Map<String, Object> area = areas.get(0);
			if (area.get("gbId") != null && !"".equals(area.get("gbId")) && !"0".equals(area.get("gbId"))) {// 小区
				request.setAttribute("areaType", 3);
				if(isUpdate){
					// 已选小区处理
					for (Map<String, Object> areaItem:areas){
						Object gbIdTmp = areaItem.get("gbId");
						for (GroupBuildingSimpleEntity gb:gbList) {
							if (gbIdTmp!=null && gb.getId()!=null && gbIdTmp.toString().equals(gb.getId().toString())) {
								gb.setIsPushed("yes");
								break;
							}
						}
					}
					request.setAttribute("gbList", gbList);
				}
			} else if (area.get("cityId") != null && !"".equals(area.get("cityId")) && !"0".equals(area.get("cityId"))) {// 城市（兼容老数据）

				if(isUpdate){
					// 已选城市对应的小区处理（兼容旧数据）
					for (Map<String, Object> areaItem:areas){
						Object blockId = areaItem.get("blockId");
						for (GroupBuildingSimpleEntity gb:blockList) {
							if (blockId!=null && gb.getBlockId()!=null && blockId.toString().equals(gb.getBlockId().toString())) {
								gb.setIsPushed("yes");
							}
						}
					}
				} else {
					// 已选城市对应的小区处理（兼容旧数据）
					List<Map<String, Object>> areaGbs = new ArrayList<Map<String, Object>>();
					for (Map<String, Object> areaItem:areas){
						Object gbIdTmp = areaItem.get("cityId");
						for (GroupBuildingSimpleEntity gb:gbList) {
							if (gbIdTmp!=null && gb.getCityId()!=null && gbIdTmp.toString().equals(gb.getCityId().toString())) {
								Map<String, Object> areaTmp = new HashMap<String, Object>();
								areaTmp.put("gbName", gb.getName());
								areaGbs.add(areaTmp);
							}
						}
					}
					areas = areaGbs;
				}
				request.setAttribute("blockList", blockList);
				request.setAttribute("areaType", 2);
			} else {// 全国
				request.setAttribute("areaType", 1);
			}
		} else {
			request.setAttribute("areaType", 1);
		}
		request.setAttribute("areas", areas);
	}
	
	/**
	 * 上门服务商品新建保存
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/productSave.html")
	public ModelAndView productAddNewSave(HttpServletRequest request) throws IllegalStateException, IOException {
		// 1.搜集参数
		ProductAddNewParamter p = new ProductAddNewParamter();
		p.setPrdtName(ParamUtils.getString(request, "prdtName"));
		p.setDesc(ParamUtils.getString(request, "desc"));
		p.setDt2Id(ParamUtils.getBigInteger(request, "dt2Id", null));
		p.setSsId(ParamUtils.getBigInteger(request, "ssId", null));
		p.setPayType(ParamUtils.getInt(request, "payType", 2));//"1":"服务前付款","2":"服务后付款"}
		String prdtSpecJson = request.getParameter("prdtSpecJson");
		if (prdtSpecJson != null) {
			p.setPrdtSpecList(JSON.parseArray(prdtSpecJson, DredgeProductSpecification.class));
		}
		List<MultipartFile> prdtPicList = ((MultipartHttpServletRequest) request).getFiles("prdtPic");
		List<MultipartFile> introducePicList =  ((MultipartHttpServletRequest) request).getFiles("introducePic");
		p.setSellRange(ParamUtils.getInt(request, "sellRange", 1));//1 == all country
		p.setBlockIds(ParamUtils.getBigIntegerList(request, "blockId", -1));
		p.setGbIds(ParamUtils.getBigIntegerList(request, "gbId", -1));
		
		//share...
		List<MultipartFile> sharePicList =  ((MultipartHttpServletRequest) request).getFiles("sharePic");
		p.setShareFridendTitle(ParamUtils.getString(request, "shareFridendTitle"));
		p.setShareTimelineTitle(ParamUtils.getString(request, "shareTimelineTitle"));
		List<MultipartFile> sharePushPicList =  ((MultipartHttpServletRequest) request).getFiles("sharePushPic");
		p.setShareContent(ParamUtils.getString(request, "shareContent"));
		
		// 2.交互
		if(p.getPrdtName() == null) {
			throw new ValidateRuntimeException("商品名称不能为空！");
		}
		if(p.getDesc() == null) {
			throw new ValidateRuntimeException("商品描述不能为空！");
		}
		if(p.getDt2Id() == null) {
			throw new ValidateRuntimeException("产品大类不能为空！");
		}
		if(p.getSellRange() == 2 &&
				p.getCityIds().size() == 0) {
			throw new ValidateRuntimeException("销售范围为城市时，传入城市不能为空！");
		}
		if(p.getSellRange() == 3 &&
				p.getGbIds().size() == 0) {
			throw new ValidateRuntimeException("销售范围为小区时，传入小区不能为空！");
		}
		
		// 3.结果处理
		BigInteger pdId = dredgeService.saveDredgeProduct(p, prdtPicList, introducePicList, sharePicList, sharePushPicList);
		
		advertiseForOmsService.dealServiceArea(p.getSellRange(), pdId, null, p.getBlockIds(), p.getGbIds(), EbSupplyType.t_dredge_product);
		
		return new ModelAndView("redirect:/dredge/productList.html");
	}

	/**
	 * 取消指定到家服务单
	 * @param dredgeBillId 服务单ID
	 * @return
	 */
	@RequestMapping("/cancel.html")
	@ResponseBody
	public JsonResponse cancelDredgeBill(BigInteger dredgeBillId) {
		JsonResponse json = new JsonResponse();
		DredgeBill dredgeBill = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
		if (dredgeBill.getBillType() == DredgeConstant.DredgeBillType.Dredge_Pay_First ||
				(dredgeBill.getStatus() != DredgeConstant.DredgeBill.DredgeBill_Status_Accepted
						&& dredgeBill.getStatus() != DredgeConstant.DredgeBill.DredgeBill_Status_Submited)) {
			json.setStatus("0001");
			json.setMessage("该订单暂不可取消");
			return json;
		}
		DredgeBill updBill = new DredgeBill();
		updBill.setId(dredgeBillId);
		updBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Canceled);
		dredgeBillBaseService.updateDredgeBill(updBill);
		return json;
	}

	/**
	 * 到家单改派
	 * @param dredgeBillId
	 * @param propertyRepairerId
	 * @param expectWorkTime
	 * @return
	 */
	@RequestMapping(value = "reAssignMaster.html")
	@ResponseBody
	public JsonResponse reAssignMaster(BigInteger dredgeBillId, BigInteger repairerId, String expectWorkTime) {
		if (dredgeBillId == null)
			throw new IllegalArgumentException("repairId can not be null");
		if (repairerId == null) {
			throw new IllegalArgumentException("propertyRepairerId can not be null");
		}
		DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
		JsonResponse json = new JsonResponse();
		if (db.getStatus() != DredgeConstant.DredgeBill.DredgeBill_Status_Accepted) {
			json.setStatus("0001");
			json.setMessage("订单当前状态不支持改派");
			return json;
		}
		DredgeBill dredgeBill = new DredgeBill();
		dredgeBill.setId(dredgeBillId);
		dredgeBill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
		dredgeBill.settWorkerFId(repairerId);
		dredgeBill.setAcceptTime(DateUtils.getCurrentDate());
		dredgeBill.setExpectWorkTime(expectWorkTime);
		dredgeBillBaseService.updateDredgeBill(dredgeBill);
		return json;
	}

	@RequestMapping("/confirmFinish.html")
	@ResponseBody
	public JsonResponse confirmFinish(BigInteger dredgeBillId, BigDecimal laborAmount, BigDecimal materialAmount) {
		dredgeService.oosConfirmFinish(dredgeBillId, laborAmount, materialAmount);
		return new JsonResponse();
	}

	@RequestMapping("/applyRefundInfo.json")
	@ResponseBody
	public JsonResponse getApplyRefundInfo(BigInteger dredgeBillId) {
		Map<String, Object> applyRefundInfo = dredgeService.getApplyRefundInfo(dredgeBillId);
		applyRefundInfo.put("payMethodDesc", EbuyDict.PAYMETHOD_MAP.get(applyRefundInfo.get("payMethod").toString()));
		JsonResponse json = new JsonResponse();
		json.putData("detail", applyRefundInfo);
		return json;
	}

}
