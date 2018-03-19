package com.cnfantasia.server.ms.propertyPayConfig.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.service.IMrCalculateRuleCfgBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.service.IMrFeeItemBaseService;
import com.cnfantasia.server.domainbase.mrFeeItem.service.MrFeeItemBaseService;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.service.IMrFeeItemFormulaBaseService;
import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;
import com.cnfantasia.server.domainbase.mrStandardBuilding.service.IMrStandardBuildingBaseService;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.service.IMrStandardGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;
import com.cnfantasia.server.domainbase.mrStandardRoom.service.IMrStandardRoomBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoom.service.IRealRoomBaseService;
import com.cnfantasia.server.ms.propertyPayConfig.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.alterPeriodCfg.dao.IAlterPeriodCfgBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodCfg.entity.AlterPeriodCfg;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.dao.IAlterPeriodFeeItemBaseDao;
import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.tmpFeeItem.dao.ITmpFeeItemBaseDao;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.ms.groupBuilding.dao.IGroupBuildingDao;
import com.cnfantasia.server.ms.payBill.constant.PropIconUtil;
import com.cnfantasia.server.ms.propertyPayConfig.dao.IPropertyPayConfigDao;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 收费项配置
 * 
 * @author liyulin
 * @version 1.0 2016年10月24日 下午5:07:21
 */
public class PropertyPayConfigService implements IPropertyPayConfigService {
	private IAlterPeriodFeeItemBaseDao alterPeriodFeeItemBaseDao;
	private IAlterPeriodCfgBaseDao alterPeriodCfgBaseDao;
	private IUuidManager uuidManager;
	private IPropertyPayConfigDao propertyPayConfigDao;
	@Resource
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	@Resource
	private IGroupBuildingDao groupBuildingDao;
	@Resource
	private ITmpFeeItemBaseDao tmpFeeItemBaseDao;
	@Resource
	private IMrCalculateRuleCfgBaseService mrCalculateRuleCfgBaseService;
	@Resource
	private IMrFeeItemFormulaBaseService mrFeeItemFormulaBaseService;
	@Resource
	private IMrStandardRoomBaseService mrStandardRoomBaseService;
	@Resource
	private IMrStandardBuildingBaseService mrStandardBuildingBaseService;
	@Resource
	private IMrStandardGroupBuildingBaseService mrStandardGroupBuildingBaseService;
	@Resource
	private IMrFeeItemBaseService mrFeeItemBaseService;

	public IAlterPeriodFeeItemBaseDao getAlterPeriodFeeItemBaseDao() {
		return alterPeriodFeeItemBaseDao;
	}

	public void setAlterPeriodFeeItemBaseDao(IAlterPeriodFeeItemBaseDao alterPeriodFeeItemBaseDao) {
		this.alterPeriodFeeItemBaseDao = alterPeriodFeeItemBaseDao;
	}

	public IAlterPeriodCfgBaseDao getAlterPeriodCfgBaseDao() {
		return alterPeriodCfgBaseDao;
	}

	public void setAlterPeriodCfgBaseDao(IAlterPeriodCfgBaseDao alterPeriodCfgBaseDao) {
		this.alterPeriodCfgBaseDao = alterPeriodCfgBaseDao;
	}

