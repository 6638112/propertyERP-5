package com.cnfantasia.server.api.dredge.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commSysPara.entity.CompanyInfoConfig;
import com.cnfantasia.server.api.commSysPara.parser.CompanyInfoParamParser;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.communitySupply.entity02.Location;
import com.cnfantasia.server.api.coupon.constant.CouponUseTypeConstant;
import com.cnfantasia.server.api.coupon.entity.UserCouponEntity;
import com.cnfantasia.server.api.couponArea.contant.UserCouponStatus;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.AccountAmount;
import com.cnfantasia.server.api.dredge.entity.AddDredgeBillParamter;
import com.cnfantasia.server.api.dredge.entity.AmountDetail;
import com.cnfantasia.server.api.dredge.entity.ApplyListForMaster;
import com.cnfantasia.server.api.dredge.entity.ApplyWithdrawInfo;
import com.cnfantasia.server.api.dredge.entity.BlockForMaster;
import com.cnfantasia.server.api.dredge.entity.BlockWithDredgeService;
import com.cnfantasia.server.api.dredge.entity.CanWithdrawBill;
import com.cnfantasia.server.api.dredge.entity.CanWithdrawBill4Master;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForDetail;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForList;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForMaster;
import com.cnfantasia.server.api.dredge.entity.DredgeParentType;
import com.cnfantasia.server.api.dredge.entity.DredgeProductEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeForUser;
import com.cnfantasia.server.api.dredge.entity.IncomeWithdrawRecorder;
import com.cnfantasia.server.api.dredge.entity.MasterProfile;
import com.cnfantasia.server.api.dredge.entity.RSADetail;
import com.cnfantasia.server.api.dredge.entity.SelfBuyProduct;
import com.cnfantasia.server.api.dredge.service.DredgeService;
import com.cnfantasia.server.api.ebuy.domain.Share;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.ebuy.entity.EbuyAdvertise;
import com.cnfantasia.server.api.ebuy.entity.ProductIdQtyEntity;
import com.cnfantasia.server.api.ebuy.service.IEbuyAdvertiseService;
import com.cnfantasia.server.api.ebuy.service.IEbuyNewService;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.login.entity.SimpleLoginInfo;
import com.cnfantasia.server.api.operation.entity.AddressIdEntity;
import com.cnfantasia.server.api.operation.service.IAddressOperationService;
import com.cnfantasia.server.api.propertyRepair.service.IPropertyRepairService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.header.HeaderManager;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.redenvelope.entity.SimpleHbBalanceEntity;
import com.cnfantasia.server.api.redenvelope.service.IRedenvelopeService;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.room.service.IGroupBuildingService;
import com.cnfantasia.server.api.userCoupon.service.IUserCouponService;
import com.cnfantasia.server.business.pub.RepeatReqValidation.annotation.RepeatReqValidate;
import com.cnfantasia.server.business.pub.communitySupply.ICommunitySupplyManager;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.CommRequestFileParser;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressCity.service.IAddressCityBaseService;
import com.cnfantasia.server.domainbase.coupon.entity.Coupon;
import com.cnfantasia.server.domainbase.dredgeBankCard.entity.DredgeBankCard;
import com.cnfantasia.server.domainbase.dredgeBankCard.service.DredgeBankCardBaseService;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBill.service.DredgeBillBaseService;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.service.IDredgeBillAmountDetailBaseService;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.entity.DredgeBillHasProcessRecording;
import com.cnfantasia.server.domainbase.dredgeBillHasProcessRecording.service.IDredgeBillHasProcessRecordingBaseService;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProduct.service.IDredgeProductBaseService;
import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;
import com.cnfantasia.server.domainbase.dredgeType.service.DredgeTypeBaseService;
import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;
import com.cnfantasia.server.domainbase.dredgeType2nd.service.IDredgeType2ndBaseService;
import com.cnfantasia.server.domainbase.dredgeWorker.entity.DredgeWorker;
import com.cnfantasia.server.domainbase.dredgeWorker.service.IDredgeWorkerBaseService;
import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.loginNo.service.LoginNoBaseService;
import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;
import com.cnfantasia.server.domainbase.supportBank.entity.SupportBank;
import com.cnfantasia.server.domainbase.supportBank.service.ISupportBankBaseService;
import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/**
 * 疏通服务
 *
 * @author wenfq 20150720
 */
@RequestMapping("/dredge")
@Controller
public class DredgeController extends BaseController{
    private Log logger = LogFactory.getLog(getClass());

    @Resource
    private DredgeTypeBaseService dredgeTypeBaseService;

    @Resource
    private DredgeBillBaseService dredgeBillBaseService;

    @Resource
    private DredgeService dredgeService;

    @Resource
    private ICommonRoomService commonRoomService;

    @Resource
    private IRedenvelopeService redenvelopeService;

    @Resource
    private ISysParamManager sysParamManager;

    @Resource
    private ICommunitySupplyManager communitySupplyManager;

    @Resource
    private IFileServerService fileServerService;

    @Resource
    private IPropertyRepairService propertyRepairService;

    @Resource
    private IDredgeBillAmountDetailBaseService dredgeBillAmountDetailBaseService;

    @Resource
    private IRedpointService redpointService;

    @Resource
    private IUserCouponService userCouponService;

    @Resource
    private IEbuyOrderBaseService ebuyOrderBaseService;

    @Resource
    private IGroupBuildingService groupBuildingService;

    @Resource
    private IDredgeBillHasProcessRecordingBaseService dredgeBillHasProcessRecordingBaseService;

    @Resource
    private IEbuyNewService ebuyNewService;

    @Resource
    private IAddressOperationService addressOperationService;

    @Resource
    private IDredgeProductBaseService dredgeProductBaseService;

    @Resource
    private IDredgeType2ndBaseService dredgeType2ndBaseService;

    @Resource
    private IEbuyAdvertiseService ebuyAdvertiseService;

    @Resource
    private IAddressCityBaseService addressCityBaseService;

    /**
     * 获取疏通服务类型
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDredgeTypeList.json")
    @Deprecated
    public JsonResponse getDredgeTypeList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();

        // 1.搜集参数
        BigDecimal parentTypeId = ParamUtils.getBigDecimal(request, "parentTypeId", null);

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        // 2.交互
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tCommunitySupplyTypeFId", parentTypeId);
        List<DredgeType> dredgeTypeList = dredgeTypeBaseService.getDredgeTypeByCondition(paramMap, pageModel);

        // 3.结果处理
        List<DredgeTypeForUser> resList = new ArrayList<DredgeTypeForUser>();
        for (DredgeType DredgeType : dredgeTypeList) {
            DredgeTypeForUser dredgeTypeForUser = dredgeService.getDredgeTypeForUser(DredgeType);
            resList.add(dredgeTypeForUser);
        }

        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * 获取疏通服务类型
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDredgeTypeListNew.json")
    public JsonResponse getDredgeTypeListNew(BigInteger parentTypeId, BigInteger blockId, BigInteger gbId) {
        JsonResponse jsonResponse = new JsonResponse();
        List<BigInteger> addrCodeIdList = getAddrCodeIdList(blockId, gbId, UserContext.getOperIdBigInteger());
        List<DredgeTypeEntity> entityList = dredgeService.getDredgeTypeByParentTypeId(parentTypeId, addrCodeIdList, false);
        jsonResponse.putData("dredgeTypeList", entityList);
        return jsonResponse;
    }

    private List<BigInteger> getAddrCodeIdList(BigInteger blockId, BigInteger gbId, BigInteger userId) {
        if (blockId != null || gbId != null) {
            return addressOperationService.getCodeIdList(null, null, null, blockId, gbId);
        }
        gbId = HeaderManager.getGbId() == null ? commonRoomService.getGroupBuildingIdByUserId(userId) : HeaderManager.getGbId();
        return addressOperationService.getCodeIdList(null, null, null, null, gbId);
    }

    /**
     * 查询维修服务商品
     *
     * @param dredgeType2ndId 维修子类
     * @param city            城市，LA会用
     * @return
     */
    @RequestMapping(value = "/qryDredgeProductList.json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JsonResponse getDredgeProductList(BigInteger dredgeType2ndId, BigInteger blockId, BigInteger gbId, HttpServletRequest request) {
        PageModel pageModel = ControllerUtils.getPageModel(request);
        JsonResponse json = new JsonResponse();
        BigInteger userId = UserContext.getOperIdBigInteger();
        List<BigInteger> addrCodeIdList = getAddrCodeIdList(blockId, gbId, userId);
        List<DredgeProductEntity> productEntityList = dredgeService.getDredgeProductListByDredgeType2ndId(dredgeType2ndId, addrCodeIdList, pageModel);
        if (!DataUtil.isEmpty(productEntityList)) {
            for (DredgeProductEntity dredgeProductEntity : productEntityList) {
                String desc = ebuyNewService.getEbuyThemeDescByShelfId(dredgeProductEntity.getId(), 2);
                dredgeProductEntity.setInAnniversary(desc != null);
            }
        }
        json.putData("productList", productEntityList);
        return json;
    }

