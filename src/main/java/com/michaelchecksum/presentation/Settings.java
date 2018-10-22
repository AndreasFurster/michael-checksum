package com.michaelchecksum.presentation;

import com.michaelchecksum.domain.viewmodels.SettingsViewModel;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class Settings extends Stage {
    private VBox root = new VBox(30);
    private SettingsViewModel viewModel;
    private Scene scene;
    public void initializeComponent(SettingsViewModel settingsViewModel) {
        this.viewModel = settingsViewModel;

        this.setTitle("Settings");
        this.setList();
        this.setAddRemove();

        this.root.setAlignment(Pos.TOP_CENTER);

        this.setScene(new Scene(this.root, 300, 250));

    }

    public void setList(){
        ListView<String> listView = new ListView<>();
        listView.setMaxHeight(150);
        listView.setMinWidth(300);
        listView.setMaxWidth(300);
        listView.setItems(this.viewModel.getPaths());

        HBox listOfPaths = new HBox();
        listOfPaths.getChildren().add(listView);

        this.root.getChildren().add(listOfPaths);
    }

    public void setAddRemove(){
        //file name display
        Text file = new Text();
        file.prefWidth(200);

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

            file.setText(path);
        });

        //configure explorer button box
        HBox fileExplorerBox = new HBox();
        fileExplorer.setAlignment(Pos.TOP_CENTER);
        fileExplorerBox.getChildren().add(fileExplorer);

        fileExplorer.setPrefWidth(100);


        Button buttonAdd =  new Button("Add");
        buttonAdd.setPrefWidth(50);

        Button buttonRemove = new Button("Del");
        buttonRemove.setPrefWidth(50);

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
