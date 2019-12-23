package kasei.javase.se.frequently.io;

/** Java 流层次结构:********************************************************
 * InputStream  read()  close()
 *  │
 *  ├─ FileInputStream  从文件中进行以字节为单位的读取
 *  │		└─ SocketInputStream 封装了对Socket的字节型流式读取
 *  ├─ ByteArrayInputStream  从字节数组(byte[])中进行以字节为单位的读取
 *  ├─ FilterInputStream  用来"封装其它的输入流，并为它们提供额外的功能"
 *  │		├─ InflaterInputStream 从压缩数据源(zip)中以字节为单位读取数据
 *  │		│		└─ ZipInputStream 从zip文件中以字节为单位读取数据
 *  │		├─ BufferedInputStream: 开辟"内部字节数组"对输入流进行缓存，函数的返回也是一个字节数组
 *  │		└─ DataInputStream 是用来装饰其它输入流，它"允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型"。
 *  ├─ ObjectInputStream 从输入流中读取序列化后的数据，并进行反序列化(deserializes)
 *  └─ PipedInputStream 从管道中读取数据
 *
 * OutputStream  write()  flush()  close()
 * │
 * ├─ FileOutputStream  以字节为单位将数据写入到文件中
 * │		└─ SocketOutputStream 封装了对Socket的字节型流式写入
 * ├─ ByteArrayOutputStream  以字节为单位将数据写入到从字节数组(byte[])中
 * ├─ FilterOutputStream
 * │		├─ ZipOutputStream 从zip文件中以字节为单位读取数据
 * │		├─ PrintStream 是用来装饰其它输出流。它能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式
 * │		├─ BufferedOutputStream: 开辟"内部字节数组"对输入流进行缓存，函数的返回也是一个字节数组
 * │		└─ DataOutputStream 是用来装饰其它输入流，它"允许应用程序以与机器无关方式向底层输出流中写入基本 Java 数据类型"。
 * ├─ ObjectOutputStream 对数据进行序列化(serializes)，并向输出流中写入序列化后的数据
 * └─ PipedOutputStream 向管道中写入数据
 *
 * Reader read()  close()
 * │
 * ├─ InputStreamReader 封裝了InputStream在里头, 它以较高级的方式,一次读取一个一个字符，以文本格式输入/输出，可以指定编码格式。
 * │		└─ FileReader: 提供对文本文件(保存字符的文件)进行以字符为单位的读取
 * ├─ BufferedReader: readLine() 会一次性从物理流中读取8k(默认数值,可以设置)字节内容到内存，如果外界有请求，就会到这里存取，如果内存里没有才到物理流里再去读。即使读，也是再8k
 * ├─ CharArrayReader
 * ├─ FilterReader
 * ├─ PipedReader
 * └─ StringReader
 *
 * Writer write()  flush()  close()
 * │
 * ├─ OutputStreamWriter
 * │			└─ FileWriter
 * ├─ BufferedWriter newLine()
 * ├─ StringWriter
 * ├─ PipedWriter
 * ├─ PrintWriter
 * └─ CharArrayWriter
 * */

/** Java 流关闭原则：
 * 1. 自己创建的自己关闭，不是自己创建的不关闭
 * 2. 输出流完成前需要调用 os.flush() 方法，将缓冲区中的数据写入到流中，防止出现数据丢失的问题
 * */




