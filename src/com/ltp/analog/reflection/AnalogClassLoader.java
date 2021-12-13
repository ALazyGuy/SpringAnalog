package com.ltp.analog.reflection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AnalogClassLoader extends ClassLoader{

    @Override
    public Class findClass(String name) {
        Class cl = findLoadedClass(name);

        if(cl != null){
            return cl;
        }

        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                fileName.replaceAll("[.]", "/") + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }

}
