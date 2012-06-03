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

public abstract class JGamePanel extends JPanel implements GamePanel {
  private static final long serialVersionUID = 6798239655884559261L;

  protected final AwtKeyMemorizer keyMemorizer;

  public JGamePanel() {
    this(false);
  }

  public JGamePanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);

    keyMemorizer = new AwtKeyMemorizer();
  }

  @Override
  public abstract Image createEmptyImage(int width, int height);

  @Override
  public Image loadImage(String path) {
    ClassLoader cl = this.getClass().getClassLoader();
    URL url = cl.getResource(path);
    return Toolkit.getDefaultToolkit().getImage(url);
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
  public void paintComponent(Graphics g) {
    Image bufferImage = getBufferImage();
    if (bufferImage == null) {
      super.paintComponent(g);
    } else {
      g.drawImage(bufferImage, 0, 0, this);
    }
  }

  @Override
  public void setSize(Dimension d) {
    super.setSize(d);
    resizeBufferImage(d.width, d.height);
  }

  @Override
  public void setSize(int width, int height) {
    super.setSize(width, height);
    resizeBufferImage(width, height);
  }

  private void resizeBufferImage(int width, int height) {
    Image bufferImage = getBufferImage();
    if (bufferImage == null) {
      return;
    }
    if (width == bufferImage.getWidth(this) && height == bufferImage.getHeight(this)) {
      return;
    }

    Image newImage = this.createEmptyImage(width, height);
    newImage.getGraphics().drawImage(bufferImage, 0, 0, this);
    bufferImage = newImage;
    updateRendererImage();
  }

  public abstract void updateRendererImage();
}
