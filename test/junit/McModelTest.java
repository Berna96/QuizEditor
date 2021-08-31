package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnit44Runner;

import view.CustomQueue;

@RunWith(MockitoJUnit44Runner.class)
public class McModelTest {
	
	@Mock
	private CustomQueue<String> cq;
	
	@Before
	public void init() {
	}
	
	@Test
	public void test01() {
		cq.enqueue("");
		Mockito.verify(cq).enqueue("");
		assertEquals(0, cq.size());
		
		Mockito.when(cq.size()).thenReturn(1000);
		assertEquals(1000, cq.size());
	}

}
