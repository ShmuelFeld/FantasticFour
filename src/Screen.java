/***
 *Adar Dorham 203537824 89-281-03
 *Omer Forma 304823230 89-281-03
 *Roi Peretz 203258041 89-281-04
 *Tomer Rahamim 203717475 89-281-05
 ***/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * Screen class - The class of the main screen window.
 */

public class Screen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen.fxml"));
        ScreenController controller = new ScreenController(primaryStage);
        loader.setController(controller);
        Parent root = (Parent) loader.load();
        //The Window title.
        primaryStage.setTitle("DB-Project");
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root, 700, 350);
        scene.getStylesheets().add(getClass().getResource("Screen.css").toExternalForm());
        primaryStage.setScene(scene);
        //Window size is fixed.
        primaryStage.setResizable(false);

        primaryStage.show();
    }
}