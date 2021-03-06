/**
 * Shmuel Feld 305469801 89281-01
 * Shani Shliselberg 313288839 89-281-02
 * Ahinoam Rosengarten 308425164 89-281-02
 * Amir Halfon 308559251 89-281-02
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


/**
 * Controller class- responsible for the connection between the view and the model, and for the view logic.
 */
public class Controller {
    public VBox mainPane;
    private Stage primaryStage;

    @FXML
    ComboBox<String> tablesComboBox;
    @FXML
    RadioButton ddlFileRadioBtn;
    @FXML
    TextField ddlFileChooserTxt;
    @FXML
    Button ddlFileChooserBtn;
    @FXML
    RadioButton ddlQueryRadioBtn;
    @FXML
    TextArea ddlQueryTxt;
    @FXML
    Button ddlSendBtn;
    @FXML
    RadioButton dmlFileRadioBtn;
    @FXML
    TextField dmlFileChooserTxt;
    @FXML
    Button dmlFileChooserBtn;
    @FXML
    RadioButton dmlQueryRadioBtn;
    @FXML
    TextArea dmlQueryTxt;
    @FXML
    Button dmlSendBtn;
    @FXML
    TextArea ddlAnswerTxt;
    @FXML
    TextArea dmlAnswerTxt;
    @FXML
    Button sendButton;
    @FXML
    Tab simpleQueryTab;
    @FXML
    VBox columns;
    @FXML
    TextField whereClause;
    @FXML
    TextArea simpleQueryResult;

