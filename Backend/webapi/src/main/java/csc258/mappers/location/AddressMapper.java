package csc258.mappers.location;

import csc258.domain.db.location.AddressDomain;
import csc258.domain.frontend.location.Address;

/**
 * Created by Paril on 4/24/2017.
 */
public class AddressMapper {

    public static Address mapAddressBackendToFrontend(AddressDomain addressDomain) {
        if (addressDomain == null) return null;
        return new Address(addressDomain.getAddressLine1(),
                addressDomain.getAddressLine2(),
                addressDomain.getCity(),
                addressDomain.getState(),
                addressDomain.getCountry(),
                addressDomain.getZip(),
                addressDomain.getLatitude(),
                addressDomain.getLongitude());
    }

    public static AddressDomain mapAddressFrontendToBackend(Address address) {
        if (address == null) return null;
        return new AddressDomain(address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getCountry(), address.getZip(),address.getLatitude(),address.getLongitude());
    }
}