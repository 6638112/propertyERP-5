package com.cnfantasia.server.domainbase.wechatDredgebillMq.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.wechatDredgebillMq.entity.WechatDredgebillMq;

/**
 * 描述:(微信公众号维修单消息队列) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class WechatDredgebillMqBaseDao extends AbstractBaseDao implements IWechatDredgebillMqBaseDao{
	/**
	 * 根据条件查询(微信公众号维修单消息队列)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<WechatDredgebillMq> selectWechatDredgebillMqByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("wechatDredgebillMqBase.select_wechatDredgebillMq",paramMap);
	}
	/**
	 * 按条件分页查询(微信公众号维修单消息队列)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<WechatDredgebillMq> selectWechatDredgebillMqByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectWechatDredgebillMqCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<WechatDredgebillMq> resMap= sqlSession.selectList("wechatDredgebillMqBase.select_wechatDredgebillMq_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微信公众号维修单消息队列)信息
	 * @param id
	 * @return
	 */
	@Override
	public WechatDredgebillMq selectWechatDredgebillMqBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("wechatDredgebillMqBase.select_wechatDredgebillMq_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微信公众号维修单消息队列)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectWechatDredgebillMqCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("wechatDredgebillMqBase.select_wechatDredgebillMq_count", paramMap);
	}
	/**
	 * 往(微信公众号维修单消息队列)新增一条记录
	 * @param wechatDredgebillMq
	 * @return
	 */
	@Override
	public int insertWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq){
		return sqlSession.insert("wechatDredgebillMqBase.insert_wechatDredgebillMq",wechatDredgebillMq);
	}
	/**
	 * 批量新增(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMqList
	 * @return
	 */
	@Override
	public int insertWechatDredgebillMqBatch(List<WechatDredgebillMq> wechatDredgebillMqList) {
		return sqlSession.insert("wechatDredgebillMqBase.insert_wechatDredgebillMq_Batch",wechatDredgebillMqList);
	}
	
	/**
	 * 更新(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMq
	 * @return
	 */
	@Override
	public int updateWechatDredgebillMq(WechatDredgebillMq wechatDredgebillMq){
		return sqlSession.update("wechatDredgebillMqBase.update_wechatDredgebillMq", wechatDredgebillMq);
	}
	/**
	 * 批量更新(微信公众号维修单消息队列)信息
	 * @param wechatDredgebillMqList
	 * @return
	 */
	@Override
	public int updateWechatDredgebillMqBatch(List<WechatDredgebillMq> wechatDredgebillMqList) {
		return sqlSession.update("wechatDredgebillMqBase.update_wechatDredgebillMq_Batch", wechatDredgebillMqList);
	}
	
	/**
	 * 根据序列号删除(微信公众号维修单消息队列)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteWechatDredgebillMqLogic(java.math.BigInteger id){
		WechatDredgebillMq wechatDredgebillMq = new WechatDredgebillMq();
		wechatDredgebillMq.setId(id);
		wechatDredgebillMq.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("wechatDredgebillMqBase.delete_wechatDredgebillMq_Logic",wechatDredgebillMq);
	}
	
	/**
	 * 根据Id 批量删除(微信公众号维修单消息队列)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteWechatDredgebillMqLogicBatch(List<java.math.BigInteger> idList) {
		List<WechatDredgebillMq> delList = new java.util.ArrayList<WechatDredgebillMq>();
		for(java.math.BigInteger id:idList){
			WechatDredgebillMq wechatDredgebillMq = new WechatDredgebillMq();
			wechatDredgebillMq.setId(id);
			wechatDredgebillMq.setSys0DelState(RecordState_DELETED);
			delList.add(wechatDredgebillMq);
		}
		return sqlSession.update("wechatDredgebillMqBase.delete_wechatDredgebillMq_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微信公众号维修单消息队列)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteWechatDredgebillMq(java.math.BigInteger id){
//		return sqlSession.delete("wechatDredgebillMqBase.delete_wechatDredgebillMq", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微信公众号维修单消息队列)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteWechatDredgebillMqBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("wechatDredgebillMqBase.delete_wechatDredgebillMq_Batch", idList);
//	}
	
	
}
