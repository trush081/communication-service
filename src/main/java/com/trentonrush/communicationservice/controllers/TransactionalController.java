package com.trentonrush.communicationservice.controllers;

import com.trentonrush.communicationservice.disptachers.CommunicationDispatcher;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.dtos.TransactionalDTO;
import org.springframework.web.bind.annotation.*;

/**
 * Communication Controller
 */
@RestController
@RequestMapping("/v1/transactional")
public class TransactionalController {

    private final CommunicationDispatcher communicationDispatcher;

    public TransactionalController(CommunicationDispatcher communicationDispatcher) {
        this.communicationDispatcher = communicationDispatcher;
    }

    /**
     * Receives Request of communication that needs to be sent
     * @param transactionalDTO object details that need to be sent
     */
    @PostMapping("/send")
    public void send(@RequestBody TransactionalDTO transactionalDTO) {
        communicationDispatcher.dispatch(Communication.fromDTO(transactionalDTO));
    }

}
