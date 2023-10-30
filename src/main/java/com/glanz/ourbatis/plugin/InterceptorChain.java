package com.glanz.ourbatis.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author GlanzWen
 * @Description 拦截器链
 * @github
 */
public class InterceptorChain {

    private final List<Interceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<Interceptor> getInterceptors(){
        return Collections.unmodifiableList(interceptors);
    }

}
