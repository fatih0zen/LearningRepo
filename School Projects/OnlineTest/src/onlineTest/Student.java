package onlineTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class Student implements Comparable<Student> {
	
	private String name;
	
	private HashMap<Integer, ArrayList<Double>> examScores = new HashMap<>();
	
	public Student(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addScore(int examId, Double score) {
		ArrayList<Double> scores = examScores.get(examId);
		
		if (scores == null) {
			scores = new ArrayList<>();
			examScores.put(examId, scores);
		}
		
		scores.add(score);
	}
	
	public ArrayList<Double> getScores(int examId) {
		return examScores.get(examId);
	}

	public Double getExamScore(int examId) {
		Double totalScore = 0.0;
		
		for (Double score: examScores.get(examId)) {
			totalScore += score;
		}
		
		return totalScore;
	}
	
	public Double getQuestionScore(int examId, int index) {
		return examScores.get(examId).get(index);
	}
	
	public Double getCourseNumericGrade() {
		return Course.getCourseNumericGrade(this, examScores.keySet());
	}
	
	public String getCourseLetterGrade() {
		return Course.getCourseLetterGrade(this, examScores.keySet());
	}
	
	public Set<Integer> getExamIds() {
		return examScores.keySet();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		
		return this.name.compareTo(o.name);
	}
	
}
