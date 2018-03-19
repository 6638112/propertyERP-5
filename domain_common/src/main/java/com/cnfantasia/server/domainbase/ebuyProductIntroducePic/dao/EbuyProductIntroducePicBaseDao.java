package com.cnfantasia.server.domainbase.ebuyProductIntroducePic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductIntroducePic.entity.EbuyProductIntroducePic;

/**
 * 描述:(产品介绍图片信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductIntroducePicBaseDao extends AbstractBaseDao implements IEbuyProductIntroducePicBaseDao{
	/**
	 * 根据条件查询(产品介绍图片信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductIntroducePic> selectEbuyProductIntroducePicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductIntroducePicBase.select_ebuyProductIntroducePic",paramMap);
	}
	/**
	 * 按条件分页查询(产品介绍图片信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductIntroducePic> selectEbuyProductIntroducePicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductIntroducePicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductIntroducePic> resMap= sqlSession.selectList("ebuyProductIntroducePicBase.select_ebuyProductIntroducePic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(产品介绍图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductIntroducePic selectEbuyProductIntroducePicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductIntroducePicBase.select_ebuyProductIntroducePic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(产品介绍图片信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductIntroducePicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductIntroducePicBase.select_ebuyProductIntroducePic_count", paramMap);
	}
	/**
	 * 往(产品介绍图片信息)新增一条记录
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	@Override
	public int insertEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic){
		return sqlSession.insert("ebuyProductIntroducePicBase.insert_ebuyProductIntroducePic",ebuyProductIntroducePic);
	}
	/**
	 * 批量新增(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePicList
	 * @return
	 */
	@Override
	public int insertEbuyProductIntroducePicBatch(List<EbuyProductIntroducePic> ebuyProductIntroducePicList) {
		return sqlSession.insert("ebuyProductIntroducePicBase.insert_ebuyProductIntroducePic_Batch",ebuyProductIntroducePicList);
	}
	
	/**
	 * 更新(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePic
	 * @return
	 */
	@Override
	public int updateEbuyProductIntroducePic(EbuyProductIntroducePic ebuyProductIntroducePic){
		return sqlSession.update("ebuyProductIntroducePicBase.update_ebuyProductIntroducePic", ebuyProductIntroducePic);
	}
	/**
	 * 批量更新(产品介绍图片信息)信息
	 * @param ebuyProductIntroducePicList
	 * @return
	 */
	@Override
	public int updateEbuyProductIntroducePicBatch(List<EbuyProductIntroducePic> ebuyProductIntroducePicList) {
		return sqlSession.update("ebuyProductIntroducePicBase.update_ebuyProductIntroducePic_Batch", ebuyProductIntroducePicList);
	}
	
	/**
	 * 根据序列号删除(产品介绍图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductIntroducePicLogic(java.math.BigInteger id){
		EbuyProductIntroducePic ebuyProductIntroducePic = new EbuyProductIntroducePic();
		ebuyProductIntroducePic.setId(id);
		ebuyProductIntroducePic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductIntroducePicBase.delete_ebuyProductIntroducePic_Logic",ebuyProductIntroducePic);
	}
	
	/**
	 * 根据Id 批量删除(产品介绍图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductIntroducePicLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductIntroducePic> delList = new java.util.ArrayList<EbuyProductIntroducePic>();
		for(java.math.BigInteger id:idList){
			EbuyProductIntroducePic ebuyProductIntroducePic = new EbuyProductIntroducePic();
			ebuyProductIntroducePic.setId(id);
			ebuyProductIntroducePic.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductIntroducePic);
		}
		return sqlSession.update("ebuyProductIntroducePicBase.delete_ebuyProductIntroducePic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(产品介绍图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductIntroducePic(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductIntroducePicBase.delete_ebuyProductIntroducePic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品介绍图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductIntroducePicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductIntroducePicBase.delete_ebuyProductIntroducePic_Batch", idList);
//	}
	
	
}
