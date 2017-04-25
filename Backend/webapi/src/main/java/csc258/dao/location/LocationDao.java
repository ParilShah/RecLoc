package csc258.dao.location;

import csc258.dao.repositories.location.ILocationRepository;
import csc258.domain.db.location.LocationDomain;

/**
 * Created by desair4 on 4/24/2017.
 */
public class LocationDao {
    private ILocationRepository locationRepository;

    public LocationDomain saveLocation(LocationDomain location) {
        return locationRepository.save(location);
    }

    public void setLocationRepository(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
}
