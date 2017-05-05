package csc258.domain.db.user;

import csc258.domain.db.category.CategoryDomain;
import csc258.domain.db.location.LocationDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by desair4 on 4/15/2017.
 */
@Entity(name = "User")
public class UserDomain implements Serializable {

    @Id
    private String deviceId;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_category", joinColumns = @JoinColumn(name = "device_Id",
            referencedColumnName = "deviceId"
    ),
            inverseJoinColumns = @JoinColumn(name = "category_id",
                    referencedColumnName = "categoryId"
            ))
    private List<CategoryDomain> categoryDomains;

    @OneToMany(mappedBy = "userDomain", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<LocationDomain> locationDomains;

    public UserDomain() {
    }

    public UserDomain(String deviceId) {
        this.deviceId = deviceId;
    }

    public UserDomain(String deviceId, List<CategoryDomain> categoryDomains) {
        this.deviceId = deviceId;
        this.categoryDomains = categoryDomains;
    }

    public UserDomain(String deviceId, List<CategoryDomain> categoryDomains, List<LocationDomain> locationDomains) {
        this.deviceId = deviceId;
        this.categoryDomains = categoryDomains;
        this.locationDomains = locationDomains;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<CategoryDomain> getCategoryDomains() {
        return categoryDomains;
    }

    public void setCategoryDomains(List<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    public List<LocationDomain> getLocationDomains() {
        return locationDomains;
    }

    public void setLocationDomains(List<LocationDomain> locationDomains) {
        this.locationDomains = locationDomains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserDomain)) return false;

        UserDomain that = (UserDomain) o;

        return new EqualsBuilder()
                .append(getDeviceId(), that.getDeviceId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getDeviceId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "UserDomain{" +
                "deviceId='" + deviceId + '\'' +
                ", categoryDomains=" + categoryDomains +
                '}';
    }
}