package com.cnfantasia.server.domainbase.alterPeriodPrefer.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodPrefer.entity.AlterPeriodPrefer;

/**
 * 描述:(选择周期缴费优惠表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AlterPeriodPreferBaseDao extends AbstractBaseDao implements IAlterPeriodPreferBaseDao{
	/**
	 * 根据条件查询(选择周期缴费优惠表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AlterPeriodPrefer> selectAlterPeriodPreferByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("alterPeriodPreferBase.select_alterPeriodPrefer",paramMap);
	}
	/**
	 * 按条件分页查询(选择周期缴费优惠表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AlterPeriodPrefer> selectAlterPeriodPreferByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAlterPeriodPreferCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AlterPeriodPrefer> resMap= sqlSession.selectList("alterPeriodPreferBase.select_alterPeriodPrefer_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(选择周期缴费优惠表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterPeriodPrefer selectAlterPeriodPreferBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("alterPeriodPreferBase.select_alterPeriodPrefer_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(选择周期缴费优惠表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAlterPeriodPreferCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("alterPeriodPreferBase.select_alterPeriodPrefer_count", paramMap);
	}
	/**
	 * 往(选择周期缴费优惠表)新增一条记录
	 * @param alterPeriodPrefer
	 * @return
	 */
	@Override
	public int insertAlterPeriodPrefer(AlterPeriodPrefer alterPeriodPrefer){
		return sqlSession.insert("alterPeriodPreferBase.insert_alterPeriodPrefer",alterPeriodPrefer);
	}
	/**
	 * 批量新增(选择周期缴费优惠表)信息
	 * @param alterPeriodPreferList
	 * @return
	 */
	@Override
	public int insertAlterPeriodPreferBatch(List<AlterPeriodPrefer> alterPeriodPreferList) {
		return sqlSession.insert("alterPeriodPreferBase.insert_alterPeriodPrefer_Batch",alterPeriodPreferList);
	}
	
	/**
	 * 更新(选择周期缴费优惠表)信息
	 * @param alterPeriodPrefer
	 * @return
	 */
	@Override
	public int updateAlterPeriodPrefer(AlterPeriodPrefer alterPeriodPrefer){
		return sqlSession.update("alterPeriodPreferBase.update_alterPeriodPrefer", alterPeriodPrefer);
	}
	/**
	 * 批量更新(选择周期缴费优惠表)信息
	 * @param alterPeriodPreferList
	 * @return
	 */
	@Override
	public int updateAlterPeriodPreferBatch(List<AlterPeriodPrefer> alterPeriodPreferList) {
		return sqlSession.update("alterPeriodPreferBase.update_alterPeriodPrefer_Batch", alterPeriodPreferList);
	}
	
	/**
	 * 根据序列号删除(选择周期缴费优惠表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodPreferLogic(java.math.BigInteger id){
		AlterPeriodPrefer alterPeriodPrefer = new AlterPeriodPrefer();
		alterPeriodPrefer.setId(id);
		alterPeriodPrefer.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("alterPeriodPreferBase.delete_alterPeriodPrefer_Logic",alterPeriodPrefer);
	}
	
	/**
	 * 根据Id 批量删除(选择周期缴费优惠表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodPreferLogicBatch(List<java.math.BigInteger> idList) {
		List<AlterPeriodPrefer> delList = new java.util.ArrayList<AlterPeriodPrefer>();
		for(java.math.BigInteger id:idList){
			AlterPeriodPrefer alterPeriodPrefer = new AlterPeriodPrefer();
			alterPeriodPrefer.setId(id);
			alterPeriodPrefer.setSys0DelState(RecordState_DELETED);
			delList.add(alterPeriodPrefer);
		}
		return sqlSession.update("alterPeriodPreferBase.delete_alterPeriodPrefer_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(选择周期缴费优惠表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodPrefer(java.math.BigInteger id){
//		return sqlSession.delete("alterPeriodPreferBase.delete_alterPeriodPrefer", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(选择周期缴费优惠表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodPreferBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("alterPeriodPreferBase.delete_alterPeriodPrefer_Batch", idList);
//	}
	
	
}
