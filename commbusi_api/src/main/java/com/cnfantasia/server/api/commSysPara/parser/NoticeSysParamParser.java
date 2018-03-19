/**
 * 
 */
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;

/**
 * 类说明：
 *
 * @author hunter
 * @since 2014年6月6日上午10:58:25
 */
public class NoticeSysParamParser extends AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected String doParse(String sysParamValue) {
		return sysParamValue;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.NOTICE_PIC_PATH;
	}

}
