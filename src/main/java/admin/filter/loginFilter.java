package admin.filter;

import admin.Utils.JwtUtil;
import admin.domain.protocol.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.core.annotation.Order;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

@Order(1)
@WebFilter(urlPatterns = "/*")
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //采用OutputStream而不是Writer的原因，Tomcat要求写入流一致，否则报错。而SpringBoot已经使用了OutputStream,故此处遵循一致
        ServletOutputStream out = null;

        String uri = req.getRequestURI();
        List<String> path = Arrays.asList(".html","/pages","/css","/image","/js","/plugins",".ico");
        //放行白名单
        for (String p : path){
            if(uri.contains(p))
                filterChain.doFilter(request,response);
        }

        if (uri.equals("/") || uri.contains("/userFun"))
            filterChain.doFilter(request,response);//登录操作直接放行
        else {
                String token = req.getHeader("Authorization");
                //验证token是否存在
                if (token == null) {
                    String noLogin = JSONObject.toJSONString(Result.NEED_TOKEN);
                    out = resp.getOutputStream();
                    out.print(noLogin);
                    out.close();

                }else {
                    //验证Token是否有效
//                    token = token.substring(1, token.length() - 1);//去除外层双引号, ""token""  ->  "token"
                    if (JwtUtil.verifyJWT(token)) {
                        filterChain.doFilter(request, response);
                    }
                    else {
                        String invalidLogin = JSONObject.toJSONString(Result.OVERDUE_TOKEN);
                        out = resp.getOutputStream();
                        out.print(invalidLogin);
                        out.close();
                    }
                }
        }




    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
