package csc258.domain.frontend.user.submituser;

import com.fasterxml.jackson.annotation.*;
import csc258.domain.frontend.common.ResponseDetail;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "responseDetail"
})
public class SubmitUserResponse implements Serializable {

    @JsonProperty("responseDetail")
    @Valid
    private ResponseDetail responseDetail;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8278802927549241200L;

    /**
     * No args constructor for use in serialization
     */
    public SubmitUserResponse() {
    }

    /**
     * @param responseDetail
     */
    public SubmitUserResponse(ResponseDetail responseDetail) {
        super();
        this.responseDetail = responseDetail;
    }

    @JsonProperty("responseDetail")
    public ResponseDetail getResponseDetail() {
        return responseDetail;
    }

    @JsonProperty("responseDetail")
    public void setResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
    }

    public SubmitUserResponse withResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
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

    public SubmitUserResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(responseDetail).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SubmitUserResponse) == false) {
            return false;
        }
        SubmitUserResponse rhs = ((SubmitUserResponse) other);
        return new EqualsBuilder().append(responseDetail, rhs.responseDetail).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
