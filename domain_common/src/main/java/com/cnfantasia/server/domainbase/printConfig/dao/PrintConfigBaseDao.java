package com.cnfantasia.server.domainbase.printConfig.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.printConfig.entity.PrintConfig;

/**
 * 描述:(物业账单打印小区模板配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrintConfigBaseDao extends AbstractBaseDao implements IPrintConfigBaseDao{
	/**
	 * 根据条件查询(物业账单打印小区模板配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrintConfig> selectPrintConfigByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("printConfigBase.select_printConfig",paramMap);
	}
	/**
	 * 按条件分页查询(物业账单打印小区模板配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrintConfig> selectPrintConfigByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrintConfigCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrintConfig> resMap= sqlSession.selectList("printConfigBase.select_printConfig_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业账单打印小区模板配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrintConfig selectPrintConfigBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("printConfigBase.select_printConfig_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业账单打印小区模板配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrintConfigCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("printConfigBase.select_printConfig_count", paramMap);
	}
	/**
	 * 往(物业账单打印小区模板配置表)新增一条记录
	 * @param printConfig
	 * @return
	 */
	@Override
	public int insertPrintConfig(PrintConfig printConfig){
		return sqlSession.insert("printConfigBase.insert_printConfig",printConfig);
	}
	/**
	 * 批量新增(物业账单打印小区模板配置表)信息
	 * @param printConfigList
	 * @return
	 */
	@Override
	public int insertPrintConfigBatch(List<PrintConfig> printConfigList) {
		if (printConfigList == null || printConfigList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = printConfigList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PrintConfig> batchList = printConfigList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("printConfigBase.insert_printConfig_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业账单打印小区模板配置表)信息
	 * @param printConfig
	 * @return
	 */
	@Override
	public int updatePrintConfig(PrintConfig printConfig){
		return sqlSession.update("printConfigBase.update_printConfig", printConfig);
	}
	/**
	 * 批量更新(物业账单打印小区模板配置表)信息
	 * @param printConfigList
	 * @return
	 */
	@Override
	public int updatePrintConfigBatch(List<PrintConfig> printConfigList) {
		if (printConfigList == null || printConfigList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("printConfigBase.update_printConfig_Batch", printConfigList);
	}
	
	/**
	 * 根据序列号删除(物业账单打印小区模板配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrintConfigLogic(java.math.BigInteger id){
		PrintConfig printConfig = new PrintConfig();
		printConfig.setId(id);
		printConfig.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("printConfigBase.delete_printConfig_Logic",printConfig);
	}
	
	/**
	 * 根据Id 批量删除(物业账单打印小区模板配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrintConfigLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PrintConfig> delList = new java.util.ArrayList<PrintConfig>();
		for(java.math.BigInteger id:idList){
			PrintConfig printConfig = new PrintConfig();
			printConfig.setId(id);
			printConfig.setSys0DelState(RecordState_DELETED);
			delList.add(printConfig);
		}
		return sqlSession.update("printConfigBase.delete_printConfig_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业账单打印小区模板配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrintConfig(java.math.BigInteger id){
//		return sqlSession.delete("printConfigBase.delete_printConfig", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业账单打印小区模板配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrintConfigBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("printConfigBase.delete_printConfig_Batch", idList);
//	}
	
	
}
