package net.exkazuu.gameaiarena.gui;


import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class SceneManager {
  private double fps;
  private double mspf;
  private int maxSkip;
  private boolean ended;
  private long nextTime;

  public SceneManager() {
    this(60);
  }

  public SceneManager(double fps) {
    this(fps, 0);
  }

  public SceneManager(double fps, int maxSkip) {
    this.setFps(fps);
  }

  public double getFps() {
    return this.fps;
  }

  public double getMaxSkip() {
    return this.maxSkip;
  }

  public double getMspf() {
    return this.mspf;
  }

  public <Env extends Environment> void run(final Env env, final Scene<Env> firstScene) {
    Scene<Env> scene = firstScene;
    initialize(env, scene);
    while (!ended) {
      int maxSkip = this.maxSkip; // 最大で連続に設定した回数(maxSkip回)runを実行
      long nowTime;
      do {
        scene = runOneStep(env, scene);
        if (scene == null) {
          return;
        }

        nextTime += this.mspf;
        nowTime = System.currentTimeMillis();
        if (--maxSkip <= 0) {
          break;
        }
      } while (nextTime <= nowTime);

      if (nextTime > nowTime) {
        try {
          Thread.sleep(nextTime - nowTime);
        } catch (final InterruptedException e) {
          e.printStackTrace();
        }
      }
      if (env.getRenderer() != null) {
        scene.draw();
        env.getRenderer().forceRepaint();
        for (Renderer renderer : env.getSubRenderers()) {
          renderer.forceRepaint();
        }
      }
    }
    scene.release();
  }

  public <Env extends Environment> void initialize(final Env env, final Scene<Env> firstScene) {
    Scene<Env> scene = firstScene;
    scene.setEnvironment(env);
    scene.initialize();
    ended = false;
    nextTime = System.currentTimeMillis();
  }

  public <Env extends Environment> Scene<Env> runOneStep(final Env env, Scene<Env> scene) {
    // キー入力の更新
    if (env.getInputer() != null) {
      env.getInputer().update();
    }
    // シーンの処理
    final Scene<Env> nextScene = scene.run();
    if (nextScene != scene) {
      scene.release();
      if (nextScene == null) {
        return null;
      }
      scene = nextScene;
      scene.setEnvironment(env);
      scene.initialize();
    }
    return scene;
  }

  public SceneManager setFps(double fps) {
    this.fps = fps;
    this.mspf = 1000 / fps;
    return this;
  }

  public SceneManager setMaxSkip(int maxSkip) {
    this.maxSkip = maxSkip;
    return this;
  }

  /**
   * Adds a window listener for terminating this manager in the specified window.
   * 
   * @param window the window to be added a window listener
   */
  public SceneManager addWindowListenerForTerminating(Window window) {
    window.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        terminate();
      }

      @Override
      public void windowClosed(WindowEvent e) {
        terminate();
      }
    });
    return this;
  }

  public void terminate() {
    ended = true;
  }
}
