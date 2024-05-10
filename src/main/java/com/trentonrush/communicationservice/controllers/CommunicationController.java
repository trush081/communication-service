package com.trentonrush.communicationservice.controllers;

import com.trentonrush.communicationservice.disptachers.CommunicationDispatcher;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.dtos.CommunicationDTO;
import org.springframework.web.bind.annotation.*;

/**
 * Communication Controller
 */
@RestController()
@RequestMapping("/v1/send")
public class CommunicationController {

    private final CommunicationDispatcher communicationDispatcher;

    public CommunicationController(CommunicationDispatcher communicationDispatcher) {
        this.communicationDispatcher = communicationDispatcher;
    }

    /**
     * Receives Request of communication that needs to be sent
     * @param communicationDTO object details that need to be sent
     */
    @PostMapping
    public void publicEndpoint(@RequestBody CommunicationDTO communicationDTO) {
        communicationDispatcher.dispatch(Communication.fromDTO(communicationDTO));
    }

}
