package com.cnfantasia.server.api.access.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cnfantasia.server.api.access.constant.AccessDict;
import com.cnfantasia.server.api.access.constant.AccessDict.Code;
import com.cnfantasia.server.api.access.constant.AccessDict.JFQCarType;
import com.cnfantasia.server.api.access.constant.HpfCarDict;
import com.cnfantasia.server.api.access.entity.AccessMsgBean;
import com.cnfantasia.server.api.access.entity.CarDetailEntity;
import com.cnfantasia.server.api.access.entity.CarFeeType;
import com.cnfantasia.server.api.access.entity.CarNumPayLogDetail;
import com.cnfantasia.server.api.access.entity.CarPreferDto;
import com.cnfantasia.server.api.access.entity.CarPreferParam;
import com.cnfantasia.server.api.access.entity.GroupBuildingParkingInfo;
import com.cnfantasia.server.api.access.entity.MonthCarInfo;
import com.cnfantasia.server.api.access.entity.OpenDoorLogs;
import com.cnfantasia.server.api.access.entity.ParkingRecordEntity;
import com.cnfantasia.server.api.access.entity.PayCarKeyOrderParam;
import com.cnfantasia.server.api.access.entity.PlotInfo;
import com.cnfantasia.server.api.access.entity.TempCarInfo;
import com.cnfantasia.server.api.access.entity.TmpCarPayDetail;
import com.cnfantasia.server.api.access.entity.UserDoorKeyMsg;
import com.cnfantasia.server.api.access.service.BaseCarService;
import com.cnfantasia.server.api.access.service.IAccessService;
import com.cnfantasia.server.api.access.service.MonthCarService;
import com.cnfantasia.server.api.access.service.TempCarPlotService;
import com.cnfantasia.server.api.access.service.ThirdCarFactory;
import com.cnfantasia.server.api.access.service.XMFCarService;
import com.cnfantasia.server.api.access.session.IAccessPublishHandler;
import com.cnfantasia.server.api.accesslink.entity.AccessLinkDto;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.coupon.entity.CarCoupon;
import com.cnfantasia.server.api.coupon.service.ICouponService;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.service.IEbuyAdvertiseService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.login.service.IValicodeManager;
import com.cnfantasia.server.api.notice.service.INoticeService;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.plotproperty.web.FinanceCarController;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.BuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.PropertyManagementEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.service.IGroupBuildingService;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.api.version.constant.VersionConstant;
import com.cnfantasia.server.api.version.entity.AppVersionEntity;
import com.cnfantasia.server.api.version.service.IVersionService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.carGroupBuilding.entity.CarGroupBuilding;
import com.cnfantasia.server.domainbase.carGroupBuilding.service.ICarGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.carKeyRoomTemp.entity.CarKeyRoomTemp;
import com.cnfantasia.server.domainbase.carKeyRoomTemp.service.ICarKeyRoomTempBaseService;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.CarNumPayLog;
import com.cnfantasia.server.domainbase.carNumPayLog.entity.PayCarFeeLog;
import com.cnfantasia.server.domainbase.carNumPayLog.service.ICarNumPayLogBaseService;
import com.cnfantasia.server.domainbase.carPrefer.entity.CarPrefer;
import com.cnfantasia.server.domainbase.carPrefer.service.ICarPreferBaseService;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.service.ICloudKeyUserAuditBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.openDoorLog.entity.OpenDoorLog;
import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;
import com.cnfantasia.server.domainbase.parkingRecord.service.IParkingRecordBaseService;
import com.cnfantasia.server.domainbase.payKeyList.entity.PayKeyList;
import com.cnfantasia.server.domainbase.payKeyList.service.IPayKeyListBaseService;
import com.cnfantasia.server.domainbase.roomHasCarNum.entity.RoomHasCarNum;
import com.cnfantasia.server.domainbase.roomHasCarNum.service.IRoomHasCarNumBaseService;

/**
 * @author wangzhe
 * @date 2015年12月10日
 * @description
 * 
 */
@RequestMapping("/access")
@Controller
public class AccessController extends BaseController {
    private final Logger logger = Logger.getLogger(getClass());

    @Resource
    private IAccessService accessService;

    @Resource
    private INoticeService noticeService;

    @Resource
    private IValicodeManager valicodeManager;

    @Resource
    private IPayKeyListBaseService payKeyListBaseService;

    @Resource
    private ICloudKeyUserAuditBaseService cloudKeyUserAuditBaseService;

    @Resource
    private ICarKeyRoomTempBaseService carKeyRoomTempBaseService;

    @Resource
    private IParkingRecordBaseService parkingRecordBaseService;

    @Resource
    private ICarNumPayLogBaseService carNumPayLogBaseService;

    @Resource
    private ICarGroupBuildingBaseService carGroupBuildingBaseService;

    @Resource
    private IRoomHasCarNumBaseService roomHasCarNumBaseService;

    @Resource
    private ICarNumListBaseService carNumListBaseService;

    @Resource
    private IUuidManager uuidManager;

    @Resource
    private IRoomService roomService;

    @Resource
    private IEbuyOrderBaseService ebuyOrderBaseService;

    @Resource
    protected ISysParamManager sysParamManager;

    @Resource
    protected IFileServerService fileServerService;

    @Resource
    private IGroupBuildingBaseService groupBuildingBaseService;
    
    @Resource
    private IAccessPublishHandler accessPublishHandler;

    @Resource
    private IVersionService versionService;
    
    @Resource
    private ICarPreferBaseService carPreferBaseService;
    
    @Resource
    private ICouponService couponService;
    
    @Resource
    private ThirdCarFactory thirdCarFactory;
    
    @Resource
    private MonthCarService monthCarService;
    
    @Resource
    private XMFCarService xmfCarService;
    
    @Resource
    private IGroupBuildingService groupBuildingService;

    @Resource
    private IAddressOperationService addressOperationService;

    @Resource
    private IEbuyAdvertiseService ebuyAdvertiseService;
    
    @Resource
    private TempCarPlotService tempCarPlotService;
    
    @RequestMapping("/qryDoorFee.json")
    @ResponseBody
    public JsonResponse qryDoorFee(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        final List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        List<PayKeyList> reslist = payKeyListBaseService.getPayKeyListByCondition(null);
        for (int i = 0; i < reslist.size(); i++) {
            final Map<String, Object> resMap = new HashMap<String, Object>(3);
            resMap.put("payId", reslist.get(i).getId());
            resMap.put("fee", PriceUtil.div100(reslist.get(i).getPayPrice()));
            resMap.put("tip", reslist.get(i).getDesc());
            resList.add(resMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 门禁认证申请【此接口已废弃===》新接口cloudKeyUserData/insertCloudKeyUserAudit.json】 update
     * by Liyl
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @Deprecated
    @RequestMapping("/applyDoorUserAudit.json")
    @ResponseBody
    public JsonResponse applyDoorUserAudit(final HttpServletRequest request) throws IOException {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String userName = ParamUtils.getString(request, "userName", null);// 姓名
        String phoneNum = ParamUtils.getString(request, "phoneNum", null);// 手机号
        String userNo = ParamUtils.getString(request, "userNo", null);// 身份证号
        RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
        GroupBuildingEntity gb = rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
        if (gb.getSignStatus() != 1) {
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("该小区为非签约小区！！！");
            return jsonResponse;
        }
        if (gb.gettPropertyManagementFId() == null) {
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("该小区无物业管理处信息！！！");
            return jsonResponse;
        }
        // 判断是否重新审核
        Map<String, Object> paramMap = new HashMap<String, Object>(3);
        paramMap.put("tBuildingFId", rewv.getRealRoomEntity().gettBuildingFId());
        paramMap.put("huaId", userId);
        paramMap.put("sys0DelState", 0);
        List<CloudKeyUserAudit> auditList = cloudKeyUserAuditBaseService.getCloudKeyUserAuditByCondition(paramMap);
        if (auditList != null && auditList.size() > 0) {
            if (auditList.get(0).getStatus() == 0) {
                jsonResponse.setDataValue(true);
                return jsonResponse;
            } else if (auditList.get(0).getStatus() == 2) {
                auditList.get(0).setSys0DelState(1);
                auditList.get(0).setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
                cloudKeyUserAuditBaseService.updateCloudKeyUserAudit(auditList.get(0));
            }
        }
        CloudKeyUserAudit cloudKeyUserAudit = new CloudKeyUserAudit();
        cloudKeyUserAudit.setHuaId(userId);
        cloudKeyUserAudit.setStatus(0);
        cloudKeyUserAudit.setPhoneNo(phoneNum);
        cloudKeyUserAudit.setUserNo(userNo);
        cloudKeyUserAudit.setUserName(userName);
        cloudKeyUserAudit.settBuildingFId(rewv.getRealRoomEntity().gettBuildingFId());
        cloudKeyUserAudit.settRealRoomFId(rewv.getRealRoomEntity().getId());
        cloudKeyUserAudit.settGroupBuildingFId(rewv.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId());
        cloudKeyUserAudit.settPropertyManagementFId(
                rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getPropertyManagementEntity().getId());
        // 上传图片
        String pic = upImageouser(request, userId);
        cloudKeyUserAudit.setPhotoUrl(pic);
        cloudKeyUserAudit.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
        cloudKeyUserAudit.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_cloud_key_user_audit));
        cloudKeyUserAuditBaseService.insertCloudKeyUserAudit(cloudKeyUserAudit);
        jsonResponse.setDataValue(true);
        return jsonResponse;
    }

    @RequestMapping("/qryUserDoorStatus.json")
    @ResponseBody
    public JsonResponse qryUserDoorStatus(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 搜集参数
        String userIdStr = request.getParameter("userId");
        BigInteger userId = null;
        if(StringUtils.isNotBlank(userIdStr)){
        	userId= new BigInteger(userIdStr);
        } else {
        	userId= UserContext.getOperIdMustExistBigInteger();
        }
        RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
        Map<String, Object> paramMap = new HashMap<String, Object>(2);
        paramMap.put("userId", userId);
        BigInteger buildingId = rewv.getRealRoomEntity().gettBuildingFId();
        paramMap.put("buildingId", buildingId);
        List<UserDoorKeyMsg> userDoorMsgs = accessService.qryUserDoorKeyDetail(paramMap);
        if (userDoorMsgs != null && userDoorMsgs.size()>0) {
        	BigInteger gbId = rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getId();
            jsonResponse.putData("gbId", gbId);
            PropertyManagementEntity propertymanager = rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getPropertyManagementEntity();
            jsonResponse.putData("tel", propertymanager == null ? null : propertymanager.getTel());
            
            List<String> picList = new ArrayList<String>();
            String baseurl = getDoorKeyPicUrl();
            picList.add(baseurl + "doorKey1.png");
            picList.add(baseurl + "doorKey2.png");
            picList.add(baseurl + "doorKey3.png");
            picList.add(baseurl + "doorKey4.png");
            jsonResponse.putData("picList", picList);
            jsonResponse.putData("buildingId", buildingId);
            
            // 密钥列表
            List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
            for(UserDoorKeyMsg userDoorKeyMsg:userDoorMsgs){
            	 Map<String, Object> resultMap = new HashMap<String, Object>(8);
            	 // 2支付确认中，1已付款，0未付款
            	 resultMap.put("payStatus", userDoorKeyMsg.getPayStatus());
            	 resultMap.put("auditReason", userDoorKeyMsg.getAuditReason());
            	 resultMap.put("doorKey", userDoorKeyMsg.getDoorKey());
            	 resultMap.put("type", userDoorKeyMsg.getType());
            	 resultMap.put("doorName", userDoorKeyMsg.getDoorName());
            	 resultMap.put("openRemote", (userDoorKeyMsg.getIsOpenRemote()!=1));
            	 resultMap.put("openClose", (userDoorKeyMsg.getIsOpenClose()!=1));
            	 resultMap.put("gbId", userDoorKeyMsg.getGbId());
                 
                 resList.add(resultMap);
            }
            jsonResponse.putData("openstatus", getOpenStatus(userDoorMsgs));
            
            jsonResponse.putData("doorKeyList", resList);
        } else {
            PropertyManagementEntity propertymanager = rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity()
                    .getPropertyManagementEntity();
            jsonResponse.putData("tel", propertymanager == null ? null : propertymanager.getTel());
            jsonResponse.putData("buildingId", rewv.getRealRoomEntity().gettBuildingFId());
            jsonResponse.putData("openstatus", 3);// 未认证
            jsonResponse.putData("gbId", rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getId());
        }
        return jsonResponse;
    }
    /**
     * 获取门禁认证状态（0：认证中，1：已认证；2：认证失败；3：未认证）
     * 
     * @param userDoorMsgs
     * @return
     */
    private String getOpenStatus(List<UserDoorKeyMsg> userDoorMsgs){
        String openStatus = "3";
        for(UserDoorKeyMsg userDoorKeyMsg:userDoorMsgs){
        	if(userDoorKeyMsg.getOpenStatus()==1){
        		openStatus = "1";
        		break;
        	} 
        }
        if("3".equals(openStatus)){
        	for(UserDoorKeyMsg userDoorKeyMsg:userDoorMsgs){
            	if(userDoorKeyMsg.getOpenStatus()==0){
            		openStatus = "0";
            		break;
            	} 
            }
        	
        	 if("3".equals(openStatus)){
             	for(UserDoorKeyMsg userDoorKeyMsg:userDoorMsgs){
                 	if(userDoorKeyMsg.getOpenStatus()==2){
                 		openStatus = "2";
                 		break;
                 	} 
                 }
             }
        }
        
        return openStatus;
    }

    /**
     * 获取图片地址
     * @return
     */
    private String getDoorKeyPicUrl() {
        String basePath = sysParamManager.getSysParaValue(SysParamKey.doorKeyPicUrl);
        String serverUrl = sysParamManager.getImageServerUrl(SysParamKey.doorKeyPicUrl);
        return serverUrl + basePath;
    }

    /*
     * 门禁广告确认支付
     */
    @RequestMapping("/getDoorKeyOrderId.json")
    @ResponseBody
    public JsonResponse getDoorKeyOrderNo(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger payId = ParamUtils.getBigInteger(request, "payId", null);
        if (payId == null) {
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("请选择要支付的选项！！！");
            return jsonResponse;
        }
        PayKeyList payKeyList = payKeyListBaseService.getPayKeyListBySeqId(payId);
        Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
        BigInteger orderId = accessService.payDoorKeyOrder(payId, payKeyList.getPayPrice(), userId, subChannelId);
        // 3.结果处理
        jsonResponse.putData("orderId", orderId);
        return jsonResponse;
    }

    private String upImageouser(HttpServletRequest request, BigInteger userId) throws IOException {
        RequestFileEntity requestFile = CommRequestFileParser.parseRequsetFileInfo(request, "photoPic");
        String doorKeyPicBase = sysParamManager.getSysParaValue(SysParamKey.doorKeyPicUrl);
        // 生成文件名 userId+time+3位随机数
        String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr()).append("_").append("_")
                .append(RandomUtils.createRandom(true, 3)).append(".").append(requestFile.getFileType()).toString();
        String serverPath = doorKeyPicBase + resFileName;
        fileServerService.uploadFile(serverPath, requestFile.getFileContent());
        return resFileName;
    }

