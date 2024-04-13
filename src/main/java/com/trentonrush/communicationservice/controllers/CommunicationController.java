package com.trentonrush.communicationservice.controllers;

import com.trentonrush.communicationservice.disptachers.CommunicationDispatcher;
import com.trentonrush.communicationservice.models.Communication;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping(path = "api")
public class CommunicationController {

    private final CommunicationDispatcher communicationDispatcher;

    public CommunicationController(CommunicationDispatcher communicationDispatcher) {
        this.communicationDispatcher = communicationDispatcher;
    }
    @PostMapping(value = "/public")
    public void publicEndpoint(@RequestBody Communication communication) {
        communicationDispatcher.dispatch(communication);
    }

}
