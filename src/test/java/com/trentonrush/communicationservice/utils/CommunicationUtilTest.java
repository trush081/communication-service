package com.trentonrush.communicationservice.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommunicationUtilTest {

    @Test
    void sanitizePhoneNumber_allDigits() {
        assertEquals("1234567890", CommunicationUtil.sanitizePhoneNumber("1234567890"));
    }

    @Test
    void sanitizePhoneNumber_formatted() {
        assertEquals("1234567890", CommunicationUtil.sanitizePhoneNumber("(123) 456-7890"));
    }

    @Test
    void sanitizePhoneNumber_moreThanTenDigits() {
        assertEquals("1234567890", CommunicationUtil.sanitizePhoneNumber("+1 (123) 456-7890"));
    }

    @Test
    void sanitizePhoneNumber_nonUS() {
        assertNull(CommunicationUtil.sanitizePhoneNumber("+12 (123) 456-7890"));
    }

    @Test
    void sanitizePhoneNumber_lessThanNineDigits() {
        assertNull(CommunicationUtil.sanitizePhoneNumber("17890"));
    }
}
