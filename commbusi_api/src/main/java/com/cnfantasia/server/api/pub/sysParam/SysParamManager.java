package com.cnfantasia.server.api.pub.sysParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.business.pub.sysParam.ISysParamService;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.ms.omsCommonSysParam.constant.OmsSysParamKey;

/**
 * 描述:系统参数管理类
 *
 * @author syl
 * @version 1.00
 */
public class SysParamManager implements ISysParamManager {
    /**
     * 系统参数表的参数码
     */
    public static final String SYSPARA_CODE = "sysParaCode";
    /**
     * 系统参数的参数值
     */
    public static final String SYSPARA_VALUE = "sysParaValue";

    private Map<String, String> sysParamMap;//系统参数map缓存

    private ISysParamService sysParamService;

    public void setSysParamService(ISysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }


    /**
     * 初始化
     */
    public void init() {
        List<Map<String, Object>> sysParamListMap = sysParamService.getAllSysParamListMap();
        sysParamMap = new HashMap<String, String>();
        for (Map<String, Object> tmpMap : sysParamListMap) {
            sysParamMap.put((String) tmpMap.get(SYSPARA_CODE), (String) tmpMap.get(SYSPARA_VALUE));
        }
    }

    /**
     * 查询某个系统参数码对应的参数值
     *
     * @param sysParaCode 系统参数码
     * @return 参数值
     */
    public String getSysParaValue(String sysParaCode) {
        if (!StringUtils.isEmpty(sysParaCode)) {//过滤空格
            sysParaCode = sysParaCode.trim();
        }
        return sysParamMap.get(sysParaCode);
    }

    /**
     * 获取图片服务器地址
     *
     * @param basePicPath 图片的文件夹路径
     * @return 图片服务器地址
     */
    public String getImageServerUrl(String basePicPath) {
        String privatePicBasePathList = getSysParaValue(OmsSysParamKey.PrivatePicBasePathList);
        if (StringUtils.isEmpty(privatePicBasePathList)
                || StringUtils.isEmpty(basePicPath) ||
                !privatePicBasePathList.contains(basePicPath)) {
            return getSysParaValue(SysParamKey.FILE_SERVER_CONFIG).split(";")[0];
        }
        return getSysParaValue(SysParamKey.PrivateImageServerUrl);
    }

    public void updateContextPath(String contextPath) {
        String value;
        for (String key : sysParamMap.keySet()) {
            value = sysParamMap.get(key);
            sysParamMap.put(key, value.replace("/API-version/", contextPath + "/"));
        }
    }

    /**
     * 更新重新加载系统参数信息到缓存
     */
    public void updSysParaValue() {
        init();
    }

}
