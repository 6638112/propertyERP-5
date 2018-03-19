package com.cnfantasia.server.domainbase.fixedFeeItemRoomData.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.entity.FixedFeeItemRoomData;

/**
 * 描述:(房间数据信息表（已经导入数据的）) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFixedFeeItemRoomDataBaseService {
	
	/**
	 * 根据条件查询(房间数据信息表（已经导入数据的）)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(房间数据信息表（已经导入数据的）)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(房间数据信息表（已经导入数据的）)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(房间数据信息表（已经导入数据的）)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<FixedFeeItemRoomData> getFixedFeeItemRoomDataByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(房间数据信息表（已经导入数据的）)信息
	 * @param id
	 * @return
	 */
	FixedFeeItemRoomData getFixedFeeItemRoomDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间数据信息表（已经导入数据的）)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getFixedFeeItemRoomDataCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(房间数据信息表（已经导入数据的）)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getFixedFeeItemRoomDataCountDim(Map<String, Object> paramMap);
	/**
	 * 往(房间数据信息表（已经导入数据的）)新增一条记录
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	int insertFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData);
	/**
	 * 批量新增(房间数据信息表（已经导入数据的）)
	 * @param fixedFeeItemRoomDataList
	 * @return
	 */
	int insertFixedFeeItemRoomDataBatch(List<FixedFeeItemRoomData> fixedFeeItemRoomDataList);
	/**
	 * 更新(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	int updateFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData);
	/**
	 * 批量更新(房间数据信息表（已经导入数据的）)信息
	 * @param fixedFeeItemRoomDataList
	 * @return
	 */
	int updateFixedFeeItemRoomDataBatch(List<FixedFeeItemRoomData> fixedFeeItemRoomDataList);
	/**
	 * 根据序列号删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteFixedFeeItemRoomDataLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteFixedFeeItemRoomDataLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(房间数据信息表（已经导入数据的）)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFixedFeeItemRoomData(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(房间数据信息表（已经导入数据的）)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFixedFeeItemRoomDataBatch(List<java.math.BigInteger> idList);
	
}
