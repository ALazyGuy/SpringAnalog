package com.ltp.analog.core.context;

public class AnnotationApplicationContext extends ApplicationContext{

    public AnnotationApplicationContext(Class<?> applicationClass) {
        super(applicationClass);
    }

    @Override
    public void initialize() {

    }
}
