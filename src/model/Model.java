package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Model implements IModel {

	@Override
	public void setFile(String filename) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWellFormed() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertAnswer(IAnswers a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IAnswers[] readAnswers(String category) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeWrongLines() throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
