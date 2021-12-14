package com.ltp.analog.core.context;

import com.ltp.analog.core.annotation.Component;
import com.ltp.analog.core.context.bean.Bean;
import com.ltp.analog.core.context.bean.BeanDefinition;
import com.ltp.analog.reflection.ReflectionUtils;
import com.ltp.analog.reflection.qualifier.Qualifiers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AnnotationApplicationContext extends ApplicationContext{

    public AnnotationApplicationContext(Class<?> applicationClass) {
        super(applicationClass);
    }

    @Override
    protected void loadBeanDefinitions() {
        List<Class> componentClasses = new LinkedList<>();
        scans.forEach(s -> componentClasses.addAll(ReflectionUtils.getClassesInPackageRecursively(s, Qualifiers.annotatedBy(Component.class))));
        componentClasses
                .stream()
                .distinct()
                .forEach(cl -> beanDefinitionRegistry.putIfAbsent(cl.getName(), new BeanDefinition(cl, cl.getName())));
    }

    @Override
    protected void createBeansFromBeanDefinitions() {
        List<BeanDefinition> interfaces = beanDefinitionRegistry.findAll(BeanDefinition::isInterface);
        List<BeanDefinition> classes = beanDefinitionRegistry.findAll(bd -> !bd.isInterface());

        //TODO Add multiple implementation

        for(BeanDefinition inter : interfaces){
            BeanDefinition implementation = null;

            for(BeanDefinition clazz : classes){
                if(Arrays.stream(clazz.getOriginalClass().getInterfaces()).anyMatch(i -> i.getName().equals(inter.getName()))){
                    implementation = clazz;
                    break;
                }
            }

            if(implementation != null){
                classes.remove(implementation);
                beanRegistry.putIfAbsent(inter.getName(), new Bean(inter.getName(), implementation.getOriginalClass()));
            }else{
                beanRegistry.putIfAbsent(inter.getName(), new Bean(inter.getName(), null));
            }

        }

        for(BeanDefinition clazz : classes){
            beanRegistry.putIfAbsent(clazz.getName(), new Bean(clazz.getName(), clazz.getOriginalClass()));
        }

    }
}
