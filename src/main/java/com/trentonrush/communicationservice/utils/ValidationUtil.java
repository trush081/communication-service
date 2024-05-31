package com.trentonrush.communicationservice.utils;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation Utility
 */
public class ValidationUtil {

    private ValidationUtil() {
        throw new AssertionError();
    }

    /**
     * Validate a communication
     * @param communication that needs to be validates
     */
    public static void validate(Communication communication) {
        if (Objects.isNull(communication)) {
            throw new IllegalArgumentException("Communication is null");
        }
        if (Strings.isEmpty(communication.getSource())) {
            throw new IllegalArgumentException("Source is empty");
        }
        if (Objects.isNull(communication.getMessageType())) {
            throw new IllegalArgumentException("MessageType is null");
        }
    }

    /**
     * Validate an email specific message
     * @param message email type that needs to be validated
     */
    public static void validateEmail(Message message) {
        if (Objects.isNull(message)) {
            throw new IllegalArgumentException("Message is null");
        }
        if (Strings.isEmpty(message.getTemplate())) {
            throw new IllegalArgumentException("Template is empty");
        }
        if (Strings.isEmpty(message.getRecipient()) && isValidEmail(message.getRecipient())) {
            throw new IllegalArgumentException("Recipient is invalid");
        }
        if (Objects.isNull(message.getMessageDetails())) {
            throw new IllegalArgumentException("Message Details is null");
        }
    }

    /**
     * Checks if the email string is valid
     * @param email string that needs to be validated
     * @return true if the email is valid
     */
    public static boolean isValidEmail(String email) {
        // Regular expression pattern for validating email addresses
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Validate an sms specific message
     * @param message sms type that needs to be validated
     */
    public static void validateSMS(Message message) {
        if (Objects.isNull(message)) {
            throw new IllegalArgumentException("Message is null");
        }
        if (Strings.isEmpty(message.getTemplate())) {
            throw new IllegalArgumentException("Template is empty");
        }
        if (Strings.isEmpty(message.getRecipient()) && isValidPhoneNumber(message.getRecipient())) {
            throw new IllegalArgumentException("Recipient is invalid");
        }
    }

    /**
     * Checks if the phone number string is valid
     * @param phoneNumber string that needs to be validated
     * @return true if the phone number is valid
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression pattern for validating phone numbers
        String pattern = "^\\+?(\\d{1,3})?[-.\\s]?(\\(\\d{3}\\))?[-.\\s]?\\d{3}[-.\\s]?\\d{4}$";
        String phoneDigits = phoneNumber.replaceAll("\\D", "");
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(phoneDigits);
        return matcher.matches();
    }
}
