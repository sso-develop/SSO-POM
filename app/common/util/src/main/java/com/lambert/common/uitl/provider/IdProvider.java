package com.lambert.common.uitl.provider;

import java.util.UUID;

/**
 * <b>Description:id生成工具</b><br>
 */
public class IdProvider {
	/**
	 * Description:通过uuid生成唯一的记录id
	 * @return 生成的id
	 */
	public static String createUUIDId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
}
