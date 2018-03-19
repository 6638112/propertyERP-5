package com.cnfantasia.server.api.prize.entity;

import java.util.LinkedHashMap;

public class MultiPrizeArea {
//	private Log logger = LogFactory.getLog(getClass());
	/**
	 * 链接后的奖品组合信息
	 */
	private LinkedHashMap<PrizeArea,StartEnd> paLinkedMap = new LinkedHashMap<PrizeArea, StartEnd>();
	public synchronized MultiPrizeArea  append(PrizeArea prizeArea,int start,int length){
		StartEnd startEnd = new StartEnd(start, start+length);
		paLinkedMap.put(prizeArea, startEnd);
		return this;
	}
	public synchronized DiscountValueEntity getDiscount(int index){
		int count = 0;
		for(PrizeArea prizeArea:paLinkedMap.keySet()){
			StartEnd tmp = paLinkedMap.get(prizeArea);
			count+=tmp.getLength();
			if(index<count){
				return prizeArea.getDiscount();
			}
		}
		return DiscountValueEntity.unPrizedEntity;//返回不中奖的信息
	}
	public synchronized int getTotalCount(){
		int count = 0;
		for(PrizeArea prizeArea:paLinkedMap.keySet()){
			StartEnd tmp = paLinkedMap.get(prizeArea);
			count+=tmp.getLength();
		}
		return count;
	}
	public synchronized String showData(){
		StringBuffer sbf = new StringBuffer();
		for(PrizeArea prizeArea:paLinkedMap.keySet()){
			StartEnd tmp = paLinkedMap.get(prizeArea);
			sbf.append("\r");
			sbf.append(prizeArea.getInterval());
			for(int i=0;i<tmp.getLength();i++){
				sbf.append(prizeArea.getData(i)+" ");
				if(i!=0&&i%20==0){
					sbf.append("\r");
				}
			}
		}
		sbf.append("\r");
		return sbf.toString();
	}
}
