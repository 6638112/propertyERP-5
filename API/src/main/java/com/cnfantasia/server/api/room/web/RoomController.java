package com.cnfantasia.server.api.room.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.entity.UserRoomApplyEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.util.CommonRoomUtil;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.entity.LoginNoEntity;
import com.cnfantasia.server.api.notice.entity.NoticeBean;
import com.cnfantasia.server.api.notice.service.INoticeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.entity.SimpleUserDetails;
import com.cnfantasia.server.api.pub.entity.SysUser;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.entity.BuildingAndUnitEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomBaseInfo;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.entity.RoomInfoPicParamEntity;
import com.cnfantasia.server.api.room.entity.SimpleRoomInfo;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.api.room.util.RoomInfoEncodeUtil;
import com.cnfantasia.server.api.user.entity.UserHasTRoomEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoom.service.IRealRoomBaseService;
import com.cnfantasia.server.domainbase.room.entity.Room;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.UserBaseService;
import com.cnfantasia.server.domainbase.userHasTMessage.dao.IUserHasTMessageBaseDao;
import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;
import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;
import com.cnfantasia.server.domainbase.userSwitchGb.service.IUserSwitchGbBaseService;
import com.cnfantasia.server.ms.pub.securityUpdateUser.DefaultUserAuthenticationToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/room") 
public class RoomController extends BaseController {
	private Log logger = LogFactory.getLog(getClass());
	private IRoomService roomService;
	public void setRoomService(IRoomService roomService) {
		this.roomService = roomService;
	}
//	private IRealRoomBaseService realRoomBaseService;
//	public void setRealRoomBaseService(IRealRoomBaseService realRoomBaseService) {
//		this.realRoomBaseService = realRoomBaseService;
//	}
	private UserBaseService userBaseService;
	public void setUserBaseService(UserBaseService userBaseService) {
		this.userBaseService = userBaseService;
	}
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	private ISysParamParser roomInfoPicParamParser;
	public void setRoomInfoPicParamParser(ISysParamParser roomInfoPicParamParser) {
		this.roomInfoPicParamParser = roomInfoPicParamParser;
	}

	@Resource
	private IUserSwitchGbBaseService userSwitchGbBaseService;
	//	private ISysParamParser userImageParamParser;
//	public void setUserImageParamParser(ISysParamParser userImageParamParser) {
//		this.userImageParamParser = userImageParamParser;
//	}
	
