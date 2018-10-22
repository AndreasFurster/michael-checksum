package com.michaelchecksum.business;

import com.michaelchecksum.data.FileValidationStorage;
import com.michaelchecksum.domain.FileValidationResult;
import com.michaelchecksum.domain.viewmodels.FileValidationResultViewModel;
import com.michaelchecksum.domain.viewmodels.FileValidationViewModel;
import com.michaelchecksum.presentation.FileValidationUi;
import com.michaelchecksum.presentation.FileValidationResultUi;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileValidationManager implements FileEventListener {
    private FileValidationUi fileValidationUi;
    private FileValidationViewModel viewModel;
    private FileValidationResultUi fileValidationResultUi;
    private FileValidationResultViewModel fileValidationResultViewModel;
    private FileValidationStorage fileValidationStorage;

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

        this.fileValidationStorage = new FileValidationStorage();
    }

    @Override
    public void handleNewFileFound(File file) {
        validateFile(file);
    }

    public void validateFile(File file) {
        this.fileValidationUi = new FileValidationUi();
        this.fileValidationUi.initializeComponent(this.viewModel);

        this.viewModel.setFile(file);

        this.fileValidationUi.show();
        this.fileValidationUi.setAlwaysOnTop(true);
    }

    private void closeGui() {
        this.fileValidationUi.close();
    }

    private void validateChecksum() {
        this.fileValidationResultUi = new FileValidationResultUi();
        this.fileValidationResultViewModel = new FileValidationResultViewModel();
        fileValidationResultUi.initializeComponent(fileValidationResultViewModel);

        try {
            FileValidationResult result = checkHash(viewModel.getFile(), viewModel.getHash());

            this.fileValidationResultViewModel.setSuccess(result.getSuccess());
            this.fileValidationStorage.add(result);
        } catch (NoSuchAlgorithmException | IllegalArgumentException | IOException e) {
            fileValidationResultViewModel.setValidationErrorPresent(true);
            fileValidationResultViewModel.setValidationErrorMessage(e.getMessage());
        } finally {
            this.fileValidationUi.close();
            this.fileValidationResultUi.show();
            this.fileValidationUi.setAlwaysOnTop(true);
        }
    }

    private FileValidationResult checkHash(File file, String hash) throws NoSuchAlgorithmException, IOException, IllegalArgumentException {
        if (hash == null)
            throw new IllegalArgumentException("There is no hash supplied");

        boolean checksumValidationResult = false;

        switch (hash.length()) {
            // MD5 128 Bit
            case 128 / 4:
                checksumValidationResult = validateChecksum("MD5", file, hash);
                break;

            // SHA1
            case 160 / 4:
                checksumValidationResult = validateChecksum("SHA-1", file, hash);
                break;

            // SHA256
            case 256 / 4:
                checksumValidationResult = validateChecksum("SHA-256", file, hash);
                break;

            // SHA512
            case 512 / 4:
                checksumValidationResult = validateChecksum("SHA-512", file, hash);
                break;

            default:
                throw new IllegalArgumentException("The supplied hash is unsupported");
        }

        return new FileValidationResult(file, hash, checksumValidationResult);
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
