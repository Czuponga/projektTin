package projekt.tin.controller;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
	
	//zwraca list� kt�rej indexy s� numerami kwadrans�w, warto�ci ilo�ci� wywo�a� w kwadransach
	public void numberOfCalls() {
		int minute;
		int value;
		String line;
		String path  = "./resources/INT.TXT";
		String sCurrentLine;
		List<Integer> calls = new ArrayList<Integer>();
		
		FileInputStream input = null;
		BufferedReader br;
		
		try {
			input = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(input));
			while ((sCurrentLine = br.readLine()) != null) {
				for (String str2 : sCurrentLine.split("\\t")) {
					System.out.println(str2);
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	
}
