package com.cnfantasia.server.ms.fixedFeeCfg.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cnfantasia.server.ms.fixedFeeCfg.entity.FixedFeeItemHasRoomEntity;
import com.cnfantasia.server.ms.fixedFeeCfg.entity.FixedFeeItemHasRoomParam;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.file.FileDownloadUtils;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.fixedFeeItem.service.IFixedFeeItemBaseService;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.ms.buildingRoom.entity.RoomEntity;
import com.cnfantasia.server.ms.buildingRoom.service.IBuildingRoomService;
import com.cnfantasia.server.ms.fixedFeeCfg.entity.FixedFeeDetailQryParam;
import com.cnfantasia.server.ms.fixedFeeCfg.entity.RoomFixedFeeItemDetail;
import com.cnfantasia.server.ms.fixedFeeCfg.service.FixedFeeCfgService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * @ClassName: FixedFeeCfgController
 * @Date: 2017-01-18 17:41
 * @Auther: yanghua
 * @Description:(固定收费项配置)
 */
@Controller
@RequestMapping("/fixedFeeCfg")
public class FixedFeeCfgController {

    @Resource
    private FixedFeeCfgService fixedFeeCfgService;
    @Resource
    private IFixedFeeItemBaseService fixedFeeItemBaseService;
    @Resource
    private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
    @Resource
    private IGroupBuildingBaseService groupBuildingBaseService;
    @Resource
    private IBuildingRoomService buildingRoomService;
    @Resource
    private IUuidManager uuidManager;

    /**
     * 查询配置了固定收费项的小区列表
     * @param request
     * @return
     */
    @RequestMapping("/getGroupBuildingList.html")
    public ModelAndView getGroupBuildingList(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/fixedFeeCfg/groupBuildingList");
        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("isAdmin", UserContext.getCurrUser().getIsadmin());
//        paramMap.put("isSubUser", UserContext.getCurrUser().getIsSubUser());
//        paramMap.put("adminId", UserContext.getCurrUser().getId());
        paramMap.put("gbIdList", UserContext.getGbIdList());

        //分页参数
        PageModel pageModel = getPageModel(request);
        String gbName = request.getParameter("gbName");
        paramMap.put("gbName", gbName);
        int total = fixedFeeCfgService.getGroupBuildingListCount(paramMap);
        List<HashMap<String, Object>> resList = fixedFeeCfgService.getGroupBuildingList(paramMap, pageModel);

        mav.addObject("gbName", gbName);
        mav.addObject("total", total);
        mav.addObject("resList", resList);

        return mav;
    }

    /**
     * 跳转到收费项配置页面
     * @param gbId
     * @return
     */
    @RequestMapping("jumpToCreateFeeDetail.html")
    public ModelAndView jumpToCreateFeeDetail(BigInteger gbId, String gbName, HttpServletRequest request, FixedFeeDetailQryParam param) {
        ModelAndView mav = new ModelAndView("/fixedFeeCfg/createFeeDetail");
        //分页数据
        PageModel pageModel = ControllerUtils.getPageModel(request,1,10);  
        List<RoomFixedFeeItemDetail> resList = fixedFeeCfgService.getRoomFixedFeeItemDetailList(param.getGbId(), param.getbName(),
                param.getUnitName(), param.getRoomNumTail(), pageModel);
        int total = fixedFeeCfgService.getRoomFixedFeeItemDetailCount(param.getGbId(), param.getbName(), param.getUnitName(), param.getRoomNumTail());
        
        //数据详情
        FixedFeeItem fixedFeeItem = new FixedFeeItem();
        fixedFeeItem.settGbId(param.getGbId());
        List<FixedFeeItem> items = fixedFeeItemBaseService.getFixedFeeItemByCondition(MapConverter.toMap(fixedFeeItem));
        Collections.reverse(items);

        //每个房间具体费用，必须单个查，不然分页不对
        for (RoomFixedFeeItemDetail roomFixedFeeItemDetail : resList) {
            FixedFeeItemHasRoom hasFeeItem = new FixedFeeItemHasRoom();
            hasFeeItem.settFixedFeeDataId(roomFixedFeeItemDetail.getFixedFeeItemHasRoomDataId());
            roomFixedFeeItemDetail.setHasFeeItemList(fixedFeeItemHasRoomBaseService.getFixedFeeItemHasRoomByCondition(MapConverter.toMap(hasFeeItem)));
        }

        mav.addObject("resList", resList);
        mav.addObject("total", total);
        mav.addObject("feeItems", items);
        mav.addObject("gbName", gbName);
        mav.addObject("gbId", gbId);
        pageModel.freshPageModel(resList.size(),total);
        mav.addObject("page", pageModel.toMap());

        return mav;
    }

