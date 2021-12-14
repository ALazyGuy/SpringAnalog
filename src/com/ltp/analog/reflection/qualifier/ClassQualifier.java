package com.ltp.analog.reflection.qualifier;

@FunctionalInterface
public interface ClassQualifier {
    boolean test(Class cl);
}
