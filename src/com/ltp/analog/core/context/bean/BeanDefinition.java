package com.ltp.analog.core.context.bean;

public class BeanDefinition {

    private final String name;
    private Class<?> clazz;

    public BeanDefinition(Class<?> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }

    public Class<?> getOriginalClass() {
        return clazz;
    }

    public void setOriginalClass(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public boolean isInterface(){
        return this.clazz.isInterface();
    }

}
