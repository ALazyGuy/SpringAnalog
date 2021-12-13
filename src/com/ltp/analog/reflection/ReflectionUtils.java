package com.ltp.analog.reflection;

import com.ltp.analog.reflection.qualifier.ClassQualifier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {

    private static final AnalogClassLoader cl = new AnalogClassLoader();

    public static List<Class> getClassesInPackageRecursively(String packName, ClassQualifier qualifier) {
        return getClassesInPackageRecursively(packName)
                .stream()
                .filter(qualifier::test)
                .collect(Collectors.toList());
    }

    public static List<Class> getClassesInPackageRecursively(String packName){
        List<String> packs = getSubpackagesRecursively(packName);

        List<Class> result = new LinkedList<>();

        packs.forEach(pack -> result.addAll(getClassesInPackage(pack)));

        return result.stream().distinct().collect(Collectors.toList());
    }

    public static List<Class> getClassesInPackage(String packName){
        if(packName == null || packName.isEmpty()){
            return List.of();
        }

        URL url = ClassLoader.getSystemClassLoader().getResource(packName.replaceAll("[.]", "/"));

        if(url == null){
            return List.of();
        }

        File pack = new File(url.getPath());

        if(!pack.isDirectory() || pack.listFiles() == null){
            return List.of();
        }

        return Arrays.stream(pack.listFiles())
                .filter(File::isFile)
                .filter(f -> f.getName().endsWith(".class"))
                .map(f -> cl.findClass(packName + "." + f.getName().substring(0, f.getName().indexOf('.'))))
                .collect(Collectors.toList());
    }

    public static List<String> getSubpackagesRecursively(String packName){
        List<String> result = new LinkedList<>();

        for(String pack : getSubpackages(packName)){
            List<String> subPacks = getSubpackagesRecursively(pack);
            result.addAll(subPacks);
        }

        result.add(packName);

        return result.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> getSubpackages(String packName){
        if(packName == null || packName.isEmpty()){
            return List.of();
        }

        URL url = ClassLoader.getSystemClassLoader().getResource(packName.replaceAll("[.]", "/"));

        if(url == null){
            return List.of();
        }

        File pack = new File(url.getPath());

        if(!pack.isDirectory() || pack.listFiles() == null){
            return List.of();
        }

        return Arrays.stream(pack.listFiles())
                .filter(File::isDirectory)
                .map(f -> packName + "." + f.getName())
                .collect(Collectors.toList());
    }

    private ReflectionUtils(){}

}
