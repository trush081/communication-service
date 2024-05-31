package com.trentonrush.communicationservice.utils;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * HTTP Response Wrapper
 */
public class BufferingClientHttpResponseWrapper implements ClientHttpResponse {
    private final ClientHttpResponse response;
    private final byte[] body;

    public BufferingClientHttpResponseWrapper(ClientHttpResponse response) throws IOException {
        this.response = response;
        this.body = StreamUtils.copyToByteArray(response.getBody());
    }

    @Override
    @Nonnull
    public HttpStatusCode getStatusCode() throws IOException {
        return response.getStatusCode();
    }

    @Override
    @Nonnull
    public String getStatusText() throws IOException {
        return response.getStatusText();
    }

    @Override
    public void close() {
        response.close();
    }

    @Override
    @Nonnull
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(body);
    }

    @Override
    @Nonnull
    public HttpHeaders getHeaders() {
        return response.getHeaders();
    }
}
