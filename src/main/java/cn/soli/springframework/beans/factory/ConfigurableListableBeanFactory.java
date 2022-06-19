package cn.soli.springframework.beans.factory;

import cn.soli.springframework.beans.BeansException;
import cn.soli.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.soli.springframework.beans.factory.config.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
