/**   
* Filename:    MultiFileEntity.java   
* @version:    1.0  
* Create at:   2014年6月5日 上午11:16:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月5日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.ms.commonBusiness.entity;

import java.io.InputStream;

/**
 * Filename:    MultiFileEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月5日 上午11:16:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月5日       shiyl             1.0             1.0 Version
 */
public class MultiFileEntity {
	private String fileName;
	private byte[] datas = null;
	private InputStream inputStream;
	public MultiFileEntity(String fileName,byte[] datas,InputStream inputStream){
		this.fileName = fileName;
		this.datas = datas;
		this.inputStream = inputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getDatas() {
		return datas;
	}
	public void setDatas(byte[] datas) {
		this.datas = datas;
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
