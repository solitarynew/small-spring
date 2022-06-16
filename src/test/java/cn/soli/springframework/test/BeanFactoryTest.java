package cn.soli.springframework.test;

import cn.soli.springframework.BeanDefinition;
import cn.soli.springframework.BeanFactory;
import cn.soli.springframework.test.bean.TestService;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void test_BeanFactory() {

        // init beanFactory
        BeanFactory beanFactory = new BeanFactory();

        // register bean
        beanFactory.registerBeanDefinition("testService", new BeanDefinition(new TestService()));

        // get bean
        TestService testService = (TestService) beanFactory.getBean("testService");
        testService.test();
    }
}
