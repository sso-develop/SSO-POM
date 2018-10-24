package com.lambert.common.uitl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.util.Log4jConfigurer;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
/**
 * 加载平台属性文件，同时初始化Log4J引擎
 * @author lambert  2018-10-15 22:16:52
 *
 */
public class FrameworkPropertiesFactoryBean extends PropertiesFactoryBean {

	private static final int REFRESH_INTERVAL_INSECONDS = 5;

	private static String locations;
	private String refreshInterval;

	@Override
	protected Properties createProperties() throws IOException {
		Properties properties = super.createProperties();
		// ①通过项目配置文件初始化Log4J引擎配置
		//initLog4j(properties);
		try {
			load();
		} catch (JoranException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private void initLog4j(Properties properties) throws IOException {
		String confLocation = locations;
		long refreshIntervalLong = REFRESH_INTERVAL_INSECONDS;
		refreshInterval = refreshInterval.trim();
		if (StringUtils.hasText(refreshInterval)) {
			int value = Integer.parseInt(refreshInterval);
			if (value < 2) {
				value = 2;
			}
			refreshIntervalLong = Integer.parseInt(refreshInterval) * 1000L;
		}
		// ②初始化Log4J引擎的配置
		Log4jConfigurer.initLogging(confLocation, refreshIntervalLong);
	}

	/**
	 * 加载外部的logback配置文件
	 * 
	 * @param externalConfigFileLocation
	 *            配置文件路径
	 * @throws IOException
	 * @throws JoranException
	 */
	public static void load() throws IOException, JoranException {

		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

		File externalConfigFile = new File(locations);

		if (!externalConfigFile.exists()) {

			throw new IOException("Logback External Config File Parameter does not reference a file that exists");

		} else {

			if (!externalConfigFile.isFile()) {
				throw new IOException("Logback External Config File Parameter exists, but does not reference a file");

			} else {

				if (!externalConfigFile.canRead()) {
					throw new IOException("Logback External Config File exists and is a file, but cannot be read.");

				} else {

					JoranConfigurator configurator = new JoranConfigurator();
					configurator.setContext(lc);
					lc.reset();
					configurator.doConfigure(locations);

					StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
				}

			}

		}

	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(String refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

}
