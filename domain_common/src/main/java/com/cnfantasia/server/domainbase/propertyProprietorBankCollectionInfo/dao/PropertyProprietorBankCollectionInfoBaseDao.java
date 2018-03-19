package com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

/**
 * 描述:(业主银行托收信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyProprietorBankCollectionInfoBaseDao extends AbstractBaseDao implements IPropertyProprietorBankCollectionInfoBaseDao{
	/**
	 * 根据条件查询(业主银行托收信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyProprietorBankCollectionInfo> selectPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyProprietorBankCollectionInfoBase.select_propertyProprietorBankCollectionInfo",paramMap);
	}
	/**
	 * 按条件分页查询(业主银行托收信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyProprietorBankCollectionInfo> selectPropertyProprietorBankCollectionInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyProprietorBankCollectionInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyProprietorBankCollectionInfo> resMap= sqlSession.selectList("propertyProprietorBankCollectionInfoBase.select_propertyProprietorBankCollectionInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(业主银行托收信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyProprietorBankCollectionInfo selectPropertyProprietorBankCollectionInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyProprietorBankCollectionInfoBase.select_propertyProprietorBankCollectionInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(业主银行托收信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyProprietorBankCollectionInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyProprietorBankCollectionInfoBase.select_propertyProprietorBankCollectionInfo_count", paramMap);
	}
	/**
	 * 往(业主银行托收信息)新增一条记录
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	@Override
	public int insertPropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo){
		return sqlSession.insert("propertyProprietorBankCollectionInfoBase.insert_propertyProprietorBankCollectionInfo",propertyProprietorBankCollectionInfo);
	}
	/**
	 * 批量新增(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfoList
	 * @return
	 */
	@Override
	public int insertPropertyProprietorBankCollectionInfoBatch(List<PropertyProprietorBankCollectionInfo> propertyProprietorBankCollectionInfoList) {
		if (propertyProprietorBankCollectionInfoList == null || propertyProprietorBankCollectionInfoList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyProprietorBankCollectionInfoList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyProprietorBankCollectionInfo> batchList = propertyProprietorBankCollectionInfoList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyProprietorBankCollectionInfoBase.insert_propertyProprietorBankCollectionInfo_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfo
	 * @return
	 */
	@Override
	public int updatePropertyProprietorBankCollectionInfo(PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo){
		return sqlSession.update("propertyProprietorBankCollectionInfoBase.update_propertyProprietorBankCollectionInfo", propertyProprietorBankCollectionInfo);
	}
	/**
	 * 批量更新(业主银行托收信息)信息
	 * @param propertyProprietorBankCollectionInfoList
	 * @return
	 */
	@Override
	public int updatePropertyProprietorBankCollectionInfoBatch(List<PropertyProprietorBankCollectionInfo> propertyProprietorBankCollectionInfoList) {
		if (propertyProprietorBankCollectionInfoList == null || propertyProprietorBankCollectionInfoList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyProprietorBankCollectionInfoBase.update_propertyProprietorBankCollectionInfo_Batch", propertyProprietorBankCollectionInfoList);
	}
	
	/**
	 * 根据序列号删除(业主银行托收信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorBankCollectionInfoLogic(java.math.BigInteger id){
		PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo = new PropertyProprietorBankCollectionInfo();
		propertyProprietorBankCollectionInfo.setId(id);
		propertyProprietorBankCollectionInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyProprietorBankCollectionInfoBase.delete_propertyProprietorBankCollectionInfo_Logic",propertyProprietorBankCollectionInfo);
	}
	
	/**
	 * 根据Id 批量删除(业主银行托收信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorBankCollectionInfoLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyProprietorBankCollectionInfo> delList = new java.util.ArrayList<PropertyProprietorBankCollectionInfo>();
		for(java.math.BigInteger id:idList){
			PropertyProprietorBankCollectionInfo propertyProprietorBankCollectionInfo = new PropertyProprietorBankCollectionInfo();
			propertyProprietorBankCollectionInfo.setId(id);
			propertyProprietorBankCollectionInfo.setSys0DelState(RecordState_DELETED);
			delList.add(propertyProprietorBankCollectionInfo);
		}
		return sqlSession.update("propertyProprietorBankCollectionInfoBase.delete_propertyProprietorBankCollectionInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(业主银行托收信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietorBankCollectionInfo(java.math.BigInteger id){
//		return sqlSession.delete("propertyProprietorBankCollectionInfoBase.delete_propertyProprietorBankCollectionInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主银行托收信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietorBankCollectionInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyProprietorBankCollectionInfoBase.delete_propertyProprietorBankCollectionInfo_Batch", idList);
//	}
	
	
}
