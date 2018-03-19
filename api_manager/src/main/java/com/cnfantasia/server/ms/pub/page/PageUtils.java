package com.cnfantasia.server.ms.pub.page;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;
import com.cnfantasia.server.ms.pub.omsSysParam.OmsSysParamManager;

/*
 * 分页工具类
 */
public class PageUtils {

	/**
	 * 往查询参数paramMap中添加分页请求参数信息
	 * 
	 * @author wenfq 2015-03-24
	 * 
	 * @param paramMap
	 *            查询参数，
	 * @return
	 */
	public static void addPageInfoToParam(HttpServletRequest request, Map paramMap) {
		// 页数的参数名 
		String pageIndexName = new org.displaytag.util.ParamEncoder("row").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		// 每页显示的条数 
		int pageSize = Integer.parseInt(OmsSysParamManager.getOmsSysParaValue(OmsSysParamKey.PageNum));
		// 当前页    
		int curPageIndex = StringUtils.isEmpty(request.getParameter(pageIndexName)) ? 0 : (Integer.parseInt(request.getParameter(pageIndexName)) - 1);

		paramMap.put(PageModel.BEGIN, pageSize * curPageIndex);
		paramMap.put(PageModel.LENGTH, pageSize);
	}
}
