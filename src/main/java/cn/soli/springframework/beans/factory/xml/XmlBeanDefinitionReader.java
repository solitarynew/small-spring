package cn.soli.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.soli.springframework.beans.BeansException;
import cn.soli.springframework.beans.PropertyValue;
import cn.soli.springframework.beans.PropertyValues;
import cn.soli.springframework.beans.factory.config.BeanDefinition;
import cn.soli.springframework.beans.factory.config.BeanReference;
import cn.soli.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import cn.soli.springframework.beans.factory.support.BeanDefinitionRegistry;
import cn.soli.springframework.core.io.Resource;
import cn.soli.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        Resource resource = getResourceLoader().getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
            if (!node.getNodeName().equals("bean")) {
                continue;
            }

            Element beanElement = (Element) node;
            String name = beanElement.getAttribute("name");
            String id = beanElement.getAttribute("id");
            String className = beanElement.getAttribute("class");

            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            beanName = StrUtil.isNotEmpty(beanName) ? beanName : StrUtil.lowerFirst(clazz.getSimpleName());

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            NodeList propertyNodeList = beanElement.getChildNodes();

            for (int j = 0; j < propertyNodeList.getLength(); j++) {
                Node propertyNode = propertyNodeList.item(j);
                if (!(propertyNode instanceof Element)) {
                    continue;
                }
                if (!propertyNode.getNodeName().equals("property")) {
                    continue;
                }

                Element propertyElement = (Element) propertyNode;
                String propertyName = propertyElement.getAttribute("name");
                String propertyValue = propertyElement.getAttribute("value");
                String propertyRef = propertyElement.getAttribute("ref");

                if (!StrUtil.isNotEmpty(propertyName)) {
                    continue;
                }

                if (StrUtil.isNotEmpty(propertyValue)) {
                    beanDefinition.getPropertyValues().addPropertyValue(
                            new PropertyValue(propertyName, propertyValue));
                } else if (StrUtil.isNotEmpty(propertyRef)) {
                    beanDefinition.getPropertyValues().addPropertyValue(
                            new PropertyValue(propertyName, new BeanReference(propertyRef)));
                }

            }

            // TODO

        }
    }

}
