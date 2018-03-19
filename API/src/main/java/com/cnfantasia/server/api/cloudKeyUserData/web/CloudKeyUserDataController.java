package com.cnfantasia.server.api.cloudKeyUserData.web;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cloudKeyUserData.service.ICloudKeyUserDataService;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.room.service.IRoomService;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.service.IUserService;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.RandomUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.service.ICloudKeyUserAuditBaseService;
import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 业主门禁认证（可配置）信息
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午6:52:07
 */
@Controller
@RequestMapping("/cloudKeyUserData")
public class CloudKeyUserDataController  extends BaseController{
	
	@Resource(name="cloudKeyUserDataService")
	private ICloudKeyUserDataService cloudKeyUserDataService;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private IUserService userService;
	@Resource
    private IRoomService roomService;
	@Resource
    private ICloudKeyUserAuditBaseService cloudKeyUserAuditBaseService;
	
	/**
	 * 根据用户id查询(业主门禁认证（可配置）信息)信息
	 * @return
	 */
	@RequestMapping("/queryCloudKeyUserData.json")
	@ResponseBody
	public JsonResponse queryCloudKeyUserData(){
		// 根据userId和realRoom查询cloudKeyUserAuditId
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		UserEntity userEntity = userService.getUserById(userId);
		BigInteger realRoomId = userEntity.getDefaultRoomEntity().gettRealRoomFId();
		BigInteger cloudKeyUserAuditId = cloudKeyUserDataService.queryCloudKeyUserAuditId(userId, realRoomId);
		
		List<CloudKeyUserData> cloudKeyUserDatas = cloudKeyUserDataService.queryCloudKeyUserDataByCondition(cloudKeyUserAuditId);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(cloudKeyUserDatas);
		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
		return jsonResponse;
	}
	
