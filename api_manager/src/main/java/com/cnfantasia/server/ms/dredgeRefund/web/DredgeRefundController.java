package com.cnfantasia.server.ms.dredgeRefund.web;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.ms.dredgeRefund.entity.DredgeRefundEntity;
import com.cnfantasia.server.ms.dredgeRefund.service.IDredgeRefundService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: DredgeRefundController.
 * @Date: 2017-10-12 13:40
 * @Auther: kangduo
 * @Description: ()
 */
@Controller
@RequestMapping("/dredgeRefund")
public class DredgeRefundController {
    private Log logger = LogFactory.getLog(getClass());

    @Resource
    private IDredgeRefundService dredgeRefundService;

    @RequestMapping("/refundList.html")
    public ModelAndView refundList(HttpServletRequest request, DredgeRefundEntity entity) {
        ModelAndView mav = new ModelAndView("/dredge/refundList");
        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名
        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);
        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);

        List<DredgeRefundEntity> dredgeRefundList = dredgeRefundService.getDredgeRefundList(entity, pageModel);
        int total = dredgeRefundService.getDredgeRefundCount(entity);
        mav.addObject("resList", dredgeRefundList);
        mav.addObject("resultSize", total);
        request.getSession().setAttribute("dfControllerQueryParam", entity);
        return mav;
    }

    @RequestMapping("/refundDetail.html")
    @ResponseBody
    public JsonResponse getRefundDetail(BigInteger refundId) {
        JsonResponse json = new JsonResponse();
        DredgeRefundEntity detail = dredgeRefundService.getDredgeRefundDetail(refundId);
        json.putData("detail", detail);
        return json;
    }

    @RequestMapping("/addRefund.html")
    @ResponseBody
    public JsonResponse addRefund(DredgeRefundEntity entity) {
        dredgeRefundService.addDredgeRefund(entity);
        return new JsonResponse();
    }

    @RequestMapping("/audit.html")
    @ResponseBody
    public JsonResponse audit(BigInteger refundId, String result, String auditReason) {
        dredgeRefundService.audit(refundId, result, auditReason);
        return new JsonResponse();
    }

    @RequestMapping("/export.html")
    @ResponseBody
    public void exportRefundList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DredgeRefundEntity entity = (DredgeRefundEntity) request.getSession().getAttribute("dfControllerQueryParam");
        List<DredgeRefundEntity> dredgeRefundList = dredgeRefundService.getDredgeRefundList(entity, null);
        ExcelUtil<DredgeRefundEntity> excelUtil = new ExcelUtil<>();
        String exportFileName = "维修单退款_"+ UUIDGenerater.getFileName();
        excelUtil.exportExcelWithoutTemplate(exportFileName, "sheet1", dredgeRefundList, DredgeRefundEntity.class, response);
    }
}
