package com.trentonrush.communicationservice.models.sendgrid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendGridRequest {

    @JsonProperty("from")
    Map<String, String> from;

    @JsonProperty("personalizations")
    List<SendGridPersonalization> personalizations;

    @JsonProperty("template_id")
    String templateId;

    public SendGridRequest(Builder builder) {
        this.from = builder.from;
        this.personalizations = builder.personalizations;
        this.templateId = builder.templateId;
    }

    public Map<String, String> getFrom() {
        return from;
    }

    public void setFrom(Map<String, String> from) {
        this.from = from;
    }

    public List<SendGridPersonalization> getPersonalizations() {
        return personalizations;
    }

    public void setPersonalizations(List<SendGridPersonalization> personalizations) {
        this.personalizations = personalizations;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public static class Builder {
        private Map<String, String> from;
        private final List<SendGridPersonalization> personalizations;
        private String templateId;

        public Builder() {
            personalizations = new ArrayList<>();
        }

        public Builder setFrom(String email) {
            this.from = Map.of("email", email);
            return this;
        }

        public Builder addPersonalization(SendGridPersonalization personalization) {
            this.personalizations.add(personalization);
            return this;
        }

        public Builder setTemplateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public SendGridRequest build() {
            return new SendGridRequest(this);
        }
    }

}
