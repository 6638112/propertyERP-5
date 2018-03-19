package com.cnfantasia.server.domainbase.hobby.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.hobby.entity.Hobby;

/**
 * 描述:(兴趣爱好表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class HobbyBaseDao extends AbstractBaseDao implements IHobbyBaseDao{
	/**
	 * 根据条件查询(兴趣爱好表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Hobby> selectHobbyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("hobbyBase.select_hobby",paramMap);
	}
	/**
	 * 按条件分页查询(兴趣爱好表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Hobby> selectHobbyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectHobbyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Hobby> resMap= sqlSession.selectList("hobbyBase.select_hobby_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(兴趣爱好表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Hobby selectHobbyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("hobbyBase.select_hobby_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(兴趣爱好表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectHobbyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("hobbyBase.select_hobby_count", paramMap);
	}
	/**
	 * 往(兴趣爱好表)新增一条记录
	 * @param hobby
	 * @return
	 */
	@Override
	public int insertHobby(Hobby hobby){
		return sqlSession.insert("hobbyBase.insert_hobby",hobby);
	}
	/**
	 * 批量新增(兴趣爱好表)信息
	 * @param hobbyList
	 * @return
	 */
	@Override
	public int insertHobbyBatch(List<Hobby> hobbyList) {
		return sqlSession.insert("hobbyBase.insert_hobby_Batch",hobbyList);
	}
	
	/**
	 * 更新(兴趣爱好表)信息
	 * @param hobby
	 * @return
	 */
	@Override
	public int updateHobby(Hobby hobby){
		return sqlSession.update("hobbyBase.update_hobby", hobby);
	}
	/**
	 * 批量更新(兴趣爱好表)信息
	 * @param hobbyList
	 * @return
	 */
	@Override
	public int updateHobbyBatch(List<Hobby> hobbyList) {
		return sqlSession.update("hobbyBase.update_hobby_Batch", hobbyList);
	}
	
	/**
	 * 根据序列号删除(兴趣爱好表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteHobbyLogic(java.math.BigInteger id){
		Hobby hobby = new Hobby();
		hobby.setId(id);
		hobby.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("hobbyBase.delete_hobby_Logic",hobby);
	}
	
	/**
	 * 根据Id 批量删除(兴趣爱好表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteHobbyLogicBatch(List<java.math.BigInteger> idList) {
		List<Hobby> delList = new java.util.ArrayList<Hobby>();
		for(java.math.BigInteger id:idList){
			Hobby hobby = new Hobby();
			hobby.setId(id);
			hobby.setSys0DelState(RecordState_DELETED);
			delList.add(hobby);
		}
		return sqlSession.update("hobbyBase.delete_hobby_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(兴趣爱好表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteHobby(java.math.BigInteger id){
//		return sqlSession.delete("hobbyBase.delete_hobby", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(兴趣爱好表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteHobbyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("hobbyBase.delete_hobby_Batch", idList);
//	}
	
	
}
