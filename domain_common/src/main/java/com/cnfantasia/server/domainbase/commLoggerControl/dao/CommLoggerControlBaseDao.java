package com.cnfantasia.server.domainbase.commLoggerControl.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commLoggerControl.entity.CommLoggerControl;

/**
 * 描述:(公共日志记录控制表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommLoggerControlBaseDao extends AbstractBaseDao implements ICommLoggerControlBaseDao{
	/**
	 * 根据条件查询(公共日志记录控制表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommLoggerControl> selectCommLoggerControlByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commLoggerControlBase.select_commLoggerControl",paramMap);
	}
	/**
	 * 按条件分页查询(公共日志记录控制表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommLoggerControl> selectCommLoggerControlByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommLoggerControlCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommLoggerControl> resMap= sqlSession.selectList("commLoggerControlBase.select_commLoggerControl_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(公共日志记录控制表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommLoggerControl selectCommLoggerControlBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commLoggerControlBase.select_commLoggerControl_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(公共日志记录控制表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommLoggerControlCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commLoggerControlBase.select_commLoggerControl_count", paramMap);
	}
	/**
	 * 往(公共日志记录控制表)新增一条记录
	 * @param commLoggerControl
	 * @return
	 */
	@Override
	public int insertCommLoggerControl(CommLoggerControl commLoggerControl){
		return sqlSession.insert("commLoggerControlBase.insert_commLoggerControl",commLoggerControl);
	}
	/**
	 * 批量新增(公共日志记录控制表)信息
	 * @param commLoggerControlList
	 * @return
	 */
	@Override
	public int insertCommLoggerControlBatch(List<CommLoggerControl> commLoggerControlList) {
		return sqlSession.insert("commLoggerControlBase.insert_commLoggerControl_Batch",commLoggerControlList);
	}
	
	/**
	 * 更新(公共日志记录控制表)信息
	 * @param commLoggerControl
	 * @return
	 */
	@Override
	public int updateCommLoggerControl(CommLoggerControl commLoggerControl){
		return sqlSession.update("commLoggerControlBase.update_commLoggerControl", commLoggerControl);
	}
	/**
	 * 批量更新(公共日志记录控制表)信息
	 * @param commLoggerControlList
	 * @return
	 */
	@Override
	public int updateCommLoggerControlBatch(List<CommLoggerControl> commLoggerControlList) {
		return sqlSession.update("commLoggerControlBase.update_commLoggerControl_Batch", commLoggerControlList);
	}
	
	/**
	 * 根据序列号删除(公共日志记录控制表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerControlLogic(java.math.BigInteger id){
		CommLoggerControl commLoggerControl = new CommLoggerControl();
		commLoggerControl.setId(id);
		commLoggerControl.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commLoggerControlBase.delete_commLoggerControl_Logic",commLoggerControl);
	}
	
	/**
	 * 根据Id 批量删除(公共日志记录控制表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommLoggerControlLogicBatch(List<java.math.BigInteger> idList) {
		List<CommLoggerControl> delList = new java.util.ArrayList<CommLoggerControl>();
		for(java.math.BigInteger id:idList){
			CommLoggerControl commLoggerControl = new CommLoggerControl();
			commLoggerControl.setId(id);
			commLoggerControl.setSys0DelState(RecordState_DELETED);
			delList.add(commLoggerControl);
		}
		return sqlSession.update("commLoggerControlBase.delete_commLoggerControl_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(公共日志记录控制表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommLoggerControl(java.math.BigInteger id){
//		return sqlSession.delete("commLoggerControlBase.delete_commLoggerControl", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(公共日志记录控制表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommLoggerControlBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commLoggerControlBase.delete_commLoggerControl_Batch", idList);
//	}
	
	
}
