
package ds.frontend.domain.searchcategory.request;

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
        "deviceId",
        "categoryName"
})
public class SearchCategoryRequest implements Serializable {

    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3143936135015186374L;

    /**
     * No args constructor for use in serialization
     */
    public SearchCategoryRequest() {
    }

    /**
     * @param categoryName
     * @param deviceId
     */
    public SearchCategoryRequest(String deviceId, String categoryName) {
        super();
        this.deviceId = deviceId;
        this.categoryName = categoryName;
    }

    @JsonProperty("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("deviceId")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public SearchCategoryRequest withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    @JsonProperty("categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    @JsonProperty("categoryName")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public SearchCategoryRequest withCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public SearchCategoryRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceId).append(categoryName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchCategoryRequest) == false) {
            return false;
        }
        SearchCategoryRequest rhs = ((SearchCategoryRequest) other);
        return new EqualsBuilder().append(deviceId, rhs.deviceId).append(categoryName, rhs.categoryName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
