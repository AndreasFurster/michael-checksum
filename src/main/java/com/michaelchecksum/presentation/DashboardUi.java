package com.michaelchecksum.presentation;


import com.michaelchecksum.domain.viewmodels.DashboardViewModel;
import com.michaelchecksum.domain.viewmodels.ValidationResultViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


public class DashboardUi extends Stage {
    private DashboardViewModel viewModel;
    private VBox root;

    public void initializeComponent(DashboardViewModel viewModel) {
        this.viewModel = viewModel;
        this.root = new VBox();

        this.setTitle("Dashboard");

        this.setToolbar();
        this.setStatistics();
        this.setTotals();

        this.root.setAlignment(Pos.TOP_CENTER);
        this.setScene(new Scene(this.root, 800, 500));
    }


    private void setToolbar(){
        Button settingsButton = new Button("Settings");

        settingsButton.setOnMouseClicked(this.viewModel.onSettingsClick());

        HBox toolbar = new HBox(3);
        toolbar.setAlignment(Pos.TOP_RIGHT);
        toolbar.getChildren().add(settingsButton);

        this.root.getChildren().add(toolbar);
    }

    private void setStatistics() {
        TableView<ValidationResultViewModel> tableView = new TableView<>();

        TableColumn usernameCol = new TableColumn("Username");
        usernameCol.setMinWidth(100);
        usernameCol.setCellValueFactory(new PropertyValueFactory<ValidationResultViewModel, String>("username"));

        TableColumn hashTypeCol = new TableColumn("Hash type");
        hashTypeCol.setMinWidth(100);
        hashTypeCol.setCellValueFactory(new PropertyValueFactory<ValidationResultViewModel, String>("hashType"));

        TableColumn fileNameCol = new TableColumn("File name");
        fileNameCol.setMinWidth(300);
        fileNameCol.setCellValueFactory(new PropertyValueFactory<ValidationResultViewModel, String>("fileName"));

        TableColumn succeedCol = new TableColumn("Succeed");
        succeedCol.setMinWidth(100);
        succeedCol.setCellValueFactory(new PropertyValueFactory<ValidationResultViewModel, String>("succeed"));

        tableView.setItems(viewModel.validationResults());
        tableView.getColumns().addAll(usernameCol, hashTypeCol, fileNameCol, succeedCol);

        this.root.getChildren().add(tableView);
    }

    private void setTotals(){
        Font statisticsFont = Font.font(20);

        // Create label for success values
        Label successLabel = new Label("Succeed: ");
        Label successValueLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty successAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(successAmountString, this.viewModel.successAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        successValueLabel.textProperty().bind(successAmountString);

        // Create label for failed values
        Label failedLabel = new Label("Failed: ");
        Label failedValueLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty failedAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(failedAmountString, this.viewModel.failedAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        failedValueLabel.textProperty().bind(failedAmountString);

        // Create label for total values
        Label totalLabel = new Label("Total: ");
        Label totalValueLabel = new Label();

        // Convert the DoubleProperty in viewModel to StringProperty
        StringProperty totalAmountString = new SimpleStringProperty();
        Bindings.bindBidirectional(totalAmountString, this.viewModel.totalAmountProperty(), new NumberStringConverter());

        // Bind new StringProperty to textProperty of label
        totalValueLabel.textProperty().bind(totalAmountString);

        // Set fonts
        successLabel.setFont(statisticsFont);
        successValueLabel.setFont(statisticsFont);
        failedLabel.setFont(statisticsFont);
        failedValueLabel.setFont(statisticsFont);
        totalLabel.setFont(statisticsFont);
        totalValueLabel.setFont(statisticsFont);

        // Set some padding
        successValueLabel.setPadding(new Insets(0, 60, 0, 0));
        failedValueLabel.setPadding(new Insets(0, 60, 0, 0));

        // Add labels to HBox
        HBox statistics = new HBox(3);
        statistics.setAlignment(Pos.TOP_CENTER);

        statistics.getChildren().add(successLabel);
        statistics.getChildren().add(successValueLabel);

        statistics.getChildren().add(failedLabel);
        statistics.getChildren().add(failedValueLabel);

        statistics.getChildren().add(totalLabel);
        statistics.getChildren().add(totalValueLabel);

        this.root.getChildren().add(statistics);
    }

}
