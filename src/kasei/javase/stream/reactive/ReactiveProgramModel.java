/** 
 * 由于 reactive 的编程方式，每调用一个方法，就生成一个新的 Publisher 实例，所以根据具体原始实例在方法中的调用顺序，可以分为两类
 *  头插式：执行一个方法，在原有的 Publisher 之前插入其他 Operation(数据操作逻辑)
 *  尾插式：执行一个方法，在原有的 Publisher 之后插入其他 Operation(数据操作逻辑)
 * */
public class ReactiveProgramModel {

    public Flux init(){
        return new Flux();
    }
  
    public Flux headInsertPattern(){
        Flux init = init();
        // 这里相当于返回了新的管道，而不是在老的管道上添加操作，只是新的管道依赖老的管道，新管道执行中途，调用老管道执行
        return Flux.from(1,6).faltMap( i -> init.concat(i) );    
    }
    
    /** 尾插式： */
    public Flux tailInsertPattern(){
        Flux init = init();
        return init.map( () -> {} );   // 当 subscribe 时，这里的 lambda 表达式，在 init() 方法之后执行, 但是 subscribe 本身则是 lambda 表达式先被 订阅
    }
    
    
    
    public static void main(){
    
    
    
    }





}
