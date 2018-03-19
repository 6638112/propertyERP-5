package com.cnfantasia.server.domainbase.msPrizeOptionCity.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeOptionCity.entity.MsPrizeOptionCity;

/**
 * 描述:(奖项城市关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MsPrizeOptionCityBaseDao extends AbstractBaseDao implements IMsPrizeOptionCityBaseDao{
	/**
	 * 根据条件查询(奖项城市关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeOptionCity> selectMsPrizeOptionCityByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("msPrizeOptionCityBase.select_msPrizeOptionCity",paramMap);
	}
	/**
	 * 按条件分页查询(奖项城市关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeOptionCity> selectMsPrizeOptionCityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMsPrizeOptionCityCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MsPrizeOptionCity> resMap= sqlSession.selectList("msPrizeOptionCityBase.select_msPrizeOptionCity_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(奖项城市关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeOptionCity selectMsPrizeOptionCityBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("msPrizeOptionCityBase.select_msPrizeOptionCity_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(奖项城市关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMsPrizeOptionCityCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("msPrizeOptionCityBase.select_msPrizeOptionCity_count", paramMap);
	}
	/**
	 * 往(奖项城市关系表)新增一条记录
	 * @param msPrizeOptionCity
	 * @return
	 */
	@Override
	public int insertMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity){
		return sqlSession.insert("msPrizeOptionCityBase.insert_msPrizeOptionCity",msPrizeOptionCity);
	}
	/**
	 * 批量新增(奖项城市关系表)信息
	 * @param msPrizeOptionCityList
	 * @return
	 */
	@Override
	public int insertMsPrizeOptionCityBatch(List<MsPrizeOptionCity> msPrizeOptionCityList) {
		return sqlSession.insert("msPrizeOptionCityBase.insert_msPrizeOptionCity_Batch",msPrizeOptionCityList);
	}
	
	/**
	 * 更新(奖项城市关系表)信息
	 * @param msPrizeOptionCity
	 * @return
	 */
	@Override
	public int updateMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity){
		return sqlSession.update("msPrizeOptionCityBase.update_msPrizeOptionCity", msPrizeOptionCity);
	}
	/**
	 * 批量更新(奖项城市关系表)信息
	 * @param msPrizeOptionCityList
	 * @return
	 */
	@Override
	public int updateMsPrizeOptionCityBatch(List<MsPrizeOptionCity> msPrizeOptionCityList) {
		return sqlSession.update("msPrizeOptionCityBase.update_msPrizeOptionCity_Batch", msPrizeOptionCityList);
	}
	
	/**
	 * 根据序列号删除(奖项城市关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionCityLogic(java.math.BigInteger id){
		MsPrizeOptionCity msPrizeOptionCity = new MsPrizeOptionCity();
		msPrizeOptionCity.setId(id);
		msPrizeOptionCity.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("msPrizeOptionCityBase.delete_msPrizeOptionCity_Logic",msPrizeOptionCity);
	}
	
	/**
	 * 根据Id 批量删除(奖项城市关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionCityLogicBatch(List<java.math.BigInteger> idList) {
		List<MsPrizeOptionCity> delList = new java.util.ArrayList<MsPrizeOptionCity>();
		for(java.math.BigInteger id:idList){
			MsPrizeOptionCity msPrizeOptionCity = new MsPrizeOptionCity();
			msPrizeOptionCity.setId(id);
			msPrizeOptionCity.setSys0DelState(RecordState_DELETED);
			delList.add(msPrizeOptionCity);
		}
		return sqlSession.update("msPrizeOptionCityBase.delete_msPrizeOptionCity_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(奖项城市关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOptionCity(java.math.BigInteger id){
//		return sqlSession.delete("msPrizeOptionCityBase.delete_msPrizeOptionCity", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(奖项城市关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOptionCityBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("msPrizeOptionCityBase.delete_msPrizeOptionCity_Batch", idList);
//	}
	
	
}
