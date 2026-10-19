package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        if(requestURI.contains("/login")){
            log.info("登录请求,放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        String token = request.getHeader("token");

        if(token == null || token.isEmpty()){
            log.info("令牌为空,401");
            response.setStatus(401);
            return;
        }

        try {
            Claims claims = JwtUtils.parseToken(token);
            CurrentHolder.setCurrentId((Integer) claims.get("id"));
            log.info("当前登陆用户id:{}",claims.get("id"));
        } catch (Exception e) {
            log.info("令牌非法,401");
            response.setStatus(401);
            return;
        }

        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest,servletResponse);

        CurrentHolder.remove();

    }
}
