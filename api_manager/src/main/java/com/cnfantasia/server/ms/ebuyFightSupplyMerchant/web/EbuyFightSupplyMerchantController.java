package com.cnfantasia.server.ms.ebuyFightSupplyMerchant.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.api.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchantDto;
import com.cnfantasia.server.api.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;
import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.service.IEbuyFightMerchantHasGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;
import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.service.IEbuyFightSupplyMerchantBaseService;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 自提点控制类 create 2016.05.25
 */
@Controller
@RequestMapping(value = "/ebuyFightSupplyMerchant")
public class EbuyFightSupplyMerchantController extends BaseController {

    @Resource
    private IEbuyFightSupplyMerchantService ebuyFightSupplyMerchantService;
    @Resource
    private IEbuyFightMerchantHasGroupBuildingBaseService ebuyFightMerchantHasGroupBuildingBaseService;
    @Resource
    private IGroupBuildingService msGroupBuildingService;
    @Resource
    private IEbuyFightSupplyMerchantBaseService ebuyFightSupplyMerchantBaseService;
    /**
     * 自提点列表
     * @return
     */
    @RequestMapping(value = "/list.html")
    public ModelAndView jumpToList(EbuyFightSupplyMerchantDto dto, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("ebuyFightSupplyMerchant/fightMerchantList");
        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名

        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));

        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);

        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
        List<EbuyFightSupplyMerchantDto> dtos = ebuyFightSupplyMerchantService.getFightMerchantList(dto, pageModel);
        BigInteger total = ebuyFightSupplyMerchantService.getFightMerchantCount(dto);
        mav.addObject("param", dto);
        mav.addObject("resList", dtos);
        mav.addObject("resultSize", total.intValue());
        return mav;
    }

    @RequestMapping(value = "/fightMerchantDetail.html", method = RequestMethod.GET)
    public ModelAndView viewFightMerchantDetail(EbuyFightSupplyMerchantDto dto) {
        ModelAndView mav = addOrUpdMav(dto);
        mav.setViewName("ebuyFightSupplyMerchant/fightMerchantDetail");
        return mav;
    }

    @RequestMapping(value = "/updFightMerchant.html", method = RequestMethod.GET)
    public ModelAndView jumpToUpdFightMerchant(EbuyFightSupplyMerchantDto dto) {
        ModelAndView mav = addOrUpdMav(dto);
        mav.setViewName("ebuyFightSupplyMerchant/updFightMerchant");
        return mav;
    }
    private ModelAndView addOrUpdMav(EbuyFightSupplyMerchantDto dto) {
        ModelAndView mav = new ModelAndView();
        PageModel pageModel = new PageModel(0, 1);
        List<EbuyFightSupplyMerchantDto> dtos = ebuyFightSupplyMerchantService.getFightMerchantList(dto, pageModel);
        dto = null;

        //查小区
        if (!DataUtil.isEmpty(dtos)) {
            dto = dtos.get(0);
            EbuyFightMerchantHasGroupBuilding hasGroupBuilding = new EbuyFightMerchantHasGroupBuilding();
            hasGroupBuilding.settEbuyFightSupplyMerchantFId(dto.getFightMerchantId());
            List<EbuyFightMerchantHasGroupBuilding> buildings = ebuyFightMerchantHasGroupBuildingBaseService.getEbuyFightMerchantHasGroupBuildingByCondition(MapConverter.toMap(hasGroupBuilding));
            List<BigInteger> ids = new ArrayList<BigInteger>();
            for (EbuyFightMerchantHasGroupBuilding building : buildings) {
                ids.add(building.gettGroupBuildingId());
            }
            if (ids.size() > 0) {
                List<Map<String, Object>> buildingInfos = msGroupBuildingService.getBuildingListBySeqIdList(ids);
                mav.addObject("buildings", buildingInfos);
            }
        }
        mav.addObject("dto", dto);
        return mav;
    }

    @RequestMapping(value = "/updFightMerchant.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updFightMerchant(EbuyFightSupplyMerchantDto dto) {
        ebuyFightSupplyMerchantService.updFightMerchant(dto);
        return new JsonResponse();
    }

    @RequestMapping(value = "/addFightMerchant.html", method = RequestMethod.GET)
    public ModelAndView jumpToAddFightMerchant() {
        return new ModelAndView("ebuyFightSupplyMerchant/addFightMerchant");
    }

    @RequestMapping(value = "/addFightMerchant.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addFightMerchant(EbuyFightSupplyMerchantDto dto) {
        BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
        dto.getEbuyFightSupplyMerchant().setSys0AddUser(userId);
        ebuyFightSupplyMerchantService.addFightMerchant(dto);
        return new JsonResponse();
    }

    @RequestMapping(value = "/getFightMerchantListByName.json")
    @ResponseBody
    public JsonResponse getFightMerchantListByName(String name) {
        JsonResponse json = new JsonResponse();
        EbuyFightSupplyMerchant ebuyFightSupplyMerchant = new EbuyFightSupplyMerchant();
        ebuyFightSupplyMerchant.setName(name);
        List<EbuyFightSupplyMerchant> fightMerchantList = ebuyFightSupplyMerchantBaseService
                .getEbuyFightSupplyMerchantByConditionDim(MapConverter.toMap(ebuyFightSupplyMerchant));
        json.putData("list", fightMerchantList);
        return json;
    }
}
