package com.cnfantasia.server.ms.fixedFeeCfg.service;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillInitEntity;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UpdatePayBillTypeDict;
import com.cnfantasia.server.api.groupBuildingCycleCfg.service.UpdatePayBillRunnable;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.fixedFeeItem.service.IFixedFeeItemBaseService;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.entity.FixedFeeItemRoomData;
import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.service.IFixedFeeItemRoomDataBaseService;
import com.cnfantasia.server.ms.fixedFeeCfg.dao.FixedFeeCfgDao;
import com.cnfantasia.server.ms.fixedFeeCfg.entity.RoomFixedFeeItemDetail;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.session.UserContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.annotation.ThreadSafe;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: FixedFeeCfgService
 * @Date: 2017-01-18 18:02
 * @Auther: yanghua
 * @Description:(固定收费项配置)
 */
@Service
public class FixedFeeCfgService {

    @Resource
    private FixedFeeCfgDao fixedFeeCfgDao;
    @Resource
    private IFixedFeeItemBaseService fixedFeeItemBaseService;
    @Resource
    private IUuidManager uuidManager;
    @Resource
    private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
    @Resource
    private IFixedFeeItemRoomDataBaseService fixedFeeItemRoomDataBaseService;

    /**
     * 查询 配置固定收费小区的列表总数
     * @param paramMap
     * @return
     */
    public int getGroupBuildingListCount(Map<String, Object> paramMap) {
        return fixedFeeCfgDao.getGroupBuildingListCount(paramMap);
    }

    /**
     * 查询 配置固定收费小区的列表
     * @param paramMap
     * @param pageModel
     * @return
     */
    public List<HashMap<String, Object>> getGroupBuildingList(Map<String, Object> paramMap, PageModel pageModel) {
        return fixedFeeCfgDao.getGroupBuildingList(paramMap, pageModel);
    }

    /**
     * 查询固定收费项的列表
     * @param gbId
     * @return
     */
    public List<FixedFeeItem> getFixedFeeItemList(BigInteger gbId) {
        HashMap<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("tGbId", gbId);
        return fixedFeeItemBaseService.getFixedFeeItemByCondition(paraMap);
    }

