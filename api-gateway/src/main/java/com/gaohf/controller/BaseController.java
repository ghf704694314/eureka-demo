package com.gaohf.controller;

import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.ZuulFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * com.gaohf.controller
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/20
 */
@RestController
public class BaseController {


    @RequestMapping("/filter/getAll")
    public void getAllZuulFilters(){
        FilterLoader filterLoader=FilterLoader.getInstance();
        List<ZuulFilter> preFilters=filterLoader.getFiltersByType("pre");
        List<ZuulFilter> routeFilters=filterLoader.getFiltersByType("route");
        List<ZuulFilter> postFilters=filterLoader.getFiltersByType("post");
        List<ZuulFilter> errorFilters= filterLoader.getFiltersByType("error");

        List<ZuulFilter> allFilters=new ArrayList<>();
        allFilters.addAll(preFilters);
        allFilters.addAll(routeFilters);
        allFilters.addAll(postFilters);
        allFilters.addAll(errorFilters);

        allFilters.forEach(zuulFilter -> System.out.println("FilterType:"+zuulFilter.filterType()+"---FilterName:"+zuulFilter.getClass().getName()+"---FilterOrder:"+zuulFilter.filterOrder()));
    }
}
