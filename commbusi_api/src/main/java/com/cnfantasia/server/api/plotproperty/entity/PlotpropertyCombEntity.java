/**   
* Filename:    PlotpropertyCombEntity.java   
* @version:    1.0  
* Create at:   2014年8月18日 上午6:08:02   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.entity;

import java.util.List;

import com.cnfantasia.server.api.plotproperty.util.PropPayUtil;
import com.cnfantasia.server.api.prize.entity.PrizeRecordSimpleEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.support.util.SupportCountUtil;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.entity.PropFeeDiscount;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * Filename:    PlotpropertyCombEntity.java
 * @version:    1.0.0
 * Create at:   2014年8月18日 上午6:08:02
 * Description: 组合数据模型
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月18日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyCombEntity {
	
	/**
	 * 构造方法
	 * @param roomEntity 用户当前默认门牌信息
	 * @param payBillEntity 物业账单信息
	 * @param prizeRecordSimpleEntity 当月最低折扣信息
	 */
	public PlotpropertyCombEntity(RoomEntity roomEntity,PayBillInfo payBillEntity
			,PrizeRecordSimpleEntity prizeRecordSimpleEntity
			,PlotpropertyOrderEntity plotpropertyOrderEntity,PayBillType payBillType,IBusinessMonthYear monthYearWithGB){
		this.roomEntity = roomEntity;
		this.payBillEntity = payBillEntity;
		this.prizeRecordSimpleEntity = prizeRecordSimpleEntity;
		this.plotpropertyOrderEntity = plotpropertyOrderEntity;
		this.payBillType = payBillType;
		this.monthYearWithGB = monthYearWithGB;
	}
	/**
	 * 构造方法
	 * @param roomEntity 用户当前默认门牌信息
	 * @param splitPrizeRecordList 周期缴账单拆分折扣列表
	 * @param prizeRecordSimpleEntity 当月最低折扣信息
	 */
	public PlotpropertyCombEntity(RoomEntity roomEntity,PayBillInfo payBillEntity
			,List<PropFeeDiscount> splitPrizeRecordList
			,PlotpropertyOrderEntity plotpropertyOrderEntity,PayBillType payBillType,IBusinessMonthYear monthYearWithGB){
		this.roomEntity = roomEntity;
		this.payBillEntity = payBillEntity;
		this.splitPrizeRecordList = splitPrizeRecordList;
		this.plotpropertyOrderEntity = plotpropertyOrderEntity;
		this.payBillType = payBillType;
		this.monthYearWithGB = monthYearWithGB;
	}
	
	/**
	 * 用户当前默认门牌信息
	 */
	private RoomEntity roomEntity;
	public RoomEntity getRoomEntity() {
		return roomEntity;
	}

	
	/**
	 * 物业账单信息
	 */
	private PayBillInfo payBillEntity;
	public PayBillInfo getPayBillEntity() {
		return payBillEntity;
	}

	
	/**
	 * 当月最低折扣信息
	 */
	private PrizeRecordSimpleEntity prizeRecordSimpleEntity;
	public PrizeRecordSimpleEntity getPrizeRecordSimpleEntity() {
		return prizeRecordSimpleEntity;
	}

	/**
	 * 物业账单对应的支付单信息
	 */
	private PlotpropertyOrderEntity plotpropertyOrderEntity;
	public PlotpropertyOrderEntity getPlotpropertyOrderEntity() {
		return plotpropertyOrderEntity;
	}

	
	
	/**获取签约状态*/
	public boolean fetchSignStatus(){
		boolean signStatus =roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().isSign();
		return signStatus;
	}
	/**获取是否可缴纳物业费*/
	public boolean fetchCanPayProp(){
		boolean canPayProp = PropPayUtil.canPayProp(roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity());
		return canPayProp;
	}
	
	/**获取是否已点赞状态*/
	public boolean fetchIsSupport(){
		boolean isSupport=roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getIsSupport();
		return isSupport;
	}
	
	public int fetchTotalSupportCount(){
		GroupBuildingEntity currGroupBuilding = roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
		Integer totalSupportCount=SupportCountUtil.getSupportCount(currGroupBuilding.getTotalSupportCount(), currGroupBuilding);
		return totalSupportCount==null?0:totalSupportCount;
	}
	
	/**周期缴账单拆分折扣列表*/
	private List<PropFeeDiscount> splitPrizeRecordList;
	public List<PropFeeDiscount> getSplitPrizeRecordList() {
		return splitPrizeRecordList;
	}
	
	/**费用类别信息*/
	private PayBillType payBillType;
	public PayBillType getPayBillType() {
		return payBillType;
	}
	
	/**账单月份信息*/
	private IBusinessMonthYear monthYearWithGB;
	public IBusinessMonthYear getMonthYearWithGB() {
		return monthYearWithGB;
	}
	
}
