package csc258.controllers;

import csc258.domain.frontend.common.ResponseDetail;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.location.fetchLocationsByCategoriesById.FetchLocationsByCategoriesByIdRequest;
import csc258.domain.frontend.location.submitlocation.SubmitLocationRequest;
import csc258.service.location.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by desair4 on 4/13/2017.
 */
@RestController
@RequestMapping("location")
public class LocationController {

    private LocationService locationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @RequestMapping(value = "/submitLocation", method = RequestMethod.POST)
//    public ResponseDetail submitLocation(@RequestParam("jsonRequest") SubmitLocationRequest submitLocationRequest, @RequestPart("image") MultipartFile image, HttpServletRequest request, HttpServletResponse response) {
//    public ResponseDetail submitLocation(@RequestPart("jsonRequest") @JsonDeserialize(keyAs = String.class, contentAs = SubmitLocationRequest.class) SubmitLocationRequest submitLocationRequest, @RequestPart("image") MultipartFile image, HttpServletRequest request, HttpServletResponse response) {
    public ResponseDetail submitLocation(@RequestPart("jsonRequest") String submitLocationRequestString, @RequestPart("image") MultipartFile image, HttpServletRequest request, HttpServletResponse response) {
        try {
            SubmitLocationRequest submitLocationRequest = mappingJackson2HttpMessageConverter.getObjectMapper().readValue(submitLocationRequestString, SubmitLocationRequest.class);
//            System.out.println(s);
            System.out.println(submitLocationRequest);
            if (locationService.saveLocation(submitLocationRequest, image)) {
                return new ResponseDetail(200L, "Location Saved");
            }
        } catch (Exception e) {
            LOGGER.error("Location save failed", e);
        }
        return new ResponseDetail(400L, "Location Save Failed");
    }

    @RequestMapping(value = "/fetchLocationsByCategoriesId", method = RequestMethod.POST)
    public List<Location> fetchLocationsByCategoriesById(@RequestBody FetchLocationsByCategoriesByIdRequest fetchLocationsByCategoriesByIdRequest) {
        List<Location> locations = locationService.fetchLocationsByCategoriesById(fetchLocationsByCategoriesByIdRequest);
        return locations;
    }

    @RequestMapping(value = "/fetchLocationsByCountryAndCategoriesId", method = RequestMethod.POST)
    public List<Location> fetchLocationsByCountryAndCategoriesById(@RequestBody FetchLocationsByCategoriesByIdRequest fetchLocationsByCategoriesByIdRequest) {
//        return locationService.findByCountryAndCategoriesId(fetchLocationsByCategoriesByIdRequest);
        return null;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(locationService, "locationService cannot be null");
    }
}