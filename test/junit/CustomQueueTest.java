package junit;

import org.junit.Test;

import view.CustomQueue;

public class CustomQueueTest {
	private CustomQueue<String> q;
	private int max_capacity = 10;
	
	public CustomQueueTest() {
		q = new CustomQueue<String>(max_capacity);
	}
	
	@Test
	public void CapacityTest() {
		new CustomQueue<String>(-1);
	}
}