    /**
     * 门禁广告支付详情页
     * 
     * @return
     */
    @RequestMapping("/qryDoorKeydetail.json")
    @ResponseBody
    public JsonResponse qryDoorKeydetail(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 搜集参数
        BigInteger orderId = ParamUtils.getBigInteger(request, "orderId", null);
        if (orderId != null) {
            EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(orderId);
            if (ebuyOrder.getPayStatus() != null) {
                switch (ebuyOrder.getPayStatus()) {
                case 1:
                    jsonResponse.putData("result", "progress");
                    jsonResponse.putData("resultDes", "支付确认中");
                    break;
                case 2:
                    jsonResponse.putData("result", "success");
                    jsonResponse.putData("resultDes", "支付成功");
                    break;
                case 3:
                    jsonResponse.putData("result", "failed");
                    jsonResponse.putData("resultDes", "支付失败");
                    break;
                }
            } else {
                jsonResponse.putData("result", "progress");
                jsonResponse.putData("resultDes", "支付确认中");
            }
            if (StringUtils.isNotEmpty(ebuyOrder.getPayMethod())) {
                switch (Integer.valueOf(ebuyOrder.getPayMethod())) {
                case 1:
                    jsonResponse.putData("pay_method", "微信支付");
                    break;
                case 2:
                    jsonResponse.putData("pay_method", "支付宝支付");
                    break;
                case 3:
                    jsonResponse.putData("pay_method", "银联支付");
                    break;
                case 4:
                    jsonResponse.putData("pay_method", "纯粮票支付");
                    break;
                case 9:
                    jsonResponse.putData("pay_method", "银行卡支付");
                    break;
                }
            } else {
                jsonResponse.putData("pay_method", null);
            }
            jsonResponse.putData("pay_time", ebuyOrder.getPayTime());
            jsonResponse.putData("pay_fee", PriceUtil.div100(ebuyOrder.getAmount()) + "元");
            jsonResponse.putData("fee", PriceUtil.div100(ebuyOrder.getAmount()));
        } else {
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("订单号为空！！！");
        }
        return jsonResponse;
    }

