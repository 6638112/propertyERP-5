package com.cnfantasia.server.ms.loan.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.loanProduct.entity.LoanProduct;
import com.cnfantasia.server.domainbase.loanProduct.service.ILoanProductBaseService;
import com.cnfantasia.server.domainbase.loanThird.entity.LoanThird;
import com.cnfantasia.server.domainbase.loanThird.service.ILoanThirdBaseService;
import com.cnfantasia.server.ms.loan.entity.LoanBuyLogEntity;
import com.cnfantasia.server.ms.loan.entity.LoanBuyLogReq;
import com.cnfantasia.server.ms.loan.service.LoanService;
import com.cnfantasia.server.ms.pub.BaseController;
import com.cnfantasia.server.ms.pub.utils.MapConverter;

/**
 * 借贷
 * 
 * @author liyulin
 * @version 1.0 2017年6月7日 下午4:06:47
 */
@Controller
@RequestMapping("/loan")
public class LoanController extends BaseController {

	@Autowired
	private LoanService loanService;
	@Autowired
	private ILoanThirdBaseService loanThirdBaseService;
	@Autowired
	private ILoanProductBaseService loanProductBaseService;

	/**
	 * 借贷订单管理
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/orderManager.html")
	public ModelAndView orderManager(LoanBuyLogReq loanBuyLogReq, HttpServletRequest request) {
		Map<String, Object> paramMap = MapConverter.toMap(loanBuyLogReq);

		// 订单总数
		int total = loanService.getLoanBuyLogWithCount(paramMap);

		// 页数的参数名 
		String pageIndexName = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = 10;
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);
		// 分页信息
		paramMap.put("_begin", pageSize * curPageIndex);
		paramMap.put("_length", pageSize);
		List<LoanBuyLogEntity> loanBuyLogEntities = loanService.getLoanBuyLogWithList(paramMap);

		// 借贷平台
		List<LoanThird> loanThirdList = loanThirdBaseService.getLoanThirdByCondition(null);
		// 产品类型
		List<LoanProduct> loanProductList = loanProductBaseService.getLoanProductByCondition(null);

		ModelAndView modelAndView = new ModelAndView("/loan/orderManager");
		modelAndView.addObject("loanThirdList", loanThirdList);
		modelAndView.addObject("loanProductList", loanProductList);
		modelAndView.addObject("total", total);
		modelAndView.addObject("loanBuyLogEntities", loanBuyLogEntities);

		return modelAndView;
	}

}
