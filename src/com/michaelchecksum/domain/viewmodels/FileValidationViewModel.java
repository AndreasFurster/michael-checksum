package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;

public class FileValidationViewModel {
    private StringProperty filePath = new SimpleStringProperty();

    private EventHandler onConfirmClick;

    public void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }

    public void SetOnConfirmClick(EventHandler event){
        this.onConfirmClick = event;
    }



    public StringProperty filePath() {
        return filePath;
    }

}
