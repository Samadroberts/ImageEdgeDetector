package gui.components.imageViewer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Sam Roberts on 8/5/2016.
 */
public class ImageViewer extends ImageView {

	private Image image;

	public ImageViewer() {
		this.setPreserveRatio(true);
	}
}
