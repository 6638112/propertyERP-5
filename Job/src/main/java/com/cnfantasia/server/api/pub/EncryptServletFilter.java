package com.cnfantasia.server.api.pub;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.commonBusiness.util.RequestParseUtil;
import com.cnfantasia.server.api.pub.header.HeaderConstant;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.version.util.VersionTransferUtil;
import com.cnfantasia.server.commbusi.encrypt.constant.EncryptConstant;
import com.cnfantasia.server.commbusi.encrypt.constant.EncryptDict;
import com.cnfantasia.server.commbusi.encrypt.entity.EncryptUrlEntity;
import com.cnfantasia.server.commbusi.encrypt.service.IEncryptService;
import com.cnfantasia.server.commbusi.encrypt.util.EncryptUtil;
import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.json.JsonResponse;

public class EncryptServletFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

//	private Log logger = LogFactory.getLog(getClass());

	/** 加密处理Service */
	private IEncryptService encryptService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encryptService = ApplicationContextBothUtil.getEncryptService();
	}

	@Override
	public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse, FilterChain paramFilterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;
//		Enumeration pEnum = request.getParameterNames();
//		while(pEnum.hasMoreElements()){
//			Object tmp1 = pEnum.nextElement();
//			System.out.println("=--sss"+tmp1);
//			System.out.println(request.getParameter((String)tmp1));
//		}
		//1.获取请求的url
		String reqUrl = RequestParseUtil.parseUrlFromRequest(request);
		
		String versionStr =  request.getHeader(HeaderConstant.Header_Param.HEADER_PARAM_NAME_VERSION);
		Long version = null;
		try {version = VersionTransferUtil.versionStr2Long(versionStr);} catch (Exception e) {}
		
		//2.根据当前请求的url及版本号,判断是否匹配,取出加密方式
		EncryptUrlEntity encryptUrlEntity = encryptService.getEncrypt(reqUrl, version);
		
		boolean validRes = false;
		//3.根据加密方式进行校验
		if(encryptUrlEntity!=null){
			String encryptUrl = encryptUrlEntity.getUrl();
			Integer signType = encryptUrlEntity.getSignType();
			boolean isAllColumn = EncryptDict.EncryptUrl_AllColumn.YES.compareTo(encryptUrlEntity.getAllColumn())==0?true:false;//取数据库配置
			
			Map<String,String> paramMap = null;
			Map<String,String> reqParamMap = getRequestMap(request);
			
			if(isAllColumn){
				paramMap = reqParamMap;
			}else{
				List<String> columnList = encryptUrlEntity.getColumList();
				if(columnList!=null&&columnList.size()>0){
					paramMap = new HashMap<String, String>();
					for(String tmpColumn:columnList){
						paramMap.put(tmpColumn, reqParamMap.get(tmpColumn));
					}
				}
			}
			
			String toValidSign = request.getParameter(EncryptConstant.PARAM_TOKEN);
			validRes = encryptService.validate(encryptUrl, paramMap, signType,toValidSign);
		}else{
			validRes = true;
		}
		
		if(validRes){
			paramFilterChain.doFilter(request, response);
			return;
		}else{//4.校验不通过则提示失败信息
			paramServletResponse.setContentType("text/json");
			paramServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(constructErrorInfo());
			out.flush();
			out.close();
			return;
		}
		
	}
	
	
	private String constructErrorInfo(){
		JsonResponse jsonResponse = new JsonResponse();
		String status = CommConstants.ResponseStatus.BUSINESS_FAILED;// status状态码
		String errCode = CommConstants.DEFAULT_SYS_ERRCODE;// errCode
		String errorMsg = "请求数据有误！";// errMessage
		{
			jsonResponse.setStatus(status);
			jsonResponse.setErrcode(errCode);
			jsonResponse.setMessage(errorMsg);
		}
		return JSON.toJSONString(jsonResponse);
	}
	
	private Map<String, String> getRequestMap(HttpServletRequest request) {
		return EncryptUtil.getRequestMap(request.getParameterMap());
	}
	
}
