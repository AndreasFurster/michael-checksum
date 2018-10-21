package com.michaelchecksum.presentation;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class Dashboard extends Stage {
    public void initializeComponent(){
        this.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> System.out.println("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        this.setScene(new Scene(root, 300, 250));
    }

}
