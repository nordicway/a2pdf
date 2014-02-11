package it.zielke.a2pdf.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.commons.io.FilenameUtils;

public class Util {

	/**
	 * Finds the directory the current JAR the application is running from. Eg.
	 * if the JAR file is located at "/temp/name.jar", will return "/temp/".
	 * 
	 * @return JAR parent directory
	 */
	public static String getJARFileLocation() {

		boolean debug = false;
		String decodedPath = null;

		if (debug) {
			decodedPath = FilenameUtils.normalize(new File(".")
					.getAbsolutePath());
		} else {
			String path = new File(Util.class.getProtectionDomain()
					.getCodeSource().getLocation().getPath()).getParentFile()
					.getAbsolutePath();
			try {
				decodedPath = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// this should not happen on an official JVM
				try {
					decodedPath = URLDecoder.decode(path, "US-ASCII");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return decodedPath;
	}
}
