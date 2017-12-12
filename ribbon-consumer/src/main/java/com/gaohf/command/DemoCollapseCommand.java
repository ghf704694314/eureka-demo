package com.gaohf.command;

import com.gaohf.entity.DemoEntity;
import com.gaohf.service.DemoService;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * com.gaohf.command
 *
 * @Author : Gaohf
 * @Description :
 * @Date : 2017/12/12
 */
public class DemoCollapseCommand extends HystrixCollapser<List<DemoEntity>,DemoEntity,Long> {

    private DemoService demoService;
    private Long demoId;

    public  DemoCollapseCommand(DemoService demoService,Long demoId){
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("demoCollapseCommand")).andCollapserPropertiesDefaults(
                HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.demoService=demoService;
        this.demoId=demoId;
    }

    @Override
    public Long getRequestArgument() {
        return demoId;
    }

    @Override
    protected HystrixCommand<List<DemoEntity>> createCommand(Collection<CollapsedRequest<DemoEntity, Long>> collapsedRequests) {
        List<Long> demoIds=new ArrayList<>(collapsedRequests.size());
        demoIds.addAll(collapsedRequests.stream().map(CollapsedRequest :: getArgument).collect(Collectors.toList()));
        return new DemoBatchCommand(demoService,demoIds);
    }

    @Override
    protected void mapResponseToRequests(List<DemoEntity> demoEntities, Collection<CollapsedRequest<DemoEntity, Long>> collapsedRequests) {
        int count=0;
        for (CollapsedRequest<DemoEntity,Long> collapsedRequest : collapsedRequests){
            DemoEntity demoEntity=demoEntities.get(count++);
            collapsedRequest.setResponse(demoEntity);
        }
    }

}
