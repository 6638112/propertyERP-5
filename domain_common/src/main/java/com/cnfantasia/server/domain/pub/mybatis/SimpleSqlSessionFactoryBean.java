/**   
 * Filename:    SimpleSqlSessionFactoryBean.java   
 * @version:    1.0  
 * Create at:   2015年6月4日 上午2:52:17   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2015年6月4日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.domain.pub.mybatis;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Filename: SimpleSqlSessionFactoryBean.java
 * 
 * @version: 1.0.0 Create at: 2015年6月4日 上午2:52:17 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2015年6月4日 shiyl 1.0 1.0 Version
 */
public class SimpleSqlSessionFactoryBean extends SqlSessionFactoryBean {
	private Log logger = LogFactory.getLog(SqlSessionFactoryBean.class);
	private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
	private Interceptor[] plugins;
	private Class<?>[] typeAliases;
	private String typeAliasesPackage;
	private TypeHandler<?>[] typeHandlers;
	private String typeHandlersPackage;
	private TransactionFactory transactionFactory;
	private Properties configurationProperties;
	private Resource[] configLocations;
	private DataSource dataSource;
	private String environment = SqlSessionFactoryBean.class.getSimpleName();
	private DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
	private Resource[] mapperLocations;
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * ======================================methods==============================
	 * ===
	 */
	/* 修改该方法 */
	public void setConfigLocation(Resource configLocation) {

		this.configLocations = configLocation != null ? new Resource[] { configLocation } : null;
	}

	/* 增加该方法 */
	public void setConfigLocations(Resource[] configLocations) {

		this.configLocations = configLocations;
	}

	/**
	 * 合并mybatis配置文件
	 */
	public Document SQLConfigMap() {
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		DocumentFactory documentFactory = new DocumentFactory();
		DocumentType docType = documentFactory.createDocType("configuration", "-//mybatis.org//DTD Config 3.0//EN",
				"http://mybatis.org/dtd/mybatis-3-config.dtd");
		doc.setDocType(docType);
		Element rootElement = doc.addElement("configuration");
		//(properties?,settings?,typeAliases?,typeHandlers?,objectFactory?,objectWrapperFactory?,plugins?,environments?,databaseIdProvider?,mappers?)
		rootElement.addElement("settings");
		rootElement.addElement("typeAliases");
		rootElement.addElement("plugins");
		rootElement.addElement("mappers");
		return doc;
	}

