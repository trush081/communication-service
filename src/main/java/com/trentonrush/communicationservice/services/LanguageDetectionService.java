package com.trentonrush.communicationservice.services;

import com.google.gson.JsonObject;
import com.trentonrush.communicationservice.configs.SendGridConfig;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.MessageDetails;
import com.trentonrush.communicationservice.models.openai.CompletionRequest;
import com.trentonrush.communicationservice.models.openai.CompletionResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

/**
 * Language Detection Service
 */
@Service
public class LanguageDetectionService {

    private final RestClient openAiRestClient;

    LanguageDetectionService(@Qualifier("openAiRestClient") RestClient openAiRestClient) {
        this.openAiRestClient = openAiRestClient;
    }

    /**
     * Calls Open AI to check if String contains inappropriate content
     * Throws error to block email if bad content detected
     *
     * @param messageDetails the details of a contact email message
     */
    public void checkLanguage(MessageDetails messageDetails) {
        String content = combineMessageContent(messageDetails.getCustomerName(), messageDetails.getSubject(), messageDetails.getContent());
        ResponseEntity<CompletionResponse> response = openAiRestClient.post()
                .body(new CompletionRequest.Builder()
                        .setPromptText(content)
                        .build())
                .retrieve()
                .toEntity(CompletionResponse.class);
        if (containsBadContent(Objects.requireNonNull(response.getBody()))){
            throw new IllegalArgumentException("Inappropriate content detected in request.");
        }
    }

    /**
     * Combines all the message detail content
     *
     * @param strings list of strings to be combined
     * @return a single String that separates content by ~
     */
    private String combineMessageContent(String... strings) {
        return String.join(" ~ ", strings);
    }

    /**
     * Checks if the response is yes or no...or anything similar
     *
     * @param response from open ai prompt
     * @return true if y or yes, false if n or no
     */
    private boolean containsBadContent(CompletionResponse response) {
        String content = response.getChoices().getFirst().getMessage().getContent().toLowerCase();
        return content.contains("y") && !content.contains("n");
    }
}
