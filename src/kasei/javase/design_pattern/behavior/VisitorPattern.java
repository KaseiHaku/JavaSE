package kasei.demo;

public class VisitorPattern {

    /** todo Host 用于表示一个类是 被访问的，即房东 */
    private static interface Host {
        public void accept(Visitor visitor); // 该方法 实现类 决定到底调用 Visitor 中的哪个方法
    }



    private static class MyHouse implements Host {
        String color = "red";
        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }
    }
    private static class MyCar implements Host {
        String price = "1234";
        @Override
        public void accept(Visitor visitor) {
            visitor.visit2(this);
        }
    }


    /** todo Visitor 用于表示一个类是 访客 */
    private static interface Visitor {
        default public void visit(MyHouse host){}
        default public void visit2(MyCar car){}
    }



    private static class MyFriend implements Visitor {
        @Override
        public void visit(MyHouse myHouse) {
            System.out.println(myHouse.color);
        }
        @Override
        public void visit2(MyCar myCar) {
            System.out.println(myCar.price);
        }
    }



    public static void main(String[] args) {


        MyFriend visitor = new MyFriend();

        MyHouse house = new MyHouse();
        house.accept(visitor);

        MyCar car = new MyCar();
        car.accept(visitor);

    }


}
