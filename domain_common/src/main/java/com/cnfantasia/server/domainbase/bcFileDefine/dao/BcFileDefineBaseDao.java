package com.cnfantasia.server.domainbase.bcFileDefine.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;

/**
 * 描述:(出盘回盘文件格式定义) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcFileDefineBaseDao extends AbstractBaseDao implements IBcFileDefineBaseDao{
	/**
	 * 根据条件查询(出盘回盘文件格式定义)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcFileDefine> selectBcFileDefineByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcFileDefineBase.select_bcFileDefine",paramMap);
	}
	/**
	 * 按条件分页查询(出盘回盘文件格式定义)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcFileDefine> selectBcFileDefineByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcFileDefineCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcFileDefine> resMap= sqlSession.selectList("bcFileDefineBase.select_bcFileDefine_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(出盘回盘文件格式定义)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcFileDefine selectBcFileDefineBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcFileDefineBase.select_bcFileDefine_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(出盘回盘文件格式定义)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcFileDefineCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcFileDefineBase.select_bcFileDefine_count", paramMap);
	}
	/**
	 * 往(出盘回盘文件格式定义)新增一条记录
	 * @param bcFileDefine
	 * @return
	 */
	@Override
	public int insertBcFileDefine(BcFileDefine bcFileDefine){
		return sqlSession.insert("bcFileDefineBase.insert_bcFileDefine",bcFileDefine);
	}
	/**
	 * 批量新增(出盘回盘文件格式定义)信息
	 * @param bcFileDefineList
	 * @return
	 */
	@Override
	public int insertBcFileDefineBatch(List<BcFileDefine> bcFileDefineList) {
		if (bcFileDefineList == null || bcFileDefineList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcFileDefineList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcFileDefine> batchList = bcFileDefineList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcFileDefineBase.insert_bcFileDefine_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(出盘回盘文件格式定义)信息
	 * @param bcFileDefine
	 * @return
	 */
	@Override
	public int updateBcFileDefine(BcFileDefine bcFileDefine){
		return sqlSession.update("bcFileDefineBase.update_bcFileDefine", bcFileDefine);
	}
	/**
	 * 批量更新(出盘回盘文件格式定义)信息
	 * @param bcFileDefineList
	 * @return
	 */
	@Override
	public int updateBcFileDefineBatch(List<BcFileDefine> bcFileDefineList) {
		if (bcFileDefineList == null || bcFileDefineList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcFileDefineBase.update_bcFileDefine_Batch", bcFileDefineList);
	}
	
	/**
	 * 根据序列号删除(出盘回盘文件格式定义)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcFileDefineLogic(java.math.BigInteger id){
		BcFileDefine bcFileDefine = new BcFileDefine();
		bcFileDefine.setId(id);
		bcFileDefine.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcFileDefineBase.delete_bcFileDefine_Logic",bcFileDefine);
	}
	
	/**
	 * 根据Id 批量删除(出盘回盘文件格式定义)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcFileDefineLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcFileDefine> delList = new java.util.ArrayList<BcFileDefine>();
		for(java.math.BigInteger id:idList){
			BcFileDefine bcFileDefine = new BcFileDefine();
			bcFileDefine.setId(id);
			bcFileDefine.setSys0DelState(RecordState_DELETED);
			delList.add(bcFileDefine);
		}
		return sqlSession.update("bcFileDefineBase.delete_bcFileDefine_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(出盘回盘文件格式定义)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcFileDefine(java.math.BigInteger id){
//		return sqlSession.delete("bcFileDefineBase.delete_bcFileDefine", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(出盘回盘文件格式定义)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcFileDefineBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcFileDefineBase.delete_bcFileDefine_Batch", idList);
//	}
	
	
}
