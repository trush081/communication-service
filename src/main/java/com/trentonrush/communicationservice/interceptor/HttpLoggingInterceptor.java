package com.trentonrush.communicationservice.interceptor;

import com.trentonrush.communicationservice.utils.BufferingClientHttpResponseWrapper;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ClientHttpResponse response = execution.execute(request, body);
        stopWatch.stop();
        ClientHttpResponse bufferedResponse = new BufferingClientHttpResponseWrapper(response);
        traceResponse(bufferedResponse, stopWatch.getTotalTimeMillis());

        return bufferedResponse;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        String requestBody = new String(body, StandardCharsets.UTF_8);

        logger.info("[Request] {} {} | {}", request.getMethod(), request.getURI(), requestBody);
    }

    private void traceResponse(ClientHttpResponse response, long totalTimeMillis) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        } catch (Exception exception) {
            logger.warn("Unable to read response", exception);
        }

        logger.info("[Response] {} in {}ms | {}", response.getStatusCode(), totalTimeMillis, inputStringBuilder);
    }
}
