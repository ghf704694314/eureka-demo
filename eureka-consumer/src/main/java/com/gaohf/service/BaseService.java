package com.gaohf.service;

import org.springframework.stereotype.Service;

/**
 * com.gaohf.service
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/11/30
 */
@Service
public class BaseService {

    public String fallBack(){
        return "error";
    }
}
