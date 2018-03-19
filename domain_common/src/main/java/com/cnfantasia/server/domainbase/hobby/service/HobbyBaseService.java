package com.cnfantasia.server.domainbase.hobby.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.hobby.dao.IHobbyBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.hobby.entity.Hobby;

/**
 * 描述:(兴趣爱好表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class HobbyBaseService implements IHobbyBaseService{
	
	private IHobbyBaseDao hobbyBaseDao;
	public void setHobbyBaseDao(IHobbyBaseDao hobbyBaseDao) {
		this.hobbyBaseDao = hobbyBaseDao;
	}
	/**
	 * 根据条件查询(兴趣爱好表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Hobby> getHobbyByCondition(Map<String,Object> paramMap){
		return hobbyBaseDao.selectHobbyByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(兴趣爱好表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<Hobby> getHobbyByConditionDim(Map<String,Object> paramMap){
		return hobbyBaseDao.selectHobbyByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(兴趣爱好表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Hobby> getHobbyByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return hobbyBaseDao.selectHobbyByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(兴趣爱好表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Hobby> getHobbyByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return hobbyBaseDao.selectHobbyByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(兴趣爱好表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Hobby getHobbyBySeqId(java.math.BigInteger id){
		return hobbyBaseDao.selectHobbyBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(兴趣爱好表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getHobbyCount(Map<String,Object> paramMap){
		return hobbyBaseDao.selectHobbyCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(兴趣爱好表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getHobbyCountDim(Map<String,Object> paramMap){
		return hobbyBaseDao.selectHobbyCount(paramMap,true);
	}
	/**
	 * 往(兴趣爱好表)新增一条记录
	 * @param hobby
	 * @return
	 */
	@Override
	public int insertHobby(Hobby hobby){
		return hobbyBaseDao.insertHobby(hobby);
	}
	/**
	 * 批量新增(兴趣爱好表)
	 * @param hobbyList
	 * @return
	 */
	@Override
	public int insertHobbyBatch(List<Hobby> hobbyList){
		return hobbyBaseDao.insertHobbyBatch(hobbyList);
	}
	/**
	 * 更新(兴趣爱好表)信息
	 * @param hobby
	 * @return
	 */
	@Override
	public int updateHobby(Hobby hobby){
		return hobbyBaseDao.updateHobby(hobby);
	}
	/**
	 * 批量更新(兴趣爱好表)信息
	 * @param hobbyList
	 * @return
	 */
	@Override
	public int updateHobbyBatch(List<Hobby> hobbyList){
		return hobbyBaseDao.updateHobbyBatch(hobbyList);
	}
	/**
	 * 根据序列号删除(兴趣爱好表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteHobbyLogic(java.math.BigInteger id){
		return hobbyBaseDao.deleteHobbyLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(兴趣爱好表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteHobbyLogicBatch(List<java.math.BigInteger> idList){
		return hobbyBaseDao.deleteHobbyLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(兴趣爱好表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteHobby(java.math.BigInteger id){
//		return hobbyBaseDao.deleteHobby(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(兴趣爱好表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteHobbyBatch(List<java.math.BigInteger> idList){
//		return hobbyBaseDao.deleteHobbyBatch(idList);
//	}
	
}
