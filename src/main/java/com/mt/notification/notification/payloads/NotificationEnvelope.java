package com.mt.notification.notification.payloads;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record NotificationEnvelope(
        String id, String type, String message, Map<String, Object> data,
        String fromUserId, String toUserId, Instant timestamp
) {
    public static NotificationEnvelope of(NotificationPayload p, String from, String to) {
        return new NotificationEnvelope(
                UUID.randomUUID().toString(),
                p.type(),
                p.message(),
                p.data(),
                from,
                to,
                Instant.now()
        );
    }
}
