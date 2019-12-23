package kasei.spring.di;

import java.util.HashSet;
import java.util.Set;

public class ObserverPattern {

    /** todo 可观察接口 */
    interface Observable {
        void addObserver(Observer observer);
        void removeObserver(Observer observer);
        void notifyObservers(String msg);
    }
    /** todo 观察者接口 */
    interface Observer {
        void report(String msg);
    }

    /** todo 可观察接口的实现类 */
    public static class ObservableImpl implements Observable {
        Set<Observer> observers = new HashSet<>();

        @Override
        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers(String msg) {
            observers.forEach(x -> x.report(msg));
        }

        public void execute(){
            this.notifyObservers("active");
        }
    }

    /** todo 观察者接口实现类 */
    public static class ObserverImpl implements Observer {
        @Override
        public void report(String msg) {
            System.out.println(msg);
        }
    }




    public static void main(String[] args) {
        ObservableImpl observable = new ObservableImpl();
        observable.addObserver(new ObserverImpl());
        observable.execute();

    }
}
