package com.ltp.analog.core.context.bean;

public class Bean {

    private final String name;
    private final Class<?> implementation;

    public Bean(String name, Class<?> implementation) {
        this.name = name;
        this.implementation = implementation;
    }

    public String getName() {
        return name;
    }

    public Class<?> getImplementation() {
        return implementation;
    }

}
