package kasei.boot.web.config;


/** TODO 适配器模式
 * 作用: 将一个 class A 的实例转换成 class B 的实例，A 和 B 毫无关系
 * */
public class AdapterPattern {


    interface Fruit {
        void eat();
    }

    static class Apple implements Fruit {
        @Override
        public void eat() {
            System.out.println("eat apple");
        }
    }

    interface Hammer {
        void strike();
    }

    static class IronHammer implements Hammer {
        @Override
        public void strike() {
            System.out.println("use iron hammer to strike");
        }
    }


    static class HammerFruit implements Fruit {
        Hammer hammer;

        public HammerFruit(Hammer hammer) {
            this.hammer = hammer;
        }

        @Override
        public void eat() {
            System.out.println("hammer fruit");
            hammer.strike();
        }
    }


    public static void main(String[] args) {


        Hammer hammer = new IronHammer();
        Fruit fruit = new HammerFruit(hammer);
        fruit.eat();

    }
}
