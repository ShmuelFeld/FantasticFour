/**
 * Shmuel Feld 305469801 89281-01
 * Shani Shliselberg 313288839 89-281-02
 * Ahinoam Rosengarten 308425164 89-281-02
 * Amir Halfon 308559251 89-281-02
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ErrorMsg class - the window to show when some error occurs.
 */
public class errorMsg implements Runnable {
    String tag;
    String msg;
    String query;

    /**
     * constructor.
     *
     * @param tag from which tag the error is coming from.
     * @param msg message about the error.
     */
    public errorMsg(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
        this.query = null;
    }

    /**
     * constructor.
     *
     * @param tag   from which tag the error is coming from.
     * @param msg   message about the error.
     * @param query which query had an error.
     */
    public errorMsg(String tag, String msg, String query) {
        this.tag = tag;
        this.msg = msg;
        this.query = query;
    }

    /**
     * show- show the window.
     */
    public void show() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("errorMsg.fxml"));
            errorMsgController controller;
            if (this.query == null) {
                controller = new errorMsgController(this.tag, this.msg);
            } else {
                controller = new errorMsgController(this.tag, this.msg, this.query);
            }
            loader.setController(controller);
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Error!");
        Scene scene = new Scene(root, 500, 350);
        scene.getStylesheets().add(getClass().getResource("errorMSG.css").toExternalForm());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @Override
    /**
     * run- implementation of Runnable interface.
     */
    public void run() {
        show();
    }
}
