package csc258.domain.db.location;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by desair4 on 4/24/2017.
 */
public class LocationDomain {

    //todo decide on primary key

    private String locationName;
    private String locationDescription;
    private AddressDomain address;
    private List<String> tags;

    public LocationDomain(String locationName, AddressDomain address) {
        this.locationName = locationName;
        this.address = address;
    }

    public LocationDomain(String locationName, String locationDescription) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }

    public LocationDomain(String locationName, String locationDescription, AddressDomain address) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.address = address;
    }

    public LocationDomain(String locationName, String locationDescription, AddressDomain address, List<String> tags) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.address = address;
        this.tags = tags;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public AddressDomain getAddress() {
        return address;
    }

    public void setAddress(AddressDomain address) {
        this.address = address;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    //TODO override hashcode and equlas

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("locationName", locationName)
                .append("locationDescription", locationDescription)
                .append("address", address)
                .append("tags", tags)
                .toString();
    }
}
