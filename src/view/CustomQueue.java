package view;

import static org.junit.Assert.assertArrayEquals;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue<T> implements ICustomQueue<T>{
	private LinkedList<T> queue;
	private int max_capacity;
	
	public CustomQueue(int max_capacity){
		if (max_capacity <= 0) {
			throw new IllegalArgumentException("La capacità massima deve essere superiore a 1");
		}
		queue = new LinkedList<T>();
		assert max_capacity > 0;
		this.max_capacity = max_capacity;
	}
	
	@Override
	public boolean enqueue(T e) {
		queue.addLast(e);
		if (queue.size() == max_capacity + 1) {
			queue.removeFirst();
		}
		assert 0<=queue.size() && queue.size() <= max_capacity : "Violazione della capacità corrente: " + queue.size() + "; dimensione massima : " + max_capacity + ";";
		return queue.size() == max_capacity;
	}
	
	@Override
	public boolean isEmpty() {
		//assert queue.isEmpty() <==> queue.size() == 0;
		assert (!queue.isEmpty() || queue.size() == 0)
			&& (!(queue.size() == 0) || queue.isEmpty());
		return queue.isEmpty();
	}
	
	@Override
	public void reset() {
		queue.clear();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T[] getArray() {
		return (T[]) queue.toArray();
	}
	
	@Override
	public String toString() {
		String str = "";
		for (T t : queue) {
			str += t.toString() + "\n";
		}
		return str;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)	return false;
		if (!(o instanceof CustomQueue))	return false;
		@SuppressWarnings("unchecked")
		CustomQueue<T> cq = (CustomQueue<T>) o;
		return cq.queue.equals(this.queue) && cq.max_capacity == this.max_capacity;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public int maxSize() {
		return max_capacity;
	}

	@Override
	public boolean hasReachedMaxsize() {
		return queue.size() == max_capacity;
	}
}
