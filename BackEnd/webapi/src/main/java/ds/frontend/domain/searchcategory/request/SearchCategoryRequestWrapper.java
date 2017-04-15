
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
        "searchCategoryRequest"
})
public class SearchCategoryRequestWrapper implements Serializable {

    @JsonProperty("searchCategoryRequest1")
    @Valid
    private SearchCategoryRequest searchCategoryRequest;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3801514678503864221L;

    /**
     * No args constructor for use in serialization
     */
    public SearchCategoryRequestWrapper() {
    }

    /**
     * @param searchCategoryRequest
     */
    public SearchCategoryRequestWrapper(SearchCategoryRequest searchCategoryRequest) {
        super();
        this.searchCategoryRequest = searchCategoryRequest;
    }

    @JsonProperty("searchCategoryRequest")
    public SearchCategoryRequest getSearchCategoryRequest() {
        return searchCategoryRequest;
    }

    @JsonProperty("searchCategoryRequest")
    public void setSearchCategoryRequest(SearchCategoryRequest searchCategoryRequest) {
        this.searchCategoryRequest = searchCategoryRequest;
    }

    public SearchCategoryRequestWrapper withSearchCategoryRequest(SearchCategoryRequest searchCategoryRequest) {
        this.searchCategoryRequest = searchCategoryRequest;
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

    public SearchCategoryRequestWrapper withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(searchCategoryRequest).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchCategoryRequestWrapper) == false) {
            return false;
        }
        SearchCategoryRequestWrapper rhs = ((SearchCategoryRequestWrapper) other);
        return new EqualsBuilder().append(searchCategoryRequest, rhs.searchCategoryRequest).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
