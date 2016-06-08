package projekt.tin.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;

import projekt.tin.controller.GNR;

@SuppressWarnings("serial")
public class MainApp extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menuHelp, menuAboutGNR;
	private JMenuItem miAuthors, miAboutApp, miHowToUse, miAboutTCBH, miAboutADPQH, miAboutADPFH, miAboutFDMP;
	private JButton bStart;
	private MainOptionsPanel mainOptionsPanel;
	private AdditionalOptionsPanel additionalOptionsPanel;

	public MainApp() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("GNR");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		
		menuBar = new JMenuBar();
		menuHelp = new JMenu("Pomoc");
		menuAboutGNR = new JMenu("O GNR");
		miAboutApp = new JMenuItem("O programie");
		miHowToUse = new JMenuItem("Jak korzystaæ");
		miAuthors = new JMenuItem("Autorzy");
		miAboutADPFH = new JMenuItem("O metodzie ADPFH");
		miAboutADPQH = new JMenuItem("O metodzie ADPQH");
		miAboutFDMP = new JMenuItem("O metodzie FDMP");
		miAboutTCBH = new JMenuItem("O metodzie TCBH");

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
		

		miAboutApp.addActionListener(this);
		miAuthors.addActionListener(this);
		miHowToUse.addActionListener(this);
		miAboutADPFH.addActionListener(this);
		miAboutADPQH.addActionListener(this);
		miAboutFDMP.addActionListener(this);
		miAboutTCBH.addActionListener(this);
		
		
		mainOptionsPanel = new MainOptionsPanel();
		additionalOptionsPanel = new AdditionalOptionsPanel();
		getContentPane().add(mainOptionsPanel, BorderLayout.WEST);
		bStart = mainOptionsPanel.getbStart();
		bStart.addActionListener(this);
		getContentPane().add(additionalOptionsPanel, BorderLayout.EAST);
		getContentPane().add(bStart, BorderLayout.SOUTH);
		pack();
		setLocation(dim.width/2 - this.getWidth()/2, dim.height/2 - this.getHeight()/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		
		if(src == bStart){
			GNR gnr = new GNR();
			if(mainOptionsPanel.getMethod() == MainOptionsPanel.TCBH){
				createTimeChartWindow(gnr, null, null);
			}
			else if(mainOptionsPanel.getMethod() == MainOptionsPanel.ADPQH){
				createBarChartWindow(gnr);
			}
			else if(mainOptionsPanel.getMethod() == MainOptionsPanel.ADPFH){
				createBarChartWindowADPFH(gnr);
			} else if (mainOptionsPanel.getMethod() == MainOptionsPanel.FDMP) {
				Integer startFrom = convertHourToMinutes(mainOptionsPanel.getTimeFrom().getText());
				Integer endIn = convertHourToMinutes(mainOptionsPanel.getTimeTo().getText());
				if (startFrom == null || endIn == null || endIn < startFrom) {
					JOptionPane.showMessageDialog(null, "B³êdne dane!");
					return;
				}
				
				createTimeChartWindow(gnr, startFrom, endIn);
			}
		}
		else if (src == miAboutApp) {
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
			String message = "Aby wykonaæ obliczenia Godziny Najwiêkszego Ruchu nale¿y wybraæ dwa pliki(pierwszy to CZAS.txt, drugi INT.txt). "
					+ "\nPo wybraniu metody obliczeñ po prawej stronie okna mo¿na za³¹czyæ lub wy³¹czyæ generowanie wykresu."
					+ "\nDla metody FDMP mo¿liwe jest wprowadzenie przedzia³u czasowego obserwacji w formacie hh:mm."
					+ "\nWygenerowany wykres mo¿na zapisaæ naciskaj¹c przyisk \"zapisz\", wykresy zapisuj¹ siê w katalogu \"charts\" w folderze aplikacji.";
			JOptionPane.showMessageDialog(null, message);
		}
		else if(src == miAboutADPFH){
			String message = "Natê¿enie ruchu jest mierzone w sposób ci¹g³y przez ca³y dzieñ w godzinnych przedzia³ach. "
					+ "\nTylko najwiêksza wartoœæ natê¿enia jest rejestrowana. "
					+ "\nNatê¿enie w sensie ADPH jest œredni¹ z 10 dziennych natê¿eñ szczytowych, wybranych spoœród 14 kolejnych dni pomiarowych";
			JOptionPane.showMessageDialog(null, message);
		}
		else if (src == miAboutADPQH) {
			String message = "Natê¿enie ruchu jest mierzone w sposób ci¹g³y przez ca³y dzieñ w 15-minutowych przedzia³ach. "
					+ "\nWartoœci natê¿enia s¹ przetwarzane codziennie w celu znalezienia czterech kolejnych kwadransów, "
					+ "\nktórych suma daje najwiêksz¹ wartoœæ natê¿enia. Tylko ta wartoœæ natê¿enia ruchu zastaje przechowana. "
					+ "\nŒrednia jest obliczana z dziennych natê¿eñ szczytowych.";
			JOptionPane.showMessageDialog(null, message);
		}
		else if (src == miAboutTCBH) {
			String message = "Standardowa metoda obliczania œredniej godziny najwiêkszego ruchu. "
					+ "\nKa¿dego dnia dla poszczególnych kwadransów zapisuje siê wartoœci za³atwianego ruchu "
					+ "\nNastêpnie wartoœci z tych samych kwadransów poszczególnych dni s¹ nastêpnie uœredniane. "
					+ "\nCztery nastêpuj¹ce po sobie kwadranse z tego dnia, "
					+ "\nktóre po zsumowaniu daj¹ najwiêksz¹ wartoœæ, tworz¹ godzinê TCBH z jej natê¿eniem. ";
			JOptionPane.showMessageDialog(null, message);
		}
		else if (src == miAboutFDMP) {
			String message = "Operator mo¿e uznaæ, ¿e jest rzecz¹ ekonomicznie uzasadnion¹, "
					+ "\nby ograniczyæ pomiary do kilku lub jednej godziny dziennie . "
					+ "\nOkres pomiaru w metodzie FDMP powinien odpowiadaæ najwy¿ej po³o¿onej czêœci przekroju ruchu, "
					+ "\nw której przypuszczalnie znajduje siê godzina najwiêkszego ruchu obliczana metod¹ TCBH. "
					+ "\nWartoœci pomiarowe zbierane s¹ oddzielnie dla ka¿dego kwadransa, a godzina najwiêkszego ruchu jest okreœlana na koñcu okresu pomiarowego. "
					+ "\nW praktyce metoda ta dostarcza wyniki, które stanowi¹ oko³o 95% poziomu ruchu obliczanego metod¹ TCBH";
			JOptionPane.showMessageDialog(null, message);
		}

	}
	
	private void createBarChartWindowADPFH(GNR gnr) {
		List<Double> gnrInEachDay = new ArrayList<>();
		
		gnrInEachDay = gnr.methodADPFH(mainOptionsPanel.getThirtyDaysCallsInQuarter());
		System.out.println(gnrInEachDay.size());
		TimeChart timeChart = new TimeChart();
		
		JFrame chart = new JFrame();
		chart.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Button saveButton = new Button("Zapisz wykres");
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		
		ChartPanel chartPanel = timeChart.barChart(gnrInEachDay);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ChartUtilities.saveChartAsJPEG(new File("charts/chart" + dateFormat.format(date) + ".jpg"), chartPanel.getChart(), 1000, 600);
					JOptionPane.showMessageDialog(null, "Zapisano!");
				} catch (IOException ex) {
					System.err.println(ex);
				}
			}
		});
		
		Button okButton = new Button("Ok");
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chart.dispose();
			}
		});
		
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		chart.add(new JLabel(gnr.toString()), gbc);
		gbc.gridy++;
		if (additionalOptionsPanel.getCheckboxChart().isSelected()) {
			chart.add(chartPanel, gbc);
			gbc.gridy++;
			chart.add(saveButton, gbc);
			gbc.gridx++;
		}
		chart.add(okButton, gbc);
		chart.setVisible(true);
		chart.pack();
		
		return;
	}
	
	
	private void createBarChartWindow(GNR gnr) {
		List<Double> gnrInEachDay = new ArrayList<>();
		
		gnrInEachDay = gnr.methodADPQH(mainOptionsPanel.getThirtyDaysCallsInQuarter());
		System.out.println(gnrInEachDay.size());
		TimeChart timeChart = new TimeChart();
		
		JFrame chart = new JFrame();
		chart.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Button saveButton = new Button("Zapisz wykres");
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		
		ChartPanel chartPanel = timeChart.barChart(gnrInEachDay);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ChartUtilities.saveChartAsJPEG(new File("charts/chart" + dateFormat.format(date) + ".jpg"), chartPanel.getChart(), 1000, 600);
					JOptionPane.showMessageDialog(null, "Zapisano!");
				} catch (IOException ex) {
					System.err.println(ex);
				}
			}
		});
		
		Button okButton = new Button("Ok");
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chart.dispose();
			}
		});
		
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		chart.add(new JLabel(gnr.toString()), gbc);
		gbc.gridy++;
		if (additionalOptionsPanel.getCheckboxChart().isSelected()) {
			chart.add(chartPanel, gbc);
			gbc.gridy++;
			chart.add(saveButton, gbc);
			gbc.gridx++;
		}
		chart.add(okButton, gbc);
		chart.setVisible(true);
		chart.pack();
		
		return;
	}

	private void createTimeChartWindow(GNR gnr, Integer startFrom, Integer endIn) {
		gnr.methodTCBH(mainOptionsPanel.getThirtyDaysCallsInQuarter(), startFrom, endIn);
		TimeChart timeChart = new TimeChart();
		
		JFrame chart = new JFrame();
		chart.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Button saveButton = new Button("Zapisz wykres");
		
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		ChartPanel chartPanel = timeChart.addData(mainOptionsPanel.getOneDayCallsInQuarter(), startFrom, endIn);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ChartUtilities.saveChartAsJPEG(new File("charts/chart" + dateFormat.format(date) + ".jpg"), chartPanel.getChart(), 1000, 600);
					JOptionPane.showMessageDialog(null, "Zapisano!");
				} catch (IOException ex) {
					System.err.println(ex);
				}
			}
		});
		
		Button okButton = new Button("Ok");
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chart.dispose();
			}
		});
		
		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		chart.add(new JLabel(gnr.toString()), gbc);
		gbc.gridy++;
		if (additionalOptionsPanel.getCheckboxChart().isSelected()) {
			chart.add(chartPanel, gbc);
			gbc.gridy++;
			chart.add(saveButton, gbc);
			gbc.gridx++;
		}
		chart.add(okButton, gbc);
		chart.setVisible(true);
		chart.pack();
		
		return;
	}
	
	public Integer convertHourToMinutes(String time) {
		String[] parts;
		parts = time.split(":");
		double hour;
		
		try {
			hour = Double.parseDouble(parts[0]);
		} catch (Exception e) {
			return null;
		}
		double minute;
		if (parts.length > 1) {
			minute = Double.parseDouble(parts[1]);
		}  else {
			minute = 0;
		}
		if (hour > 24 || minute > 59) {
			return null;
		}
		double minutes = (hour * 60) + minute;
		System.out.println(minutes);
		return (int) minutes;
	}
	
	public void enableStartButton() {
		bStart.setEnabled(true);
	}
	
	public static void main(String[] args) {
		new MainApp();
	}


}
