package csc258.domain.db.location;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by desair4 on 4/24/2017.
 */
//@Entity(name = "Address")
public class AddressDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zip;

    public AddressDomain(String city, String state, String country) {
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public AddressDomain(String city, String state, String country, String zip) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public AddressDomain(String addressLine1, String addressLine2, String city, String state, String country, String zip) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDomain)) return false;

        AddressDomain that = (AddressDomain) o;

        if (getId() != that.getId()) return false;
        if (!getAddressLine1().equals(that.getAddressLine1())) return false;
        if (getAddressLine2() != null ? !getAddressLine2().equals(that.getAddressLine2()) : that.getAddressLine2() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        if (getCountry() != null ? !getCountry().equals(that.getCountry()) : that.getCountry() != null) return false;
        return getZip() != null ? getZip().equals(that.getZip()) : that.getZip() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getAddressLine1().hashCode();
        result = 31 * result + (getAddressLine2() != null ? getAddressLine2().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getZip() != null ? getZip().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("addressLine1", addressLine1)
                .append("addressLine2", addressLine2)
                .append("city", city)
                .append("state", state)
                .append("country", country)
                .append("zip", zip)
                .toString();
    }
}