	/**
	 * 新增业主门禁认证信息
	 * @param request 需要map（{key、value}的json数组）
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/insertCloudKeyUserAudit.json")
	@ResponseBody
	public JsonResponse insertCloudKeyUserAudit(HttpServletRequest request) throws IOException{
		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
		RoomEntityWithValidate rewv = roomService.getDefaultRoomInfo(userId);
	    GroupBuildingEntity gb = rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
	    JsonResponse jsonResponse = new JsonResponse();
	    if(gb.getSignStatus()!=1){
		     jsonResponse.setStatus("0001");
		     jsonResponse.setMessage("该小区为非签约小区！！！");
		     return jsonResponse;
	    }
	    if(gb.gettPropertyManagementFId() ==null){
		     jsonResponse.setStatus("0001");
		     jsonResponse.setMessage("该小区无物业管理处信息！！！");
		     return jsonResponse;
	    }
		
	    //判断是否重新审核
	    Map<String, Object> paramMaps = new HashMap<String, Object>();
	    paramMaps.put("tBuildingFId", rewv.getRealRoomEntity().gettBuildingFId());
	    paramMaps.put("huaId", userId);
	    paramMaps.put("sys0DelState", 0);
	    List<CloudKeyUserAudit> auditList = cloudKeyUserAuditBaseService.getCloudKeyUserAuditByCondition(paramMaps);
	    if(auditList !=null && auditList.size()>0){
	    	if(auditList.get(0).getStatus()==0){
	    		jsonResponse.setDataValue(true);
	    		return jsonResponse;
	    	}else if(auditList.get(0).getStatus()==2){
		    	auditList.get(0).setSys0DelState(1);
		    	auditList.get(0).setSys0UpdTime(CnfantasiaCommbusiUtil.getCurrentTime());
		    	cloudKeyUserAuditBaseService.updateCloudKeyUserAudit(auditList.get(0));
	    	}
	    }
	    // 正常字段
		List mapList = JSON.parseArray(request.getParameter("map"));
		
		// 图片
		MultipartHttpServletRequest multipartRequest = null;
		Map<String, MultipartFile> mapMultipartFile = null;
		if (request instanceof MultipartHttpServletRequest) {
			multipartRequest = (MultipartHttpServletRequest) request; 
			if(multipartRequest!=null){
				mapMultipartFile = multipartRequest.getFileMap();
			}
		}
		
		if(mapList==null && mapMultipartFile==null){
			 jsonResponse.setStatus("0001");
		     jsonResponse.setMessage("信息不能全为空！！！");
		     return jsonResponse;
		}
		
		List<BigInteger> cloudKeyUserDataIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_cloud_key_user_data, (mapList!=null?mapList.size():0)+(mapMultipartFile!=null?mapMultipartFile.size():0));
		CloudKeyUserAudit cloudKeyUserAudit = new CloudKeyUserAudit();
		// 设置cloudKeyUserAudit的属性值
        cloudKeyUserAudit.setHuaId(userId);
        cloudKeyUserAudit.setStatus(0);
        cloudKeyUserAudit.settBuildingFId(rewv.getRealRoomEntity().gettBuildingFId());
        cloudKeyUserAudit.settRealRoomFId(rewv.getRealRoomEntity().getId());
        BigInteger groupBuildingId = rewv.getRealRoomEntity().getBuildingEntity().gettGroupBuildingFId();
        cloudKeyUserAudit.settGroupBuildingFId(groupBuildingId);
        cloudKeyUserAudit.settPropertyManagementFId(rewv.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getPropertyManagementEntity().getId());
        cloudKeyUserAudit.setSys0AddTime(CnfantasiaCommbusiUtil.getCurrentTime());
		BigInteger cloudKeyUserAuditId = uuidManager.getNextUuidBigInteger(SEQConstants.t_cloud_key_user_audit);
		cloudKeyUserAudit.setId(cloudKeyUserAuditId);
		List<CloudKeyUserData> cloudKeyUserDataList = new ArrayList<CloudKeyUserData>();
		if(mapList!=null){
			for(int i=0; i<mapList.size(); i++){
				CloudKeyUserData cloudKeyUserData = new CloudKeyUserData();
				cloudKeyUserData.setId(cloudKeyUserDataIds.get(i));
				cloudKeyUserData.settCloudKeyUserAuditFId(cloudKeyUserAuditId);
				Map<String, String> map = (Map<String, String>)mapList.get(i);
				
				for(Map.Entry<String, String> entry:map.entrySet()){
					cloudKeyUserData.setKey(entry.getKey());
					cloudKeyUserData.setValue(entry.getValue());
					break;// 只有一个key:value对
				}
				cloudKeyUserDataList.add(cloudKeyUserData);
			}
		}
		// 上传图片===>获取img id
		if(mapMultipartFile!=null){
			int index = 0;
			for(Map.Entry<String, MultipartFile> imgInfo: mapMultipartFile.entrySet()){
				CloudKeyUserData cloudKeyUserData = new CloudKeyUserData();
				cloudKeyUserData.setId(cloudKeyUserDataIds.get((mapList!=null?mapList.size():0)+(index++)));
				cloudKeyUserData.settCloudKeyUserAuditFId(cloudKeyUserAuditId);
				try {
					String picUrl = upImageouser(request, userId, imgInfo.getKey());
					cloudKeyUserData.setValue(picUrl);
					cloudKeyUserData.setKey(imgInfo.getKey());
				} catch (Exception e) {
					jsonResponse.setMessage("图片上传失败！");
					jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
					return jsonResponse;
				}
				cloudKeyUserDataList.add(cloudKeyUserData);
			}
		}
		
		if(cloudKeyUserDataService.insertCloudKeyUserAudit(cloudKeyUserAudit, cloudKeyUserDataList)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			jsonResponse.setDataValue(true);
			jsonResponse.setMessage("操作成功！");
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	
	/**
	 * 修改业主门禁认证信息
	 * @param request
	 * @return
	 *//*
	@RequestMapping("/updateCloudKeyUserAudit.json")
	@ResponseBody
	public JsonResponse updateCloudKeyUserAudit(HttpServletRequest request){
		List<CloudKeyUserData> cloudKeyUserDataList = JSON.parseArray(request.getParameter("map"),CloudKeyUserData.class);
		
		// 设置cloudKeyUserAudit的属性值
		CloudKeyUserAudit cloudKeyUserAudit = new CloudKeyUserAudit();
		String huaId = request.getParameter("huaId");
		if(huaId!=null){
			cloudKeyUserAudit.setHuaId(new BigInteger(huaId));
		}
		
		String auditReason = request.getParameter("auditReason");
		if(auditReason!=null){
			cloudKeyUserAudit.setAuditReason(auditReason);
		}
		
		String tBuildingFId = request.getParameter("tBuildingFId");
		if(auditReason!=null){
			cloudKeyUserAudit.settBuildingFId(new BigInteger(tBuildingFId));
		}
		
		String tPropertyManagementFId = request.getParameter("tPropertyManagementFId");
		if(tPropertyManagementFId!=null){
			cloudKeyUserAudit.settPropertyManagementFId(new BigInteger(tPropertyManagementFId));
		}
		
		JsonResponse jsonResponse = new JsonResponse();
		if(cloudKeyUserDataService.updateCloudKeyUserAudit(cloudKeyUserAudit, cloudKeyUserDataList)){
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			jsonResponse.setMessage("操作成功！");
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}*/
	
	@Resource
    protected ISysParamManager sysParamManager;
	@Resource
    protected IFileServerService fileServerService;
	
	/**
	 * 上传图片
	 * @param request
	 * @param userId
	 * @param imgId
	 * @return
	 * @throws IOException
	 */
	private String upImageouser(HttpServletRequest request, BigInteger userId, String imgId) throws IOException {
        RequestFileEntity requestFile = CommRequestFileParser.parseRequsetFileInfo(request, imgId);
        String doorKeyPicBase = sysParamManager.getSysParaValue(SysParamKey.doorKeyPicUrl);
        // 生成文件名 userId+time+3位随机数
        String resFileName = new StringBuffer().append(userId).append(DateUtil.getCurrSysTimeStr()).append("_").append("_").append(RandomUtils.createRandom(true, 3)).append(".")
                .append(requestFile.getFileType()).toString();
        String serverPath = doorKeyPicBase + resFileName;
        fileServerService.uploadFile(serverPath, requestFile.getFileContent());
        return resFileName;
    }
}
