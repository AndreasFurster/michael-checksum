package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FileValidationResultViewModel {
    private boolean validationErrorPresent = false;
    private String validationErrorMessage;
    private boolean success = false;
    private StringProperty resultMessage = new SimpleStringProperty();

    public void setValidationErrorPresent(boolean validationErrorPresent) {
        this.validationErrorPresent = validationErrorPresent;
        setResultMessage();
    }

    public void setValidationErrorMessage(String validationErrorMessage) {
        this.validationErrorMessage = validationErrorMessage;
        setResultMessage();
    }

    public void setSuccess(boolean success) {
        this.success = success;
        setResultMessage();
    }

    public StringProperty resultMessage() {
        return this.resultMessage;
    }

    private void setResultMessage() {
        if (validationErrorPresent) {
            resultMessage.set("An error has occured: " + validationErrorMessage);
        } else {
            resultMessage.set("Result: " + (success ? "SUCCESS" : "FAILED"));
        }
    }

}
