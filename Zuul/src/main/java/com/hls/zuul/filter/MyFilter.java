package com.hls.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Component
public class MyFilter extends ZuulFilter {
    private final List<String> whitelist = Arrays.asList("127.0.0.1", "localhost");

    private static final Logger logger =
            LoggerFactory.getLogger(
                    MyFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
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
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        //获得请求
        HttpServletRequest request = ctx.getRequest();

        List<String> list= Arrays.asList("/consumer","/provider");
        String uri=request.getRequestURI();
        if(uri.startsWith("/consumer")){
            if (!whitelist.contains(request.getRemoteAddr())){
                ctx.setSendZuulResponse(false);
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                logger.info("ip校验不通过: [" + getIpAddr(request) + "]");
                ctx.setResponseBody("ip地址校验不通过");
            }else {
                ctx.setSendZuulResponse(true);
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                logger.info("ip校验通过: [" + getIpAddr(request) + "]");
            }
        }

        return null;
    }


    public String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
/*  //头部信息
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            logger.info("当前请求没有token");
             ctx.setSendZuulResponse(false);
             ctx.setResponseStatusCode(401);
             return  null;
        }
         logger.info("请求通过过滤器");*/