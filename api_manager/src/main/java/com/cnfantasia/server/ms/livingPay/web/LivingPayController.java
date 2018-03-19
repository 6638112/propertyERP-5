package com.cnfantasia.server.ms.livingPay.web;

import com.cnfantasia.server.api.livingPay.entity.PayRecordRevenue;
import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.ms.pub.page.PageUtils;
import com.cnfantasia.server.ms.pub.session.UserContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: wenfq
 * @Date: 2017-11-13 11:08
 */
@Controller
@RequestMapping(value = "livingPay")
public class LivingPayController extends BaseController {
    private Log logger = LogFactory.getLog(getClass());
    @Resource
    LivingPayService livingPayService;

    private static String qryLivingPayRevenueListParamMap = "qryLivingPayRevenueListParamMap";

    /**
     * 生活缴费收益明细
     * @param request
     * @return
     */
    @RequestMapping(value = "/qryLivingPayRevenueList.html", method = RequestMethod.GET)
    public ModelAndView qryLivingPayItemList(HttpServletRequest request) {
        int itemId = ParamUtils.getInt(request, "itemId", -1);
        String chargeObject = ParamUtils.getString(request, "chargeObject");
        String linkTel = ParamUtils.getString(request, "linkTel");
        String payTimeStart = ParamUtils.getString(request, "payTimeStart");
        String payTimeEnd = ParamUtils.getString(request, "payTimeEnd");
        int status = ParamUtils.getInt(request, "status", -1);
        int settleStatus = ParamUtils.getInt(request, "settleStatus", -1);
        BigInteger revenueApplyId = ParamUtils.getBigInteger(request, "revenueApplyId", null); //合并后提款单的id

        Map<String, Object> paramMap = new HashMap<>();
        if(itemId>0)
            paramMap.put("itemId", itemId);
        paramMap.put("chargeObject",chargeObject);
        paramMap.put("linkTel", linkTel);
        paramMap.put("payTimeStart", payTimeStart);
        paramMap.put("payTimeEnd", payTimeEnd);
        if(revenueApplyId != null)
            paramMap.put("revenueApplyId", revenueApplyId);
        if(status>=0)
            paramMap.put("status", status);
        if(settleStatus>0)
            paramMap.put("settleStatus", settleStatus);

        int totalSize = livingPayService.qryLivingPayRevenueListTotalCount(paramMap);
        request.setAttribute("total", totalSize);
        request.getSession().setAttribute(qryLivingPayRevenueListParamMap, new HashMap<>(paramMap)); //Excel导出时用到
        PageUtils.addPageInfoToParam(request, paramMap);

        List<PayRecordRevenue> payRecordRevenueList = livingPayService.qryLivingPayRevenueList(paramMap);
        request.setAttribute("resList", payRecordRevenueList);

        return new ModelAndView("/livingPay/revenueList");
    }

    /**
     * 确认充值
     * @param id 缴费单id
     * @param amtBalance 充值后余额
     * @return
     */
    @RequestMapping(value = "/confirmCharge.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse confirmCharge(BigInteger id, Double amtBalance){
        if(id == null || amtBalance < 0){
            throw new ValidateRuntimeException("传入参数id或amtBalance不正确");
        }

        JsonResponse jsonResponse = new JsonResponse();
        int updCount = livingPayService.confirmCharge(id, amtBalance);
        jsonResponse.setStatus(updCount > 0 ? "0000": "0001");
        jsonResponse.setMessage(updCount > 0 ? "充值成功": "充值失败");
        return jsonResponse;
    }

    /**
     * 发起提款
     * @param id 缴费单id
     * @param amtBalance 充值后余额
     * @return
     */
    @RequestMapping(value = "/applyRevenue.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse applyRevenue(){
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
        String userName = UserContext.getCurrUser().getRealName();
        double applyTotalAmount = livingPayService.applyRevenue(userId, userName);
        jsonResponse.putData("applyTotalAmount", applyTotalAmount);
        jsonResponse.setMessage(applyTotalAmount > 0 ? "提款成功": "没有可提款金额");
        return jsonResponse;
    }


    /**
     * Excel导出
     *
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/exportExcel.html")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> paramMap = (Map<String, Object>) request.getSession().getAttribute(qryLivingPayRevenueListParamMap);

        OutputStream fOut = null;
        InputStream fin = null;
        try {
            HSSFWorkbook workbook = livingPayService.createRevenueReport(paramMap);
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String fileName = "生活缴费代收收益"+format.format(new Date());
            response.setContentType("application/msexcel;charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" +new String(fileName.getBytes(), "iso8859-1") + ".xls");
            fOut = response.getOutputStream();
            workbook.write(fOut);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fOut != null) {
                    fOut.flush();
                    fOut.close();
                }
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ie) {
                throw new RuntimeException(ie);
            }
        }
    }

}
