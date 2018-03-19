package com.cnfantasia.server.domainbase.ebuyProductPic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductPic.dao.IEbuyProductPicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;

/**
 * 描述:(产品图片信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductPicBaseService implements IEbuyProductPicBaseService{
	
	private IEbuyProductPicBaseDao ebuyProductPicBaseDao;
	public void setEbuyProductPicBaseDao(IEbuyProductPicBaseDao ebuyProductPicBaseDao) {
		this.ebuyProductPicBaseDao = ebuyProductPicBaseDao;
	}
	/**
	 * 根据条件查询(产品图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductPic> getEbuyProductPicByCondition(Map<String,Object> paramMap){
		return ebuyProductPicBaseDao.selectEbuyProductPicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(产品图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductPic> getEbuyProductPicByConditionDim(Map<String,Object> paramMap){
		return ebuyProductPicBaseDao.selectEbuyProductPicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(产品图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductPic> getEbuyProductPicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductPicBaseDao.selectEbuyProductPicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(产品图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductPic> getEbuyProductPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductPicBaseDao.selectEbuyProductPicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(产品图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductPic getEbuyProductPicBySeqId(java.math.BigInteger id){
		return ebuyProductPicBaseDao.selectEbuyProductPicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(产品图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductPicCount(Map<String,Object> paramMap){
		return ebuyProductPicBaseDao.selectEbuyProductPicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(产品图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductPicCountDim(Map<String,Object> paramMap){
		return ebuyProductPicBaseDao.selectEbuyProductPicCount(paramMap,true);
	}
	/**
	 * 往(产品图片信息)新增一条记录
	 * @param ebuyProductPic
	 * @return
	 */
	@Override
	public int insertEbuyProductPic(EbuyProductPic ebuyProductPic){
		return ebuyProductPicBaseDao.insertEbuyProductPic(ebuyProductPic);
	}
	/**
	 * 批量新增(产品图片信息)
	 * @param ebuyProductPicList
	 * @return
	 */
	@Override
	public int insertEbuyProductPicBatch(List<EbuyProductPic> ebuyProductPicList){
		return ebuyProductPicBaseDao.insertEbuyProductPicBatch(ebuyProductPicList);
	}
	/**
	 * 更新(产品图片信息)信息
	 * @param ebuyProductPic
	 * @return
	 */
	@Override
	public int updateEbuyProductPic(EbuyProductPic ebuyProductPic){
		return ebuyProductPicBaseDao.updateEbuyProductPic(ebuyProductPic);
	}
	/**
	 * 批量更新(产品图片信息)信息
	 * @param ebuyProductPicList
	 * @return
	 */
	@Override
	public int updateEbuyProductPicBatch(List<EbuyProductPic> ebuyProductPicList){
		return ebuyProductPicBaseDao.updateEbuyProductPicBatch(ebuyProductPicList);
	}
	/**
	 * 根据序列号删除(产品图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductPicLogic(java.math.BigInteger id){
		return ebuyProductPicBaseDao.deleteEbuyProductPicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(产品图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductPicLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductPicBaseDao.deleteEbuyProductPicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(产品图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductPic(java.math.BigInteger id){
//		return ebuyProductPicBaseDao.deleteEbuyProductPic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductPicBatch(List<java.math.BigInteger> idList){
//		return ebuyProductPicBaseDao.deleteEbuyProductPicBatch(idList);
//	}
	
}
