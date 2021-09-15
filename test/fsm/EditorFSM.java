package fsm;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.iterators.EntrySetMapIterator;
import org.apache.commons.collections15.keyvalue.MultiKey;
import org.apache.commons.collections15.map.MultiKeyMap;
import org.junit.Assert;

import edu.uci.ics.jung.graph.util.Pair;
import model.AnswerMC;
import model.IAnswers;
import model.McModel;
import model.TfModel;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.ModelTestCase;
import nz.ac.waikato.modeljunit.Tester;
import nz.ac.waikato.modeljunit.VerboseListener;
import nz.ac.waikato.modeljunit.coverage.CoverageMetric;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;

public class EditorFSM implements FsmModel{
	//Costanti
	private static final String FILEPATH_MC = "test_mc.txt";
	//private static final String FILEPATH_TF = "test_tf.txt";
	private static final int MAX_DIM = 10;
	
	//EFSM states
	enum Category {CATEGORY_A, CATEGORY_B};
	enum Quiz {QUESTION01, QUESTION02}
	enum State {INSERTED, PRINT_RESULT}
	State state;
	
//	private class Couple{
//		Category category;
//		Quiz quiz;
//		
//		public Couple(Category c, Quiz q) {
//			category = c;
//			quiz = q;
//		}
//	}
	
	@Action
	public void inserted() {
		state = State.INSERTED;
		assertTrue(false);
		System.out.println("THIS IS IT");
	}
	
	/*
	private Couple genNewCouple() {
		int pickCateg = new Random().nextInt(Category.values().length);
		int pickQuest = new Random().nextInt(Quiz.values().length);
		return new Couple(Category.values()[pickCateg], Quiz.values()[pickQuest]);
	}
	
	private Couple[] searchCouples(Category categ) {
		ArrayList<Couple> s = new ArrayList<EditorFSM.Couple>();
		for (Couple c : file) {
			if (c.category.equals(categ)) {
				s.add(c);
			}
		}
		return (Couple[]) s.toArray();
	}
	
	
	//STATE : state + file;
	State state; 
	ArrayList<Couple> file;
	
	
	public EditorFSM() {
		//state
		file = new ArrayList<EditorFSM.Couple>();
		state = State.INSERTED;
	}
	
	@Action
	public void insertMC() {
		state = State.INSERTED;
		Couple c = new Couple(Category.CATEGORY_A, Quiz.QUESTION01);
		file.add(c);
	}
	
	public boolean insertMCGuard() {
		return state.equals(State.INSERTED) && file.size() < MAX_DIM;
	}
	
	@Override
	public Object getState() {
		return state.toString() + ": " + file.toString();
	}

	@Override
	public void reset(boolean arg0) {
		state = State.INSERTED;
		file.clear();
	}
	*/
	
	public static void main(String[] args) {
		Tester tester = new GreedyTester(new EditorFSM());
		tester.buildGraph();
		CoverageMetric trCoverage = new TransitionCoverage();
		tester.addCoverageMetric(trCoverage);
		tester.addListener(new VerboseListener());
		tester.generate(50);
		tester.getModel().printMessage(trCoverage.getName() + " was "+ trCoverage.toString());
	}

	@Override
	public Object getState() {
		// TODO Auto-generated method stub
		return state.toString();
	}

	@Override
	public void reset(boolean arg0) {
		state = State.INSERTED;
		
	}

}
