package kasei.javase.se.objectoriented.classes;

/** 实例内部类：即内部类类名前面没有 static 修饰的内部类 
 * */
//在另一个类中创建内部类对象语法：必须先存在外部类对象，才能创建内部类对象,
//OuterClass.InnerClass innerObj = outerObj.new InnerClass();
public class InnerClass {
    private int i;
    private void ddl(){
        System.out.println("内部类访问外部类成员函数：InnerClass.ddl()");
    }
	
    public void bb(){
        Hh hh = new Hh(); // 外部类想要访问内部类里的字段或方法，必须新建一个内部类对象
        System.out.println(hh.priStr); // 坑：外部类中可以直接访问内部类私有成员，而不受访问修饰符影响
        
    }
    
    
    //内部类
    public class Hh{
        private String priStr = "内部类私有字段";
        //1、内部类中可直接访问外部类的所有成员
        void print(){
            System.out.println("内部类访问外部类成员变量：i = " + i);
            ddl();
        }

        //2、不能在内部类中定义静态成员
        //static int i;//方法也不行

        // 在内部类中调用外部类当前对象  InnerClass.this == 外部类当前对象
        public void innerPrint(){
            InnerClass.this.ddl();
        }	
    }
}


