package gui.components.buttons;

import gui.application;
import gui.components.buttons.ButtonConstants.ButtonProperties;
import gui.components.imageViewer.ImageViewer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by Sam Roberts on 8/5/2016.
 */
public class FileSelectorButton extends Button {

    private static final String DEFAULT_BTN_TEXT = "Select a file";

    public static Button getFileSelectorButton(ImageViewer imageViewer, TextField textField) {
		Button button = new Button(DEFAULT_BTN_TEXT);
		button.setPrefWidth(ButtonProperties.WIDTH.getValue());
		button.setPrefHeight(ButtonProperties.HEIGHT.getValue());
		FileEventHandler fileEventHandler = new FileEventHandler(imageViewer, textField);
		button.setOnAction(fileEventHandler);
		return  button;
	}

	private static class FileEventHandler implements EventHandler {
		private File file;
		private ImageViewer imageViewer;
		private TextField textField;

		public FileEventHandler(ImageViewer imageViewer, TextField textField) {
			this.imageViewer = imageViewer;
			this.textField = textField;
		}

		@Override
		public void handle(Event event) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Select a picture");
			FileChooser.ExtensionFilter extFilterALL = new FileChooser.ExtensionFilter("All Images", "*.*");
			FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
			FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
			//not supported
			//FileChooser.ExtensionFilter extFilterTIF = new FileChooser.ExtensionFilter("TIF files (*.tif)", "*.tif");
			FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif");
			FileChooser.ExtensionFilter extFilterBMP = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
			fileChooser.getExtensionFilters().addAll(extFilterALL,
					extFilterJPG,
					extFilterPNG,
					//extFilterTIF,
					extFilterGIF,
					extFilterBMP);
			this.file = fileChooser.showOpenDialog(new Stage());
			try {
				if (file != null) {
					textField.setText(file.getCanonicalPath());
					BufferedImage bufferedImage = ImageIO.read(file);
					if(bufferedImage != null) {
						Image image = SwingFXUtils.toFXImage(bufferedImage, null);
						imageViewer.setImage(image);
						application.setFile(file);
					} else {
						textField.setText("Unsupported File type :(");
						throw new IOException("Unsupported File type");
					}
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

	}
}
