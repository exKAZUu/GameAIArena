package net.exkazuu.gameaiarena.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.apache.commons.lang.StringUtils;

public class ExternalComputerPlayer {

  private Process _process;

  private BufferedReader _reader;
  private BufferedReader _errorReader;

  private PrintStream _writer;
  private PrintStream _stdinLogStream;
  private PrintStream _stdoutLogStream;
  private PrintStream _errorLogStream;

  public ExternalComputerPlayer(String[] command) throws IOException {
    ProcessBuilder pb = new ProcessBuilder(command);
    try {
      _process = pb.start();
      _reader = new BufferedReader(new InputStreamReader(_process.getInputStream()));
      _writer = new PrintStream(_process.getOutputStream());
      _errorReader = new BufferedReader(new InputStreamReader(_process.getErrorStream()));
    } catch (IOException e) {
      System.err.println("Fail to lauch the specified command for running an AI program");
      System.err.println("    Command with args: " + StringUtils.join(command, " "));
      if (_process != null) {
        _process.destroy();
      }
      throw e;
    }
  }

  public void setStdinLogStream(PrintStream outStream) {
    _stdinLogStream = outStream;
  }

  public void setStdoutLogStream(PrintStream outStream) {
    _stdoutLogStream = outStream;
  }

  public void setErrorLogStream(PrintStream outStream) {
    _errorLogStream = outStream;
  }

  public void setStdinLogStream(File file) throws FileNotFoundException {
    _stdinLogStream = new PrintStream(file);
  }

  public void setStdoutLogStream(File file) throws FileNotFoundException {
    _stdoutLogStream = new PrintStream(file);
  }

  public void setErrorLogStream(File file) throws FileNotFoundException {
    _errorLogStream = new PrintStream(file);
  }

  public void setStdinLogStream(String filePath) throws FileNotFoundException {
    _stdinLogStream = new PrintStream(filePath);
  }

  public void setStdoutLogStream(String filePath) throws FileNotFoundException {
    _stdoutLogStream = new PrintStream(filePath);
  }

  public void setErrorLogStream(String filePath) throws FileNotFoundException {
    _errorLogStream = new PrintStream(filePath);
  }

  public void release() {
    if (_process == null) {
      return;
    }
    writeError();
    _process.destroy();
    try {
      if (_reader != null) {
        _reader.close();
      }
      if (_writer != null) {
        _writer.close();
      }
      if (_errorReader != null) {
        _errorReader.close();
      }
      if (_stdinLogStream != null) {
        _stdinLogStream.close();
      }
      if (_stdoutLogStream != null) {
        _stdoutLogStream.close();
      }
      if (_errorLogStream != null) {
        _errorLogStream.close();
      }
    } catch (IOException e) {
      System.err.println("Fail to close streams.");
    }
    _process = null;
  }

  protected void writeLine(String str) {
    if (_stdinLogStream != null) {
      _stdinLogStream.println(str);
    }
    _writer.println(str);
    _writer.flush();
  }

  protected String readLine() {
    String line = "";
    try {
      line = _reader.readLine();
    } catch (IOException e) {
      System.err.println("Fail to read a line from the standar output.");
    }
    writeError();
    if (_stdoutLogStream != null) {
      _stdoutLogStream.println(line);
    }
    return line;
  }

  private void writeError() {
    try {
      if (!_errorReader.ready()) {
        return;
      }
      String line;
      while (_errorReader.ready() && (line = _errorReader.readLine()) != null) {
        _errorLogStream.println(line);
      }
    } catch (IOException e) {
      System.err.println("Fail to read the error stream.");
    }
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      release();
    } finally {
      super.finalize();
    }
  }
}
