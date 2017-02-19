package grozail.lab2.persons;

/**
 * Created by grozail
 * on 19.2.17.
 */
public class Postgraduate extends Student {
	private Academic supervisor;

	public Postgraduate(String name, String login, String email) {
		super(name, login, email);
		supervisor = new Academic("no-name-academic");
	}

	public Academic getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Academic supervisor) {
		this.supervisor = supervisor;
	}
}
