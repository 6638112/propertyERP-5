/**
 * WSPayBillServerInterFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package com.kingdee.eas.payBill;

public class WSPayBillServerInterFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements WSPayBillServerInterFacadeSrvProxyService {

    // Use to get a proxy class for WSPayBillServerInterFacade
    private final java.lang.String WSPayBillServerInterFacade_address = "http://192.168.1.5:8888/ormrpc/services/WSPayBillServerInterFacade";

    public java.lang.String getWSPayBillServerInterFacadeAddress() {
        return WSPayBillServerInterFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSPayBillServerInterFacadeWSDDServiceName = "WSPayBillServerInterFacade";

    public java.lang.String getWSPayBillServerInterFacadeWSDDServiceName() {
        return WSPayBillServerInterFacadeWSDDServiceName;
    }

    public void setWSPayBillServerInterFacadeWSDDServiceName(java.lang.String name) {
        WSPayBillServerInterFacadeWSDDServiceName = name;
    }

    public WSPayBillServerInterFacadeSrvProxy getWSPayBillServerInterFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSPayBillServerInterFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getWSPayBillServerInterFacade(endpoint);
    }

    public WSPayBillServerInterFacadeSrvProxy getWSPayBillServerInterFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WSPayBillServerInterFacadeSoapBindingStub _stub = new WSPayBillServerInterFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSPayBillServerInterFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WSPayBillServerInterFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                WSPayBillServerInterFacadeSoapBindingStub _stub = new WSPayBillServerInterFacadeSoapBindingStub(new java.net.URL(WSPayBillServerInterFacade_address), this);
                _stub.setPortName(getWSPayBillServerInterFacadeWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        java.rmi.Remote _stub = getPort(serviceEndpointInterface);
        ((org.apache.axis.client.Stub) _stub).setPortName(portName);
        return _stub;
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.1.5:8888/ormrpc/services/WSPayBillServerInterFacade", "WSPayBillServerInterFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("WSPayBillServerInterFacade"));
        }
        return ports.iterator();
    }

}
