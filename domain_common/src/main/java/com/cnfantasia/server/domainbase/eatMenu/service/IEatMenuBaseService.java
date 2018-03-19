package com.cnfantasia.server.domainbase.eatMenu.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.eatMenu.entity.EatMenu;

/**
 * 描述:(今晚吃什么的菜谱) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEatMenuBaseService {
	
	/**
	 * 根据条件查询(今晚吃什么的菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EatMenu> getEatMenuByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(今晚吃什么的菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EatMenu> getEatMenuByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(今晚吃什么的菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EatMenu> getEatMenuByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(今晚吃什么的菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EatMenu> getEatMenuByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(今晚吃什么的菜谱)信息
	 * @param id
	 * @return
	 */
	public EatMenu getEatMenuBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(今晚吃什么的菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEatMenuCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(今晚吃什么的菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEatMenuCountDim(Map<String,Object> paramMap);
	/**
	 * 往(今晚吃什么的菜谱)新增一条记录
	 * @param eatMenu
	 * @return
	 */
	public int insertEatMenu(EatMenu eatMenu);
	/**
	 * 批量新增(今晚吃什么的菜谱)
	 * @param eatMenuList
	 * @return
	 */
	public int insertEatMenuBatch(List<EatMenu> eatMenuList);
	/**
	 * 更新(今晚吃什么的菜谱)信息
	 * @param eatMenu
	 * @return
	 */
	public int updateEatMenu(EatMenu eatMenu);
	/**
	 * 批量更新(今晚吃什么的菜谱)信息
	 * @param eatMenuList
	 * @return
	 */
	public int updateEatMenuBatch(List<EatMenu> eatMenuList);
	/**
	 * 根据序列号删除(今晚吃什么的菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEatMenuLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(今晚吃什么的菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEatMenuLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(今晚吃什么的菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEatMenu(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(今晚吃什么的菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEatMenuBatch(List<java.math.BigInteger> idList);
	
}
