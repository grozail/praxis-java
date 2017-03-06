package grozail.lab3.clock;

import grozail.lab3.MainFrame3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by grozail
 * on 3.3.17.
 */
public class ClockPanel extends JPanel {
	private Clock clock = new Clock();
	private int time = 0;
	private static final int SECOND = 1000;
	public ClockPanel() {
		Timer timer = new Timer(SECOND, (event) -> {
			time += SECOND;
			repaint();
			if (time >= 60 * SECOND) {
				time = 0;
			}
		});
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2D = ((Graphics2D) g);
		graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		clock.draw(graphics2D);
	}

	private class Clock {
		private final double RADIUS = MainFrame3.DEF_HEIGHT / 8;
		private Ellipse2D dial = new Ellipse2D.Double(MainFrame3.DEF_WIDTH / 2 - RADIUS, MainFrame3.DEF_HEIGHT / 2 - 2*RADIUS, 2*RADIUS, 2*RADIUS);
		private Pointer pointer = new Pointer();

		Clock() {

		}


		void draw(Graphics2D graphics2D) {
			graphics2D.draw(dial);
			graphics2D.drawLine(((int) pointer.x0), ((int) pointer.y0), ((int) pointer.x), ((int) pointer.y));
			pointer.update();
		}

		private class Pointer {
			double x;
			double y;
			final double x0 = dial.getCenterX();
			final double y0 = dial.getCenterY();

			Pointer() {
				x = x0;
				y = y0;
			}

			void update() {
				x = x0 + RADIUS * Math.cos(Math.PI / 2 - 2 * Math.PI * time / (60 * SECOND));
				y = y0 + RADIUS * Math.sin(-Math.PI / 2 - 2 * Math.PI * time / (60 * SECOND));
			}
		}
	}

}
