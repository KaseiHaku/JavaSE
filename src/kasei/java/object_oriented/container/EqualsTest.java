package kasei.javase.se.objectoriented.container;


public class EqualsTest extends Object{

    private String name;
    private Integer age;


    @Override // 必须和 equals() 同时重写
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override // 必须和 hashCode() 同时重写
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        EqualsTest other = (EqualsTest) obj;
        if(!this.name.equals(other.name)) return false;
        if(!this.age.equals(other.age)) return false;

        return true;
    }

}
