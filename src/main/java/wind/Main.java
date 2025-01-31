package wind;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application class that handles the GUI initialization and startup.
 */
public class Main extends Application {
    private final Wind wind = new Wind();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setWind(wind); // inject the Duke instance
            fxmlLoader.<MainWindow>getController().sendWelcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
