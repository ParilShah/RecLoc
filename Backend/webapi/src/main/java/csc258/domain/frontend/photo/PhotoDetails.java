package csc258.domain.frontend.photo;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileName",
        "file"
})
public class PhotoDetails implements Serializable {

    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("file")
    private MultipartFile file;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7910261071030984859L;

    /**
     * No args constructor for use in serialization
     */
    public PhotoDetails() {
    }

    /**
     * @param file
     * @param fileName
     */
    public PhotoDetails(String fileName, MultipartFile file) {
        super();
        this.fileName = fileName;
        this.file = file;
    }

    /**
     * @param file
     * @param fileName
     */
    public PhotoDetails(String fileName, String filePath, MultipartFile file) {
        super();
        this.fileName = fileName;
        this.filePath = filePath;
        this.file = file;
    }

    @JsonProperty("fileName")
    public String getFileName() {
        return fileName;
    }

    @JsonProperty("fileName")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public PhotoDetails withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @JsonProperty("filePath")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("filePath")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public PhotoDetails withFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @JsonProperty("file")
    public MultipartFile getFile() {
        return file;
    }

    @JsonProperty("file")
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public PhotoDetails withFile(MultipartFile file) {
        this.file = file;
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

    public PhotoDetails withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(fileName).append(file).append(filePath).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PhotoDetails) == false) {
            return false;
        }
        PhotoDetails rhs = ((PhotoDetails) other);
        return new EqualsBuilder().append(fileName, rhs.fileName).append(filePath, rhs.filePath).append(file, rhs.file).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}