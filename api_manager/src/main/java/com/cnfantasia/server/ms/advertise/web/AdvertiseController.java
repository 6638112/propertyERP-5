package com.cnfantasia.server.ms.advertise.web;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.advertise.service.AdvertiseService;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.UUIDGenerater;
import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;
import com.cnfantasia.server.domainbase.communityAds.service.ICommunityAdsBaseService;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.communitySupplyType.service.ICommunitySupplyTypeBaseService;
import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProduct.service.IDredgeProductBaseService;
import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.domainbase.ebuyAdvertise.service.IEbuyAdvertiseBaseService;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;
import com.cnfantasia.server.domainbase.operationHomeSupplyType.service.IOperationHomeSupplyTypeBaseService;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant;
import com.cnfantasia.server.ms.advertise.entity.AdvQryParam;
import com.cnfantasia.server.ms.advertise.entity.AdvertiseDto;
import com.cnfantasia.server.ms.advertise.entity.EbuyAdvertiseDto;
import com.cnfantasia.server.ms.advertise.service.AdvertiseForOmsService;
import com.cnfantasia.server.ms.advertise.service.IAdvertiseForOmsService;
import com.cnfantasia.server.ms.groupBuilding.entity.GroupBuildingSimpleEntity;
import com.cnfantasia.server.ms.notice.service.INoticeService;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.constant.PathConstants;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 广告管理controller
 */
@Controller
@RequestMapping("/adv")
public class AdvertiseController extends BaseController {

    @Resource
    private IEbuyAdvertiseBaseService ebuyAdvertiseBaseService;
    @Resource
    private IAdvertiseForOmsService advertiseForOmsService;
    @Resource
    private IOperationHomeSupplyTypeBaseService operationHomeSupplyTypeBaseService;
    @Resource
    private ICommunityAdsBaseService communityAdsBaseService;
    @Resource
    private ICommunitySupplyTypeBaseService communitySupplyTypeBaseService;
    @Resource
    private IDredgeProductBaseService dredgeProductBaseService;
    @Resource
	private INoticeService noticeService;
    @Resource
    private ISysParamManager sysParamManager;

    /**
     * 跳转新增页
     * @return
     */
    @RequestMapping(value = "/addThemeProductAdv.html", method = RequestMethod.GET)
    public String jumpToAddThemeProductAdv() {
        return "/advertise/addThemeProductAdv";
    }

    /**
     * 新增
     * @return
     */
    @RequestMapping(value = "/addThemeProductAdv.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addThemeProductAdv(AdvertiseDto dto, HttpServletRequest request) throws IOException {
        JsonResponse json = new JsonResponse();
        String pic = uploadImage(request, AdvConstant.AdvType.Shou_Ye_Tan_Chuang);
        dto.getEbuyAdvertise().setPicName(pic);
        advertiseForOmsService.addThemeProductAdv(dto);
        return json;
    }

    /**
     * 广告列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/themeProductAdvList.html")
    public ModelAndView themeProductAdvList(AdvQryParam param, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/advertise/themeProductAdvList");

        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名
        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);
        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
        Long total = advertiseForOmsService.getThemeProductAdvListCount(param);
        List<EbuyAdvertiseDto> resList = advertiseForOmsService.getThemeProductAdvList(param, pageModel);
        mav.addObject("param", param);
        mav.addObject("total", total.intValue());
        mav.addObject("resList", resList);
        return mav;
    }

    /**
     * 详情
     * @param advId
     * @return
     */
    @RequestMapping(value = "/themeProductAdvDetail.html", method = RequestMethod.GET)
    public ModelAndView themeProductAdvDetail(BigInteger advId) {
        ModelAndView mav = getThemeProductAdvInfo(advId);
        mav.setViewName("/advertise/themeProductAdvDetail");
        return mav;
    }

    /**
     * 跳转更新页
     * @param advId
     * @return
     */
    @RequestMapping(value = "/updThemeProductAdv.html", method = RequestMethod.GET)
    public ModelAndView jumpToUpdThemeProductAdv(BigInteger advId) {
        ModelAndView mav = getThemeProductAdvInfo(advId);
        mav.setViewName("/advertise/updThemeProductAdv");
        return mav;
    }

