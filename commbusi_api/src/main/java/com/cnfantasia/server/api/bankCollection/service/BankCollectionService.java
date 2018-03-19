package com.cnfantasia.server.api.bankCollection.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cnfantasia.server.api.bankCollection.constant.BankCollectionConstants;
import com.cnfantasia.server.api.bankCollection.constant.BankCollectionConstants.CollectionRange;
import com.cnfantasia.server.api.bankCollection.dao.BankCollectionDao;
import com.cnfantasia.server.api.bankCollection.entity.BCHistoryDto;
import com.cnfantasia.server.api.bankCollection.entity.BcBankInfoDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoByGbDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoByPPDto;
import com.cnfantasia.server.api.bankCollection.entity.BcInfoEditReq;
import com.cnfantasia.server.api.bankCollection.entity.BcPrintDetail;
import com.cnfantasia.server.api.bankCollection.entity.BcRebackDetailEntity;
import com.cnfantasia.server.api.bankCollection.entity.BillCycleAndTypeName;
import com.cnfantasia.server.api.bankCollection.entity.PPBCInfo4List;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.entity.RebackRecordDto;
import com.cnfantasia.server.api.bankCollection.entity.ResultMsg;
import com.cnfantasia.server.api.bankCollection.entity.RoomEntity;
import com.cnfantasia.server.api.bankCollection.transfer.IBankCollectionTransfer;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.HSSFCellUtil;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.bankCollectionDate.dao.IBankCollectionDateBaseDao;
import com.cnfantasia.server.domainbase.bankCollectionDate.entity.BankCollectionDate;
import com.cnfantasia.server.domainbase.basedataBank.dao.IBasedataBankBaseDao;
import com.cnfantasia.server.domainbase.basedataBank.entity.BasedataBank;
import com.cnfantasia.server.domainbase.bcFileDefine.dao.IBcFileDefineBaseDao;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.dao.IBcGroupBuildingCycleBaseDao;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity.BcGroupBuildingCycleEntry;
import com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.dao.IBcInfoHasTGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.entity.BcInfoHasTGroupBuilding;
import com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.dao.IBcInfoHasTPropertyProprietorBaseDao;
import com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.entity.BcInfoHasTPropertyProprietor;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.domainbase.bcRebackRecord.dao.IBcRebackRecordBaseDao;
import com.cnfantasia.server.domainbase.bcRebackRecord.entity.BcRebackRecord;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.dao.IPayBillBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.dao.IPropertyCompanyBankCollectionInfoBaseDao;
import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;
import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.dao.IPropertyProprietorBankCollectionInfoBaseDao;
import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

public class BankCollectionService {

	@Resource
	private BankCollectionDao bankCollectionDao;
	@Resource
	private IUuidManager uuidManager;
	@Resource
	private IBankCollectionDateBaseDao bankCollectionDateBaseDao;
	@Resource
	private IPropertyCompanyBankCollectionInfoBaseDao propertyCompanyBankCollectionInfoBaseDao;
	@Resource
	private IBcInfoHasTPropertyProprietorBaseDao bcInfoHasTPropertyProprietorBaseDao;
	@Resource
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	@Resource
	private IPropertyProprietorBankCollectionInfoBaseDao proprietorBankCollectionInfoBaseDao;
	@Resource
	private IBasedataBankBaseDao basedataBankBaseDao;
	@Resource
	private IBcInfoHasTGroupBuildingBaseDao bcInfoHasTGroupBuildingBaseDao;
	@Resource
	private IBcGroupBuildingCycleBaseDao bcGroupBuildingCycleBaseDao;
	@Resource
	private IBcRebackRecordBaseDao bcRebackRecordBaseDao;
	@Resource
	private IPayBillBaseDao payBillBaseDao;
	@Resource
	private IBcFileDefineBaseDao bcFileDefineBaseDao;

	/**
	 * 查询所有物业公司的托收配置
	 * @author wenfq
	 * @return
	 */
	public List<PropertyCompanyBCInfo> selectAllBankCollectionInfoList(){
		return bankCollectionDao.selectAllBankCollectionInfoList();
	}

	/**
	 * 查询托收银行维护列表
	 * @param pcId
	 * @return
	 */
	public List<BcBankInfoDto> selectBcBankInfoList(BigInteger pcId){
		List<BcBankInfoDto> bcBankInfoList = bankCollectionDao.selectBcBankInfoList(pcId);
		if(bcBankInfoList!=null && bcBankInfoList.size()>0){
			for(BcBankInfoDto bcBankInfo : bcBankInfoList){
				Set<String> gbNameSet = bcBankInfo.getGbNameSet();
				Integer range = bcBankInfo.getCollectionRange();
				
				String note = (CollectionRange.By_GB.compareTo(range)==0) ? StringUtils.EMPTY: "（业主）";
				StringBuilder gbNames = new StringBuilder();
				Iterator<String> gbNameSetIterator = gbNameSet.iterator();  
				while (gbNameSetIterator.hasNext()) {  
					String gbName = gbNameSetIterator.next();  
					if(StringUtils.isBlank(gbName)){continue;}
					gbNames.append(gbName).append(note);
					if(gbNameSetIterator.hasNext()){
						gbNames.append("，");
					}
				}
				String names = gbNames.toString();
				if(names.endsWith("，")){
					names = names.substring(0, names.length()-1);
				}
				
				bcBankInfo.setGbNames(gbNames.toString());
			}
		}
		return bcBankInfoList;
	}

