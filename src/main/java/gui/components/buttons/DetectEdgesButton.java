package gui.components.buttons;

import gaussianfilter.ImageUtils;
import gui.application;
import gui.components.buttons.ButtonConstants.ButtonProperties;
import gui.components.imageViewer.ImageViewer;
import javafx.scene.control.Button;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sam Roberts on 8/7/2016.
 */
public class DetectEdgesButton extends Button {
	private ImageViewer imageViewer;
	public  DetectEdgesButton(ImageViewer imageViewer) {
		this.imageViewer = imageViewer;
		this.setText("Detect edges");
		this.setPrefHeight(ButtonProperties.HEIGHT.getValue());
		this.setPrefWidth(ButtonProperties.WIDTH.getValue());
		this.setOnAction((event) -> {
			File file = application.getFile();
			if(file == null) {
				return;
			}
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				imageViewer.setImage(ImageUtils.rgbDetectEdges(bufferedImage));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		});
	}


}
