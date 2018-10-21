package com.michaelchecksum.business;

import com.michaelchecksum.domain.viewmodels.FileValidationViewModel;
import com.michaelchecksum.presentation.FileValidation;

import java.io.File;

public class FileValidationManager implements FileEventListener {
    private FileValidation gui;
    private FileValidationViewModel viewModel;

    FileValidationManager() {
        this.gui = new FileValidation();
        this.viewModel = new FileValidationViewModel();
        this.gui.initializeComponent(this.viewModel);
    }

    @Override
    public void handleNewFileFound(File file) {
        // TODO: Handle new file result (open popup)
        System.out.println(file.getTotalSpace());

        this.viewModel.setFilePath(file.getPath());

        this.gui.show();
    }
}
