package com.michaelchecksum.business;

import com.michaelchecksum.data.FileValidationStorage;
import com.michaelchecksum.domain.FileValidationResult;
import com.michaelchecksum.domain.HashType;
import com.michaelchecksum.domain.utils.FileEventListener;
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
    private FileValidationStorage fileValidationStorage;

    FileValidationManager() {
        this.fileValidationStorage = new FileValidationStorage();
    }

    @Override
    public void handleNewFileFound(File file) {
        validateFile(file);
    }

    private void validateFile(File file) {
        this.fileValidationUi = new FileValidationUi();
        FileValidationViewModel viewModel = CreateViewModel();
        this.fileValidationUi.initializeComponent(viewModel);

        viewModel.setFile(file);

        this.fileValidationUi.show();
        this.fileValidationUi.setAlwaysOnTop(true);
    }

    private FileValidationViewModel CreateViewModel(){
        FileValidationViewModel viewModel = new FileValidationViewModel();

        viewModel.setOnConfirmClickEventHandler(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                validateChecksum(viewModel);
            }
        });

        viewModel.setOnCancelClickEventHandler(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                closeGui();
            }
        });

        return viewModel;
    }

    private void validateChecksum(FileValidationViewModel viewModel) {
        FileValidationResultUi fileValidationResultUi = new FileValidationResultUi();
        FileValidationResultViewModel fileValidationResultViewModel = new FileValidationResultViewModel();
        fileValidationResultUi.initializeComponent(fileValidationResultViewModel);

        try {
            FileValidationResult result = checkHash(viewModel.getFile(), viewModel.getHash());

            fileValidationResultViewModel.setSuccess(result.getSuccess());
            this.fileValidationStorage.add(result);
        } catch (NoSuchAlgorithmException | IllegalArgumentException | IOException e) {
            fileValidationResultViewModel.setValidationErrorMessage(e.getMessage());
        } finally {
            this.fileValidationUi.close();
            fileValidationResultUi.show();
            this.fileValidationUi.setAlwaysOnTop(true);
        }
    }

    private void closeGui() {
        this.fileValidationUi.close();
    }

    private FileValidationResult checkHash(File file, String hash) throws NoSuchAlgorithmException, IOException, IllegalArgumentException {
        if (hash == null)
            throw new IllegalArgumentException("There is no hash supplied");

        HashType hashType;

        switch (hash.length()) {
            // MD5 128 Bit
            case 128 / 4:
                hashType = HashType.MD5;
                break;

            // SHA1
            case 160 / 4:
                hashType = HashType.SHA1;
                break;

            // SHA256
            case 256 / 4:
                hashType = HashType.SHA256;
                break;

            // SHA512
            case 512 / 4:
                hashType = HashType.SHA512;
                break;

            default:
                throw new IllegalArgumentException("The supplied hash is unsupported");
        }

        return validateChecksum(file, hash, hashType);
    }

    private FileValidationResult validateChecksum(File file, String hash, HashType hashType) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(hashType.toString());

        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        boolean result = sb.toString().equals(hash);

        return new FileValidationResult(file.getName(), hash, hashType, result);
    }
}
