package com.primeton.jackson.test.demo3;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author weibing@primeton.com
 * @date 2019/10/12 11:04
 */
public class Proxy {
    public static Object newProxyInstance() throws IOException {
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("TimeProxy")
                .addSuperinterface(Flyable.class);
        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class, "flyable", Modifier.PRIVATE).build();
        typeSpec.addField(fieldSpec);
        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Flyable.class, "flyable")
                .addStatement("this.flyable=flyable")
                .build();
        typeSpec.addMethod(methodSpec);
        for (Method method : Flyable.class.getDeclaredMethods()) {
            MethodSpec methodSpec1 = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addStatement("long start =$T.currentTimeMillis()", System.class)
                    .addCode("\n")
                    .addStatement("this.flyable." + method.getName() + "()")
                    .addCode("\n")
                    .addStatement("long end =$T.currentTimeMillis()", System.class)
                    .addStatement("$T.out.println(\"Fly time = \"+(end-start)/1000+\"s\")", System.class)
                    .build();
            typeSpec.addMethod(methodSpec1);
        }
        JavaFile file = JavaFile.builder("com.primeton.jackson.test.demo3", typeSpec.build()).build();
        file.writeTo(new File("jackson-test/src/main/java"));
        return null;
    }

    public static Object newProxyInstance(Class clazz) throws IOException {
        TypeSpec.Builder typeSpec = TypeSpec.classBuilder("TimeProxy")
                .addSuperinterface(clazz);
        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class, "flyable", Modifier.PRIVATE).build();
        typeSpec.addField(fieldSpec);
        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Flyable.class, "flyable")
                .addStatement("this.flyable=flyable")
                .build();
        typeSpec.addMethod(methodSpec);
        for (Method method : clazz.getDeclaredMethods()) {
            MethodSpec methodSpec1 = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addStatement("long start =$T.currentTimeMillis()", System.class)
                    .addCode("\n")
                    .addStatement("this.flyable." + method.getName() + "()")
                    .addCode("\n")
                    .addStatement("long end =$T.currentTimeMillis()", System.class)
                    .addStatement("$T.out.println(\"Fly time = \"+(end-start)/1000+\"s\")", System.class)
                    .build();
            typeSpec.addMethod(methodSpec1);
        }
        JavaFile file = JavaFile.builder("com.primeton.jackson.test.demo3", typeSpec.build()).build();
        file.writeTo(new File("jackson-test/src/main/java"));
        return null;

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Proxy.newProxyInstance();
        String sourcePath = "jackson-test\\src\\main\\java";
        MyJavaCompiler.compile(new File("jackson-test\\src\\main\\java\\com\\primeton\\jackson\\test\\demo3\\TimeProxy.java"));
        URL[] urls = new URL[]{new URL("file:/" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("com.primeton.jackson.test.demo3.TimeProxy");
        Constructor constructor = clazz.getConstructor(Flyable.class);
        Flyable flyable = (Flyable) constructor.newInstance(new Bird());
        flyable.fly();
    }
}
