package com.cnfantasia.server.domainbase.eatMenu.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.eatMenu.entity.EatMenu;

/**
 * 描述:(今晚吃什么的菜谱) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EatMenuBaseDao extends AbstractBaseDao implements IEatMenuBaseDao{
	/**
	 * 根据条件查询(今晚吃什么的菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EatMenu> selectEatMenuByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("eatMenuBase.select_eatMenu",paramMap);
	}
	/**
	 * 按条件分页查询(今晚吃什么的菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EatMenu> selectEatMenuByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEatMenuCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EatMenu> resMap= sqlSession.selectList("eatMenuBase.select_eatMenu_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(今晚吃什么的菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public EatMenu selectEatMenuBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("eatMenuBase.select_eatMenu_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(今晚吃什么的菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEatMenuCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("eatMenuBase.select_eatMenu_count", paramMap);
	}
	/**
	 * 往(今晚吃什么的菜谱)新增一条记录
	 * @param eatMenu
	 * @return
	 */
	@Override
	public int insertEatMenu(EatMenu eatMenu){
		return sqlSession.insert("eatMenuBase.insert_eatMenu",eatMenu);
	}
	/**
	 * 批量新增(今晚吃什么的菜谱)信息
	 * @param eatMenuList
	 * @return
	 */
	@Override
	public int insertEatMenuBatch(List<EatMenu> eatMenuList) {
		return sqlSession.insert("eatMenuBase.insert_eatMenu_Batch",eatMenuList);
	}
	
	/**
	 * 更新(今晚吃什么的菜谱)信息
	 * @param eatMenu
	 * @return
	 */
	@Override
	public int updateEatMenu(EatMenu eatMenu){
		return sqlSession.update("eatMenuBase.update_eatMenu", eatMenu);
	}
	/**
	 * 批量更新(今晚吃什么的菜谱)信息
	 * @param eatMenuList
	 * @return
	 */
	@Override
	public int updateEatMenuBatch(List<EatMenu> eatMenuList) {
		return sqlSession.update("eatMenuBase.update_eatMenu_Batch", eatMenuList);
	}
	
	/**
	 * 根据序列号删除(今晚吃什么的菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEatMenuLogic(java.math.BigInteger id){
		EatMenu eatMenu = new EatMenu();
		eatMenu.setId(id);
		eatMenu.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("eatMenuBase.delete_eatMenu_Logic",eatMenu);
	}
	
	/**
	 * 根据Id 批量删除(今晚吃什么的菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEatMenuLogicBatch(List<java.math.BigInteger> idList) {
		List<EatMenu> delList = new java.util.ArrayList<EatMenu>();
		for(java.math.BigInteger id:idList){
			EatMenu eatMenu = new EatMenu();
			eatMenu.setId(id);
			eatMenu.setSys0DelState(RecordState_DELETED);
			delList.add(eatMenu);
		}
		return sqlSession.update("eatMenuBase.delete_eatMenu_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(今晚吃什么的菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenu(java.math.BigInteger id){
//		return sqlSession.delete("eatMenuBase.delete_eatMenu", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(今晚吃什么的菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenuBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("eatMenuBase.delete_eatMenu_Batch", idList);
//	}
	
	
}
