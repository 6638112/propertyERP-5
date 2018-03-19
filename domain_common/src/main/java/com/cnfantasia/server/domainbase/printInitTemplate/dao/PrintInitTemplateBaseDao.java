package com.cnfantasia.server.domainbase.printInitTemplate.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.printInitTemplate.entity.PrintInitTemplate;

/**
 * 描述:(账单打印模板初始化表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrintInitTemplateBaseDao extends AbstractBaseDao implements IPrintInitTemplateBaseDao{
	/**
	 * 根据条件查询(账单打印模板初始化表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrintInitTemplate> selectPrintInitTemplateByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("printInitTemplateBase.select_printInitTemplate",paramMap);
	}
	/**
	 * 按条件分页查询(账单打印模板初始化表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrintInitTemplate> selectPrintInitTemplateByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrintInitTemplateCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrintInitTemplate> resMap= sqlSession.selectList("printInitTemplateBase.select_printInitTemplate_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(账单打印模板初始化表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrintInitTemplate selectPrintInitTemplateBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("printInitTemplateBase.select_printInitTemplate_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(账单打印模板初始化表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrintInitTemplateCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("printInitTemplateBase.select_printInitTemplate_count", paramMap);
	}
	/**
	 * 往(账单打印模板初始化表)新增一条记录
	 * @param printInitTemplate
	 * @return
	 */
	@Override
	public int insertPrintInitTemplate(PrintInitTemplate printInitTemplate){
		return sqlSession.insert("printInitTemplateBase.insert_printInitTemplate",printInitTemplate);
	}
	/**
	 * 批量新增(账单打印模板初始化表)信息
	 * @param printInitTemplateList
	 * @return
	 */
	@Override
	public int insertPrintInitTemplateBatch(List<PrintInitTemplate> printInitTemplateList) {
		if (printInitTemplateList == null || printInitTemplateList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = printInitTemplateList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PrintInitTemplate> batchList = printInitTemplateList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("printInitTemplateBase.insert_printInitTemplate_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(账单打印模板初始化表)信息
	 * @param printInitTemplate
	 * @return
	 */
	@Override
	public int updatePrintInitTemplate(PrintInitTemplate printInitTemplate){
		return sqlSession.update("printInitTemplateBase.update_printInitTemplate", printInitTemplate);
	}
	/**
	 * 批量更新(账单打印模板初始化表)信息
	 * @param printInitTemplateList
	 * @return
	 */
	@Override
	public int updatePrintInitTemplateBatch(List<PrintInitTemplate> printInitTemplateList) {
		if (printInitTemplateList == null || printInitTemplateList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("printInitTemplateBase.update_printInitTemplate_Batch", printInitTemplateList);
	}
	
	/**
	 * 根据序列号删除(账单打印模板初始化表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrintInitTemplateLogic(java.math.BigInteger id){
		PrintInitTemplate printInitTemplate = new PrintInitTemplate();
		printInitTemplate.setId(id);
		printInitTemplate.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("printInitTemplateBase.delete_printInitTemplate_Logic",printInitTemplate);
	}
	
	/**
	 * 根据Id 批量删除(账单打印模板初始化表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrintInitTemplateLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PrintInitTemplate> delList = new java.util.ArrayList<PrintInitTemplate>();
		for(java.math.BigInteger id:idList){
			PrintInitTemplate printInitTemplate = new PrintInitTemplate();
			printInitTemplate.setId(id);
			printInitTemplate.setSys0DelState(RecordState_DELETED);
			delList.add(printInitTemplate);
		}
		return sqlSession.update("printInitTemplateBase.delete_printInitTemplate_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(账单打印模板初始化表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrintInitTemplate(java.math.BigInteger id){
//		return sqlSession.delete("printInitTemplateBase.delete_printInitTemplate", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账单打印模板初始化表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrintInitTemplateBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("printInitTemplateBase.delete_printInitTemplate_Batch", idList);
//	}
	
	
}
