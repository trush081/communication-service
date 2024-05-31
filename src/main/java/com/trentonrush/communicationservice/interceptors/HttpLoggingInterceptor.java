package com.trentonrush.communicationservice.interceptors;

import com.trentonrush.communicationservice.utils.BufferingClientHttpResponseWrapper;
import jakarta.annotation.Nonnull;
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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Http Logging Interceptor
 */
public class HttpLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpLoggingInterceptor.class);

    /**
     * Intercepts all outgoing http requests and responses
     * @param request bring sent out
     * @param body details of request
     * @param execution calls the original request after details are logged
     * @return Http Response details
     * @throws IOException when there is error with the buffer
     */
    @Override
    @Nonnull
    public ClientHttpResponse intercept(@Nonnull HttpRequest request, @Nonnull byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ClientHttpResponse response = execution.execute(request, body);
        stopWatch.stop();
        ClientHttpResponse bufferedResponse = new BufferingClientHttpResponseWrapper(response);
        traceResponse(bufferedResponse, stopWatch.getTotalTimeMillis());
        return bufferedResponse;
    }

    /**
     * Logs details of the Request
     * @param request details being sent out
     * @param body of request being sent out
     */
    private void traceRequest(HttpRequest request, byte[] body) {
        String requestBody = new String(body, StandardCharsets.UTF_8);
        logger.info("[Request] {} {} | {}", request.getMethod(), request.getURI(), requestBody);
    }

    /**
     * Logs details of the Response
     * @param response details that are received from server
     * @param responseTime taken to receive response
     * @throws IOException when there is error with the buffer
     */
    private void traceResponse(ClientHttpResponse response, long responseTime) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            logger.warn("Unable to read response", e);
        }

        logger.info("[Response] {} {} | {}", response.getStatusCode(), responseTime, stringBuilder);
    }
}
