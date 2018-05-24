package com.lambert.common.uitl.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * redis链接管理对象，单例使用，放在初始化对象里<br>
 *
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ShardedJedisPoolConfig {
	private String addresses; // 中间用逗号分隔例如：127.0.0.1:6379,127.0.0.2:6379
	private List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
	private ShardedJedis jedis;

	private ShardedJedisPool sjp;

	/**
	 * 初始化方法，系统启动的时候调用
	 */
	public void init() {
		String[] host = addresses.split(",");
		for (String item : host) {
			String[] tmp = item.split(":");
			
			JedisShardInfo info = new JedisShardInfo(tmp[0],Integer.parseInt(tmp[1]));
			info.setPassword(tmp[2]);
			shards.add(info);	
		}

		JedisPoolConfig config = new JedisPoolConfig();// Jedis池配置
		setSjp(new ShardedJedisPool(config, shards));

	}

	/**
	 * 销毁方法，系统安全关闭的时候调用
	 */
	public void destory() {
		jedis.close();
	}

	public void returnResource(ShardedJedis jedis) {
		sjp.returnResource(jedis);
	}

	public ShardedJedis getShardedJedis() {
		return sjp.getResource();
	}

	public String getAddresses() {
		return addresses;
	}

	public void setAddresses(String addresses) {
		this.addresses = addresses;
	}

	public ShardedJedisPool getSjp() {
		return sjp;
	}

	public void setSjp(ShardedJedisPool sjp) {
		this.sjp = sjp;
	}

}
