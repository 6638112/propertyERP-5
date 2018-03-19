package com.cnfantasia.server.domainbase.lanternFestivalPrize.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;

/**
 * 描述:(元宵节猜谜中奖公示表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILanternFestivalPrizeBaseService {
	
	/**
	 * 根据条件查询(元宵节猜谜中奖公示表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LanternFestivalPrize> getLanternFestivalPrizeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(元宵节猜谜中奖公示表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LanternFestivalPrize> getLanternFestivalPrizeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(元宵节猜谜中奖公示表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LanternFestivalPrize> getLanternFestivalPrizeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(元宵节猜谜中奖公示表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LanternFestivalPrize> getLanternFestivalPrizeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(元宵节猜谜中奖公示表)信息
	 * @param id
	 * @return
	 */
	public LanternFestivalPrize getLanternFestivalPrizeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(元宵节猜谜中奖公示表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLanternFestivalPrizeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(元宵节猜谜中奖公示表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLanternFestivalPrizeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(元宵节猜谜中奖公示表)新增一条记录
	 * @param lanternFestivalPrize
	 * @return
	 */
	public int insertLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize);
	/**
	 * 批量新增(元宵节猜谜中奖公示表)
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
	 * 根据序列号批量删除(元宵节猜谜中奖公示表)信息_逻辑删除
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
