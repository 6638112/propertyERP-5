package com.cnfantasia.server.domainbase.carHpfCardsn.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.carHpfCardsn.entity.CarHpfCardsn;

/**
 * 描述:(华鹏飞临停车cardsn) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarHpfCardsnBaseService {
	
	/**
	 * 根据条件查询(华鹏飞临停车cardsn)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarHpfCardsn> getCarHpfCardsnByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(华鹏飞临停车cardsn)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CarHpfCardsn> getCarHpfCardsnByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(华鹏飞临停车cardsn)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarHpfCardsn> getCarHpfCardsnByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(华鹏飞临停车cardsn)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CarHpfCardsn> getCarHpfCardsnByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(华鹏飞临停车cardsn)信息
	 * @param id
	 * @return
	 */
	public CarHpfCardsn getCarHpfCardsnBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(华鹏飞临停车cardsn)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCarHpfCardsnCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(华鹏飞临停车cardsn)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCarHpfCardsnCountDim(Map<String,Object> paramMap);
	/**
	 * 往(华鹏飞临停车cardsn)新增一条记录
	 * @param carHpfCardsn
	 * @return
	 */
	public int insertCarHpfCardsn(CarHpfCardsn carHpfCardsn);
	/**
	 * 批量新增(华鹏飞临停车cardsn)
	 * @param carHpfCardsnList
	 * @return
	 */
	public int insertCarHpfCardsnBatch(List<CarHpfCardsn> carHpfCardsnList);
	/**
	 * 更新(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsn
	 * @return
	 */
	public int updateCarHpfCardsn(CarHpfCardsn carHpfCardsn);
	/**
	 * 批量更新(华鹏飞临停车cardsn)信息
	 * @param carHpfCardsnList
	 * @return
	 */
	public int updateCarHpfCardsnBatch(List<CarHpfCardsn> carHpfCardsnList);
	/**
	 * 根据序列号删除(华鹏飞临停车cardsn)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCarHpfCardsnLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(华鹏飞临停车cardsn)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCarHpfCardsnLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(华鹏飞临停车cardsn)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCarHpfCardsn(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(华鹏飞临停车cardsn)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCarHpfCardsnBatch(List<java.math.BigInteger> idList);
	
}
