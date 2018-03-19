package com.cnfantasia.server.domainbase.ebuyProductFightPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductFightPic.entity.EbuyProductFightPic;

/**
 * 描述:(拼购产品列表图片信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductFightPicBaseDao extends AbstractBaseDao implements IEbuyProductFightPicBaseDao{
	/**
	 * 根据条件查询(拼购产品列表图片信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductFightPic> selectEbuyProductFightPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductFightPicBase.select_ebuyProductFightPic",paramMap);
	}
	/**
	 * 按条件分页查询(拼购产品列表图片信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductFightPic> selectEbuyProductFightPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductFightPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductFightPic> resMap= sqlSession.selectList("ebuyProductFightPicBase.select_ebuyProductFightPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(拼购产品列表图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductFightPic selectEbuyProductFightPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductFightPicBase.select_ebuyProductFightPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(拼购产品列表图片信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductFightPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductFightPicBase.select_ebuyProductFightPic_count", paramMap);
	}
	/**
	 * 往(拼购产品列表图片信息)新增一条记录
	 * @param ebuyProductFightPic
	 * @return
	 */
	@Override
	public int insertEbuyProductFightPic(EbuyProductFightPic ebuyProductFightPic){
		return sqlSession.insert("ebuyProductFightPicBase.insert_ebuyProductFightPic",ebuyProductFightPic);
	}
	/**
	 * 批量新增(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPicList
	 * @return
	 */
	@Override
	public int insertEbuyProductFightPicBatch(List<EbuyProductFightPic> ebuyProductFightPicList) {
		return sqlSession.insert("ebuyProductFightPicBase.insert_ebuyProductFightPic_Batch",ebuyProductFightPicList);
	}
	
	/**
	 * 更新(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPic
	 * @return
	 */
	@Override
	public int updateEbuyProductFightPic(EbuyProductFightPic ebuyProductFightPic){
		return sqlSession.update("ebuyProductFightPicBase.update_ebuyProductFightPic", ebuyProductFightPic);
	}
	/**
	 * 批量更新(拼购产品列表图片信息)信息
	 * @param ebuyProductFightPicList
	 * @return
	 */
	@Override
	public int updateEbuyProductFightPicBatch(List<EbuyProductFightPic> ebuyProductFightPicList) {
		return sqlSession.update("ebuyProductFightPicBase.update_ebuyProductFightPic_Batch", ebuyProductFightPicList);
	}
	
	/**
	 * 根据序列号删除(拼购产品列表图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightPicLogic(java.math.BigInteger id){
		EbuyProductFightPic ebuyProductFightPic = new EbuyProductFightPic();
		ebuyProductFightPic.setId(id);
		ebuyProductFightPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductFightPicBase.delete_ebuyProductFightPic_Logic",ebuyProductFightPic);
	}
	
	/**
	 * 根据Id 批量删除(拼购产品列表图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductFightPicLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductFightPic> delList = new java.util.ArrayList<EbuyProductFightPic>();
		for(java.math.BigInteger id:idList){
			EbuyProductFightPic ebuyProductFightPic = new EbuyProductFightPic();
			ebuyProductFightPic.setId(id);
			ebuyProductFightPic.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductFightPic);
		}
		return sqlSession.update("ebuyProductFightPicBase.delete_ebuyProductFightPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(拼购产品列表图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightPic(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductFightPicBase.delete_ebuyProductFightPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼购产品列表图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductFightPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductFightPicBase.delete_ebuyProductFightPic_Batch", idList);
//	}
	
	
}
