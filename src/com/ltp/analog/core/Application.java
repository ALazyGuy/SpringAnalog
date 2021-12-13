package com.ltp.analog.core;

import com.ltp.analog.core.context.AnnotationApplicationContext;
import com.ltp.analog.core.context.ApplicationContext;

public final class Application {

    private static ApplicationContext context;

    public static ApplicationContext getContext(){
        return context;
    }

    public static void run(Class<?> applicationClass){
        context = new AnnotationApplicationContext(applicationClass);
        context.initialize();
    }

    private Application(){}

}
