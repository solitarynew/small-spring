package cn.soli.springframework.beans.factory;

import cn.soli.springframework.BeansException;

public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object... args) throws BeansException;

}