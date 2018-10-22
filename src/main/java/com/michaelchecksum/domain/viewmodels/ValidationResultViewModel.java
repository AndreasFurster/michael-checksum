package com.michaelchecksum.domain.viewmodels;

import com.michaelchecksum.domain.HashType;
import javafx.beans.property.SimpleStringProperty;

public class ValidationResultViewModel {
    private SimpleStringProperty username;
    private SimpleStringProperty hashType;
    private SimpleStringProperty fileName;
    private SimpleStringProperty succeed;
    private boolean succeedBool;

    public ValidationResultViewModel(String username, HashType hashType, String fileName, boolean succeed) {
        this.username = new SimpleStringProperty(username);
        this.hashType = new SimpleStringProperty(hashType.toString());
        this.fileName = new SimpleStringProperty(fileName);
        this.succeed = new SimpleStringProperty(succeed ? "Yes" : "No");
        this.succeedBool = succeed;
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

    public SimpleStringProperty succeedProperty() {
        return succeed;
    }

    public boolean getSucceed(){
        return succeedBool;
    }

}
