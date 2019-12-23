package kasei.frame;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class KaseiDynamicCompile {


    private static String dynamicCls = "package kasei; \r\npublic class DynamicCompileClass { \npublic void say(){ \nSystem.out.pintln(\"dynamic compile class's method say()\"); \n} \n}";


    private static File javaFileDir = new File("E:\\DynamicCompile"); // .java 文件保存路径
    private static File classFileRootDir = javaFileDir; // 生成的 .class 文件保存根目录
    private static String javaFileName = "DynamicCompileClass.java"; // dynamicCls 字符串在硬盘上生成的 .java 文件名称
    private static String absoluteClassName = "kasei.DynamicCompileClass"; // dynamicCls 字符串中类的全类名


    /**
     * todo 将字符串中的内容写入到文件中去
     * */
    private static void writeStr2File() throws FileNotFoundException {

        File javaFile = new File(javaFileDir, javaFileName); // 需要编译的 .java 文件
        PrintWriter pw = new PrintWriter(new FileOutputStream(javaFile));
        pw.write(dynamicCls);
        pw.flush();
        pw.close();
    }

    /**
     * @trap 坑：jdk 1.6
     * */
    public static void method1() throws Exception {

        writeStr2File();

        /* todo 动态编译硬盘中的 .java 文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); // 获取编译器实例
        /* compiler.run() 前三个参数传 null， 表示默认使用系统 标准输入、标准输出、标准错误输出
         * 最后一个参数： 要编译的 .java 文件的绝对路径
         * 中间的参数：编译时要传入的编译参数
         * */
        int compileResult = compiler.run (
                System.in,
                System.out,
                System.err,
                "-target", "1.8", "-encoding", "utf-8", "-d",
                classFileRootDir.getCanonicalPath(),
                new File(javaFileDir, javaFileName).getAbsolutePath()
        );
        if(compileResult != 0) {
            System.err.println("编译失败!!");
            return;
        }


        /* todo 反射的方式动态执行编译后的代码 */
        KaseiClassLoader kaseiClassLoader = new KaseiClassLoader(); // 需要使用自定义的类加载器
        Class clz = Class.forName(absoluteClassName,true,  kaseiClassLoader);
        Method method = clz.getMethod("say");
        method.invoke(clz.newInstance());
    }


    /**
     * @trap 坑：jdk 1.6
     * */
    public static void method2() throws Exception {

        writeStr2File();


        /* todo 数据预埋，对代码中所有需要的数据及变量，进行预先设置操作 */
        Writer w = new OutputStreamWriter(System.err); // 编译出错时，用于输出错误的流

        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();// 新建一个编译错误诊断收集器实例

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); // 获取编译器实例
        JavaFileManager javaFileManager = compiler.getStandardFileManager(diagnosticCollector, null, Charset.forName("utf-8"));  // 文件管理器

        List<String> options = new ArrayList<>(); // 编译器参数
        options.add("-target"); options.add("1.8"); // 使编译出来的代码兼容 1.8 版本的 JVM
        options.add("-encoding"); options.add("utf-8"); // .java 文件的编码格式
        options.add("-d"); options.add(classFileRootDir.getCanonicalPath()); // 编译后 .class 保存的根目录
        options.add("-sourcepath"); options.add("C:\\");// 表示编译当前的 .java 文件中，包含对其他文件的引用，其实就是引入依赖

        List<String> classes = new ArrayList<>(); // 参与编译的类


        StandardJavaFileManager standardJavaFileManager = (StandardJavaFileManager) javaFileManager;
        List<File> fileList = new ArrayList<>();
        fileList.add(new File(javaFileDir, javaFileName));
        Iterable<? extends JavaFileObject> javaFileObjects = standardJavaFileManager.getJavaFileObjectsFromFiles(fileList); // 需要编译的 .java 文件


        JavaCompiler.CompilationTask task = compiler.getTask(w, javaFileManager, diagnosticCollector, options, classes, javaFileObjects);


        /* todo 编译 .java 文件 */
        Boolean compileResult = task.call();
        javaFileManager.close();
        System.out.println(compileResult ? "编译成功" : "编译失败");


        List<Diagnostic<? extends JavaFileObject>> diagnostics = diagnosticCollector.getDiagnostics();
        for (Diagnostic item : diagnostics) {
            System.out.println(item.getCode());
            System.out.println(item.getStartPosition());
            System.out.println(item.getEndPosition());
            System.out.println(item.getColumnNumber());
            System.out.println(item.getMessage(null));
        }

        /* todo 反射的方式动态执行编译后的代码 */
        KaseiClassLoader kaseiClassLoader = new KaseiClassLoader(); // 需要使用自定义的类加载器
        Class clz = Class.forName(absoluteClassName,true,  kaseiClassLoader);
        Method method = clz.getMethod("say");
        method.invoke(clz.newInstance());
    }


    /**
     * @trap 坑：jdk 1.8
     * */
    public static void method3(){

    }


}
