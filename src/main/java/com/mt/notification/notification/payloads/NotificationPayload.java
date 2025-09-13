package com.mt.notification.notification.payloads;

import java.util.Map;

public record NotificationPayload(String type, String message, Map<String, Object> data) {
}
