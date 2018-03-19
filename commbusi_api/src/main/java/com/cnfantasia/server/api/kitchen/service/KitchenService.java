/**   
* Filename:    KitchenService.java   
* @version:    1.0  
* Create at:   2014年7月28日 上午9:59:06   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年7月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.kitchen.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.FutureTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commonBusiness.entity.AddAndDelIdsEntity;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.service.ICommonRoomService;
import com.cnfantasia.server.api.commonBusiness.service.ICommonUserService;
import com.cnfantasia.server.api.commonBusiness.util.IdsAddDelCheckUtil;
import com.cnfantasia.server.api.familyMsg.constant.FamilyMsgDict;
import com.cnfantasia.server.api.familyMsg.entity.MsgExtDataAdd;
import com.cnfantasia.server.api.familyMsg.service.IFamilyMsgService;
import com.cnfantasia.server.api.kitchen.constant.KitchenConstant;
import com.cnfantasia.server.api.kitchen.dao.IKitchenDao;
import com.cnfantasia.server.api.kitchen.entity.DietAnalysisEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookCollectEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenCookbookTypeEntity;
import com.cnfantasia.server.api.kitchen.entity.KitchenEntity;
import com.cnfantasia.server.api.login.constant.LoginDict;
import com.cnfantasia.server.api.msgpush.constant.MsgpushDict;
import com.cnfantasia.server.api.msgpush.service.IMsgpushService;
import com.cnfantasia.server.api.notice.constant.NoticeDict;
import com.cnfantasia.server.api.redpoint.callable.RedpointAddRunnableForFamily;
import com.cnfantasia.server.api.redpoint.constant.RedpointConstant;
import com.cnfantasia.server.api.redpoint.constant.RedpointDict;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.service.IRedpointService;
import com.cnfantasia.server.api.room.constant.RoomConstants;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.api.user.entity.UserSimpleEntity;
import com.cnfantasia.server.business.pub.dao.IDualDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
import com.cnfantasia.server.domainbase.kitchenCookbookCollect.dao.IKitchenCookbookCollectBaseDao;
import com.cnfantasia.server.domainbase.kitchenCookbookCollect.entity.KitchenCookbookCollect;
import com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.dao.IKitchenCookbookCollectIslikeBaseDao;
import com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.entity.KitchenCookbookCollectIslike;
import com.cnfantasia.server.domainbase.kitchenCookbookMix.entity.KitchenCookbookMix;
import com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.dao.IKitchenCookbookTypeCollectBaseDao;
import com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.entity.KitchenCookbookTypeCollect;
import com.cnfantasia.server.domainbase.message.entity.Message;
import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;
import com.cnfantasia.server.domainbase.user.dao.IUserBaseDao;
import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * Filename: KitchenService.java
 * 
 * @version: 1.0.0 Create at: 2014年7月28日 上午9:59:06 Description:
 *
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年7月28日 shiyl 1.0 1.0 Version
 */
public class KitchenService implements IKitchenService {
    private Log logger = LogFactory.getLog(getClass());

    private IMsgpushService msgpushService;

    public void setMsgpushService(IMsgpushService msgpushService) {
        this.msgpushService = msgpushService;
    }

    private ICommonUserService commonUserService;

    public void setCommonUserService(ICommonUserService commonUserService) {
        this.commonUserService = commonUserService;
    }

    private IKitchenDao kitchenDao;

    public void setKitchenDao(IKitchenDao kitchenDao) {
        this.kitchenDao = kitchenDao;
    }

    private IKitchenCookbookTypeCollectBaseDao kitchenCookbookTypeCollectBaseDao;

    public void setKitchenCookbookTypeCollectBaseDao(IKitchenCookbookTypeCollectBaseDao kitchenCookbookTypeCollectBaseDao) {
        this.kitchenCookbookTypeCollectBaseDao = kitchenCookbookTypeCollectBaseDao;
    }

    private IKitchenCookbookCollectBaseDao kitchenCookbookCollectBaseDao;

    public void setKitchenCookbookCollectBaseDao(IKitchenCookbookCollectBaseDao kitchenCookbookCollectBaseDao) {
        this.kitchenCookbookCollectBaseDao = kitchenCookbookCollectBaseDao;
    }

    private IDualDao dualDao;

    public void setDualDao(IDualDao dualDao) {
        this.dualDao = dualDao;
    }

    private IUuidManager uuidManager;

    public void setUuidManager(IUuidManager uuidManager) {
        this.uuidManager = uuidManager;
    }

