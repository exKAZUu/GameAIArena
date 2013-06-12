package net.exkazuu.gameaiarena.io;

import java.io.FileInputStream;
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
		} catch (Exception e) {
		}
		try {
			return new FileInputStream(resourceOrFilePath);
		} catch (Exception e) {
		}
		return null;
	}

	public static InputStream openFileOrResource(String resourceOrFilePath) {
		try {
			return new FileInputStream(resourceOrFilePath);
		} catch (Exception e) {
		}
		try {
			URL url = InputStreams.class.getClassLoader().getResource(
					resourceOrFilePath);
			return url.openStream();
		} catch (Exception e) {
		}
		return null;
	}
}
