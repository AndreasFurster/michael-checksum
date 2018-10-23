package com.michaelchecksum.domain.utils;

import javafx.util.converter.BooleanStringConverter;

public class FileValidationSucceedBooleanStringConverter extends BooleanStringConverter {
    @Override
    public String toString(Boolean value) {
        if (value == null) return "No";

        return value ? "Yes" : "No";
    }

    @Override
    public Boolean fromString(String value) {
        return value.equals("Yes");
    }
}

