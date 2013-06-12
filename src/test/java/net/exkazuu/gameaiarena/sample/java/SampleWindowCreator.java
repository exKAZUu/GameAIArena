package net.exkazuu.gameaiarena.sample.java;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import net.exkazuu.gameaiarena.gui.JGamePanel;
import net.exkazuu.gameaiarena.gui.builder.WindowCreator;

public class SampleWindowCreator extends WindowCreator {
  @Override
  public JFrame createWindow(JGamePanel gamePanel) {
    // Initialize layout components
    JPanel mainPanel = new JPanel();
    SpringLayout layout = new SpringLayout();
    mainPanel.setLayout(layout);

    // Initialize each component
    JFrame window = new JFrame();
    mainPanel.add(gamePanel);
    JTextArea logArea = new JTextArea();
    JScrollPane logScrollPane = new JScrollPane(logArea);
    logScrollPane.setPreferredSize(new Dimension(0, 0));
    mainPanel.add(logScrollPane);
    logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    logArea.setEditable(false);
    JTextField commandField = new JTextField();
    commandField.setPreferredSize(new Dimension(0, 20));
    commandField.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
    mainPanel.add(commandField);

    // Layout compo6nents
    layout.putConstraint(SpringLayout.NORTH, gamePanel, 0, SpringLayout.NORTH, mainPanel);
    layout.putConstraint(SpringLayout.NORTH, logScrollPane, 0, SpringLayout.SOUTH, gamePanel);
    layout.putConstraint(SpringLayout.SOUTH, logScrollPane, 0, SpringLayout.NORTH, commandField);
    layout.putConstraint(SpringLayout.SOUTH, commandField, 0, SpringLayout.SOUTH, mainPanel);
    layout.putConstraint(SpringLayout.WEST, logScrollPane, 0, SpringLayout.WEST, mainPanel);
    layout.putConstraint(SpringLayout.WEST, commandField, 0, SpringLayout.WEST, mainPanel);
    layout.putConstraint(SpringLayout.EAST, logScrollPane, 0, SpringLayout.EAST, mainPanel);
    layout.putConstraint(SpringLayout.EAST, commandField, 0, SpringLayout.EAST, mainPanel);

    window.getContentPane().add(mainPanel);
    return window;
  }
}