	private ICommEntityConvertService commEntityConvertService;
	public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
		this.commEntityConvertService = commEntityConvertService;
	}
	
	private ICommonRoomService commonRoomService;
	public void setCommonRoomService(ICommonRoomService commonRoomService) {
		this.commonRoomService = commonRoomService;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IDualDao dualDao;
	public void setDualDao(IDualDao dualDao) {
		this.dualDao = dualDao;
	}
	
	@Resource
	private INoticeService noticeService;
	
	@Resource
	private IUuidManager uuidManager;
	
	@Resource
	private IUserHasTMessageBaseDao userHasTmessageBaseDao;
	
	@RequestMapping("/getRoomListById.json")
	@ResponseBody
	public JsonResponse getRoomListById(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		BigInteger buildingId = ParamUtils.getBigInteger(request, "buildingId", null);
		// 2.交互
		List<RealRoom> list = roomService.getRealRoomListByBuildId(buildingId);
		// 3.结果处理
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (RealRoom tmp : list) {
			Map<String, Object> tmpMap = commEntityConvertService.realRoom2Map(tmp);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList);
	}
	
//	@RequestMapping("/getRoomListById.json")
//	@ResponseBody
//	public JsonResponse getRoomListById(HttpServletRequest request) {
//		JsonResponse jsonResponse = new JsonResponse();
//		// 1.搜集参数
//		String buildingId = request.getParameter("buildingId");
//		RealRoom qry = new RealRoom();
//		qry.settBuildingFId(new BigInteger(buildingId));
//		// 2.交互
//		List<RealRoom> list = realRoomBaseService.getRealRoomByCondition(MapConverter.toMap(qry));
//		// 3.结果处理
//		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
//		for (RealRoom tmp : list) {
//			Map<String, Object> tmpMap = commEntityConvertService.realRoom2Map(tmp);
//			resList.add(tmpMap);
//		}
//		return ControllerUtils.addPageInfo(jsonResponse, resList);
//	}

	// /**
	// * 创建门牌
	// *
	// * @param request
	// * @return
	// */
	// @RequestMapping("/addRoom.json")
	// @ResponseBody
	// public JsonResponse addRoom(HttpServletRequest request) {
	// JsonResponse jsonResponse = new JsonResponse();
	// // 获取登录用户信息
	// BigInteger userId = UserContext.getOperIdMustExistBigInteger();
	// if (StringUtils.isEmpty(userId)) {
	// jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
	// jsonResponse.setMessageKey(("room.addRoom.getUserId.failed"));
	// return jsonResponse;
	// }
	// // 1.搜集参数
	// BigInteger realRoomId = new BigInteger(request.getParameter("roomNum"));//
	// 这里要注意，此处roomNum是真实房间的唯一标识
	// // 是否设置为默认门牌
	// String isDefaultStr = request.getParameter("isDefault");
	// Boolean isDefault = false;
	// if (!StringUtils.isEmpty(isDefaultStr) &&
	// Boolean.parseBoolean(isDefaultStr)) {
	// isDefault = true;
	// }
	// // 用户姓名
	// String realName = request.getParameter("realName");
	// // 如果需要创建新的真实房间需要的信息：所属单元Id即building，真实房间名称roomName如A301
	// BigInteger tBuildingFId = null;
	// String building = request.getParameter("building");
	// if (StringUtils.isEmpty(building)) {
	// tBuildingFId = new BigInteger(building);
	// }
	// String roomName = request.getParameter("roomName");
	// // 2.交互
	// // 执行添加相关操作
	// roomService.addRomm(userId, realRoomId, tBuildingFId, roomName, isDefault,
	// realName);
	// // 3.结果处理
	// return jsonResponse;
	// }
	
	
//	/**
//	 * 申请加入门牌
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/applyJoinRoom.json")
//	@ResponseBody
//	public JsonResponse applyJoinRoom(HttpServletRequest request) {
//		JsonResponse jsonResponse = new JsonResponse();
//		//1.搜集参数
//		BigInteger roomId = new BigInteger(request.getParameter("roomId"));//此处roomId是虚拟门牌Id
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		//2.交互
//		roomService.applyJoinRoom(userId,roomId);
//		//3.结果处理
//		return jsonResponse;
//	}
	
	/**
	 * 申请加入门牌(面对面邀请)
	 * @param request
	 * @return
	 */
	@RequestMapping("/applyJoinRoomFace2Face.json")
	@ResponseBody
	public JsonResponse applyJoinRoomFace2Face(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger inviterId = new BigInteger(request.getParameter("inviterId"));
		String roomInfo = request.getParameter("roomInfo");//此处roomInfo是待加入的门牌加密信息 
		SimpleRoomInfo simpleRoomInfo = RoomInfoEncodeUtil.decode(roomInfo,inviterId);//TODO 加密待完善 
		BigInteger roomId = simpleRoomInfo.getRoomId();
		//2.交互
		BigInteger applyUserId = UserContext.getOperIdMustExistBigInteger();
		roomService.applyJoinRoomFace2Face(applyUserId, roomId, inviterId);
		//3.结果处理
		return jsonResponse;
	}
	
	/**
	 * 决策是否同意加入门牌
	 * @param request
	 * @return
	 */
	@RequestMapping("/decideJoinRoom.json")
	@ResponseBody
	public JsonResponse decideJoinRoom(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger applyId = new BigInteger(request.getParameter("applyId"));//申请的记录Id
		Boolean isAgree = Boolean.valueOf(request.getParameter("isAgree"));
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean res = roomService.decideJoinRoom(userId,applyId,isAgree);
		if(!res){
			throw new BusinessRuntimeException("RoomController.decideJoinRoom.failed");
		}
		//3.结果处理
		return jsonResponse;
	}
	
//	/**
//	 * 加入门牌
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/joinRoom.json")
//	@ResponseBody
//	public JsonResponse joinRoom(HttpServletRequest request) {
//		JsonResponse jsonResponse = new JsonResponse();
//		// 获取登录用户信息
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		// 1.搜集参数
//		BigInteger roomId = new BigInteger(request.getParameter("roomNum"));// 这里要注意，此处roomNum是虚拟门牌Id
//		// 是否设置为默认门牌
//		String isDefaultStr = request.getParameter("isDefault");
//		Boolean isDefault = false;
//		if (!StringUtils.isEmpty(isDefaultStr) && Boolean.parseBoolean(isDefaultStr)) {
//			isDefault = true;
//		}
//		// 2.交互
//		roomService.joinRoom(userId, roomId, isDefault);
//		// 3.结果处理
//		return jsonResponse;
//	}

	/**
	 * 查询当前门牌列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryRoomList.json")
	@ResponseBody
	public JsonResponse qryRoomList(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 获取登录用户信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// 1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		// 2.交互
		List<UserHasTRoomEntity> roomTmpList = roomService.getRoomList(userId, pageModel);
		//查询当前用户默认门牌
		BigInteger defaultRoomId = userBaseService.getUserBySeqId(userId).getDefaultRoomId();
		// 3.结果处理
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for (UserHasTRoomEntity tmpRoom : roomTmpList) {
			RoomEntityWithValidate roomEntity = tmpRoom;
			if(roomEntity==null||roomEntity.checkIsEmptyRoom()){
				continue;//
			}
			Map<String,Object> tmpMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(),defaultRoomId);
			String realRoomAdminNameDesc = tmpRoom.getRealRoomAdminName()==null?"":"管理员:"+tmpRoom.getRealRoomAdminName();
			tmpMap.put("propertyProprietorName",realRoomAdminNameDesc);
			resList.add(tmpMap);
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	private boolean checkInRoomList(BigInteger userId,BigInteger roomId){
		boolean isIn = false;
		List<Room> roomList = commonRoomService.getUserRoomList(userId);
		if(roomList!=null&&roomList.size()>0){
			for(Room tmpRoom:roomList){
				if(tmpRoom.getId().compareTo(roomId)==0){
					isIn = true;
				}
			}
		}
		return isIn;
	}
	
	@RequestMapping("/setDefaultRoom.json")
	@ResponseBody
	public JsonResponse setDefaultRoom(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 获取登录用户信息
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// 1.搜集参数
		BigInteger roomId = new BigInteger(request.getParameter("roomId"));
		// 2.交互
		boolean isIn = checkInRoomList(userId, roomId);
		if(isIn){
			User user = new User();
			user.setId(userId);
			user.setDefaultRoomId(roomId);
			int userUpdRes = userBaseService.updateUser(user);
			if (userUpdRes <= 0) {
				throw new BusinessRuntimeException("room.setDefaultRoom.updUserDefaultRoom.failed");
			}

			//门牌切换成功后，刷新springsecurity的用户门牌信息
			LoginNoEntity loginNoEntity = UserContext.getCurrLoginNo();
			loginNoEntity.getUserEntity().setDefaultRoomId(roomId);
			SysUser sysUser = new SysUser(loginNoEntity);
			SimpleUserDetails userDetails = (SimpleUserDetails) SecurityContextHolder.getContext()
					.getAuthentication()
					.getPrincipal();
			userDetails.setSysUser(sysUser);
			DefaultUserAuthenticationToken token = new DefaultUserAuthenticationToken(userDetails);
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		//添加最近小区记录
		Map<String, Object> addressIds = commonRoomService.getRoomAddressIdByRoom(roomId);
		UserSwitchGb switchGb = new UserSwitchGb();
		switchGb.setGbId(BigInteger.valueOf((Long)addressIds.get("gbId")));
		switchGb.setUserId(userId);
		switchGb.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_user_switch_gb));
		switchGb.setSys0AddTime(DateUtil.getCurrSysTimeStr());
		userSwitchGbBaseService.insertUserSwitchGb(switchGb);
		// 3.结果处理
		return jsonResponse;
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping("/qryUserList.json")
	@ResponseBody
	public JsonResponse qryUserList(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		PageModel pageModel = ControllerUtils.getPageModel(request);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		List<UserRoomApplyEntity> userList = commonRoomService.getUserListOfSameRoomByUserId(userId,pageModel);
		//3.结果处理
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>();
		if(userList!=null){
			for(UserRoomApplyEntity tmpUserRoomApplyEntity:userList){
				UserSimpleEntity tmpUser = tmpUserRoomApplyEntity.getApplyUser();
//				tmpMap.put("applyStatus", tmpUserRoomApplyEntity.getApplyStatus());//0:未处理;1: 已同意;2:已拒绝 ;(目前只返回状态为0和1的数据)
				Integer validedStatus = null;
				if(userId.compareTo(tmpUserRoomApplyEntity.gettUserFId())==0){
					validedStatus = 1;
				}else{
					
					validedStatus = tmpUserRoomApplyEntity.checkIsValidedSuccess()?1:0;
				}
				Map<String,Object> tmpMap = commEntityConvertService.baseUser2MapForApply(tmpUser,validedStatus,tmpUserRoomApplyEntity.getId());
//				tmpMap.put("applyStatus", validedStatus);//0:未处理;1: 已同意;2:已拒绝 ;(目前只返回状态为0和1的数据)
//				tmpMap.put("applyId", tmpUserRoomApplyEntity.getId());
				resList.add(tmpMap);
			}
		}
		return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
	}
	
	@RequestMapping("/delUser.json")
	@ResponseBody
	public JsonResponse delUser(HttpServletRequest request){
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		String toDelUserListStr = request.getParameter("toDelUserList");
		List<BigInteger> toDelUserList = null;
		if(!StringUtils.isEmpty(toDelUserListStr)){
			toDelUserList = JSON.parseArray(toDelUserListStr, BigInteger.class);
		}
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		commonRoomService.removeUserOfSameRoom(userId, toDelUserList);
		//3.结果处理
		return jsonResponse;
	}
	
	@RequestMapping("/qryDefaultRoomInfo.json")
	@ResponseBody
	public JsonResponse qryDefaultRoomInfo(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		// 获取当前门牌信息
		RoomEntityWithValidate roomEntity = roomService.getDefaultRoomInfo(userId);
		//3.结果处理
		Map<String,Object> defaultRoomInfoMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(),roomEntity.getId());
		jsonResponse.setDataValue(defaultRoomInfoMap);
		return jsonResponse;
	}
	
	@RequestMapping("/qryDefaultRoomInfo2.json")
	@ResponseBody
	public JsonResponse qryDefaultRoomInfo2(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// BigInteger userId = new BigInteger("50076");
		
		//2.交互
		// 获取当前门牌信息
		RoomEntityWithValidate roomEntity = roomService.getDefaultRoomInfo(userId);
		//3.结果处理
		Map<String,Object> defaultRoomInfoMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(),roomEntity.getId());
		String roomNum = StringUtils.isEmpty(roomEntity.getRealRoomEntity().getNumTail())? roomEntity.getRealRoomEntity().getNum():roomEntity.getRealRoomEntity().getNumTail();
		
		defaultRoomInfoMap.put("roomNum", roomNum);
		defaultRoomInfoMap.put("unitName", roomEntity.getRealRoomEntity().getUnitName());
		defaultRoomInfoMap.put("buildingId", roomEntity.getRealRoomEntity().gettBuildingFId());
		defaultRoomInfoMap.put("groupBuildingId", roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getId());
		
		jsonResponse.setDataValue(defaultRoomInfoMap);
		return jsonResponse;
	}
	
	@RequestMapping("/qryBuildingInfoByGroupBuildingId.json")
	@ResponseBody
	public JsonResponse qryBuildingInfoByGroupBuildingId(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger groupBuildingId = ParamUtils.getBigInteger(request, "groupBuildingId", null);
		if(groupBuildingId==null){
			throw new ValidateRuntimeException("groupBuildingId can't be null");
		}
		
		//2.交互
		//3.结果处理
		List<BuildingAndUnitEntity> buildingList = roomService.getBuildingListByGroupBuildingId(groupBuildingId);
		jsonResponse.putData(PageModel.PageKey.LIST,buildingList);
		
		return jsonResponse;
	}
	
	@Resource
	IRealRoomBaseService realRoomBaseService;
	
	@RequestMapping("/qryRoomListByBuildingId.json")
	@ResponseBody
	public JsonResponse qryRoomListByBuildingId(HttpServletRequest request) {
		
		/**
		 * 用来排序
		 * @author wenfq
		 *
		 */
		class RoomInfo implements Comparable<RoomInfo> {
			BigInteger id;
			String roomNum;
			public BigInteger getId() {
				return id;
			}
			public void setId(BigInteger id) {
				this.id = id;
			}
			public String getRoomNum() {
				return roomNum;
			}
			public void setRoomNum(String roomNum) {
				this.roomNum = roomNum;
			}
			
			@Override
			public int compareTo(RoomInfo o) {
				return this.getRoomNum().compareTo(o.getRoomNum());
			}
			
		}
			
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger buildingId = ParamUtils.getBigInteger(request, "buildingId", null);
		String unitName = ParamUtils.getString(request, "unitName");
		
		//2.交互
		//3.结果处理
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sys0DelState", 0);
		paramMap.put("tBuildingFId", buildingId);
		paramMap.put("checkStatus", 1);
		if(org.apache.commons.lang.StringUtils.isNotEmpty(unitName)){
			paramMap.put("unitName", unitName);
		}
		List<RealRoom> rrList1 = realRoomBaseService.getRealRoomByCondition(paramMap);
		paramMap.put("checkStatus", 9);
		List<RealRoom> rrList9 = realRoomBaseService.getRealRoomByCondition(paramMap);
		rrList9.addAll(rrList1);
		
		List<RoomInfo> resultList = new ArrayList<RoomInfo>();
		for (RealRoom rr : rrList9) {
			RoomInfo roomInfo  = new RoomInfo();
			roomInfo.setId(rr.getId());
			String roomNum = org.apache.commons.lang.StringUtils.isNotEmpty(rr.getNumTail()) ? rr.getNumTail() : rr.getNum();
			roomInfo.setRoomNum(roomNum);
			resultList.add(roomInfo);
		}
		Collections.sort(resultList);
		jsonResponse.putData(PageModel.PageKey.LIST, resultList);
		
		return jsonResponse;
	}
	
	@RequestMapping("/qryDefaultRoomPic.json")
	@ResponseBody
	public JsonResponse qryDefaultRoomPic(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// 2.交互
		// 获取当前门牌信息
		RoomEntityWithValidate roomEntity = roomService.getDefaultRoomInfo(userId);
		if(roomEntity==null||roomEntity.checkIsEmptyRoom()){
			throw new BusinessRuntimeException("room.qryDefaultRoomPic.notSet.failed");
		}
		String addressTotalDetail = CommonRoomUtil.getAddressTotalDetail(roomEntity);
		RoomBaseInfo roomBaseInfo =CommonRoomUtil.getRoomBaseInfo(roomEntity);
		if(addressTotalDetail==null){
			throw new ValidateRuntimeException("room.qryDefaultRoomPic.roomBaseInfo.isNull");
		}
//		String contents = new StringBuffer().append(addressTotalDetail).append(";")/*.append(roomEntity.getId())*/.toString();
		String appDownloadBaseUrl = sysParamManager.getSysParaValue(SysParamKey.APP_DOWNLOAD_BASEURL);
		
		StringBuffer contentsSbf = new StringBuffer(appDownloadBaseUrl);
		
		BigInteger inviterId = userId;//邀请人Id
		String roomInfo = null;
		String roomName = null;//用户待加入的门牌描述
		String nowTime = dualDao.getNowTime();
		String roomShowId = null;
		try {//注意拼字符串的时候要过滤&号 URLDecoder.encode 只对value进行encode操作
			{
				BigInteger roomId = roomEntity.getId();
				roomInfo = RoomInfoEncodeUtil.encode(roomId, nowTime,inviterId);
				roomShowId = roomEntity.getRealRoomEntity().getNum();
			}
			roomName =  CommonRoomUtil.getAddressDetail(roomEntity);
			boolean isFirst = false;
			if(!appDownloadBaseUrl.endsWith("?")){
				if(!appDownloadBaseUrl.contains("?")){
					contentsSbf.append("?");
					isFirst = true;
				}
			}
			if(!isFirst){
				contentsSbf.append("&");
			}
			contentsSbf.append("roomInfo=");
			contentsSbf.append(URLEncoder.encode(roomInfo, "UTF-8"));//对value进行encode操作
			contentsSbf.append("&roomName=");
			contentsSbf.append(URLEncoder.encode(roomName, "UTF-8"));//对value进行encode操作
			contentsSbf.append("&inviterId=");
			contentsSbf.append(URLEncoder.encode(inviterId.toString(), "UTF-8"));//对value进行encode操作
			contentsSbf.append("&roomShowId=");
			contentsSbf.append(URLEncoder.encode(roomShowId.toString(), "UTF-8"));//对value进行encode操作
		} catch (UnsupportedEncodingException e) {
			logger.debug("RoomController.qryDefaultRoomPic.encode.roomInfo cause exception,roomInfo is "+roomInfo+",roomName is "+roomName,e);
		}
		
		String contents = contentsSbf.toString();
		
		// 生成或者替换二维码图片
		// 用户门牌图片信息配置，本地路径，图片格式，长，宽
		// tmpfile/pic/roomInfo;png;200;200
		RoomInfoPicParamEntity roomInfoPicParamEntity = roomInfoPicParamParser.parseParamValue();
		// 用户Id+.+二维码图片类型
		String qCodeFileName = new StringBuffer().append(userId).append(".").append(roomInfoPicParamEntity.getGoalFileType()).toString();
		try {
			contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");// 如果不想更改源码，则将字符串转换成ISO-8859-1编码
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.MARGIN, 0);//白边处理
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE,
					roomInfoPicParamEntity.getWidth(), roomInfoPicParamEntity.getHeight(),hints);
			String relativeFilePath = new StringBuffer().append(roomInfoPicParamEntity.getPicBasePath()).append(qCodeFileName).toString();
			fileServerService.uploadQCodeFile(bitMatrix, roomInfoPicParamEntity.getGoalFileType(), relativeFilePath);
		} catch (Exception e) {
			logger.debug("MakeRoomPicFile for " + userId + " cause error,The error Message is:" + e.getMessage());
			throw new BusinessRuntimeException("room.qryDefaultRoomPic.makeRoomPicFile.failed",e);
		}
		// 3.结果处理
		{
			//使用用户虚拟门牌的时间更新二维码图片信息
			jsonResponse.putData("picUrl",fileServerService.getAccessUrl(roomInfoPicParamEntity.getPicBasePath() + qCodeFileName,nowTime));
			
			jsonResponse.putData("provinceName", roomBaseInfo.getProvince());
			jsonResponse.putData("cityName", roomBaseInfo.getCity());
			jsonResponse.putData("blockName", roomBaseInfo.getBlock());
			jsonResponse.putData("groupBuildingName", roomBaseInfo.getGroupBuilding());
			jsonResponse.putData("buildingName", roomBaseInfo.getBuilding());
			jsonResponse.putData("realRoomName", roomBaseInfo.getRoomNum());
			
			jsonResponse.putData("roomId", roomEntity.getId());
			jsonResponse.putData("roomVerifyStatus", roomEntity.fetchRoomValidteVerifyStatus());//返回我的门牌的验证状态
			
			jsonResponse.putData("totalAddress",addressTotalDetail);
		}
		return jsonResponse;
	}

	@RequestMapping("/addBuildingAndRoom.json")
	@ResponseBody
	public JsonResponse addBuildingAndRoom(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		BigInteger groupBuildingId = new BigInteger(request.getParameter("groupBuildingId"));
		String buildingName = request.getParameter("buildingName");
		String unitName = request.getParameter("unitName");
		String roomNum = request.getParameter("roomNum");
		Integer changeDefaultRoom = ParamUtils.getInteger(request, "changeDefaultRoom", 1);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//判断是否注册过该小区
		Boolean isNotBg = checkUserGb(userId,groupBuildingId);
		// 2.交互
		RoomEntityWithValidate roomEntity = roomService.addBuildingAndRoom(groupBuildingId, buildingName,unitName, roomNum, userId, changeDefaultRoom > 0);
		//是否推送公告
		addMsgFornewUser(isNotBg,groupBuildingId,userId,roomEntity.getId());
		// 3.结果处理
		Map<String,Object> resMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(), null);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	/**
	 * 判断是否注册过该小区
	 */
	private Boolean checkUserGb(BigInteger userId,BigInteger groupBuildingId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		Boolean isNotBg=false;
		List<GroupBuildingEntity> userGb = commonRoomService.queryUserIsRegisterGroupbuilding(paramMap);
		if(userGb!=null){
			//是否注册过该小区
			for(GroupBuildingEntity gb:userGb){
				if(groupBuildingId.compareTo(gb.getId())==0){
					isNotBg=false;
					break;
				}else{
					isNotBg=true;
				}
			}
		}else{
			isNotBg=true;
		}
		return isNotBg;
	}
	
	/**是否推送公告*/
	private void addMsgFornewUser(Boolean isNotBg,BigInteger groupBuildingId,BigInteger userId,BigInteger roomId){
		if(isNotBg){
			//关系表插入五条公告
			List<NoticeBean> list = noticeService.queryMsgForNewUser(groupBuildingId,userId);
			User user = userBaseService.getUserBySeqId(userId);
			List<UserHasTMessage> userList = new ArrayList<UserHasTMessage>();
			//批量获取用户消息关系表ID
			List<BigInteger> addIdsList = uuidManager.getNextUuidBigInteger(SEQConstants.t_user_has_t_message,list.size());
			if(list != null && list.size() > 0){
				for(NoticeBean notice :list){
					UserHasTMessage uhmsg = new UserHasTMessage();
					SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time = dateForma.format(notice.getNoticeDate());
					uhmsg.setSys0AddTime(time);
					uhmsg.settMessageFId(notice.getId());
					uhmsg.setUserType(1);
					uhmsg.setTryFailedCount(0L);
					uhmsg.setUserRoomId(roomId);
					uhmsg.settUserFId(user.getId());
					uhmsg.setSendStatus(0);
					uhmsg.setStatus(0);
					userList.add(uhmsg);
				}
				for (int i = 0; i < list.size(); i++) {
					userList.get(i).setId(addIdsList.get(i));
				}
				userHasTmessageBaseDao.insertUserHasTMessageBatch(userList);
			}
		}
	}

	@RequestMapping("/addGroupBuildingAndRoom.json")
	@ResponseBody
	public JsonResponse addGroupBuildingAndRoom(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		BigInteger addressBlockId = new BigInteger(request.getParameter("addressBlockId"));
		String groupBuildingName = request.getParameter("groupBuildingName");
		String buildingName = request.getParameter("buildingName");
		String unitName = request.getParameter("unitName");
		String roomNum = request.getParameter("roomNum");
		Integer changeDefaultRoom = ParamUtils.getInteger(request, "changeDefaultRoom", 1);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// 2.交互
		RoomEntityWithValidate roomEntity = roomService.addGroupBuildingAndRoom(addressBlockId, groupBuildingName, buildingName,unitName,roomNum, userId, changeDefaultRoom > 0);
		// 3.结果处理
		Map<String,Object> resMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(), null);
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}

	/**
	 * 小区存在(已签约)—加入建筑和门牌,创建虚拟门牌
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addVirtualRoomOnly.json")
	@ResponseBody
	public JsonResponse addVirtualRoomOnly(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		// 1.搜集参数
		BigInteger realRoomId = null;
		{
			String realRoomIdStr = request.getParameter("realRoomId");
			if(StringUtils.isEmpty(realRoomIdStr)){
				throw new BusinessRuntimeException("roomController.realRoomId.isEmpty");
			}else{
				realRoomId = new BigInteger(realRoomIdStr);
			}
		}
		Integer changeDefaultRoom = ParamUtils.getInteger(request, "changeDefaultRoom", 1);
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		// 2.交互
		GroupBuilding groupBuilding = roomService.getGroupBuildingbyRrid(realRoomId);
		//判断是否注册过该小区
		Boolean isNotBg = checkUserGb(userId,groupBuilding.getId());
		RoomEntityWithValidate roomEntity = roomService.addVirtualRoomOnly(groupBuilding.getId(), realRoomId, userId, changeDefaultRoom > 0);
		BigInteger bgId = roomEntity.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
		// 3.结果处理
		Map<String,Object> resMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(), null);
		//是否推送公告
		addMsgFornewUser(isNotBg,bgId,userId,roomEntity.getId());
		jsonResponse.setDataValue(resMap);
		return jsonResponse;
	}
	
//	/**
//	 * 将虚拟门牌信息填充到jsonResponse
//	 * @param jsonResponse
//	 * @param roomEntity
//	 * @return
//	 */
//	private JsonResponse afterRoomAdd(RoomEntity roomEntity){
//		JsonResponse jsonResponse = new JsonResponse();
//		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(roomEntity);
//		if(roomBaseInfo!=null){
//			jsonResponse.setDataValue(roomBaseInfo);
//		}
////		jsonResponse.putData("province", roomBaseInfo.getProvince());
////		jsonResponse.putData("city", roomBaseInfo.getCity());
////		jsonResponse.putData("block", roomBaseInfo.getBlock());
////		jsonResponse.putData("groupBuilding", roomBaseInfo.getGroupBuilding());
////		jsonResponse.putData("building", roomBaseInfo.getBuilding());
////		jsonResponse.putData("roomNum", roomBaseInfo.getRoomNum());
////		jsonResponse.putData("totalAddress", roomBaseInfo.getTotalAddress());
////		jsonResponse.putData("id", roomEntity.getId());
//		// 3.结果处理
//		return jsonResponse;
//	}
	
	
	/**
	 * 修改真实门牌
	 * @param request
	 * @return
	 */
	@RequestMapping("/changeRealRoom.json")
	@ResponseBody
	public JsonResponse changeRealRoom(HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		//1.搜集参数
		BigInteger roomId = new BigInteger(request.getParameter("roomId"));
		BigInteger realRoomId = null;//真实门牌Id
		BigInteger groupBuildingId = null;//小区Id
		BigInteger addressBlockId = null;//行政区Id
		String groupBuildingName = null;//小区名称
		String buildingName = null;//建筑名称
		String unitName = request.getParameter("unitName"); //单元号
		String roomNum = null;//门牌号
		{
			String realRoomIdStr = request.getParameter("realRoomId");
			if(!StringUtils.isEmpty(realRoomIdStr)){
				realRoomId = new BigInteger(realRoomIdStr);
			}
		}
		if(realRoomId==null){//真实门牌为空
			{
				String groupBuildingIdStr = request.getParameter("groupBuildingId");
				if(!StringUtils.isEmpty(groupBuildingIdStr)){
					groupBuildingId = new BigInteger(groupBuildingIdStr);
				}
			}
			buildingName = request.getParameter("buildingName");
			if(StringUtils.isEmpty(buildingName)){ throw new BusinessRuntimeException("room.changeRealRoom.buildingName.empty"); }
			roomNum = request.getParameter("roomNum"); 
			if(StringUtils.isEmpty(roomNum)){ throw new BusinessRuntimeException("room.changeRealRoom.roomNum.empty"); }
			if(groupBuildingId==null){
				{
					String addressBlockIdStr = request.getParameter("addressBlockId");
					if(!StringUtils.isEmpty(addressBlockIdStr)){
						addressBlockId = new BigInteger(addressBlockIdStr);
					}
				}
				if(addressBlockId==null){ throw new BusinessRuntimeException("room.changeRealRoom.addressBlockId.empty"); }
				groupBuildingName = request.getParameter("groupBuildingName");//小区名称
				if(StringUtils.isEmpty(groupBuildingName)){ throw new BusinessRuntimeException("room.changeRealRoom.groupBuildingName.empty"); }
			}
		}
		//用户Id
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		//2.交互
		boolean isIn = checkInRoomList(userId, roomId);
		
		if(isIn){
			RoomEntityWithValidate roomEntity = roomService.changeRealRoom(userId,roomId,realRoomId,groupBuildingId,addressBlockId,groupBuildingName,buildingName,unitName,roomNum);
			Map<String,Object> resMap = CommonRoomUtil.getRoomInfo(userId,roomEntity, roomEntity.getRoomValidate(), roomEntity.getId());

			//删除旧公告
			UserHasTMessage hasTMessage = new UserHasTMessage();
			hasTMessage.setUserRoomId(roomId);
			List<UserHasTMessage> hasTMessageList = userHasTmessageBaseDao
					.selectUserHasTMessageByCondition(MapConverter.toMap(hasTMessage), false);
			if (!DataUtil.isEmpty(hasTMessageList)) {
				List<BigInteger> delIds = new ArrayList<BigInteger>(hasTMessageList.size());
				for (UserHasTMessage userHasTMessage : hasTMessageList) {
					delIds.add(userHasTMessage.getId());
				}
				userHasTmessageBaseDao.deleteUserHasTMessageLogicBatch(delIds);
			}
			//判断是否注册过该小区
//			groupBuildingId = roomEntity.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
//			Boolean isNotBg = checkUserGb(userId,groupBuildingId);
			//是否推送公告
			Map<String, Object> roomAddressIdByRoom = commonRoomService.getRoomAddressIdByRoom(roomId);
			BigInteger updRoomGbId = new BigInteger(roomAddressIdByRoom.get("gbId").toString());
			addMsgFornewUser(true,updRoomGbId,userId,roomId);
			jsonResponse.setDataValue(resMap);
		}
		//3.结果处理
		return jsonResponse;
	}
	
}
