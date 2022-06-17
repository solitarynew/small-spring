package cn.soli.springframework.beans.factory.support;

import cn.soli.springframework.beans.BeansException;
import cn.soli.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException;
}