	/**
	 * 查询业主托收信息列表
	 * @param paramMap
	 * @return
     */
	public List<PropertyProprietorBankCollectionInfo> getPropertyProprietorBankCollectionInfoByCondition(Map<String, Object> paramMap) {
		return bankCollectionDao.getPropertyProprietorBankCollectionInfoByCondition(paramMap);
	}

	/**
	 * 查询业主托收信息列表总数
	 * @param paramMap
	 * @return
     */
	public int getPropertyProprietorBankCollectionInfoByConditionCount(Map<String, Object> paramMap) {
		return bankCollectionDao.getPropertyProprietorBankCollectionInfoByConditionCount(paramMap);
	}
	/**
	 * 保存托收时间配置
	 * @param bcDate1
	 * @param bcDate2
	 * @return
	 */
	@Transactional
	public boolean saveBankCollectionDate(String bcDate1, String bcDate2, BigInteger pcId){
		List<BankCollectionDate> bcDateList = new ArrayList<BankCollectionDate>();
		int bcDateIdSize = 1;
		if(StringUtils.isNotBlank(bcDate1) && StringUtils.isNotBlank(bcDate2)){
			bcDateIdSize = 2;
		}
		List<BigInteger> bcDateIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_bank_collection_date, bcDateIdSize);
		
		if(StringUtils.isNotBlank(bcDate1)){
			BankCollectionDate bcDate = new BankCollectionDate();
			bcDate.setId(bcDateIdList.get(0));
			bcDate.setBankCollectionDate(Integer.parseInt(bcDate1));
			bcDate.settPropertyCompanyFId(pcId);
			
			bcDateList.add(bcDate);
		}
		
		if(StringUtils.isNotBlank(bcDate2)){
			BankCollectionDate bcDate = new BankCollectionDate();
			bcDate.setId(bcDateIdList.get(bcDateIdSize-1));
			bcDate.setBankCollectionDate(Integer.parseInt(bcDate2));
			bcDate.settPropertyCompanyFId(pcId);
			
			bcDateList.add(bcDate);
		}
		
