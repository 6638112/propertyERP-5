package com.cnfantasia.server.domainbase.dredgeAddress.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeAddress.entity.DredgeAddress;

/**
 * 描述:(维修地址) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeAddressBaseDao extends AbstractBaseDao implements IDredgeAddressBaseDao{
	/**
	 * 根据条件查询(维修地址)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeAddress> selectDredgeAddressByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeAddressBase.select_dredgeAddress",paramMap);
	}
	/**
	 * 按条件分页查询(维修地址)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeAddress> selectDredgeAddressByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeAddressCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeAddress> resMap= sqlSession.selectList("dredgeAddressBase.select_dredgeAddress_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修地址)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeAddress selectDredgeAddressBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeAddressBase.select_dredgeAddress_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修地址)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeAddressCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeAddressBase.select_dredgeAddress_count", paramMap);
	}
	/**
	 * 往(维修地址)新增一条记录
	 * @param dredgeAddress
	 * @return
	 */
	@Override
	public int insertDredgeAddress(DredgeAddress dredgeAddress){
		return sqlSession.insert("dredgeAddressBase.insert_dredgeAddress",dredgeAddress);
	}
	/**
	 * 批量新增(维修地址)信息
	 * @param dredgeAddressList
	 * @return
	 */
	@Override
	public int insertDredgeAddressBatch(List<DredgeAddress> dredgeAddressList) {
		if (dredgeAddressList == null || dredgeAddressList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dredgeAddressList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DredgeAddress> batchList = dredgeAddressList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dredgeAddressBase.insert_dredgeAddress_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(维修地址)信息
	 * @param dredgeAddress
	 * @return
	 */
	@Override
	public int updateDredgeAddress(DredgeAddress dredgeAddress){
		return sqlSession.update("dredgeAddressBase.update_dredgeAddress", dredgeAddress);
	}
	/**
	 * 批量更新(维修地址)信息
	 * @param dredgeAddressList
	 * @return
	 */
	@Override
	public int updateDredgeAddressBatch(List<DredgeAddress> dredgeAddressList) {
		if (dredgeAddressList == null || dredgeAddressList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dredgeAddressBase.update_dredgeAddress_Batch", dredgeAddressList);
	}
	
	/**
	 * 根据序列号删除(维修地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeAddressLogic(java.math.BigInteger id){
		DredgeAddress dredgeAddress = new DredgeAddress();
		dredgeAddress.setId(id);
		dredgeAddress.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeAddressBase.delete_dredgeAddress_Logic",dredgeAddress);
	}
	
	/**
	 * 根据Id 批量删除(维修地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeAddressLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DredgeAddress> delList = new java.util.ArrayList<DredgeAddress>();
		for(java.math.BigInteger id:idList){
			DredgeAddress dredgeAddress = new DredgeAddress();
			dredgeAddress.setId(id);
			dredgeAddress.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeAddress);
		}
		return sqlSession.update("dredgeAddressBase.delete_dredgeAddress_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修地址)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeAddress(java.math.BigInteger id){
//		return sqlSession.delete("dredgeAddressBase.delete_dredgeAddress", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修地址)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeAddressBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeAddressBase.delete_dredgeAddress_Batch", idList);
//	}
	
	
}
