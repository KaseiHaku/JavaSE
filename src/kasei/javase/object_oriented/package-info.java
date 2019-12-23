package kasei.javase.se.objectoriented;

/** Java 知识主要名词解释
 * JPA:Java Presistence API -- java 持久化 API -- 其中的事务实现靠数据库自身
 * JTA:Java Transaction API -- java 事务API -- 其中的事务实现靠应用服务，可实现分布式数据库事务
 * IOC:
 * AOP:
 * ORM:Object Relation Mapping -- 对象关系映射 --用于数据库表和Java程序类之间的对应
 * **/


/* Java ***************************************************************************
 *Object
 *  │
 *  ├─ abstract ─ 实现类1 ─ 实现类2
 *  ├─ interface ─ 实现类1 ─ 实现类2
 *  └─ FatherClass
 *         └─ ChildClass
 *
 *  对象和类的关系：对象是具体的类，层级关系：
 *  抽象类和实现类的关系：抽象类是所有实现类的统一调用接口；层级关系：同层
 *  接口和实现类的关系：接口是所有实现类的统一调用接口；层级关系：同层
 *  父类和子类的关系：子类对象可以造型为父类对象；层级关系：子类为父类对象的下一层
 *
 *  在 Java 中，你只能通过引用操作对象，除了基本类型以外；比如 Integer i = new Integer();i是引用， new出来的东西才是对象
 * */