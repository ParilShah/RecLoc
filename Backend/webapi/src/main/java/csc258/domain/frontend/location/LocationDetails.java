package csc258.domain.frontend.location;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "locationName",
        "locationDescription",
        "Address",
        "tags"
})
public class LocationDetails implements Serializable {

    @JsonProperty("locationName")
    private String locationName;
    @JsonProperty("locationDescription")
    private String locationDescription;
    @JsonProperty("Address")
    @Valid
    private Address address;
    @JsonProperty("tags")
    @Valid
    private List<String> tags = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8872444609212129281L;

    /**
     * No args constructor for use in serialization
     */
    public LocationDetails() {
    }

    /**
     * @param locationDescription
     * @param locationName
     */
    public LocationDetails(String locationName, String locationDescription) {
        super();
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }

    /**
     * @param address
     * @param locationDescription
     * @param locationName
     */
    public LocationDetails(String locationName, String locationDescription, Address address) {
        super();
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.address = address;
    }

    /**
     * @param tags
     * @param locationDescription
     * @param locationName
     */
    public LocationDetails(String locationName, String locationDescription, List<String> tags) {
        super();
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.tags = tags;
    }

    /**
     * @param tags
     * @param address
     * @param locationDescription
     * @param locationName
     */
    public LocationDetails(String locationName, String locationDescription, Address address, List<String> tags) {
        super();
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.address = address;
        this.tags = tags;
    }

    @JsonProperty("locationName")
    public String getLocationName() {
        return locationName;
    }

    @JsonProperty("locationName")
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocationDetails withLocationName(String locationName) {
        this.locationName = locationName;
        return this;
    }

    @JsonProperty("locationDescription")
    public String getLocationDescription() {
        return locationDescription;
    }

    @JsonProperty("locationDescription")
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public LocationDetails withLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
        return this;
    }

    @JsonProperty("Address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(Address address) {
        this.address = address;
    }

    public LocationDetails withAddress(Address address) {
        this.address = address;
        return this;
    }

    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocationDetails withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public LocationDetails withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(locationName).append(locationDescription).append(address).append(tags).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LocationDetails) == false) {
            return false;
        }
        LocationDetails rhs = ((LocationDetails) other);
        return new EqualsBuilder().append(locationName, rhs.locationName).append(locationDescription, rhs.locationDescription).append(address, rhs.address).append(tags, rhs.tags).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}