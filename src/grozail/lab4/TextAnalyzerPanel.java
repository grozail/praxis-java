package grozail.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by grozail
 * on 14.3.17.
 */
class TextAnalyzerPanel extends JPanel {
	private static final Pattern pattern = Pattern.compile("(\\d{4}([,./-])((0[1-9])|(1[0-2]))(\\2)(([0-2][0-9])|(3[01])))|(\\d{4}(\\2)(\\7)(\\2)(\\3))|" +
			"((\\3)(\\2)(\\7)(\\2)\\d{4})|((\\7)(\\2)(\\3)(\\2)\\d{4})");
	private final JTextArea textArea = new JTextArea(80,0);
	private final JButton button = new JButton("Analyze");
	private final JList<String> list = new JList<>();
	private final DefaultListModel<String> listModel = new DefaultListModel<>();
	TextAnalyzerPanel() {
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		add(list, BorderLayout.EAST);
		list.setModel(listModel);
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/grozail/lab4/input.txt"))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				textArea.append(line);
			}
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(TextAnalyzerPanel.this, "Shit happened", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		button.addActionListener(this::buttonHandler);
	}

	private void buttonHandler(ActionEvent event) {
		String text = textArea.getText();
		listModel.clear();
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			listModel.addElement(matcher.group(0));
		}
	}
}
