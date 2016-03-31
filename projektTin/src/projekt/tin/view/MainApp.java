package projekt.tin.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import projekt.tin.controller.TextFileReader;

public class MainApp extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private long timeCount;
	private String path = "./resources/CZAS.TXT";
	
	public MainApp(){
		setTitle("GNR");
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		TextFileReader fileReader = new TextFileReader();
		timeCount = fileReader.countFileLines(path);
		System.out.println(timeCount);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainApp mainApp = new MainApp();
			}
		});

	}

}