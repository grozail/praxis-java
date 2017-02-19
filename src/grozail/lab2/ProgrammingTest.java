package grozail.lab2;

import grozail.lab2.persons.Postgraduate;
import grozail.lab2.persons.Student;
import grozail.lab2.persons.Undergraduate;
import grozail.lab2.util.Notifiable;
import grozail.lab2.util.Notifier;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by grozail
 * on 19.2.17.
 */
public class ProgrammingTest {
	public static void main(String[] args) {

		Set<Student> courseListeners = new HashSet<>();
		courseListeners.add(new Postgraduate("one", "one"));
		courseListeners.add(new Undergraduate("two", "two"));
		courseListeners.add(new Postgraduate("three", "three"));
		courseListeners.add(new Undergraduate("four", "four"));
		courseListeners.add(new Postgraduate("five", "five"));

		Course course = new Course("Course", courseListeners);

		Set<Notifiable> notifiables = new HashSet<>();

		for (Postgraduate postgraduate : course.getPostgraduates("no-name-academic")) {
			notifiables.add(((Notifiable) postgraduate));
		}

		Notifier notifier = new Notifier(notifiables);

		notifier.doNotifyAll();
	}
}
