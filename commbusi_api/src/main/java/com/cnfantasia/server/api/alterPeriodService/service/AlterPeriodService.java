package com.cnfantasia.server.api.alterPeriodService.service;

import java.math.BigInteger;
import java.util.*;

import javax.annotation.Resource;

import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.plotproperty.entity.PropertyAlterBillInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.alterPeriod.dao.IAlterPeriodDao;
import com.cnfantasia.server.commbusi.alterPeriod.entity.AlterPeroidDetailWithItem;
import com.cnfantasia.server.commbusi.alterPeriod.entity.RoomAlterPeroidDetail;
import com.cnfantasia.server.commbusi.alterPeriod.service.LatefeeCalculateService;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.service.IAlterPeriodDataDetailBaseService;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.service.IAlterRoomHasFeeItemBaseService;

public class AlterPeriodService {
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IAlterPeriodDao alterPeriodDao;

	@Resource
	private IUuidManager uuidManager;
	
	public String saveImportDataDetail(List<AlterPeriodDataDetail> addAlterWithItemList, List<AlterPeriodDataDetail> updateAlterWithItemList,
			List<AlterRoomHasFeeItem> addAlterItemList, List<AlterRoomHasFeeItem> updateAlterItemList) {
		int updateCount = 0; 
		if (!addAlterWithItemList.isEmpty())
			updateCount += alterPeriodDataDetailBaseService.insertAlterPeriodDataDetailBatch(addAlterWithItemList);
		//需求说不更新之前已经有的数据
//		if (!updateAlterWithItemList.isEmpty())
//			updateCount += alterPeriodDataDetailBaseService.updateAlterPeriodDataDetailBatch(updateAlterWithItemList);
		if (!addAlterItemList.isEmpty())
			updateCount += alterRoomHasFeeItemBaseService.insertAlterRoomHasFeeItemBatch(addAlterItemList);
		//需求说不更新之前已经有的数据
//		if (!updateAlterItemList.isEmpty())
//			updateCount += alterRoomHasFeeItemBaseService.updateAlterRoomHasFeeItemBatch(updateAlterItemList);
		return updateCount > 0 ? "导入成功":"导入失败";
	}
	
	/**
	 * 根据导入的excel的数据查询数据库中存在的数据
	 * @param alterPeroidDetailWithItemList
	 * @return
	 */
	public List<AlterPeroidDetailWithItem> getAlterPeroidDetailWithItemList(List<AlterPeroidDetailWithItem> alterPeroidDetailWithItemList) {
		return alterPeriodDao.getAlterPeroidDetailWithItemList(alterPeroidDetailWithItemList);
	}
		
	private IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService;
	public void setAlterPeriodDataDetailBaseService(IAlterPeriodDataDetailBaseService alterPeriodDataDetailBaseService) {
		this.alterPeriodDataDetailBaseService = alterPeriodDataDetailBaseService;
	}

	private IAlterRoomHasFeeItemBaseService alterRoomHasFeeItemBaseService;
	public void setAlterRoomHasFeeItemBaseService(IAlterRoomHasFeeItemBaseService alterRoomHasFeeItemBaseService) {
		this.alterRoomHasFeeItemBaseService = alterRoomHasFeeItemBaseService;
	}

	private LatefeeCalculateService latefeeCalculateService;
	public void setLatefeeCalculateService(LatefeeCalculateService latefeeCalculateService) {
		this.latefeeCalculateService = latefeeCalculateService;
	}

