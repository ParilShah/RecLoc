package csc258.domain.frontend.location;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "locationDetails"
})
public class Location implements Serializable {

    @JsonProperty("locationDetails")
    @Valid
    private LocationDetails locationDetails;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6220818399085514099L;

    @JsonProperty("photoBytes")
    private byte[] photoBytes;

    /**
     * No args constructor for use in serialization
     */
    public Location() {
    }

    /**
     * @param locationDetails
     */
    public Location(LocationDetails locationDetails) {
        super();
        this.locationDetails = locationDetails;
    }

    /**
     * @param locationDetails
     * @param bytes
     */
    public Location(LocationDetails locationDetails, byte[] bytes) {
        super();
        this.locationDetails = locationDetails;
        this.photoBytes = bytes;
    }

    @JsonProperty("locationDetails")
    public LocationDetails getLocationDetails() {
        return locationDetails;
    }

    @JsonProperty("locationDetails")
    public void setLocationDetails(LocationDetails locationDetails) {
        this.locationDetails = locationDetails;
    }

    public byte[] getPhotoBytes() {
        return photoBytes;
    }

    public void setPhotoBytes(byte[] photoBytes) {
        this.photoBytes = photoBytes;
    }

    public Location withLocationDetails(LocationDetails locationDetails) {
        this.locationDetails = locationDetails;
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

    public Location withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(locationDetails).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Location) == false) {
            return false;
        }
        Location rhs = ((Location) other);
        return new EqualsBuilder().append(locationDetails, rhs.locationDetails).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}