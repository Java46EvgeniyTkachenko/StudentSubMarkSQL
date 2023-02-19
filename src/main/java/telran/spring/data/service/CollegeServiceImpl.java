package telran.spring.data.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.spring.data.entities.*;
import telran.spring.data.model.*;
import telran.spring.data.proj.*;
import telran.spring.data.repo.*;
@Service
@Transactional(readOnly = true)
public class CollegeServiceImpl implements CollegeService {
	StudentRepository studentRepository;
	SubjectRepository subjectRepository;
	MarkRepository markRepository;
	@Override
	@Transactional
	public void addStudent(Student student) {
		if(studentRepository.existsById(student.id)) {
			throw new IllegalStateException(String.format("Student with id %d already exist",
					student.id));
		}
		studentRepository.save(new StudentEntity(student.id, student.name));
		
	}

	@Override
	@Transactional
	public void addSubject(Subject subject) {
		if(subjectRepository.existsById(subject.id)) {
			throw new IllegalStateException(String.format("Subject with id %d already exist",
					subject.id));
		}
		subjectRepository.save(new SubjectEntity(subject.id, subject.subject));
		
	}

	@Override
	@Transactional
	public void addMark(Mark mark) {
		StudentEntity student = studentRepository.findById(mark.stid).orElse(null);
		if (student == null) {
			throw new NoSuchElementException(String.format("student with id %d doesn't exist",
					mark.stid));
		}
		SubjectEntity subject = subjectRepository.findById(mark.suid).orElse(null);
		if(subject == null) {
			throw new NoSuchElementException(String.format("subject with id %d doesn't exist", 
					mark.suid));
		}
		MarkEntity markEntity = new MarkEntity(student, subject, mark.mark);
		markRepository.save(markEntity);
	}

	public CollegeServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository,
			MarkRepository markRepository) {
		this.studentRepository = studentRepository;
		this.subjectRepository = subjectRepository;
		this.markRepository = markRepository;
	}

	@Override
	public List<MarkProj> getMarksByNameSubject(String name, String subject) {
		
		return markRepository.findByStudentNameAndSubjectSubject(name, subject);
	}

	@Override
	public List<StudentSubjectMark> getMarksByName(String name) {
		
		return markRepository.findByStudentName(name);
	}

	@Override
	public List<StudentAvgMark> getStudentsAvgMark() {
		
		return markRepository.getStudentsAvgMark();
	}

	@Override
	public List<StudentName> getBestStudents() {
		
		return markRepository.getBestStudents();
	}

	@Override
	public List<StudentName> getTopBestStudents(int nStudents) {
		
		return markRepository.getTopBestStudents(nStudents);
	}

	@Override
	public List<StudentName> getTopBestStudentsSubject(int nStudents, String subject) {
		
		return markRepository.getTopBestStudentsSubject(nStudents, subject);
	}

	@Override
	public List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents) {
		
		return markRepository.getMarksOfWorstStudents(nStudents);
	}

	@Override
	public List<IntervalMarksCount> marksDistibution(int interval) {
		
		return markRepository.marksDistibution(interval);
	}

}
