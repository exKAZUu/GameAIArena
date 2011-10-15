package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;

public class Renderer {
	private final Graphics graphics;
	private final Image image;
	private final GamePanel panel;
	private final ImageObserver imageObserver;
	private final MediaTracker mediaTracker;
	private int imageCount;

	public Renderer(GamePanel panel, Component trackerComponent, Image image) {
		this.panel = panel;
		this.image = image;
		graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		imageObserver = panel.getObserver();
		mediaTracker = new MediaTracker(trackerComponent);
	}

	public void clear(Color c) {
		graphics.setColor(c);
		graphics.fillRect(0, 0, image.getWidth(imageObserver),
				image.getHeight(imageObserver));
	}

	public Image createImage(int width, int height) {
		return panel.createImage(width, height);
	}

	public Image createImage(int width, int height, Color c) {
		final Image image = panel.createImage(width, height);
		final Graphics g = image.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, width, height);
		g.dispose();
		return image;
	}

	public void drawImage(Image img, int x, int y) {
		graphics.drawImage(img, x, y, imageObserver);
	}

	public void forceRepaint() {
		panel.forceRepaint();
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public Image getScreenImage() {
		return image;
	}

	public ImageObserver getImageObserver() {
		return imageObserver;
	}

	public GamePanel getPanel() {
		return panel;
	}

	public Image loadImage(String path) {
		final Image image = panel.loadImage(path);
		mediaTracker.addImage(image, imageCount++);
		return image;
	}

	public void waitLoadImage() {
		try {
			mediaTracker.waitForAll();
		} catch (final InterruptedException e) {
		}
	}
}