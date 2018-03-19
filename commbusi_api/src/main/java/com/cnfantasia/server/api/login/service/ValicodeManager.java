/**
 * Filename:    ValicodeManager.java
 *
 * @version: 1.0
 * Create at:   2014年5月21日 下午12:20:55
 * Description:
 * <p>
 * Modification History:
 * Date        Author      Version     Description
 * -----------------------------------------------------------------
 * 2014年5月21日    shiyl      1.0         1.0 Version
 */
package com.cnfantasia.server.api.login.service;

import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.commSysPara.parser.ISysParamParser;
import com.cnfantasia.server.api.login.constant.LoginConstant;
import com.cnfantasia.server.api.login.entity.ValicodeEntity;
import com.cnfantasia.server.api.login.entity.ValicodeParamConfig;
import com.cnfantasia.server.api.pub.session.SessionManager;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.utils.RandomUtils;

/**
 * Filename:    ValicodeManager.java
 * @version: 1.0.0
 * Create at:   2014年5月21日 下午12:20:55
 * Description: 验证码处理工具类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月21日       shiyl             1.0             1.0 Version
 */
public class ValicodeManager implements IValicodeManager {
    private Log logger = LogFactory.getLog(ValicodeManager.class);
    private ISysParamParser valicodeSysParamParser;

    public void setValicodeSysParamParser(ISysParamParser valicodeSysParamParser) {
        this.valicodeSysParamParser = valicodeSysParamParser;
    }

    @Override
    public String generateValicode(Integer type, String phone) {
        String validateCode = null;
        {//判断是否存在
            ValicodeEntity valicodeEntity = getValicodefromSession(type);
            try {
                checkWithSession(type, phone, valicodeEntity.getValue());//校验是否可用
                validateCode = valicodeEntity.getValue();
                putValicode2Session(type, phone, validateCode);//重新存入，恢复有效时间
            } catch (Exception e) {
                String isMessageSend = CnfantasiaCommbusiUtil.getSysParaValue(SysParamKey.IS_MESSAGE_SEND);
                if ("0".equals(isMessageSend)) {
                    validateCode = "1234";
                } else {
                    validateCode = RandomUtils.createRandom(true, LoginConstant.DEFAULT_PHONE_VALICODE_LENGTH);
                }
            }
        }
        return validateCode;
    }


    /**
     * 存入验证码到Session中
     * @param type
     * @param value
     */
    @SuppressWarnings("unchecked")
    @Override
    public void putValicode2Session(Integer type, String phone, String value) {
        Map<Integer, ValicodeEntity> validateCodeMap = (Map<Integer, ValicodeEntity>) SessionManager.getSession().getAttribute(LoginConstant.SESSION_KEY_VALIDATE_CODE_MAP);
        if (validateCodeMap == null) {
            validateCodeMap = new HashMap<Integer, ValicodeEntity>();
        }
        validateCodeMap.put(type, new ValicodeEntity(type, phone, value));
        SessionManager.getSession().setAttribute(LoginConstant.SESSION_KEY_VALIDATE_CODE_MAP, validateCodeMap);
    }

    /**
     * 通过类型获取验证码
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public ValicodeEntity getValicodefromSession(Integer type) {
        Map<Integer, ValicodeEntity> validateCodeMap = (Map<Integer, ValicodeEntity>) SessionManager.getSession().getAttribute(LoginConstant.SESSION_KEY_VALIDATE_CODE_MAP);
        if (validateCodeMap == null) {
            return null;
        }
        return validateCodeMap.get(type);
    }

    /**
     * 通过类型从session中获取验证码实体，并校验结果
     * @param type
     * @return
     */
    @Override
    public void checkWithSession(Integer type, String phone, String goalValicode) {
        ValicodeEntity valicodeEntity = getValicodefromSession(type);
        ValicodeParamConfig valicodeParamConfig = valicodeSysParamParser.parseParamValue();
        if (valicodeEntity == null || type == null) {
            throw new ValidateRuntimeException("ValicodeManager.validate.valicodeEntity.isnull.error");
        }
        //校验type
        if (valicodeEntity.getType().compareTo(type) != 0) {
            throw new ValidateRuntimeException("ValicodeManager.validate.valicodeEntity.type.notEqual.error");
        }
        //校验超时时间
        if (System.currentTimeMillis() - valicodeEntity.getCreateTime() > valicodeParamConfig.getTimeOut()) {
            throw new ValidateRuntimeException("ValicodeManager.validate.valicodeEntity.timeOut.error");
        }
        //校验错误次数
        if (valicodeEntity.getErrCount() > valicodeParamConfig.getErrCount()) {
            throw new ValidateRuntimeException("ValicodeManager.validate.valicodeEntity.maxErrCount.error");
        }
        //校验取值是否匹配
        if (valicodeEntity == null || valicodeEntity.getValue() == null) {
            throw new ValidateRuntimeException("ValicodeManager.validate.valicodeEntity.sessionNotExist.error");
        } else if (!valicodeEntity.getValue().equals(goalValicode) || !valicodeEntity.getPhone().equals(phone)) {
            //手机号不同或者验证码不同
            valicodeEntity.addErrCount();
            if (logger.isDebugEnabled()) {
                logger.debug("Type of " + type + " 's current errcount is:" + getValicodefromSession(type).getErrCount());
            }
            throw new ValidateRuntimeException("ValicodeManager.validate.valicodeEntity.notMapper.error");
        }
//		return true;
    }

    /**
     * 清除验证码
     * @param type
     * @return
     */
    @Override
    public void clearValicode(Integer type) {
        putValicode2Session(type, null, null);
    }

}
