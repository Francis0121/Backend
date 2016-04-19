appender('console', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "[%-5level %d{yyyy-MM-dd HH:mm:ss}]-[%thread] %logger - %msg%n"
    }
}

logger('org.springframework', WARN, ['console'], false)
logger('org.springframework.jdbc.core.JdbcTemplate', DEBUG, ['console'], false)
logger('org.springframework.jdbc.core.StatementCreatorUtils', TRACE, ['console'], false)

root(DEBUG, ['console'])