    private ICommonRoomService commonRoomService;

    public void setCommonRoomService(ICommonRoomService commonRoomService) {
        this.commonRoomService = commonRoomService;
    }

    private IUserBaseDao userBaseDao;

    public void setUserBaseDao(IUserBaseDao userBaseDao) {
        this.userBaseDao = userBaseDao;
    }

    private IFamilyMsgService familyMsgService;

    public void setFamilyMsgService(IFamilyMsgService familyMsgService) {
        this.familyMsgService = familyMsgService;
    }

    private IKitchenCookbookCollectIslikeBaseDao kitchenCookbookCollectIslikeBaseDao;

    public void setKitchenCookbookCollectIslikeBaseDao(IKitchenCookbookCollectIslikeBaseDao kitchenCookbookCollectIslikeBaseDao) {
        this.kitchenCookbookCollectIslikeBaseDao = kitchenCookbookCollectIslikeBaseDao;
    }

    private IRedpointService redpointService;

    public void setRedpointService(IRedpointService redpointService) {
        this.redpointService = redpointService;
    }

    @Override
    public KitchenEntity getCookbookDetail(BigInteger cookbookId, BigInteger userId) {
        return kitchenDao.selectKitchenCookbookDetailById(cookbookId, userId);
    }

    // @Override
    // public KitchenEntity getRecommend(BigInteger cityId,BigInteger userId) {
    // return kitchenDao.selectKitchenCookbookRecommend(cityId,userId);
    // }

