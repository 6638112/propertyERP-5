package com.cnfantasia.server.msbase.omsUser.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.msbase.omsUser.entity.OmsUser;

/**
 * 描述:(OMS用户表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsUserBaseDao extends AbstractBaseDao implements IOmsUserBaseDao{
	/**
	 * 根据条件查询(OMS用户表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUser> selectOmsUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsUserBase.select_omsUser",paramMap);
	}
	/**
	 * 按条件分页查询(OMS用户表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUser> selectOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsUser> resMap= sqlSession.selectList("omsUserBase.select_omsUser_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS用户表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUser selectOmsUserBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsUserBase.select_omsUser_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsUserBase.select_omsUser_count", paramMap);
	}
	/**
	 * 往(OMS用户表)新增一条记录
	 * @param omsUser
	 * @return
	 */
	@Override
	public int insertOmsUser(OmsUser omsUser){
		return sqlSession.insert("omsUserBase.insert_omsUser",omsUser);
	}
	/**
	 * 批量新增(OMS用户表)信息
	 * @param omsUserList
	 * @return
	 */
	@Override
	public int insertOmsUserBatch(List<OmsUser> omsUserList) {
		return sqlSession.insert("omsUserBase.insert_omsUser_Batch",omsUserList);
	}
	
	/**
	 * 更新(OMS用户表)信息
	 * @param omsUser
	 * @return
	 */
	@Override
	public int updateOmsUser(OmsUser omsUser){
		return sqlSession.update("omsUserBase.update_omsUser", omsUser);
	}
	/**
	 * 批量更新(OMS用户表)信息
	 * @param omsUserList
	 * @return
	 */
	@Override
	public int updateOmsUserBatch(List<OmsUser> omsUserList) {
		return sqlSession.update("omsUserBase.update_omsUser_Batch", omsUserList);
	}
	
	/**
	 * 根据序列号删除(OMS用户表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserLogic(java.math.BigInteger id){
		OmsUser omsUser = new OmsUser();
		omsUser.setId(id);
		omsUser.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsUserBase.delete_omsUser_Logic",omsUser);
	}
	
	/**
	 * 根据Id 批量删除(OMS用户表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsUser> delList = new java.util.ArrayList<OmsUser>();
		for(java.math.BigInteger id:idList){
			OmsUser omsUser = new OmsUser();
			omsUser.setId(id);
			omsUser.setSys0DelState(RecordState_DELETED);
			delList.add(omsUser);
		}
		return sqlSession.update("omsUserBase.delete_omsUser_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS用户表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUser(java.math.BigInteger id){
//		return sqlSession.delete("omsUserBase.delete_omsUser", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS用户表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsUserBase.delete_omsUser_Batch", idList);
//	}
	
	
}
