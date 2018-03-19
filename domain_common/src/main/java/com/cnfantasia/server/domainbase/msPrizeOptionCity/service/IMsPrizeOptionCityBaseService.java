package com.cnfantasia.server.domainbase.msPrizeOptionCity.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.msPrizeOptionCity.entity.MsPrizeOptionCity;

/**
 * 描述:(奖项城市关系表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeOptionCityBaseService {
	
	/**
	 * 根据条件查询(奖项城市关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(奖项城市关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(奖项城市关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(奖项城市关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<MsPrizeOptionCity> getMsPrizeOptionCityByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(奖项城市关系表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeOptionCity getMsPrizeOptionCityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(奖项城市关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeOptionCityCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(奖项城市关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getMsPrizeOptionCityCountDim(Map<String,Object> paramMap);
	/**
	 * 往(奖项城市关系表)新增一条记录
	 * @param msPrizeOptionCity
	 * @return
	 */
	public int insertMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity);
	/**
	 * 批量新增(奖项城市关系表)
	 * @param msPrizeOptionCityList
	 * @return
	 */
	public int insertMsPrizeOptionCityBatch(List<MsPrizeOptionCity> msPrizeOptionCityList);
	/**
	 * 更新(奖项城市关系表)信息
	 * @param msPrizeOptionCity
	 * @return
	 */
	public int updateMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity);
	/**
	 * 批量更新(奖项城市关系表)信息
	 * @param msPrizeOptionCityList
	 * @return
	 */
	public int updateMsPrizeOptionCityBatch(List<MsPrizeOptionCity> msPrizeOptionCityList);
	/**
	 * 根据序列号删除(奖项城市关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMsPrizeOptionCityLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(奖项城市关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMsPrizeOptionCityLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(奖项城市关系表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMsPrizeOptionCity(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(奖项城市关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMsPrizeOptionCityBatch(List<java.math.BigInteger> idList);
	
}
