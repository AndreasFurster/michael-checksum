package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

// TODO: success, failed and total counter values can be calculated
// TODO: success, failed and total don't have to be bidirectional
public class DashboardViewModel {
    private DoubleProperty successAmount = new SimpleDoubleProperty();
    private DoubleProperty failedAmount = new SimpleDoubleProperty();
    private DoubleProperty totalAmount = new SimpleDoubleProperty();


    private ObservableList<ValidationResultViewModel> validationResults = FXCollections.observableArrayList();

    private EventHandler<MouseEvent> onSettingsClick;

    public void addValidationResult(ValidationResultViewModel validationResult) {
        this.validationResults.add(validationResult);
        calculateStatistics();
    }

    private void calculateStatistics() {
        int succeed = 0;
        int failed = 0;
        int total = 0;

        for (ValidationResultViewModel validationResult : this.validationResults) {
            if (validationResult.getSucceed()){
                succeed += 1;
            }
            else {
                failed += 1;
            }

            total += 1;
        }

        this.successAmount.set(succeed);
        this.failedAmount.set(failed);
        this.totalAmount.set(total);
    }

    public ObservableList<ValidationResultViewModel> validationResults(){
        return validationResults;
    }

    public DoubleProperty failedAmountProperty() {
        return failedAmount;
    }

    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }

    public final DoubleProperty successAmountProperty() {
        return successAmount;
    }

    public void setOnSettingsClickEventHandler(EventHandler<MouseEvent> eventHandler) {
        this.onSettingsClick = eventHandler;
    }

    public EventHandler<MouseEvent> onSettingsClick() {
        return this.onSettingsClick;
    }

}
