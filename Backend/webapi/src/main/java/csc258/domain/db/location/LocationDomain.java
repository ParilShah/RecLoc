package csc258.domain.db.location;

import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.user.UserDomain;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.List;

/**
 * Created by desair4 on 4/24/2017.
 */
@Entity(name = "location")
public class LocationDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int locationId;

    private String locationName;
    private String locationDescription;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDomain address;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "location_category", joinColumns = @JoinColumn(name = "location_Id",
            referencedColumnName = "locationId"
    ),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "categoryId"
            ))
    private List<CategoryDomain> categoryDomains;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceId")
    private UserDomain userDomain;

    private String photoName;

    public LocationDomain() {
    }

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

    public LocationDomain(String locationName, String locationDescription, AddressDomain address, List<CategoryDomain> categoryDomains) {
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.address = address;
        this.categoryDomains = categoryDomains;
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public List<CategoryDomain> getCategoryDomains() {
        return categoryDomains;
    }

    public void setCategoryDomains(List<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    public UserDomain getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(UserDomain userDomain) {
        this.userDomain = userDomain;
    }

    public AddressDomain getAddress() {
        return address;
    }

    public void setAddress(AddressDomain address) {
        this.address = address;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationDomain that = (LocationDomain) o;

        return locationId == that.locationId;
    }

    @Override
    public int hashCode() {
        return locationId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}