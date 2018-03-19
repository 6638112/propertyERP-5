package com.cnfantasia.server.ms.flashDealActivity.web;

import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;
import com.cnfantasia.server.domainbase.ebuyProductShelf.service.IEbuyProductShelfBaseService;
import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.entity.EbuySupplyMerchant;
import com.cnfantasia.server.domainbase.ebuySupplyMerchant.service.IEbuySupplyMerchantBaseService;
import com.cnfantasia.server.domainbase.flashDealActivity.entity.FlashDealActivity;
import com.cnfantasia.server.domainbase.flashDealActivity.service.IFlashDealActivityBaseService;
import com.cnfantasia.server.ms.ebuyProduct.service.IEbuyProductService;
import com.cnfantasia.server.ms.ebuyProductType.service.IEbuyProductTypeService;
import com.cnfantasia.server.ms.flashDealActivity.entity.FlashDealActivityCfgParam;
import com.cnfantasia.server.ms.flashDealActivity.service.IFlashDealActivityCfgService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FlashDealActvityCfgController
 * @Date: 2016-11-23 16:54
 * @Auther: yanghua
 * @Description:(幸运购活动配置)
 */
@Controller
@RequestMapping("/flashDealActivityCfg")
public class FlashDealActivityCfgController extends BaseController {

    @Resource
    private IEbuyProductService ebuyProductService;
    @Resource
    private IEbuySupplyMerchantBaseService ebuySupplyMerchantBaseService;
    @Resource
    private IEbuyProductTypeService ebuyProductTypeService;
    @Resource
    private IEbuyProductShelfBaseService ebuyProductShelfBaseService;
    @Resource
    private IFlashDealActivityCfgService flashDealActivityCfgService;
    @Resource
    private IFlashDealActivityBaseService flashDealActivityBaseService;

    /**
     * 幸运购活动列表
     * @param flashDealActivityCfgParam
     * @return
     */
    @RequestMapping("/flashDealActivityList.html")
    public ModelAndView list(FlashDealActivityCfgParam flashDealActivityCfgParam,HttpServletRequest request) {
        Map<String, Object> paramMap = MapConverter.toMap(flashDealActivityCfgParam);
        ModelAndView modelAndView = new ModelAndView();
        // 页数的参数名
        String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
        // 每页显示的条数
        int pageSize = Integer.valueOf(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        // 当前页
        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
        paramMap.put("_begin", pageSize * curPageIndex);
        paramMap.put("_length", pageSize);

        List<FlashDealActivityCfgParam> flashDealActivityList = flashDealActivityCfgService.getFlashDealActivityList(paramMap);
        //总数量
        int resultSize = flashDealActivityCfgService.getFlashDealActivityListCount(paramMap);

        request.setAttribute("resultSize", resultSize);
        request.setAttribute("resList", flashDealActivityList);
        request.setAttribute("param", flashDealActivityCfgParam);
        return new ModelAndView("/flashDealActivity/flashDealActivityList");
    }

    /**
     * 跳转到发布幸运购界面
     * @param request
     * @return
     */
    @RequestMapping("/jumpToFlashDealActivityCfg.html")
    public ModelAndView jumpToFlashDealActivityCfg(HttpServletRequest request) {
        BigInteger productId = CnfantasiaCommUtil.convert2BigInteger(request.getParameter("epId"));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/flashDealActivity/addFlashDealActivityCfg");
        //获取商品，货架信息
        getProductAndShelf(modelAndView, productId);

        return modelAndView;
    }

    /**
     * 保存幸运购配置信息
     * @param flashDealActivityCfgParam
     * @param response
     * @return
     */
    @RequestMapping("/saveFlashDealActivityCfg.html")
    @ResponseBody
    public JsonResponse saveFlashDealActivityCfg(FlashDealActivityCfgParam flashDealActivityCfgParam) {
        JsonResponse jsonResponse = new JsonResponse();
        flashDealActivityCfgService.saveFlashDealActivityCfg(flashDealActivityCfgParam);
        return jsonResponse;
    }

    /**
     * 查看/编辑活动详情
     * @param pageType
     * @param productId
     * @return
     */
    @RequestMapping("/flashDealActivityCfgDetail.html")
    public ModelAndView ebuyProductDetail(String pageType, BigInteger flashDealActivityId){
        ModelAndView modelAndView = new ModelAndView();
        if(pageType.equals("detail")){
            FlashDealActivityCfgParam flashDealActivityCfgParam = flashDealActivityCfgService.getFlashDealActivityDetail(flashDealActivityId);
            //获取商品，货架信息
            getProductAndShelf(modelAndView, flashDealActivityCfgParam.gettEbuyProductFId());

            modelAndView.addObject("flashDealActivityCfgParam", flashDealActivityCfgParam);
            modelAndView.setViewName("/flashDealActivity/flashDealActivityDetail");
        } else if(pageType.equals("edit")){
            FlashDealActivity flashDealActivity = flashDealActivityBaseService.getFlashDealActivityBySeqId(flashDealActivityId);
            //获取商品，货架信息
            getProductAndShelf(modelAndView, flashDealActivity.gettEbuyProductFId());

            modelAndView.addObject("flashDealActivity", flashDealActivity);
            modelAndView.setViewName("/flashDealActivity/addFlashDealActivityCfg");
        }
        return modelAndView;
    }

    /**
     *  商品附属信息,货架设置的价格信息
     * @param modelAndView
     * @param productId
     */
    private void getProductAndShelf(ModelAndView modelAndView, BigInteger productId) {
        //商品附属信息
        EbuyProduct ebuyProduct = ebuyProductService.getEbuyProductBySeqId(productId);
        EbuySupplyMerchant ebuySupplyMerchant = ebuySupplyMerchantBaseService.getEbuySupplyMerchantBySeqId(ebuyProduct.gettSupplyMerchantFId());
        EbuyProductType ebuyProductType = ebuyProductTypeService.getEbuyProductTypeBySeqId(ebuyProduct.gettEbuyProductTypeFId());

        //货架设置的价格信息
        EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
        ebuyProductShelf.settEbuyProductId(productId);
        ebuyProductShelf.setUpShelfState(0);
        Long priceDiscount = 0L;
        List<EbuyProductShelf> shelfs = ebuyProductShelfBaseService.getEbuyProductShelfByCondition(MapConverter.toMap(ebuyProductShelf));
        if (!DataUtil.isEmpty(shelfs)) {
            for (EbuyProductShelf shelf : shelfs) {
                if ((ebuyProduct.gettEbuyProductTypeFId()).compareTo(shelf.gettEbuyProductTypeId()) == 0) {
                    priceDiscount = shelf.getPriceDiscount();
                }
            }
        }
        if(priceDiscount.compareTo(0L) == 0) {//不理解上述获取价格的问题，所以如果价格为零则直接取商品的折扣价格
            priceDiscount = ebuyProduct.getPriceDiscount();
        }
        modelAndView.addObject("priceDiscount", priceDiscount);
        modelAndView.addObject("ebuyProduct", ebuyProduct);
        modelAndView.addObject("ebuyProductType", ebuyProductType);
        modelAndView.addObject("ebuySupplyMerchant", ebuySupplyMerchant);
    }
}
