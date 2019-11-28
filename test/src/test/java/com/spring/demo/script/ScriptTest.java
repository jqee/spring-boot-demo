package com.spring.demo.script;

import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSON;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.var;
import org.junit.Test;

import javax.script.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author weibing@primeton.com
 * @date 2019/11/28 17:00
 */
public class ScriptTest {

    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("JavaScript");
    private Bindings map = new SimpleBindings();
    private String key = "key";
    private String value = "value";tes

    public void name() {
    }

    @Test
    public void test13() {
        map.put(key, "hello");
        map.put(value, 2019);
        Console.log("key: {}", map.get(key));
        Console.log("value: {}", map.get(value));
        map.remove(key);
        var year = map.get(value);
        Console.log("year: {}", year);
        Console.log("contains {} : {}", key, map.containsKey(key));
        Console.log("contains {} : {}", value, map.containsKey(value));
    }

    @Test
    public void test12() {
        eval("var year = 2019");
        var year = engine.get("year");
        Console.log("year's class: {}", year.getClass().getName());
        Console.log("year's value: {}", year);
    }

    @Test
    public void test11() {
        put("msg", "primeton.cn");
        eval("print(msg.toUpperCase());print(msg.indexOf(\"c\"));");
    }

    @Test
    public void test10() {
        put("msg", "Hello form Java program");
        eval("print(msg)");
    }

    private void put(String key, Object value) {
        engine.put(key, value);
    }

    @Test
    public void test9() {
        var map = new HashMap<>();
        engine.put("map", map);
        var script = "3 + 4; map.put(\"l\",\"v\");map.get(\"l\")";
        evalAndPrint(script);
        logJson(map);
    }

    private void logJson(Object obj) {
        Console.log(JSON.toJSONString(obj));
    }


    @Test
    public void test8() {
        String script = "1 + 2;";
        evalAndPrint(script);
        script = "1 + 2; 3+ 4;";
        evalAndPrint(script);
        script = "1 + 2; 3+ 4; var v =5; v = 6;";
        evalAndPrint(script);
        script = "1 + 2; 3+ 4; var v =5;";
        evalAndPrint(script);
        script = "print(\"1+2\")";
        evalAndPrint(script);
    }

    private void evalAndPrint(String script) {
        Console.log(eval(script));
    }

    @Test
    @SneakyThrows
    public void test7() {
        var path = Paths.get("test.js");
        var reader = Files.newBufferedReader(path);
        eval(reader);

    }

    @Test
    @SneakyThrows
    public void test6() {
        File file = new File("output.txt");
        Console.log("Script output will be written to {}", file.getAbsolutePath());
        @Cleanup FileWriter writer = new FileWriter(file);
        var context = engine.getContext();
        context.setWriter(writer);
        String script = "print(\"Hello custom output writer\")";
        eval(script);
    }

    @Test
    public void test5() {
        String script = "printf(\"%d + %d = %d\", 1, 2, 1 + 2);";
        eval(script);
    }

    @SneakyThrows
    private Object eval(String script) {
        return engine.eval(script);
    }

    @SneakyThrows
    private Object eval(Reader script) {
        return engine.eval(script);
    }

    @Test
    public void test4() {
        var script = "print(\"Hello\", \"World!\")";
        eval(script);
        script = "print(\"Hello World!\")";
        eval(script);

    }

    @Test
    @SneakyThrows
    public void test3() {
        var factory = engine.getFactory();
        var script = factory.getOutputStatement("\"primeton\"");
        Console.log("Syntax: {}", script);
        engine.eval(script);
    }

    @Test
    public void test2() {
        var manager = new ScriptEngineManager();
        execute(manager, "JavaScript", "print(\"hello JavaScript\")");
        execute(manager, "Groovy", "println(\"hello Groovy\")");
        execute(manager, "jython", "print \"hello JavaScript\"");
        execute(manager, "jruby", "puts(\"hello JRuby\")");

    }

    @SneakyThrows
    private static void execute(ScriptEngineManager manager, String name, String script) {
        ScriptEngine engine = manager.getEngineByName(name);
        if (engine == null) {
            Console.log("{} is not available.", name);
            return;
        }
        engine.eval(script);
    }


    @Test
    public void test1() {
        var manager = new ScriptEngineManager();
        var list = manager.getEngineFactories();
        list.forEach(ScriptTest::print);
    }

    private static void print(ScriptEngineFactory factory) {
        log("Engine Name", factory.getEngineName());
        log("Engine Version", factory.getEngineVersion());
        log("Language Name", factory.getLanguageName());
        log("Engine Version", factory.getLanguageVersion());
        log("Engine Short Names", factory.getNames());
        log("Mime Types", factory.getMimeTypes());
    }

    private static String format(String str) {
        return String.format("%-20s", str);
    }

    private static void log(String str, Object value) {
        str = format(str);
        Console.log("{}: {}", str, value);
    }
}
