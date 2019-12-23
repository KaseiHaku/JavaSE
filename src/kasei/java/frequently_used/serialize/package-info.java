package kasei.javase.se.frequently.serialize;

/*
1、什么是序列化？
java序列化是指把Java对象转换为字节序列的过程，

2、为什么需要序列化？
把Java对象转换为字节序列（序列化）后，对象可以在网络上传送，实现两个java进程间对象的传送。
且序列化后可以把数据永久的保存在硬盘里，实现数据的持久化

3、实现序列化的要求：
只有实现了Serializable或Externalizable接口的类的对象才能被序列化，否则抛出异常。

4、ObjectOutputStream 用于对象的序列化
如果写入对象是对象的类型是String，或数组，或Enum，或Serializable，那么就可以对该对象进行序列化，否则将抛出NotSerializableException
*/