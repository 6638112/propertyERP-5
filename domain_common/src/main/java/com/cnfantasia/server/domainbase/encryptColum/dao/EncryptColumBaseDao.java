package com.cnfantasia.server.domainbase.encryptColum.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encryptColum.entity.EncryptColum;

/**
 * 描述:(加密字段) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EncryptColumBaseDao extends AbstractBaseDao implements IEncryptColumBaseDao{
	/**
	 * 根据条件查询(加密字段)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EncryptColum> selectEncryptColumByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("encryptColumBase.select_encryptColum",paramMap);
	}
	/**
	 * 按条件分页查询(加密字段)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EncryptColum> selectEncryptColumByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEncryptColumCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EncryptColum> resMap= sqlSession.selectList("encryptColumBase.select_encryptColum_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(加密字段)信息
	 * @param id
	 * @return
	 */
	@Override
	public EncryptColum selectEncryptColumBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("encryptColumBase.select_encryptColum_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(加密字段)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEncryptColumCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("encryptColumBase.select_encryptColum_count", paramMap);
	}
	/**
	 * 往(加密字段)新增一条记录
	 * @param encryptColum
	 * @return
	 */
	@Override
	public int insertEncryptColum(EncryptColum encryptColum){
		return sqlSession.insert("encryptColumBase.insert_encryptColum",encryptColum);
	}
	/**
	 * 批量新增(加密字段)信息
	 * @param encryptColumList
	 * @return
	 */
	@Override
	public int insertEncryptColumBatch(List<EncryptColum> encryptColumList) {
		return sqlSession.insert("encryptColumBase.insert_encryptColum_Batch",encryptColumList);
	}
	
	/**
	 * 更新(加密字段)信息
	 * @param encryptColum
	 * @return
	 */
	@Override
	public int updateEncryptColum(EncryptColum encryptColum){
		return sqlSession.update("encryptColumBase.update_encryptColum", encryptColum);
	}
	/**
	 * 批量更新(加密字段)信息
	 * @param encryptColumList
	 * @return
	 */
	@Override
	public int updateEncryptColumBatch(List<EncryptColum> encryptColumList) {
		return sqlSession.update("encryptColumBase.update_encryptColum_Batch", encryptColumList);
	}
	
	/**
	 * 根据序列号删除(加密字段)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEncryptColumLogic(java.math.BigInteger id){
		EncryptColum encryptColum = new EncryptColum();
		encryptColum.setId(id);
		encryptColum.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("encryptColumBase.delete_encryptColum_Logic",encryptColum);
	}
	
	/**
	 * 根据Id 批量删除(加密字段)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEncryptColumLogicBatch(List<java.math.BigInteger> idList) {
		List<EncryptColum> delList = new java.util.ArrayList<EncryptColum>();
		for(java.math.BigInteger id:idList){
			EncryptColum encryptColum = new EncryptColum();
			encryptColum.setId(id);
			encryptColum.setSys0DelState(RecordState_DELETED);
			delList.add(encryptColum);
		}
		return sqlSession.update("encryptColumBase.delete_encryptColum_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(加密字段)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptColum(java.math.BigInteger id){
//		return sqlSession.delete("encryptColumBase.delete_encryptColum", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(加密字段)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptColumBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("encryptColumBase.delete_encryptColum_Batch", idList);
//	}
	
	
}
