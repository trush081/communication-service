package com.trentonrush.communicationservice.handlers;

import com.trentonrush.communicationservice.models.Communication;
import com.trentonrush.communicationservice.models.Message;
import com.trentonrush.communicationservice.models.MessageDetails;
import com.trentonrush.communicationservice.models.enums.MessageType;
import com.trentonrush.communicationservice.repositories.CommunicationRepository;
import com.trentonrush.communicationservice.services.SendGridService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailHandlerTest {

    private SendGridService sendGridService;
    private CommunicationRepository communicationRepository;
    private EmailHandler emailHandler;


    @BeforeEach
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        sendGridService = mock(SendGridService.class);
        communicationRepository = mock(CommunicationRepository.class);
        emailHandler = new EmailHandler(sendGridService, communicationRepository);
        setFieldValue(emailHandler, "trentonrush", "trentonrush@example.com");
        setFieldValue(emailHandler, "ukpray", "ukpray@example.com");
    }

    @Test
    void getMessageType() {
        assertEquals(MessageType.EMAIL, emailHandler.getMessageType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"trentonrush", "granitesolutions", "ukpray", "unknown"})
    void send_trentonrush(String source) {
        Communication communication = buildCommunication(source, MessageType.EMAIL);
        doNothing().when(sendGridService).sendEmail(any(Message.class));
        doReturn(communication).when(communicationRepository).save(any(Communication.class));

        emailHandler.send(communication);
        assertEquals(Objects.equals(source, "ukpray") ? "ukpray@example.com" : "trentonrush@example.com", communication.getMessage().getSender());
        verify(communicationRepository, times(2)).save(communication);
        verify(sendGridService, times(1)).sendEmail(any(Message.class));
    }

    public static Communication buildCommunication(String source, MessageType messageType) {
        Communication communication = new Communication();
        communication.setSource(source);
        communication.setMessageType(messageType);
        communication.setMessage(buildMessage());
        return communication;
    }

    public static Message buildMessage() {
        Message message = new Message();
        message.setRecipient("email@example.com");
        message.setTemplate("test");
        message.setMessageDetails(new MessageDetails());
        return message;
    }

    // Helper method to set field value using reflection
    private void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }
}
