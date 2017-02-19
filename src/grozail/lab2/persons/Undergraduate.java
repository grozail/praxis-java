package grozail.lab2.persons;

/**
 * Created by grozail
 * on 19.2.17.
 */
public class Undergraduate extends Student {
	private Academic tutor;

	public Undergraduate(String name, String login, String email) {
		super(name, login, email);
		tutor = new Academic("no-name-academic");
	}

	public Academic getTutor() {
		return tutor;
	}

	public void setTutor(Academic tutor) {
		this.tutor = tutor;
	}
}
