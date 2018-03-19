package com.cnfantasia.server.domainbase.msPrizeOptionCity.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeOptionCity.entity.MsPrizeOptionCity;

/**
 * 描述:(奖项城市关系表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMsPrizeOptionCityBaseDao {
	/**
	 * 根据条件查询(奖项城市关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeOptionCity> selectMsPrizeOptionCityByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(奖项城市关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MsPrizeOptionCity> selectMsPrizeOptionCityByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(奖项城市关系表)信息
	 * @param id
	 * @return
	 */
	public MsPrizeOptionCity selectMsPrizeOptionCityBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(奖项城市关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMsPrizeOptionCityCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(奖项城市关系表)新增一条记录
	 * @param msPrizeOptionCity
	 * @return
	 */
	public int insertMsPrizeOptionCity(MsPrizeOptionCity msPrizeOptionCity);
	
	/**
	 * 批量新增(奖项城市关系表)信息
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
	 * 根据Id 批量删除(奖项城市关系表)信息_逻辑删除
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
