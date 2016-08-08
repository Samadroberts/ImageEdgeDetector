package gui.components.buttons.ButtonConstants;

/**
 * Created by Sam Roberts on 8/8/2016.
 */
public enum ButtonProperties {
	WIDTH(150),
	HEIGHT(20),
	;

	private double value;
	ButtonProperties(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}
}
