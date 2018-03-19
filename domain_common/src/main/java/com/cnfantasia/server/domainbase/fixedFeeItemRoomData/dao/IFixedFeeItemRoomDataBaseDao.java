package com.cnfantasia.server.domainbase.fixedFeeItemRoomData.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItemRoomData.entity.FixedFeeItemRoomData;

/**
 * 描述:(房间数据信息表（已经导入数据的）) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFixedFeeItemRoomDataBaseDao {
	/**
	 * 根据条件查询(房间数据信息表（已经导入数据的）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<FixedFeeItemRoomData> selectFixedFeeItemRoomDataByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(房间数据信息表（已经导入数据的）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<FixedFeeItemRoomData> selectFixedFeeItemRoomDataByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(房间数据信息表（已经导入数据的）)信息
	 * @param id
	 * @return
	 */
	FixedFeeItemRoomData selectFixedFeeItemRoomDataBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(房间数据信息表（已经导入数据的）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectFixedFeeItemRoomDataCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(房间数据信息表（已经导入数据的）)新增一条记录
	 * @param fixedFeeItemRoomData
	 * @return
	 */
	int insertFixedFeeItemRoomData(FixedFeeItemRoomData fixedFeeItemRoomData);
	
	/**
	 * 批量新增(房间数据信息表（已经导入数据的）)信息
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
	 * 根据Id 批量删除(房间数据信息表（已经导入数据的）)信息_逻辑删除
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
