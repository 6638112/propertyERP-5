/**   
* Filename:    OperationService.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午9:13:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.fileServer.service.IFileServerService;
import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.api.operation.dao.IOperationDao;
import com.cnfantasia.server.api.operation.entity.OperationConstantDataEntity;
import com.cnfantasia.server.api.operation.entity.OperationSignalWithDealEntity;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;

/**
 * Filename:    OperationService.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午9:13:53
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public class OperationService implements IOperationService{
	
	private IOperationDao operationDao;
	public void setOperationDao(IOperationDao operationDao) {
		this.operationDao = operationDao;
	}
	
	private ISysParamManager sysParamManager;
	public void setSysParamManager(ISysParamManager sysParamManager) {
		this.sysParamManager = sysParamManager;
	}
	
	private IFileServerService fileServerService;
	public void setFileServerService(IFileServerService fileServerService) {
		this.fileServerService = fileServerService;
	}
	
	@Override
	public OperationConstantDataEntity getOperationSignal(String code) {
		if(StringUtils.isEmpty(code)){return null;}
		List<String> codeList = new ArrayList<String>();
		codeList.add(code);
		List<OperationConstantDataEntity> resList =  getOperationMulti(codeList);
		return (resList==null||resList.size()<=0)?null:resList.get(0);
	}

	@Override
	public List<OperationConstantDataEntity> getOperationMulti(List<String> codeList) {
		if(codeList==null||codeList.size()<=0){return null;}
		MsPrizeActivity nowAct = null;
		if(codeList.contains(OperationDict.OperationCode.SHARE_SUPRISE_TXT) ||codeList.contains(OperationDict.OperationCode.SHARE_SUPRISE_PIC) ){
			nowAct = ApplicationContextBothUtil.getPrizeActivityService().getMsPrizeActivityCurrDoing();
		}
		List<OperationConstantDataEntity> resList = operationDao.selectOperationConstantDataMulti(codeList);
		if(nowAct!=null){//需要替换数据
			for(OperationConstantDataEntity tmpEntity:resList){
				if(tmpEntity.getCode().contains(OperationDict.OperationCode.SHARE_SUPRISE_TXT)||tmpEntity.getCode().contains(OperationDict.OperationCode.SHARE_SUPRISE_PIC)){
					tmpEntity.freshData(nowAct);
				}
			}
		}
		return resList;
	}

	@Override
	public OperationSignalWithDealEntity getOperationSignalWithDeal(String code) {
		OperationConstantDataEntity tmpEntity = getOperationSignal(code);
		String finalContent = getOperationConstantFinalContent(tmpEntity);
		OperationSignalWithDealEntity res = new OperationSignalWithDealEntity(tmpEntity, finalContent);
		return res;
	}
	
	@Override
	public String getOperationConstantFinalContent(OperationConstantDataEntity tmpEntity){
		String content = null;
		if(tmpEntity!=null){
			Integer dataType =  tmpEntity.getDataType();
			boolean isPriAct = StringUtils.isEmpty(tmpEntity.getPrizeContent())?false:true;
			if(dataType.compareTo(OperationDict.OperationConstantData_dataType.TEXT)==0){
				content = isPriAct?tmpEntity.getPrizeContent():tmpEntity.getContent();
			}else if(dataType.compareTo(OperationDict.OperationConstantData_dataType.PIC)==0){
				if(isPriAct){
					content = ApplicationContextBothUtil.getAbsolutePath(tmpEntity.getPrizeContent(), SysParamKey.PrizeActivity_Icon_BasePath, tmpEntity.getSys0UpdTime());
				}else{
					content = ApplicationContextBothUtil.getAbsolutePath(tmpEntity.getContent(), SysParamKey.OperationData_Pic_BasePath, tmpEntity.getSys0UpdTime());
//					String picUrl = tmpEntity.getContent();
//					String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.OperationData_Pic_BasePath);
//					content = StringUtils.isEmpty(picUrl)?FileServerConstant.DEFAULT_NULL_PIC_URL:fileServerService.getAccessUrl(iconBasePath+picUrl,tmpEntity);
				}
			}
		}
		return content;
	}
	
	@Override
	public List<String> getOperationConstantFinalContents(OperationConstantDataEntity tmpEntity){
		List<String> content = null;
		if(tmpEntity!=null){
			Integer dataType =  tmpEntity.getDataType();
			if(dataType.compareTo(OperationDict.OperationConstantData_dataType.TEXT)==0){
				content = Arrays.asList(tmpEntity.getContent().split(","));
			}else if(dataType.compareTo(OperationDict.OperationConstantData_dataType.PIC)==0){
				String picUrl = tmpEntity.getContent();
				String[] picUrls = picUrl.split(",");
				String iconBasePath = sysParamManager.getSysParaValue(SysParamKey.OperationData_Pic_BasePath);
				
				content = new ArrayList<String>();
				for(String tempStr : picUrls) {
					if(!StringUtils.isEmpty(picUrl)) {
						content.add(fileServerService.getAccessUrl(iconBasePath+tempStr,tmpEntity));
					}
				}
			}
		}
		return content;
	}
	
}
