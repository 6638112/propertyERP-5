package com.cnfantasia.server.business.pub.logger;

import org.apache.log4j.Layout;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 描述: 日志格式输出 扩展Layout增加[用户ID]
 * 
 * @version 1.00
 * 
 */

public abstract class AbstractPatternLayout extends Layout {
	
	public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
	public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %x - %m%n";
	protected final int BUF_SIZE = 256;
	protected final int MAX_CAPACITY = 1024;
	private StringBuffer sbuf;
	private String pattern;
	private PatternConverter head;
	@SuppressWarnings("unused")
	private String timezone;

	public AbstractPatternLayout() {
		this("%m%n");
	}

	public AbstractPatternLayout(String pattern) {
		sbuf = new StringBuffer(256);
		this.pattern = pattern;
		head = createPatternParser(pattern != null ? pattern : "%m%n").parse();
	}

	public void setConversionPattern(String conversionPattern) {
		pattern = conversionPattern;
		head = createPatternParser(conversionPattern).parse();
	}

	public String getConversionPattern() {
		return pattern;
	}

	public void activateOptions() {
	}

	public boolean ignoresThrowable() {
		return true;
	}

	protected PatternParser createPatternParser(String pattern) {
		return new PatternParser(pattern);
	}

	public String format(LoggingEvent event) {
		if (sbuf.capacity() > 1024) {
			sbuf = new StringBuffer(256);
		} else {
			sbuf.setLength(0);
		}
		for (PatternConverter c = head; c != null; c = c.next) {
			c.format(sbuf, event);
		}
		String sbufInfo = getPrefixInfo() + sbuf.toString();
		return sbufInfo;
	}

	/**
	 * 获取日志头前缀信息 取得[用户ID]
	 * 
	 * @return
	 */
	public abstract String getPrefixInfo();
}
