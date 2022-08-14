package onlineTest;

import java.util.Arrays;
import java.util.Objects;

public class Question {

	public static final String TRUE_OR_FALSE = "True or False Question";
	public static final String MULTIPLE_CHOICE = "Multiple Choice Question";
	public static final String FILL_IN_THE_BLANK = "Fill-in-the-blank Question";
	
	private String questionType;
	private int questionNumber;
	private String text;
	private double points;
	private boolean answer;
	private String[] answerChoices;
	
	public Question(String questionType, int questionNumber, String text, double points, String[] answer) {
		this.questionType = questionType;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		answerChoices = answer;
	}
	
	public Question(String questionType, int questionNumber, String text, double points, boolean answer) {
		this.questionType = questionType;
		this.questionNumber = questionNumber;
		this.text = text;
		this.points = points;
		this.answer = answer;
	}

	public String getQuestionType() {
		return questionType;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}

	public String getText() {
		return text;
	}

	public double getPoints() {
		return points;
	}
	
	public void getAnswer(int examId, Student currentStudent, boolean answer) {
		if (answer == this.answer) {
			currentStudent.addScore(examId, points);
		} else {
			currentStudent.addScore(examId, 0.0);
		}
	}
	
	public void getAnswer(int examId, Student currentStudent, String[] answer) {
		if (questionType == MULTIPLE_CHOICE) {
			if (answer.length != answerChoices.length) {
				currentStudent.addScore(examId, 0.0);
			} else {
				int answerCount = 0;
				
				for (int i = 0; i < answer.length; i++) {
					if (answer[i] == answerChoices[i]) {
						answerCount++;
					}
				}
				
				if (answerCount == answer.length) {
					currentStudent.addScore(examId, points);
				} else {
					currentStudent.addScore(examId, 0.0);
				}
			}
		} else {
			Double answerCount = 0.0;
			
			for (int i = 0; i < answer.length; i++) {
				for (int j = 0; j < answerChoices.length; j++) {
					if (answer[i] == answerChoices[j]) {
						answerCount += points / answerChoices.length;
					}
				}
			}
			
			currentStudent.addScore(examId, answerCount);
		}
	}
	
	public String getStringAnswer() {
		if (questionType == TRUE_OR_FALSE) {
			if (answer) {
				return "True";
			} else {
				return "False";
			}
		} else {
			Arrays.sort(answerChoices);
			return Arrays.toString(answerChoices);
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(questionNumber, questionType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		return questionNumber == other.questionNumber && Objects.equals(questionType, other.questionType);
	}
	
}