	public IUuidManager getUuidManager() {
		return uuidManager;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public IPropertyPayConfigDao getPropertyPayConfigDao() {
		return propertyPayConfigDao;
	}

	public void setPropertyPayConfigDao(IPropertyPayConfigDao propertyPayConfigDao) {
		this.propertyPayConfigDao = propertyPayConfigDao;
	}

	/**
	 * 根据条件查询(选择周期收费项)信息
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> selectAlterPeriodFeeItemByCondition(Map<String, Object> paramMap) {
		return propertyPayConfigDao.selectAlterPeriodFeeItemByCondition(paramMap);
	}
	
	/**
	 * 保存收费项配置
	 * 
	 * @param gbId
	 * @param alterPeriodFeeItems
	 * @param billName
	 *@param periodMonths @return
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public boolean savePayConfig(BigInteger gbId, String alterPeriodFeeItems, String billName, String periodMonths) {
		// t_alter_period_cfg为null：修复强哥
		AlterPeriodCfg alterPeriodCfg = propertyPayConfigDao.getAlterPeriodCfgWithLock(gbId);
		if (alterPeriodCfg == null || alterPeriodCfg.getId() == null) {
			alterPeriodCfg = new AlterPeriodCfg();
			alterPeriodCfg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_alter_period_cfg));
			alterPeriodCfg.setLatefeeStatus(2);
			alterPeriodCfg.setLatefeeRate(null);
			alterPeriodCfg.settGbId(gbId);
			alterPeriodCfg.setCalculateExpression(null);
			
			alterPeriodCfgBaseDao.insertAlterPeriodCfg(alterPeriodCfg);
		}

		return saveCommPayConfig(gbId, alterPeriodFeeItems, billName, periodMonths);
	}

	private boolean saveCommPayConfig(BigInteger gbId, String alterPeriodFeeItems, String billName, String periodMonths) {
        //保存账单名称及缴费月份数:月份可以为空 但是账单名称不会为空
        if(!DataUtil.isEmpty(billName)) {
            GroupBuilding groupBuilding = groupBuildingDao.selectGroupBuildingBySeqId(gbId);
            //保存账单名称
            Map<String, Object> resMap = new HashMap<String, Object>();
            resMap.put("paytimeType", 2);
            resMap.put("isPropFee", 1);
            resMap.put("gbId", gbId);
            resMap.put("name", billName);
            List<PayBillType> payBillTypes = payBillTypeBaseDao.selectPayBillTypeByCondition(resMap, false);
            PayBillType payBillType = new PayBillType();
            if(!DataUtil.isEmpty(payBillTypes)) {//存在更新
                payBillType = payBillTypes.get(0);
                payBillType.setName(billName);
                payBillType.setIcon(PropIconUtil.getBillIcon(billName));
                payBillType.setPreferStatus(groupBuilding.getIsPrefer());//v349版本已经废弃，为保证一致性 还在维护这个字段
                payBillType.setActiveStatus(groupBuilding.getPropfeeCanpay());
                payBillType.setLastUpdTime(DateUtils.getCurrentDate());
				//多加 为了修复原来的存在bug的数据
				payBillType.setPaytimeType(2);
				payBillType.setIsPropFee(1);
                payBillTypeBaseDao.updatePayBillType(payBillType);
            } else {//新增
                payBillType.setId(uuidManager.getNextUuidBigInteger("t_pay_bill_type"));
                payBillType.setName(billName);
                payBillType.setGbId(gbId);
                payBillType.setIcon(PropIconUtil.getBillIcon(billName));
                payBillType.setPreferStatus(groupBuilding.getIsPrefer());//v349版本已经废弃，为保证一致性 还在维护这个字段
                payBillType.setActiveStatus(groupBuilding.getPropfeeCanpay());
                payBillType.setPaytimeType(2);
                payBillType.setIsPropFee(1);
                payBillType.setLastUpdTime(DateUtils.getCurrentDate());
                payBillTypeBaseDao.insertPayBillType(payBillType);
            }

            //保存缴费月份配置
            if(!DataUtil.isEmpty(periodMonths)) {
                groupBuilding.setPeriodMonths(periodMonths);
                groupBuildingDao.updateGroupBuilding(groupBuilding);
            }
        }

		if (StringUtils.isNotBlank(alterPeriodFeeItems) && !"[]".equals(alterPeriodFeeItems)) {
			BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
			String now = DateUtils.getCurrentDate();

			List<AlterPeriodFeeItem> alterPeriodFeeItemList = JSON.parseArray(alterPeriodFeeItems, AlterPeriodFeeItem.class);
			int insertSize = 0;
			for (AlterPeriodFeeItem alterPeriodFeeItem : alterPeriodFeeItemList) {
				if (alterPeriodFeeItem.getId() == null) {
					insertSize++;
				}
			}

			List<BigInteger> ids = null;
			if (insertSize > 0) {
				ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_alter_period_fee_item, alterPeriodFeeItemList.size());
			}

			List<AlterPeriodFeeItem> insertAlterPeriodFeeItems = new ArrayList<AlterPeriodFeeItem>();
			List<AlterPeriodFeeItem> updateAlterPeriodFeeItems = new ArrayList<AlterPeriodFeeItem>();
			int insertIndex = 0;
			for (AlterPeriodFeeItem alterPeriodFeeItem : alterPeriodFeeItemList) {
				alterPeriodFeeItem.settGbId(gbId);
				if (alterPeriodFeeItem.getId() == null) {// insert
					alterPeriodFeeItem.setId(ids.get(insertIndex++));
					alterPeriodFeeItem.setSys0AddUser(userId);
					alterPeriodFeeItem.setSys0AddTime(now);

					insertAlterPeriodFeeItems.add(alterPeriodFeeItem);
				} else {// update
					alterPeriodFeeItem.setSys0UpdUser(userId);
					alterPeriodFeeItem.setSys0UpdTime(now);

					updateAlterPeriodFeeItems.add(alterPeriodFeeItem);
				}
			}

			int affectedCount = 0;
			if (insertAlterPeriodFeeItems.size() > 0) {
				affectedCount = alterPeriodFeeItemBaseDao.insertAlterPeriodFeeItemBatch(insertAlterPeriodFeeItems);
			}

			if (updateAlterPeriodFeeItems.size() > 0) {
				affectedCount += alterPeriodFeeItemBaseDao.updateAlterPeriodFeeItemBatch(updateAlterPeriodFeeItems);
			}

			return affectedCount > 0;
		} else {
			return true;
		}
	}

	/**
	 * 保存滞纳金配置
	 * 
	 * @param lateMoneyConfigRequest
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED)
	public boolean saveLateMoneyConfig(LateMoneyConfigRequest lateMoneyConfigRequest) {
		// 加锁，避免并发
		// 如果没有，则插入；有，则修改
		AlterPeriodCfg oldAlterPeriodCfg = propertyPayConfigDao.getAlterPeriodCfgWithLock(lateMoneyConfigRequest.getGbId());

		saveCommPayConfig(lateMoneyConfigRequest.getGbId(), lateMoneyConfigRequest.getAlterPeriodFeeItems(), lateMoneyConfigRequest.getBillName(), lateMoneyConfigRequest.getPeriodMonths());

		BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
		String now = DateUtils.getCurrentDate();
		AlterPeriodCfg alterPeriodCfg = new AlterPeriodCfg();
		if (oldAlterPeriodCfg != null && oldAlterPeriodCfg.getId() != null) {
			// update
			alterPeriodCfg.setId(oldAlterPeriodCfg.getId());
			alterPeriodCfg.setLatefeeStatus(lateMoneyConfigRequest.getLatefeeStatus());
			alterPeriodCfg.setLatefeeRate(lateMoneyConfigRequest.getLatefeeRate());
			alterPeriodCfg.setCalculateExpression(lateMoneyConfigRequest.getCalExp());
			alterPeriodCfg.setSys0UpdUser(userId);
			alterPeriodCfg.setSys0UpdTime(now);

			propertyPayConfigDao.updateAlterPeriodCfg(alterPeriodCfg);
			return true;
		} else {
			// insert
			alterPeriodCfg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_alter_period_cfg));
			alterPeriodCfg.setLatefeeStatus(lateMoneyConfigRequest.getLatefeeStatus());
			alterPeriodCfg.setLatefeeRate(lateMoneyConfigRequest.getLatefeeRate());
			alterPeriodCfg.settGbId(lateMoneyConfigRequest.getGbId());
			alterPeriodCfg.setCalculateExpression(lateMoneyConfigRequest.getCalExp());
			alterPeriodCfg.setSys0AddUser(userId);
			alterPeriodCfg.setSys0AddTime(now);

			return alterPeriodCfgBaseDao.insertAlterPeriodCfg(alterPeriodCfg) > 0;
		}
	}
	
	@Override
	public String deleteItem(BigInteger itemId, BigInteger gbId) {
		//删除费用项
		int i = alterPeriodFeeItemBaseDao.deleteAlterPeriodFeeItemLogic(itemId);
		//删除明细信息
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("alterPeriodFeeItemId", itemId);
		paramMap.put("tGbId", gbId);
		int j = propertyPayConfigDao.deleteAlterRoomHasFeeItemByItemId(paramMap);

		int k = propertyPayConfigDao.deleteAllDetailByGb(paramMap);

		return i > 0 || j > 0 || k > 0 ? "清除成功！" : "清除失败！";
	}

	@Override
	public String deleteAllDetailByGb(BigInteger gbId) {
		String res = "删除成功！";
		Map paraMap = new HashMap<Object, String>();
		paraMap.put("tGbId", gbId);

		int j = propertyPayConfigDao.deleteAllDetailByGb(paraMap);

		return j > 0 ? "删除成功！" : "删除失败！";
	}
	
	/**
     * 保存临时收费项配置
     * @param gbId
     * @param items 收费项
     * @return
     */
	@Override
	@Transactional(propagation = Propagation.NESTED)
    public boolean saveTmpFeeItem(BigInteger gbId, String items) {
        if (StringUtils.isNotBlank(items) && !"[]".equals(items)) {
            BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
            String now = DateUtils.getCurrentDate();
            List<TmpFeeItem> tmpFeeItemList = JSON.parseArray(items, TmpFeeItem.class);
            int insertSize = 0;
            for (TmpFeeItem tmpFeeItem : tmpFeeItemList) {
                if (tmpFeeItem.getId() == null) {
                    insertSize++;
                }
            }
            List<BigInteger> ids = null;
            if (insertSize > 0) {
                ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_tmp_fee_item, tmpFeeItemList.size());
            }

            List<TmpFeeItem> insertFeeItems = new ArrayList<TmpFeeItem>();
            List<TmpFeeItem> updateFeeItems = new ArrayList<TmpFeeItem>();
            for (int i=0; i<tmpFeeItemList.size(); i++) {
            	TmpFeeItem tmpFeeItem = tmpFeeItemList.get(i);
            	tmpFeeItem.settGbId(gbId);
                if (tmpFeeItem.getId() == null) {// insert
                	tmpFeeItem.setId(ids.get(i));
                	tmpFeeItem.setSys0AddUser(userId);
                	tmpFeeItem.setSys0AddTime(now);
                	tmpFeeItem.settGbId(gbId);

                    insertFeeItems.add(tmpFeeItem);
                } else {// update
                	tmpFeeItem.setSys0UpdUser(userId);
                    tmpFeeItem.setSys0UpdTime(now);
                    tmpFeeItem.settGbId(gbId);
                    updateFeeItems.add(tmpFeeItem);
                }
            }

            int affectedCount = 0;
            if (insertFeeItems.size() > 0) {
                affectedCount = tmpFeeItemBaseDao.insertTmpFeeItemBatch(insertFeeItems);
            }

            if (updateFeeItems.size() > 0) {
                affectedCount += tmpFeeItemBaseDao.updateTmpFeeItemBatch(updateFeeItems);
            }

            return affectedCount > 0;
        }
        return false;
    }

