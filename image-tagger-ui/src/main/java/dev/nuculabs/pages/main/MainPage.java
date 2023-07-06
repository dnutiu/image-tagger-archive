package dev.nuculabs.pages.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPage.class.getResource("/pages/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 640);
        stage.setTitle("Image Tagger Utility");
        stage.setScene(scene);
        stage.setMinHeight(640);
        stage.setMinWidth(640);
        stage.show();
    }
}
