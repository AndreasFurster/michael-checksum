package com.michaelchecksum.presentation;


import com.michaelchecksum.domain.viewmodels.DashboardViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class Dashboard extends Stage {
    private DashboardViewModel viewModel;
    private Scene scene;
    private VBox root;
    public void initializeComponent(DashboardViewModel viewModel) {
        this.viewModel = viewModel;
        this.root = new VBox();

        this.setTitle("Dashboard");

        this.setToolbar();
        this.setStatistics();


        this.root.setAlignment(Pos.TOP_CENTER);
        this.setScene(new Scene(this.root, 300, 250));

    }

    private void setToolbar(){
        Button settingsButton = new Button("Settings");

        settingsButton.setOnMouseClicked(this.viewModel.onSettingsClick());

        HBox toolbar = new HBox(3);
        toolbar.setAlignment(Pos.TOP_RIGHT);
        toolbar.getChildren().add(settingsButton);

        this.root.getChildren().add(toolbar);
    }

    private void setStatistics(){
        // Create label for success values
        Label successLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty successAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(successAmountString, this.viewModel.successAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        successLabel.textProperty().bind(successAmountString);

        // Create label for failed values
        Label failedLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty failedAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(failedAmountString, this.viewModel.failedAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        failedLabel.textProperty().bind(failedAmountString);

        // Create label for total values
        Label totalLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty totalAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(totalAmountString, this.viewModel.totalAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        totalLabel.textProperty().bind(totalAmountString);

        // Add label
        HBox statistics = new HBox(3);

        statistics.getChildren().add(successLabel);
        statistics.getChildren().add(failedLabel);
        statistics.getChildren().add(totalLabel);

        this.root.getChildren().add(statistics);
    }

}
