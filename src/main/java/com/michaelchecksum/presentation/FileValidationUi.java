package com.michaelchecksum.presentation;

import com.michaelchecksum.domain.viewmodels.FileValidationViewModel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileValidationUi extends Stage {
    public void initializeComponent(FileValidationViewModel viewModel) {
        this.setTitle("New file found");

        Label title = new Label();
        title.setText("A new file is found. Would you like to compare it with an hash?");
        title.setFont(Font.font(25));

        HBox filenameBox = new HBox(10);
        filenameBox.getChildren().add(new Label("Filename: "));

        Label filename = new Label();
        filename.textProperty().bind(viewModel.filePath());
        filenameBox.getChildren().add(filename);

        javafx.scene.control.TextField hashTextField = new javafx.scene.control.TextField();
        viewModel.hashProperty().bind(hashTextField.textProperty());
        

        HBox actions = new HBox(10);
        javafx.scene.control.Button confirm = new javafx.scene.control.Button("Confirm");
        confirm.setOnMouseClicked(viewModel.onConfirmClick());

        actions.getChildren().add(confirm);

        javafx.scene.control.Button cancel = new javafx.scene.control.Button("Cancel");
        actions.getChildren().add(cancel);
        cancel.setOnMouseClicked(viewModel.onCancelClick());


        // Add label
        VBox root = new VBox(3);
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(title);
        root.getChildren().add(filenameBox);
        root.getChildren().add(hashTextField);
        root.getChildren().add(actions);
        this.setScene(new Scene(root, 900, 400));
    }
}
