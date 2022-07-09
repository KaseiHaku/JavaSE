

public class AutoClose implements AutoCloseable {

    public void autoCloseResource(){
        
        // 该语法自动关闭 try() 中的所有实现了 AutoCloseable 接口的实例
        try(InputStream is = new ByteArrayInputStream();
                OutputStream os = null) {
            
            IOUtils.copy(is, os);
            
        } catch ( Exception e) {
            // 使用 try() 语法后，这里不用再关闭 流 了
            // is.close();
            // os.close();
        }
        
        
    }
  
}
