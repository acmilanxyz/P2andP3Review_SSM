package org.spoto.utlis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
    public static Object getBean(String file, String beanName) {
        ApplicationContext conf = new ClassPathXmlApplicationContext(file);
        return conf.getBean(beanName);
    }
}