    /**
     * 获取推广商品广告详情
     * @param advId
     * @return
     */
    private ModelAndView getThemeProductAdvInfo(BigInteger advId) {
        ModelAndView mav = new ModelAndView();
        EbuyAdvertise ebuyAdvertise = ebuyAdvertiseBaseService.getEbuyAdvertiseBySeqId(advId);
        mav.addObject("advertise", ebuyAdvertise);
        List<Map<String, Object>> shelfProducts = advertiseForOmsService.getThemeProductAdvProductList(advId);
        mav.addObject("products", shelfProducts);
        List<Map<String, Object>> areas = advertiseForOmsService.getAdvAreaByAdvId(advId, AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang);
        mav.addObject("areas", areas);
        String picName = ebuyAdvertise.getPicName();
        mav.addObject("picPath", OmsSysParamManager.getImageServerUrl(PathConstants.Advertise_Pic_base)
                + PathConstants.Advertise_Pic_base + picName);
        if (!DataUtil.isEmpty(areas)) {
            Map<String, Object> area = areas.get(0);
            if (area.get("gbId") != null && !"".equals(area.get("gbId")) && !"0".equals(area.get("gbId"))) {
                mav.addObject("areaType", 3);
            } else if (area.get("cityId") != null && !"".equals(area.get("cityId")) && !"0".equals(area.get("cityId"))) {
                mav.addObject("areaType", 2);
            } else {
                mav.addObject("areaType", 1);
            }
        }
        return mav;
    }
    
