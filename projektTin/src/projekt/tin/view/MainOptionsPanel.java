package projekt.tin.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainOptionsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel lFileOne, lFileTwo;
	private JButton bFileOne, bFileTwo;
	private JRadioButton rbMethod1, rbMethod2, rbMethod3, rbMethod4;
	private ButtonGroup bgMethodChooser;

	public MainOptionsPanel() {
		super(new GridBagLayout());
		setVisible(true);
		setPreferredSize(new Dimension(250, 300));
		GridBagConstraints gbc = new GridBagConstraints();

		setBorder(BorderFactory.createTitledBorder("Opcje g�owne"));

		lFileOne = new JLabel("Wybierz pierwszy plik");
		lFileTwo = new JLabel("Wybierz drugi plik");
		bFileOne = new JButton("...");
		bFileTwo = new JButton("...");

		bFileTwo.setEnabled(false);

		rbMethod1 = new JRadioButton("Metoda 1", true);
		rbMethod2 = new JRadioButton("Metoda 2");
		rbMethod3 = new JRadioButton("Metoda 3");
		rbMethod4 = new JRadioButton("Metoda 4");

		bgMethodChooser = new ButtonGroup();
		bgMethodChooser.add(rbMethod1);
		bgMethodChooser.add(rbMethod2);
		bgMethodChooser.add(rbMethod3);
		bgMethodChooser.add(rbMethod4);

		bFileOne.addActionListener(this);
		bFileTwo.addActionListener(this);

		/*
		 * /* Definiowanie uk�adu komponent�w
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
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		if (src == bFileOne) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				lFileOne.setText(file.getName());
				bFileTwo.setEnabled(true);
			}
		}
		else if (src == bFileTwo) {
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				lFileTwo.setText(file.getName());
			}
		}
	}
}