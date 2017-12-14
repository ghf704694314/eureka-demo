//package com.gaohf.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.apache.http.impl.bootstrap.HttpServer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * com.gaohf.filter
// *
// * @Author : Gaohf
// * @Description :run()只进行简单的token检验,后期可以扩展成真实的用户中心鉴权
// * @Date : 2017/12/13
// */
//public class AccessFilter extends ZuulFilter {
//
//    private static Logger log= LoggerFactory.getLogger(AccessFilter.class);
//
//    /**
//     * filterType:
//     * pre:请求被路由之前
//     * routing:路由请求时
//     * post:在routing和error过滤器之后
//     * error:处理请求发生错误时
//     * @return
//     */
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx=RequestContext.getCurrentContext();
//        HttpServletRequest request=ctx.getRequest();
//
//        log.info("send {} request to {}",request.getMethod(),request.getRequestURL().toString());
//
//        Object accessToken=request.getParameter("accessToken");
//        if(accessToken==null){
//            log.warn("access token is empty");
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            return null;
//        }
//        log.info("access token ok");
//        return null;
//    }
//}
