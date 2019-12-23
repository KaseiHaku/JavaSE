package kasei.javase.se.syntax.lambdaexpression;

public class LambdaExpressionDemo {

    /** java 8 lambda 表达式完整语法
        (Type1 param1, Type2 param2, ..., TypeN paramN) -> {
            statment1;
            statment2;
            //.............
            return statmentM;
        }
    */

    public static void main(String[] args) {
        lambda((String name, Integer age) ->{
            age++;
            name = name + "haku";
            System.out.println(name + age);
            return name;
        }, "kasei", 22);
    }


    public static void lambda(LambdaExpressionInterface a, String name, Integer age){
        a.print(name, age);
    }

}


