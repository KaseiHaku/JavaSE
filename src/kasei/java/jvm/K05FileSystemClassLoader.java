package main.java.jvm;

import java.io.*;

/**
 * 自定义文件系统类加载器
 * */
class K05FileSystemClassLoader extends ClassLoader
{
	private String rootDir;
	
	public K05FileSystemClassLoader(String rootDir)
	{
		this.rootDir = rootDir;
	}
	
	@Override
	protected Class<?> findClass(String name)throws ClassNotFoundException
	{
		Class<?> cls = findLoadedClass(name);
		
		if(cls!=null) {//查询是否加载过这个类	
			return cls;
		}else
		{
			ClassLoader parent = this.getParent();
			cls = parent.loadClass(name);
			
			if(cls!=null)
			{
				return cls;
			}else
			{
				byte[] classData = getClassData(name);
				if(classData==null)
				{
					throw new ClassNotFoundException();
				}else
				{
					cls = defineClass(name, classData, 0, classData.length);
				}
			}
		}
		return cls;
	}
	
	private byte[] getClassData(String className)
	{
		String path = rootDir+"/"+className.replace('.', '/')+".class";
		
		InputStream is=null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try 
		{
			is = new FileInputStream(path);
			byte[] buffer= new byte[1024];
			int len = 0;
			while((len=is.read(buffer))!=-1)
			{
				baos.write(buffer, 0, len);
			}
			return baos.toByteArray();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}finally
		{
			try 
			{
				if(is!=null)
				{		
					is.close();		
				}
				if(baos!=null)
				{
					baos.close();
				}
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
}
