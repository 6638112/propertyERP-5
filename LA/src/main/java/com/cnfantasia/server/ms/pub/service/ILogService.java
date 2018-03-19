package com.cnfantasia.server.ms.pub.service;

import java.util.HashMap;
import java.util.List;

/**
 * 描述:日志处理服务类
 * 
 * @version 1.00
 * @author syl
 * 
 */
public interface ILogService {
  /**
   * 获取日志控制列表信息
   * @return
   */
  public List<HashMap<String, Object>> getLogControlList();
  /**
   * 新增日志
   * @param logobj
   */
  public void insertLog(HashMap<String, Object> logobj);
  
  /**
   * 通过Uri获取要记录日志的action
   * @param srcUri
   * @return
   */
  public String getRealFuncUrl(String srcUri);
  
}
