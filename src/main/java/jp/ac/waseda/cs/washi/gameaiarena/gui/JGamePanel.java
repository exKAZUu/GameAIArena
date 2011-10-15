package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.swing.JComponent;
import javax.swing.JPanel;

import jp.ac.waseda.cs.washi.gameaiarena.key.AwtKeyMemorizer;

public class JGamePanel extends JPanel implements GamePanel {
	private static final long serialVersionUID = 6798239655884559261L;

	private Image bufferImage;
	private final AwtKeyMemorizer keyMemorizer;

	public JGamePanel() {
		this(false);
	}

	public JGamePanel(final boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		keyMemorizer = new AwtKeyMemorizer();
	}

	@Override
	public Image createImage(final int width, final int height) {
		return super.createImage(width, height);
	}

	@Override
	public void forceRepaint() {
		final Graphics g = getGraphics();
		update(g);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	@Override
	public AwtKeyMemorizer getKeyMemorizer() {
		return keyMemorizer;
	}

	@Override
	public ImageObserver getObserver() {
		return this;
	}

	public Renderer initialize() {
		return this.initialize(this);
	}

	public Renderer initialize(final JComponent keyFocus) {
		final Dimension d = getPreferredSize();
		bufferImage = this.createImage(d.width, d.height);
		final Renderer renderer = new Renderer(this, keyFocus, bufferImage);

		keyFocus.addKeyListener(keyMemorizer);
		keyFocus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				keyFocus.requestFocusInWindow();
			}
		});
		keyFocus.requestFocusInWindow();
		return renderer;
	}

	@Override
	public Image loadImage(final String path) {
		final ClassLoader cl = this.getClass().getClassLoader();
		final URL url = cl.getResource(path);
		final Image image = Toolkit.getDefaultToolkit().getImage(url);
		return image;
	}

	@Override
	public void paintComponent(final Graphics g) {
		g.drawImage(bufferImage, 0, 0, this);
	}

	@Override
	public void setPreferredSize(final Dimension d) {
		super.setPreferredSize(d);

		if (bufferImage == null) {
			return;
		}
		if (d.width == bufferImage.getWidth(this)
				&& d.height == bufferImage.getHeight(this)) {
			return;
		}
		bufferImage = this.createImage(d.width, d.height);
	}

	@Override
	public void setSize(final Dimension d) {
		setPreferredSize(d);
	}

	@Override
	public void setSize(final int width, final int height) {
		this.setSize(new Dimension(width, height));
	}
}
