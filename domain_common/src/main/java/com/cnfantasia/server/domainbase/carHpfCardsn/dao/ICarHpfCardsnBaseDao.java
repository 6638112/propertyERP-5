package com.cnfantasia.server.domainbase.carHpfCardsn.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.carHpfCardsn.entity.CarHpfCardsn;

/**
 * 描述:(华鹏飞临停车cardsn) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICarHpfCardsnBaseDao {
	/**
	 * 根据条件查询(华鹏飞临停车cardsn)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarHpfCardsn> selectCarHpfCardsnByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(华鹏飞临停车cardsn)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CarHpfCardsn> selectCarHpfCardsnByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(华鹏飞临停车cardsn)信息
	 * @param id
	 * @return
	 */
	public CarHpfCardsn selectCarHpfCardsnBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(华鹏飞临停车cardsn)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCarHpfCardsnCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(华鹏飞临停车cardsn)新增一条记录
	 * @param carHpfCardsn
	 * @return
	 */
	public int insertCarHpfCardsn(CarHpfCardsn carHpfCardsn);
	
	/**
	 * 批量新增(华鹏飞临停车cardsn)信息
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
	 * 根据Id 批量删除(华鹏飞临停车cardsn)信息_逻辑删除
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
