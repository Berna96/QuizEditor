package junit.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnit44Runner;

import controller.Controller;
import controller.IController;
import view.View;

@RunWith(MockitoJUnit44Runner.class)
public class ViewTest {
	
	@Mock
	IController c;
	
	@InjectMocks
	View v;
	
	public ViewTest() {
		v = new View();
		v.registerListener(c);
	}
	
	@Test
	public void test() {
	}
}
