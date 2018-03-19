package com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompanyBankCollectionInfo.entity.PropertyCompanyBankCollectionInfo;

/**
 * 描述:(物业公司托收银行信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCompanyBankCollectionInfoBaseDao extends AbstractBaseDao implements IPropertyCompanyBankCollectionInfoBaseDao{
	/**
	 * 根据条件查询(物业公司托收银行信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCompanyBankCollectionInfo> selectPropertyCompanyBankCollectionInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCompanyBankCollectionInfoBase.select_propertyCompanyBankCollectionInfo",paramMap);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCompanyBankCollectionInfo> selectPropertyCompanyBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCompanyBankCollectionInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCompanyBankCollectionInfo> resMap= sqlSession.selectList("propertyCompanyBankCollectionInfoBase.select_propertyCompanyBankCollectionInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业公司托收银行信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyBankCollectionInfo selectPropertyCompanyBankCollectionInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCompanyBankCollectionInfoBase.select_propertyCompanyBankCollectionInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCompanyBankCollectionInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCompanyBankCollectionInfoBase.select_propertyCompanyBankCollectionInfo_count", paramMap);
	}
	/**
	 * 往(物业公司托收银行信息)新增一条记录
	 * @param propertyCompanyBankCollectionInfo
	 * @return
	 */
	@Override
	public int insertPropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo){
		return sqlSession.insert("propertyCompanyBankCollectionInfoBase.insert_propertyCompanyBankCollectionInfo",propertyCompanyBankCollectionInfo);
	}
	/**
	 * 批量新增(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfoList
	 * @return
	 */
	@Override
	public int insertPropertyCompanyBankCollectionInfoBatch(List<PropertyCompanyBankCollectionInfo> propertyCompanyBankCollectionInfoList) {
		if (propertyCompanyBankCollectionInfoList == null || propertyCompanyBankCollectionInfoList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyCompanyBankCollectionInfoList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyCompanyBankCollectionInfo> batchList = propertyCompanyBankCollectionInfoList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyCompanyBankCollectionInfoBase.insert_propertyCompanyBankCollectionInfo_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfo
	 * @return
	 */
	@Override
	public int updatePropertyCompanyBankCollectionInfo(PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo){
		return sqlSession.update("propertyCompanyBankCollectionInfoBase.update_propertyCompanyBankCollectionInfo", propertyCompanyBankCollectionInfo);
	}
	/**
	 * 批量更新(物业公司托收银行信息)信息
	 * @param propertyCompanyBankCollectionInfoList
	 * @return
	 */
	@Override
	public int updatePropertyCompanyBankCollectionInfoBatch(List<PropertyCompanyBankCollectionInfo> propertyCompanyBankCollectionInfoList) {
		if (propertyCompanyBankCollectionInfoList == null || propertyCompanyBankCollectionInfoList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyCompanyBankCollectionInfoBase.update_propertyCompanyBankCollectionInfo_Batch", propertyCompanyBankCollectionInfoList);
	}
	
	/**
	 * 根据序列号删除(物业公司托收银行信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyBankCollectionInfoLogic(java.math.BigInteger id){
		PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo = new PropertyCompanyBankCollectionInfo();
		propertyCompanyBankCollectionInfo.setId(id);
		propertyCompanyBankCollectionInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCompanyBankCollectionInfoBase.delete_propertyCompanyBankCollectionInfo_Logic",propertyCompanyBankCollectionInfo);
	}
	
	/**
	 * 根据Id 批量删除(物业公司托收银行信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyBankCollectionInfoLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyCompanyBankCollectionInfo> delList = new java.util.ArrayList<PropertyCompanyBankCollectionInfo>();
		for(java.math.BigInteger id:idList){
			PropertyCompanyBankCollectionInfo propertyCompanyBankCollectionInfo = new PropertyCompanyBankCollectionInfo();
			propertyCompanyBankCollectionInfo.setId(id);
			propertyCompanyBankCollectionInfo.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCompanyBankCollectionInfo);
		}
		return sqlSession.update("propertyCompanyBankCollectionInfoBase.delete_propertyCompanyBankCollectionInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyBankCollectionInfo(java.math.BigInteger id){
//		return sqlSession.delete("propertyCompanyBankCollectionInfoBase.delete_propertyCompanyBankCollectionInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyBankCollectionInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCompanyBankCollectionInfoBase.delete_propertyCompanyBankCollectionInfo_Batch", idList);
//	}
	
	
}
