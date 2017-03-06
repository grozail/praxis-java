package grozail.lab3.spin;

import grozail.lab3.MainFrame3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by grozail
 * on 3.3.17.
 */
public class SpinPanel extends JPanel {

	private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
	public JSlider getSlider() {
		return slider;
	}

	private SomeObject object = new SomeObject();

	private static final int TICK = 100;

	public SpinPanel() {
		setLayout(new BorderLayout());
		initSlider();
		Timer timer = new Timer(TICK, event -> {
			object.update();
			repaint();
		});
		timer.start();
	}

	private void initSlider() {
		slider.setMajorTickSpacing(2);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(event -> {
			object.velocity = slider.getValue();
		});
		add(slider, BorderLayout.SOUTH);
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
		object.draw(graphics2D);
	}

	private static class SomeObject {
		BufferedImage image;
		double x0, y0, x, y;
		int velocity = 50;
		int time = 0;
		static final int RADIUS = MainFrame3.DEF_HEIGHT / 4;
		SomeObject() {
			x0 = MainFrame3.DEF_WIDTH / 2;
			y0 = MainFrame3.DEF_HEIGHT / 2;
			try {
				image = ImageIO.read(new File("src/grozail/lab3/img/sprite_1_player.png"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		void update() {
			time+=TICK;
			if (time >= 100 * TICK) {
				time = 0;
			}
			x = x0 + RADIUS * Math.cos(Math.PI / 2 - 2*Math.PI * (time*velocity)/(100 * TICK)) - image.getWidth() / 2;
			y = y0 + RADIUS * Math.sin(-Math.PI / 2 - 2*Math.PI * (time*velocity)/(100 * TICK)) - image.getHeight() / 2;

		}

		void draw(Graphics2D graphics2D) {
			graphics2D.drawImage(image, ((int) x), ((int) y), null);
			update();
		}
	}

}
