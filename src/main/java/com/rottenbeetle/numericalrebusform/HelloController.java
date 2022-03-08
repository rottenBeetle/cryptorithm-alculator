package com.rottenbeetle.numericalrebusform;

import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox vBox;

    @FXML
    private AnchorPane anchorePane1;

    @FXML
    private TextField inputField;

    @FXML
    private AnchorPane anchorePane2;

    @FXML
    private Text label1;

    @FXML
    private Button executeButton;

    @FXML
    private Text label2;

    @FXML
    private TextField outputField;

    @FXML
    private Text label3;

    @FXML
    private Text label4;

    @FXML
    private Text label5;

    @FXML
    private Button exitButton;

//    <ProgressIndicator layoutX="130.0" layoutY="296.0" progress="0.0" />
//    @FXML
//    private ProgressIndicator progress;

    @FXML
    void initialize() {
            executeButton.setOnAction(event -> {
                outputField.setText("");
                try{
                    String query = inputField.getText();
                    query = query.trim();
                    char[] chars = query.toCharArray();
                    for (char test : chars) {
                        if (test == ' '){
                            throw new IncorrectInputException("Incorrect input");
                        }
                        if(Character.isLowerCase(test)){
                            throw new IncorrectInputException("Incorrect input");
                        }
                        if (chars.length >= 50){
                            throw new IncorrectInputException("Incorrect input");
                        }
                    }

                    //Output и проверка на пустое значение
                    if (NumericalRebus.solve(query) != ""){
                        outputField.setText(NumericalRebus.solve(query));
                    }else{
                        outputField.setText("No solution");
                    }
                }catch (ArrayIndexOutOfBoundsException | NoSuchElementException | IncorrectInputException e){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText("Проверьте правильность ввода");
                    alert.setContentText("В строке не должно быть пробелов, " +
                            "буквы должны быть заглавными, " +
                            "cтрока не должна превышать 50 символов");
                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == ButtonType.OK) {
                            System.out.println("Pressed OK.");
                        }
                    });
                }
        });
        exitButton.setOnAction(event ->{
            Platform.exit();
            System.out.println("Stage is closing");
        });


    }
}
