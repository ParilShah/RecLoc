package csc258.mappers.location;

import csc258.domain.db.location.AddressDomain;
import csc258.domain.frontend.location.Address;

/**
 * Created by desair4 on 4/24/2017.
 */
public class AddressMapper {

    public static Address mapAddressBackendToFrontend(AddressDomain addressDomain) {
        return new Address(addressDomain.getAddressLine1(), addressDomain.getAddressLine2(), addressDomain.getCity(), addressDomain.getState(), addressDomain.getCountry(), addressDomain.getZip());
    }

    public static AddressDomain mapAddressFrontendToBackend(Address address) {
        return new AddressDomain(address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getCountry(), address.getZip());
    }
}
