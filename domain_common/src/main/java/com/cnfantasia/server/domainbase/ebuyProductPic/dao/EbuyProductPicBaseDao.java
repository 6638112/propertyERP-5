package com.cnfantasia.server.domainbase.ebuyProductPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;

/**
 * 描述:(产品图片信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductPicBaseDao extends AbstractBaseDao implements IEbuyProductPicBaseDao{
	/**
	 * 根据条件查询(产品图片信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductPic> selectEbuyProductPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductPicBase.select_ebuyProductPic",paramMap);
	}
	/**
	 * 按条件分页查询(产品图片信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductPic> selectEbuyProductPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductPic> resMap= sqlSession.selectList("ebuyProductPicBase.select_ebuyProductPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(产品图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductPic selectEbuyProductPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductPicBase.select_ebuyProductPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(产品图片信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductPicBase.select_ebuyProductPic_count", paramMap);
	}
	/**
	 * 往(产品图片信息)新增一条记录
	 * @param ebuyProductPic
	 * @return
	 */
	@Override
	public int insertEbuyProductPic(EbuyProductPic ebuyProductPic){
		return sqlSession.insert("ebuyProductPicBase.insert_ebuyProductPic",ebuyProductPic);
	}
	/**
	 * 批量新增(产品图片信息)信息
	 * @param ebuyProductPicList
	 * @return
	 */
	@Override
	public int insertEbuyProductPicBatch(List<EbuyProductPic> ebuyProductPicList) {
		return sqlSession.insert("ebuyProductPicBase.insert_ebuyProductPic_Batch",ebuyProductPicList);
	}
	
	/**
	 * 更新(产品图片信息)信息
	 * @param ebuyProductPic
	 * @return
	 */
	@Override
	public int updateEbuyProductPic(EbuyProductPic ebuyProductPic){
		return sqlSession.update("ebuyProductPicBase.update_ebuyProductPic", ebuyProductPic);
	}
	/**
	 * 批量更新(产品图片信息)信息
	 * @param ebuyProductPicList
	 * @return
	 */
	@Override
	public int updateEbuyProductPicBatch(List<EbuyProductPic> ebuyProductPicList) {
		return sqlSession.update("ebuyProductPicBase.update_ebuyProductPic_Batch", ebuyProductPicList);
	}
	
	/**
	 * 根据序列号删除(产品图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductPicLogic(java.math.BigInteger id){
		EbuyProductPic ebuyProductPic = new EbuyProductPic();
		ebuyProductPic.setId(id);
		ebuyProductPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductPicBase.delete_ebuyProductPic_Logic",ebuyProductPic);
	}
	
	/**
	 * 根据Id 批量删除(产品图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductPicLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductPic> delList = new java.util.ArrayList<EbuyProductPic>();
		for(java.math.BigInteger id:idList){
			EbuyProductPic ebuyProductPic = new EbuyProductPic();
			ebuyProductPic.setId(id);
			ebuyProductPic.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductPic);
		}
		return sqlSession.update("ebuyProductPicBase.delete_ebuyProductPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(产品图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductPic(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductPicBase.delete_ebuyProductPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductPicBase.delete_ebuyProductPic_Batch", idList);
//	}
	
	
}
