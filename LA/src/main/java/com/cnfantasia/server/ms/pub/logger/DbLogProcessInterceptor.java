package com.cnfantasia.server.ms.pub.logger;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.ms.pub.constant.FocConstants;
import com.cnfantasia.server.ms.pub.service.ILogService;
import com.cnfantasia.server.ms.pub.session.UserContext;

/**
 * 描述: 拦截，匹配指定的url记录日志
 * 
 * @version 1.00
 * 
 */

public class DbLogProcessInterceptor extends HandlerInterceptorAdapter {

  /** 日志对象 */
  private static Log logger = LogFactory.getLog(DbLogProcessInterceptor.class);
  /** 日志控制列表 */
  private static Map<String, LinkedList<String>> mapLogControl;
  public static final String CODE_KEY = "--CODE_KEY--";
  public static final String ARGS_KEY = "--ARGS_KEY--";
  
  private ILogService logService;
  
  public ILogService getLogService() {
		return logService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;//TODO spring注入的配置
	}

	/**
   * 初始化日志控制列表
   */
  private void initListLog() {
    List<HashMap<String, Object>> resListMap = getLogService().getLogControlList();
    if (resListMap == null) {
      mapLogControl = null;
    } else {
      mapLogControl = new HashMap<String, LinkedList<String>>();
      for (HashMap<String, Object> tmpMap : resListMap) {
        LinkedList<String> tmpList = new LinkedList<String>();
        tmpList.add(0, (String) (tmpMap.get("description")));// 描述
        tmpList.add(1, (String) (tmpMap.get("ext1")));// 是否记录参数信息
        mapLogControl.put((String) (tmpMap.get("sourceUrl")), tmpList);
      }
    }
    if (mapLogControl == null) {
      logger.info("----The map_log_control is null.");
      return;
    }
    if (mapLogControl.isEmpty()) {
      logger.info("----The map_log_control is isEmpty.");
    }
  }

  /**
   * see the HandlerInterceptorAdapter 执行于action之前
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    return true;
  }

  /**
   * see the HandlerInterceptorAdapter 执行于action之后
   */
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    return;
  }

  /**
   * see the HandlerInterceptorAdapter 最后执行
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    // 请求uri
    String srcUri = request.getRequestURI();
    String funcAction = getLogService().getRealFuncUrl(srcUri);
//    if (mapLogControl == null) { // 第一次取回所有配置记录
//      initListLog();
//    }
    // 判断 是否需要记录日志，不记录日志直接返回
    String logDesc = getLogDesc(funcAction);// 数据库字段约束不能为空
    if (logDesc == null) {//没有配置对应的记录
      return;
    }
    // 日志对象
    HashMap<String, Object> logobj = new HashMap<String, Object>();
    String errCode = (String) request.getAttribute(CODE_KEY); // 错误代码
    Object[] errDatas = (Object[]) request.getAttribute(ARGS_KEY); // 错误参数
    String transResult = (String) request.getAttribute(FocConstants.TRANS_RESULT); // 获取业务人员设定的交易状态
    if (transResult == null || transResult.trim().length() <= 0) {// 开发人员未指定交易
      logger.debug("----开发人员未指定交易--");
      if (errCode == null || errCode.trim().length() <= 0) {
        transResult = FocConstants.TRAN_STATUS_SUCCESSFUL;
      } else {
        transResult = FocConstants.TRAN_STATUS_FAILED;
      }
    } else if (FocConstants.TRAN_STATUS_SUCCESSFUL.equals(transResult)) {
      logger.debug("----开发人员指定为成功交易----");
      transResult = FocConstants.TRAN_STATUS_SUCCESSFUL;
    } else {
      logger.debug("----开发人员指定为错误交易或其它未知的交易----");
      transResult = FocConstants.TRAN_STATUS_FAILED;// 设定为失败
    }
    // 获取错误信息
    String errMessage = (String) request.getAttribute(FocConstants.ERRMESSAGE);
    if (errMessage == null || errMessage.trim().length() <= 0) {
      // 如果错误信息为空,而错误码不为空
      if (errCode != null && errCode.trim().length() > 0) {
        // 取到错误码对应的错误 信息
        errMessage = MessageSourceUtil.getMessage(errCode, errDatas);
      } else {
        errMessage = FocConstants.DEFAULT_SYS_ERRMESSAGE;// 取默认信息
      }
    }

    // 员工编号
    String operId = UserContext.getOperId();
    if (operId == null) {
      // 如果用户获取失败，尝试从登录时验证码过滤器存储的session信息中获取，
      // 原因是springSecurity登录失败后不保存用户名信息
      operId = (String) request.getSession(true).getAttribute(FocConstants.Logn_Session_LoginName);
      if (operId == null) {
        operId = "null";
      }
    }
    // 日志对象赋值
    logobj.put("userName", operId);
    logobj.put("sourceUrl", srcUri);
    logobj.put("logDesc", logDesc);
    if (FocConstants.TRAN_STATUS_SUCCESSFUL.equals(transResult)) {
      logobj.put("tranStatus", "0");// 0成功
    } else {
      logobj.put("tranStatus", "1");// 1失败
    }
    logobj.put("tranRetcode", errCode);
    logobj.put("tranRetmsg", errMessage);
    try {
      if (mapLogControl.get(funcAction) == null || !"1".equals(mapLogControl.get(funcAction).get(1))) {// 1表示不记录参数信息
        String paramStr = JSON.toJSONString(request.getParameterMap());
        byte[] paramBytes = paramStr.getBytes();
        int currLen = paramBytes.length;
        if (currLen > 2000) {// 超过长度，截取之前的
          byte[] newParam = new byte[2000];
          for (int i = 0; i < currLen; i++) {
            newParam[i] = paramBytes[i];
          }
          paramStr = new String(newParam);
        }
        logobj.put("ext1", paramStr);// 请求的参数信息
      }
      // 记录日志
      getLogService().insertLog(logobj);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

  }

  /**
   * 判断 是否需要记录日志
   * @param funcAction 请求地址
   * @return null不需要，非空需要
   */
  public static String getLogDesc(String funcAction) {
    String logDesc = null;
    if (mapLogControl != null && mapLogControl.get(funcAction) != null) {
      logDesc = mapLogControl.get(funcAction).get(0);
    }
    return logDesc;
  }
  /**
   * 更新日志控制信息
   */
  public void updateLogControl(){
    initListLog();
  }
}
