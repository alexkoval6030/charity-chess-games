package by.kovalenko.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceContextUtil {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();

    public ServiceContextUtil() {
        this.context = context;
    }

    public ApplicationContext getContext(){
        context.setConfigLocation("service-application-context.xml");
        context.refresh();
        return context;
    }
}
