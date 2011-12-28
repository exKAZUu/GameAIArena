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

	public JGamePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);

		keyMemorizer = new AwtKeyMemorizer();
	}

	@Override
	public Image createImage(int width, int height) {
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

	@Override
	public void initialize() {
		final JComponent keyFocus = this;
		keyFocus.addKeyListener(keyMemorizer);
		keyFocus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				keyFocus.requestFocusInWindow();
			}
		});
		keyFocus.requestFocusInWindow();
	}

	@Override
	public Renderer createDoubleBufferedRenderer() {
		Dimension d = getPreferredSize();
		bufferImage = this.createImage(d.width, d.height);
		return new DoubleBufferedRenderer(this, this, bufferImage);
	}

	@Override
	public Renderer createRawRenderer() {
		bufferImage = null;
		return new RawRenderer(this);
	}

	@Override
	public Image loadImage(String path) {
		ClassLoader cl = this.getClass().getClassLoader();
		URL url = cl.getResource(path);
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (bufferImage == null) {
			super.paintComponent(g);
		} else {
			g.drawImage(bufferImage, 0, 0, this);
		}
	}

	@Override
	public void setPreferredSize(Dimension d) {
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
	public void setSize(Dimension d) {
		setPreferredSize(d);
	}

	@Override
	public void setSize(int width, int height) {
		this.setSize(new Dimension(width, height));
	}
}
