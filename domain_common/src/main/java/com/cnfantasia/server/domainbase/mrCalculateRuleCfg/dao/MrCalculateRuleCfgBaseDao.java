package com.cnfantasia.server.domainbase.mrCalculateRuleCfg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrCalculateRuleCfg.entity.MrCalculateRuleCfg;

/**
 * 描述:(抄表计算规则配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MrCalculateRuleCfgBaseDao extends AbstractBaseDao implements IMrCalculateRuleCfgBaseDao{
	/**
	 * 根据条件查询(抄表计算规则配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrCalculateRuleCfg> selectMrCalculateRuleCfgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("mrCalculateRuleCfgBase.select_mrCalculateRuleCfg",paramMap);
	}
	/**
	 * 按条件分页查询(抄表计算规则配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrCalculateRuleCfg> selectMrCalculateRuleCfgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMrCalculateRuleCfgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MrCalculateRuleCfg> resMap= sqlSession.selectList("mrCalculateRuleCfgBase.select_mrCalculateRuleCfg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抄表计算规则配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrCalculateRuleCfg selectMrCalculateRuleCfgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("mrCalculateRuleCfgBase.select_mrCalculateRuleCfg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抄表计算规则配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMrCalculateRuleCfgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("mrCalculateRuleCfgBase.select_mrCalculateRuleCfg_count", paramMap);
	}
	/**
	 * 往(抄表计算规则配置)新增一条记录
	 * @param mrCalculateRuleCfg
	 * @return
	 */
	@Override
	public int insertMrCalculateRuleCfg(MrCalculateRuleCfg mrCalculateRuleCfg){
		return sqlSession.insert("mrCalculateRuleCfgBase.insert_mrCalculateRuleCfg",mrCalculateRuleCfg);
	}
	/**
	 * 批量新增(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfgList
	 * @return
	 */
	@Override
	public int insertMrCalculateRuleCfgBatch(List<MrCalculateRuleCfg> mrCalculateRuleCfgList) {
		if (mrCalculateRuleCfgList == null || mrCalculateRuleCfgList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = mrCalculateRuleCfgList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<MrCalculateRuleCfg> batchList = mrCalculateRuleCfgList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("mrCalculateRuleCfgBase.insert_mrCalculateRuleCfg_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfg
	 * @return
	 */
	@Override
	public int updateMrCalculateRuleCfg(MrCalculateRuleCfg mrCalculateRuleCfg){
		return sqlSession.update("mrCalculateRuleCfgBase.update_mrCalculateRuleCfg", mrCalculateRuleCfg);
	}
	/**
	 * 批量更新(抄表计算规则配置)信息
	 * @param mrCalculateRuleCfgList
	 * @return
	 */
	@Override
	public int updateMrCalculateRuleCfgBatch(List<MrCalculateRuleCfg> mrCalculateRuleCfgList) {
		if (mrCalculateRuleCfgList == null || mrCalculateRuleCfgList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("mrCalculateRuleCfgBase.update_mrCalculateRuleCfg_Batch", mrCalculateRuleCfgList);
	}
	
	/**
	 * 根据序列号删除(抄表计算规则配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrCalculateRuleCfgLogic(java.math.BigInteger id){
		MrCalculateRuleCfg mrCalculateRuleCfg = new MrCalculateRuleCfg();
		mrCalculateRuleCfg.setId(id);
		mrCalculateRuleCfg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("mrCalculateRuleCfgBase.delete_mrCalculateRuleCfg_Logic",mrCalculateRuleCfg);
	}
	
	/**
	 * 根据Id 批量删除(抄表计算规则配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrCalculateRuleCfgLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<MrCalculateRuleCfg> delList = new java.util.ArrayList<MrCalculateRuleCfg>();
		for(java.math.BigInteger id:idList){
			MrCalculateRuleCfg mrCalculateRuleCfg = new MrCalculateRuleCfg();
			mrCalculateRuleCfg.setId(id);
			mrCalculateRuleCfg.setSys0DelState(RecordState_DELETED);
			delList.add(mrCalculateRuleCfg);
		}
		return sqlSession.update("mrCalculateRuleCfgBase.delete_mrCalculateRuleCfg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抄表计算规则配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrCalculateRuleCfg(java.math.BigInteger id){
//		return sqlSession.delete("mrCalculateRuleCfgBase.delete_mrCalculateRuleCfg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表计算规则配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrCalculateRuleCfgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("mrCalculateRuleCfgBase.delete_mrCalculateRuleCfg_Batch", idList);
//	}
	
	
}
