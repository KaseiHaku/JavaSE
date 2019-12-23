package kasei.demo;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioDemo {

    public static void nio() throws IOException {
        Channel channel = null;
        Buffer buffer = null;
        Selector selector = null;

        Path path = Paths.get("C:\\Users\\Kasei\\Desktop", "nio", "beRead.txt");
        OpenOption openOption = StandardOpenOption.READ;
        FileChannel fileChannel = FileChannel.open(path, openOption);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int readResult = fileChannel.read(byteBuffer);
        while (readResult != -1) {
            System.out.println("Read " + readResult);
            byteBuffer.flip(); // 将 buffer 从被写模式切换到被读模式

            while(byteBuffer.hasRemaining()){
                System.out.print((char) byteBuffer.get()); // 从 buffer 中读取数据
            }

            byteBuffer.clear(); // 清空 buffer ，并使 buffer 回到被写模式
            byteBuffer.compact();// 只清空已经被读取的数据，未被读取的数据，将放到 buffer 起始位置，供下次读取，并使 buffer 回到被写模式
            readResult = fileChannel.read(byteBuffer); // 从 channel 中读取数据，写入到 buffer 中
        }
        fileChannel.close();
    }


    public static void nio2() throws IOException {

        Charset charset = Charset.forName("utf-8");
        CharsetEncoder charsetEncoder = charset.newEncoder();
        String data = "1234567890\nabcdefg";

        Path path = Paths.get("C:\\Users\\Kasei\\Desktop", "nio", "beWrite.txt");
        OpenOption openOption = StandardOpenOption.WRITE;
        FileChannel fileChannel = FileChannel.open(path, openOption);

        CharBuffer charBuffer = CharBuffer.allocate(256);
        charBuffer.clear(); // 设置 charBuffer 为被写模式
        charBuffer.put(data); // 手工往 buffer 里面写数据
        charBuffer.flip();
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get()); // 从 buffer 中读取数据
        }


        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        byteBuffer.clear();
        byteBuffer.put(data.getBytes(charset));
        byteBuffer.flip();
        System.out.println("");
        while (byteBuffer.hasRemaining()) {
            System.out.print((int) byteBuffer.get()); // 从 buffer 中读取数据，这行代码导致 1 丢失
            int writeResult = fileChannel.write(byteBuffer);
        }

        fileChannel.force(false); // true 包含 metaData 一起写入
        fileChannel.close();

    }
}
