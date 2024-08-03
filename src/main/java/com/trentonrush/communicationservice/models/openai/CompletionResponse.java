package com.trentonrush.communicationservice.models.openai;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CompletionResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("object")
    private String object;

    @JsonProperty("created")
    private long created;

    @JsonProperty("model")
    private String model;

    @JsonProperty("choices")
    private List<Choice> choices;

    @JsonProperty("usage")
    private Usage usage;

    @JsonProperty("system_fingerprint")
    private Object systemFingerprint;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public Object getSystemFingerprint() {
        return systemFingerprint;
    }

    public static class Choice {

        @JsonProperty("index")
        private int index;

        @JsonProperty("message")
        private Message message;

        @JsonProperty("logprobs")
        private Object logprobs;

        @JsonProperty("finish_reason")
        private String finishReason;

        public int getIndex() {
            return index;
        }

        public Message getMessage() {
            return message;
        }

        public Object getLogprobs() {
            return logprobs;
        }

        public String getFinishReason() {
            return finishReason;
        }

        public static class Message {

            @JsonProperty("role")
            private String role;

            @JsonProperty("content")
            private String content;

            public String getRole() {
                return role;
            }

            public String getContent() {
                return content;
            }
        }
    }

    public static class Usage {

        @JsonProperty("prompt_tokens")
        private int promptTokens;

        @JsonProperty("completion_tokens")
        private int completionTokens;

        @JsonProperty("total_tokens")
        private int totalTokens;

        public int getPromptTokens() {
            return promptTokens;
        }

        public int getCompletionTokens() {
            return completionTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }
    }
}
