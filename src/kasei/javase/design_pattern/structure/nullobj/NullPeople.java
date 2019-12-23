package kasei.javase.designpattern.structure.nullobj;

public class NullPeople implements People{

    @Override
    public String getName() {
        return "This People Not Exist";
    }
}
