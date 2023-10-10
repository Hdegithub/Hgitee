package com.geekaca.news.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String password;
    @Value("${spring.data.redis.timeout}")
    private int timeout;

    @Value("${spring.data.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.data.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.data.redis.jedis.pool.min-idle}")
    private int minIdle;

    // 说明这个方法的返回值对象，要交给spring容器管理
    @Bean
    public JedisPool jedisPool(){
        //组装各种配置参数
        JedisPoolConfig jedisConfig = new JedisPoolConfig();
        jedisConfig.setMaxIdle(maxIdle);
        jedisConfig.setMaxTotal(maxActive);
        jedisConfig.setMinIdle(minIdle);
        //构造Jedis连接池对象
        JedisPool jedisPool = new JedisPool(jedisConfig, host, port, timeout, password);
        return jedisPool;
    }
}
