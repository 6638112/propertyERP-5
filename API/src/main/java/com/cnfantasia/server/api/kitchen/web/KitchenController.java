/**   
 * Filename:    KitchenController.java   
 * @version:    1.0  
 * Create at:   2014年7月28日 上午9:28:14   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年7月28日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.kitchen.web;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.service.ICommonKitchenService;
import com.cnfantasia.server.api.commonBusiness.util.Lunar;
import com.cnfantasia.server.api.kitchen.constant.KitchenDict;
import com.cnfantasia.server.api.kitchen.entity.DietAnalysisEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookTypeEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.kitchen.service.IKitchenService;
import com.cnfantasia.server.api.pub.BaseController;
import com.cnfantasia.server.api.pub.ControllerUtils;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.user.constant.UserDict;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.kitchenCookbook.entity.KitchenCookbook;

/**
 * Filename: KitchenController.java
 * 
 * @version: 1.0.0 Create at: 2014年7月28日 上午9:28:14 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年7月28日 shiyl 1.0 1.0 Version
 */
@RequestMapping("/kitchen")
@Controller
public class KitchenController extends BaseController {
    private IKitchenService kitchenService;

    public void setKitchenService(IKitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    private ICommonKitchenService commonKitchenService;

    public void setCommonKitchenService(ICommonKitchenService commonKitchenService) {
        this.commonKitchenService = commonKitchenService;
    }

    private IDualDao dualDao;

    public void setDualDao(IDualDao dualDao) {
        this.dualDao = dualDao;
    }

    private ISysParamManager sysParamManager;

    public void setSysParamManager(ISysParamManager sysParamManager) {
        this.sysParamManager = sysParamManager;
    }

    @RequestMapping("/qryCookbookDetail.json")
    @ResponseBody
    public JsonResponse qryCookbookDetail(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cookbookId = new BigInteger(request.getParameter("cookbookId"));
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        KitchenEntity kitchenEntity = kitchenService.getCookbookDetail(cookbookId, userId);
        // 3.结果处理
        if (kitchenEntity != null) {
            Map<String, Object> resMap = commonKitchenService.cookbookInfo2Map(kitchenEntity, kitchenEntity.getTotalLikeEatCount(), kitchenEntity.getKitchenDetailList(), kitchenEntity.getIsLikeEat(),
                    kitchenEntity.getKitchenCookbookStepList(), kitchenEntity.getCollectFlag());
            jsonResponse.setDataValue(resMap);
        }
        return jsonResponse;
    }

    @RequestMapping("/qryCookbookTypes.json")
    @ResponseBody
    public JsonResponse qryCookbookTypes(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cityId = fetchCityId(request);
        PageModel pageModel = ControllerUtils.getPageModel(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        List<KitchenCookbookTypeEntity> typeList = kitchenService.getCookbookTypeList(cityId, pageModel, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (KitchenCookbookTypeEntity kitchenCookbookType : typeList) {
            Map<String, Object> tmpMap = commonKitchenService.cookbookTypeInfo2Map(kitchenCookbookType, kitchenCookbookType.getKitchenCookbookTopType(), kitchenCookbookType.getCollectFlag());
            resList.add(tmpMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    @RequestMapping("/qryCookbookList.json")
    @ResponseBody
    public JsonResponse qryCookbookList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String searchKey = request.getParameter("searchKey");
        String cityName = fetchCityNamePosition(request);
        List<Integer> cookbookTypeIds = new ArrayList<Integer>();
        try {
            Integer typeId = Integer.valueOf(request.getParameter("typeId"));
            if (null != typeId) {
                cookbookTypeIds.add(typeId);
            } else {
            }
        } catch (NumberFormatException e) {
            // e.printStackTrace(); 参数缺失，正常
        }
        try {
            Integer role = Integer.valueOf(request.getParameter("role"));
            if (null != role) {
                cookbookTypeIds.addAll(UserDict.S_ROLE2CBTYPE.get(role));
            } else {
            }
        } catch (NumberFormatException e) {
            // e.printStackTrace(); 参数缺失，正常
        }
        PageModel pageModel = ControllerUtils.getPageModel(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        String nowUpdTime = kitchenService.fetchAllKitchenLastUpdTime(cookbookTypeIds, searchKey, pageModel, userId);
        if (!ControllerUtils.checkHasNewData(request, jsonResponse, nowUpdTime)) {
            return jsonResponse;
        }

        List<KitchenEntity> kitchenEntityList = kitchenService.getCookbookList(cityName, cookbookTypeIds, searchKey, pageModel, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (KitchenEntity tmpKitchenEntity : kitchenEntityList) {
            Map<String, Object> tmpMap = commonKitchenService.cookbookInfo2Map(tmpKitchenEntity, tmpKitchenEntity.getTotalLikeEatCount(), null, tmpKitchenEntity.getIsLikeEat(), null,
                    tmpKitchenEntity.getCollectFlag());
            resList.add(tmpMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    @RequestMapping("/qryCookbookListRandom.json")
    @ResponseBody
    public JsonResponse qryCookbookListRandom(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String cityName = fetchCityNamePosition(request);
        List<Integer> cookbookTypeIds = new ArrayList<Integer>();
        try {
            Integer role = Integer.valueOf(request.getParameter("role"));
            if (null != role) {
                cookbookTypeIds.addAll(UserDict.S_ROLE2CBTYPE.get(role));
            } else {
            }
        } catch (NumberFormatException e) {
            // e.printStackTrace(); 参数缺失，正常
        }

        Integer count = 10; // 返回个数

        try {
            count = Integer.valueOf(request.getParameter("count"));
        } catch (NumberFormatException e) {
            // e.printStackTrace(); 参数缺失，正常
        }

        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        // String nowUpdTime =
        // kitchenService.fetchAllKitchenLastUpdTime(cookbookTypeIds, searchKey,
        // pageModel, userId);
        // if (!ControllerUtils.checkHasNewData(request, jsonResponse,
        // nowUpdTime)) {
        // return jsonResponse;
        // }

        List<KitchenEntity> kitchenEntityList = kitchenService.getCookbookListRandom(cityName, cookbookTypeIds, userId, count);
        // 3.结果处理

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (KitchenEntity tmpKitchenEntity : kitchenEntityList) {
            Map<String, Object> tmpMap = commonKitchenService.cookbookInfo2Map(tmpKitchenEntity, tmpKitchenEntity.getTotalLikeEatCount(), null, tmpKitchenEntity.getIsLikeEat(), null,
                    tmpKitchenEntity.getCollectFlag());
            resList.add(tmpMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    @RequestMapping("/qryRecommend.json")
    @ResponseBody
    public JsonResponse qryRecommend(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String cityName = fetchCityNamePosition(request);
        String ignoreIdsStr = request.getParameter("ignoreIds");
        List<BigInteger> ignoreCoolbookIds = null;
        if (!StringUtils.isEmpty(ignoreIdsStr)) {
            ignoreCoolbookIds = JSON.parseArray(ignoreIdsStr, BigInteger.class);
        }
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        // KitchenEntity kitchenEntity = kitchenService.getRecommend(cityId,
        // userId);
        KitchenEntity kitchenEntity = kitchenService.getRecommend(cityName, userId, ignoreCoolbookIds);
        // 3.结果处理
        Map<String, Object> resMap = commonKitchenService.cookbookInfo2Map(kitchenEntity, kitchenEntity.getTotalLikeEatCount(), kitchenEntity.getKitchenDetailList(), kitchenEntity.getIsLikeEat(),
                kitchenEntity.getKitchenCookbookStepList(), kitchenEntity.getCollectFlag());
        {// 日期信息
            String nowTime = dualDao.getNowTime();
            Long nowTimeLong = DateUtil.timeToLong(nowTime);
            Calendar today = Calendar.getInstance();
            today.setTime(new Date(nowTimeLong));
            Lunar lunar = new Lunar(today);
            resMap.put("currTime01", lunar.toStringMonthAndDay());
            resMap.put("currTime02", DateUtil.chineseMonthDay.get().format(today.getTime()));
            resMap.put("currSeason", DateUtil.getSeasion(today));
        }
        jsonResponse.setDataValue(resMap);
        return jsonResponse;
    }

    /**
     * 查询今日推荐的n个菜
     * 
     * @param request
     * @deprecated useless after ver.310
     * @return
     */
    @RequestMapping("/qryCookbookRecommendByCount.json")
    @ResponseBody
    @Deprecated
    public JsonResponse qryCookbookRecommendFor4(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String ignoreIdsStr = request.getParameter("ignoreIdsStr");
        Integer count = Integer.valueOf(request.getParameter("count"));
        List<BigInteger> ignoreCoolbookIds = null;
        if (!StringUtils.isEmpty(ignoreIdsStr)) {
            ignoreCoolbookIds = JSON.parseArray(ignoreIdsStr, BigInteger.class);
        }
        String cityName = fetchCityNamePosition(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        List<KitchenEntity> kitchenEntitylist = kitchenService.getCookbookRecommendByCount(cityName, ignoreCoolbookIds, count, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (KitchenEntity tmpKitchenEntity : kitchenEntitylist) {
            Map<String, Object> resMap = commonKitchenService.cookbookInfo2Map(tmpKitchenEntity, tmpKitchenEntity.getTotalLikeEatCount(), tmpKitchenEntity.getKitchenDetailList(),
                    tmpKitchenEntity.getIsLikeEat(), tmpKitchenEntity.getKitchenCookbookStepList(), tmpKitchenEntity.getCollectFlag());
            resList.add(resMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 灶神推荐1个菜
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryGodRecommend.json")
    @ResponseBody
    public JsonResponse qryGodRecommend(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String ignoreIdsStr = request.getParameter("ignoreIdsStr");
        List<BigInteger> ignoreCoolbookIds = null;
        if (!StringUtils.isEmpty(ignoreIdsStr)) {
            ignoreCoolbookIds = JSON.parseArray(ignoreIdsStr, BigInteger.class);
        }
        BigInteger cityId = fetchCityId(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        KitchenEntity tmpKitchenEntity = kitchenService.getGodRecommend(cityId, ignoreCoolbookIds, userId);
        // 3.结果处理
        if (tmpKitchenEntity != null) {
            Map<String, Object> resMap = commonKitchenService.cookbookInfo2Map(tmpKitchenEntity, tmpKitchenEntity.getTotalLikeEatCount(), tmpKitchenEntity.getKitchenDetailList(),
                    tmpKitchenEntity.getIsLikeEat(), tmpKitchenEntity.getKitchenCookbookStepList(), tmpKitchenEntity.getCollectFlag());
            jsonResponse.setDataValue(resMap);
        }
        return jsonResponse;
    }

    /**
     * 查询某个菜谱可搭配的菜
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryMixCookbookListByCookbookId.json")
    @ResponseBody
    public JsonResponse qryCookbookDetailWithMix(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cookbookId = new BigInteger(request.getParameter("cookbookId"));
        BigInteger cityId = fetchCityId(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        List<KitchenEntity> kitchenEntityList = kitchenService.getMixCookbookListByCookbookId(cityId, cookbookId, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (KitchenEntity tmpKitchenEntity : kitchenEntityList) {
            if (tmpKitchenEntity.getId().compareTo(cookbookId) == 0) {
                continue;// 移除当前的菜
            }
            Map<String, Object> tmpMap = commonKitchenService.cookbookInfo2Map(tmpKitchenEntity, tmpKitchenEntity.getTotalLikeEatCount(), tmpKitchenEntity.getKitchenDetailList(),
                    tmpKitchenEntity.getIsLikeEat(), tmpKitchenEntity.getKitchenCookbookStepList(), tmpKitchenEntity.getCollectFlag());
            resList.add(tmpMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 查询用户收藏的菜谱类别列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryCollectCookbookTypeList.json")
    @ResponseBody
    public JsonResponse qryCollectCookbookTypeList(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cityId = fetchCityId(request);
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        List<KitchenCookbookTypeEntity> typeList = null;
        if (userId != null) {
            typeList = kitchenService.getCollectCookbookTypeList(cityId, userId);
        }

        // syl-del 默认不给用户收藏菜谱类别
        // if(typeList==null||typeList.size()<=0){//userId 为空则查询默认的菜谱类别
        // typeList =
        // kitchenService.getDefaultCollectCookbookTypeList(cityId,userId);
        // }

        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        if (typeList != null) {
            for (KitchenCookbookTypeEntity kitchenCookbookType : typeList) {
                Map<String, Object> tmpMap = commonKitchenService.cookbookTypeInfo2Map(kitchenCookbookType, kitchenCookbookType.getKitchenCookbookTopType(), kitchenCookbookType.getCollectFlag());
                resList.add(tmpMap);
            }
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 将指定的多个菜谱类别设置为当前收藏
     * 
     * @param request
     * @return
     */
    @RequestMapping("/submitAllCollectCookbookTypes.json")
    @ResponseBody
    public JsonResponse submitAllCollectCookbookTypes(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String collectTypeIdsStr = request.getParameter("collectTypeIds");
        List<BigInteger> collectTypeIds = null;
        if (!StringUtils.isEmpty(collectTypeIdsStr)) {
            collectTypeIds = JSON.parseArray(collectTypeIdsStr, BigInteger.class);
        }
        BigInteger cityId = fetchCityId(request);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        List<KitchenCookbookTypeEntity> typeList = kitchenService.submitAllCollectCookbookTypes(cityId, collectTypeIds, userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (KitchenCookbookTypeEntity kitchenCookbookType : typeList) {
            Map<String, Object> tmpMap = commonKitchenService.cookbookTypeInfo2Map(kitchenCookbookType, kitchenCookbookType.getKitchenCookbookTopType(), kitchenCookbookType.getCollectFlag());
            resList.add(tmpMap);
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 添加收藏某个菜谱类别
     * 
     * @param request
     * @return
     */
    @RequestMapping("/doCollectCookbookType.json")
    @ResponseBody
    public JsonResponse doCollectCookbookType(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cookbookTypeId = new BigInteger(request.getParameter("cookbookTypeId"));
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        kitchenService.doCollectCookbookType(cookbookTypeId, userId);
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 取消收藏某个菜谱类别
     * 
     * @param request
     * @return
     */
    @RequestMapping("/cancelCollectCookbookType.json")
    @ResponseBody
    public JsonResponse cancelCollectCookbookType(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cookbookTypeId = new BigInteger(request.getParameter("cookbookTypeId"));
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        kitchenService.cancelCollectCookbookType(cookbookTypeId, userId);
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 查询当天最近一个收藏的菜谱
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryLastCollectCookbookCurrDay.json")
    @ResponseBody
    public JsonResponse qryLastCollectCookbookCurrDay(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdBigInteger();
        // 2.交互
        KitchenCookbookCollectEntity kitchenCookbookCollectEntity = null;
        if (userId != null) {
            kitchenCookbookCollectEntity = kitchenService.getLastCollectCookbookCurrDay(userId);
        }
        // if(kitchenEntity==null){//userId 为空或者收藏的菜谱返回为空，则使用默认的菜谱展示
        // kitchenEntity =
        // kitchenService.getDefaultCollectCookbookCurrDay(userId);
        // }
        // 3.结果处理
        Map<String, Object> resMap = null;
        if (kitchenCookbookCollectEntity != null) {
            resMap = commonKitchenService.cookbookCollect2Map(kitchenCookbookCollectEntity, false);
        } else {
            KitchenCookbook kitchenCookbook = new KitchenCookbook();
            String defaultKitchenCookbookPic = sysParamManager.getSysParaValue(SysParamKey.DEFAULT_KITCHEN_COOKBOOK_PIC);
            // kitchenCookbook.setPicUrl(KitchenConstant.DEFAULT_kitchen_cookbook_PIC);
            kitchenCookbook.setPicUrl(defaultKitchenCookbookPic);
            resMap = commonKitchenService.cookbookInfo2Map(kitchenCookbook, null, null, null, null, null, true);
        }
        {// 日期信息
            String nowTime = dualDao.getNowTime();
            Long nowTimeLong = DateUtil.timeToLong(nowTime);
            Calendar today = Calendar.getInstance();
            today.setTime(new Date(nowTimeLong));
            Lunar lunar = new Lunar(today);
            resMap.put("currTime01", lunar.toStringMonthAndDay());
            resMap.put("currTime02", DateUtil.chineseMonthDay.get().format(today.getTime()));
            resMap.put("currSeason", DateUtil.getSeasion(today));
        }
        jsonResponse.setDataValue(resMap);
        return jsonResponse;
    }

    /**
     * 查询用户收藏的今日菜单列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryCollectCookbookListCurrDay.json")
    @ResponseBody
    public JsonResponse qryCollectCookbookListCurrDay(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        List<KitchenCookbookCollectEntity> collectEntityList = kitchenService.qryKitchenCookbookCollectListToday(userId);
        // 3.结果处理
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        if (collectEntityList != null && collectEntityList.size() > 0) {
            for (KitchenCookbookCollectEntity tmpCookbookCollectEntity : collectEntityList) {
                Map<String, Object> tmpMap = commonKitchenService.cookbookCollect2Map(tmpCookbookCollectEntity, null);
                resList.add(tmpMap);
            }
        }
        return ControllerUtils.addPageInfo(jsonResponse, resList);
        // List<Map<String,Object>> userList = new
        // ArrayList<Map<String,Object>>();
        // {
        // Map<String,Object> userMap = new HashMap<String, Object>();
        // userMap.put("userId", 34);
        // userMap.put("imgProfile",
        // "http://192.168.1.31:8080/userProfilePic/400052014-07-15_09_54_21.jpg");
        // userList.add(userMap);
        // }
        // {
        // Map<String,Object> userMap = new HashMap<String, Object>();
        // userMap.put("userId", 35);
        // userMap.put("imgProfile",
        // "http://192.168.1.31:8080/userProfilePic/400052014-07-15_09_54_21.jpg");
        // userList.add(userMap);
        // }
        // List<Map<String,Object>> resList = new
        // ArrayList<Map<String,Object>>();
        // {
        // Map<String,Object> tmpMap = new HashMap<String, Object>();
        // tmpMap.put("id", 201);
        // tmpMap.put("name", "凉瓜黄豆龙骨汤");
        // tmpMap.put("picUrl",
        // "http://202.170.133.211:8080/kitchenCookBookPic/201_liangguahuangdoulonggutang.jpg?2014-09-30_23_50_12");
        // tmpMap.put("desc", null);
        // tmpMap.put("eatWeight", "3人");
        // tmpMap.put("cookTime", "2小时");
        // tmpMap.put("taste", "咸鲜");
        // tmpMap.put("cookTech", "煲");
        // tmpMap.put("tips", null);
        // tmpMap.put("createTime", "2014-08-02");
        // tmpMap.put("ext_fml_isLikeEat", true);
        // tmpMap.put("ext_fml_totalLikeEatCount", 2);
        // {
        // tmpMap.put("ext_fml_isLikeEat_userList",userList);
        // }
        // resList.add(tmpMap);
        // }
        // {
        // Map<String,Object> tmpMap = new HashMap<String, Object>();
        // tmpMap.put("id", 202);
        // tmpMap.put("name", "凉瓜黄豆龙骨汤2");
        // tmpMap.put("picUrl",
        // "http://202.170.133.211:8080/kitchenCookBookPic/201_liangguahuangdoulonggutang.jpg?2014-09-30_23_50_12");
        // tmpMap.put("desc", null);
        // tmpMap.put("eatWeight", "3人");
        // tmpMap.put("cookTime", "2小时");
        // tmpMap.put("taste", "咸鲜");
        // tmpMap.put("cookTech", "煲");
        // tmpMap.put("tips", null);
        // tmpMap.put("createTime", "2014-08-02");
        // tmpMap.put("ext_fml_isLikeEat", true);
        // tmpMap.put("ext_fml_totalLikeEatCount", 2);
        // {
        // tmpMap.put("ext_fml_isLikeEat_userList",userList);
        // }
        // resList.add(tmpMap);
        // }
        // return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 家庭用户喜欢吃指定的菜谱
     * 
     * @param request
     * @return
     */
    @RequestMapping("/doLikeEatCookbookCollect.json")
    @ResponseBody
    public JsonResponse doLikeEatCookbookCollect(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String cookCollectIdStr = request.getParameter("cookbookCollectId");
        BigInteger cookCollectId = new BigInteger(cookCollectIdStr);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        kitchenService.doLikeEatCookbookCollect(cookCollectId, userId);
        KitchenCookbookCollectEntity cookbookCollectEntity = kitchenService.qryKitchenCookbookCollectDetailById(cookCollectId, userId);
        // 3.结果处理
        Map<String, Object> resMap = commonKitchenService.cookbookCollect2Map(cookbookCollectEntity, null);
        jsonResponse.setDataValue(resMap);
        return jsonResponse;

        // List<Map<String,Object>> userList = new
        // ArrayList<Map<String,Object>>();
        // {
        // Map<String,Object> userMap = new HashMap<String, Object>();
        // userMap.put("userId", 34);
        // userMap.put("imgProfile",
        // "http://192.168.1.31:8080/userProfilePic/400052014-07-15_09_54_21.jpg");
        // userList.add(userMap);
        // }
        // {
        // Map<String,Object> userMap = new HashMap<String, Object>();
        // userMap.put("userId", 35);
        // userMap.put("imgProfile",
        // "http://192.168.1.31:8080/userProfilePic/400052014-07-15_09_54_21.jpg");
        // userList.add(userMap);
        // }
        // {
        // Map<String,Object> tmpMap = new HashMap<String, Object>();
        // tmpMap.put("id", 202);
        // tmpMap.put("name", "凉瓜黄豆龙骨汤2");
        // tmpMap.put("picUrl",
        // "http://202.170.133.211:8080/kitchenCookBookPic/201_liangguahuangdoulonggutang.jpg?2014-09-30_23_50_12");
        // tmpMap.put("desc", null);
        // tmpMap.put("eatWeight", "3人");
        // tmpMap.put("cookTime", "2小时");
        // tmpMap.put("taste", "咸鲜");
        // tmpMap.put("cookTech", "煲");
        // tmpMap.put("tips", null);
        // tmpMap.put("createTime", "2014-08-02");
        // tmpMap.put("ext_fml_isLikeEat", true);
        // tmpMap.put("ext_fml_totalLikeEatCount", 2);
        // {
        // tmpMap.put("ext_fml_isLikeEat_userList",userList);
        // }
        // jsonResponse.setDataValue(tmpMap);
        // }
        // return jsonResponse;
    }

    /**
     * 查询用户收藏的本周菜单列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryCollectCookbookListCurrWeek.json")
    @ResponseBody
    public JsonResponse qryCollectCookbookListCurrWeek(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        List<KitchenCookbookCollectEntity> kitchenCookbookCollectEntityList = kitchenService.getCollectCookbookListCurrWeek(userId);
        String nowTime = dualDao.getNowTime();
        Long nowTimeDay = DateUtil.timeToLong(nowTime, DateUtil.formatDay.get());
        // 3.结果处理
        // 按日期分组<日期，收藏的List>
        List<Map<String, Object>> resList = kitchenCookbookCollectEntityListGroupByDay(kitchenCookbookCollectEntityList, nowTimeDay);
        return ControllerUtils.addPageInfo(jsonResponse, resList);
    }

    /**
     * 查询用户收藏的历史菜单列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/qryCollectCookbookListHistory.json")
    @ResponseBody
    public JsonResponse qryCollectCookbookListHistory(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        // 分页查询，按日期的天数
        PageModel pageModel = ControllerUtils.getPageModel(request);
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        List<KitchenCookbookCollectEntity> kitchenCookbookCollectEntityList = kitchenService.getCollectCookbookListHistory(userId, pageModel);
        // 3.结果处理
        // 按日期分组<日期，收藏的List>
        List<Map<String, Object>> resList = kitchenCookbookCollectEntityListGroupByDay(kitchenCookbookCollectEntityList, null);
        return ControllerUtils.addPageInfo(jsonResponse, resList, pageModel.isLast, pageModel.count);
    }

    /**
     * 添加收藏指定的菜谱
     * 
     * @param request
     * @deprecated useless after ver.310, Use
     *             {@link #doCollectTodayCookbook(HttpServletRequest request)}
     *             instead
     * @return
     */
    @RequestMapping("/doCollectCookbook.json")
    @ResponseBody
    @Deprecated
    public JsonResponse doCollectCookbook(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cookbookId = new BigInteger(request.getParameter("cookbookId"));
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        kitchenService.doCollectCookbook(cookbookId, userId);
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * @author wangzhe
     * @date 2015年8月28日
     * @description 添加收藏当天指定的菜谱们
     *
     * @param request
     * @return 空 response
     */
    @RequestMapping("/doCollectTodayCookbook.json")
    @ResponseBody
    public JsonResponse doCollectTodayCookbook(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String cookbookIds = request.getParameter("cookbookId");
        List<BigInteger> cbList = null;
        if (!StringUtils.isEmpty(cookbookIds)) {
            cbList = JSON.parseArray(cookbookIds, BigInteger.class);
        }
        String comment = request.getParameter("comment");

        // @的用户列表
        Set<BigInteger> atUserIdSet = null;
        String userIdListStr = request.getParameter("userIdList");
        if (!StringUtils.isEmpty(userIdListStr)) {
            List<BigInteger> tmpList = JSON.parseArray(userIdListStr, BigInteger.class);
            if (tmpList != null && tmpList.size() > 0) {
                atUserIdSet = new HashSet<BigInteger>();
                atUserIdSet.addAll(tmpList);
            }
        }

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        kitchenService.doCollectTodayCookbook(cbList, comment, atUserIdSet, userId);
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 取消收藏指定的菜谱
     * 
     * @param request
     * @return
     */
    @RequestMapping("/cancelCollectCookbook.json")
    @ResponseBody
    public JsonResponse cancelCollectCookbook(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        BigInteger cookbookId = new BigInteger(request.getParameter("cookbookId"));
        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        // 2.交互
        kitchenService.cancelCollectCookbook(cookbookId, userId);
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 批量取消收藏指定的菜谱
     * 
     * @param request
     * @return
     */
    @RequestMapping("/cancelMultiCollectCookbook.json")
    @ResponseBody
    public JsonResponse cancelMultiCollectCookbook(HttpServletRequest request) {
        JsonResponse jsonResponse = new JsonResponse();
        // 1.搜集参数
        String cbStr = request.getParameter("cookbookIds");
        if (!StringUtils.isEmpty(cbStr)) {
            List<BigInteger> cookBookIds = JSON.parseArray(cbStr, BigInteger.class);
            if (null != cookBookIds && 0 < cookBookIds.size()) {
                // 2.交互
                kitchenService.cancelMultiCollectCookbook(cookBookIds, UserContext.getOperIdMustExistBigInteger());
            } else {
            }
        }
        // 3.结果处理
        return jsonResponse;
    }

    /**
     * @author wangzhe
     * @date 2015年10月27日
     * @description 查询饮食分析
     *
     * @return 饮食分析结果
     */
    @RequestMapping("/qryDietAnalysis.json")
    @ResponseBody
    public JsonResponse qryDietAnalysis() {
        JsonResponse jsonResponse = new JsonResponse();

        BigInteger userId = UserContext.getOperIdMustExistBigInteger();
        if (null != userId) {
            DietAnalysisEntity dae = kitchenService.qryDietAnalysis(userId);
            if (null != dae) {
                jsonResponse.setDataValue(dae.genResult());
            } else {
            }
        } else {
        }

        // Map<String, Integer> result = new HashMap<String, Integer>();
        //
        // result.put(KitchenDict.Kitchen_Cookbook_DietAnalysis.bean, 5);
        // result.put(KitchenDict.Kitchen_Cookbook_DietAnalysis.fat, 4);
        // result.put(KitchenDict.Kitchen_Cookbook_DietAnalysis.grain, 3);
        // result.put(KitchenDict.Kitchen_Cookbook_DietAnalysis.meat, 2);
        // result.put(KitchenDict.Kitchen_Cookbook_DietAnalysis.veg, 1);

        // jsonResponse.setDataValue(result);

        // 3.结果处理
        return jsonResponse;
    }

    /**
     * 获取当前所属城市Id
     * 
     * @param request
     * @return
     */
    private BigInteger fetchCityId(HttpServletRequest request) {
        fetchCityNamePosition(request);// 校验客户端需要传入cityName
        BigInteger cityId = null;
        cityId = RoomConstants.DEFAULT_ADDRESS_CITY_ID;// TODO 目前均使用默认城市
        return cityId;
    }

    /**
     * 获取当前所属城市名称
     * 
     * @param request
     * @return
     */
    private String fetchCityNamePosition(HttpServletRequest request) {
        String cityName = request.getParameter("cityName");
        // syl-del 2014-12-10 19:11:15
        // if(StringUtils.isEmpty(cityName)){
        // throw new
        // BusinessRuntimeException("kitchen.fetchCityId.cityName.empty");
        // }
        return cityName;
    }

    /**
     * 将收藏的菜谱列表按日期分组
     * 
     * @param kitchenCookbookCollectEntityList
     * @return
     */
    private List<Map<String, Object>> kitchenCookbookCollectEntityListGroupByDay(List<KitchenCookbookCollectEntity> kitchenCookbookCollectEntityList, Long nowTimeDay) {
        // 按日期分组<日期，收藏的List>
        Map<String, List<KitchenCookbookCollectEntity>> aaMap = new LinkedHashMap<String, List<KitchenCookbookCollectEntity>>();
        for (KitchenCookbookCollectEntity tmpKitchenCookbookCollectEntity : kitchenCookbookCollectEntityList) {
            String time = DateUtil.strFormate(tmpKitchenCookbookCollectEntity.getTime(), DateUtil.formatSecond.get(), DateUtil.formatDay.get());
            List<KitchenCookbookCollectEntity> tmpList = aaMap.get(time);
            if (tmpList != null) {
                tmpList.add(tmpKitchenCookbookCollectEntity);
            } else {
                tmpList = new ArrayList<KitchenCookbookCollectEntity>();
                tmpList.add(tmpKitchenCookbookCollectEntity);
                aaMap.put(time, tmpList);
            }
        }
        // 转Map
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (String time : aaMap.keySet()) {
            Map<String, Object> resMap = new LinkedHashMap<String, Object>();
            List<KitchenCookbookCollectEntity> kitchenEntityList = aaMap.get(time);
            resMap.put("time", time);
            if (nowTimeDay != null && !StringUtils.isEmpty(time)) {// 是否为当天
                Long currTimeDayLong = DateUtil.timeToLong(time, DateUtil.formatDay.get());
                if (currTimeDayLong.compareTo(nowTimeDay) == 0) {
                    resMap.put("isToday", true);
                } else {
                    resMap.put("isToday", false);
                }
            }
            {// 星期几
                String weekDay = DateUtil.getWeekOfDate(time, DateUtil.formatDay.get());
                resMap.put("weekDay", weekDay);
            }
            List<Map<String, Object>> tmpkitchenEntityList = new ArrayList<Map<String, Object>>();
            for (KitchenCookbookCollectEntity tmpKitchenEntityCollect : kitchenEntityList) {
                Map<String, Object> signalMap = commonKitchenService.cookbookCollect2Map(tmpKitchenEntityCollect, null);
                tmpkitchenEntityList.add(signalMap);
            }
            resMap.put("kitchenList", tmpkitchenEntityList);
            resList.add(resMap);
        }
        return resList;
    }

}
