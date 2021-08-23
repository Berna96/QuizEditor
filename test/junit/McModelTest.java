package junit;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;
import model.McModel;

public class McModelTest {
	
	private McModel model;
	private String filename;
	
	public McModelTest() {
		filename = "C:";
		model = new McModel(filename);
	}

	//get exception
	@Test
	public void test() {
		McModel wrongmodel = new McModel("");
	}
	
	@Test
	public void test01() {
		assertEquals(0, 0);
	}

}
