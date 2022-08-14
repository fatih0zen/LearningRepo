package onlineTest;

import java.util.ArrayList;

public class SystemManager implements Manager {
	
	ArrayList<Exam> exams = new ArrayList<>();
	ArrayList<Student> students = new ArrayList<>();

	@Override
	public boolean addExam(int examId, String title) {
		if (exams.contains(new Exam(examId, title))) {
			return false;
		} else {
			exams.add(new Exam(examId, title));
			return true;
		}
	}

	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		for (Exam exam: exams) {
			if (exam.getExamId() == examId) {
				exam.addTrueFalseQuestion(questionNumber, text, points, answer);
			}
		}
	}

	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		// TODO Auto-generated method stub
		for (Exam exam: exams) {
			if (exam.getExamId() == examId) {
				exam.addMultipleChoiceQuestion(questionNumber, text, points, answer);
			}
		}
		
	}

	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		// TODO Auto-generated method stub
		for (Exam exam: exams) {
			if (exam.getExamId() == examId) {
				exam.addFillInTheBlanksQuestion(questionNumber, text, points, answer);
			}
		}
	}

	@Override
	public String getKey(int examId) {
		// TODO Auto-generated method stub
		Exam currentExam = getCurrentExam(examId);
		String info = "";
		
		for (Question q: currentExam.getQuestions()) {
			info += "Question Text: " + q.getText() + "\n"
					+ "Points: " + q.getPoints() + "\n"
					+ "Correct Answer: " + q.getStringAnswer() + "\n";
		}
		
		return info;
	}

	@Override
	public boolean addStudent(String name) {
		
		Student newStudent = new Student(name);
		
		for (Student s : students) {
			if (s.equals(newStudent)) {
				return false;
			}
		}
		
		students.add(newStudent);
		
		return true;
	}

	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		// TODO Auto-generated method stub
		Student currentStudent = getCurrentStudent(studentName);
		Exam currentExam = getCurrentExam(examId);
		Question currentQuestion = getCurrentQuestion(questionNumber, currentExam);
		
		currentQuestion.getAnswer(examId, currentStudent, answer);
		
	}

	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		// TODO Auto-generated method stub
		Student currentStudent = getCurrentStudent(studentName);
		Exam currentExam = getCurrentExam(examId);
		Question currentQuestion = getCurrentQuestion(questionNumber, currentExam);
		
		currentQuestion.getAnswer(examId, currentStudent, answer);
	}

	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		// TODO Auto-generated method stub
		
		Student currentStudent = getCurrentStudent(studentName);
		Exam currentExam = getCurrentExam(examId);
		Question currentQuestion = getCurrentQuestion(questionNumber, currentExam);
		
		currentQuestion.getAnswer(examId, currentStudent, answer);
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		// TODO Auto-generated method stub
		Student currentStudent = getCurrentStudent(studentName);
//		Exam currentExam = getCurrentExam(examId);
		
		return currentStudent.getExamScore(examId);
	}

	@Override
	public String getGradingReport(String studentName, int examId) {
		// TODO Auto-generated method stub
		Student currentStudent = getCurrentStudent(studentName);
		Exam currentExam = getCurrentExam(examId);
		
		String report = "";
		
		int count = 0;
		for (Question q : currentExam.getQuestions()) {
			report += "Question #" + q.getQuestionNumber() + " " + currentStudent.getQuestionScore(examId, count) + " points out of " + q.getPoints() + "\n";
			count++;
		}
		
		report += "Final Score: " + currentStudent.getExamScore(examId) + " out of " + currentExam.getMaxScore();
		
		return report;
	}

	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		Course.setLetterGradesCutoffs(letterGrades, cutoffs);
	}

	@Override
	public double getCourseNumericGrade(String studentName) {
		// TODO Auto-generated method stub
		Student currentStudent = getCurrentStudent(studentName);
//		
//		return currentStudent.getCourseNumericGrade();
		
		Double totalGrade = 0.0;
		Double maxPoints = 0.0;
		
		for (int examId: currentStudent.getExamIds()) {
			Exam currentExam = getCurrentExam(examId);
			
			totalGrade += currentStudent.getExamScore(examId);
			maxPoints += currentExam.getMaxScore();
		}
		
		return (totalGrade / maxPoints) * 100;
	}

	@Override
	public String getCourseLetterGrade(String studentName) {
		// TODO Auto-generated method stub
		Student currentStudent = getCurrentStudent(studentName);
		
		return currentStudent.getCourseLetterGrade();
	}

	@Override
	public String getCourseGrades() {
		// TODO Auto-generated method stub
		return Course.getCourseGrades(students);
	}

	@Override
	public double getMaxScore(int examId) {
		// TODO Auto-generated method stub
		return Course.getMaxMinAverageScore(examId, students, 1);
	}

	@Override
	public double getMinScore(int examId) {
		// TODO Auto-generated method stub
		return Course.getMaxMinAverageScore(examId, students, -1);
	}

	@Override
	public double getAverageScore(int examId) {
		// TODO Auto-generated method stub
		return Course.getMaxMinAverageScore(examId, students, 0);
	}

	@Override
	public void saveManager(Manager manager, String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Manager restoreManager(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Exam getCurrentExam(int examId) {
		for (Exam exam: exams) {
			if (exam.getExamId() == examId) {
				return exam;
			}
		}
		
		return null;
	}
	
	private Question getCurrentQuestion(int questionNumber, Exam currentExam) {
		for (Question question: currentExam.getQuestions()) {
			if (question.getQuestionNumber() == questionNumber) {
				return question;
			}
		}
		
		return null;
	}
	
	private Student getCurrentStudent(String name) {
		for (Student student: students) {
			if (student.getName() == name) {
				return student;
			}
		}
		
		return null;
	}

}
