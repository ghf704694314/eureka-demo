package com.gaohf.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.gaohf.controller
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/4
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @RequestMapping("/id")
    public String getString(Long id){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("userName","老狗");
        return jsonObject.toString();
    }

    @RequestMapping("/update")
    public String update(){
        return "updateSuccess";
    }
}
