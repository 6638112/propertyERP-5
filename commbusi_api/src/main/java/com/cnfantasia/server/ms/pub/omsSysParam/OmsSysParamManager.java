package com.cnfantasia.server.ms.pub.omsSysParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.business.pub.omsSysParam.IOmsSysParamManager;
import com.cnfantasia.server.business.pub.omsSysParam.IOmsSysParamService;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;

/**
 * 描述:系统参数管理类
 * 
 * @version 1.00
 * @author syl
 * 
 */
public class OmsSysParamManager implements IOmsSysParamManager {
  /** 系统参数表的参数码 */
  public static final String SYSPARA_CODE = "sysParaCode";
  /** 系统参数的参数值 */
  public static final String SYSPARA_VALUE = "sysParaValue";
  
	private static Map<String, String> omsSysParamMap;//系统参数map缓存
  
	@Resource
	private IOmsSysParamService omsSysParamService;
  
  /**
   * 初始化
   */
  public void init(){
    List<Map<String, Object>> sysParamListMap = omsSysParamService.getAllSysParamListMap();
    omsSysParamMap=new HashMap<String, String>();
    for(Map<String, Object> tmpMap:sysParamListMap){
      omsSysParamMap.put((String)tmpMap.get(SYSPARA_CODE), (String)tmpMap.get(SYSPARA_VALUE));
    }
  }
  
  /**
   * 查询某个系统参数码对应的参数值
   * @param sysParaCode 系统参数码
   * @return 参数值
   */
	public String getSysParaValue(String sysParaCode) {
  	if(!StringUtils.isEmpty(sysParaCode)){//过滤空格
  		sysParaCode = sysParaCode.trim();
  	}
    return omsSysParamMap.get(sysParaCode);
  }
  
  	/**
	 * 查询某个系统参数码对应的参数值(静态方法)
	 * 
	 * @param sysParaCode
	 *            系统参数码
	 * @return 参数值
	 */
	public static String getOmsSysParaValue(String sysParaCode) {
		if (!StringUtils.isEmpty(sysParaCode)) {//过滤空格
			sysParaCode = sysParaCode.trim();
		}
		return omsSysParamMap.get(sysParaCode);
	}

	/**
	 * 获取图片服务器地址
	 * @param basePicPath 图片的文件夹路径
	 * @return 图片服务器地址
     */
	public static String getImageServerUrl(String basePicPath) {
		String privatePicBasePathList = getOmsSysParaValue(OmsSysParamKey.PrivatePicBasePathList);
		if (StringUtils.isEmpty(privatePicBasePathList)
				|| StringUtils.isEmpty(basePicPath) ||
				!privatePicBasePathList.contains(basePicPath)) {
			return getOmsSysParaValue(OmsSysParamKey.IMAGE_SERVER_URL);
		}
		return getOmsSysParaValue(OmsSysParamKey.PrivateImageServerUrl);
	}

	/**
	 * 更新重新加载系统参数信息到缓存
	 */
  public void updSysParaValue(){
    init();
  }
  
}