	public void readXML(Resource configXML, final Element elementTypeAlias, final Element elementMapper, final Element elementPlugin, final Element elementSetting) {
		SAXReader saxReader = new SAXReader();
		saxReader.setEntityResolver(new EntityResolver() {
			@Override
			public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
				String jarPath = SqlSessionFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath();
				String filePath = "org/apache/ibatis/builder/xml/mybatis-3-config.dtd";
				InputStream jarIn = null;
				try {
					JarFile jarFile = new JarFile(jarPath);
					JarEntry jarEntry = jarFile.getJarEntry(filePath);
					jarIn = jarFile.getInputStream(jarEntry);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return new InputSource(jarIn);
			}
		});

		/* typeAliases合并 */
		saxReader.addHandler("/configuration/typeAliases/typeAlias", new ElementHandler() {
			public void onEnd(ElementPath path) {
				Element row = path.getCurrent();
				Element els = elementTypeAlias.addElement("typeAlias");
				els.addAttribute("alias", row.attributeValue("alias")).addAttribute("type", row.attributeValue("type"));
				row.detach();
			}

			public void onStart(ElementPath arg0) {
			}

			// protected ValueObject getValueObject() {
			// return null;
			// }
		});

		/* mapper合并 */
		saxReader.addHandler("/configuration/mappers/mapper", new ElementHandler() {

			public void onEnd(ElementPath path) {
				Element row = path.getCurrent();
				Element els = elementMapper.addElement("mapper");
				String mapper = row.attributeValue("mapper");
				String resource = row.attributeValue("resource");
				els.addAttribute("mapper", mapper);
				els.addAttribute("resource", resource);
				row.detach();
			}

			public void onStart(ElementPath arg0) {

			}

			// protected ValueObject getValueObject() {
			// return null;
			// }
		});
		
		/* plugins合并 */
		saxReader.addHandler("/configuration/plugins/plugin", new ElementHandler() {

			public void onEnd(ElementPath path) {
				Element row = path.getCurrent();
				Element els = elementPlugin.addElement("plugin");
				String interceptor = row.attributeValue("interceptor");
				els.addAttribute("interceptor", interceptor);
				row.detach();
			}

			public void onStart(ElementPath arg0) {

			}

			// protected ValueObject getValueObject() {
			// return null;
			// }
		});
		
		/* settings合并 */
		saxReader.addHandler("/configuration/settings/setting", new ElementHandler() {
			
			public void onEnd(ElementPath path) {
				Element row = path.getCurrent();
				Element els = elementSetting.addElement("setting");
				String name = row.attributeValue("name");
				String value = row.attributeValue("value");
				els.addAttribute("name", name);
				els.addAttribute("value", value);
				row.detach();
			}
			
			public void onStart(ElementPath arg0) {
				
			}
			
			// protected ValueObject getValueObject() {
			// return null;
			// }
		});
		
		try {
			saxReader.read(configXML.getInputStream());
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return SqlSessionFactory
	 * @throws IOException
	 */
	protected SqlSessionFactory buildSqlSessionFactory() throws IOException {
		Configuration configuration = null;
		XMLConfigBuilder xmlConfigBuilder = null;
		Document document = this.SQLConfigMap();
		Element root = document.getRootElement();
		Element elementMapper = root.element("mappers");
		Element elementTypeAlias = root.element("typeAliases");
		Element elementPlugin = root.element("plugins");
		Element elementSetting = root.element("settings");
		for (Resource configLocation : configLocations) {
			readXML(configLocation, elementTypeAlias, elementMapper,elementPlugin,elementSetting);
		}
		// Reader reader = null; InputStream inputStream = null;
		if (this.configLocations != null) {
//			logger.debug(document.asXML());
			InputStream inputSteam = new ByteArrayInputStream(document.asXML().getBytes());
			xmlConfigBuilder = new XMLConfigBuilder(inputSteam, null, this.configurationProperties);
			configuration = xmlConfigBuilder.getConfiguration();
			if (inputSteam != null) {
				inputSteam.close();
				inputSteam = null;
			}
			document = null;
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Property 'configLocation' not specified,using default MyBatis Configuration");
			}
			configuration = new Configuration();
			configuration.setVariables(this.configurationProperties);
		}

		if (hasLength(this.typeAliasesPackage)) {
			String[] typeAliasPackageArray = tokenizeToStringArray(this.typeAliasesPackage,
					ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			for (String packageToScan : typeAliasPackageArray) {
				configuration.getTypeAliasRegistry().registerAliases(packageToScan);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Scanned package: '" + packageToScan + "' for aliases");
				}
			}
		}
		if (!isEmpty(this.typeAliases)) {
			for (Class<?> typeAlias : this.typeAliases) {
				configuration.getTypeAliasRegistry().registerAlias(typeAlias);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Registered type alias: '" + typeAlias + "'");
				}
			}
		}

		if (!isEmpty(this.plugins)) {
			for (Interceptor plugin : this.plugins) {
				configuration.addInterceptor(plugin);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Registered plugin: '" + plugin + "'");
				}
			}
		}

		if (hasLength(this.typeHandlersPackage)) {
			String[] typeHandlersPackageArray = tokenizeToStringArray(this.typeHandlersPackage,
					ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
			for (String packageToScan : typeHandlersPackageArray) {
				configuration.getTypeHandlerRegistry().register(packageToScan);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Scanned package: '" + packageToScan + "' for type handlers");
				}
			}
		}

