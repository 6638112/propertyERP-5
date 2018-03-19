package com.cnfantasia.server.domainbase.microblogReport.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(微博信息举报表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MicroblogReport implements Serializable{
*/


public class MicroblogReport extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 主键,同t_ebuy_product.f_id */	private BigInteger id;	/** 微博信息id */	private BigInteger tMicroblogContentFId;	/** 退款原因 */	private String reportInfo;	/** 退款原因 */	private String areaInfo;
	public MicroblogReport(){
	}
	public MicroblogReport(MicroblogReport arg){
		this.id = arg.getId();		this.tMicroblogContentFId = arg.gettMicroblogContentFId();		this.reportInfo = arg.getReportInfo();		this.areaInfo = arg.getAreaInfo();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 主键,同t_ebuy_product.f_id	 * @param tMicroblogContentFId 微博信息id	 * @param reportInfo 退款原因	 * @param areaInfo 退款原因	 * @param sys0DelState 记录状态	 */
	public MicroblogReport(BigInteger id,BigInteger tMicroblogContentFId,String reportInfo,String areaInfo,Integer sys0DelState){
		this.id = id;		this.tMicroblogContentFId = tMicroblogContentFId;		this.reportInfo = reportInfo;		this.areaInfo = areaInfo;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tMicroblogContentFId=").append(tMicroblogContentFId).append(";");		sbf.append("reportInfo=").append(reportInfo).append(";");		sbf.append("areaInfo=").append(areaInfo).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettMicroblogContentFId() {		return tMicroblogContentFId;	}	public void settMicroblogContentFId(BigInteger tMicroblogContentFId) {		this.tMicroblogContentFId = tMicroblogContentFId;	}	public String getReportInfo() {		return reportInfo;	}	public void setReportInfo(String reportInfo) {		this.reportInfo = reportInfo;	}	public String getAreaInfo() {		return areaInfo;	}	public void setAreaInfo(String areaInfo) {		this.areaInfo = areaInfo;	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MicroblogReport other = (MicroblogReport) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
