package com.michaelchecksum.presentation;

import com.michaelchecksum.domain.viewmodels.SettingsViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class SettingsUi extends Stage {
    private VBox root;
    private SettingsViewModel viewModel;

    private Button buttonAdd;
    private Button buttonRemove;
    private Text file;

    public void initializeComponent(SettingsViewModel settingsViewModel) {
        this.root = new VBox(30);
        this.viewModel = settingsViewModel;

        this.setTitle("Settings");
        this.setList();
        this.setAddRemove();

        this.root.setAlignment(Pos.TOP_CENTER);

        this.setScene(new Scene(this.root, 300, 250));
    }

    public void setList(){
        //message for restart
        Text message = new Text();
        message.setFill(Paint.valueOf("red"));
        message.setText("Don't forget to restart after configuring all the folders.");

        HBox messageBox = new HBox();
        messageBox.setAlignment(Pos.TOP_CENTER);
        messageBox.getChildren().add(message);

        ListView<String> listView = new ListView<>();
        listView.setOnMouseClicked(Event -> {
            //System.out.println(Event);
            System.out.println();
            if(this.buttonRemove != null && this.file != null){
                String text  = listView.getSelectionModel().getSelectedItem();
                this.buttonRemove.setId(text);
                this.file.setText(text);
            }


        });

        listView.itemsProperty().bind(this.viewModel.getListProperty());
        listView.setMaxHeight(150);
        listView.setMinWidth(300);
        listView.setMaxWidth(300);

        HBox listOfPaths = new HBox();
        listOfPaths.getChildren().add(listView);

        this.root.getChildren().add(messageBox);
        this.root.getChildren().add(listOfPaths);

    }

    public void setAddRemove(){
        //file name display
        Text file = new Text();
        file.prefWidth(200);

        this.file = file;

        //configure filebox
        HBox fileBox = new HBox(5);
        fileBox.getChildren().add(file);
        fileBox.setAlignment(Pos.TOP_CENTER);


        //buttons display
        Button fileExplorer = new Button("Browser");
        fileExplorer.setOnMouseClicked(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File showDialog = chooser.showDialog(this);
            String path = showDialog.toString();
            this.file.setText(path);
            if(this.buttonAdd != null) {
                this.buttonAdd.setId(this.file.getText());
            }
        });

        //configure explorer button box
        HBox fileExplorerBox = new HBox();
        fileExplorerBox.setAlignment(Pos.TOP_CENTER);
        fileExplorerBox.getChildren().add(fileExplorer);

        //button add
        Button buttonAdd =  new Button("Add");
        buttonAdd.setPrefWidth(50);
        this.buttonAdd = buttonAdd;

        //button remove
        Button buttonRemove = new Button("Del");
        buttonRemove.setPrefWidth(50);
        this.buttonRemove = buttonRemove;

        fileExplorer.setPrefWidth(100);

        this.buttonAdd.setOnMouseClicked(this.viewModel.onPathAdd());
        this.buttonRemove.setOnMouseClicked(this.viewModel.onPathRemove());

        //add everything to the box
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.TOP_CENTER);
        hbox.getChildren().add(buttonAdd);
        hbox.getChildren().add(buttonRemove);

        //add to root
        this.root.getChildren().add(fileBox);
        this.root.getChildren().add(fileExplorerBox);
        this.root.getChildren().add(hbox);

    }
}
