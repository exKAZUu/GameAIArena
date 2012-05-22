package jp.ac.waseda.cs.washi.gameaiarena.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import jp.ac.waseda.cs.washi.gameaiarena.key.AwtKeyMemorizer;

import com.google.common.collect.Lists;

public class JGamePanel extends JPanel implements GamePanel {
  private static final long serialVersionUID = 6798239655884559261L;

  private Image bufferImage;
  private final AwtKeyMemorizer keyMemorizer;
  private final List<GamePanelListener> gamePanelListeners;

  public JGamePanel() {
    this(false);
  }

  public JGamePanel(boolean isDoubleBuffered) {
    super(isDoubleBuffered);

    keyMemorizer = new AwtKeyMemorizer();
    gamePanelListeners = Lists.newArrayList();
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
  public Renderer createSwingDoubleBufferedRenderer() {
    setDoubleBuffered(true);
    bufferImage = null;
    return new RawRenderer(this);
  }

  @Override
  public Renderer createDefaultDoubleBufferedRenderer() {
    setDoubleBuffered(false);
    Dimension d = getPreferredSize();
    bufferImage = this.createImage(d.width, d.height);
    return new DoubleBufferedRenderer(this, this, bufferImage);
  }

  @Override
  public Renderer createDuplicateDoubleBufferedRenderer() {
    setDoubleBuffered(true);
    Dimension d = getPreferredSize();
    bufferImage = this.createImage(d.width, d.height);
    return new DoubleBufferedRenderer(this, this, bufferImage);
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
    if (bufferImage == null) {
      return;
    }
    if (width == bufferImage.getWidth(this) && height == bufferImage.getHeight(this)) {
      return;
    }

    Image newImage = this.createImage(width, height);
    newImage.getGraphics().drawImage(bufferImage, 0, 0, this);
    bufferImage = newImage;

    for (GamePanelListener listener : gamePanelListeners) {
      listener.updatedBufferImage(newImage);
    }
  }

  @Override
  public void addGamePnaelListenerForRenderer(GamePanelListener listener) {
    gamePanelListeners.add(listener);
  }
}
