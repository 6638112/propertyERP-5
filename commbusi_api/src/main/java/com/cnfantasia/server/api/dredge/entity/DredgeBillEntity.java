package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.dredge.entity.DredgeDetails.Comment;
import com.cnfantasia.server.api.dredge.entity.DredgeDetails.ProcessRecording;
import com.cnfantasia.server.api.dredge.util.DredgeCombineStatusUtil;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;
import com.cnfantasia.server.domainbase.dredgeBillAmountDetail.entity.DredgeBillAmountDetail;

/**
 * @ClassName: DredgeBillEntity
 * @Date: 2016-11-04 14:35
 * @Auther: yanghua
 * @Description:(维修订单拓展实体)
 */
public class DredgeBillEntity extends DredgeBill{
    /**维修师傅姓名*/
    private String workerName;
    /**维修师傅电话*/
    private String workerMobile;
    
    private String supplyTypeName;
    /**维修一级类型*/
    private String dredgeType;
    /**维修二级类型*/
    private String dredgeType2nd;
    /**维修师傅等级*/
    private Integer level;
    /**订单评价星级**/
    private String commentLevel;
    /**订单支付时间*/
    private String payTime;
    /**订单总金额*/
    private Long totalAmount;
    /**订单总金额包括耗材费*/
    private Long orderAmount;
    /**支付方式 */
    private Integer payMethod;
    /**第三方服务商id*/
    private BigInteger serviceSupplierId;
    /**用户手机号*/
    private String userTel;
    /**费用类型*/
    private List<DredgeBillAmountDetail> dredgeBillAmountDetailList;
    /**区县*/
    private String blockName;
    /**城市*/
    private String cityName;

    private int processRecordCount;//流程记录条数
    
    private String serviceSupplier; //服务供应商
    
    private List<ProcessRecording> processRecordingList;
	
	private List<Comment> commentList;
	
	private BigInteger buyerId;

    // 1 待付款 2 待分配 3 待服务 4 已服务 5 已完成 6 已取消 7 退款中 8 已退款
    private Integer combineStatus;
    private String dredgeProductName;

    public int getProcessRecordCount() {
		return processRecordCount;
	}

	public void setProcessRecordCount(int processRecordCount) {
		this.processRecordCount = processRecordCount;
	}

	public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getSupplyTypeName() {
		return supplyTypeName;
	}

	public void setSupplyTypeName(String supplyTypeName) {
		this.supplyTypeName = supplyTypeName;
	}

	public String getDredgeType() {
        return dredgeType;
    }

    public void setDredgeType(String dredgeType) {
        this.dredgeType = dredgeType;
    }

    public String getDredgeType2nd() {
        return dredgeType2nd;
    }

    public void setDredgeType2nd(String dredgeType2nd) {
        this.dredgeType2nd = dredgeType2nd;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    
    public String getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(String commentLevel) {
		this.commentLevel = commentLevel;
	}
	
	public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigInteger getServiceSupplierId() {
        return serviceSupplierId;
    }

    public void setServiceSupplierId(BigInteger serviceSupplierId) {
        this.serviceSupplierId = serviceSupplierId;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public List<DredgeBillAmountDetail> getDredgeBillAmountDetailList() {
        return dredgeBillAmountDetailList;
    }

    public void setDredgeBillAmountDetailList(List<DredgeBillAmountDetail> dredgeBillAmountDetailList) {
        this.dredgeBillAmountDetailList = dredgeBillAmountDetailList;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

	public String getServiceSupplier() {
		return serviceSupplier;
	}

	public void setServiceSupplier(String serviceSupplier) {
		this.serviceSupplier = serviceSupplier;
	}

	public Long getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Long orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public List<ProcessRecording> getProcessRecordingList() {
		return processRecordingList;
	}

	public void setProcessRecordingList(List<ProcessRecording> processRecordingList) {
		this.processRecordingList = processRecordingList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public BigInteger getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(BigInteger buyerId) {
		this.buyerId = buyerId;
	}

    public Integer getCombineStatus() {
        return DredgeCombineStatusUtil.getCombineStatus(super.getBillType(), super.getStatus());
    }

    public void setCombineStatus(Integer combineStatus) {
        this.combineStatus = combineStatus;
    }

    public String getDredgeProductName() {
        return dredgeProductName;
    }

    public void setDredgeProductName(String dredgeProductName) {
        this.dredgeProductName = dredgeProductName;
    }
}
