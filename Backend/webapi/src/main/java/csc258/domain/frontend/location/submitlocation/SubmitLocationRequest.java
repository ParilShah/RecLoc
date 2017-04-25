package csc258.domain.frontend.location.submitlocation;

import com.fasterxml.jackson.annotation.*;
import csc258.domain.frontend.location.Location;
import csc258.domain.frontend.user.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "user",
        "location"
})
public class SubmitLocationRequest implements Serializable {

    @JsonProperty("user")
    @Valid
    private User user;
    @JsonProperty("location")
    @Valid
    private Location location;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7132173029696519102L;

    /**
     * No args constructor for use in serialization
     */
    public SubmitLocationRequest() {
    }

    /**
     * @param location
     * @param user
     */
    public SubmitLocationRequest(User user, Location location) {
        super();
        this.user = user;
        this.location = location;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    public SubmitLocationRequest withUser(User user) {
        this.user = user;
        return this;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public SubmitLocationRequest withLocation(Location location) {
        this.location = location;
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

    public SubmitLocationRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(user).append(location).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SubmitLocationRequest) == false) {
            return false;
        }
        SubmitLocationRequest rhs = ((SubmitLocationRequest) other);
        return new EqualsBuilder().append(user, rhs.user).append(location, rhs.location).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
