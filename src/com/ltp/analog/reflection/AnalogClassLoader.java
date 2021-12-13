package com.ltp.analog.reflection;

import com.ltp.analog.core.Application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AnalogClassLoader extends ClassLoader {

    public AnalogClassLoader() {
        super(Application.getContext().getApplicationClass().getClassLoader());
    }

    @Override
    public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
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

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            try {
                super.loadClass(name, resolve);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (c == null) {
                c = findClass(name);
            }

            if(c == null){
                c = findClass(name);
            }

        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
