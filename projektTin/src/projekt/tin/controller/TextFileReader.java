package projekt.tin.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFileReader {
	public long countFileLines(String path) {
		FileInputStream input = null;
		BufferedReader br;
		try {
			input = new FileInputStream(path);
			String sCurrentLine;
			long linesCounter = 0;
			br = new BufferedReader(new InputStreamReader(input));
			while ((sCurrentLine = br.readLine()) != null) {
				linesCounter++;
			}
			br.close();
			return linesCounter;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		} 
		
	}
	
	
}