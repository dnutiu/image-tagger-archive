package dev.nuculabs.pages.main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainPageController {
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    public MainPageController() {
        logger.info("MainPageController created");
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
