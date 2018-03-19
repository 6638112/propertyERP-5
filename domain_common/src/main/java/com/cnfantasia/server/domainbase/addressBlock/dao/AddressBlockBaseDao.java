package com.cnfantasia.server.domainbase.addressBlock.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;

/**
 * 描述:(区) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AddressBlockBaseDao extends AbstractBaseDao implements IAddressBlockBaseDao{
	/**
	 * 根据条件查询(区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressBlock> selectAddressBlockByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("addressBlockBase.select_addressBlock",paramMap);
	}
	/**
	 * 按条件分页查询(区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressBlock> selectAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAddressBlockCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AddressBlock> resMap= sqlSession.selectList("addressBlockBase.select_addressBlock_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(区)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressBlock selectAddressBlockBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("addressBlockBase.select_addressBlock_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAddressBlockCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("addressBlockBase.select_addressBlock_count", paramMap);
	}
	/**
	 * 往(区)新增一条记录
	 * @param addressBlock
	 * @return
	 */
	@Override
	public int insertAddressBlock(AddressBlock addressBlock){
		return sqlSession.insert("addressBlockBase.insert_addressBlock",addressBlock);
	}
	/**
	 * 批量新增(区)信息
	 * @param addressBlockList
	 * @return
	 */
	@Override
	public int insertAddressBlockBatch(List<AddressBlock> addressBlockList) {
		return sqlSession.insert("addressBlockBase.insert_addressBlock_Batch",addressBlockList);
	}
	
	/**
	 * 更新(区)信息
	 * @param addressBlock
	 * @return
	 */
	@Override
	public int updateAddressBlock(AddressBlock addressBlock){
		return sqlSession.update("addressBlockBase.update_addressBlock", addressBlock);
	}
	/**
	 * 批量更新(区)信息
	 * @param addressBlockList
	 * @return
	 */
	@Override
	public int updateAddressBlockBatch(List<AddressBlock> addressBlockList) {
		return sqlSession.update("addressBlockBase.update_addressBlock_Batch", addressBlockList);
	}
	
	/**
	 * 根据序列号删除(区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockLogic(java.math.BigInteger id){
		AddressBlock addressBlock = new AddressBlock();
		addressBlock.setId(id);
		addressBlock.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("addressBlockBase.delete_addressBlock_Logic",addressBlock);
	}
	
	/**
	 * 根据Id 批量删除(区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockLogicBatch(List<java.math.BigInteger> idList) {
		List<AddressBlock> delList = new java.util.ArrayList<AddressBlock>();
		for(java.math.BigInteger id:idList){
			AddressBlock addressBlock = new AddressBlock();
			addressBlock.setId(id);
			addressBlock.setSys0DelState(RecordState_DELETED);
			delList.add(addressBlock);
		}
		return sqlSession.update("addressBlockBase.delete_addressBlock_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlock(java.math.BigInteger id){
//		return sqlSession.delete("addressBlockBase.delete_addressBlock", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlockBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("addressBlockBase.delete_addressBlock_Batch", idList);
//	}
	
	
}
