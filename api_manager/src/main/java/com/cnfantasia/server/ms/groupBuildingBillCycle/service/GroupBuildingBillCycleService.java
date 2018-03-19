package com.cnfantasia.server.ms.groupBuildingBillCycle.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cnfantasia.server.api.groupBuildingCycleCfg.constant.CycleCfgDict;
import com.cnfantasia.server.api.groupBuildingCycleCfg.service.GroupBuildingCycleCfgService;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.meterReading.entity.MrFeeItemWithFormula;
import com.cnfantasia.server.api.meterReading.entity.RealRoomHasMrLastRecordEntity;
import com.cnfantasia.server.api.meterReading.service.MeterReadingService;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.utils.NumberUtils;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.*;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.IGroupBuildingBillCycleBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.GroupBuildingBillCycleBaseService;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.dao.IGroupBuildingBillCycleConfigBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycleConfig.entity.GroupBuildingBillCycleConfig;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.service.MrFeeItemBaseService;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrPayBillRecord.dao.IMrPayBillRecordBaseDao;
import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;
import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;
import com.cnfantasia.server.domainbase.mrStandardRoom.service.IMrStandardRoomBaseService;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillTimeCfg.dao.IPayBillTimeCfgBaseDao;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;
import com.cnfantasia.server.domainbase.payBillTimeCfg.service.IPayBillTimeCfgBaseService;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.payBillType.service.IPayBillTypeBaseService;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.IPropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.dao.IPropertyFeeDetailTempBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;
import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.service.IPropertyFeeDetailTempBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao.IRealRoomHasMrLastRecordBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;
import com.cnfantasia.server.domainbase.tmpFeeItem.dao.ITmpFeeItemBaseDao;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;
import com.cnfantasia.server.ms.fixedFeeCfg.dao.FixedFeeCfgDao;
import com.cnfantasia.server.ms.fixedFeeCfg.service.FixedFeeCfgService;
import com.cnfantasia.server.ms.groupBuilding.service.IGroupBuildingService;
import com.cnfantasia.server.ms.groupBuildingBillCycle.dao.IGroupBuildingBillCycleDao;
import com.cnfantasia.server.ms.groupBuildingBillCycle.dto.BillEditParam;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.payBill.constant.PropIconUtil;
import com.cnfantasia.server.ms.payBill.dao.IPayBillDao;
import com.cnfantasia.server.ms.propertyPayConfig.entity.CalculateRuleEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.MrImportDataEntity;
import com.cnfantasia.server.ms.propertyPayConfig.entity.VerifyMrDataEntity;
import com.cnfantasia.server.ms.propertyPayConfig.service.IPropertyPayConfigService;
import com.cnfantasia.server.ms.pub.comm.CnfantasiaCommUtil;
import com.cnfantasia.server.ms.pub.constant.JSPConstants;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.revenue.entity.AlterUnPaidEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class GroupBuildingBillCycleService extends GroupBuildingBillCycleBaseService implements IGroupBuildingBillCycleService {
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private IGroupBuildingBillCycleDao groupBuildingBillCycleDao;
	@Resource
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	@Resource
	private IPayBillTimeCfgBaseDao payBillTimeCfgBaseDao;
	@Resource
	private IGroupBuildingService msGroupBuildingService;
	@Resource 
	private IPayBillDao payBillDao;
	@Resource
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private FixedFeeCfgDao fixedFeeCfgDao;
	@Resource
	private IPropertyFeeDetailTempBaseDao propertyFeeDetailTempBaseDao;
	@Resource
	private IGroupBuildingBillCycleBaseDao groupBuildingBillCycleBaseDao;
	@Resource
	private IPayBillBaseDao payBillBaseDao;
	@Resource
	private IPropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	@Resource
	private MrFeeItemBaseService mrFeeItemBaseService;
	@Resource
	FixedFeeCfgService feeCfgService;
	@Resource
	IPropertyFeeDetailTempBaseService propertyFeeDetailTempBaseService;
	@Resource
	private MeterReadingService meterReadingService;
	@Resource
	IRealRoomHasMrLastRecordBaseDao realRoomHasMrLastRecordBaseDao;
	@Resource
	IMrPayBillRecordBaseDao mrPayBillRecordBaseDao;
    @Resource
    private GroupBuildingCycleCfgService groupBuildingCycleCfgService;
    @Resource
    private IGroupBuildingBillCycleConfigBaseDao groupBuildingBillCycleConfigBaseDao;
    @Resource
    private IPayBillTypeBaseService payBillTypeBaseService;
    @Resource
    private IPayBillTimeCfgBaseService payBillTimeCfgBaseService;
	@Resource
	private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
	@Resource
	private ITmpFeeItemBaseDao tmpFeeItemBaseDao;
	@Resource
	private IMrStandardRoomBaseService mrStandardRoomBaseService;
	@Resource
	private IPropertyPayConfigService propertyPayConfigService;

	@Override
	public int queryBuildingForCount(Map<String, Object> paramMap) {
		return groupBuildingBillCycleDao.queryBuildingForCount(paramMap);
	}

	@Override
	public List<GroupBuildingBillCycleEntity> queryBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap, boolean isPage) {
		if(isPage){
			paramMap.put("_begin", pageSize * curPageIndex);
			paramMap.put("_length", pageSize);
		}
		return groupBuildingBillCycleDao.queryBuildingForList(paramMap);
	}

	public IGroupBuildingBillCycleDao getGroupBuildingBillCycleDao() {
		return groupBuildingBillCycleDao;
	}

	public void setGroupBuildingBillCycleDao(IGroupBuildingBillCycleDao groupBuildingBillCycleDao) {
		this.groupBuildingBillCycleDao = groupBuildingBillCycleDao;
	}

	@Override
	public GroupBuildingBillCycleEntity getGroupBuildingBillCycleById(BigInteger id) {
		return groupBuildingBillCycleDao.getGroupBuildingBillCycleById(id);
	}

	@Override
	@Transactional
	public int saveOrUpdateBillCycel(BillEditParam billEditParam) {
		int i = 0;
		try {
			GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
			GroupBuildingBillCycle groupBuildingBillCycle02 = new GroupBuildingBillCycle();//判断是否需要更新关联表的数据信息

			BigInteger gbbcId = billEditParam.getId();
			if(!StringUtils.isEmpty(gbbcId)){
				groupBuildingBillCycle.setId(gbbcId);
				groupBuildingBillCycle02 = groupBuildingBillCycleDao.selectGroupBuildingBillCycleBySeqId(gbbcId);
			}

			String feeType = "";
			for (String feeTypeStr : billEditParam.getFeeType()) {
				feeType += feeTypeStr + ",";
			}
			feeType = feeType.substring(0, feeType.length()-1);

			groupBuildingBillCycle.setBillMonthEnd(billEditParam.getBillMonthEnd());
			groupBuildingBillCycle.setBillMonthStart(billEditParam.getBillMonthStart());
			groupBuildingBillCycle.setBillPayEnd(billEditParam.getBillPayEnd());
			groupBuildingBillCycle.setBillPayStart(billEditParam.getBillPayStart());
			groupBuildingBillCycle.settGroupBuildingId(billEditParam.getGbId());
			groupBuildingBillCycle.setBillMonth(billEditParam.getBillMonth());

			//没有账单名称且有账期id，证明该账期下有数据，只能修改缴费窗口
			if((billEditParam.getBillName() == null || "".equals(billEditParam.getBillName())) && !StringUtils.isEmpty(gbbcId)) {
				groupBuildingBillCycle02.setBillPayStart(billEditParam.getBillPayStart());
				groupBuildingBillCycle02.setBillPayEnd(billEditParam.getBillPayEnd());
				groupBuildingBillCycle02.setSys0UpdTime(DateUtils.getCurrentDate());
				groupBuildingBillCycle02.setFeeType(feeType);
				updatePayBillDate(groupBuildingBillCycle02, groupBuildingBillCycle);
				return groupBuildingBillCycleBaseDao.updateGroupBuildingBillCycle(groupBuildingBillCycle02);
			}

			GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(billEditParam.getGbId());
			String billName = billEditParam.getBillName();

			PayBillType payBillType = null;
			if(billEditParam.getPayBillTypeId() != null) {//修改账单名称
				payBillType = payBillTypeBaseDao.selectPayBillTypeBySeqId(billEditParam.getPayBillTypeId());
			}
			//新增或更新账单名称---t_pay_bill_type存储账单名称
			if(payBillType == null) {//账单名称不存在  新增
				payBillType = new PayBillType();
				payBillType.setId(uuidManager.getNextUuidBigInteger("t_pay_bill_type"));
				payBillType.setName(billName);
				payBillType.setGbId(groupBuilding.getId());
				payBillType.setIcon(PropIconUtil.getBillIcon(billName));
				payBillType.setPreferStatus(groupBuilding.getIsPrefer());//v349版本已经废弃，为保证一致性 还在维护这个字段
				payBillType.setActiveStatus(groupBuilding.getPropfeeCanpay());
				payBillType.setPaytimeType(2);//暂时以原来周期缴费的方式存储
				payBillType.setIsPropFee(2);
				payBillType.setSys0AddTime(DateUtils.getCurrentDate());
				payBillType.setSys0UpdTime(DateUtils.getCurrentDate());
				payBillType.setLastUpdTime(DateUtils.getCurrentDate());
				payBillTypeBaseDao.insertPayBillType(payBillType);
				
				groupBuildingBillCycle.setPaytimeType(2);
			} else {//更新账期中的对应id
				if(payBillType.getName() != null && !payBillType.getName().equals(billName)) {//更新账单名称
					Map<String, Object> paraMap = new HashMap<String, Object>();
					paraMap.put("tBillCycleId", groupBuildingBillCycle.getId());
					List<PayBill> payBillList = payBillDao.selectPayBillByCondition(paraMap, false);
					for (PayBill payBill : payBillList) {
						payBill.setBillTypeName(billName);
					}
					if(!DataUtil.isEmpty(payBillList)) {
						payBillDao.updatePayBillBatch(payBillList);
					}
				}
				payBillType.setName(billName);
				payBillType.setIcon(PropIconUtil.getBillIcon(billName));
				payBillType.setLastUpdTime(DateUtils.getCurrentDate());
				payBillType.setSys0UpdTime(DateUtils.getCurrentDate());
				groupBuildingBillCycle.setPaytimeType(2);
				payBillTypeBaseDao.updatePayBillType(payBillType);
			}
			groupBuildingBillCycle.settPayBillTypeId(payBillType.getId());
			
			// t_pay_bill_time_cfg
			PayBillTimeCfg payBillTimeCfg = new PayBillTimeCfg();
			payBillTimeCfg.setBillTypeId(payBillType.getId());
			payBillTimeCfg.setGbId(billEditParam.getGbId());
			payBillTimeCfg.setBillMonthSize((long)DateUtils.getDiffMonths(DateUtils.convertStrToDate(billEditParam.getBillMonthStart()), DateUtils.convertStrToDate(billEditParam.getBillMonthEnd()))+1);
			payBillTimeCfg.setBillMonthStart(billEditParam.getBillMonthStart());
			payBillTimeCfg.setBillMonthEnd(billEditParam.getBillMonthEnd());
			payBillTimeCfg.setPayDayStart(billEditParam.getBillPayStart());
			payBillTimeCfg.setPayDayEnd(billEditParam.getBillPayEnd());
			
			if(billEditParam.getPayBillTimeCfgId() == null){
				payBillTimeCfg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill_time_cfg));
				payBillTimeCfgBaseDao.insertPayBillTimeCfg(payBillTimeCfg);
			} else {
				payBillTimeCfgBaseDao.updatePayBillTimeCfg(payBillTimeCfg);
			}
			groupBuildingBillCycle.settPayBillTimeCfgId(payBillTimeCfg.getId());
			groupBuildingBillCycle.setFeeType(feeType);
			//设置缴费模式为固定周期
			groupBuildingBillCycle.setChargingMode(CycleCfgDict.RechargeMode.FIXED_CYCLE);

			if(!StringUtils.isEmpty(gbbcId)){
				updatePayBillDate(groupBuildingBillCycle02, groupBuildingBillCycle);
				groupBuildingBillCycle.setSys0UpdTime(DateUtils.getCurrentDate());
				i = groupBuildingBillCycleDao.updateGroupBuildingBillCycle(groupBuildingBillCycle);
			} else {
				CnfantasiaCommUtil.newStandard(groupBuildingBillCycle, SEQConstants.t_group_building_bill_cycle);
				groupBuildingBillCycle.setBankCollectionStatus(1);
				i = groupBuildingBillCycleDao.insertGroupBuildingBillCycle(groupBuildingBillCycle);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 账单更新
	 * @param paytimeType 账单类型
	 * @param groupBuildingBillCycle02 修改前的账期
	 * @param groupBuildingBillCycle 修改后的账期
	 */
	private void updatePayBillDate(GroupBuildingBillCycle groupBuildingBillCycle02, GroupBuildingBillCycle groupBuildingBillCycle) {
		//String billMonthOld = groupBuildingBillCycle02.getBillMonth();
		String startBillOld = groupBuildingBillCycle02.getBillMonthStart();
		String endBillOld = groupBuildingBillCycle02.getBillMonthEnd();
		String startPayOld = groupBuildingBillCycle02.getBillPayStart();
		String endPayOld = groupBuildingBillCycle02.getBillPayEnd();
		
		//String billMonthNew = groupBuildingBillCycle.getBillMonth();
		String startBillNew = groupBuildingBillCycle.getBillMonthStart();
		String endBillNew = groupBuildingBillCycle.getBillMonthEnd();
		String startPayNew = groupBuildingBillCycle.getBillPayStart();
		String endPayNew = groupBuildingBillCycle.getBillPayEnd();

		if(!startBillNew.equals(startBillOld) || !endBillNew.equals(endBillOld) || !startPayNew.equals(startPayOld) || !endPayNew.equals(endPayOld)) {//如果缴费时间有更新
			//更新账单
			HashMap<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("billCycleId", groupBuildingBillCycle.getId());
			updateMap.put("billMonthStart", startBillNew);
			updateMap.put("billMonthEnd", endBillNew);
			updateMap.put("payDayStart", startPayNew);
			updateMap.put("payDayEnd", endPayNew);
			updateMap.put("sys0UpdTime", DateUtils.getCurrentDate());
			updateMap.put("monthSize", (long)DateUtils.getDiffMonths(DateUtils.convertStrToDate(startBillNew), DateUtils.convertStrToDate(endBillNew)) + 1);
			payBillDao.updatePayBillBillMonth(updateMap);
		}
	}

	@Override
	public int deleteAllBillById(Map<String, Object> paraMap) {
		//先查询出账单信息，防止被删除后信息不准确
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("tBillCycleId", paraMap.get("billCycleId"));
		List<PayBill> payBillList = payBillBaseDao.selectPayBillByCondition(map, false);*/

		//获取小区下的门牌信息
		List<BigInteger> roomList = msGroupBuildingService.selectGroupBuildingRealRoomList(new BigInteger(paraMap.get("gbId").toString()));
		paraMap.put("roomList", roomList);
		return groupBuildingBillCycleDao.deleteAllBillById(paraMap);
	}
	
	public IGroupBuildingBaseDao getGroupBuildingBaseDao() {
		return groupBuildingBaseDao;
	}

	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}

	public IPayBillTimeCfgBaseDao getPayBillTimeCfgBaseDao() {
		return payBillTimeCfgBaseDao;
	}

	public void setPayBillTimeCfgBaseDao(IPayBillTimeCfgBaseDao payBillTimeCfgBaseDao) {
		this.payBillTimeCfgBaseDao = payBillTimeCfgBaseDao;
	}

	@Override
	public boolean isHashSameBillCycle(Map<String, Object> paraMap) {
		String billMonthStart = (String) paraMap.get("billMonthStart");
		String billMonthEnd = (String) paraMap.get("billMonthEnd");
		if (paraMap.get("billMonthStart") != null)
			paraMap.put("billMonthStart",
					DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart, "yyyy-MM"), "yyyy-MM-dd"));

		if (paraMap.get("billMonthEnd")!= null)
			paraMap.put("billMonthEnd", DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthEnd, "yyyy-MM"), "yyyy-MM-dd"));
		Integer count = groupBuildingBillCycleDao.isHashSameBillWindow(paraMap);
		return count != null && count > 0;

	}

	@Override
	public boolean isHashSameBillWindow(Map<String, Object> paraMap) {
		Integer count = groupBuildingBillCycleDao.isHashSameBillWindow(paraMap);
		return count != null && count > 0;
	}

	public void setPayBillDao(IPayBillDao payBillDao) {
		this.payBillDao = payBillDao;
	}

	@Override
	public boolean isExistBillName(Map<String, Object> paraMap) {
		Map<String, Object> paraMap02 = new HashMap<String, Object>();
		paraMap02.put("name", paraMap.get("billName").toString().trim());
		paraMap02.put("gbId", paraMap.get("gbId"));

		List<PayBillType> payBillTypes = payBillTypeBaseDao.selectPayBillTypeByCondition(paraMap02, false);
		return !DataUtil.isEmpty(payBillTypes);

	}

	@Override
	public PayBillType getPayBillType(String billName, BigInteger gbId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", billName.trim());
		paramMap.put("gbId", gbId);

		List<PayBillType> payBillTypes = payBillTypeBaseDao.selectPayBillTypeByCondition(paramMap, false);
		if(!DataUtil.isEmpty(payBillTypes)) {//存在账单名称
			return payBillTypes.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public JsonResponse synchroFixedData(BigInteger cycleId, BigInteger gbId) {
		JsonResponse jsonResponse = new JsonResponse();
		//查询账期信息
		GroupBuildingBillCycle groupBuildingBillCycle = groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleBySeqId(cycleId);
		//查询小区对应的固定周期数据信息
		List<PropertyFeeDetailTemp> list = fixedFeeCfgDao.getNeedSynchroData(gbId, groupBuildingBillCycle.getGbbcCfgId());
		if(list!=null && list.size()>0){
			//组装数据
			List<BigInteger> dataIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail_temp, list.size());
			int i = 0;
			for(PropertyFeeDetailTemp propertyFeeDetailTemp : list) {
				propertyFeeDetailTemp.settBillCycleId(cycleId);
				propertyFeeDetailTemp.setSys0AddUser(UserContext.getCurrUser().getId());
				propertyFeeDetailTemp.setSys0AddTime(DateUtils.getCurrentDate());
				propertyFeeDetailTemp.setType(FeeTypeDict.Gu_Ding);
				propertyFeeDetailTemp.setId(dataIds.get(i));
				i ++;
			}
			//删除临时表中的该账期对应的所有数据  逻辑删除
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("tGbId", gbId);
			paraMap.put("tBillCycleId", cycleId);
			paraMap.put("type", FeeTypeDict.Gu_Ding);
			List<PropertyFeeDetailTemp> propertyFeeDetailTemps = propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempByCondition(paraMap, false);
			if(!DataUtil.isEmpty(propertyFeeDetailTemps)) {
				groupBuildingBillCycleDao.deleteFeeDetailTempByCycleAndGb(cycleId, gbId, FeeTypeDict.Gu_Ding);
			}

			//将数据保存到费用项临时表中t_property_fee_detail_temp
			int j = propertyFeeDetailTempBaseDao.insertPropertyFeeDetailTempBatch(list);
			if(j>0){
				jsonResponse.setMessage("操作成功！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			} else {
				jsonResponse.setMessage("操作失败！");
				jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			}
		} else {
			jsonResponse.setMessage("没有数据信息，同步失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	
	/**
	 * 导入抄水表数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@Override
	public String importMrData(HttpServletRequest request) throws IOException{
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			BigInteger gbId = new BigInteger(request.getParameter("importGbId"));
			if(org.apache.commons.lang.StringUtils.isBlank(request.getParameter("billCycleId"))){
				result = "账单类型对应缴费时间暂未配置！请配置！";
			} else {
				BigInteger gbbcId = new BigInteger(request.getParameter("billCycleId"));
				Map<String, BigInteger> roomInfoMap = feeCfgService.getRoomStrByGbId(gbId);
				VerifyMrDataEntity verifyMrDataEntity = new VerifyMrDataEntity();
				verifyMrDataEntity.setGbId(gbId);
				verifyMrDataEntity.setWb(wb);
				verifyMrDataEntity.setRoomInfoMap(roomInfoMap);
				String verifyResult = verifyMeterReadingData(verifyMrDataEntity);
				logger.info("[mrStandardRoomInfoMap]"+JSONArray.toJSONString(verifyMrDataEntity.getMrStandardRoomInfoMap()));
				logger.info("[roomMrValues]"+JSONArray.toJSONString(verifyMrDataEntity.getRoomMrValues()));
				if(!verifyResult.equals("通过校验")){
					result = "校验失败："+ verifyResult;
				} else {
					List<BigInteger> pfdTempIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail_temp, verifyMrDataEntity.getValidCounts());
					List<PropertyFeeDetailTemp> pdfTemps = new ArrayList<PropertyFeeDetailTemp>();
					String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
					BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
					List<RealRoomHasMrLastRecordEntity> realRoomLastRecordList = meterReadingService.getRealRoomLastRecordByGbId(gbId.toString());
					GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
					List<MrFeeItemWithFormula> mrFeeItemWithFormulaList = meterReadingService.getMrFeeItemWithFormulaByGbId(gbId.toString());
					int k = 0;//取pfdTempIds得index
					for (int g = 0; g < wb.getNumberOfSheets(); g++) {
						HSSFSheet sheet = wb.getSheetAt(g);
						int startRow = 3; //从第3行（含）开始导入数据
						for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
							if(isEmptyRow(sheet, i)) continue;
							logger.info("============="+startRow+" row start=======================");
							HSSFRow row = sheet.getRow(i);
							String mrName = HSSFCellUtil.getStringValue(row.getCell(3));//计费表名称
							// t_real_room f_id
							String roomInfo = HSSFCellUtil.getStringValue(row.getCell(0)) + "-" +HSSFCellUtil.getStringValue(row.getCell(1)) + "-" + HSSFCellUtil.getStringValue(row.getCell(2));
							BigInteger realRoomId = roomInfoMap.get(roomInfo);
							String feeName = HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(3));
							BigInteger mrFeeItemId = verifyMrDataEntity.getMrFeeItemIds().get(feeName);
							logger.info("[realRoomId_mrFeeItemId]("+realRoomId+")("+realRoomId+")");
							HSSFCell startVlauecell = row.getCell(5);//上期读数
							HSSFCell endVlauecell = row.getCell(6);//本期读数
							BigInteger standardRoomId = verifyMrDataEntity.getMrStandardRoomInfoMap().get(realRoomId+"_"+mrFeeItemId+"_"+mrName.trim());
							logger.info("[standardRoomId]" + standardRoomId);
							//是否为使用其他费用项用量计算:（1：根据读数计算；2：根据其他费用用量计算）
							Integer calculatePriceType = propertyPayConfigService.getCalculatePriceTypeByStandardRoomId(standardRoomId);
							//根据读数计算的才进行本上期读数空忽略
							if(DataUtil.isEmpty(HSSFCellUtil.getStringValue(startVlauecell)) && DataUtil.isEmpty(HSSFCellUtil.getStringValue(endVlauecell)) && calculatePriceType.equals(1)) continue;//本&&上期读数为空时不生成明细
							if(!DataUtil.isEmpty(HSSFCellUtil.getStringValue(startVlauecell)) && DataUtil.isEmpty(HSSFCellUtil.getStringValue(endVlauecell)) && calculatePriceType.equals(1)) continue;

							PropertyFeeDetailTemp pfdTemp = new PropertyFeeDetailTemp();
							pfdTemp.setId(pfdTempIds.get(k));
							pfdTemp.setType(FeeTypeDict.Chao_Biao);
							pfdTemp.setName(feeName);
							pfdTemp.setMrName(mrName);
							// 计算“费用合计”
							// XXX:此处，有时间要优化:V518临时处理--yh
							MrFeeItemWithFormula mfiWithFormula = new MrFeeItemWithFormula();
							for (MrFeeItemWithFormula mrFeeItemWithFormula : mrFeeItemWithFormulaList) {
								if(!feeName.equals(mrFeeItemWithFormula.getName())) continue;
								mfiWithFormula = mrFeeItemWithFormula;
							}

							double endValue = HSSFCellUtil.getNumbericValue(endVlauecell);
							double startValue = HSSFCellUtil.getNumbericValue(startVlauecell);
							if(DataUtil.isEmpty(HSSFCellUtil.getStringValue(row.getCell(5))) ){
								Map<String, Object> paramMap = new HashMap<String, Object>();
								paramMap.put("tRealRoomFId", realRoomId);
								paramMap.put("tMrFeeItemFId", mrFeeItemId);
								List<RealRoomHasMrLastRecord> mrLastRecords = realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap, false);
								if(mrLastRecords!=null && mrLastRecords.size()>0){
									RealRoomHasMrLastRecord mrLastRecord = mrLastRecords.get(0);
									if(mrLastRecord.getLastRecord() >= 0){
										startValue = mrLastRecord.getLastRecord();
									}
								}
								String payBillFullName = groupBuilding.getName() + "_" + HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "_" +HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "_" + HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));
								RealRoomHasMrLastRecordEntity rrRecord = getRealRoomLastRecord(realRoomLastRecordList, payBillFullName, mfiWithFormula);
								if (rrRecord != null && rrRecord.getLastRecord() > 0){
									double d = endValue - rrRecord.getLastRecord();
									if(d<0 && DataUtil.isEmpty(HSSFCellUtil.getStringValue(startVlauecell)) && calculatePriceType.equals(1)){
										result = "第"+ (i+1) + "行本次导入的读数必须大于上次导入上期读数，"+ pfdTemp.getName() + "上次导入的读数是：" + rrRecord.getLastRecord();
										return result;
									}
								}else if(DataUtil.isEmpty(HSSFCellUtil.getStringValue(row.getCell(5))) && calculatePriceType.equals(1)) {
									result = "第"+ (i+1) + "行房间首次导入，"+  pfdTemp.getName()+ "必须录入上期读数";
									return result;
								}
							}

							//抄表倍率：空、默认为1
							Double multiplierTimes = !DataUtil.isEmpty(HSSFCellUtil.getNumbericValue(row.getCell(4))) ? HSSFCellUtil.getNumbericValue(row.getCell(4)) : 1.0;
							List<CalculateRuleEntity> calculateRuleEntityList = propertyPayConfigService.getCalculateRuleByStandardRooId(standardRoomId);
							logger.info("[calculateRuleEntityList]"+ JSONArray.toJSONString(calculateRuleEntityList));
							if(DataUtil.isEmpty(calculateRuleEntityList) || calculateRuleEntityList.size() > 1) {
								continue;
							}
							CalculateRuleEntity calculateRuleEntity = calculateRuleEntityList.get(0);
							Map<String, Double> priceMap = getPriceForMrNew(calculateRuleEntity, startValue, endValue, verifyMrDataEntity.getMrImportDataEntities(), multiplierTimes, realRoomId);
							logger.info("[priceMap]"+JSONArray.toJSONString(priceMap));
							if(DataUtil.isEmpty(priceMap)) continue;
							String signalPriceStr = "";
							List<MrFeeItemFormula> mrFeeItemFormulas = calculateRuleEntity.getMrFeeItemFormulas();
							for (int i1 = 0; i1 < mrFeeItemFormulas.size(); i1++) {
								MrFeeItemFormula mrFeeItemFormula = mrFeeItemFormulas.get(i1);
								Double unitValue = mrFeeItemFormula.getUnitValue();
								if(DataUtil.isEmpty(unitValue)) continue;
								if(i1 == 0){
									signalPriceStr +=  unitValue.toString();
								} else {
									signalPriceStr += " / " + unitValue.toString();
								}
							}
							pfdTemp.setSignalPriceStr(signalPriceStr);
							pfdTemp.setPrice(DataUtil.isEmpty(priceMap.get("totalPrice")) ? 0 : priceMap.get("totalPrice"));
							pfdTemp.setPriorValue(startValue);
							pfdTemp.setNowValue(endValue);
							//用量=（结束-开始）乘以 100 在乘以 倍率
							Long priceUnitValue = BigDecimal.valueOf(endValue).subtract(BigDecimal.valueOf(startValue)).multiply(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(multiplierTimes)).longValue();
							pfdTemp.setPriceUnitValue(priceUnitValue);
							pfdTemp.settGbId(gbId);
							pfdTemp.settBillCycleId(gbbcId);

							pfdTemp.settRealRoomId(realRoomId);
							pfdTemp.setTargetId(mrFeeItemId);
							pfdTemp.settMrStandardRoomId(standardRoomId);
							pfdTemp.setMultiplierTimes(multiplierTimes);
							pfdTemp.setSys0AddTime(now);
							pfdTemp.setSys0AddUser(userId);
							pfdTemp.setSys0DelState(0);

							if(pfdTemp.getPrice() >= 0) {
								pdfTemps.add(pfdTemp);
							}
							k ++;
							logger.info("============="+startRow+" row end=======================");
						}
					}
					//锁定数据--更改账期操作状态
					GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
					groupBuildingBillCycle.setId(gbbcId);
					groupBuildingBillCycle.setOperateStatus(1);
					groupBuildingBillCycleDao.updateGroupBuildingBillCycle(groupBuildingBillCycle);

					//处理账单明细数据
					new Thread(new ImportFeeDetailTempRunnable(gbbcId, gbId, pdfTemps, this)).start();

					result = "操作成功，后台正在处理数据！";
				}
			}
		}
		
		return result;
	}

	/**
	 * 获取“抄表”合计值---多表
	 * @param calculateRuleEntity
     * @param startValue
     * @param endValue
	* @param multiplierTimes
	* @param realRoomId
	 *@param mrFeeItemId 
	 *@return
     */
	private Map<String, Double> getPriceForMrNew(CalculateRuleEntity calculateRuleEntity, double startValue, double endValue, List<MrImportDataEntity> mrImportDataEntities, Double multiplierTimes, BigInteger realRoomId){
		Map<String, Double> map = new HashMap<String, Double>();
		BigDecimal totalPrice = BigDecimal.ZERO;
		//定价方式（1：根据读数计算；2：根据其他费用用量计算）
		if(calculateRuleEntity.getCalculatePriceType().equals(1)) {
			//计算规则（1：分段计费；2：阶梯计费）
			BigDecimal value = BigDecimal.ZERO;
			value = BigDecimal.valueOf(endValue).subtract(BigDecimal.valueOf(startValue));
			//最终用量=读数之差乘以倍率
			value = value.multiply(BigDecimal.valueOf(multiplierTimes));
			totalPrice = getTotalPrice(calculateRuleEntity, value);
		} else {
			logger.info("[mrImportDataEntities]"+JSONArray.toJSONString(mrImportDataEntities));
			if(DataUtil.isEmpty(mrImportDataEntities)){
				return null;
			}
			BigDecimal percent = BigDecimal.valueOf(calculateRuleEntity.getMoreMrCalculatePercent()).divide(new BigDecimal(100)).setScale(2);
			//一户多表时的计算方式（1：总用量；2：各表用量；）
			if(calculateRuleEntity.getMoreMrCalculateType() == 1) {
				BigDecimal val = BigDecimal.ZERO;
				for (MrImportDataEntity mrImportDataEntity : mrImportDataEntities) {
					if(mrImportDataEntity.getRealRoomId().equals(realRoomId) && mrImportDataEntity.getFeeItemId().equals(calculateRuleEntity.getBasisItemId())) {
						//读数 获取倍率
						double mrNameValue = mrImportDataEntity.getMrNameValue();
						Double multiplierTimes1 = mrImportDataEntity.getMultiplierTimes();
						//最终用量=读数之差乘以倍率
						val = val.add(BigDecimal.valueOf(mrNameValue).multiply(BigDecimal.valueOf(multiplierTimes1)));
					}
				}

				val = val.multiply(percent);
				totalPrice = getTotalPrice(calculateRuleEntity, val);
			} else {
				for (MrImportDataEntity mrImportDataEntity : mrImportDataEntities) {
					if(mrImportDataEntity.getRealRoomId().equals(realRoomId) && mrImportDataEntity.getFeeItemId().equals(calculateRuleEntity.getBasisItemId())) {
						//读数 获取倍率
						BigDecimal doubleValue = BigDecimal.valueOf(mrImportDataEntity.getMrNameValue()).multiply(percent);
						Double multiplierTimes1 = mrImportDataEntity.getMultiplierTimes();
						//最终用量=读数之差乘以倍率
						doubleValue = doubleValue.multiply(BigDecimal.valueOf(multiplierTimes1));
						totalPrice = totalPrice.add(getTotalPrice(calculateRuleEntity, doubleValue));
					}
				}
			}
		}

		if(totalPrice.doubleValue() >= 0) {
			map.put("totalPrice", totalPrice.doubleValue()*100);//单价存的是元，在其他地方是根据分进行计算的
			return map;
		}

		return null;
	}

	private BigDecimal getTotalPrice(CalculateRuleEntity calculateRuleEntity, BigDecimal value) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		if (value.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		//参与计算的用量
		BigDecimal calculateValue = value;
		List<MrFeeItemFormula> mrFeeItemFormulas = calculateRuleEntity.getMrFeeItemFormulas();
		if(calculateRuleEntity.getCalculateRule().equals(1)) {//分段计费
			for (int i = 0; i < mrFeeItemFormulas.size(); i++) {
				MrFeeItemFormula mrFeeItemFormula = mrFeeItemFormulas.get(i);
				Double startValue1 = mrFeeItemFormula.getStartValue();
				Double endValue1 = mrFeeItemFormula.getEndValue();
				//计算方式（1：单价计算；2定额计算）
				if(mrFeeItemFormula.getCalculateType().equals(1)) {
					if(i == mrFeeItemFormulas.size() - 1 || (value.doubleValue() > startValue1 && value.doubleValue() <= endValue1)) {//最后一段需要使用剩余用量 或者在某段中间的量
						totalPrice = totalPrice.add(calculateValue.multiply(BigDecimal.valueOf(mrFeeItemFormula.getUnitValue())));
					} else {
						totalPrice = totalPrice.add(BigDecimal.valueOf(endValue1 - startValue1).multiply(BigDecimal.valueOf(mrFeeItemFormula.getUnitValue())));
					}
				} else {
					totalPrice = totalPrice.add(BigDecimal.valueOf(mrFeeItemFormula.getUnitValue()));
				}
				//用量小于等于阶段截止数则结束计算
				if(value.doubleValue() <= endValue1) break;
				calculateValue = calculateValue.subtract(BigDecimal.valueOf(endValue1).subtract(BigDecimal.valueOf(startValue1)));
			}
		} else {//阶梯计费
			for (MrFeeItemFormula mrFeeItemFormula : mrFeeItemFormulas) {
				Double startValue1 = mrFeeItemFormula.getStartValue();
				Double endValue1 = mrFeeItemFormula.getEndValue();
				if(startValue1 < calculateValue.doubleValue() && calculateValue.doubleValue() <= endValue1) {
					//计算方式（1：单价计算；2定额计算）
					if(mrFeeItemFormula.getCalculateType().equals(1)) {
						totalPrice = totalPrice.add(calculateValue.multiply(BigDecimal.valueOf(mrFeeItemFormula.getUnitValue())));
					} else {
						totalPrice = totalPrice.add(BigDecimal.valueOf(mrFeeItemFormula.getUnitValue()));
					}
					break;
				}
			}
		}
		return totalPrice;
	}

	/**
	 * 获取“抄水表”合计值、单价
	 * 
	 * @param mfiWithFormula
	 * @param startValue
	 * @param endValue
	 * @return
	 */
	private Map<String, Double> getPriceForMr(MrFeeItemWithFormula mfiWithFormula, double startValue, double endValue){
		Map<String, Double> map = new HashMap<String, Double>();
		double totalPrice = 0;
		double signalPrice = 0;
		if(mfiWithFormula.getMfifList().size() == 1){//单一计价规则
			double d = endValue - startValue;
			signalPrice = mfiWithFormula.getMfifList().get(0).getUnitValue();
			totalPrice = d*signalPrice;
			map.put("signalPrice", signalPrice);// 只有“单一计价规则”需要存储
		}else{//按阶梯规则来计算
			double d = endValue - startValue;//用量
			for(int k = 0; k < mfiWithFormula.getMfifList().size(); k++){
				MrFeeItemFormula mrFeeItemFormula = mfiWithFormula.getMfifList().get(k);
				signalPrice = mrFeeItemFormula.getUnitValue();
				if(d>mrFeeItemFormula.getEndValue()){
					totalPrice += (mrFeeItemFormula.getEndValue()-mrFeeItemFormula.getStartValue()) * signalPrice;
				} else {
					totalPrice += (d - mrFeeItemFormula.getStartValue())* signalPrice;
					break;
				}
			}
		}
		map.put("totalPrice", totalPrice);
		return map;
	}
	
	/**
	 * 校验数据有效性<p>
	 * 周期导入模板校验如下：
	 * 
		1、模板上已存在的所有列（包括账单类型的备选列）不允许删除
		2、以下标题名称不允许修改：配置项、小区、楼栋、单元、房号、业主姓名
		3、以下列为必填：本期读数
		4、以下列为非必填：上期读数
	 * @param verifyMrDataEntity
	 */
	private String verifyMeterReadingData(VerifyMrDataEntity verifyMrDataEntity) {
		String verifyResult = "通过校验";
		Map<String, BigInteger> mrFeeItemIds = new HashMap<String, BigInteger>();
		List<MrImportDataEntity> mrImportDataEntities = new ArrayList<MrImportDataEntity>();
		int validCounts = 0;
		for (int g = 0; g < verifyMrDataEntity.getWb().getNumberOfSheets(); g++) {
			HSSFSheet sheet = verifyMrDataEntity.getWb().getSheetAt(g);

			String[] colNames = { "楼栋", "单元", "门牌"};
			for (int j = 0; j < colNames.length; j++) {
				if (!colNames[j].equals(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(j)))) {
					return "第3行，第" + (j + 1) + "列的名称不是" + colNames[j] + ", 请重新下载抄表模板";
				}
			}

			if(!"上期读数".equals(HSSFCellUtil.getStringValue(sheet.getRow(2).getCell(5)))
					|| !"本期读数".equals(HSSFCellUtil.getStringValue(sheet.getRow(2).getCell(6)))) {
				return "模板不正确，请重新下载";
			}

			{
				int rowNumStart = 3;
				//费用项开始列
				int colNumStart = 3;
				validCounts += sheet.getLastRowNum() - rowNumStart + 1;
				//校验收费项配置数据
				MrFeeItem mriQry = new MrFeeItem();
				mriQry.setGbId(verifyMrDataEntity.getGbId());
				mriQry.setName(HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(colNumStart)));
				List<MrFeeItem> mriList = mrFeeItemBaseService.getMrFeeItemByCondition(MapConverter.convertBean(mriQry));
				if(mriList.isEmpty()){
					return "收费配置为空，请进入【抄表收费项】中进行配置。或者Excel中缴费配置项不正确，请重新导出抄表模板";
				}
				if(mriList.size() > 1) {
					return "收费配置重名，请进入【抄表收费项】中进行配置";
				}
				if(sheet.getLastRowNum() == 2) {
					return "请添加要导入的数据";
				}
				String checkNameMsg = "【" + mriList.get(0).getName() + "】的";
				Set<String> roomInfoSet = new HashSet<String>();
				for(int i = rowNumStart; i <= sheet.getLastRowNum(); i++){
					boolean isEmptyRow = isEmptyRow(sheet, i);
					if(isEmptyRow) continue;

					// 楼栋、房号  不能为空
					if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0))))
						return checkNameMsg + "第"+(i+1)+"行的楼栋不能为空";
					if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2))))
						return checkNameMsg + "第"+(i+1)+"行的门牌不能为空";

					String roomInfo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "-" +HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "-" + HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));
					BigInteger realRoomId = verifyMrDataEntity.getRoomInfoMap().get(roomInfo);
					if(realRoomId == null) {
						return checkNameMsg + "第"+(i+1)+"行的楼栋房号不存在！";
					}

					String roomDataInfo = roomInfo;
					String mrName = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(3));
					Double multiplierTimes = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(4));
					//校验抄表计费表名称和倍率不能为空
					if(DataUtil.isEmpty(mrName)) {
						return checkNameMsg + "第" + (i + 1) + "行的计费表名称不能为空！";
					}
					if(DataUtil.isEmpty(multiplierTimes)) {
						return checkNameMsg + "第" + (i + 1) + "行的倍率不能为空！";
					}

					roomDataInfo = roomDataInfo + "_" + mrName + "_" + multiplierTimes;
					BigInteger feeItemId = mriList.get(0).getId();
					mrFeeItemIds.put(mriList.get(0).getName(), feeItemId);
					//校验读数
					Integer calculatePriceType = 1;
					try {
						//校验抄表计费表名称和倍率是否为模板原始数据
						try {
							Map<String, Object> paraMap = new HashMap<String, Object>();
							paraMap.put("mrName", mrName);
							paraMap.put("multiplierTimes", multiplierTimes);
							paraMap.put("tMrFeeItemId", feeItemId);
							paraMap.put("tRealRoomId", realRoomId);
							List<MrStandardRoom> mrStandardRoomByCondition = mrStandardRoomBaseService.getMrStandardRoomByCondition(paraMap);
							if(DataUtil.isEmpty(mrStandardRoomByCondition)) {
								return checkNameMsg + "第" + (i + 1) + "行的计费表名称/倍率数据不是默认模板数据！";
							}
							if(mrStandardRoomByCondition.size() > 1) {
								return checkNameMsg + "第" + (i + 1) + "行的房间收费标准数据存在问题！";
							}
							//房间ID_费用项ID_计费表名称
							verifyMrDataEntity.getMrStandardRoomInfoMap().put(realRoomId+"_"+feeItemId+"_"+mrName, mrStandardRoomByCondition.get(0).getId());
							//是否为使用其他费用项用量计算:（1：根据读数计算；2：根据其他费用用量计算）
							calculatePriceType = propertyPayConfigService.getCalculatePriceTypeByStandardRoomId(mrStandardRoomByCondition.get(0).getId());
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
							return checkNameMsg + "第" + (i + 1) + "行的倍率数据格式不正确！";
						}

						if(DataUtil.isEmpty(calculatePriceType)) {
							return checkNameMsg + "第" + (i + 1) + "行的房间费用项没有配置计算规则！";
						}

						//存在空的情况不进行读数大小校验
						if(DataUtil.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(5))) && DataUtil.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(6))) && calculatePriceType.equals(1)) continue;
						if(!DataUtil.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(5))) && DataUtil.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(6))) && calculatePriceType.equals(1)) continue;
						double startValue = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(5));
						double endValue = HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(6));

						if (startValue > 0 && startValue > endValue) {
							return checkNameMsg + "第" + (i + 1) + "行的本期读数必须要大于上期读数";
						}

						//存储每个房间每个费用项各个表的用量数据
						Double mrNameValue = BigDecimal.valueOf(endValue).subtract(BigDecimal.valueOf(startValue)).doubleValue();
						MrImportDataEntity mrImportDataEntity = new MrImportDataEntity();
						mrImportDataEntity.setRealRoomId(realRoomId);
						mrImportDataEntity.setFeeItemId(feeItemId);
						mrImportDataEntity.setMrName(mrName);
						mrImportDataEntity.setMultiplierTimes(multiplierTimes);
						mrImportDataEntity.setMrNameValue(mrNameValue);
						mrImportDataEntities.add(mrImportDataEntity);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
						return checkNameMsg + "第" + (i + 1) + "行的本期读数和上期读数只能录入数字";
					}

					//校验数据是否重复（房间相同，表名称相同，倍率相同）
					if(!roomInfoSet.add(roomDataInfo)){
						return checkNameMsg + "第" + (i + 1) + "行的房间数据重复！";
					}
				}
			}
		}
		verifyMrDataEntity.setValidCounts(validCounts);
		verifyMrDataEntity.setMrFeeItemIds(mrFeeItemIds);
		verifyMrDataEntity.setMrImportDataEntities(mrImportDataEntities);

		return verifyResult;
	}
	
	/**
	 * 导入临时数据
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@Override
	@Transactional
	public String importTmpBillData(HttpServletRequest request) throws IOException{
		String result = "导入成功";
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
            HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
            HSSFSheet sheet = wb.getSheetAt(0);

            BigInteger gbId = ParamUtils.getBigInteger(request, "gbIdForTmpBill", null);
            BigInteger gbbcId = ParamUtils.getBigInteger(request, "gbbcIdForTmpBill", null);

            String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
            String urlSuffix = "?" + pageIndexName + "=" + ParamUtils.getInt(request, "currentPage", 1);
            request.setAttribute(JSPConstants.ToURL, "../groupBuildingBillCycle/billCycleList.html" + urlSuffix);//保证导入后仍停留在之前的页面

            String gbName = request.getParameter("gbNameForTmpBill");
            if(!gbName.equals(HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0)))){
                result = "小区名称不正确，请确认模板是否正确";
            } else {
            	List<PropertyFeeDetailTemp> propertyFeeDetailTemps = new ArrayList<PropertyFeeDetailTemp>();
                String verifyResult = verifyImportDataFormat(sheet, propertyFeeDetailTemps, gbId);

                if(!verifyResult.equals("通过校验")){
                	result = "校验失败，原因如下：\\r\\r"+ verifyResult;
                } else {
                	// 处理t_property_fee_detail_temp表f_id
                	String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
                	BigInteger userId = UserContext.getOperIdBigIntegerMustExist();
                	List<BigInteger> pfdDetailTempIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail_temp, propertyFeeDetailTemps.size());
                	for(int i=0; i<propertyFeeDetailTemps.size(); i++){
                		PropertyFeeDetailTemp pfdDetailTemp = propertyFeeDetailTemps.get(i);
                		pfdDetailTemp.setId(pfdDetailTempIds.get(i));
                		pfdDetailTemp.setType(FeeTypeDict.Lin_Shi);
                		pfdDetailTemp.settGbId(gbId);
                		pfdDetailTemp.settBillCycleId(gbbcId);
                		pfdDetailTemp.setSys0AddUser(userId);
                		pfdDetailTemp.setSys0AddTime(now);
                		pfdDetailTemp.setSys0DelState(0);
                	}
                	//删除临时表中的该账期对应的所有数据  逻辑删除
        			groupBuildingBillCycleDao.deleteFeeDetailTempByCycleAndGb(gbbcId, gbId, FeeTypeDict.Lin_Shi);
        			//将数据保存到费用项临时表中t_property_fee_detail_temp
					int batchStepSize = 100; //一次插入100条，分批插入能提高性能
					int j = 0;
					for (int i = 0; i < (propertyFeeDetailTemps.size() / batchStepSize) + 1; i++) {
						int endIndex = (i + 1) * batchStepSize > propertyFeeDetailTemps.size() ? propertyFeeDetailTemps.size() : (i + 1) * batchStepSize;

						List<PropertyFeeDetailTemp> subList = propertyFeeDetailTemps.subList(i * batchStepSize, endIndex);
						if (subList.size() > 0) {
							j += propertyFeeDetailTempBaseDao.insertPropertyFeeDetailTempBatch(subList);
						}
					}
        			if(j>0){
        				result = "操作成功！";
        			} else {
        				result = "操作失败！";
        			}
                }
            }
            
        }

        request.setAttribute(JSPConstants.OprtResult, result);
		return result;
	}
	
	/**
	 * 校验即将导入的数据的格式问题
	 * 
	 * @param sheet
	 * @param propertyFeeDetailTemps
	 * @param gbId
	 * @return
	 */
    private String verifyImportDataFormat(HSSFSheet sheet, List<PropertyFeeDetailTemp> propertyFeeDetailTemps, BigInteger gbId){
        String resultInfo =  "";
        int coloumNum=sheet.getRow(1).getLastCellNum();
        if(coloumNum<=4){// 没有临时收费项
        	resultInfo = "没有临时收费项，导入失败！";
        } else {
        	//临时收费
    		Map<String,Object> paramMap = new HashMap<String,Object>();
    		paramMap.put("tGbId", gbId);
    		List<TmpFeeItem> tmpFeeItemList = tmpFeeItemBaseDao.selectTmpFeeItemByCondition(paramMap, true);
    		if(tmpFeeItemList==null || tmpFeeItemList.size()==0){// 临时收费项没有配置
				resultInfo = verifyImportDataFormatWithNoConfig(coloumNum, sheet, propertyFeeDetailTemps, gbId);
    		} else {
    			Collections.reverse(tmpFeeItemList);
				resultInfo = verifyImportDataFormatWithConfig(coloumNum, sheet, propertyFeeDetailTemps, gbId, tmpFeeItemList);
    		}
        }

        return StringUtils.isEmpty(resultInfo) ? "通过校验" : resultInfo;
    }

    /**
     * 处理有配置的临时收费项
     * @param coloumNum
     * @param sheet
     * @param propertyFeeDetailTemps
     * @param gbId
     * @return
     */
    private String verifyImportDataFormatWithConfig(int coloumNum, HSSFSheet sheet, List<PropertyFeeDetailTemp> propertyFeeDetailTemps, BigInteger gbId, List<TmpFeeItem> tmpFeeItemList){
    	String resultInfo =  "";
    	Set<String> roomInfoSet = new HashSet<String>();
    	Map<String, BigInteger> roomInfoMap = getRoomStrByGbId(gbId);
        for (int i = 3; i < sheet.getLastRowNum() + 1; i++) {
			if (isEmptyRow(sheet, i)) {continue;}// 空行跳过，不导入
            try {
                if(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0))==null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))) {
                    return  "第" + (i + 1) + "行的楼栋号不能为空！";
                }
                if(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2))==null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))) {
                    return  "第" + (i + 1) + "行的房间号不能为空！";
                }
                String roomInfo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "-"+
                				  HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "-" +
                				  HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));

                if(!roomInfoSet.add(roomInfo)){
                    return  "第" + (i + 1) + "行的房间号数据重复！";
                }

                int dataStartColIndex = 3;//数据开始列
                int mergeNum = 0;

                //=========================
            	HSSFCell totalAmtCell = sheet.getRow(i).getCell(coloumNum-1);
                double totalAmt = 0.0;
                if(DataUtil.isEmpty(totalAmtCell)){
                	return "第" + (i + 1) + "行的费用合计不能为空！整表不予导入！";
                } else {
                	totalAmt = NumberUtils.doubleM100ToLong(totalAmtCell.getNumericCellValue());
                }
                //=========================
                for(int p=0; p<tmpFeeItemList.size(); p++){
                	TmpFeeItem tmpFeeItem = tmpFeeItemList.get(p);
                	int valSize = 0;
                	if(1==tmpFeeItem.getCalculateType() || 3==tmpFeeItem.getCalculateType()){
    					//1===>费用合计", "单价", "建筑面积"
                		//3===>"费用合计", "单价", "用量"
                		valSize = 3;
    				} else if(2==tmpFeeItem.getCalculateType()){
    					//2===>"费用合计"
    					valSize = 1;
    				}
                	//校验金额存在值，不能为负数且必须为数字
                    for(int k=1; k <= valSize; k++){
                        if(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)!=null
                                && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)))){
                            if(HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)) < 0) {
                                return "第" + (i + 1) + "行的第"+dataStartColIndex+mergeNum + k+"列必须大于等于零！！";
                            }
                        }
                    }

                    // 费用合计
                    Double totalPrice = 0.0;
                    // 单价
                    Double signalPrice= 0.0;
                    // 用量
					Long priceunitvalue  = 0L;
                    // title
                    String title = HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(dataStartColIndex+mergeNum));
					if (sheet.getRow(i).getCell(dataStartColIndex+mergeNum) == null || sheet.getRow(i).getCell(dataStartColIndex+mergeNum).getNumericCellValue() <= 0) {
						//总价 为空或0时，跳过不需要保存
						mergeNum += valSize;
						continue;
					} else {
						//金额要4舍5入到分
						totalPrice = DataUtil.isEmpty(sheet.getRow(i).getCell(dataStartColIndex+mergeNum)) ? 0 : sheet.getRow(i).getCell(dataStartColIndex+mergeNum).getNumericCellValue()*100;//总价
					}

					if(1==tmpFeeItem.getCalculateType() || 3==tmpFeeItem.getCalculateType()){
						if (sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1) == null || sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1).getNumericCellValue() <= 0) {
							signalPrice = null;//单价
						} else {
							signalPrice = DataUtil.isEmpty(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1)) ? 0 : sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1).getNumericCellValue()*100;//单价
						}

						if (sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2) == null || sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2).getNumericCellValue() <= 0) {
							priceunitvalue = null;//用量
						} else {
							priceunitvalue = DataUtil.isEmpty(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2)) ? 0 : NumberUtils.doubleM100ToLong(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2).getNumericCellValue());//用量
						}
    				}

					mergeNum += valSize;

                    PropertyFeeDetailTemp pfdDetailTemp= new PropertyFeeDetailTemp();
                    pfdDetailTemp.setPrice(totalPrice);
                    pfdDetailTemp.setTotalAmount(totalAmt);
                    pfdDetailTemp.setName(title);
                    pfdDetailTemp.setSignalPrice(signalPrice);
                    pfdDetailTemp.setPriceUnitValue(priceunitvalue);
                    // 获取t_real_room f_id
                    BigInteger realRoomId = roomInfoMap.get(roomInfo);
					if(realRoomId == null || "".equals(realRoomId)) {
						return "第" + (i + 1) + "行的楼栋房号不存在！";
					}
                    pfdDetailTemp.settRealRoomId(realRoomId);

					if(pfdDetailTemp.getPrice() > 0) {
						propertyFeeDetailTemps.add(pfdDetailTemp);
					}
                }
				if(DataUtil.isEmpty(propertyFeeDetailTemps)) {
					return "数据信息有误，请检查（费用项合计不能全为空）";
				}
            } catch(Exception e) {
                e.printStackTrace();
                resultInfo += "第" + (i + 1) + "行数据格式检验错误！\\r";
            }
        }

        return resultInfo;
    }

    /**
     * 处理没有配置的临时收费项
     * @param coloumNum
     * @param sheet
     * @param propertyFeeDetailTemps
     * @param gbId
     * @return
     */
    private String verifyImportDataFormatWithNoConfig(int coloumNum, HSSFSheet sheet, List<PropertyFeeDetailTemp> propertyFeeDetailTemps, BigInteger gbId){
    	String resultInfo =  "";
    	Set<String> roomInfoSet = new HashSet<String>();
    	Map<String, BigInteger> roomInfoMap = getRoomStrByGbId(gbId);
        for (int i = 3; i < sheet.getLastRowNum() + 1; i++) {
			if (isEmptyRow(sheet, i)) {continue;}// 空行跳过，不导入
            try {
                if(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0))==null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))) {
                    return  "第" + (i + 1) + "行的楼栋号不能为空！";
                }
                if(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2))==null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))) {
                    return  "第" + (i + 1) + "行的房间号不能为空！";
                }
                String roomInfo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "-"+
                				  HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "-" +
                				  HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));

                if(!roomInfoSet.add(roomInfo)){
                    return  "第" + (i + 1) + "行的房间号数据重复！";
                }

                int dataStartColIndex = 3;//数据开始列
                int mergeNum = 0;
                int titleNum = (coloumNum-dataStartColIndex-1)/3;
                HSSFCell totalAmtCell = sheet.getRow(i).getCell(coloumNum-1);
                double totalAmt = 0.0;
                if(DataUtil.isEmpty(totalAmtCell)){
                	return "第" + (i + 1) + "行的费用合计不能为空！整表不予导入！";
                } else {
                	totalAmt = NumberUtils.doubleM100ToLong(totalAmtCell.getNumericCellValue());
                }

                for(int j = 0; j < titleNum; j++) {
                    //校验金额存在值，不能为负数且必须为数字
                    for(int k=1; k <= 3; k++){
                        if(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)!=null
                                && !"".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)))){
                            if(HSSFCellUtil.getNumbericValue(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + k)) < 0) {
                                return "第" + (i + 1) + "行的第"+dataStartColIndex+mergeNum + k+"列金额必须大于等于零！！";
                            }
                        }
                    }

                    // 费用合计
                    Double totalPrice = 0.0;
                    // 单价
                    Double signalPrice= 0.0;
                    // 用量
					Long priceunitvalue  = 0L;
                    // title
                    String title = HSSFCellUtil.getStringValue(sheet.getRow(1).getCell(dataStartColIndex+mergeNum));

					if (sheet.getRow(i).getCell(dataStartColIndex+mergeNum) == null || sheet.getRow(i).getCell(dataStartColIndex+mergeNum).getNumericCellValue() <= 0) {
						//总价 为空或0时，跳过不需要保存
						mergeNum += 3;
						continue;
					} else {
						//金额要4舍5入到分
						totalPrice = DataUtil.isEmpty(sheet.getRow(i).getCell(dataStartColIndex+mergeNum)) ? 0 : sheet.getRow(i).getCell(dataStartColIndex+mergeNum).getNumericCellValue()*100;//总价
					}

					if (sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1) == null || sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1).getNumericCellValue() <= 0) {
						signalPrice = null;//单价
					} else {
						signalPrice = DataUtil.isEmpty(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1)) ? 0 : sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 1).getNumericCellValue()*100;//单价
					}

					if (sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2) == null || sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2).getNumericCellValue() <= 0) {
						priceunitvalue = null;//用量
					} else {
						priceunitvalue = DataUtil.isEmpty(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2)) ? 0 : NumberUtils.doubleM100ToLong(sheet.getRow(i).getCell(dataStartColIndex+mergeNum + 2).getNumericCellValue());//用量
					}

                    mergeNum += 3;

                    PropertyFeeDetailTemp pfdDetailTemp= new PropertyFeeDetailTemp();
                    pfdDetailTemp.setPrice(totalPrice);
                    pfdDetailTemp.setTotalAmount(totalAmt);
                    pfdDetailTemp.setName(title);
                    pfdDetailTemp.setSignalPrice(signalPrice);
                    pfdDetailTemp.setPriceUnitValue(priceunitvalue);
                    // 获取t_real_room f_id
                    BigInteger realRoomId = roomInfoMap.get(roomInfo);
					if(realRoomId == null || "".equals(realRoomId)) {
						return "第" + (i + 1) + "行的楼栋房号不存在！";
					}
                    pfdDetailTemp.settRealRoomId(realRoomId);

					if(pfdDetailTemp.getPrice() > 0) {
						propertyFeeDetailTemps.add(pfdDetailTemp);
					}
                }
				if(DataUtil.isEmpty(propertyFeeDetailTemps)) {
					return "数据信息有误，请检查（费用项合计不能全为空）";
				}
            } catch(Exception e) {
                e.printStackTrace();
                resultInfo += "第" + (i + 1) + "行数据格式检验错误！\\r";
            }
        }

        return resultInfo;
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
    
    /**
     * 是否空行
     * @param sheet
     * @param i
     * @return
     */
    private boolean isEmptyRow(HSSFSheet sheet, int i) {
        boolean isEmptyRow = false;
        if (sheet.getRow(i) == null) {//处理空行的情况，有可能用户没有删除空白行
            return true;
        }

        if(StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))
                && StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)))
                && StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))){
            // 小区	楼栋	单元	房号 都为空时，也认为是空行, 跳过不导入
            isEmptyRow = true;
        }

        return isEmptyRow;
    }

	/**
	 * 生成账单（1：抄表收费，2：固定收费，3：临时收费）
	 */
	@Override
	@Transactional(propagation = Propagation.NESTED, timeout=600)
	public String createPayBill(BigInteger cycleId, BigInteger gbId) {
		String msg = "没有收费项数据！";
		GroupBuildingBillCycle gbbc = groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleBySeqId(cycleId);
		{
			PayBillType payBillType = payBillTypeBaseDao.selectPayBillTypeBySeqId(gbbc.gettPayBillTypeId());
			//查询临时表数据
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("tBillCycleId",cycleId);
			List<PropertyFeeDetailTemp> propertyFeeDetailTempList = propertyFeeDetailTempBaseDao.selectPropertyFeeDetailTempByCondition(paraMap, false);
			if(!DataUtil.isEmpty(propertyFeeDetailTempList)) {//存在收费项数据才进行账单生成
				//查询改小区下，需要生成账单的门牌
				List<RealRoom> realRooms = groupBuildingBillCycleDao.getNeedCreateBillRealRoom(cycleId, gbId);
				if(!DataUtil.isEmpty(realRooms)) {//存在需要生成账单的门牌
					List<BigInteger> payBillIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, realRooms.size());
					List<BigInteger> feeDetailIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail, propertyFeeDetailTempList.size());
					// 生成t_mr_pay_bill_record f_id
					int chaoBiaoNum = 0;
					for(PropertyFeeDetailTemp pfdTemp : propertyFeeDetailTempList){
						if(FeeTypeDict.Chao_Biao.equals(pfdTemp.getType())){
							chaoBiaoNum++;
						}
					}
					List<BigInteger> mrPayBillRecordIds = null;
					List<MrPayBillRecord> mrPayBillRecords = null;
					List<RealRoomHasMrLastRecord> mrLastRecordForInserts = null;
					List<RealRoomHasMrLastRecord> mrLastRecordForUpdates = null;
					if(chaoBiaoNum>0){
						mrPayBillRecordIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_pay_bill_record, chaoBiaoNum);
						mrPayBillRecords = new ArrayList<MrPayBillRecord>();
						mrLastRecordForInserts = new ArrayList<RealRoomHasMrLastRecord>();
						mrLastRecordForUpdates = new ArrayList<RealRoomHasMrLastRecord>();
					}

					List<PayBill> payBillList = new ArrayList<PayBill>();
					
					List<PropertyFeeDetail> propertyFeeDetailWillInsertList = new ArrayList<PropertyFeeDetail>();
					List<FixedFeeItemHasRoom> fixedFeeItemHasRoomsWillUpdList = new ArrayList<FixedFeeItemHasRoom>();
					String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
					int i = 0;
					int j = 0;
					int mrPayBillRecordIndex = 0;
					
					for (RealRoom realRoom : realRooms) {
						List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
						//常规项账单生成截止月份list
						List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
						
						BigDecimal amount = BigDecimal.ZERO;
						Long lastUnpaid = 0L;

						boolean isCalTotalTmpAmt = false;// 是否已计算临时费用合计

						int monthToMonthSize = getMonthToMonthSize(gbbc.getBillMonthStart(), gbbc.getBillMonthEnd());
						for (PropertyFeeDetailTemp propertyFeeDetailTemp : propertyFeeDetailTempList){
							if(propertyFeeDetailTemp.gettRealRoomId().equals(realRoom.getId())) {
								//组装费用明细
								PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
								propertyFeeDetail.setId(feeDetailIds.get(j));
								propertyFeeDetail.setName(propertyFeeDetailTemp.getName());
								//费用类型=={"1":"管理费","2":"主体金","3":"垃圾处理费","4":"水费","5":"污水处理费","9":"其它"}
								//固定收费项的费用类型设置为1管理费，因为物业宝仅会抵扣管理费和主体金，固定收费全部可用进行物业宝抵扣
								if(propertyFeeDetailTemp.getType() != null && propertyFeeDetailTemp.getType().equals(2)) {
									propertyFeeDetail.setType(1);
								} else {
									propertyFeeDetail.setType(9);
								}
								propertyFeeDetail.setBillMonthSize(Long.parseLong(monthToMonthSize+""));
								if(!DataUtil.isEmpty(propertyFeeDetailTemp.getPrice())) {
									propertyFeeDetail.setTotalPrice(BigDecimal.valueOf(propertyFeeDetailTemp.getPrice()).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue());
								}
								if(!DataUtil.isEmpty(propertyFeeDetailTemp.getSignalPrice())) {
									propertyFeeDetail.setSignalPrice(propertyFeeDetailTemp.getSignalPrice().doubleValue());//单价
								}
								if(!DataUtil.isEmpty(propertyFeeDetailTemp.getSignalPriceStr())) {
									propertyFeeDetail.setSignalPriceStr(propertyFeeDetailTemp.getSignalPriceStr());
								}
								if(!DataUtil.isEmpty(propertyFeeDetailTemp.getPriceUnitValue())) {
									propertyFeeDetail.setPriceUnitValue(propertyFeeDetailTemp.getPriceUnitValue());//用量/面积
								}
								propertyFeeDetail.setTotalAmount(propertyFeeDetailTemp.getTotalAmount());
								propertyFeeDetail.settPayBillFId(payBillIds.get(i));
								propertyFeeDetail.settCycleId(cycleId);
								propertyFeeDetail.setFeeType(propertyFeeDetailTemp.getType());
								propertyFeeDetail.setSys0AddTime(DateUtils.getCurrentDate());
								propertyFeeDetail.setSys0AddUser(UserContext.getCurrUser().getId());
								propertyFeeDetail.setAllowancePrice(0L);
								propertyFeeDetail.setItemHasRoomId(propertyFeeDetailTemp.getTargetId());
								propertyFeeDetail.setMrName(propertyFeeDetailTemp.getMrName());
								propertyFeeDetail.setMultiplierTimes(propertyFeeDetailTemp.getMultiplierTimes());
								propertyFeeDetailList.add(propertyFeeDetail);
								logger.info("==price="+payBillIds.get(i)+"="+propertyFeeDetailTemp.getPrice());
								if(!DataUtil.isEmpty(propertyFeeDetailTemp.getPrice())) {
									Double totalAmt = propertyFeeDetailTemp.getTotalAmount();
									if(FeeTypeDict.Lin_Shi.equals(propertyFeeDetailTemp.getType()) && totalAmt!=null && totalAmt>0){
										if(!isCalTotalTmpAmt){
											isCalTotalTmpAmt = true;
											amount = amount.add(BigDecimal.valueOf(totalAmt).setScale(0, BigDecimal.ROUND_HALF_UP));
										}
									}else {
										//账单金额 = 费用项金额 * 缴费时长   （常规收费项）
										BigDecimal bigDecimal = BigDecimal.valueOf(propertyFeeDetailTemp.getPrice());
										if(!DataUtil.isEmpty(propertyFeeDetail.getBillMonthSize()) && propertyFeeDetail.getBillMonthSize() > 0 && FeeTypeDict.Gu_Ding.equals(propertyFeeDetailTemp.getType())) {
											bigDecimal = bigDecimal.multiply(new BigDecimal(propertyFeeDetail.getBillMonthSize()));
										}
										amount = amount.add(bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP));
									}
								}
								if(FeeTypeDict.Chao_Biao.equals(propertyFeeDetailTemp.getType())){// 抄表的有“往月欠费”（“固定”、“临时”没有）
									// 抄表数据记录
									mrProcess(mrPayBillRecords, mrLastRecordForInserts, mrLastRecordForUpdates, propertyFeeDetailTemp, payBillIds.get(i),
											mrPayBillRecordIds.get(mrPayBillRecordIndex++), feeDetailIds.get(j), now);
								}
								j++;//更新收费明细的IDS的index

								//更新常规项的费用生成截止时间
								if(FeeTypeDict.Gu_Ding.equals(propertyFeeDetailTemp.getType())) {
									FixedFeeItemHasRoom fixedFeeItemHasRoom = new FixedFeeItemHasRoom();
									fixedFeeItemHasRoom.setId(propertyFeeDetailTemp.getTargetId());
									fixedFeeItemHasRoom.setCreateBillMonth(DateUtils.convertDateToStr(DateUtils.addMonths(DateUtils.convertStrToDate(gbbc.getBillMonthEnd()), 1), "yyyy-MM-dd"));
									fixedFeeItemHasRooms.add(fixedFeeItemHasRoom);
								}
							}
						}
						

						if(amount.doubleValue() > 0) {
							//组装账单信息
							//1.查询账单数据
							PayBill payBill = new PayBill();
							payBill.setId(payBillIds.get(i));
							payBill.setAmount(amount.longValue());
							payBill.setIsPay(2);//未缴
							payBill.settRealRoomFId(realRoom.getId());
							payBill.setPropertyProprietorId(realRoom.gettPropertyProprietorFId().toString());
							payBill.setBillTypeId(gbbc.gettPayBillTypeId());
							payBill.setIsPropFee(1);//都设置为物业费
							payBill.setBillMonthSize(Long.parseLong(monthToMonthSize+""));
							payBill.setBillMonthStart(gbbc.getBillMonthStart());
							payBill.setBillMonthEnd(gbbc.getBillMonthEnd());
							payBill.setPayDayStart(gbbc.getBillPayStart());
							payBill.setPayDayEnd(gbbc.getBillPayEnd());
							BigInteger ptcId = gbbc.gettPayBillTimeCfgId();
							if(ptcId==null){
								ptcId = BigInteger.valueOf(1);/*数据库中提示不能为空 所以写了一个默认值*/
							}
							payBill.setBillTimeCfgId(ptcId);
							payBill.setBillTypeName(payBillType.getName());
							payBill.setPaytimeType(2);/*默认改为 以前的周期缴费方式*/
							payBill.setPreferType(1);
							payBill.setCycleType(1);//固定周期缴费(1固定，2选择周期)
							payBill.settBillCycleId(gbbc.getId());
							payBill.setSys0AddTime(DateUtils.getCurrentDate());
							payBill.setSys0AddUser(UserContext.getCurrUser().getId());
							payBill.setLastUnpaid(lastUnpaid);
							payBill.setBankCollectionStatus(0);
							payBillList.add(payBill);
							propertyFeeDetailWillInsertList.addAll(propertyFeeDetailList);
							fixedFeeItemHasRoomsWillUpdList.addAll(fixedFeeItemHasRooms);
							i++;//更新账单IDS的index
						} else {//如果抄表的账单金额为零，则把多余的数据进行删除
							if(!DataUtil.isEmpty(mrPayBillRecords)) {
								for (int i1 = mrPayBillRecords.size() - 1; i1 >= 0 ; i1--) {
									if(mrPayBillRecords.get(i1).gettPayBillFId().equals(payBillIds.get(i))) {
										mrPayBillRecords.remove(i1);
									}
								}
							}
						}
					}

					//保存数据
					if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailWillInsertList)) {
						//欠费关系维护:只有自动生成账期才进行欠费关系维护
                        if(gbbc.getGbbcCfgId() != null) {
                            GroupBuildingBillCycleConfig groupBuildingBillCycleConfig = groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigBySeqId(gbbc.getGbbcCfgId());
                            if (!DataUtil.isEmpty(groupBuildingBillCycleConfig.getArrearsTransfer()) && groupBuildingBillCycleConfig.getArrearsTransfer().equals(2)) {
                                groupBuildingCycleCfgService.autoCarryoverUnPaid(gbbc.gettGroupBuildingId(), payBillType.getName(), payBillList, groupBuildingBillCycleConfig.getChargingMode());
							}
                        }
                        
						logger.debug("=====payBillList======="+JSONObject.toJSON(payBillList));
						int k = payBillBaseDao.insertPayBillBatch(payBillList);
						if(k > 0) {
							msg = "生成账单成功！";
						} else {
							msg = "生成账单失败！";
						}
					} else {
						msg = "没有可以生成账单的门牌（门牌已生成该期账单）！";
					}
					
					if(!DataUtil.isEmpty(payBillList) && !DataUtil.isEmpty(propertyFeeDetailWillInsertList)) {
						//删除临时表中的该账期对应的所有数据  逻辑删除
						groupBuildingBillCycleDao.deleteFeeDetailTempByCycleAndGb(cycleId, gbId, FeeTypeDict.Gu_Ding);
						groupBuildingBillCycleDao.deleteFeeDetailTempByCycleAndGb(cycleId, gbId, FeeTypeDict.Chao_Biao);
						groupBuildingBillCycleDao.deleteFeeDetailTempByCycleAndGb(cycleId, gbId, FeeTypeDict.Lin_Shi);
						
						logger.debug("=====propertyFeeDetailList======="+JSONObject.toJSON(propertyFeeDetailWillInsertList));
						int g = propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailWillInsertList);
						if(g > 0) {
							msg = "明细生成成功！";
							// 抄表数据处理
							if(mrPayBillRecords!=null && mrPayBillRecords.size()>0){
								logger.debug("======mrPayBillRecords.size()1=============================="+mrPayBillRecords.size());
								mrPayBillRecordBaseDao.insertMrPayBillRecordBatch(mrPayBillRecords);
							}
							if(mrLastRecordForInserts!=null && mrLastRecordForInserts.size()>0){
								//插入id
								List<BigInteger> lastRecord = uuidManager.getNextUuidBigInteger(SEQConstants.t_real_room_has_mr_last_record, mrLastRecordForInserts.size());
								for (int l = 0; l < mrLastRecordForInserts.size(); l++){
									mrLastRecordForInserts.get(l).setId(lastRecord.get(l));
								}
								realRoomHasMrLastRecordBaseDao.insertRealRoomHasMrLastRecordBatch(mrLastRecordForInserts);
							}
							if(mrLastRecordForUpdates!=null && mrLastRecordForUpdates.size()>0){
								realRoomHasMrLastRecordBaseDao.updateRealRoomHasMrLastRecordBatch(mrLastRecordForUpdates);
							}
						} else {
							msg = "明细生成失败！";
						}
					}

					//更新常规项的费用生成截止时间
					if(!DataUtil.isEmpty(fixedFeeItemHasRoomsWillUpdList)) {
						fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRoomsWillUpdList);
					}

					return msg;
				}
				msg = "没有可以生成账单的门牌（门牌已生成该期账单）！";
			}
		}
		return msg;
	}

	@Override
	public Boolean getIsHasSameCycleCfg(Map<String, Object> paraMap) {
        List<GroupBuildingBillCycleConfig> isHasSameCycleCfg = groupBuildingBillCycleDao.getIsHasSameCycleCfg(paraMap);
        return !DataUtil.isEmpty(isHasSameCycleCfg);
    }

    @Override
    public boolean isHasAutoCreateCycle(String billName, BigInteger gbId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("tGbId", gbId);
        paraMap.put("billName", billName);
        List<GroupBuildingBillCycleConfig> groupBuildingBillCycleConfigs = groupBuildingBillCycleConfigBaseDao.selectGroupBuildingBillCycleConfigByCondition(paraMap, false);

        return (!DataUtil.isEmpty(groupBuildingBillCycleConfigs) && groupBuildingBillCycleConfigs.size() > 0);
    }

    /**
     * 注：任何地方不能再次引用该方法
     * 防止自动生成失败时间过了，所以使用手动生成
     * @param cycleCfgId
     * @param type
	 * @return
     */
    @Override
    public void autoCreateCycleAndPayBill(BigInteger cycleCfgId, int type) {
		if(!DataUtil.isEmpty(cycleCfgId) && type == 0) {
			groupBuildingCycleCfgService.autoCreateCycleAndPayBill(cycleCfgId);
		} else {
			//选择周期欠费计算
			List<AlterUnPaidEntity> alterUnPaidEntities = groupBuildingCycleCfgService.getNeedUnpaidPayBillAndCycle02(cycleCfgId);
			logger.info("[alterUnPaidEntities]:"+ JSON.toJSONString(alterUnPaidEntities));
			for (AlterUnPaidEntity alterUnPaidEntity : alterUnPaidEntities) {
				logger.info("[alterUnPaidEntity]:"+ JSON.toJSONString(alterUnPaidEntity));
				groupBuildingCycleCfgService.autoCarryoverUnPaid(alterUnPaidEntity.gettGroupBuildingId(), alterUnPaidEntity.getBillName(), alterUnPaidEntity.getPayBills(), alterUnPaidEntity.getChargingMode());
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String deleteGroupBuildingCycle(Map<String, Object> paraMap) {
		//删除t_group_building_cycle
        int billCycleId = groupBuildingBillCycleBaseDao.deleteGroupBuildingBillCycleLogic((BigInteger) paraMap.get("billCycleId"));
        //删除t_pay_bill_type
        int billTypeId = payBillTypeBaseService.deletePayBillTypeLogic((BigInteger) paraMap.get("billTypeId"));
        //t_pay_bill_time_cfg
        int billTimeCfgId = payBillTimeCfgBaseDao.deletePayBillTimeCfgLogic((BigInteger) paraMap.get("billTimeCfgId"));
        //删除账单信息（t_pay_bill）
		//删除账单明细信息（t_property_fee_detail）
        int i = deleteAllBillById(paraMap);

		HashMap<String, Object> stringObjectHashMap = new HashMap<>();
		stringObjectHashMap.put("tBillCycleId", paraMap.get("billCycleId"));
		List<PayBill> payBillList = payBillBaseDao.selectPayBillByCondition(stringObjectHashMap, false);
		if(!DataUtil.isEmpty(payBillList) && i == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "删除失败！包含物业宝部分抵扣账单！";
		}
        return (billCycleId > 0 || billTypeId > 0 || billTimeCfgId > 0 || i > 0) ? "删除成功" : "删除失败";
	}

	@Override
	public List<MrFeeItem> getMrFeeItemByCondition(Map<String, Object> mriQryMap) {
		return groupBuildingCycleCfgService.getMrFeeItemByCondition(mriQryMap);
	}

	@Override
	public List<TmpFeeItem> getTmpFeeItemByCondition(Map<String, Object> mriQryMap) {
		return groupBuildingCycleCfgService.getTmpFeeItemByCondition(mriQryMap);
	}

	/**
	 * "抄表"账单生成处理
	 */
	private void mrProcess(List<MrPayBillRecord> mrPayBillRecords, List<RealRoomHasMrLastRecord> mrLastRecordForInserts, List<RealRoomHasMrLastRecord> mrLastRecordForUpdates,
			PropertyFeeDetailTemp pfdTemp, BigInteger payBillId, BigInteger mpbrId, BigInteger pfdDetailId, String now){
		// t_mr_pay_bill_record
		MrPayBillRecord mpbr = new MrPayBillRecord();
		mpbr.setId(mpbrId);
		mpbr.settPayBillFId(payBillId);
		mpbr.settMrFeeItemFId(pfdTemp.getTargetId());
		mpbr.settPropertyFeeDetailFId(pfdDetailId);
		mpbr.setStartValue(pfdTemp.getPriorValue());
		mpbr.setEndValue(pfdTemp.getNowValue());
		mpbr.setSys0DelState(0);
		mrPayBillRecords.add(mpbr);
		logger.debug("======mrPayBillRecords.size()2=============================="+mpbr);
		logger.debug("======mrPayBillRecords.size()3=============================="+mrPayBillRecords);

		// t_real_room_has_mr_last_record
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tRealRoomFId", pfdTemp.gettRealRoomId());
		paramMap.put("tMrFeeItemFId", pfdTemp.getTargetId());
		List<RealRoomHasMrLastRecord> mrLastRecords = realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap, false);
		
		if(mrLastRecords==null || mrLastRecords.size()==0){// insert
			RealRoomHasMrLastRecord mrLastRecord = new RealRoomHasMrLastRecord();
			mrLastRecord.settRealRoomFId(pfdTemp.gettRealRoomId());
			mrLastRecord.settMrFeeItemFId(pfdTemp.getTargetId());
			mrLastRecord.setLastRecord(pfdTemp.getNowValue());
			mrLastRecord.setLastUpdTime(now);
			mrLastRecord.setSys0AddTime(now);
			
			mrLastRecordForInserts.add(mrLastRecord);
		} else {// update
			RealRoomHasMrLastRecord mrLastRecord = mrLastRecords.get(0);
			mrLastRecord.setLastRecord(pfdTemp.getNowValue());
			mrLastRecord.setLastUpdTime(now);
			
			mrLastRecordForUpdates.add(mrLastRecord);
		}
	}

	private RealRoomHasMrLastRecordEntity getRealRoomLastRecord(List<RealRoomHasMrLastRecordEntity> realRoomLastRecordList,
																String payBillFullName, MrFeeItemWithFormula mfiWithFormula) {
		for (int i = 0; i < realRoomLastRecordList.size(); i++) {
			RealRoomHasMrLastRecordEntity rrRecord = realRoomLastRecordList.get(i);
			if(rrRecord.getId() == null)
				continue;

			if(rrRecord.getFullName().equals(payBillFullName) && rrRecord.gettMrFeeItemFId().equals(mfiWithFormula.getId()))
				return rrRecord;
		}

		return null;
	}
	
	private static int getFeeDetailType(String itemName) {
		//'费用类型=={"1":"管理费","2":"主体金","3":"垃圾处理费","4":"水费","5":"污水处理费","9":"其它"}'
		if(!DataUtil.isEmpty(itemName)) {
			itemName = itemName.trim();
			if(itemName.equals("管理费")) {
				return 1;
			}
			if(itemName.equals("主体金")) {
				return 2;
			}
			if(itemName.equals("垃圾处理费")) {
				return 3;
			}
			if(itemName.equals("水费")) {
				return 4;
			}
			if(itemName.equals("污水处理费")) {
				return 5;
			}
		}
		return 9;
	}

	/**
	 * 字符串时间比较 str >= str1 == true;
	 * @param str
	 * @param str1
	 * @return
	 */
	private static int comparaStrDate(String str, String str1) {
		if(DataUtil.isEmpty(str) && !DataUtil.isEmpty(str1)) {
			return -1;
		}
		if(!DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
			return 1;
		}
		if(DataUtil.isEmpty(str) && DataUtil.isEmpty(str1)) {
			return 1;
		}
		long date = DateUtils.convertStrToDate(str, "yyyy-MM").getTime();
		long date1 = DateUtils.convertStrToDate(str1, "yyyy-MM").getTime();
		if(date >  date1) {
			return 1;
		} else if(date == date1) {
			return 0;
		}
		return -1;
	}


	/**
	 *计算两个日期的月份间隔数
	 * 2017-07-01 - 2017-07-02 = 1
	 * 2017-07-01 - 2017-08-01 = 2
	 * @param startStr
	 * @param endStr
	 * @return
	 */
	public static int getMonthToMonthSize(String startStr, String endStr) {
		Date start = DateUtils.convertStrToDate(startStr, "yyyy-MM");
		Date end = DateUtils.convertStrToDate(endStr, "yyyy-MM");
		//计算时间间隔
		int size = DateUtils.getDiffMonths(start, end) + 1;
		return size;
	}

	public void insertFeeDetailTemps(BigInteger gbbcId, BigInteger gbId, List<PropertyFeeDetailTemp> pdfTemps) {
		//删除明细信息
		groupBuildingBillCycleDao.deleteFeeDetailTempByCycleAndGb(gbbcId, gbId, FeeTypeDict.Chao_Biao);

		//插入明细数据
		int batchStepSize = 100;
		for (int i = 0; i < (pdfTemps.size() / batchStepSize) + 1; i++) {
			int endIndex = (i + 1) * batchStepSize > pdfTemps.size() ? pdfTemps.size() : (i + 1) * batchStepSize;
			List<PropertyFeeDetailTemp> subList = pdfTemps.subList(i * batchStepSize, endIndex);
			if (subList.size() > 0) {
				propertyFeeDetailTempBaseService.insertPropertyFeeDetailTempBatch(subList);
			}
		}

		//释放锁定数据--更改账期操作状态
		GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
		groupBuildingBillCycle.setId(gbbcId);
		groupBuildingBillCycle.setOperateStatus(0);
		groupBuildingBillCycleDao.updateGroupBuildingBillCycle(groupBuildingBillCycle);
	}
}
/**
 * 异步处理抄表账单导入的明细插入
 */
class ImportFeeDetailTempRunnable implements Runnable {
	private GroupBuildingBillCycleService groupBuildingBillCycleService;
	private List<PropertyFeeDetailTemp> pdfTemps;
	private BigInteger gbbcId;
	private BigInteger gbId;

	public ImportFeeDetailTempRunnable(BigInteger gbbcId, BigInteger gbId, List<PropertyFeeDetailTemp> pdfTemps, GroupBuildingBillCycleService groupBuildingBillCycleService) {
		this.gbbcId = gbbcId;
		this.gbId = gbId;
		this.pdfTemps = pdfTemps;
		this.groupBuildingBillCycleService = groupBuildingBillCycleService;
	}

	@Override
	public void run() {
		groupBuildingBillCycleService.insertFeeDetailTemps(gbbcId, gbId, pdfTemps);
	}
}