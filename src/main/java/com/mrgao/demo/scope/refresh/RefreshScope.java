package com.mrgao.demo.scope.refresh;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mr.Gao
 * @date 2024/1/30 13:53
 * @apiNote:
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Scope(RefreshScopeBean.REFRESH_SCOPE)
public @interface RefreshScope {

    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;
}
