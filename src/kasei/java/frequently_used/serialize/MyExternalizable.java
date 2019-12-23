package kasei.javase.se.frequently.serialize;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashMap;

/**
 * 实现 Externalizable 接口的序列化可以自己控制
 * */
public class MyExternalizable implements Externalizable {

    private int age;
    private String name;
    private String sex;
    private transient String fujia;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFujia() {
        return fujia;
    }

    public void setFujia(String fujia) {
        this.fujia = fujia;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("ll", "小明");
        out.writeObject(map);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        HashMap<String, String> map = (HashMap<String, String>) in.readObject();
        System.out.println(map.get("ll"));
        this.setFujia(map.get("ll").toString());
    }
}
