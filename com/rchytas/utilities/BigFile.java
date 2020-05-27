package com.rchytas.utilities;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

/**
 * Class to read a very large file in Java
 * @author mpandit
 * Usage:
 * 		BigFile file = new BigFile("/Users/mpandit/workspace/very_very_large_log.log");
		for (String line : file)
			DO STUFF
 */
public class BigFile implements Iterable<String> {
	private BufferedReader _reader;

	public BigFile(String filePath) throws Exception {
		_reader = new BufferedReader(new FileReader(filePath));
	}

	public void Close() {
		try {
			_reader.close();
		} catch (Exception ex) {
		}
	}

	public Iterator<String> iterator() {
		return new FileIterator();
	}

	private class FileIterator implements Iterator<String> {
		private String _currentLine;

		public boolean hasNext() {
			try {
				_currentLine = _reader.readLine();
			} catch (Exception ex) {
				_currentLine = null;
				ex.printStackTrace();
			}

			return _currentLine != null;
		}

		public String next() {
			return _currentLine;
		}

		public void remove() {
		}
	}
}