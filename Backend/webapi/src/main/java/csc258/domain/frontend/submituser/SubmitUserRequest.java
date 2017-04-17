
package csc258.domain.frontend.submituser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import csc258.domain.frontend.category.Category;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deviceId",
    "category"
})
public class SubmitUserRequest implements Serializable
{

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("category")
    @Valid
    private List<Category> category = new ArrayList<Category>();
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5527366094092649199L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubmitUserRequest() {
    }

    /**
     * 
     * @param category
     * @param deviceId
     */
    public SubmitUserRequest(String deviceId, List<Category> category) {
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

    public SubmitUserRequest withDeviceId(String deviceId) {
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

    public SubmitUserRequest withCategory(List<Category> category) {
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

    public SubmitUserRequest withAdditionalProperty(String name, Object value) {
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
        if ((other instanceof SubmitUserRequest) == false) {
            return false;
        }
        SubmitUserRequest rhs = ((SubmitUserRequest) other);
        return new EqualsBuilder().append(deviceId, rhs.deviceId).append(category, rhs.category).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
