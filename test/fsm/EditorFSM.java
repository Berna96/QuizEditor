package fsm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.xml.ws.Action;

import org.junit.Assert;

import model.AnswerMC;
import model.IAnswers;
import model.McModel;
import model.TfModel;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.ModelTestCase;

public class EditorFSM implements FsmModel{

	//EFSM states
	enum State {WAIT, INCORRECT, INSERT_MC, INSERT_TF, SEARCH};
	enum Category {NONE, CATEGORY_A, CATEGORY_B, CATEGORY_C};
	Category category;
	State state;
	//sut
	McModel model_mc;
	TfModel model_tf;
	
	public EditorFSM() {
		//state
		state = State.WAIT;
		category = Category.NONE;
		//sut
		try {
			model_mc = new McModel("");
			model_tf = new TfModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Action
	public void insertMCQuizCategA() {
		state = State.INSERT_MC;
		category = Category.CATEGORY_A;
		IAnswers mc = new AnswerMC("question", "A", "B", "C", "D", "A", "caption");
		try {
			boolean hasInsert = model_mc.insertAnswer(mc);
			assertTrue(hasInsert);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertMCQuizCategAGuard() {
		return state == State.WAIT;
	}
	
	@Action
	public void insertMcQuizCategB() {
		state = State.INSERT_MC;
		category = Category.CATEGORY_B;
		IAnswers mc = new AnswerMC("question", "A", "B", "C", "D", "A", "caption");
		try {
			boolean hasInsert = model_mc.insertAnswer(mc);
			assertTrue(hasInsert);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertMCQuizCategBGuard() {
		return state == State.WAIT;
	}
	
	@Action
	public void insertWrong() {
		//state
		state = State.INCORRECT;
		category = Category.NONE;
		//sut
		IAnswers mc = new AnswerMC("quest\",\"ion", "A", "B", "C", "D", "A", "caption");
		try {
			boolean hasInsert = model_mc.insertAnswer(mc);
			assertFalse(hasInsert);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertWrongGuard() {
		return state == State.WAIT;
	}
	
	
	@Override
	public Object getState() {
		return state.toString();
	}

	@Override
	public void reset(boolean arg0) {
		state = State.WAIT;
	}

}
