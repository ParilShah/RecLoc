package csc258.service.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by desair4 on 4/24/2017.
 */
public class FileSaveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSaveService.class);

    public static boolean saveFileToLocal(byte[] fileBytes, String fileName, String filePath) {
        if (fileBytes == null) return false;
        try {
            FileUtils.writeByteArrayToFile(new File(filePath + File.pathSeparator + filePath), fileBytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
