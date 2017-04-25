package csc258.domain.frontend.user.fetchuser;

import com.fasterxml.jackson.annotation.*;
import csc258.domain.frontend.common.ResponseDetail;
import csc258.domain.frontend.user.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "responseDetail",
        "user"
})
public class FetchAllUserResponse implements Serializable {

    @JsonProperty("responseDetail")
    @Valid
    private ResponseDetail responseDetail;
    @JsonProperty("user")
    @Valid
    private User user;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 779584814921703048L;

    /**
     * No args constructor for use in serialization
     */
    public FetchAllUserResponse() {
    }

    /**
     * @param responseDetail
     * @param user
     */
    public FetchAllUserResponse(ResponseDetail responseDetail, User user) {
        super();
        this.responseDetail = responseDetail;
        this.user = user;
    }

    @JsonProperty("responseDetail")
    public ResponseDetail getResponseDetail() {
        return responseDetail;
    }

    @JsonProperty("responseDetail")
    public void setResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
    }

    public FetchAllUserResponse withResponseDetail(ResponseDetail responseDetail) {
        this.responseDetail = responseDetail;
        return this;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    public FetchAllUserResponse withUser(User user) {
        this.user = user;
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

    public FetchAllUserResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(responseDetail).append(user).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FetchAllUserResponse) == false) {
            return false;
        }
        FetchAllUserResponse rhs = ((FetchAllUserResponse) other);
        return new EqualsBuilder().append(responseDetail, rhs.responseDetail).append(user, rhs.user).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
