/**
 * Shmuel Feld 305469801 89281-01
 * Shani Shliselberg 313288839 89-281-02
 * Ahinoam Rosengarten 308425164 89-281-02
 * Amir Halfon 308559251 89-281-02
 */

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * errorMsgController - Controller for errorMsg class.
 */
public class errorMsgController {
    @FXML
    Label lblQuery;
    @FXML
    Label lblError;
    @FXML
    Label titleError;
    @FXML
    Button btnError;
    private String tag;
    private String msg;
    private String query;

    /**
     * constructor.
     *
     * @param tag from which tag the error is coming from.
     * @param msg message about the error.
     */
    public errorMsgController(String tag, String msg) {
        this.tag = tag;
        this.msg = msg;
        query = null;
    }

    /**
     * constructor
     *
     * @param tag   from which tag the error is coming from.
     * @param msg   message about the error.
     * @param query which query had an error.
     */
    public errorMsgController(String tag, String msg, String query) {
        this.tag = tag;
        this.msg = msg;
        this.query = query;
    }

    /**
     * initialize- initialize the window.
     */
    @FXML
    void initialize() {
        titleError.setText(this.tag);
        if (this.query != null) {
            lblQuery.setText("Script in line:" + this.query);
        }
        lblError.setText(this.msg);
        btnError.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Stage) (btnError.getScene().getWindow())).close();
            }
        });
        lblError.setPrefWidth(450);
        lblError.setWrapText(true);
        lblQuery.setPrefWidth(450);
        lblQuery.setWrapText(true);
    }
}
