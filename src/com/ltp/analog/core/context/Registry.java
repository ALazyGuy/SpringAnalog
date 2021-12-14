package com.ltp.analog.core.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Registry<T> {

    private Map<String, T> values;

    public Registry() {
        values = new HashMap<>();
    }

    public void putIfAbsent(String key, T value){
        if(!values.containsKey(key)){
            values.put(key, value);
        }
    }

    public T get(String key){
        return values.get(key);
    }

    public List<T> findAll(Predicate<T> condition){
        return values
                .values()
                .stream()
                .filter(condition::test)
                .collect(Collectors.toList());
    }
}