    /**
     * 同步的车牌房间对应信息(取消门牌与车牌挂钩)
     * 
     * @param request
     * @return
     */
    @RequestMapping("/savecarmsg.json")
    @ResponseBody
    public Object savecarmsg(HttpServletRequest request) {
        Map<String, Object> respMap = new HashMap<String, Object>(2);
        try {
            String tradeCode = ParamUtils.getString(request, "trade_code", null);
            String carNum = ParamUtils.getString(request, "car_num", null);
            String oldCarNum = ParamUtils.getString(request, "old_car_num", null);
            String roomNum = ParamUtils.getString(request, "room_num", null);
            String buildingNum = ParamUtils.getString(request, "building_num", null);
            String unitNum = ParamUtils.getString(request, "unit_num", null);
            Integer addUpdate = ParamUtils.getInteger(request, "add_update", null);
            Integer status = ParamUtils.getInteger(request, "status", null);
            Double parkingRate = ParamUtils.getDouble(request, "parkingRate", 0);
            if (carNum == null || tradeCode == null /*|| building_num == null*/ || roomNum == null || status == null) {
                respMap.put("status", "0001");
                respMap.put("message", "信息不能为空！");
                return JSONObject.toJSONString(respMap);
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("trade_code", tradeCode);
            paramMap.put("car_num", carNum);
            paramMap.put("old_car_num", oldCarNum);
            paramMap.put("room_num", roomNum);
            paramMap.put("unit_num", unitNum);
            paramMap.put("building_num", buildingNum);
            paramMap.put("add_update", addUpdate);
            paramMap.put("status", status);
            paramMap.put("parkingRate", parkingRate);
            CarKeyRoomTemp carKeyRoomTemp = new CarKeyRoomTemp();
            CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(tradeCode);
            if (carGroupBuilding != null) {
                // 推送记录
                carKeyRoomTemp.setBuildingNo(buildingNum);
                carKeyRoomTemp.setRoomNo(roomNum);
                carKeyRoomTemp.setCarNum(carNum);
                carKeyRoomTemp.setStatus(status);
                carKeyRoomTemp.setIsnotStatus(0);
                carKeyRoomTemp.setBuildingNo(buildingNum);
                carKeyRoomTemp.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_key_room_temp));
                // 获取小区ID
                BigInteger gbId = carGroupBuilding.gettGroupBuildingId();
                carKeyRoomTemp.settGroupBuildingFId(gbId);
                carKeyRoomTempBaseService.insertCarKeyRoomTemp(carKeyRoomTemp);
                if (addUpdate == 1 && oldCarNum != null) {
                    // 修改
                	paramMap.clear();
                	paramMap.put("carNum", oldCarNum);
                	paramMap.put("gbId", carGroupBuilding.gettGroupBuildingId());
                    CarNumList oldCar = accessService.getCardetail(paramMap);
                    
                    if (oldCar != null) {
                    	oldCar.setSys0DelState(1);
                        carNumListBaseService.updateCarNumList(oldCar);
                    }
                }
                // 车牌表插入一个车牌
                CarNumList carNummsg = new CarNumList();
                // 判断是否认证门牌,验证门牌则查询当前门牌下的所有用户自动添加绑定关系，非验证门牌则不操作
                carNummsg.setFee(PriceUtil.multiply100(parkingRate));
                carNummsg.setStatus(status);
                carNummsg.settCarNum(carNum);
                carNummsg.setLockStatus(0);
                carNummsg.setRealRoomId(null);
                carNummsg.setBuildingNum(buildingNum);
                carNummsg.setRoomNum(roomNum);
                carNummsg.setUnitNum(unitNum);
                carNummsg.settGroupBuildingFId(gbId);
                carNummsg.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
                carNummsg.setExpireDate(CnfantasiaCommbusiUtil.getCurrentTime());
                carNummsg.setSys0DelState(0);
                if (addUpdate == 0) {
                    // 查询该车牌是否存在
                	paramMap.clear();
                	paramMap.put("carNum", carNum);
                	paramMap.put("gbId", carGroupBuilding.gettGroupBuildingId());
                    CarNumList oldCar = accessService.getCardetail(paramMap);
                    if (oldCar != null) {
                        carNummsg.setId(oldCar.getId());
                        carNumListBaseService.updateCarNumList(carNummsg);
                    } else {
                        carNummsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
                        carNumListBaseService.insertCarNumList(carNummsg);
                    }
                } else {
                    carNummsg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
                    carNumListBaseService.insertCarNumList(carNummsg);
                }
                respMap.put("status", "0000");
                respMap.put("message", "车牌信息同步成功！");
            } else {
                respMap.put("status", "0001");
                respMap.put("message", "未知的业务编号！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            respMap.put("status", "0002");
            respMap.put("message", "系统异常！请联系管理员！！");
        }
        return JSONObject.toJSONString(respMap);
    }

    /**
     * 停车记录同步
     * 
     * @param request
     * @return
     */
    @RequestMapping("/parkingRecord.json")
    @ResponseBody
    public Object parkingRecord(HttpServletRequest request) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        try {
            String trade_code = ParamUtils.getString(request, "trade_code", null);
            String start_time = ParamUtils.getString(request, "start_time", null);
            String end_time = ParamUtils.getString(request, "end_time", null);
            String car_num = ParamUtils.getString(request, "car_num", null);
            double parking_Rate = ParamUtils.getDouble(request, "parking_Rate", 0);
            Integer status = ParamUtils.getInteger(request, "status", null);
//            Integer pay_platform = ParamUtils.getInteger(request, "pay_platform", null);
            if (car_num == null || trade_code == null || status == null/* || pay_platform == null*/) {
                respMap.put("status", "0001");
                respMap.put("message", "信息不能为空！");
                return JSONObject.toJSONString(respMap);
            }
            
            // 获取小区ID
            CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(trade_code);
            if (carGroupBuilding != null) {
            	Map<String, Object> paramMap = new HashMap<String, Object>();
            	paramMap.put("carNum", car_num);
            	paramMap.put("gbId", carGroupBuilding.gettGroupBuildingId());
            	CarNumList carnum = accessService.getCardetail(paramMap);
            	if (null == carnum) {
                    // 没有车辆信息，新增一临时车
                    carnum = new CarNumList();
                    carnum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
                    carnum.settCarNum(car_num);
                    carnum.settGroupBuildingFId(carGroupBuilding.gettGroupBuildingId());
                    carnum.setFee(0L);
                    carnum.setExpireDate(DateUtils.getCurrentDate());
                    carnum.setStatus(0);
                    carnum.setLockStatus(0);
                    carnum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
                    carNumListBaseService.insertCarNumList(carnum);
                }
            	
                BigInteger gbId = carGroupBuilding.gettGroupBuildingId();
                ParkingRecord parkingRecord = new ParkingRecord();
                parkingRecord.setParkingStartTime(start_time);
                parkingRecord.setParkingEndTime(end_time);
                parkingRecord.setParkingFee(PriceUtil.multiply100(parking_Rate));
                parkingRecord.settCarNumId(carnum.getId());
                parkingRecord.settGroupBuildingId(gbId);
                
                paramMap.clear();
                paramMap.put("tCarNumId", carnum.getId());
//                boolean isOut = true;
                List<ParkingRecord> parkList = parkingRecordBaseService.getParkingRecordByCondition(paramMap);
                if (parkList != null && parkList.size() > 0
                        && (StringUtils.isBlank(parkList.get(0).getParkingEndTime()))) {
                    parkingRecord.setId(parkList.get(0).getId());
                    parkingRecordBaseService.updateParkingRecord(parkingRecord);
                } else {
                    parkingRecord.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_parking_record));
                    parkingRecordBaseService.insertParkingRecord(parkingRecord);
//                    if (parkingRecord.getParkingEndTime() != null && !parkingRecord.getParkingEndTime().equals("")) {
//                    } else {
//                        isOut = false;
//                    }
                }
                // 解放区划扣
//                if (pay_platform!=null && pay_platform == 1 && status == 0 && isOut) {
//                    Long carFee = carnum.getFee() == null ? 0L : carnum.getFee();
//                    Long balance = carFee - PriceUtil.multiply100(parking_Rate);
//                    if (balance >= 0) {
//                        carnum.setFee(balance);
//                        carnum.setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
//                        carNumListBaseService.updateCarNumList(carnum);
//                        respMap.put("status", "0000");
//                        respMap.put("message", "停车记录同步成功！");
//                    } else {
//                        respMap.put("status", "0003");
//                        respMap.put("message", "您的app余额不足！");
//                    }
//                } else {
                    respMap.put("status", "0000");
                    respMap.put("message", "停车记录同步成功！");
//                }
            } else {
                respMap.put("status", "0001");
                respMap.put("message", "未知的业务编号！");
            }
        } catch (Exception e) {
            logger.error(e);
            respMap.put("status", "0002");
            respMap.put("message", "系统异常！请联系管理员！！");
        }
        return JSONObject.toJSONString(respMap);
    }

    /**
     * 月卡车缴费同步接口
     * 
     * @param request
     * @return
     * @throws ParseException
     */
    @Deprecated    
    @RequestMapping("/carPayLogValidDate.json")
    @ResponseBody
    public Object carPayLog(HttpServletRequest request) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        try {
            String trade_code = ParamUtils.getString(request, "trade_code", null);
            String pay_time = ParamUtils.getString(request, "pay_time", null);
            String car_num = ParamUtils.getString(request, "car_num", null);
            String pay_valid_date = ParamUtils.getString(request, "pay_valid_date", null);// 有效期
            if (trade_code == null || pay_time == null || car_num == null) {
                respMap.put("status", "0001");
                respMap.put("message", "信息不能为空！");
                return JSONObject.toJSONString(respMap);
            }
            Map<String, Object> paramMap = new HashMap<String, Object>();
        	paramMap.put("carNum", car_num);
        	CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(trade_code);
        	paramMap.put("gbId", carGroupBuilding.gettGroupBuildingId());
            CarNumList carnum = accessService.getCardetail(paramMap);
            
            SimpleDateFormat sdf = new SimpleDateFormat(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
            Date startDate = sdf.parse(carnum.getExpireDate());
            Date endDate = sdf.parse(pay_valid_date);
            
            DateTimeFormatter format = DateTimeFormat.forPattern(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);  
			DateTime startTime = DateTime.parse(carnum.getExpireDate(), format);
			DateTime endTime = DateTime.parse(pay_valid_date, format);

			Months months = Months.monthsBetween(startTime, endTime);
            
            Long pay_num = (long) months.getMonths();
            CarNumPayLog carNumpayLog = new CarNumPayLog();
            carNumpayLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_pay_log));
            carNumpayLog.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
            carNumpayLog.setPayTime(pay_time);
            carNumpayLog.setFee(carnum.getFee() * pay_num);
            carNumpayLog.setPayNum(pay_num);
            carNumpayLog.settCarNumId(carnum.getId());
            carNumpayLog.setStatus(1);// 非解放区缴费
            carNumpayLog.setPushStatus(1);// 已推送
            carNumpayLog.settEbuyOrderId(null);// 非解放区无订单号
            {
            	if(pay_num>0){
            		carNumpayLog.setPayStartDate(sdf.format(startDate));
                	carNumpayLog.setPayEndDate(sdf.format(endDate));
            	}
            }
            
            carNumPayLogBaseService.insertCarNumPayLog(carNumpayLog);
            // 车牌有效期加pay_num个月
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("pay_num", pay_num);
            resMap.put("car_num", car_num);
            resMap.put("pay_valid_date", pay_valid_date);
            accessService.updatecarNumendTime(resMap);
            respMap.put("status", "0000");
            respMap.put("message", "缴费记录同步成功！");
        } catch (Exception e) {
            logger.error(e);
            respMap.put("status", "0002");
            respMap.put("message", "系统异常！请联系管理员！！");
        }
        return JSONObject.toJSONString(respMap);
    }
    
    /**
     * 根据tradeCode获取小区id
     * @param tradeCode
     * @return
     *//*
    private BigInteger getGbIdByTradeCode(String tradeCode){
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("tradeCode", tradeCode);
    	List<CarGroupBuilding> carGroupBuildingList = carGroupBuildingBaseService.getCarGroupBuildingByCondition(paramMap);
    	return carGroupBuildingList.get(0).gettGroupBuildingId();
    }*/

    /**
     * 根据小区业务编号同步推送地址
     * 
     * @param request
     * @return
     */
    @RequestMapping("/getPushUrl.json")
    @ResponseBody
    public Object getPushUrl(HttpServletRequest request) {
        Map<String, Object> respMap = new HashMap<String, Object>();
        try {
            String trade_code = ParamUtils.getString(request, "trade_code", null);
            String push_url = ParamUtils.getString(request, "push_url", null);
            Long parking_total = ParamUtils.getLong(request, "parking_total", null);
            Long parking_crnt = ParamUtils.getLong(request, "parking_crnt", null);
            if (trade_code == null/* || push_url == null*/) {
                respMap.put("status", "0001");
                respMap.put("message", "信息不能为空！");
                return JSONObject.toJSONString(respMap);
            }
            CarGroupBuilding carGroupBuilding = accessService.getCarGroupBuilding(trade_code);
            if (carGroupBuilding != null) {
                if (parking_total > 0 && parking_crnt > 0 && parking_total >= parking_crnt) {
                    carGroupBuilding.setPushUrl(push_url);
                    carGroupBuilding.setParkingTotal(parking_total);
                    carGroupBuilding.setParkingCrnt(parking_crnt);
                    carGroupBuildingBaseService.updateCarGroupBuilding(carGroupBuilding);
                    respMap.put("status", "0000");
                    respMap.put("message", "推送url成功！");
                } else {
                    respMap.put("status", "0001");
                    respMap.put("message", "车位信息有误，请检查！");
                }
            } else {
                respMap.put("status", "0001");
                respMap.put("message", "未知的业务编号！");
            }
        } catch (Exception e) {
            logger.error(e);
            respMap.put("status", "0002");
            respMap.put("message", "系统异常！请联系管理员！！");
        }
        return JSONObject.toJSONString(respMap);
    }

    private static final String CACHE_PRE_KEY = "financeCar";
    private Map<String, Integer> getFinanceCarProfitPerDay(long actualfee){
    	String key = getFinanceCarProfitPerDayKey(actualfee);
    	String fee = RedisCacheHandler.get(key);
    	if(StringUtils.isNotBlank(fee)){
    		return JSON.parseObject(fee, new TypeReference<Map<String, Integer>>(){});
    	}
    	return null;
    }
    
    private String getFinanceCarProfitPerDayKey(long actualfee){
    	return CACHE_PRE_KEY.concat(String.valueOf(actualfee)).trim();
    }
    
    /**
     * @author wangzhe
     * @date 2015年12月18日
     * @description 查询该用户名下车的概要
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/qryCarInfo.json")
    @ResponseBody
    public JsonResponse qryCarInfo(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        final BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        final Map<String, Object> ret = new HashMap<String, Object>();
        final RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
        BigInteger groupBuildingId = null;
        if (rewv != null) {
            groupBuildingId = rewv.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
            if (groupBuildingId.compareTo(RoomConstants.DEFAULT_GROUP_BUILDING_ID) == 0) {
                groupBuildingId = HeaderManager.getGbId() == null ? groupBuildingId : HeaderManager.getGbId();
            }
            Map<String, Object> resMap = groupBuildingService.queryGroupBuildingInfoById(groupBuildingId);
            GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(groupBuildingId);
            final GroupBuildingParkingInfo pi = accessService
                    .qryParkingInfo(groupBuildingId);
            ret.put("totalpark", pi != null ? pi.getTotal() : 0); // 总停车位
            ret.put("currentpark", pi != null ? pi.getCurrent() : 0); // 当前剩余停车位
            ret.put("address", resMap.get("cityName").toString() + resMap.get("blockName") + resMap.get("gbName")); // 地址
            ret.put("phonenumber", noticeService
                    .queryMobilePhone(gb.gettPropertyManagementFId()));
        } else {
            ret.put("totalpark", "0"); // 总停车位
            ret.put("currentpark", "0"); // 当前剩余停车位
            ret.put("address", "未选择门牌"); // 地址
            ret.put("phonenumber", "无"); // 地址
        }

        final List<CarDetailEntity> carDetails = accessService.getCarInfos(userId);
		if (carDetails != null && carDetails.size() > 0) {
			final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	        final Date now = new Date();
	        List<List<CarFeeType>> allCarFeeTypeList = new ArrayList<List<CarFeeType>>();
	        monthCarService.dealMonthCar(carDetails, allCarFeeTypeList);
	        
	        for (int i=0; i<carDetails.size(); i++) {
	        	CarDetailEntity carDetail = carDetails.get(i);
				if (carDetail.getSys0DelState() == 1) { continue; }
	        	long actualfee = carDetail.getFee();
	        	String expire = carDetail.getExpireDate();
	            List<CarFeeType> carFeeTypeList = allCarFeeTypeList.get(i);

	            final Map<String, Object> map = new HashMap<String, Object>();
	            map.put("carFeeTypeList", carFeeTypeList);

	            if(carFeeTypeList==null || carFeeTypeList.size()==0) {
	                map.put("carFeeMsg", "本年度费用已经缴清！");
	            }
	            map.put("id", carDetail.getId());
	            map.put("cardname", carDetail.getStatus() != 0 ? "月卡" : "次缴卡");
	            map.put("monthcard", carDetail.getStatus() != 0);
	            map.put("groupbuildingName", carDetail.getGbName());
	            map.put("carMonthIsOpen", (null!=carDetail.getCarMonthIsOpen()) && ("1".equals(carDetail.getCarMonthIsOpen().toString())));
	            map.put("needBillIsOpen", (null!=carDetail.getNeedBillIsOpen()) && ("1".equals(carDetail.getNeedBillIsOpen().toString())));
	            try {
	                final Date expiredate = DateUtil.formatSecond.get().parse(expire);
	                map.put("expiredate", expiredate);
	                map.put("validdays", (expiredate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
	            } catch (final ParseException e) {
	            	logger.error("日期转化异常", e);
	            }
	            map.put("platenumber", carDetail.gettCarNum());
	            map.put("fee", BigDecimalUtil.div100(actualfee));

	            map.put("isBuyFinance", carDetail.getIsBuyFinance());
	            map.put("buyMoney", carDetail.getBuyMoney());
	            map.put("returnMoney", carDetail.getReturnMoney());
	            map.put("deduBeginTm", carDetail.getDeduBeginTm());
	            map.put("deduEndTm", carDetail.getDeduEndTm());
	            // 获取当天收益
	            if(carDetail.getIsBuyFinance()!=null && carDetail.getIsBuyFinance()==1){// 已经购买停车宝
	                try {
	                    Map<String, Integer> fees = getFinanceCarProfitPerDay(actualfee);
	                    if (fees == null) {
	                        Map<String, Object> feeInfo = (Map<String, Object>) FinanceCarController.getCurrentDayIncome(actualfee);
	                        if(feeInfo!=null){
	                        	fees = (Map<String, Integer>) feeInfo.get("data");
	                        	String key = getFinanceCarProfitPerDayKey(actualfee);
	                        	RedisCacheHandler.set(key, JSON.toJSONString(fees), AccessDict.CacheExpire.FINANCE_CAR_EXPIRE_3600s);
	                        }
	                    }
	                    int buyMonthNum = DateUtils.getDiffMonths(DateUtils.convertStrToDate(carDetail.getDeduBeginTm()), DateUtils.convertStrToDate(carDetail.getDeduEndTm()));
	                    Integer profitPerDay = fees.get(String.valueOf(buyMonthNum).trim());
	                    Date beginTm = DateUtils.convertStrToDate(carDetail.getDeduBeginTm(), "yyyy-MM-dd");
	                    Date endTm = DateUtils.convertStrToDate(carDetail.getDeduEndTm(), "yyyy-MM-dd");
	                    int dayNum = DateUtils.getDiffDays(endTm, beginTm);
	                    double ppd = profitPerDay.intValue() * 1.0 / dayNum;
	                    map.put("currentDayIncome", String.format("%.2f", ppd));// 当天收益
	                } catch (Exception e) {
	                    logger.error("停车宝收益获取异常", e);
	                    map.put("currentDayIncome", 0);
	                }
	            } else {
	            	map.put("currentDayIncome", 0);
	            }

	            list.add(map);
	        }
	        ret.put("list", list);
		}
        String financeCarUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_INDEX);// index.html
        ret.put("financeCarUrl", financeCarUrl);
        String isFinanceCarOpened = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_FINANCE_CAR_OPENED);
        // V322版本android的停车宝有问题
        Integer versionNum = HeaderManager.getVersionNum();
        if (versionNum != null && versionNum != 322) {
            ret.put("isFinanceCarOpened", "1".equals(isFinanceCarOpened));// 系统是否开通停车宝===>{0：关闭；1：开通}
        } else {
            ret.put("isFinanceCarOpened", false);// 系统是否开通停车宝===>{0：关闭；1：开通}
        }
        
        //车禁广告
        List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, null, groupBuildingId);
        Map<String, Object> paramMap = new HashMap<>();
        List<String> codeList = new ArrayList<>();
        codeList.add("CAR_FEE_AD");
        paramMap.put("codeList", codeList);
        paramMap.put("version", HeaderManager.getVersionNum());
        paramMap.put("codeIdList", codeIdList);
        List<EbuyAdvertise> ebuyAdvertiseList = ebuyAdvertiseService.getEbuyAdvertiseList(paramMap);
        if (!DataUtil.isEmpty(ebuyAdvertiseList)) {
            for (EbuyAdvertise ad : ebuyAdvertiseList) {
                ad.setPicUrl(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.AD_PIC_BASE_PATH, ad.getPicName(), ad.getUpdTime()));
            }
        }
        ret.put("adList", ebuyAdvertiseList);
        jsonResponse.setDataValue(ret);

        return jsonResponse;
    }

    /**
     * @author Liyl
     * @date 2016年4月12日
     * @description 查询该用户名下某辆车的概要
     * 
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/queryOneCarInfo.json")
    @ResponseBody
    public JsonResponse queryOneCarInfo(final HttpServletRequest request, String carNum) {
        final JsonResponse jsonResponse = new JsonResponse();
        final BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        final CarDetailEntity carDetail = accessService.qryCarInfo(userId, carNum);
        if(carDetail==null){
        	jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            jsonResponse.setMessage("该账户没有绑定该车牌！");
        } else if(carDetail.getIsRelieve()!=null && carDetail.getIsRelieve()==1){
        	jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            jsonResponse.setMessage("该车牌已解除绑定！");
        }else {
        	BigInteger gbId = carDetail.gettGroupBuildingFId();
        	MonthCarInfo monthCarInfo = thirdCarFactory.getOneMonthCarInfo(gbId, carNum);
        	Date expire = new Date(monthCarInfo.getExpire());
            List<CarFeeType> carFeeTypeList = thirdCarFactory.getCarFeeTypeList(gbId, monthCarInfo.getRealAmt(), DateUtil.formatDay.get().format(expire), monthCarInfo.getCarTypeId(), null);

        	final Date now = new Date();
            final Map<String, Object> map = new HashMap<String, Object>();

            map.put("carFeeTypeList", carFeeTypeList);
            if(carFeeTypeList==null || carFeeTypeList.size()==0) {
                map.put("carFeeMsg", "本年度费用已经缴清！");
            }

            map.put("id", carDetail.getId());
            map.put("cardname", carDetail.getStatus() != 0 ? "月卡" : "次缴卡");
            map.put("monthcard", carDetail.getStatus() != 0);
            map.put("groupbuildingName", carDetail.getGbName());
            map.put("carMonthIsOpen", (null!=carDetail.getCarMonthIsOpen()) && ("1".equals(carDetail.getCarMonthIsOpen().toString())));
            map.put("needBillIsOpen", (null!=carDetail.getNeedBillIsOpen()) && ("1".equals(carDetail.getNeedBillIsOpen().toString())));
            final String expiredate = DateUtil.formatSecond.get().format(expire);
            map.put("expiredate", expiredate);
            map.put("validdays", (monthCarInfo.getExpire() - now.getTime()) / (1000 * 60 * 60 * 24));
            map.put("platenumber", carDetail.gettCarNum());
            map.put("fee", BigDecimalUtil.div100(monthCarInfo.getRealAmt()));

            map.put("isBuyFinance", carDetail.getIsBuyFinance());
            map.put("buyMoney", carDetail.getBuyMoney());
            map.put("returnMoney", carDetail.getReturnMoney());
            map.put("deduBeginTm", carDetail.getDeduBeginTm());
            map.put("deduEndTm", carDetail.getDeduEndTm());
            // 获取当天收益
            if(carDetail.getIsBuyFinance()!=null && carDetail.getIsBuyFinance()==1){// 已经购买停车宝
            	try {
                    Map<String, Integer> fees = getFinanceCarProfitPerDay(monthCarInfo.getRealAmt());
                    if (fees == null) {
                        Map<String, Object> feeInfo = (Map<String, Object>) FinanceCarController.getCurrentDayIncome(monthCarInfo.getRealAmt());
                        if(feeInfo!=null){
                        	fees = (Map<String, Integer>) feeInfo.get("data");
                        	String key = getFinanceCarProfitPerDayKey(monthCarInfo.getRealAmt());
                        	RedisCacheHandler.set(key, JSON.toJSONString(fees), AccessDict.CacheExpire.FINANCE_CAR_EXPIRE_3600s);
                        }
                    }
                    int buyMonthNum = DateUtils.getDiffMonths(DateUtils.convertStrToDate(carDetail.getDeduBeginTm()),
                            DateUtils.convertStrToDate(carDetail.getDeduEndTm()));
                    Integer profitPerDay = fees.get(String.valueOf(buyMonthNum).trim());
                    Date beginTm = DateUtils.convertStrToDate(carDetail.getDeduBeginTm(), "yyyy-MM-dd");
                    Date endTm = DateUtils.convertStrToDate(carDetail.getDeduEndTm(), "yyyy-MM-dd");
                    int dayNum = DateUtils.getDiffDays(endTm, beginTm);
                    double ppd = profitPerDay.intValue() * 1.0 / dayNum;
                    map.put("currentDayIncome", String.format("%.2f", ppd));// 当天收益
                } catch (Exception e) {
                	logger.error("停车宝收益获取异常", e);
                    map.put("currentDayIncome", 0);
                }
            } else {
            	map.put("currentDayIncome", 0);
            }

            String financeCarUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.FINANCE_CAR_INDEX);// index.html
            map.put("financeCarUrl", financeCarUrl);
            String isFinanceCarOpened = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_FINANCE_CAR_OPENED);

            Integer versionNum = HeaderManager.getVersionNum();
            if (versionNum != null && versionNum != 322) {
                map.put("isFinanceCarOpened", "1".equals(isFinanceCarOpened));// 系统是否开通停车宝===>{0：关闭；1：开通}
            } else {
                map.put("isFinanceCarOpened", false);// 系统是否开通停车宝===>{0：关闭；1：开通}
            }

            jsonResponse.setDataValue(map);
        }
        return jsonResponse;
    }

    /**
     * @author wangzhe
     * @date 2015年12月18日
     * @description 查询停车记录（老版本）
     * 
     * @param request 需要carid page pageNum
     * @return
     */
    @RequestMapping("/qryCarParkingRecord.json")
    @ResponseBody
    @Deprecated
    public JsonResponse qryCarParkingRecord(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        final BigInteger carId = ParamUtils.getBigInteger(request, "carid", null);
        final PageModel pageModel = ControllerUtils.getPageModel(request);

        final List<ParkingRecord> prs = accessService.qryParkingRecord(carId, pageModel);// 取消参数小区id

        final List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();

        final Map<String, List<Map<String, Object>>> pendingMap = new TreeMap<String, List<Map<String, Object>>>();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        for (final ParkingRecord pr : prs) {
            String time = "";
            // fix bug if f_parking_start_time or f_parking_end_time is null
            if (StringUtils.isNotBlank(pr.getParkingStartTime())) {
                time = DateUtil.strFormate(pr.getParkingStartTime(), DateUtil.formatSecond.get(), sdf);
            } else if (StringUtils.isNotBlank(pr.getParkingEndTime())) {
                time = DateUtil.strFormate(pr.getParkingEndTime(), DateUtil.formatSecond.get(), sdf);
            } else {
                continue;
            }

            List<Map<String, Object>> list = pendingMap.get(time);
            if (null == list) {
                list = new ArrayList<Map<String, Object>>();
                pendingMap.put(time, list);
            } else {
            }

            final Map<String, Object> recordMap = new HashMap<String, Object>();
            GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(pr.gettGroupBuildingId());
            try {
                recordMap.put("enterdate",
                        pr.getParkingStartTime() != null ? DateUtil.formatSecond.get().parse(pr.getParkingStartTime()) : "");
                recordMap.put("exitdate", pr.getParkingEndTime() != null ? DateUtil.formatSecond.get().parse(pr.getParkingEndTime()) : "");
                recordMap.put("fee", String.format("%.2f", pr.getParkingFee() / 100f));
                recordMap.put("groupbuildingName", gb != null ? gb.getName() : "未选择小区");
            } catch (final ParseException e) {
            	logger.error("ParseException", e);
            }
            list.add(recordMap);
        }

        for (final Entry<String, List<Map<String, Object>>> entry : pendingMap.entrySet()) {
            final Map<String, Object> mapt = new HashMap<String, Object>();
            mapt.put("caption", entry.getKey());
            mapt.put("list", entry.getValue());
            resList.add(0, mapt);
        }

        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * @author Liyl
     * @date 2016年04月27日
     * @description 查询停车记录（version>=V325）
     * 
     * @param request 需要carid page pageNum
     * @return
     */
    @RequestMapping("/qryCarParkingRecord2.json")
    @ResponseBody
    public JsonResponse qryCarParkingRecord2(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        final BigInteger carId = ParamUtils.getBigInteger(request, "carid", null);
        final PageModel pageModel = ControllerUtils.getPageModel(request);

        final List<ParkingRecordEntity> prs = accessService.qryParkingRecord2(carId, pageModel);// 取消参数小区id

        final List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();

        final Map<String, List<Map<String, Object>>> pendingMap = new TreeMap<String, List<Map<String, Object>>>();

        for (final ParkingRecordEntity pr : prs) {
            final Map<String, Object> recordMap = new HashMap<String, Object>();
            recordMap.put("parkingTime", DateUtils.convertDateToStr(pr.getParkingTime(), AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss));
            recordMap.put("groupbuildingName", pr.getGbName() != null ? pr.getGbName() : "未选择小区");
            recordMap.put("parkingType", pr.getType());

            String time = DateUtils.convertDateToStr(pr.getParkingTime(), "yyyy年MM月");
            List<Map<String, Object>> list = pendingMap.get(time);
            if (null == list) {
                list = new ArrayList<Map<String, Object>>();
                pendingMap.put(time, list);
            }
            list.add(recordMap);
        }

        for (final Entry<String, List<Map<String, Object>>> entry : pendingMap.entrySet()) {
            final Map<String, Object> mapt = new HashMap<String, Object>();
            mapt.put("caption", entry.getKey());
            mapt.put("list", entry.getValue());
            resList.add(0, mapt);
        }

        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * @author wangzhe
     * @date 2015年12月24日
     * @description 查询缴费记录
     * 
     * @param request 需要carid page pageNum
     * @return
     */
    @RequestMapping("/qryCarPaymentRecord.json")
    @ResponseBody
    public JsonResponse qryCarPaymentRecord(final HttpServletRequest request) {
        final JsonResponse jsonResponse = new JsonResponse();
        final BigInteger carId = ParamUtils.getBigInteger(request, "carid", null);
        final PageModel pageModel = ControllerUtils.getPageModel(request);
        final BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        final List<CarNumPayLogDetail> cnpls = accessService.qryPaymentRecord(userId, carId, pageModel);

        final List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>(); // 返回值

        final Map<String, List<Map<String, Object>>> pendingMap = new TreeMap<String, List<Map<String, Object>>>();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        for (final CarNumPayLogDetail cnp : cnpls) {
            final String time = DateUtil.strFormate(cnp.getPayTime() != null ? cnp.getPayTime() : cnp.getSys0AddTime(),
                    DateUtil.formatSecond.get(), sdf);
            List<Map<String, Object>> list = pendingMap.get(time);
            if (null == list) {
                list = new ArrayList<Map<String, Object>>();
                pendingMap.put(time, list);
            } else {
            }

            final Map<String, Object> recordMap = new HashMap<String, Object>();
            try {
                recordMap.put("paytime",
                        DateUtil.formatSecond.get().parse(cnp.getPayTime() != null ? cnp.getPayTime() : cnp.getSys0AddTime()));
                if (cnp.getId().intValue() == 0) {
                    // t_finance_buy表单位为元
                    recordMap.put("fee", String.format("%.2f", cnp.getFee().doubleValue()));
                } else {
                    // t_car_num_pay_log表单位为分
                    recordMap.put("fee", String.format("%.2f", cnp.getFee() / 100f));
                }

                recordMap.put("addmonths", cnp.getPayNum());
                recordMap.put("isBuyFinance", cnp.isBuyFinance());
                if (cnp.getStatus().compareTo(1) == 0) {
                    recordMap.put("result", "success");
                    recordMap.put("resultDes", "支付成功");
                } else {
                    EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(cnp.gettEbuyOrderId());
                    if (ebuyOrder.getPayStatus() != null) {
                        switch (ebuyOrder.getPayStatus()) {
                        case 1:
                            recordMap.put("result", "progress");
                            recordMap.put("resultDes", "支付确认中");
                            break;
                        case 2:
                            recordMap.put("result", "success");
                            recordMap.put("resultDes", "支付成功");
                            break;
                        case 3:
                            recordMap.put("result", "failed");
                            recordMap.put("resultDes", "支付失败");
                            break;
                        }
                    } else {
                        recordMap.put("result", "progress");
                        recordMap.put("resultDes", "支付确认中");
                    }
                }
            } catch (final ParseException e) {
            	logger.error("ParseException", e);
            }
            list.add(recordMap);
        }

        for (final Entry<String, List<Map<String, Object>>> entry : pendingMap.entrySet()) {
            final Map<String, Object> mapt = new HashMap<String, Object>();
            mapt.put("caption", entry.getKey());
            mapt.put("list", entry.getValue());
            float total = 0f;
            for (Map<String, Object> map : entry.getValue()) {
                total += Float.parseFloat(map.get("fee").toString());
            }
            mapt.put("total", String.format("%.2f", total));
            resList.add(0, mapt);
        }

        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

	private boolean isJfqLightApp(Long subChannelId) {
    	return subChannelId!=null && subChannelId.compareTo(HeaderConstant.SubChannelId.Jfq_Light_App)==0;
    }
    
    /**
     * @author wangzhe
     * @date 2015年12月24日
     * @description 绑定car
     * 
     * @param request
     * @return
     */
	@RequestMapping("/bindCar.json")
    @ResponseBody
    public JsonResponse bindCar(final HttpServletRequest request) {
    	final JsonResponse jsonResponse = new JsonResponse();
        final BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        final RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
        BigInteger plotId = null;
        Integer versionNum = HeaderManager.getVersionNum();
        Long subChannelId = HeaderManager.getSubChannelIdLong();
        if (!isJfqLightApp(subChannelId) && versionNum != null && versionNum <= 516) {
        	BigInteger gbId = rewv.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
        	if(gbId==null || RoomConstants.DEFAULT_GROUP_BUILDING_ID.compareTo(gbId)==0) {
        		jsonResponse.setStatus("0001");
                jsonResponse.setMessage("请先在【我--->我的房产】中添加门牌！");
                return jsonResponse;
        	} else {
        		plotId = gbId;
        	}
        } else {
        	plotId = ParamUtils.getBigInteger(request, "plotId", null);
        	if(null==plotId) {
        		jsonResponse.setStatus("0001");
                jsonResponse.setMessage("停车场不能为空！请选择停车场！");
                return jsonResponse;
        	}
        }
        final String realName = ParamUtils.getString(request, "realname", "");
        final String plateNum = ParamUtils.getString(request, "platenum", ""); // 车牌号
        final String idNum = ParamUtils.getString(request, "idnum", ""); // 身份证号
        final String cellphone = ParamUtils.getString(request, "cellphone", ""); // 手机号
        final String captcha = ParamUtils.getString(request, "captcha", "captcha"); // 验证码
        
        dealBindCar(plateNum, plotId, userId);
        // 如果没有验证码，则不校验验证码
        if (!"captcha".equals(captcha)) {
            valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.BIND_CAR, cellphone, captcha);
            // 清除验证码
            valicodeManager.clearValicode(LoginDict.ValiCodeGetType.FORGET_PASSWORD);
        }
        // 查询是否存在(取消realRoomId关系，只根据车牌查询 rewv.gettRealRoomFId(),)
        if (accessService.qryRoomAndCar(plateNum)) {
            // 车牌存在
        	Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("plateNum", plateNum);
            paramMap.put("userId", userId);
            RoomHasCarNum rhcn = accessService.qryCarBindedUser(paramMap);
            // 是否绑定过该车牌
            if (rhcn==null) {
                // 绑定关系
            	paramMap.clear();
            	paramMap.put("carNum", plateNum);
                paramMap.put("gbId", plotId);
                CarNumList carNum = accessService.getCardetail(paramMap);
                if(null==carNum) {
                	jsonResponse.setStatus("0001");
                    jsonResponse.setMessage("该停车场暂无该车牌信息！");
                    return jsonResponse;
                }
                final RoomHasCarNum roomHasCarNum = new RoomHasCarNum();
                roomHasCarNum.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_room_has_car_num));
                roomHasCarNum.settRealRoomId(rewv != null ? rewv.gettRealRoomFId() : null);
                roomHasCarNum.settUserId(userId);
                roomHasCarNum.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
                roomHasCarNum.setRealName(realName);
                roomHasCarNum.settCarNumListFId(carNum.getId());
                roomHasCarNum.setCellphone(cellphone);
                roomHasCarNum.setIdCardNo(idNum);
                roomHasCarNum.setStatus(0);
                roomHasCarNum.setSys0DelState(0);
                roomHasCarNum.setIsRelieve(0);
                jsonResponse.setDataValue(0 < roomHasCarNumBaseService.insertRoomHasCarNum(roomHasCarNum));
            } else {
            	if(rhcn.getIsRelieve()==0){
            		jsonResponse.setStatus("0001");
                    jsonResponse.setMessage("该车牌已经绑定");
            	}
            	if(rhcn.getIsRelieve()==1){
            		rhcn.setIsRelieve(0);
            		jsonResponse.setDataValue(roomHasCarNumBaseService.updateRoomHasCarNum(rhcn)>0);
            	}
            }
        } else {
            jsonResponse.setDataValue(false);
        }

        return jsonResponse;
    }
	
	private void dealBindCar(String plateNum, BigInteger gbId, BigInteger userId) {
		Code code = BaseCarService.getCodeByGbId(gbId);
		if (AccessDict.Code.yihaosheng.equals(code)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("carNum", plateNum);
			paramMap.put("gbId", gbId);
			CarNumList carNumList = accessService.queryCarNumListByCondition(paramMap);
			if (carNumList != null && (carNumList.getSys0DelState() == 1 || carNumList.getStatus() == 0)) {
				carNumList.setSys0DelState(0);
				carNumList.setStatus(1);
				carNumListBaseService.updateCarNumList(carNumList);
			}
		} else {
			MonthCarInfo monthCarInfo = thirdCarFactory.getMonthCarInfo(gbId, plateNum);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
	    	paramMap.put("carNum", plateNum);
	    	paramMap.put("gbId", gbId);
	    	CarNumList carNumList = accessService.queryCarNumListByCondition(paramMap);
	    	if(carNumList==null){
				CarNumList insertCarNumList = new CarNumList();
				insertCarNumList.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list));
				insertCarNumList.settCarNum(plateNum);
				insertCarNumList.setStatus(1);
				insertCarNumList.setFee(monthCarInfo.getRealAmt());
				insertCarNumList.setLockStatus(0);
				insertCarNumList.settGroupBuildingFId(gbId);
				
				String expireDate = DateUtil.formatSecond.get().format(new Date(monthCarInfo.getExpire()));
				insertCarNumList.setExpireDate(expireDate);
				insertCarNumList.setSys0AddTime(DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss));
				insertCarNumList.setSys0AddUser(userId);
				insertCarNumList.setSys0DelState(0);
				carNumListBaseService.insertCarNumList(insertCarNumList);
	    	} else if(carNumList.getSys0DelState()==1 || carNumList.getStatus()==0){
	    		carNumList.setSys0DelState(0);
	    		carNumList.setStatus(1);
	    		carNumListBaseService.updateCarNumList(carNumList);
	    	}
		}
	}
    
    /**
     * 解除绑定
     * 
     * @param carId
     * @return
     */
    @RequestMapping("/relieveBindCar.json")
    @ResponseBody
    public JsonResponse relieveBindCar(String carId){
    	BigInteger userId = UserContext.getOperIdMustExistBigInteger();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tCarNumListFId", carId);
        paramMap.put("tUserId", userId);
        List<RoomHasCarNum> roomHasCarNums = roomHasCarNumBaseService.getRoomHasCarNumByCondition(paramMap);
        boolean isSuccess = false;
        if(roomHasCarNums!=null && roomHasCarNums.size()>0){
        	RoomHasCarNum roomHasCarNum = roomHasCarNums.get(0);
        	roomHasCarNum.setIsRelieve(1);
        	isSuccess = roomHasCarNumBaseService.updateRoomHasCarNum(roomHasCarNum)>0;
        }
    	final JsonResponse jsonResponse = new JsonResponse();
    	if(isSuccess){
    		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
            jsonResponse.setMessage("操作成功！");
    	} else {
    		jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
            jsonResponse.setMessage("操作失败！");
    	}
    	return jsonResponse;
    }

    /*
     * 车禁缴费支付（包含次卡缴费，月卡）
     */
    @RequestMapping("/payCarKeyOrder.json")
    @ResponseBody
    public JsonResponse getCarKeyOrderNo(HttpServletRequest request) {
        // 搜集参数
        BigInteger userId = UserContext.getOperIdBigInteger();
        BigInteger carId = ParamUtils.getBigInteger(request, "carId", null);
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);    // 临停车缴费需要
        Long payNum = ParamUtils.getLong(request, "payNum", 0L);
        double payFee = ParamUtils.getDouble(request, "payFee", 0);           // 临停车缴费金额；单位：元
        Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
        Integer needBill = ParamUtils.getInteger(request, "needBill", 0);     // 是否需要发票
        double receivableFee = ParamUtils.getDouble(request, "receivableFee", 0);
        long discountamount = ParamUtils.getLong(request, "discountamount", 0L);//优惠金额（小蜜蜂车禁，单位：分）
        String orderid = ParamUtils.getString(request, "orderid", null);//停车订单编号（小蜜蜂）
        BigInteger ucId = ParamUtils.getBigInteger(request, "ucId", null);
        
        // 交互
        PayCarKeyOrderParam payCarKeyOrderParam = new PayCarKeyOrderParam();
        {
        	payCarKeyOrderParam.setUserId(userId);
        	payCarKeyOrderParam.setCarId(carId);
        	payCarKeyOrderParam.setGbId(gbId);
        	payCarKeyOrderParam.setPayNum(payNum);
        	payCarKeyOrderParam.setPayFee(payFee);
        	payCarKeyOrderParam.setSubChannelId(subChannelId);
        	payCarKeyOrderParam.setNeedBill(needBill);
        	payCarKeyOrderParam.setHbAmt(null);
        	/**华鹏飞车禁*/
        	payCarKeyOrderParam.setReceivableFee(receivableFee);// 华鹏飞车禁需要
        	payCarKeyOrderParam.setSessionId(request.getSession().getId());
        	/**小蜜蜂车禁*/
        	payCarKeyOrderParam.setDiscountamount(discountamount);
        	payCarKeyOrderParam.setOrderid(orderid);
        	payCarKeyOrderParam.setUcId(ucId);
        	payCarKeyOrderParam.setFromTotalOrder(false);
        }
        PreOrderDto preOrder = accessService.payCarKeyOrder(payCarKeyOrderParam);
        // 3.结果处理
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.putData("orderId", preOrder.getOrderId());
        jsonResponse.putData("free", preOrder.isFree());
        return jsonResponse;
    }

    /**
     * 支付详情页
     */
    @RequestMapping("/getCarKeyOrderDetail.json")
    @ResponseBody
    public JsonResponse getCarKeyOrderDetail(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 搜集参数
        BigInteger orderId = ParamUtils.getBigInteger(request, "orderId", null);
        final Date now = new Date();
        if (orderId != null) {
            EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(orderId);
            CarNumPayLog carPayLog = accessService.getCarPayLogDetail(orderId);
            if (order != null && carPayLog != null) {
                CarNumList carMsg = carNumListBaseService.getCarNumListBySeqId(carPayLog.gettCarNumId());
                jsonResponse.putData("car_num", carMsg.gettCarNum());
                if (carMsg.gettGroupBuildingFId() != null) {
                    GroupBuilding gb = groupBuildingBaseService.getGroupBuildingBySeqId(carMsg.gettGroupBuildingFId());
                    jsonResponse.putData("GroupBuildName", gb != null ? gb.getName() + "停车场" : null);
                }
                if (carMsg.getStatus() == 1) {
                    jsonResponse.putData("pay_num", carPayLog.getPayNum() + "个月");
                    try {
                        final Date expiredate = DateUtil.formatSecond.get().parse(carMsg.getExpireDate());
                        jsonResponse.putData("expiredate", expiredate);
                        jsonResponse.putData("validdays", (expiredate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
                    } catch (final ParseException e) {
                        e.printStackTrace();
                    }

                    //返回可缴月份list
                    refreshMonth(jsonResponse, carMsg, request);
                }
                if (order.getPayStatus() != null) {
                    switch (order.getPayStatus()) {
                    case 1:
                        jsonResponse.putData("result", "progress");
                        jsonResponse.putData("resultDes", "支付确认中");
                        break;
                    case 2:
                        jsonResponse.putData("result", "success");
                        jsonResponse.putData("resultDes", "支付成功");
                        break;
                    case 3:
                        jsonResponse.putData("result", "failed");
                        jsonResponse.putData("resultDes", "支付失败");
                        break;
                    }
                } else {
                    jsonResponse.putData("result", "progress");
                    jsonResponse.putData("resultDes", "支付确认中");
                }
                jsonResponse.putData("pay_fee", PriceUtil.div100(order.getAmount()) + "元");
                jsonResponse.putData("couponAmount", PriceUtil.div100(order.getTotalCouponAmount()) + "元");
                jsonResponse.putData("fee", PriceUtil.div100(carMsg.getFee()));
                jsonResponse.putData("orderId", orderId);
                if (StringUtils.isNotEmpty(order.getPayMethod())) {
                    switch (Integer.valueOf(order.getPayMethod())) {
                    case 1:
                        jsonResponse.putData("pay_method", "微信支付");
                        break;
                    case 2:
                        jsonResponse.putData("pay_method", "支付宝支付");
                        break;
                    case 3:
                        jsonResponse.putData("pay_method", "银联支付");
                        break;
                    case 4:
                        jsonResponse.putData("pay_method", "纯粮票支付");
                        break;
                    case 9:
                        jsonResponse.putData("pay_method", "银行卡支付");
                        break;
                    }
                } else {
                    jsonResponse.putData("pay_method", null);
                }
                jsonResponse.putData("pay_time", order.getPayTime());
                jsonResponse.putData("order_no", order.getOrderNo());
            } else {
                jsonResponse.setStatus("0001");
                jsonResponse.setMessage("无该订单号的相关信息！！");
            }
        } else {
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("订单号为空！！");
        }
        return jsonResponse;
    }

    /**
     * 刷新缴费月份
     * @param jsonResponse
     * @param carMsg
     * @param request
     */
    private void refreshMonth(JsonResponse jsonResponse, CarNumList carMsg, HttpServletRequest request) {
        String carNum = carMsg.gettCarNum();
        BigInteger gbId = carMsg.gettGroupBuildingFId();
        
        MonthCarInfo monthCarInfo = thirdCarFactory.getOneMonthCarInfo(gbId, carNum);
    	Date expire = new Date(monthCarInfo.getExpire());
        List<CarFeeType> carFeeTypeList = thirdCarFactory.getCarFeeTypeList(gbId, monthCarInfo.getRealAmt(), DateUtil.formatDay.get().format(expire), monthCarInfo.getCarTypeId(), null);
       
        if(carFeeTypeList==null || carFeeTypeList.size()==0) {
            jsonResponse.putData("carFeeMsg", "本年度费用已经缴清！");
        }
        
        jsonResponse.putData("carFeeTypeList", carFeeTypeList);
    }

    /**
     * 门禁开锁记录安卓
     * 
     * @return
     */
    @RequestMapping("/saveOpenDoorRec.json")
    @ResponseBody
    public JsonResponse saveOpenDoorRecord(@RequestBody List<OpenDoorLogs> OpenDoorLogList, HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
        String os = request.getHeader("User-Agent");// 获取手机相关信息
        String nowTime = CnfantasiaCommbusiUtil.getCurrentTime();
        List<OpenDoorLog> logList = new ArrayList<OpenDoorLog>();
        // 交互
        try {
            if (OpenDoorLogList != null && OpenDoorLogList.size() > 0) {
                List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_open_door_log, OpenDoorLogList.size());
                for (int i = 0; i < OpenDoorLogList.size(); i++) {
                    OpenDoorLog openLog = new OpenDoorLog();
                    openLog.setId(idList.get(i));
                    openLog.setPhoneDevice(subChannelId);
                    openLog.setOs(os);
                    openLog.setSys0AddTime(nowTime);
                    openLog.setStatus(OpenDoorLogList.get(i).getOpenStatus());
                    openLog.settUserId(userId);
                    openLog.setSys0DelState(0);
                    openLog.settBuildingFId(OpenDoorLogList.get(i).getBuildingId());
                    openLog.setOpenDoorTime(OpenDoorLogList.get(i).getOpenTime());
                    openLog.setFailReason(OpenDoorLogList.get(i).getFailReason());
                    logList.add(openLog);
                }

            }
            accessService.saveOpenDoorLog(logList);
            jsonResponse.setDataValue(true);
        } catch (Exception e) {
            logger.error("saveOpenDoorRecord=====>transformation Exception");
            logger.error(e.getMessage(), e);
            jsonResponse.setDataValue(false);
        }
        // 结果处理
        return jsonResponse;
    }

    /**
     * 门禁开锁记录IOS
     * 
     * @return
     */
    @RequestMapping("/saveOpenDoorIOSLog.json")
    @ResponseBody
    public JsonResponse saveOpenDoorRecordIOS(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
        String openDoorLogList = request.getParameter("OpenDoorLogList");
        String os = request.getHeader("User-Agent");// 获取手机相关信息
        String nowTime = CnfantasiaCommbusiUtil.getCurrentTime();
        List<OpenDoorLog> logList = new ArrayList<OpenDoorLog>();
        // 交互
        if (StringUtils.isEmpty(openDoorLogList)) {
            jsonResponse.setDataValue(false);
            return jsonResponse;
        }
        try {
            List<OpenDoorLogs> resList = JSONObject.parseArray(openDoorLogList, OpenDoorLogs.class);
            if (resList != null && resList.size() > 0) {
                List<BigInteger> idList = uuidManager.getNextUuidBigInteger(SEQConstants.t_open_door_log, resList.size());
                for (int i = 0; i < resList.size(); i++) {
                    OpenDoorLog openLog = new OpenDoorLog();
                    openLog.setId(idList.get(i));
                    openLog.setPhoneDevice(subChannelId);
                    openLog.setOs(os);
                    openLog.setSys0AddTime(nowTime);
                    openLog.setStatus(resList.get(i).getOpenStatus());
                    openLog.settUserId(userId);
                    openLog.setSys0DelState(0);
                    openLog.settBuildingFId(resList.get(i).getBuildingId());
                    openLog.setOpenDoorTime(resList.get(i).getOpenTime());
                    openLog.setFailReason(resList.get(i).getFailReason());
                    logList.add(openLog);
                }
            }
            accessService.saveOpenDoorLog(logList);
            jsonResponse.setDataValue(true);
        } catch (Exception e) {
            logger.error("saveOpenDoorRecordIOS=====>transformation Exception");
            logger.error(e.getMessage(), e);
            jsonResponse.setDataValue(false);
        }
        // 结果处理
        return jsonResponse;
    }

    /**
     * @author Liyl
     * @date 2016年3月17日
     * @description 查询该用户名下车的概要（轻应用）
     * 
     * @param userId
     * @return
     */
    @RequestMapping("/qryCarFeeInfo.json")
    @ResponseBody
    public JsonResponse qryCarFeeInfo(HttpServletRequest request) {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        List<CarDetailEntity> cnls = accessService.getCarInfos(userId);

        // 小区名：数据
        Map<String, List<Map<String, Object>>> dataList = new TreeMap<String, List<Map<String, Object>>>();
        Date now = new Date();
        monthCarService.dealMonthCar(cnls, null);
        
        for (int i = 0; i < cnls.size(); i++) {
        	CarDetailEntity cnlA = cnls.get(i);
        	if (cnlA.getSys0DelState() == 1) { continue; }
            String groupbuildingNameA = cnlA.getGbName();
            for (int k = 0; k < cnls.size(); k++) {
            	CarDetailEntity cnlB = cnls.get(k);
            	if (cnlB == null) { continue; }
                String groupbuildingNameB = cnlB.getGbName();
                if (groupbuildingNameA.equals(groupbuildingNameB)) {
                    List<Map<String, Object>> listTemp = dataList.get(groupbuildingNameA);
                    boolean isAdd = true;// 是否需要添加tag
                    boolean isFirst = false;// 是否第一次添加tag
                    if (listTemp != null) {
                        for (Map<String, Object> mapTemp : listTemp) {
                            if (mapTemp.get("id").equals(cnlA.getId())) {
                                isAdd = false;// 已存在，不需要添加
                                break;
                            }
                        }
                    } else {
                        isFirst = true;
                    }
                    
                    if (isAdd) {
                    	long actualfee = cnlA.getFee();
                    	String expire = cnlA.getExpireDate();
                    	
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("id", cnlA.getId());
                        map.put("cardname", cnlA.getStatus() != 0 ? "月卡" : "次缴卡");
                        map.put("monthcard", cnlA.getStatus() != 0);
                        map.put("groupbuildingName", groupbuildingNameA);
                        map.put("groupBuildingId", cnlA.gettGroupBuildingFId());
                        if (!StringUtils.isEmpty(cnlA.getExpireDate())) {
                            try {
                                final Date expiredate = DateUtil.formatSecond.get().parse(expire);
                                map.put("expiredate", new SimpleDateFormat("yyyy年MM月dd日").format(expiredate));
                                map.put("validdays", (expiredate.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
                            } catch (final ParseException e) {
                                logger.error("ParseException", e);
                            }
                        }
                        map.put("platenumber", cnlA.gettCarNum());
                        map.put("carId", cnlA.getId());
                        map.put("fee", BigDecimalUtil.div100(actualfee));
                        List<Map<String, Object>> list = null;
                        if (isFirst) {
                            list = new ArrayList<Map<String, Object>>();
                            list.add(map);
                            dataList.put(groupbuildingNameA, list);
                        } else {
                            list = dataList.get(groupbuildingNameA);
                            list.add(map);
                        }
                    }
                }
            }
        }

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDataValue(dataList);
        return jsonResponse;
    }

    /**
     * @author Liyl
     * @date 2016年3月18日
     * @description 根据userId、小区Id查询缴费记录
     * 
     * @param carId
     * @param groupBuildingId
     * @return
     */
    @RequestMapping("/qryPaymentCarFeeRecords.json")
    @ResponseBody
    public JsonResponse qryPaymentCarFeeRecords(BigInteger groupBuildingId) {
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        final JsonResponse jsonResponse = new JsonResponse();
        final List<PayCarFeeLog> cnpls = accessService.qryPaymentCarFeeRecords(userId, groupBuildingId);

        final List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>(); // 返回值

        final Map<String, List<Map<String, Object>>> pendingMap = new TreeMap<String, List<Map<String, Object>>>();
        final SimpleDateFormat yyyymmSdf = new SimpleDateFormat("yyyy年MM月");
        final SimpleDateFormat mmddhhmmSdf = new SimpleDateFormat("MM月dd HH:mm");
        for (final PayCarFeeLog pcfl : cnpls) {
            final String time = DateUtil.strFormate(pcfl.getPayTime() != null ? pcfl.getPayTime() : pcfl.getSys0AddTime(),
                    DateUtil.formatSecond.get(), yyyymmSdf);
            List<Map<String, Object>> list = pendingMap.get(time);
            if (null == list) {
                list = new ArrayList<Map<String, Object>>();
                pendingMap.put(time, list);
            }

            final Map<String, Object> recordMap = new HashMap<String, Object>();
            String paytime = null;
            try {
                paytime = mmddhhmmSdf.format(mmddhhmmSdf.parse(pcfl.getPayTime() != null ? pcfl.getPayTime() : pcfl.getSys0AddTime()));
            } catch (ParseException e) {
                logger.error("ParseException", e);
            }

            recordMap.put("paytime", paytime);
            recordMap.put("fee", String.format("%.2f", pcfl.getFee() / 100f));
            recordMap.put("addmonths", pcfl.getPayNum());
            recordMap.put("carNum", pcfl.gettCarNum());
            if (pcfl.getStatus().compareTo(1) == 0) {
                recordMap.put("result", "success");
                recordMap.put("resultDes", "支付成功");
            } else {
                EbuyOrder ebuyOrder = ebuyOrderBaseService.getEbuyOrderBySeqId(pcfl.gettEbuyOrderId());
                if (ebuyOrder.getPayStatus() != null) {
                    switch (ebuyOrder.getPayStatus()) {
                    case 1:
                        recordMap.put("result", "progress");
                        recordMap.put("resultDes", "支付确认中");
                        break;
                    case 2:
                        recordMap.put("result", "success");
                        recordMap.put("resultDes", "支付成功");
                        break;
                    case 3:
                        recordMap.put("result", "failed");
                        recordMap.put("resultDes", "支付失败");
                        break;
                    }
                } else {
                    recordMap.put("result", "progress");
                    recordMap.put("resultDes", "支付确认中");
                }
            }
            list.add(recordMap);
        }

        for (final Entry<String, List<Map<String, Object>>> entry : pendingMap.entrySet()) {
            final Map<String, Object> mapt = new HashMap<String, Object>();
            mapt.put("caption", entry.getKey());
            mapt.put("list", entry.getValue());
            float total = 0f;
            for (Map<String, Object> map : entry.getValue()) {
                total += Float.parseFloat(map.get("fee").toString());
            }
            mapt.put("total", String.format("%.2f", total));
            resList.add(0, mapt);
        }

        jsonResponse.setDataValue(resList);
        return jsonResponse;
    }

    /**
     * 根据carId查询car信息
     * 
     * @param carId
     * @return
     */
    @RequestMapping("/queryCarInfoByCardId.json")
    @ResponseBody
    public JsonResponse queryCarInfoByCardId(BigInteger carId, HttpSession session) {
    	Map<String, Object> resultMap = accessService.queryCarInfoByCardId(carId);
    	
    	CarNumList car = carNumListBaseService.getCarNumListBySeqId(carId);
        BigInteger gbId = car.gettGroupBuildingFId();
        
        MonthCarInfo monthCarInfo = thirdCarFactory.getOneMonthCarInfo(gbId, car.gettCarNum());
    	Date expire = new Date(monthCarInfo.getExpire());
        List<CarFeeType> carFeeTypeList = thirdCarFactory.getCarFeeTypeList(gbId, monthCarInfo.getRealAmt(), DateUtil.formatDay.get().format(expire), monthCarInfo.getCarTypeId(), null);
        
        String msg = "";
        if(carFeeTypeList==null || carFeeTypeList.size()==0) {
            msg = "本年度费用已经缴清！";
        } else {
        	resultMap.put("fee", carFeeTypeList.get(0).getFee());
            resultMap.put("carFeeTypeList", carFeeTypeList);
        }
        resultMap.put("carFeeMsg", msg);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDataValue(resultMap);

        return jsonResponse;
    }

    /**
     * 根据carId查询car信息（轻应用V323_tingchebao需要，下一个版本可以删除，用/queryCarInfoByCardId.
     * json替代）
     * 
     * @param carId
     * @return
     */
    @Deprecated
    @RequestMapping("/getParkingFeePerMonth.json")
    @ResponseBody
    public JsonResponse getParkingFeePerMonth(BigInteger carId) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDataValue(accessService.queryCarInfoByCardId(carId).get("fee"));
        return jsonResponse;
    }

    /**
     * 查询临时车费用
     * @param plate 车牌
     * @param gbId 停车场所在小区
     * @param isOnlyQryFee 是否仅仅只查询停车费=={true：是；false：否}
     * @param request
     * @return
     */
	@RequestMapping("/qryFee.json")
    @ResponseBody
    public synchronized JsonResponse qryFee(String plate, BigInteger gbId, Boolean isOnlyQryFee, String from, HttpServletRequest request) {
		if (null!=plate) {
			plate = plate.toUpperCase();// 转化为大写
		}
		CarNumList carNumList = dealCarNumList(plate, gbId);
		TempCarInfo tempCarInfo = thirdCarFactory.getTempCarInfo(gbId, plate);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
    	dataMap.put("carId", carNumList.getId());
    	dataMap.put("gbId", gbId);
    	dataMap.put("plate", plate);
    	BigDecimal fee = BigDecimalUtil.div100(tempCarInfo.getRealAmt());
    	dataMap.put("fee", fee);
    	dataMap.put("discountamount", BigDecimalUtil.div100(tempCarInfo.getDiscountFee()));//优惠金额（元）
    	dataMap.put("prepaymentamount", BigDecimalUtil.div100(tempCarInfo.getPaidFee()));//已缴停车费（元）
    	dataMap.put("receivableFee", BigDecimalUtil.div100(tempCarInfo.getAmt()));
    	dataMap.put("orderid", tempCarInfo.getOrderId());
    	Date inDate = new Date(tempCarInfo.getEnterTime());
    	dataMap.put("inDate", DateUtils.convertDateToStr(inDate, "MM月dd日  HH:mm"));
        GroupBuilding groupBuilding = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
        if (groupBuilding != null) {
        	dataMap.put("gbName", groupBuilding.getName());
        }
        dataMap.put("parkingTime", getDistanceTime(inDate, new Date()));
        dataMap.put("enterDate", DateUtils.convertDateToStr(inDate, AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss));
        
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDataValue(dataMap);
    	
    	if (!isOnlyQryFee) {
    		int tmpCarOpenStatus = getTmpCarOpenStatus(gbId);
    		if(tmpCarOpenStatus!=AccessDict.TmpCarOpenStatus.CLOSE){
    	    	double amount = fee.doubleValue();
	    		Map<String, Object> payCarPreferMap = getPayCarPreferMap(gbId, 0, amount, plate, 0, tmpCarOpenStatus, request.getSession().getId());
    			jsonResponse.putDataAll(payCarPreferMap);
    		}
    		jsonResponse.putData("carTmpIsOpen", tmpCarOpenStatus);
    	}
    	
    	return jsonResponse;
    }
	
	/**
	 * 查询临停车券
	 * @return
	 */
	@RequestMapping("/qryCarCouponByUserId.json")
	@ResponseBody
	public JsonResponse qryCarCouponByUserId(){
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		CarCoupon carCoupon = couponService.selectAvailableCarCouponByUserId(userId);
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(carCoupon);
		return jsonResponse;
	}
    
    private CarNumList dealCarNumList(String carNum, BigInteger gbId){
    	HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tCarNum", carNum);
        List<CarNumList> carNumLists = carNumListBaseService.getCarNumListByCondition(paramMap);

        CarNumList carnum = null;
    	if(carNumLists==null || carNumLists.size()==0){
    		carnum = new CarNumList();
            carnum.settCarNum(carNum);
            carnum.setLockStatus(0);
            String now = DateTime.now().toString(AccessDict.DateFormat.yyyy_MM_dd_HH_mm_ss);
            carnum.setExpireDate(now);
            carnum.setStatus(1);
            carnum.setFee(0L);
            carnum.settGroupBuildingFId(gbId);
            BigInteger carId = uuidManager.getNextUuidBigInteger(SEQConstants.t_car_num_list);
            carnum.setId(carId);
            carnum.setSys0AddTime(now);
            carnum.setSys0DelState(0);
            carNumListBaseService.insertCarNumList(carnum);
    	} else {
    		carnum = carNumLists.get(0);
    	}
    	
    	return carnum;
    }
    
    /**
     * 发送已缴费通知
     * 
     * @param gbid
     * @param plate
     * @param fee
     * @param payTime
     * @return
     */
    @RequestMapping("/reqPayFee.json")
    public void reqPayFee(BigInteger gbid, String plate, BigDecimal fee, String payTime) {
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setCarNum(plate);
    	accessMsgBean.setFee(fee);
    	accessMsgBean.setGbId(gbid);
    	accessMsgBean.setPayDate(payTime);
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_SEND_PAY_SUCCESS);
    	accessPublishHandler.sendMessage(accessMsgBean);
    }

    /**
     * 查询临时车缴费详情
     * 
     * @param tmpCarPayDetail
     * @return
     */
    @RequestMapping("/qryParkingFeeDetail.json")
    @ResponseBody
    public JsonResponse qryParkingFeeDetail(String carNum, BigInteger orderId) {
        final JsonResponse jsonResponse = new JsonResponse();

        TmpCarPayDetail tmpCarPayDetail = accessService.qryParkingFeeDetail(orderId);
        if (tmpCarPayDetail != null) {
        	jsonResponse.putData("couponAmount", tmpCarPayDetail.getCouponAmount());
        	jsonResponse.putData("ucAmount", tmpCarPayDetail.getUcAmount());
        	jsonResponse.putData("orderNo", tmpCarPayDetail.getOrderNo());
        	jsonResponse.putData("payMoney", tmpCarPayDetail.getPayMoney());
        	jsonResponse.putData("payTime", tmpCarPayDetail.getPayTime());
            if (tmpCarPayDetail.getPayMethod() != null) {
                switch (tmpCarPayDetail.getPayMethod()) {
                case 1:
                	jsonResponse.putData("payMethod", "微信支付");
                    break;
                case 2:
                	jsonResponse.putData("payMethod", "支付宝支付");
                    break;
                case 3:
                	jsonResponse.putData("payMethod", "银联支付");
                    break;
                case 4:
                	jsonResponse.putData("payMethod", "纯粮票支付");
                    break;
                case 5:
                	jsonResponse.putData("payMethod", "纯积分支付");
                    break;
                case 6:
                	jsonResponse.putData("payMethod", "微信轻应用支付");
                    break;
                case 7:
                	jsonResponse.putData("payMethod", "纯优惠券支付");
                    break;
                case 8:
                	jsonResponse.putData("payMethod", "纯折扣支付");
                    break;
                case 9:
                	jsonResponse.putData("payMethod", "银行卡支付");
                    break;
                }
            } else {
            	jsonResponse.putData("payMethod", "");
            }

            if (tmpCarPayDetail.getPayStatus() != null) {
                switch (tmpCarPayDetail.getPayStatus()) {
                case 1:
                	jsonResponse.putData("payStatus", "支付确认中");
                    break;
                case 2:
                	jsonResponse.putData("payStatus", "支付成功");
                    break;
                case 3:
                	jsonResponse.putData("payStatus", "支付失败");
                    break;
                }
            } else {
            	jsonResponse.putData("payStatus", "支付确认中");
            }
            setHLBConfig((Map<String, Object>)jsonResponse.getDataValue(), carNum);
            jsonResponse.setStatus("0000");
        } else {
        	jsonResponse.setStatus("0001");
        	jsonResponse.setMessage("暂无订单信息");
            jsonResponse.setDataValue(null);
        }
        return jsonResponse;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public final static String getDistanceTime(Date one, Date two) {
        long day = 0;
        long hour = 0;
        long min = 0;
        // long sec = 0;
        long time1 = one.getTime();
        long time2 = two.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        // sec = (diff/1000-day*24*60*60-hour*60*60-min*60);

        StringBuilder result = new StringBuilder();
        if (day != 0) {
            result.append(day + "天");
        }
        if (day != 0 || hour != 0) {
            result.append(hour + "小时");
        }
        result.append(min + "分");
        return result.toString();
    }
    
    /**
     * 查询所有连接
     * 
     * @return
     */
    @RequestMapping("/queryTcpLinks.json")
    @ResponseBody
    public synchronized JsonResponse queryTcpLinks(){
    	String uuid = UUIDGenerater.getId();
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setUuid(uuid);
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_QUERY_ALL_LINKS);
    	
    	// 消息广播前清空队列中的消息
    	accessPublishHandler.sendMessage(accessMsgBean);
    	
    	Integer waitTime = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.QUERY_ALL_LINKS_WAITING_MAX_TIME, 8*1000);
    	Integer apiNodeCount = CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.API_NODE_COUNT, 2);
    	long begin = System.currentTimeMillis();
    	long end = System.currentTimeMillis();
    	
    	final String countKey = uuid+"count";
    	final String key = uuid + "links";
    	String v = RedisCacheHandler.get(countKey);
    	while ((v == null || Integer.parseInt(v)<apiNodeCount) && ((end - begin) < waitTime)) {
    		try {
				Thread.sleep(HpfCarDict.WHILE_WAITING_TIME);
			} catch (InterruptedException e) {
				logger.error("InterruptedException", e);
			}
    		end = System.currentTimeMillis();
    		v = RedisCacheHandler.get(countKey);
    	}
		
		List<AccessLinkDto> accessLinks = new ArrayList<AccessLinkDto>();
		
		List<String> links = RedisCacheHandler.lpopAll(key);
		if(links!=null && links.size()>0){
			for(String link:links){
				AccessLinkDto accessLink = JSON.parseObject(link, AccessLinkDto.class);
				accessLinks.add(accessLink);
			}
		}
		
		RedisCacheHandler.del(countKey);
    	RedisCacheHandler.del(key);
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(accessLinks);
    	return jsonResponse;
    }
    
    /**
     * 断开连接
     * 
     * @param ioSessionId
     */
    @RequestMapping("/closeTcpLinkByIoSessionId.json")
    public void closeTcpLinkByIoSessionId(long ioSessionId){
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setIoSessionId(ioSessionId);
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_BREAK_LINK);
    	
    	accessPublishHandler.sendMessage(accessMsgBean);
    }
    
    /**
     * 根据ip断开连接（黑名单）
     * 
     * @param remoteIp
     */
    @RequestMapping("/closeTcpLinkByIp.json")
    public void closeTcpLinkByIp(String remoteIp){
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setRemoteIp(remoteIp);
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_ADD_BLACK_LIST);
    	
    	accessPublishHandler.sendMessage(accessMsgBean);
    }
    
    /**
     * 移除黑名单
     * 
     * @param remoteIp
     */
    @RequestMapping("/removeBlackList.json")
    public void removeBlackList(String remoteIp){
    	AccessMsgBean accessMsgBean = new AccessMsgBean();
    	accessMsgBean.setRemoteIp(remoteIp);
    	accessMsgBean.setMsgType(AccessDict.MsgType.W_REMOVE_BLACK_LIST);
    	
    	accessPublishHandler.sendMessage(accessMsgBean);
    }
    
    /**
     * 设置胡萝卜广告配置
     * 
     * @param map
     * @param plate
     */
    public void setHLBConfig(Map<String, Object> map, String plate){
    	String adImageUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.HLB_AD_IMG_URL);
    	String adUrl = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.HLB_CAR_INSURANCE_URL);
    	String key = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.HLB_KEY);
    	
    	map.put("adImageUrl", adImageUrl);
    	map.put("adUrl", adUrl+"?provider=Linlile&channelId=1005&actId=1&fkw=ins&carNo="+plate);
    	map.put("adKey", key);
    	
    	BigInteger groupBuildingId = null;
    	BigInteger userId = UserContext.getOperIdBigInteger();
    	if(userId!=null){
    		final RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
    		if(rewv!=null){
    			RealRoomEntity realRoomEntity = rewv.getRealRoomEntity();
	            if(realRoomEntity!=null){
	            	BuildingEntity buildingEntity = realRoomEntity.getBuildingEntity();
	            	if(buildingEntity!=null){
	            		groupBuildingId = buildingEntity.gettGroupBuildingFId();
	            	}
	            }
    		}
    	}
    	if(groupBuildingId!=null){
    		GroupBuilding groupBuilding = groupBuildingBaseService.getGroupBuildingBySeqId(groupBuildingId);
    		if(groupBuilding==null || (groupBuilding.getHlbSwitch()!=null && groupBuilding.getHlbSwitch()==1)){
    			map.put("isAd", true);
    		} else {
    			map.put("isAd", false);
    		}
    	} else {
    		map.put("isAd", true);
    	}
    }

    /**
     * @author wangzhe
     * @date 2016年6月28日
     * @description 查询新版本事宜
     *
     * @return
     */
    @RequestMapping("/qryPluginInfo.json")
    @ResponseBody
    public JsonResponse qryPluginInfo(){
        Map<String, Object> valueMap = new HashMap<String, Object>();
        JsonResponse jsonResponse = new JsonResponse();
        AppVersionEntity version = versionService.getLastVersionInfo(VersionConstant.Might_PluginId,null);
        valueMap.put("lastModified", version.getVersion());
        valueMap.put("downloadUrl",  version.getVersionDwonUrl());
        jsonResponse.setDataValue(valueMap);
        return jsonResponse;
    }
    
    /**
     * 查询车禁缴费随机立减优惠金额  
     * @param carNum 车牌号
     * @param payType  缴费类型=={"0":"临停车缴费","1":"月卡缴费"}
     * @param request
     * @return
     */
    @RequestMapping("/getPayCarPrefer.json")
    @ResponseBody
    public JsonResponse getPayCarPrefer(String carNum, Integer payType, HttpServletRequest request){
    	// 临停车缴费小区id
    	BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
    	// 月卡缴费月数
    	int payMonth = ParamUtils.getInt(request, "payMonth", 0);
    	// 临停车缴费金额（单位：元）
    	double amount = request.getParameter("amount")==null ? 0:Double.parseDouble(String.valueOf(request.getParameter("amount")));
    	int carOpenStatus = 0;
		if (JFQCarType.MONTH_CAR.compareTo(payType) == 0) {
    		Map<String, Object> paramMap = new HashMap<String, Object>();
    		paramMap.put("tCarNum", carNum);
    		List<CarNumList> carNumLists = carNumListBaseService.getCarNumListByCondition(paramMap);
    		gbId = carNumLists.get(0).gettGroupBuildingFId();
    		carOpenStatus = getMonthCarOpenStatus(gbId);
    	} else {
    		carOpenStatus = getTmpCarOpenStatus(gbId);
    	}
    	
    	Map<String, Object> payCarPreferMap = getPayCarPreferMap(gbId, payMonth, amount, carNum, payType, carOpenStatus, request.getSession().getId());
    	
		JsonResponse jsonResponse = new JsonResponse();
	    jsonResponse.putDataAll(payCarPreferMap);
		return jsonResponse;
    }
    
    /**
     * 
     * @param gbId  临停车缴费小区id
     * @param payMonth  月卡缴费月数
     * @param amount 临停车缴费金额（单位：元）
     * @param carNum
     * @param payType
     * @param sessionId
     * @return
     */
    public Map<String, Object> getPayCarPreferMap(BigInteger gbId, int payMonth, double amount, String carNum, Integer payType, Integer carOpenStatus, String sessionId){
    	// FIXME:V510要改为根据t_car_num_list的f_id查询
    	Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCarNum", carNum);
		List<CarNumList> carNumLists = carNumListBaseService.getCarNumListByCondition(paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(carNumLists!=null && carNumLists.size()>0){
			CarNumList carNumList = carNumLists.get(0);
			CarPreferParam carPreferParam = new CarPreferParam();
	    	carPreferParam.setCarId(carNumList.getId());
	    	carPreferParam.setUserId(UserContext.getOperIdMustExistBigInteger());
	    	carPreferParam.setPayType(payType);
	    	carPreferParam.setCarTmpOpenStatus(carOpenStatus);
	    	BigInteger totalAmount = BigInteger.ZERO;
	    	
			if (AccessDict.JFQCarType.UN_MONTH_CAR.compareTo(payType) == 0) {// 临停车
	    		totalAmount = BigInteger.valueOf(PriceUtil.multiply100(amount));
	    		carPreferParam.setGbId(gbId);
	    		carPreferParam.setAmount(totalAmount);
	    	} else {// 月卡缴费：小区id从t_car_num_list表中取
	    		MonthCarInfo monthCarInfo = thirdCarFactory.getMonthCarPayInfo(gbId, carNum, payMonth);
	    		
	    		totalAmount = BigInteger.valueOf(monthCarInfo.getRealAmt());
	    		
	    		carPreferParam.setGbId(carNumList.gettGroupBuildingFId());
	    		carPreferParam.setPayMonth(payMonth);
	    		carPreferParam.setAmount(totalAmount);
	    	}
	    	
	    	CarPreferDto carPreferDto = accessService.getPayCarPrefer(carPreferParam);
	    	BigInteger couponAmount = carPreferDto.getAmount();
	    	//免单处理：免单时，最小金额为1分钱
	    	if(couponAmount.intValue()>=totalAmount.intValue()){
	    		couponAmount = totalAmount.subtract(new BigInteger("1"));
	    		if(couponAmount.intValue()<0){
	    			couponAmount = BigInteger.ZERO;
	    		}
	    		carPreferDto.setAmount(couponAmount);
	    		
	    		CarPrefer carPrefer = new CarPrefer();
	    		carPrefer.setId(carPreferDto.getId());
	    		carPrefer.setCouponAmount(couponAmount.longValue());
	    		carPreferBaseService.updateCarPrefer(carPrefer);
	    	}
	    	// 返回金额单位：元
	    	resultMap.put("couponAmount", couponAmount.intValue()*1.0/100);
	    	resultMap.put("carPreferId", carPreferDto.getId());
		} else {
			resultMap.put("couponAmount", "0.00");
		}
		return resultMap;
    }
    
    /**
     * 根据小区id查询是否开始临停车缴费优惠
     * @param gbId
     * @return
     */
    @RequestMapping("/getCarTmpIsOpen.json")
    @ResponseBody
    public JsonResponse getCarTmpIsOpen(BigInteger gbId){
    	JsonResponse jsonResponse = new JsonResponse();
    	jsonResponse.putData("carTmpIsOpen", getTmpCarOpenStatus(gbId));
    	return jsonResponse;
    }
    
    /**
     * 查询临停车是否开启优惠
     * @param gbId
     * @return
     */
    private int getTmpCarOpenStatus(BigInteger gbId){
    	GroupBuilding groupBuilding = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
    	return groupBuilding.getCarTmpIsOpen()==null?0:groupBuilding.getCarTmpIsOpen();
    }
    
    /**
     * 查询月卡车是否开启优惠
     * @param gbId
     * @return
     */
    private int getMonthCarOpenStatus(BigInteger gbId){
    	GroupBuilding groupBuilding = groupBuildingBaseService.getGroupBuildingBySeqId(gbId);
    	return groupBuilding.getCarMonthIsOpen()==null?0:groupBuilding.getCarMonthIsOpen();
    }

    @RequestMapping("/getCarNumPrefixByGbId.json")
    @ResponseBody
    public JsonResponse getCarNumPrefixByGbId(BigInteger gbId){
    	String carNumPrefix = accessService.getCarNumPrefixByGbId(gbId);
    	
    	JsonResponse jsonResponse = new JsonResponse();
    	jsonResponse.setDataValue(carNumPrefix);
    	return jsonResponse;
    }
    
    /**
     * 获取停车场列表
     * @return
     */
    @RequestMapping("/getPloList.json")
    @ResponseBody
    public JsonResponse getPloList(){
    	JSONObject ploList = xmfCarService.getPloList();
    	
    	JsonResponse jsonResponse = new JsonResponse();
    	jsonResponse.setDataValue(ploList);
    	return jsonResponse;
    }
    
    /**
     * 查询解放区停车场
     * 
     * @param name
     * @return
     */
    @RequestMapping("/getJFQPlots.json")
    @ResponseBody
    public JsonResponse getJFQPlots(String name) {
    	JsonResponse jsonResponse = new JsonResponse();
    	jsonResponse.putData("list", accessService.getJFQPlots(name));
    	return jsonResponse;
    }

    /**
     * 查询车牌所在的停车场小区id
     * @return
     */
    @RequestMapping("/getGbIdByCarNum.json")
    @ResponseBody
    public JsonResponse getGbIdByCarNum(final String carNum){
        BigInteger gbId = tempCarPlotService.qryTmpCarGbId(carNum);
        
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDataValue(gbId);
        return jsonResponse;
    }
	
    /**
     * 查询停车场信息（轻应用导航）
     * @param city
     * @param plotName
     * @return
     */    
    @RequestMapping("/getPlots.json")
    @ResponseBody
    public JsonResponse getPlots(String city, String plotName) {
    	List<PlotInfo> plotInfo = accessService.getPlots(city, plotName);
    	
    	JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setDataValue(plotInfo);
        return jsonResponse;
    }
}
