package com.cnfantasia.server.domainbase.commonGatherData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commonGatherData.entity.CommonGatherData;

/**
 * 描述:(通用模块信息归集) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommonGatherDataBaseDao extends AbstractBaseDao implements ICommonGatherDataBaseDao{
	/**
	 * 根据条件查询(通用模块信息归集)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommonGatherData> selectCommonGatherDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commonGatherDataBase.select_commonGatherData",paramMap);
	}
	/**
	 * 按条件分页查询(通用模块信息归集)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommonGatherData> selectCommonGatherDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommonGatherDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommonGatherData> resMap= sqlSession.selectList("commonGatherDataBase.select_commonGatherData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(通用模块信息归集)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommonGatherData selectCommonGatherDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commonGatherDataBase.select_commonGatherData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(通用模块信息归集)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommonGatherDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commonGatherDataBase.select_commonGatherData_count", paramMap);
	}
	/**
	 * 往(通用模块信息归集)新增一条记录
	 * @param commonGatherData
	 * @return
	 */
	@Override
	public int insertCommonGatherData(CommonGatherData commonGatherData){
		return sqlSession.insert("commonGatherDataBase.insert_commonGatherData",commonGatherData);
	}
	/**
	 * 批量新增(通用模块信息归集)信息
	 * @param commonGatherDataList
	 * @return
	 */
	@Override
	public int insertCommonGatherDataBatch(List<CommonGatherData> commonGatherDataList) {
		return sqlSession.insert("commonGatherDataBase.insert_commonGatherData_Batch",commonGatherDataList);
	}
	
	/**
	 * 更新(通用模块信息归集)信息
	 * @param commonGatherData
	 * @return
	 */
	@Override
	public int updateCommonGatherData(CommonGatherData commonGatherData){
		return sqlSession.update("commonGatherDataBase.update_commonGatherData", commonGatherData);
	}
	/**
	 * 批量更新(通用模块信息归集)信息
	 * @param commonGatherDataList
	 * @return
	 */
	@Override
	public int updateCommonGatherDataBatch(List<CommonGatherData> commonGatherDataList) {
		return sqlSession.update("commonGatherDataBase.update_commonGatherData_Batch", commonGatherDataList);
	}
	
	/**
	 * 根据序列号删除(通用模块信息归集)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommonGatherDataLogic(java.math.BigInteger id){
		CommonGatherData commonGatherData = new CommonGatherData();
		commonGatherData.setId(id);
		commonGatherData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commonGatherDataBase.delete_commonGatherData_Logic",commonGatherData);
	}
	
	/**
	 * 根据Id 批量删除(通用模块信息归集)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommonGatherDataLogicBatch(List<java.math.BigInteger> idList) {
		List<CommonGatherData> delList = new java.util.ArrayList<CommonGatherData>();
		for(java.math.BigInteger id:idList){
			CommonGatherData commonGatherData = new CommonGatherData();
			commonGatherData.setId(id);
			commonGatherData.setSys0DelState(RecordState_DELETED);
			delList.add(commonGatherData);
		}
		return sqlSession.update("commonGatherDataBase.delete_commonGatherData_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(通用模块信息归集)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommonGatherData(java.math.BigInteger id){
//		return sqlSession.delete("commonGatherDataBase.delete_commonGatherData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(通用模块信息归集)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommonGatherDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commonGatherDataBase.delete_commonGatherData_Batch", idList);
//	}
	
	
}
