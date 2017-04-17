package csc258.domain.frontend.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
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
    "responseCode",
    "responseMessage"
})
public class ResponseDetail implements Serializable
{

    @JsonProperty("responseCode")
    private Integer responseCode;
    @JsonProperty("responseMessage")
    private String responseMessage;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5910490341864755277L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseDetail() {
    }

    /**
     * 
     * @param responseCode
     */
    public ResponseDetail(Integer responseCode) {
        super();
        this.responseCode = responseCode;
    }

    /**
     *
     * @param responseMessage
     * @param responseCode
     */
    public ResponseDetail(Integer responseCode, String responseMessage) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    @JsonProperty("responseCode")
    public Integer getResponseCode() {
        return responseCode;
    }

    @JsonProperty("responseCode")
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseDetail withResponseCode(Integer responseCode) {
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
