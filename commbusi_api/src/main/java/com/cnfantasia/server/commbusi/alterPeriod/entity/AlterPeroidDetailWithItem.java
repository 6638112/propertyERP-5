package com.cnfantasia.server.commbusi.alterPeriod.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.common.utils.PriceUtil;
import com.cnfantasia.server.domainbase.alterPeriodDataDetail.entity.AlterPeriodDataDetail;
import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;

/**
 * @ClassName: RoomAlterPeroidDetail
 * @Date: 2016-10-25 9:14
 * @Auther: kangduo
 * @Description:(单个房间的收费项详情)
 */
public class AlterPeroidDetailWithItem extends AlterPeriodDataDetail {
    private static final long serialVersionUID = -5793848921696166790L;
    //小区ID
    private BigInteger gbId;
    //真实房间ID
//    private BigInteger realRoomId;
    //小区名
    private String gbName;
    //楼栋名
    private String bName;
    //单元
    private String unitName;
    //房号
    private String roomNumTail;
    
    private List<AlterRoomHasFeeItem> alterRoomHasFeeItemList;
    
    public BigInteger getGbId() {
        return gbId;
    }

    public void setGbId(BigInteger gbId) {
        this.gbId = gbId;
    }

//    public BigInteger getRealRoomId() {
//        return realRoomId;
//    }
//
//    public void setRealRoomId(BigInteger realRoomId) {
//        this.realRoomId = realRoomId;
//    }

    public String getGbName() {
        return gbName;
    }

    public void setGbName(String gbName) {
        this.gbName = gbName;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getUnitName() {
        return unitName == null? "": unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomNumTail() {
        return roomNumTail;
    }

    public void setRoomNumTail(String roomNumTail) {
        this.roomNumTail = roomNumTail;
    }

    public BigDecimal getLatefeeAmountDecimal() {
        return getLatefeeAmount() == null ? BigDecimal.ZERO : PriceUtil.div100(getLatefeeAmount());
    }

	public List<AlterRoomHasFeeItem> getAlterRoomHasFeeItemList() {
		return alterRoomHasFeeItemList;
	}

	public void setAlterRoomHasFeeItemList(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList) {
		this.alterRoomHasFeeItemList = alterRoomHasFeeItemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gbId == null) ? 0 : gbId.hashCode());
		result += ((bName == null) ? 0 : bName.hashCode());
		result += ((unitName == null) ? 0 : unitName.hashCode());
		result += ((roomNumTail == null) ? 0 : roomNumTail.hashCode());
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
		AlterPeroidDetailWithItem other = (AlterPeroidDetailWithItem) obj;
		if (gbId == null) {
			if (other.gbId != null)
				return false;
		} else if (!gbId.equals(other.gbId))
			return false;
		
		if (bName == null) {
			if (other.bName != null)
				return false;
		} else if (!bName.equals(other.bName))
			return false;
		
		if (this.getUnitName() == null) {
			if (other.getUnitName() != null)
				return false;
		} else if (!this.getUnitName().equals(other.getUnitName()))
			return false;
		
		if (roomNumTail == null) {
			if (other.roomNumTail != null)
				return false;
		} else if (!roomNumTail.equals(other.roomNumTail))
			return false;

		return true;
	}
	
}
