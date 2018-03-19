package com.cnfantasia.server.ms.propertyProprietor.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoom.service.IRealRoomBaseService;
import com.cnfantasia.server.domainbase.room.service.IRoomBaseService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.propertyProprietor.service.IPropertyProprietorService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/propertyProprietor")
public class PropertyProprietorController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPropertyProprietorService propertyProprietorService;

	public void setPropertyProprietorService(IPropertyProprietorService propertyProprietorService) {
		this.propertyProprietorService = propertyProprietorService;
	}

	@Resource
	IRealRoomBaseService realRoomBaseService;
	
	@Resource
	IRoomBaseService roomBaseService;

	@Resource
	IGroupBuildingService msGroupBuildingService ;

	@Resource
	IBuildingBaseService buildingBaseService;

	/**
	 * 业主列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppList");
		return modelAndView;
	}

	/**
	 * 统一处理List和Search请求
	 * 
	 * @param request
	 * @param paramMap
	 *            请求参数
	 */
	private void handleListOrSearch(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("omsUserId", UserContext.getOperId());
		paramMap.put("isadmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());

		int saleStatus = ParamUtils.getInt(request, "saleStatus", -1);
		if(saleStatus > 0)
			paramMap.put("saleStatus", saleStatus);

		int livingStatus = ParamUtils.getInt(request, "livingStatus", -1);
		if(livingStatus > 0)
			paramMap.put("livingStatus", livingStatus);

		int leaseStatus = ParamUtils.getInt(request, "leaseStatus", -1);
		if(leaseStatus > 0)
			paramMap.put("leaseStatus", leaseStatus);

		int resultSize = propertyProprietorService.getPPList_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<PropertyProprietorEntity> searchRestList = propertyProprietorService.getPPList_byUserId_forPage(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 列出小区下的楼栋
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getBuildingListByGbId.html")
	@ResponseBody
	public String getBuildingListByGbId(HttpServletRequest request) {
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGroupBuildingFId", gbId);
		paramMap.put("sys0DelState", 0);
		paramMap.put("checkStatus", 1);

		List<Building> buildingList1 = buildingBaseService.getBuildingByCondition(paramMap);
		paramMap.put("checkStatus", 9);
		List<Building> buildingList9 = buildingBaseService.getBuildingByCondition(paramMap);
		buildingList1.addAll(buildingList9);
		
		return JSON.toJSONString(buildingList1);
	}

	/**
	 * 新增房号和业主信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addNew.html")
	public ModelAndView addNew(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", CnfantasiaCommUtil.getCurrentUserId());
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		List<GroupBuildingSimpleEntity> gbSimpleList = msGroupBuildingService.selectGroupBuildingForDialogList(paramMap);

		request.setAttribute("gbList", gbSimpleList);
		return new ModelAndView("/propertyProprietor/ppAddNew");
	}

	/**
	 * 新增房号和业主信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveAddNew.html")
	public ModelAndView saveAddNew(HttpServletRequest request) {
		String resultInfo = "保存成功";
		
		BigInteger buildingId = ParamUtils.getBigInteger(request, "buildingId", null);
		String unitName = ParamUtils.getString(request, "unitName",null);
		String realRoomNum = ParamUtils.getString(request, "realRoomNum",null);
		String ppName = ParamUtils.getString(request, "ppName", null);
		String ppIdentifyNo = ParamUtils.getString(request, "ppIdentifyNo",null);
		String ppPhone = ParamUtils.getString(request, "ppPhone",null);
		double roomSize = ParamUtils.getDouble(request, "roomSize", 0);
		double roomManagerPrice = ParamUtils.getDouble(request, "roomManagerPrice", 0);
		Long manangeFee = PriceUtil.multiply100(ParamUtils.getDouble(request, "manangeFee", 0));

		RealRoom rr = new RealRoom();
		rr.setUnitName(unitName);
		rr.setNumTail(realRoomNum);
		if (!StringUtils.isEmpty(unitName)) {
			rr.setNum(unitName + "-" + realRoomNum);
		} else {
			rr.setNum(realRoomNum);
		}
		rr.setRoomSize(roomSize);
		rr.setPropertyFeePerM2(roomManagerPrice);
		rr.setPropMoney(manangeFee);
		rr.settBuildingFId(buildingId);
		rr.setCheckStatus(RoomDict.CheckStatus.WuXuShenHe);

		PropertyProprietor pp = new PropertyProprietor();
		pp.setIdentifyNo(ppIdentifyNo);
		pp.setName(ppName);
		pp.setPhone(ppPhone);

		resultInfo = propertyProprietorService.saveAddNew(rr, pp);

		request.setAttribute("result", resultInfo);

		return new ModelAndView("/propertyProprietor/ppOprtSuccess");
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		String rrId = request.getParameter("rrId");
		PropertyProprietorEntity ppEntity = propertyProprietorService.getPropertyProprietorByRoomId(new BigInteger(rrId));
		request.setAttribute("ppEntity", ppEntity);

		List<PropertyProprietor> ppList = propertyProprietorService.getPropertyProprietorListByRoomId(new BigInteger(rrId));
		request.setAttribute("ppList", ppList);//业主

		List<PropertyLessee> plList = propertyProprietorService.getPropertyLesseeListByRoomId(new BigInteger(rrId));
		request.setAttribute("plList", plList);//租户

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppEdit");
		return modelAndView;
	}

	/**
	 * 删除房间
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoom.html")
	@ResponseBody
	public String deleteRoom(HttpServletRequest request) {
		BigInteger rrId = ParamUtils.getBigInteger(request, "rrId", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tRealRoomFId", rrId);
		paramMap.put("sys0DelState", 0);

		if (roomBaseService.getRoomCount(paramMap) > 0) {
			return "该房间下已有用户注册，不允许删除";
		}

		int deleteCount = realRoomBaseService.deleteRealRoomLogic(rrId);

		return deleteCount == 1 ? "true" : "false";
	}


	/**
	 * 编辑保存房间信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView saveEdit(HttpServletRequest request) {
		String id = request.getParameter("ppId");

		BigInteger buildingId = ParamUtils.getBigInteger(request, "buildingId", null);
		BigInteger rrId = ParamUtils.getBigInteger(request, "rrId", null);
		String unitName = request.getParameter("unitName"); // 单元号
		String roomNumber = request.getParameter("realRoomNum"); // 房间号
		double roomSize = ParamUtils.getDouble(request, "roomSize", 0);
		double roomManagerPrice = ParamUtils.getDouble(request, "roomManagerPrice", 0);
		/** 出售情况 */
		Integer saleStatus = ParamUtils.getInt(request, "saleStatus", 0);
		/** 居住情况 */
		Integer livingStatus = ParamUtils.getInt(request, "livingStatus", 0);
		/** 出租情况 */
		Integer leaseStatus = ParamUtils.getInt(request, "leaseStatus", 0);

		RealRoom rr = new RealRoom();
		rr.setId(rrId);
		rr.settBuildingFId(buildingId);
		rr.setUnitName(unitName);
		if (!StringUtils.isEmpty(unitName)) {
			rr.setNum(unitName + "-" + roomNumber);
		} else {
			rr.setNum(roomNumber);
		}
		rr.setNumTail(roomNumber);
		rr.setRoomSize(roomSize);
		rr.setPropertyFeePerM2(roomManagerPrice);
		rr.setSys0UpdUser(UserContext.getCurrUser().getId());
		rr.setSaleStatus(saleStatus);
		rr.setLivingStatus(livingStatus);
		rr.setLeaseStatus(leaseStatus);

		String resultInfo = propertyProprietorService.updatePropertyProprietor_and_realRoom(rr);
		propertyProprietorService.doAutoRoomValidateFromDB(rr);

		request.setAttribute("result", resultInfo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppOprtSuccess");
		return modelAndView;
	}

	/**
	 *保存业主信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/savePP.json")
	@ResponseBody
	public JsonResponse savePP(BigInteger rrId, PropertyProprietor pp) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger ppId = propertyProprietorService.savePropertyProprietor(rrId, pp);
		jsonResponse.setErrcode(ppId !=null ? "0000" : "0001");
		jsonResponse.setMessage(ppId != null ? "保存成功" : "保存失败");
		jsonResponse.putData("ppId", ppId);
		return jsonResponse;
	}

	/**
	 * 删除房间信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePP.json")
	@ResponseBody
	public JsonResponse deletePP(BigInteger rrId, BigInteger ppId) {
		JsonResponse jsonResponse = new JsonResponse();
		int updCount = propertyProprietorService.deletePropertyProprietor(rrId, ppId);
		jsonResponse.setStatus(updCount > 0 ? "0000" : "0001");
		jsonResponse.setMessage(updCount > 0 ? "删除成功" : "删除失败");
		return jsonResponse;
	}

	/**
	 *保存租户信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/savePL.json")
	@ResponseBody
	public JsonResponse savePL(BigInteger rrId, PropertyLessee pl) {
		JsonResponse jsonResponse = new JsonResponse();
		BigInteger plId = propertyProprietorService.savePropertyLessee(rrId, pl);
		jsonResponse.setErrcode(plId != null ? "0000" : "0001");
		jsonResponse.setMessage(plId != null ? "保存成功" : "保存失败");
		jsonResponse.putData("plId", plId);
		return jsonResponse;
	}

	/**
	 * 删除租户信息
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("/deletePL.json")
	@ResponseBody
	public JsonResponse deletePL(BigInteger rrId, BigInteger plId) {
		JsonResponse jsonResponse = new JsonResponse();
		int updCount = propertyProprietorService.deletePropertyLessee(rrId, plId);
		jsonResponse.setErrcode(updCount > 0 ? "0000" : "0001");
		jsonResponse.setMessage(updCount > 0 ? "保存成功" : "保存失败");
		return jsonResponse;
	}

	/**
	 * 导入
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importPP.html")
	public ModelAndView importRealRoomAndPP(HttpServletRequest request) throws Exception {
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);

			List<PropertyProprietorEntity> ppList = new ArrayList<PropertyProprietorEntity>();
			int startRow = 1; //从第2行（含）开始导入数据
			for (int i = startRow; i < sheet.getLastRowNum() + 1; i++) {
				PropertyProprietorEntity ppEntity = new PropertyProprietorEntity();
				ppEntity.setCityName(sheet.getRow(i).getCell(0).getRichStringCellValue().toString());//城市
				ppEntity.setGroupBuildingName(sheet.getRow(i).getCell(1).getRichStringCellValue().toString());//小区
				ppEntity.setBuildingName(sheet.getRow(i).getCell(2).getRichStringCellValue().toString());// 楼栋
				ppEntity.setRealRoomUnitName(sheet.getRow(i).getCell(3).getRichStringCellValue().toString());//房间单元号
				ppEntity.setRealRoomNum(sheet.getRow(i).getCell(4).getRichStringCellValue().toString());//房间号
				ppEntity.setContactNum(sheet.getRow(i).getCell(5).getRichStringCellValue().toString());// 合同号
				ppEntity.setName(sheet.getRow(i).getCell(6).getRichStringCellValue().toString());//业主姓名
				ppEntity.setIdentifyNo(sheet.getRow(i).getCell(7).getRichStringCellValue().toString());//身份证号
				ppEntity.setPhone(sheet.getRow(i).getCell(8).getRichStringCellValue().toString());//联系方式
				ppList.add(ppEntity);
			}

			//	result = payBillService.saveImportPayBill(payBills);
			result = propertyProprietorService.saveImportPPEntity(ppList);
		}

		request.setAttribute("result", result);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppOprtSuccess");
		return modelAndView;
	}

	/**
	 * 查找
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/search.html")
	public ModelAndView search(HttpServletRequest request) {
		String gbName = request.getParameter("gbName");
		String ppName = request.getParameter("ppName");
		String bName = request.getParameter("bName");
		String rrUnitName = request.getParameter("rrUnitName");
		String rrRoomNum = request.getParameter("rrRoomNum");
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbName", gbName);
		paramMap.put("ppName", ppName);
		paramMap.put("bName", bName);
		paramMap.put("rrUnitName", rrUnitName);
		paramMap.put("rrRoomNum", rrRoomNum);
		paramMap.put("gbId", gbId);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppList");
		modelAndView.addObject("gbId", gbId);
		return modelAndView;
	}
}
