package com.mrgao.demo.scope.refresh;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mr.Gao
 * @date 2024/1/30 13:47
 * @apiNote:
 */
public class RefreshScopeBean implements Scope {

    public static final String REFRESH_SCOPE = "refresh";//@1

    private static final RefreshScopeBean INSTANCE = new RefreshScopeBean();

    //来个map用来缓存bean
    private ConcurrentHashMap<String, Object> beanMap = new ConcurrentHashMap<>(); //@1

    public static RefreshScopeBean getInstance() {
        return INSTANCE;
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("******* >> 执行Scope的get方法，对象名：" + name);
        Object bean = beanMap.get(name);
        if (bean == null) {
            bean = objectFactory.getObject();
            beanMap.put(name, bean);
        }
        return bean;
    }

    /**
     * 清理当前
     */
    public void clean() {
        INSTANCE.beanMap.clear();
    }


    @Override
    public Object remove(String name) {
        return this.beanMap.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
