package csc258.mappers.location;

import csc258.domain.db.location.LocationDomain;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.location.LocationDetails;
import csc258.mappers.category.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by desair4 on 4/16/2017.
 */
public class LocationMapper {

    public static Location mapLocationBackendToFrontend(LocationDomain locationDomain) {
        if (locationDomain == null) return null;
        return new Location(new LocationDetails(
                locationDomain.getLocationName(),
                locationDomain.getLocationDescription(),
                AddressMapper.mapAddressBackendToFrontend(
                        locationDomain.getAddress()
                ),
                locationDomain.getPhotoName()
//                locationDomain.getCategoryDomains()
        ));
//        return null;
    }

    public static List<Location> mapLocationListBackendToFrontend(List<LocationDomain> locationDomainList) {
        if (locationDomainList == null) return null;
        return locationDomainList.parallelStream().map(LocationMapper::mapLocationBackendToFrontend).collect(Collectors.toList());
    }

    public static List<LocationDomain> mapLocationListFrontendToBackend(List<Location> locationList) {
        if (locationList == null) return null;
        return locationList.parallelStream().map(LocationMapper::mapLocationFrontendToBackend).collect(Collectors.toList());
    }

    public static LocationDomain mapLocationFrontendToBackend(Location location) {
        if (location == null) return null;
        LocationDetails locationDetails = location.getLocationDetails();
        LocationDomain locationDomain = new LocationDomain(
                locationDetails.getLocationName(),
                locationDetails.getLocationDescription(),
                AddressMapper.mapAddressFrontendToBackend(locationDetails.getAddress())
        );
        List<String> tags = location.getLocationDetails().getTags();
        if (tags != null) {
            locationDomain.setCategoryDomains(CategoryMapper.mapTagListFrontendToCategoryBackend(tags));
        }

        List<Long> categoryIds = location.getLocationDetails().getCategoryIds();
        if (categoryIds != null) {
            locationDomain.setCategoryDomains(CategoryMapper.mapCategoryIdListFrontendToCategoryBackend(categoryIds));
        }

        return locationDomain;
    }
}