package view;

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue<T>{
	private LinkedList<T> queue;
	private int max_capacity;
	private int current_capacity;
	
	public CustomQueue(int max_capacity){
		queue = new LinkedList<T>();
		this.max_capacity = max_capacity;
		this.current_capacity = 0;
	}
	
	public boolean enqueue(T e) {
		queue.addFirst(e);
		if (current_capacity == max_capacity) {
			queue.removeLast();
		}
		assert current_capacity == queue.size() : "current_capacity (" + current_capacity + ") non è uguale a size() della coda (" + queue.size() + ")" ;
		assert 0<=current_capacity && current_capacity <= max_capacity : "Violazione di current_capacity : " + current_capacity + "; dimensione massima : " + max_capacity + ";";
		return queue.size() == max_capacity;
	}
	
	public T dequeue() {
		return queue.removeLast();
	}
	
	public boolean isEmpty() {
		//assert queue.isEmpty() <==> current_capacity == 0;
		assert (!queue.isEmpty() || current_capacity == 0)
			&& (!(current_capacity == 0) || queue.isEmpty());
		return queue.isEmpty();
	}
	
	public void reset() {
		queue.clear();
	}
	
	@SuppressWarnings("unchecked")
	public T[] getArray() {
		return (T[]) queue.toArray();
	}
	
	public String toString() {
		String str = "";
		for (T t : queue) {
			str += t.toString() + "\n";
		}
		return str;
	}
}
