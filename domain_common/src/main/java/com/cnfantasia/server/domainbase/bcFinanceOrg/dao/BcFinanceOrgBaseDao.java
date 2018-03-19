package com.cnfantasia.server.domainbase.bcFinanceOrg.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;

/**
 * 描述:(银行托收金融机构信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcFinanceOrgBaseDao extends AbstractBaseDao implements IBcFinanceOrgBaseDao{
	/**
	 * 根据条件查询(银行托收金融机构信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcFinanceOrg> selectBcFinanceOrgByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcFinanceOrgBase.select_bcFinanceOrg",paramMap);
	}
	/**
	 * 按条件分页查询(银行托收金融机构信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcFinanceOrg> selectBcFinanceOrgByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcFinanceOrgCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcFinanceOrg> resMap= sqlSession.selectList("bcFinanceOrgBase.select_bcFinanceOrg_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(银行托收金融机构信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcFinanceOrg selectBcFinanceOrgBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcFinanceOrgBase.select_bcFinanceOrg_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(银行托收金融机构信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcFinanceOrgCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcFinanceOrgBase.select_bcFinanceOrg_count", paramMap);
	}
	/**
	 * 往(银行托收金融机构信息)新增一条记录
	 * @param bcFinanceOrg
	 * @return
	 */
	@Override
	public int insertBcFinanceOrg(BcFinanceOrg bcFinanceOrg){
		return sqlSession.insert("bcFinanceOrgBase.insert_bcFinanceOrg",bcFinanceOrg);
	}
	/**
	 * 批量新增(银行托收金融机构信息)信息
	 * @param bcFinanceOrgList
	 * @return
	 */
	@Override
	public int insertBcFinanceOrgBatch(List<BcFinanceOrg> bcFinanceOrgList) {
		if (bcFinanceOrgList == null || bcFinanceOrgList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcFinanceOrgList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcFinanceOrg> batchList = bcFinanceOrgList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcFinanceOrgBase.insert_bcFinanceOrg_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(银行托收金融机构信息)信息
	 * @param bcFinanceOrg
	 * @return
	 */
	@Override
	public int updateBcFinanceOrg(BcFinanceOrg bcFinanceOrg){
		return sqlSession.update("bcFinanceOrgBase.update_bcFinanceOrg", bcFinanceOrg);
	}
	/**
	 * 批量更新(银行托收金融机构信息)信息
	 * @param bcFinanceOrgList
	 * @return
	 */
	@Override
	public int updateBcFinanceOrgBatch(List<BcFinanceOrg> bcFinanceOrgList) {
		if (bcFinanceOrgList == null || bcFinanceOrgList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcFinanceOrgBase.update_bcFinanceOrg_Batch", bcFinanceOrgList);
	}
	
	/**
	 * 根据序列号删除(银行托收金融机构信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcFinanceOrgLogic(java.math.BigInteger id){
		BcFinanceOrg bcFinanceOrg = new BcFinanceOrg();
		bcFinanceOrg.setId(id);
		bcFinanceOrg.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcFinanceOrgBase.delete_bcFinanceOrg_Logic",bcFinanceOrg);
	}
	
	/**
	 * 根据Id 批量删除(银行托收金融机构信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcFinanceOrgLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcFinanceOrg> delList = new java.util.ArrayList<BcFinanceOrg>();
		for(java.math.BigInteger id:idList){
			BcFinanceOrg bcFinanceOrg = new BcFinanceOrg();
			bcFinanceOrg.setId(id);
			bcFinanceOrg.setSys0DelState(RecordState_DELETED);
			delList.add(bcFinanceOrg);
		}
		return sqlSession.update("bcFinanceOrgBase.delete_bcFinanceOrg_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(银行托收金融机构信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcFinanceOrg(java.math.BigInteger id){
//		return sqlSession.delete("bcFinanceOrgBase.delete_bcFinanceOrg", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(银行托收金融机构信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcFinanceOrgBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcFinanceOrgBase.delete_bcFinanceOrg_Batch", idList);
//	}
	
	
}
