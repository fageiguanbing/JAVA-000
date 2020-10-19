package fageiguanbing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        Class<?> hello = new MyClassLoader().findClass("Hello");
        Object o = hello.newInstance();
        Method helloMethod = hello.getMethod("hello");
        helloMethod.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("/Users/yikaifei/Downloads/Hello/Hello.xlass");
        int length = (int) file.length();
        byte[] b = new byte[length];
        try {
            new FileInputStream(file).read(b);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        }
        for (int i = 0; i < length; i++) {
            b[i] = (byte) (255 - b[i]);
        }
        return defineClass(name, b, 0, length);
    }
}
