package com.trentonrush.communicationservice.models;

public class Message {

    private String recipient;
    private String template;
    private String sender;
    private int responseCode;
    private MessageDetails messageDetails;

    public Message() {
        // empty constructor
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public MessageDetails getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(MessageDetails messageDetails) {
        this.messageDetails = messageDetails;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String toString() {
        return "Message{" +
                "recipient='" + recipient + '\'' +
                ", template='" + template + '\'' +
                ", sender='" + sender + '\'' +
                ", fulfillment=" + messageDetails +
                '}';
    }
}
