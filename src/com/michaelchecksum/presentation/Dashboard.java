package com.michaelchecksum.presentation;


import com.michaelchecksum.domain.viewmodels.DashboardViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class Dashboard extends Stage {
    private DashboardViewModel viewModel;

    public void initializeComponent(DashboardViewModel viewModel){
        this.setTitle("Dashboard");

        // Create label for success values
        Label successLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty successAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(successAmountString, viewModel.successAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        successLabel.textProperty().bind(successAmountString);

        // Create label for failed values
        Label failedLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty failedAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(failedAmountString, viewModel.failedAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        failedLabel.textProperty().bind(failedAmountString);

        // Create label for total values
        Label totalLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty totalAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(totalAmountString, viewModel.totalAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        totalLabel.textProperty().bind(totalAmountString);

        // Add label
        HBox root = new HBox(3);

        root.getChildren().add(successLabel);
        root.getChildren().add(failedLabel);
        root.getChildren().add(totalLabel);

        this.setScene(new Scene(root, 300, 250));
    }

}
