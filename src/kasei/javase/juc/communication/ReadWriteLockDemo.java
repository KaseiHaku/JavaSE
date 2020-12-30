package kasei.javase.juc.communication;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReadWriteLockDemo {

    static class MyCache{
        private volatile Map<String, Object> map = new HashMap<>();


        /** ReadWriteLock 多个线程时，读锁 和 写锁 的共存性
         * 读 - 读： 可以共存
         * 读 - 写： 不能共存
         * 写 - 写： 不能共存
         * 读锁：是 共享锁
         * 写锁：是 排他锁，独占锁
         * 同一个线程
         *      先获取 读锁但不释放，再获取写锁，无法实现，即： 读锁 无法 升级为 写锁
         *      先获取 写锁但不释放，再获取读锁，可以实现，即： 写锁 可以 降级为 读锁
         * */
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void put(String key, Object value) {

            readWriteLock.writeLock().lock();
            try{

                System.out.println("写入 " + key);
                TimeUnit.SECONDS.sleep(1);
                map.put(key, value);
                System.out.println("写入 " + key + " ok ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }

        public Object get(String key) {
            readWriteLock.readLock().lock();
            Object o = null;
            try{
                System.out.println("读取" + key);
                TimeUnit.SECONDS.sleep(1);
                o = map.get(key);
                System.out.println("读取" + key + "完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readWriteLock.readLock().unlock();
            }

            return o;
        }

    }




    public static void main(String[] args) {

        MyCache cache = new MyCache();



        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(()->{
                cache.put(String.valueOf(temp), UUID.randomUUID().toString().substring(0, 4));
            }, String.valueOf(i)).start();
        }


        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(()->{
                cache.get(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }


    }


}
