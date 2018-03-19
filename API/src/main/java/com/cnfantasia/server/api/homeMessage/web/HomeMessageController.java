package com.cnfantasia.server.api.homeMessage.web;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.homeMessage.entity.AskHelpConfig;
import com.cnfantasia.server.api.homeMessage.entity.HomeMessageEntity;
import com.cnfantasia.server.api.homeMessage.service.IHomeMessageService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.groupBuildingExt.entity.GroupBuildingExt;
import com.cnfantasia.server.domainbase.groupBuildingExt.service.IGroupBuildingExtBaseService;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * @ClassName: HomeMessageController
 * @Date: 2017-02-24 14:01
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/homeMessage")
public class HomeMessageController extends BaseController {

    @Resource
    private IHomeMessageService homeMessageService;
    @Resource
    private ICommonRoomService commonRoomService;
    @Resource
    private IGroupBuildingExtBaseService groupBuildingExtBaseService;
    @Resource
    private ISysParamManager sysParamManager;

    @RequestMapping(value = "/qryUserHomeMessage.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUserHomeMessage() {
        BigInteger userId = UserContext.getOperIdBigInteger();
        JsonResponse json = new JsonResponse();

        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserIdForHome(userId);

        //寻求帮助功能配置
        GroupBuildingExt gbExt = groupBuildingExtBaseService.getGroupBuildingExtBySeqId(gbId);
        AskHelpConfig config = new AskHelpConfig();
        config.setSearchHelpOpen(gbExt == null || gbExt.getCannotAskHelp() == null || gbExt.getCannotAskHelp() != 1);
        if (gbExt != null) {
            String placehold = gbExt.getAskHelpPlacehole();
            if (placehold != null && (Arrays.asList(placehold.split(";")).size() == 2)) {
                List<String> placeholdList = Arrays.asList(placehold.split(";"));
                config.setPlaceholdOut(placeholdList.get(0));
                config.setPlaceholdIn(placeholdList.get(1));
            }
        }
        //默认文案
        if (config.getPlaceholdIn() == null){
            String placehold = sysParamManager.getSysParaValue(SysParamKey.AskHelpPlaceholdDefault);
            if (placehold != null && Arrays.asList(placehold.split(";")).size() == 2) {
                List<String> placeholdList = Arrays.asList(placehold.split(";"));
                config.setPlaceholdOut(placeholdList.get(0));
                config.setPlaceholdIn(placeholdList.get(1));
            } else {
                config.setPlaceholdOut("寻求帮助");
                config.setPlaceholdIn("希望物业为您提供什么服务");
            }
        }
        json.putData("searchHelpConfig", config);

        boolean isUserAuth = false;
        if (userId != null) {
            //当前门牌是否已认证
            RoomEntityWithValidate defaultRoomInfo = commonRoomService.getDefaultRoomInfo(userId);
            RoomValidate roomValidate = defaultRoomInfo.getRoomValidate();
            isUserAuth = (roomValidate != null && roomValidate.getVerifyStatus() == RoomDict.RoomValidte_VerifyStatus.SUCCESS.intValue());
        }
        json.putData("isUserAuth", isUserAuth);

        //消息数据
       
        Integer version = HeaderManager.getVersionNum();
        if(userId == null){ //没有注册的用户，给默认小区ID，它配置了消息流
        	gbId = RoomConstants.DEFAULT_GROUP_BUILDING_ID;
        }
        List<HomeMessageEntity> messageEntityList = homeMessageService.getHomeMessageListByUserId(userId, gbId, isUserAuth, version);
        
        json.putData("list", messageEntityList);

        return json;
    }
}
