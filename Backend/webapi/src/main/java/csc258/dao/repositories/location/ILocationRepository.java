package csc258.dao.repositories.location;

import csc258.domain.db.location.LocationDomain;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by desair4 on 4/24/2017.
 */
public interface ILocationRepository extends CrudRepository<LocationDomain, Long> {
}
