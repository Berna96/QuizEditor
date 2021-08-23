package model;

public class AnswerTF implements ITFAnswers {

	private boolean correctAnswer;
	private String question, caption;
	
	public AnswerTF(String question, boolean correctAnswer, String caption) {
		this.caption = question;
		this.caption = caption;
		this.correctAnswer = correctAnswer;
	}
	
	@Override
	public boolean isCorrect(boolean answer) {
		return correctAnswer == answer;
	}

	@Override
	public boolean getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public String getCaption() {
		return caption;
	}

}
