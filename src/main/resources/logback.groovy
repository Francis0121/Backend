appender('console', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "[%-5level%d{yyyy-MM-dd HH:mm:ss}]-[%thread] %logger - %msg%n"
    }
}

root(INFO, ['console'])