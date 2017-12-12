package com.gaohf.command;

import com.gaohf.entity.DemoEntity;
import com.gaohf.service.DemoService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.List;

/**
 * com.gaohf.command
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/12
 */
public class DemoBatchCommand extends HystrixCommand<List<DemoEntity>> {

    private DemoService demoService;
    private List<Long> demoIds;

    public DemoBatchCommand(DemoService demoService,List<Long> demoIds){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("demoServiceCommand")));
        this.demoService=demoService;
        this.demoIds=demoIds;
    }

    @Override
    protected List<DemoEntity> run() throws Exception {
        return demoService.findAll(demoIds);
    }
}