		if (!isEmpty(this.typeHandlers)) {
			for (TypeHandler<?> typeHandler : this.typeHandlers) {
				configuration.getTypeHandlerRegistry().register(typeHandler);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Registered type handler: '" + typeHandler + "'");
				}
			}
		}

		if (xmlConfigBuilder != null) {
			try {
				xmlConfigBuilder.parse();

				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Parsed configuration file: '" + this.configLocations + "'");
				}
			} catch (Exception ex) {
				throw new NestedIOException("Failed to parse config resource: " + this.configLocations, ex);
			} finally {
				ErrorContext.instance().reset();
			}
		}

		if (this.transactionFactory == null) {
			this.transactionFactory = new SpringManagedTransactionFactory();
		}

		Environment environment = new Environment(this.environment, this.transactionFactory, this.dataSource);
		configuration.setEnvironment(environment);

		if (this.databaseIdProvider != null) {
			try {
				configuration.setDatabaseId(this.databaseIdProvider.getDatabaseId(this.dataSource));
			} catch (SQLException e) {
				throw new NestedIOException("Failed getting a databaseId", e);
			}
		}

		if (!isEmpty(this.mapperLocations)) {
			for (Resource mapperLocation : this.mapperLocations) {
				if (mapperLocation == null) {
					continue;
				}

				try {
					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(), configuration,
							mapperLocation.toString(), configuration.getSqlFragments());
					xmlMapperBuilder.parse();
				} catch (Exception e) {
					throw new NestedIOException("Failed to parse mapping resource: '" + mapperLocation + "'", e);
				} finally {
					ErrorContext.instance().reset();
				}

				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Parsed mapper file: '" + mapperLocation + "'");
				}
			}
		} else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Property 'mapperLocations' was not specified or no matching resources found");
			}
		}
		return this.sqlSessionFactoryBuilder.build(configuration);
	}

	public void afterPropertiesSet() throws Exception {
		notNull(dataSource, "Property 'dataSource' is required");
		notNull(sqlSessionFactoryBuilder, "Property 'sqlSessionFactoryBuilder' is required");
		this.sqlSessionFactory = buildSqlSessionFactory();
	}

	public SqlSessionFactory getObject() throws Exception {
		if (this.sqlSessionFactory == null) {
			afterPropertiesSet();
		}

		return this.sqlSessionFactory;
	}

	/*
	 * public Class<? extends SqlSessionFactory> getObjectType() { return
	 * this.sqlSessionFactory == null ? SqlSessionFactory.class :
	 * this.sqlSessionFactory.getClass(); }
	 * 
	 * public void onApplicationEvent(ApplicationEvent event) { if (failFast &&
	 * event instanceof ContextRefreshedEvent) { // fail-fast -> check all
	 * statements are completed
	 * this.sqlSessionFactory.getConfiguration().getMappedStatementNames(); } }
	 */

	/**
	 * ======================================setter==============================
	 * ===
	 */
	public SqlSessionFactoryBuilder getSqlSessionFactoryBuilder() {
		return sqlSessionFactoryBuilder;
	}

	public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
		this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
	}

	public void setPlugins(Interceptor[] plugins) {
		this.plugins = plugins;
	}

	public void setTypeAliases(Class<?>[] typeAliases) {
		this.typeAliases = typeAliases;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public void setTypeHandlers(TypeHandler<?>[] typeHandlers) {
		this.typeHandlers = typeHandlers;
	}

	public void setTypeHandlersPackage(String typeHandlersPackage) {
		this.typeHandlersPackage = typeHandlersPackage;
	}

	public void setTransactionFactory(TransactionFactory transactionFactory) {
		this.transactionFactory = transactionFactory;
	}

	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public void setDataSource(DataSource dataSource) {
		if (dataSource instanceof TransactionAwareDataSourceProxy) {
			this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
		} else {
			this.dataSource = dataSource;
		}
	}
}
