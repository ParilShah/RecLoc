package csc258.domain.frontend.fetchcategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import csc258.domain.frontend.category.Category;
import csc258.domain.frontend.common.ResponseDetail;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "responseDetail",
    "category"
})
public class FetchCategoryResponse implements Serializable
{

    @JsonProperty("responseDetail")
    @Valid
    private ResponseDetail responseDetail;
    @JsonProperty("category")
    @Valid
    private List<Category> category = new ArrayList<Category>();
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6730588249917930917L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FetchCategoryResponse() {
    }

    /**
     * 
     * @param category
     * @param responseDetail
     */
    public FetchCategoryResponse(ResponseDetail responseDetail, List<Category> category) {
        super();
        this.responseDetail = responseDetail;
        this.category = category;
    }

    @JsonProperty("responseDetail")
    public ResponseDetail getResponseDetail() {
        return responseDetail;
    }

    @JsonProperty("responseDetail")
    public void setResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
    }

    public FetchCategoryResponse withResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
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

    public FetchCategoryResponse withCategory(List<Category> category) {
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

    public FetchCategoryResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(responseDetail).append(category).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FetchCategoryResponse) == false) {
            return false;
        }
        FetchCategoryResponse rhs = ((FetchCategoryResponse) other);
        return new EqualsBuilder().append(responseDetail, rhs.responseDetail).append(category, rhs.category).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
