package com.gaohf.springConfig;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaohf.config.Config;
import com.gaohf.redis.RedisUtils;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;


/**
 * Created by xuqinghua on 2017/3/20.
 */
@Configuration
@EnableJpaAuditing
@Service
public class JDBCSpringConfig {

    public static final String user_cache_name = "usercenter";
    public static final String user_cache_name_prefix = "uc_";

    @Autowired
    private Config config;

    @Bean
    @Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl(config.getDataSourceUrl());
        ds.setUsername(config.getDataSourceUserName());
        ds.setPassword(config.getDataSourcePassword());

        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(true);
        ds.setValidationQuery("SELECT 1");

        return ds;
    }

    @Bean
    public RedisUtils getRedisUtils(){
        RedisUtils redisUtils = new RedisUtils(user_cache_name, user_cache_name_prefix, getRedisTemplate(), config.getRedisExpiration());

        return redisUtils;
    }

    @Bean
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    @Bean
    public RedisTemplate<?, ?> getRedisTemplate(){
        RedisTemplate<?,?> template = new StringRedisTemplate(redisConnectionFactory());
        template.setValueSerializer(jackson2JsonRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();

        factory.setHostName(config.getRedisHost());
        factory.setPort(config.getRedisPort());
        factory.setPassword(config.getRedisPassword());
        factory.setDatabase(config.getRedisDbIndex());
        factory.setUsePool(true);

        factory.afterPropertiesSet();

        return factory;
    }

}