    @Override
    public KitchenEntity getRecommend(String cityName, BigInteger userId, List<BigInteger> ignoreCoolbookIds) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        // RoomEntityWithValidate roomEntityWithValidate= null;
        // try {
        // if(userId!=null){
        // roomEntityWithValidate =
        // commonRoomService.getDefaultRoomInfo(userId);
        // }
        // if(roomEntityWithValidate!=null){
        // cityId =
        // roomEntityWithValidate.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity().gettCityFId();
        // }
        // } catch (Exception e) {
        // logger.error("KitchenService getDefaultRoomInfo by userId cause
        // exception,cityName is :"+cityName,e);
        // }
        // if(roomEntityWithValidate==null){
        // if(!StringUtils.isEmpty(cityName)){
        // List<AddressCity> addressCityList =
        // commonRoomService.getAddressCityListByCityNameDim(cityName);
        // if(addressCityList!=null&&addressCityList.size()>0){
        // cityId = addressCityList.get(0).getId();
        // }
        // }
        // }
        // if(cityId==null){
        // cityId = RoomConstants.DEFAULT_ADDRESS_CITY_ID;
        // }
        KitchenEntity resKitchenEntity = kitchenDao.selectKitchenCookbookRecommend(cityId, userId, ignoreCoolbookIds);
        if (resKitchenEntity == null) {
            resKitchenEntity = kitchenDao.selectKitchenCookbookRecommend(RoomConstants.DEFAULT_ADDRESS_CITY_ID, userId, ignoreCoolbookIds);
        }
        return resKitchenEntity;
    }

    @Override
    public List<KitchenCookbookTypeEntity> getCookbookTypeList(BigInteger cityId, PageModel pageModel, BigInteger userId) {
        return kitchenDao.selectCookbookTypeList(cityId, pageModel, userId);
    }

    @Override
    public List<KitchenCookbookTypeEntity> getCookbookTypeList(String cityName, PageModel pageModel, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return getCookbookTypeList(cityId, pageModel, userId);
    }

    @Override
    public List<KitchenEntity> getCookbookList(String cityName, List<Integer> cookbookTypeIds, String searchKey, PageModel pageModel, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return kitchenDao.selectCookbookList(cityId, cookbookTypeIds, searchKey, pageModel, userId);
    }

    public List<KitchenEntity> getCookbookListRandom(String cityName, List<Integer> cookbookTypeIds, BigInteger userId, Integer count) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return kitchenDao.selectCookbookListRandom(cityId, cookbookTypeIds, userId, count);
    }

    @Override
    public List<KitchenEntity> getMixCookbookListByCookbookId(BigInteger cityId, BigInteger cookbookId, BigInteger userId) {
        List<KitchenEntity> resKitchenEntityList = new ArrayList<KitchenEntity>();
        // 查询包含对应菜谱的组合菜列表
        List<KitchenCookbookMix> kitchenCookbookMixList = kitchenDao.selectKitchenCookbookMixByCookbookId(cookbookId);
        if (kitchenCookbookMixList != null && kitchenCookbookMixList.size() > 0) {
            // 随机获取一个
            int randomIndex = (int) (kitchenCookbookMixList.size() * Math.random() + 0);
            // 查询对应的组合菜列表
            KitchenCookbookMix currMix = kitchenCookbookMixList.get(randomIndex);
            resKitchenEntityList = kitchenDao.selectCookbookListByMixId(currMix.getId());
        } else {// 使用随机方式生成
            List<BigInteger> ignoreCoolbookIds = new ArrayList<BigInteger>();
            ignoreCoolbookIds.add(cookbookId);
            resKitchenEntityList = getCookbookRecommendByCount(cityId, ignoreCoolbookIds, KitchenConstant.DEFAULT_MIX_COUNT, userId);
        }
        return resKitchenEntityList;
    }

    @Override
    public List<KitchenEntity> getMixCookbookListByCookbookId(String cityName, BigInteger cookbookId, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return getMixCookbookListByCookbookId(cityId, cookbookId, userId);
    }

    @Override
    public List<KitchenEntity> getCookbookRecommendByCount(BigInteger cityId, List<BigInteger> ignoreCoolbookIds, Integer count, BigInteger userId) {
        List<KitchenEntity> tmpList = kitchenDao.selectCookbookRecommendByCount(cityId, ignoreCoolbookIds, count, userId);
        if (tmpList == null || tmpList.size() <= 0) {
            tmpList = kitchenDao.selectCookbookRecommendByCount(RoomConstants.DEFAULT_ADDRESS_CITY_ID, ignoreCoolbookIds, count, userId);
        }
        return tmpList;
    }

    @Override
    public List<KitchenEntity> getCookbookRecommendByCount(String cityName, List<BigInteger> ignoreCoolbookIds, Integer count, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return getCookbookRecommendByCount(cityId, ignoreCoolbookIds, count, userId);
    }

    @Override
    public KitchenEntity getGodRecommend(BigInteger cityId, List<BigInteger> ignoreCoolbookIds, BigInteger userId) {
        return kitchenDao.selectGodRecommend(cityId, ignoreCoolbookIds, userId);
    }

    @Override
    public KitchenEntity getGodRecommend(String cityName, List<BigInteger> ignoreCoolbookIds, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return getGodRecommend(cityId, ignoreCoolbookIds, userId);
    }

    @Override
    public KitchenCookbookCollectEntity getLastCollectCookbookCurrDay(BigInteger userId) {
        return kitchenDao.selectLastCollectCookbookCurrDay(userId);
    }

    // @Override
    // public KitchenEntity getDefaultCollectCookbookCurrDay(BigInteger userId)
    // {
    // return getCookbookDetail(KitchenConstant.DEFAULT_kitchen_cookbook_ID,
    // userId);
    // }

    @Override
    public List<KitchenCookbookTypeEntity> getCollectCookbookTypeList(BigInteger cityId, BigInteger userId) {
        List<KitchenCookbookTypeEntity> tmpList = kitchenDao.selectCollectCookbookTypeList(cityId, userId);
        return tmpList;
    }

    @Override
    public List<KitchenCookbookTypeEntity> getCollectCookbookTypeList(String cityName, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return getCollectCookbookTypeList(cityId, userId);
    }

    // @Override
    // public List<KitchenCookbookTypeEntity>
    // getDefaultCollectCookbookTypeList(BigInteger cityId,BigInteger userId) {
    // PageModel pageModel = new PageModel(0,
    // KitchenConstant.DEFAULT_COOKBOOK_TYPE_COUNT);
    // List<KitchenCookbookTypeEntity> tmpList = getCookbookTypeList(cityId,
    // pageModel,userId);//默认查询收藏类别的个数
    // if(userId!=null&&tmpList!=null&&tmpList.size()>0){//已登录用户默认添加收藏
    // List<BigInteger> collectTypeIds = new ArrayList<BigInteger>();
    // for(KitchenCookbookTypeEntity tmpData:tmpList){
    // collectTypeIds.add(tmpData.getId());
    // }
    // return submitAllCollectCookbookTypes(cityId, collectTypeIds, userId);
    // }else{
    // return tmpList;
    // }
    // }

    @Override
    public List<KitchenCookbookTypeEntity> getDefaultCollectCookbookTypeList(BigInteger cityId, BigInteger userId) {
        // PageModel pageModel = new PageModel(0,
        // KitchenConstant.DEFAULT_COOKBOOK_TYPE_COUNT);//默认查询收藏类别的个数
        List<KitchenCookbookTypeEntity> tmpList = kitchenDao.selectCookbookTypeRecommendList(cityId, userId);
        if (userId != null && tmpList != null && tmpList.size() > 0) {// 已登录用户默认添加收藏
            List<BigInteger> collectTypeIds = new ArrayList<BigInteger>();
            for (KitchenCookbookTypeEntity tmpData : tmpList) {
                collectTypeIds.add(tmpData.getId());
            }
            return submitAllCollectCookbookTypes(cityId, collectTypeIds, userId);
        } else {
            return tmpList;
        }
    }

    @Override
    public List<KitchenCookbookTypeEntity> getDefaultCollectCookbookTypeList(String cityName, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return getDefaultCollectCookbookTypeList(cityId, userId);
    }

    @Override
    public List<KitchenCookbookTypeEntity> submitAllCollectCookbookTypes(BigInteger cityId, List<BigInteger> collectTypeIds, BigInteger userId) {
        // 查询当前已经收藏的
        List<KitchenCookbookTypeEntity> existList = kitchenDao.selectCollectCookbookTypeList(cityId, userId);
        List<BigInteger> existIds = new ArrayList<BigInteger>();
        for (KitchenCookbookTypeEntity tmpKitchenCookbookTypeEntity : existList) {
            existIds.add(tmpKitchenCookbookTypeEntity.getId());
        }
        // 对比当前用户要提交的 筛选出需要新增的和需要删除的
        AddAndDelIdsEntity addAndDelIdsEntity = IdsAddDelCheckUtil.analyze(existIds, collectTypeIds);
        Set<BigInteger> toAddIdsSet = addAndDelIdsEntity.getToAddIds();
        Set<BigInteger> toDelIdsSet = addAndDelIdsEntity.getToDelIds();
        // List<BigInteger> noNeedDoneIds =
        // addAndDelIdsEntity.getNoNeedDoneIds();//要新增，且当前已经存在的，不需要处理
        if (toAddIdsSet.size() > 0) {// 批量新增
            List<KitchenCookbookTypeCollect> batchList = new ArrayList<KitchenCookbookTypeCollect>();
            List<BigInteger> addIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_kitchen_cookbook_type_collect, toAddIdsSet.size());
            String nowTime = dualDao.getNowTime();
            List<BigInteger> toAddIds = new ArrayList<BigInteger>();
            for (BigInteger tmpSetId : toAddIdsSet) {
                toAddIds.add(tmpSetId);
            }
            for (int i = 0; i < toAddIds.size(); i++) {
                BigInteger cookbookTypeId = toAddIds.get(i);
                BigInteger addId = addIdList.get(i);
                KitchenCookbookTypeCollect kitchenCookbookTypeCollect = new KitchenCookbookTypeCollect();
                kitchenCookbookTypeCollect.setId(addId);
                kitchenCookbookTypeCollect.setTime(nowTime);
                kitchenCookbookTypeCollect.settKitchenCookbookTypeFId(cookbookTypeId);
                kitchenCookbookTypeCollect.setUserId(userId);
                batchList.add(kitchenCookbookTypeCollect);
            }
            kitchenCookbookTypeCollectBaseDao.insertKitchenCookbookTypeCollectBatch(batchList);
        }
        if (toDelIdsSet.size() > 0) {// 批量删除
            List<BigInteger> toDelIds = new ArrayList<BigInteger>();
            for (BigInteger tmpSetId : toDelIdsSet) {
                toDelIds.add(tmpSetId);
            }
            kitchenDao.cancelCollectCookbookTypeBatch(userId, toDelIds);
            // kitchenCookbookTypeCollectBaseDao.deleteKitchenCookbookTypeCollectLogicBatch(toDelIds);
        }
        // 查询返回当前用户最新的类别收藏列表
        return getCollectCookbookTypeList(cityId, userId);
    }

    @Override
    public List<KitchenCookbookTypeEntity> submitAllCollectCookbookTypes(String cityName, List<BigInteger> collectTypeIds, BigInteger userId) {
        BigInteger cityId = getCityIdByInfo(cityName, userId);
        return submitAllCollectCookbookTypes(cityId, collectTypeIds, userId);
    }

    @Override
    public void doCollectCookbookType(BigInteger cookbookTypeId, BigInteger userId) {
        // 校验是否已被收藏
        KitchenCookbookTypeCollect kitchenCookbookTypeCollectQry = new KitchenCookbookTypeCollect();
        kitchenCookbookTypeCollectQry.setUserId(userId);
        kitchenCookbookTypeCollectQry.settKitchenCookbookTypeFId(cookbookTypeId);
        int existCount = kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectCount(MapConverter.toMap(kitchenCookbookTypeCollectQry), false);
        if (existCount > 0) {
            throw new BusinessRuntimeException("kitchen.doCollectCookbookType.haveExist");
        }
        // 执行收藏
        {
            BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_kitchen_cookbook_type_collect);
            String nowTime = dualDao.getNowTime();
            KitchenCookbookTypeCollect kitchenCookbookTypeCollect = new KitchenCookbookTypeCollect();
            kitchenCookbookTypeCollect.setId(addId);
            kitchenCookbookTypeCollect.setTime(nowTime);
            kitchenCookbookTypeCollect.settKitchenCookbookTypeFId(cookbookTypeId);
            kitchenCookbookTypeCollect.setUserId(userId);
            int res = kitchenCookbookTypeCollectBaseDao.insertKitchenCookbookTypeCollect(kitchenCookbookTypeCollect);
            if (res <= 0) {
                throw new BusinessRuntimeException("kitchen.doCollectCookbookType.failed");
            }
        }
    }

    @Override
    public void cancelCollectCookbookType(BigInteger cookbookTypeId, BigInteger userId) {
        KitchenCookbookTypeCollect kitchenCookbookTypeCollectQry = new KitchenCookbookTypeCollect();
        kitchenCookbookTypeCollectQry.setUserId(userId);
        kitchenCookbookTypeCollectQry.settKitchenCookbookTypeFId(cookbookTypeId);
        List<KitchenCookbookTypeCollect> currExistList = kitchenCookbookTypeCollectBaseDao.selectKitchenCookbookTypeCollectByCondition(MapConverter.toMap(kitchenCookbookTypeCollectQry), false);
        if (currExistList != null && currExistList.size() > 0) {
            List<BigInteger> delIds = new ArrayList<BigInteger>();
            for (KitchenCookbookTypeCollect tmpKitchenCookbookTypeCollect : currExistList) {
                delIds.add(tmpKitchenCookbookTypeCollect.getId());
            }
            int res = kitchenCookbookTypeCollectBaseDao.deleteKitchenCookbookTypeCollectLogicBatch(delIds);
            if (res <= 0) {
                throw new BusinessRuntimeException("kitchen.cancelCollectCookbookType.failed");
            }
        }
    }

    @Override
    public List<KitchenCookbookCollectEntity> getCollectCookbookListCurrWeek(BigInteger userId) {
        return kitchenDao.selectCollectCookbookListCurrWeek(userId);
    }

    @Override
    public List<KitchenCookbookCollectEntity> getCollectCookbookListHistory(BigInteger userId, PageModel pageModel) {
        List<String> dayTimeList = kitchenDao.selectExistCollectCookbookDayListHistory(userId, pageModel);
        List<KitchenCookbookCollectEntity> resList = null;
        // if(dayTimeList==null||dayTimeList.size()<=0){
        // resList = new ArrayList<KitchenEntity>();
        // }else{
        resList = kitchenDao.selectCollectCookbookListHistory(userId, dayTimeList);
        // }
        return resList;
    }

    @Override
    public void doCollectCookbook(BigInteger cookbookId, BigInteger userId) {
        // 校验cookbookId当天是否已被收藏
        String timeDay = dualDao.getNowDay();
        Integer existCount = kitchenDao.selectCollectCookbookCountByDay(userId, cookbookId, timeDay);
        if (existCount != null && existCount > 0) {
            throw new BusinessRuntimeException("kitchen.doCollectCookbook.haveExist");
        }
        // 执行收藏
        BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_kitchen_cookbook_collect);
        {
            String nowTime = dualDao.getNowTime();
            BigInteger roomId = commonRoomService.getDefaultRoomIdByUserId(userId);
            KitchenCookbookCollect kitchenCookbookCollect = new KitchenCookbookCollect();
            kitchenCookbookCollect.setId(addId);
            kitchenCookbookCollect.setTime(nowTime);
            kitchenCookbookCollect.settKitchenCookbookFId(cookbookId);
            kitchenCookbookCollect.setUserId(userId);
            kitchenCookbookCollect.setRoomId(roomId);
            int res = kitchenCookbookCollectBaseDao.insertKitchenCookbookCollect(kitchenCookbookCollect);
            if (res <= 0) {
                throw new BusinessRuntimeException("kitchen.doCollectCookbook.failed");
            }
        }
        doLikeEatCookbookCollect(addId, userId);// syl-add-2015-1-23 15:32:42
                                                // 默认增加喜欢吃
        {// 添加红点提醒
            List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
            sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.FamilyCookBook_Collect, addId, "INSERT"));
            FutureTask<Boolean> task = new FutureTask<Boolean>(new RedpointAddRunnableForFamily(redpointService, userId, RedpointConstant.RedpointContent_ModelCode.FML_COOKBOOK, sourceList));
            new Thread(task).start();
        }
    }

    @Override
    public void doCollectTodayCookbook(final List<BigInteger> cookbookIdList, String comment, Set<BigInteger> atUserIdSet, final BigInteger userId) {
        // 校验cookbookId当天是否已被收藏
        final String timeDay = dualDao.getNowDay();

        List<BigInteger> redundantKccIds = new ArrayList<BigInteger>();

        // 去掉重复项
        List<KitchenCookbookCollectEntity> collectEntityList = kitchenDao.selectKitchenCookbookCollectListToday(userId);
        Iterator<BigInteger> it = cookbookIdList.iterator();
        while (it.hasNext()) {
            BigInteger bigInteger = it.next();
            for (KitchenCookbookCollectEntity kcce : collectEntityList) {
                if (bigInteger.equals(kcce.gettKitchenCookbookFId())) {
                    redundantKccIds.add(kcce.getId());
                    it.remove();
                    break;
                } else {
                }
            }
        }

        // final Integer existCount =
        // kitchenDao.selectCollectMultipleCookbookCountByDay(userId,
        // cookbookIdList, timeDay);
        // if (existCount != null && existCount > 0) {
        // throw new
        // BusinessRuntimeException("kitchen.doCollectCookbook.haveExist");
        // }
        List<BigInteger> kccIds = null;
        if (0 < cookbookIdList.size()) {
            final KitchenCookbookCollect kccTemplate = new KitchenCookbookCollect();
            kccTemplate.setTime(dualDao.getNowTime());
            kccTemplate.setUserId(userId);
            kccTemplate.setRoomId(commonRoomService.getDefaultRoomIdByUserId(userId));
            final List<KitchenCookbookCollect> batchInsertList = new ArrayList<KitchenCookbookCollect>();
            kccIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_kitchen_cookbook_collect, cookbookIdList.size());
            for (int index = 0; index < cookbookIdList.size(); index++) {
                final KitchenCookbookCollect kcc = new KitchenCookbookCollect(kccTemplate);
                kcc.setId(kccIds.get(index));
                kcc.settKitchenCookbookFId(cookbookIdList.get(index));
                batchInsertList.add(kcc);
            }

            // 执行收藏
            final int res = kitchenCookbookCollectBaseDao.insertKitchenCookbookCollectBatch(batchInsertList);
            if (res <= 0) {
                throw new BusinessRuntimeException("kitchen.doCollectCookbook.failed");
            }

            {// 添加红点提醒
                final List<BasicSourceEntity> sourceList = new ArrayList<BasicSourceEntity>();
                for (BigInteger bi : cookbookIdList) {
                    sourceList.add(new BasicSourceEntity(RedpointDict.Redpoint_Content_SourceType.FamilyCookBook_Collect, bi, "INSERT"));
                }
                final FutureTask<Boolean> task = new FutureTask<Boolean>(
                        new RedpointAddRunnableForFamily(redpointService, userId, RedpointConstant.RedpointContent_ModelCode.FML_COOKBOOK, sourceList));
                new Thread(task).start();
            }

            // 发送推送
            Integer extDataType = FamilyMsgDict.FamilyMsgExtData_type.TodayCookBookNew;
            List<MsgExtDataAdd> msgExtDataAddList = new ArrayList<MsgExtDataAdd>();
            for (BigInteger dataId : cookbookIdList) {
                msgExtDataAddList.add(new MsgExtDataAdd(dataId, extDataType));
            }

            familyMsgService.addMsg(userId, comment, extDataType, atUserIdSet, msgExtDataAddList, null);
        } else {
        }

        // 添加喜欢吃
        if (null != kccIds) {
            redundantKccIds.addAll(kccIds);
        } else {
        }
        if (!redundantKccIds.isEmpty()) {
            doLikeEatMultiCookbookCollect(redundantKccIds, userId);// 默认增加喜欢吃
        } else {
        }
    }

    /**
     * @author wangzhe
     * @date 2015年9月14日
     * @description 推送给家人
     *
     * @param userId 推送者id
     * @param timeDay 时间
     * @param cbCount 菜个数
     * @param isAdd 添加还是删除
     */
    private void pushToFamily(BigInteger userId, String timeDay, int cbCount, boolean isAdd) {
        UserSimpleEntity user = commonUserService.getUserInfoById(userId);
        List<UserSimpleEntity> members = commonUserService.getFamilyMembersWithoutSelf(userId, true);
        if (0 < members.size()) {
            List<CommUserDataEntity> cudes = mapCommUserDataEntity(members);

            Message msg = new Message();
            msg.setCreater(userId);
            msg.setContent("您的家人  " + (null != user.getNickName() ? user.getNickName() : user.getId()) + (isAdd ? "  添加了" : "  删除了") + cbCount + "道菜");
            msg.setTime(timeDay);
            msg.setType(NoticeDict.Message_Type.Cookbook_List_changed);
            msg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message));

            List<MessageParameter> mps = new ArrayList<MessageParameter>();
            MessageParameter tmpMessageParameter = new MessageParameter();
            tmpMessageParameter.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_message_parameter));
            tmpMessageParameter.setKey("pushId");
            tmpMessageParameter.settMessageFId(msg.getId());
            tmpMessageParameter.setValue(MsgpushDict.PushId.CookBookChanged);

            mps.add(tmpMessageParameter);

            // 超时时间是第二天0点
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_YEAR, 1);

            msgpushService.addMessage2Pool(cudes, msg, DateUtil.formatDay.get().format(c.getTime()), mps);
        } else {
        }
    }

    private List<CommUserDataEntity> mapCommUserDataEntity(List<UserSimpleEntity> uraes) {
        List<CommUserDataEntity> cudes = new ArrayList<CommUserDataEntity>();
        for (UserSimpleEntity urae : uraes) {
            if (null != urae) {
                cudes.add(new CommUserDataEntity(urae.getId(), LoginDict.UserRegistOrTmp.REGIST_USER, urae.getDefaultRoomId()));
            } else {
            }
        }
        return cudes;
    }

    @Override
    public void cancelCollectCookbook(BigInteger cookbookId, BigInteger userId) {
        // 仅取消当天的收藏
        String timeDay = dualDao.getNowDay();
        Integer resCount = kitchenDao.cancelCollectCookbookByDay(userId, cookbookId, timeDay);
        if (resCount == null || resCount <= 0) {
            throw new BusinessRuntimeException("kitchen.cancelCollectCookbook.failed");
        }
    }

    @Override
    public void cancelMultiCollectCookbook(List<BigInteger> cookBookIds, BigInteger userId) {
        // 仅取消当天的收藏
        String timeDay = dualDao.getNowDay();
        List<UserSimpleEntity> uses = commonUserService.getFamilyMembers(userId);
        List<BigInteger> userIds = new ArrayList<BigInteger>();
        for (UserSimpleEntity use : uses) {
            userIds.add(use.getId());
        }
        Integer resCount = kitchenDao.cancelMultiCollectCookbookByDay(userIds, cookBookIds, timeDay);
        if (resCount == null || resCount <= 0) {
            throw new BusinessRuntimeException("kitchen.cancelCollectCookbook.failed");
        }
        pushToFamily(userId, timeDay, resCount, false);
    }

    @Override
    public String fetchAllKitchenLastUpdTime(List<Integer> cookbookTypeIds, String searchKey, PageModel pageModel, BigInteger userId) {
        String res = null;
        if (StringUtils.isEmpty(searchKey) && pageModel.begin == 0) {// 不是搜索的且分页查询的是第一页的
            res = kitchenDao.selectAllKitchenLastUpdTime(cookbookTypeIds, pageModel, userId);
        }
        return res;
    }

    @Override
    public BigInteger getCityIdByInfo(String cityName, BigInteger userId) {
        BigInteger cityId = null;
        RoomEntityWithValidate roomEntityWithValidate = null;
        try {
            if (userId != null) {
                roomEntityWithValidate = commonRoomService.getDefaultRoomInfo(userId);
            }
            if (roomEntityWithValidate != null) {
                cityId = roomEntityWithValidate.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity().gettCityFId();
            }
        } catch (Exception e) {
            logger.error("KitchenService getDefaultRoomInfo by userId cause exception,cityName is :" + cityName, e);
        }
        if (roomEntityWithValidate == null) {
            if (!StringUtils.isEmpty(cityName)) {
                List<AddressCity> addressCityList = commonRoomService.getAddressCityListByCityNameDim(cityName);
                if (addressCityList != null && addressCityList.size() > 0) {
                    cityId = addressCityList.get(0).getId();
                }
            }
        }
        if (cityId == null) {
            cityId = RoomConstants.DEFAULT_ADDRESS_CITY_ID;
        }
        return cityId;
    }

    @Override
    public synchronized void doLikeEatCookbookCollect(BigInteger cookCollectId, BigInteger userId) {
        if (cookCollectId == null || userId == null) {
            return;
        }
        // 是否已喜欢
        Boolean haveDoLike = null;
        {
            KitchenCookbookCollectIslike qryMap = new KitchenCookbookCollectIslike();
            qryMap.setUserId(userId);
            qryMap.settKitchenCookbookCollectFId(cookCollectId);
            int count = kitchenCookbookCollectIslikeBaseDao.selectKitchenCookbookCollectIslikeCount(MapConverter.toMap(qryMap), false);
            if (count > 0) {
                haveDoLike = true;
            } else {
                haveDoLike = false;
            }
        }
        if (haveDoLike) {
            throw new BusinessRuntimeException("KitchenService.doLikeEatCookbook.haveLike");
        } else {
            User baseUser = userBaseDao.selectUserBySeqId(userId);
            BigInteger addId = uuidManager.getNextUuidBigInteger(SEQConstants.t_kitchen_cookbook_collect_islike);
            KitchenCookbookCollectIslike cookbookCollectIslikeAdd = new KitchenCookbookCollectIslike();
            cookbookCollectIslikeAdd.setId(addId);
            cookbookCollectIslikeAdd.setRoomId(baseUser.getDefaultRoomId());
            cookbookCollectIslikeAdd.settKitchenCookbookCollectFId(cookCollectId);
            cookbookCollectIslikeAdd.setUserId(userId);
            Integer resCount = kitchenCookbookCollectIslikeBaseDao.insertKitchenCookbookCollectIslike(cookbookCollectIslikeAdd);
            if (resCount == null || resCount <= 0) {
                throw new BusinessRuntimeException("kitchenService.doLikeEatCookbook.failed");
            }
        }
    }

    @Override
    public void doLikeEatMultiCookbookCollect(final List<BigInteger> cookCollectIds, final BigInteger userId) {
        List<BigInteger> kccIds = kitchenDao.selectKitchenCookbookCollectIslike(userId);
        Iterator<BigInteger> it = cookCollectIds.iterator();
        while (it.hasNext()) {
            BigInteger bigInteger = (BigInteger) it.next();
            for (BigInteger bi : kccIds) {
                if (bigInteger.equals(bi)) {
                    it.remove();
                    break;
                } else {
                }
            }
        }
        if (0 < cookCollectIds.size()) {
            User baseUser = userBaseDao.selectUserBySeqId(userId);
            KitchenCookbookCollectIslike template = new KitchenCookbookCollectIslike();
            template.setUserId(userId);
            template.setRoomId(baseUser.getDefaultRoomId());
            List<BigInteger> addIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_kitchen_cookbook_collect_islike, cookCollectIds.size());
            List<KitchenCookbookCollectIslike> batch = new ArrayList<KitchenCookbookCollectIslike>();
            for (int i = 0; i < cookCollectIds.size(); i++) {
                KitchenCookbookCollectIslike kcci = new KitchenCookbookCollectIslike(template);
                kcci.setId(addIds.get(i));
                kcci.settKitchenCookbookCollectFId(cookCollectIds.get(i));
                batch.add(kcci);
            }
            Integer resCount = kitchenCookbookCollectIslikeBaseDao.insertKitchenCookbookCollectIslikeBatch(batch);
            if (resCount == null || resCount <= 0) {
                throw new BusinessRuntimeException("kitchenService.doLikeEatCookbook.failed");
            }
        } else {
        }
    }

    @Override
    public KitchenCookbookCollectEntity qryKitchenCookbookCollectDetailById(BigInteger cookCollectId, BigInteger userId) {
        return kitchenDao.selectKitchenCookbookCollectDetailById(cookCollectId, userId);
    }

    @Override
    public List<KitchenCookbookCollectEntity> qryKitchenCookbookCollectListToday(BigInteger userId) {
        return kitchenDao.selectKitchenCookbookCollectListToday(userId);
    }

    @Override
    public DietAnalysisEntity qryDietAnalysis(BigInteger userId) {
        return kitchenDao.qryDietAnalysis(userId);
    }

}
