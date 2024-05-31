package com.trentonrush.communicationservice.utils;

/**
 * General Utilities
 */
public class CommunicationUtil {

    private CommunicationUtil() {
        throw new AssertionError();
    }

    /**
     * Sanitize a given phone number to only contain numbers
     * @param phoneNumber that needs to be sanitized
     * @return string of sanitized number
     */
    public static String sanitizePhoneNumber(String phoneNumber) {
        // Remove all non-digit from phone number
        String sanitizedNumber = phoneNumber.replaceAll("\\D", "");

        // Remove the leading "1"
        if (sanitizedNumber.startsWith("1") && sanitizedNumber.length() == 11 ) {
            sanitizedNumber = sanitizedNumber.substring(1);
        // US numbers only
        } else if (sanitizedNumber.length() > 10) {
            return null;
        }

        // Check if fewer than 9 digits (Scam check)
        if (sanitizedNumber.length() < 9) {
            return null;
        }

        return sanitizedNumber;
    }
}
