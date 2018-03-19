/**   
* Filename:    EbuyConfirm_ProductAndCount.java   
* @version:    1.0  
* Create at:   2015年1月26日 下午1:20:45   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年1月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cnfantasia.server.api.user.entity.UserSimpleEntity;

/**
 * Filename:    EbuyConfirm_ProductAndCount.java
 * @version:    1.0.0
 * Create at:   2015年1月26日 下午1:20:45
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年1月26日       shiyl             1.0             1.0 Version
 */
public class EbuyConfirm_ProductAndCount implements Serializable,Comparable<EbuyConfirm_ProductAndCount>{
	private static final long serialVersionUID = 1L;
	
//	public EbuyConfirm_ProductAndCount(EbuyProductEntity ebuyProductEntity,Long productQty
//			,List<EbuyBuyCarHasTEbuyProduct_WithExtData> ebuyBuyCarHasTEbuyProduct_WithExtDataList
//			,EbuyProductSpec buyCarEbuyProductSpec){
//		EbuyProductWithCurrProductSpec ebuyProductWithSpec = new EbuyProductWithCurrProductSpec(ebuyProductEntity, buyCarEbuyProductSpec);
//		this.ebuyProductWithSpec = ebuyProductWithSpec;
//		this.productQty = productQty;
//		this.ebuyBuyCarHasTEbuyProduct_WithExtDataList = ebuyBuyCarHasTEbuyProduct_WithExtDataList;
//	}
	
	public EbuyConfirm_ProductAndCount(EbuyProductWithCurrProductSpec ebuyProductWithSpec,Long productQty
			,List<EbuyBuyCarHasTEbuyProduct_WithExtData> ebuyBuyCarHasTEbuyProduct_WithExtDataList){
		this.ebuyProductWithSpec = ebuyProductWithSpec;
		this.productQty = productQty;
		this.ebuyBuyCarHasTEbuyProduct_WithExtDataList = ebuyBuyCarHasTEbuyProduct_WithExtDataList;
	}
	
	public boolean isEqualExt(EbuyConfirm_ProductAndCount goal){
		boolean res = false;
		if(goal!=null){
			res = this.getEbuyProductWithSpec().isEqualExt(goal.getEbuyProductWithSpec());
		}
		return res;
	}
	
	private EbuyProductWithCurrProductSpec ebuyProductWithSpec;
	public void setEbuyProductWithSpec(EbuyProductWithCurrProductSpec ebuyProductWithSpec) {
		this.ebuyProductWithSpec = ebuyProductWithSpec;
	}
	public EbuyProductWithCurrProductSpec getEbuyProductWithSpec() {
		return ebuyProductWithSpec;
	}

	/** 商品数量 */
	private Long productQty;
	public Long getProductQty() {
		return productQty;
	}
	public void setProductQty(Long productQty) {
		this.productQty = productQty;
	}
	
	private List<EbuyBuyCarHasTEbuyProduct_WithExtData> ebuyBuyCarHasTEbuyProduct_WithExtDataList;
	public List<EbuyBuyCarHasTEbuyProduct_WithExtData> getEbuyBuyCarHasTEbuyProduct_WithExtDataList() {
		return ebuyBuyCarHasTEbuyProduct_WithExtDataList;
	}
	public void setEbuyBuyCarHasTEbuyProduct_WithExtDataList(
			List<EbuyBuyCarHasTEbuyProduct_WithExtData> ebuyBuyCarHasTEbuyProduct_WithExtDataList) {
		this.ebuyBuyCarHasTEbuyProduct_WithExtDataList = ebuyBuyCarHasTEbuyProduct_WithExtDataList;
	}
	
	public Set<UserSimpleEntity> getBuyerList(){
		Set<UserSimpleEntity> resList = new HashSet<UserSimpleEntity>();
		if(ebuyBuyCarHasTEbuyProduct_WithExtDataList!=null){
			for(EbuyBuyCarHasTEbuyProduct_WithExtData tmpA:ebuyBuyCarHasTEbuyProduct_WithExtDataList){
				resList.add(tmpA.getBuyUser());
			}
		}
		return resList;
	}
	
