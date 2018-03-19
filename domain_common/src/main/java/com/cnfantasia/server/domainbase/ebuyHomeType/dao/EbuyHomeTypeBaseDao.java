package com.cnfantasia.server.domainbase.ebuyHomeType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHomeType.entity.EbuyHomeType;

/**
 * 描述:(首页分类) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyHomeTypeBaseDao extends AbstractBaseDao implements IEbuyHomeTypeBaseDao{
	/**
	 * 根据条件查询(首页分类)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyHomeType> selectEbuyHomeTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyHomeTypeBase.select_ebuyHomeType",paramMap);
	}
	/**
	 * 按条件分页查询(首页分类)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyHomeType> selectEbuyHomeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyHomeTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyHomeType> resMap= sqlSession.selectList("ebuyHomeTypeBase.select_ebuyHomeType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(首页分类)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyHomeType selectEbuyHomeTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyHomeTypeBase.select_ebuyHomeType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(首页分类)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyHomeTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyHomeTypeBase.select_ebuyHomeType_count", paramMap);
	}
	/**
	 * 往(首页分类)新增一条记录
	 * @param ebuyHomeType
	 * @return
	 */
	@Override
	public int insertEbuyHomeType(EbuyHomeType ebuyHomeType){
		return sqlSession.insert("ebuyHomeTypeBase.insert_ebuyHomeType",ebuyHomeType);
	}
	/**
	 * 批量新增(首页分类)信息
	 * @param ebuyHomeTypeList
	 * @return
	 */
	@Override
	public int insertEbuyHomeTypeBatch(List<EbuyHomeType> ebuyHomeTypeList) {
		return sqlSession.insert("ebuyHomeTypeBase.insert_ebuyHomeType_Batch",ebuyHomeTypeList);
	}
	
	/**
	 * 更新(首页分类)信息
	 * @param ebuyHomeType
	 * @return
	 */
	@Override
	public int updateEbuyHomeType(EbuyHomeType ebuyHomeType){
		return sqlSession.update("ebuyHomeTypeBase.update_ebuyHomeType", ebuyHomeType);
	}
	/**
	 * 批量更新(首页分类)信息
	 * @param ebuyHomeTypeList
	 * @return
	 */
	@Override
	public int updateEbuyHomeTypeBatch(List<EbuyHomeType> ebuyHomeTypeList) {
		return sqlSession.update("ebuyHomeTypeBase.update_ebuyHomeType_Batch", ebuyHomeTypeList);
	}
	
	/**
	 * 根据序列号删除(首页分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeLogic(java.math.BigInteger id){
		EbuyHomeType ebuyHomeType = new EbuyHomeType();
		ebuyHomeType.setId(id);
		ebuyHomeType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyHomeTypeBase.delete_ebuyHomeType_Logic",ebuyHomeType);
	}
	
	/**
	 * 根据Id 批量删除(首页分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyHomeType> delList = new java.util.ArrayList<EbuyHomeType>();
		for(java.math.BigInteger id:idList){
			EbuyHomeType ebuyHomeType = new EbuyHomeType();
			ebuyHomeType.setId(id);
			ebuyHomeType.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyHomeType);
		}
		return sqlSession.update("ebuyHomeTypeBase.delete_ebuyHomeType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(首页分类)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeType(java.math.BigInteger id){
//		return sqlSession.delete("ebuyHomeTypeBase.delete_ebuyHomeType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页分类)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyHomeTypeBase.delete_ebuyHomeType_Batch", idList);
//	}
	
	
}
