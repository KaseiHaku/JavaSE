package kasei.javase.se.syntax.function;

public class VarArgsFunction {


    //变长参数格式：有且只有一个，并且必须放最后一个
    public static int getMax(int first, int... varArgs) {
        int max = first;

        for(int i : varArgs) {
            max = i > max ? i : max;
        }
        return max;
    }
}
