package com.trentonrush.communicationservice.controllers;

import com.trentonrush.communicationservice.disptachers.CommunicationDispatcher;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.dtos.ContactDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Communication Controller
 */
@RestController
@RequestMapping("/v1/contact")
public class ContactController {
    private final CommunicationDispatcher communicationDispatcher;

    public ContactController(CommunicationDispatcher communicationDispatcher) {
        this.communicationDispatcher = communicationDispatcher;
    }

    /**
     * Receives Request of communication that needs to be sent
     * @param contactDTO object details that need to be sent
     */
    @PostMapping("/send")
    public void send(@RequestBody ContactDTO contactDTO) {
        communicationDispatcher.dispatch(Communication.fromDTO(contactDTO));
    }
}
