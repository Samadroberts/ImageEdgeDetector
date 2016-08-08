package gui.components.textField;

import javafx.scene.control.TextField;

/**
 * Created by Sam Roberts on 8/5/2016.
 */
public class ReadOnlyTextField extends TextField {

    public ReadOnlyTextField() {
        this.setEditable(false);
    }
}
