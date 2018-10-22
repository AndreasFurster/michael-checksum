package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

// TODO: success, failed and total counter values can be calculated
// TODO: success, failed and total don't have to be bidirectional
public class DashboardViewModel {
    private DoubleProperty successAmount = new SimpleDoubleProperty();
    private DoubleProperty failedAmount = new SimpleDoubleProperty();
    private DoubleProperty totalAmount = new SimpleDoubleProperty();

    private EventHandler<MouseEvent> onSettingsClick;

    public DoubleProperty failedAmountProperty() {
        return failedAmount;
    }

    public void setFailedAmount(double failedAmount) {
        this.failedAmount.set(failedAmount);
    }

    public DoubleProperty totalAmountProperty() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount.set(totalAmount);
    }

    public final void setSuccess(double successAmount) {
        this.successAmount.set(successAmount);
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
