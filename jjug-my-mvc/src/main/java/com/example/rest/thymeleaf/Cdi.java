package com.example.rest.thymeleaf;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import java.util.Set;

public class Cdi {

    public Object bean(String name) {
        CDI<Object> cdi = CDI.current();
        BeanManager beanManager = cdi.getBeanManager();
        Set<Bean<?>> beans = beanManager.getBeans(name);
        Bean<?> bean = beanManager.resolve(beans);
        Class<?> beanClass = bean.getBeanClass();
        return cdi.select(beanClass).get();
    }
}
