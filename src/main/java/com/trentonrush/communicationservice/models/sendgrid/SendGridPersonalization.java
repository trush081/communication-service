package com.trentonrush.communicationservice.models.sendgrid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trentonrush.communicationservice.models.MessageDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendGridPersonalization {

    @JsonProperty("to")
    List<Map<String, String>> to;

    @JsonProperty("dynamic_template_data")
    MessageDetails dynamicTemplateData;

    public SendGridPersonalization(Builder builder) {
        this.to = builder.to;
        this.dynamicTemplateData = builder.dynamicTemplateData;
    }

    public List<Map<String, String>> getTo() {
        return to;
    }

    public void setTo(List<Map<String, String>> to) {
        this.to = to;
    }

    public MessageDetails getDynamicTemplateData() {
        return dynamicTemplateData;
    }

    public void setDynamicTemplateData(MessageDetails dynamicTemplateData) {
        this.dynamicTemplateData = dynamicTemplateData;
    }

    public static class Builder {
        List<Map<String, String>> to;
        MessageDetails dynamicTemplateData;

        public Builder() {
            to = new ArrayList<>();
        }

        public Builder addTo(String email) {
            this.to.add(Map.of("email", email));
            return this;
        }

        public Builder setDynamicTemplateData(MessageDetails dynamicTemplateData) {
            this.dynamicTemplateData = dynamicTemplateData;
            return this;
        }

        public SendGridPersonalization build() {
            return new SendGridPersonalization(this);
        }
    }
}
