/***
 *Adar Dorham 203537824 89-281-03
 *Omer Forma 304823230 89-281-03
 *Roi Peretz 203258041 89-281-04
 *Tomer Rahamim 203717475 89-281-05
 ***/

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ErrorMsg class - the window to show when some error occured.
 */
public class errorMsg implements Runnable{
    String tag;
    String msg;
    String query;

    public errorMsg(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
        this.query = null;
    }

    public errorMsg(String tag, String msg, String query) {
        this.tag = tag;
        this.msg = msg;
        this.query = query;
    }

    public void show() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("errorMsg.fxml"));
            errorMsgController controller;
            if(this.query == null) {
                controller = new errorMsgController(this.tag, this.msg);
            }
            else {
                controller = new errorMsgController(this.tag, this.msg, this.query);
            }
            loader.setController(controller);
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Error!");
        Scene scene = new Scene(root, 500, 350);
        scene.getStylesheets().add(getClass().getResource("errorMsg.css").toExternalForm());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    @Override
    public void run() {
        show();
    }
}