    /**
     * 收费项保存,更新
     * @param request
     * @return
     */
    @RequestMapping("saveFeeItem.json")
    @ResponseBody
    public JsonResponse saveFeeItem(BigInteger gbId, String items) {
        JsonResponse jsonResponse = new JsonResponse();
        if ("[]".equals(items)) {
            jsonResponse.setMessage("收费项不能为空！操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        } else {
            boolean isSuccessed = fixedFeeCfgService.saveFeeItem(gbId, items);
            if(isSuccessed){
                jsonResponse.setMessage("操作成功！");
            } else {
                jsonResponse.setMessage("操作失败！");
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            }
        }
        return jsonResponse;
    }

    /**
     * 收费项删除
     * @param request
     * @return
     */
    @RequestMapping("delFeeItem.json")
    @ResponseBody
    public JsonResponse delFeeItem(BigInteger id) {
        JsonResponse jsonResponse = new JsonResponse();
        boolean isSuccessed = fixedFeeCfgService.delFeeItem(id);
        if(isSuccessed){
            jsonResponse.setMessage("操作成功！");
        } else {
            jsonResponse.setMessage("操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        }
        return jsonResponse;
    }

    /**
     * 修改收费项和房间的关联信息
     * @param itemHasRoomId
     * @return
     */
    @RequestMapping("/editFeeDetail.html")
    @ResponseBody
    public ModelAndView editFeeDetail(BigInteger dataId){
        ModelAndView modelAndView = new ModelAndView("/fixedFeeCfg/editFeeDetail");
        RoomFixedFeeItemDetail roomFixedFeeItemDetail = fixedFeeCfgService.getRoomFixedFeeItemDetailByDataId(dataId);

        //组装需要OOS增加的费用项列表
        List<FixedFeeItem> itemList = fixedFeeCfgService.getFixedFeeItemList(roomFixedFeeItemDetail.getGbId());
        for (FixedFeeItemHasRoomEntity fixedFeeItemHasRoomEntity : roomFixedFeeItemDetail.getEditFeeItemList()) {
            for (int i = itemList.size() - 1; i >= 0; i--) {
                FixedFeeItem fixedFeeItem = itemList.get(i);
                if(fixedFeeItemHasRoomEntity.gettFixedFeeItemId().equals(fixedFeeItem.getId())) {
                    itemList.remove(i);
                }
            }
        }
        modelAndView.addObject("entity", roomFixedFeeItemDetail);
        modelAndView.addObject("itemList", itemList);
        modelAndView.addObject("dataId", dataId);

        return modelAndView;
    }

    /**
     * 保存修改收费项和房间的关联信息
     * @param itemHasRoomId
     * @return
     */
    @RequestMapping("/updateFeeDetail.json")
    @ResponseBody
    public JsonResponse updateFeeDetail(String items, BigInteger realRoomId, BigInteger gbId){
        JsonResponse jsonResponse = new JsonResponse();
        if ("[]".equals(items)) {
            jsonResponse.setMessage("收费项金额不能为空！操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        } else {
            BigInteger userId = UserContext.getOperIdBigInteger();
            //FixedFeeItemHasRoomParam是为了处理JSON.parseArray转换时tFixedFeeItemId不能赋值：因为t字母后面是大写F，一般的属性getX,和setX所以无法转换
            List<FixedFeeItemHasRoomParam> fixedFeeItemHasRooms = JSON.parseArray(items, FixedFeeItemHasRoomParam.class);
            HashSet<BigInteger> fixedFeeItemIds = new HashSet<>();
            List<FixedFeeItemHasRoom> fixedFeeItemHasRoomsForUpdate = new ArrayList<>();
            List<FixedFeeItemHasRoom> fixedFeeItemHasRoomsForAdd = new ArrayList<>();
            boolean isReptItem = true;
            for (FixedFeeItemHasRoomParam fixedFeeItemHasRoomParam : fixedFeeItemHasRooms) {
                FixedFeeItemHasRoom fixedFeeItemHasRoom = new FixedFeeItemHasRoom();
                fixedFeeItemHasRoom.setId(fixedFeeItemHasRoomParam.getId());
                fixedFeeItemHasRoom.setTotalPrice(fixedFeeItemHasRoomParam.getTotalPrice());
                fixedFeeItemHasRoom.setPriceUnitValue(fixedFeeItemHasRoomParam.getPriceUnitValue());
                fixedFeeItemHasRoom.setSignalPrice(fixedFeeItemHasRoomParam.getSignalPrice());
                fixedFeeItemHasRoom.setBillMonthStart(fixedFeeItemHasRoomParam.getBillMonthStart());
                fixedFeeItemHasRoom.settFixedFeeItemId(fixedFeeItemHasRoomParam.getFixedFeeItemId());
                fixedFeeItemHasRoom.setSys0UpdUser(userId);
                if(!DataUtil.isEmpty(fixedFeeItemHasRoomParam.getFixedFeeItemId()) && DataUtil.isEmpty(fixedFeeItemHasRoomParam.getId())) {
                    isReptItem = fixedFeeItemIds.add(fixedFeeItemHasRoomParam.getFixedFeeItemId());
                    fixedFeeItemHasRoomsForAdd.add(fixedFeeItemHasRoom);
                } else {
                    fixedFeeItemHasRoomsForUpdate.add(fixedFeeItemHasRoom);
                }
            }
            if(!isReptItem) {
                jsonResponse.setMessage("新增费用项重复！");
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
                return jsonResponse;
            }

            boolean isSuccessed = fixedFeeCfgService.updateFeeDetail(fixedFeeItemHasRoomsForUpdate, fixedFeeItemHasRoomsForAdd, realRoomId, gbId, userId);
            if(isSuccessed){
                jsonResponse.setMessage("操作成功！");
            } else {
                jsonResponse.setMessage("操作失败！");
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            }
        }
        return jsonResponse;
    }

    /**
     * 删除门牌下的收费项及数据信息
     * @param ids
     * @param realRoomId
     * @param gbId
     * @return
     */
    @RequestMapping("/delFeeDetail.json")
    @ResponseBody
    public JsonResponse delFeeDetail(String ids, BigInteger realRoomId, BigInteger gbId){
        JsonResponse jsonResponse = new JsonResponse();
        if ("".equals(ids)) {
            jsonResponse.setMessage("请选择需要删除的收费项！操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        } else {
            BigInteger userId = UserContext.getOperIdBigInteger();
            String[] idsArr = ids.split(",");
            boolean isSuccessed = fixedFeeCfgService.delFeeDetail(idsArr, realRoomId, gbId, userId);
            if(isSuccessed){
                jsonResponse.setMessage("操作成功！");
            } else {
                jsonResponse.setMessage("操作失败！");
                jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            }
        }
        return jsonResponse;
    }

    /**
     * 查询收费项房间详情
     * @param gbId
     * @return
     */
    @RequestMapping("qryFeeDetail.html")
    public ModelAndView qryFeeDetail(BigInteger dataId) {
        ModelAndView mav = new ModelAndView("/fixedFeeCfg/qryFeeDetail");
        RoomFixedFeeItemDetail roomFixedFeeItemDetail = fixedFeeCfgService.getRoomFixedFeeItemDetailByDataId(dataId);
        mav.addObject("entity", roomFixedFeeItemDetail);
        return mav;
    }

    /**
     * 收费项房间关联信息删除
     * @param id
     * @return
     */
    @RequestMapping("delFixedFeeDetail.json")
    @ResponseBody
    public JsonResponse delFixedFeeDetail(BigInteger id) {
        JsonResponse jsonResponse = new JsonResponse();
        boolean isSuccessed = fixedFeeCfgService.delFixedFeeDetail(id);
        if(isSuccessed){
            jsonResponse.setMessage("操作成功！");
        } else {
            jsonResponse.setMessage("操作失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        }
        return jsonResponse;
    }

    /**
     * 下载数据导出模板
     * @param request
     * @return
     */
    @RequestMapping("/exportTemplate.html")
    public ModelAndView exportTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if (gbId == null) {
            throw new BusinessRuntimeException("传入的小区ID不能为空");
        }

        GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);

        String filePath = request.getSession().getServletContext().getRealPath("/docs/jfq_fixedItemDataDetail_import_template.xls");
        FileInputStream fin = new FileInputStream(new File(filePath));
        HSSFWorkbook wb = new HSSFWorkbook(fin);
        wb.setSheetName(0, gb.getName());
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.getRow(0).getCell(0).setCellValue(gb.getName());

        //获取动态配置项 ，并填充
        FixedFeeItem fixedFeeItem = new FixedFeeItem();
        fixedFeeItem.settGbId(gbId);
        fixedFeeItem.setSys0DelState(0);


        List<FixedFeeItem> fixedFeeItemList = fixedFeeItemBaseService.getFixedFeeItemByCondition(MapConverter.toMap(fixedFeeItem));
        Collections.reverse(fixedFeeItemList);//需求要求：配置项顺序与导入模板顺序一致
        
        int lastColumnStartIndex = 3;
        int mergeIndex = 0;//费用项所占列数
        HSSFRow row2 =sheet.createRow(2);
        for(int i = 0; i < fixedFeeItemList.size(); i++){//动态填充配置项
            HSSFRow row1 = sheet.getRow(1);
            if(row1==null){
                row1 = sheet.createRow(1);
            }
            row1.createCell(lastColumnStartIndex + mergeIndex).setCellValue(fixedFeeItemList.get(i).getName());
            Integer calculateType = fixedFeeItemList.get(i).getCalculateType();
            if(calculateType != null && (calculateType.equals(1) || calculateType.equals(3))) {//1：建筑面积*单价,2：指定金额
                //CellRangeAddress(起始行号，终止行号， 起始列号，终止列号）
                sheet.addMergedRegion(new CellRangeAddress(1, 1, lastColumnStartIndex+mergeIndex, lastColumnStartIndex+mergeIndex+3));
                row2.createCell(mergeIndex + lastColumnStartIndex).setCellValue("金额");
                row2.createCell(mergeIndex + lastColumnStartIndex + 1).setCellValue("单价");
                if(calculateType.equals(1)){
                	row2.createCell(mergeIndex + lastColumnStartIndex + 2).setCellValue("建筑面积");
                } else {
                	row2.createCell(mergeIndex + lastColumnStartIndex + 2).setCellValue("用量");
                }
                row2.createCell(mergeIndex + lastColumnStartIndex + 3).setCellValue("费用起始时间");
                mergeIndex += 4;
            } else {
            	sheet.addMergedRegion(new CellRangeAddress(1, 1, lastColumnStartIndex+mergeIndex, lastColumnStartIndex+mergeIndex+1));
                row2.createCell(mergeIndex + lastColumnStartIndex).setCellValue("金额");
                row2.createCell(mergeIndex + lastColumnStartIndex + 1).setCellValue("费用起始时间");
                mergeIndex += 2;
            }
        }

        {//导出数据
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("groupbuildingId", gbId);//导出小区
            List<RoomEntity> rooms = buildingRoomService.queryRoomForList(null, null, paramMap, false);
            int dataFromRowIndex = 3;
            for(int i = 0; i< rooms.size(); i++){
                RoomEntity room = rooms.get(i);
                HSSFRow row = sheet.createRow(i + dataFromRowIndex);
                row.createCell(0).setCellValue(room.getBuildingName());//楼栋
                row.createCell(1).setCellValue(room.getUnitName());//单元
                row.createCell(2).setCellValue(room.getNumTail());//房号
            }
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
        }

        HSSFCellStyle centerstyle = wb.createCellStyle();
        centerstyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER); // 居中

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
     * 导入数据
     * @param id
     * @return
     */
    @RequestMapping("importFeeDetail.html")
    public ModelAndView importFeeDetail(HttpServletRequest request) throws Exception {
        String result = "导入成功";
        if (request instanceof MultipartHttpServletRequest) {
            // 转型为Spring的MultipartHttpRequest(重点的所在)
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 根据前台的name名称得到上传的文件
            MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
            HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);

            BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
            String gbName = ParamUtils.getString(request, "gbName", null);

            request.setAttribute(JSPConstants.ToURL, "../fixedFeeCfg/jumpToCreateFeeDetail.html?gbId="+gbId+"&gbName="+ gbName);

            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("tGbId", gbId);
            paramMap.put("sys0DelState", 0);
            if(buildingRoomService.getBuildingAndRoomCountByGbId(gbId) == null || buildingRoomService.getBuildingAndRoomCountByGbId(gbId) == 0) {
                result = "该小区还没有楼栋房号信息，不能导入";
                request.setAttribute(JSPConstants.OprtResult, result);
                return new ModelAndView(JSPConstants.OprtSuccessPage);
            }
            List<FixedFeeItem> fixedFeeItemList = fixedFeeItemBaseService.getFixedFeeItemByCondition(paramMap);
            if(fixedFeeItemList.isEmpty()){
                result = "该小区还没有“配置收费项”，不能导入";
                request.setAttribute(JSPConstants.OprtResult, result);
                return new ModelAndView(JSPConstants.OprtSuccessPage);
            }
            Collections.reverse(fixedFeeItemList);
            
            if(!gbName.equals(HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0)))){
                result = "小区名称不正确，请确认模板是否正确";
                request.setAttribute(JSPConstants.OprtResult, result);
                return new ModelAndView(JSPConstants.OprtSuccessPage);
            }

            ArrayList<RoomFixedFeeItemDetail> roomFixedFeeItemDetailArrayList = new ArrayList<RoomFixedFeeItemDetail>();
            String verifyResult = verifyImportDataFormat(sheet, fixedFeeItemList, gbName, roomFixedFeeItemDetailArrayList, gbId);

            if(!verifyResult.equals("通过校验")){
                request.setAttribute(JSPConstants.OprtResult,"校验失败，原因如下：\\r\\r"+ verifyResult);
                request.setAttribute(JSPConstants.ToURL, "../fixedFeeCfg/jumpToCreateFeeDetail.html?gbId="+gbId+"&gbName="+ gbName);
                return new ModelAndView(JSPConstants.OprtSuccessPage);
            }
            result = fixedFeeCfgService.saveImportDataDetail(roomFixedFeeItemDetailArrayList, gbId);
        }

        request.setAttribute(JSPConstants.OprtResult, result);

        return new ModelAndView(JSPConstants.OprtSuccessPage);
    }

    /**
     * 清空数据
     * @return
     */
    @RequestMapping("delAllDetail.json")
    @ResponseBody
    public JsonResponse delAllDetail(BigInteger gbId) {
        JsonResponse jsonResponse = new JsonResponse();
        boolean isSuccessed = fixedFeeCfgService.delFixedFeeDetailAll(gbId);
        if(isSuccessed){
            jsonResponse.setMessage("清除成功！");
        } else {
            jsonResponse.setMessage("清除失败！");
            jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
        }
        return jsonResponse;
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
     * 校验即将导入的数据的格式问题
     * @param gbId
     * @param sheet
     * @return
     */
    private String verifyImportDataFormat(HSSFSheet sheet, List<FixedFeeItem> fixedFeeItemList, String gbName, List<RoomFixedFeeItemDetail> roomFixedFeeItemDetailArrayList, BigInteger gbId){
        String resultInfo =  "";
        int itemSize = fixedFeeItemList.size();
        if(itemSize == 0) {
            return "校验不通过,没有配置缴费项!";
        }

        Set<String> roomInfoSet = new HashSet<String>();
        for (int i = 3; i < sheet.getLastRowNum() + 1; i++) {
            if(isEmptyRow(sheet, i))
                continue;

            try {
                if(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0))==null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))) {
                    return  "第" + (i + 1) + "行的楼栋号不能为空！";
                }
                if(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2))==null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))) {
                    return  "第" + (i + 1) + "行的房间号不能为空！";
                }
                RoomFixedFeeItemDetail fixedItemDetail = new RoomFixedFeeItemDetail();
                fixedItemDetail.setGbId(fixedFeeItemList.get(0).gettGbId());
                fixedItemDetail.setbName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)));
                fixedItemDetail.setUnitName(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)));
                fixedItemDetail.setRoomNumTail(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)));
                fixedItemDetail.setGbName(gbName);

                String roomInfo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "-"+
                        HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "-" +
                        HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));

                if(!roomInfoSet.add(roomInfo)){
                    return  "第" + (i + 1) + "行的房间号数据重复！";
                }
                Map<String, BigInteger> roomStrByGbIds = fixedFeeCfgService.getRoomStrByGbId(gbId);
                BigInteger realRoomId = roomStrByGbIds.get(roomInfo);
                if(realRoomId == null) {
                    return  "第"+(i+1)+"行的楼栋房号不存在！";
                }
                fixedItemDetail.setRealRoomId(realRoomId);

                List<FixedFeeItemHasRoom> hasFeeItemList = new ArrayList<FixedFeeItemHasRoom>();
                int dataStartColIndex = 3;//数据开始列
                //int coloumNum=sheet.getRow(1).getPhysicalNumberOfCells();
                boolean itemPriceIsAllZero = false;//所有收费项金额是否为零或者空
                int mergeNum = 0;
                for(int j = 0; j < itemSize; j++) {
                    //第一个收费项 不需要判断是否合并单元格，因为都是一样的
                    if(!fixedFeeItemList.get(j).getName().equals(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(dataStartColIndex + mergeNum)))){
                        return "Excel表头("+fixedFeeItemList.get(j).getName()+")与配置项不同，请重新导出模板";
                    }

                    //校验金额存在值，则不能为负数且必须为数字
                    if(fixedFeeItemList.get(j).getCalculateType().equals(1)||fixedFeeItemList.get(j).getCalculateType().equals(3)) {
                        for(int k=1; k <= 3; k++){
                            if(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)!=null
                                    && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)))){
                                if(HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)) < 0) {
                                    return "第" + (i + 1) + "行的第"+dataStartColIndex+mergeNum + k+"列金额必须大于等于零！！";
                                }
                            }
                        }
                        if(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 4)!=null
                                && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 4)))){
                            if(StringUtils.isBlank(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 4)))) {
                                return "第" + (i + 1) + "行的第"+dataStartColIndex+mergeNum + 4+"列不能为空！！";
                            }
                        }
                    } else {
                        if(sheet.getRow(i).getCell(dataStartColIndex+mergeNum)!=null
                                && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum)))){
                            if(HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum)) < 0) {
                                return "第" + (i + 1) + "行的第"+dataStartColIndex+mergeNum+"列金额必须大于等于零！！";
                            }
                        }
                        if(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2)!=null
                                && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2)))){
                            if(StringUtils.isBlank(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2)))) {
                                return "第" + (i + 1) + "行的第"+dataStartColIndex+mergeNum + 2+"列不能为空！！";
                            }
                        }
                    }
                    /*for(int k=dataStartColIndex; k < sheet.getDefaultColumnWidth(); k++){
                        if(sheet.getRow(i).getCell(k)!=null && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(k)))){
                            if(HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(k)) < 0) {
                                return "第" + (i + 1) + "行的第"+k+"列金额必须大于等于零！！";
                            }
                        }
                    }*/

                    FixedFeeItemHasRoom item = new FixedFeeItemHasRoom();
                    item.settFixedFeeItemId(fixedFeeItemList.get(j).getId());
                    item.setName(fixedFeeItemList.get(j).getName());
                    if(fixedFeeItemList.get(j).getCalculateType().equals(1)||fixedFeeItemList.get(j).getCalculateType().equals(3)) {//非指定金额
                        //金额
                        double totalPrice = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum));
                        //单价
                        double signalPrice = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1));
                        //建筑面积
                        double priceunitvalue = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2));
                        if(totalPrice==0){
                        	totalPrice = signalPrice*priceunitvalue;
                        }
                        String billMonthStart = HSSFCellUtil.getDate(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 3));

                        item.setTotalPrice(DataUtil.isEmpty(NumberUtils.doubleM100ToLong(totalPrice)) ? 0 : NumberUtils.doubleM100ToLong(totalPrice));
                        item.setPriceUnitValue(DataUtil.isEmpty(NumberUtils.doubleM100ToLong(priceunitvalue)) ? 0 : NumberUtils.doubleM100ToLong(priceunitvalue));
                        item.setSignalPrice(DataUtil.isEmpty(NumberUtils.doubleM100ToLong(signalPrice)) ? 0 : NumberUtils.doubleM100ToLong(signalPrice));
                        try{
                            if(!DataUtil.isEmpty(billMonthStart)) {
                                item.setBillMonthStart(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart), "yyyy-MM-dd"));
                                item.setCreateBillMonth(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart), "yyyy-MM-dd"));
                            }
                        } catch(Exception exception){
                        	exception.printStackTrace();
                        	return "日期格式不正确！正确格式（例如：2017-08-02）";
                        }
                        if(totalPrice > 0) {
                            itemPriceIsAllZero = true;
                        }
                    } else {
                        //金额
                        double totalPrice = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum));
                        String billMonthStart = HSSFCellUtil.getDate(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1));
                        item.setTotalPrice(DataUtil.isEmpty(NumberUtils.doubleM100ToLong(totalPrice)) ? 0 : NumberUtils.doubleM100ToLong(totalPrice));
                        try{
                            if(!DataUtil.isEmpty(billMonthStart)) {
                                item.setBillMonthStart(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart), "yyyy-MM-dd"));
                                item.setCreateBillMonth(DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart), "yyyy-MM-dd"));
                            }
                        } catch(Exception exception){
                        	exception.printStackTrace();
                        	return "日期格式不正确！正确格式（例如：2017-08-02）";
                        }
                        if(totalPrice > 0) {
                            itemPriceIsAllZero = true;
                        }
                    }

                    if(item.getTotalPrice() > 0) {
                        if(DataUtil.isEmpty(item.getBillMonthStart())) {
                            return "第" + (i + 1) + "行的("+fixedFeeItemList.get(j).getName()+")费用起始时间不能为空！";
                        }
                        hasFeeItemList.add(item);
                    }

                    if(fixedFeeItemList.get(j).getCalculateType().equals(1)||fixedFeeItemList.get(j).getCalculateType().equals(3)) {//非指定金额的 费用项名称是合并单元格的
                        mergeNum += 4;
                    } else {
                        mergeNum += 2;
                    }
                }
                fixedItemDetail.setHasFeeItemList(hasFeeItemList);
                if(itemPriceIsAllZero) {//金额不全部为零时才导入改数据
                    roomFixedFeeItemDetailArrayList.add(fixedItemDetail);
                }
            } catch(Exception e) {
                e.printStackTrace();
                resultInfo += "第" + (i + 1) + "行数据格式检验错误！\\r";
            }
        }

        return StringUtils.isEmpty(resultInfo) ? "通过校验" : resultInfo;
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
}
