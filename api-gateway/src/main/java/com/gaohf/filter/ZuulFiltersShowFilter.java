package com.gaohf.filter;

import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * com.gaohf.filter
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/20
 */
//@Component
public class ZuulFiltersShowFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        getAllZuulFilters();
        return null;
    }

    protected void getAllZuulFilters(){
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

        allFilters.forEach(zuulFilter -> System.out.println("FilterType:"+zuulFilter.filterType()+"FilterName:"+zuulFilter.getClass().getName()+"FilterOrder:"+zuulFilter.filterOrder()));
    }
}
