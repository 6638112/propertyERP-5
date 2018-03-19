package com.cnfantasia.server.ms.propertyProprietor.dao;

import com.cnfantasia.server.domainbase.propertyLessee.entity.PropertyLessee;
import com.cnfantasia.server.domainbase.propertyProprietor.dao.PropertyProprietorBaseDao;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.ms.propertyProprietor.entity.PropertyProprietorEntity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PropertyProprietorDao extends PropertyProprietorBaseDao implements IPropertyProprietorDao {

	/**
	 * 根据序列号查询(业主信息表)信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public PropertyProprietorEntity selectPropertyProprietorByRoomId(java.math.BigInteger id) {
		return sqlSession.selectOne("propertyProprietor.select_ppInfo_ByRealRoomId", id);
	}

	@Override
	public String vierfyImportPayBill(List<PropertyProprietorEntity> ppList) {
		if (ppList.size() == 0)
			return "无业主数据，请先在Excel中维护业主信息。";

		int[] indexs = new int[ppList.size()]; //记录ppList中被删除情况，删除的指标上置1
		StringBuffer sb = new StringBuffer();
		sb.append("部分行未能导入，具体行如下：\\r\\r");
		List<String> paramBaseInfoList = new ArrayList<String>();//城市+小区名 
		for (int i = 0; i < ppList.size(); i++) {
			paramBaseInfoList.add(ppList.get(i).getCityName() + ppList.get(i).getGroupBuildingName());
		}

		boolean isRemoveSomeOne = false;
		// step1: 根据账单的《城市 + 小区名》，去数据库中找看是否有对应的基本物业信息
		List<String> summaryInfoList = sqlSession.selectList("propertyProprietor.select_verifyInfo_for_ImportedPP_step1", paramBaseInfoList);
		Set<String> summarySet = new HashSet<String>();
		summarySet.addAll(summaryInfoList);

		for (int i = paramBaseInfoList.size() - 1; i >= 0; i--) {
			if (!summarySet.contains(paramBaseInfoList.get(i))) {
				//《城市+小区名》 在数据库中不存在
				ppList.remove(i);//剔除不需要导入的
				indexs[i] = 1; //删除的指标上置1
				isRemoveSomeOne = true;
				sb.append(String.format("第" + (i + 1) + "行的 "));
				sb.append("《城市+小区名》 信息不存在导致导入失败，请先维护小区信息\\r");
			}
		}
		if (ppList.size() == 0)
			return sb.toString();
		// step1:end

		//step2: 根据账单的《城市 + 小区名 + 楼栋 + 单元 + 房间号 + 合同号 + 业主姓名》，去数据库中找看是否已有业主信息
		List<String> paramList = new ArrayList<String>();//《城市+小区名 + 楼栋 + 单元 + 房间号 + 合同号 + 业主姓名》
		for (int i = 0; i < ppList.size(); i++) {
			String summaryString = ppList.get(i).getCityName() + ppList.get(i).getGroupBuildingName();
			summaryString = summaryString + ppList.get(i).getRealRoomUnitName() + ppList.get(i).getRealRoomNum() + ppList.get(i).getContactNum()
					+ ppList.get(i).getName();
			paramList.add(summaryString);
		}
		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step2", paramList);
		summarySet.clear();
		summarySet.addAll(summaryInfoList);

		for (int i = paramList.size() - 1; i >= 0; i--) {
			if (summarySet.contains(paramList.get(i))) {
				//《城市+小区名 + 楼栋 + 单元 + 房间号 + 合同号 + 合同号 + 业主姓名》 在数据库中已存在
				for (int j = 0, k = -1; j < indexs.length; j++) {
					if (indexs[j] == 0) {//找未被删除的元素，即跳过被删除的元素
						k++;
					}
					if (k == i) {//找到导入数据所在行位置
						sb.append(String.format("第" + (j + 1) + "行的 "));
						sb.append("业主信息已存在 \\r");
						break;
					}
				}
				ppList.remove(i);//剔除不需要导入的
				isRemoveSomeOne = true;
			}
		}

		if (ppList.size() == 0)
			return sb.toString();
		// step2:end

		//TODO 导入功能还未实现，后期要先加上楼栋和房间维护功能，这里只导入业主信息

		// step3: 对有效数据处理楼栋信息，若楼栋不存在，需要先导入  
		/*paramBaseInfoList.clear(); //小区名 + 楼栋 + 单元 + 房间号 + 合同号 + 业主姓名
		for (int i = 0; i < ppList.size(); i++) {
			paramBaseInfoList.add(ppList.get(i).toString());
		}
		summaryInfoList = sqlSession.selectList("payBill.select_verifyInfo_for_ImportedPayBill_step3", paramBaseInfoList);
		for (int i = 0; i < ppList.size(); i++) {
			for (int j = 0; j < summaryInfoList.size(); j++) {
				if (summaryInfoList.get(j).startsWith(ppList.get(i).toString())) {
					String[] strings = summaryInfoList.get(j).split("_");
					ppList.get(i).settRealRoomFId(new BigInteger(strings[1]));
					ppList.get(i).setPropertyProprietorId(strings[2]);
					break;
				}
			}
		}*/
		// step3:end

		if (!isRemoveSomeOne) {//全部是有效数据
			sb = new StringBuffer("全部成功导入。");
		}

		return sb.toString();
	}

	@Override
	public int updatePropertyProprietor_for_NameIdentfyNoPhone(PropertyProprietor propertyProprietor) {
		return sqlSession.update("propertyProprietor.update_propertyProprietor", propertyProprietor);
	}

	@Override
	public int getPPList_byUserId_forCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("propertyProprietor.select_ppList_byOmsUserId_count", paramMap);
	}

	@Override
	public List<PropertyProprietorEntity> getPPList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		return sqlSession.selectList("propertyProprietor.select_ppList_byOmsUserId", paramMap);
	}

	@Override
	public int selectCountBeforeUpdateRoom(Map<String, Object> paramMap) {
		return sqlSession.selectOne("propertyProprietor.selectCountBeforeUpdateRoom", paramMap);
	}

	@Override
	public List<PropertyProprietor> getPropertyProprietorListByRoomId(BigInteger rrId) {
		return sqlSession.selectList("propertyProprietor.selectPropertyProprietorListByRoomId", rrId);
	}

	@Override
	public List<PropertyLessee> getPropertyLesseeListByRoomId(BigInteger rrId) {
		return sqlSession.selectList("propertyProprietor.selectPropertyLesseeListByRoomId", rrId);
	}

	@Override
	public int deletePropertyProprietor(BigInteger rrId, BigInteger ppId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rrId", rrId);
		paramMap.put("ppId", ppId);
		return sqlSession.update("propertyProprietor.deletePropertyProprietor", paramMap);
	}

	@Override
	public int deletePropertyLessee(BigInteger rrId, BigInteger plId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rrId", rrId);
		paramMap.put("plId", plId);
		return sqlSession.update("propertyProprietor.deletePropertyLessee", paramMap);
	}

	@Override
	public int updateRealRoomPropertyPropietor(RealRoom realRoom) {
		return sqlSession.update("propertyProprietor.updateRealRoomPropertyPropietor", realRoom);
	}
}
