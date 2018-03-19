package com.cnfantasia.server.domainbase.carHpfCardsn.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carHpfCardsn.entity.CarHpfCardsn;

/**
 * 描述:(华鹏飞临停车cardsn) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CarHpfCardsnBaseDao extends AbstractBaseDao implements ICarHpfCardsnBaseDao{
	/**
	 * 根据条件查询(华鹏飞临停车cardsn)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarHpfCardsn> selectCarHpfCardsnByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("carHpfCardsnBase.select_carHpfCardsn",paramMap);
	}
	/**
	 * 按条件分页查询(华鹏飞临停车cardsn)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CarHpfCardsn> selectCarHpfCardsnByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCarHpfCardsnCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CarHpfCardsn> resMap= sqlSession.selectList("carHpfCardsnBase.select_carHpfCardsn_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(华鹏飞临停车cardsn)信息
	 * @param id
	 * @return
	 */
	@Override
	public CarHpfCardsn selectCarHpfCardsnBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("carHpfCardsnBase.select_carHpfCardsn_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(华鹏飞临停车cardsn)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCarHpfCardsnCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("carHpfCardsnBase.select_carHpfCardsn_count", paramMap);
	}
	/**
	 * 往(华鹏飞临停车cardsn)新增一条记录
	 * @param carHpfCardsn
	 * @return
	 */
	@Override
	public int insertCarHpfCardsn(CarHpfCardsn carHpfCardsn){
		return sqlSession.insert("carHpfCardsnBase.insert_carHpfCardsn",carHpfCardsn);
	}
	/**
	 * 批量新增(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsnList
	 * @return
	 */
	@Override
	public int insertCarHpfCardsnBatch(List<CarHpfCardsn> carHpfCardsnList) {
		if (carHpfCardsnList == null || carHpfCardsnList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = carHpfCardsnList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<CarHpfCardsn> batchList = carHpfCardsnList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("carHpfCardsnBase.insert_carHpfCardsn_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsn
	 * @return
	 */
	@Override
	public int updateCarHpfCardsn(CarHpfCardsn carHpfCardsn){
		return sqlSession.update("carHpfCardsnBase.update_carHpfCardsn", carHpfCardsn);
	}
	/**
	 * 批量更新(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsnList
	 * @return
	 */
	@Override
	public int updateCarHpfCardsnBatch(List<CarHpfCardsn> carHpfCardsnList) {
		if (carHpfCardsnList == null || carHpfCardsnList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("carHpfCardsnBase.update_carHpfCardsn_Batch", carHpfCardsnList);
	}
	
	/**
	 * 根据序列号删除(华鹏飞临停车cardsn)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCarHpfCardsnLogic(java.math.BigInteger id){
		CarHpfCardsn carHpfCardsn = new CarHpfCardsn();
		carHpfCardsn.setId(id);
		carHpfCardsn.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("carHpfCardsnBase.delete_carHpfCardsn_Logic",carHpfCardsn);
	}
	
	/**
	 * 根据Id 批量删除(华鹏飞临停车cardsn)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCarHpfCardsnLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<CarHpfCardsn> delList = new java.util.ArrayList<CarHpfCardsn>();
		for(java.math.BigInteger id:idList){
			CarHpfCardsn carHpfCardsn = new CarHpfCardsn();
			carHpfCardsn.setId(id);
			carHpfCardsn.setSys0DelState(RecordState_DELETED);
			delList.add(carHpfCardsn);
		}
		return sqlSession.update("carHpfCardsnBase.delete_carHpfCardsn_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(华鹏飞临停车cardsn)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCarHpfCardsn(java.math.BigInteger id){
//		return sqlSession.delete("carHpfCardsnBase.delete_carHpfCardsn", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(华鹏飞临停车cardsn)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCarHpfCardsnBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("carHpfCardsnBase.delete_carHpfCardsn_Batch", idList);
//	}
	
	
}
