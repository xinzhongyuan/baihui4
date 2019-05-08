package com.tensqure.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：xzy
 * @ Date       ：Created in 16:56 2019/5/6
 */
@Component
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

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
        System.out.println("后台是必须用户名密码登录的否则只能进入登陆页面");
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.contains("/admin/login")) {
            System.out.println("前往登陆页面不用拦截");
            return null;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    context.addZuulRequestHeader("Authorization", authHeader);
                    System.out.println("头信息通过");
                    return null;
                }
            }

        }
        context.setSendZuulResponse(false);//终止运行
        context.setResponseStatusCode(401);//http状态码
        context.setResponseBody("无权访问");
        context.getResponse().setContentType("text/html;charset=UTF-8");
        return null;
    }
}
