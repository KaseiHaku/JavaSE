package kasei.javase.se.thread;


public class ThreadInfo extends Thread{
	
	public boolean flag = true;
	@Override
	public void run() {
		while(flag) {
			System.out.println("我咧个去");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {		
				e.printStackTrace();
			}
		}
	}

	public static void testInfo() throws InterruptedException {
		/* 线程信息操作：
		 * Thread.currentThread() 获取当前线程
		 * setName()
		 * getName()
		 * isAlive()
		 * */
		
		Thread t = Thread.currentThread();//获取当前线程，写在哪个线程体中就获取哪个线程
		System.out.println("当前线程的名字："+ t.getName());
		t.setName("主线程");//设置线程名称
		System.out.println("当前线程的名字："+ t.getName());
		
		ThreadInfo t1 = new ThreadInfo();
		t1.start();
		System.out.println("t1线程还活着吗？"+t1.isAlive());
		t1.flag = false;
		Thread.sleep(5000);
		System.out.println("t1线程还活着吗？"+t1.isAlive());	
	}
	
	public static void testPriority() {
		/* 优先级：1-10  默认为5
		 * setPriority()
		 * getPriority()
		 * */
        CreateThread1 t1 = new CreateThread1();
		t1.setName("t1");
        CreateThread1 t2 = new CreateThread1();
		t2.setName("t2");
		t1.setPriority(MAX_PRIORITY);//设置最大优先级，表示的是执行的概率提高
		t2.setPriority(MIN_PRIORITY);
		t1.start();
		t2.start();
	}
	
	public static void main(String[] args)  {
		//testInfo();
		testPriority();
	}
}
