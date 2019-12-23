package kasei.javase.designpattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;


// 备忘录类，保存多个备忘节点
public class Memo {

    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }

}
