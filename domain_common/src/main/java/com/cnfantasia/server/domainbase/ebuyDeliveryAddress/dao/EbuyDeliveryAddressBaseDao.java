package com.cnfantasia.server.domainbase.ebuyDeliveryAddress.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;

/**
 * 描述:(收货地址) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyDeliveryAddressBaseDao extends AbstractBaseDao implements IEbuyDeliveryAddressBaseDao{
	/**
	 * 根据条件查询(收货地址)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryAddress> selectEbuyDeliveryAddressByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryAddressBase.select_ebuyDeliveryAddress",paramMap);
	}
	/**
	 * 按条件分页查询(收货地址)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryAddress> selectEbuyDeliveryAddressByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyDeliveryAddressCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyDeliveryAddress> resMap= sqlSession.selectList("ebuyDeliveryAddressBase.select_ebuyDeliveryAddress_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(收货地址)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryAddress selectEbuyDeliveryAddressBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyDeliveryAddressBase.select_ebuyDeliveryAddress_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(收货地址)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyDeliveryAddressCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyDeliveryAddressBase.select_ebuyDeliveryAddress_count", paramMap);
	}
	/**
	 * 往(收货地址)新增一条记录
	 * @param ebuyDeliveryAddress
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryAddress(EbuyDeliveryAddress ebuyDeliveryAddress){
		return sqlSession.insert("ebuyDeliveryAddressBase.insert_ebuyDeliveryAddress",ebuyDeliveryAddress);
	}
	/**
	 * 批量新增(收货地址)信息
	 * @param ebuyDeliveryAddressList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryAddressBatch(List<EbuyDeliveryAddress> ebuyDeliveryAddressList) {
		return sqlSession.insert("ebuyDeliveryAddressBase.insert_ebuyDeliveryAddress_Batch",ebuyDeliveryAddressList);
	}
	
	/**
	 * 更新(收货地址)信息
	 * @param ebuyDeliveryAddress
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryAddress(EbuyDeliveryAddress ebuyDeliveryAddress){
		return sqlSession.update("ebuyDeliveryAddressBase.update_ebuyDeliveryAddress", ebuyDeliveryAddress);
	}
	/**
	 * 批量更新(收货地址)信息
	 * @param ebuyDeliveryAddressList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryAddressBatch(List<EbuyDeliveryAddress> ebuyDeliveryAddressList) {
		return sqlSession.update("ebuyDeliveryAddressBase.update_ebuyDeliveryAddress_Batch", ebuyDeliveryAddressList);
	}
	
	/**
	 * 根据序列号删除(收货地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryAddressLogic(java.math.BigInteger id){
		EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
		ebuyDeliveryAddress.setId(id);
		ebuyDeliveryAddress.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyDeliveryAddressBase.delete_ebuyDeliveryAddress_Logic",ebuyDeliveryAddress);
	}
	
	/**
	 * 根据Id 批量删除(收货地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryAddressLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyDeliveryAddress> delList = new java.util.ArrayList<EbuyDeliveryAddress>();
		for(java.math.BigInteger id:idList){
			EbuyDeliveryAddress ebuyDeliveryAddress = new EbuyDeliveryAddress();
			ebuyDeliveryAddress.setId(id);
			ebuyDeliveryAddress.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyDeliveryAddress);
		}
		return sqlSession.update("ebuyDeliveryAddressBase.delete_ebuyDeliveryAddress_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(收货地址)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryAddress(java.math.BigInteger id){
//		return sqlSession.delete("ebuyDeliveryAddressBase.delete_ebuyDeliveryAddress", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收货地址)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryAddressBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyDeliveryAddressBase.delete_ebuyDeliveryAddress_Batch", idList);
//	}
	
	
}