    /**
     * 查维修服务商品详情
     *
     * @param dredgeProductId 商品ID
     * @return
     */
    @RequestMapping(value = "/qryDredgeProductDetail.json")
    @ResponseBody
    public JsonResponse getDredgeProductDetail(BigInteger dredgeProductId) {
        JsonResponse json = new JsonResponse();
        DredgeProductEntity dredgeProductDetail = dredgeService.getDredgeProductDetail(dredgeProductId);
        json.putData("productDetail", dredgeProductDetail);
        String themeAdDesc = ebuyNewService.getEbuyThemeDescByShelfId(dredgeProductId, 2);
        json.putData("themeAdDesc", themeAdDesc);
        DredgeProduct dredgeProduct = dredgeProductBaseService.getDredgeProductBySeqId(dredgeProductId);
        DredgeType2nd dredgeType2nd = dredgeType2ndBaseService.getDredgeType2ndBySeqId(dredgeProduct.getDredgeType2ndId());
        Share share = new Share();
        share.setFriendTitle(dredgeProduct.getShareFriendTitle());
        share.setCycleTitle(dredgeProduct.getShareCycleTitle());
        share.setSharePic(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, dredgeProduct.getSharePic(), null));
        share.setSharePushPic(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.DredgePicBasePath, dredgeProduct.getSharePushPic(), null));
        share.setDesc(dredgeProduct.getShareContent().replaceAll("\\n|\\t|\\r", ""));
        share.setUrl(CnfantasiaCommbusiUtil.getSysParaValue("laUrl") + "/dredge/productDetail.html?productId=" + dredgeProductId);
        json.putData("share", share);
        json.putData("dredgeTypeId", dredgeType2nd.gettDredgeTypeFId());
        json.putData("subTypeId", dredgeType2nd.getId());
        return json;
    }

    @RequestMapping(value = "/productDetail.html", method = RequestMethod.GET)
    public String dredgeProductDetailPage() {
        return "/dredge/productDetail";
    }

    /**
     * 获取上门服务大类
     *
     * @return
     */
    @RequestMapping(value = "/qryCommunitySupplyType.json")
    @ResponseBody
    public JsonResponse qryCommunitySupplyType(BigInteger blockId, BigInteger gbId) {
        JsonResponse json = new JsonResponse();
        gbId = gbId == null ? HeaderManager.getGbId() : gbId;
        //取上门服务类
        List<BigInteger> addrCodeIdList = getAddrCodeIdList(blockId, gbId, UserContext.getOperIdBigInteger());
        List dredgeList = communitySupplyManager.getCommunitySupplyTypeList(addrCodeIdList);
        json.putData("dredgeList", dredgeList);
        return json;
    }

    /**
     * 获取上门服务类型及子类信息
     *
     * @param userId api_manager（PropertyRepairController.queryServiceTypes）调用需要
     * @return
     */
    @RequestMapping(value = "/qryCommunitySupplyTypeAndDredgeType.json")
    @ResponseBody
    @Deprecated
    public JsonResponse qryCommunitySupplyTypeAndDredgeType(BigInteger userId) {
        if (userId == null) {
            userId = UserContext.getOperIdMustExistBigInteger();
        }
        String blockId = commonRoomService.
                getGroupBuildingByUserId(userId).gettBlockFId().toString();
        JsonResponse json = new JsonResponse();

        //取上门服务类
        List<Map<String, Object>> dredgeList = communitySupplyManager.getCommunitySupplyTypeList(null);
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        Map<String, Object> resMap = null;
        for (Map<String, Object> map : dredgeList) {
            BigInteger dredgeType = (BigInteger) map.get("id");
            //v505-yh增加了小区管理处维度---增加了一个变量--管理处id，此处不填写
//			List dredgeTypes = communitySupplyManager.getDredgeTypeListByParentId(dredgeType, blockId, null);
            List<BigInteger> addrCodeIdList = getAddrCodeIdList(null, null, userId);
            List<DredgeTypeEntity> entityList = dredgeService.getDredgeTypeByParentTypeId(dredgeType, addrCodeIdList, true);
            resMap = new HashMap<String, Object>(map);
            resMap.put("item", entityList);
            resList.add(resMap);
        }
        json.putData("list", resList);
        return json;
    }


    /**
     * 添加疏通服务工单
     *
     * @param request
     * @return
     */
    @RequestMapping("/addDredgeBill.json")
    @ResponseBody
    @RepeatReqValidate
    public JsonResponse addDredgeBill(AddDredgeBillParamter parameter, HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();

        //自选耗材
        List<ProductIdQtyEntity> productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);
        parameter.setProductIdQtyList(productIdQtyList);

        //商品规格
        List<DredgeProductSpecEntity> specList = JSON.parseArray(request.getParameter("specList"), DredgeProductSpecEntity.class);
        parameter.setProductSpecList(specList);

        //用户Id
        BigInteger userId = ParamUtils.getBigInteger(request, "userId", null);// api_manager调用需要
        if (userId == null) {
            userId = UserContext.getOperIdMustExistBigInteger();
        }
        parameter.setUserId(userId);
        BigInteger roomId = commonRoomService.getDefaultRoomByUserId(userId).getId();
        parameter.setRoomId(roomId);

        if (parameter.getBlockId() == null) {
            BigInteger blockId = commonRoomService.getGroupBuildingByUserId(userId).gettBlockFId();
            parameter.setBlockId(blockId);
        }

        if (parameter.getDredgeContent() != null && parameter.getDredgeContent().length() > 1000) {
            throw new ValidateRuntimeException("Dredge.addDredge.dredgeeContent.length>1000");
        }

        List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
        parameter.setPicList(picList);
        parameter.setSubmitChannel(1);
        //2.交互
        Map<String, Object> res = dredgeService.addDredgeBill(parameter);
        jsonResponse.putDataAll(res);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 判断当前区域是否开通疏通预约服务
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryIsOpenDredgeServiceByBlockId.json")
    @ResponseBody
    public JsonResponse qryIsOpenDredgeService4Block(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String blockId = request.getParameter("blockId");
        String dredgeTypeId = request.getParameter("dredgeTypeId");

        //2.交互
        int isOpenDredgeService = dredgeService.qryIsOpenDredgeService4Block(blockId, dredgeTypeId);

        jsonResponse.putData("isOpenDredgeService", isOpenDredgeService);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 判断当前门牌是否开通疏通预约服务
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryIsOpenDredgeServiceByRoomId.json")
    @ResponseBody
    public JsonResponse qryIsOpenDredgeService4Room(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String dredgeTypeId = request.getParameter("dredgeTypeId");

        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String blockId = commonRoomService.getGroupBuildingByUserId(userId).gettBlockFId().toString();
        int isOpenDredgeService = dredgeService.qryIsOpenDredgeService4Block(blockId, dredgeTypeId);

        jsonResponse.putData("isOpenDredgeService", isOpenDredgeService);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 判断当前城市的疏通预约服务开通情况
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryDredgeServiceListByCity.json")
    @ResponseBody
    public JsonResponse qryDredgeServiceListByCity(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String cityId = request.getParameter("cityId");
        String cityName = request.getParameter("cityName");
        String dredgeTypeId = request.getParameter("dredgeTypeId");
        if (StringUtils.isEmpty(dredgeTypeId)) {
            BigInteger dredgeProductId = ParamUtils.getBigInteger(request, "dredgeProductId", BigInteger.ZERO);
            DredgeProduct dredgeProduct = dredgeProductBaseService.getDredgeProductBySeqId(dredgeProductId);
            DredgeType2nd type2nd = dredgeType2ndBaseService.getDredgeType2ndBySeqId(dredgeProduct.getDredgeType2ndId());
            dredgeTypeId = type2nd.gettDredgeTypeFId().toString();
        }
        //2.交互

        List<BlockWithDredgeService> blockWithDredgeServiceList = dredgeService.qryDredgeServiceListByCity(cityId, cityName, dredgeTypeId);

        jsonResponse.putData(PageModel.PageKey.LIST, blockWithDredgeServiceList);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 查询我的预约单
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryMyDredgeBillList.json")
    @ResponseBody
    public JsonResponse queryMyDredgeBillList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        int type = ParamUtils.getInt(request, "type", 1);
        boolean fromLa = ParamUtils.getBoolean(request, "fromLA", false);

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

//		BigInteger userId = new BigInteger("5260130");
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);

        List<DredgeBillForList> dredgeBillForList = dredgeService.getDredgeBillList(userId, type, gbId, pageModel, fromLa);

        //3.结果处理
        return ControllerUtils.addPageInfo(jsonResponse, dredgeBillForList, pageModel.isLast, pageModel.count);
    }

    /**
     * 查询我的报修单
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryMyDredgeRepairList.json")
    @ResponseBody
    public JsonResponse queryMyDredgeRepairList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        int type = ParamUtils.getInt(request, "type", 1);

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);

        List<DredgeBillForList> dredgeBillForList = dredgeService.getDredgeRepairList(userId, type, gbId, pageModel);
        jsonResponse.putData("list", dredgeBillForList);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 查询预约单详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryDredgeBillDetail.json")
    @Deprecated // 改用qryDredgeBillOrPropertyRepairDetail.json
    @ResponseBody
    public JsonResponse qryDredgeBillDetail(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String id = request.getParameter("id");

        //2.交互
        DredgeBillForDetail db = dredgeService.qryDredgeBillDetail(id);
        dredgeService.processDredgeBillPicInfo(db);
        jsonResponse.setDataValue(MapConverter.toMap(db));

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 查询预约单详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/qryDredgeBillOrPropertyRepairDetail.json")
    @ResponseBody
    @Deprecated //app从507开始即用预支付接口查，LA还在用
    public JsonResponse qryDredgeBillOrPropertyRepairDetail(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        String id = request.getParameter("id");
        int billType = ParamUtils.getInt(request, "billType", 1);
        DredgeBillForDetail db = null;
        if (billType == DredgeConstant.DredgeBillType.Dredge_Bill_Common
                || billType == DredgeConstant.DredgeBillType.Dredge_Bill_Transed
                || billType == DredgeConstant.DredgeBillType.Dredge_Repair
                || billType == DredgeConstant.DredgeBillType.Dredge_Pay_First) {
            db = dredgeService.qryDredgeBillDetail(id);
            dredgeService.appendMaterialFee(db);
            dredgeService.processDredgeBillPicInfo(db);
        } else if (billType == DredgeConstant.DredgeBillType.Property_Repair) {
            db = dredgeService.qryPropertyRepairDetail(new BigInteger(id));
            List<String> picInfo = new ArrayList<String>();
            if (!StringUtils.isEmpty(db.getPicUrl())) {
                String[] pics = db.getPicUrl().split(";");
                String relativePath = sysParamManager.getSysParaValue(SysParamKey.Repair_Pic_BasePath);
                for (String pic : pics) {
                    picInfo.add(fileServerService.getAccessUrl(relativePath + pic, new DredgeBill()));
                }
            }
            db.setPicInfo(picInfo);
        }
        //2.交互
        jsonResponse.setDataValue(MapConverter.toMap(db));
        if ((billType == DredgeConstant.DredgeBillType.Property_Repair || billType == DredgeConstant.DredgeBillType.Dredge_Repair)
                && db.getStatus() == DredgeConstant.DredgeBill.DredgeBill_Status_Property_Closed) {
            if (StringUtils.isEmpty(db.getCloseDesc())) {
                jsonResponse.putData("propertyCloseDesc", "物业关闭此单，此单可能不在物业服务范围内，业主可选用其它预约类型。");
            } else {
                jsonResponse.putData("propertyCloseDesc", db.getCloseDesc());
            }
        }

        CompanyInfoParamParser companyInfoParamParser = (CompanyInfoParamParser) CnfantasiaCommbusiUtil.getBeanManager("companyInfoParamParser");
        CompanyInfoConfig companyInfoConfig = companyInfoParamParser.parseParamValue();
        logger.debug("test-here1!");
        logger.debug("test-here2!" + (companyInfoConfig == null));
        jsonResponse.putData("servePhone", companyInfoConfig.getTel());
        logger.debug("test-here3!" + companyInfoConfig.getTel());

        logger.debug("test-here3!" + db.getOrderId());
        if (!StringUtils.isEmpty(db.getOrderId())) {
            EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(BigInteger.valueOf(db.getOrderId()));
            jsonResponse.putData("totalCouponAmount", PriceUtil.div100(order.getTotalCouponAmount()));
            jsonResponse.putData("payMethod", order.getPayMethod());
            jsonResponse.putData("tradeTime", order.getSys0UpdTime());
            jsonResponse.putData("orderNo", order.getOrderNo());
        } else {
            jsonResponse.putData("totalCouponAmount", null);
            jsonResponse.putData("payMethod", null);
            jsonResponse.putData("tradeTime", null);
            jsonResponse.putData("orderNo", null);
        }

        Map<String, Object> dbParam = new HashMap<String, Object>();
        dbParam.put("dredgeBillId", id);
        dbParam.put("userId", UserContext.getOperIdMustExistBigInteger());
        List<SelfBuyProduct> selfBuyProductList = dredgeService.getSelfBuyProductList(dbParam);
        jsonResponse.putData("selfBuyProductList", selfBuyProductList);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 流程记录(师傅端)
     *
     * @param request
     * @return
     */
    @RequestMapping("/master/addProcessRecording.json")
    @ResponseBody
    public JsonResponse addProcessRecording(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger billId = ParamUtils.getBigInteger(request, "billId", null);
        String processDesc = request.getParameter("processDesc");
        int billType = ParamUtils.getInt(request, "billType", 1);
        List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");

        if (billId == null) {
            throw new ValidateRuntimeException("billId cant be null!");
        }

        if (StringUtils.isEmpty(processDesc) && (picList == null || picList.isEmpty())) {
            throw new BusinessRuntimeException("DredgeController.addProcessRecording.descAndPicCantBeAllNull");
        }

        //2.交互
        dredgeService.addProcessRecording(billId, processDesc, billType, picList);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 查询预约单详情(师傅端)
     *
     * @param request
     * @return
     */
    @RequestMapping("/master/qryDredgeBillDetail.json")
    @ResponseBody
    public JsonResponse qryDredgeBillDetailForMaster(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String id = request.getParameter("id");
        int isPropertyBill = ParamUtils.getInt(request, "isPropertyBill", 0);

        //2.交互
        DredgeBillForMaster db = dredgeService.qryDredgeBillDetailForMaster(id, isPropertyBill);
        appendMaterialFee(db);

        jsonResponse.setDataValue(MapConverter.toMap(db));

        // 取出流程记录
        {
            DredgeBillHasProcessRecording dredgeBillHasProcessRecordingQry = new DredgeBillHasProcessRecording();
            dredgeBillHasProcessRecordingQry.setBillType(isPropertyBill == 1 ? DredgeConstant.DredgeBillType.Property_Repair : DredgeConstant.DredgeBillType.Dredge_Bill_Common);
            if (isPropertyBill == 1) {
                dredgeBillHasProcessRecordingQry.settPropertyRepairFId(new BigInteger(id));
            } else if (isPropertyBill == 0) {
                dredgeBillHasProcessRecordingQry.settDredgeBillFId(new BigInteger(id));
            }
            List<DredgeBillHasProcessRecording> prList = dredgeBillHasProcessRecordingBaseService.getDredgeBillHasProcessRecordingByCondition(MapConverter.toMap(dredgeBillHasProcessRecordingQry));
            if (!prList.isEmpty())
                jsonResponse.putData("processRecordList", dredgeService.convertProcessRecordMap(prList));
        }

        Map<String, Object> dbParam = new HashMap<>();
        dbParam.put("dredgeBillId", id);
        List<SelfBuyProduct> selfBuyProductList = dredgeService.getSelfBuyProductList(dbParam);
        jsonResponse.putData("selfBuyProductList", selfBuyProductList);

        //3.结果处理
        return jsonResponse;
    }

    private void appendMaterialFee(DredgeBillForMaster db) {
        if (db.getAmountList() == null) {
            db.setAmountList(new ArrayList<AmountDetail>());
        }

        if (db.getAmountList().size() == 0) {
            AmountDetail ad = new AmountDetail();
            ad.setId(-1);
            ad.setFeeAmount(db.getPayAmount() == null ? BigDecimal.ZERO.setScale(2) : db.getPayAmount());
            ad.setFeeName("人工费");
            ad.setIsIncludePlatformFee(1);
            ad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Labor_fee);
            db.getAmountList().add(ad);

            AmountDetail ad2 = new AmountDetail();
            ad2.setId(-2);
            ad2.setFeeAmount(BigDecimal.ZERO.setScale(2));
            ad2.setFeeName("其它费");
            ad2.setIsIncludePlatformFee(0);
            ad2.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
            db.getAmountList().add(ad2);
        } else if (db.getAmountList().size() == 1) {
            AmountDetail ad = new AmountDetail();
            ad.setId(-1);
            ad.setFeeAmount(BigDecimal.ZERO.setScale(2));
            ad.setFeeName("其它费");
            ad.setIsIncludePlatformFee(0);
            ad.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
            db.getAmountList().add(ad);
        }
    }

    /**
     * 取消预约单
     *
     * @param request
     * @return
     */
    @RequestMapping("/cancelDredge.json")
    @ResponseBody
    public JsonResponse cancelDredge(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger id = ParamUtils.getBigInteger(request, "id", null);
        String cancelDesc = request.getParameter("cancelDesc");

        //2.交互
        DredgeBill dredgeBill = dredgeBillBaseService.getDredgeBillBySeqId(id);
        if (dredgeBill.gettWorkerFId() != null) {
            //throw new ValidateRuntimeException("师傅已接单，不能取消");
            jsonResponse.setStatus("0001");
            jsonResponse.setMessage("师傅已接单，不能取消");
            return jsonResponse;
        }

        DredgeBill db = new DredgeBill();
        db.setId(id);
        db.setCancelReason(cancelDesc);
        db.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Canceled);
        int updCount = dredgeBillBaseService.updateDredgeBill(db);
        if (updCount == 1) {
            try {//添加订单完成红点
                DredgeBill dredgeBill2 = dredgeBillBaseService.getDredgeBillBySeqId(id);
                List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
                sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.Dredage, dredgeBill2.getId(), "UPDATE"));
                redpointService.addRedpointContent(dredgeBill2.gettUserFId(), LoginDict.UserRegistOrTmp.REGIST_USER, dredgeBill2.getRoomid(),
                        RedpointConstant.RedpointContent_ModelCode.DREDGEBILL_HASFINISHED, sourceList);
            } catch (Exception e) {
                logger.info(e.getMessage(), e);
            }
        }

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 准备支付工单
     *
     * @param request
     * @return
     */
    @RequestMapping("/prepareForPay.json")
    @ResponseBody
    public JsonResponse prepareForPay(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger id = ParamUtils.getBigInteger(request, "id", null);
        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        DredgeBillForDetail db = dredgeService.qryDredgeBillDetail(id + "");
        dredgeService.processDredgeBillPicInfo(db);
        dredgeService.appendMaterialFee(db);
        jsonResponse.putDataAll(MapConverter.toMap(db));

        if (db.getPayAmount() != null && db.getPayAmount().compareTo(BigDecimal.ZERO) > 0) {// 消费券列表
            BigInteger dredgeProductId = null;
            List<DredgeProductSpecEntity> dredgeProductSpecList = db.getDredgeProductSpecList();
            if (!DataUtil.isEmpty(dredgeProductSpecList)) {
                dredgeProductId = dredgeProductSpecList.get(0).getDredgeProductId();
            }
            List<UserCouponEntity> coupons = userCouponService.
                    getDredgeCouponListCanUse(db.getPayAmount(), userId, db.getCommunitySupplyType(), db.getDredgeTypeId(), dredgeProductId);

            //查维修可用的超市券
            Integer productAmount = dredgeService.getDredgeHasProductAmount(id);
            UserCoupon userCoupon = new UserCoupon();
            Coupon coupon = new Coupon();
            coupon.setLeastSpendUse(db.getPayAmount().multiply(BigDecimal.valueOf(100)).intValue() + productAmount);
            coupon.setUseType(CouponUseTypeConstant.EBUY_PRODUCT);
            userCoupon.setCoupon(coupon);
            userCoupon.settUserFId(userId);
            userCoupon.setStatus(UserCouponStatus.VALID);
            Map<String, Object> param = MapConverter.toMap(userCoupon);

            Map<String, Object> dbParam = new HashMap<String, Object>();
            dbParam.put("dredgeBillId", id);
            dbParam.put("userId", UserContext.getOperIdMustExistBigInteger());
            List<SelfBuyProduct> selfBuyProductList = dredgeService.getSelfBuyProductList(dbParam);
            jsonResponse.putData("selfBuyProductList", selfBuyProductList);

            List<Object> supplyMerchantIds = new ArrayList<Object>();
            for (SelfBuyProduct selfBuyProduct : selfBuyProductList) {
                supplyMerchantIds.add(selfBuyProduct.getSupplyMerchantId());
            }
            if (supplyMerchantIds.size() > 0) {
                param.put("supplyMerchantIds", supplyMerchantIds);
                List<UserCouponEntity> couponEbuy = userCouponService.getUserCouponList(param);
                coupons.addAll(couponEbuy);
                Collections.sort(coupons, new CouponComparator());
            }
            jsonResponse.putData("ext_isContainCoupon", !DataUtil.isEmpty(coupons));
            jsonResponse.putData("ext_couponCombiInfo", coupons);
        } else {
            jsonResponse.putData("ext_isContainCoupon", false);
            jsonResponse.putData("ext_couponCombiInfo", new ArrayList());
        }
        {// 查询粮票使用的金额统计信息  参考 /redenvelope/qryBalance.json
            SimpleHbBalanceEntity simpleHbBalanceEntity = redenvelopeService.getAllBalanceCollectInfo(userId,0);

            jsonResponse.putData("balance", simpleHbBalanceEntity.getBalanceDiv100());
            jsonResponse.putData("totalConvertMoney", simpleHbBalanceEntity.getTotalConvertMoneyDiv100());
            jsonResponse.putData("totalConsumMoney", simpleHbBalanceEntity.getTotalConsumMoneyDiv100());
        }
        if (!StringUtils.isEmpty(db.getOrderId())) {
            EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(BigInteger.valueOf(db.getOrderId()));
            jsonResponse.putData("totalCouponAmount", PriceUtil.div100(order.getTotalCouponAmount()));
            jsonResponse.putData("payMethod", order.getPayMethod());
            jsonResponse.putData("tradeTime", order.getSys0UpdTime());
            jsonResponse.putData("orderNo", order.getOrderNo());
        } else {
            jsonResponse.putData("totalCouponAmount", null);
            jsonResponse.putData("payMethod", null);
            jsonResponse.putData("tradeTime", null);
            jsonResponse.putData("orderNo", null);
        }
        //3.结果处理
        return jsonResponse;
    }

    /**
     * 确认缴疏通服务费
     */
    @RequestMapping("/confirmPayDredgeBill.json")
    @ResponseBody
    @RepeatReqValidate
    public JsonResponse confirmPayBill(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger dredgeBillId = new BigInteger(request.getParameter("dredgeBillId"));
        //BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        Long hbAmount = null;
        if (!StringUtils.isEmpty(request.getParameter("hbAmount"))) {
            Double hbAmountDouble = null;
            hbAmountDouble = Double.parseDouble(request.getParameter("hbAmount"));//粮票金额
            hbAmount = PriceUtil.multiply100(hbAmountDouble);
        }
        Set<BigInteger> couponIdSet = null;
        if (!StringUtils.isEmpty(request.getParameter("couponIdList"))) {//优惠的Id列表
            List<BigInteger> couponIdList = JSON.parseArray(request.getParameter("couponIdList"), BigInteger.class);
            couponIdSet = new HashSet<BigInteger>();
            couponIdSet.addAll(couponIdList);
        }

        //2.交互

        {//判断：使用的粮票不能大于人工费
            if (hbAmount != null) {
                DredgeBillAmountDetail dbadQry = new DredgeBillAmountDetail();
                dbadQry.settDredgeBillFId(dredgeBillId);
                List<DredgeBillAmountDetail> dbadList = dredgeBillAmountDetailBaseService.getDredgeBillAmountDetailByCondition(MapConverter.toMap(dbadQry));
                for (DredgeBillAmountDetail dbad : dbadList) {
                    if (dbad.getFeeType() == DredgeConstant.DredgeBillAmountDetailType.Labor_fee && hbAmount > dbad.getPayAmount()) {
                        throw new BusinessRuntimeException("DredgeController.confirmPayBill.hbAmount.morethan.laborAmount");
                    }
                }
            }
        }

        Integer subChannelId = HeaderManager.getSubChannelIdLong().intValue();
        EbuyOrder order = dredgeService.confirmPayBill(dredgeBillId, hbAmount, couponIdSet, subChannelId);

        //3.结果处理
        jsonResponse.putData("orderId", order.getId());
        jsonResponse.putData("orderShouldPayAmount", order.getAmount());
        return jsonResponse;
    }

    @RequestMapping(value = "/dredgePayDetail.json", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getDredgePayDetail(BigInteger dredgeBillId) {
        JsonResponse json = new JsonResponse();
        DredgeBill db = dredgeService.getDredgeBillById(dredgeBillId);
        EbuyOrder order = ebuyOrderBaseService.getEbuyOrderBySeqId(db.gettEbuyOrderFId());
        long coupon = order.getTotalCouponAmount() == null ? 0 : order.getTotalCouponAmount();
        json.putData("totalAmount", order.getAmount() + coupon);
        json.putData("couponAmount", order.getTotalCouponAmount());
        json.putData("payMethod", order.getPayMethod());
        List<DredgeProductSpecEntity> productList = dredgeService.getDredgeProductListByDbId(dredgeBillId);
        json.putData("productSpecList", productList);
        Map<String, Object> dbParam = new HashMap<String, Object>();
        dbParam.put("dredgeBillId", dredgeBillId);
        dbParam.put("userId", UserContext.getOperIdMustExistBigInteger());
        List<SelfBuyProduct> selfBuyProductList = dredgeService.getSelfBuyProductList(dbParam);
        json.putData("selfBuyProductList", selfBuyProductList);
        return json;
    }

    /**
     * 准备评价服务
     */
    @RequestMapping("/prepareComment.html")
    public ModelAndView prepareComment(HttpServletRequest request) {
        //1.搜集参数
        BigInteger masterId = ParamUtils.getBigInteger(request, "masterId", null);
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);

        //2.交互
        request.setAttribute("masterId", masterId);
        request.setAttribute("dredgeBillId", dredgeBillId);
        request.setAttribute("cpList", dredgeService.getCommentsPointList());
        //3.结果处理
        return new ModelAndView("/dredge/evaluate");
    }

    /**
     * 用户设置确认完成维修单
     */
    @RequestMapping("/finishBill.json")
    @ResponseBody
    public JsonResponse userFinishBill(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger billId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        // 2.交互
        JsonResponse json = new JsonResponse();
        DredgeBill bill = new DredgeBill();
        bill.setId(billId);
        bill.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedNoComment);
        int updCount = dredgeBillBaseService.updateDredgeBill(bill);
        json.setStatus(updCount == 0 ? "0001" : "0000");
        json.setMessage(updCount == 0 ? "操作失败" : "操作成功");
        return json;
    }

    /**
     * 根据传入的page参数跳转到相应的页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/toPage.html")
    public ModelAndView toPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String page = ParamUtils.getString(request, "page", null);

        if (page != null) {
            modelAndView.setViewName("/dredge/" + page);
        }
        return modelAndView;
    }

    /**
     * 师傅-查询可配置的服务类型
     */
    @RequestMapping("/master/qryConfigServiceType.json")
    @ResponseBody
    public JsonResponse qryConfigServiceType(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数

        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("52585");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);

        //首次设置时，可以看到所有类型；非首次设置只能看到之前设置的类型
        DredgeWorker dredgeWorkerQry = new DredgeWorker();
        dredgeWorkerQry.settUserFId(userId);
        dredgeWorkerQry.setAuditStatus(DredgeConstant.DredgeWorker.DredgeWorker_Status_Accepted);
        if (dredgeWorkerBaseService.getDredgeWorkerByCondition(MapConverter.toMap(dredgeWorkerQry)).size() > 0) {
            paramMap.put("canQryAllDredgeType", CnfantasiaCommbusiUtil.getSysParaValueInt(SysParamKey.MASTER_CAN_QRY_ALL_DREDGE_TYPE, 1));
        }

        List<DredgeParentType> dredgeTypeList = dredgeService.qryConfigServiceType(paramMap);
        //如果服务类型全空，则查全部
        if (DataUtil.isEmpty(dredgeTypeList)) {
            paramMap.remove("canQryAllDredgeType");
            dredgeTypeList = dredgeService.qryConfigServiceType(paramMap);
        }
        jsonResponse.putData("list", dredgeTypeList);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 师傅-查询可配置的服务区域
     */
    @RequestMapping("/master/qryConfigServiceBlockByCity.json")
    @ResponseBody
    public JsonResponse qryConfigServiceBlockByCity(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String cityId = request.getParameter("cityId");
        String cityName = request.getParameter("cityName");

        if (cityId == null && cityName == null) {
            throw new ValidateRuntimeException("cityId and cityName can't all be null ");
        }

        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");
        List<BlockForMaster> blockList = dredgeService.qryConfigServiceBlockByCity(userId, cityId, cityName);
        jsonResponse.putData("list", blockList);

        //3.结果处理

        return jsonResponse;
    }

    /**
     * 师傅-配置服务区域
     */
    @RequestMapping("/master/submitConfigServiceBlock.json")
    @ResponseBody
    public JsonResponse submitConfigServiceBlock(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        List<BigInteger> blockIdList = JSON.parseArray(request.getParameter("blockIdList"), BigInteger.class);

        if (blockIdList.size() < 1) {
            throw new ValidateRuntimeException("service block must more than one");
        }

        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");
        dredgeService.submitConfigServiceBlock(userId, blockIdList);
        //3.结果处理

        return jsonResponse;
    }

    /**
     * 师傅-配置服务类型
     */
    @RequestMapping("/master/submitConfigServiceType.json")
    @ResponseBody
    public JsonResponse submitConfigServiceType(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        List<BigInteger> dredgeTypeIdList = JSON.parseArray(request.getParameter("dredgeTypeIdList"), BigInteger.class);

        if (dredgeTypeIdList.size() < 1) {
            throw new ValidateRuntimeException("service type must more than one");
        }

        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");
        dredgeService.submitConfigServiceType(userId, dredgeTypeIdList);

        //3.结果处理

        return jsonResponse;
    }


    /**
     * 师傅-订单中心
     */
    @RequestMapping("/master/qryDredgeBillListForMaster.json")
    @ResponseBody
    public JsonResponse qryDreDgeBillListForMaster(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String type = request.getParameter("type"); // 查询类型：1派单中，2已接单，3已结束

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        if (type == null)
            throw new ValidateRuntimeException("type can't be null");

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        List<DredgeBillForMaster> ddList = dredgeService.qryDreDgeBillListForMaster(userId, type, pageModel);

        //3.结果处理
        ControllerUtils.addPageInfo(jsonResponse, ddList, pageModel.isLast, pageModel.count);

        jsonResponse.putData("curTime", DateUtils.getCurrentDate()); //本次接口时间

        jsonResponse.putData("timeInterval", Integer.parseInt(sysParamManager.getSysParaValue(SysParamKey.qryNewDredgeBillCountTimeInterval)));//定时请求任务时间间隔

        return jsonResponse;
    }

    /**
     * 师傅-查询新的订单数
     */
    @RequestMapping("/master/qryNewDredgeBillCount.json")
    @ResponseBody
    public JsonResponse qryNewDredgeBillCount(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String curTime = request.getParameter("curTime"); // 查询类型：1派单中，2已接单，3已结束

        //2.交互
        if (curTime == null)
            throw new ValidateRuntimeException("curTime can't be null");

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        //3.结果处理
        int type = 1;//只取待派单的
        int billNum = dredgeService.qryNewDredgeBillCountForMaster(type, userId, curTime);

        jsonResponse.putData("billNum", billNum);

        return jsonResponse;
    }

    /**
     * 师傅-设置收费金额
     */
    @RequestMapping("/master/setPayAmountForBill.json")
    @ResponseBody
    public JsonResponse setPayAmountForBill(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        BigDecimal laborAmount = ParamUtils.getBigDecimal(request, "laborAmount", BigDecimal.ZERO);
        BigDecimal materialAmount = ParamUtils.getBigDecimal(request, "materialAmount", BigDecimal.ZERO);
        //1代表收费，2代表不收费
        Integer type = ParamUtils.getInteger(request, "type", 1);

        if (dredgeBillId == null || laborAmount == null) {
            throw new ValidateRuntimeException("dredgeBillId and laborAmount can't be null");
        }

        //2.交互

        dredgeService.setPayAmountForBill(dredgeBillId, type, laborAmount, materialAmount, jsonResponse);

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 师傅-取消订单
     */
    @RequestMapping("/master/dredgeWorkerCancel.json")
    @ResponseBody
    public JsonResponse cancelReceiving(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        String cancelDesc = request.getParameter("cancelDesc");

        if (dredgeBillId == null || cancelDesc == null) {
            throw new ValidateRuntimeException("dredgeBillId and cancelDesc can't be null");
        }

        //2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        int updCount = dredgeService.dredgeWorkerCancel(userId, dredgeBillId, cancelDesc);

        if (updCount < 1) {
            jsonResponse.setMessage("取消订单失败");
        }
        //3.结果处理
        return jsonResponse;
    }

    /**
     * 师傅-确认接单
     */
    @RequestMapping("/master/confirmReceiving.json")
    @ResponseBody
    public JsonResponse confirmReceiving(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);

        if (dredgeBillId == null) {
            throw new ValidateRuntimeException("dredgeBillId can't be null");
        }

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("52585");

        //3.结果处理
        MasterProfile mp = dredgeService.qryMyProfileById(userId);

        String isReceived = "isReceived"; //抢单结果：1成功，0失败
        String confirmResultDesc = "confirmResultDesc"; //抢单结果描述
        boolean isReceivedSuccess = false;
        jsonResponse.putData("certificateStatus", mp.getCertificateStatus());
        if (mp.getCertificateStatus() == DredgeConstant.DredgeWorker.DredgeWorker_Status_Accepted) {
            int udpCount = dredgeService.confirmReceiving(dredgeBillId, userId);

            jsonResponse.putData(isReceived, udpCount > 0 ? 1 : 0);
            jsonResponse.putData(confirmResultDesc, udpCount > 0 ? "抢单成功" : "抢单失败，被他人抢走了，下次快点哦");
            isReceivedSuccess = udpCount > 0;

        } else if (mp.getCertificateStatus() == DredgeConstant.DredgeWorker.DredgeWorker_Status_NoCertificate) {
            jsonResponse.putData(isReceived, 0);
            jsonResponse.putData(confirmResultDesc, "抢单失败，尚未提交实名认证");
        } else if (mp.getCertificateStatus() == DredgeConstant.DredgeWorker.DredgeWorker_Status_WaitCertificate) {
            jsonResponse.putData(isReceived, 0);
            jsonResponse.putData(confirmResultDesc, "抢单失败，实名认证中");
        } else if (mp.getCertificateStatus() == DredgeConstant.DredgeWorker.DredgeWorker_Status_Refused) {
            jsonResponse.putData(isReceived, 0);
            jsonResponse.putData(confirmResultDesc, "抢单失败，实名认证失败");
        }

        //发送耗材信息短息
        if (isReceivedSuccess) {
            if (dredgeService.isHasEbuyProductByDredgeBillId(dredgeBillId)) {//存在耗材，进行短息发送
                try {//发送耗材信息给师傅
                    dredgeService.sendMsgThread(dredgeBillId, "msg.DREDGE_MASTER");
                } catch (Exception e) {
                    logger.info("发送耗材信息给师傅，调用短息sendMsgThread方法异常:" + e.getMessage());
                }
            }
        }

        return jsonResponse;
    }

    /**
     * 师傅-实名认证
     */
    @RequestMapping("/master/certificateRealName.json")
    @ResponseBody
    public JsonResponse certificateRealName(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        String realName = request.getParameter("realName");
        String idNumber = request.getParameter("idNumber");
        String personalDesc = request.getParameter("personalDesc"); //个人描述

        List<RequestFileEntity> picList = CommRequestFileParser.parseRequsetFileInfoDimLike(request, "picInfo");
        if (realName == null || idNumber == null)
            throw new BusinessRuntimeException("realName or idNumber can't be null");
        if (picList == null || picList.isEmpty()) {
            throw new BusinessRuntimeException("身份证的正反面没有上传");
        }
        if (personalDesc != null && personalDesc.length() > 500) {
            throw new BusinessRuntimeException("personalDesc can't be more than 500 characters ");
        }

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        dredgeService.updateCertificateRealName(userId, realName, idNumber, personalDesc, picList);

        //3.结果处理

        return jsonResponse;
    }

    /**
     * 师傅-个人中心
     */
    @RequestMapping("/master/qryMyProfile.json")
    @ResponseBody
    public JsonResponse qryMyProfile(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        //BigInteger userId = new BigInteger("52585");
        MasterProfile masterProfile = dredgeService.qryMyProfileById(userId);

        jsonResponse.putDataAll(MapConverter.toMap(masterProfile));

        //3.结果处理
        return jsonResponse;
    }

    /**
     * 师傅-查询金额
     * 二期不用这个接口了，
     *
     * @see
     */
    @Deprecated
    @RequestMapping("/master/qryAccountAmount.json")
    @ResponseBody
    public JsonResponse qryAccountAmount(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        AccountAmount accountAmount = dredgeService.qryAccountAmount(userId);

        jsonResponse.putDataAll(MapConverter.convertBean(accountAmount));

        //3.结果处理

        return jsonResponse;
    }

    /**
     * 师傅-查询收入与提现记录列表
     */
    @Deprecated
    @RequestMapping("/master/qryIncomeOrWithdrawList.json")
    @ResponseBody
    public JsonResponse qryIncomeOrWithdrawList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        //1.搜集参数
        Integer type = ParamUtils.getInteger(request, "type", null);// type=1收入，type=2提现
        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        //2.交互
        if (type == null) {
            throw new BusinessRuntimeException("the paramter 'type' can't be null.");
        }

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        paramMap.put("type", type);

        List<IncomeWithdrawRecorder> incomeWithdrawList = dredgeService.qryIncomeOrWithdrawList(paramMap, pageModel);

        //3.结果处理
        return ControllerUtils.addPageInfo(jsonResponse, incomeWithdrawList, pageModel.isLast, pageModel.count);
    }

    @Resource
    DredgeBankCardBaseService dredgeBankCardBaseService;

    /**
     * 师傅-查询我的账户
     */
    @RequestMapping("/master/qryMyAccount.json")
    @ResponseBody
    public JsonResponse qryMyAccount_master(HttpServletRequest request) {
        // 用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        JsonResponse jsonResponse = new JsonResponse();

        BigDecimal canWithdrawAmt = BigDecimal.ZERO;
        BigDecimal totalAmt = BigDecimal.ZERO;
        List<CanWithdrawBill4Master> canWithdrawList = dredgeService.selectCanWithdrawList_master(userId);
        List<CanWithdrawBill4Master> resList = new ArrayList<>(canWithdrawList.size());
        for (int i = 0; i < canWithdrawList.size(); i++) {
            boolean include = false;
            if (!DataUtil.isEmpty(canWithdrawList.get(i).getAmountList())) {
                for (RSADetail rsaDetail : canWithdrawList.get(i).getAmountList()) {
                    if (rsaDetail.getFeeAmount().compareTo(BigDecimal.ZERO) > 0) {
                        include = true;
                        break;
                    }
                }
            }
            if (include) {
                canWithdrawAmt = canWithdrawAmt.add(canWithdrawList.get(i).getCanWithdrawAmt());
                totalAmt = totalAmt.add(canWithdrawList.get(i).getTotalAmt());
                if (canWithdrawList.get(i).getAmountList() != null
                        && canWithdrawList.get(i).getAmountList().size() == 1) {//若没有其它费时，追加金额为0的其它费
                    RSADetail rsaDetail = new RSADetail();
                    rsaDetail.setId(-1);
                    rsaDetail.setCanWithdrawDays(0);
                    rsaDetail.setCostAmt(BigDecimal.ZERO);
                    rsaDetail.setFeeAmount(BigDecimal.ZERO);
                    rsaDetail.setFeeName("其它费");
                    rsaDetail.setFeeType(DredgeConstant.DredgeBillAmountDetailType.Material_fee);
                    rsaDetail.setIsWithdrawed(0);
                    rsaDetail.setSubsidyAmt(BigDecimal.ZERO);
                    canWithdrawList.get(i).getAmountList().add(rsaDetail);
                }
                resList.add(canWithdrawList.get(i));
            }
        }

        jsonResponse.putData("canWithdrawAmt", canWithdrawAmt.setScale(2, BigDecimal.ROUND_HALF_UP));//可提现金额
        jsonResponse.putData("totalAmt", totalAmt.setScale(2, BigDecimal.ROUND_HALF_UP));//总资产

        jsonResponse.putData("canWithdrawList", resList);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tUserFId", userId);
        List<DredgeBankCard> dredgeBankCardList = dredgeBankCardBaseService.getDredgeBankCardByCondition(paramMap);
        if (dredgeBankCardList.isEmpty()) {
            jsonResponse.putData("bankCard", null);//银行卡号
        } else {
            String bankNo = dredgeBankCardList.get(0).getBankNo();
            jsonResponse.putData("bankCard", generateSecretCardNo(bankNo));
        }

        return jsonResponse;
    }

    /**
     * 推荐人-查询我的账户
     */
    @RequestMapping("/recommend/qryMyAccount.json")
    @ResponseBody
    public JsonResponse qryMyAccount_recommend(HttpServletRequest request) {
        // 用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        JsonResponse jsonResponse = new JsonResponse();

        BigDecimal canWithdrawAmt = BigDecimal.ZERO;
        List<CanWithdrawBill> canWithdrawList = dredgeService.selectCanWithdrawList_recommend(userId);
        /*CanWithdrawBill cwb = new CanWithdrawBill();
        cwb.setBillAddress("深圳南山松坪村");
		cwb.setBillAmt(new BigDecimal(65.5));
		cwb.setDredgeBillId(50001L);
		cwb.setDredgeType("下水道疏通");
		cwb.setPayTime("2015-03-21 12:23:33");
		cwb.setReferrerMobile("13596857485");
		cwb.setTotalAmt(new BigDecimal("3.514"));
		cwb.setUserMobile("13145826351");
		cwb.setSubsidyAmt(new BigDecimal(2));
		cwb.setCostAmt(new BigDecimal(2));
		cwb.setSubmitDate("2016-02-25 10:23:21");
		canWithdrawList.add(cwb);
		canWithdrawList.add(cwb);
		canWithdrawList.add(cwb);
		canWithdrawList.add(cwb);
		canWithdrawList.add(cwb);*/
        for (int i = 0; i < canWithdrawList.size(); i++) {
            canWithdrawAmt = canWithdrawAmt.add(canWithdrawList.get(i).getTotalAmt());
        }
        jsonResponse.putData("canWithdrawAmt", canWithdrawAmt.setScale(2, BigDecimal.ROUND_HALF_UP));//可提现金额

        jsonResponse.putData("canWithdrawList", canWithdrawList);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tUserFId", userId);
        List<DredgeBankCard> dredgeBankCardList = dredgeBankCardBaseService.getDredgeBankCardByCondition(paramMap);
        if (dredgeBankCardList.isEmpty()) {
            jsonResponse.putData("bankCard", null);//银行卡号
        } else {
            String bankNo = dredgeBankCardList.get(0).getBankNo();
            jsonResponse.putData("bankCard", generateSecretCardNo(bankNo));
        }

        return jsonResponse;
    }

    /**
     * 将银行卡号转换为带*的卡号
     *
     * @param bankNo
     * @return
     */
    private String generateSecretCardNo(String bankNo) {
        return bankNo.subSequence(0, 4) + " **** **** " + bankNo.subSequence(bankNo.length() - 4, bankNo.length());
    }

    @Resource
    ISupportBankBaseService supportBankBaseService;

    /**
     * 师傅-查询可支持的银行名称
     */
    @RequestMapping("/master/qryBankNameList.json")
    @ResponseBody
    public JsonResponse qryBankNameList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        List<SupportBank> supportBankList = supportBankBaseService.getSupportBankByCondition(null);
        String[] bankNames = new String[supportBankList.size()];
        for (int i = 0; i < supportBankList.size(); i++) {
            bankNames[i] = supportBankList.get(i).getSimpleName();
        }
        jsonResponse.putData(PageModel.PageKey.LIST, bankNames);//银行卡号

        return jsonResponse;
    }

    /**
     * 师傅-绑定银行卡
     */
    @RequestMapping("/master/bindBankCard.json")
    @ResponseBody
    public JsonResponse bindBankCard(HttpServletRequest request) {
        // 1.搜集参数
        String cardOwner = request.getParameter("cardOwner");
        String cardNumber = request.getParameter("cardNumber");
        String bankName = request.getParameter("bankName");
        String bankBranchName = request.getParameter("bankBranchName");

        String bankProvince = request.getParameter("bankProvince");
        String bankCity = request.getParameter("bankCity");

        // 2.交互
        if (cardOwner == null || cardNumber == null || bankName == null
                || bankBranchName == null/* || bankProvince == null || bankCity == null*/) {
            throw new BusinessRuntimeException(
                    "the cardOwner, cardNumber, bankName, bankBranchName cant be null.");
        }

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        DredgeBankCard dredgeBankCard = new DredgeBankCard();
        dredgeBankCard.setId(CnfantasiaCommbusiUtil.getUuid(SEQConstants.t_dredge_bank_card));
        dredgeBankCard.setBankBranchName(bankBranchName);
        dredgeBankCard.setBankName(bankName);
        dredgeBankCard.setBankNo(cardNumber);
        dredgeBankCard.setOwnerName(cardOwner);
        dredgeBankCard.settUserFId(userId);
        dredgeBankCard.setBankProvince(bankProvince);
        dredgeBankCard.setBankCity(bankCity);
        dredgeBankCard.setIsDefault(1);

        dredgeBankCardBaseService.insertDredgeBankCard(dredgeBankCard);

        JsonResponse jsonResponse = new JsonResponse();
        return jsonResponse;
    }

    @Resource
    IDredgeWorkerBaseService dredgeWorkerBaseService;

    /**
     * 师傅-更新个人描述
     */
    @RequestMapping("/master/updatePersonalDesc.json")
    @ResponseBody
    public JsonResponse updatePersonalDesc(HttpServletRequest request) {
        // 1.搜集参数
        String updatePersonalDesc = request.getParameter("updatePersonalDesc");

        if (updatePersonalDesc == null) {
            throw new BusinessRuntimeException(
                    "the updatePersonalDesc cant be null.");
        }
        // 2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tUserFId", userId);
        paramMap.put("sys0DelState", 0);
        List<DredgeWorker> dwList = dredgeWorkerBaseService.getDredgeWorkerByCondition(paramMap);
        DredgeWorker dw = new DredgeWorker();
        dw.setId(dwList.get(0).getId());
        dw.setPersonalDesc(updatePersonalDesc);
        dredgeWorkerBaseService.updateDredgeWorker(dw);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setMessage("OK");
        return jsonResponse;
    }

    /**
     * 师傅-申请提款, 默认提现所有可提现的工单
     */
    @RequestMapping("/master/applyWithdraw.json")
    @ResponseBody
    public JsonResponse applyWithdraw_master(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("50330");

        //dredgeBillIdList暂时没有用到
        List<BigInteger> dredgeBillIdList = JSON.parseArray(request.getParameter("dredgeBillIdList"), BigInteger.class);

        // 2.交互

        BigInteger applyRevenueId = dredgeService.applyRevenue_master(userId, dredgeBillIdList);

        ApplyWithdrawInfo applyWithDrawInfo = dredgeService.selectApplyDetail_byApplyId(applyRevenueId);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.putDataAll(MapConverter.toMap(applyWithDrawInfo));
//		jsonResponse.putData("billAmt", 120.00);
        //		jsonResponse.putData("billCount", "3笔");
//		jsonResponse.putData("submitTime", "2015-11-19 18:20");
        //		jsonResponse.putData("bankName", "提款银行");
//		jsonResponse.putData("bankCardNumber", "6225********4546");
        //		jsonResponse.putData("tkStatus", "提款中");

        return jsonResponse;
    }

    /**
     * 推荐人-申请提款, 默认提现所有可提现的工单
     */
    @RequestMapping("/recommend/applyWithdraw.json")
    @ResponseBody
    public JsonResponse applyWithdraw_recommend(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("50330");

        List<BigInteger> dredgeBillIdList = JSON.parseArray(request.getParameter("dredgeBillIdList"), BigInteger.class);

        // 2.交互

        BigInteger applyRevenueId = dredgeService.applyRevenue_recommend(userId, dredgeBillIdList);

        ApplyWithdrawInfo applyWithDrawInfo = dredgeService.selectApplyDetail_byApplyId(applyRevenueId);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.putDataAll(MapConverter.toMap(applyWithDrawInfo));
//		jsonResponse.putData("billAmt", 120.00);
        //		jsonResponse.putData("billCount", "3笔");
//		jsonResponse.putData("submitTime", "2015-11-19 18:20");
        //		jsonResponse.putData("bankName", "提款银行");
//		jsonResponse.putData("bankCardNumber", "6225********4546");
        //		jsonResponse.putData("tkStatus", "提款中");

        return jsonResponse;
    }

    /**
     * 师傅-查看提款列表
     */
    @RequestMapping("/master/viewWithdrawList.json")
    @ResponseBody
    public JsonResponse viewWithdrawList_master(HttpServletRequest request) {
        // 1.搜集参数

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        List<ApplyListForMaster> applyList = dredgeService.selectApplyList_byMasterId(userId, pageModel);


        JsonResponse jsonResponse = new JsonResponse();
		
		/*List<Map<String, Object>> withdrawList = new ArrayList<Map<String,Object>>();
		Map<String, Object> withdrawMap = new HashMap<String,Object>();
		withdrawMap.put("applyStatus", "申请成功");
		withdrawMap.put("applyAmount", "300");
		withdrawMap.put("billCount", "3笔");
		withdrawMap.put("tkStatus", "提款中");
		withdrawMap.put("tkTime", "2015-11-19 15:54");
		withdrawList.add(withdrawMap);
		
		withdrawMap.put("applyStatus", "申请成功");
		withdrawMap.put("applyAmount", "300");
		withdrawMap.put("billCount", "3笔");
		withdrawMap.put("tkStatus", "提款中");
		withdrawMap.put("tkTime", "2015-11-19 15:54");
		withdrawList.add(withdrawMap);
		return ControllerUtils.addPageInfo(jsonResponse, withdrawList, true, 10);
		*/

        return ControllerUtils.addPageInfo(jsonResponse, applyList, pageModel.isLast, pageModel.count);
    }

    /**
     * 推荐人-查看提款列表
     */
    @RequestMapping("/recommend/viewWithdrawList.json")
    @ResponseBody
    public JsonResponse viewWithdrawList_recommend(HttpServletRequest request) {
        // 1.搜集参数

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        //2.交互

        //用户Id
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        List<ApplyListForMaster> applyList = dredgeService.selectApplyList_byRecommendId(userId, pageModel);


        JsonResponse jsonResponse = new JsonResponse();

        //
		/*
		 List<Map<String, Object>> withdrawList = new ArrayList<Map<String,Object>>();
		Map<String, Object> withdrawMap = new HashMap<String,Object>();
		withdrawMap.put("id", 50000);
		withdrawMap.put("applyStatus", "申请成功");
		withdrawMap.put("applyAmount", "100");
		withdrawMap.put("billCount", "3笔");
		withdrawMap.put("tkStatus", "提款中");
		withdrawMap.put("tkTime", "2015-11-19 15:54");
		withdrawList.add(withdrawMap);
		
		withdrawMap = new HashMap<String,Object>();
		withdrawMap.put("applyStatus", "申请成功");
		withdrawMap.put("id", 50001);
		withdrawMap.put("applyAmount", "200");
		withdrawMap.put("billCount", "3笔");
		withdrawMap.put("tkStatus", "结算中");
		withdrawMap.put("tkTime", "2015-11-19 15:54");
		withdrawList.add(withdrawMap);
		return ControllerUtils.addPageInfo(jsonResponse, withdrawList, true, 10);
		 */

        return ControllerUtils.addPageInfo(jsonResponse, applyList, pageModel.isLast, pageModel.count);
    }

    /**
     * 师傅-查看每笔提款详情
     */
    @RequestMapping("/recommend/viewWithdrawDetail.json")
    @ResponseBody
    public JsonResponse viewWithdrawDetail_recommend(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger revenueApplyId = ParamUtils.getBigInteger(request, "revenueApplyId", null);

        // 2.交互
        if (revenueApplyId == null) {
            throw new BusinessRuntimeException(
                    "the revenueApplyId  can't be null. ");
        }

        JsonResponse jsonResponse = new JsonResponse();
//		jsonResponse.putData("billAmt", 120.00);
//		jsonResponse.putData("billCount", "3笔");
//		jsonResponse.putData("submitTime", "2015-11-19 18:20");
//		jsonResponse.putData("bankName", "提款银行");
//		jsonResponse.putData("bankCardNumber", "6225********4546");
//		jsonResponse.putData("tkStatus", "已结算");

        ApplyWithdrawInfo applyWithDrawInfo = dredgeService.selectApplyDetail_byApplyId(revenueApplyId);

        jsonResponse.putDataAll(MapConverter.toMap(applyWithDrawInfo));

        return jsonResponse;
    }

    /**
     * 推荐人-查看每笔提款详情
     */
    @RequestMapping("/master/viewWithdrawDetail.json")
    @ResponseBody
    public JsonResponse viewWithdrawDetail(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger revenueApplyId = ParamUtils.getBigInteger(request, "revenueApplyId", null);

        // 2.交互
        if (revenueApplyId == null) {
            throw new BusinessRuntimeException(
                    "the revenueApplyId  can't be null. ");
        }

        JsonResponse jsonResponse = new JsonResponse();
//		jsonResponse.putData("billAmt", 120.00);
        //		jsonResponse.putData("billCount", "3笔");
//		jsonResponse.putData("submitTime", "2015-11-19 18:20");
        //		jsonResponse.putData("bankName", "提款银行");
//		jsonResponse.putData("bankCardNumber", "6225********4546");
        //		jsonResponse.putData("tkStatus", "已结算");

        ApplyWithdrawInfo applyWithDrawInfo = dredgeService.selectApplyDetail_byApplyId(revenueApplyId);

        jsonResponse.putDataAll(MapConverter.toMap(applyWithDrawInfo));

        return jsonResponse;
    }


    /**
     * 师傅-银行卡列表
     */
    @RequestMapping("/master/qryBankCardList.json")
    @ResponseBody
    public JsonResponse qryBankCardList(HttpServletRequest request) {
        // 1.搜集参数

        // 2.交互

        JsonResponse jsonResponse = new JsonResponse();

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tUserFId", userId);
        List<DredgeBankCard> dredgeBankCardList = dredgeBankCardBaseService.getDredgeBankCardByCondition(paramMap);

        List<Map<String, Object>> bankCardList = new ArrayList<Map<String, Object>>();
        for (DredgeBankCard dredgeBankCard : dredgeBankCardList) {
            Map<String, Object> bankCardMap = new HashMap<String, Object>();
            bankCardMap.put("cardNumber", generateSecretCardNo(dredgeBankCard.getBankNo()));
            bankCardMap.put("bankCardId", dredgeBankCard.getId());
            bankCardMap.put("bankName", dredgeBankCard.getBankName());
            bankCardList.add(bankCardMap);
        }

        jsonResponse.putData(PageModel.PageKey.LIST, bankCardList);

        return jsonResponse;
    }


    /**
     * 师傅-校验登录密码
     */
    @RequestMapping("/master/verifyLoginPwd.json")
    @ResponseBody
    public JsonResponse verifyLoginPwd(HttpServletRequest request) {
        // 1.搜集参数
        SimpleLoginInfo simpleLoginInfo = SimpleLoginInfo.parseLoginInfo(request);
        //BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        //BigInteger userId = new BigInteger("52585");

        // 2.交互
        LoginNoBaseService loginNoBaseService = (LoginNoBaseService) CnfantasiaCommbusiUtil.getBeanManager("loginNoBaseService");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //paramMap.put("tUserFId", userId);
        paramMap.put("type", simpleLoginInfo.getLoginType());
        paramMap.put("no", simpleLoginInfo.getNumber());
        paramMap.put("password", simpleLoginInfo.getPassword());
        int isPassVerify = loginNoBaseService.getLoginNoCount(paramMap);

        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.putData("isPassVerify", isPassVerify);
        return jsonResponse;
    }

    /**
     * 师傅-设置修改上门时间
     */
    @RequestMapping("/master/setServiceTime.json")
    @ResponseBody
    public JsonResponse setServiceTime(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger id = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        String serviceTime = ParamUtils.getString(request, "serviceTime");
        Integer billType = ParamUtils.getInteger(request, "billType", DredgeConstant.DredgeBillType.Property_Repair);

        // 2.交互
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("userId", userId);
        paramMap.put("serviceTime", serviceTime);
        paramMap.put("billType", billType);
        JsonResponse jsonResponse = new JsonResponse();
        int updCount = dredgeService.setServiceTime(paramMap);
        if (updCount > 0) {
            jsonResponse.putData("isSuccess", 1);
            jsonResponse.setMessage("修改上门服务时间成功");
        } else {
            jsonResponse.putData("isSuccess", 0);
            jsonResponse.setMessage("修改上门服务时间失败");
        }

        return jsonResponse;
    }

    /**
     * 师傅-设置确认完成旧物业单
     */
    @RequestMapping("/master/confirmFinished.json")
    @ResponseBody
    public JsonResponse confirmFinished(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger id = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        // 2.交互
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("userId", userId);
        JsonResponse jsonResponse = new JsonResponse();
        int updCount = dredgeService.confirmFinished(paramMap);
        if (updCount == 1) {
            jsonResponse.putData("isSuccess", 1);
            jsonResponse.setMessage("确认完成操作成功");
        } else {
            jsonResponse.putData("isSuccess", 0);
            jsonResponse.setMessage("确认完成操作失败");
        }

        return jsonResponse;
    }

    /**
     * 师傅-设置确认完成维修单
     */
    @RequestMapping("/master/finishBill.json")
    @ResponseBody
    public JsonResponse finishBill(HttpServletRequest request) {
        // 1.搜集参数
        BigInteger billId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        // 2.交互
        JsonResponse json = new JsonResponse();
        DredgeBill bill = new DredgeBill();
        bill.setId(billId);
        bill.setStatus(DredgeConstant.DredgeBill.Master_Finish);
        int updCount = dredgeBillBaseService.updateDredgeBill(bill);
        json.setStatus(updCount == 0 ? "0001" : "0000");
        json.setMessage(updCount == 0 ? "操作失败" : "操作成功");
        return json;
    }

    /**
     * 查询上次下维修单的手机号
     */
    @RequestMapping(value = "/getLastDredgeCellphone.json")
    @ResponseBody
    public JsonResponse getLastDredgeCellphone() {
        JsonResponse json = new JsonResponse();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        PageModel pageModel = new PageModel(0, 1);
        DredgeBill dredgeBill = new DredgeBill();
        dredgeBill.settUserFId(userId);
        PropertyRepair propertyRepair = new PropertyRepair();
        propertyRepair.settUserFId(userId);
        String lastCellphone = null;
        List<DredgeBill> bills = dredgeBillBaseService.getDredgeBillByCondition(MapConverter.toMap(dredgeBill), pageModel);
        List<PropertyRepair> repairs = propertyRepairService.getPropertyRepairByCondition(MapConverter.toMap(propertyRepair), pageModel);
        //比较时间，取最近一次的手机号
        if (DataUtil.isEmpty(bills) && DataUtil.isEmpty(repairs)) {
            lastCellphone = UserContext.getUser().getLoginNoEntity().getUserEntity().getMobile();
        } else if (!DataUtil.isEmpty(bills) && !DataUtil.isEmpty(repairs)) {
            lastCellphone = bills.get(0).getSys0AddTime().compareTo(repairs.get(0).getSys0AddTime()) > 0 ? bills.get(0).getTel() : repairs.get(0).getTel();
        } else {
            lastCellphone = DataUtil.isEmpty(bills) ? repairs.get(0).getTel() : bills.get(0).getTel();
        }
        json.putData("lastCellphone", lastCellphone);
        return json;
    }

    /**
     * 查询是否是第一次下维修单付款，不是的缓存到redis，下次不用查
     */
    @RequestMapping(value = "/isFirstPayDredge.json")
    @ResponseBody
    public JsonResponse qryIsFirstPayDredge() {
        JsonResponse json = new JsonResponse();
        boolean flag = false;
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        if (RedisCacheHandler.get(RedisCachePrefix.isFirstPayDredge + userId) == null) {
            DredgeBill dredgeBill = new DredgeBill();
            dredgeBill.settUserFId(userId);
            int count = dredgeBillBaseService.getDredgeBillCount(MapConverter.toMap(dredgeBill));
            if (count == 1) {
                flag = true;
            } else {
                //缓存30天有效期
                RedisCacheHandler.set(RedisCachePrefix.isFirstPayDredge + userId, "N", 60 * 60 * 24 * 30);
            }
        }

        json.putData("isFirstPayDredge", flag);
        return json;
    }

    /**
     * 查询是否有可用耗材
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryHasProductOrNot.json")
    public JsonResponse qryHasProductOrNot(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();

        // 1.搜集参数
        BigDecimal dredgeTypeId = ParamUtils.getBigDecimal(request, "dredgeTypeId", null);
        BigDecimal subTypeId = ParamUtils.getBigDecimal(request, "subTypeId", null);
        //BigDecimal operation = ParamUtils.getBigDecimal(request, "operation", null); //new, edit

        // 2.交互
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dredgeTypeId", dredgeTypeId);
        paramMap.put("subTypeId", subTypeId);
        paramMap.put("userId", userId);
        paramMap.put("gbId", gbId);


        // 3.结果处理
        jsonResponse.putData("hasProductOrNot", dredgeService.qryProductListCount(paramMap) > 0);

//		if("new".equals(operation)){//新增维修单，即将跳转的选耗材H5
//			jsonResponse.putData("url", "http://192.168.1.65/API/dredge/toPage.html?page=maintain_itemSelect_fromindent");
//		}else if("edit".equals(operation)){//编辑下单，即将跳转选耗材的H5
//			jsonResponse.putData("url", "http://192.168.1.65/API/dredge/toPage.html?page=maintain_itemSelect_fromorder");
//		}

        return jsonResponse;
    }

    /**
     * 可自选耗费列表
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryProductList.json")
    public JsonResponse qryProductList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();

        // 1.搜集参数
        BigDecimal dredgeTypeId = ParamUtils.getBigDecimal(request, "dredgeTypeId", null);
        BigDecimal subTypeId = ParamUtils.getBigDecimal(request, "subTypeId", null);
        Integer appType = ParamUtils.getInteger(request, "appType", 1);

        //分页信息
        PageModel pageModel = ControllerUtils.getPageModel(request);

        // 2.交互
        BigInteger userId = UserContext.getOperIdBigInteger();
        BigInteger gbId = commonRoomService.getGroupBuildingIdByUserId(userId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dredgeTypeId", dredgeTypeId);
        paramMap.put("subTypeId", subTypeId);
        paramMap.put("userId", userId);
        paramMap.put("gbId", gbId);
        paramMap.put("appType", appType);
        paramMap.putAll(pageModel.toMap());

        // 3.结果处理
        List<SelfBuyProduct> resList = dredgeService.qryProductList(paramMap);
        pageModel.freshPageModel(resList.size(), dredgeService.qryProductListCount(paramMap));

        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * 查看用户自选耗材
     *
     * @param request
     * @return
     */
    @RequestMapping("/viewSelfBuyProductList.html")
    public ModelAndView viewSelfBuyProductList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();

        // 1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        String operType = ParamUtils.getString(request, "operType", "view");

        if (dredgeBillId == null) {
            throw new ValidateRuntimeException("dredgeBillId can't be null");
        }

        // 2.交互
