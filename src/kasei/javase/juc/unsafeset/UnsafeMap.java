package kasei.javase.juc.unsafeset;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class UnsafeMap {

    public static void main(String[] args) {
        // 有并发访问的问题
        // map 是这样用的吗？ 不是，工作中不用 HashMap
        // 默认等价于 new HashMap<>(16, 0.75);
        // Map<String, String> map = new HashMap<>();



        // 解决方案 1：使用  Collections.synchronizedList(）转换成线程同步的
        // Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        // 解决方案 2：使用 juc 下面的 CopyOnWriteArraySet 类
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                    map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5)); // 会报 java.util.ConcurrentModificationException 并发修改异常

                System.out.println(map);
            }, String.valueOf(i)).start();
        }

    }
}
