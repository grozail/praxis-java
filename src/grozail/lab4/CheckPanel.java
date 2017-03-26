package grozail.lab4;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * Created by grozail
 * on 13.3.17.
 */
class CheckPanel extends JPanel {
	public static final String[] CHOICES = {"Natural number", "Integer", "Decimal", "Date", "Time", "Email"};
	private String choice = CHOICES[0];
	private Pattern pattern = PATTERNS[0];
	private JComboBox<String> comboBox = new JComboBox<>(CHOICES);
	private JTextField textField = new JTextField(20);
	private JButton button = new JButton("Validate!");
	Color color = Color.MAGENTA;
	private static final Pattern[] PATTERNS = {
			Pattern.compile("\\+?[^0\\D]\\d*"),
			Pattern.compile("[+-]?\\d+"),
			Pattern.compile("[+-]?\\d*[.,]?\\d+(((E[+-])|(e[+-]))\\d+)?"),
			Pattern.compile("(\\d{4}([,./-])((0[1-9])|(1[0-2]))(\\2)(([0-2][0-9])|(3[01])))|(\\d{4}(\\2)(\\7)(\\2)(\\3))|" +
					"((\\3)(\\2)(\\7)(\\2)\\d{4})|((\\7)(\\2)(\\3)(\\2)\\d{4})"),
			Pattern.compile("(((2[0-4])|([01]?[0-9]))([:,./-])[0-5][0-9]\\5[0-5][0-9])"),
			Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)

	};
	/*(\d{4}([,./-])((0[1-9])|(1[0-2]))[,./-](([0-2][0-9])|(3[01])))|(\d{4}[,./-](([0-2][0-9])|(3[01]))(\2)((0[1-9])|(1[0-2])))|" +
					"(((0[1-9])|(1[0-2]))[,./-](([0-2][0-9])|(3[01]))[,./-]\d{4})|((([0-2][0-9])|(3[01]))(\2)((0[1-9])|(1[0-2]))(\2)\d{4})*/

	CheckPanel() {
		setLayout(new FlowLayout());
		configComboBox();
		add(textField);
		configButton();
	}

	private void configComboBox() {
		add(comboBox);
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(event -> {
			choice = CHOICES[comboBox.getSelectedIndex()];
			pattern = PATTERNS[comboBox.getSelectedIndex()];
		});
	}

	private void configButton() {
		add(button);
		button.addActionListener(event -> {
			String text = textField.getText().toLowerCase();
			if (text.matches(pattern.pattern())) {
				button.setText("VALID!");
				button.setBackground(Color.green);
			}
			else {
				button.setText("INVALID!");
				button.setBackground(Color.red);
			}
		});
	}

}

