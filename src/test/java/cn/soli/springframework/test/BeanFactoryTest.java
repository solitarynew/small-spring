package cn.soli.springframework.test;

import cn.soli.springframework.factory.config.BeanDefinition;
import cn.soli.springframework.factory.BeanFactory;
import cn.soli.springframework.factory.support.DefaultListableBeanFactory;
import cn.soli.springframework.test.bean.TestService;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void test_BeanFactory() {

        // init beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // register bean
        BeanDefinition beanDefinition = new BeanDefinition(TestService.class);
        beanFactory.registerBeanDefinition("testService", beanDefinition);

        // get bean firstly
        TestService testService = (TestService) beanFactory.getBean("testService", "user1");
        testService.test();

        // get bean secondly
        TestService testService2 = (TestService) beanFactory.getBean("testService", "user2");
        testService2.test();
    }
}