	public List<BigInteger> getBuyerIdList(){
		List<BigInteger> resList = new ArrayList<BigInteger>();
		Set<BigInteger> tmpList = new HashSet<BigInteger>();
		if(ebuyBuyCarHasTEbuyProduct_WithExtDataList!=null){
			for(EbuyBuyCarHasTEbuyProduct_WithExtData tmpA:ebuyBuyCarHasTEbuyProduct_WithExtDataList){
				tmpList.add(tmpA.getBuyUser().getId());
			}
			resList.addAll(tmpList);
		}
		return resList;
	}
	
	@Override
	public int compareTo(EbuyConfirm_ProductAndCount goal) {
		if(goal==null){
			return 0;
		}
		{//当前排序
			Collections.sort(getEbuyBuyCarHasTEbuyProduct_WithExtDataList(), new Comparator<EbuyBuyCarHasTEbuyProduct_WithExtData>() {
				@Override
				public int compare(EbuyBuyCarHasTEbuyProduct_WithExtData o1, EbuyBuyCarHasTEbuyProduct_WithExtData o2) {
					if(o1==null||o2==null){
						return 0;
					}
					return o1.compareTo(o2);
				}
			});
		}
		{//目标排序
			Collections.sort(goal.getEbuyBuyCarHasTEbuyProduct_WithExtDataList(), new Comparator<EbuyBuyCarHasTEbuyProduct_WithExtData>() {
				@Override
				public int compare(EbuyBuyCarHasTEbuyProduct_WithExtData o1, EbuyBuyCarHasTEbuyProduct_WithExtData o2) {
					if(o1==null||o2==null){
						return 0;
					}
					return o1.compareTo(o2);
				}
			});
		}
		//排序完成后再取出用户列表
		List<BigInteger> currBuyerList = getBuyerIdList();
		Collections.sort(currBuyerList,new Comparator<BigInteger>(){
			@Override
			public int compare(BigInteger src, BigInteger goal) {
				if(src==null||goal==null){
					return 0;
				}
				return src.compareTo(goal);
			}
		});
		List<BigInteger> goalBuyerList = goal.getBuyerIdList();
		Collections.sort(goalBuyerList,new Comparator<BigInteger>(){
			@Override
			public int compare(BigInteger src, BigInteger goal) {
				if(src==null||goal==null){
					return 0;
				}
				return src.compareTo(goal);
			}
		});
		if(currBuyerList.size()!=goalBuyerList.size()){//小的排前面
			return currBuyerList.size()-goalBuyerList.size();
		}else{
			return currBuyerList.hashCode()-goalBuyerList.hashCode();
		}
		
	}
	
//	public static void main(String[] args){
//		List<BigInteger> srcList = new ArrayList<BigInteger>();
//		srcList.add(new BigInteger("1005"));
//		srcList.add(new BigInteger("1002"));
//		srcList.add(new BigInteger("1006"));
//		Collections.sort(srcList,new Comparator<BigInteger>(){
//			@Override
//			public int compare(BigInteger src, BigInteger goal) {
//				return src.compareTo(goal);
//			}
//		});
//		System.out.println(JSON.toJSONString(srcList));
//		List<BigInteger> goalList = new ArrayList<BigInteger>();
//		goalList.add(new BigInteger("1005"));
//		goalList.add(new BigInteger("1006"));
//		goalList.add(new BigInteger("1002"));
//		Collections.sort(goalList,new Comparator<BigInteger>(){
//			@Override
//			public int compare(BigInteger src, BigInteger goal) {
//				return src.compareTo(goal);
//			}
//		});
//		System.out.println(JSON.toJSONString(goalList));
//		System.out.println(srcList.hashCode());
//		System.out.println(goalList.hashCode());
//	}
	
}
