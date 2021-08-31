package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class McModel implements IModel{

	private String filename;
	private File file;
	private static final String FIRST_KEY = "\"MC\"";
	private static final String[] MC_KEYS = {"category","question",
			"A", "B", "C", "D", "correct answer", "caption"};
	private static final String REGEX = "\",\"";
	private BufferedReader br;
	private BufferedWriter bw;
	
	/**
	 * 
	 * @param filename nome del file
	 * @throws IOException 
	 */
	public McModel(String filename) throws FileNotFoundException, IOException {
		this.filename = filename;
		if (filename == null) {
			throw new IllegalArgumentException("Hai passato un valore nullo come nome del file");
		}
		file = new File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException("Il file " + filename + "non esiste");
		}
		br = new BufferedReader(new FileReader(file));
		//write
		FileOutputStream fos = new FileOutputStream(file);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		//check first line
		boolean hasKey = hasKeyWords();
		if (hasKey) {
			throw new FileNotFoundException("Il file non ha il formato corretto");
		}
	}
	
	private boolean hasKeyWords() throws IOException {
		br.reset();
		String first_line = br.readLine();
		String second_line = br.readLine();
		String[] keys = second_line.split(REGEX);
		return first_line.equals(FIRST_KEY) && keys.equals(MC_KEYS);
	}
	
	@Override
	public boolean isWellFormed() throws IOException {
		String line;
	    //check key words
		if (!hasKeyWords()) {
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
	public boolean insertAnswer(IAnswers a) throws IOException {
		if (a instanceof AnswerMC) {
			return false;
		}
		AnswerMC ans = (AnswerMC) a;
		String ansStr = "\"TODO/CATEGORY\"" + "\",\"" + ans.getQuestion() + "\",\"" + ans.getA() +
				"\",\"" + ans.getB() + ans.getC() + ans.getD() + ans.getCorrectAnswer()
				+ ans.getCaption();
		bw.write(ansStr);
		bw.newLine();
		return true;
	}

	@Override
	public IAnswers[] readAnswers(String category_search) throws FileNotFoundException, IOException {
		if (category_search == null) {
			throw new IllegalArgumentException("la stringa di ricerca è null");
		}
		int num_answers = (int) (file.length() - 1);
		if (num_answers <= 0 || !file.exists()) {
			return null; 
		}
		ArrayList<AnswerMC> answer_list = new ArrayList<AnswerMC>();
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
	public boolean removeWrongLines() throws IOException {
		br.reset();
		String line;
	    //skip first and second line
	    br.readLine();
	    br.readLine();
	    //search new line
	    while ((line = br.readLine()) != null) {
	       String[] words = line.split(REGEX);
	       if (words.length != MC_KEYS.length) {
	    	   //Rimuovi
	    	   //leggi tutte le righe
	    	   bw.write(line);
	    	   bw.newLine();
	       }
	    }
		return false;
	}

	@Override
	public void setFile(String filename) throws FileNotFoundException, IOException {
		if (filename == null) {
			throw new IllegalArgumentException("La stringa del file è null");
		}
		file = new File(filename);
		br.close();
		br = new BufferedReader(new FileReader(file));
		//write
		FileOutputStream fos = new FileOutputStream(file);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
	}
	
	public void closeFile() throws IOException {
		br.close();
		bw.close();
	}

}
