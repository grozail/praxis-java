package grozail.lab2.util;

import java.util.Set;

/**
 * Created by grozail
 * on 19.2.17.
 */
public class Notifier {
	private Set<Notifiable> notifiables;

	public Notifier(Set<Notifiable> notifiables) {
		this.notifiables = notifiables;
	}

	public void doNotifyAll() {
		for (Notifiable notifiable : notifiables) {
			notifiable.notify("Lorem ipsum");
		}
	}

}
