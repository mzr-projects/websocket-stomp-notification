package com.mt.notification.notification.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

@Slf4j
public class StompErrorHandler extends StompSubProtocolErrorHandler {

    @Override
    public Message<byte[]> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex) {
        log.warn("STOMP error: {}", ex.getMessage(), ex);
        return super.handleClientMessageProcessingError(clientMessage, ex);
    }
}
