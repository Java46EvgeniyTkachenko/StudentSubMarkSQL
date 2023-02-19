package telran.spring.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.spring.data.entities.MarkEntity;
import telran.spring.data.proj.*;

public interface MarkRepository extends JpaRepository<MarkEntity, Long>{

	List<MarkProj> findByStudentNameAndSubjectSubject(String name, String subject);
	
	@Query(value = "select name, subject, mark"
			+ " from students  join marks on students.stid=marks.stid join "
			+ "subjects  on subjects.suid=marks.suid "
			+ "where name=:name",nativeQuery = true)
	List<StudentSubjectMark> findByStudentName(String name);
	
	@Query(value="select name from students join marks"
			+ " on stid=students.stid group by name having avg(mark) >"
			+ " (select avg(mark) from marks) order by avg(mark) desc", nativeQuery = true)
	List<StudentName> getBestStudents();
	
	@Query(value="select name from students join marks"
			+ " on stid=students.stid group by name "
			+ "  order by avg(mark) desc limit :nStudents", nativeQuery = true)
	List<StudentName> getTopBestStudents(int nStudents);
	
	@Query(value="select name from public.st_sb_marks where subject=:subject "
			+ "group by name having avg(mark) > (select avg(mark) from public.st_sb_marks "
			+ "where subject=:subject) order by avg(mark) desc limit :nStudents", nativeQuery = true)
	List<StudentName> getTopBestStudentsSubject(int nStudents, String subject);

	@Query(value="select name, mark from students join marks"
			+ " on marks.stid=students.stid group by name, mark "
			+ "  order by avg(mark) limit :nStudents", nativeQuery = true)
	List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents);
	
	@Query(value="select name, avg(mark) from students join marks"
			+ " on marks.stid=students.stid group by name "
			+ "  order by name", nativeQuery = true)
	List<StudentAvgMark> getStudentsAvgMark();
	
	@Query(value="select (mark/ :interval) * :interval as min, "
			+ "(mark/ :interval)* :interval + :interval-1 as max, count(*) as marksCount "
			+ "from marks "
			+ "group by min,max "
			+ "order by 1"
			,nativeQuery = true)
	List<IntervalMarksCount> marksDistibution(int interval);
	
	

}
