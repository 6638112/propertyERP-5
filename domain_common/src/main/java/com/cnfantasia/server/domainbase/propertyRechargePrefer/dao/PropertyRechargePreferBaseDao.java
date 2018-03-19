package com.cnfantasia.server.domainbase.propertyRechargePrefer.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRechargePrefer.entity.PropertyRechargePrefer;

/**
 * 描述:(物业充值随机立减记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyRechargePreferBaseDao extends AbstractBaseDao implements IPropertyRechargePreferBaseDao{
	/**
	 * 根据条件查询(物业充值随机立减记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRechargePrefer> selectPropertyRechargePreferByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyRechargePreferBase.select_propertyRechargePrefer",paramMap);
	}
	/**
	 * 按条件分页查询(物业充值随机立减记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRechargePrefer> selectPropertyRechargePreferByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyRechargePreferCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyRechargePrefer> resMap= sqlSession.selectList("propertyRechargePreferBase.select_propertyRechargePrefer_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业充值随机立减记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRechargePrefer selectPropertyRechargePreferBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyRechargePreferBase.select_propertyRechargePrefer_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业充值随机立减记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyRechargePreferCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyRechargePreferBase.select_propertyRechargePrefer_count", paramMap);
	}
	/**
	 * 往(物业充值随机立减记录表)新增一条记录
	 * @param propertyRechargePrefer
	 * @return
	 */
	@Override
	public int insertPropertyRechargePrefer(PropertyRechargePrefer propertyRechargePrefer){
		return sqlSession.insert("propertyRechargePreferBase.insert_propertyRechargePrefer",propertyRechargePrefer);
	}
	/**
	 * 批量新增(物业充值随机立减记录表)信息
	 * @param propertyRechargePreferList
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferBatch(List<PropertyRechargePrefer> propertyRechargePreferList) {
		if (propertyRechargePreferList == null || propertyRechargePreferList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyRechargePreferList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyRechargePrefer> batchList = propertyRechargePreferList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyRechargePreferBase.insert_propertyRechargePrefer_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业充值随机立减记录表)信息
	 * @param propertyRechargePrefer
	 * @return
	 */
	@Override
	public int updatePropertyRechargePrefer(PropertyRechargePrefer propertyRechargePrefer){
		return sqlSession.update("propertyRechargePreferBase.update_propertyRechargePrefer", propertyRechargePrefer);
	}
	/**
	 * 批量更新(物业充值随机立减记录表)信息
	 * @param propertyRechargePreferList
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferBatch(List<PropertyRechargePrefer> propertyRechargePreferList) {
		if (propertyRechargePreferList == null || propertyRechargePreferList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyRechargePreferBase.update_propertyRechargePrefer_Batch", propertyRechargePreferList);
	}
	
	/**
	 * 根据序列号删除(物业充值随机立减记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferLogic(java.math.BigInteger id){
		PropertyRechargePrefer propertyRechargePrefer = new PropertyRechargePrefer();
		propertyRechargePrefer.setId(id);
		propertyRechargePrefer.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyRechargePreferBase.delete_propertyRechargePrefer_Logic",propertyRechargePrefer);
	}
	
	/**
	 * 根据Id 批量删除(物业充值随机立减记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRechargePreferLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyRechargePrefer> delList = new java.util.ArrayList<PropertyRechargePrefer>();
		for(java.math.BigInteger id:idList){
			PropertyRechargePrefer propertyRechargePrefer = new PropertyRechargePrefer();
			propertyRechargePrefer.setId(id);
			propertyRechargePrefer.setSys0DelState(RecordState_DELETED);
			delList.add(propertyRechargePrefer);
		}
		return sqlSession.update("propertyRechargePreferBase.delete_propertyRechargePrefer_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业充值随机立减记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePrefer(java.math.BigInteger id){
//		return sqlSession.delete("propertyRechargePreferBase.delete_propertyRechargePrefer", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业充值随机立减记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyRechargePreferBase.delete_propertyRechargePrefer_Batch", idList);
//	}
	
	
}
