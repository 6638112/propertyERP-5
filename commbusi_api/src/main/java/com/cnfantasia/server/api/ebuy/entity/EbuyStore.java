package com.cnfantasia.server.api.ebuy.entity;

import com.cnfantasia.server.api.cache.constant.RedisCachePrefix;
import com.cnfantasia.server.api.cache.handler.RedisCacheHandler;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.limitBuy.entity.LimitBuyInfo;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.sysParam.ISysParamManager;
import com.cnfantasia.server.common.utils.DataUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EbuyStore implements Serializable, Comparable<EbuyStore> {

	private static final long serialVersionUID = -7956753371357372918L;
	
	private Long id;
	
	private String storeName;
	
	private String storePic;

	private String proPic;

	private String shopPhotoes;
	
	private String phone;
	
	private String address;
	
	private String startTime;
	
	private String endTime;
	
	private Date updTime;

	//是否是自提点，因为未定楼下店自提点规则，先用para表配置，出规则再改get方法，并删除para记录
	private Boolean isServicePoint;
	
	private Integer distance; //距离，多少米
	
	private Integer deliveTime; //到达时间，多少分钟

	private Integer viewCountToday; //当天浏览量
	
	private List<EbuyProdForLst> ebuyProdForLstList;
	
	private List<EbuyProdType> ebuyProdTypeList;

	private List<LimitBuyInfo> limitBuyList;

	private Integer order;//优先级

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStorePic() {
		if(storePic != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.EBUY_STORE_PIC, storePic, updTime);
		}
		return storePic;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public String getProPic() {
		if(proPic != null) {
			return CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.EBUY_STORE_PIC, proPic, updTime);
		}
		return proPic;
	}

	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}

	public List<String> getShopPicList() {
		if (DataUtil.isEmpty(shopPhotoes)) {
			return null;
		}
		List<String> pics = Arrays.asList(shopPhotoes.split(";"));
		List<String> picList = new ArrayList<String>(pics.size());
		for (String pic : pics) {
			picList.add(CnfantasiaCommbusiUtil.getPicUrl(SysParamKey.EBUY_STORE_PIC, pic, updTime));
		}
		return picList;
	}

	public String getDistanceStr() {
		if(distance != null) {
			return distance + "m";
		} else {
			return "";
		}
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getDeliveTimeStr() {
		if(deliveTime != null) {
			return "预计" + deliveTime + "分钟到货";
		} else {
			return "";
		}
	}

	public void setDeliveTime(Integer deliveTime) {
		this.deliveTime = deliveTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

//	public Date getUpdTime() {
//		return updTime;
//	}

	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}

	public List<EbuyProdForLst> getEbuyProdForLstList() {
		return ebuyProdForLstList;
	}

	public void setEbuyProdForLstList(List<EbuyProdForLst> ebuyProdForLstList) {
		this.ebuyProdForLstList = ebuyProdForLstList;
	}

	public List<EbuyProdType> getEbuyProdTypeList() {
		return ebuyProdTypeList;
	}

	public void setEbuyProdTypeList(List<EbuyProdType> ebuyProdTypeList) {
		this.ebuyProdTypeList = ebuyProdTypeList;
	}

	public List<LimitBuyInfo> getLimitBuyList() {
		return limitBuyList;
	}

	public void setLimitBuyList(List<LimitBuyInfo> limitBuyList) {
		this.limitBuyList = limitBuyList;
	}

	public Boolean getServicePoint() {
		ISysParamManager sysParamManager = (ISysParamManager) CnfantasiaCommbusiUtil.getBeanManager("sysParamManager");
		String servicePointMerchantIds = sysParamManager.getSysParaValue(SysParamKey.Service_Point_Supply_Merchant_Ids);
		if (!DataUtil.isEmpty(servicePointMerchantIds)) {
			List<String> ids = Arrays.asList(servicePointMerchantIds.split(";"));
			if (ids.contains(this.id.toString())) {
				return true;
			}
		}
		return false;
	}

	public void setServicePoint(Boolean servicePoint) {
		isServicePoint = servicePoint;
	}

	public String getViewCountTodayStr() {
		String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String count = RedisCacheHandler.get(RedisCachePrefix.storeViewCountToday + today + id);
		if (count == null) {
			return "";
		}
		this.setViewCountToday(Integer.valueOf(count));
		int wan = Integer.valueOf(count) / 10000;
		if (wan > 0) {
			return "今天" + wan + "万+ 人逛过";
		}
		return "今天" + count + "人逛过";
	}

	public Integer getViewCountToday() {
		return viewCountToday;
	}

	public void setViewCountToday(Integer viewCountToday) {
		this.viewCountToday = viewCountToday;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public int compareTo(EbuyStore store) {
		int orderCompare = orderCompare(store);
		if (orderCompare == 0) {
			if (this.viewCountToday == null && store.getViewCountToday() == null) {
				return 0;
			} else if (this.viewCountToday == null) {
				return 1;
			} else if (store.getViewCountToday() == null) {
				return -1;
			}
			return store.getViewCountToday().compareTo(this.viewCountToday);
		} else {
			return orderCompare;
		}
	}

	private int orderCompare(EbuyStore store) {
		if (this.order == null && store.getOrder() == null) {
			return 0;
		} else if (this.order == null) {
			return 1;
		} else if (store.getOrder() == null) {
			return -1;
		}
		return store.getOrder().compareTo(this.order);
	}
}
