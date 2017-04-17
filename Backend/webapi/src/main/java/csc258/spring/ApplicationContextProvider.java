package csc258.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by desair4 on 4/16/2017.
 * <p>
 * Any class can be registered as a Bean with Spring. If done so, the lifecycle of the Bean is then maintained by Spring.
 * In order to get Spring Beans in any non Spring Bean objects, we need ApplicationContext.
 * <p>
 * This class allows you to get application context anywhere in the application without relying on Spring.
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    public static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ac) {
        context = ac;
    }
}