package com.cnfantasia.server.ms.buildingKeyConfig.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;

/**
 * 门禁认证选项配置管理Dao
 *
 * @author Liyl
 * @version 1.0 2016年3月22日 下午5:57:52
 */
public class BuildingKeyConfigDao extends AbstractBaseDao implements IBuildingKeyConfigDao {

	/**
	 * 根据条件查询满足条件的(门禁认证选项显示配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBuildingKeyConfigCount(String groupBuildingId){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put(QUERY_TYPE_IF_DIM, true);
		paramMap.put("tGroupBuildingFId", groupBuildingId);
		return sqlSession.selectOne("buildingKeyConfigBase.select_buildingKeyConfig_count", paramMap);
	}
	
	/**
	 * 新增配置信息
	 * @param buildingKeyConfigList
	 * @return
	 */
	@Override
	public int insertBuildingKeyConfigBatch(List<BuildingKeyConfig> buildingKeyConfigList){
		return sqlSession.insert("buildingKeyConfigBase.insert_buildingKeyConfig_Batch",buildingKeyConfigList);
	}
	
	/**
	 * 根据小区id查询配置
	 * @param groupBuildingId
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(String groupBuildingId){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(QUERY_TYPE_IF_DIM, true);
		paramMap.put("tGroupBuildingFId", groupBuildingId);
		return sqlSession.selectList("buildingKeyConfigBase.select_buildingKeyConfig",paramMap);
	}
	
	/**
	 * 根据Id 批量删除(门禁认证选项显示配置表)
	 * @param idList
	 * @return
	 */
	@Override
	public int deleteBuildingKeyConfigBatch(List<java.math.BigInteger> idList) {
		return sqlSession.update("buildingKeyConfigBase.delete_buildingKeyConfig_Batch",idList);
	}
	
	/**
	 * 根据id删除配置信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteBuildingKeyConfig(java.math.BigInteger id){
		return sqlSession.update("buildingKeyConfigBase.delete_buildingKeyConfig",id);
	}

	/**
	 * 配置项上移
	 * @param id
	 * @param order
	 * @return
	 */
	@Override
	public JsonResponse upOrder(BigInteger id, BigInteger order){
		// query the pre config:id、order
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Map<String, Object> preMap = sqlSession.selectOne("buildingKeyConfigBase.select_pre_item", params);
		
		JsonResponse jsonResponse = new JsonResponse();
		// top
		if (preMap == null || preMap.get("id") == null) {
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("该项已是第一个了，不能再上移了！");
			return jsonResponse;
		}

		// click too fast
		if (id.equals(preMap.get("id").toString())) {
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("您的点击速度太快，请稍后再试！");
			return jsonResponse;
		}
		
		// update this item order
		Map<String, Object> thisParams = new HashMap<String, Object>();
		thisParams.put("order", preMap.get("order"));
		thisParams.put("id", id);
		int affectedCount = sqlSession.update("buildingKeyConfigBase.update_buildingKeyConfig_order", thisParams);
		
		// update pre item order
		Map<String, Object> preParams = new HashMap<String, Object>();
		preParams.put("order", order);
		preParams.put("id", preMap.get("id"));
		affectedCount += sqlSession.update("buildingKeyConfigBase.update_buildingKeyConfig_order", preParams);
		
		if(affectedCount==2){
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			jsonResponse.setMessage("操作成功！");
		} else {
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("操作失败！");
		}
		return jsonResponse;
	}

	/**
	 * 配置项下移
	 * @param id
	 * @param order
	 * @return
	 */
	@Override
	public JsonResponse downOrder(BigInteger id, BigInteger order) {
		// query the next config:id、order
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Map<String, Object> nextMap = sqlSession.selectOne("buildingKeyConfigBase.select_next_item", params);
		
		JsonResponse jsonResponse = new JsonResponse();
		// bottom
		if (nextMap == null || nextMap.get("id") == null) {
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("该项已是最后一个了，不能再下移了！");
			return jsonResponse;
		}

		// click too fast
		if (id.equals(nextMap.get("id").toString())) {
			jsonResponse.setStatus(CommConstants.ResponseStatus.VALIDATE_ERR);
			jsonResponse.setMessage("您的点击速度太快，请稍后再试！");
			return jsonResponse;
		}
		
		// update this item order
		Map<String, Object> thisParams = new HashMap<String, Object>();
		thisParams.put("order", nextMap.get("order"));
		thisParams.put("id", id);
		int affectedCount = sqlSession.update("buildingKeyConfigBase.update_buildingKeyConfig_order", thisParams);
		
		// update pre item order
		Map<String, Object> preParams = new HashMap<String, Object>();
		preParams.put("order", order);
		preParams.put("id", nextMap.get("id"));
		affectedCount += sqlSession.update("buildingKeyConfigBase.update_buildingKeyConfig_order", preParams);
		
		if(affectedCount==2){
			jsonResponse.setStatus(CommConstants.ResponseStatus.SUCCESS);
			jsonResponse.setMessage("操作成功！");
		} else {
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
			jsonResponse.setMessage("操作失败！");
		}
		return jsonResponse;
	}

}