//		BigInteger userId = UserContext.getOperIdMustExistBigInteger();
//		BigInteger userId = new BigInteger("5259950");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dredgeBillId", dredgeBillId);


        List<SelfBuyProduct> resList = dredgeService.getSelfBuyProductList(paramMap);
        jsonResponse.putData(PageModel.PageKey.LIST, resList);

        //canEdit默认为true，如果APP带过来为false，则直接取false
        boolean canEdit = ParamUtils.getBooleanNotNull(request, "canEdit", true);
        if (canEdit) {
            DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
//			DredgeBill db2 = new DredgeBill();
//			db2.setId(dredgeBillId);
//			db2.settUserFId(userId);
//			int count = dredgeBillBaseService.getDredgeBillCount(MapConverter.toMap(db2));
//			if (db != null && (db.getStatus() == 1 || db.getStatus() == 2) && count > 0) {
//				canEdit = true;
//			}
            if (db == null || (db.getStatus() != 1 && db.getStatus() != 2)) {
                canEdit = false;
            }
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("resList", resList);
        mav.addObject("canEdit", canEdit);
        if ("view".equals(operType)) {
            mav.setViewName("/dredge/maintain_itemHasSelected");
        } else {
            mav.setViewName("/dredge/maintain_itemEdit");
        }

        // 3.结果处理
        return mav;
    }

    /**
     * 查看用户自选耗材
     *
     * @param request
     * @return
     */
    @RequestMapping("/viewSelfBuyProductList.json")
    @ResponseBody
    public JsonResponse viewSelfBuyProductListJson(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        String operType = ParamUtils.getString(request, "operType", "view");

        if (dredgeBillId == null) {
            throw new ValidateRuntimeException("dredgeBillId can't be null");
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dredgeBillId", dredgeBillId);

        List<SelfBuyProduct> resList = dredgeService.getSelfBuyProductList(paramMap);
        jsonResponse.putData(PageModel.PageKey.LIST, resList);

        //canEdit默认为true，如果APP带过来为false，则直接取false
        boolean canEdit = ParamUtils.getBooleanNotNull(request, "canEdit", true);
        if (canEdit) {
            DredgeBill db = dredgeBillBaseService.getDredgeBillBySeqId(dredgeBillId);
            if (db == null || (db.getStatus() != 1 && db.getStatus() != 2)) {
                canEdit = false;
            }
        }
        jsonResponse.putData("canEdit", canEdit);

        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 保存用户自选耗材
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveSelfBuyProduct.json")
    public JsonResponse saveSelfBuyProduct(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        String operType = ParamUtils.getString(request, "operType", "add");

        List<ProductIdQtyEntity> productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);
        if (dredgeBillId == null) {
            throw new ValidateRuntimeException("Dredge.addDredge.wrong.repairId");
        }

        // 2.交互
        dredgeService.saveSelfBuyProduct(dredgeBillId, productIdQtyList, operType);

        // 3.结果处理
        return jsonResponse;
    }


    /**
     * 比较器，用于用户消费券按使用结束时间排序
     */
    private static class CouponComparator implements Comparator<UserCouponEntity>, Serializable {

        private static final long serialVersionUID = -8481359026312140000L;

        @Override
        public int compare(UserCouponEntity o1, UserCouponEntity o2) {
            String coupon1 = o1.getUseEndDate();
            String coupon2 = o2.getUseEndDate();
            return coupon1.compareTo(coupon2);
        }
    }

    /**
     * 删除用户自选耗材
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteSelfBuyProduct.json")
    public JsonResponse deleteSelfBuyProduct(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();

        // 1.搜集参数
        BigInteger dredgeBillId = ParamUtils.getBigInteger(request, "dredgeBillId", null);
        List<BigInteger> productShelfIds = JSON.parseArray(request.getParameter("shelfIds"), BigInteger.class);
        if (DataUtil.isEmpty(productShelfIds)) {
            throw new ValidateRuntimeException("DredgeController.deleteSelfBuyProduct.productShelfIds.cant be null");
        }

        // 2.交互
        dredgeService.deleteSelfBuyProduct(dredgeBillId, productShelfIds);

        // 3.结果处理
        return jsonResponse;
    }


    /**
     * 查询小区或房间所在经纬度
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/getLocationByGbIdOrRoomId.json")
    public JsonResponse getLocationByGbIdOrRoomId(HttpServletRequest request, BigInteger gbId, BigInteger roomId) {
        JsonResponse jsonResponse = new JsonResponse();

        // 1.搜集参数
        if (gbId == null && roomId == null) {
            throw new ValidateRuntimeException("gbId and roomId can't all be null");
        }

        // 2.交互
        Location location = groupBuildingService.getLocationByGbIdOrRoomId(gbId, roomId);
        jsonResponse.putData("lat", location.getLat());
        jsonResponse.putData("lng", location.getLng());

        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 维修报修单，内转外
     *
     * @param dredgeTypeId
     * @param subTypeId
     * @param expectDate
     * @param dredgeId
     * @return
     */
    @RequestMapping(value = "turnBillType.json", method = RequestMethod.POST)
    @ResponseBody
    @Deprecated//507开始不用这个了
    public JsonResponse turnBillType(BigInteger dredgeTypeId, BigInteger subTypeId, Long expectDate, BigInteger dredgeId, HttpServletRequest request) {
        JsonResponse json = new JsonResponse();
        if (dredgeId == null || dredgeTypeId == null || expectDate == null) {
            json.setStatus("0001");
            json.setMessage("所需信息不全");
            return json;
        }
        List<ProductIdQtyEntity> productIdQtyList = JSON.parseArray(request.getParameter("productList"), ProductIdQtyEntity.class);

        DredgeBill dredgeBill = new DredgeBill();
        dredgeBill.setId(dredgeId);
        dredgeBill.settDredgeTypeFId(dredgeTypeId);
        dredgeBill.settDredgeType2ndFId(subTypeId);
        dredgeBill.setExpectdate(DateUtils.formatTime(new Date(expectDate)));
        dredgeService.turnBillType(dredgeBill, productIdQtyList);
        return json;
    }

    @RequestMapping(value = "/qryDredgeBottomAd.json")
    @ResponseBody
    public JsonResponse getDredgeBottomAd(BigInteger gbId, BigInteger blockId) {
    	BigInteger userId = UserContext.getOperIdBigInteger();
    	
        gbId = gbId == null ? commonRoomService.getGroupBuildingIdByUserId(userId) : gbId;
        
        Map<String, Object> para = new HashMap<>();
		List<BigInteger> addrCodeIdList = getAddrCodeIdList(blockId, gbId, userId);
        List<String> codeList = new ArrayList<>();
        codeList.add("DREDGE_ACTIVITY_AD");
        para.put("codeIdList", addrCodeIdList);
        para.put("codeList", codeList);
        List<EbuyAdvertise> ebuyAdvertiseList = ebuyAdvertiseService.getEbuyAdvertiseList(para);
        Map<String, Object> adMap = new HashMap<>();
        adMap.put("adDesc", "限时优惠");
        if (DataUtil.isEmpty(ebuyAdvertiseList)) {
            adMap.put("adDesc", "服务保障");
            codeList.clear();
            codeList.add("DREDGE_ACTIVITY_STATIC");
            ebuyAdvertiseList = ebuyAdvertiseService.getEbuyAdvertiseList(para);
        }
        if (!DataUtil.isEmpty(ebuyAdvertiseList)) {
            for (EbuyAdvertise ebuyAdvertise : ebuyAdvertiseList) {
                List<String> lines = Arrays.asList(ebuyAdvertise.getTittle().split(";"));
                ebuyAdvertise.setFirstLine(lines.get(0));
                ebuyAdvertise.setSecondLine(lines.get(1));
                ebuyAdvertise.setThirdLine(lines.get(2));
                ebuyAdvertise.setPicUrl(OmsSysParamManager.getImageServerUrl(PathConstants.Advertise_Pic_base) +  PathConstants.Advertise_Pic_base + ebuyAdvertise.getPicName());
            }
        }
        adMap.put("adList", ebuyAdvertiseList);
        List<Map<String, Object>> resList = new ArrayList<>();
        resList.add(adMap);
        JsonResponse json = new JsonResponse();
        json.putData("ads", resList);
        return json;
    }

    /**
     * 获取地址ID.
     *
     * @param city  城市
     * @param block 区县ID
     * @return 地址id
     */
    @RequestMapping(value = "/qryAddressIds.json")
    @ResponseBody
    public JsonResponse getAddressIds(String city, String block) {
        BigInteger cityId = null, blockId = null, provinceId = null;
        if (StringUtils.isEmpty(block)) {
            Map<String, Object> paramMap2 = new HashMap<String, Object>();
            paramMap2.put("city", city);
            cityId = ebuyNewService.selectCityIdByName(paramMap2);
            if (cityId != null) {
                provinceId = addressCityBaseService.getAddressCityBySeqId(cityId).gettProvinceFId();
            }
        } else {
            AddressIdEntity addressIdEntity = addressOperationService.getAddressIdEntity(city, block);
            if (addressIdEntity != null) {
                cityId = addressIdEntity.getCityId();
                blockId = addressIdEntity.getBlockId();
                provinceId = addressIdEntity.getProvinceId();
            }
        }
        JsonResponse json = new JsonResponse();
        json.putData("cityId", cityId);
        json.putData("blockId", blockId);
        json.putData("provinceId", provinceId);
        return json;
    }

    @RequestMapping(value = "validateDredgeAddress.json", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse validateDredgeAddress(BigInteger gbId, BigInteger blockId, BigInteger dredgeProductId) {
        List<BigInteger> codeIdList = addressOperationService.getCodeIdList(null, null, null, blockId, gbId);
        boolean inDredgeProductArea = dredgeService.isInDredgeProductArea(codeIdList, dredgeProductId);
        JsonResponse json = new JsonResponse();
        json.putData("ok", inDredgeProductArea);
        return json;
    }

    @RequestMapping(value = "sdfsa", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse hasATest(BigInteger id){
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.putData("", 11);
        jsonResponse.getErrcode();
        return jsonResponse;
    }

}

