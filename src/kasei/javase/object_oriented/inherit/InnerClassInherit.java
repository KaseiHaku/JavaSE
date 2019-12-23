package kasei.javase.se.objectoriented.inherit;

import kasei.javase.se.objectoriented.classes.InnerClass;

public class InnerClassInherit extends InnerClass.Hh {

    InnerClassInherit(InnerClass ic) {
        ic.super();
        //内部类的继承:构造函数必须有这句话 enclosingClassReference.super()
        //enclosing:  外围、外部、把什么围住的意思
    }

    public void print(){System.out.println("内部类的继承演示");}
}
