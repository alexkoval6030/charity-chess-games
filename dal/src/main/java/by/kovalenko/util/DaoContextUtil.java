package by.kovalenko.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DaoContextUtil {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();

    public DaoContextUtil() {
        this.context = context;
    }

    public ApplicationContext getContext(){
        context.setConfigLocation("dao-application-context.xml");
        context.refresh();
        return context;
    }
}
