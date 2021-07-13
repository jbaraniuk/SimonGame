/**
 * @author Justin Baraniuk
 */
import java.util.Iterator;    
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
	
	private class ArrayIterator implements Iterator<E> {
		private int j = 0;
		private boolean removable = false;
		
		public boolean hasNext() { 
			return j < size; 
		}
		
		public E next() throws NoSuchElementException {
			if (j == size) 
				throw new NoSuchElementException("No next element");
			removable = true;
			return data[j++];
		}
		
		public void remove() throws IllegalStateException {
			if (!removable) throw new IllegalStateException("nothing to remove");
			ArrayList.this.remove(j-1);
			j--;
			removable = false;
		}
	}
			
	public static final int CAPACITY = 4;
	private E[] data;
	private int size;
	
	public ArrayList() { this(CAPACITY); }
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
	}
	
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
	public int size() { 
		return size; 
	}
	
	public boolean isEmpty() { 
		return size == 0; 
	}
	
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}
	
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		data[i] = e;
		return temp;
	}
	
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if (size == data.length) 
			resize(2 * data.length);
		for (int k = size - 1; k >= i; k--)
			data[k+1] = data[k];
		data[i] = e;
		size++;
	}
	
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = data[i];
		for (int k=i; k < size-1; k++)
			data[k] = data[k+1];
		data[size-1] = null; 
		size--;
		if (data.length < size / 2)
			resize(size / 2);
		return temp;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) 
			return false;
		if (getClass() != o.getClass()) 
			return false;	
		
		ArrayList<E> other = (ArrayList<E>) o;
		if (size != other.size) 
			return false;

		Iterator<E> iter = this.iterator();
		Iterator<E> iter2 = other.iterator();
		E walkA;
		E walkB;
		while (iter.hasNext()) {
			walkA = iter.next();
			walkB = iter2.next();
			if (!walkA.equals(walkB))
				return false;
		}
		return true;
	}
	
	protected void resize(int capacity) {
		E[] temp = (E[]) new Object[capacity];
		for (int k = 0; k < size; k++)
			temp[k] = data[k];
		data = temp;
	}
	
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException { 
		if (i < 0 || i >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + i);
	}

}
