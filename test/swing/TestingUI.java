package swing;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Queue;

import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

import view.CustomQueue;

public class TestingUI extends JFrame implements ActionListener{

	private static final int WIDTH_W = 700;
	private static final int HEIGHT_W = 1000;
	private static final int MAX_LINE_IN_MEMORY = 8;
	private CustomQueue<String> queue;
	
	//menu
	JMenuBar bar;
	JMenu menu;
	JMenuItem search;
	//radiobutton
	JRadioButton insertRB;
	JRadioButton searchRB;
	JRadioButton mc;
	JRadioButton tf;
	
	
	JPanel insertOrSearchPanel;
	JPanel contentPanel;
	JPanel errorPanel;
	JPanel menuPanel;
	
	JButton b1, b2;
	
	JTextArea question;
	JTextArea displayError;
	
	CardLayout cardLayout;
	
	public TestingUI() {
		queue = new CustomQueue<String>(MAX_LINE_IN_MEMORY);
		setTitle("Testing Swing");
		
		//panel
		Container c = getContentPane();
		
		insertOrSearchPanel = new JPanel();
		contentPanel = new JPanel();
		errorPanel = new JPanel();
		menuPanel = new JPanel();
		
		GridLayout gridLayout = new GridLayout(4, 1);
		c.setLayout(gridLayout);
		c.add(menuPanel);
		c.add(insertOrSearchPanel);
		c.add(contentPanel);
		c.add(errorPanel);
		
		//menu
		bar = new JMenuBar();
		menu = new JMenu("File");
		search = new JMenuItem("Search file");
		search.addActionListener(this);
		menu.add(search);
		bar.add(menu);
		menuPanel.add(bar);
		//insert
		insertRB = new JRadioButton("Insert", true);
		searchRB = new JRadioButton("Search", false);
		
		GridLayout gridLayout2 = new GridLayout(1, 2);
		insertOrSearchPanel.setLayout(gridLayout2);
		insertOrSearchPanel.add(insertRB);
		insertOrSearchPanel.add(searchRB);
		
		//card layout and content panel
		cardLayout = new CardLayout();
		b1 = new JButton("Button 1");
		b2 = new JButton("Button 2");
		b1.addActionListener(this);
		b2.addActionListener(this);
		contentPanel.setLayout(cardLayout);
		contentPanel.add(b1);
		contentPanel.add(b2);
		
		//display error panel
		displayError = new JTextArea("Non editabile");
		displayError.setEditable(false);
		errorPanel.add(displayError);
		/*
		//Button
		mc = new JRadioButton("Multiple Choice", true);
		tf = new JRadioButton("True-False", false);
		
		//add buttons
		insertOrSearchPanel.setLayout(new GridLayout(3, 1));
		insertOrSearchPanel.add(bar);
		
		
		contentPanel.setLayout(new GridLayout(3, 1));
		contentPanel.add(mc); 
		contentPanel.add(tf); 
		contentPanel.add(displayError);
		//2 layouts
		GridLayout grid = new GridLayout(2, 1);
		c.setLayout(grid);
		add(insertOrSearchPanel);
		add(contentPanel);
		
		*/
		//add listeners
		insertRB.addActionListener(this);
		searchRB.addActionListener(this);
		//mc.addActionListener(this);
		//tf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH_W, HEIGHT_W);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new TestingUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == insertRB) {
			if (searchRB.isSelected()) {
				queue.enqueue("Premuto insert");
				displayError.setText(queue.toString());
				cardLayout.next(contentPanel);	
			}
			insertRB.setSelected(true);
			searchRB.setSelected(false);
			
		}else if (obj == searchRB) {
			if (insertRB.isSelected()) {
				queue.enqueue("Premuto search");
				displayError.setText(queue.toString());
				cardLayout.next(contentPanel);
			}
			searchRB.setSelected(true);
			insertRB.setSelected(false);
			
		}
		
		if (obj == mc) {
			tf.setSelected(false);
			assert mc.isSelected();
		}else if (obj == tf) {
			mc.setEnabled(false);
			assert tf.isSelected();
		}
		
		/*
		if (obj == b1) {
			cardLayout.next(contentPanel);
		}else if (obj == b2) {
			cardLayout.next(contentPanel);
		}
		*/
		
	}
	
}
