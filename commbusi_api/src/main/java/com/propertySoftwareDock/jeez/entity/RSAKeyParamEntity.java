package com.propertySoftwareDock.jeez.entity;

import java.io.Serializable;

/**
 * @ClassName: KeyEntity
 * @Date: 2016-11-21 19:02
 * @Auther: kangduo
 * @Description:(RSA密钥产生需要的字段)
 */
public class RSAKeyParamEntity implements Serializable {
    private static final long serialVersionUID = -1712781362605549987L;

    private String modulus;
    private String exponent;
    private String p;
    private String q;
    private String d;
    private String dp;
    private String dq;
    private String inverseQ;

    public RSAKeyParamEntity() {
    }

    public RSAKeyParamEntity(String mudulus, String exponent, String p, String q, String d, String dp, String dq, String inverseQ) {
        this.modulus = mudulus;
        this.exponent = exponent;
        this.p = p;
        this.q = q;
        this.d = d;
        this.dp = dp;
        this.dq = dq;
        this.inverseQ = inverseQ;
    }

    public String getModulus() {
        return modulus;
    }

    public void setModulus(String modulus) {
        this.modulus = modulus;
    }

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getInverseQ() {
        return inverseQ;
    }

    public void setInverseQ(String inverseQ) {
        this.inverseQ = inverseQ;
    }
}
