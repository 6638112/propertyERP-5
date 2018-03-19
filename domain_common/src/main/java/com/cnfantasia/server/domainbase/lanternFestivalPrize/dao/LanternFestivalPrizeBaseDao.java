package com.cnfantasia.server.domainbase.lanternFestivalPrize.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;

/**
 * 描述:(元宵节猜谜中奖公示表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LanternFestivalPrizeBaseDao extends AbstractBaseDao implements ILanternFestivalPrizeBaseDao{
	/**
	 * 根据条件查询(元宵节猜谜中奖公示表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LanternFestivalPrize> selectLanternFestivalPrizeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("lanternFestivalPrizeBase.select_lanternFestivalPrize",paramMap);
	}
	/**
	 * 按条件分页查询(元宵节猜谜中奖公示表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LanternFestivalPrize> selectLanternFestivalPrizeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLanternFestivalPrizeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LanternFestivalPrize> resMap= sqlSession.selectList("lanternFestivalPrizeBase.select_lanternFestivalPrize_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(元宵节猜谜中奖公示表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LanternFestivalPrize selectLanternFestivalPrizeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("lanternFestivalPrizeBase.select_lanternFestivalPrize_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(元宵节猜谜中奖公示表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLanternFestivalPrizeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("lanternFestivalPrizeBase.select_lanternFestivalPrize_count", paramMap);
	}
	/**
	 * 往(元宵节猜谜中奖公示表)新增一条记录
	 * @param lanternFestivalPrize
	 * @return
	 */
	@Override
	public int insertLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize){
		return sqlSession.insert("lanternFestivalPrizeBase.insert_lanternFestivalPrize",lanternFestivalPrize);
	}
	/**
	 * 批量新增(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrizeList
	 * @return
	 */
	@Override
	public int insertLanternFestivalPrizeBatch(List<LanternFestivalPrize> lanternFestivalPrizeList) {
		if (lanternFestivalPrizeList == null || lanternFestivalPrizeList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = lanternFestivalPrizeList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LanternFestivalPrize> batchList = lanternFestivalPrizeList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("lanternFestivalPrizeBase.insert_lanternFestivalPrize_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrize
	 * @return
	 */
	@Override
	public int updateLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize){
		return sqlSession.update("lanternFestivalPrizeBase.update_lanternFestivalPrize", lanternFestivalPrize);
	}
	/**
	 * 批量更新(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrizeList
	 * @return
	 */
	@Override
	public int updateLanternFestivalPrizeBatch(List<LanternFestivalPrize> lanternFestivalPrizeList) {
		if (lanternFestivalPrizeList == null || lanternFestivalPrizeList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("lanternFestivalPrizeBase.update_lanternFestivalPrize_Batch", lanternFestivalPrizeList);
	}
	
	/**
	 * 根据序列号删除(元宵节猜谜中奖公示表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLanternFestivalPrizeLogic(java.math.BigInteger id){
		LanternFestivalPrize lanternFestivalPrize = new LanternFestivalPrize();
		lanternFestivalPrize.setId(id);
		lanternFestivalPrize.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("lanternFestivalPrizeBase.delete_lanternFestivalPrize_Logic",lanternFestivalPrize);
	}
	
	/**
	 * 根据Id 批量删除(元宵节猜谜中奖公示表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLanternFestivalPrizeLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LanternFestivalPrize> delList = new java.util.ArrayList<LanternFestivalPrize>();
		for(java.math.BigInteger id:idList){
			LanternFestivalPrize lanternFestivalPrize = new LanternFestivalPrize();
			lanternFestivalPrize.setId(id);
			lanternFestivalPrize.setSys0DelState(RecordState_DELETED);
			delList.add(lanternFestivalPrize);
		}
		return sqlSession.update("lanternFestivalPrizeBase.delete_lanternFestivalPrize_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(元宵节猜谜中奖公示表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLanternFestivalPrize(java.math.BigInteger id){
//		return sqlSession.delete("lanternFestivalPrizeBase.delete_lanternFestivalPrize", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(元宵节猜谜中奖公示表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLanternFestivalPrizeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("lanternFestivalPrizeBase.delete_lanternFestivalPrize_Batch", idList);
//	}
	
	
}
