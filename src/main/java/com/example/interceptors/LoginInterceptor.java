package com.example.interceptors;

import com.example.utils.JwtUtils;
import com.example.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
//拦截器类 也可以存ThreadLocal
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;

    //重写一个preHandle方法 请求到达接口之前执行
    //afterCompletion方法 请求结束后执行
    //创建完拦截器还得注册到springMVC -> WebConfig
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证 请求头带token
        //从redis获取相同的token
        //判断是否相同
        //添加这行代码，让OPTIONS请求通过
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        //一次跨域请求会先放过来一次options检测请求，来检测此次请求是否安全
        //因此后端拦截器要对options请求放行，否则就是出现那种牛头不对马嘴得恶心错误。
        String redisToken = redisTemplate.opsForValue().get("token");
        System.out.println(redisToken);
        String token = request.getHeader("Authorization");
        System.out.println("Token from request header:"+ token);
        if (redisToken == null) {
            return false;
        }
        if (token.equals(redisToken)){
            Map<String, Object> claims = JwtUtils.parseToken(redisToken);
            //把业务数据存入ThreadLocal 一定要清除
            //claims 中 只有id和username
            ThreadLocalUtil.set(claims);
            return true;//放行
        }
        else{response.setStatus(402);
            return false;//拦截}

        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求处理完毕 清除ThreadLocal
        ThreadLocalUtil.remove();
    }
}
