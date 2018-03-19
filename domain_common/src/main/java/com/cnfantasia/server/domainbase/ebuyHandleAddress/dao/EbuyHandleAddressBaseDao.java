package com.cnfantasia.server.domainbase.ebuyHandleAddress.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;

/**
 * 描述:(手动输入的收货地址) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyHandleAddressBaseDao extends AbstractBaseDao implements IEbuyHandleAddressBaseDao{
	/**
	 * 根据条件查询(手动输入的收货地址)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyHandleAddress> selectEbuyHandleAddressByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyHandleAddressBase.select_ebuyHandleAddress",paramMap);
	}
	/**
	 * 按条件分页查询(手动输入的收货地址)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyHandleAddress> selectEbuyHandleAddressByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyHandleAddressCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyHandleAddress> resMap= sqlSession.selectList("ebuyHandleAddressBase.select_ebuyHandleAddress_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(手动输入的收货地址)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyHandleAddress selectEbuyHandleAddressBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyHandleAddressBase.select_ebuyHandleAddress_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(手动输入的收货地址)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyHandleAddressCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyHandleAddressBase.select_ebuyHandleAddress_count", paramMap);
	}
	/**
	 * 往(手动输入的收货地址)新增一条记录
	 * @param ebuyHandleAddress
	 * @return
	 */
	@Override
	public int insertEbuyHandleAddress(EbuyHandleAddress ebuyHandleAddress){
		return sqlSession.insert("ebuyHandleAddressBase.insert_ebuyHandleAddress",ebuyHandleAddress);
	}
	/**
	 * 批量新增(手动输入的收货地址)信息
	 * @param ebuyHandleAddressList
	 * @return
	 */
	@Override
	public int insertEbuyHandleAddressBatch(List<EbuyHandleAddress> ebuyHandleAddressList) {
		return sqlSession.insert("ebuyHandleAddressBase.insert_ebuyHandleAddress_Batch",ebuyHandleAddressList);
	}
	
	/**
	 * 更新(手动输入的收货地址)信息
	 * @param ebuyHandleAddress
	 * @return
	 */
	@Override
	public int updateEbuyHandleAddress(EbuyHandleAddress ebuyHandleAddress){
		return sqlSession.update("ebuyHandleAddressBase.update_ebuyHandleAddress", ebuyHandleAddress);
	}
	/**
	 * 批量更新(手动输入的收货地址)信息
	 * @param ebuyHandleAddressList
	 * @return
	 */
	@Override
	public int updateEbuyHandleAddressBatch(List<EbuyHandleAddress> ebuyHandleAddressList) {
		return sqlSession.update("ebuyHandleAddressBase.update_ebuyHandleAddress_Batch", ebuyHandleAddressList);
	}
	
	/**
	 * 根据序列号删除(手动输入的收货地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyHandleAddressLogic(java.math.BigInteger id){
		EbuyHandleAddress ebuyHandleAddress = new EbuyHandleAddress();
		ebuyHandleAddress.setId(id);
		ebuyHandleAddress.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyHandleAddressBase.delete_ebuyHandleAddress_Logic",ebuyHandleAddress);
	}
	
	/**
	 * 根据Id 批量删除(手动输入的收货地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyHandleAddressLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyHandleAddress> delList = new java.util.ArrayList<EbuyHandleAddress>();
		for(java.math.BigInteger id:idList){
			EbuyHandleAddress ebuyHandleAddress = new EbuyHandleAddress();
			ebuyHandleAddress.setId(id);
			ebuyHandleAddress.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyHandleAddress);
		}
		return sqlSession.update("ebuyHandleAddressBase.delete_ebuyHandleAddress_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(手动输入的收货地址)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHandleAddress(java.math.BigInteger id){
//		return sqlSession.delete("ebuyHandleAddressBase.delete_ebuyHandleAddress", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(手动输入的收货地址)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHandleAddressBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyHandleAddressBase.delete_ebuyHandleAddress_Batch", idList);
//	}
	
	
}
