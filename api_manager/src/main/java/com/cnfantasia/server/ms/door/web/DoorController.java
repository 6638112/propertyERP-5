package com.cnfantasia.server.ms.door.web;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.channelPartner.entity.ChannelPartner;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.ms.channelPartner.service.IChannelPartnerService;
import com.cnfantasia.server.ms.door.entity.DoorVerifyAndPaymentDto;
import com.cnfantasia.server.ms.door.entity.DoorVerifyAndPaymentParam;
import com.cnfantasia.server.ms.door.service.IDoorService;
import com.cnfantasia.server.ms.propertyCompany.service.IPropertyCompanyService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.session.UserContext;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 门牌验证缴费
 * 
 * @author liyulin
 * @version 1.0 2016年7月14日 上午10:58:09
 */
@Controller
@RequestMapping("/door")
public class DoorController extends BaseController {

	@Resource
	private IDoorService doorService;
	@Resource
	private IPropertyCompanyService propertyCompanyService;
	@Resource
	private IChannelPartnerService channelPartnerService;

	/**
	 * 门牌验证缴费页面查询
	 * 
	 * @param doorVerifyAndPaymentParam
	 * @param request
	 * @return
	 */
	@RequestMapping("/doorVerifyAndPaymentIndex.html")
	public ModelAndView doorVerifyAndPaymentIndex(DoorVerifyAndPaymentParam doorVerifyAndPaymentParam, HttpServletRequest request) {
		Map<String, Object> paramMap = MapConverter.toMap(doorVerifyAndPaymentParam);
		filterByUserType(paramMap);
		paramMap.put("gbIdList", UserContext.getGbIdList());

		int total = doorService.selectDoorVerifyAndPaymentForCount(paramMap);
		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<DoorVerifyAndPaymentDto> doorVerifyAndPayments = doorService.selectDoorVerifyAndPaymentForList(paramMap);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("total", total);
		modelAndView.addObject("doorVerifyAndPayments", doorVerifyAndPayments);
		modelAndView.setViewName("/roomVerifyPayment/list");

		return modelAndView;
	}

	/**
	 * 结算申请页面列表导出
	 * 
	 * @param doorVerifyAndPaymentParam
	 * @param response
	 */
	@RequestMapping("/exportDoorVerifyAndPayment.html")
	public void exportDoorVerifyAndPayment(DoorVerifyAndPaymentParam doorVerifyAndPaymentParam, HttpServletResponse response) {
		Map<String, Object> paramMap = MapConverter.toMap(doorVerifyAndPaymentParam);
		filterByUserType(paramMap);

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String exportFileName = "门牌验证缴费情况_" + format.format(new Date());
		HSSFWorkbook workbook = doorService.exportDoorVerifyAndPayment(paramMap);

		ExcelUtil.commonExport(exportFileName, workbook, response);
	}

	/**
	 * 根据用户类型，过滤数据
	 * 
	 * @param map
	 */
	private void filterByUserType(Map<String, Object> map) {
		BigInteger userId = UserContext.getOperIdBigIntegerMustExist();

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("adminId", userId);
		List<PropertyCompany> propertyCompanys = propertyCompanyService.getPropertyCompanyByCondition(paramMap);
		if (propertyCompanys != null && propertyCompanys.size() > 0) {// 物业
			List<BigInteger> pcIds = new ArrayList<BigInteger>();
			for (PropertyCompany pc : propertyCompanys) {
				pcIds.add(pc.getId());
			}
			map.put("pcIds", pcIds);
		} else {
			paramMap.clear();
			paramMap.put("tOmsUserFId", userId);
			List<ChannelPartner> channelPartners = channelPartnerService.getChannelPartnerByCondition(paramMap);
			if (channelPartners != null && channelPartners.size() > 0) {// 代理
				List<BigInteger> cpIds = new ArrayList<BigInteger>();
				for (ChannelPartner cp : channelPartners) {
					cpIds.add(cp.getId());
				}
				map.put("cpIds", cpIds);
			} else {// 管理员、财务：不过滤，查询所有

			}
		}
	}

}
