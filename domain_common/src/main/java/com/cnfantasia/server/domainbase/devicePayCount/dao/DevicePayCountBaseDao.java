package com.cnfantasia.server.domainbase.devicePayCount.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.devicePayCount.entity.DevicePayCount;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DevicePayCountBaseDao extends AbstractBaseDao implements IDevicePayCountBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DevicePayCount> selectDevicePayCountByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("devicePayCountBase.select_devicePayCount",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DevicePayCount> selectDevicePayCountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDevicePayCountCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DevicePayCount> resMap= sqlSession.selectList("devicePayCountBase.select_devicePayCount_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public DevicePayCount selectDevicePayCountBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("devicePayCountBase.select_devicePayCount_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDevicePayCountCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("devicePayCountBase.select_devicePayCount_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param devicePayCount
	 * @return
	 */
	@Override
	public int insertDevicePayCount(DevicePayCount devicePayCount){
		return sqlSession.insert("devicePayCountBase.insert_devicePayCount",devicePayCount);
	}
	/**
	 * 批量新增()信息
	 * @param devicePayCountList
	 * @return
	 */
	@Override
	public int insertDevicePayCountBatch(List<DevicePayCount> devicePayCountList) {
		return sqlSession.insert("devicePayCountBase.insert_devicePayCount_Batch",devicePayCountList);
	}
	
	/**
	 * 更新()信息
	 * @param devicePayCount
	 * @return
	 */
	@Override
	public int updateDevicePayCount(DevicePayCount devicePayCount){
		return sqlSession.update("devicePayCountBase.update_devicePayCount", devicePayCount);
	}
	/**
	 * 批量更新()信息
	 * @param devicePayCountList
	 * @return
	 */
	@Override
	public int updateDevicePayCountBatch(List<DevicePayCount> devicePayCountList) {
		return sqlSession.update("devicePayCountBase.update_devicePayCount_Batch", devicePayCountList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDevicePayCountLogic(java.math.BigInteger id){
		DevicePayCount devicePayCount = new DevicePayCount();
		devicePayCount.setId(id);
		devicePayCount.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("devicePayCountBase.delete_devicePayCount_Logic",devicePayCount);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDevicePayCountLogicBatch(List<java.math.BigInteger> idList) {
		List<DevicePayCount> delList = new java.util.ArrayList<DevicePayCount>();
		for(java.math.BigInteger id:idList){
			DevicePayCount devicePayCount = new DevicePayCount();
			devicePayCount.setId(id);
			devicePayCount.setSys0DelState(RecordState_DELETED);
			delList.add(devicePayCount);
		}
		return sqlSession.update("devicePayCountBase.delete_devicePayCount_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDevicePayCount(java.math.BigInteger id){
//		return sqlSession.delete("devicePayCountBase.delete_devicePayCount", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDevicePayCountBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("devicePayCountBase.delete_devicePayCount_Batch", idList);
//	}
	
	
}
