### List Array Exchange
>- list 转 array： 
>   - 错误写法：`String[] ary = (String [])list.toArray();`这样写编译没有什么问题，但是运行时会报 ClassCastException
>   - 正确写法：`String[] ary = list.toArray(new String[list.size()]);`
------------------
### List 调用 remove() 方法时，for(;;) 报 IndexOutOfBoundsException、for(:) 报 ConcurrentModificationException
> 使用 Iterator 遍历，他会自动维护索引
