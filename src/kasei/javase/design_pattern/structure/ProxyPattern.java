package kasei.javase.designpattern.structure.proxy;

//代理模式：一个对象A可以由另一个对象B完全代理，A中的所有方法在B中都会被覆盖，且A和B属于同一个类的子类
public class ProxyPattern {



    private static interface DelegatorInterface {
        public void method();
    }


    private static class Delegator implements DelegatorInterface {
        @Override
        public void method() {
            System.out.println("委托人发放内容");
        }
    }

    /** todo 静态代理 */
    private static class AgentClass implements DelegatorInterface {
        private DelegatorInterface delegator ;

        public AgentClass(DelegatorInterface delegator){
            this.delegator = delegator;
        }
        @Override
        public void method() {
            System.out.println("代理类里面的内容");
            this.delegator.method();
        }
    }

    
    public static void main(String[] args) {
        AgentClass agentClass = new AgentClass(new Delegator());
        agentClass.method();

    }


}
