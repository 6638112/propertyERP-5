package com.cnfantasia.server.domainbase.addressCity.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;

/**
 * 描述:(市) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AddressCityBaseDao extends AbstractBaseDao implements IAddressCityBaseDao{
	/**
	 * 根据条件查询(市)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressCity> selectAddressCityByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("addressCityBase.select_addressCity",paramMap);
	}
	/**
	 * 按条件分页查询(市)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressCity> selectAddressCityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAddressCityCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AddressCity> resMap= sqlSession.selectList("addressCityBase.select_addressCity_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(市)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressCity selectAddressCityBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("addressCityBase.select_addressCity_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(市)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAddressCityCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("addressCityBase.select_addressCity_count", paramMap);
	}
	/**
	 * 往(市)新增一条记录
	 * @param addressCity
	 * @return
	 */
	@Override
	public int insertAddressCity(AddressCity addressCity){
		return sqlSession.insert("addressCityBase.insert_addressCity",addressCity);
	}
	/**
	 * 批量新增(市)信息
	 * @param addressCityList
	 * @return
	 */
	@Override
	public int insertAddressCityBatch(List<AddressCity> addressCityList) {
		return sqlSession.insert("addressCityBase.insert_addressCity_Batch",addressCityList);
	}
	
	/**
	 * 更新(市)信息
	 * @param addressCity
	 * @return
	 */
	@Override
	public int updateAddressCity(AddressCity addressCity){
		return sqlSession.update("addressCityBase.update_addressCity", addressCity);
	}
	/**
	 * 批量更新(市)信息
	 * @param addressCityList
	 * @return
	 */
	@Override
	public int updateAddressCityBatch(List<AddressCity> addressCityList) {
		return sqlSession.update("addressCityBase.update_addressCity_Batch", addressCityList);
	}
	
	/**
	 * 根据序列号删除(市)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressCityLogic(java.math.BigInteger id){
		AddressCity addressCity = new AddressCity();
		addressCity.setId(id);
		addressCity.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("addressCityBase.delete_addressCity_Logic",addressCity);
	}
	
	/**
	 * 根据Id 批量删除(市)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressCityLogicBatch(List<java.math.BigInteger> idList) {
		List<AddressCity> delList = new java.util.ArrayList<AddressCity>();
		for(java.math.BigInteger id:idList){
			AddressCity addressCity = new AddressCity();
			addressCity.setId(id);
			addressCity.setSys0DelState(RecordState_DELETED);
			delList.add(addressCity);
		}
		return sqlSession.update("addressCityBase.delete_addressCity_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(市)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCity(java.math.BigInteger id){
//		return sqlSession.delete("addressCityBase.delete_addressCity", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(市)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCityBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("addressCityBase.delete_addressCity_Batch", idList);
//	}
	
	
}
