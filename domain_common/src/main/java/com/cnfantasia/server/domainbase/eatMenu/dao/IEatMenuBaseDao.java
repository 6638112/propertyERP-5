package com.cnfantasia.server.domainbase.eatMenu.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.eatMenu.entity.EatMenu;

/**
 * 描述:(今晚吃什么的菜谱) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEatMenuBaseDao {
	/**
	 * 根据条件查询(今晚吃什么的菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EatMenu> selectEatMenuByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(今晚吃什么的菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EatMenu> selectEatMenuByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(今晚吃什么的菜谱)信息
	 * @param id
	 * @return
	 */
	public EatMenu selectEatMenuBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(今晚吃什么的菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEatMenuCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(今晚吃什么的菜谱)新增一条记录
	 * @param eatMenu
	 * @return
	 */
	public int insertEatMenu(EatMenu eatMenu);
	
	/**
	 * 批量新增(今晚吃什么的菜谱)信息
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
	 * 根据Id 批量删除(今晚吃什么的菜谱)信息_逻辑删除
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
