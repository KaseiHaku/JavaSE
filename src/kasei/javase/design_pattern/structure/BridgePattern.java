
/** TODO 桥接模式
 * 作用：将一个功能的实现，抽象成为一个接口，使功能实现和实际调用类解耦
 * */
public class BridgePattern {

    interface Body {
        public void eat();
    }

    static class PigBody implements Body {
        @Override
        public void eat() {
            System.out.println("pig eating");
        }
    }

    static abstract class Soul {
        protected Body body;

        public Soul(Body body) {
            this.body = body;
        }

        public abstract void eat();
    }

    static class HumanSoul extends Soul {

        public HumanSoul(Body body) {
            super(body);
        }

        @Override
        public void eat() {
            this.body.eat();
        }
    }


    public static void main(String[] args) {
        Body body = new PigBody();
        Soul soul = new HumanSoul(body);
        soul.eat();
    }


}
