package com.michaelchecksum.presentation;

import com.michaelchecksum.domain.utils.FileValidationSucceedBooleanStringConverter;
import com.michaelchecksum.domain.viewmodels.FileValidationResultViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.BooleanStringConverter;

public class FileValidationResultUi extends Stage {
    public void initializeComponent(FileValidationResultViewModel viewModel) {
        VBox root = new VBox(3);

        HBox result = new HBox(10);
        result.getChildren().add(new Label("Succeed: "));

        Label resultLabel = new Label();
        StringProperty resultString = new SimpleStringProperty();
        Bindings.bindBidirectional(resultString, viewModel.successProperty(), new FileValidationSucceedBooleanStringConverter());
        resultLabel.textProperty().bind(resultString);

        result.getChildren().add(resultLabel);
        root.getChildren().add(result);

        Text errorLabel = new Text();
        errorLabel.textProperty().bind(viewModel.validationErrorMessage());
        errorLabel.setFill(Paint.valueOf("red"));
        root.getChildren().add(errorLabel);


        this.setScene(new Scene(root, 300, 250));

    }
}
