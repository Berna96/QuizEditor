package view;

import controller.IController;

public interface IView {
	public String[] getSelectedAnswer();
	public String[] getFileNames();
	public String getCategory();
	public void displayGeneralInfoMessages(String message);
	public void displayErrorMessages(String message);
	public void displayQuizMessages(String message);
	public void registerListener(IController controller);
}
