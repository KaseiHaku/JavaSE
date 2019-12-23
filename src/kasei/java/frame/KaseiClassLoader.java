package kasei.frame;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class KaseiClassLoader extends ClassLoader {


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
}
