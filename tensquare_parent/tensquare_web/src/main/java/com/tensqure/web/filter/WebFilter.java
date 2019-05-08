package com.tensqure.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 10:16 2019/5/6
 */
@Component
    public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return  "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return  true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("网关的作用分发和阻拦过滤器也是在进行运行之前的分发");
        System.out.println("但是网关会把加进来的头信息过滤掉.为了安全");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            currentContext.addZuulRequestHeader("Authorization", authorization);
        }
        return null;
    }
}
