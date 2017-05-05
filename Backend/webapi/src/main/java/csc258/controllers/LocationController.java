package csc258.controllers;

import csc258.domain.frontend.common.ResponseDetail;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.location.fetchLocationsByCategoriesById.FetchLocationsByCategoriesByIdRequest;
import csc258.domain.frontend.location.submitlocation.SubmitLocationRequest;
import csc258.service.location.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/submitLocation", method = RequestMethod.POST)
    public ResponseDetail submitLocation(@RequestBody SubmitLocationRequest submitLocationRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (locationService.saveLocation(submitLocationRequest)) {
                return new ResponseDetail(200L, "Location Saved");
            }
        } catch (Exception e) {
            LOGGER.error("Location save failed", e);
        }
        return new ResponseDetail(400L, "Location Save Failed");
    }

    @RequestMapping(value = "/fetchLocationsByCategoriesById", method = RequestMethod.POST)
    public List<Location> fetchLocationsByCategoriesById(@RequestBody FetchLocationsByCategoriesByIdRequest fetchLocationsByCategoriesByIdRequest) {
        return locationService.fetchLocationsByCategoriesById(fetchLocationsByCategoriesByIdRequest);
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