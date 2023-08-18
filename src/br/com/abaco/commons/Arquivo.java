package br.com.abaco.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Arquivo {
	private String path;
	private File file;

	public Arquivo(String path) {
		this.path = path;
		file = new File(this.path);
	}

	public boolean exists() {
		return file.exists();
	}

	public long length() {
		return file.length();
	}

	public byte[] toBytes() throws IOException {
		byte[] b = new byte[(int) file.length()];
		try {
			
			new FileInputStream(file).read(b);			
//			for (int i = 0; i < b.length; i++) {
//				System.out.print((char) b[i]);
//			}
			return b;
			
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("File Not Found.");
		} catch (IOException e1) {
			throw new IOException("Error Reading The File.");
		}

		
	}

}
