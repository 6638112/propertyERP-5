package com.cnfantasia.server.domainbase.userSwitchGb.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;

/**
 * 描述:(用户切换小区表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserSwitchGbBaseDao extends AbstractBaseDao implements IUserSwitchGbBaseDao{
	/**
	 * 根据条件查询(用户切换小区表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserSwitchGb> selectUserSwitchGbByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userSwitchGbBase.select_userSwitchGb",paramMap);
	}
	/**
	 * 按条件分页查询(用户切换小区表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserSwitchGb> selectUserSwitchGbByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserSwitchGbCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserSwitchGb> resMap= sqlSession.selectList("userSwitchGbBase.select_userSwitchGb_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户切换小区表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserSwitchGb selectUserSwitchGbBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userSwitchGbBase.select_userSwitchGb_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户切换小区表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserSwitchGbCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userSwitchGbBase.select_userSwitchGb_count", paramMap);
	}
	/**
	 * 往(用户切换小区表)新增一条记录
	 * @param userSwitchGb
	 * @return
	 */
	@Override
	public int insertUserSwitchGb(UserSwitchGb userSwitchGb){
		return sqlSession.insert("userSwitchGbBase.insert_userSwitchGb",userSwitchGb);
	}
	/**
	 * 批量新增(用户切换小区表)信息
	 * @param userSwitchGbList
	 * @return
	 */
	@Override
	public int insertUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList) {
		if (userSwitchGbList == null || userSwitchGbList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = userSwitchGbList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<UserSwitchGb> batchList = userSwitchGbList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("userSwitchGbBase.insert_userSwitchGb_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(用户切换小区表)信息
	 * @param userSwitchGb
	 * @return
	 */
	@Override
	public int updateUserSwitchGb(UserSwitchGb userSwitchGb){
		return sqlSession.update("userSwitchGbBase.update_userSwitchGb", userSwitchGb);
	}
	/**
	 * 批量更新(用户切换小区表)信息
	 * @param userSwitchGbList
	 * @return
	 */
	@Override
	public int updateUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList) {
		if (userSwitchGbList == null || userSwitchGbList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("userSwitchGbBase.update_userSwitchGb_Batch", userSwitchGbList);
	}
	
	/**
	 * 根据序列号删除(用户切换小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserSwitchGbLogic(java.math.BigInteger id){
		UserSwitchGb userSwitchGb = new UserSwitchGb();
		userSwitchGb.setId(id);
		userSwitchGb.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userSwitchGbBase.delete_userSwitchGb_Logic",userSwitchGb);
	}
	
	/**
	 * 根据Id 批量删除(用户切换小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserSwitchGbLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<UserSwitchGb> delList = new java.util.ArrayList<UserSwitchGb>();
		for(java.math.BigInteger id:idList){
			UserSwitchGb userSwitchGb = new UserSwitchGb();
			userSwitchGb.setId(id);
			userSwitchGb.setSys0DelState(RecordState_DELETED);
			delList.add(userSwitchGb);
		}
		return sqlSession.update("userSwitchGbBase.delete_userSwitchGb_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户切换小区表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserSwitchGb(java.math.BigInteger id){
//		return sqlSession.delete("userSwitchGbBase.delete_userSwitchGb", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户切换小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserSwitchGbBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userSwitchGbBase.delete_userSwitchGb_Batch", idList);
//	}
	
	
}
