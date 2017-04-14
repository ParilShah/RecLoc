
package ds.frontend.domain.common;

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
        "categoryId",
        "categoryName"
})
public class Result implements Serializable {

    @JsonProperty("categoryId")
    private Long categoryId;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 3672118104220993969L;

    /**
     * No args constructor for use in serialization
     */
    public Result() {
    }

    /**
     * @param categoryName
     * @param categoryId
     */
    public Result(Long categoryId, String categoryName) {
        super();
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    @JsonProperty("categoryId")
    public Long getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Result withCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Result withCategoryName(String categoryName) {
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

    public Result withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(categoryId).append(categoryName).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Result) == false) {
            return false;
        }
        Result rhs = ((Result) other);
        return new EqualsBuilder().append(categoryId, rhs.categoryId).append(categoryName, rhs.categoryName).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
