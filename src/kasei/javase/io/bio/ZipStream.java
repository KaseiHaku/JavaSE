
// import java.util.zip.ZipEntry; // 不使用 JDK 自带的，因为会出现乱码
// import java.util.zip.ZipOutputStream;

import org.apache.tools.zip.ZipEntry; 
import org.apache.tools.zip.ZipOutputStream;


public class ZipStream {

    /* todo 使用方法演示 */
    public static void demo() throws IOException {
        List<File> fileList = new ArrayList<>();
        fileList.add(new File("C:\\Users\\Kasei\\Desktop\\compact\\1dir"));
        fileList.add(new File("C:\\Users\\Kasei\\Desktop\\compact\\1empty"));
        fileList.add(new File("C:\\Users\\Kasei\\Desktop\\compact\\1file.txt"));

        String savePath = System.getProperty("java.io.tmpdir");
        zip(fileList, new File(savePath), "qwer", true);
    }
    
    /* todo 使用方法演示 */
    public static void demo2() throws IOException {
        Map<String, InputStream> isObjMap = new HashMap<>();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("C:\\Users\\Kasei\\Desktop\\compact\\qwer.zip"));

        String strStream = "我空间科技网大富科技nwddkf,dfkHSihsio胡搜IQ会忘记哦H哦以hiwehjhd分位数 我恶房间噢请问佛我发积分KJHWEFIFDKD,304R35R45DDADP93";
        putIs(isObjMap, "aa   ", new FileInputStream("C:\\Users\\Kasei\\Desktop\\compact\\1file.txt"));
        putIs(isObjMap, "bb\\ ", new ByteArrayInputStream(strStream.getBytes()));

        Boolean zipBoolean = zip(isObjMap, zos);
        zos.close();
    }
    
    /** todo 合法的往 Map 中添加 InputStream 对象
     * @param map 存放 InputStream 对象的 Map
     * @param entryPoint map 参数的 key
     * @param is 存入 InputStream 对象
     * @throws RuntimeException 格式校验出错
     * @trap(坑): 如果添加的 entryPoint 重复，会覆盖之前添加的 InputStream 对象
     * */
    public static void putIs(Map<String, InputStream> map, String entryPoint, InputStream is) throws RuntimeException {
        if(map==null || entryPoint==null || is==null){
            throw new RuntimeException("方法参数存在 null 值");
        }

        String trimEntryPoint = entryPoint.trim();
        if("".equals(trimEntryPoint)
                || trimEntryPoint.endsWith("/")
                || trimEntryPoint.endsWith("\\")
                || trimEntryPoint.startsWith("/")
                || trimEntryPoint.startsWith("\\")){
            throw new RuntimeException("第二个参数格式校验失败");
        }

        map.put(trimEntryPoint, is); // 会覆盖之前添加的同名 key 中的对象
    }


    /**
     * todo Demand(需求)：需要对指定的流进行压缩，比如该流的数据来自于网络、内存、字节数组等，当然也包括本地文件系统的文件流
     * @param isMap 需要压缩的输入流 Map
     * @param zos 压缩流对象
     * @return false: 表示处理失败
     * @trap 坑：注意要在上层函数中关闭 zos 流
     * */
    public static Boolean zip(Map<String, InputStream> isMap, ZipOutputStream zos){

        if (isMap==null || zos==null ) {
            System.out.println("方法参数存在 null 值");
            return false;
        }

        // 开始压缩流
        for (String key : isMap.keySet()) {
            InputStream is = isMap.get(key);
            ZipEntry zipEntry = new ZipEntry(key);
            try {
                zos.putNextEntry(zipEntry);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int count = 0;
                while((count =bis.read(buffer)) != -1){
                    zos.write(buffer, 0, count);
                }
                zos.closeEntry();
                // bis.close(); 不关闭流，让调用程序自行关闭
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    
    
    /** todo 对一个文件列表进行打包压缩，列表中的文件打包为一个文件夹，文件夹名称即为 zipFileName
     * @param fileList 需要压缩的文件列表
     * @param saveDir 压缩文件保存目录
     * @param zipFileName 压缩文件名称（不包含扩展名，扩展名固定为 .zip）
     * @param cover 是否覆盖同名文件
     * */
    public static Boolean zip(List<File> fileList, File saveDir, String zipFileName, Boolean cover) {

        if(!saveDir.exists() || "".equals(zipFileName)){
            System.out.println("保存目录不存在或者文件名为空");
            return false;
        }

        //创建 zip 输出流
        File zipFile = new File(saveDir.getAbsolutePath() + File.separator + zipFileName + ".zip");
        if(zipFile.exists() && !cover){
            System.out.println("保存目录中存在同名文件或同名文件夹 " + zipFileName + ".zip");
            return false;
        }

        if (zipFile.exists() && zipFile.isDirectory()) {
            System.out.println("保存目录中的同名文件为文件夹，无法覆盖" + zipFileName + ".zip");
            return false;
        }

        // java io 流中默认 append 为 false 即覆盖原文件
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (File item : fileList) {
                if(!item.exists()){
                    System.out.println("需要压缩的文件不存在");
                    zos.close();
                    zipFile.delete(); // 先关流 再删文件，因为流会占用文件，即文件被打开，跟 window 中删除文件提示被占用一样
                    return false;
                }
                compress(zos, item, zipFileName+File.separator);
            }
            zos.flush();
            zos.close();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            zos = null; // 此处置 null 是为了让 GC 更快的回收对象
        }
    }


    /** todo 压缩一个文件或文件夹
     * @param zos 压缩输出流.
     * @param file 被压缩的文件或者文件夹
     * @param base 目录进入点
     * */
    public static void compress(ZipOutputStream zos, File file, String base) throws IOException {

        if(file.isFile()){
            // 直接压缩
            ZipEntry zipEntry = new ZipEntry(base+file.getName());
            zos.putNextEntry(zipEntry);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

            byte[] buffer = new byte[1024];
            int count = 0;
            while((count =bis.read(buffer)) != -1){
                zos.write(buffer, 0, count);
            }
            zos.closeEntry();
            bis.close();
            bis = null;
            return ;
        }

        File[] childFileList = file.listFiles();
        if (childFileList.length == 0) {
            ZipEntry zipEntry = new ZipEntry(base+file.getName()+File.separator);
            zos.putNextEntry(zipEntry);
            zos.closeEntry();
            return ;
        }

        for (File item : childFileList) {
            compress(zos, item, base+file.getName()+File.separator);
        }
    }
}