    /**
     * 保存收费项配置
     * @param gbId
     * @param items 收费项
     * @return
     */
    public boolean saveFeeItem(BigInteger gbId, String items) {
        if (StringUtils.isNotBlank(items) && !"[]".equals(items)) {
            BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
            String now = DateUtils.getCurrentDate();
            List<FixedFeeItem> fixedFeeItemList = JSON.parseArray(items, FixedFeeItem.class);
            int insertSize = 0;
            for (FixedFeeItem fixedFeeItem : fixedFeeItemList) {
                if (fixedFeeItem.getId() == null) {
                    insertSize++;
                }
            }

            List<BigInteger> ids = null;
            if (insertSize > 0) {
                ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_fixed_fee_item, fixedFeeItemList.size());
            }

            List<FixedFeeItem> insertFeeItems = new ArrayList<FixedFeeItem>();
            List<FixedFeeItem> updateFeeItems = new ArrayList<FixedFeeItem>();
            int insertIndex = 0;
            for (FixedFeeItem fixedFeeItem : fixedFeeItemList) {
                fixedFeeItem.settGbId(gbId);
                if (fixedFeeItem.getId() == null) {// insert
                    fixedFeeItem.setId(ids.get(insertIndex++));
                    fixedFeeItem.setSys0AddUser(userId);
                    fixedFeeItem.setSys0AddTime(now);
                    fixedFeeItem.settGbId(gbId);

                    insertFeeItems.add(fixedFeeItem);
                } else {// update
                    fixedFeeItem.setSys0UpdUser(userId);
                    fixedFeeItem.setSys0UpdTime(now);
                    fixedFeeItem.settGbId(gbId);
                    updateFeeItems.add(fixedFeeItem);
                }
            }

            int affectedCount = 0;
            if (insertFeeItems.size() > 0) {
                affectedCount = fixedFeeItemBaseService.insertFixedFeeItemBatch(insertFeeItems);
            }

            if (updateFeeItems.size() > 0) {
                affectedCount += fixedFeeItemBaseService.updateFixedFeeItemBatch(updateFeeItems);
            }

            return affectedCount > 0;
        }
        return false;
    }

    /**
     * 删除收费项配置  物理删除
     * @param id
     * @return
     */
    public boolean delFeeItem(BigInteger id) {
        //更新收费项数据信息  删除前进行数据查询
        List<UpdatePayBillInitEntity> updatePayBillInitEntities = fixedFeeCfgDao.itemHasRoomByItemId(id);

        FixedFeeItem fixedFeeItemBySeqId = fixedFeeItemBaseService.getFixedFeeItemBySeqId(id);
        int count = fixedFeeCfgDao.delFeeItem(id, fixedFeeItemBySeqId.gettGbId());

        if(!DataUtil.isEmpty(updatePayBillInitEntities)) {
            FutureTask futureTask = new FutureTask(new UpdatePayBillRunnable(updatePayBillInitEntities));
            new Thread(futureTask).start();
        }
        return count > 0;
    }

    /**
     * 收费详情列表查询
     * @param gbId
     * @param bName
     * @param unitName
     * @param roomNum
     * @param pageModel
     * @return
     */
    public List<RoomFixedFeeItemDetail> getRoomFixedFeeItemDetailList(BigInteger gbId, String bName, String unitName, String roomNum, PageModel pageModel) {
        return fixedFeeCfgDao.getRoomFixedFeeItemDetailList(gbId,  bName,  unitName,  roomNum,  pageModel);
    }

    /**
     * 收费详情列表数量查询
     * @param gbId
     * @param bName
     * @param unitName
     * @param roomNum
     * @return
     */
    public int getRoomFixedFeeItemDetailCount(BigInteger gbId, String bName, String unitName, String roomNum) {
        return fixedFeeCfgDao.getRoomFixedFeeItemDetailCount( gbId,  bName,  unitName,  roomNum);
    }

    /**
     * 修改 查询的收费详情信息
     * @param dataId
     * @return
     */
    public RoomFixedFeeItemDetail getRoomFixedFeeItemDetailByDataId(BigInteger dataId) {
        return fixedFeeCfgDao.getRoomFixedFeeItemDetailByDataId(dataId);
    }

    /**
     * 更新门牌  收费数据信息
     * @param items
     * @param roomId
     * @param fixedFeeItemHasRoomsForUpdate
     * @param realRoomId  @return
     * @param userId
     */
    public boolean updateFeeDetail(List<FixedFeeItemHasRoom> fixedFeeItemHasRoomsForUpdate, List<FixedFeeItemHasRoom> fixedFeeItemHasRoomsForAdd, BigInteger realRoomId, BigInteger gbId, BigInteger userId) {
        int i = fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRoomsForUpdate);

        if(!DataUtil.isEmpty(fixedFeeItemHasRoomsForAdd)) {
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put("tGbId", gbId);
            paraMap.put("tRealRoomId", realRoomId);
            List<FixedFeeItemRoomData> fixedFeeItemRoomDataByCondition = fixedFeeItemRoomDataBaseService.getFixedFeeItemRoomDataByCondition(paraMap);

            CnfantasiaCommUtil.newStandardList(fixedFeeItemHasRoomsForAdd, SEQConstants.t_fixed_fee_item_has_room);
            for (FixedFeeItemHasRoom fixedFeeItemHasRoom : fixedFeeItemHasRoomsForAdd) {
                fixedFeeItemHasRoom.settFixedFeeDataId(fixedFeeItemRoomDataByCondition.get(0).getId());
            }
            int j = fixedFeeItemHasRoomBaseService.insertFixedFeeItemHasRoomBatch(fixedFeeItemHasRoomsForAdd);

        }

        //更新账单数据
        List<UpdatePayBillInitEntity> updatePayBillInitEntities = new ArrayList<UpdatePayBillInitEntity>();
        UpdatePayBillInitEntity updatePayBillInitEntity = new UpdatePayBillInitEntity();
        updatePayBillInitEntity.setFixedFeeItemHasRoomList(fixedFeeItemHasRoomsForUpdate);
        updatePayBillInitEntity.setRealRoomId(realRoomId);
        updatePayBillInitEntity.setType(UpdatePayBillTypeDict.UPDATEALL);
        updatePayBillInitEntities.add(updatePayBillInitEntity);
        FutureTask futureTask = new FutureTask(new UpdatePayBillRunnable(updatePayBillInitEntities));
        new Thread(futureTask).start();

        List<UpdatePayBillInitEntity> updatePayBillInitEntities02 = new ArrayList<UpdatePayBillInitEntity>();
        UpdatePayBillInitEntity updatePayBillInitEntity02 = new UpdatePayBillInitEntity();
        updatePayBillInitEntity02.setFixedFeeItemHasRoomList(fixedFeeItemHasRoomsForAdd);
        updatePayBillInitEntity02.setRealRoomId(realRoomId);
        updatePayBillInitEntity02.setType(UpdatePayBillTypeDict.UPDATEALL);
        updatePayBillInitEntities02.add(updatePayBillInitEntity02);
        FutureTask futureTask02 = new FutureTask(new UpdatePayBillRunnable(updatePayBillInitEntities02));
        new Thread(futureTask02).start();

        return i > 0;
    }

    /**
     * 删除单条收费数据
     * @param id
     * @return
     */
    public boolean delFixedFeeDetail(BigInteger id) {
        int i = fixedFeeCfgDao.delFixedFeeDetail(id);
        return i > 0;
    }

    /**
     * 清除所有的房间收费关联信息
     * @return
     */
    public boolean delFixedFeeDetailAll(BigInteger gbId) {
        int i = fixedFeeCfgDao.delFixedFeeDetailAll(gbId);
        return i > 0;
    }

    /**
     * 数据详情-导入校验使用list
     * @param gbId
     * @return
     */
    public List<RoomFixedFeeItemDetail> getRoomFixedFeeItemDetailListForImportCheck(BigInteger gbId) {
        return fixedFeeCfgDao.getRoomFixedFeeItemDetailListForImportCheck(gbId);
    }

    /**
     * 保存excel中的数据  这里使用的是新增 t_fixed_fee_item_has_room费用项和房间关系，t_fixed_fee_item_room_data数据详情表
     * 如果存在费用项关系数据，则进行删除，然后在进行新增；数据详情只进行新增，不进行修改
     * @param roomFixedFeeItemDetailArrayList
     * @param gbId
     * @return
     */
    @Transactional
    public String saveImportDataDetail(List<RoomFixedFeeItemDetail> roomFixedFeeItemDetailArrayList, BigInteger gbId) {
        List<FixedFeeItemRoomData> fixedFeeItemRoomDataList = new ArrayList<FixedFeeItemRoomData>();
        List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList = new ArrayList<FixedFeeItemHasRoom>();
        List<FixedFeeItemHasRoom> fixedFeeItemHasRoomListUpdate = new ArrayList<FixedFeeItemHasRoom>();
        List<UpdatePayBillInitEntity> updatePayBillInitEntitiesForUpdate = new ArrayList<UpdatePayBillInitEntity>();
        List<UpdatePayBillInitEntity> updatePayBillInitEntitiesForAddRoom = new ArrayList<UpdatePayBillInitEntity>();
        List<UpdatePayBillInitEntity> updatePayBillInitEntitiesForAddItem = new ArrayList<UpdatePayBillInitEntity>();
        List<UpdatePayBillInitEntity> updatePayBillInitEntities = new ArrayList<UpdatePayBillInitEntity>();
        convertFeeItemDetaiList(roomFixedFeeItemDetailArrayList, fixedFeeItemRoomDataList, fixedFeeItemHasRoomList, gbId,
                fixedFeeItemHasRoomListUpdate, updatePayBillInitEntitiesForUpdate, updatePayBillInitEntitiesForAddRoom, updatePayBillInitEntitiesForAddItem);
        int g = 0;
        int h = 0;
        int batchStepSize = 100; //一次插入100条，分批插入能提高性能
        for (int i = 0; i < (fixedFeeItemRoomDataList.size() / batchStepSize) + 1; i++) {
            int endIndex = (i + 1) * batchStepSize > fixedFeeItemRoomDataList.size() ? fixedFeeItemRoomDataList.size() : (i + 1) * batchStepSize;

            List<FixedFeeItemRoomData> subList = fixedFeeItemRoomDataList.subList(i * batchStepSize, endIndex);
            if (subList.size() > 0) {
                g += fixedFeeItemRoomDataBaseService.insertFixedFeeItemRoomDataBatch(subList);
            }
        }
        for (int i = 0; i < (fixedFeeItemHasRoomList.size() / batchStepSize) + 1; i++) {
            int endIndex = (i + 1) * batchStepSize > fixedFeeItemHasRoomList.size() ? fixedFeeItemHasRoomList.size() : (i + 1) * batchStepSize;

            List<FixedFeeItemHasRoom> subList = fixedFeeItemHasRoomList.subList(i * batchStepSize, endIndex);
            if (subList.size() > 0) {
                h += fixedFeeItemHasRoomBaseService.insertFixedFeeItemHasRoomBatch(subList);
            }
        }
        for (int i = 0; i < (fixedFeeItemHasRoomListUpdate.size() / batchStepSize) + 1; i++) {
            int endIndex = (i + 1) * batchStepSize > fixedFeeItemHasRoomListUpdate.size() ? fixedFeeItemHasRoomListUpdate.size() : (i + 1) * batchStepSize;

            List<FixedFeeItemHasRoom> subList = fixedFeeItemHasRoomListUpdate.subList(i * batchStepSize, endIndex);
            if (subList.size() > 0) {
                h += fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(subList);
            }
        }


        //修改选择周期的预缴账单数据
        if(!DataUtil.isEmpty(updatePayBillInitEntitiesForUpdate)) {
            updatePayBillInitEntities.addAll(updatePayBillInitEntitiesForUpdate);
        }
        if(!DataUtil.isEmpty(updatePayBillInitEntitiesForAddItem)) {
            updatePayBillInitEntities.addAll(updatePayBillInitEntitiesForAddItem);
        }
        if(!DataUtil.isEmpty(updatePayBillInitEntitiesForAddRoom)) {
            updatePayBillInitEntities.addAll(updatePayBillInitEntitiesForAddRoom);
        }

        FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new UpdatePayBillRunnable(updatePayBillInitEntities));
        new Thread(futureTask).start();

        if(g > 0 || h > 0) {
            return "导入成功";
        } else {
            return  "导入失败";
        }
    }

    /**
     * 组装导入的list
     * @param importDetailItemList
     * @param fixedFeeItemRoomDataList
     * @param fixedFeeItemHasRoomList
     * @param gbId
     * @param fixedFeeItemHasRoomListUpdate
     * @param payBillInitEntitiesForUpdate
     * @param updatePayBillInitEntitiesForAddRoom
     * @param updatePayBillInitEntitiesForAddItem
     */
    private void convertFeeItemDetaiList(List<RoomFixedFeeItemDetail> importDetailItemList, List<FixedFeeItemRoomData> fixedFeeItemRoomDataList,
                                         List<FixedFeeItemHasRoom> fixedFeeItemHasRoomList, BigInteger gbId, List<FixedFeeItemHasRoom> fixedFeeItemHasRoomListUpdate,
                                         List<UpdatePayBillInitEntity> payBillInitEntitiesForUpdate, List<UpdatePayBillInitEntity> updatePayBillInitEntitiesForAddRoom,
                                         List<UpdatePayBillInitEntity> updatePayBillInitEntitiesForAddItem){
        //查询小区对应的账期配置的所有费用项id
        List<BigInteger> cycleHasFeeItemIds = fixedFeeCfgDao.getCycleCfgFeeItemsByGbId(gbId);

        List<RoomFixedFeeItemDetail> existItemList = getRoomFixedFeeItemDetailListForImportCheck(gbId);
        Map<String, BigInteger> roomStrByGbIds = getRoomStrByGbId(gbId);
        // 计算新增t_fixed_fee_item_has_room f_id的数量
        int fixedFeeItemHasRoomSize = 0;
        for(RoomFixedFeeItemDetail roomFixedFeeItemDetail : importDetailItemList){
        	List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = roomFixedFeeItemDetail.getHasFeeItemList();
        	if(fixedFeeItemHasRooms!=null){
        		fixedFeeItemHasRoomSize += fixedFeeItemHasRooms.size();
        	}
        }
        
        List<BigInteger> dataIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_fixed_fee_item_room_data, importDetailItemList.size());
        List<BigInteger> hasRoomIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_fixed_fee_item_has_room, fixedFeeItemHasRoomSize);
        int i = 0, j = 0;
        for(RoomFixedFeeItemDetail roomFixedFeeItemDetail : importDetailItemList) {
            boolean addData = true;//是否是新增数据

            for (RoomFixedFeeItemDetail roomFixedFeeItemDetailExist : existItemList){
                //判断数据是否存在
                if(isSameRoom(roomFixedFeeItemDetailExist, roomFixedFeeItemDetail)) {//存在 更新数据
                    addData = false;

                    //更新选择周期预缴账单数据
                    List<FixedFeeItemHasRoom> updateListTmp = new ArrayList<FixedFeeItemHasRoom>();
                    List<FixedFeeItemHasRoom> addListTmp = new ArrayList<FixedFeeItemHasRoom>();

                    for (FixedFeeItemHasRoom fixedFeeItemHasRoom : roomFixedFeeItemDetail.getHasFeeItemList()){
                        BigInteger updateId = null;
                        for (FixedFeeItemHasRoom feeItemHasRoom : roomFixedFeeItemDetailExist.getHasFeeItemList()) {
                            if(feeItemHasRoom.gettFixedFeeItemId().equals(fixedFeeItemHasRoom.gettFixedFeeItemId())) {
                                updateId = feeItemHasRoom.getId();
                                break;
                            }
                        }

                        //如果账期配置中不存在该费用项，则不需要进行数据的更新
                        boolean isUpdated = !DataUtil.isEmpty(cycleHasFeeItemIds) && cycleHasFeeItemIds.contains(fixedFeeItemHasRoom.gettFixedFeeItemId());
                        if(updateId != null) {//修改
                            fixedFeeItemHasRoom.setId(updateId);
                            fixedFeeItemHasRoom.setSys0UpdTime(DateUtils.getCurrentDate());
                            fixedFeeItemHasRoom.setSys0UpdUser(UserContext.getCurrUser().getId());
                            fixedFeeItemHasRoomListUpdate.add(fixedFeeItemHasRoom);
                            if(isUpdated) {
                                updateListTmp.add(fixedFeeItemHasRoom);
                            }
                        } else {//新增
                            fixedFeeItemHasRoom.setId(hasRoomIds.get(j));
                            fixedFeeItemHasRoom.settFixedFeeDataId(roomFixedFeeItemDetailExist.getFixedFeeItemHasRoomDataId());
                            fixedFeeItemHasRoom.setSys0AddTime(DateUtils.getCurrentDate());
                            fixedFeeItemHasRoom.setSys0AddUser(UserContext.getCurrUser().getId());
                            fixedFeeItemHasRoomList.add(fixedFeeItemHasRoom);
                            if(isUpdated) {
                                addListTmp.add(fixedFeeItemHasRoom);
                            }
                            j++;
                        }
                    }

                    //组装需要进行预缴费更新的数据
                    if(!DataUtil.isEmpty(updateListTmp)) {
                        UpdatePayBillInitEntity updatePayBillInitEntity = new UpdatePayBillInitEntity();
                        updatePayBillInitEntity.setRealRoomId(roomFixedFeeItemDetail.getRealRoomId());
                        updatePayBillInitEntity.setFixedFeeItemHasRoomList(updateListTmp);
                        updatePayBillInitEntity.setType(UpdatePayBillTypeDict.UPDATEALL);
                        payBillInitEntitiesForUpdate.add(updatePayBillInitEntity);
                    }
                    if(!DataUtil.isEmpty(addListTmp)) {
                        UpdatePayBillInitEntity updatePayBillInitEntity = new UpdatePayBillInitEntity();
                        updatePayBillInitEntity.setRealRoomId(roomFixedFeeItemDetail.getRealRoomId());
                        updatePayBillInitEntity.setFixedFeeItemHasRoomList(addListTmp);
                        updatePayBillInitEntity.setType(UpdatePayBillTypeDict.ADDITEM);
                        updatePayBillInitEntitiesForAddItem.add(updatePayBillInitEntity);
                    }

                }
            }

            if(addData) {
                String roomStr = "";
                if(DataUtil.isEmpty(roomFixedFeeItemDetail.getUnitName())) {
                    roomStr = roomFixedFeeItemDetail.getbName()+"--"+roomFixedFeeItemDetail.getRoomNumTail();
                } else {
                    roomStr = roomFixedFeeItemDetail.getbName()+"-"+roomFixedFeeItemDetail.getUnitName()+"-"+roomFixedFeeItemDetail.getRoomNumTail();
                }
                List<FixedFeeItemHasRoom> addListTmp = new ArrayList<FixedFeeItemHasRoom>();
                FixedFeeItemRoomData fixedFeeItemRoomData = new FixedFeeItemRoomData();
                BigInteger realRoomId = roomStrByGbIds.get(roomStr);
                fixedFeeItemRoomData.setId(dataIds.get(i));
                fixedFeeItemRoomData.settGbId(gbId);
                fixedFeeItemRoomData.settRealRoomId(realRoomId);
                fixedFeeItemRoomData.setSys0AddTime(DateUtils.getCurrentDate());
                fixedFeeItemRoomData.setSys0AddUser(UserContext.getCurrUser().getId());
                fixedFeeItemRoomDataList.add(fixedFeeItemRoomData);

                List<FixedFeeItemHasRoom> hasFeeItemList = roomFixedFeeItemDetail.getHasFeeItemList();
                for (FixedFeeItemHasRoom fixedFeeItemHasRoom : hasFeeItemList){
                    fixedFeeItemHasRoom.setId(hasRoomIds.get(j));
                    fixedFeeItemHasRoom.settFixedFeeDataId(fixedFeeItemRoomData.getId());
                    fixedFeeItemHasRoom.setSys0AddTime(DateUtils.getCurrentDate());
                    fixedFeeItemHasRoom.setSys0AddUser(UserContext.getCurrUser().getId());
                    fixedFeeItemHasRoomList.add(fixedFeeItemHasRoom);

                    j ++;
                    //如果账期配置中不存在该费用项，则不需要进行数据的更新
                    if(!DataUtil.isEmpty(cycleHasFeeItemIds) && !cycleHasFeeItemIds.contains(fixedFeeItemHasRoom.gettFixedFeeItemId())) continue;

                    addListTmp.add(fixedFeeItemHasRoom);
                }

                UpdatePayBillInitEntity updatePayBillInitEntity = new UpdatePayBillInitEntity();
                updatePayBillInitEntity.setRealRoomId(roomFixedFeeItemDetail.getRealRoomId());
                updatePayBillInitEntity.setFixedFeeItemHasRoomList(addListTmp);
                updatePayBillInitEntity.setType(UpdatePayBillTypeDict.ADDROOM);
                updatePayBillInitEntitiesForAddRoom.add(updatePayBillInitEntity);
            }
            i ++;
        }
    }

    /**
     * 判断是否为同一个房间（excel导入校验）
     * @param obj1 数据库中的
     * @param obj2 excel中的
     * @return
     */
    private static boolean isSameRoom(RoomFixedFeeItemDetail obj1, RoomFixedFeeItemDetail obj2) {
        if(obj1.getbName().equals(obj2.getbName())) {
            if((!DataUtil.isEmpty(obj1.getUnitName()) &&(!DataUtil.isEmpty(obj2.getUnitName())))){
                if(obj1.getUnitName().equals(obj2.getUnitName())) {
                    return obj1.getRoomNumTail().equals(obj2.getRoomNumTail());
                }
            } else {
                return obj1.getRoomNumTail().equals(obj2.getRoomNumTail());
            }
        } else {
            return false;
        }
        return false;
    }

    public  Map<String, BigInteger> getRoomStrByGbId(BigInteger gbId) {
        List<Map<String, Object>> list = fixedFeeCfgDao.getRoomStrByGbId(gbId);
        Map<String, BigInteger> resMap = new HashMap<String, BigInteger>();
        for (Map<String, Object> map : list) {
            if(!DataUtil.isEmpty(map.get("room"))) {
                resMap.put(map.get("room").toString(),BigInteger.valueOf(Long.valueOf(map.get("realRoomId").toString())));
            }
        }
        return resMap;
    }

    public boolean delFeeDetail(String[] idsArr, BigInteger realRoomId, BigInteger gbId, BigInteger userId) {
        List<BigInteger> idList = new ArrayList<>();
        for (int i = 0; i < idsArr.length; i++) {
            idList.add(new BigInteger(idsArr[i]));
        }
        int i = fixedFeeItemHasRoomBaseService.deleteFixedFeeItemHasRoomLogicBatch(idList);
        return i > 0;
    }
}
