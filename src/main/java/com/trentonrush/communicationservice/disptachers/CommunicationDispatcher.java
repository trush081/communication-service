package com.trentonrush.communicationservice.disptachers;

import com.trentonrush.communicationservice.handlers.CommunicationHandler;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.enums.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class CommunicationDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(CommunicationDispatcher.class);

    private final Map<MessageType, CommunicationHandler> handlers;
    public CommunicationDispatcher(List<CommunicationHandler> communicationHandlers) {
        handlers = new EnumMap<>(MessageType.class);
        for (CommunicationHandler handler : communicationHandlers) {
            handlers.put(handler.getMessageType(), handler);
        }
    }

    public void dispatch(Communication communication) {
        CommunicationHandler handler = handlers.get(communication.getMessageType());
        if (handler != null) {
            handler.send(communication);
        } else {
            logger.warn("No handler registered for message type: {}", communication.getMessageType());
        }
    }
}
