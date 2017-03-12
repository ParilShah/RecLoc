
package ds.frontend.domain.searchcategory.response;

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
        "searchCategoryResponse"
})
public class SearchCategoryResponseWrapper implements Serializable {

    @JsonProperty("searchCategoryResponse")
    @Valid
    private SearchCategoryResponse searchCategoryResponse;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1415706750004099213L;

    /**
     * No args constructor for use in serialization
     */
    public SearchCategoryResponseWrapper() {
    }

    /**
     * @param searchCategoryResponse
     */
    public SearchCategoryResponseWrapper(SearchCategoryResponse searchCategoryResponse) {
        super();
        this.searchCategoryResponse = searchCategoryResponse;
    }

    @JsonProperty("searchCategoryResponse")
    public SearchCategoryResponse getSearchCategoryResponse() {
        return searchCategoryResponse;
    }

    @JsonProperty("searchCategoryResponse")
    public void setSearchCategoryResponse(SearchCategoryResponse searchCategoryResponse) {
        this.searchCategoryResponse = searchCategoryResponse;
    }

    public SearchCategoryResponseWrapper withSearchCategoryResponse(SearchCategoryResponse searchCategoryResponse) {
        this.searchCategoryResponse = searchCategoryResponse;
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

    public SearchCategoryResponseWrapper withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(searchCategoryResponse).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SearchCategoryResponseWrapper) == false) {
            return false;
        }
        SearchCategoryResponseWrapper rhs = ((SearchCategoryResponseWrapper) other);
        return new EqualsBuilder().append(searchCategoryResponse, rhs.searchCategoryResponse).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
