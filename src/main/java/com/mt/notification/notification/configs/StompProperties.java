package com.mt.notification.notification.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "messaging.stomp")
public class StompProperties {

    private String relayHost = "localhost";
    private int relayPort = 61613;
    private String virtualHost = "/";
    private String clientLogin = "guest";
    private String clientPasscode = "guest";
    private String systemLogin = "guest";
    private String systemPasscode = "guest";
    private Heartbeat heartbeat = new Heartbeat();
    private java.util.List<String> allowedOrigins = java.util.List.of();

    @Getter
    @Setter
    public static class Heartbeat {
        private long sendInterval = 10000L;
        private long receiveInterval = 10000L;
    }
}
