package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IModel {
	
	/**
	 * 
	 * @param filename percorso del file
	 */
	public void setFile(String filename) throws FileNotFoundException;
	/**
	 * Controlla che il linguaggio del file sia ben formato (vedere ogni singolo formato)
	 * @return true se ben formato, false altrimenti
	 * @throws FileNotFoundException Eccezione lanciata se non trova il file
	 * @throws IOException Eccezione lanciata se non trova il file
	 */
	public boolean isWellFormed() throws FileNotFoundException, IOException;
	/**
	 * Inserisci il quiz nel file
	 * @param a quiz da inserire
	 * @return true se inserisci, false altrimenti
	 */
	public boolean insertAnswer(IAnswers a);
	/**
	 * 
	 * @param category Stringa di ricerca della categoria (se vuota ricerca tutto, altrimenti solo la categoria specificata)
	 * @return Ritorna la lista di quiz IAnswers trovate
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public IAnswers[] readAnswers(String category) throws FileNotFoundException, IOException;
	/**
	 * Rimuove le righe malformate 
	 * @return true se rimosse, false altrimenti
	 */
	public boolean removeWrongLines();
}
