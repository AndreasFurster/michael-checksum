package com.michaelchecksum.presentation;

import com.michaelchecksum.domain.viewmodels.FileValidationResultViewModel;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FileValidationResult extends Stage {
    public void initializeComponent(FileValidationResultViewModel viewModel) {


        HBox root = new HBox(3);

        Label resultLabel = new Label();
        resultLabel.textProperty().bind(viewModel.resultMessage());
        root.getChildren().add(resultLabel);

        this.setScene(new Scene(root, 300, 250));

    }
}
