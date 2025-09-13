package com.mt.notification.notification.controllers;

import com.mt.notification.notification.payloads.NotificationEnvelope;
import com.mt.notification.notification.payloads.NotificationPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@Slf4j
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Client sends to: /app/notifications.broadcast
    // Consumers subscribe to: /topic/notifications
    @MessageMapping("/notifications.broadcast")
    public void broadcast(NotificationPayload payload, Principal principal) {
        String from = principal != null ? principal.getName() : "anonymous";
        var envelope = NotificationEnvelope.of(payload, from, null);
        messagingTemplate.convertAndSend("/topic/notifications", envelope);
        log.debug("Broadcasted notification from {} -> /topic/notifications", from);
    }

    // Client sends to: /app/notifications.user.{userId}
    // Target user subscribes to: /user/queue/notifications
    @MessageMapping("/notifications.user.{userId}")
    public void user(NotificationPayload payload, @DestinationVariable String userId, Principal principal) {
        String from = principal != null ? principal.getName() : "anonymous";
        var envelope = NotificationEnvelope.of(payload, from, userId);
        messagingTemplate.convertAndSendToUser(userId, "/queue/notifications", envelope);
        log.debug("Sent notification from {} -> user {} (/user/queue/notifications)", from, userId);
    }

}
