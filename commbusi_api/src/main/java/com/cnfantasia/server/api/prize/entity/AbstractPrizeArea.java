package com.cnfantasia.server.api.prize.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPrizeArea implements PrizeArea{
	/**折扣区间*/
	protected DiscountInterval discountInterval;
	/**包含的所有折扣信息*/
	protected List<DiscountValueEntity> prizeInfo;
	/**剩余的折扣数*/
	protected int leftCount;
	
	@Override
	public synchronized final int insertDiscount(List<DiscountValueEntity> discountValueList){
		if(discountValueList==null||discountValueList.size()<=0){return 0;}
		if(prizeInfo==null){
			prizeInfo = new ArrayList<DiscountValueEntity>();
		}
		//查询已被抽走的折扣数量
		List<Integer> unPrizedList = new ArrayList<Integer>();
		for(int i=0;i<prizeInfo.size();i++){
			if(unPrizedList.size()>=discountValueList.size()){
				break;
			}
			if(prizeInfo.get(i).equals(DiscountValueEntity.unPrizedEntity)){
				unPrizedList.add(i);
			}
		}
		//回填折扣信息
		int miniSize = unPrizedList.size()<discountValueList.size()?unPrizedList.size():discountValueList.size();
		for(int j=0;j<miniSize;j++){//覆盖指定位置的折扣信息
			prizeInfo.set(unPrizedList.get(j), discountValueList.get(j));
			leftCount++;
		}
		if(prizeInfo!=null&&leftCount>=prizeInfo.size()){
			leftCount = prizeInfo.size();
		}
		return miniSize;
	}
	
	@Override
	public synchronized final DiscountValueEntity getDiscount(){
		DiscountValueEntity resDsccount = DiscountValueEntity.unPrizedEntity;
		int tmpIndex = (int)(Math.random()*prizeInfo.size());
		if(prizeInfo.get(tmpIndex)!=DiscountValueEntity.unPrizedEntity){
			leftCount--;
			resDsccount=prizeInfo.get(tmpIndex);
			prizeInfo.set(tmpIndex, DiscountValueEntity.unPrizedEntity);
		}
		return resDsccount;
	}
	
	@Override
	public DiscountValueEntity getData(int index){
		return prizeInfo.get(index);
	}
	
	@Override
	public final int getPrizeInitCount() {
		return prizeInfo.size();
	}
	
	/**获取折扣区间*/
	@Override
	public DiscountInterval getInterval(){
		return discountInterval;
	}
	
	@Override
	public final synchronized int getLeftCount() {
		return leftCount;
	}
	
	@Override
	public boolean isInArea(DiscountValueEntity discount){
		return discountInterval.isContain(discount);
	}
	
	@Override
	public boolean isInArea(double discount){
		return discountInterval.isContain(discount);
	}
	
	@Override
	public synchronized final boolean removeInArea(double discount){
		for(int i=0;i<prizeInfo.size();i++){
			DiscountValueEntity tmpDiscountEntity = prizeInfo.get(i);
			if(tmpDiscountEntity.doubleValue()==discount){
				prizeInfo.set(i, DiscountValueEntity.unPrizedEntity);
				return true;
			}
		}
		return false;
	}
	
//公用方法
//	/**
//	 * 将折扣信息discount录入到数组prizeInfo对应index位置，若大于数组下标，则不录入
//	 * @param prizeInfo
//	 * @param index
//	 * @param discount
//	 */
//	public static void setDiscount(double[] prizeInfo,int index,double discount){
//		if(index<prizeInfo.length){
//			prizeInfo[index]=discount;
//		}
//	}
	
//	/**
//	 * 计算折扣信息
//	 * @param basic  基数
//	 * @param index 下标
//	 * @param max 允许的最大值
//	 * @return
//	 */
//	public static double calculateDiscount(double basic,int index,double max){
//		double res = mul(getFiveFive(index));
//		res = (double)(Math.round(res*10))/10;
//		res = (double)(Math.round((res+basic)*10))/10;
//		if(res>max){res = max;}
//		return res;
//	}
	
//	/**
//	 * 将整数i乘以0.1,若结果只取小数部分
//	 * @param i
//	 * @return 返回结果取值范围为[0.0,0.9];
//	 */
//	private static double mul(int i){
//		double res = i*0.1;
//		res=res-(int)res;
//		return (double)(Math.round(res*10))/10;
//	}
	
//	/**
//	 * 返回范围[-5,5)
//	 * @param i
//	 * @return
//	 */
//	private static int getFiveFive(int i){
//		int tmp = i%18;
//		int change = 0;
//		if(tmp>=0&&tmp<=8){
//			if(tmp%2==0){
//				change = (tmp/2+1);
//			}else{
//				change = (-1)*((tmp+1)/2);
//			}
//		}else if(tmp>=9&&tmp<=17){
//			tmp=tmp-9;
//			if(tmp%2==0){
//				change = (-1)*(tmp/2+1);
//			}else{
//				change = ((tmp+1)/2);
//			}
//		}
//		//如果等于5，则取4，目的是为了保证在1折范围内，结果不会产生2.0的情况
//		if(change==5){
//			change=4;
//		}
//		return change;
//	}
	
}
