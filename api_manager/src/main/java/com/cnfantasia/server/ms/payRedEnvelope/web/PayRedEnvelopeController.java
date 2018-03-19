package com.cnfantasia.server.ms.payRedEnvelope.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.payRedEnvelope.entity.PayRedEnvelope;
import com.cnfantasia.server.ms.payRedEnvelope.entity.PcToEnvelopeQryParam;
import com.cnfantasia.server.ms.payRedEnvelope.service.IPayRedEnvelopeService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 代扣卡转粮票一览表
 * 
 * @author liyulin
 * @version 1.0 2016年11月7日 下午1:42:47
 */
@Controller
@RequestMapping("/payRedEnvelope")
public class PayRedEnvelopeController extends BaseController {

	@Resource
	private IPayRedEnvelopeService payRedEnvelopeService;

	@RequestMapping("/pcToEnvelopeHistory.html")
	public ModelAndView pcToEnvelopeHistory(PcToEnvelopeQryParam qryParam, HttpServletRequest request) {
		Map<String, Object> paramMap = MapConverter.toMap(qryParam);
		int total = payRedEnvelopeService.selectPcToEnvelopeHistoryCount(paramMap);
		Long totalMoney = payRedEnvelopeService.selectPcToEnvelopeTotalMoney(paramMap);
		if (totalMoney == null) {
			totalMoney = 0L;
		}

		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<PayRedEnvelope> redEnvelopeHistory = payRedEnvelopeService.selectPcToEnvelopeHistory(paramMap);

		ModelAndView mav = new ModelAndView();
		mav.addObject("redEnvelopeHistory", redEnvelopeHistory);
		mav.addObject("total", total);
		mav.addObject("totalMoney", totalMoney);
		mav.setViewName("/propertyCard/pcToEnvelopeHistory");
		return mav;
	}
}
