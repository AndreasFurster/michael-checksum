package com.michaelchecksum.domain.viewmodels;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

// TODO: success, failed and total counter values can be calculated
// TODO: success, failed and total don't have to be bidirectional
public class DashboardViewModel {
    private DoubleProperty successAmount = new SimpleDoubleProperty();
    private DoubleProperty failedAmount = new SimpleDoubleProperty();
    private DoubleProperty totalAmount = new SimpleDoubleProperty();

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


}
