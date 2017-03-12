
package ds.frontend.domain.common;

import com.fasterxml.jackson.annotation.*;
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
        "Result"
})
public class SuccessResponse implements Serializable {

    @JsonProperty("Result")
    @Valid
    private List<Result> result = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4831613610075304827L;

    /**
     * No args constructor for use in serialization
     */
    public SuccessResponse() {
    }

    /**
     * @param result
     */
    public SuccessResponse(List<Result> result) {
        super();
        this.result = result;
    }

    @JsonProperty("Result")
    public List<Result> getResult() {
        return result;
    }

    @JsonProperty("Result")
    public void setResult(List<Result> result) {
        this.result = result;
    }

    public SuccessResponse withResult(List<Result> result) {
        this.result = result;
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
        return new HashCodeBuilder().append(result).append(additionalProperties).toHashCode();
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
        return new EqualsBuilder().append(result, rhs.result).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
