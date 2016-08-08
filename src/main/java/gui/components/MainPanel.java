package gui.components;

import gui.components.buttons.DetectEdgesButton;
import gui.components.fileSelection.FileSelectionView;
import gui.components.imageViewer.ImageViewer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Sam Roberts on 8/5/2016.
 */
public class MainPanel extends VBox {
    private static double DEFAULT_HEIGHT = 900;
    private static double DEFAULT_WIDTH = 1600;

    private FileSelectionView fileSelectionView;
	private ImageViewer imageViewer = new ImageViewer();
	ScrollPane scrollPane = new ScrollPane();

    public MainPanel(double height, double width, Stage stage) {
    	this.setPrefWidth(DEFAULT_WIDTH);
    	this.setPrefHeight(DEFAULT_HEIGHT);
		HBox imagehbox = new HBox();
		imagehbox.setPrefWidth(width-50);
		imagehbox.setPrefHeight(height-200);
		imagehbox.setAlignment(Pos.CENTER);
		imagehbox.getChildren().add(imageViewer);
		imagehbox.setHgrow(imageViewer, Priority.ALWAYS);
		scrollPane.setContent(imagehbox);
		scrollPane.setPadding(new Insets(5,5,5,5));
		HBox  hBox = new HBox();
		hBox.setPadding(new Insets(5,5,5,5));
		hBox.getChildren().add(new DetectEdgesButton(imageViewer));
		this.getChildren().addAll(scrollPane,FileSelectionView.getFileSelectionView(imageViewer), hBox);
    }
    public MainPanel(Stage stage) {
    	this(DEFAULT_HEIGHT, DEFAULT_WIDTH, stage);
    }
 }
