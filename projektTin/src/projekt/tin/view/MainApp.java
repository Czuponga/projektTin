package projekt.tin.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import projekt.tin.controller.TextFileReader;

public class MainApp extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private int timeCount;
	private String timePath = "./resources/CZAS.TXT";
	private String intPath = "./resources/INT.TXT";
	
	public MainApp(){
		setTitle("GNR");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		TextFileReader fileReader = new TextFileReader(timePath);
		timeCount = fileReader.countFileLines();
		fileReader.setPath(intPath);
		fileReader.numberOfCallsInEachMinute();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){	
				MainApp mainApp = new MainApp();
			}
		});

	}

}
