package grozail.lab2;

import grozail.lab2.persons.Postgraduate;
import grozail.lab2.persons.Student;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by grozail
 * on 19.2.17.
 */
public class Course {
	private String name;
	private Set<Student> students;

	public Course(String name, Set<Student> students) {
		this.name = name;
		this.students = students;
	}

	Set<Postgraduate> getPostgraduates(String nameOfSupervisor) {
		Set<Postgraduate> postgraduates = new HashSet<>();
		for (Student student : students) {
			if(student instanceof Postgraduate)
				if(((Postgraduate) student).getSupervisor().getName().equals(nameOfSupervisor))
					postgraduates.add((Postgraduate)student);
		}
		return postgraduates;
	}
}
