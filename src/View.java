/**
 * Shmuel Feld 305469801 89281-01
 * Shani Shliselberg 313288839 89-281-02
 * Ahinoam Rosengarten 308425164 89-281-02
 * Amir Halfon 308559251 89-281-02
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * View class - The class of the main screen window.
 */
public class View extends Application {
    /**
     * main function.
     *
     * @param args program arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start- implementation of Application abstract function.
     *
     * @param primaryStage primary stage.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Screen.fxml"));
        Controller controller = new Controller(primaryStage);
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