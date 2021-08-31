package model;

public class AnswerMC implements IMCAnswers {

	private /*@ spec_public @*/ static final String[] ANSWERS_TYPE = {"A", "B", "C", "D"};
	private String question;
	private String A, B, C, D;
	private String correctAnswer;
	private String caption;
 	
	//@ requires question != null && !question.isEmpty();
	//@ requires A != null && B != null && C != null && D != null;
	//@ requires !A.isEmpty() && !B.isEmpty() || !C.isEmpty() && !D.isEmpty();
	//@ requires correctAnswer != null && !correctAnswer.isEmpty();
	//@ requires caption != null && !caption.isEmpty();
	//@ requires (\exists int i; 0<= i < ANSWERS_TYPE.length; correctAnswer.equals(ANSWERS_TYPE[i]));
	
	public AnswerMC(String question, String A, String B, 
			String C, String D, String correctAnswer, String caption) {
		//checks
		if (question == null || A == null || B == null || C == null ||
				D == null || correctAnswer == null || caption == null) {
			throw new IllegalArgumentException("Alcune stringhe sono nulle");
		}
		if (question.isEmpty() || A.isEmpty() || B.isEmpty() || C.isEmpty()
				|| D.isEmpty() || correctAnswer.isEmpty() || caption.isEmpty()) {
			throw new IllegalArgumentException("Alcune stringhe sono vuote");
		}
		boolean notOk = true;
		for (String ANS : ANSWERS_TYPE) {
			notOk = notOk && !correctAnswer.equals(ANS);
		}
		if (notOk) {
			throw new IllegalArgumentException("Correct Answers NON è A, B, C, D");
		}
		
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
		if (answer == null) {
			throw new IllegalArgumentException("La stringa di input è nulla");
		}
		if (answer.isEmpty()) {
			throw new IllegalArgumentException("La stringa di input è vuota");
		}
		boolean notOk = true;
		for (String ANS : ANSWERS_TYPE) {
			notOk = notOk && !correctAnswer.equals(ANS);
		}
		if (notOk) {
			throw new IllegalArgumentException("La stringa di input non corrisponde ad A, B, C o D");
		}
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
