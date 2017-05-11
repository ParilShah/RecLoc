package csc258.service.location;

import csc258.dao.CategoryDao;
import csc258.dao.UserDao;
import csc258.dao.location.LocationDao;
import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.location.LocationDomain;
import csc258.domain.frontend.category.Category;
import csc258.domain.frontend.location.Address;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.location.LocationDetails;
import csc258.domain.frontend.location.fetchLocations.FetchLocationsRequest;
import csc258.domain.frontend.location.submitlocation.SubmitLocationRequest;
import csc258.mappers.location.AddressMapper;
import csc258.mappers.location.LocationMapper;
import csc258.mappers.user.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by Paril on 4/24/2017.
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

    public boolean saveLocation(SubmitLocationRequest submitLocationRequest, MultipartFile file) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>" + submitLocationRequest);
        //TODO what to do if photo save is not successful
//        Map<String, String> fileMap = savePhoto(file);
        List<CategoryDomain> categoryDomainList = categoryDao.findByCategoryNameIn(submitLocationRequest.getLocation().getLocationDetails().getTags());
        LocationDomain locationDomain = LocationMapper.mapLocationFrontendToBackend(submitLocationRequest.getLocation());
        locationDomain.setUserDomain(UserMapper.mapUserFrontendToBackend(submitLocationRequest.getUser()));
        locationDomain.setAddress(AddressMapper.mapAddressFrontendToBackend(submitLocationRequest.getLocation().getLocationDetails().getAddress()));
        byte[] bytes = null;//new byte[0];
        try {
            bytes = file.getBytes();
            locationDomain.setPhotoBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        locationDomain.setPhotoName(fileMap.get("fileName"));
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

    /*public List<Location> fetchLocationsByCategoriesById(FetchLocationsByCategoriesByIdRequest fetchLocationsByCategoriesByIdRequest) {
        return LocationMapper.mapLocationListBackendToFrontend(
                locationDao.fetchLocationsByCategoriesById(
                        CategoryMapper.mapCategoryListFrontendToBackend(
                                fetchLocationsByCategoriesByIdRequest.getCategory())));
    }*/

    public List<Location> fetchLocations(FetchLocationsRequest fetchLocationsRequest) {
//        Location location = new Location(
//                new LocationDetails(new Address(fetchLocationsRequest.getCountry()),
//                fetchLocationsRequest.getCategory().parallelStream().map(Category::getCategoryId).collect(Collectors.toList())));
        Location location = new Location();
        LocationDetails locationDetails = new LocationDetails();
        if (fetchLocationsRequest.getCountry() == null && (fetchLocationsRequest.getCategory() == null && fetchLocationsRequest.getCategory().isEmpty()))
            return null;

        if (fetchLocationsRequest.getCountry() != null) {
            locationDetails.setAddress(new Address(fetchLocationsRequest.getCountry()));
        }
        if (fetchLocationsRequest.getCategory() != null) {
            locationDetails.setCategoryIds(fetchLocationsRequest.getCategory().parallelStream().map(Category::getCategoryId).collect(Collectors.toList()));
        }

        location.setLocationDetails(locationDetails);
        return LocationMapper.mapLocationListBackendToFrontend(locationDao.
                fetchLocations(LocationMapper.
                        mapLocationFrontendToBackend(location)));
    }

//    private Map<String, String> savePhoto(MultipartFile file) {
//        if (file == null) return null;
//
//        try {
//            byte[] fileBytes = file.getBytes();
//            if (fileBytes == null) return null;
//            List<String> tempList = getFilePathAndFileName(file);
//            String fileName = tempList.get(0);
//            String filePath = tempList.get(1);
//            FileSaveService.saveFileToLocal(fileBytes, fileName, filePath);
//            return new HashMap<String, String>() {{
//                put("fileName", fileName);
//            }};
//        } catch (IOException e) {
//            e.printStackTrace();
//            LOGGER.error("Error obtaining file bytes", e);
//        }
//        return null;
//    }

    private List<String> getFilePathAndFileName(MultipartFile file) {
        List<String> list = new LinkedList<>();
        list.add(getFileName(UUID.randomUUID().toString()) + ".png");   //TODO get extention from file.getContentType()
        list.add(getFilePath());
        return list;
    }

    private String getFilePath() {

        //TODO get from systemProperties
//        return "C:/Users/Paril/D drive/Rushi/Paril/images/temp";
//        return "/Users/Paril/Desktop/RecLocImagePath";

        return System.getProperty("fileBasePath");
//        String basePath = System.getProperty("fileBasePath");
//        if (photoDetails.getFilePath() == null) {
//            return basePath + UUID.randomUUID().toString();
//        } else {
//            return basePath;
//        }
    }

    private String getFileName(String fileName) {
        if (fileName == null) {
            return getRandomFileName();
        } else {
            return fileName;
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