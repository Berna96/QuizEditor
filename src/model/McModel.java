package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class McModel implements IModel{

	private String filename;
	private File file;
	private static final String[] MC_KEYS = {"category","question",
			"A", "B", "C", "D", "correct answer", "caption"};
	private static final String REGEX = "\",\"";
	
	/**
	 * 
	 * @param filename nome del file
	 * @throws IOException 
	 */
	public McModel(String filename) throws FileNotFoundException, IOException {
		this.filename = filename;
		if (filename == null) {
			throw new FileNotFoundException("Hai passato un valore nullo come nome del file");
		}
		file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException("Il file " + filename + "non esiste");
		}
		BufferedReader br = new BufferedReader(new FileReader(file));
		String first_line = br.readLine();
		String[] keys = first_line.split(REGEX);
		if (!keys.equals(MC_KEYS)) {
			throw new FileNotFoundException("Il file non ha il formato corretto");
		}
	}
	
	private boolean hasKeyWords(BufferedReader br) throws IOException {
		br.reset();
		String first_key = br.readLine();
		String[] keys = first_key.split(REGEX);
		return keys.equals(MC_KEYS);
	}
	
	@Override
	public boolean isWellFormed() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
	    //check key words
		if (!hasKeyWords(br)) {
			return false;
		}
	    //check new line
	    while ((line = br.readLine()) != null) {
	       String[] words = line.split(REGEX);
	       if (words.length != MC_KEYS.length) {
	    	   return false;
	       }
	    }
	    return true;
	}

	@Override
	public boolean insertAnswer(IAnswers a) {
		if (a instanceof AnswerMC) {
			return false;
		}
		AnswerMC answer = (AnswerMC) a;
		//scrivi nel file
		return false;
	}

	@Override
	public IAnswers[] readAnswers(String category_search) throws FileNotFoundException, IOException {
		if (category_search == null) {
			return null;
		}
		int num_answers = (int) (file.length() - 1);
		if (num_answers <= 0 || !file.exists()) {
			return null; 
		}
		ArrayList<AnswerMC> answer_list = new ArrayList<AnswerMC>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
	    //skip first line
	    br.readLine();
	    //search new line
	    while ((line = br.readLine()) != null) {
	       String[] words = line.split("\"");
	       String category = words[0];
	       if (category_search!=null && category_search.isEmpty() 
	    		   && category_search.equals(category)) {
	    	   //Questions
	    	   String question = words[1];
	    	   String A = words[2];
	    	   String B = words[3];
	    	   String C = words[4];
	    	   String D = words[5];
	    	   String correctAnswer = words[6];
	    	   String caption = words[7];
	    	   
	    	   AnswerMC a = new AnswerMC(question, A, B, C, D, correctAnswer, caption);
	    	   answer_list.add(a);
	       }
	    }

		if (answer_list==null || answer_list.isEmpty()) {
			return null;
		}else {
			return (IAnswers[]) answer_list.toArray();
		}
		
	}

	@Override
	public boolean removeWrongLines() {
		BufferedReader br = new BufferedReader(new FileReader(file))
		String line;
	    //skip first line
	    br.readLine();
	    //search new line
	    while ((line = br.readLine()) != null) {
	       String[] words = line.split("\"");
	       if (words.length != MC_KEYS.length) {
	    	   //Rimuovi
	       }
	    }
		return false;
	}

}
