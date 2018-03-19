package com.cnfantasia.server.domainbase.addressCountry.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCountry.entity.AddressCountry;

/**
 * 描述:(国家表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AddressCountryBaseDao extends AbstractBaseDao implements IAddressCountryBaseDao{
	/**
	 * 根据条件查询(国家表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressCountry> selectAddressCountryByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("addressCountryBase.select_addressCountry",paramMap);
	}
	/**
	 * 按条件分页查询(国家表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressCountry> selectAddressCountryByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAddressCountryCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AddressCountry> resMap= sqlSession.selectList("addressCountryBase.select_addressCountry_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(国家表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressCountry selectAddressCountryBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("addressCountryBase.select_addressCountry_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(国家表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAddressCountryCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("addressCountryBase.select_addressCountry_count", paramMap);
	}
	/**
	 * 往(国家表)新增一条记录
	 * @param addressCountry
	 * @return
	 */
	@Override
	public int insertAddressCountry(AddressCountry addressCountry){
		return sqlSession.insert("addressCountryBase.insert_addressCountry",addressCountry);
	}
	/**
	 * 批量新增(国家表)信息
	 * @param addressCountryList
	 * @return
	 */
	@Override
	public int insertAddressCountryBatch(List<AddressCountry> addressCountryList) {
		return sqlSession.insert("addressCountryBase.insert_addressCountry_Batch",addressCountryList);
	}
	
	/**
	 * 更新(国家表)信息
	 * @param addressCountry
	 * @return
	 */
	@Override
	public int updateAddressCountry(AddressCountry addressCountry){
		return sqlSession.update("addressCountryBase.update_addressCountry", addressCountry);
	}
	/**
	 * 批量更新(国家表)信息
	 * @param addressCountryList
	 * @return
	 */
	@Override
	public int updateAddressCountryBatch(List<AddressCountry> addressCountryList) {
		return sqlSession.update("addressCountryBase.update_addressCountry_Batch", addressCountryList);
	}
	
	/**
	 * 根据序列号删除(国家表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressCountryLogic(java.math.BigInteger id){
		AddressCountry addressCountry = new AddressCountry();
		addressCountry.setId(id);
		addressCountry.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("addressCountryBase.delete_addressCountry_Logic",addressCountry);
	}
	
	/**
	 * 根据Id 批量删除(国家表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressCountryLogicBatch(List<java.math.BigInteger> idList) {
		List<AddressCountry> delList = new java.util.ArrayList<AddressCountry>();
		for(java.math.BigInteger id:idList){
			AddressCountry addressCountry = new AddressCountry();
			addressCountry.setId(id);
			addressCountry.setSys0DelState(RecordState_DELETED);
			delList.add(addressCountry);
		}
		return sqlSession.update("addressCountryBase.delete_addressCountry_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(国家表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCountry(java.math.BigInteger id){
//		return sqlSession.delete("addressCountryBase.delete_addressCountry", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(国家表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCountryBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("addressCountryBase.delete_addressCountry_Batch", idList);
//	}
	
	
}
