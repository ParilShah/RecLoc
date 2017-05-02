package csc258.dao.location;

import csc258.dao.repositories.location.ILocationRepository;
import csc258.domain.db.location.LocationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by desair4 on 4/24/2017.
 */
@Component
@Transactional
public class LocationDao {
    private ILocationRepository locationRepository;

    public LocationDomain saveLocation(LocationDomain location) {
        return locationRepository.save(location);
    }

    @Autowired
    public void setLocationRepository(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
}
