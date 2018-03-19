package com.cnfantasia.server.domainbase.parkCache.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(缓存的socket数据) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
 * public class ParkCache implements Serializable{
 */

public class ParkCache extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /** 主键 */
	private BigInteger id;
	/** x小区的业务编号 */
	private String tradeCode;
	/** 缓存 */
	private Byte[] buffer;
	public ParkCache(){
	}
	public ParkCache(ParkCache arg){
		this.id = arg.getId();
        this.tradeCode = arg.getTradeCode();
        this.buffer = arg.getBuffer();
        this.sys0AddTime = arg.getSys0AddTime();
        this.sys0UpdTime = arg.getSys0UpdTime();
        this.sys0DelTime = arg.getSys0DelTime();
        this.sys0AddUser = arg.getSys0AddUser();
        this.sys0UpdUser = arg.getSys0UpdUser();
        this.sys0DelUser = arg.getSys0DelUser();
        this.sys0DelState = arg.getSys0DelState();

    }

    /**
     * 
     * @param id 主键
     * @param tradeCode x小区的业务编号
     * @param buffer 缓存
     * @param sys0AddTime
     * @param sys0UpdTime
     * @param sys0DelTime
	 * @param sys0AddUser 
	 * @param sys0UpdUser 
	 * @param sys0DelUser 
	 * @param sys0DelState 记录状态
	 */
	public ParkCache(BigInteger id,String tradeCode,Byte[] buffer,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.tradeCode = tradeCode;
		this.buffer = buffer;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
        this.sys0DelTime = sys0DelTime;
        this.sys0AddUser = sys0AddUser;
        this.sys0UpdUser = sys0UpdUser;
        this.sys0DelUser = sys0DelUser;
        this.sys0DelState = sys0DelState;

    }

    @Override
    public String toString() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("id=").append(id).append(";");
        sbf.append("tradeCode=").append(tradeCode).append(";");
        sbf.append("buffer=").append(buffer).append(";");
        sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
        sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
        sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
        sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
        sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
        sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
        sbf.append("sys0DelState=").append(sys0DelState).append(";");
        return sbf.toString();

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

	public Byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(Byte[] buffer) {
		this.buffer = buffer;
	}
	
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
        ParkCache other = (ParkCache) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;

        return true;
    }

}