    /**
     * 获取广告详情
     * @param advId
     * @param advType
     * @param isUpdate
     * @return
     */
    private ModelAndView getAdvInfo(BigInteger advId, String advType, boolean isUpdate) {
    	ModelAndView mav = new ModelAndView();
    	// 广告基础信息
        EbuyAdvertise ebuyAdvertise = null; 
        if (AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType)
                || AdvConstant.AdvType.Dao_Jia.equals(advType)
                || AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)
                || AdvConstant.AdvType.WX_STORE_ORDER_PUSH.equals(advType)
                || AdvConstant.AdvType.CAR_FEE.equals(advType)
                || AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType)) {
        	ebuyAdvertise = ebuyAdvertiseBaseService.getEbuyAdvertiseBySeqId(advId);
        	if(!AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType) && ebuyAdvertise!=null && ebuyAdvertise.getType()!=null && ebuyAdvertise.getType()==5){
        		String linkUrl = ebuyAdvertise.getLinkUrl();
        		if(linkUrl!=null && StringUtils.isNumeric(linkUrl)){
        			CommunitySupplyType communitySupplyType = communitySupplyTypeBaseService.getCommunitySupplyTypeBySeqId(new BigInteger(linkUrl));
        			mav.addObject("typeName", communitySupplyType.getName());
        		}
        	}
        	//到家底部的广告，三行描述放在title了
            if (AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)) {
                List<String> lines = Arrays.asList(ebuyAdvertise.getTittle().split(";"));
                mav.addObject("lines", lines);
            }
            AdvertiseForOmsService.dealRealTitle(ebuyAdvertise);
        } else if (AdvConstant.AdvType.Shou_Ye_Lan_Yao.equals(advType)) {
			OperationHomeSupplyType ohst = operationHomeSupplyTypeBaseService.getOperationHomeSupplyTypeBySeqId(advId);
			ebuyAdvertise = new EbuyAdvertise();
			ebuyAdvertise.setId(advId);
			ebuyAdvertise.setTittle(ohst.getName());
			ebuyAdvertise.setCode(ohst.getCode());
			ebuyAdvertise.setLinkUrl(ohst.getLinkUrl());
			if(ohst.getDataType()!=null){
				if(ohst.getDataType()==1){
					ebuyAdvertise.setType(AdvConstant.JumpType.No_Jump);
				} else if(ohst.getDataType()==2){
					ebuyAdvertise.setType(AdvConstant.JumpType.H5);
				} else if(ohst.getDataType()==3){
					ebuyAdvertise.setType(AdvConstant.JumpType.App);
				}
			}
			ebuyAdvertise.setOrder(ohst.getOrder());
			ebuyAdvertise.setVersion(ohst.getVersion());
			ebuyAdvertise.setMaxVersion(ohst.getMaxVersion());
			ebuyAdvertise.setPicName(ohst.getIconName());
			ebuyAdvertise.setStartTime(ohst.getStartTime());
			ebuyAdvertise.setEndTime(ohst.getEndTime());
		} else if (AdvConstant.AdvType.Jie_Fang.equals(advType)) {
			CommunityAds ca = communityAdsBaseService.getCommunityAdsBySeqId(advId);
			ebuyAdvertise = new EbuyAdvertise();
			ebuyAdvertise.setId(advId);
			ebuyAdvertise.setTittle(ca.getName());
			ebuyAdvertise.setLinkUrl(ca.getDetailUrl());
			if(StringUtils.isNotBlank(ca.getDetailUrl())){
				ebuyAdvertise.setType(AdvConstant.JumpType.H5);
			} else {
				ebuyAdvertise.setType(AdvConstant.JumpType.No_Jump);
			}
			ebuyAdvertise.setOrder(ca.getOrder());
			ebuyAdvertise.setVersion(ca.getMinVersion());
			ebuyAdvertise.setMaxVersion(ca.getMaxVersion());
			ebuyAdvertise.setPicName(ca.getIcon());
			ebuyAdvertise.setStartTime(ca.getStartTime());
			ebuyAdvertise.setEndTime(ca.getEndTime());
		}
        mav.addObject("advertise", ebuyAdvertise);
        mav.addObject("advType", advType);
        if(ebuyAdvertise!=null && StringUtils.isNotBlank(ebuyAdvertise.getLinkUrl()) && StringUtils.isNumeric(ebuyAdvertise.getLinkUrl())){
        	mav.addObject("linkUrlId", Integer.valueOf(ebuyAdvertise.getLinkUrl()));
        }
        
        // 广告范围
        List<Map<String, Object>> areas = advertiseForOmsService.getAdvAreaByAdvId(advId, getAdvType(advType));
        String picName = ebuyAdvertise.getPicName();
        mav.addObject("picPath", OmsSysParamManager.getImageServerUrl(PathConstants.Advertise_Pic_base) + getBasePathForAdv(advType) + picName);
        if (!DataUtil.isEmpty(areas)) {
            Map<String, Object> area = areas.get(0);
            if (area.get("gbId") != null && !"".equals(area.get("gbId")) && !"0".equals(area.get("gbId"))) {// 小区
                mav.addObject("areaType", 3);
                if(isUpdate){
                	// 已选小区处理
                	List<GroupBuildingSimpleEntity> gbList = advertiseForOmsService.getGbs();
                    for (Map<String, Object> areaItem:areas){
                    	Object gbIdTmp = areaItem.get("gbId");
            			for (GroupBuildingSimpleEntity gb:gbList) {
            				if (gbIdTmp!=null && gb.getId()!=null && gbIdTmp.toString().equals(gb.getId().toString())) {
            					gb.setIsPushed("yes");
            				}
            			}
            		}
                    mav.addObject("gbList", gbList);
                    
                    Map<String, Object> params = new HashMap<String, Object>();
            		params.put("gbIdList", UserContext.getGbIdList());
            		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
            		mav.addObject("blockList", blockList);
                }
            } else if (area.get("cityId") != null && !"".equals(area.get("cityId")) && !"0".equals(area.get("cityId"))) {// 城市（兼容老数据）
            	if(isUpdate){
            		Map<String, Object> params = new HashMap<String, Object>();
            		params.put("gbIdList", UserContext.getGbIdList());
            		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
            		for (Map<String, Object> areaItem:areas){
                    	Object blockIdTmp = areaItem.get("blockId");
            			for (GroupBuildingSimpleEntity block:blockList){
                        	BigInteger blockId = block.getBlockId();
                        	if (blockIdTmp!=null && blockId!=null && blockIdTmp.toString().equals(blockId.toString())) {
            					block.setIsPushed("yes");
            				}
                		}
            		}
            		mav.addObject("blockList", blockList);
            		
            		// 已选城市对应的小区处理（兼容旧数据）
            		List<GroupBuildingSimpleEntity> gbList = advertiseForOmsService.getGbs();
                    mav.addObject("gbList", gbList);
                }
                mav.addObject("areaType", 4);
            } else {// 全国
                mav.addObject("areaType", 1);
            	if(isUpdate){
            		Map<String, Object> params = new HashMap<String, Object>();
            		params.put("gbIdList", UserContext.getGbIdList());
            		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
            		for (GroupBuildingSimpleEntity block:blockList) {
            			block.setIsPushed("yes");
        			}
            		mav.addObject("blockList", blockList);
            		
            		// 已选小区处理
                	List<GroupBuildingSimpleEntity> gbList = advertiseForOmsService.getGbs();
        			for (GroupBuildingSimpleEntity gb:gbList) {
    					gb.setIsPushed("yes");
        			}
                    mav.addObject("gbList", gbList);
            	}
            }
        }
        
        if(!isUpdate){
        	mav.addObject("areas", areas);
        }
        return mav;
    }
    
    private List<GroupBuildingSimpleEntity> getSelectedGbs(List<Map<String, Object>> areas, String typeKey){
    	List<GroupBuildingSimpleEntity> gbList = advertiseForOmsService.getGbs();
        for (Map<String, Object> areaItem:areas){
        	Object gbIdTmp = areaItem.get(typeKey);
			for (GroupBuildingSimpleEntity gb:gbList) {
				if (gbIdTmp!=null && gb.getCityId()!=null && gbIdTmp.toString().equals(gb.getCityId().toString())) {
					gb.setIsPushed("yes");
				}
			}
		}
        return gbList;
    }
    
    /**
     * 根据广告类型获取对应的t_operation_sa_has_eb_supply表类型
     * @param advType
     * @return
     */
	private Integer getAdvType(String advType) {
		if (AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType)) {
			return AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
		} else if (AdvConstant.AdvType.Shou_Ye_Lan_Yao.equals(advType)) {
			return AdvConstant.EbSupplyType.Shou_Ye_Lan_Yao;
		} else if (AdvConstant.AdvType.Dao_Jia.equals(advType)) {
			return AdvConstant.EbSupplyType.Dao_Jia;
		} else if (AdvConstant.AdvType.Jie_Fang.equals(advType)) {
			return AdvConstant.EbSupplyType.Jie_Fang;
		} else if (AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)
                || AdvConstant.AdvType.WX_STORE_ORDER_PUSH.equals(advType)
                || AdvConstant.AdvType.CAR_FEE.equals(advType)
                || AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType)) {
            return AdvConstant.EbSupplyType.Shou_Ye_Tan_Chuang;
        }
		return null;
	}

    /**
     * 更新主题商品广告
     * @param advId
     * @return
     */
    @RequestMapping(value = "/updThemeProductAdv.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updThemeProductAdv(AdvertiseDto dto, HttpServletRequest request) throws IOException {
        JsonResponse json = new JsonResponse();
        String pic = uploadImage(request, AdvConstant.AdvType.Shou_Ye_Tan_Chuang);
        if (pic != null) {
            dto.getEbuyAdvertise().setPicName(pic);
        }
        advertiseForOmsService.updThemeProductAdv(dto);
        return json;
    }

    /**
     * 更新广告
     * @param ebuyAdvertise
     * @return
     */
    @RequestMapping(value = "/updAdv.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updAdv(EbuyAdvertise ebuyAdvertise, boolean isEndOperation) {
        JsonResponse jsonResponse = new JsonResponse();
        if (isEndOperation) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ebuyAdvertise.setEndTime(sdf.format(new Date()));
        }
        ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
        return jsonResponse;
    }
    
    /**
     * 结束广告
     * @param id
     * @param advType
     * @return
     */
    @RequestMapping(value = "/finishAdv.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse finishAdv(BigInteger id, String advType){
    	String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    	BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
    	
    	int affectedCount = 0;
    	if(AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType)
                || AdvConstant.AdvType.Dao_Jia.equals(advType)
                || AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)
                || AdvConstant.AdvType.WX_STORE_ORDER_PUSH.equals(advType)
                || AdvConstant.AdvType.CAR_FEE.equals(advType)
                || AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType)){
    		EbuyAdvertise ebuyAdvertise = new EbuyAdvertise();
    		ebuyAdvertise.setId(id);
    		ebuyAdvertise.setEndTime(now);
    		ebuyAdvertise.setSys0UpdTime(now);
    		ebuyAdvertise.setSys0UpdUser(userId);
    		affectedCount = ebuyAdvertiseBaseService.updateEbuyAdvertise(ebuyAdvertise);
    	} else if(AdvConstant.AdvType.Shou_Ye_Lan_Yao.equals(advType)){
    		OperationHomeSupplyType ohst = new OperationHomeSupplyType();
    		ohst.setId(id);
    		ohst.setEndTime(now);
    		ohst.setSys0UpdTime(now);
    		ohst.setSys0UpdUser(userId);
    		affectedCount = operationHomeSupplyTypeBaseService.updateOperationHomeSupplyType(ohst);
    	} else if(AdvConstant.AdvType.Jie_Fang.equals(advType)){
    		CommunityAds ca = new CommunityAds();
    		ca.setId(id);
    		ca.setEndTime(now);
    		ca.setSys0UpdTime(now);
    		ca.setSys0UpdUser(userId);
    		affectedCount = communityAdsBaseService.updateCommunityAds(ca);
    	}
    	
    	JsonResponse jsonResponse = new JsonResponse();
    	if(affectedCount > 0){
    		jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
    		jsonResponse.setMessage("操作成功！");
    	} else {
    		jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
    		jsonResponse.setMessage("操作失败！");
    	}
		return jsonResponse;
    }

    @RequestMapping(value = "getShelfProductForAdv.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getShelfProductForAdv(String qryStr, Integer appType, boolean experienceStore) {
        PageModel pageModel = new PageModel(0, 20);
        BigInteger supplyMerchantId = null;
        if (experienceStore) {
            String storeId = sysParamManager.getSysParaValue(SysParamKey.Experience_Store_Id);
            if (!StringUtils.isEmpty(storeId)) {
                supplyMerchantId = new BigInteger(storeId);
            }
        }
        return advertiseForOmsService.getShelfProductForAdv(qryStr, supplyMerchantId, appType, pageModel);
    }

    /**
     * 
     * 街坊广告：
     * 
     * @param request
     * @return
     * @throws IOException
     */
    private String uploadImage(HttpServletRequest request, String advType) throws IOException {
        // 浏览图片
        String picImgs = null;
        // 详情图片
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 浏览图片
            List<MultipartFile> picImageFiles = multipartRequest.getFiles("photoimage");
            if (null != picImageFiles && picImageFiles.size() > 0) {
                String picPath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + getBasePathForAdv(advType);
                for (MultipartFile picImageFile : picImageFiles) {
                    if (picImageFile != null && !com.cnfantasia.server.common.utils.StringUtils.isEmpty(picImageFile.getOriginalFilename())) {//有上传图片
                        int indexOfDot = picImageFile.getOriginalFilename().indexOf(".");
                        String fileNameC = UUIDGenerater.getFileName() + picImageFile.getOriginalFilename().substring(indexOfDot);
                        File fileC = new File(picPath + fileNameC);
                        if (!fileC.exists())
                            fileC.mkdirs();
                        picImgs = fileNameC;
                        picImageFile.transferTo(fileC);
                    }
                }
            }
        }
        return picImgs;
    }
    
    private String getBasePathForAdv(String advType){
    	if(AdvConstant.AdvType.Shou_Ye_Tan_Chuang.equals(advType)
                || AdvConstant.AdvType.Dao_Jia.equals(advType)
                || AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(advType)
                || AdvConstant.AdvType.WX_STORE_ORDER_PUSH.equals(advType)
                || AdvConstant.AdvType.CAR_FEE.equals(advType)
                || AdvConstant.AdvType.EXPERIENCE_STORE.equals(advType)){
    		return PathConstants.Advertise_Pic_base;
    	} else if(AdvConstant.AdvType.Shou_Ye_Lan_Yao.equals(advType)){
    		return PathConstants.CommunitySupply_Type_Ico;
    	} else if(AdvConstant.AdvType.Jie_Fang.equals(advType)){
    		return PathConstants.CommunityForum_Type_Ico;
        }
        return "/";
    }

    /***************************************** 以下为首页弹框广告  ***************************************************/

    /**
     * 跳转新增弹框页
     * @return
     */
    @RequestMapping(value = "/addAlertAdv.html", method = RequestMethod.GET)
    public ModelAndView jumpToAddAlertAdv(String advType, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	if(StringUtils.isBlank(advType)){
    		request.setAttribute(JSPConstants.OprtResult, "非法进入，跳转类型不能为空！");
    		request.setAttribute(JSPConstants.ToURL, "../security/welcome.html");
    		mav.setViewName(JSPConstants.OprtSuccessPage);
    	} else {
    		mav.setViewName("/advertise/addAlertAdv");
    		mav.addObject("advType", advType);
    		mav.addObject("gbList", advertiseForOmsService.getGbs());
    		
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("gbIdList", UserContext.getGbIdList());
    		List<GroupBuildingSimpleEntity> blockList = noticeService.getBlockList(params);
    		mav.addObject("blockList", blockList);
    		
    		dealTypeForDaoJia(advType, mav);
    	}
        return mav;
    }
    
    /**
     * 到家跳到App时的类型处理
     * 
     * @param advType
     * @param mav
     */
    private void dealTypeForDaoJia(String advType, ModelAndView mav){
    	if(AdvConstant.AdvType.Dao_Jia.equals(advType)){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("importanceLevel", -4);
			List<CommunitySupplyType> communitySupplyTypeList = communitySupplyTypeBaseService.getCommunitySupplyTypeByCondition(paramMap);
			mav.addObject("communitySupplyTypeList", communitySupplyTypeList);
		}
    }

    /**
     * 新增弹框广告
     * @return
     */
    @RequestMapping(value = "/addAlertAdv.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse addAlertAdv(AdvertiseDto dto, HttpServletRequest request) throws IOException {
        String pic = uploadImage(request, String.valueOf(dto.getAdvType()));
        dto.getEbuyAdvertise().setPicName(pic);
        advertiseForOmsService.addAlertAdv(dto);
        return new JsonResponse();
    }

    /**
     * 广告列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/alertAdvList.html")
    public ModelAndView alertAdvList(AdvQryParam param, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/advertise/alertAdvList");

        String pageIndexName = new org.displaytag.util.ParamEncoder("row")
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);// 页数的参数名
        int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
        int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ?
                0 : (Integer.parseInt(request.getParameter(pageIndexName))-1);
        PageModel pageModel = new PageModel((curPageIndex)*pageSize, pageSize);
        Long total = advertiseForOmsService.getAlertAdvListCount(param);
        List<EbuyAdvertiseDto> resList = advertiseForOmsService.getAlertAdvList(param, pageModel);
        if(resList!=null && resList.size()>0) {
        	for(EbuyAdvertiseDto ebuyAdvertise:resList) {
        		if(AdvConstant.AdvType.DAO_JIA_BOTTOM.equals(ebuyAdvertise.getAdvType())) {
        			AdvertiseForOmsService.dealRealTitle(ebuyAdvertise);
        		}
        	}
        }
        mav.addObject("param", param);
        mav.addObject("total", total.intValue());
        mav.addObject("resList", resList);
        return mav;
    }

    /**
     * 跳转更新页
     * @param advId
     * @return
     */
    @RequestMapping(value = "/updAlertAdv.html", method = RequestMethod.GET)
    public ModelAndView jumpToAlertAdv(BigInteger advId, String advType) {
        ModelAndView mav = getAdvInfo(advId, advType, true);
        
        dealTypeForDaoJia(advType, mav);
        
        mav.setViewName("/advertise/updAlertAdv");
        return mav;
    }

    /**
     * 跳转更新页
     * @param advId
     * @return
     */
    @RequestMapping(value = "/updAlertAdv.html", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updAlertAdv(AdvertiseDto dto, HttpServletRequest request) throws IOException {
        String pic = uploadImage(request, String.valueOf(dto.getAdvType()));
        if (pic != null) {
            dto.getEbuyAdvertise().setPicName(pic);
        }
        advertiseForOmsService.updAlertAdv(dto);
        return new JsonResponse();
    }

    /**
     * 跳转详情页
     * @param advId
     * @return
     */
    @RequestMapping(value = "/alertAdvDetail.html", method = RequestMethod.GET)
    public ModelAndView jumpToAlertAdvDetail(BigInteger advId, String advType) {
        ModelAndView mav = getAdvInfo(advId, advType, false);
        mav.setViewName("/advertise/alertAdvDetail");
        return mav;
    }

    @RequestMapping(value = "getDredgeProductList.json", method = RequestMethod.GET)
    @ResponseBody
    public List<DredgeProduct> getDredgeProductList(String name) {
        DredgeProduct product = new DredgeProduct();
        product.setName(name);
        product.setStatus(1);
        PageModel pageModel = new PageModel(0, 10);
        return dredgeProductBaseService.getDredgeProductByConditionDim(MapConverter.toMap(product), pageModel);
    }

}