		bankCollectionDao.deleteBankCollectionDateByPcId(pcId);
		bankCollectionDateBaseDao.insertBankCollectionDateBatch(bcDateList);
		return true;
	}

	/**
	 * 传入托收小区ID集，找出其名下所有可缴账期列表, 按账单名称和账单周期排序
	 * @author wenfq
	 * @param gbIdList
	 * @return
	 */
	public List<BillCycleAndTypeName> selectGBBCListByGbIDList(List<BigInteger> gbIdList) {
		return bankCollectionDao.selectGBBCListByGbIDList(gbIdList);
	}

	/**
	 * 出盘文件定义列表，经过从左往右排序的
	 * @param bcInfoId 物业配置托收ID
	 * @param isSumFile 0明细文件，1汇总文件
	 * @return
	 */
	public List<BcFileDefine> selectFileDefineList(BigInteger bcInfoId, int isSumFile) {
		return bankCollectionDao.selectFileDefineList(bcInfoId, isSumFile);
	}

	/**
	 * 导入业主托收信息
	 * @param request
	 * @return
     */
	public String importPpInfoData(HttpServletRequest request) throws IOException {
		String result = "导入成功";
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile uploadExcelfile = multipartRequest.getFile("excelFile");
			HSSFWorkbook wb = new HSSFWorkbook(uploadExcelfile.getInputStream());
			HSSFSheet sheet = wb.getSheetAt(0);
			List<PropertyProprietorBankCollectionInfo> ppbcInfoUpdate = new ArrayList<PropertyProprietorBankCollectionInfo>();
			List<PropertyProprietorBankCollectionInfo> ppbcInfoAdd = new ArrayList<PropertyProprietorBankCollectionInfo>();
			//小区信息校验
			String gbIdStr = HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(0));
			String gbName = HSSFCellUtil.getStringValue(sheet.getRow(0).getCell(1));
			if(DataUtil.isEmpty(gbIdStr) || DataUtil.isEmpty(gbName)) {
				result = "校验失败：小区信息不正确，请重新下载模板！";
				return result;
			}
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", gbIdStr);
			paraMap.put("name", gbName);
			List<GroupBuilding> groupBuildings = groupBuildingBaseDao.selectGroupBuildingByCondition(paraMap, false);
			if(DataUtil.isEmpty(groupBuildings) || (!DataUtil.isEmpty(groupBuildings) && groupBuildings.size() > 1)) {
				result = "校验失败：小区信息不正确，请重新下载模板！";
				return result;
			}

			//excel校验
			String verifyResult =  verifyPpInfoData(sheet, groupBuildings.get(0).getId(), ppbcInfoUpdate, ppbcInfoAdd);
			if(!verifyResult.equals("通过校验")){
				result = "校验失败："+ verifyResult;
			} else {
				//导入数据
				int i = 0;
				if(!DataUtil.isEmpty(ppbcInfoAdd)) {
					CnfantasiaCommbusiUtil.newStandardList(ppbcInfoAdd, SEQConstants.t_property_proprietor_bank_collection_info);
					i += proprietorBankCollectionInfoBaseDao.insertPropertyProprietorBankCollectionInfoBatch(ppbcInfoAdd);
				}

				if(!DataUtil.isEmpty(ppbcInfoUpdate)) {
					proprietorBankCollectionInfoBaseDao.updatePropertyProprietorBankCollectionInfoBatch(ppbcInfoUpdate);
					i += ppbcInfoUpdate.size();
				}

				result = "成功导入"+ i + "条";
			}
		}
		return result;
	}

	/**
	 * 导入托收数据校验
	 * @param sheet
	 * @param gbId
	 * @param list
     * @return
     */
	private String verifyPpInfoData(HSSFSheet sheet, BigInteger gbId, List<PropertyProprietorBankCollectionInfo> ppbcInfoUpdate, List<PropertyProprietorBankCollectionInfo> ppbcInfoAdd) {
		String resultInfo =  "通过校验";
		Map<String, RoomEntity> roomInfoMap = getRoomStrByGbId(gbId);
		//查询该小区下的存在的业主托收记录
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("gbId", gbId);
		List<PropertyProprietorBankCollectionInfo> ppBankInfoList = getPropertyProprietorBankCollectionInfoByCondition(paramMap);
		Set<String> roomInfoSet = new HashSet<String>();
		for (int i = 2; i < sheet.getLastRowNum() + 1; i++) {
			if (isEmptyRow(sheet, i)) {
				continue;
			}// 空行跳过，不导入
			if (HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) == null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))) {
				return "第" + (i + 1) + "行的楼栋号不能为空！";
			}
			if (HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)) == null || "".equals(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))) {
				return "第" + (i + 1) + "行的房间号不能为空！";
			}
			String roomInfo = HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)) + "-" +
					HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)) + "-" +
					HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2));
			if(!roomInfoSet.add(roomInfo)){
				return  "第" + (i + 1) + "行的楼栋、单元、房间号数据重复！";
			}
			
			if (!roomInfoMap.containsKey(roomInfo)) {
				return "第" + (i + 1) + "行的楼栋、单元、房间号数据不匹配！";
			} else {
				String roomNo = DataUtil.delStrSpace(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(4)));
				String bankAccount = DataUtil.delStrSpace(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(5)));
				String bankOwnerName = DataUtil.delStrSpace(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(6)));
				String bankName = DataUtil.delStrSpace(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(7)));

				//获取银行信息id
				Map<String, Object> paramMap02 = new HashMap<String, Object>();
				paramMap02.put("name", bankName);
				BigInteger basedataBankId = null;
				List<BasedataBank> basedataBankList = basedataBankBaseDao.selectBasedataBankByCondition(paramMap02, false);
				if(!DataUtil.isEmpty(basedataBankList)) {
					basedataBankId = basedataBankList.get(0).getId();
				}

				RoomEntity roomEntity = roomInfoMap.get(roomInfo);
				PropertyProprietorBankCollectionInfo ppbcInfo = ppbankInfoExist(ppBankInfoList, roomEntity.getId());
				if(!DataUtil.isEmpty(ppbcInfo)) {//已经存在数据  更新
					String notChange = "";//判断是否进行修改
					if(!DataUtil.isEmpty(roomNo)) {
						notChange +=roomNo.equals(ppbcInfo.getRoomNo());
						ppbcInfo.setRoomNo(roomNo);
					}
					if(!DataUtil.isEmpty(bankAccount)) {
						notChange += bankAccount.equals(ppbcInfo.getBankAccount());
						ppbcInfo.setBankAccount(bankAccount);
					}
					if(!DataUtil.isEmpty(bankAccount)) {
						notChange += bankName.equals(ppbcInfo.getBankName());
						ppbcInfo.setBankName(bankName);
					}
					if(!DataUtil.isEmpty(bankOwnerName)) {
						notChange += bankOwnerName.equals(ppbcInfo.getBankOwnerName());
						ppbcInfo.setBankOwnerName(bankOwnerName);
					}
					ppbcInfo.setSys0UpdTime(DateUtils.getCurrentDate());
					if(notChange.contains("false")) {
						ppbcInfoUpdate.add(ppbcInfo);
					}
				} else {//新增
					PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo = new PropertyProprietorBankCollectionInfo();
					if(!DataUtil.isEmpty(roomNo)) {
						propertyProprietorBankCollectionInfo.setRoomNo(roomNo);
					}
					if(!DataUtil.isEmpty(bankAccount)) {
						propertyProprietorBankCollectionInfo.setBankAccount(bankAccount);
					}
					if(!DataUtil.isEmpty(bankName)) {
						propertyProprietorBankCollectionInfo.setBankName(bankName);
					}
					if(!DataUtil.isEmpty(bankOwnerName)) {
						propertyProprietorBankCollectionInfo.setBankOwnerName(bankOwnerName);
					}
					propertyProprietorBankCollectionInfo.setGbId(gbId);
					propertyProprietorBankCollectionInfo.settRealRoomFId(roomEntity.getId());
					propertyProprietorBankCollectionInfo.setBuildingName(roomEntity.getBuildingName());
					propertyProprietorBankCollectionInfo.setGbName(roomEntity.getGroupbuildingName());
					propertyProprietorBankCollectionInfo.setRrNum(roomEntity.getNumTail());
					propertyProprietorBankCollectionInfo.settBasedataBankFId(basedataBankId);
					propertyProprietorBankCollectionInfo.setUnitName(roomEntity.getUnitName());
					ppbcInfoAdd.add(propertyProprietorBankCollectionInfo);
				}
			}
		}
		return resultInfo;
	}

	/**
	 * 是否存在数据
	 * @param gbId
	 * @param realRoomId
     * @return
     */
	private PropertyProprietorBankCollectionInfo ppbankInfoExist(List<PropertyProprietorBankCollectionInfo> ppBankInfoList, BigInteger realRoomId) {
		for (PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo : ppBankInfoList) {
			if(propertyProprietorBankCollectionInfo.gettRealRoomFId().equals(realRoomId)) {
				return propertyProprietorBankCollectionInfo;
			}
		}
		return null;
	}

	/**
	 * 组装房间信息
	 * @param gbId
	 * @return
     */
	private  Map<String, RoomEntity> getRoomStrByGbId(BigInteger gbId) {
		List<RoomEntity> rooms = bankCollectionDao.queryRoomForList(gbId);
		Map<String, RoomEntity> resMap = new HashMap<String, RoomEntity>();
		for (RoomEntity roomEntity : rooms) {
			String buildingName = roomEntity.getBuildingName() == null ? "" : roomEntity.getBuildingName();
			String unitName = roomEntity.getUnitName() == null ? "" : roomEntity.getUnitName();
			String numTail = roomEntity.getNumTail() == null ? "" : roomEntity.getNumTail();
			resMap.put(buildingName + "-" + unitName + "-" + numTail, roomEntity);
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

		if(com.cnfantasia.server.common.utils.StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(0)))
				&& com.cnfantasia.server.common.utils.StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(1)))
				&& com.cnfantasia.server.common.utils.StringUtils.isEmpty(HSSFCellUtil.getStringValue(sheet.getRow(i).getCell(2)))){
			// 楼栋	单元	房号 都为空时，也认为是空行, 跳过不导入
			isEmptyRow = true;
		}

		return isEmptyRow;
	}

	/**
	 * 物业公司对应的所有管理处、小区信息
	 * @param pcId
	 * @return
	 */
	public List<BcInfoByGbDto> selectBcInfoByGB(BigInteger pcId, BigInteger pcbciId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcId", pcId);
		paramMap.put("pcbciId", pcbciId);
		return bankCollectionDao.selectBcInfoByGB(paramMap);
	}

	/**物业公司对应的所有用户托收信息*/
	public List<BcInfoByPPDto> selectBcInfoByPP(BigInteger pcId, BigInteger pcbciId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pcId", pcId);
		paramMap.put("pcbciId", pcbciId);
		return bankCollectionDao.selectBcInfoByPP(paramMap);
	}
	
	/**
	 * 保存托收信息
	 * @param bcInfoEditReq
	 * @return
	 */
	@Transactional
	public ResultMsg saveBankCollectionInfo(BcInfoEditReq bcInfoEditReq){
		// 按小区、业主托盘互斥重复性校验
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(bcInfoEditReq.getCollectionRange()==BankCollectionConstants.CollectionRange.By_GB){// 按小区
			String[] gbIdsStr = bcInfoEditReq.getGdIds();
			List<BigInteger> gbIds = new ArrayList<BigInteger>();
			for(String gbIdStr:gbIdsStr){
				gbIds.add(new BigInteger(gbIdStr));
			}
			paramMap.put("pcbciId", bcInfoEditReq.getPcbciId());
			paramMap.put("gbIds", gbIds);
			int repeatCount = bankCollectionDao.selectRepeatGbCount(paramMap);
			if(repeatCount>0){
				return new ResultMsg("存在小区既按小区，又按业主托盘！设置失败！", false);
			}
		} else {
			String[] ppbciIdsStr = bcInfoEditReq.getPpbciIds();
			List<BigInteger> ppbciIds = new ArrayList<BigInteger>();
			for(String ppbciIdStr:ppbciIdsStr){
				ppbciIds.add(new BigInteger(ppbciIdStr));
			}
			paramMap.put("pcbciId", bcInfoEditReq.getPcbciId());
			paramMap.put("ppbciIds", ppbciIds);
			int repeatCount = bankCollectionDao.selectRepeatPpCount(paramMap);
			if(repeatCount>0){
				return new ResultMsg("存在业主既按小区，又按业主托盘！设置失败！", false);
			}
		}
		
		BigInteger pcbciId = null;
		if(StringUtils.isBlank(bcInfoEditReq.getPcbciId())){
			pcbciId = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_company_bank_collection_info);
		} else {
			pcbciId = new BigInteger(bcInfoEditReq.getPcbciId());
		}
		if(StringUtils.isBlank(bcInfoEditReq.getPcbciId())){// 新增
			PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo = new PropertyCompanyBankCollectionInfo();
			propertyCompanyBankCollectionInfo.setId(pcbciId);
			propertyCompanyBankCollectionInfo.settPropertyCompanyFId(bcInfoEditReq.getPcId());
			propertyCompanyBankCollectionInfo.setNo(bcInfoEditReq.getNo());
			propertyCompanyBankCollectionInfo.setBankAccount(bcInfoEditReq.getBankAccount());
			propertyCompanyBankCollectionInfo.setCollectionRange(bcInfoEditReq.getCollectionRange());
			propertyCompanyBankCollectionInfo.setBankName(bcInfoEditReq.getBankOrgName());
			propertyCompanyBankCollectionInfo.settBankCollectionFinanceOrgFId(new BigInteger(bcInfoEditReq.getBankOrg()));
			propertyCompanyBankCollectionInfo.setContractNo(bcInfoEditReq.getContractNo());
			propertyCompanyBankCollectionInfo.setSys0DelState(0);
			
			propertyCompanyBankCollectionInfoBaseDao.insertPropertyCompanyBankCollectionInfo(propertyCompanyBankCollectionInfo);
		} else {// 修改
			PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo = propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoBySeqId(pcbciId);
			propertyCompanyBankCollectionInfo.setBankAccount(bcInfoEditReq.getBankAccount());
			propertyCompanyBankCollectionInfo.setNo(bcInfoEditReq.getNo());
			propertyCompanyBankCollectionInfo.setCollectionRange(bcInfoEditReq.getCollectionRange());
			propertyCompanyBankCollectionInfo.setBankName(bcInfoEditReq.getBankOrgName());
			propertyCompanyBankCollectionInfo.settBankCollectionFinanceOrgFId(new BigInteger(bcInfoEditReq.getBankOrg()));
			propertyCompanyBankCollectionInfo.setContractNo(bcInfoEditReq.getContractNo());

			propertyCompanyBankCollectionInfoBaseDao.updatePropertyCompanyBankCollectionInfo(propertyCompanyBankCollectionInfo);
		}
		
		if(bcInfoEditReq.getCollectionRange()==BankCollectionConstants.CollectionRange.By_GB){// 按小区
			// delete
			bankCollectionDao.deleteBcInfoHasTGroupBuildingByPcbciId(pcbciId);
			// insert
			String[] gbIds = bcInfoEditReq.getGdIds();
			List<BigInteger> bcigbIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_bc_info_has_t_group_building, gbIds.length);
			List<BcInfoHasTGroupBuilding> bcigbList = new ArrayList<BcInfoHasTGroupBuilding>();
			for(int i=0; i<gbIds.length; i++){
				BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding = new BcInfoHasTGroupBuilding();
				bcInfoHasTGroupBuilding.setId(bcigbIds.get(i));
				bcInfoHasTGroupBuilding.settGroupBuildingFId(new BigInteger(gbIds[i]));
				bcInfoHasTGroupBuilding.settPropertyCompanyBankCollectionInfoFId(pcbciId);
				bcInfoHasTGroupBuilding.setSys0DelState(0);

				bcigbList.add(bcInfoHasTGroupBuilding);
			}

			bcInfoHasTGroupBuildingBaseDao.insertBcInfoHasTGroupBuildingBatch(bcigbList);
		} else {// 按业主
			// delete
			bankCollectionDao.deleteBcInfoHasTPropertyProprietorByPcbciId(pcbciId);
			// insert
			String[] ppbciIds = bcInfoEditReq.getPpbciIds();
			//
			List<BigInteger> bihppIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_bc_info_has_t_property_proprietor, ppbciIds.length);
			List<BcInfoHasTPropertyProprietor> bihppIdList = new ArrayList<BcInfoHasTPropertyProprietor>();
			for(int i=0; i<ppbciIds.length; i++){
				BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor = new BcInfoHasTPropertyProprietor();
				bcInfoHasTPropertyProprietor.setId(bihppIds.get(i));
				bcInfoHasTPropertyProprietor.settPropertyProprietorBankCollectionInfoFId(new BigInteger(ppbciIds[i]));
				bcInfoHasTPropertyProprietor.settPropertyCompanyBankCollectionInfoFId(pcbciId);
				bcInfoHasTPropertyProprietor.setSys0DelState(0);

				bihppIdList.add(bcInfoHasTPropertyProprietor);
			}
			
			bcInfoHasTPropertyProprietorBaseDao.insertBcInfoHasTPropertyProprietorBatch(bihppIdList);
			
			// 强哥那边需要---start
			// delete
			bankCollectionDao.deleteBcInfoHasTGroupBuildingByPcbciId(pcbciId);
			// insert
			Set<String> gbIds = bcInfoEditReq.getGbIdSet();
			List<BigInteger> bcigbIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_bc_info_has_t_group_building, gbIds.size());
			List<BcInfoHasTGroupBuilding> bcigbList = new ArrayList<BcInfoHasTGroupBuilding>();
			int i=0;
			for(String gbId : gbIds){
				BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding = new BcInfoHasTGroupBuilding();
				bcInfoHasTGroupBuilding.setId(bcigbIds.get(i++));
				bcInfoHasTGroupBuilding.settGroupBuildingFId(new BigInteger(gbId));
				bcInfoHasTGroupBuilding.settPropertyCompanyBankCollectionInfoFId(pcbciId);
				bcInfoHasTGroupBuilding.setSys0DelState(0);

				bcigbList.add(bcInfoHasTGroupBuilding);
			}

			bcInfoHasTGroupBuildingBaseDao.insertBcInfoHasTGroupBuildingBatch(bcigbList);
			// 强哥那边需要---end
		}

		return new ResultMsg(null, true);
	}
	
	/**
	 * 托收数据列表查询
	 * @param paramMap
	 * @return
	 */
	public List<BCHistoryDto> selectBCHistoryForList(Map<String, Object> paramMap){
		return bankCollectionDao.selectBCHistoryForList(paramMap);
	}
	
	/**
	 * 托收数据列表条数查询
	 * @param paramMap
	 * @return
	 */
	public Integer selectBCHistoryForCount(Map<String, Object> paramMap){
		return bankCollectionDao.selectBCHistoryForCount(paramMap);
	}
	
	/**
	 * 确认回盘完成
	 * @param bgbcId
	 * @param pcbciId
	 * @return
	 */
	@Transactional
	public ResultMsg confirmBc(BigInteger userId, BigInteger bgbcId, BigInteger pcbciId){
		BcGroupBuildingCycle bgbc = bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleBySeqId(bgbcId);
		String basePath = OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_BASE_PATH) + File.separator;
		if(bgbc==null 
				|| StringUtils.isBlank(bgbc.getDetailFileUrl()) || !new File(basePath + bgbc.getDetailFileUrl()).exists()
				|| (StringUtils.isNotBlank(bgbc.getSumFileUrl()) && !new File(basePath + bgbc.getSumFileUrl()).exists())){
			// detailFileUrl必须存在，sumFileUrl不一定存在
			return new ResultMsg("回盘文件不存在，确认回盘失败！", false);
		}
		
		PropertyCompanyBankCollectionInfo pcbcInfo = propertyCompanyBankCollectionInfoBaseDao.selectPropertyCompanyBankCollectionInfoBySeqId(pcbciId);
		Integer collectionRange = pcbcInfo.getCollectionRange();
		String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
		// t_pay_bill
		if(collectionRange!=null && collectionRange.compareTo(BankCollectionConstants.CollectionRange.By_GB)==0){
			bankCollectionDao.confirmBcByGB(bgbcId);
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("bgbcId", bgbcId);
			paramMap.put("pcbciId", pcbciId);
			bankCollectionDao.confirmBcByPP(paramMap);
		}
		
		// t_bc_group_building_cycle
		BcGroupBuildingCycle bcGroupBuildingCycle = new BcGroupBuildingCycle();
		bcGroupBuildingCycle.setId(bgbcId);
		bcGroupBuildingCycle.setRebackStatus(1);
		bcGroupBuildingCycle.setConfirmTime(now);
		bcGroupBuildingCycle.setSys0UpdUser(userId);
		bcGroupBuildingCycle.setSys0UpdTime(now);
		bcGroupBuildingCycleBaseDao.updateBcGroupBuildingCycle(bcGroupBuildingCycle);
		
		return new ResultMsg(null, true);
	}
	
	/**
	 * 查看回盘结果列表
	 * @param paramMap
	 * @return
	 */
	public List<RebackRecordDto> selectRebackRecordForList(Map<String, Object> paramMap){
		return bankCollectionDao.selectRebackRecordForList(paramMap);
	}
	
	/**
	 * 查看回盘结果列表数据条数
	 * @param paramMap
	 * @return
	 */
	public Integer selectRebackRecordForCount(Map<String, Object> paramMap){
		return bankCollectionDao.selectRebackRecordForCount(paramMap);
	}

	/**
	 * 银行托收回盘
	 * @param uploadfile
	 * @param bcgroupBuildingCycleId
	 * @return
     */
	public ResultMsg importDetailFile(MultipartFile uploadfile, BigInteger bcgroupBuildingCycleId) {
		List<BcRebackDetailEntity> rebackDetailEntityList = new ArrayList<BcRebackDetailEntity>();//记录回盘信息

		//回盘银行信息
		BcFinanceOrg bcFinanceOrg = bankCollectionDao.getBcFinanceOrg(bcgroupBuildingCycleId);
		if(DataUtil.isEmpty(bcFinanceOrg)) return new ResultMsg("物业公司银行托收信息配置有误或者为空！", false);
		//回盘文件格式
		List<BcFileDefine> bcFileDefineList = bankCollectionDao.selectFileDefineListByFinanceOrgId(bcFinanceOrg.getId());

		//解析文件对象实例化
		Class<?> aClass = null;
		IBankCollectionTransfer bankCollectionTransfer = null;
		try {
			aClass = Class.forName(bcFinanceOrg.getClassName());
			bankCollectionTransfer = (IBankCollectionTransfer)aClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg("银行回盘文件格式解析实现类不正确！", false);
		}
		if(bankCollectionTransfer == null) return new ResultMsg("银行回盘文件格式解析实现类不正确！", false);

		//回盘文件解析
		ResultMsg resultMsg = null;
		try {
			resultMsg = bankCollectionTransfer.importDetailFile(uploadfile, rebackDetailEntityList, bcFileDefineList, bcgroupBuildingCycleId);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultMsg("回盘文件解析失败！", false);
		}
		if(!resultMsg.isSuccess()) return resultMsg;//文件解析不成功，或文件内容有误
		//if(DataUtil.isEmpty(rebackDetailEntityList)) return new ResultMsg("回盘内容为空，回盘文件格式不匹配（仅支持.T10）", false);

		List<BcRebackRecord> bcRebackRecordList = new ArrayList<BcRebackRecord>();//记录回盘信息
		List<PayBill> payBillList = new ArrayList<PayBill>();
		//回盘账单信息--修改账单状态
		int j = 0;//记录账单回盘成功数
		for (BcRebackDetailEntity rebackDetailEntity : rebackDetailEntityList) {
			BcRebackRecord rebackRecord = new BcRebackRecord();
			rebackRecord.setPaybillId(rebackDetailEntity.getPaybillId());
			rebackRecord.setRebackContent(rebackDetailEntity.getRebackContent());
			//银行托收是否成功
			if(!rebackDetailEntity.getStatus().equals("Y")) {
				rebackRecord.setStatus(BankCollectionConstants.BankHuiPanStatus.NOT_SUFFICIENT_FUNDS);//余额不足
				rebackRecord.setRebackTime(DateUtils.getCurrentDate());
				rebackRecord.settBcGroupBuildingCycleFId(bcgroupBuildingCycleId);
				bcRebackRecordList.add(rebackRecord);
				continue;
			}

			//校验金额
			PayBill payBill = payBillBaseDao.selectPayBillBySeqId(rebackDetailEntity.getPaybillId());
			if(payBill == null) {
				rebackRecord.setStatus(BankCollectionConstants.BankHuiPanStatus.NOT_FIND_PAYBILL);//账单不存在
				rebackRecord.setRebackTime(DateUtils.getCurrentDate());
				rebackRecord.settBcGroupBuildingCycleFId(bcgroupBuildingCycleId);
				bcRebackRecordList.add(rebackRecord);
				continue;
			}
			//处理物业宝抵扣情况
			Long billAmt = payBill.getAmount() - (payBill.getDeduPrice() == null ? 0L : payBill.getDeduPrice());
//			if(billAmt.equals(rebackDetailEntity.getAmount())) {
				if(!payBill.getIsPay().equals(BankCollectionConstants.PayBillIsPay.PAID_IN)) {
					//回写账单信息
					payBill.setIsPay(BankCollectionConstants.PayBillIsPay.PAID_IN);
					payBill.setPayTime(DateUtils.getCurrentDate());
					payBill.setSuccPayAmount(payBill.getAmount());
					payBill.setPaymentWay(BankCollectionConstants.BillPayMethod.BANK_COLLECTION);
					payBillList.add(payBill);//update-paybill
					rebackRecord.setStatus(BankCollectionConstants.BankHuiPanStatus.SUCCESSED);//回盘成功
					j++;
				} else {
					rebackRecord.setStatus(BankCollectionConstants.BankHuiPanStatus.REPEAT);
				}
//			} else {
//				rebackRecord.setStatus(BankCollectionConstants.BankHuiPanStatus.AMOUNT_MISMATCHING);//金额不匹配
//			}

			//处理往月欠费情况
			List<PayBill> payBills = bankCollectionDao.selectUnPaidPayBllWithPayBillId(payBill.getId());
			for (PayBill bill : payBills) {
				BcRebackRecord rebackRecordEntity = new BcRebackRecord();
				rebackRecordEntity.setPaybillId(bill.getId());
				rebackRecordEntity.setRebackContent(rebackDetailEntity.getRebackContent());
				if(!bill.getIsPay().equals(BankCollectionConstants.PayBillIsPay.PAID_IN)) {
					bill.setIsPay(BankCollectionConstants.PayBillIsPay.PAID_IN);
					bill.setPayTime(DateUtils.getCurrentDate());
					bill.setSuccPayAmount(bill.getAmount());
					bill.setPaymentWay(BankCollectionConstants.BillPayMethod.BANK_COLLECTION);
					payBillList.add(bill);//update-paybill
					rebackRecordEntity.setStatus(BankCollectionConstants.BankHuiPanStatus.SUCCESSED);//回盘成功
					j++;
				} else {
					rebackRecordEntity.setStatus(BankCollectionConstants.BankHuiPanStatus.REPEAT);
				}
				rebackRecordEntity.setRebackTime(DateUtils.getCurrentDate());
				rebackRecordEntity.settBcGroupBuildingCycleFId(bcgroupBuildingCycleId);
				bcRebackRecordList.add(rebackRecordEntity);
			}

			rebackRecord.setRebackTime(DateUtils.getCurrentDate());
			rebackRecord.settBcGroupBuildingCycleFId(bcgroupBuildingCycleId);
			bcRebackRecordList.add(rebackRecord);
		}

		if(!DataUtil.isEmpty(payBillList)) {
			int i1 = payBillBaseDao.updatePayBillBatch(payBillList);
			if(i1 == 0) return new ResultMsg("写入账单回盘记录操作失败！", false);
		}

		CnfantasiaCommbusiUtil.newStandardList(bcRebackRecordList, SEQConstants.t_bc_reback_record);
		int i = bcRebackRecordBaseDao.insertBcRebackRecordBatch(bcRebackRecordList);
		if(i == 0) return new ResultMsg("写入回盘记录操作失败！", false);

		return new ResultMsg("共回盘"+ i +"条，账单回写成功"+ j +"条", true);
	}

	public int updatePropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo) {
		return bankCollectionDao.updatePropertyProprietorBankCollectionInfo(propertyProprietorBankCollectionInfo);
	}

	public int updatePayBillAfterOffer(List<BcOfferRecord> bcOfferRecordList) {
		return bankCollectionDao.updatePayBillAfterOffer(bcOfferRecordList);
	}

	public PPBCInfo4List getPropertyProprietorBankCollectionInfoBySeqId(BigInteger id) {
		return bankCollectionDao.selectPropertyProprietorBankCollectionInfoBySeqId(id);
	}

	/**
	 * 出盘账单与业主托收信息:按小区
	 * @author wenfq
	 * @param bcGBCycleEntryAddList 账期
	 * @return
	 */
	public List<PPBankCollectionWithPayBill> selectPayBllWithBCCycleIdForGBRange(List<BcGroupBuildingCycleEntry> bcGBCycleEntryAddList) {
		return bankCollectionDao.selectPayBllWithBCCycleIdForGBRange(bcGBCycleEntryAddList);
	}
	
	/**
	 * 出盘账单与业主托收信息:按业主
	 * @author wenfq
	 * @param bcInfoId 物业托收信息id
	 * @param bcGBCycleEntryAddList 账期
	 * @return
	 */
	public List<PPBankCollectionWithPayBill> selectPayBllWithBCCycleIdForPPRange(BigInteger bcInfoId, List<BcGroupBuildingCycleEntry> bcGBCycleEntryAddList) {
		return bankCollectionDao.selectPayBllWithBCCycleIdForPPRange(bcInfoId, bcGBCycleEntryAddList);
	}

	/**
	 * 平安银行回盘信息校验--账单
	 * @param bankNo
	 * @param amount
	 * @param ids
     * @param bcgroupBuildingCycleId
	 * @return
     */
	public List<BigInteger> getPayBillCheckForPingAnBankCollection(String bankNo, Long amount, List<BigInteger> ids, BigInteger bcgroupBuildingCycleId) {
		return bankCollectionDao.getPayBillCheckForPingAnBankCollection(bankNo, amount, ids, bcgroupBuildingCycleId);
	}

	/**
	 * 查询账单id
	 * @param str
	 * @param bcgroupBuildingCycleId
	 * @param amount
     * @return
     */
	public List<BigInteger> getPayBillCheckForJinRongAndICBCCollection(String roomNo, BigInteger bcgroupBuildingCycleId, Long amount) {
		return bankCollectionDao.getPayBillCheckForJinRongAndICBCCollection(roomNo, bcgroupBuildingCycleId, amount);
	}
	
	/*========================================= 打印 start =========================================*/
	/**
	 * 查询银行出盘明细
	 * @param bgbcId
	 * @return
	 */
	public List<BcPrintDetail> getBcPrintDetailByBgbcId(BigInteger bgbcId){
		return bankCollectionDao.getBcPrintDetailByBgbcId(bgbcId);
	}
	
	/*========================================= 打印 end =========================================*/

	/**
	 * 找到账单所关联的未缴账单
	 * @param bcPayBillList 当前缴费窗口可以缴费的账单
	 * @return 找到对应历史未缴账单
	 */
	public List<PPBankCollectionWithPayBill> selectUnPaidPayBllWithPayBillList(
			List<PPBankCollectionWithPayBill> bcPayBillList) {
		return bankCollectionDao.selectUnPaidPayBllWithPayBillList(bcPayBillList);
	}
}
