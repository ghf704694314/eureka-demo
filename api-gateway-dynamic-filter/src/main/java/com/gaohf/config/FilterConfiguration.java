package com.gaohf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * com.gaohf.config
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/19
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {

    private String root;
    private Integer interval;

    public String getRoot(){
        return root;
    }

    public void setRoot(String root){
        this.root=root;
    }

    public Integer getInterval(){
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
