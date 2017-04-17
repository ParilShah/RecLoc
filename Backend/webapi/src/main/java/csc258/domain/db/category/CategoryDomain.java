package csc258.domain.db.category;

import csc258.domain.db.user.UserDomain;

import javax.persistence.Entity;
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

    @ManyToMany
    private List<UserDomain> userDomains;

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

    @Override
    public String toString() {
        return "CategoryDomain{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", userDomains=" + userDomains +
                '}';
    }
}