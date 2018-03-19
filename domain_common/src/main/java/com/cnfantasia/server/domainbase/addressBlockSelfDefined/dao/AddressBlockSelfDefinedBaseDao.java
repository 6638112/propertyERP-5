package com.cnfantasia.server.domainbase.addressBlockSelfDefined.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressBlockSelfDefined.entity.AddressBlockSelfDefined;

/**
 * 描述:(自定义片区) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AddressBlockSelfDefinedBaseDao extends AbstractBaseDao implements IAddressBlockSelfDefinedBaseDao{
	/**
	 * 根据条件查询(自定义片区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressBlockSelfDefined> selectAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("addressBlockSelfDefinedBase.select_addressBlockSelfDefined",paramMap);
	}
	/**
	 * 按条件分页查询(自定义片区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressBlockSelfDefined> selectAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAddressBlockSelfDefinedCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AddressBlockSelfDefined> resMap= sqlSession.selectList("addressBlockSelfDefinedBase.select_addressBlockSelfDefined_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(自定义片区)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressBlockSelfDefined selectAddressBlockSelfDefinedBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("addressBlockSelfDefinedBase.select_addressBlockSelfDefined_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(自定义片区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAddressBlockSelfDefinedCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("addressBlockSelfDefinedBase.select_addressBlockSelfDefined_count", paramMap);
	}
	/**
	 * 往(自定义片区)新增一条记录
	 * @param addressBlockSelfDefined
	 * @return
	 */
	@Override
	public int insertAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined){
		return sqlSession.insert("addressBlockSelfDefinedBase.insert_addressBlockSelfDefined",addressBlockSelfDefined);
	}
	/**
	 * 批量新增(自定义片区)信息
	 * @param addressBlockSelfDefinedList
	 * @return
	 */
	@Override
	public int insertAddressBlockSelfDefinedBatch(List<AddressBlockSelfDefined> addressBlockSelfDefinedList) {
		return sqlSession.insert("addressBlockSelfDefinedBase.insert_addressBlockSelfDefined_Batch",addressBlockSelfDefinedList);
	}
	
	/**
	 * 更新(自定义片区)信息
	 * @param addressBlockSelfDefined
	 * @return
	 */
	@Override
	public int updateAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined){
		return sqlSession.update("addressBlockSelfDefinedBase.update_addressBlockSelfDefined", addressBlockSelfDefined);
	}
	/**
	 * 批量更新(自定义片区)信息
	 * @param addressBlockSelfDefinedList
	 * @return
	 */
	@Override
	public int updateAddressBlockSelfDefinedBatch(List<AddressBlockSelfDefined> addressBlockSelfDefinedList) {
		return sqlSession.update("addressBlockSelfDefinedBase.update_addressBlockSelfDefined_Batch", addressBlockSelfDefinedList);
	}
	
	/**
	 * 根据序列号删除(自定义片区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockSelfDefinedLogic(java.math.BigInteger id){
		AddressBlockSelfDefined addressBlockSelfDefined = new AddressBlockSelfDefined();
		addressBlockSelfDefined.setId(id);
		addressBlockSelfDefined.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("addressBlockSelfDefinedBase.delete_addressBlockSelfDefined_Logic",addressBlockSelfDefined);
	}
	
	/**
	 * 根据Id 批量删除(自定义片区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockSelfDefinedLogicBatch(List<java.math.BigInteger> idList) {
		List<AddressBlockSelfDefined> delList = new java.util.ArrayList<AddressBlockSelfDefined>();
		for(java.math.BigInteger id:idList){
			AddressBlockSelfDefined addressBlockSelfDefined = new AddressBlockSelfDefined();
			addressBlockSelfDefined.setId(id);
			addressBlockSelfDefined.setSys0DelState(RecordState_DELETED);
			delList.add(addressBlockSelfDefined);
		}
		return sqlSession.update("addressBlockSelfDefinedBase.delete_addressBlockSelfDefined_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(自定义片区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlockSelfDefined(java.math.BigInteger id){
//		return sqlSession.delete("addressBlockSelfDefinedBase.delete_addressBlockSelfDefined", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自定义片区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlockSelfDefinedBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("addressBlockSelfDefinedBase.delete_addressBlockSelfDefined_Batch", idList);
//	}
	
	
}
