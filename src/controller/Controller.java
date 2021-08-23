package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import view.IView;
import view.View;
import model.AnswerMC;
import model.AnswerTF;
import model.IAnswers;
import model.IMCAnswers;
import model.McModel;
import model.TfModel;

public class Controller implements IController{

	private McModel model_mc;
	private TfModel model_tf;
	private IView view;

	private static final String ERROR_MESSAGE = "Problemi nella lettura/scrittura del file\n";
	
	public Controller(McModel model_mc, TfModel model_tf, IView view) throws Exception {
		if (model_mc == null || model_tf == null || view == null) {
			//sostituire l'eccezione con una custom
			throw new Exception("Non hai inizializzato o i modelli o la view");
		}
		assert model_mc != null;
		assert model_tf != null;
		assert view != null;
		
		this.model_mc = model_mc;
		this.model_tf = model_tf;
		this.view = view;
	}
	
	private void checkCorrectFormat() throws IOException {
		//check mc model
		if (!model_mc.isWellFormed()) {
			model_mc.removeWrongLines();
			view.displayGeneralInfoMessages("Attenzione: file non segue il formato corretto : le linee scorrette verranno rimosse\n");
		}
		//check tf model
		if (!model_tf.isWellFormed()) {
			model_mc.removeWrongLines();
			view.displayGeneralInfoMessages("Attenzione: file non segue il formato corretto : le linee scorrette verranno rimosse\n");
		}
	}

	@Override
	public void OnInsertButtonPressed() {
		String[] answers = view.getSelectedAnswer();
		try {
			checkCorrectFormat();
			//inseriamo le domande
			if (answers.length == 7) {
				AnswerMC a = new AnswerMC(null, 
						null, null, null, null, 
						null, null);
				model_mc.insertAnswer(a);
			}else if (answers.length == 3) {
				AnswerTF a = new AnswerTF(answers[0], true, answers[2]);
				model_tf.insertAnswer(a);
			}else {
				//non deve arrivare qua
				assert false;
			}
			
		}catch (Exception e) {
			view.displayInfoMessagesForInsert("Problemi nella lettura/scrittura del file\n");
		}
	}

	@Override
	public void OnSearchButtonPressed() {
		String category = view.getCategory();
		assert category != null;
		try {
			checkCorrectFormat();
			IAnswers[] ansMC = model_mc.readAnswers(category);
			IAnswers[] ansTF = model_tf.readAnswers(category);
			//concat array
			List<IAnswers> resultList = new ArrayList<>(ansMC.length + ansTF.length);
		    Collections.addAll(resultList, ansMC);
		    Collections.addAll(resultList, ansTF);
		    @SuppressWarnings("unchecked")
		    //the type cast is safe as the array1 has the type T[]
		    IAnswers[] resultArray = (IAnswers[]) Array.newInstance(ansMC.getClass().getComponentType(), 0);
		    IAnswers[] ans = resultList.toArray(resultArray);
			//create message
		    String message = "";
		    for (IAnswers a : ans) {
				message += a.toString() + "\n";
			}
		    view.displayQuizMessages(message);
		    
		}catch (FileNotFoundException e) {
			view.displayErrorMessages(ERROR_MESSAGE);
		}catch (IOException e) {
			view.displayErrorMessages(ERROR_MESSAGE);
		}
		
	}

	@Override
	public void OnChangeFilePath() {
		String[] filenames = view.getFileNames();
		assert filenames != null && filenames.length == 2 : "Attenzione: la stringa";
		try {
			model_mc.setFile(filenames[0]);
			model_tf.setFile(filenames[1]);
			view.displayGeneralInfoMessages("è stato cambiato il file con successo ora il file è" + filename);
		} catch (FileNotFoundException e) {
			view.displayErrorMessages("Stoca");
		}
	}

}
