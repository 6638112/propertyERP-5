package com.cnfantasia.server.domainbase.eatMenu.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.eatMenu.dao.IEatMenuBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.eatMenu.entity.EatMenu;

/**
 * 描述:(今晚吃什么的菜谱) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EatMenuBaseService implements IEatMenuBaseService{
	
	private IEatMenuBaseDao eatMenuBaseDao;
	public void setEatMenuBaseDao(IEatMenuBaseDao eatMenuBaseDao) {
		this.eatMenuBaseDao = eatMenuBaseDao;
	}
	/**
	 * 根据条件查询(今晚吃什么的菜谱)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EatMenu> getEatMenuByCondition(Map<String,Object> paramMap){
		return eatMenuBaseDao.selectEatMenuByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(今晚吃什么的菜谱)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EatMenu> getEatMenuByConditionDim(Map<String,Object> paramMap){
		return eatMenuBaseDao.selectEatMenuByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(今晚吃什么的菜谱)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EatMenu> getEatMenuByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return eatMenuBaseDao.selectEatMenuByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(今晚吃什么的菜谱)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EatMenu> getEatMenuByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return eatMenuBaseDao.selectEatMenuByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(今晚吃什么的菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public EatMenu getEatMenuBySeqId(java.math.BigInteger id){
		return eatMenuBaseDao.selectEatMenuBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(今晚吃什么的菜谱)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEatMenuCount(Map<String,Object> paramMap){
		return eatMenuBaseDao.selectEatMenuCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(今晚吃什么的菜谱)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEatMenuCountDim(Map<String,Object> paramMap){
		return eatMenuBaseDao.selectEatMenuCount(paramMap,true);
	}
	/**
	 * 往(今晚吃什么的菜谱)新增一条记录
	 * @param eatMenu
	 * @return
	 */
	@Override
	public int insertEatMenu(EatMenu eatMenu){
		return eatMenuBaseDao.insertEatMenu(eatMenu);
	}
	/**
	 * 批量新增(今晚吃什么的菜谱)
	 * @param eatMenuList
	 * @return
	 */
	@Override
	public int insertEatMenuBatch(List<EatMenu> eatMenuList){
		return eatMenuBaseDao.insertEatMenuBatch(eatMenuList);
	}
	/**
	 * 更新(今晚吃什么的菜谱)信息
	 * @param eatMenu
	 * @return
	 */
	@Override
	public int updateEatMenu(EatMenu eatMenu){
		return eatMenuBaseDao.updateEatMenu(eatMenu);
	}
	/**
	 * 批量更新(今晚吃什么的菜谱)信息
	 * @param eatMenuList
	 * @return
	 */
	@Override
	public int updateEatMenuBatch(List<EatMenu> eatMenuList){
		return eatMenuBaseDao.updateEatMenuBatch(eatMenuList);
	}
	/**
	 * 根据序列号删除(今晚吃什么的菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEatMenuLogic(java.math.BigInteger id){
		return eatMenuBaseDao.deleteEatMenuLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(今晚吃什么的菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEatMenuLogicBatch(List<java.math.BigInteger> idList){
		return eatMenuBaseDao.deleteEatMenuLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(今晚吃什么的菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenu(java.math.BigInteger id){
//		return eatMenuBaseDao.deleteEatMenu(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(今晚吃什么的菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenuBatch(List<java.math.BigInteger> idList){
//		return eatMenuBaseDao.deleteEatMenuBatch(idList);
//	}
	
}
