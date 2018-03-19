package com.cnfantasia.server.ms.alterPeriod.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.alterPeriodService.service.AlterPeriodService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.file.FileDownloadUtils;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.alterPeriod.entity.AlterPeroidDetailWithItem;
import com.cnfantasia.server.commbusi.alterPeriod.entity.RoomAlterPeroidDetail;
import com.cnfantasia.server.commbusi.alterPeriod.service.LatefeeCalculateService;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodCfg.service.AlterPeriodCfgBaseService;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.service.IAlterPeriodDataDetailBaseService;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.service.AlterPeriodFeeItemBaseService;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.service.IAlterRoomHasFeeItemBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.GroupBuildingBaseService;
import com.cnfantasia.server.ms.alterPeriod.entity.AlterPeriodQryParam;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 选择周期   控制器
 * @author wenfq 2016-10-20 
 *
 */

@Controller
@RequestMapping("/alterPeriod")
public class AlterPeriodController extends BaseController {
	
	@Resource
	GroupBuildingBaseService groupBuildingBaseService; 
	
	@Resource
	AlterPeriodFeeItemBaseService alterPeriodFeeItemBaseService; 
	
	@Resource
	private IBuildingRoomService buildingRoomService;
	
	@Resource
	private AlterPeriodService alterPeriodService;

    @Resource
	AlterPeriodCfgBaseService alterPeriodCfgBaseService;
	
	@Resource
	private IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService;
	
	@Resource
	private IUuidManager uuidManager;

    @Resource
    private IAlterRoomHasFeeItemBaseService alterRoomHasFeeItemBaseService;
    
    @Resource
    LatefeeCalculateService latefeeCalculateService;
    
    /**
     * 常规收费项
     * @param request
     * @return
     */
    @RequestMapping("/commFeeTypes.html")
    public ModelAndView commFeeTypes(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("/alterPeriod/commFeeTypes");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
		paramMap.put("isPmUser", UserContext.getCurrUser().getIsPmUser());
		paramMap.put("adminId", UserContext.getOperId());

        //分页参数
        PageModel pageModel = getPageModel(request);
        String gbName = request.getParameter("gbName");
		paramMap.put("gbName", gbName);
		int total = alterPeriodService.getAlterPeriodCfgCount(paramMap);
        List<AlterPeriodCfg> resList = alterPeriodService.getAlterPeriodCfgList(paramMap, pageModel);

        mav.addObject("gbName", gbName);
        mav.addObject("total", total);
        mav.addObject("resList", resList);
        return mav;
    }

    /**
     * 数据详情 列表（有些数据动态获取，且分页统计可能出错，只能分多次查）
     * @param request
     * @return
     */
    @RequestMapping("/listDataDetail.html")
    public ModelAndView listDataDetail(AlterPeriodQryParam param, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/alterPeriod/alterPeriodDataDetailList");
        mav.addObject("param", param);
        if (param.getGbId() == null) {
            mav.addObject("resList", new ArrayList());
            mav.addObject("total", 0);
            return mav;
        }
        //查询小区信息
        mav.addObject("gbName", groupBuildingBaseService.getGroupBuildingBySeqId(param.getGbId()).getName());
        //分页数据
        PageModel pageModel = getPageModel(request);
        List<RoomAlterPeroidDetail> resList = alterPeriodService.getRoomAlterPeroidList(param.getGbId(), param.getbName(),
                param.getUnitName(), param.getRoomNumTail(), pageModel);
        int total = alterPeriodService.getRoomAlterPeroidCount(param.getGbId(), param.getbName(), param.getUnitName(), param.getRoomNumTail());

        //配置的费用类型
        AlterPeriodFeeItem feeItem = new AlterPeriodFeeItem();
        feeItem.settGbId(param.getGbId());
        List<AlterPeriodFeeItem> items = alterPeriodFeeItemBaseService.getAlterPeriodFeeItemByCondition(MapConverter.toMap(feeItem));
        Collections.reverse(items);

        //每个房间具体费用，必须单个查，不然分页不对
        for (RoomAlterPeroidDetail roomAlterPeroidDetail : resList) {
            AlterRoomHasFeeItem hasFeeItem = new AlterRoomHasFeeItem();
            hasFeeItem.settRealRoomId(roomAlterPeroidDetail.getRealRoomId());
            roomAlterPeroidDetail.setHasFeeItemList(alterRoomHasFeeItemBaseService.getAlterRoomHasFeeItemByCondition(MapConverter.toMap(hasFeeItem)));
        }

        mav.addObject("resList", resList);
        mav.addObject("total", total);
        mav.addObject("feeItems", items);
        return mav;
    }

