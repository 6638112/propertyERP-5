package com.cnfantasia.server.common.cryption.tmpInfo;

import java.security.Provider;
import java.security.Security;

public class ProviderInformation {
  public static void main(String[] args) {
//    //增加provider
//    Security.addProvider(new com.sun.crypto.provider.SunJCE());
//    //移除provider
//    Security.removeProvider("SunJCE");
    Provider[] providers = Security.getProviders();
    for (int i = 0; i < providers.length; i++) {
      Provider provider = providers[i];
      System.out.println("Provider name: " + provider.getName());
//      System.out.println("Provider information: " + provider.getInfo());
      System.out.println("Provider class: " + provider.getClass());
//      System.out.println("Provider version: " + provider.getVersion());
      /*Set entries = provider.entrySet();
      Iterator iterator = entries.iterator();
      while (iterator.hasNext()) {
        System.out.println("Property entry: " + iterator.next());
      }*/
      System.out.println("--------------");
    }
  }
}
