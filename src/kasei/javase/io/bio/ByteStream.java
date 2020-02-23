package main.java.se.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 字节流：
 * 输入流：InputStream  read()  close()
 * 	FileInputStream
 * 输出流：OutputStream  write()  flush()  close()
 * 	FileOutputStream
 * */
class ByteStream 
{
	
	public static void fileCopy(File s, File d){	
		if(s.exists()){
			if(s.isFile()){
				if(d.exists()){
					System.out.println("已存在同名文件或文件夹，操作失败！");
				}else{
					File d0 = d;
					List<File> fps = new ArrayList<File>();
					while(!d.getParentFile().exists()){
						fps.add(d.getParentFile());
						d = d.getParentFile();
					}				
					for(int i = fps.size()-1; i >= 0; i--){
						fps.get(i).mkdir();
					}
					
					//3、选择流
					InputStream is = null;
					OutputStream os = null;
					try {
						d0.createNewFile();
						is = new FileInputStream(s);
						os = new FileOutputStream(d0,true);//true：以追加的形式写入文件
						byte[] buffer = new byte[10];//定义用于存放数据的缓存
						int len = 0;//接收实际读取的大小
						while((len = is.read(buffer)) != -1){
							os.write(buffer, 0, len);//从 cache 字节数组的 0号下标开始写入 len 长度个字节
							os.flush();//强制写入
						}
					} catch (IOException e) {
						e.printStackTrace();
					} finally{
						try {
							//4、释放资源
							if(is!=null){ 
								is.close();
							}
							if(os!=null){ 
								os.close();
							} 			
						}catch (IOException e) {
							System.out.println("关闭流失败！");
						}
					}
					System.out.println("文件复制成功！");
				}
			}	
		}
	}
		
	
	public static void copyDir(File src, File dest){
		
		if(!src.exists()){
			System.out.println("源文件不存在，无法复制！");
			return ;
		}
		
		if(src.isDirectory()){
			dest = new File(dest,src.getName());
		}
		copyDirDetail(src, dest);
	}
	
	public static void copyDirDetail(File src, File dest){
		if(src.isFile()){
			fileCopy(src, dest);
		}else if(src.isDirectory()){
			if(dest.isFile()){
				System.out.println("目标路径是一个文件，不能创建同名的文件夹");
			}else {
				dest.mkdirs();
				
				//获取下一级的目录和文件
				for(File sub:src.listFiles()){
					copyDirDetail(sub, new File(dest, sub.getName()));
				}
			}		
		}
	}

	static void del(File a){//递归删除文件夹的第一个文件
		if(a.listFiles().length!=0){
			del(a.listFiles()[0]);
		}
		a.delete();
	}
	
	public static void main(String[] args) {
//		File f1 = new File("e:/123.png");
//		File f2 = new File("e:/aa/123.png");
//		A.fileCopy(f1, f2);
		File dir1 = new File("e:/src");
		File dir2 = new File("e:/aa/");
		copyDir(dir1, dir2);
		//del(dir2);		
	}
}