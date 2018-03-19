package com.cnfantasia.server.ms.car.dao;

import java.util.List;
import java.util.Map;

import com.cnfantasia.server.ms.car.entity.AutoCompleteEntity;
import com.cnfantasia.server.ms.car.entity.CarEntity;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

/**
 * 车辆管理
 * 
 * @author liyulin
 * @version 1.0 2016年11月23日 下午5:14:00
 */
public class CarDao extends AbstractBaseDao implements ICarDao {
	
	/**
	 * 车辆管理首页数据查询
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<CarEntity> queryCarListForPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("car.select_car_list_with_page", paramMap);
	}
	
	/**
	 * 车辆管理首页数据数量
	 * 
	 * @param paramMap
	 * @return
	 */
	public int queryCarListForCount(Map<String, Object> paramMap){
		return sqlSession.selectOne("car.select_car_list_with_count", paramMap);
	}

	/**
	 * 根据小区名称查询小区信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<AutoCompleteEntity> qryGbByName(Map<String, Object> paramMap) {
		return sqlSession.selectList("car.select_gb_by_name", paramMap);
	}

	/**
	 * 根据楼栋名称、gbId查询楼栋信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<AutoCompleteEntity> qryBuildingByName(Map<String, Object> paramMap) {
		return sqlSession.selectList("car.select_building_by_name", paramMap);
	}

	/**
	 * 根据buildingId查询房间信息
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<AutoCompleteEntity> qryRealRoomByName(Map<String, Object> paramMap) {
		return sqlSession.selectList("car.select_realRoom_by_name", paramMap);
	}

}