	@Override
	public List<MrFeeItemEntity> getMrFeeItemEntityByGbId(BigInteger gbId) {
		return propertyPayConfigDao.getMrFeeItemEntityByGbId(gbId);
	}

	@Override
	public void saveMrCalculateRuleCfg(CalculateRuleEntity calculateRuleEntity, BigInteger gbId) {
		BigInteger calculateRuleCfgId = null;
		if(calculateRuleEntity.getId() != null) {//新增t_mr_calculate_rule_cfg
			calculateRuleCfgId = calculateRuleEntity.getId();
			calculateRuleEntity.setSys0UpdTime(DateUtils.getCurrentDate());
			mrCalculateRuleCfgBaseService.updateMrCalculateRuleCfg(calculateRuleEntity);
		} else {
			calculateRuleCfgId = uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_calculate_rule_cfg);
			calculateRuleEntity.setId(calculateRuleCfgId);
			calculateRuleEntity.setSys0AddTime(DateUtils.getCurrentDate());
			mrCalculateRuleCfgBaseService.insertMrCalculateRuleCfg(calculateRuleEntity);
		}
		saveMrFeeItemFormula(calculateRuleEntity.getMrFeeItemFormulas(), calculateRuleCfgId, calculateRuleEntity.gettMrFeeItemId());

