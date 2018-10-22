package com.michaelchecksum.business;

import com.michaelchecksum.domain.viewmodels.FileValidationResultViewModel;
import com.michaelchecksum.domain.viewmodels.FileValidationViewModel;
import com.michaelchecksum.presentation.FileValidation;
import com.michaelchecksum.presentation.FileValidationResult;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileValidationManager implements FileEventListener {
    private FileValidation fileValidation;
    private FileValidationViewModel viewModel;
    private FileValidationResult fileValidationResult;
    private FileValidationResultViewModel fileValidationResultViewModel;

    FileValidationManager() {
        this.viewModel = new FileValidationViewModel();
        this.viewModel.setOnConfirmClickEventHandler(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                validateChecksum();
            }
        });

        this.viewModel.setOnCancelClickEventHandler(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                closeGui();
            }
        });
    }

    @Override
    public void handleNewFileFound(File file) {
        validateFile(file);
    }

    public void validateFile(File file) {
        this.fileValidation = new FileValidation();
        this.fileValidation.initializeComponent(this.viewModel);

        this.viewModel.setFile(file);

        this.fileValidation.show();
        this.fileValidation.setAlwaysOnTop(true);
    }

    private void closeGui() {
        this.fileValidation.close();
    }

    private void validateChecksum() {
        this.fileValidationResult = new FileValidationResult();
        this.fileValidationResultViewModel = new FileValidationResultViewModel();
        fileValidationResult.initializeComponent(fileValidationResultViewModel);

        try {
            boolean result = checkHash(viewModel.getFile(), viewModel.getHash());

            fileValidationResultViewModel.setSuccess(result);
        } catch (NoSuchAlgorithmException | IllegalArgumentException e) {
            fileValidationResultViewModel.setValidationErrorPresent(true);
            fileValidationResultViewModel.setValidationErrorMessage(e.getMessage());
        } catch (IOException e) {
            fileValidationResultViewModel.setValidationErrorPresent(true);
            fileValidationResultViewModel.setValidationErrorMessage(e.getMessage());
        } finally {
            this.fileValidation.close();
            this.fileValidationResult.show();
            this.fileValidation.setAlwaysOnTop(true);
        }
    }

    private Boolean checkHash(File file, String hash) throws NoSuchAlgorithmException, IOException, IllegalArgumentException {
        if (hash == null)
            throw new IllegalArgumentException("There is no hash supplied");

        switch (hash.length()) {
            // MD5 128 Bit
            case 128 / 4:
                return validateChecksum("MD5", file, hash);

            // SHA1
            case 160 / 4:
                return validateChecksum("SHA-1", file, hash);

            // SHA256
            case 256 / 4:
                return validateChecksum("SHA-256", file, hash);

            // SHA512
            case 512 / 4:
                return validateChecksum("SHA-512", file, hash);
        }

        throw new IllegalArgumentException("The supplied hash is unsupported");
    }

    private boolean validateChecksum(String hashType, File file, String hash) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(hashType);

        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString().equals(hash);
    }
}
