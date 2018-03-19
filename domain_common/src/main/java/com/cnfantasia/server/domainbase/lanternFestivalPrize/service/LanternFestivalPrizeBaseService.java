package com.cnfantasia.server.domainbase.lanternFestivalPrize.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.lanternFestivalPrize.dao.ILanternFestivalPrizeBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lanternFestivalPrize.entity.LanternFestivalPrize;

/**
 * 描述:(元宵节猜谜中奖公示表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LanternFestivalPrizeBaseService implements ILanternFestivalPrizeBaseService{
	
	private ILanternFestivalPrizeBaseDao lanternFestivalPrizeBaseDao;
	public void setLanternFestivalPrizeBaseDao(ILanternFestivalPrizeBaseDao lanternFestivalPrizeBaseDao) {
		this.lanternFestivalPrizeBaseDao = lanternFestivalPrizeBaseDao;
	}
	/**
	 * 根据条件查询(元宵节猜谜中奖公示表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LanternFestivalPrize> getLanternFestivalPrizeByCondition(Map<String,Object> paramMap){
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(元宵节猜谜中奖公示表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LanternFestivalPrize> getLanternFestivalPrizeByConditionDim(Map<String,Object> paramMap){
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(元宵节猜谜中奖公示表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LanternFestivalPrize> getLanternFestivalPrizeByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(元宵节猜谜中奖公示表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LanternFestivalPrize> getLanternFestivalPrizeByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(元宵节猜谜中奖公示表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LanternFestivalPrize getLanternFestivalPrizeBySeqId(java.math.BigInteger id){
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(元宵节猜谜中奖公示表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLanternFestivalPrizeCount(Map<String,Object> paramMap){
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(元宵节猜谜中奖公示表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLanternFestivalPrizeCountDim(Map<String,Object> paramMap){
		return lanternFestivalPrizeBaseDao.selectLanternFestivalPrizeCount(paramMap,true);
	}
	/**
	 * 往(元宵节猜谜中奖公示表)新增一条记录
	 * @param lanternFestivalPrize
	 * @return
	 */
	@Override
	public int insertLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize){
		return lanternFestivalPrizeBaseDao.insertLanternFestivalPrize(lanternFestivalPrize);
	}
	/**
	 * 批量新增(元宵节猜谜中奖公示表)
	 * @param lanternFestivalPrizeList
	 * @return
	 */
	@Override
	public int insertLanternFestivalPrizeBatch(List<LanternFestivalPrize> lanternFestivalPrizeList){
		return lanternFestivalPrizeBaseDao.insertLanternFestivalPrizeBatch(lanternFestivalPrizeList);
	}
	/**
	 * 更新(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrize
	 * @return
	 */
	@Override
	public int updateLanternFestivalPrize(LanternFestivalPrize lanternFestivalPrize){
		return lanternFestivalPrizeBaseDao.updateLanternFestivalPrize(lanternFestivalPrize);
	}
	/**
	 * 批量更新(元宵节猜谜中奖公示表)信息
	 * @param lanternFestivalPrizeList
	 * @return
	 */
	@Override
	public int updateLanternFestivalPrizeBatch(List<LanternFestivalPrize> lanternFestivalPrizeList){
		return lanternFestivalPrizeBaseDao.updateLanternFestivalPrizeBatch(lanternFestivalPrizeList);
	}
	/**
	 * 根据序列号删除(元宵节猜谜中奖公示表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLanternFestivalPrizeLogic(java.math.BigInteger id){
		return lanternFestivalPrizeBaseDao.deleteLanternFestivalPrizeLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(元宵节猜谜中奖公示表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLanternFestivalPrizeLogicBatch(List<java.math.BigInteger> idList){
		return lanternFestivalPrizeBaseDao.deleteLanternFestivalPrizeLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(元宵节猜谜中奖公示表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLanternFestivalPrize(java.math.BigInteger id){
//		return lanternFestivalPrizeBaseDao.deleteLanternFestivalPrize(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(元宵节猜谜中奖公示表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLanternFestivalPrizeBatch(List<java.math.BigInteger> idList){
//		return lanternFestivalPrizeBaseDao.deleteLanternFestivalPrizeBatch(idList);
//	}
	
}
