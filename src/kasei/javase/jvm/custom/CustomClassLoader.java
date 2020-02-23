package kasei.javase.jvm.custom;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class CustomClassLoader extends ClassLoader {

    /** todo 复写 loadClass 方法，破坏双亲委托机制 */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        /* todo 1. 父 ClassLoader 没有加载到指定的类，那么用当前加载器加载 */
        File rootDirOfLoadeClasses = new File("E:\\DynamicCompile\\");  // 类加载的根路径，因为 .class 文件是根据包名分目录编译的
        String relativePath = name.replace(".", "/"); // 全类名中的 . 替换为 /
        Class<?> cls = null;
        File classFile = new File(rootDirOfLoadeClasses, relativePath + ".class");
        try {
            // 从硬盘中读取 .class 文件
            FileInputStream fis = new FileInputStream(classFile);
            FileChannel fc = fis.getChannel();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            WritableByteChannel wbc = Channels.newChannel(bos);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (true){
                int i = fc.read(byteBuffer);
                if (i == 0 || i == -1)
                    break;
                byteBuffer.flip();
                wbc.write(byteBuffer);
                byteBuffer.clear();
            }
            fis.close();

            byte[] bytes = bos.toByteArray();

            cls = this.defineClass(name, bytes, 0, bytes.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /* todo 2. 到最后都没有加载到指定的类，那么抛异常 */
        if (cls == null) {
            // 到最后都没有找到 .class 文件，那么报异常
            throw new ClassNotFoundException(name);
        }

        return cls;
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();  // 获取 应用(系统)类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent(); // 获取 扩展类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent(); // 获取 启动类加载器，Bootstrap 类加载器是由原生代码实现，所以返回 null
        
        System.out.println(System.getProperty("java.ext.dirs"));  // 获取扩展类加载器 Launcher$ExtClassLoader 从哪些目录加载类
        System.out.println(System.getProperty("java.class.path"));  // 获取应用类加载器 Launcher$AppClassLoader 从哪些目录加载类，如果命令行没有指定 -classpath 参数，则默认加载路径为当前目录
        
        
        /* todo 使用自定义类加载器加载类 */
        // 由于破坏了双亲委托机制导致 Object 类找不到，报错
        Class<?> aClass = Class.forName("kasei.MyClassLoaderTest", true, new CustomClassLoader()); 
        Method[] methods = aClass.getMethods();
        methods[0].invoke(null, null);
    
    }
    
    
}
