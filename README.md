# LogbackTelegramAdapter
使用telegram机器人发送日志

# How to use
## 引入包
```xml
<dependency>
  <groupId>com.github.mayan31370</groupId>
  <artifactId>LogbackTelegramAdapter</artifactId>
  <version>v0.1</version>
</dependency>
```
## 修改你的`logback.xml`
```xml
<appender name="TG" class="win.mayan.utils.TelegramAppender">
  <encoder>
    <pattern>[%thread] %logger : %msg%n%ex</pattern>
  </encoder>
  <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
    <level>ERROR</level>
  </filter>
  <title>your_log_title</title>
  <telegramChatId>your_channel_id</telegramChatId>
  <telegramToken>your_bot_token</telegramToken>
</appender>
```