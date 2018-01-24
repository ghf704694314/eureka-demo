package com.gaohf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * disconf配置
 */
@Configuration
@Scope("singleton")
public class Config {

    private String dataSourceUrl;
    private String dataSourceUserName;
    private String dataSourcePassword;

    private String redisHost;
    private int redisPort;
    private String redisPassword;
    private int redisDbIndex;
    private long redisExpiration;

    private String rabbitmqAddress;
    private String rabbitmqUsername;
    private String rabbitmqPassword;
    private String rabbitmqVirtualHost;

    public long getRedisExpiration() {
        return redisExpiration;
    }

    public void setRedisExpiration(long redisExpiration) {
        this.redisExpiration = redisExpiration;
    }

    public String getRabbitmqAddress() {
        return rabbitmqAddress;
    }

    public String getRabbitmqUsername() {
        return rabbitmqUsername;
    }

    public String getRabbitmqPassword() {
        return rabbitmqPassword;
    }

    public String getRabbitmqVirtualHost() {
        return rabbitmqVirtualHost;
    }

    public String getDataSourceUrl() {
        return dataSourceUrl;
    }

    public String getDataSourceUserName() {
        return dataSourceUserName;
    }

    public String getDataSourcePassword() {
        return dataSourcePassword;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public int getRedisDbIndex() {
        return redisDbIndex;
    }

    public void setRabbitmqAddress(String rabbitmqAddress) {
        this.rabbitmqAddress = rabbitmqAddress;
    }

    public void setRabbitmqUsername(String rabbitmqUsername) {
        this.rabbitmqUsername = rabbitmqUsername;
    }

    public void setRabbitmqPassword(String rabbitmqPassword) {
        this.rabbitmqPassword = rabbitmqPassword;
    }

    public void setRabbitmqVirtualHost(String rabbitmqVirtualHost) {
        this.rabbitmqVirtualHost = rabbitmqVirtualHost;
    }

    public void setDataSourceUrl(String dataSourceUrl) {
        this.dataSourceUrl = dataSourceUrl;
    }

    public void setDataSourceUserName(String dataSourceUserName) {
        this.dataSourceUserName = dataSourceUserName;
    }

    public void setDataSourcePassword(String dataSourcePassword) {
        this.dataSourcePassword = dataSourcePassword;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

    public void setRedisDbIndex(int redisDbIndex) {
        this.redisDbIndex = redisDbIndex;
    }
}