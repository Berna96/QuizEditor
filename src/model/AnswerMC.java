package model;

public class AnswerMC implements IMCAnswers {

	private String question;
	private String A, B, C, D;
	private String correctAnswer;
	private String caption;
 	
	public AnswerMC(String question, String A, String B, 
			String C, String D, String correctAnswer, String caption) {
		this.question = question;
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.correctAnswer = correctAnswer;
		this.caption = caption;
	}
	
	
	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	@Override
	public String[] getMCAnswers() {
		String[] answers = {A, B, C, D};
		return answers;
	}

	@Override
	public boolean isCorrect(String answer) {
		return correctAnswer.equals(answer);
	}

	@Override
	public String getA() {
		return A;
	}

	@Override
	public String getB() {
		return B;
	}

	@Override
	public String getC() {
		return C;
	}

	@Override
	public String getD() {
		return D;
	}
	
}
