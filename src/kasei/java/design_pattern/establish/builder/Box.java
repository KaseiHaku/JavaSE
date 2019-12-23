package kasei.javase.designpattern.establish.builder;

import java.util.List;

public class Box {

    private String boxName;
    private List<Object> list;

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }
}
