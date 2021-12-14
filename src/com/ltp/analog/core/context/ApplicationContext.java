package com.ltp.analog.core.context;

/* TODO
    Stage 1 -> Loading Configuration BeanDefinitions to context
    Stage 2 -> Creating beans of configuration classes
    Stage 3 -> Loading BeanDefinitions of components to context         DONE!
    Stage 4 -> Configuring BeanDefinitions using configuration beans
    Stage 5 -> Creating beans of components
    Stage 6 -> Configuring beans using configuration beans
*/

import com.ltp.analog.core.context.bean.Bean;
import com.ltp.analog.core.context.bean.BeanDefinition;

import java.util.LinkedList;
import java.util.List;

public abstract class ApplicationContext {

    protected final List<String> scans = new LinkedList<>();
    protected final Class<?> applicationClass;
    protected final Registry<BeanDefinition> beanDefinitionRegistry = new Registry<>();
    protected final Registry<Bean> beanRegistry = new Registry<>();

    protected ApplicationContext(Class<?> applicationClass){
        this.applicationClass = applicationClass;
    }

    public Class<?> getApplicationClass(){
        return this.applicationClass;
    }

    public void appendScanningPackage(String pack){
        if(!scans.contains(pack))
            scans.add(pack);
    }

    public void initialize(){
        loadBeanDefinitions();
        createBeansFromBeanDefinitions();
    }

    public Bean getBean(String name){
        return beanRegistry.get(name);
    }

    protected abstract void loadBeanDefinitions();
    protected abstract void createBeansFromBeanDefinitions();

}
