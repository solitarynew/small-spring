package cn.soli.springframework.test;

import cn.hutool.core.io.IoUtil;
import cn.soli.springframework.core.io.DefaultResourceLoader;
import cn.soli.springframework.core.io.Resource;
import cn.soli.springframework.core.io.ResourceLoader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ResourceLoaderTest {

    ResourceLoader resourceLoader;

    @Before
    public void setUp() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource  = resourceLoader.getResource("src/test/resources/test.properties");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);
    }

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource  = resourceLoader.getResource("classpath:test.properties");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);
    }

    @Test
    public void testUrlResource() throws IOException {
        Resource resource  = resourceLoader.getResource("https://github.com/solitarynew/small-spring/tree/main/src/test/resources/test.properties");
        String content = IoUtil.readUtf8(resource.getInputStream());
        System.out.println(content);
    }
}
