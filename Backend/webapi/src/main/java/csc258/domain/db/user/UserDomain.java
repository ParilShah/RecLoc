package csc258.domain.db.user;

import csc258.domain.db.category.CategoryDomain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by desair4 on 4/15/2017.
 */
@Entity(name = "User")
public class UserDomain {

    @Id
    private String deviceId;

    @ManyToMany
    /*@JoinTable(
            name = "User_Category"
    )*/
    private List<CategoryDomain> categoryDomainList;

    public UserDomain() {
    }

    public UserDomain(String deviceId) {
        this.deviceId = deviceId;
    }

    public UserDomain(String deviceId, List<CategoryDomain> categoryDomainList) {
        this.deviceId = deviceId;
        this.categoryDomainList = categoryDomainList;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public List<CategoryDomain> getCategoryDomainList() {
        return categoryDomainList;
    }

    public void setCategoryDomainList(List<CategoryDomain> categoryDomainList) {
        this.categoryDomainList = categoryDomainList;
    }

    @Override
    public String toString() {
        return "UserDomain{" +
                "deviceId='" + deviceId + '\'' +
                ", categoryDomainList=" + categoryDomainList +
                '}';
    }
}