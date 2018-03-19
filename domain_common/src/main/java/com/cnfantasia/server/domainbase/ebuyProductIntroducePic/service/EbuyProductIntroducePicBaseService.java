package com.cnfantasia.server.domainbase.ebuyProductIntroducePic.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductIntroducePic.dao.IEbuyProductIntroducePicBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;

/**
 * 描述:(产品介绍图片信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductIntroducePicBaseService implements IEbuyProductIntroducePicBaseService{
	
	private IEbuyProductIntroducePicBaseDao ebuyProductIntroducePicBaseDao;
	public void setEbuyProductIntroducePicBaseDao(IEbuyProductIntroducePicBaseDao ebuyProductIntroducePicBaseDao) {
		this.ebuyProductIntroducePicBaseDao = ebuyProductIntroducePicBaseDao;
	}
	/**
	 * 根据条件查询(产品介绍图片信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByCondition(Map<String,Object> paramMap){
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(产品介绍图片信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByConditionDim(Map<String,Object> paramMap){
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(产品介绍图片信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(产品介绍图片信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductIntroducePic> getEbuyProductIntroducePicByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(产品介绍图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductIntroducePic getEbuyProductIntroducePicBySeqId(java.math.BigInteger id){
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(产品介绍图片信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductIntroducePicCount(Map<String,Object> paramMap){
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(产品介绍图片信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductIntroducePicCountDim(Map<String,Object> paramMap){
		return ebuyProductIntroducePicBaseDao.selectEbuyProductIntroducePicCount(paramMap,true);
	}
	/**
	 * 往(产品介绍图片信息)新增一条记录
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	@Override
	public int insertEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic){
		return ebuyProductIntroducePicBaseDao.insertEbuyProductIntroducePic(ebuyProductIntroducePic);
	}
	/**
	 * 批量新增(产品介绍图片信息)
	 * @param ebuyProductIntroducePicList
	 * @return
	 */
	@Override
	public int insertEbuyProductIntroducePicBatch(List<EbuyProductIntroducePic> ebuyProductIntroducePicList){
		return ebuyProductIntroducePicBaseDao.insertEbuyProductIntroducePicBatch(ebuyProductIntroducePicList);
	}
	/**
	 * 更新(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	@Override
	public int updateEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic){
		return ebuyProductIntroducePicBaseDao.updateEbuyProductIntroducePic(ebuyProductIntroducePic);
	}
	/**
	 * 批量更新(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePicList
	 * @return
	 */
	@Override
	public int updateEbuyProductIntroducePicBatch(List<EbuyProductIntroducePic> ebuyProductIntroducePicList){
		return ebuyProductIntroducePicBaseDao.updateEbuyProductIntroducePicBatch(ebuyProductIntroducePicList);
	}
	/**
	 * 根据序列号删除(产品介绍图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductIntroducePicLogic(java.math.BigInteger id){
		return ebuyProductIntroducePicBaseDao.deleteEbuyProductIntroducePicLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(产品介绍图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductIntroducePicLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductIntroducePicBaseDao.deleteEbuyProductIntroducePicLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(产品介绍图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductIntroducePic(java.math.BigInteger id){
//		return ebuyProductIntroducePicBaseDao.deleteEbuyProductIntroducePic(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品介绍图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductIntroducePicBatch(List<java.math.BigInteger> idList){
//		return ebuyProductIntroducePicBaseDao.deleteEbuyProductIntroducePicBatch(idList);
//	}
	
}
