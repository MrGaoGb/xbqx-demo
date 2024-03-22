package com.mrgao.demo.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mr.Gao
 * @date 2024/1/30 11:57
 * @apiNote:
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Scope(ThreadScope.THREAD_SCOPE)
public @interface MyScope {

    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
