package com.cnfantasia.server.ms.car.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.carNumList.entity.CarNumList;
import com.cnfantasia.server.domainbase.carNumList.service.ICarNumListBaseService;
import com.cnfantasia.server.ms.car.entity.CarEntity;
import com.cnfantasia.server.ms.car.service.ICarService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 车辆管理
 * 
 * @author liyulin
 * @version 1.0 2016年11月23日 下午5:12:20
 */
@Controller
@RequestMapping("/car")
public class CarController extends BaseController{
	
	@Resource
	private ICarService carService;
	@Resource
	private ICarNumListBaseService carNumListBaseService;

	/**
	 * 车辆管理首页
	 * 
	 * @param car
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request){
		String carNum = ParamUtils.getString(request, "carNum", null);
		String park = ParamUtils.getString(request, "park", null);
		String gbName = ParamUtils.getString(request, "gbName", null);
		String buildingName = ParamUtils.getString(request, "buildingName", null);
		String roomName = ParamUtils.getString(request, "roomName", null);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("carNum", carNum);
		paramMap.put("park", park);
		paramMap.put("gbName", gbName);
		paramMap.put("buildingName", buildingName);
		paramMap.put("roomName", roomName);
		paramMap.put("gbIdList", UserContext.getGbIdList());
		
		int total = carService.queryCarListForCount(paramMap);
		
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<CarEntity> carEntities = carService.queryCarListForPage(paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("total", total);
		modelAndView.addObject("carEntities", carEntities);
		
		modelAndView.setViewName("/car/list");
		return modelAndView;
	}
	
	/**
	 * 根据小区名称查询小区信息
	 * 
	 * @param gbName
	 * @return
	 */
	@RequestMapping("/qryGbByName.html")
	@ResponseBody
	public JsonResponse qryGbByName(String gbName){
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(carService.qryGbByName(gbName));
		return jsonResponse;
	}
	
	/**
	 * 根据楼栋名称、gbId查询楼栋信息
	 * 
	 * @param buildingName
	 * @param gbId
	 * @return
	 */
	@RequestMapping("/qryBuildingByName.html")
	@ResponseBody
	public JsonResponse qryBuildingByName(String buildingName, String gbId){
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(carService.qryBuildingByName(buildingName, gbId));
		return jsonResponse;
	}
	
	/**
	 * 根据buildingId查询房间信息
	 * 
	 * @param buildingId
	 * @param roomName
	 * @return
	 */
	@RequestMapping("/qryRealRoomByName.html")
	@ResponseBody
	public JsonResponse qryRealRoomByName(String buildingId, String roomName){
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setDataValue(carService.qryRealRoomByName(buildingId, roomName));
		return jsonResponse;
	}
	
	/**
	 * 插入车牌
	 * 
	 * @param carNumList
	 * @return
	 */
	@RequestMapping("/addCar.html")
	@ResponseBody
	public JsonResponse addCar(CarEntity car){
		CarNumList carNumList = new CarNumList();
		carNumList.settCarNum(car.getCarNum());
		carNumList.settGroupBuildingFId(new BigInteger(car.getPark()));
		carNumList.setRealRoomId(new BigInteger(car.getRoomName()));
		carNumList.setFee(car.getFee().multiply(BigDecimal.valueOf(100)).longValue());
		carNumList.setExpireDate(car.getExpireDate());
		
		carNumList.setStatus(1);
		carNumList.setLockStatus(0);
		carNumList.setSys0AddUser(UserContext.getOperIdBigIntegerMustExist());
		carNumList.setSys0AddTime(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
		carNumList.setSys0DelState(0);
		return carService.addCar(carNumList);
	}
	
	/**
	 * 删除车牌
	 * 
	 * @param carId
	 * @return
	 */
	@RequestMapping("/delCar.html")
	@ResponseBody
	public JsonResponse delCar(BigInteger carId){
		JsonResponse jsonResponse = new JsonResponse();
		if(carService.delCar(carId, UserContext.getOperIdBigIntegerMustExist())){
			jsonResponse.setMessage("操作成功！");
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
	
	/**
	 * 设置车辆来源
	 * 
	 * @param carId
	 * @return
	 */
	@RequestMapping("/setFrom.html")
	@ResponseBody
	public JsonResponse setFrom(BigInteger carId, Integer from){
		JsonResponse jsonResponse = new JsonResponse();
		CarNumList carNumList = new CarNumList();
		carNumList.setId(carId);
		carNumList.setFrom(from);
		carNumList.setSys0UpdUser(UserContext.getOperIdBigIntegerMustExist());
		carNumList.setSys0UpdTime(DateUtils.getCurrentDate());
		
		if(carNumListBaseService.updateCarNumList(carNumList)>0){
			jsonResponse.setMessage("操作成功！");
		} else {
			jsonResponse.setMessage("操作失败！");
			jsonResponse.setStatus(CommConstants.ResponseStatus.BUSINESS_FAILED);
		}
		return jsonResponse;
	}
}
