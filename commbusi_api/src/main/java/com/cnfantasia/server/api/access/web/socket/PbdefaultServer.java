package com.cnfantasia.server.api.access.web.socket;

import org.apache.log4j.xml.DOMConfigurator;

public class PbdefaultServer {

    public static void main(final String[] args) {
        final String dir = System.getProperty("user.dir", "");
        DOMConfigurator.configure(dir + "/config/log4j.xml");
        new DefaultAccessHandler();
    }

}