		//初始化小区，楼栋，房间抄表标准数据
		HashMap<String, Object> paraMap = new HashMap<>();
		paraMap.put("gbId", gbId);
		List<MrFeeItem> mrFeeItemByCondition = mrFeeItemBaseService.getMrFeeItemByCondition(paraMap);
		if(!DataUtil.isEmpty(mrFeeItemByCondition)) {
			InitMrStandardData initMrStandardData = new InitMrStandardData(mrFeeItemByCondition, gbId);
			new Thread(initMrStandardData).start();
		}
	}

	private void saveMrFeeItemFormula(List<MrFeeItemFormula> mrFeeItemFormulas, BigInteger calculateRuleCfgId, BigInteger mrFeeItemId) {
		List<MrFeeItemFormula> addList = new ArrayList<MrFeeItemFormula>();
		List<MrFeeItemFormula> updateList = new ArrayList<MrFeeItemFormula>();
		List<BigInteger> ids = uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_fee_item_formula, mrFeeItemFormulas.size());
		int i = 0;
		for (MrFeeItemFormula mrFeeItemFormula : mrFeeItemFormulas) {
			if(mrFeeItemFormula.getId() != null) {
				mrFeeItemFormula.setSys0UpdTime(DateUtils.getCurrentDate());
				mrFeeItemFormula.settMrCalculateRuleCfgId(calculateRuleCfgId);
				mrFeeItemFormula.settMrFeeItemFId(mrFeeItemId);
				updateList.add(mrFeeItemFormula);
			} else {
				mrFeeItemFormula.setId(ids.get(i));
				mrFeeItemFormula.setSys0AddTime(DateUtils.getCurrentDate());
				mrFeeItemFormula.settMrCalculateRuleCfgId(calculateRuleCfgId);
				mrFeeItemFormula.settMrFeeItemFId(mrFeeItemId);
				addList.add(mrFeeItemFormula);
			}
			i++;
		}
		if(!DataUtil.isEmpty(addList)) {
			mrFeeItemFormulaBaseService.insertMrFeeItemFormulaBatch(addList);
		}
		if(!DataUtil.isEmpty(updateList)) {
			mrFeeItemFormulaBaseService.updateMrFeeItemFormulaBatch(updateList);
		}
	}

	/**
	 * 删除计费规则
	 * @param id
     */
	@Override
	public int delMrCalculateRuleCfg(BigInteger id) {
		BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
		return propertyPayConfigDao.delMrCalculateRuleCfg(userId, id);
	}

	/**
	 * 根据小区ID查询抄表收费标准的楼栋房间数据
	 * @param gbId
	 * @return
     */
	@Override
	public List<GroupBuildingTreeEntity> getRoomTreeByGbId(BigInteger gbId) {
		return propertyPayConfigDao.getRoomTreeByGbId(gbId);
	}

	/**
	 * 查询抄表收费标准
	 * @param gbId
	 * @param buildingId
	 * @param realRoomId
     * @return
     */
	@Override
	public JsonResponse getMrStandardList(BigInteger gbId, BigInteger buildingId, BigInteger realRoomId) {
		JsonResponse jsonResponse = new JsonResponse();
		if(realRoomId != null) {//查询房间
			Map<String, Object> data = new HashMap<String, Object>();
			//房间数据
			List<MrStandardRoomEntity> mrStandardRoomEntities = propertyPayConfigDao.getMrStandardRoomByRealRoomId(realRoomId);
			data.put("mrStandardRooms", mrStandardRoomEntities);
			//费用项--计算规则
			List<CalculateRuleForStandar> itemFeesHascalculateRules = propertyPayConfigDao.getItemFeesHascalculateRules(realRoomId);
			data.put("itemFeesHascalculateRules", itemFeesHascalculateRules);

			jsonResponse.setDataValue(data);
		} else if(buildingId != null) {//查询楼栋
			List<MrStandardBuildingEntity> mrStandardBuildings = propertyPayConfigDao.getMrStandardBuildingByBuildingId(buildingId);
			jsonResponse.setDataValue(mrStandardBuildings);
		} else if(gbId != null) {//查询小区
			List<MrStandardGroupBuildingEntity> mrStandardGroupBuildings = propertyPayConfigDao.getMrStandardBuildingBygbId(gbId);
			jsonResponse.setDataValue(mrStandardGroupBuildings);
		}

		return jsonResponse;
	}

	/**
	 * 保存抄表收费标准
	 * @param standardSaveParam
	 * @return
     */
	@Override
	public String saveMrStandard(StandardSaveParam standardSaveParam) {
		//保存小区
		if(standardSaveParam.getStandardType() == 1) {
			return saveStandardGroupBuilding(standardSaveParam);
		} else if(standardSaveParam.getStandardType() == 2) {//保存楼栋
			return saveStandardBuilding(standardSaveParam);
		} else if(standardSaveParam.getStandardType() == 3) {//保存房间
			return saveStandardRoom(standardSaveParam);
		}

		return "保存失败！";
	}

	private String saveStandardRoom(StandardSaveParam standardSaveParam) {
		BigInteger standardRoomId = standardSaveParam.getId();

		MrStandardRoom mrStandardRoom = new MrStandardRoom();
		mrStandardRoom.setId(standardRoomId);
		mrStandardRoom.settMrCalculateRuleCfgId(standardSaveParam.getMrCalculateRuleCfgId());
		mrStandardRoom.settMrFeeItemId(standardSaveParam.getMrFeeItemId());
		mrStandardRoom.setMultiplierTimes(standardSaveParam.getMultiplierTimes());
		mrStandardRoom.setMrName(standardSaveParam.getMrName() != null ? standardSaveParam.getMrName().trim() : standardSaveParam.getMrName());

		if(standardRoomId == null) {
			standardRoomId = uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_standard_room);
			mrStandardRoom.setId(standardRoomId);
			mrStandardRoom.settGbId(standardSaveParam.getGbId());
			mrStandardRoom.settBuildingId(standardSaveParam.getBuildingId());
			mrStandardRoom.settRealRoomId(standardSaveParam.getRealRoomId());
			mrStandardRoom.setSys0AddTime(DateUtils.getCurrentDate());
			mrStandardRoom.setSys0AddUser(UserContext.getOperIdBigInteger());

			int i = mrStandardRoomBaseService.insertMrStandardRoom(mrStandardRoom);
			return i >=0 ? "保存成功！" : "保存失败！";
		} else {
			//校验:同一个房间不能出现收费项和计费表名称相同情况
			MrStandardRoom standardRoom = mrStandardRoomBaseService.getMrStandardRoomBySeqId(standardRoomId);
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("tRealRoomId", standardRoom.gettRealRoomId());
			paraMap.put("tMrFeeItemId", standardSaveParam.getMrFeeItemId());
			paraMap.put("mrName", standardSaveParam.getMrName() != null ? standardSaveParam.getMrName().trim() : standardSaveParam.getMrName());
			List<MrStandardRoom> mrStandardRoomList = mrStandardRoomBaseService.getMrStandardRoomByCondition(paraMap);
			int sameCount = 0;
			for (MrStandardRoom mrStandardRoom01 : mrStandardRoomList) {
				if(!mrStandardRoom01.getId().equals(standardRoomId)) {
					sameCount += 1;
				}
			}
			if(sameCount > 0) {
				return "保存失败！【收费项】和【计费表名称】重复";
			}

			mrStandardRoom.setSys0UpdTime(DateUtils.getCurrentDate());
			mrStandardRoom.setSys0UpdUser(UserContext.getOperIdBigInteger());

			int i = mrStandardRoomBaseService.updateMrStandardRoom(mrStandardRoom);
			return i >=0 ? "保存成功！" : "保存失败！";
		}

	}

	private String saveStandardBuilding(StandardSaveParam standardSaveParam) {
		MrStandardBuilding mrStandardBuilding = new MrStandardBuilding();
		mrStandardBuilding.setId(standardSaveParam.getId());
		mrStandardBuilding.settMrCalculateRuleCfgId(standardSaveParam.getMrCalculateRuleCfgId());
		mrStandardBuilding.setSys0UpdTime(DateUtils.getCurrentDate());
		mrStandardBuilding.setSys0UpdUser(UserContext.getOperIdBigInteger());

		int i = mrStandardBuildingBaseService.updateMrStandardBuilding(mrStandardBuilding);

		//更新房间
		MrStandardBuilding mrStandardBuildingBySeqId = mrStandardBuildingBaseService.getMrStandardBuildingBySeqId(standardSaveParam.getId());
		BigInteger gbId = mrStandardBuildingBySeqId.gettGbId();
		BigInteger buildingId = mrStandardBuildingBySeqId.gettBuildingId();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tGbId", gbId);
		paraMap.put("tBuildingId", buildingId);
		paraMap.put("tMrFeeItemId", mrStandardBuildingBySeqId.gettMrFeeItemId());
		List<MrStandardRoom> mrStandardRoomList = mrStandardRoomBaseService.getMrStandardRoomByCondition(paraMap);
		for (MrStandardRoom mrStandardRoom : mrStandardRoomList) {
			mrStandardRoom.settMrCalculateRuleCfgId(standardSaveParam.getMrCalculateRuleCfgId());
			mrStandardRoom.setSys0UpdTime(DateUtils.getCurrentDate());
			mrStandardRoom.setSys0UpdUser(UserContext.getOperIdBigInteger());
		}
		if(!DataUtil.isEmpty(mrStandardRoomList)){
			mrStandardRoomBaseService.updateMrStandardRoomBatch(mrStandardRoomList);
		}

		return i >=0 ? "保存成功！" : "保存失败！";
	}

	private String saveStandardGroupBuilding(StandardSaveParam standardSaveParam) {
		MrStandardGroupBuilding mrStandardGroupBuilding = new MrStandardGroupBuilding();
		mrStandardGroupBuilding.setId(standardSaveParam.getId());
		mrStandardGroupBuilding.settMrCalculateRuleCfgId(standardSaveParam.getMrCalculateRuleCfgId());
		mrStandardGroupBuilding.setSys0UpdTime(DateUtils.getCurrentDate());
		mrStandardGroupBuilding.setSys0UpdUser(UserContext.getOperIdBigInteger());

		int i = mrStandardGroupBuildingBaseService.updateMrStandardGroupBuilding(mrStandardGroupBuilding);

		MrStandardGroupBuilding mrStandardGroupBuildingBySeqId = mrStandardGroupBuildingBaseService.getMrStandardGroupBuildingBySeqId(standardSaveParam.getId());
		BigInteger gbId = mrStandardGroupBuildingBySeqId.gettGbId();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tGbId", gbId);
		paraMap.put("tMrFeeItemId", mrStandardGroupBuildingBySeqId.gettMrFeeItemId());

		//更新楼栋
		List<MrStandardBuilding> mrStandardBuildingList = mrStandardBuildingBaseService.getMrStandardBuildingByCondition(paraMap);
		for (MrStandardBuilding mrStandardBuilding : mrStandardBuildingList) {
			mrStandardBuilding.settMrCalculateRuleCfgId(standardSaveParam.getMrCalculateRuleCfgId());
			mrStandardBuilding.setSys0UpdTime(DateUtils.getCurrentDate());
			mrStandardBuilding.setSys0UpdUser(UserContext.getOperIdBigInteger());
		}
		if(!DataUtil.isEmpty(mrStandardBuildingList)){
			mrStandardBuildingBaseService.updateMrStandardBuildingBatch(mrStandardBuildingList);
		}

		//更新房间
		List<MrStandardRoom> mrStandardRoomList = mrStandardRoomBaseService.getMrStandardRoomByCondition(paraMap);
		for (MrStandardRoom mrStandardRoom : mrStandardRoomList) {
			mrStandardRoom.settMrCalculateRuleCfgId(standardSaveParam.getMrCalculateRuleCfgId());
			mrStandardRoom.setSys0UpdTime(DateUtils.getCurrentDate());
			mrStandardRoom.setSys0UpdUser(UserContext.getOperIdBigInteger());
		}
		if(!DataUtil.isEmpty(mrStandardRoomList)){
			mrStandardRoomBaseService.updateMrStandardRoomBatch(mrStandardRoomList);
		}

		return i >=0 ? "保存成功！" : "保存失败！";
	}

	@Override
	public int delMrStandard(BigInteger id) {
		return mrStandardRoomBaseService.deleteMrStandardRoomLogic(id);
	}

	@Override
	public List<CalculateRuleEntity> getCalculateRuleByStandardRooId(BigInteger standardRoomId) {
		return propertyPayConfigDao.getCalculateRuleByStandardRooId(standardRoomId);
	}

	@Override
	public Integer getCalculatePriceTypeByStandardRoomId(BigInteger standardRoomId) {
		return propertyPayConfigDao.getCalculatePriceTypeByStandardRoomId(standardRoomId);
	}

	@Override
	public Boolean calculateRuleIsUse(BigInteger id) {
		Integer useCounts = 0;//propertyPayConfigDao.getCalculateRuleIsUseList(id);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tMrCalculateRuleCfgId", id);
        List<MrStandardGroupBuilding> mrStandardGroupBuildingByCondition = mrStandardGroupBuildingBaseService.getMrStandardGroupBuildingByCondition(paraMap);
        if(!DataUtil.isEmpty(mrStandardGroupBuildingByCondition)) {
            useCounts += mrStandardGroupBuildingByCondition.size();
        }
        List<MrStandardBuilding> mrStandardBuildingByCondition = mrStandardBuildingBaseService.getMrStandardBuildingByCondition(paraMap);
        if(!DataUtil.isEmpty(mrStandardBuildingByCondition)) {
            useCounts += mrStandardBuildingByCondition.size();
        }
        List<MrStandardRoom> mrStandardRoomByCondition = mrStandardRoomBaseService.getMrStandardRoomByCondition(paraMap);
        if(!DataUtil.isEmpty(mrStandardRoomByCondition)) {
            useCounts += mrStandardRoomByCondition.size();
        }

        return useCounts > 0;
	}
}
/**
 * 异步初始化小区，楼栋，房间数据
 */
