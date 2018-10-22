package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.File;


public class FileValidationViewModel {

    private File file;
    private StringProperty filePath = new SimpleStringProperty();
    private StringProperty hash = new SimpleStringProperty();

    private EventHandler<MouseEvent> onConfirmClick;
    private EventHandler<MouseEvent> onCancelClick;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        this.setFilePath(file.getPath());
    }

    public String getHash() {
        return hash.get();
    }

    private void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }

    public void setOnConfirmClickEventHandler(EventHandler<MouseEvent> eventHandler) {
        this.onConfirmClick = eventHandler;
    }

    public void setOnCancelClickEventHandler(EventHandler<MouseEvent> eventHandler) {
        this.onCancelClick = eventHandler;
    }


    public StringProperty filePath() {
        return filePath;
    }

    public StringProperty hashProperty() {
        return hash;
    }

    public EventHandler<MouseEvent> onConfirmClick() {
        return this.onConfirmClick;
    }

    public EventHandler<MouseEvent> onCancelClick() {
        return this.onCancelClick;
    }
}
