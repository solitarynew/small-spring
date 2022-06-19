package cn.soli.springframework.beans.factory.support;

import cn.soli.springframework.beans.BeansException;
import cn.soli.springframework.core.io.Resource;
import cn.soli.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
