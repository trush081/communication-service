package com.trentonrush.communicationservice.utils;

/**
 * Constants
 */
public class CommunicationConstants {

    private CommunicationConstants() {
        throw new AssertionError();
    }

    // Sources
    public static final String TRENTON_RUSH = "trentonrush";

    // Request Types
    public static final String TRANSACTIONAL = "transactional";
    public static final String CONTACT = "contact";

    // Open AI
    public static final String OPEN_AI_DEFAULT_MODEL = "gpt-4o";
    public static final String OPEN_AI_CONTENT_PROMPT = "Please respond with 'yes' or 'no'. Does the following text contain any references to inappropriate words, threats, suicide, death, vulgar language, or anything else that might be bad to send in an email? Text: ";

}
