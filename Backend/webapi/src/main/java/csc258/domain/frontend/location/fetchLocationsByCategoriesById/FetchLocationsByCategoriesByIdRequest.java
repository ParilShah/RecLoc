package csc258.domain.frontend.location.fetchLocationsByCategoriesById;

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
        "category"
})
public class FetchLocationsByCategoriesByIdRequest implements Serializable {

    @JsonProperty("category")
    @Valid
    private List<Category> category = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6826842586515272320L;

    /**
     * No args constructor for use in serialization
     */
    public FetchLocationsByCategoriesByIdRequest() {
    }

    /**
     * @param category
     */
    public FetchLocationsByCategoriesByIdRequest(List<Category> category) {
        super();
        this.category = category;
    }

    @JsonProperty("category")
    public List<Category> getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public FetchLocationsByCategoriesByIdRequest withCategory(List<Category> category) {
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

    public FetchLocationsByCategoriesByIdRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(category).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FetchLocationsByCategoriesByIdRequest) == false) {
            return false;
        }
        FetchLocationsByCategoriesByIdRequest rhs = ((FetchLocationsByCategoriesByIdRequest) other);
        return new EqualsBuilder().append(category, rhs.category).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}