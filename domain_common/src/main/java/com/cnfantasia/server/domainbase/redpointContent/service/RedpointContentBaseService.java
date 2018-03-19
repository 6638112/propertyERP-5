package com.cnfantasia.server.domainbase.redpointContent.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.redpointContent.dao.IRedpointContentBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;

/**
 * 描述:(红点信息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class RedpointContentBaseService implements IRedpointContentBaseService{
	
	private IRedpointContentBaseDao redpointContentBaseDao;
	public void setRedpointContentBaseDao(IRedpointContentBaseDao redpointContentBaseDao) {
		this.redpointContentBaseDao = redpointContentBaseDao;
	}
	/**
	 * 根据条件查询(红点信息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RedpointContent> getRedpointContentByCondition(Map<String,Object> paramMap){
		return redpointContentBaseDao.selectRedpointContentByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(红点信息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<RedpointContent> getRedpointContentByConditionDim(Map<String,Object> paramMap){
		return redpointContentBaseDao.selectRedpointContentByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(红点信息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RedpointContent> getRedpointContentByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return redpointContentBaseDao.selectRedpointContentByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(红点信息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<RedpointContent> getRedpointContentByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return redpointContentBaseDao.selectRedpointContentByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(红点信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RedpointContent getRedpointContentBySeqId(java.math.BigInteger id){
		return redpointContentBaseDao.selectRedpointContentBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(红点信息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRedpointContentCount(Map<String,Object> paramMap){
		return redpointContentBaseDao.selectRedpointContentCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(红点信息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getRedpointContentCountDim(Map<String,Object> paramMap){
		return redpointContentBaseDao.selectRedpointContentCount(paramMap,true);
	}
	/**
	 * 往(红点信息表)新增一条记录
	 * @param redpointContent
	 * @return
	 */
	@Override
	public int insertRedpointContent(RedpointContent redpointContent){
		return redpointContentBaseDao.insertRedpointContent(redpointContent);
	}
	/**
	 * 批量新增(红点信息表)
	 * @param redpointContentList
	 * @return
	 */
	@Override
	public int insertRedpointContentBatch(List<RedpointContent> redpointContentList){
		return redpointContentBaseDao.insertRedpointContentBatch(redpointContentList);
	}
	/**
	 * 更新(红点信息表)信息
	 * @param redpointContent
	 * @return
	 */
	@Override
	public int updateRedpointContent(RedpointContent redpointContent){
		return redpointContentBaseDao.updateRedpointContent(redpointContent);
	}
	/**
	 * 批量更新(红点信息表)信息
	 * @param redpointContentList
	 * @return
	 */
	@Override
	public int updateRedpointContentBatch(List<RedpointContent> redpointContentList){
		return redpointContentBaseDao.updateRedpointContentBatch(redpointContentList);
	}
	/**
	 * 根据序列号删除(红点信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentLogic(java.math.BigInteger id){
		return redpointContentBaseDao.deleteRedpointContentLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(红点信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRedpointContentLogicBatch(List<java.math.BigInteger> idList){
		return redpointContentBaseDao.deleteRedpointContentLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(红点信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContent(java.math.BigInteger id){
//		return redpointContentBaseDao.deleteRedpointContent(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(红点信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRedpointContentBatch(List<java.math.BigInteger> idList){
//		return redpointContentBaseDao.deleteRedpointContentBatch(idList);
//	}
	
}
