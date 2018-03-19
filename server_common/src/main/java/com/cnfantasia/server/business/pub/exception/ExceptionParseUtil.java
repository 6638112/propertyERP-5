/**   
 * Filename:    ExceptionParseUtil.java   
 * @version:    1.0  
 * Create at:   2014年6月10日 上午8:02:59   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月10日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.exception;

import com.cnfantasia.server.common.CommConstants;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.exception.IBaseException;
import com.cnfantasia.server.common.exception.TimeOutRuntimeException;
import com.cnfantasia.server.common.exception.ValidateRuntimeException;
import com.cnfantasia.server.common.messageSource.MessageSourceUtil;
import com.cnfantasia.server.common.utils.StringUtils;

/**
 * Filename: ExceptionParseUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年6月10日 上午8:02:59 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月10日 shiyl 1.0 1.0 Version
 */
public class ExceptionParseUtil {
	
	public static void main(String[] args) {
		try {
			Integer.parseInt("fewfwe");
			// try {
			// Integer.parseInt("fewfwe");
			// } catch (Exception e) {
			// throw new BusinessRuntimeException("Mycode", e);
			// }
		} catch (Exception e) {
			System.out.println(ExceptionParseUtil.parseExceptionMessage(e));
		}
	}
	
	public static String parseExceptionMessage(Throwable e) {
		StringBuffer errMessSbf = new StringBuffer();
		if (e != null) {
			{//exception
				errMessSbf.append("LocalizedMessage:" + e.getLocalizedMessage());
				errMessSbf.append(";Message:" + e.getMessage());
				StackTraceElement[] stackTraceElements = e.getStackTrace();
				if (stackTraceElements != null && stackTraceElements.length > 0) {
					errMessSbf.append(";StackTrace:");
					for (int i = 0; i < stackTraceElements.length; i++) {
						errMessSbf.append(e.getStackTrace()[i] + ";");
					}
				}
			}
			if(e.getCause()!=null){//cause
				Throwable cause = e.getCause();
				errMessSbf.append("cause-LocalizedMessage:" + cause.getLocalizedMessage());
				errMessSbf.append(";cause-Message:" + cause.getMessage());
				StackTraceElement[] stackTraceElements = cause.getStackTrace();
				if (stackTraceElements != null && stackTraceElements.length > 0) {
					errMessSbf.append(";cause-StackTrace:");
					for (int i = 0; i < stackTraceElements.length; i++) {
						errMessSbf.append(cause.getStackTrace()[i] + ";");
					}
				}
			}
		}
		return errMessSbf.toString();
	}
	
	public static String parseErrorCode(Exception ex,boolean hasDefault){
		String errCode = null;
		if(ex instanceof IBaseException){
			errCode = ((IBaseException) ex).getErrCode();
		}
//		if (ex instanceof FocRuntimeException) {
//			errCode = ((FocRuntimeException) ex).getErrCode();
//		}else if(ex instanceof FocException){
//			errCode = ((FocException) ex).getErrCode();
//		}
		if(StringUtils.isEmpty(errCode)&&hasDefault){
				errCode = CommConstants.DEFAULT_SYS_ERRCODE;
		}
		return errCode;
	}
	
	
	public static String parseErrorMsg(Exception ex,boolean hasDefault){
		String errorMsg = null;
		String errCode = null;
		Object[] paramArrayOfObject = null;
		if(ex instanceof IBaseException){
			IBaseException baseException = (IBaseException) ex;
			errCode = baseException.getErrCode();
			paramArrayOfObject = baseException.getParamArrayOfObject();
			if(!StringUtils.isEmpty(baseException.getErrorMsg())){
				return baseException.getErrorMsg();
			}
		}
//		if (ex instanceof FocRuntimeException) {
//			errCode = ((FocRuntimeException) ex).getErrCode();
//			paramArrayOfObject = ((FocRuntimeException) ex).getParamArrayOfObject();
//		}else if(ex instanceof FocException){
//			errCode = ((FocException) ex).getErrCode();
//			paramArrayOfObject = ((FocException) ex).getParamArrayOfObject();
//		}
		if(!StringUtils.isEmpty(errCode)){
			errorMsg = MessageSourceUtil.getMessage(errCode,paramArrayOfObject);
		}
		if(StringUtils.isEmpty(errorMsg)&&hasDefault){
			errorMsg = MessageSourceUtil.getMessage(CommConstants.DEFAULT_SYS_ERRCODE);
		}
		return errorMsg;
	}
	
	
	public static String parseStatus(Exception ex,boolean hasDefault) {
		// status状态码
		String status = null;
		if (TimeOutRuntimeException.class.equals(ex.getClass())) {// 系统超时
			status = CommConstants.ResponseStatus.LOGIN_TIME_OUT;
		} else if (BusinessRuntimeException.class.equals(ex.getClass())) {// 业务处理异常
			status = CommConstants.ResponseStatus.BUSINESS_FAILED;
		} else if (ValidateRuntimeException.class.equals(ex.getClass())) {// 校验异常
			status = CommConstants.ResponseStatus.VALIDATE_ERR;
		} else {
		}
		if(StringUtils.isEmpty(status)&&hasDefault){
			status = CommConstants.ResponseStatus.SYSTEM_ERR;
		}
		return status;
	}

//	/**
//	 * 获取异常描述
//	 * @param ex
//	 * @return
//	 */
//	public static String parseErrorDesc(Exception ex){
//		String errCode = null;
//		Object[] paramArrayOfObject = null;
//		if(ex instanceof IBaseException){
//			errCode = ((IBaseException) ex).getErrCode();
//			paramArrayOfObject = ((IBaseException) ex).getParamArrayOfObject();
//		}
////		if (ex instanceof FocRuntimeException) {
////			errCode = ((FocRuntimeException) ex).getErrCode();
////			paramArrayOfObject = ((FocRuntimeException) ex).getParamArrayOfObject();
////		}else if(ex instanceof FocException){
////			errCode = ((FocException) ex).getErrCode();
////			paramArrayOfObject = ((FocException) ex).getParamArrayOfObject();
////		}
//		
//		String errorMsg = null;
//		String errorStack = null;
//		if (errCode != null) {// errCode
//			if (ex.getCause() == null) {
//				String message =MessageSourceUtil.getMessage(errCode,paramArrayOfObject);
//				errorMsg = message;
//			} else {// 有异常信息，把异常信息抛出
//				String stackInfo = ExceptionParseUtil.getExceptionMessage(ex);
//				errorStack = stackInfo;
//			}
//		} else {
//			String stackInfo = ExceptionParseUtil.getExceptionMessage(ex);
//			errorStack = stackInfo;
//		}
//		
//		StringBuffer sbf = new StringBuffer();
//		sbf.append("<errCode>"+errCode+"</errCode>");
//		sbf.append("<errMsg>"+errorMsg+"</errMsg>");
//		sbf.append("<errStack>"+errorStack+"</errStack>");
//		return sbf.toString();
//	}
	
}
