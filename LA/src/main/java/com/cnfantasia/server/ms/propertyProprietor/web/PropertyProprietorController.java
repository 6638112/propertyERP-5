package com.cnfantasia.server.ms.propertyProprietor.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;
import com.cnfantasia.server.ms.propertyProprietor.service.IPropertyProprietorService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;

@Controller
@RequestMapping("/propertyProprietor")
public class PropertyProprietorController extends BaseController{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPropertyProprietorService propertyProprietorService;

	public void setPropertyProprietorService(IPropertyProprietorService propertyProprietorService) {
		this.propertyProprietorService = propertyProprietorService;
	}

	/**
	 * 业主列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/list.html")
	public ModelAndView list(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
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
		int resultSize = propertyProprietorService.getPPList_byUserId_forCount(paramMap);
		request.setAttribute("resultSize", resultSize);

		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		List<PropertyProprietorEntity> searchRestList = propertyProprietorService.getPPList_byUserId_forPage(curPageIndex, pageSize, paramMap);

		request.setAttribute("resList", searchRestList);
	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		String id = request.getParameter("id");
		PropertyProprietorEntity ppEntity = propertyProprietorService.getPropertyProprietorByRoomId(new BigInteger(id));
		request.setAttribute("ppEntity", ppEntity);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppEdit");
		return modelAndView;
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) {
		String id = request.getParameter("ppId");
		String ppName = request.getParameter("ppName").trim();
		String ppIdentifyNo = request.getParameter("ppIdentifyNo").trim();
		String ppPhone = request.getParameter("ppPhone").trim();

		PropertyProprietorEntity ppEntity = new PropertyProprietorEntity();
		ppEntity.setId(new BigInteger(id));
		ppEntity.setName(ppName);
		ppEntity.setIdentifyNo(ppIdentifyNo);
		ppEntity.setPhone(ppPhone);
		ppEntity.setSys0UpdUser(UserContext.getCurrUser().getId());
		propertyProprietorService.updatePropertyProprietor_for_NameIdentfyNoPhone(ppEntity);

		request.setAttribute("result", "业主信息保存成功");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppOprtSuccess");
		return modelAndView;
	}

	/**
	 * 导入
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/importPP.html")
	public ModelAndView importPayBill(HttpServletRequest request) throws Exception {
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

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("omsUserId", UserContext.getCurrUser().getId());
		paramMap.put("gbName", gbName);
		paramMap.put("ppName", ppName);
		paramMap.put("bName", bName);
		paramMap.put("rrUnitName", rrUnitName);
		paramMap.put("rrRoomNum", rrRoomNum);

		handleListOrSearch(request, paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/propertyProprietor/ppList");
		return modelAndView;
	}
}
