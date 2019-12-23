package kasei.javase.designpattern.behavior.iterator;

//迭代器模式：
public class Test {

	public static void main(String[] args) {
		Repository r = new Repository();
		r.is[0] = 0;
		r.is[1] = 1;
		r.is[2] = 2;
		Iterator iterator = r.getIterator();
		for(; iterator.hasNext();){
			System.out.println(iterator.next());
		}
	}
}
