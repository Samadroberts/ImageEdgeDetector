package gui.components.fileSelection;

import gui.components.buttons.FileSelectorButton;
import gui.components.imageViewer.ImageViewer;
import gui.components.textField.ReadOnlyTextField;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Sam Roberts on 8/5/2016.
 */
public class FileSelectionView {
    public static HBox  getFileSelectionView(ImageViewer imageViewer) {
    	HBox hBox = new HBox();
		hBox.setSpacing(15);
		hBox.setPadding(new Insets(5,5,5,5));
        ReadOnlyTextField readOnlyTextField = new ReadOnlyTextField();
		hBox.setHgrow(readOnlyTextField, Priority.ALWAYS);
		Button button = FileSelectorButton.getFileSelectorButton(imageViewer, readOnlyTextField);
		hBox.getChildren().addAll(button, readOnlyTextField);
		return hBox;
    }
}
