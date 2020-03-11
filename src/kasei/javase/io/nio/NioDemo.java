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

    
    public static void nioGetTextFromFile() throws IOException {

        Path path = Paths.get("src/test/resources/Xml.xml");
        System.out.println(path.toUri());
        OpenOption option = StandardOpenOption.READ;
        FileChannel fileChannel = FileChannel.open(path, option);

        ByteBuffer byteBuffer =  ByteBuffer.allocate(1);    // 创建一个 byte buffer 用于保存从文件中读取的字节
        CharBuffer charBuffer = CharBuffer.allocate(16);    // 创建一个 char buffer 用于保存读取的字节所转换成的 char
        StringBuilder sb = new StringBuilder();             // 最终字符串


        /* 由于 java 中字符在内存中都是 unicode 编码，所以以下描述中的 byte ，其实就是 unicode 编码的字符存储的 byte  */
        // utf-8 解码器：将 byte 转成 utf8 字符，
        CharsetDecoder utf8Decoder = Charset.forName("utf-8").newDecoder()
                .onMalformedInput(CodingErrorAction.IGNORE)         // 当遇到非法的字节时，忽略
                .onUnmappableCharacter(CodingErrorAction.REPORT);   // 当遇到不可映射字符时，报错
        
        // utf-8 编码器：将 utf8 字符转成 byte，
        CharsetEncoder utf8Encoder = Charset.forName("utf-8").newEncoder()
                .onMalformedInput(CodingErrorAction.IGNORE).onUnmappableCharacter(CodingErrorAction.REPORT); 


        int readResult = 0;
        do {
            readResult = fileChannel.read(byteBuffer); // 从 channel 中读取数据，写入到 byte buffer 中
            System.out.println("Read " + readResult);
            byteBuffer.flip();// 将 byte buffer 从被写模式切换到被读模式

            CharBuffer unicodeCharBuffer = utf8Decoder.decode(byteBuffer); // 使用 utf-8 解码器，将字节解析成对应的 unicode 编码
            while(unicodeCharBuffer.remaining()>0){
                sb.append(unicodeCharBuffer.get());
            }

            // byteBuffer.clear(); // 清空 buffer ，并使 buffer 回到被写模式
            byteBuffer.compact();// 只清空已经被读取的数据，未被读取的数据，将放到 buffer 起始位置，供下次读取，并使 buffer 回到被写模式

        } while (readResult != -1); // 必须是 -1 ，因为 -1 是文件尾标记
        fileChannel.close();

        System.out.println(sb);
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
