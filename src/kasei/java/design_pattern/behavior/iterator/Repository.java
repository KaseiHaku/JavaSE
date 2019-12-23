package kasei.javase.designpattern.behavior.iterator;

public class Repository implements Container {
	
	public int[] is = new int[10];

    @Override
    public Iterator getIterator() {

        return new MyIterator();
    }

	private class MyIterator implements Iterator {

		int index = 0;
		
		@Override
		public boolean hasNext() {
			if(index < is.length){
				return true;
			}else{
				return false;
			}		
		}

		@Override
		public Object next() {
			if(this.hasNext() == true){
				return is[index++];
			}
			return null;
		}
		
	}


}
