package projekt.tin.view;

import javax.swing.BoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoundedChangeListener implements ChangeListener {
	private JLabel simulationSpeedLabel;
	
	public BoundedChangeListener(JLabel label) {
		this.simulationSpeedLabel = label;
	}
	
	@Override
	public void stateChanged(ChangeEvent changeEvent) {
		 Object source = changeEvent.getSource();
		    if (source instanceof BoundedRangeModel) {
		      BoundedRangeModel aModel = (BoundedRangeModel) source;
		      if (!aModel.getValueIsAdjusting()) {
		      }
		    } else if (source instanceof JSlider) {
		      JSlider theJSlider = (JSlider) source;
		      if (!theJSlider.getValueIsAdjusting()) {
		        simulationSpeedLabel.setText(Integer.toString(theJSlider.getValue()));
		      }
		    }
		  }
}
