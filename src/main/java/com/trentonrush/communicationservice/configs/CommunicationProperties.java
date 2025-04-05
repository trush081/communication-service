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
    private Twilio twilio;
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
        private Map<String, String> recipients;

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

        public Map<String, String> getRecipients() {
            return recipients;
        }

        public void setRecipients(Map<String, String> recipients) {
            this.recipients = recipients;
        }
    }

    /**
     * Twilio Properties
     */
    public static class Twilio {
        private String url;
        private String authToken;
        private String accountSid;
        private String messagingServiceSid;
        private Map<String, String> senders;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, String> getSenders() {
            return senders;
        }

        public void setSenders(Map<String, String> senders) {
            this.senders = senders;
        }

        public String getAccountSid() {
            return accountSid;
        }

        public void setAccountSid(String accountSid) {
            this.accountSid = accountSid;
        }

        public String getMessagingServiceSid() {
            return messagingServiceSid;
        }

        public void setMessagingServiceSid(String messagingServiceSid) {
            this.messagingServiceSid = messagingServiceSid;
        }

        public String getAuthToken() {
            return authToken;
        }

        public void setAuthToken(String authToken) {
            this.authToken = authToken;
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

    public Twilio getTwilio() {
        return twilio;
    }

    public void setTwilio(Twilio twilio) {
        this.twilio = twilio;
    }

    public OpenAi getOpenAi() {
        return openAi;
    }

    public void setOpenAi(OpenAi openAi) {
        this.openAi = openAi;
    }
}
