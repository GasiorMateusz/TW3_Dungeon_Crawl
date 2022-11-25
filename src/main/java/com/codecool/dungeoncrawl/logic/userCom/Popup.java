package com.codecool.dungeoncrawl.logic.userCom;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Popup {


    public static void display() {
        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("GAME OVER");


        Label label1 = new Label("YOU DIED!");


        Button button1 = new Button("TRY AGAIN");


        button1.setOnAction(e -> popupwindow.close());


        VBox layout = new VBox(10);


        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 150);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}


