package com.cnfantasia.server.api.access.web.socket;

import java.io.File;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.mina.filter.ssl.KeyStoreFactory;
import org.apache.mina.filter.ssl.SslContextFactory;

public class SSLContextGenerator {

	/**
	 * 这个方法，通过keystore和truststore文件返回一个SSLContext对象
	 *
	 * @return
	 */
	public static SSLContext getSslContext() {

		SSLContext sslContext = null;
		try {
			/*
			 * 提供keystore的存放目录，读取keystore的文件内容
			 */
			
			final File keyStoreFile = new File(System.getProperty("user.dir", ""),"data_connector_client.jks");

			/*
			 * 提供truststore的存放目录，读取truststore的文件内容
			 */

			if (keyStoreFile.exists()) {
				final KeyStoreFactory keyStoreFactory = new KeyStoreFactory();
				System.out.println("Url is: " + keyStoreFile.getAbsolutePath());
				keyStoreFactory.setDataFile(keyStoreFile);

				keyStoreFactory.setPassword("ake123");

				final SslContextFactory sslContextFactory = new SslContextFactory();
				final KeyStore keyStore = keyStoreFactory.newInstance();
				sslContextFactory.setKeyManagerFactoryKeyStore(keyStore);

				sslContextFactory.setKeyManagerFactoryKeyStorePassword("ake123");
				sslContext = sslContextFactory.newInstance();
				System.out.println("SSL provider is: " + sslContext.getProvider());
			} else {
				System.out.println("Keystore or Truststore file does not exist");
			}
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
		return sslContext;
	}
}