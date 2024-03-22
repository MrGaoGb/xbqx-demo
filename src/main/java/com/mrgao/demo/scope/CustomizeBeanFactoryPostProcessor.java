package com.mrgao.demo.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Mr.Gao
 * @date 2024/1/29 15:38
 * @apiNote:
 */
public class CustomizeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        beanFactory.registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());
    }
}
