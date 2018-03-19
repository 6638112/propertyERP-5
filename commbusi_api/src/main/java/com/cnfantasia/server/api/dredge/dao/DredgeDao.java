package com.cnfantasia.server.api.dredge.dao;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.commonBusiness.entity.OperateConfigRange;
import com.cnfantasia.server.api.dredge.constant.DredgeConstant;
import com.cnfantasia.server.api.dredge.entity.AccountAmount;
import com.cnfantasia.server.api.dredge.entity.ApplyListForMaster;
import com.cnfantasia.server.api.dredge.entity.ApplyWithdrawInfo;
import com.cnfantasia.server.api.dredge.entity.BlockForMaster;
import com.cnfantasia.server.api.dredge.entity.BlockWithDredgeService;
import com.cnfantasia.server.api.dredge.entity.CanWithdrawBill;
import com.cnfantasia.server.api.dredge.entity.CanWithdrawBill4Master;
import com.cnfantasia.server.api.dredge.entity.DredgeBill4Qsdj;
import com.cnfantasia.server.api.dredge.entity.DredgeBillEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForDetail;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForList;
import com.cnfantasia.server.api.dredge.entity.DredgeBillForMaster;
import com.cnfantasia.server.api.dredge.entity.DredgeDetails;
import com.cnfantasia.server.api.dredge.entity.DredgeMasterList4admin;
import com.cnfantasia.server.api.dredge.entity.DredgeMsgEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeParentType;
import com.cnfantasia.server.api.dredge.entity.DredgeProduct4Admin;
import com.cnfantasia.server.api.dredge.entity.DredgeProduct4Turn;
import com.cnfantasia.server.api.dredge.entity.DredgeProductEntity;
import com.cnfantasia.server.api.dredge.entity.DredgeProductView;
import com.cnfantasia.server.api.dredge.entity.DredgeTypeEntity;
import com.cnfantasia.server.api.dredge.entity.IncomeWithdrawRecorder;
import com.cnfantasia.server.api.dredge.entity.MasterInfo4Audit;
import com.cnfantasia.server.api.dredge.entity.MasterProfile;
import com.cnfantasia.server.api.dredge.entity.SMSInfo;
import com.cnfantasia.server.api.dredge.entity.SelfBuyProduct;
import com.cnfantasia.server.api.ebuy.entity.DredgeProductSpecEntity;
import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBillFollowRecord.entity.DredgeBillFollowRecord;
import com.cnfantasia.server.domainbase.dredgeType2nd.entity.DredgeType2nd;
import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.dao.DredgeWorkerHasDredgeTypeBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.entity.DredgeWorkerHasDredgeType;
import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.dao.DredgeWorkerServiceAddressBlockBaseDao;
import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity.DredgeWorkerServiceAddressBlock;
import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public class DredgeDao extends AbstractBaseDao {

	@Resource
	private DredgeWorkerServiceAddressBlockBaseDao dredgeWorkerServiceAddressBlockBaseDao;

	@Resource
	private DredgeWorkerHasDredgeTypeBaseDao dredgeWorkerHasDredgeTypeBaseDao;

	@Resource
	private IUuidManager uuidManager;

	@Resource
	private ISysParamManager sysParamManager;

	public List<DredgeBillForList> queryMyDredgeBillList(BigInteger userId, int type, String roomId, PageModel pageModel) {
		HashMap<String, Object> paramMap = pageModel.toMap();
		paramMap.put("userId", userId);
		paramMap.put("type", type);
		paramMap.put("roomId", roomId);
		int totalCount = sqlSession.selectOne("dredge.getDredgeBillList_forCount", paramMap);
		List<DredgeBillForList> resList = sqlSession.selectList("dredge.getDredgeBillList_forPage", paramMap);
		pageModel.freshPageModel(resList.size(), totalCount);//pageModel信息的更新通过引用实现
		return resList;
	}

	/**
	 * 查询区是否开通了疏通服务
	 *
	 * @param blockId
	 *            区域id
	 * @param dredgeTypeId
	 *            疏通类型id
	 * @return 0未开通，1已开通
	 */
	public int qryIsOpenDredgeService4Block(String blockId, String dredgeTypeId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("blockId", blockId);
		paramMap.put("dredgeTypeId", dredgeTypeId);

		int totalCount = sqlSession.selectOne("dredge.qryIsOpenDredgeService4Block", paramMap);

		return totalCount > 0 ? 1 : 0;
	}

	public List<BlockWithDredgeService> qryDredgeServiceListByCity(String cityId, String cityName, String dredgeTypeId) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cityId", cityId);
		paramMap.put("cityName", cityName);
		paramMap.put("dredgeTypeId", dredgeTypeId);
		return sqlSession.selectList("dredge.qryDredgeServiceListByCity", paramMap);
	}

	public DredgeBillForDetail qryDredgeBillDetail(String id) {
		return sqlSession.selectOne("dredge.qryDredgeBillDetailById", id);
	}
	
	public DredgeDetails getDredgeBillDetail(String id) {
		return sqlSession.selectOne("dredge.getDredgeBillDetailById", id);
	}

	public DredgeBillForDetail qryPropertyRepairDetail(BigInteger id) {
		List<DredgeBillForDetail> detailList = sqlSession.selectList("dredge.qryPropertyRepairDetailById", id);
		DredgeBillForDetail detail = (detailList == null || detailList.size() == 0) ? null : detailList.get(0);
		return detail;
	}

	public List<BlockForMaster> qryConfigServiceBlockByCity(BigInteger userId, String cityId, String cityName) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cityId", cityId);
		paramMap.put("cityName", cityName);
		paramMap.put("userId", userId);

		return sqlSession.selectList("dredge.qryConfigServiceBlockByCity", paramMap);
	}

	public void submitConfigServiceBlock(BigInteger userId, List<BigInteger> blockIdList) {
		//1 先取出已经配置的接单区域
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		List<DredgeWorkerServiceAddressBlock> dwsabList = dredgeWorkerServiceAddressBlockBaseDao.selectDredgeWorkerServiceAddressBlockByCondition(paramMap, false);
		List<BigInteger> willBeDeletedIdList = new ArrayList<BigInteger>();
		for (DredgeWorkerServiceAddressBlock dwsab : dwsabList) {
			if (!blockIdList.contains(dwsab.gettAddressBlockFId()))
				willBeDeletedIdList.add(dwsab.getId());
		}

		//2 删除上次选中，本次未选的
		if (!willBeDeletedIdList.isEmpty()) {
			sqlSession.delete("dredge.deleteConfigServiceBlockByIds", willBeDeletedIdList);
		}

		//3再插入新选中的区域
		List<BigInteger> hasInsertedBlockIdList = new ArrayList<BigInteger>();
		for (DredgeWorkerServiceAddressBlock dwsab : dwsabList) {
			hasInsertedBlockIdList.add(dwsab.gettAddressBlockFId());
		}
		blockIdList.removeAll(hasInsertedBlockIdList);

		if (blockIdList.isEmpty())//没有新的数据要插入
			return;

		List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList = new ArrayList<DredgeWorkerServiceAddressBlock>();
		List<BigInteger> newIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_worker_service_address_block, blockIdList.size());

		for (int i = 0; i < blockIdList.size(); i++) {
			DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock = new DredgeWorkerServiceAddressBlock();
			dredgeWorkerServiceAddressBlock.setId(newIdList.get(i));
			dredgeWorkerServiceAddressBlock.settUserFId(userId);
			dredgeWorkerServiceAddressBlock.settAddressBlockFId(blockIdList.get(i));
			dredgeWorkerServiceAddressBlockList.add(dredgeWorkerServiceAddressBlock);
		}

		dredgeWorkerServiceAddressBlockBaseDao.insertDredgeWorkerServiceAddressBlockBatch(dredgeWorkerServiceAddressBlockList);
	}

	public List<DredgeBillForMaster> qryDreDgeBillListForMaster(BigInteger userId, String type, PageModel pageModel) {
		Map<String, Object> paramMap = pageModel.toMap();
		paramMap.put("userId", userId);
		paramMap.put("type", type);
		int totalCount_dredge = sqlSession.selectOne("dredge.getDredgeBillList_for_master_forCount", paramMap);

		List<DredgeBillForMaster> resList = new ArrayList<DredgeBillForMaster>();

		if ("1".equals(type)) {//可接订单，走原来的逻辑
			List<DredgeBillForMaster> dredgeList = sqlSession.selectList("dredge.getDredgeBillList_forMaster_forPage", paramMap);
			resList.addAll(dredgeList);
			pageModel.freshPageModel(resList.size(), totalCount_dredge);//pageModel信息的更新通过引用实现
		} else {//处理中，已完成订单，要加上物业维修数据
			int totalCount_property = sqlSession.selectOne("dredge.getPropertyBillList_for_master_forCount", paramMap);
			int totalCount = totalCount_property + totalCount_dredge;

			resList = sqlSession.selectList("dredge.getDredgeBillListAndPropertyBillList_forMaster_forPage", paramMap);

			pageModel.freshPageModel(resList.size(), totalCount);//pageModel信息的更新通过引用实现
		}

		return resList;
	}

	public int qryNewDredgeBillCountForMaster(int type, BigInteger userId, String curTime) {
		Map<String, Object> paramMap =  new HashMap<String, Object>();
		paramMap.put("type", type);
		paramMap.put("userId", userId);
		paramMap.put("curTime", curTime);

		return sqlSession.selectOne("dredge.qryNewDredgeBillCountForMaster", paramMap);
	}

	public int confirmReceiving(BigInteger dredgeBillId, BigInteger userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("dredgeBillId", dredgeBillId);
		return sqlSession.update("dredge.confirmReceivingDredgeBill", paramMap);
	}

	public int dredgeWorkerCancel(BigInteger userId, BigInteger dredgeBillId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("dredgeBillId", dredgeBillId);

		return sqlSession.update("dredge.dredgeWorkerCancel_update_dredgeBill", paramMap);
	}

	public void submitConfigServiceType(BigInteger userId, List<BigInteger> dredgeTypeIdList) {
		//1 先取出已经配置的接单区域
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tUserFId", userId);
		List<DredgeWorkerHasDredgeType> dwhdtList = dredgeWorkerHasDredgeTypeBaseDao.selectDredgeWorkerHasDredgeTypeByCondition(paramMap, false);
		List<BigInteger> willBeDeletedIdList = new ArrayList<BigInteger>();
		for (DredgeWorkerHasDredgeType dwhdt : dwhdtList) {
			if (!dredgeTypeIdList.contains(dwhdt.gettDredgeTypeFId()))
				willBeDeletedIdList.add(dwhdt.getId());
		}

		//2 删除上次选中，本次未选的
		if (!willBeDeletedIdList.isEmpty()) {
			sqlSession.delete("dredge.deleteConfigServiceTypeByIds", willBeDeletedIdList);
		}

		//3再插入新选中的类型
		List<BigInteger> hasInsertedBlockIdList = new ArrayList<BigInteger>();
		for (DredgeWorkerHasDredgeType dwhdt : dwhdtList) {
			hasInsertedBlockIdList.add(dwhdt.gettDredgeTypeFId());
		}
		dredgeTypeIdList.removeAll(hasInsertedBlockIdList);

		if (dredgeTypeIdList.isEmpty())//没有新的数据要插入
			return;

		List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList = new ArrayList<DredgeWorkerHasDredgeType>();
		List<BigInteger> newIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_dredge_worker_has_dredge_type, dredgeTypeIdList.size());

		for (int i = 0; i < dredgeTypeIdList.size(); i++) {
			DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType = new DredgeWorkerHasDredgeType();
			dredgeWorkerHasDredgeType.setId(newIdList.get(i));
			dredgeWorkerHasDredgeType.settUserFId(userId);
			dredgeWorkerHasDredgeType.settDredgeTypeFId(dredgeTypeIdList.get(i));
			dredgeWorkerHasDredgeTypeList.add(dredgeWorkerHasDredgeType);
		}

		dredgeWorkerHasDredgeTypeBaseDao.insertDredgeWorkerHasDredgeTypeBatch(dredgeWorkerHasDredgeTypeList);
	}

	public DredgeBillForMaster qryDredgeBillDetailForMaster(String id) {
		return sqlSession.selectOne("dredge.qryDredgeBillDetailForMasterById", id);
	}

	public MasterProfile qryMyProfileById(BigInteger userId) {
		return sqlSession.selectOne("dredge.qryMasterProfileById", userId.longValue());
	}

	public List<DredgeParentType> qryConfigServiceType(Map<String, Object> paramMap) {
		return sqlSession.selectList("dredge.qryConfigServiceTypeByUserId", paramMap);
	}

	public AccountAmount qryAccountAmount(BigInteger userId) {
		return sqlSession.selectOne("dredge.qryAccountAmount", userId);
	}

	public List<IncomeWithdrawRecorder> qryIncomeOrWithdrawList(Map<String, Object> paramMap, PageModel pageModel) {
		HashMap<String, Object> pageParamMap = pageModel.toMap();
		pageParamMap.putAll(paramMap);

		int totalCount = sqlSession.selectOne("dredge.qryIncomeOrWithdrawList_forCount", pageParamMap);
		List<IncomeWithdrawRecorder> resList = sqlSession.selectList("dredge.qryIncomeOrWithdrawList_forPage", pageParamMap);
		pageModel.freshPageModel(resList.size(), totalCount);//pageModel信息的更新通过引用实现
		return resList;
	}

	/**
	 * 根据电商的订单ID，查询商品信息， {师傅名称}服务类型[维修费]
	 *
	 * @param orderId
	 *            电商的订单id
	 * @return 商品信息
	 */
	public String qryProductInfoByOrderId(BigInteger orderId) {
		return sqlSession.selectOne("dredge.qryProductInfoByOrderId", orderId);
	}

	/**
	 * 根据预约单id，查询接单后的订单信息
	 *
	 * @param dredgeBillId
	 * @return
	 */
	public SMSInfo selectSMSInfoByDgId(BigInteger dredgeBillId) {
		return sqlSession.selectOne("dredge.selectSMSInfoByDgId", dredgeBillId);
	}

	/**
	 * 查询可提现的维修单(师傅)
	 *
	 * @param userId
	 * @return
	 */
	public List<CanWithdrawBill4Master> selectCanWithdrawList_master(BigInteger userId) {
		return sqlSession.selectList("dredge.selectCanWithdrawList_master", userId);
	}

	/**
	 * 查询可提现的维修单(推荐人)
	 *
	 * @param userId
	 * @return
	 */
	public List<CanWithdrawBill> selectCanWithdrawList_recommend(BigInteger userId) {
		return sqlSession.selectList("dredge.selectCanWithdrawList_recommend", userId);
	}

	public ApplyWithdrawInfo selectApplyDetail_byApplyId(BigInteger applyRevenueId) {
		return sqlSession.selectOne("dredge.selectApplyDetail_byApplyId", applyRevenueId);
	}

	/**
	 * 查询师傅的提现记录
	 *
	 * @param userId
	 *            师傅对应的userId
	 * @param pageModel
	 *            分页信息
	 * @return
	 */
	public List<ApplyListForMaster> selectApplyList_byMasterId(BigInteger userId, PageModel pageModel) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		int totalCount =TotalCountGetter.getTotalCount(sqlSession, "dredge.selectApplyList_byMasterId", paramMap);
		paramMap.putAll(pageModel.toMap());//加上分页参数信息
		List<ApplyListForMaster> resList = sqlSession.selectList("dredge.selectApplyList_byMasterId", paramMap);
		pageModel.freshPageModel(resList.size(), totalCount);//pageModel信息的更新通过引用实现
		return resList;
	}

	public List<DredgeMasterList4admin> selectMasterList4Audit(Map<String, Object> paramMap) {
		return sqlSession.selectList("dredge.select_masterList_for_admin_page", paramMap);
	}
	
	public MasterInfo4Audit selectMasterDetail_forAudit(java.math.BigInteger dwId){
		return sqlSession.selectOne("dredge.selectMasterDetail_forAudit", dwId);
	}

	public int getMaster4AuditListSize(Map<String, Object> paramMap) {
		return sqlSession.selectOne("dredge.select_masterList_for_admin_count", paramMap);
	}

	public List<Map> selectDredgeTypeNameList() {
		return sqlSession.selectList("dredge.selectDredgeTypeNameList", null);
	}

	public String selectFullDredgeTypeName(BigInteger dredgeType2ndId) {
		return sqlSession.selectOne("dredge.selectFullDredgeTypeName", dredgeType2ndId);
	}

	public int qryOpenDredgeService_by_cstId_and_cityName(Map<String, Object> paramMap){
		return sqlSession.selectOne("dredge.qryOpenDredgeService_by_cstId_and_cityName", paramMap);
	}

	public BigInteger qryBlockId_by_roomId(BigInteger roomId) {
		return sqlSession.selectOne("dredge.qryBlockId_by_roomId", roomId);
	}

	public List<DredgeTypeEntity> selectDredgeTypeByParentTypeId(BigInteger parentTypeId, List<BigInteger> addrCodeIdList, boolean forceInclude2nd) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("parentTypeId", parentTypeId);
		if (parentTypeId.compareTo(DredgeConstant.ParentCommunitySupplyType.WYBX) == 0) {
			addrCodeIdList = null;
		}
		tmpMap.put("addrCodeIdList", addrCodeIdList);
		tmpMap.put("forceInclude2nd", forceInclude2nd);
		List<DredgeTypeEntity> entities = sqlSession.selectList("dredge.select_DredgeType_ByParentTypeId", tmpMap);
		for (DredgeTypeEntity entity : entities) {
			tmpMap.put("dredgeTypeId", entity.getId());
			List<DredgeType2nd> objects = sqlSession.selectList("dredge.select_2nd_dredgeType_has_product", tmpMap);
			entity.setDredgeType2ndList(objects);
		}
		return entities;
	}

	public List<DredgeProductEntity> getDredgeProductListByDredgeType2ndId(BigInteger dredgeType2ndId,
																		   List<BigInteger> addrCodeIdList,
																		   PageModel pageModel) {
		Map<String, Object> para = new HashMap<>();
		para.put("dredgeType2ndId", dredgeType2ndId);
		para.put("addrCodeIdList", addrCodeIdList);
		if (pageModel != null) {
			para.put("_begin", pageModel.getBegin());
			para.put("_length", pageModel.getLength());
		}
		return sqlSession.selectList("dredge.getDredgeProductListByDredgeType2ndId", para);
	}

	/**
	 * 查看物业维修单详情-师傅
	 *
	 * @param id
	 * @return
	 */
	public DredgeBillForMaster qryRepairBillDetailForMaster(String id) {
		return sqlSession.selectOne("dredge.qryPropertyBillDetailForMasterById", id);
	}

	/**
	 * 获得师傅列表，待推送消息的对象
	 *
	 * @param addressBlockFId
	 * @param dredgeTypeFId
	 * @return
	 */
	public List<CommUserDataEntity> getMasterList4Push(BigInteger addressBlockFId, BigInteger dredgeTypeFId, int workerLevel) {
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("addressBlockFId", addressBlockFId);
		tmpMap.put("dredgeTypeFId", dredgeTypeFId);
		tmpMap.put("workerLevel", workerLevel);
		return sqlSession.selectList("dredge.select_MasterList4Push", tmpMap);
	}

	/**
	 * 查看推荐人的提现记录表
	 * @param userId 推荐人的id
	 * @param pageModel 分页信息
	 * @return
	 */
	public List<ApplyListForMaster> selectApplyList_byRecommendId(BigInteger userId, PageModel pageModel) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		int totalCount =TotalCountGetter.getTotalCount(sqlSession, "dredge.selectApplyList_byRecommendId", paramMap);

		paramMap.putAll(pageModel.toMap());//另上分页参数信息
		List<ApplyListForMaster> resList = sqlSession.selectList("dredge.selectApplyList_byRecommendId", paramMap);
		pageModel.freshPageModel(resList.size(), totalCount);//pageModel信息的更新通过引用实现
		return resList;
	}

	/**
	 * 获取上门服务类型的大类
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypesInDredgeType(List<BigInteger> addrCodeIdList) {
		Map<String, Object> param = new HashMap<>();
		param.put("addrCodeIdList", addrCodeIdList);
		return sqlSession.selectList("dredge.select_communitySupplyTypesInDredgeType", param);
	}

	/**
	 * 获取用户所有的上门服务单及物业维修单
	 * @param userId 用户ID
	 * @param type 1 未结束 2 已结束
	 * @param groupBuildingId 小区ID, 物业维修单查询时用
	 * @param pageModel 分页信息
	 * @return
	 */
	public List<DredgeBillForList> getDredgeBillList(BigInteger userId, int type,
													 BigInteger groupBuildingId, PageModel pageModel, boolean fromLA) {
		HashMap<String, Object> paramMap = pageModel.toMap();
		paramMap.put("userId", userId);
		paramMap.put("stateType", type - 1);
		paramMap.put("gbId", groupBuildingId);
		paramMap.put("fromLA", fromLA);
		paramMap.put("type", type);
		int billCount = sqlSession.selectOne("dredge.getDredgeBillList_forCount", paramMap);
		paramMap.putAll(pageModel.toMap());

		List<DredgeBillForList> resList = sqlSession.selectList("dredge.getDredgeBillAndPropertyRepairList_forPage", paramMap);


		/*for (DredgeBillForList dredgeBillForList : resList) {
			//物业维修单 物业关闭 没内转外 时间没超过设定的时间 这样条件的可以内转外
			if ((dredgeBillForList.getBillType() == DredgeConstant.DredgeBillType.Property_Repair || dredgeBillForList.getBillType() == DredgeConstant.DredgeBillType.Dredge_Repair)
					&& dredgeBillForList.getStatus() == DredgeConstant.DredgeBill.DredgeBill_Status_Property_Closed && dredgeBillForList.getIsTransed() == 0
					&&(new Date().getTime() - dredgeBillForList.getUpdateTime().getTime() < day * 24*60*60*1000)) {
				dredgeBillForList.setCanTrans(true);
			}
			//物业单状态映射成外部单
			if (dredgeBillForList.getBillType() == DredgeConstant.DredgeBillType.Property_Repair) {
				int status = dredgeBillForList.getStatus();
				if (status == 0) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
				} else if (status == 1) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Canceled);
				} else if (status == 2) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
				} else if (status == 3) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment);
				}
			}
		}*/
		pageModel.freshPageModel(resList.size(), billCount);//pageModel信息的更新通过引用实现
		return resList;
	}

	public List<DredgeBillForList> getDredgeRepairList(BigInteger userId, int type, BigInteger groupBuildingId, PageModel pageModel) {
		HashMap<String, Object> paramMap = pageModel.toMap();
		int day = Integer.parseInt(sysParamManager.getSysParaValue(SysParamKey.REPAIR_CONVERT_DAY));
		paramMap.put("userId", userId);
		paramMap.put("stateType", type - 1);
		paramMap.put("gbId", groupBuildingId);
		paramMap.put("repairConverDay", day);

		paramMap.put("type", type);
		paramMap.putAll(pageModel.toMap());

		List<DredgeBillForList> resList = sqlSession.selectList("dredge.getDredgeRepairList_forPage", paramMap);

		for (DredgeBillForList dredgeBillForList : resList) {
			//物业维修单 物业关闭 没内转外 时间没超过设定的时间 这样条件的可以内转外
			if ((dredgeBillForList.getBillType() == DredgeConstant.DredgeBillType.Property_Repair || dredgeBillForList.getBillType() == DredgeConstant.DredgeBillType.Dredge_Repair)
					&& dredgeBillForList.getStatus() == DredgeConstant.DredgeBill.DredgeBill_Status_Property_Closed && dredgeBillForList.getIsTransed() == 0
					&&(new Date().getTime() - dredgeBillForList.getUpdateTime().getTime() < day * 24*60*60*1000)) {
				dredgeBillForList.setCanTrans(true);
			}
			//物业单状态映射成外部单
			if (dredgeBillForList.getBillType() == DredgeConstant.DredgeBillType.Property_Repair) {
				int status = dredgeBillForList.getStatus();
				if (status == 0) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Submited);
				} else if (status == 1) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Canceled);
				} else if (status == 2) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_Accepted);
				} else if (status == 3) {
					dredgeBillForList.setStatus(DredgeConstant.DredgeBill.DredgeBill_Status_FinishedHasComment);
				}
			}
		}
		return resList;
	}

	/**
	 * 列出可选耗材总数
	 * @param paramMap
	 * @return
	 */
	public int qryProductListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("dredge.selectProductList_count", paramMap);
	}

	/**
	 * 列出可选耗材
	 * @param paramMap
	 * @return
	 */
	public List<SelfBuyProduct> qryProductList(Map<String, Object> paramMap) {
		return sqlSession.selectList("dredge.selectProductList_page", paramMap);
	}

	/**
	 * 查看维修选中耗材总价
	 * @param dredgeBillId
	 * @return
	 */
	public Integer getDredgeHasProductAmount(BigInteger dredgeBillId) {
		return sqlSession.selectOne("dredge.getDredgeHasProductAmount", dredgeBillId);
	}

	/**
	 * 查出维修单选中的耗材
	 * @param paramMap
	 * @return
	 */
	public List<SelfBuyProduct> qryProductListWithDredgeBillId(Map<String, Object> paramMap) {
		return sqlSession.selectList("dredge.selectProductListWithDredgeBillId", paramMap);
	}

	/**
	 * 查询  维修单下的耗材信息
	 * @param dredgeBillId
	 * @return
	 */
	public List<Map<BigInteger, Integer>> selectEbuyProductByDredgeBillId(BigInteger dredgeBillId) {
		return sqlSession.selectList("dredge.selectEbuyProductByDredgeBillId", dredgeBillId);
	}

	/**
	 * 更新 耗材的库存信息
	 * @param productList
	 * @return
	 */
	public int updateEbuyProductInventory(List<Map<BigInteger, Integer>> productList) {
		return sqlSession.update("dredge.updateEbuyProductInventory", productList);
	}

	/**
	 * 查询维修单下的耗材数量
	 * @param dredgeBillId
	 * @return
	 */
	public int isHasEbuyProductByDredgeBillId(BigInteger dredgeBillId) {
		return sqlSession.selectOne("dredge.selectEbuyProductCountByDredgeBillId", dredgeBillId);
	}

	/**
	 * 查询维修师傅的联系电话
	 * @param gettUserFId
	 * @return
	 */
	public String getDredgeMasterMobile(BigInteger userId) {
		return sqlSession.selectOne("dredge.getDredgeMasterMobile", userId);
	}

	/**
	 * 查询  发送短息的内容（商品  供应商）
	 * @param id
	 * @return
	 */
	public List<DredgeMsgEntity> getDredgeBillMsgContent(BigInteger dredgeBillId) {
		return sqlSession.selectList("dredge.getDredgeBillMsgContent", dredgeBillId);
	}

	public int deleteSelfBuyProduct(BigInteger dredgeBillId, List<BigInteger> productShelfIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productShelfIds", productShelfIds);
		param.put("userId", UserContext.getOperIdMustExistBigInteger());
		param.put("dredgeBillId", dredgeBillId);
		return sqlSession.update("dredge.deleteSelfBuyProduct", param);
	}

	/**
	 * 维修单列表
	 * @param paramMap
	 * @return
	 */
	public List<DredgeBillEntity> getDredgeBillList(Map<String, Object> paramMap) {
		return sqlSession.selectList("dredge.getDredgeBillList", paramMap);
	}

	public int getDredgeBillListCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("dredge.getDredgeBillListCount", paramMap);
	}
	
	/**
	 * 查询订单跟踪信息
	 * @return
	 */
	public List<DredgeBillFollowRecord> qryOrderFollowRecord(String dredgeBillId) {
		return sqlSession.selectList("dredge.getDredgeBillFollowRecordList", dredgeBillId);
	}

	/**
	 * 查询维修师傅列表
	 * @return
	 */
	public List<Map<String, Object>> getDredgeWorkerList() {
		return sqlSession.selectList("dredge.getDredgeWorkerList");
	}

	/**
	 * 修改维修单状态
	 * @param paramMap
     */
	public int editDredgeBillStatus(Map<String, Object> paramMap) {
		return sqlSession.update("dredge.editDredgeBillStatus", paramMap);
	}

	/**
	 * 删除维修单费用明细
	 * @param dredgeBillId
     */
	public int delDredgeBillAmountDetail(BigInteger dredgeBillId) {
		return sqlSession.update("dredge.delDredgeBillAmountDetail", dredgeBillId);
	}
	
	/**
	 * 为轻松到家维修单信息
	 * @param dredgeBillId
	 * @return
	 */
	public DredgeBill4Qsdj qryDredgeBill4Qsdj(BigInteger dredgeBillId) {
		return sqlSession.selectOne("dredge.selectDredgeBillDetail4Qsdj", dredgeBillId);
	}

	/**
	 * 根据维修单id找出片区经理人信息
	 * @param id
	 * @return
	 */
	public PropertyDistrict selectPropertyDistrictByDredgeBillId(BigInteger id) {
		return sqlSession.selectOne("dredge.selectPropertyDistrictByDredgeBillId", id);
	}

	public List<BigInteger> qryRepairDredgeType(BigInteger gbId) {
		return sqlSession.selectList("dredge.qryRepairDredgeType", gbId);
	}

	public Integer turnDredgeBillById(DredgeBill dredgeBill) {
		Map<String, Object> param = new HashMap<>();
		param.put("dredgeBillId", dredgeBill.getId());
		param.put("dredgeTypeId", dredgeBill.gettDredgeTypeFId());
		param.put("dredgeType2ndId", dredgeBill.gettDredgeType2ndFId());
		param.put("expectDate", dredgeBill.getExpectdate());
		param.put("billType", dredgeBill.getBillType());
		return sqlSession.update("dredge.turnDredgeBillById", param);
	}

	public Map<String, Object> getRoomAddressIdByRoom(BigInteger roomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("roomId", roomId);
		return sqlSession.selectOne("commonRoom.getRoomAddressIdByRoom", tmpMap);
	}

	public DredgeProductEntity getDredgeProductDetail(BigInteger dredgeProductId) {
		return sqlSession.selectOne("dredge.getDredgeProductDetail", dredgeProductId);
	}

	/**
	 * 查询所有服务商品总数
	 * @param paramMap
	 * @return
	 */
	public int qryDredgeProductCount(Map<String, Object> paramMap) {
		return TotalCountGetter.getTotalCount(sqlSession, "dredge.qryDredgeProductList", paramMap);
	}
	
	/**
	 * 查询所有服务商品
	 * @param paramMap
	 * @return
	 */
	public List<DredgeProduct4Admin> qryDredgeProductList(Map<String, Object> paramMap) {
		return sqlSession.selectList("dredge.qryDredgeProductList", paramMap);
	}

	/**
	 * 查询指定维修单选的商品规格
	 * @param dredgeBillId 维修单ID
	 * @return
	 */
	public List<DredgeProductSpecEntity> getDredgeProductListByDbId(BigInteger dredgeBillId) {
		return sqlSession.selectList("dredge.getDredgeProductListByDbId", dredgeBillId);
	}

	/**
	 * 是否可以解放区师傅接单
	 * @param dredgeBillId
	 * @return
	 */
	public boolean isPayFirstJfqBill(BigInteger dredgeBillId) {
		return sqlSession.selectOne("dredge.isPayFirstJfqBill", dredgeBillId);
	}

	/**
	 * 查看服务商品详情
	 * @param id
	 * @return
	 */
	public DredgeProductView qryDredgeProductDetail(BigInteger id) {
		return sqlSession.selectOne("dredge.qryDredgeProductDetail", id);
	}

	public String getAddressByBlockId(BigInteger blockId) {
		return sqlSession.selectOne("dredge.getAddressByBlockId", blockId);
	}

	public List<DredgeProduct4Turn> qryDredgeProductList4Trun(BigInteger dt2Id) {
		return sqlSession.selectList("dredge.qryDredgeProductList4Trun", dt2Id);
	}

	public List<OperateConfigRange> qryDredgeProductSellRangeByPrdtId(BigInteger id) {
		return sqlSession.selectList("dredge.qryDredgeProductSellRangeByPrdtId", id);
	}

	public Integer autoFinishBill() {
		return sqlSession.update("dredge.autoFinishBill");
	}

	public Integer isInDredgeProductArea(List<BigInteger> addrCodeIdList, BigInteger dredgeProductId) {
		Map<String, Object> para = new HashMap<>();
		para.put("addrCodeIdList", addrCodeIdList);
		para.put("dredgeProductId", dredgeProductId);
		return sqlSession.selectOne("dredge.isInDredgeProductArea", para);
	}

	public Map<String, Object> getApplyRefundInfo(BigInteger dredgeBillId) {
		return sqlSession.selectOne("dredge.getApplyRefundInfo", dredgeBillId);
	}

	public String getOpenId(BigInteger userId) {
		Map<String, Object> param = new HashMap<>();
		param.put("userId", userId);
		return sqlSession.selectOne("dredge.getOpenId", param);
	}
}
