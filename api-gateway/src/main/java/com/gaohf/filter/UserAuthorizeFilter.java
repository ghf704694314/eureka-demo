//package com.gaohf.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolderStrategy;
//import org.springframework.stereotype.Component;
//
///**
// * com.gaohf.filter
// *
// * @Author : Gaohf
// * @Description :
// * @Date : 2017/12/15
// */
//@Component
//public class UserAuthorizeFilter extends ZuulFilter{
//    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 150;
//    }
////				ctx.set(ACCESS_TOKEN, oauth.getTokenValue());
////				ctx.set(TOKEN_TYPE, oauth.getTokenType()==null ? "Bearer" : oauth.getTokenType());
//    @Override
//    public boolean shouldFilter() {
//        RequestContext context=RequestContext.getCurrentContext();
//        String auth=(String) context.get(ACCESS_TOKEN);
//        return StringUtils.isNotBlank(auth)&&!isAdmin();
//    }
//
//    @Override
//    public Object run() {
//        RequestContext requestContext=RequestContext.getCurrentContext();
//        String auth=(String) requestContext.get(ACCESS_TOKEN);
//        if(StringUtils.isNotBlank(auth)){
//            SecurityContext securityContext=SecurityContextHolder.getContext();
//        }else {
//            return null;
//        }
//        return null;
//    }
//
//    public Boolean isAdmin(){
//        return false;
//    }
//
//}
