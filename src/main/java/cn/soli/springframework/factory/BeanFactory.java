package cn.soli.springframework.factory;

import cn.soli.springframework.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;
}
