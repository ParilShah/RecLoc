
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
        "errorDetail"
})
public class ErrorResponse implements Serializable {

    @JsonProperty("errorDetail")
    private String errorDetail;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6161460576269821286L;

    /**
     * No args constructor for use in serialization
     */
    public ErrorResponse() {
    }

    /**
     * @param errorDetail
     */
    public ErrorResponse(String errorDetail) {
        super();
        this.errorDetail = errorDetail;
    }

    @JsonProperty("errorDetail")
    public String getErrorDetail() {
        return errorDetail;
    }

    @JsonProperty("errorDetail")
    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public ErrorResponse withErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
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

    public ErrorResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(errorDetail).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ErrorResponse) == false) {
            return false;
        }
        ErrorResponse rhs = ((ErrorResponse) other);
        return new EqualsBuilder().append(errorDetail, rhs.errorDetail).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
