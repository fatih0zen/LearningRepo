package onlineTest;

import java.util.ArrayList;
import java.util.Objects;

public class Exam {
	
	private int examId;
	private String title;
	
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	public Exam(int examId, String title) {
		this.examId = examId;
		this.title = title;
	}
	
	public int getExamId() {
		return examId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Double getMaxScore() {
		Double maxScore = 0.0;
		
		for (Question q : questions) {
			maxScore += q.getPoints();
		}
		
		return maxScore;
	}
	
	public ArrayList<Question> getQuestions() {
		ArrayList<Question> copyQuestions = new ArrayList<Question>();
		
		for (Question q : questions) {
			copyQuestions.add(q);
		}
		
		return copyQuestions;
	}

	public void addTrueFalseQuestion(int questionNumber, String text, double points, boolean answer) {
		Question newQuestion = new Question(Question.TRUE_OR_FALSE, questionNumber, text, points, answer);
		
		if (!doesQuestionExist(newQuestion)) {
			questions.add(newQuestion);
		}
	}
	
	public void addMultipleChoiceQuestion(int questionNumber, String text, double points, String[] answer) {
		Question newQuestion = new Question(Question.MULTIPLE_CHOICE ,questionNumber, text, points, answer);
		
		if (!doesQuestionExist(newQuestion)) {
			questions.add(newQuestion);
		}
	}
	
	public void addFillInTheBlanksQuestion(int questionNumber, String text, double points, String[] answer) {
		Question newQuestion = new Question(Question.FILL_IN_THE_BLANK, questionNumber, text, points, answer);
		
		if (!doesQuestionExist(newQuestion)) {
			questions.add(newQuestion);
		}
	}
	
	private boolean doesQuestionExist(Question question) {
		boolean doesExist = false;
		
		for (Question q: questions) {
			if (q.equals(question)) {
				q = question;
				doesExist = true;;
			}
		}
		
		return doesExist;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(examId, questions, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exam other = (Exam) obj;
		return examId == other.examId && Objects.equals(questions, other.questions)
				&& Objects.equals(title, other.title);
	}
}