class InitMrStandardData implements Runnable {
	private List<MrFeeItem> feeItemList;
	private BigInteger gbId;
	private IBuildingBaseService buildingBaseService;
	private IRealRoomBaseService realRoomBaseService;
	private IMrStandardRoomBaseService mrStandardRoomBaseService;
	private IMrStandardBuildingBaseService mrStandardBuildingBaseService;
	private IMrStandardGroupBuildingBaseService mrStandardGroupBuildingBaseService;

	public InitMrStandardData(List<MrFeeItem> feeItemList, BigInteger gbId) {
		this.feeItemList = feeItemList;
		this.gbId = gbId;
		buildingBaseService = (IBuildingBaseService) CnfantasiaCommbusiUtil.getBeanManager("buildingBaseService");
		realRoomBaseService = (IRealRoomBaseService) CnfantasiaCommbusiUtil.getBeanManager("realRoomBaseService");
		mrStandardRoomBaseService = (IMrStandardRoomBaseService) CnfantasiaCommbusiUtil.getBeanManager("mrStandardRoomBaseService");
		mrStandardBuildingBaseService = (IMrStandardBuildingBaseService) CnfantasiaCommbusiUtil.getBeanManager("mrStandardBuildingBaseService");
		mrStandardGroupBuildingBaseService = (IMrStandardGroupBuildingBaseService) CnfantasiaCommbusiUtil.getBeanManager("mrStandardGroupBuildingBaseService");
	}

