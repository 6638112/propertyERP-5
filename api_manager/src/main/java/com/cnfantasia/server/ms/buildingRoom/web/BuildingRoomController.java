package com.cnfantasia.server.ms.buildingRoom.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.pub.file.FileDownloadUtils;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.ms.buildingRoom.entity.BuildingEntity;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.groupBuilding.constant.GroupBuildingConstant;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleViewEntity;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.property.importer.entity.BuildingRoomProprietor;
import com.cnfantasia.server.ms.propertyManagement.entity.PropertyManagementEntity;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/buildingRoom")
public class BuildingRoomController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	private IBuildingRoomService buildingRoomService;
	private IGroupBuildingService groupBuildingService;
	
	@Resource
	private IBuildingBaseService buildingBaseService;

	public IBuildingRoomService getBuildingRoomService() {
		return buildingRoomService;
	}

	public void setBuildingRoomService(IBuildingRoomService buildingRoomService) {
		this.buildingRoomService = buildingRoomService;
	}

	public IGroupBuildingService getGroupBuildingService() {
		return groupBuildingService;
	}

	public void setGroupBuildingService(IGroupBuildingService groupBuildingService) {
		this.groupBuildingService = groupBuildingService;
	}
	

	private List<GroupBuildingSimpleViewEntity> gbList;

	/**
	 * 列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/buildingList.html")
	public ModelAndView list(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		handleListOrSearchBuilding(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/buildingRoom/buildingList");
		return modelAndView;
	}
	
	/**
	 * 查找
	 * @param request
	 * @return
	 */
	@RequestMapping("/buildingSearch.html")
	public ModelAndView search(HttpServletRequest request) {
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("managementName", request.getParameter("managementName"));//管理处
		paramMap.put("groupbuildingName", request.getParameter("groupbuildingName"));//小区
		paramMap.put("buildingName", request.getParameter("buildingName"));//楼栋
		paramMap.put("buildingCode", request.getParameter("buildingCode"));//楼栋编码
		paramMap.put("gbId", gbId);//小区id
		handleListOrSearchBuilding(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/buildingRoom/buildingList");
		modelAndView.addObject("gbId", gbId);
		return modelAndView;
	}
	
	/**
	 * 统一处理List和Search请求
	 * @param request
	 * @param paramMap
	 */
	private void handleListOrSearchBuilding(HttpServletRequest request, Map<String, Object> paramMap) {
		paramMap.put("adminId", CnfantasiaCommUtil.getCurrentUserId());
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		//总数量
		int resultSize = this.buildingRoomService.queryBuildingForCount(paramMap);
		request.setAttribute("resultSize", resultSize);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		//分页信息
		List<BuildingEntity> searchRestList = buildingRoomService.queryBuildingForList(curPageIndex, pageSize, paramMap, true);
		request.setAttribute("resList", searchRestList);
	}
	/**
	 * 楼栋管理-初始化编辑或添加楼栋信息
	 * @author huangzj 2015-05-29
	 * @param request
	 * @return
	 */
	@RequestMapping("/initBuilding.html")
	public ModelAndView initBuilding(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		//1.加载管理处信息
		List<PropertyManagementEntity> managements = CnfantasiaCommUtil.getPropertyManagementByCurrentUser();
		if(null!=managements && managements.size()>0){//不是物业公司的直接取管理处的信息
			request.setAttribute("managements", managements);
		}else{
			/*request.setAttribute(JSPConstants.OprtResult, "跳转失败，您当前没有物业公司或管理处的信息!");
			request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
			modelAndView.setViewName(JSPConstants.OprtSuccessPage);
			return modelAndView;*/
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//2.加载小区信息
		if(null==gbList){//加载省
			paramMap.put("adminId", UserContext.getOperId());
			paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
			gbList = groupBuildingService.selectGroupBuildingByOmsUser(paramMap);
		}
		request.setAttribute("gbList", gbList);
		//3.获取楼栋信息
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){//编辑使用当前单据的物业公司信息
			BuildingEntity entity = buildingRoomService.queryBuildingById(id);
			request.setAttribute("entity", entity);
		}
		//4.加载楼栋的房号信息
		/*if(!StringUtils.isEmpty(id)){
			paramMap.clear();
			paramMap.put("buildingId", id);
			List<RoomEntity> rooms = this.getBuildingRoomService().queryRoomForList(null, null, paramMap, false);
			request.setAttribute("roomList", rooms);
			request.setAttribute("roomSize", rooms.size());
		}*/
		modelAndView.setViewName("/buildingRoom/buildingEdit");
		return modelAndView;
	}
	
	/**
	 * 物业管理 新增 楼栋
	 * 
	 * @author huangzj 2015-05-29
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveBuilding.html")
	public ModelAndView saveBuilding(HttpServletRequest request) throws IOException{
		String id = request.getParameter("buildingId");//ID
		String name = request.getParameter("buildingName").trim();//名称
		String groupbuildingId = request.getParameter("groupbuildingId");//小区
		String code = request.getParameter("buildingCode");//小区
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tGroupBuildingFId", groupbuildingId);
		paramMap.put("sys0DelState", 0);
		paramMap.put("name", name);
		if (buildingBaseService.getBuildingCount(paramMap) > 0) {
			request.setAttribute(JSPConstants.OprtResult, "该小区已存在重复名称的楼栋，保存失败");
		}else{
			if (this.getBuildingRoomService().saveOrUpdateBuilding(id, groupbuildingId, name, code) > 0) {
				request.setAttribute(JSPConstants.OprtResult, "保存成功");
			} else {
				request.setAttribute(JSPConstants.OprtResult, "保存失败");
			}
		}
		

		request.setAttribute(JSPConstants.ToURL, "../buildingRoom/buildingList.html");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查看明细
	 * @param request
	 * @return
	 */
	@RequestMapping("/viewBuilding.html")
	public ModelAndView viewBuilding(HttpServletRequest request) {
		//1.获取楼栋信息
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){//编辑使用当前单据的物业公司信息
			BuildingEntity entity = buildingRoomService.queryBuildingById(id);
			request.setAttribute("entity", entity);
		}
		//2.加载楼栋的房号信息
		if(!StringUtils.isEmpty(id)){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("buildingId", id);
			List<RoomEntity> rooms = this.getBuildingRoomService().queryRoomForList(null, null, paramMap, false);
			request.setAttribute("roomList", rooms);
			request.setAttribute("roomSize", rooms.size());
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/buildingRoom/buildingView");
		return modelAndView;
	}
	
	/**
	 * 删除楼栋
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteBuilding.html")
	@ResponseBody
	public String deleteBuilding(String id) {
		if(this.buildingRoomService.deleteBuilding(id)>0){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}
	
	
	/**
	 * 楼栋房号管理-初始化批量导入房号信息
	 * @author huangzj 2015-05-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/roomManage.html")
	public ModelAndView roomManage(HttpServletRequest request) {
		String id = request.getParameter("building_id");
		//1.编辑使用当前单据的物业公司信息
		if(!StringUtils.isEmpty(id)){
			BuildingEntity entity = buildingRoomService.queryBuildingById(id);
			request.setAttribute("entity", entity);
			//2.加载楼栋的房号信息
			if(null!=entity){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("buildingId", id);
				paramMap.put("unitName", request.getParameter("unitName"));
				paramMap.put("roomNum", request.getParameter("roomNum"));
				paramMap.put("proprietorName", request.getParameter("proprietorName"));
				paramMap.put("proprietorPhone", request.getParameter("proprietorPhone"));
				List<RoomEntity> rooms = this.getBuildingRoomService().queryRoomForList(null, null, paramMap, false);
				request.setAttribute("roomList", rooms);
				request.setAttribute("roomSize", rooms.size());
			}
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/buildingRoom/roomList");
		return modelAndView;
	}
	
	/**
	 * 删除房号
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteRoom.html")
	@ResponseBody
	public String deleteRoom(String roomId, String ppId) {
		if(this.buildingRoomService.deleteRoom(roomId, ppId)>0){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}
	
	/**
	 * 导出楼栋房间业主信息模板
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exportTemplate.html")
	public ModelAndView exportTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String groupBuildingName = ParamUtils.getString(request, "groupBuildingName", null);
		if (groupBuildingName == null) {
			throw new BusinessRuntimeException("传入的小区名称不能为空");
		}

		String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_room_import_templateV521.xls");
		FileInputStream fin = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		wb.setSheetName(0, groupBuildingName);
		HSSFSheet sheet = wb.getSheetAt(0);
		sheet.getRow(0).getCell(0).setCellValue(groupBuildingName);
		String fileName = groupBuildingName + "-" + "房间号导入模板.xls";

		fileName = FileDownloadUtils.encodeFilename(fileName, request);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

		return null;
	}

	/**
	 * 批量导入[楼栋、房号、业主]信息 <br>
	 * 按小区批量导入楼栋房号业主信息 按模板导入楼栋、单元、房号、业主、物业费等信息
	 * <p>
	 * 1、如果重复导入同一门牌号信息，则该房间记录更新;<br>
	 * 2、同一门牌号，重复导入，没有的字段进行新增（即补充更新），有内容的字段进行不更新 <br>
	 * 3、物业费信息字段写入可兑换物业费粮票字段<br>
	 * 4、导入后，需做手机号匹配，如果匹配成功，则注册用户自动添加门牌且验证成功
	 * 
	 * @author wenfq 2015-9-9
	 */
	@RequestMapping("/importBuildingRoomProprietor.html")
	public ModelAndView importBuildingRoomProprietor(HttpServletRequest request) throws IOException {
		String resultInfo = "导入成功";
		if (!(request instanceof MultipartHttpServletRequest)) {
			resultInfo = "未接收到Excel文档";
		}

		BigInteger groupBuildingId = ParamUtils.getBigInteger(request, "groupBuildingId", null);
		if (groupBuildingId == null) {
			throw new BusinessRuntimeException("BuildingRoomController.importBuildingRoomProprietor.failed--groupBuildingId cant be null");
		}
		String groupBuildingName = ParamUtils.getString(request, "groupBuildingName", null);

		// 转型为Spring的MultipartHttpRequest(重点的所在)
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 根据前台的name名称得到上传的文件
		MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
		HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
		HSSFSheet sheet = wb.getSheetAt(0);
		//3.解析Excel行
		Set<BuildingRoomProprietor> brpSet = new HashSet<BuildingRoomProprietor>();
		Set<String> buildingSet = new HashSet<String>();
		//verifyExcelFile校验数据，校验结果放在resultInfo中
		resultInfo = verifyExcelFile(resultInfo, groupBuildingName, buildingSet, brpSet, sheet);

		if ("导入成功".equals(resultInfo)) {
			resultInfo = buildingRoomService.saveBuildingRoomProprietor(groupBuildingId, buildingSet, brpSet);
			if (resultInfo.contains("没有更新和插入数据"))
				request.setAttribute(JSPConstants.ToURL, "../groupBuilding/list.html");
			else
				//有导入数据
				request.setAttribute(JSPConstants.ToURL, "../propertyProprietor/list.html");
		} else {
			request.setAttribute(JSPConstants.ToURL, "../groupBuilding/list.html");
		}
		request.setAttribute(JSPConstants.OprtResult, resultInfo);
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	private final String[] columnHeadNames = new String[]{"楼栋号（必填）","单元号","房间号(必填）","房屋面积","管理费单价","每月物业管理费",
			"出售情况", "居住情况", "出租情况", "业主姓名", "手机号码", "身份证号", "业主姓名", "手机号码", "身份证号" };

	/**
	 * 校验Excel文件，返回校验结果
	 * 
	 * @author wenfq 2015-09-10
	 * @param resultInfo
	 *            提示哪一行必录项不能为空，哪一行数据重复
	 * @param brpSet
	 *            楼栋房间业主Set
	 * @param buildingSet
	 *            楼栋Set
	 * @param sheet
	 *            传入的Excel文档s
	 * @return
	 */
	private String verifyExcelFile(String resultInfo, String groupBuildingName, Set<String> buildingSet, Set<BuildingRoomProprietor> brpSet, HSSFSheet sheet) {
		if (!groupBuildingName.equals(sheet.getRow(0).getCell(0).getRichStringCellValue().getString())) {
			resultInfo = "上传的Excel中的小区名，与将要导入的小区不一致，请确定修改后再导入";
			return resultInfo;
		}
		
		for(int j = 0; j < columnHeadNames.length; j++){
			String cellValue = HSSFCellUtil.getStringValue(sheet.getRow(3).getCell(j));
			if(!columnHeadNames[j].equals(cellValue)){
				logger.info("the column " + j +"name is not " + columnHeadNames[j]);
				resultInfo = "上传的Excel中的没找到“" + columnHeadNames[j] + "”列，可能删除了模板的列或更改列的顺序";
				return resultInfo;
			}
		}

		int index_buildingName = 0; //楼栋
		int index_roomNumber = 2; //房号
		int index_managerFee = 5; //管理费

		if (sheet.getLastRowNum() <= 1) {
			resultInfo = "没有要导入的数据，请先完善Excel文件中的信息后再导入。";
		}

		for (int i = 4; i < sheet.getLastRowNum() + 1; i++) {
			try {
				if (sheet.getRow(i) == null) {
					//处理空行的情况，如果用户一行的必录信息没有录全不导入
					resultInfo = "不能导入，第" + (i + 1) + "行为空行，请先删除空行";
					continue;
				} else if (sheet.getRow(i).getCell(index_buildingName) == null
						|| StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(index_buildingName)))) {
					resultInfo = "不能导入，第" + (i + 1) + "行“楼栋”不能为空，请对该行记录进行完善或者删除";
					break;
				} else if (sheet.getRow(i).getCell(index_roomNumber) == null
						|| StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(index_roomNumber)))) {
					resultInfo = "不能导入，第" + (i + 1) + "行“房间号”不能为空，请对该行记录进行完善或者删除";
					break;
				} 

				BuildingRoomProprietor brp = new BuildingRoomProprietor();
				brp.setBuildingName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(index_buildingName)));
				buildingSet.add(brp.getBuildingName());
				if(sheet.getRow(i).getCell(1) == null){
					brp.setRoomUnit(null);
				}else if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)).trim())){
					brp.setRoomUnit(null);
				}else{
					brp.setRoomUnit(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)).trim());
				}

				brp.setRoomNumber(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(index_roomNumber)));
				brp.setRoomSize(sheet.getRow(i).getCell(3) == null ? null : HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(3)));
				brp.setRoomManagerPrice(sheet.getRow(i).getCell(4) == null ? null : HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(4)));
				brp.setManangeFee(HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(index_managerFee)));
				int j = 6;//出售情况
				brp.setSaleStatus(getCellSaleStatus(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++))));
				brp.setLivingStatus(getCellLivingStatus(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++))));
				brp.setLeaseStatus(getCellLeaseStatus(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++))));

				brp.setProprietorName1(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++)));
				brp.setProprietorPhone1(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++)));
				brp.setProprietorIdNumber1(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++)));

				brp.setProprietorName2(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++)));
				brp.setProprietorPhone2(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++)));
				brp.setProprietorIdNumber2(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(j++)));

				boolean isRepeated = !brpSet.add(brp);
				if (isRepeated) {
					resultInfo = "不能导入，第" + (i + 1) + "行数据重复";
					break;
				}
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
				resultInfo = e.getMessage();
			}
		}
		return resultInfo;
	}

	//1未出售; 2已出售; 3已交房
	private int getCellSaleStatus(String saleStatuString){
		if("未出售".equals(saleStatuString)){
			return 1;
		}else if("已出售".equals(saleStatuString)){
			return 2;
		}else if("已交房".equals(saleStatuString))
			return 3;

		return 0;
	}

	//居住情况: 1未入住; 2已入住'
	private int getCellLivingStatus(String livingStatusString){
		if("未入住".equals(livingStatusString)){
			return 1;
		}else if("已入住".equals(livingStatusString)){
			return 2;
		}

		return 0;
	}

	//出租情况: 1未出租；2已出租';
	private int getCellLeaseStatus(String leaseStatusString){
		if("未出租".equals(leaseStatusString)){
			return 1;
		}else if("已出租".equals(leaseStatusString)){
			return 2;
		}

		return 0;
	}

	/**
	 * 楼栋房号管理-保存批量导入房号信息
	 * 
	 * @author huangzj 2015-04-28
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping("/saveImportRooms.html")
	public ModelAndView saveImportRooms(HttpServletRequest request) throws IOException{
		String buildingId = request.getParameter("building_id");
		if(StringUtils.isEmpty(buildingId)){
			throw new BusinessRuntimeException("BuildingRoomController.saveImportRooms.failed");
		}
		try{
			List<RoomEntity> roomTmpList = new ArrayList<RoomEntity>();
			//2.解析Excel导入信息
			if (request instanceof MultipartHttpServletRequest) {
				// 转型为Spring的MultipartHttpRequest(重点的所在)
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 根据前台的name名称得到上传的文件
				MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
				HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
				HSSFSheet sheet = wb.getSheetAt(0);
				RoomEntity room;
				PropertyProprietor proprietor;
				//3.解析Excel行
				for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
					if (null == sheet.getRow(i)
							|| null==sheet.getRow(i).getCell(1)) {//处理空行的情况，如果用户一行的必录信息没有录全不导入
						continue;
					}
					room = new RoomEntity();
					proprietor = new PropertyProprietor();
					if(null!=sheet.getRow(i).getCell(0)){
						sheet.getRow(i).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						room.setUnitName(sheet.getRow(i).getCell(0).getStringCellValue());//单元号
					}
					sheet.getRow(i).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					room.setNumTail(sheet.getRow(i).getCell(1).getStringCellValue());//房号
					room.setNum(room.getUnitName()==null?"":room.getUnitName()+"-"+room.getNumTail());//房号
					room.settBuildingFId(CnfantasiaCommUtil.convert2BigInteger(buildingId));
					proprietor = new PropertyProprietor();
					if(null!=sheet.getRow(i).getCell(2)){
						sheet.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						proprietor.setName(sheet.getRow(i).getCell(2).toString());//户名
					}
					if(null!=sheet.getRow(i).getCell(3)){
						sheet.getRow(i).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
						proprietor.setPhone(sheet.getRow(i).getCell(3).getStringCellValue());//电话
					}
					if(null!=sheet.getRow(i).getCell(4)){
						sheet.getRow(i).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						proprietor.setIdentifyNo(sheet.getRow(i).getCell(4).getStringCellValue());//身份证
					}
					room.setProprietor(proprietor);
					roomTmpList.add(room);
				}
			}
			if(roomTmpList.isEmpty()){
				request.setAttribute("alertMsg", "Excel导入数据为空，请检查后再导入");
			}else{
				if(this.getBuildingRoomService().saveImportRooms(roomTmpList)>0){
					request.setAttribute("alertMsg", "导入成功");
				}else{
					request.setAttribute("alertMsg", "导入失败");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("building_id", buildingId);
		return this.roomManage(request);
	}
	
	/**
	 * 楼栋房号管理-初始化房号信息
	 * @author huangzj 2015-05-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/initRoom.html")
	public ModelAndView initRoom(HttpServletRequest request) {
		String id = request.getParameter("id");
		RoomEntity entity = null;
		if(StringUtils.isEmpty(id)){
			entity = new RoomEntity();
			entity.setGroupbuildingName(request.getParameter("gbName"));
			entity.setBuildingName(request.getParameter("buildingName"));
			entity.settBuildingFId(CnfantasiaCommUtil.convert2BigInteger(request.getParameter("buildingId")));
		}else{
			entity = this.getBuildingRoomService().queryRoomById(id);
		}
		ModelAndView modelAndView = new ModelAndView();
		request.setAttribute("entity", entity);
		modelAndView.setViewName("/buildingRoom/roomEdit");
		return modelAndView;
	}
	/**
	 * 小区审核-保存化审核信息
	 * @author huangzj 2015-04-28
	 * @param request
	 * @return
	 */
	@RequestMapping("/saveRoom.html")
	@Transactional
	public ModelAndView saveRoom(HttpServletRequest request) throws IOException{
		String id = request.getParameter("roomId");//ID
		
		String buildingId = request.getParameter("buildingId");//楼栋
		String unitName = request.getParameter("unitName");//单元号
		String rommNum = request.getParameter("rommNum");//房间号
		String proprietorId = request.getParameter("proprietorId");//户名
		String proprietorName = request.getParameter("proprietorName");//住户类型
		String proprietorTel = request.getParameter("proprietorTel");//联系房号
		String proprietorIdentifyNo = request.getParameter("proprietorIdentifyNo");//身份证
		String roomDesc = request.getParameter("roomDesc");//描述
		
		if(this.buildingRoomService.saveOrUpdateRoom(id, buildingId, unitName, 
					rommNum, proprietorId, proprietorName, proprietorTel, proprietorIdentifyNo, roomDesc)>0){
			request.setAttribute(JSPConstants.OprtResult, "保存成功");
		}else{
			request.setAttribute(JSPConstants.OprtResult, "保存失败");
		}
		
		request.setAttribute(JSPConstants.ToURL, "../buildingRoom/roomManage.html?building_id="+buildingId);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(JSPConstants.OprtSuccessPage);
		return modelAndView;
	}
	
	/**
	 * 查询楼栋
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/queryGroupBuilding.html")
	@ResponseBody
	public String queryGroupBuilding(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", request.getParameter("id"));
		paramMap.put("pmName", request.getParameter("mgtName"));
		paramMap.put("gbName",request.getParameter("gbName"));
		IGroupBuildingService groupBuildingService = (IGroupBuildingService)CnfantasiaCommUtil.getBeanManager("msGroupBuildingService");
		paramMap.put("adminId", CnfantasiaCommUtil.getCurrentUserId());
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("auditStatus", GroupBuildingConstant.Check_Status.CS1);
		paramMap.put("_begin", 0);
		paramMap.put("_length", 10);
		List<GroupBuildingSimpleEntity> list = groupBuildingService.selectGroupBuildingForDialogList(paramMap);
		return JSON.toJSONString(list);
	}
}
