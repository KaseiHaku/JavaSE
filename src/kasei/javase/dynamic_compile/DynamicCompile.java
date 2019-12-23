package kasei;

import javax.sound.midi.Soundbank;
import javax.tools.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DynamicCompile {


    public static void main(String[] args) {
        execute();
    }


    public static void execute(){
        while(true){
           
            String inputStr = getInput();// 获取输出字符串
            System.out.println(inputStr);
        }
    }

    public static String getInput(){
        Scanner scaner = new Scanner(System.in);
        System.out.print("请输入 Java 代码: ");
        String result = scaner.nextLine();
        return result;
    }

    // todo 动态编译
    public static void compile(String javaStr){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); // 获取编译器对象
        DiagnosticCollector<JavaFileObject> diagnosticListener = new DiagnosticCollector<>(); // 新建一个编译错误诊断收集器
        JavaCompiler.CompilationTask task = compiler.getTask(out, fileManager, diagnosticListener, options, classes, compilationUnits);


        diagnosticListener.getDiagnostics().forEach(item -> System.out.println(item.toString()));


    }

}
