package projekt.tin.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import projekt.tin.controller.DaysGenerator;
import projekt.tin.controller.GNR;
import projekt.tin.controller.TextFileReader;

public class MainApp extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int timeCount;
	private String timePath = "./resources/CZAS.TXT";
	private String intPath = "./resources/INT.TXT";
	private JMenuBar menuBar;
	private JMenu menuHelp, menuAboutGNR;
	private JMenuItem miAuthors, miAboutApp, miHowToUse,
			miAboutTCBH, miAboutADPQH, miAboutADPFH, miAboutFDMP, miAboutFDMH;
	public List<List> thirtyDaysCallsInQuarters = new ArrayList<>();

	public MainApp() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
		
		
		

		TextFileReader fileReader = new TextFileReader(timePath);
		timeCount = fileReader.countFileLines();
		fileReader.setPath(intPath);
		DaysGenerator days = new DaysGenerator();
		List<Double> firstDay = new ArrayList<>();
		thirtyDaysCallsInQuarters = days.generateDays(fileReader
				.numberOfCallsInEachMinute());
		System.out.println(thirtyDaysCallsInQuarters.size());
		for (int i = 0; i < thirtyDaysCallsInQuarters.size(); i++) {
			System.out.println(thirtyDaysCallsInQuarters.get(i).size());
		}
	

	}

	public void createAndShowGUI() {
		try {
			UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("GNR");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	
		menuBar = new JMenuBar();
		menuHelp = new JMenu("Pomoc");
		menuAboutGNR = new JMenu("O GNR");
		miAboutApp = new JMenuItem("O programie");
		miHowToUse = new JMenuItem("Jak korzystaæ");
		miAuthors = new JMenuItem("Autorzy");
		miAboutADPFH = new JMenuItem("O ADPFH");
		miAboutADPQH = new JMenuItem("O ADPQH");
		miAboutFDMH = new JMenuItem("O FDMH");
		miAboutFDMP = new JMenuItem("O FDMP");
		miAboutTCBH = new JMenuItem("O TCBH");

		setJMenuBar(menuBar);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(menuHelp);
		menuHelp.add(miAboutApp);
		menuHelp.add(menuAboutGNR);
		menuHelp.add(miHowToUse);
		menuHelp.add(miAuthors);
		menuAboutGNR.add(miAboutTCBH);
		menuAboutGNR.add(miAboutADPQH);
		menuAboutGNR.add(miAboutADPFH);
		menuAboutGNR.add(miAboutFDMP);
		menuAboutGNR.add(miAboutFDMH);
		

		miAboutApp.addActionListener(this);
		miAuthors.addActionListener(this);
		miHowToUse.addActionListener(this);
		miAboutADPFH.addActionListener(this);
		miAboutADPQH.addActionListener(this);
		miAboutFDMH.addActionListener(this); 
		miAboutFDMP.addActionListener(this);
		miAboutTCBH.addActionListener(this);
		
		getContentPane().add(new MainOptionsPanel(), BorderLayout.WEST);
		getContentPane().add(new AdditionalOptionsPanel(), BorderLayout.EAST);
		getContentPane().add(new JButton("Uruchom program"), BorderLayout.SOUTH);
		pack();
	}

	public static void main(String[] args) {
		
		new MainApp();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if (src == miAboutApp) {
			String message = "Ten program wizualizuje ró¿ne metody wyznaczania Godziny Najwiêkszego Ruchu. Rysuje równie¿ wykresy.";
			JOptionPane.showMessageDialog(null, message);
		}
//		else if (src == miAboutGNR) {
//			String message = "Tutaj opiszemy wszystkie metody wyznaczania GNR";
//			JOptionPane.showMessageDialog(null, message);
//		}
		else if (src == miAuthors) {
			String message = "Program stworzyli studenci 2 roku teleinformytyki Politechniki Wroc³awskiej. \n Jakub Parys i Adrian Kuliñski";
			JOptionPane.showMessageDialog(null, message);
		}
		else if (src == miHowToUse) {
			String message = "Ma³y help dla u¿ytkowników gdzie maj¹ klikaæ";
			JOptionPane.showMessageDialog(null, message);
		}
		else if(src == miAboutADPFH){
			JOptionPane.showMessageDialog(null, "ADPFH");
		}

	}

}
