package com.cnfantasia.server.ms.payBill.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.payBill.entity.FeeCollectionViewEntity;
import com.cnfantasia.server.ms.payBill.service.PayBillReportService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PayBillReportController
 * @Date: 2017-06-08 15:59
 * @Auther: yanghua
 * @Description:(收费报表)
 */
@Controller
@RequestMapping("/payBillReport")
public class PayBillReportController extends BaseController {

    @Resource
    private PayBillReportService payBillReportService;

    @RequestMapping("feeCollectionView.html")
    public ModelAndView feeCollectionView(HttpServletRequest request) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        String billMonth = ParamUtils.getString(request, "billMonth");
        if(DataUtil.isEmpty(billMonth)) billMonth = DateUtils.getTodayStr().substring(0, 7);//月份格式为2017-06
        paraMap.put("billMonth", billMonth);
        paraMap.put("propertyManagementId", ParamUtils.getBigInteger(request, "propertyManagementId", null));
        paraMap.put("gbId", ParamUtils.getBigInteger(request, "gbId", null));
        paraMap.put("buildingId", ParamUtils.getBigInteger(request, "buildingId", null));
        paraMap.put("gbIdList", UserContext.getGbIdList());
        ModelAndView modelAndView = new ModelAndView();

        List<FeeCollectionViewEntity> feeCollectionViewEntityList = payBillReportService.getFeeCollectionList(paraMap);
        modelAndView.addObject("resList", feeCollectionViewEntityList);
        modelAndView.addObject("billMonth", billMonth);
        modelAndView.addObject("gbId", ParamUtils.getBigInteger(request, "gbId", null));
        modelAndView.addObject("buildingId", ParamUtils.getBigInteger(request, "buildingId", null));
        modelAndView.addObject("propertyManagementId", ParamUtils.getBigInteger(request, "propertyManagementId", null));

        String pmListStr = request.getParameter("pmList");
        String gbListStr = request.getParameter("gbList");
        String buildingListStr = request.getParameter("buildingList");
        String isSelect = request.getParameter("isSelect");
        if(!DataUtil.isEmpty(pmListStr)) {
            List<Map> lists = JSONObject.parseArray(pmListStr, Map.class);
            modelAndView.addObject("pmList", lists);
            modelAndView.addObject("pmListStr", pmListStr);
        }
        if(!DataUtil.isEmpty(gbListStr)) {
            List<Map> lists = JSONObject.parseArray(gbListStr, Map.class);
            modelAndView.addObject("gbList", lists);
            modelAndView.addObject("gbListStr", gbListStr);
        }
        if(!DataUtil.isEmpty(buildingListStr)) {
            List<Map> lists = JSONObject.parseArray(buildingListStr, Map.class);
            modelAndView.addObject("buildingList", lists);
            modelAndView.addObject("buildingListStr", buildingListStr);
        }

        modelAndView.addObject("isSelect", isSelect);
        modelAndView.setViewName("payBillReport/feeCollectionView");
        return modelAndView;
    }

    @RequestMapping("feeDetailView.html")
    public ModelAndView feeDetailView(HttpServletRequest request) {
        return null;
    }

    @RequestMapping("unpaidDetailView.html")
    public ModelAndView unpaidDetailView(HttpServletRequest request) {
        return null;
    }

    @RequestMapping("getGbList.json")
    @ResponseBody
    public String getGbList(HttpServletRequest request) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("gbIdList", UserContext.getGbIdList());
        paraMap.put("pmId", ParamUtils.getBigInteger(request, "pmId", null));
        List<Map<BigInteger, String>> list = payBillReportService.getGroupBuildingList(paraMap);
        request.getSession().setAttribute("gbListForReport", list);
        JSONObject jsonObj = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            jsonObj.put(list.get(i).toString(), list.get(i));
        }
        return jsonObj.toJSONString();
    }

    @RequestMapping("getPmList.json")
    @ResponseBody
    public String getPmList(HttpServletRequest request) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("gbIdList", UserContext.getGbIdList());
        //paraMap.put("name", UserContext.getGbIdList());
        List<Map<BigInteger, String>> list = payBillReportService.getPmList(paraMap);
        request.getSession().setAttribute("pmListForReport", list);
        JSONObject jsonObj = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            jsonObj.put(list.get(i).toString(), list.get(i));
        }
        return jsonObj.toJSONString();
    }

    @RequestMapping("getBuildingList.json")
    @ResponseBody
    public String getBuildingList(HttpServletRequest request) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("gbIdList", UserContext.getGbIdList());
        BigInteger gbId = ParamUtils.getBigInteger(request, "gbId", null);
        if(gbId == null) {
            return "";
        }
        paraMap.put("gbId", gbId);
        List<Map<BigInteger, String>> list = payBillReportService.getBuildingList(paraMap);
        request.getSession().setAttribute("buildingListForReport", list);
        JSONObject jsonObj = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            jsonObj.put(list.get(i).toString(), list.get(i));
        }
        return jsonObj.toJSONString();
    }

}
