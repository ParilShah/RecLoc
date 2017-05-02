package csc258.domain.db.category;

import csc258.domain.db.location.LocationDomain;
import csc258.domain.db.user.UserDomain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * Created by desair4 on 4/15/2017.
 */
@Entity(name = "Category")
public class CategoryDomain {
    @Id
    private long categoryId;

    private String categoryName;

    @ManyToMany(
//            cascade = CascadeType.ALL,
            mappedBy = "categoryDomains", fetch = FetchType.LAZY)
//    @ManyToMany
//    @JoinTable(name = "user_category", joinColumns = @JoinColumn(name = "categoryId"), inverseJoinColumns = @JoinColumn(name = "deviceId"))
    private List<UserDomain> userDomains;

    @ManyToMany(
//            cascade = CascadeType.ALL,
            mappedBy = "categoryDomains", fetch = FetchType.LAZY)
    private List<LocationDomain> locationDomains;

    public CategoryDomain() {
    }

    public CategoryDomain(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public CategoryDomain(long categoryId, String categoryName, List<UserDomain> userDomains) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userDomains = userDomains;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<UserDomain> getUserDomains() {
        return userDomains;
    }

    public void setUserDomains(List<UserDomain> userDomains) {
        this.userDomains = userDomains;
    }

    public void addUserDomain(UserDomain userDomain) {
        userDomains.add(userDomain);
        userDomain.getCategoryDomains().add(this);
    }

    public void removeUserDomain(UserDomain userDomain) {
        userDomains.remove(userDomain);
        userDomain.getCategoryDomains().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CategoryDomain)) return false;

        CategoryDomain that = (CategoryDomain) o;

        return new EqualsBuilder()
                .append(getCategoryId(), that.getCategoryId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getCategoryId())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CategoryDomain{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", userDomains=" + userDomains +
                '}';
    }
}