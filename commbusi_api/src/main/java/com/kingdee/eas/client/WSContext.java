/**
 * WSContext.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.kingdee.eas.client;

public class WSContext  implements java.io.Serializable {
    private java.lang.String slnName;
    private java.lang.String password;
    private int dbType;
    private java.lang.String userName;
    private java.lang.String sessionId;
    private java.lang.String dcName;

    public WSContext() {
    }

    public java.lang.String getSlnName() {
        return slnName;
    }

    public void setSlnName(java.lang.String slnName) {
        this.slnName = slnName;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public int getDbType() {
        return dbType;
    }

    public void setDbType(int dbType) {
        this.dbType = dbType;
    }

    public java.lang.String getUserName() {
        return userName;
    }

    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    public java.lang.String getSessionId() {
        return sessionId;
    }

    public void setSessionId(java.lang.String sessionId) {
        this.sessionId = sessionId;
    }

    public java.lang.String getDcName() {
        return dcName;
    }

    public void setDcName(java.lang.String dcName) {
        this.dcName = dcName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WSContext)) return false;
        WSContext other = (WSContext) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((slnName==null && other.getSlnName()==null) || 
             (slnName!=null &&
              slnName.equals(other.getSlnName()))) &&
            ((password==null && other.getPassword()==null) || 
             (password!=null &&
              password.equals(other.getPassword()))) &&
            dbType == other.getDbType() &&
            ((userName==null && other.getUserName()==null) || 
             (userName!=null &&
              userName.equals(other.getUserName()))) &&
            ((sessionId==null && other.getSessionId()==null) || 
             (sessionId!=null &&
              sessionId.equals(other.getSessionId()))) &&
            ((dcName==null && other.getDcName()==null) || 
             (dcName!=null &&
              dcName.equals(other.getDcName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getSlnName() != null) {
            _hashCode += getSlnName().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        _hashCode += getDbType();
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        if (getSessionId() != null) {
            _hashCode += getSessionId().hashCode();
        }
        if (getDcName() != null) {
            _hashCode += getDcName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WSContext.class);

    static {
        org.apache.axis.description.FieldDesc field = new org.apache.axis.description.ElementDesc();
        field.setFieldName("slnName");
        field.setXmlName(new javax.xml.namespace.QName("", "slnName"));
        field.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new org.apache.axis.description.ElementDesc();
        field.setFieldName("password");
        field.setXmlName(new javax.xml.namespace.QName("", "password"));
        field.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new org.apache.axis.description.ElementDesc();
        field.setFieldName("dbType");
        field.setXmlName(new javax.xml.namespace.QName("", "dbType"));
        field.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(field);
        field = new org.apache.axis.description.ElementDesc();
        field.setFieldName("userName");
        field.setXmlName(new javax.xml.namespace.QName("", "userName"));
        field.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new org.apache.axis.description.ElementDesc();
        field.setFieldName("sessionId");
        field.setXmlName(new javax.xml.namespace.QName("", "sessionId"));
        field.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new org.apache.axis.description.ElementDesc();
        field.setFieldName("dcName");
        field.setXmlName(new javax.xml.namespace.QName("", "dcName"));
        field.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
    };

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
