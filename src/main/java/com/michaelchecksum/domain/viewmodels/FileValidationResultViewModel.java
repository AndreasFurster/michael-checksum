package com.michaelchecksum.domain.viewmodels;

import com.michaelchecksum.domain.HashType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.converter.BooleanStringConverter;

public class FileValidationResultViewModel {
    private SimpleStringProperty validationErrorMessage = new SimpleStringProperty();

    private SimpleStringProperty username = new SimpleStringProperty();
    private SimpleStringProperty hashType = new SimpleStringProperty();
    private SimpleStringProperty fileName = new SimpleStringProperty();

    private SimpleBooleanProperty success = new SimpleBooleanProperty();
    public FileValidationResultViewModel(){}

    public FileValidationResultViewModel(String username, HashType hashType, String fileName, boolean success) {
        this.username.set(username);
        this.hashType.set(hashType.toString());
        this.fileName.set(fileName);
        this.success.set(success);
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public SimpleStringProperty hashTypeProperty() {
        return hashType;
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public SimpleBooleanProperty successProperty(){ return success; }

    public void setValidationErrorMessage(String validationErrorMessage) {
        this.validationErrorMessage.set(validationErrorMessage);
    }

    public void setSuccess(boolean success) {
        this.success.set(success);
    }

    public boolean getSucces(){
        return this.success.get();
    }

    public SimpleStringProperty validationErrorMessage() {
        return validationErrorMessage;
    }
}

