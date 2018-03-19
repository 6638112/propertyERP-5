package com.cnfantasia.server.domainbase.htCarGb.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.htCarGb.entity.HtCarGb;

/**
 * 描述:(华庭车禁小区表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class HtCarGbBaseDao extends AbstractBaseDao implements IHtCarGbBaseDao{
	/**
	 * 根据条件查询(华庭车禁小区表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<HtCarGb> selectHtCarGbByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("htCarGbBase.select_htCarGb",paramMap);
	}
	/**
	 * 按条件分页查询(华庭车禁小区表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<HtCarGb> selectHtCarGbByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectHtCarGbCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<HtCarGb> resMap= sqlSession.selectList("htCarGbBase.select_htCarGb_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(华庭车禁小区表)信息
	 * @param id
	 * @return
	 */
	@Override
	public HtCarGb selectHtCarGbBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("htCarGbBase.select_htCarGb_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(华庭车禁小区表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectHtCarGbCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("htCarGbBase.select_htCarGb_count", paramMap);
	}
	/**
	 * 往(华庭车禁小区表)新增一条记录
	 * @param htCarGb
	 * @return
	 */
	@Override
	public int insertHtCarGb(HtCarGb htCarGb){
		return sqlSession.insert("htCarGbBase.insert_htCarGb",htCarGb);
	}
	/**
	 * 批量新增(华庭车禁小区表)信息
	 * @param htCarGbList
	 * @return
	 */
	@Override
	public int insertHtCarGbBatch(List<HtCarGb> htCarGbList) {
		if (htCarGbList == null || htCarGbList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = htCarGbList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<HtCarGb> batchList = htCarGbList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("htCarGbBase.insert_htCarGb_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(华庭车禁小区表)信息
	 * @param htCarGb
	 * @return
	 */
	@Override
	public int updateHtCarGb(HtCarGb htCarGb){
		return sqlSession.update("htCarGbBase.update_htCarGb", htCarGb);
	}
	/**
	 * 批量更新(华庭车禁小区表)信息
	 * @param htCarGbList
	 * @return
	 */
	@Override
	public int updateHtCarGbBatch(List<HtCarGb> htCarGbList) {
		if (htCarGbList == null || htCarGbList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("htCarGbBase.update_htCarGb_Batch", htCarGbList);
	}
	
	/**
	 * 根据序列号删除(华庭车禁小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteHtCarGbLogic(java.math.BigInteger id){
		HtCarGb htCarGb = new HtCarGb();
		htCarGb.setId(id);
		htCarGb.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("htCarGbBase.delete_htCarGb_Logic",htCarGb);
	}
	
	/**
	 * 根据Id 批量删除(华庭车禁小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteHtCarGbLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<HtCarGb> delList = new java.util.ArrayList<HtCarGb>();
		for(java.math.BigInteger id:idList){
			HtCarGb htCarGb = new HtCarGb();
			htCarGb.setId(id);
			htCarGb.setSys0DelState(RecordState_DELETED);
			delList.add(htCarGb);
		}
		return sqlSession.update("htCarGbBase.delete_htCarGb_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(华庭车禁小区表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteHtCarGb(java.math.BigInteger id){
//		return sqlSession.delete("htCarGbBase.delete_htCarGb", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(华庭车禁小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteHtCarGbBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("htCarGbBase.delete_htCarGb_Batch", idList);
//	}
	
	
}
