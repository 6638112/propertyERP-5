/**   
* Filename:    FileServerParamParser.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午2:21:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commSysPara.parser;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.entity.FileServerConfigEntity;

/**
 * Filename:    FileServerParamParser.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午2:21:52
 * Description: 文件服务器参数信息解析
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
public class FileServerParamParser extends AbstractSysParamParser{

	@SuppressWarnings("unchecked")
	@Override
	protected FileServerConfigEntity doParse(String sysParamValue) {
		String[] config =sysParamValue.split(";");
		String accessBaseUrl=config[0];
		String protocolType=config[1];
		String ip=config[2];
		String port=config[3];
		String userName=config[4];
		String password=config[5];
		String localFileDir=config[6];
		FileServerConfigEntity fileServerConfigEntity= new FileServerConfigEntity(accessBaseUrl, protocolType, ip, port, userName, password,localFileDir);
		return fileServerConfigEntity;
	}

	@Override
	protected String getSysParamKey() {
		return SysParamKey.FILE_SERVER_CONFIG;
	}
	
}
