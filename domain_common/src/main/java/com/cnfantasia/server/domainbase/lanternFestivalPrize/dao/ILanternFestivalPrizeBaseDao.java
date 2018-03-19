package com.cnfantasia.server.domainbase.lanternFestivalPrize.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;

/**
 * 描述:(元宵节猜谜中奖公示表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILanternFestivalPrizeBaseDao {
	/**
	 * 根据条件查询(元宵节猜谜中奖公示表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LanternFestivalPrize> selectLanternFestivalPrizeByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(元宵节猜谜中奖公示表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LanternFestivalPrize> selectLanternFestivalPrizeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(元宵节猜谜中奖公示表)信息
	 * @param id
	 * @return
	 */
	public LanternFestivalPrize selectLanternFestivalPrizeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(元宵节猜谜中奖公示表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLanternFestivalPrizeCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(元宵节猜谜中奖公示表)新增一条记录
	 * @param lanternFestivalPrize
	 * @return
	 */
	public int insertLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize);
	
	/**
	 * 批量新增(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrizeList
	 * @return
	 */
	public int insertLanternFestivalPrizeBatch(List<LanternFestivalPrize> lanternFestivalPrizeList);
	
	/**
	 * 更新(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrize
	 * @return
	 */
	public int updateLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize);
	
	/**
	 * 批量更新(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrizeList
	 * @return
	 */
	public int updateLanternFestivalPrizeBatch(List<LanternFestivalPrize> lanternFestivalPrizeList);
	
	/**
	 * 根据序列号删除(元宵节猜谜中奖公示表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLanternFestivalPrizeLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(元宵节猜谜中奖公示表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLanternFestivalPrizeLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(元宵节猜谜中奖公示表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLanternFestivalPrize(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(元宵节猜谜中奖公示表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLanternFestivalPrizeBatch(List<java.math.BigInteger> idList);
	
	
}
