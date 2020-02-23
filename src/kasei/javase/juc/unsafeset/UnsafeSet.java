package kasei.javase.juc.unsafeset;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UnsafeSet {

    public static void main(String[] args) {
        // 有并发访问的问题
        // Set<String> set = new HashSet<>();


        // 解决方案 1：使用  Collections.synchronizedList(）转换成线程同步的
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());

        // 解决方案 2：使用 juc 下面的 CopyOnWriteArraySet 类
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    set.add(UUID.randomUUID().toString().substring(0,5)); // 会报 java.util.ConcurrentModificationException 并发修改异常
                }
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

    }
}
