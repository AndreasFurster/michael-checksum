import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashChecker {
    public Boolean CheckHash(URI fileUri, String hash) throws NoSuchAlgorithmException, IOException, IllegalArgumentException {
        File file = new File(fileUri);

        switch (hash.length()){
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

        throw new IllegalArgumentException("The supplied hash is unsupported or invalid");
    }

    private boolean validateChecksum(String hashType, File file, String hash) throws IOException, NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance(hashType);

        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString().equals(hash);
    }



}
