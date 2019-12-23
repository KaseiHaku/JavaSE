package kasei.javase.designpattern.establish.builder;

public class BoxBuilder {

    private Box box;

    public BoxBuilder(){
        this.box = new Box();
    }

    public BoxBuilder setBoxName(String boxName){
        this.box.setBoxName(boxName);
        return this;
    }

    public BoxBuilder addGood(Object obj){
        this.box.getList().add(obj);
        return this;
    }

    public Box getBox(){
        return this.box;
    }

}
