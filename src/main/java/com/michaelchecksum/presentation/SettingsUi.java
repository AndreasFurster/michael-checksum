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

    //adding
    private HBox addBox;
    private Button directoryChoose;
    private Button add;
    private Text folderToAdd;

    //removing
    private HBox removeBox;
    private Button remove;
    private Text folderToRemove;

    //message
    private HBox messageBox;
    private Text message;

    //list
    private HBox listBox;

    //username
    private HBox usernameBox;
    private Text username;
    private TextField usernameValue;
    private Button usernameSet;


    public void initializeComponent(SettingsViewModel settingsViewModel) {

        this.viewModel = settingsViewModel;

        this.setTitle("Settings");
        this.setList();
        this.setAddBox();
        this.setRemoveBox();
        this.setUsernameBox();

        this.root = new VBox(30);
        this.root.setAlignment(Pos.TOP_CENTER);

        this.root.getChildren().add(this.messageBox);
        this.root.getChildren().add(this.listBox);
        this.root.getChildren().add(this.removeBox);
        this.root.getChildren().add(this.addBox);
        this.root.getChildren().add(this.usernameBox);

        this.setScene(new Scene(this.root, 300, 250));
    }

    public void setList(){
        //message for restart
        this.message = new Text();
        this.message.setFill(Paint.valueOf("red"));
        this.message.setText("Don't forget to restart after configuring all the folders.");

        this.messageBox = new HBox();
        this.messageBox.setAlignment(Pos.TOP_CENTER);
        this.messageBox.getChildren().add(message);

        ListView<String> listView = new ListView<>();
        listView.setOnMouseClicked(Event -> {
            //System.out.println(Event);
            System.out.println();
            if(this.remove != null && this.folderToRemove != null){
                String text  = listView.getSelectionModel().getSelectedItem();
                this.remove.setId(text);
                this.folderToRemove.setText(text);
            }


        });

        listView.itemsProperty().bind(this.viewModel.getListProperty());
        listView.setMaxHeight(150);
        listView.setMinWidth(300);
        listView.setMaxWidth(300);

        this.listBox = new HBox(10);
        this.listBox.getChildren().add(listView);
    }

    public void setAddBox(){
        //file name selected new
        this.folderToAdd = new Text();
        folderToAdd.prefWidth(150);

        this.add = new Button("Add");
        this.add.prefWidth(75);
        this.add.setOnMouseClicked(this.viewModel.onPathAdd());

        this.directoryChoose = new Button("Browser");
        this.directoryChoose.prefWidth(75);
        this.directoryChoose.setOnMouseClicked(event -> {
            DirectoryChooser chooser = new DirectoryChooser();
            File showDialog = chooser.showDialog(this);
            String path = showDialog.toString();
            this.folderToAdd.setText(path);
            if(this.folderToAdd != null && this.add != null) {
                this.add.setId(this.folderToAdd.getText());
            }
        });
        this.addBox = new HBox(10 );
        this.addBox.getChildren().add(this.folderToAdd);
        this.addBox.getChildren().add(this.directoryChoose);
        this.addBox.getChildren().add(this.add);
        this.addBox.setAlignment(Pos.TOP_CENTER);
    }

    public void setRemoveBox(){
        //file name selected removal
        this.folderToRemove = new Text();
        this.folderToRemove.prefWidth(200);

        this.remove = new Button("Remove");
        this.remove.prefWidth(100);
        this.remove.setOnMouseClicked(this.viewModel.onPathRemove());

        this.removeBox = new HBox(10);
        this.removeBox.getChildren().add(this.folderToRemove);
        this.removeBox.getChildren().add(this.remove);
        this.removeBox.setAlignment(Pos.TOP_CENTER);
    }

    public void setUsernameBox(){

        this.username = new Text();
        this.username.setText("Username:");
        this.username.prefWidth(50);

        this.usernameValue = new TextField();
        this.usernameValue.setPrefWidth(150);
        this.usernameValue.textProperty().bindBidirectional(viewModel.getUsername());
        
        this.usernameSet = new Button("Set");
        this.usernameSet.setOnMouseClicked(this.viewModel.onSetUsername());

        this.usernameBox = new HBox(10);
        this.usernameBox.getChildren().add(this.username);
        this.usernameBox.getChildren().add(this.usernameValue);
        this.usernameBox.getChildren().add(this.usernameSet);

    }
}
