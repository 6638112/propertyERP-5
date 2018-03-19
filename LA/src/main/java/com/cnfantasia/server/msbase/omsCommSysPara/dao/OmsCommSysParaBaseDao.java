package com.cnfantasia.server.msbase.omsCommSysPara.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.msbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsCommSysParaBaseDao extends AbstractBaseDao implements IOmsCommSysParaBaseDao{
	/**
	 * 根据条件查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,boolean isDim){
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsCommSysParaBase.select_omsCommSysPara",paramMap);
	}
	/**
	 * 按条件分页查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsCommSysParaCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("参数合并发生信息丢失。");}
		
		List<OmsCommSysPara> resMap= sqlSession.selectList("omsCommSysParaBase.select_omsCommSysPara_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param seqId
	 * @return
	 */
	public OmsCommSysPara selectOmsCommSysParaBySeqId(String seqId){
		return sqlSession.selectOne("omsCommSysParaBase.select_omsCommSysPara_bySeqId",seqId);
	}
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsCommSysParaCount(Map<String,Object> paramMap,boolean isDim){
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsCommSysParaBase.select_omsCommSysPara_count", paramMap);
	}
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param paramMap
	 * @return
	 */
	public int insertOmsCommSysPara(Map<String,Object> paramMap){
		return sqlSession.insert("omsCommSysParaBase.insert_omsCommSysPara",paramMap);
	}
	/**
	 * 更新(OMS系统参数表)信息
	 * @param paramMap
	 * @return
	 */
	public int updateOmsCommSysPara(Map<String,Object> paramMap){
		return sqlSession.update("omsCommSysParaBase.update_omsCommSysPara", paramMap);
	}
	/**
	 * 根据序列号删除(OMS系统参数表)信息
	 * @param seqId
	 * @return
	 */
	public int deleteOmsCommSysPara(String seqId){
		return sqlSession.delete("omsCommSysParaBase.delete_omsCommSysPara", seqId);
	}
	
	//TODO ____续写...OmsCommSysParaDao
	
}
