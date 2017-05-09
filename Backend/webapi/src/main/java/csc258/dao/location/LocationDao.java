package csc258.dao.location;

import csc258.dao.repositories.location.ILocationRepository;
import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.location.AddressDomain;
import csc258.domain.db.location.LocationDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<LocationDomain> fetchLocationsByCategoriesById(List<CategoryDomain> categories) {
        return locationRepository.findByCategoryDomainsIn(categories);
    }

    public List<LocationDomain> fetchLocations(LocationDomain locationDomains) {
//        Example<LocationDomain> locationDomainExample = Example.of(locationDomains);
//        List<LocationDomain> target = new ArrayList<>();
        AddressDomain addressDomain = locationDomains.getAddress();
        List<CategoryDomain> categoryDomains = locationDomains.getCategoryDomains();
        if (categoryDomains == null && addressDomain == null) return null;

        if (addressDomain != null && categoryDomains != null) {
            return locationRepository.findDistinctLocationDomainsByAddressCountryAndCategoryDomainsIn(addressDomain.getCountry(), categoryDomains);
        } else if (categoryDomains != null) {
            return locationRepository.findByCategoryDomainsIn(categoryDomains);
        } else {
            return locationRepository.findByAddressCountry(addressDomain.getCountry());
        }

//        return StreamSupport
//                .stream(locationRepository.findAll(locationDomainExample).spliterator(), true)
//                .collect(Collectors.toList());
//        locationRepository.findAll().forEach(target::add);
//        return target;
//        return locationRepository.findByCategoryDomainsIn(locationDomains);
    }

    @Autowired
    public void setLocationRepository(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
}