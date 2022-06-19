package cn.soli.springframework.core.io;

import java.io.IOException;

public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);

}
