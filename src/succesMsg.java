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
 *
 */
public class succesMsg implements Runnable{
    String tag;
    String msg;

    public succesMsg(String tag, String msg) {
        this.msg = msg;
        this.tag = tag;
    }

    public void show() {
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("succesMsg.fxml"));
            succesMsgController controller = new succesMsgController(this.tag, this.msg);
            loader.setController(controller);
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Success");
        Scene scene = new Scene(root, 250, 150);
        scene.getStylesheets().add(getClass().getResource("succesMsg.css").toExternalForm());
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
