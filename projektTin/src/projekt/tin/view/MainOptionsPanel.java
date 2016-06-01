package projekt.tin.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import projekt.tin.controller.DaysGenerator;
import projekt.tin.controller.TextFileReader;

@SuppressWarnings("serial")
public class MainOptionsPanel extends JPanel implements ActionListener {

	private JLabel lFileOne, lFileTwo;
	private JButton bFileOne, bFileTwo;
	private JRadioButton rbMethod1, rbMethod2, rbMethod3, rbMethod4;
	private ButtonGroup bgMethodChooser;
	private List<Double> oneDayCallsInQuarter;
	private List<Double> oneDayCallsInHour;
	private List<List> thirtyDaysCallsInQuarter;
	private List<List> thirtyDaysCallsInHour;
	private int numberOfCallsInDay;
	public static int TCBH = 1, ADPQH = 2, ADPFH = 3, FDMP = 4;
	private JButton bStart;
	private JTextField timeFrom;
	private JTextField timeTo;

	public MainOptionsPanel() {
		super(new GridBagLayout());
		createAndShowComponents();
	}

	public void createAndShowComponents() {
		setVisible(true);
		setPreferredSize(new Dimension(250, 300));
		GridBagConstraints gbc = new GridBagConstraints();

		setBorder(BorderFactory.createTitledBorder("Opcje g³owne"));

		bStart = new JButton("Uruchom program");
		bStart.setEnabled(false);

		lFileOne = new JLabel("Wybierz pierwszy plik");
		lFileTwo = new JLabel("Wybierz drugi plik");
		bFileOne = new JButton("...");
		bFileTwo = new JButton("...");

		bFileTwo.setEnabled(false);

		rbMethod1 = new JRadioButton("Metoda TCBH", true);
		rbMethod2 = new JRadioButton("Metoda ADPQH");
		rbMethod3 = new JRadioButton("Metoda ADPFH");
		rbMethod4 = new JRadioButton("Metoda FDMP");
		
		rbMethod4.addActionListener(this);
		rbMethod3.addActionListener(this);
		rbMethod2.addActionListener(this);
		rbMethod1.addActionListener(this);
		
		bgMethodChooser = new ButtonGroup();
		bgMethodChooser.add(rbMethod1);
		bgMethodChooser.add(rbMethod2);
		bgMethodChooser.add(rbMethod3);
		bgMethodChooser.add(rbMethod4);
		
		timeFrom = new JTextField();
		timeFrom.setText("Od");
		timeFrom.setMinimumSize(new Dimension(50, 25));
		timeFrom.setEnabled(false);
		
		timeTo = new JTextField();
		timeTo.setText("Do");
		timeTo.setMinimumSize(new Dimension(50, 25));
		timeTo.setEnabled(false);

		bFileOne.addActionListener(this);
		bFileTwo.addActionListener(this);

		/*
		 * /* Definiowanie uk³adu komponentów
		 */

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lFileOne, gbc);
		gbc.gridy++;
		add(lFileTwo, gbc);

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(bFileOne, gbc);
		gbc.gridy++;
		add(bFileTwo, gbc);

		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(rbMethod1, gbc);
		gbc.gridy++;
		add(rbMethod2, gbc);
		gbc.gridy++;
		add(rbMethod3, gbc);
		gbc.gridy++;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(rbMethod4, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		
		add(timeFrom, gbc);
		gbc.gridx++;
		add(timeTo, gbc);
		
	}

	public int getMethod() {
		int result = 0;
		if (rbMethod1.isSelected()) {
			result = TCBH;
		}
		else if (rbMethod2.isSelected()) {
			result = ADPQH;
		}
		else if (rbMethod3.isSelected()) {
			result = ADPFH;
		} else if (rbMethod4.isSelected()) {
			result = FDMP;
		}
		return result;
	}

	public JButton getbStart() {
		return bStart;
	}

	public void setbStart(JButton bStart) {
		this.bStart = bStart;
	}

	public List<List> getThirtyDaysCallsInQuarter() {
		return thirtyDaysCallsInQuarter;
	}

	public List<List> getThirtyDaysCallsInHour() {
		return thirtyDaysCallsInHour;
	}

	private List<List> generateDays() {
		DaysGenerator daysGenerator = new DaysGenerator();
		return daysGenerator.generateDays(oneDayCallsInQuarter);
	}
	
	private void convertToCallsInHour() {
		double sum = 0;
		for (int k = 0; k < thirtyDaysCallsInQuarter.size(); k++) {
			for (int i = 0; i <= thirtyDaysCallsInQuarter.get(k).size() - 4; i += 4) {
				for (int j = i; j < i + 4; j++) {
					sum += oneDayCallsInQuarter.get(j);
				}
				oneDayCallsInHour.add(sum);
				sum = 0;
			}
			thirtyDaysCallsInHour.add(oneDayCallsInHour);
			oneDayCallsInHour = new ArrayList<>();
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		TextFileReader fileReader;
		if (src == bFileOne) {
			JFileChooser fileChooser = new JFileChooser("./resources/");
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				fileReader = new TextFileReader(file.getAbsolutePath());
				numberOfCallsInDay = fileReader.countFileLines();
				if (numberOfCallsInDay == 0) {
					JOptionPane.showMessageDialog(null,
							"Wybrano niepoprawny plik");
				}
				else {
					lFileOne.setText(file.getName());
					bFileTwo.setEnabled(true);
				}
			}
		}
		else if (src == bFileTwo) {
			JFileChooser fileChooser = new JFileChooser("./resources/");
			oneDayCallsInQuarter = new ArrayList<>();
			oneDayCallsInHour = new ArrayList<>();
			thirtyDaysCallsInQuarter = new ArrayList<>();
			thirtyDaysCallsInHour = new ArrayList<>();
			
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				fileReader = new TextFileReader(file.getAbsolutePath());
				oneDayCallsInQuarter = fileReader.numberOfCallsInEachQuarter(numberOfCallsInDay);

				if (oneDayCallsInQuarter == null) {
					JOptionPane.showMessageDialog(null,
							"Wybrano niepoprawny plik");
				}
				else {
					lFileTwo.setText(file.getName());
					bStart.setEnabled(true);
					thirtyDaysCallsInQuarter = generateDays();
					convertToCallsInHour();
					
				}

			}
		}
		
		if (rbMethod4.isSelected()) {
			timeFrom.setEnabled(true);
			timeTo.setEnabled(true);
		}
		if (rbMethod3.isSelected() || rbMethod2.isSelected() || rbMethod2.isSelected()) {
			timeFrom.setEnabled(false);
			timeTo.setEnabled(false);
		}
	}

	public List<Double> getOneDayCallsInQuarter() {
		return oneDayCallsInQuarter;
	}

	public JRadioButton getRbMethod4() {
		return rbMethod4;
	}

	public JTextField getTimeFrom() {
		return timeFrom;
	}

	public JTextField getTimeTo() {
		return timeTo;
	}


}
