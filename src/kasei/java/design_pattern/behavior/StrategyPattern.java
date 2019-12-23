
public class StrategyPattern {

    /** todo 定义一个策略算法接口 */
    private static interface StrategyInterface {
        void strategyAlgorithm();
    }

    /** todo 策略算法接口的实现类 */
    private static class Strategy1 implements StrategyInterface {
        @Override
        public void strategyAlgorithm() {
            System.out.println("strategy algorithm one");
        }
    }
    private static class Strategy2 implements StrategyInterface {
        @Override
        public void strategyAlgorithm() {
            System.out.println("strategy algorithm two");
        }
    }

    /** todo 策略算法调用类 */
    private static class StrategyInvoker {
        StrategyInterface strategy ;

        public void setStrategy(StrategyInterface strategy) {
            this.strategy = strategy;
        }

        public void invokeStrategy(){
            this.strategy.strategyAlgorithm();
        }
    }


    public static void main(String[] args) {
        StrategyInvoker invoker = new StrategyInvoker();
        invoker.setStrategy(new Strategy1());
        invoker.invokeStrategy();
        invoker.setStrategy(new Strategy2());
        invoker.invokeStrategy();

    }

}
