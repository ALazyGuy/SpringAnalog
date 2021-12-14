package com.ltp.analog.core;

import com.ltp.analog.core.annotation.Main;
import com.ltp.analog.core.context.AnnotationApplicationContext;
import com.ltp.analog.core.context.ApplicationContext;

public final class Application {

    private static ApplicationContext context;

    public static ApplicationContext getContext(){
        return context;
    }

    public static void run(Class<?> applicationClass){
        if(!applicationClass.isAnnotationPresent(Main.class)){
            throw new RuntimeException("Unable to start application from class not annotated by @Main");
        }

        context = new AnnotationApplicationContext(applicationClass);
        context.appendScanningPackage(applicationClass.getPackageName());
        context.initialize();
    }

    private Application(){}

}
