package com.trentonrush.communicationservice.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Communication Properties
 */
@Component
@ConfigurationProperties(prefix = "communication")
public class CommunicationProperties {

    private SendGrid sendgrid;
    private OpenAi openAi;

    /**
     * SendGrid Properties
     */
    public static class SendGrid {
        private String url;
        private String apiKey;
        private int readTimeout;
        private int connectTimeout;
        private Map<String, String> senders;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public int getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public Map<String, String> getSenders() {
            return senders;
        }

        public void setSenders(Map<String, String> senders) {
            this.senders = senders;
        }
    }

    /**
     * Open AI Properties
     */
    public static class OpenAi {
        private String url;
        private String apiKey;
        private int readTimeout;
        private int connectTimeout;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public int getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }
    }

    public SendGrid getSendgrid() {
        return sendgrid;
    }

    public void setSendgrid(SendGrid sendgrid) {
        this.sendgrid = sendgrid;
    }

    public OpenAi getOpenAi() {
        return openAi;
    }

    public void setOpenAi(OpenAi openAi) {
        this.openAi = openAi;
    }
}
