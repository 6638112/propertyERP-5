package com.cnfantasia.server.business.pub.session;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.common.utils.ParamUtils;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * Session中的变量管理器，所有value全放到这里统一管理
 * @author wenfq
 * @date 2017-09-06 10:52
 */
public class SessionValueManager {
    /**
     * 活动推荐人的解放号
     */
    public static final String Recommend_User_Id = "Recommend_User_Id";

    /**
     * 活动推荐人的手机号
     */
    public static final String Recommend_User_Phone = "Recommend_User_Phone";

    /**
     * 活动列表可以分享给别人，从这里开始记录发起推荐人的userId，下个用户购买就以该userId作为推荐人id
     */
    public static void putRecommendUserId(HttpServletRequest request) {
        BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);
        if (userId == null)
            userId = UserContext.getOperIdBigInteger();

        request.getSession().setAttribute(Recommend_User_Id, userId);
    }

    /**
     * 到家服务推荐人手机号
     * @param request
     */
    public static void putRecommendUserPhone(HttpServletRequest request) {
        String phone = ParamUtils.getString(request,"phone", null);
        // phone为空时，也要放到Session中，因为没有推荐人手机号时，就是需要清空推荐人手机号
        request.getSession().setAttribute(Recommend_User_Phone, phone);
    }
}
