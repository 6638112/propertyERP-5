package com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompanyThirdPayCfg.entity.PropertyCompanyThirdPayCfg;

/**
 * 描述:(物业公司/管理处/自有平台的支付宝信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCompanyThirdPayCfgBaseDao extends AbstractBaseDao implements IPropertyCompanyThirdPayCfgBaseDao{
	/**
	 * 根据条件查询(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCompanyThirdPayCfg> selectPropertyCompanyThirdPayCfgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCompanyThirdPayCfgBase.select_propertyCompanyThirdPayCfg",paramMap);
	}
	/**
	 * 按条件分页查询(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCompanyThirdPayCfg> selectPropertyCompanyThirdPayCfgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCompanyThirdPayCfgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCompanyThirdPayCfg> resMap= sqlSession.selectList("propertyCompanyThirdPayCfgBase.select_propertyCompanyThirdPayCfg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompanyThirdPayCfg selectPropertyCompanyThirdPayCfgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCompanyThirdPayCfgBase.select_propertyCompanyThirdPayCfg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司/管理处/自有平台的支付宝信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCompanyThirdPayCfgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCompanyThirdPayCfgBase.select_propertyCompanyThirdPayCfg_count", paramMap);
	}
	/**
	 * 往(物业公司/管理处/自有平台的支付宝信息)新增一条记录
	 * @param propertyCompanyThirdPayCfg
	 * @return
	 */
	@Override
	public int insertPropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg){
		return sqlSession.insert("propertyCompanyThirdPayCfgBase.insert_propertyCompanyThirdPayCfg",propertyCompanyThirdPayCfg);
	}
	/**
	 * 批量新增(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfgList
	 * @return
	 */
	@Override
	public int insertPropertyCompanyThirdPayCfgBatch(List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgList) {
		if (propertyCompanyThirdPayCfgList == null || propertyCompanyThirdPayCfgList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyCompanyThirdPayCfgList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyCompanyThirdPayCfg> batchList = propertyCompanyThirdPayCfgList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyCompanyThirdPayCfgBase.insert_propertyCompanyThirdPayCfg_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfg
	 * @return
	 */
	@Override
	public int updatePropertyCompanyThirdPayCfg(PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg){
		return sqlSession.update("propertyCompanyThirdPayCfgBase.update_propertyCompanyThirdPayCfg", propertyCompanyThirdPayCfg);
	}
	/**
	 * 批量更新(物业公司/管理处/自有平台的支付宝信息)信息
	 * @param propertyCompanyThirdPayCfgList
	 * @return
	 */
	@Override
	public int updatePropertyCompanyThirdPayCfgBatch(List<PropertyCompanyThirdPayCfg> propertyCompanyThirdPayCfgList) {
		if (propertyCompanyThirdPayCfgList == null || propertyCompanyThirdPayCfgList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyCompanyThirdPayCfgBase.update_propertyCompanyThirdPayCfg_Batch", propertyCompanyThirdPayCfgList);
	}
	
	/**
	 * 根据序列号删除(物业公司/管理处/自有平台的支付宝信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyThirdPayCfgLogic(java.math.BigInteger id){
		PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = new PropertyCompanyThirdPayCfg();
		propertyCompanyThirdPayCfg.setId(id);
		propertyCompanyThirdPayCfg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCompanyThirdPayCfgBase.delete_propertyCompanyThirdPayCfg_Logic",propertyCompanyThirdPayCfg);
	}
	
	/**
	 * 根据Id 批量删除(物业公司/管理处/自有平台的支付宝信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyThirdPayCfgLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyCompanyThirdPayCfg> delList = new java.util.ArrayList<PropertyCompanyThirdPayCfg>();
		for(java.math.BigInteger id:idList){
			PropertyCompanyThirdPayCfg propertyCompanyThirdPayCfg = new PropertyCompanyThirdPayCfg();
			propertyCompanyThirdPayCfg.setId(id);
			propertyCompanyThirdPayCfg.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCompanyThirdPayCfg);
		}
		return sqlSession.update("propertyCompanyThirdPayCfgBase.delete_propertyCompanyThirdPayCfg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司/管理处/自有平台的支付宝信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyThirdPayCfg(java.math.BigInteger id){
//		return sqlSession.delete("propertyCompanyThirdPayCfgBase.delete_propertyCompanyThirdPayCfg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司/管理处/自有平台的支付宝信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyThirdPayCfgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCompanyThirdPayCfgBase.delete_propertyCompanyThirdPayCfg_Batch", idList);
//	}
	
	
}
