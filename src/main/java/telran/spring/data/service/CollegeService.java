package telran.spring.data.service;

import telran.spring.data.model.*;
import telran.spring.data.proj.*;

import java.util.*;

public interface CollegeService {
	void addStudent(Student student);
	void addSubject(Subject subject);
	void addMark(Mark mark);
	List<MarkProj> getMarksByNameSubject(String name, String subject);
	List<StudentSubjectMark> getMarksByName(String name);
	List<StudentAvgMark> getStudentsAvgMark(); 
	List<StudentName>getBestStudents();
	List<StudentName>getTopBestStudents(int nStudents); 
	List <StudentName>getTopBestStudentsSubject(int nStudents, String subject); 
	List<StudentSubjectMark>getMarksOfWorstStudents(int nStudents);
	List<IntervalMarksCount> marksDistibution(int interval);
	
}
