package com.cnfantasia.server.domainbase.mrFeeItemFormula.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;

/**
 * 描述:(抄表费收费项阶梯计算配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MrFeeItemFormulaBaseDao extends AbstractBaseDao implements IMrFeeItemFormulaBaseDao{
	/**
	 * 根据条件查询(抄表费收费项阶梯计算配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrFeeItemFormula> selectMrFeeItemFormulaByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("mrFeeItemFormulaBase.select_mrFeeItemFormula",paramMap);
	}
	/**
	 * 按条件分页查询(抄表费收费项阶梯计算配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MrFeeItemFormula> selectMrFeeItemFormulaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMrFeeItemFormulaCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MrFeeItemFormula> resMap= sqlSession.selectList("mrFeeItemFormulaBase.select_mrFeeItemFormula_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抄表费收费项阶梯计算配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MrFeeItemFormula selectMrFeeItemFormulaBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("mrFeeItemFormulaBase.select_mrFeeItemFormula_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抄表费收费项阶梯计算配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMrFeeItemFormulaCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("mrFeeItemFormulaBase.select_mrFeeItemFormula_count", paramMap);
	}
	/**
	 * 往(抄表费收费项阶梯计算配置)新增一条记录
	 * @param mrFeeItemFormula
	 * @return
	 */
	@Override
	public int insertMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula){
		return sqlSession.insert("mrFeeItemFormulaBase.insert_mrFeeItemFormula",mrFeeItemFormula);
	}
	/**
	 * 批量新增(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormulaList
	 * @return
	 */
	@Override
	public int insertMrFeeItemFormulaBatch(List<MrFeeItemFormula> mrFeeItemFormulaList) {
		if (mrFeeItemFormulaList == null || mrFeeItemFormulaList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = mrFeeItemFormulaList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<MrFeeItemFormula> batchList = mrFeeItemFormulaList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("mrFeeItemFormulaBase.insert_mrFeeItemFormula_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormula
	 * @return
	 */
	@Override
	public int updateMrFeeItemFormula(MrFeeItemFormula mrFeeItemFormula){
		return sqlSession.update("mrFeeItemFormulaBase.update_mrFeeItemFormula", mrFeeItemFormula);
	}
	/**
	 * 批量更新(抄表费收费项阶梯计算配置)信息
	 * @param mrFeeItemFormulaList
	 * @return
	 */
	@Override
	public int updateMrFeeItemFormulaBatch(List<MrFeeItemFormula> mrFeeItemFormulaList) {
		if (mrFeeItemFormulaList == null || mrFeeItemFormulaList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("mrFeeItemFormulaBase.update_mrFeeItemFormula_Batch", mrFeeItemFormulaList);
	}
	
	/**
	 * 根据序列号删除(抄表费收费项阶梯计算配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrFeeItemFormulaLogic(java.math.BigInteger id){
		MrFeeItemFormula mrFeeItemFormula = new MrFeeItemFormula();
		mrFeeItemFormula.setId(id);
		mrFeeItemFormula.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("mrFeeItemFormulaBase.delete_mrFeeItemFormula_Logic",mrFeeItemFormula);
	}
	
	/**
	 * 根据Id 批量删除(抄表费收费项阶梯计算配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrFeeItemFormulaLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<MrFeeItemFormula> delList = new java.util.ArrayList<MrFeeItemFormula>();
		for(java.math.BigInteger id:idList){
			MrFeeItemFormula mrFeeItemFormula = new MrFeeItemFormula();
			mrFeeItemFormula.setId(id);
			mrFeeItemFormula.setSys0DelState(RecordState_DELETED);
			delList.add(mrFeeItemFormula);
		}
		return sqlSession.update("mrFeeItemFormulaBase.delete_mrFeeItemFormula_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抄表费收费项阶梯计算配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrFeeItemFormula(java.math.BigInteger id){
//		return sqlSession.delete("mrFeeItemFormulaBase.delete_mrFeeItemFormula", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表费收费项阶梯计算配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrFeeItemFormulaBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("mrFeeItemFormulaBase.delete_mrFeeItemFormula_Batch", idList);
//	}
	
	
}
