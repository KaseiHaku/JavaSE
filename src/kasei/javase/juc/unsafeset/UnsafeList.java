package kasei.javase.juc.unsafeset;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class UnsafeList {

    public static void main(String[] args) {
        // 有并发访问的问题
        // List<String> list = new ArrayList<>();

        // 解决方案 1：使用线程同步的类
        // List<String> list = new Vector<>();

        // 解决方案 2：使用  Collections.synchronizedList(）转换成线程同步的
        // List<String> list = Collections.synchronizedList(new ArrayList<>());

        // 解决方案 3：使用 juc 下面的 CopyOnWriteArrayList 类
        // CopyOnWrite 写入时复制
        // 读写分离
        // CopyOnWriteArrayList 相对于 Vector 好在哪里？  由于没有使用 synchronized 关键字，效率高
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    list.add(UUID.randomUUID().toString().substring(0,5)); // 会报 java.util.ConcurrentModificationException 并发修改异常
                }
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }
}
