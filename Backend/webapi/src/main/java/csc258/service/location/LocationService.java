package csc258.service.location;

import csc258.dao.CategoryDao;
import csc258.dao.UserDao;
import csc258.dao.location.LocationDao;
import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.location.LocationDomain;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.location.fetchLocationsByCategoriesById.FetchLocationsByCategoriesByIdRequest;
import csc258.domain.frontend.location.submitlocation.SubmitLocationRequest;
import csc258.domain.frontend.photo.PhotoDetails;
import csc258.mappers.category.CategoryMapper;
import csc258.mappers.location.AddressMapper;
import csc258.mappers.location.LocationMapper;
import csc258.mappers.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by desair4 on 4/24/2017.
 */
@Service
public class LocationService {
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);

    public boolean saveLocation(SubmitLocationRequest submitLocationRequest) {

        //TODO what to do if photo save is not successful
        boolean isPhotoSaveSuccessful = savePhoto(submitLocationRequest);
        List<CategoryDomain> categoryDomainList = categoryDao.findByCategoryNameIn(submitLocationRequest.getLocation().getLocationDetails().getTags());
        LocationDomain locationDomain = LocationMapper.mapLocationFrontendToBackend(submitLocationRequest.getLocation());
        locationDomain.setUserDomain(UserMapper.mapUserFrontendToBackend(submitLocationRequest.getUser()));
        locationDomain.setAddress(AddressMapper.mapAddressFrontendToBackend(submitLocationRequest.getLocation().getLocationDetails().getAddress()));
        //save location to db
        try {
            locationDomain.setCategoryDomains(categoryDomainList);
            locationDao.saveLocation(locationDomain);
            return true;
        } catch (Exception e) {
            LOGGER.error("Location Save failed", e);
        }
        return false;
    }

    public void findLocations() {

    }

    public List<Location> fetchLocationsByCategoriesById(FetchLocationsByCategoriesByIdRequest fetchLocationsByCategoriesByIdRequest) {
        return LocationMapper.mapLocationListBackendToFrontend(
                locationDao.fetchLocationsByCategoriesById(
                        CategoryMapper.mapCategoryListFrontendToBackend(
                                fetchLocationsByCategoriesByIdRequest.getCategory())));
    }

    private boolean savePhoto(SubmitLocationRequest submitLocationRequest) {
//        PhotoDetails photoDetails = submitLocationRequest.getLocation().getPhotoDetails();
//        if (photoDetails == null) return true;
//        byte[] fileBytes = null;
//        try {
//            fileBytes = photoDetails.getFile() != null ? photoDetails.getFile().getBytes() : null;
//            List<String> tempList = getFilePathAndFileName(photoDetails);
//            FileSaveService.saveFileToLocal(fileBytes, tempList.get(0), tempList.get(1));
//            photoDetails.setFileName(tempList.get(0));
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOGGER.error("Error obtaining file bytes", e);
//        }
        return false;
    }

    private List<String> getFilePathAndFileName(PhotoDetails photoDetails) {
        List<String> list = new LinkedList<>();
        list.add(getFileName(photoDetails));
        list.add(getFilePath(photoDetails));
        return list;
    }

    private String getFilePath(PhotoDetails photoDetails) {
        String basePath = System.getProperty("fileBasePath");
        if (photoDetails.getFilePath() == null) {
            return basePath + UUID.randomUUID().toString();
        } else {
            return basePath + photoDetails.getFilePath();
        }
    }

    private String getFileName(PhotoDetails photoDetails) {
        if (photoDetails.getFileName() == null) {
            return getRandomFileName();
        } else {
            return photoDetails.getFileName();
        }
    }

    private String getRandomFileName() {
        return UUID.randomUUID().toString();
    }


    public LocationDao getLocationDao() {
        return locationDao;
    }

    public void setLocationDao(LocationDao locationDao) {
        this.locationDao = locationDao;
    }
}