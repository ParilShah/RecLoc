package csc258.service.location;

import csc258.dao.location.LocationDao;
import csc258.domain.frontend.location.submitlocation.SubmitLocationRequest;
import csc258.domain.frontend.photo.PhotoDetails;
import csc258.mappers.location.LocationMapper;
import csc258.service.file.FileSaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by desair4 on 4/24/2017.
 */
@Service
public class LocationService {

    @Autowired
    private LocationDao locationDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);

    public boolean saveLocation(SubmitLocationRequest submitLocationRequest) {
//        create image file
        boolean isPhotoSaveSuccessful = savePhoto(submitLocationRequest);

        //save location to db
        try {
            locationDao.saveLocation(LocationMapper.mapLocationFrontendToBackend(submitLocationRequest.getLocation()));
        } catch (Exception e) {
            LOGGER.error("Location Save failed", e);
        }
        return false;
    }

    private boolean savePhoto(SubmitLocationRequest submitLocationRequest) {
        PhotoDetails photoDetails = submitLocationRequest.getLocation().getPhotoDetails();
        byte[] fileBytes = null;
        try {
            fileBytes = photoDetails.getFile().getBytes();
            FileSaveService.saveFileToLocal(fileBytes, photoDetails.getFileName());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Error obtaining file bytes", e);
        }
        return false;
    }
}