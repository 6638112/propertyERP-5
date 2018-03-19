package com.cnfantasia.server.api.user.web;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommEntityConvertService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonLoginService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.util.CommonUserImageProfileUtil;
import com.cnfantasia.server.api.fileServer.constant.FileServerConstant;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.homeMessage.constant.HomeMessageDict;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.login.service.IValicodeManager;
import com.cnfantasia.server.api.notice.entity.MessageWithReadStatusEntity;
import com.cnfantasia.server.api.notice.service.INoticeService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.user.entity.HobbyEntity;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.entity.UserImageParamEntity;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.api.user.service.IUserService;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.user.entity.User;
import com.cnfantasia.server.domainbase.user.service.IUserBaseService;
import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private ICommonUserService commonUserService;

    public void setCommonUserService(ICommonUserService commonUserService) {
        this.commonUserService = commonUserService;
    }

    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    private IFileServerService fileServerService;

    public void setFileServerService(IFileServerService fileServerService) {
        this.fileServerService = fileServerService;
    }

    private ISysParamParser userImageParamParser;

    public void setUserImageParamParser(ISysParamParser userImageParamParser) {
        this.userImageParamParser = userImageParamParser;
    }

    private IValicodeManager valicodeManager;

    public void setValicodeManager(IValicodeManager valicodeManager) {
        this.valicodeManager = valicodeManager;
    }

    private ICommonLoginService commonLoginService;

    public void setCommonLoginService(ICommonLoginService commonLoginService) {
        this.commonLoginService = commonLoginService;
    }

    private ISysParamParser noticeSysParamParser;

    public void setNoticeSysParamParser(ISysParamParser noticeSysParamParser) {
        this.noticeSysParamParser = noticeSysParamParser;
    }

    private ICommEntityConvertService commEntityConvertService;

    public void setCommEntityConvertService(ICommEntityConvertService commEntityConvertService) {
        this.commEntityConvertService = commEntityConvertService;
    }

    private INoticeService noticeService;
    public void setNoticeService(INoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Resource
    private IHomeMessageService homeMessageService;

    @Resource
    private IUserBaseService userBaseService;

    @RequestMapping("/qryPersonInfo.json")
    @ResponseBody
    public JsonResponse qryPersonInfo(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        UserEntity userEntity = userService.getUserById(userId);
        // 3.结果处理
        Map<String, Object> resMap = commEntityConvertService.personInfo2Map(userEntity, userEntity.getDefaultRoomEntity(), userEntity.getAchievementList(), userEntity.getHobbyList());
        jsonResponse.setDataValue(resMap);
        return jsonResponse;
    }

    @RequestMapping("/qryPersonInfoByUserId.json")
    @ResponseBody
    public JsonResponse qryPersonInfoByUserId(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = null;
        String userIdStr = request.getParameter("userId");
        if (!StringUtils.isEmpty(userIdStr)) {
            userId = new BigInteger(userIdStr);
        }
        if (userId == null) {
            throw new ValidateRuntimeException("user.qryPersonInfoByUserId.userId.isNull");
        }
        // 2.交互
        UserEntity userEntity = userService.getUserById(userId);
        // 3.结果处理
        if (userEntity != null) {
            // Map<String,Object> resMap =
            // commEntityConvertService.personInfo2MapSimple(userEntity,
            // userEntity.getDefaultRoomEntity(),userEntity.getAchievementList(),userEntity.getHobbyList());
            Map<String, Object> resMap = commEntityConvertService.personInfo2Map(userEntity, userEntity.getDefaultRoomEntity(), userEntity.getAchievementList(), userEntity.getHobbyList());
            jsonResponse.setDataValue(resMap);
        }
        return jsonResponse;
    }

    @RequestMapping("/qryVerifiedFamilyMembers.json")
    @ResponseBody
    public JsonResponse qryVerifiedFamilyMembers(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        List<UserSimpleEntity> userList = commonUserService.getFamilyMembers(userId);

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        if (userList != null) {
            for (UserSimpleEntity tmpUserRoomApplyEntity : userList) {
                Map<String, Object> tmpMap = commEntityConvertService.baseUser2Map(tmpUserRoomApplyEntity);
                // tmpMap.put("applyStatus", validedStatus);//0:未处理;1: 已同意;2:已拒绝
                // ;(目前只返回状态为0和1的数据)
                // tmpMap.put("applyId", tmpUserRoomApplyEntity.getId());
                resList.add(tmpMap);
            }
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    @RequestMapping("/updResident.json")
    @ResponseBody
    public JsonResponse updResident(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger resId = new BigInteger(request.getParameter("resId"));

        UserEntity resident = userService.getUserById(resId);
        Map<String, Object> resMap = commEntityConvertService.personInfo2Map(resident, resident.getDefaultRoomEntity(), resident.getAchievementList(), resident.getHobbyList());
        if (null != userId && null != resId) {
            UserEntity user = userService.getUserById(userId);
            // 管理员or本人才有权更改住户信息
            if (EqualsBuilder.reflectionEquals(userId, resId) || user.getExt_room_isAdmin()) {
                jsonResponse.setDataValue(updUser(resId, request));
            } else {
                jsonResponse.setDataValue(resMap);
            }
        } else {
            jsonResponse.setDataValue(resMap);
        }
        return jsonResponse;
    }

    @RequestMapping("/updPersonInfo.json")
    @ResponseBody
    public JsonResponse updPersonInfo(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        jsonResponse.setDataValue(updUser(userId, request));
        return jsonResponse;
    }

    /**
     * @author wangzhe
     * @date 2015年9月2日
     * @description 标准更新用户信息
     *
     * @param userId userId
     * @param request request
     * @return JsonResponse
     */
    private Map<String, Object> updUser(BigInteger userId, HttpServletRequest request) {

        // String mobile = request.getParameter("mobile");
        String mobile = null;// 此处不作手机号修改处理
        if ("wlLightApp".equals(request.getParameter("reqeustFrom"))) {
            // 请求来自于文旅轻应用，暂时让其可改手机号
            mobile = request.getParameter("mobile");
        }
        String realName = request.getParameter("realName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String nickName = request.getParameter("nickName");
        String signature = request.getParameter("signature");
        String role = request.getParameter("role");
        // 解析用户图像信息
        UserImageParamEntity userImageParamEntity = userImageParamParser.parseParamValue();
        RequestFileEntity userImageEntity = CommonUserImageProfileUtil.parseRequsetImageInfo(userImageParamEntity, request);
        String userImgprofileName = userImageEntity.getFileName();
        byte[] userImage = userImageEntity.getFileContent();
        // 用户兴趣爱好
        String hobbyIdsStr = request.getParameter("hobbyIds");
        List<BigInteger> hobbyIds = null;
        if (!StringUtils.isEmpty(hobbyIdsStr)) {
            hobbyIds = JSON.parseArray(hobbyIdsStr, BigInteger.class);
        }
        // 邀请码信息 syl-add 2015-4-16 11:50:35
        String inviteNo = request.getParameter("inviteNo");// sql部分已处理为：存在则不更新
        // 2.交互
        UserEntity userEntity = userService.updPersonInfo(userId, mobile, realName, sex, birthday, userImage, userImgprofileName, nickName, signature, hobbyIds, inviteNo, role);
        // 3.结果处理
        Map<String, Object> resMap = commEntityConvertService.personInfo2Map(userEntity, userEntity.getDefaultRoomEntity(), userEntity.getAchievementList(), userEntity.getHobbyList());
        return resMap;
    }

    /**
     * 待解绑手机的验证码是否正确
     * 
     * @param request
     * @return
     */
    @RequestMapping("/bindPhoneCheckOldVerify.json")
    @ResponseBody
    public JsonResponse bindPhoneCheckOldVerify(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String oldValicode = request.getParameter("oldValicode");
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互

        LoginNo loginNo = commonLoginService.getUserPhoneAccInfo(userId);
        if (loginNo == null) {
            throw new ValidateRuntimeException("login.getValidateCode.notBind.error");
        } else {
            String mobile = loginNo.getNo();// 设定手机号
            // 校验原始验证码是否正确
            valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.UNBIND_PHONE, mobile, oldValicode);
            valicodeManager.clearValicode(LoginDict.ValiCodeGetType.UNBIND_PHONE);// 清除验证码
            // 将验证成功的信息缓存,以便换绑新手机时验证
            SessionManager.getSession().setAttribute(LoginConstant.Change_Phone_Bind_Result, true);
        }

        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 绑定手机号
     * 
     * @param request
     * @return
     */
    @RequestMapping("/bindPhone.json")
    @ResponseBody
    public JsonResponse bindPhone(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        // String oldPhone = request.getParameter("oldPhone");
        // String oldValicode = request.getParameter("oldValicode");
        String newPhone = request.getParameter("newPhone");
        String newValicode = request.getParameter("newValicode");
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        // boolean needUnBindold = false;
        // //是否需要解绑原手机号
        // if(!StringUtils.isEmpty(oldPhone)){
        // needUnBindold = true;
        // //校验原始验证码是否正确
        // valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.UNBIND_PHONE,
        // oldValicode);
        // }
        // //绑定新手机号<事务>
        // if(needUnBindold){
        // //比对新旧手机是否相同，若相同则提示错误信息
        // if(oldPhone.equals(newPhone)){
        // throw new
        // ValidateRuntimeException("bindPhone.check.phone.same.error");
        // }
        // }
        // 校验新手机号验证码是否正确
        valicodeManager.checkWithSession(LoginDict.ValiCodeGetType.UPDATE_PASSWORD, newPhone, newValicode);
        valicodeManager.clearValicode(LoginDict.ValiCodeGetType.UPDATE_PASSWORD);// 清除验证码
        // if(!valicodeManager.checkWithSession(DictConstants.ValiCodeGetType.UPDATE_PASSWORD,
        // newValicode)){
        // throw new
        // ValidateRuntimeException("bindPhone.check.newValicode.error");
        // }
        userService.bindPhone(request, userId, newPhone);
        // 更新登录session的信息
        
        // 3.结果处理
//        return jsonResponse;
        jsonResponse = commonLoginService.afterLoginCheck(request,UserContext.getCurrLoginNo());
		/*if (UserContext.getCurrLoginNo().getUserEntity().getPhoneBindState() == BindPhoneConstant.User_Bind_State_Fail) {
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("该手机号已注册解放区，且已有业务数据，请用手机号登录或换另一个手机号绑定。");
        };*/
        
        return jsonResponse;
    }

    /**
     * 查询系统公共列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryMsgList.json")
    @ResponseBody
    public JsonResponse qryMsgList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // Long type = Long.parseLong(request.getParameter("type"));
        // type = null;//设置为查询所有类别
        PageModel pageModel = ControllerUtils.getPageModel(request);
        // 2.交互
        List<MessageWithReadStatusEntity> tmpList = noticeService.querySystemMessageList(userId, pageModel);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (MessageWithReadStatusEntity signal : tmpList) {
            Message currMessage = signal;
            Map<String, Object> rec01 = new HashMap<String, Object>();
            rec01.put("id", signal.getMessageId_Ext());
            rec01.put("msgId", signal.getMessageId_Ext());
            rec01.put("title", currMessage.getTitle());
            rec01.put("content", (currMessage.getContent()));//app端已做HTML支持，所以这里不需要移除HTML标签了 added by Wenfq 2017-03-06
            rec01.put("time", currMessage.getTime());
            rec01.put("timeLong", DateUtil.timeToLong(currMessage.getTime()));
            rec01.put("picUrl", currMessage.getPicUrl() == null ? FileServerConstant.DEFAULT_NULL_PIC_URL : currMessage.getPicUrl());
            rec01.put("status", signal.getReadStatus_Ext() == null ? "" : signal.getReadStatus_Ext());
            {
                String picUrl = StringUtils.isEmpty(currMessage.getPicUrl()) ? FileServerConstant.DEFAULT_NULL_PIC_URL
                        : fileServerService.getAccessUrl(noticeSysParamParser.parseParamValue() + currMessage.getPicUrl(), currMessage);
                rec01.put("picUrlAll", picUrl == null ? FileServerConstant.DEFAULT_NULL_PIC_URL : picUrl);
            }
            resList.add(rec01);
        }

        //删除首页寻求帮助消息流
        User user = userBaseService.getUserBySeqId(userId);
        UserHasHomeMessage hasHomeMessage = new UserHasHomeMessage();
        hasHomeMessage.setUserId(userId);
        hasHomeMessage.setMessageCode(HomeMessageDict.MessageCode.ASK_FOR_HELP_MESSAGE);
        hasHomeMessage.settRoomFId(user.getDefaultRoomId());
        List<UserHasHomeMessage> list = new ArrayList<>(1);
        list.add(hasHomeMessage);
        homeMessageService.delUserHomeMsgByParam(list);

        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * 查询爱好列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryHobbyList.json")
    @ResponseBody
    public JsonResponse qryHobbyList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        PageModel pageModel = ControllerUtils.getPageModel(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        List<HobbyEntity> hobbyList = userService.getAllHobbyList(pageModel, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        if (hobbyList != null && hobbyList.size() > 0) {
            for (HobbyEntity tmpHobby : hobbyList) {
                Map<String, Object> resMap = commEntityConvertService.hobby2Map(tmpHobby, tmpHobby.getHaveSelectFlag());
                resList.add(resMap);
            }
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * 用户设定兴趣爱好列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/submitAllHobby.json")
    @ResponseBody
    public JsonResponse submitAllHobby(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String hobbyIdsStr = request.getParameter("hobbyIds");
        List<BigInteger> hobbyIds = null;
        if (!StringUtils.isEmpty(hobbyIdsStr)) {
            hobbyIds = JSON.parseArray(hobbyIdsStr, BigInteger.class);
        }
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        List<Hobby> hobbyList = userService.submitAllHobby(hobbyIds, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        if (hobbyList != null && hobbyList.size() > 0) {
            for (Hobby tmpHobby : hobbyList) {
                Map<String, Object> resMap = commEntityConvertService.hobby2Map(tmpHobby, null);
                resList.add(resMap);
            }
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

}