    /**
     * constructor.
     *
     * @param primaryStage primary stage
     */
    public Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * initialize- initialize the view.
     *
     * @throws Exception
     */
    @FXML
    void initialize() throws Exception {
        final ToggleGroup ddlTg = new ToggleGroup();
        ddlFileRadioBtn.setToggleGroup(ddlTg);
        ddlQueryRadioBtn.setToggleGroup(ddlTg);

        final ToggleGroup dmlTg = new ToggleGroup();
        dmlFileRadioBtn.setToggleGroup(dmlTg);
        dmlQueryRadioBtn.setToggleGroup(dmlTg);

        ddlAnswerTxt.setEditable(false);
        ddlFileChooserTxt.setEditable(false);
        dmlAnswerTxt.setEditable(false);
        dmlFileChooserTxt.setEditable(false);
        simpleQueryResult.setEditable(false);

        /**
         * Invoked when ddlFileRadioBtn is clicked.
         */
        ddlFileRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlTg.getSelectedToggle() == ddlFileRadioBtn) {
                    ddlQueryTxt.setDisable(true);
                    ddlFileChooserTxt.setDisable(false);
                    ddlFileChooserBtn.setDisable(false);
                } else {
                    ddlQueryTxt.setDisable(false);
                    ddlFileChooserTxt.setDisable(true);
                    ddlFileChooserBtn.setDisable(true);
                }
            }
        });

        /**
         * Invoked when ddlQueryRadioBtn is clicked.
         */
        ddlQueryRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlTg.getSelectedToggle() == ddlQueryRadioBtn) {
                    ddlQueryTxt.setDisable(false);
                    ddlFileChooserTxt.setDisable(true);
                    ddlFileChooserBtn.setDisable(true);
                } else {
                    ddlQueryTxt.setDisable(true);
                    ddlFileChooserTxt.setDisable(false);
                    ddlFileChooserBtn.setDisable(false);
                }
            }
        });

        /**
         * Invoked when dmlQueryRadioBtn is clicked.
         */
        dmlQueryRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dmlTg.getSelectedToggle() == dmlQueryRadioBtn) {
                    dmlQueryTxt.setDisable(false);
                    dmlFileChooserTxt.setDisable(true);
                    dmlFileChooserBtn.setDisable(true);
                } else {
                    dmlQueryTxt.setDisable(true);
                    dmlFileChooserTxt.setDisable(false);
                    dmlFileChooserBtn.setDisable(false);
                }
            }
        });

        /**
         * Invoked when dmlFileRadioBtn is clicked.
         */
        dmlFileRadioBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dmlTg.getSelectedToggle() == dmlFileRadioBtn) {
                    dmlQueryTxt.setDisable(true);
                    dmlFileChooserTxt.setDisable(false);
                    dmlFileChooserBtn.setDisable(false);
                } else {
                    dmlQueryTxt.setDisable(false);
                    dmlFileChooserTxt.setDisable(true);
                    dmlFileChooserBtn.setDisable(true);
                }
            }
        });

        ddlTg.selectToggle(ddlFileRadioBtn);
        dmlTg.selectToggle(dmlFileRadioBtn);

        if (ddlTg.getSelectedToggle() == ddlFileRadioBtn) {
            ddlQueryTxt.setDisable(true);
            ddlFileChooserTxt.setDisable(false);
            ddlFileChooserBtn.setDisable(false);
        } else {
            ddlQueryTxt.setDisable(false);
            ddlFileChooserTxt.setDisable(true);
            ddlFileChooserBtn.setDisable(true);
        }

        if (dmlTg.getSelectedToggle() == dmlFileRadioBtn) {
            dmlQueryTxt.setDisable(true);
            dmlFileChooserTxt.setDisable(false);
            dmlFileChooserBtn.setDisable(false);
        } else {
            dmlQueryTxt.setDisable(false);
            dmlFileChooserTxt.setDisable(true);
            dmlFileChooserBtn.setDisable(true);
        }

        /**
         * Invoked when ddlFileChooserBtn is clicked.
         */
        ddlFileChooserBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                ddlFileChooserTxt.setText(selectedFile.getAbsolutePath().toString());
            }
        });

        /**
         * Invoked when dmlFileChooserBtn is clicked.
         */
        dmlFileChooserBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);
                dmlFileChooserTxt.setText(selectedFile.getAbsolutePath().toString());
            }
        });

        /**
         * Invoked when ddlFileChooserTxt is clicked.
         */
        ddlFileChooserTxt.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        /**
         * Dropping over surface
         */
        ddlFileChooserTxt.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file : db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        ddlFileChooserTxt.setText(filePath);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        dmlFileChooserTxt.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        /**
         * Dropping over surface
         */
        dmlFileChooserTxt.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file : db.getFiles()) {
                        filePath = file.getAbsolutePath();
                        dmlFileChooserTxt.setText(filePath);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        final Model dbc = new Model();

        /**
         * handle ddl script
         */
        ddlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (ddlFileRadioBtn.isSelected()) {
                    if (ddlFileChooserTxt.getText().equals("")) {
                        new errorMsg("Error", "Please enter file").show();
                    } else {
                        executeScript(dbc, TYPE.DDL);
                    }
                } else {
                    if (ddlQueryTxt.getText().equals("")) {
                        new errorMsg("Error", "Please enter query").show();
                    } else {
                        try {
                            String result = dbc.sendDDLQuery(ddlQueryTxt.getText());
                            ddlAnswerTxt.setText(result);
                        } catch (Exception e) {
                            if (e.getMessage().contains("syntax")) {
                                new errorMsg("WRONG QUERY STRUCTURE", e.getMessage()).show();
                            } else {
                                new errorMsg("LOGICAL ERROR", e.getMessage()).show();
                            }
                            ddlAnswerTxt.setText("Failed");
                        }
                    }

                }

            }
        });

        /**
         * handle dml script
         */
        dmlSendBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (dmlFileRadioBtn.isSelected()) {
                    if (dmlFileChooserTxt.getText().equals("")) {
                        new errorMsg("Error", "Please enter file").show();
                    } else {
                        executeScript(dbc, TYPE.DML);
                    }
                } else {
                    if (dmlQueryTxt.getText().equals("")) {
                        new errorMsg("Error", "Please enter query").show();
                    } else {
                        try {
                            String answer = dbc.sendDMLQuery(dmlQueryTxt.getText());
                            dmlAnswerTxt.setText(answer);
                        } catch (Exception e) {
                            if (e.getMessage().contains("syntax")) {
                                new errorMsg("WRONG QUERY STRUCTURE", e.getMessage()).show();
                            } else {
                                new errorMsg("LOGICAL ERROR", e.getMessage()).show();
                            }
                            dmlAnswerTxt.setText("Failed");
                        }
                    }
                }
            }
        });

        /**
         * delete result when focus on query
         */
        ddlQueryTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (ddlAnswerTxt.getText().equals("Success")) {
                    ddlAnswerTxt.setText("");
                }
            }
        });

        /**
         * delete result when focus on query
         */
        dmlQueryTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (ddlAnswerTxt.getText().equals("Success")) {
                    dmlAnswerTxt.setText("");
                }
            }
        });

        /**
         * send button of simple query- execute the query and show result.
         */
        sendButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                simpleQueryResult.clear();
                String query;
                query = "select ";
                ObservableList<Node> columnsList = columns.getChildren();
                if (!columnsList.isEmpty()) {
                    for (Node column : columnsList) {
                        CheckBox checkBox = (CheckBox) column;
                        if (checkBox.isSelected()) {
                            query += checkBox.getText() + ",";
                        }
                    }
                }

                query = query.substring(0, query.length() - 1);

                query += (" FROM " + tablesComboBox.getValue());
                String where = whereClause.getCharacters().toString();
                if (!where.isEmpty()) {
                    query += (" WHERE " + where);
                }

                try {
                    String result = dbc.sendDMLQuery(query);
                    simpleQueryResult.setText(result);

                } catch (Exception e) {
                    if (e.getMessage().contains("syntax")) {
                        new errorMsg("WRONG QUERY STRUCTURE", e.getMessage()).show();
                    } else {
                        new errorMsg("LOGICAL ERROR", e.getMessage()).show();
                    }
                }
            }


        });

        /**
         * when choosing a table, add the columns names to the screen.
         */
        tablesComboBox.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        String oldValue, String newValue) {
                        columns.getChildren().clear();
                        try {
                            List<String> columnsNames = dbc.getColumnsNames(newValue);
                            for (String name : columnsNames) {
                                columns.getChildren().add(new CheckBox(name));
                            }
                        } catch (Exception e) {
                            
                        }
                    }
                });

        /**
         * filling the combo box with tables' names.
         */
        simpleQueryTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                tablesComboBox.getItems().clear();
                try {
                    String result = dbc.sendDDLQuery("SHOW TABLES");
                    //putting the table names in array.
                    String[] resultArr = result.split("\n");
                    for (int i = 1; i < resultArr.length; i++) {
                        tablesComboBox.getItems().addAll(resultArr[i]);
                    }

                } catch (Exception e) {
                    if (e.getMessage().contains("syntax")) {
                        new errorMsg("WRONG QUERY STRUCTURE", e.getMessage()).show();
                    } else {
                        new errorMsg("LOGICAL ERROR", e.getMessage()).show();
                    }
                }


            }
        });
    }

    /**
     * execute script file
     */
    public void executeScript(Model dbc, TYPE type) {
        try {
            if (!ddlFileChooserTxt.getText().isEmpty() || !dmlFileChooserTxt.getText().isEmpty()) {
                File script = null;
                if (type == TYPE.DDL) {
                    script = new File(ddlFileChooserTxt.getText());
                } else {
                    script = new File(dmlFileChooserTxt.getText());
                }
                List<String> commands = dbc.readScriptFromFile(script);
                int size = commands.size();
                for (int i = 0; i < size; i++) {
                    try {
                        if (type == TYPE.DDL) {
                            dbc.sendDDLQuery(commands.get(i));
                        } else {
                            String answer = dbc.sendDMLQuery(commands.get(i));
                            dmlAnswerTxt.appendText(answer);
                        }
                    } catch (Exception e) {
                        if (e.getMessage().contains("syntax")) {
                            new errorMsg("WRONG QUERY STRUCTURE", e.getMessage(), commands.get(i)).show();
                        } else {
                            new errorMsg("LOGICAL ERROR", e.getMessage(), commands.get(i)).show();
                        }
                        return;
                    }
                }
                if (type == TYPE.DDL) {
                    ddlAnswerTxt.setText("Success");
                }
            }
        } catch (Exception e) {
            if (type == TYPE.DDL) {
                ddlAnswerTxt.setText("Failed");
            } else {
                dmlAnswerTxt.setText("Failed");
            }
        }
    }

    enum TYPE {DDL, DML}

}

