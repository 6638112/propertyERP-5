/**   
* Filename:    SZGuoTuGroupBuilding.java   
* @version:    1.0  
* Create at:   2014年11月27日 上午7:45:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月27日    shiyl      1.0         1.0 Version   
*/
package test.baiduApi.entity;

/**
 * Filename:    SZGuoTuGroupBuilding.java
 * @version:    1.0.0
 * Create at:   2014年11月27日 上午7:45:19
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月27日       shiyl             1.0             1.0 Version
 */
public class SZGuoTuGroupBuilding {
	/**序号*/
	private String seqId;
	/**预售证号*/
	private String preSellNo;
	/**项目名称*/
	private String name;
	/**开发企业*/
	private String enterpriseName;
	/**所在区*/
	private String blockName;
	/**批准时间*/
	private String allowTime;
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	public String getPreSellNo() {
		return preSellNo;
	}
	public void setPreSellNo(String preSellNo) {
		this.preSellNo = preSellNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getAllowTime() {
		return allowTime;
	}
	public void setAllowTime(String allowTime) {
		this.allowTime = allowTime;
	}
	
	
}
