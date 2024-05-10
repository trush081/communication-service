package com.trentonrush.communicationservice.dispatchers;

import com.trentonrush.communicationservice.disptachers.CommunicationDispatcher;
import com.trentonrush.communicationservice.handlers.CommunicationHandler;
import com.trentonrush.communicationservice.handlers.EmailHandler;
import com.trentonrush.communicationservice.handlers.SMSHandler;
import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.enums.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class CommunicationDispatcherTest {

    private EmailHandler mockEmailHandler;
    private SMSHandler mockSMSHandler;
    private CommunicationDispatcher dispatcher;

    @BeforeEach
    public void setup() {
        mockEmailHandler = mock(EmailHandler.class);
        when(mockEmailHandler.getMessageType()).thenCallRealMethod();
        mockSMSHandler = mock(SMSHandler.class);
        when(mockSMSHandler.getMessageType()).thenCallRealMethod();

        List<CommunicationHandler> communicationHandlers = List.of(mockEmailHandler, mockSMSHandler);
        dispatcher = new CommunicationDispatcher(communicationHandlers);
    }

    @Test
    void dispatch_email() {
        Communication communication = buildCommunication("trentonrush", MessageType.EMAIL);
        dispatcher.dispatch(communication);
        verify(mockEmailHandler, times(1)).send(communication);
    }

    @Test
    void dispatch_sms() {
        Communication communication = buildCommunication("trentonrush", MessageType.SMS);
        dispatcher.dispatch(communication);
        verify(mockSMSHandler, times(1)).send(communication);
    }

    @Test
    void dispatch_nonExistentMessageType() {
        Communication communication = buildCommunication("trentonrush", MessageType.OTHER);
        dispatcher.dispatch(communication);
        verify(mockSMSHandler, times(0)).send(communication);
    }

    public static Communication buildCommunication(String source, MessageType messageType) {
        Communication communication = new Communication();
        communication.setSource(source);
        communication.setMessageType(messageType);
        return communication;
    }
}
