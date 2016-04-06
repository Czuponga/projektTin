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

public class AdditionalOptionsPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JCheckBox checkboxChart, checkboxSimulation;
	private JSlider sliderSimulationSpeed;

	public AdditionalOptionsPanel() {
		super(new GridBagLayout());
		setPreferredSize(new Dimension(250, 300));
		GridBagConstraints gbc = new GridBagConstraints();

		setBorder(BorderFactory.createTitledBorder("Opcje dodatkowe"));

		checkboxChart = new JCheckBox("Generuj wykres", true);
		checkboxSimulation = new JCheckBox("Symulacja czasu", false);

		sliderSimulationSpeed = new JSlider();
		sliderSimulationSpeed.setEnabled(false);

		checkboxSimulation.addActionListener(this);

		/*
		 * definiowanie uk³adu komponentów
		 */

		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(checkboxChart, gbc);
		gbc.gridy++;
		add(checkboxSimulation, gbc);
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		add(new JLabel("Szybkoœæ symulacji"), gbc);
		gbc.gridy++;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(sliderSimulationSpeed, gbc);
		;

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (checkboxSimulation.isSelected()) {
			sliderSimulationSpeed.setEnabled(true);
		}
		else {
			sliderSimulationSpeed.setEnabled(false);
		}

	}

}
