package main.java.se.io.file;

import java.io.File;
import java.io.IOException;

class TestFile {
	public static void main(String[] args){
		System.out.println("路径分隔符："+File.pathSeparator);
		System.out.println("文件分隔符："+File.separator);
		
		//创建 File 实例
		File dir1 = new File("E:/aa");//目录
		File file1 = new File(dir1,"yy.exe");
		File file2 = new File("e:/aa","rr.txt");
		File file3 = new File("cc.c");//以当前工作目录

		
		//创建文件或目录
		dir1.mkdir();//创建目录
		try {
			file2.createNewFile();//创建新文件
			file2.delete();//删除文件及目录
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//获取文件属性

		dir1.getParentFile();//获取父目录
		dir1.getName();//获取文件名或目录名
		boolean bool = dir1.exists();
		bool = dir1.isFile();
		bool = dir1.isDirectory();
		String str = file2.getAbsolutePath();//获取绝对路径
		long length = file1.length();//获取文件的长度
		long time = dir1.lastModified();//获取文件最后的修改时间
		dir1.renameTo(new File("./array"));//修改文件名
		
		File[] files = dir1.listFiles();//获取一个目录下所有的子目录和文件
		for(int i=0; i<files.length; i++){
			System.out.println(files[i].getName());
		}
		
	
		//创建临时文件
		try{
			File temp = File.createTempFile("temp", ".temp", new File("e:/aa"));
			Thread.sleep(5000);
			temp.deleteOnExit();
		}catch (IOException e) { 
			e.printStackTrace(); 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		}		
	}
}
