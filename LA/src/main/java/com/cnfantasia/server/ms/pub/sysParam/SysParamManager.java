package com.cnfantasia.server.ms.pub.sysParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:系统参数管理类
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class SysParamManager {
  /** 系统参数表的参数码 */
  public static final String SYSPARA_CODE = "sysParaCode";
  /** 系统参数的参数值 */
  public static final String SYSPARA_VALUE = "sysParaValue";
  
  private static Map<String,String> sysParamMap;//系统参数map缓存
  
  private static ISysParamService sysParamService;
  
  public static void setSysParamService(ISysParamService sysParamService) {
		SysParamManager.sysParamService = sysParamService;
	}

  
  /**
   * 初始化
   */
  public static void init(){
    List<Map<String, Object>> sysParamListMap = sysParamService.getAllSysParamListMap();
    sysParamMap=new HashMap<String, String>();
    for(Map<String, Object> tmpMap:sysParamListMap){
      sysParamMap.put((String)tmpMap.get(SYSPARA_CODE), (String)tmpMap.get(SYSPARA_VALUE));
    }
  }
  
  /**
   * 查询某个系统参数码对应的参数值
   * @param sysParaCode 系统参数码
   * @return 参数值
   */
  public static String getSysParaValue(String sysParaCode){
    return sysParamMap.get(sysParaCode);
  }
  
  /**
   * 更新重新加载系统参数信息到缓存
   */
  public static void updSysParaValue(){
    init();
  }
  
}
