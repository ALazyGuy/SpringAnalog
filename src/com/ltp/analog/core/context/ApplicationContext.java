package com.ltp.analog.core.context;

/* TODO
    Stage 1 -> Loading Configuration BeanDefinitions to context
    Stage 2 -> Creating beans of configuration classes
    Stage 3 -> Loading BeanDefinitions of components to context
    Stage 4 -> Configuring BeanDefinitions using configuration beans
    Stage 5 -> Creating beans of components
    Stage 6 -> Configuring beans using configuration beans
*/

public abstract class ApplicationContext {

    protected final Class<?> applicationClass;

    protected ApplicationContext(Class<?> applicationClass){
        this.applicationClass = applicationClass;
    }

    public Class<?> getApplicationClass(){
        return this.applicationClass;
    }

    public abstract void initialize();

}
