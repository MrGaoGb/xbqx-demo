package com.mrgao.demo.scope;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Gao
 * @date 2024/1/29 15:01
 * @apiNote:自定义本地线程级别的bean作用域，不同的线程中对应的bean实例是不同的，同一个线程中同名的bean是同一个实例
 */
public class ThreadScope implements Scope {

    public static final String THREAD_SCOPE = "thread";//@1

    private ThreadLocal<Map<String, Object>> beanMap = ThreadLocal.withInitial(HashMap::new);

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("BeanMyScope >>>>>>>>> get:" + name); //@2
        Object bean = beanMap.get().get(name);
        if (ObjectUtil.isNull(bean)) {
            bean = objectFactory.getObject();
            beanMap.get().put(name, bean);
        }
        return bean;
    }

    @Override
    public Object remove(String name) {
        return this.beanMap.get().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //bean作用域范围结束的时候调用的方法，用于bean清理
        System.out.println(name);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        //return Thread.currentThread().getName();
        return null;
    }
}
