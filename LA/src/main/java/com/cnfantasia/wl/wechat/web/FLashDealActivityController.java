package com.cnfantasia.wl.wechat.web;

import com.cnfantasia.server.ms.pub.annotation.CheckLogin;

import java.math.BigInteger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: FLashDealActivityController.
 * @Date: 2017-09-01 14:33
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/flashDealActivity")
public class FLashDealActivityController {


    /**
     * 进入活动列表页面
     * @return
     */
    @RequestMapping(value = "/activityList.html", method = RequestMethod.GET)
    @CheckLogin
    public ModelAndView jumpToActivityList() {
        return new ModelAndView("/flashDealActivity/activityList");
    }

    /**
     * 我的抢购记录页面 + 数据
     * @return
     */
    @RequestMapping(value = "/myRecord.html", method = RequestMethod.GET)
    @CheckLogin
    public ModelAndView jumpToMyRecord() {
        return new ModelAndView("/flashDealActivity/myRecord");
    }

    /**
     * 活动详情页
     * @return
     */
    @RequestMapping(value = "/activityDetail.html", method = RequestMethod.GET)
    @CheckLogin
    public ModelAndView jumpToActivityDetail() {
        return new ModelAndView("/flashDealActivity/activityDetail");
    }

    @RequestMapping(value = "/pay.html", method = RequestMethod.GET)
    @CheckLogin
    public ModelAndView pay(BigInteger activityId) {
        ModelAndView mav = new ModelAndView("/ebuy/checkPayLukyGo");
        mav.addObject("activityId", activityId);
        return mav;
    }
}
