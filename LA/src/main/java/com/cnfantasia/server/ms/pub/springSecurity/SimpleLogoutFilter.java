package com.cnfantasia.server.ms.pub.springSecurity;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.ms.pub.constant.FocConstants;
import com.cnfantasia.server.ms.pub.logger.DbLogProcessInterceptor;
import com.cnfantasia.server.ms.pub.service.ILogService;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 描述: 拦截记录退出日志
 * 
 */

public class SimpleLogoutFilter implements Filter {

  /** 日志对象 */
  private Log logger = LogFactory.getLog(SimpleLogoutFilter.class);

  /** 退出交易URI资源 */
  private String logouturl;

  /** 已访问标识 */
  private static final String FILTERED_LOGOUTREQUEST = "_logout_filtered_request";

  private ILogService logService;
  public void setLogService(ILogService logService) {
    this.logService = logService;
  }
  /**
   * 执行过滤
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    logger.debug("EbhLogoutFilter.doFilter()");
    // 保证该过滤器在一次请求中只被调用一次
    if (request != null && request.getAttribute(FILTERED_LOGOUTREQUEST) != null) {
      chain.doFilter(request, response);
    } else {
      // 设置过滤标识，防止一次请求多次过滤
      request.setAttribute(FILTERED_LOGOUTREQUEST, Boolean.TRUE);
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String operId = UserContext.getOperId();
      String requestUri = httpRequest.getRequestURI();
      // 用户退出判断
      if (operId != null && isURILogout(requestUri, httpRequest)) {
        logger.info("----the logout operID is:" + operId);
        // 记录退出日志
        String struri = httpRequest.getRequestURI();
        try {
          logoutControl(operId, struri);
        } catch (Exception e) {
          logger.error("----error:" + operId + "," + e.getMessage() + "," + struri, e);
        }
      } else {
        // if(!getAfterSecurity2().contains(requestUri)){
        // //syl--add
        // HttpServletRequest request2=(HttpServletRequest)request;
        // request2.getRequestDispatcher(loginPage).forward(request, response);
        // //HttpServletResponse response2=(HttpServletResponse)response;
        // //response2.sendRedirect(loginPage);
        // return;
        // }
      }
      chain.doFilter(request, response);
    }
  }

  /**
   * 当前URI资源是否为退出
   * @param requestURI 请求地址
   * @param request
   * @return
   */
  private boolean isURILogout(String requestURI, HttpServletRequest request) {
    String contextPath = request.getContextPath();
    if (contextPath.equalsIgnoreCase(requestURI) || (contextPath + "/").equalsIgnoreCase(requestURI)) {
      return false;
    }
    // 比对，是否包含指定的路径
    if (requestURI != null && requestURI.indexOf(logouturl) >= 0) {
      return true;
    }
    return false;
  }

  public void destroy() {
  }

  /*
   * 
   * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
   */
  public void init(FilterConfig arg0) throws ServletException {
  }

  /**
   * 判断记录退出日志
   * @param operId 操作员编号
   * @param funcAction 请求地址
   * @throws Exception
   */
  public void logoutControl(String operId, String funcAction) throws Exception {
    String logDesc = DbLogProcessInterceptor.getLogDesc(funcAction); // 数据库字段约束不能为空
    // 判断 是否需要记录日志，不记录日志直接返回
    if (logDesc == null) { // 没有配置对应的记录
      return;
    }
    HashMap<String, Object> logobj = new HashMap<String, Object>();
    // LogObject logobj = new LogObject();
    String errCode = ""; // 错误代码
    String errMessage = ""; // 错误信息
    String tranStatus = FocConstants.TRAN_STATUS_SUCCESSFUL; // 交易状态，默认成功
    // 日志对象赋值
    logobj.put("custMgrNumber", operId);
    logobj.put("funcAction", funcAction);
    logobj.put("logDesc", logDesc);
    logobj.put("tranStatus", tranStatus);
    logobj.put("tranRetcode", errCode);
    logobj.put("tranRetmsg", errMessage);
    logger.info("----write log, tranStatus：" + tranStatus + ", operId：" + operId + ", funcAction：" + funcAction + ", logDesc：" + logDesc
        + ", errCode：" + errCode + ", errMessage：" + errMessage);
    // 记录日志
    logService.insertLog(logobj);
  }

  /**
   * @param logouturl the logouturl to set
   */
  public void setLogouturl(String logouturl) {
    this.logouturl = logouturl;
    if (logouturl == null || logouturl.trim().length() <= 0) {
      logger.warn("----Not found property config: logouturl.");
    }
  }

}