	@Override
	public void run() {
		//获取门牌楼栋信息小区对应的
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tGroupBuildingFId", gbId);
		List<Building> buildingList = buildingBaseService.getBuildingByCondition(paraMap);

		paraMap.clear();
		paraMap.put("tGbId", gbId);
		List<MrStandardGroupBuilding> mrStandardGroupBuildingByCondition = mrStandardGroupBuildingBaseService.getMrStandardGroupBuildingByCondition(paraMap);
		List<MrStandardBuilding> mrStandardBuildingByCondition = mrStandardBuildingBaseService.getMrStandardBuildingByCondition(paraMap);
		List<MrStandardRoom> mrStandardRoomByCondition = mrStandardRoomBaseService.getMrStandardRoomByCondition(paraMap);

		//组装收费标准数据
		List<MrStandardRoom> mrStandardRooms = new ArrayList<MrStandardRoom>();
		List<MrStandardBuilding> mrStandardBuildings = new ArrayList<MrStandardBuilding>();
		List<MrStandardGroupBuilding> mrStandardGroupBuildings = new ArrayList<MrStandardGroupBuilding>();
		for (MrFeeItem mrFeeItem : feeItemList) {
			//小区标准
			boolean mrStandardGroupBuildingIsExit = false;//默认 不存在
			for (MrStandardGroupBuilding mrStandardGroupBuilding0 : mrStandardGroupBuildingByCondition) {
				if (mrStandardGroupBuilding0.gettMrFeeItemId().equals(mrFeeItem.getId())) {
					mrStandardGroupBuildingIsExit = true;
					break;
				}
			}

			if (!mrStandardGroupBuildingIsExit) {
				MrStandardGroupBuilding mrStandardGroupBuilding = new MrStandardGroupBuilding();
				mrStandardGroupBuilding.settMrFeeItemId(mrFeeItem.getId());
				mrStandardGroupBuilding.settGbId(gbId);
				mrStandardGroupBuilding.setSys0AddTime(DateUtils.getCurrentDate());
				mrStandardGroupBuildings.add(mrStandardGroupBuilding);
			}

			//楼栋标准
			for (Building building : buildingList) {
				boolean mrStandardBuildingIsExit = false;
				for (MrStandardBuilding mrStandardBuilding : mrStandardBuildingByCondition) {
					if (mrStandardBuilding.gettBuildingId().equals(building.getId()) && mrStandardBuilding.gettMrFeeItemId().equals(mrFeeItem.getId())) {
						mrStandardBuildingIsExit = true;
						break;
					}
				}

				if (!mrStandardBuildingIsExit) {
					MrStandardBuilding mrStandardBuilding = new MrStandardBuilding();
					mrStandardBuilding.settGbId(gbId);
					mrStandardBuilding.settMrFeeItemId(mrFeeItem.getId());
					mrStandardBuilding.settBuildingId(building.getId());
					mrStandardBuilding.setSys0AddTime(DateUtils.getCurrentDate());
					mrStandardBuildings.add(mrStandardBuilding);
				}

				//房间标准
				paraMap.put("tBuildingFId", building.getId());
				List<RealRoom> realRooms = realRoomBaseService.getRealRoomByCondition(paraMap);
				for (RealRoom realRoom : realRooms) {
					boolean mrStandardRoomIsExit = false;
					for (MrStandardRoom mrStandardRoom : mrStandardRoomByCondition) {
						if (mrStandardRoom.gettRealRoomId().equals(realRoom.getId()) && mrStandardRoom.gettMrFeeItemId().equals(mrFeeItem.getId())) {
							mrStandardRoomIsExit = true;
							break;
						}
					}

					if (!mrStandardRoomIsExit) {
						MrStandardRoom mrStandardRoom = new MrStandardRoom();
						mrStandardRoom.settMrFeeItemId(mrFeeItem.getId());
						mrStandardRoom.settGbId(gbId);
						mrStandardRoom.settBuildingId(building.getId());
						mrStandardRoom.setMultiplierTimes(1.0);//默认倍率为1
						mrStandardRoom.setMrName("表01");//默认表名称
						mrStandardRoom.settRealRoomId(realRoom.getId());
						mrStandardRoom.setSys0AddTime(DateUtils.getCurrentDate());
						mrStandardRooms.add(mrStandardRoom);
					}
				}
			}
		}

		//保存数据
		if (!DataUtil.isEmpty(mrStandardGroupBuildings)) {
			CnfantasiaCommbusiUtil.newStandardList(mrStandardGroupBuildings, SEQConstants.t_mr_standard_group_building);
			mrStandardGroupBuildingBaseService.insertMrStandardGroupBuildingBatch(mrStandardGroupBuildings);
		}
		if (!DataUtil.isEmpty(mrStandardBuildings)) {
			CnfantasiaCommbusiUtil.newStandardList(mrStandardBuildings, SEQConstants.t_mr_standard_building);
			mrStandardBuildingBaseService.insertMrStandardBuildingBatch(mrStandardBuildings);
		}
		if (!DataUtil.isEmpty(mrStandardRooms)) {
			CnfantasiaCommbusiUtil.newStandardList(mrStandardRooms, SEQConstants.t_mr_standard_room);
			mrStandardRoomBaseService.insertMrStandardRoomBatch(mrStandardRooms);
		}

	}
}