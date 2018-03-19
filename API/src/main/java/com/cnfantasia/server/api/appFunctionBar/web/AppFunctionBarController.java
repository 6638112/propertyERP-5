package com.cnfantasia.server.api.appFunctionBar.web;

import com.cnfantasia.server.api.appFunctionBar.constant.AppFunctionBarDict;
import com.cnfantasia.server.api.appFunctionBar.entity.AppFuncBar;
import com.cnfantasia.server.api.appFunctionBar.entity.AppFunctionBarGroup;
import com.cnfantasia.server.api.appFunctionBar.service.IAppFunctionBarService;
import com.cnfantasia.server.api.common.util.LicaiUil;
import com.cnfantasia.server.api.plotproperty.entity.Propfee;
import com.cnfantasia.server.api.plotproperty.service.IPlotpropertyService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.user.entity.UserEntity;
import com.cnfantasia.server.api.user.service.IUserService;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.ParamUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: AppFunctionBarController
 * @Date: 2017-02-24 10:44
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/appFunctionBar")
public class AppFunctionBarController extends BaseController {

    @Resource
    private IAppFunctionBarService appFunctionBarService;

    @Resource
    private IPlotpropertyService plotpropertyService;

    @RequestMapping(value = "/qryBarList.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getBarList(HttpServletRequest request) {
        Integer positionType = ParamUtils.getInteger(request, "positionType", AppFunctionBarDict.PosotionType.MINE);
        Integer version = HeaderManager.getVersionNum();
        BigInteger userId = UserContext.getOperIdBigInteger();
        List<AppFunctionBarGroup> barGroupList = appFunctionBarService.getFuncBarGroupListByPositionType(positionType, version);
        for (AppFunctionBarGroup appFunctionBarGroup : barGroupList) {
            List<AppFuncBar> barList = appFunctionBarGroup.getFunctionList();
            if (!DataUtil.isEmpty(barList)) {
                for (AppFuncBar appFuncBar : barList) {
                    if ("jinrong".equals(appFuncBar.getCode())) {
                        if(userId == null) {
                            appFuncBar.setVerifyStatus(1);
                            appFuncBar.setPropfeeCanpay(0);
                        } else {
                            Map<String, Object> paramMap = new HashMap<String, Object>();
                            paramMap.put("userId", userId);
                            //查看小区是否校验，是否可以缴费，是否导入过账单
                            Propfee propfee = plotpropertyService.getPropertyFeeAndCount(paramMap);
                            if(propfee != null ) {
                                appFuncBar.setVerifyStatus(propfee.getVerifyStatus() == null ? 1 : propfee.getVerifyStatus());
                                if(propfee.getPropfeeCanpay() == null || propfee.getPropfeeCanpay() != 1) {
                                    appFuncBar.setPropfeeCanpay(0); //0小区未开通缴费
                                } else if(propfee.getTotalPrice() != null && propfee.getTotalPrice() > 0) {
                                    appFuncBar.setPropfeeCanpay(2); //小区可以缴费且导入过账单
                                } else {
                                    appFuncBar.setPropfeeCanpay(1); //小区可以缴费，但是从未导入过账单
                                }
                            } else {
                                appFuncBar.setVerifyStatus(1);
                                appFuncBar.setPropfeeCanpay(0);
                            }
                        }
//                    } else if("licai".equals(appFuncBar.getCode())) {
//                    	if(userId == null) {
//                    		appFuncBar.setLinkUrl("https://resource.linlile.com.cn/docs/prev_finance/index.htm");
//                    	} else {
//                    		UserEntity userEntity = userService.getUserById(userId);
//                    		StringBuilder url = new StringBuilder();
//                    		url.append(CnfantasiaCommbusiUtil.getSysParaValue("licaiUrl")).append("?appKey=")
//                    				.append(CnfantasiaCommbusiUtil.getSysParaValue("financeAppKey")).append("&token=").append(CnfantasiaCommbusiUtil.getSysParaValue("financeToken"))
//                    				.append("&uid=").append(userEntity.getHuaId()).append("&mobilephone=").append(userEntity.getMobile()).append("&channel=8");
//                    		appFuncBar.setLinkUrl(url.toString());
//                    	}
                    }
                }
                
                LicaiUil.filter(barList);
            }
        }
        JsonResponse json = new JsonResponse();
        json.putData("list", barGroupList);
        return json;
    }
}
