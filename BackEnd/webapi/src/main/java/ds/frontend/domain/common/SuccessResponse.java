
package ds.frontend.domain.common;

import com.fasterxml.jackson.annotation.*;
import ds.frontend.domain.category.Category;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Categories"
})
public class SuccessResponse {

    @JsonProperty("Categories")
    @Valid
    private List<Category> categories = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public SuccessResponse() {
    }

    /**
     *
     * @param categories
     */
    public SuccessResponse(List<Category> categories) {
        super();
        this.categories = categories;
    }

    @JsonProperty("Categories")
    public List<Category> getCategories() {
        return categories;
    }

    @JsonProperty("Categories")
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public SuccessResponse withCategories(List<Category> categories) {
        this.categories = categories;
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

    public SuccessResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categories).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SuccessResponse) == false) {
            return false;
        }
        SuccessResponse rhs = ((SuccessResponse) other);
        return new EqualsBuilder().append(categories, rhs.categories).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
