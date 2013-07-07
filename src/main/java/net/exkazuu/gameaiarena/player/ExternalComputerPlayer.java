package net.exkazuu.gameaiarena.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ExternalComputerPlayer {

  private Process _process;

  private final BufferedReader _reader;
  private final BufferedReader _errorReader;

  private final PrintStream _writer;
  private final List<PrintStream> _streamsForLoggingStdinOfExternalProgram;
  private final List<PrintStream> _streamsForLoggingStdoutOfExternalProgram;
  private final List<PrintStream> _streamsForLoggingErrorOfExternalProgram;

  public ExternalComputerPlayer(String[] command) throws IOException {
    ProcessBuilder pb = new ProcessBuilder(command);
    _streamsForLoggingStdinOfExternalProgram = new ArrayList<PrintStream>();
    _streamsForLoggingStdoutOfExternalProgram = new ArrayList<PrintStream>();
    _streamsForLoggingErrorOfExternalProgram = new ArrayList<PrintStream>();
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

  public void addStreamForLoggingStdinOfExternalProgram(PrintStream outStream) {
    _streamsForLoggingStdinOfExternalProgram.add(outStream);
  }

  public void addStreamForLoggingStdoutOfExternalProgram(PrintStream outStream) {
    _streamsForLoggingStdoutOfExternalProgram.add(outStream);
  }

  public void addStreamForLoggingErrorOfExternalProgram(PrintStream outStream) {
    _streamsForLoggingErrorOfExternalProgram.add(outStream);
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
      for (PrintStream stream : _streamsForLoggingStdinOfExternalProgram) {
        stream.close();
      }
      for (PrintStream stream : _streamsForLoggingStdoutOfExternalProgram) {
        stream.close();
      }
      for (PrintStream stream : _streamsForLoggingErrorOfExternalProgram) {
        stream.close();
      }
    } catch (IOException e) {
      System.err.println("Fail to close streams.");
    }
    _process = null;
  }

  public void writeLine(String str) {
    for (PrintStream stream : _streamsForLoggingStdinOfExternalProgram) {
      stream.println(str);
      stream.flush();
    }
    _writer.println(str);
    _writer.flush();
  }

  public String readLine() {
    String line = null;
    try {
      line = _reader.readLine();
    } catch (IOException e) {
      System.err.println("Fail to read a line from the standar output.");
    }
    writeError();
    for (PrintStream stream : _streamsForLoggingStdoutOfExternalProgram) {
      stream.println(line);
      stream.flush();
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
        for (PrintStream stream : _streamsForLoggingErrorOfExternalProgram) {
          stream.println(line);
          stream.flush();
        }
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
