package kasei.javase.se.frequently.serialize;

import java.io.Serializable;
import java.math.BigDecimal;

public class MySerializable implements Serializable {

    private String name ;
    private Integer age;
    private String gender;
    private transient BigDecimal asset;  // transient 关键字表示在序列化的时候该字段不做序列化操作

    public BigDecimal getAsset() {
        return asset;
    }

    public void setAsset(BigDecimal asset) {
        this.asset = asset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
