package kasei.javase.designpattern.behavior.template;

public abstract class GameTemplate {

    //模板模式：规定一个类的方法的执行逻辑，让其子类不能修改该方法
    public final void play(){

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }

    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();
}
