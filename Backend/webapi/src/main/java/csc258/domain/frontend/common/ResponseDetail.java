package csc258.domain.frontend.common;

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
        "responseCode",
        "responseMessage"
})
public class ResponseDetail implements Serializable {

    @JsonProperty("responseCode")
    private Long responseCode;
    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5910490341864755277L;

    /**
     * No args constructor for use in serialization
     */
    public ResponseDetail() {
    }

    /**
     * @param responseCode
     */
    public ResponseDetail(Long responseCode) {
        super();
        this.responseCode = responseCode;
    }

    /**
     * @param responseMessage
     * @param responseCode
     */
    public ResponseDetail(Long responseCode, String responseMessage) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    @JsonProperty("responseCode")
    public Long getResponseCode() {
        return responseCode;
    }

    @JsonProperty("responseCode")
    public void setResponseCode(Long responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseDetail withResponseCode(Long responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    @JsonProperty("responseMessage")
    public String getResponseMessage() {
        return responseMessage;
    }

    @JsonProperty("responseMessage")
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseDetail withResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
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

    public ResponseDetail withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(responseCode).append(responseMessage).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ResponseDetail) == false) {
            return false;
        }
        ResponseDetail rhs = ((ResponseDetail) other);
        return new EqualsBuilder().append(responseCode, rhs.responseCode).append(responseMessage, rhs.responseMessage).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