    /**
     * 获取请求中的分页信息
     * @param request 请求
     * @return 分页信息对象
     */
    private PageModel getPageModel(HttpServletRequest request) {
        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名
        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);
        return new PageModel((curPageIndex)*pageSize, pageSize);
    }
	
	/**
	 * 下载数据导出模板 
	 * @param request
	 * @return
	 */
	@RequestMapping("/exportTemplate.html")
	public ModelAndView exportTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//String groupBuildingName = ParamUtils.getString(request, "gbId", null);
		BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
		if (gbId == null) {
			throw new BusinessRuntimeException("传入的小区ID不能为空");
		}
		
		GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
		

		String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_alterPeriodDataDetail_import_template.xls");
		FileInputStream fin = new FileInputStream(new File(filePath));
		HSSFWorkbook wb = new HSSFWorkbook(fin);
		wb.setSheetName(0, gb.getName());
		HSSFSheet sheet = wb.getSheetAt(0);
		sheet.getRow(0).getCell(0).setCellValue(gb.getName());
		
		 //获取动态配置项 ，并填充
		AlterPeriodFeeItem alterPeriodFeeItemQry = new AlterPeriodFeeItem();
		alterPeriodFeeItemQry.settGbId(gbId);
		alterPeriodFeeItemQry.setSys0DelState(0);
		
		
		List<AlterPeriodFeeItem> alterPeriodFeeItemList = alterPeriodFeeItemBaseService.getAlterPeriodFeeItemByCondition(MapConverter.toMap(alterPeriodFeeItemQry));
		Collections.reverse(alterPeriodFeeItemList);

		int lastColumnStartIndex = 5;
		for(int i = 0; i < alterPeriodFeeItemList.size(); i++){//动态填充配置项
			sheet.getRow(1).createCell(i + lastColumnStartIndex).setCellValue(alterPeriodFeeItemList.get(i).getName());
		}
		
		{//导出数据 
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("groupbuildingId", gbId);//导出小区
			List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
			int dataFromRowIndex = 2;
			for(int i = 0; i< rooms.size(); i++){
				RoomEntity room = rooms.get(i);
				HSSFRow row = sheet.createRow(i + dataFromRowIndex);
				row.createCell(0).setCellValue(room.getBuildingName());//楼栋
				row.createCell(1).setCellValue(room.getUnitName());//单元	
				row.createCell(2).setCellValue(room.getNumTail());//房号	
			}
		}
		
		String fileName = gb.getName() + "-" + "收费项基础数据导入模板.xls";
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
	 * 保存导入的数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/importDataDetail.html")
	public ModelAndView importDataDetail(HttpServletRequest request) throws Exception {
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			// 转型为Spring的MultipartHttpRequest(重点的所在)
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			// 根据前台的name名称得到上传的文件
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
//			int startRow = 2; //从第3行（含）开始导入数据
//			int feeDetailColumnStart = 3;//从第4列（含）开始，
			
			BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
			
			request.setAttribute(JSPConstants.ToURL, "../alterPeriod/listDataDetail.html?gbId=" + gbId);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("tGbId", gbId);
			paramMap.put("sys0DelState", 0);
			List<AlterPeriodCfg> alterPeriodCfgList = alterPeriodCfgBaseService.getAlterPeriodCfgByCondition(paramMap);
			List<AlterPeriodFeeItem> alterPeriodFeeItemList = alterPeriodFeeItemBaseService.getAlterPeriodFeeItemByCondition(paramMap);
			Collections.reverse(alterPeriodFeeItemList);
			if(alterPeriodCfgList.isEmpty()){
				result = "该小区还没有“选择周期配置”，不能导入";
				request.setAttribute(JSPConstants.OprtResult, result);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			
			GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
			if(!gb.getName().equals(HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0)))){
				result = "小区名称不正确，请确认模板是否正确";
				request.setAttribute(JSPConstants.OprtResult, result);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			
			List<AlterPeroidDetailWithItem> alterPeroidDetailWithItemList = new ArrayList<AlterPeroidDetailWithItem>();
			String verifyResult = verifyImportDataFormat(sheet, alterPeriodCfgList.get(0).getId(), alterPeriodFeeItemList, alterPeroidDetailWithItemList, gbId);
			
			if(!verifyResult.equals("通过校验")){
				request.setAttribute(JSPConstants.OprtResult,"校验失败，原因如下：\\r\\r"+ verifyResult);
				request.setAttribute(JSPConstants.ToURL, "../alterPeriod/listDataDetail.html?gbId=" + gbId);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			
			List<AlterPeriodDataDetail> addAlterWithItemList = new ArrayList<AlterPeriodDataDetail>();
			List<AlterPeriodDataDetail> updateAlterWithItemList = new ArrayList<AlterPeriodDataDetail>();
			List<AlterRoomHasFeeItem> addAlterItemList = new ArrayList<AlterRoomHasFeeItem>();
			List<AlterRoomHasFeeItem> updateAlterItemList = new ArrayList<AlterRoomHasFeeItem>();
			verifyResult = verifyImportDataFromDb(alterPeroidDetailWithItemList, addAlterWithItemList, updateAlterWithItemList, addAlterItemList, updateAlterItemList);
			if(!verifyResult.equals("通过校验")){
				request.setAttribute(JSPConstants.OprtResult,"校验失败，原因如下：\\r\\r"+ verifyResult);
				request.setAttribute(JSPConstants.ToURL, "../alterPeriod/listDataDetail.html?gbId=" + gbId);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			if(DataUtil.isEmpty(addAlterWithItemList) && DataUtil.isEmpty(addAlterItemList)) {
				request.setAttribute(JSPConstants.OprtResult,"校验失败，原因如下：\\r\\r"+ "没有任何新增数据！");
				request.setAttribute(JSPConstants.ToURL, "../alterPeriod/listDataDetail.html?gbId=" + gbId);
				return new ModelAndView(JSPConstants.OprtSuccessPage);
			}
			result = alterPeriodService.saveImportDataDetail(addAlterWithItemList, updateAlterWithItemList, addAlterItemList, updateAlterItemList);
			if ("导入成功".equals(result)) {
				latefeeCalculateService.calculateByGroupBuildingByThread(gbId);
			}
		}
		
		request.setAttribute(JSPConstants.OprtResult, result);
		
		return new ModelAndView(JSPConstants.OprtSuccessPage);
	}
	
	/**
	 * 是否空行
	 * @param sheet
	 * @param i
	 * @return
	 */
	private boolean isEmptyRow(HSSFSheet sheet, int i) {
		boolean isEmptyRow = false;
		if (sheet.getRow(i) == null) {//处理空行的情况，有可能用户没有删除空白行
			return true;
		}

		if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))
				&& StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)))
				&& StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))){
			// 小区	楼栋	单元	房号 都为空时，也认为是空行, 跳过不导入
			isEmptyRow = true;
		}
		
		return isEmptyRow;
	}
	
	/**
	 * 校验即将导入的数据的格式问题
	 * @param gbId 
	 * @param sheet 
	 * @return
	 */
	private String verifyImportDataFormat(HSSFSheet sheet, BigInteger tAlterPeriodCfgId, List<AlterPeriodFeeItem> alterPeriodFeeItemList, List<AlterPeroidDetailWithItem> roomAlterPeroidDetailList, BigInteger gbId){
		String resultInfo =  "";
		int itemSize = alterPeriodFeeItemList.size();
		if(itemSize == 0) {
			return "校验不通过,没有配置缴费项!";
		}
		
		Set<AlterPeroidDetailWithItem> alterDetailSet = new HashSet<AlterPeroidDetailWithItem>();
		Set<String> roomInfoSet = new HashSet<String>();
		for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
			if(isEmptyRow(sheet, i))
				return  "第" + (i + 1) + "行的数据不能为空！";

			try {
				AlterPeroidDetailWithItem alterDetail = new AlterPeroidDetailWithItem();
				alterDetail.settAlterPeriodCfgId(tAlterPeriodCfgId);
				alterDetail.setGbId(alterPeriodFeeItemList.get(0).gettGbId());
				alterDetail.setbName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)));
				alterDetail.setUnitName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)));
				alterDetail.setRoomNumTail(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)));
				
				if(!alterDetailSet.add(alterDetail)){
					return  "第" + (i + 1) + "行的房间号数据重复！";
				}

				String roomInfo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "-"+
						HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "-" +
						HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));

				if(!roomInfoSet.add(roomInfo)){
					return  "第" + (i + 1) + "行的房间号数据重复！";
				}
				Map<String, BigInteger> roomStrByGbIds = alterPeriodService.getRoomStrByGbId(gbId);
				BigInteger realRoomId = roomStrByGbIds.get(roomInfo);
				if(realRoomId == null) {
					return  "第"+(i+1)+"行的楼栋房号不存在！";
				}
				
				List<AlterRoomHasFeeItem> alterRoomHasFeeItemList = new ArrayList<AlterRoomHasFeeItem>();
				int dataStartColIndex = 5;//数据开始列
				for(int j = 0; j < itemSize; j++) {
					if(!alterPeriodFeeItemList.get(j).getName().equals(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(dataStartColIndex + j)))){
						return "Excel表头与配置项不同，请重新导出模板";
					}
					
					int emptyAmount = 0;
					for(int k=0; k < itemSize; k++){
						if(sheet.getRow(i).getCell(dataStartColIndex+k)==null 
								|| "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+k))))
						emptyAmount++;
					}
					if(emptyAmount == itemSize){
						return "第" + (i + 1) + "行的配置项至少要录入一个金额才能导入！";
					}
					
					double amount = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+j));
					AlterRoomHasFeeItem item = new AlterRoomHasFeeItem();
					item.settAlterPeriodFeeItemId(alterPeriodFeeItemList.get(j).getId());
					item.setAmount(DataUtil.isEmpty(NumberUtils.doubleM100ToLong(amount)) ? 0 : NumberUtils.doubleM100ToLong(amount));
					alterRoomHasFeeItemList.add(item);
				}
				alterDetail.setAlterRoomHasFeeItemList(alterRoomHasFeeItemList);
				
				//起始时间检验
				if(sheet.getRow(i).getCell(dataStartColIndex -2)==null){
					return "第" + (i + 1) + "行的 物业费起始时间 不能为空！";
				}
				alterDetail.setBillMonthStart(DateUtils.formatTime(sheet.getRow(i).getCell(dataStartColIndex -2).getDateCellValue()));
				
				//滞纳金起始检验
				if (sheet.getRow(i).getCell(dataStartColIndex - 1) != null) {
					alterDetail.setLatefeeStart(DateUtils.formatTime(sheet.getRow(i).getCell(dataStartColIndex - 1).getDateCellValue()));
				}
				
				roomAlterPeroidDetailList.add(alterDetail);
			} catch(Exception e) {
				e.printStackTrace();
				resultInfo += "第" + (i + 1) + "行数据格式检验错误！\\r";
			}
		}
		
		return StringUtils.isEmpty(resultInfo) ? "通过校验" : resultInfo;
	}

	/**
	 * 校验即将导入的数据与数据库已经有的数据有没有冲突,如房间号存不存在,收费项目和配置是否一致
	 * @param gbId 
	 * @param sheet 
	 * @return
	 */
	private String verifyImportDataFromDb(List<AlterPeroidDetailWithItem> importDetailItemList, 
							List<AlterPeriodDataDetail> addAlterWithItemList, List<AlterPeriodDataDetail> updateAlterWithItemList,
							List<AlterRoomHasFeeItem> addAlterItemList, List<AlterRoomHasFeeItem> updateAlterItemList){
		List<BigInteger> uuidList = uuidManager.getNextUuidBigInteger(SEQConstants.t_alter_period_data_detail, importDetailItemList.size());
		int uuidIndex = 0;
		
		String resultInfo = "";
		//existItemList为通过楼栋单元到数据库查询已经有的数据
		List<AlterPeroidDetailWithItem> existItemList = alterPeriodService.getAlterPeroidDetailWithItemList(importDetailItemList);
		
		//通过excel导入的数据importDetailItemList与数据库的数据existItemList比较得出新增及修改的数据
		for(AlterPeroidDetailWithItem importDetailItem : importDetailItemList) {
			if(existItemList.contains(importDetailItem)) {
				if(resultInfo.equals("")) {
					int index = existItemList.indexOf(importDetailItem);
					AlterPeroidDetailWithItem exitsDetailItem = existItemList.get(index);
					importDetailItem.settRealRoomId(exitsDetailItem.gettRealRoomId());

					//新增，从未导入过此房间数据
					if (exitsDetailItem.getBillMonthStart() == null) {
						importDetailItem.setId(uuidList.get(uuidIndex ++ ));
						addAlterWithItemList.add(importDetailItem);
						for(AlterRoomHasFeeItem alterRoomHasFeeItem : importDetailItem.getAlterRoomHasFeeItemList()) {
							alterRoomHasFeeItem.settRealRoomId(importDetailItem.gettRealRoomId());
							addAlterItemList.add(alterRoomHasFeeItem);
						}
					} else { //修改，曾经导入过此房间数据
						exitsDetailItem.setBillMonthStart(importDetailItem.getBillMonthStart());
						exitsDetailItem.setLatefeeStart(importDetailItem.getLatefeeStart());
						
						List<AlterRoomHasFeeItem> exitItemList = exitsDetailItem.getAlterRoomHasFeeItemList();
						List<AlterRoomHasFeeItem> importItemList = importDetailItem.getAlterRoomHasFeeItemList();
						for(AlterRoomHasFeeItem importItem : importItemList) {
							AlterRoomHasFeeItem exitItem = null;
							boolean useImport = true;
							for(int j = 0; j < exitItemList.size(); j++ ) {
								exitItem = exitItemList.get(j);
								if(exitItem.gettAlterPeriodFeeItemId().equals(importItem.gettAlterPeriodFeeItemId())) {
									exitItem.setAmount(importItem.getAmount());
									exitItemList.remove(j);
									updateAlterItemList.add(exitItem); //修改，没有变更，用旧的
									useImport = false;
									break;
								}
							}
							if(useImport) {
//								importItem.settAlterPeriodFeeItemId(exitsDetailItem.getId());
								importItem.settRealRoomId(importDetailItem.gettRealRoomId());
								addAlterItemList.add(importItem); //修改中的新增收费项，由于修改时，这个B属于新增的
							}
						}
						
//						for(AlterRoomHasFeeItem exitItem : exitItemList) {
//							AlterRoomHasFeeItem importItem = null;
//							boolean useImport = true;
//							for(int j = 0; j < importItemList.size(); j++ ) {
//								importItem = importItemList.get(j);
//								if(exitItem.gettAlterPeriodFeeItemId().equals(importItem.gettAlterPeriodFeeItemId())) {
//									exitItem.setAmount(importItem.getAmount());
//									importItemList.remove(j);
//									updateAlterItemList.add(exitItem); //修改，没有变更，用旧的
//									useImport = false;
//									break;
//								}
//							}
//							if(useImport) {
//								importItem.settAlterPeriodFeeItemId(exitsDetailItem.getId());
//								addAlterItemList.add(importItem); //修改中的新增收费项，由于修改时，这个B属于新增的
//							}
//						}
						updateAlterWithItemList.add(exitsDetailItem);
					}
				}
			} else {
				resultInfo += importDetailItem.getbName() +"-" + importDetailItem.getUnitName() +"-" + importDetailItem.getRoomNumTail() + "不存在！\\r";
			}
		}
		
		//检验通过则设置ID，如果校验都不通过，不保存数据库，可以不设置
		if(resultInfo.equals("")) {
			resultInfo = "通过校验";
			uuidList = uuidManager.getNextUuidBigInteger(SEQConstants.t_alter_room_has_fee_item, addAlterItemList.size());
			uuidIndex = 0;
			for(AlterRoomHasFeeItem addAlterItem : addAlterItemList) {
				addAlterItem.setId(uuidList.get(uuidIndex ++ ));
			}
		}
		return resultInfo;
	}
	
	/**
	 * 重置滞纳金，只需要传入ID，不需要传入其它的字段
	 * @param alterPeriodDataDetail
	 * @return
	 */
	@RequestMapping("/resetLatefeeAmount.json")
	@ResponseBody
	public JsonResponse resetLatefeeAmount(AlterPeriodDataDetail alterPeriodDataDetail) {
		JsonResponse json = new JsonResponse();
		if(alterPeriodDataDetail.getId() == null || alterPeriodDataDetail.getId().longValue() == 0L) {
			json.setMessage("id不能为空！");
			json.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			return json;
		}
		
		alterPeriodDataDetail.setLatefeeAmount(0L);
		alterPeriodDataDetailBaseService.updateAlterPeriodDataDetail(alterPeriodDataDetail);
		return json;
	}

    /**
     * 修改选择账期配置
     * @param alterPeriodDataDetail
     * @return
     */
    @RequestMapping("/updRoomAlterPeriod.json")
    @ResponseBody
    public JsonResponse updRoomAlterPeriod(HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        BigInteger alterPeriodDataId = ParamUtils.getBigInteger(request, "alterPeriodDataId", null);
        BigInteger realRoomId = ParamUtils.getBigInteger(request, "realRoomId", null);
        String billMonthStart = request.getParameter("billMonthStart");
		billMonthStart = StringUtils.isEmpty(billMonthStart) ? billMonthStart : billMonthStart + "-01";
		String latefeeStart = request.getParameter("latefeeStart");
        String[] hasFeeItemIds = request.getParameterValues("feeItems[]");

        if(alterPeriodDataId == null || realRoomId == null) {
            json.setMessage("该配置不存在！");
            json.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
            return json;
        }

        AlterPeriodDataDetail periodDataDetail = new AlterPeriodDataDetail();
        periodDataDetail.setId(alterPeriodDataId);
        periodDataDetail.setBillMonthStart(billMonthStart);
        periodDataDetail.setLatefeeStart(latefeeStart);
        List<AlterRoomHasFeeItem> hasFeeItemList = new ArrayList<AlterRoomHasFeeItem>(hasFeeItemIds.length);
        AlterRoomHasFeeItem hasFeeItem = null;
        for (String hasFeeItemId : hasFeeItemIds) {
            String value = request.getParameter(hasFeeItemId);
            if (!DataUtil.isEmpty(value)) {
                hasFeeItem = new AlterRoomHasFeeItem();
				hasFeeItem.settRealRoomId(realRoomId);
                hasFeeItem.settAlterPeriodFeeItemId(new BigInteger(hasFeeItemId));
                hasFeeItem.setAmount(BigDecimalUtil.mul100(new BigDecimal(value)).longValue());
                hasFeeItemList.add(hasFeeItem);
            }
        }
        alterPeriodService.updRoomAlterPeriod(periodDataDetail, hasFeeItemList);
        return json;
    }
}
