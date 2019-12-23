public class StreamUtil {

    
    /** todo 将缓冲输入流写入到一个缓冲输出流中去，不关闭流，遵从流谁打开谁关闭原则
     * */
    public static Boolean bufferedIs2Os(BufferedInputStream bis, BufferedOutputStream bos, Integer bufferSize) {
        try {
            byte[] buffer = new byte[bufferSize];
            int count = 0;
            while ((count = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, count);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
        return true;
    }

    /** todo 将输入流转化成输出流 
     * */
    public static void is2os(InputStream is, OutputStream os) throws IOException {
        if (is == null || os == null) {
            return ;
        }
        BufferedInputStream bis = new BufferedInputStream(is, 2048);
        byte[] buffer = new byte[2048];
        int count = 0;
        while((count = bis.read(buffer)) != -1){
            os.write(buffer, 0, count);
        }
    }
    
    /** todo 将输出流转化成输入流 
     * 使用 ByteArrayOutputStream 和 ByteArrayInputStream
     * 适用于数据量小的情况
     * */
    public static void os2is(){
    
    }
    
    /** todo 将输出流转化成输入流 
     * 使用 PipedOutputStream 和 PipedInputStream
     * 适用于数据量大的情况
     * @trap 必须启动两个线程，否则死锁
     * */
    public static void os2is(){
    
    }
    
    /** todo 将输出流转化成输入流 
     * 使用 CircularByteBuffer 
     * */
    public static void os2is(){
    
    }
    
    
    
}
