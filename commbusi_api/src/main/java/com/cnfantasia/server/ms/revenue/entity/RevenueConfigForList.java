package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 配置详情列表数据实体类别
 * @author shiyl
 *
 */
public class RevenueConfigForList implements Serializable{
	private static final long serialVersionUID = 1L;
	/**物业公司Id*/
	private BigInteger companyId;
	/**物业公司名称*/
	private String companyName;

	private List<RevenueConfigListByType> typeGpList;
	
	private Integer totalCountCache;
	public Integer getSubSize(){
		if(totalCountCache==null){
			Integer totalCount = 0;
			if(typeGpList!=null){
				for(RevenueConfigListByType tmpType:typeGpList){
					if(tmpType!=null&&tmpType.getRevenueConfigList()!=null){
						totalCount+=tmpType.getRevenueConfigList().size();
					}
				}
			}
			totalCountCache = totalCount;
		}
		return totalCountCache;
	}
	
//	/**收益项类别分组*/
//	private Map<Integer,RevenueConfigListByType> rcMap;
//	public void addRevenueConfig(Integer projectType,RevenueConfig revenueConfig){
//		if(projectType==null){return;}
//		if(rcMap==null){rcMap = new HashMap<Integer, RevenueConfigListByType>();}
//		RevenueConfigListByType tmpRc = rcMap.get(projectType);
//		if(tmpRc==null){ tmpRc = new RevenueConfigListByType(); }
//		tmpRc.addRevenueConfig(revenueConfig);
//		rcMap.put(projectType, tmpRc);
//	}

	public BigInteger getCompanyId() {
		return companyId;
	}
	public void setCompanyId(BigInteger companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<RevenueConfigListByType> getTypeGpList() {
		return typeGpList;
	}

	public void setTypeGpList(List<RevenueConfigListByType> typeGpList) {
		this.typeGpList = typeGpList;
	}
	
}
