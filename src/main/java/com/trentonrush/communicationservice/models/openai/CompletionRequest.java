package com.trentonrush.communicationservice.models.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trentonrush.communicationservice.utils.CommunicationConstants;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompletionRequest {

    @JsonProperty("model")
    private String model;

    @JsonProperty("messages")
    private List<Message> messages;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("max_tokens")
    private int maxTokens;

    @JsonProperty("top_p")
    private double topP;

    @JsonProperty("frequency_penalty")
    private double frequencyPenalty;

    @JsonProperty("presence_penalty")
    private double presencePenalty;

    private CompletionRequest(Builder builder) {
        this.model = builder.model;
        this.messages = builder.messages;
        this.temperature = builder.temperature;
        this.maxTokens = builder.maxTokens;
        this.topP = builder.topP;
        this.frequencyPenalty = builder.frequencyPenalty;
        this.presencePenalty = builder.presencePenalty;
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public double getTopP() {
        return topP;
    }

    public double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public double getPresencePenalty() {
        return presencePenalty;
    }

    public static class Builder {
        private String model = CommunicationConstants.OPEN_AI_DEFAULT_MODEL;
        private final List<Message> messages = new ArrayList<>();
        private double temperature = 1.0;
        private int maxTokens = 1;
        private double topP = 1.0;
        private double frequencyPenalty = 0.0;
        private double presencePenalty = 0.0;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setPromptText(String text) {
            List<Content> contents = new ArrayList<>();
            contents.add(new Content("text", CommunicationConstants.OPEN_AI_CONTENT_PROMPT + text ));
            this.messages.add(new Message("system", contents));
            return this;
        }

        public Builder setTemperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder setMaxTokens(int maxTokens) {
            this.maxTokens = maxTokens;
            return this;
        }

        public Builder setTopP(double topP) {
            this.topP = topP;
            return this;
        }

        public Builder setFrequencyPenalty(double frequencyPenalty) {
            this.frequencyPenalty = frequencyPenalty;
            return this;
        }

        public Builder setPresencePenalty(double presencePenalty) {
            this.presencePenalty = presencePenalty;
            return this;
        }

        public CompletionRequest build() {
            return new CompletionRequest(this);
        }
    }

    public static class Message {
        private String role;
        private List<Content> content;

        public Message(String role, List<Content> content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public List<Content> getContent() {
            return content;
        }
    }

    public static class Content {
        private String type;
        private String text;

        public Content(String type, String text) {
            this.type = type;
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    }
}
