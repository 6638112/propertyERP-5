package com.cnfantasia.server.business.pub.omsSysParam;

import java.util.List;
import java.util.Map;

/**
 * 描述:系统参数处理接口类
 * 
 * @version 1.00
 * @author syl
 * 
 */
public interface IOmsSysParamService {
  public List<Map<String,Object>> getAllSysParamListMap();
}
