package cn.yangwanhao.boot.springboot.test.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.yangwanhao.util.util.AppParamUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 清理AppParamUtil的ThreadLocal
 *
 * @author 杨万浩
 * @version V3.0
 * @since 2022/2/8 14:05
 */
@Slf4j
@Component
public class AppParamRemoveInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        AppParamUtils.removeAll();
        log.info("执行AppParamRemoveInterceptor清理线程上下文信息");
    }
}
