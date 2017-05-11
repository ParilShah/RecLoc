package csc258.dao.repositories.location;

import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.location.LocationDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Created by Paril on 4/24/2017.
 */
public interface ILocationRepository extends CrudRepository<LocationDomain, Long>, QueryByExampleExecutor<LocationDomain> {
    List<LocationDomain> findDistinctLocationDomainsByCategoryDomainsIn(List<CategoryDomain> categoryDomainList);

    List<LocationDomain> findDistinctLocationDomainsByAddressCountryAndCategoryDomainsIn(String countryName, List<CategoryDomain> locationDomains);

    List<LocationDomain> findDistinctLocationDomainsByAddressCountry(String country);
//    List<LocationDomain> findLocationDomains(List<CategoryDomain> locationDomains);
}