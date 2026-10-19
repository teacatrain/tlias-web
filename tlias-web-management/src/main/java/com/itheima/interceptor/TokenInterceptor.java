package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        if(requestURI.contains("/login")){
            log.info("登录请求,放行");
            return  true;
        }

        String token = request.getHeader("token");

        if(token == null || token.isEmpty()){
            log.info("令牌为空,401");
            response.setStatus(401);
            return false;
        }

        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌非法,401");
            response.setStatus(401);
            return  false;
        }

        log.info("令牌合法,放行");
        return true;
    }

}
