package view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;
import controller.IController;
import model.IAnswers;

public class View extends JFrame implements IView, ActionListener{

	private static final String TITLE = "Quiz Editor";
	private static final int WIDTH_W = 400;
	private static final int HEIGHT_W = 400;
	private IController controller;
	private String currentFilePath;
	
	//elementi Swing
	JMenuBar bar;
	JMenu menu;
	JMenuItem search;
	//pannello dove c'è file, scelta fra insert e search
	JPanel insertOrSearchPanel;
	JRadioButton insertRB;
	JRadioButton searchRB;
	JFileChooser fileChooser;
	//pannello dei quiz mc e tf
	JPanel contentPanel;
	CardLayout cardlayout;
	JRadioButton mc;
	JRadioButton tf;
	//mc
	JTextArea question;
	JRadioButton A;
	JRadioButton B;
	JRadioButton C;
	JRadioButton D;
	JTextArea caption;
	JTextArea displayQuiz;
	JTextArea displayInfoErrors;
	JTextField searchText;
	
	//tf
	
	JButton insertButton;
	JButton searchButton;

	public View() {
		// TODO Aggiungere codice Swing
		setTitle(TITLE);
		//panel
		Container c = getContentPane();
		insertOrSearchPanel = new JPanel();
		contentPanel = new JPanel();
		//Button
		insertRB = new JRadioButton("Insert", true);
		searchRB = new JRadioButton("Search", false);
		mc = new JRadioButton("Multiple Choice", true);
		tf = new JRadioButton("True-False", false);
		insertButton = new JButton("Insert");
		searchButton = new JButton("Search");
		//add buttons
		insertOrSearchPanel.setLayout(new GridLayout(1, 2));
		insertOrSearchPanel.add(insertRB);
		insertOrSearchPanel.add(searchRB);
		
		contentPanel.setLayout(new GridLayout(3, 1));
		contentPanel.add(mc); 
		contentPanel.add(tf); 
		//2 layouts
		GridLayout grid = new GridLayout(2, 1);
		c.setLayout(grid);
		add(insertOrSearchPanel);
		add(contentPanel);
		//listener
		setSize(WIDTH_W, HEIGHT_W);
		setVisible(true);
	}

	@Override
	public String[] getSelectedAnswer() {
		//TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerListener(IController controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object objectSource = e.getSource();
		if (objectSource == insertButton) {
			controller.OnInsertButtonPressed();
		}else if (objectSource == searchButton) {
			controller.OnSearchButtonPressed();
		}else if(e.getSource()==search){    
		    JFileChooser fc=new JFileChooser();    
		    int i=fc.showOpenDialog(this);    
		    if(i==JFileChooser.APPROVE_OPTION){    
		        File f=fc.getSelectedFile();    
		        currentFilePath =f.getPath();
		        controller.OnChangeFilePath();
		    }    
		}   
	}

	@Override
	public String[] getFileNames() {
		return currentFilePath;
	}

	@Override
	public String getCategory() {
		return search.getText();
	}

	@Override
	public void displayGeneralInfoMessages(String message) {
		// TODO Add color
		displayInfoErrors.setText(message);
	}

	@Override
	public void displayErrorMessages(String message) {
		// TODO Add Color
		displayInfoErrors.setText(message);
	}

	@Override
	public void displayQuizMessages(String message) {
		// TODO Auto-generated method stub
		displayQuiz.setText(message);
	}

}
