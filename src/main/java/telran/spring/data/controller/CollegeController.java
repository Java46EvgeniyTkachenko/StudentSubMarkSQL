package telran.spring.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.spring.data.proj.IntervalMarksCount;
import telran.spring.data.proj.MarkProj;
import telran.spring.data.proj.StudentAvgMark;
import telran.spring.data.proj.StudentSubjectMark;
import telran.spring.data.service.CollegeService;

import java.util.*;

@RestController
@RequestMapping("college")
public class CollegeController {
	@Autowired
	CollegeService collegeService;
	@GetMapping("marks")
List<MarkProj> getMarksByNameSubject(@RequestParam (name = "subject")String subject,
		@RequestParam (name = "name")String name) {
	return collegeService.getMarksByNameSubject(name, subject);
}
	@GetMapping("marks/subjects")
	List<StudentSubjectMark> getMarksByName(
			@RequestParam (name = "name")String name) {
		return collegeService.getMarksByName(name);
	}
	
	@GetMapping("marks/avg")
	List<StudentAvgMark> getStudentsAvgMark() {
		return collegeService.getStudentsAvgMark();
	}
	@GetMapping("marks/worst")
	List<StudentSubjectMark>getMarksOfWorstStudents(@RequestParam (name ="nStudents")  int nStudents)  {
		return collegeService.getMarksOfWorstStudents(nStudents);
	}
	
	@GetMapping("marks/interval")
	List<IntervalMarksCount> marksDistibution(@RequestParam(name = "interval") int interval)  {
		return collegeService.marksDistibution(interval);
	}
	

}
