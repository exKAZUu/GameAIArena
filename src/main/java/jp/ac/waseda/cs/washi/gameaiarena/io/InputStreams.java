package jp.ac.waseda.cs.washi.gameaiarena.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class InputStreams {

	private InputStreams() {
	}

	public static InputStream openResourceOrFile(String resourceOrFilePath) {
		try {
			URL url = InputStreams.class.getClassLoader().getResource(
					resourceOrFilePath);
			return url.openStream();
		} catch (IOException e) {
		}
		try {
			return new FileInputStream(resourceOrFilePath);
		} catch (FileNotFoundException e) {
		}
		return null;
	}
}
