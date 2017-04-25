package csc258.domain.frontend.user;

import com.fasterxml.jackson.annotation.*;
import csc258.domain.frontend.category.Category;
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
        "deviceId",
        "category"
})
public class User implements Serializable {

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("category")
    @Valid
    private List<Category> category = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2937718248146590120L;

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param deviceId
     */
    public User(String deviceId) {
        super();
        this.deviceId = deviceId;
    }

    /**
     * @param category
     * @param deviceId
     */
    public User(String deviceId, List<Category> category) {
        super();
        this.deviceId = deviceId;
        this.category = category;
    }

    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("deviceId")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public User withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @JsonProperty("category")
    public List<Category> getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public User withCategory(List<Category> category) {
        this.category = category;
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

    public User withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceId).append(category).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof User) == false) {
            return false;
        }
        User rhs = ((User) other);
        return new EqualsBuilder().append(deviceId, rhs.deviceId).append(category, rhs.category).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}