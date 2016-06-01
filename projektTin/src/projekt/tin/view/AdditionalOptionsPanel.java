package projekt.tin.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class AdditionalOptionsPanel extends JPanel implements ActionListener {

	private JCheckBox checkboxChart;

	public AdditionalOptionsPanel() {
		super(new GridBagLayout());
		createAndShowComponents();
	}

	public void createAndShowComponents(){
		setPreferredSize(new Dimension(250, 300));
		GridBagConstraints gbc = new GridBagConstraints();

		setBorder(BorderFactory.createTitledBorder("Opcje dodatkowe"));

		checkboxChart = new JCheckBox("Generuj wykres", true);

		/*
		 * definiowanie uk³adu komponentów
		 */

		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(checkboxChart, gbc);
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	}
	@Override
	public void actionPerformed(ActionEvent evt) {

	}

	public JCheckBox getCheckboxChart() {
		return checkboxChart;
	}

}
