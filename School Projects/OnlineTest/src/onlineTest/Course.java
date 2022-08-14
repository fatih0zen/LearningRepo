package onlineTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Course {
	
	private static String[] letterGrades;
	private static double[] cutoffs;
		
	public static void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		Course.letterGrades = letterGrades;
		Course.cutoffs = cutoffs;
	}
	
	public static Double getCourseNumericGrade(Student student, Set<Integer> examIds) {
		Double totalGrade = 0.0;
		Double maxPoints = 0.0;
		
		for (int examId: examIds) {
			totalGrade += student.getExamScore(examId);
		}
		
		return totalGrade / examIds.size();
	}
	
	public static String getCourseLetterGrade(Student student, Set<Integer> examIds) {
		String letterGrade = "";
		Double numericGrade = getCourseNumericGrade(student, examIds);
		
		for (int i = 0; i < cutoffs.length; i++) {
			if (numericGrade >= cutoffs[i]) {
				letterGrade = letterGrades[i];
				break;
			}
		}
		
		return letterGrade;
	}
	
	public static String getCourseGrades(ArrayList<Student> students) {
		String courseGrades = "";
		
		Collections.sort(students);
		for (Student s : students) {
			courseGrades += s.getName() + " " + s.getCourseNumericGrade() + " " + s.getCourseLetterGrade() + "\n";
		}
		
		return courseGrades;
	}
	
	public static double getMaxMinAverageScore(int examId, ArrayList<Student> students, int maxMinAvg) {
		// TODO Auto-generated method stub
		ArrayList<Double> scores = new ArrayList<Double>();
		Double totalScore = 0.0;
		
		for (Student s : students) {
			scores.add(s.getExamScore(examId));
			totalScore += s.getExamScore(examId);
		}
		
		Double max = Collections.max(scores);
		Double min = Collections.min(scores);
		
		if (maxMinAvg == 1) {
			return max;
		} else if (maxMinAvg == -1) {
			return min;
		} else {
			return totalScore / students.size();
		}
		
	}
	
}
