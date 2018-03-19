package com.cnfantasia.server.domainbase.ebuyProductFightPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductFightPic.dao.IEbuyProductFightPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductFightPic.entity.EbuyProductFightPic;

/**
 * 描述:(拼购产品列表图片信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductFightPicBaseService implements IEbuyProductFightPicBaseService{
	
	private IEbuyProductFightPicBaseDao ebuyProductFightPicBaseDao;
	public void setEbuyProductFightPicBaseDao(IEbuyProductFightPicBaseDao ebuyProductFightPicBaseDao) {
		this.ebuyProductFightPicBaseDao = ebuyProductFightPicBaseDao;
	}
	/**
	 * 根据条件查询(拼购产品列表图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductFightPic> getEbuyProductFightPicByCondition(Map<String,Object> paramMap){
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(拼购产品列表图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductFightPic> getEbuyProductFightPicByConditionDim(Map<String,Object> paramMap){
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(拼购产品列表图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductFightPic> getEbuyProductFightPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(拼购产品列表图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductFightPic> getEbuyProductFightPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(拼购产品列表图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductFightPic getEbuyProductFightPicBySeqId(java.math.BigInteger id){
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(拼购产品列表图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductFightPicCount(Map<String,Object> paramMap){
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(拼购产品列表图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductFightPicCountDim(Map<String,Object> paramMap){
		return ebuyProductFightPicBaseDao.selectEbuyProductFightPicCount(paramMap,true);
	}
	/**
	 * 往(拼购产品列表图片信息)新增一条记录
	 * @param ebuyProductFightPic
	 * @return
	 */
	@Override
	public int insertEbuyProductFightPic(EbuyProductFightPic ebuyProductFightPic){
		return ebuyProductFightPicBaseDao.insertEbuyProductFightPic(ebuyProductFightPic);
	}
	/**
	 * 批量新增(拼购产品列表图片信息)
	 * @param ebuyProductFightPicList
	 * @return
	 */
	@Override
	public int insertEbuyProductFightPicBatch(List<EbuyProductFightPic> ebuyProductFightPicList){
		return ebuyProductFightPicBaseDao.insertEbuyProductFightPicBatch(ebuyProductFightPicList);
	}
	/**
	 * 更新(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPic
	 * @return
	 */
	@Override
	public int updateEbuyProductFightPic(EbuyProductFightPic ebuyProductFightPic){
		return ebuyProductFightPicBaseDao.updateEbuyProductFightPic(ebuyProductFightPic);
	}
	/**
	 * 批量更新(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPicList
	 * @return
	 */
	@Override
	public int updateEbuyProductFightPicBatch(List<EbuyProductFightPic> ebuyProductFightPicList){
		return ebuyProductFightPicBaseDao.updateEbuyProductFightPicBatch(ebuyProductFightPicList);
	}
	/**
	 * 根据序列号删除(拼购产品列表图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightPicLogic(java.math.BigInteger id){
		return ebuyProductFightPicBaseDao.deleteEbuyProductFightPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(拼购产品列表图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightPicLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductFightPicBaseDao.deleteEbuyProductFightPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(拼购产品列表图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightPic(java.math.BigInteger id){
//		return ebuyProductFightPicBaseDao.deleteEbuyProductFightPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼购产品列表图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightPicBatch(List<java.math.BigInteger> idList){
//		return ebuyProductFightPicBaseDao.deleteEbuyProductFightPicBatch(idList);
//	}
	
}
