package kasei.javase.se.objectoriented.container;

/* Java 容器层次结构:********************************************************
 * Collection, Iterator
 *  │
 *  ├─ Queue
 *  |   ├─ ArrayQueue
 *      └─ ConcurrentLinkedQueue
 *  ├─ List (存放的数据，有顺序，可重复)
 *  │	├─ Vector (线程同步)
 *  │   ├─ ArrayList (线程不同步)
 *  │   ├─ LinkedList
 *  │   └─ Stack
 *  ├─ Set (存放的数据，没有顺序，不可重复)
 *  │   ├─ HashSet
 *  │   ├─ LinkedHashSet
 *  │   └─ TreeSet
 *  └─ Map
 *      ├─ HashMap (线程不安全，数据顺序不固定)
 *      ├─ LinkedHashMap
 *      ├─ TreeMap (线程不安全，数据顺序固定)
 *      └─ HashTable (线程安全)
 * */


/* Java 容器中判断两个对象是否相等
 * obj1 == obj2; 判断两个对象的地址是否相等
 * obj1.equals(obj2); 在未重写的情况下与 == 运算符一样，也是判断地址
 * */

/** Set<Object> 判断两个对象是否重复
 * 1. 调用 Object.hashCode() 方法，判断要加入的对象是否跟 Set 中的对象 hashCode 值相同，如果都不相同则直接加入
 * 2. 如果 Object.hashCode() 返回值相同，再判断 Object.equals() 是否为 true，都为 false 则把对象添加进 Set 中
 * */


/* Java 哈希容器存入：HashSet HashMap HashTable
 * HashSet 在添加元素的时候做如下判断：
 * 1. 调用 Object.hashCode() 方法，判断要加入的对象是否跟 Set 中的对象 hashCode 值相同，如果都不相同则直接加入
 * 2. 如果 Object.hashCode() 返回值相同，再判断 Object.equals() 是否为 true，都为 false 则把对象添加进 Set 中
 * */
