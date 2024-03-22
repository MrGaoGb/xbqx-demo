package com.mrgao.demo.service;

/**
 * @author Mr.Gao
 * @date 2024/1/29 14:10
 * @apiNote:
 */
public class BeanScopeModel {

    public BeanScopeModel(String beanScope) {
        System.out.println(String.format("create BeanScopeModel,{sope=%s},{this=%s}", beanScope, this));
    }
}
