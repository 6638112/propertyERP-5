package com.cnfantasia.server.domainbase.fixedFeeItemRoomData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.entity.FixedFeeItemRoomData;

/**
 * 描述:(房间数据信息表（已经导入数据的）) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FixedFeeItemRoomDataBaseDao extends AbstractBaseDao implements IFixedFeeItemRoomDataBaseDao{
	/**
	 * 根据条件查询(房间数据信息表（已经导入数据的）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FixedFeeItemRoomData> selectFixedFeeItemRoomDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("fixedFeeItemRoomDataBase.select_fixedFeeItemRoomData",paramMap);
	}
	/**
	 * 按条件分页查询(房间数据信息表（已经导入数据的）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FixedFeeItemRoomData> selectFixedFeeItemRoomDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFixedFeeItemRoomDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FixedFeeItemRoomData> resMap= sqlSession.selectList("fixedFeeItemRoomDataBase.select_fixedFeeItemRoomData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(房间数据信息表（已经导入数据的）)信息
	 * @param id
	 * @return
	 */
	@Override
	public FixedFeeItemRoomData selectFixedFeeItemRoomDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("fixedFeeItemRoomDataBase.select_fixedFeeItemRoomData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(房间数据信息表（已经导入数据的）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFixedFeeItemRoomDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("fixedFeeItemRoomDataBase.select_fixedFeeItemRoomData_count", paramMap);
	}
	/**
	 * 往(房间数据信息表（已经导入数据的）)新增一条记录
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	@Override
	public int insertFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData){
		return sqlSession.insert("fixedFeeItemRoomDataBase.insert_fixedFeeItemRoomData",fixedFeeItemRoomData);
	}
	/**
	 * 批量新增(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomDataList
	 * @return
	 */
	@Override
	public int insertFixedFeeItemRoomDataBatch(List<FixedFeeItemRoomData> fixedFeeItemRoomDataList) {
		if (fixedFeeItemRoomDataList == null || fixedFeeItemRoomDataList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = fixedFeeItemRoomDataList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<FixedFeeItemRoomData> batchList = fixedFeeItemRoomDataList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("fixedFeeItemRoomDataBase.insert_fixedFeeItemRoomData_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	@Override
	public int updateFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData){
		return sqlSession.update("fixedFeeItemRoomDataBase.update_fixedFeeItemRoomData", fixedFeeItemRoomData);
	}
	/**
	 * 批量更新(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomDataList
	 * @return
	 */
	@Override
	public int updateFixedFeeItemRoomDataBatch(List<FixedFeeItemRoomData> fixedFeeItemRoomDataList) {
		if (fixedFeeItemRoomDataList == null || fixedFeeItemRoomDataList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("fixedFeeItemRoomDataBase.update_fixedFeeItemRoomData_Batch", fixedFeeItemRoomDataList);
	}
	
	/**
	 * 根据序列号删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemRoomDataLogic(java.math.BigInteger id){
		FixedFeeItemRoomData fixedFeeItemRoomData = new FixedFeeItemRoomData();
		fixedFeeItemRoomData.setId(id);
		fixedFeeItemRoomData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("fixedFeeItemRoomDataBase.delete_fixedFeeItemRoomData_Logic",fixedFeeItemRoomData);
	}
	
	/**
	 * 根据Id 批量删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemRoomDataLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<FixedFeeItemRoomData> delList = new java.util.ArrayList<FixedFeeItemRoomData>();
		for(java.math.BigInteger id:idList){
			FixedFeeItemRoomData fixedFeeItemRoomData = new FixedFeeItemRoomData();
			fixedFeeItemRoomData.setId(id);
			fixedFeeItemRoomData.setSys0DelState(RecordState_DELETED);
			delList.add(fixedFeeItemRoomData);
		}
		return sqlSession.update("fixedFeeItemRoomDataBase.delete_fixedFeeItemRoomData_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(房间数据信息表（已经导入数据的）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemRoomData(java.math.BigInteger id){
//		return sqlSession.delete("fixedFeeItemRoomDataBase.delete_fixedFeeItemRoomData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间数据信息表（已经导入数据的）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemRoomDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("fixedFeeItemRoomDataBase.delete_fixedFeeItemRoomData_Batch", idList);
//	}
	
	
}
