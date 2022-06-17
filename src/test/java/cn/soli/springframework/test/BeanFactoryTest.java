package cn.soli.springframework.test;

import cn.soli.springframework.beans.factory.PropertyValue;
import cn.soli.springframework.beans.factory.PropertyValues;
import cn.soli.springframework.beans.factory.config.BeanDefinition;
import cn.soli.springframework.beans.factory.config.BeanReference;
import cn.soli.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.soli.springframework.test.bean.TestDao;
import cn.soli.springframework.test.bean.TestService;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void test_BeanFactory() {

        // init beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // register bean
        beanFactory.registerBeanDefinition("testDao", new BeanDefinition(TestDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("testDao", new BeanReference("testDao")));
        propertyValues.addPropertyValue(new PropertyValue("name", "testService"));
        beanFactory.registerBeanDefinition("testService", new BeanDefinition(TestService.class, propertyValues));

        // get bean
        TestService testService = (TestService) beanFactory.getBean("testService");
        testService.test();
    }
}
