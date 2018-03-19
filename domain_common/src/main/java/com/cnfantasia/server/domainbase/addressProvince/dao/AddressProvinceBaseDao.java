package com.cnfantasia.server.domainbase.addressProvince.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

/**
 * 描述:(省) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AddressProvinceBaseDao extends AbstractBaseDao implements IAddressProvinceBaseDao{
	/**
	 * 根据条件查询(省)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressProvince> selectAddressProvinceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("addressProvinceBase.select_addressProvince",paramMap);
	}
	/**
	 * 按条件分页查询(省)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AddressProvince> selectAddressProvinceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAddressProvinceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AddressProvince> resMap= sqlSession.selectList("addressProvinceBase.select_addressProvince_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(省)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressProvince selectAddressProvinceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("addressProvinceBase.select_addressProvince_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(省)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAddressProvinceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("addressProvinceBase.select_addressProvince_count", paramMap);
	}
	/**
	 * 往(省)新增一条记录
	 * @param addressProvince
	 * @return
	 */
	@Override
	public int insertAddressProvince(AddressProvince addressProvince){
		return sqlSession.insert("addressProvinceBase.insert_addressProvince",addressProvince);
	}
	/**
	 * 批量新增(省)信息
	 * @param addressProvinceList
	 * @return
	 */
	@Override
	public int insertAddressProvinceBatch(List<AddressProvince> addressProvinceList) {
		return sqlSession.insert("addressProvinceBase.insert_addressProvince_Batch",addressProvinceList);
	}
	
	/**
	 * 更新(省)信息
	 * @param addressProvince
	 * @return
	 */
	@Override
	public int updateAddressProvince(AddressProvince addressProvince){
		return sqlSession.update("addressProvinceBase.update_addressProvince", addressProvince);
	}
	/**
	 * 批量更新(省)信息
	 * @param addressProvinceList
	 * @return
	 */
	@Override
	public int updateAddressProvinceBatch(List<AddressProvince> addressProvinceList) {
		return sqlSession.update("addressProvinceBase.update_addressProvince_Batch", addressProvinceList);
	}
	
	/**
	 * 根据序列号删除(省)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressProvinceLogic(java.math.BigInteger id){
		AddressProvince addressProvince = new AddressProvince();
		addressProvince.setId(id);
		addressProvince.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("addressProvinceBase.delete_addressProvince_Logic",addressProvince);
	}
	
	/**
	 * 根据Id 批量删除(省)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressProvinceLogicBatch(List<java.math.BigInteger> idList) {
		List<AddressProvince> delList = new java.util.ArrayList<AddressProvince>();
		for(java.math.BigInteger id:idList){
			AddressProvince addressProvince = new AddressProvince();
			addressProvince.setId(id);
			addressProvince.setSys0DelState(RecordState_DELETED);
			delList.add(addressProvince);
		}
		return sqlSession.update("addressProvinceBase.delete_addressProvince_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(省)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressProvince(java.math.BigInteger id){
//		return sqlSession.delete("addressProvinceBase.delete_addressProvince", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(省)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressProvinceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("addressProvinceBase.delete_addressProvince_Batch", idList);
//	}
	
	
}
