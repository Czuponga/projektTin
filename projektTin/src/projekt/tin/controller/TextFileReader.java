package projekt.tin.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import projekt.tin.view.MainApp;

public class TextFileReader {
	private String path;
	private FileInputStream input = null;
	private BufferedReader br;

	public TextFileReader(String path) {
		this.path = path;
		try {
			input = new FileInputStream(path);
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Brakuje pliku z danymi.");
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
		try {
			input = new FileInputStream(path);
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Brakuje pliku z danymi.");
		}
	}

	public int countFileLines() {
		int linesCounter = 0;
		try {
			br = new BufferedReader(new InputStreamReader(input));
			while (br.readLine() != null) {
				linesCounter++;
			}
			br.close();
		}
		catch (Exception e) {
			return 0;
		}
		return linesCounter;
	}

	// zwraca listê której indexy s¹ numerami kwadransów, wartoœci iloœci¹
	// wywo³añ w kwadransach
	public List<Double> numberOfCallsInEachQuarter(int numberOfCallsInDay) {
		int minute = 0;
		double value = 0;
		double tempValue = 0;
		String sCurrentLine;
		List<Double> calls = new ArrayList<Double>();
		int whichStep = 0;
		int whichQuarter = 1;

		br = new BufferedReader(new InputStreamReader(input));
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				for (String tempString : sCurrentLine.split("\\t")) {
					if (whichStep == 0) {
						minute = Integer.parseInt(tempString);
					}
					else if (whichStep == 1) {
						tempValue = Double.parseDouble(tempString)
								* numberOfCallsInDay;
						if (minute <= whichQuarter * 15) {
							value += tempValue;
						}
						else if (minute > whichQuarter * 15) {
							calls.add(value);
							value = 0;
							value += tempValue;
							whichQuarter++;
						}
					}
					whichStep++;
					if (whichStep >= 2)
						whichStep = 0;
				}
			}
			calls.add(value);
			br.close();
		}
		catch (Exception e) {
			return null;
		}
		return calls;
	}
}
