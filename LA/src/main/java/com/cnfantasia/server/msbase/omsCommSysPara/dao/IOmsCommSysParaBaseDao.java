package com.cnfantasia.server.msbase.omsCommSysPara.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsCommSysPara.entity.OmsCommSysPara;

/**
 * 描述:(OMS系统参数表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsCommSysParaBaseDao {
	/**
	 * 根据条件查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS系统参数表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsCommSysPara> selectOmsCommSysParaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS系统参数表)信息
	 * @param seqId
	 * @return
	 */
	public OmsCommSysPara selectOmsCommSysParaBySeqId(String seqId);
	/**
	 * 根据条件查询满足条件的(OMS系统参数表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsCommSysParaCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS系统参数表)新增一条记录
	 * @param paramMap
	 * @return
	 */
	public int insertOmsCommSysPara(Map<String,Object> paramMap);
	/**
	 * 更新(OMS系统参数表)信息
	 * @param paramMap
	 * @return
	 */
	public int updateOmsCommSysPara(Map<String,Object> paramMap);
	/**
	 * 根据序列号删除(OMS系统参数表)信息
	 * @param seqId
	 * @return
	 */
	public int deleteOmsCommSysPara(String seqId);
	
	//TODO ____续写...IOmsCommSysParaDao
	
}
