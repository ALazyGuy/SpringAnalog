package com.ltp.analog.reflection.qualifier;

public class Qualifiers {

    public static ClassQualifier annotatedBy(Class<?> annotation){
        return (cl) -> cl.getAnnotation(annotation) != null;
    }

    private Qualifiers(){}

}
