package grozail.lab3.diagram;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by grozail
 * on 3.3.17.
 */
public class DiagramPanel extends JPanel {
	ArrayList<Slice> slices = new ArrayList<>();
	Rectangle rectangle = new Rectangle(20, 20, 300, 300);
	public DiagramPanel() {
		Random random = new Random(1488);
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/grozail/lab3/input.txt"))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] words = line.split(" ");
				Double value = Double.valueOf(words[1]);
				if (value < 0) {
					throw new InputMismatchException("Wrong input!");
				}
				slices.add(new Slice(words[0], value,
						new Color(abs(random.nextInt() % 256), abs(random.nextInt() % 256), abs(random.nextInt() % 256))));

			}
		}
		catch (Exception e) {
			slices.clear();
		}
		repaint();
	}

	void drawDiagram(Graphics2D graphics2D, Rectangle area) {
		double sum = 0;
		for (Slice slice : slices) {
			sum += slice.value;
		}
		double currentValue = 0;
		int startAngle = 0;
		int i = 0;
		for (Slice slice : slices) {
			startAngle = (int) (currentValue * 360 / sum);
			int arcAngle = (int) (slice.value * 360 / sum);
			graphics2D.setColor(slice.color);
			graphics2D.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			currentValue += slice.value;

			graphics2D.fillRect(area.x + 330, area.y + 30 * i, 20, 20);
			graphics2D.setColor(Color.black);
			graphics2D.drawString(slice.key + " = " + slice.value, area.x + 360, area.y + 30 * i);
			i++;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawDiagram(((Graphics2D) g), rectangle);
	}

	private class Slice {
		String key;
		double value;
		Color color;
		Slice(String key, double value, Color color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}
	}

}
