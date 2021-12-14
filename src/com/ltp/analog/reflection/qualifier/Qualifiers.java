package com.ltp.analog.reflection.qualifier;

public final class Qualifiers {

    public static ClassQualifier annotatedBy(Class annotation){
        return cl -> cl.isAnnotationPresent(annotation);
    }

    private Qualifiers(){}

}
