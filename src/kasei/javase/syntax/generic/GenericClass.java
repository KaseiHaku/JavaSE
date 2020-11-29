public class GenericClass<T, U>{

    public <T, U> T method1(){
    }
  
    public genericMethodInvoke(){
        GenericClass.<String, Integer>method();  // 静态泛型方法调用时，可以在 className.<> 后加入泛型
        this.<String, Integer>method();  // 类内部调用泛型方法时，可以在 this.<> 后加入反省
        instance.<String, Integer>method(); // 通过实例调用泛型方法， instance.<>
    }

}