	@Transactional
	public void updRoomAlterPeriod(AlterPeriodDataDetail alterPeriodDataDetail, List<AlterRoomHasFeeItem> hasFeeItemList) {
		alterPeriodDataDetailBaseService.updateAlterPeriodDataDetail(alterPeriodDataDetail);
		//t_alter_room_has_fee_item表，如果有，则更新，如果无，则新增
		List<AlterRoomHasFeeItem> updList = new ArrayList<AlterRoomHasFeeItem>();
		List<AlterRoomHasFeeItem> insertList = new ArrayList<AlterRoomHasFeeItem>();
		AlterRoomHasFeeItem alterRoomHasFeeItem = null;
		for (AlterRoomHasFeeItem hasFeeItem : hasFeeItemList) {
			//查询是否已存在
			alterRoomHasFeeItem = new AlterRoomHasFeeItem();
			alterRoomHasFeeItem.settRealRoomId(hasFeeItem.gettRealRoomId());
			alterRoomHasFeeItem.settAlterPeriodFeeItemId(hasFeeItem.gettAlterPeriodFeeItemId());
			List<AlterRoomHasFeeItem> alterRoomHasFeeItems = alterRoomHasFeeItemBaseService.getAlterRoomHasFeeItemByCondition(MapConverter.toMap(alterRoomHasFeeItem));

			if (alterRoomHasFeeItems.size() > 0) {
				alterRoomHasFeeItems.get(0).setAmount(hasFeeItem.getAmount());
				updList.add(alterRoomHasFeeItems.get(0));
			} else {
				insertList.add(hasFeeItem);
			}
		}
		//数据获取完处理
		if (updList.size() > 0) {
			alterRoomHasFeeItemBaseService.updateAlterRoomHasFeeItemBatch(updList);
		}
		if (insertList.size() > 0) {
			List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_alter_room_has_fee_item, insertList.size());
			for (int i = 0; i < insertList.size(); i++) {
				insertList.get(i).setId(ids.get(i));
			}
			alterRoomHasFeeItemBaseService.insertAlterRoomHasFeeItemBatch(insertList);
		}

		alterPeriodDataDetail = alterPeriodDataDetailBaseService.
				getAlterPeriodDataDetailBySeqId(alterPeriodDataDetail.getId());
		//重新计算滞纳金
		latefeeCalculateService.calculateByRoom(alterPeriodDataDetail.getId(), alterPeriodDataDetail.gettAlterPeriodCfgId());
	}
	
	
    public Long getNeedCalculateLatefeeSum(BigInteger realRoomId) {
        return alterPeriodDao.getNeedCalculateLatefeeSum(realRoomId);
    }

    
    public PropertyAlterBillInfo getAlterPeriodDetail(BigInteger realRoomId) {
        return alterPeriodDao.getAlterPeriodDetail(realRoomId);
    }

    
    public List<AlterPeriodCfg> getAlterPeriodCfgList(Map<String, Object> param, PageModel pageModel) {
        return alterPeriodDao.getAlterPeriodCfgList(param, pageModel) ;
    }

    
    public Integer getAlterPeriodCfgCount(Map<String, Object> param) {
        return alterPeriodDao.getAlterPeriodCfgCount(param);
    }

    
    public List<RoomAlterPeroidDetail> getRoomAlterPeroidList(BigInteger gbId, String bName, String unitName, String roomNum, PageModel pageModel) {
        return alterPeriodDao.getRoomAlterPeroidList(gbId,  bName,  unitName,  roomNum,  pageModel);
    }

    
    public Integer getRoomAlterPeroidCount(BigInteger gbId, String bName, String unitName, String roomNum) {
        return alterPeriodDao.getRoomAlterPeroidCount( gbId,  bName,  unitName,  roomNum);
    }

    
    public Long getPreferentialRandom(BigInteger userId, BigInteger realRoomId, Long amt, Integer month) {
        return alterPeriodDao.getPreferentialRandom( userId,  realRoomId,  amt,  month);
    }

	public Map<String, BigInteger> getRoomStrByGbId(BigInteger gbId) {
		List<Map<String, Object>> list = alterPeriodDao.getRoomStrByGbId(gbId);
		Map<String, BigInteger> resMap = new HashMap<String, BigInteger>();
		for (Map<String, Object> map : list) {
			if(!DataUtil.isEmpty(map.get("room"))) {
				resMap.put(map.get("room").toString(),BigInteger.valueOf(Long.valueOf(map.get("realRoomId").toString())));
			}
		}
		return resMap;
	}
}
