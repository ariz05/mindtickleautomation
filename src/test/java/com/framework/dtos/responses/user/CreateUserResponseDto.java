package com.framework.dtos.responses.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "type",
        "message"
})
@Generated("jsonschema2pojo")
public class CreateUserResponseDto {

    @JsonProperty("code")
    private String code;
    @JsonProperty("type")
    private String type;
    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    public String getCode() {
        return code;
    }
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

}
