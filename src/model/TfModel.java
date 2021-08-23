package model;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TfModel implements IModel {

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
	public boolean removeWrongLines() {
		// TODO Auto-generated method stub
		return false;
	}

}
